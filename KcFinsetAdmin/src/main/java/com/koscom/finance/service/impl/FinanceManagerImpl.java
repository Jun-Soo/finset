package com.koscom.finance.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.domain.FinsetInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocVO;
import com.koscom.edoc.service.EdocManager;
import com.koscom.env.service.CodeManager;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.service.FcCodeManager;
import com.koscom.finance.dao.FinanceMapper;
import com.koscom.finance.model.FcEdocForm;
import com.koscom.finance.model.FcEdocVO;
import com.koscom.finance.model.FcLoanInfo;
import com.koscom.finance.model.FcWorkPositionForm;
import com.koscom.finance.model.FcWorkPositionVO;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finance.service.FcWorkPositionManager;
import com.koscom.finance.service.FinanceManager;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.finset.service.FinsetManager;
import com.koscom.goods.dao.GoodsMapper;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.loan.service.LoanManager;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;
import com.koscom.scrap.model.RespHealthPaymentdtlVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.FepSocket;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;
import com.koscom.util.JSTLFunction;
import com.koscom.util.LogUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service("financeManager")
@PropertySource("classpath:prop/jdbc.properties")
public class FinanceManagerImpl implements FinanceManager {

    private static final Logger logger = LoggerFactory.getLogger(FinanceManagerImpl.class);
    @Autowired
    private PrepareManager prepareManager;

    @Autowired
    private CodeManager codeManager;

    @Autowired
    private FinsetManager finsetManager;

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LoanManager loanManager;

    @Autowired
    private GoodsManager goodsManager;

    @Autowired
    private ApplyManager applyManager;

    @Autowired
    private FcCodeManager fcCodeManager;

    @Autowired
    private FincorpManager fincorpManager;

    @Autowired
    private EdocManager edocManager;

    @Autowired
    private ScrapManager scrapManager;

    @Resource
    public Environment environment;

    @Autowired
    private FcWorkPositionManager fcWorkPositionManager;

    public static final String cd_fc_pepper = "0010471"; //페퍼저축은행 금융사 코드
    public static final String HSB001 = "HSB001";
    public static final String RX = "RX";
    public static final String TX = "TX";
    public static final String L  = "L" ;
    public static final String Y  = "Y" ;
    public static final String N  = "N" ;
    public static final String T  = "T" ;
    public static final String parent_code_group = "parent_code_group";
    public static final String YN_TEST_MODE   = "YN_TEST_MODE";
    public static final String YN_THREAD_MODE = "YN_THREAD_MODE";
    public static final String _CONF_SYSTEM   = "_CONF_SYSTEM";
    public static final String FEP_PROXY_IP   = "FEP_PROXY_IP";
    public static final String FEP_PROXY_PORT = "FEP_PROXY_PORT";
    public static final String FEP_PROXY_SEC_IP   = "FEP_PROXY_SEC_IP";
    public static final String FEP_PROXY_SEC_PORT = "FEP_PROXY_SEC_PORT";
    public static final String A1             = "A1";
    public static final String TRANS_1ST      = "001";
    public static final String TRANS_2ND      = "002";
    public static final String TRANS_3TH      = "003";
    public static final String TYPE_FLEX_TCP  = "01";
    public static final String TYPE_FLEX_JSON = "02";

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * setFcEdocData
     * 요청전문 데이터 설정
     * CB, Scrap 정보 담기
     * @param fcEdocSendVO
     * @param type
     * @return TxFcTransmitVO
     **/
    private TxFcTransmitVO setFcEdocData(TxFcTransmitVO fcEdocSendVO, String type){

        int hd_cd_if = 1000;        // HEADER 전문종별코드
        int hd_cd_result = 0000; // HEADER 응답코드 0000: 정상 9999 : 오류
        String jb_tp_compsize = null; //기업규모
        String cd_fc   = null;
        String no_edoc = null;//전문번호
        int hd_seq_edoc_no = 0;//전문일련번호

        List<RespHealthPaymentdtlVO> listRespHealthPaymentdtl = null;

        long     amt_year_income = 0   ;//연소득금액
        Date     date            = null;
        Calendar calendar        = null;
        String   today           = null;
        long     hd_dt_send      = 0   ; //전문전송시간
        long     l_today         = 0   ;
        String   no_person       = null; //사용자고유번호
        String   loan_code       = null;//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출
        RespHealthPaymentdtlVO list = null;
        FcWorkPositionVO fcWorkPositionVO = null;
        String cd_job = null;           //코드
        String company_seg = null;      //company_seg
        if (fcEdocSendVO !=null) {
            cd_fc     = fcEdocSendVO.getCd_fc();
            no_edoc   = fcEdocSendVO.getNo_edoc();
            no_person = fcEdocSendVO.getNo_person();
            loan_code = fcEdocSendVO.getLoan_code();
            fcEdocSendVO.setHd_cd_partner(cd_fc  ); // HEADER 금융사코드
            fcEdocSendVO.setHd_cd_service(no_edoc); // HEADER 서비스 구분

            fcEdocSendVO.setCd_service(no_edoc);  // BODY 서비스 구분
            fcEdocSendVO.setCd_fc     (cd_fc  );  // BODY 금융사 코드

            //전문 전송 시간 설정
            calendar = Calendar.getInstance();
            date     = calendar.getTime();
            /**
             * 오늘 : yyyyMMddHHmmss
             */
            today      = format.format(date);
            hd_dt_send = NumberUtil.parseLong(today);

            //전문일련번호 생성
            hd_seq_edoc_no = financeMapper.getSeqEdocNo();

            if(TRANS_1ST.equals(type)){//1차전문 값 설정
                //기업규모 : 한글명 => code_value 변경
                jb_tp_compsize = fcEdocSendVO.getJb_tp_compsize();
                if( StringUtil.isNotEmpty(jb_tp_compsize) ){
                    jb_tp_compsize = codeManager.getCodeId("cd_size_comp", jb_tp_compsize);
                    fcEdocSendVO.setJb_tp_compsize(jb_tp_compsize);
                }

                //연소득추정금액 계산
                int nAveCnt = 0;
                long nTolAmt = 0;  // 합계금액
                long nAveAmt = 0; // 평균금액
                double insuRate = NumberUtil.stringToDouble(codeManager.getCodeName("cd_premium_rate", "NHIS_PREMIUM_RATE"), 0); // 건강보험보험료율

                /*
                직장인인 경우         : 보험료율 / 100 / 2
                개인사업자의 경우 : 보험료율 / 100
                */
                double dInsuRate = "01".equals(loan_code) ? (insuRate / 100 / 2) : (insuRate / 100);

                //Front에서 연소득금액을 입력받더라도 스크래핑을 하는 경우 스크래핑 결과로 대체
                listRespHealthPaymentdtl = scrapManager.getRespHealthPaymentdtl(no_person);


                //내역이 존재하는 경우
                if( listRespHealthPaymentdtl != null && listRespHealthPaymentdtl.size() > 0 ){

                    nAveCnt = listRespHealthPaymentdtl.size();
                    //내역 건수가 3건 이상 인 경우 최신 3건으로 설정
                    if( nAveCnt >= 3 ) {
                        nAveCnt = 3;
                    }

                    for(int i = 0; i < nAveCnt; i++){
                        list = listRespHealthPaymentdtl.get(i);
                        nTolAmt += list.getNotice_amt_health_insu_amt();
                    }
                    nAveAmt = nTolAmt / nAveCnt;
                    amt_year_income = (long)(((double)(nAveAmt / dInsuRate)) * (double)12);

                    fcEdocSendVO.setAmt_year_income(amt_year_income);

                } else {
                    // fcEdocSendVO.setAmt_year_income(0);
                }
                //페퍼 테스트 서버용 주민번호, DI 고정 , 아래 주민번호와 DI 를 사용하지 않으면 테스트 진행이 안됨. KCB 조회용 테스트 주민번호임.
                if(Y.equals(codeManager.getNvlCodeName(_CONF_SYSTEM,YN_TEST_MODE,N)))
                {
                    //20180319 : 포이시스 조선숙 차장님 요청
                    //페퍼 저축은행일 경우에 연소득이 0일 경우 0보다 큰 수로 셋팅 요청
                    if ( cd_fc_pepper.equals( fcEdocSendVO.getCd_fc() ) ) {

                        // 연소득이 0 -> 0보다 큰 값으로 셋팅
//                      if(fcEdocSendVO.getAmt_year_income() <= 0){
                        fcEdocSendVO.setAmt_year_income(52000000);
//                      }
                    }
                }

                //이지론 1차 전문 공통부
                fcEdocSendVO.setHd_cd_if     (hd_cd_if      );
                fcEdocSendVO.setHd_cd_result (hd_cd_result  );
                fcEdocSendVO.setHd_err_msg   (""            );
                fcEdocSendVO.setHd_seq       (hd_seq_edoc_no);
                fcEdocSendVO.setHd_dt_send   (hd_dt_send    );
                fcEdocSendVO.setHd_filler    (""            );
                if( "01".equals(loan_code) || "02".equals(loan_code) ) { //신용대출
                    //신용대출인 경우 대출기간 설정(Default : 02 -> 24개월)
                    fcEdocSendVO.setCd_loan_term("02");
                }
                else if( "03".equals(loan_code) ){ //주택담보대출
                }
                else if( "04".equals(loan_code) ){ //자동차담보대출
                }
                else{
                    //신용대출인 경우 대출기간 설정(Default : 02 -> 24개월)
                    fcEdocSendVO.setCd_loan_term("02");
                }

                // 페퍼저축은행인 경우 직업직위코드 설정
                if( cd_fc_pepper.equals(cd_fc)){
                    fcWorkPositionVO = fcWorkPositionManager.getPepperWorkPosition(fcEdocSendVO);

                    cd_job      = fcWorkPositionVO.getCd_job();
                    company_seg = fcWorkPositionVO.getCompany_seg();

                    //fcEdocSendVO.setCd_duty_comp(fcWorkPositionVO.getCd_work_position());
                    fcEdocSendVO.setCd_duty_comp(cd_job     );
                    fcEdocSendVO.setNo_comp_seq (company_seg);

                }
            }
            /**
             * 2차전문 값 설정
             */
            else if(TRANS_2ND.equals(type)){
                //이지론 2차 전문 공통부
                fcEdocSendVO.setHd_cd_if     (hd_cd_if      ); //, '0')
                fcEdocSendVO.setHd_cd_result (hd_cd_result  ); //, '0')
                fcEdocSendVO.setHd_err_msg   (""            ); //, '0')
                fcEdocSendVO.setHd_seq       (hd_seq_edoc_no);
                fcEdocSendVO.setHd_dt_send   (hd_dt_send    );
                fcEdocSendVO.setHd_filler    (""            );
            }
            /**
             * 3차전문 값 설정
             */
            else if(TRANS_3TH.equals(type)){
                //이지론 3차 전문 공통부
                fcEdocSendVO.setHd_cd_if     (hd_cd_if      ); //, '0')
                fcEdocSendVO.setHd_cd_result (hd_cd_result  ); //, '0')
                fcEdocSendVO.setHd_err_msg   (""            ); //, '0')
                fcEdocSendVO.setHd_seq       (hd_seq_edoc_no);
                fcEdocSendVO.setHd_dt_send   (hd_dt_send    );
                fcEdocSendVO.setHd_filler    (""            );

                //송신 부분 하드 코딩
                fcEdocSendVO.setCd_cancelation("0");//실행취소  구분 (유지)
            }

        }

        return fcEdocSendVO;
    }

    /**
     * 전문 초기값 셋팅
     * @param fcEdocSendVO
     * @return
     */
    private TxFcTransmitVO initFcEdocData(TxFcTransmitVO fcEdocSendVO) {
        String email             = null;
        String cd_live_area      = null;//거주지역
        String post_home         = null;//자택주소 우편번호
        String addr1_home        = null;
        String addr2_home        = null;
        String no_manage_home    = null;
        String cd_marry          = null;//결혼구분
        String yn_dualwork       = null;
        String ymd_house_home    = null;//현거주지 전입일
        String cnt_house_home    = null;//현거주지개월수
        String cd_house_type     = null;//주거종류
        String cd_live_type_home = null;//주거소유형태
        String job_term_month    = null;//재직기간
        String cd_job_comp       = null;//직무구분
        String cd_job_proof      = null;//재직증빙코드  3:기타 //20180319 : 포이시스 조선숙 차장님 요청
        String cd_income_proof   = null;//소득증빙 가능코드
        String cd_hp_co          = null;//통신사
        String cd_army           = null;//병역사항
        String cd_job_class      = null;//직업구분
        String no_biz_comp       = null;//직장사업장번호
        String no_comp_seq       = null;
        String cd_certify_person = null;
        String etc_field1        = null;
        long   amt_wanted        = 0; //대출희망금액
        String ph1_comp          = null;//자택전화1
        String ph1_home          = null;//자택전화1
        String hp1               = null;//휴대폰1
        String cd_sex            = null;//성별구분
        String ssn_person        = null;//주민등록번호
        String ymd_start_comp    = null;//입사일자
        String jb_dt_join        = null;//입사년월
        String ph_comp           = null;
        String ph_home           = null;
        String cd_fc             = null;

        cd_fc = fcEdocSendVO.getCd_fc();
        // 페퍼 저축은행
        if ( cd_fc_pepper.equals(cd_fc)) {

            logger.info("EDOC#1 : 페퍼 기본 데이터 셋팅");

            //페퍼 테스트 서버용 주민번호, DI 고정 , 아래 주민번호와 DI 를 사용하지 않으면 테스트 진행이 안됨. KCB 조회용 테스트 주민번호임.
            if(Y.equals(codeManager.getNvlCodeName(_CONF_SYSTEM,YN_TEST_MODE,N))) {
                fcEdocSendVO.setSsn_person("7009081999741");
                fcEdocSendVO.setKcb_di("MC0GCCqGSIb3DQIJAyEAaL9CuFahU9S+63SxY6Jc3J4/5O9k9RxyIMu5hSVOOjI=");
            }


            // 고정값
            fcEdocSendVO.setCd_onoff("1");  //오프라인 온라인 구분
            fcEdocSendVO.setHd_cd_partner( cd_fc);

            amt_wanted = fcEdocSendVO.getAmt_wanted();
            // NVL
            if (amt_wanted <= 0) {
                amt_wanted = 99999999;
                fcEdocSendVO.setAmt_wanted(amt_wanted); //대출희망금액
            }
            email             = fcEdocSendVO.getEmail            ();
            cd_live_area      = fcEdocSendVO.getCd_live_area     ();
            post_home         = fcEdocSendVO.getPost_home        ();
            addr1_home        = fcEdocSendVO.getAddr1_home       ();
            addr2_home        = fcEdocSendVO.getAddr2_home       ();
            no_manage_home    = fcEdocSendVO.getNo_manage_home   ();
            cd_marry          = fcEdocSendVO.getCd_marry         ();
            yn_dualwork       = fcEdocSendVO.getYn_dualwork      ();
            ymd_house_home    = fcEdocSendVO.getYmd_house_home   ();
            cnt_house_home    = fcEdocSendVO.getCnt_house_home   ();
            cd_house_type     = fcEdocSendVO.getCd_house_type    ();
            cd_live_type_home = fcEdocSendVO.getCd_live_type_home();
            job_term_month    = fcEdocSendVO.getJob_term_month   ();
            cd_job_comp       = fcEdocSendVO.getCd_job_comp      ();
            cd_job_proof      = fcEdocSendVO.getCd_job_proof     ();
            cd_income_proof   = fcEdocSendVO.getCd_income_proof  ();
            cd_hp_co          = fcEdocSendVO.getCd_hp_co         ();
            cd_army           = fcEdocSendVO.getCd_army          ();
            cd_job_class      = fcEdocSendVO.getCd_job_class     ();
            no_biz_comp       = fcEdocSendVO.getNo_biz_comp      ();
            no_comp_seq       = fcEdocSendVO.getNo_comp_seq      ();
            cd_certify_person = fcEdocSendVO.getCd_certify_person();
            etc_field1        = fcEdocSendVO.getEtc_field1       ();
            ph1_comp          = fcEdocSendVO.getPh1_comp         ();
            ph1_home          = fcEdocSendVO.getPh1_home         ();
            hp1               = fcEdocSendVO.getHp1              ();
            cd_sex            = fcEdocSendVO.getCd_sex           ();
            ssn_person        = fcEdocSendVO.getSsn_person       ();
            ymd_start_comp    = fcEdocSendVO.getYmd_start_comp   ();
            jb_dt_join        = fcEdocSendVO.getJb_dt_join       ();

            email             =  StringUtil.nullToString(email            , "xxxx0000@xxxx.xxx"         );
            email             =  "                 ";
            cd_live_area      =  StringUtil.nullToString(cd_live_area     , "02"                        );//거주지역
            post_home         =  StringUtil.nullToString(post_home        , "00000"                     );//자택주소 우편번호
            addr1_home        =  StringUtil.nullToString(addr1_home       , "-"                         );
            addr1_home        =  "-";
            addr2_home        =  StringUtil.nullToString(addr2_home       , "-"                         );
            no_manage_home    =  StringUtil.nullToString(no_manage_home   , "0000000000000000000000000" );
            no_manage_home    =  "0000000000000000000000000";
            cd_marry          =  StringUtil.nullToString(cd_marry         , "01"                        );//결혼구분
            cd_marry          =  "01";//결혼구분
            yn_dualwork       =  StringUtil.nullToString(yn_dualwork      , N                         );
            ymd_house_home    =  StringUtil.nullToString(ymd_house_home   , "19700101"                  );//현거주지 전입일
            cnt_house_home    =  StringUtil.nullToString(cnt_house_home   , "100"                       );//현거주지개월수
            cd_house_type     =  StringUtil.nullToString(cd_house_type    , "2"                         );//주거종류
            cd_live_type_home =  StringUtil.nullToString(cd_live_type_home, "6"                         );//주거소유형태
            job_term_month    =  StringUtil.nullToString(job_term_month   , "100"                       );//재직기간
            cd_job_comp       =  StringUtil.nullToString(cd_job_comp      , "01"                        );//직무구분
            cd_job_proof      =  StringUtil.nullToString(cd_job_proof     , "3"                         );//재직증빙코드  3:기타 //20180319 : 포이시스 조선숙 차장님 요청
            cd_income_proof   =  StringUtil.nullToString(cd_income_proof  , "105"                       );//소득증빙 가능코드
            cd_hp_co          =  StringUtil.nullToString(cd_hp_co         , "01"                        );//통신사
            cd_army           =  StringUtil.nullToString(cd_army          , "90"                        );//병역사항
            cd_job_class      =  StringUtil.nullToString(cd_job_class     , "01"                        );//직업구분
            no_biz_comp       =  StringUtil.nullToString(no_biz_comp      , "0000000000"                );//직장사업장번호
            no_comp_seq       =  StringUtil.nullToString(no_comp_seq      , "G"                         );
            cd_certify_person =  StringUtil.nullToString(cd_certify_person, "05"                        );
            etc_field1        =  StringUtil.nullToString(etc_field1       , "-"                         );

            fcEdocSendVO.setEmail            (email            );
            fcEdocSendVO.setCd_live_area     (cd_live_area     );
            fcEdocSendVO.setPost_home        (post_home        );
            fcEdocSendVO.setAddr1_home       (addr1_home       );
            fcEdocSendVO.setAddr2_home       (addr2_home       );
            fcEdocSendVO.setNo_manage_home   (no_manage_home   );
            fcEdocSendVO.setCd_marry         (cd_marry         );
            fcEdocSendVO.setYn_dualwork      (yn_dualwork      );
            fcEdocSendVO.setYmd_house_home   (ymd_house_home   );
            fcEdocSendVO.setCnt_house_home   (cnt_house_home   );
            fcEdocSendVO.setCd_house_type    (cd_house_type    );
            fcEdocSendVO.setCd_live_type_home(cd_live_type_home);
            fcEdocSendVO.setJob_term_month   (job_term_month   );
            fcEdocSendVO.setCd_job_comp      (cd_job_comp      );
            fcEdocSendVO.setCd_job_proof     (cd_job_proof     );
            fcEdocSendVO.setCd_income_proof  (cd_income_proof  );
            fcEdocSendVO.setCd_hp_co         (cd_hp_co         );
            fcEdocSendVO.setCd_army          (cd_army          );
            fcEdocSendVO.setCd_job_class     (cd_job_class     );
            fcEdocSendVO.setNo_biz_comp      (no_biz_comp      );
            fcEdocSendVO.setNo_comp_seq      (no_comp_seq      );
            fcEdocSendVO.setCd_certify_person(cd_certify_person);
            fcEdocSendVO.setEtc_field1       (etc_field1       );

            // 직장번호
            if(StringUtil.isNotEmpty(ph1_comp) && ph1_comp.length() >= 9) {
                ph_comp = StringUtil.toTelephoneNumberFormat(ph1_comp);
                if ( ph_comp.contains("-") && ph_comp.split("-").length == 3 ) {
                    fcEdocSendVO.setPh1_comp( ph_comp.split("-")[0] );
                    fcEdocSendVO.setPh2_comp( ph_comp.split("-")[1] );
                    fcEdocSendVO.setPh3_comp( ph_comp.split("-")[2] );
                }
            }
            if(StringUtil.isEmpty(ph1_comp)) {
                fcEdocSendVO.setPh1_comp("02"  );
                fcEdocSendVO.setPh2_comp("0000");
                fcEdocSendVO.setPh3_comp("0000");
            }

            // 자택번호
            if(StringUtil.isNotEmpty(ph1_home) && ph1_home.length() >= 9) {
                ph_home = StringUtil.toTelephoneNumberFormat(ph1_home);
                if ( ph_home.contains("-") && ph_home.split("-").length == 3 ) {
                    fcEdocSendVO.setPh1_home( ph_home.split("-")[0] );
                    fcEdocSendVO.setPh2_home( ph_home.split("-")[1] );
                    fcEdocSendVO.setPh3_home( ph_home.split("-")[2] );
                }
            }
            if (StringUtil.isEmpty(ph1_home)) {
                fcEdocSendVO.setPh1_home("02");
                fcEdocSendVO.setPh2_home("0000");
                fcEdocSendVO.setPh3_home("0000");
            }

            // 휴대전화
            if (StringUtil.isNotEmpty(hp1) && hp1.length() >= 9) {
                String hp = StringUtil.toTelephoneNumberFormat(hp1);
                if ( hp.contains("-") && hp.split("-").length == 3 ) {
                    fcEdocSendVO.setHp1( hp.split("-")[0] );
                    fcEdocSendVO.setHp2( hp.split("-")[1] );
                    fcEdocSendVO.setHp3( hp.split("-")[2] );
                }
            }

            if (StringUtil.isEmpty(hp1)) {
                fcEdocSendVO.setHp1("010" );
                fcEdocSendVO.setHp2("0000");
                fcEdocSendVO.setHp3("0000");
            }
            ssn_person = (ssn_person == null)? "0000000000000":ssn_person;
            fcEdocSendVO.setSsn_person(ssn_person);
            cd_sex = (StringUtil.isEmpty(cd_sex))?((ssn_person != null)? ssn_person.substring(6, 7):"1"):cd_sex;
            cd_sex = (Integer.parseInt(cd_sex) % 2 == 0 )? "2":"1";

            fcEdocSendVO.setCd_sex(cd_sex);

            if (StringUtil.isEmpty(ymd_start_comp)) {
                if(StringUtil.isNotEmpty(jb_dt_join)) {
                    ymd_start_comp = jb_dt_join+"01";
                    fcEdocSendVO.setYmd_start_comp(ymd_start_comp); //입사일자
                } else {
                    ymd_start_comp = "19700101";
                    fcEdocSendVO.setYmd_start_comp(ymd_start_comp); //입사일자
                }
            }
            //송신 부분 하드 코딩
            fcEdocSendVO.setCd_cancelation("0");  //실행취소  구분 (유지)
        }

        return fcEdocSendVO;
    }

//  /**
//   * Name   : getSeqEdocNo
//   * Desc   : 전문일련번호 생성
//   * input  : null
//   * output : String
//   * Date   : 2017.09.20
//  **/
//  private String getSeqEdocNo(){
//      return
//  }

    /**
     * Name   : reqFinanceInfo
     * Desc   : 금융사 1차 전문 송수신
     * input  : FinanceMsgSendVO
     * output : TxFcReceiveVO
     * Date   : 2017.07.06
     **/
    @Override
    public ReturnClass reqFinanceInfo(TxFcTransmitVO pFcEdocSendVO) throws FinsetException, IOException, FinsetMessageException{//이지론 1차전문
        TxFcTransmitVO fcEdocSendVO = pFcEdocSendVO;
        TxFcReceiveVO  txFcRecvVO   = null;
        FinsetForm     finsetForm   = null;

        String sErrMsg    = null;
        String no_person  = null;
        String no_bunch   = null;
        int    goods_size = 0;

        LinkedList<FinsetVO>    listGoods   = null;
        List<GoodsVO>           listFcGoods = null;
        Queue<ReqFinanceThread> threadQueue = null;

        ArrayList<Throwable> listErr = null;
        Throwable throwable = null;
        listFcGoods = (fcEdocSendVO != null)? fcEdocSendVO.getListGoods():null;
        goods_size  = (listFcGoods  != null)? listFcGoods.size()         :0;

        logger.info("EDOC#1 : no_person - "+no_person+" - "+"no_bunch - "+no_bunch);

        if(fcEdocSendVO != null && goods_size > 0 ){// 대출 상품 리스트 유무 확인

            no_person = fcEdocSendVO.getNo_person();
            no_bunch  = fcEdocSendVO.getNo_bunch();

            if (listFcGoods != null ) {

                logger.info("EDOC#1 :한도조회 신청 상품 건수" + goods_size);
                
                //쓰레드 관리용 Queue
                threadQueue = new LinkedList<ReqFinanceThread>();

                if(Y.equals(codeManager.getNvlCodeName(_CONF_SYSTEM,YN_THREAD_MODE,Y))) {
                	 for(GoodsVO goodsVO : listFcGoods){
                         if (goodsVO != null) {
                        	// 멀티쓰레드  처리 시작 및 큐에 추가
                        	 TxFcTransmitVO fcEdocSendTmpVO = new TxFcTransmitVO();
                        	 BeanUtils.copyProperties(fcEdocSendVO, fcEdocSendTmpVO);
                        	 
                        	 ReqFinanceThread thread = new ReqFinanceThread(goodsVO,fcEdocSendTmpVO,txFcRecvVO);
                        	 threadQueue.add(thread);
        					 thread.start();
                         }
                	 }
                	// 모든 쓰레드 동작 완료까지 sleep() 현재 최대 5분까지만 대기
                	waitThread(threadQueue);
                } else {
                	//싱글쓰레드 동작  부분 
                	//위에 멀티 쓰레드로 동작이 안정화되면  아래  reqFinanceThread 함수제거 필요함 
                	for(GoodsVO goodsVO : listFcGoods) {
                		if (goodsVO != null) {
                			reqFinanceThread(goodsVO,fcEdocSendVO,txFcRecvVO);
                		}
                	}
                }

            }
            /**
             * 수행중 에러내역
             */
            listErr = new ArrayList<Throwable>();
            for (ReqFinanceThread subThread : threadQueue) {
                throwable = subThread.getError();
                if (throwable != null) {
                    listErr.add(throwable);
                }
            }

            threadQueue.clear();

            finsetForm = new FinsetForm();
            finsetForm.setNo_person(no_person);
            finsetForm.setNo_bunch (no_bunch );

            listGoods = finsetManager.listFinsetGoodsInfo(finsetForm); //tx__fc_receive Select

            txFcRecvVO = (txFcRecvVO == null)?new TxFcReceiveVO():txFcRecvVO;

            txFcRecvVO.setNo_bunch (no_bunch );
            txFcRecvVO.setNo_person(no_person);
            txFcRecvVO.setListErr  (listErr  );
            if(listGoods != null) {
                txFcRecvVO.setListGoods(listGoods);
                logger.info("1차전문 CFS 처리 완료 결과 : txFcRecvVO - "+txFcRecvVO);
            }
        } else {
            txFcRecvVO = null;
        }

        if(txFcRecvVO == null) {
            return new ReturnClass(Constant.FAILED, "FAIL : reqFinanceInfo", sErrMsg);
        }
        return new ReturnClass(Constant.SUCCESS, "SUCCESS : received financeInfo", txFcRecvVO);
    }

	private void waitThread(Queue<ReqFinanceThread> threadQueue) {
		int cntLive = 0;	// 살아있는 쓰레드 갯수
		int cnt_wait = 0;	// 대기한 seconds
        Thread.State state = null;
		do {
			cntLive = 0;
            try {
                // 1초 마다 모든 쓰레드 상태 체크
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LogUtil.error(logger,e);
            }

			for (ReqFinanceThread subThread : threadQueue) {
                state = subThread.getState();
				if(Thread.State.TERMINATED != state) {
					cntLive++;
                }
			}
			// 최대 5분 대기
			if(cnt_wait > 300) {
				System.err.println("Thread wait time out: 5min");
				break;
			}
			cnt_wait++;
			
		} while (cntLive > 0);
		// 쓰레드 종료후 다음 
//		threadQueue.clear();
	}

    
    /** 금융사 1차조회(한도,금리 조회)용 쓰레드
     * @author dhkim
     *
     */
    public class ReqFinanceThread extends Thread {
		private GoodsVO goodsVO;
		private TxFcTransmitVO fcEdocSendVO;
		private TxFcReceiveVO  txFcRecvVO;
		private Throwable      error     ;
		public ReqFinanceThread(GoodsVO goodsVO, TxFcTransmitVO fcEdocSendVO,TxFcReceiveVO  txFcRecvVO)
		{
			this.goodsVO = goodsVO;
			this.fcEdocSendVO = fcEdocSendVO;
			this.txFcRecvVO = txFcRecvVO;
		}
		
		@Override
		public void run() {
			
			String      cd_fc        = null;
	        String      cd_goods     = null;
	        EdocForm    edocForm     = null;
	        EdocVO      edocDetail   = null;
	        String      no_edoc      = null;
	        String      type_flex    = null;
	        String      url_edoc     = null;   //전문URL
	        long        hd_cd_result = 0 ; //응답코드
	        boolean     isSuccess    = false;
	        logger.info("EDOC#1 : 1차 전문 요청 시작 : "+goodsVO.getCd_goods());
	        
	        cd_fc    = goodsVO.getCd_fc   ();
	        
	        
	        cd_goods = goodsVO.getCd_goods();

	        //1차 전문 리스트 조회
	        edocForm = new EdocForm();
	        edocForm.setCd_fc     (cd_fc    );
	        edocForm.setSel_detail("no_edoc");// 전문번호
	        edocForm.setTxt_detail(TRANS_1ST);// 1차 전문은 001 숫자를 포함해야한다. LIKE 검색 사용
	        edocForm.setType_txrx (TX       );


	        //해당 금융사로 등록된 전문 리스트
	        edocDetail = edocManager.getEdocDetail(edocForm);

	        if(edocDetail != null && fcEdocSendVO != null){

	            fcEdocSendVO.setCd_goods(cd_goods); //상품코드

	            cd_fc     = edocDetail.getCd_fc    (); // 금융사코드
	            no_edoc   = edocDetail.getNo_edoc  (); // 전문번호
	            type_flex = edocDetail.getType_flex();//
	            url_edoc  = edocDetail.getUrl_edoc ();//

	            cd_fc     = (cd_fc     != null)? cd_fc    :"";
	            no_edoc   = (no_edoc   != null)? no_edoc  :"";
	            type_flex = (type_flex != null)? type_flex:"";

	            //금융사코드, 전문번호, 송수신구분 설정
	            fcEdocSendVO.setCd_fc    (cd_fc    );
	            fcEdocSendVO.setNo_edoc  (no_edoc  );
	            fcEdocSendVO.setType_flex(type_flex);

	            //금융사별 데이터 디폴트값 설정
	            fcEdocSendVO = initFcEdocData(fcEdocSendVO);
	            
	            //전문 데이터 설정
	            fcEdocSendVO = setFcEdocData(fcEdocSendVO, TRANS_1ST);
	            	            
                try {
                    /**
                     * TCP 전송     , EDOC_INFO 에 통신 방식, Target URL 이 정의 되어 있음
                     */
                    if(TYPE_FLEX_TCP.equals(type_flex)){
                        txFcRecvVO = procTcpEdoc (fcEdocSendVO, url_edoc);
                    }
                    /**
                     * JSON 전송
                     */
                    else if(TYPE_FLEX_JSON.equals(type_flex)){
                        txFcRecvVO = procJsonEdoc(fcEdocSendVO, url_edoc);
                    }
                } catch (IOException e) {
                    this.setError(e);
                    LogUtil.debugLn(logger,e);
                } catch (FinsetException e) {
                    this.setError(e);
                    LogUtil.debugLn(logger,e);
                } catch (FinsetMessageException e) {
                    this.setError(e);
                    LogUtil.debugLn(logger,e);
                } catch (RuntimeException e) {
                    this.setError(e);
                    LogUtil.debugLn(logger,e);
                } catch (Exception e) {
                    this.setError(e);
                    LogUtil.debugLn(logger,e);
                } finally {
                    /**
                     * 조회중 데이터 삭제
                     */
                    removeFinsetForReady(fcEdocSendVO);

                    LogUtil.infoLn(logger,"EDOC#1 : 1차 전문 수신 결과  : "+txFcRecvVO  );
                    //응답 받은 전문 데이터가 존재 & 응답 전문의 대출상품리스트 존재
                    if( txFcRecvVO != null) {
                        hd_cd_result = txFcRecvVO.getHd_cd_result();
                        if(hd_cd_result == 0) {
                            //대출상품 리스트(응답)
                            List<FcLoanInfo> loan_info      = txFcRecvVO.getLoan_info();
                            int loan_info_size = (loan_info != null)? loan_info.size():0;
                            if(loan_info_size > 0) {
                                logger.info("EDOC#1 : 1차 전문 수신 결과  size: .getLoan_info().size() = "+loan_info_size);
                                //no_prepare Insert and tx_fc_receive Insert  //FRONT 전달 대출 리스트 생성
                                ReturnClass returnClass = setFinset(txFcRecvVO, fcEdocSendVO);//listGoods = setFinset(txFcRecvVO, fcEdocSendVO);
                                if(returnClass  == null || returnClass.isSuccess()) {
                                    txFcRecvVO = null;
                                }
                                isSuccess = true;
                            } else {
                                logger.info("EDOC#1 : 1차 전문 수신 결과  size: .getLoan_info().size() = 0");
                            }
                        }
                    } else {
                        LogUtil.infoLn(logger,"EDOC#1 : 1차 전문 수신 결과 없음= ");
                    }
                    if(isSuccess == false) {
                        /**
                         * 1차 전문 수신결과 실패(통신 실패와 connection실패)
                         */
                        setFinsetForFail(goodsVO,txFcRecvVO,fcEdocSendVO);
                    }
                }
	        }
		}

        public Throwable getError() {
            return error;
        }

        public void setError(Throwable error) {
            this.error = error;
        }
    }
   
    /** 위에멀티쓰레드 동작이 안정화 되면 제거해야할 함수임
     * @param goodsVO
     * @param fcEdocSendVO
     * @param txFcRecvVO
     * @throws FinsetException
     * @throws IOException
     * @throws FinsetMessageException
     */
    @Deprecated
    private void reqFinanceThread(GoodsVO goodsVO, TxFcTransmitVO fcEdocSendVO,TxFcReceiveVO  txFcRecvVO) throws FinsetException, IOException, FinsetMessageException {
        String      cd_fc        = null;
        String      cd_goods     = null;
        EdocForm    edocForm     = null;
        EdocVO      edocDetail   = null;
        String      no_edoc      = null;
        String      type_flex    = null;
        String      url_edoc     = null;   //전문URL
        long        hd_cd_result = 0 ; //응답코드
        boolean     isSuccess    = false;
        logger.info("EDOC#1 : 1차 전문 요청 시작 : "+goodsVO.getCd_goods());

        cd_fc    = goodsVO.getCd_fc   ();
        cd_goods = goodsVO.getCd_goods();

        //1차 전문 리스트 조회
        edocForm = new EdocForm();
        edocForm.setCd_fc     (cd_fc    );
        edocForm.setSel_detail("no_edoc");// 전문번호
        edocForm.setTxt_detail(TRANS_1ST);// 1차 전문은 001 숫자를 포함해야한다. LIKE 검색 사용
        edocForm.setType_txrx (TX       );


        //해당 금융사로 등록된 전문 리스트
        edocDetail = edocManager.getEdocDetail(edocForm);

        if(edocDetail != null && fcEdocSendVO != null){

            fcEdocSendVO.setCd_goods(cd_goods); //상품코드

            cd_fc     = edocDetail.getCd_fc    (); // 금융사코드
            no_edoc   = edocDetail.getNo_edoc  (); // 전문번호
            type_flex = edocDetail.getType_flex();//
            url_edoc  = edocDetail.getUrl_edoc ();//

            cd_fc     = (cd_fc     != null)? cd_fc    :"";
            no_edoc   = (no_edoc   != null)? no_edoc  :"";
            type_flex = (type_flex != null)? type_flex:"";

            //금융사코드, 전문번호, 송수신구분 설정
            fcEdocSendVO.setCd_fc    (cd_fc    );
            fcEdocSendVO.setNo_edoc  (no_edoc  );
            fcEdocSendVO.setType_flex(type_flex);

            //금융사별 데이터 디폴트값 설정
            fcEdocSendVO = initFcEdocData(fcEdocSendVO);

            //전문 데이터 설정
            fcEdocSendVO = setFcEdocData(fcEdocSendVO, TRANS_1ST);
            /**
             * TCP 전송     , EDOC_INFO 에 통신 방식, Target URL 이 정의 되어 있음
             */
            if(TYPE_FLEX_TCP.equals(type_flex)){
                txFcRecvVO = procTcpEdoc (fcEdocSendVO, url_edoc);
            }
            /**
             * JSON 전송
             */
            else if(TYPE_FLEX_JSON.equals(type_flex)){
                txFcRecvVO = procJsonEdoc(fcEdocSendVO, url_edoc);
            }
            /**
             * 조회중 데이터 삭제
             */
            removeFinsetForReady(fcEdocSendVO);

            LogUtil.infoLn(logger,"EDOC#1 : 1차 전문 수신 결과  : "+txFcRecvVO  );
            //응답 받은 전문 데이터가 존재 & 응답 전문의 대출상품리스트 존재
            if( txFcRecvVO != null) {
                hd_cd_result = txFcRecvVO.getHd_cd_result();
                if(hd_cd_result == 0) {
                    //대출상품 리스트(응답)
                    List<FcLoanInfo> loan_info      = txFcRecvVO.getLoan_info();
                    int loan_info_size = (loan_info != null)? loan_info.size():0;
                    if(loan_info_size > 0) {
                        logger.info("EDOC#1 : 1차 전문 수신 결과  size: .getLoan_info().size() = "+loan_info_size);
                        //no_prepare Insert and tx_fc_receive Insert  //FRONT 전달 대출 리스트 생성
                        ReturnClass returnClass = setFinset(txFcRecvVO, fcEdocSendVO);//listGoods = setFinset(txFcRecvVO, fcEdocSendVO);
                        if(returnClass  == null || returnClass.isSuccess()) {
                            txFcRecvVO = null;
                        }
                        isSuccess = true;
                    } else {
                        logger.info("EDOC#1 : 1차 전문 수신 결과  size: .getLoan_info().size() = 0");
                    }
                }
            } else {
                LogUtil.infoLn(logger,"EDOC#1 : 1차 전문 수신 결과 없음= ");
            }
            if(isSuccess == false) {
                /**
                 * 1차 전문 수신결과 실패(통신 실패와 connection실패)
                 */
                setFinsetForFail(goodsVO,txFcRecvVO,fcEdocSendVO);
            }
        }

    }

    /**
     * Name   : procTcpEdoc
     * Desc   : TCP 전문 처리
     * input  : TxFcTransmitVO, String
     * output : TxFcReceiveVO
     * Date   : 2017.08.18
     */
    private TxFcReceiveVO procTcpEdoc(TxFcTransmitVO pFcMsgSendVO, String targetUrl) throws IOException, FinsetException, FinsetMessageException {

        TxFcTransmitVO fcMsgSendVO = pFcMsgSendVO;
        TxFcReceiveVO  txFcRecvVO  = null;

        String ip   = null;
        int    port = 0   ;

        byte[] bSendArray   = null;
        byte[] bArrRecvData = null;

        FepSocket sock = null;

        String    site = null;
        String    temp = null;

        if (fcMsgSendVO != null) {

            //금융사 요청 전문 생성
            bSendArray = createBinaryData(fcMsgSendVO, targetUrl);

            if(bSendArray != null) {
                site = (environment != null)?environment.getProperty("service.profile"):"";

                // java 로 구현된 proxy 를 사용하는 경우
                sock = new FepSocket();

                ip   = codeManager.getNvlCodeName(_CONF_SYSTEM,FEP_PROXY_IP,"127.0.0.1");
                port = NumberUtil.parseInt(codeManager.getNvlCodeName(_CONF_SYSTEM,FEP_PROXY_PORT,"9880"));
//                pFcMsgSendVO.getCd_service();
                /**
                 * 로컬인 경우엔 전문 하드코딩
                 */
                if(false && "LOCAL".equals(site)) {
                    String send = new String(bSendArray);
                    // fep 에 전송 및 수신
                    //                    if("LOCAL".equals(site)) {
                    /**
                     * 1차 실패 전문 수신
                     */
                    //                        temp = "0324J001    110099990010471             최근 10일 이내 기상담 건 보유 고객입니다.                                                                                                                                                               000000197520180405220627                                                            ";
                    /**
                     * 1차 전문수신
                     */
                    if(send.contains("J001")) {
//                            temp = "0324J001    110099990010471             최근 10일 이내 기상담 건 보유 고객입니다.                                                                                                                                                               000000197520180405220627                                                            ";
                        /* 성공전문*/
                        temp = "0773J001    110000000010471                                                                                                                                                                                                                     000000207920180405221301                                                            2018040500274109PIL100        20000000000000000000000PIL101        20000000000000000000000PIL102        20000000000000000000000PIL103        20000000000000000000000PIL104        20000000000000000000000PIL300        20000000000000000000000PIL301        20000000000000000000000PIL303        20000000000000000000000PILS10        100000002000000000011.9                                                                                                    ";
                        temp = "0773J001    110000000010471                                                                                                                                                                                                                     000000197720180412193352                                                            2018041200291209PIL100        20000000000000000000000PIL101        20000000000000000000000PIL102        20000000000000000000000PIL103        100000006910000000009.2PIL104        100000006910000000009.2PIL300        20000000000000000000000PIL301        20000000000000000000000PIL303        20000000000000000000000PILS10        100000002000000000012.5                                                                                                    ";
                        /* 실패전문*/
//                            temp = "0773J001    110099990010471             TESTTESTERROR                                                                                                                                                                                           000000207920180405221301                                                            2018040500274109PIL100        20000000000000000000000PIL101        20000000000000000000000PIL102        20000000000000000000000PIL103        20000000000000000000000PIL104        20000000000000000000000PIL300        20000000000000000000000PIL301        20000000000000000000000PIL303        20000000000000000000000PILS10        100000002000000000011.9                                                                                                    ";
                        long longTemp = 7000;
                        try {
                            Thread.sleep(longTemp);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }                        
                    }
                    /**
                     * 2차 전문
                     */
                    else if(send.contains("J002")) {
                        /*성공전문*/
//                        temp = "0484J002    110000000010471                                                                                                                                                                                                                     000000207120180412160208                                                            0010471             20180412000004010000030000000000024                                                                                                         ";
                        temp = "0484J002    110000000010471                                                                                                                                                                                                                     000000197920180412211543                                                            0010471             201804120029300100006910000000009.2                                                                                                         ";
                        /*실패전문*/
//                        temp = "0484J002    110099990010471                   TESTTESTTESTTESTFAIL                                                                                                                                                                              000000207120180412160208                                                            0010471             20180412000004010000030000000000024                                                                                                         ";
                    }
                    /**
                     * 3차 성공전문수신
                     */
                    else if(send.contains("J003")) {
                        /*성공전문*/
//                        temp = "0566J003    110000000010471                                                                                                                                                                                                                     000000207220180412162310                                                            20180410000011030000020000000013.700000000심사승인완료                                                                                                                                                                                            ";
                        /*실패전문*/
                        temp = "0566J003    110099990010471                   TESTTESTTESTTESTFAIL                                                                                                                                                                              000000207120180412160208                                                            0010471             20180412000004010000030000000000024                                                                                                         ";
                    }
                    bArrRecvData = temp.getBytes();

                }
                else {
                	try {
                		bArrRecvData = sock.request(ip, port, bSendArray);
                	}
            		catch (ConnectException e) {
            			LogUtil.error(logger, "First Server Connect Failed~!");
            			ip   = codeManager.getCodeName(_CONF_SYSTEM,FEP_PROXY_SEC_IP);
                        port = NumberUtil.parseInt(codeManager.getNvlCodeName(_CONF_SYSTEM,FEP_PROXY_SEC_PORT,"0"));
                        if( ip == null || port == 0)	{
                        	LogUtil.error(logger, "Get Secondary Server Infomation is Failed");
                    		throw e;
                        }
                        LogUtil.debugLn(logger, "Try Connect Secondary Server : IP ["+ ip +"] Port ["+ port +"]");
                        {     
                        	bArrRecvData = sock.request(ip, port, bSendArray);
                        }
                        
                        	
                        //port = NumberUtil.parseInt(codeManager.getNvlCodeName(_CONF_SYSTEM,FEP_PROXY_PORT,"9880"));
            		//	throw e;
                    }

                }
//                String temp = "0484J002    110099990010471             [20180402000023] 해당 신청번호로 대출가능 건이 없습니다.                                                                                                                                                000000194520180402161826                                                                                                                                                                                                                            ";
//                bArrRecvData = temp.getBytes();
                if(bArrRecvData != null ) {
                    LogUtil.debugLn(logger,new String(bArrRecvData));
                    LogUtil.infoLn(logger,"FepSocket recived byte length :"+bArrRecvData.length);
                    txFcRecvVO = setRecvByteMsgMapping(bArrRecvData, fcMsgSendVO);
                }
            }
        }
        return txFcRecvVO;
    }

    /**
     * Name   : setFinset
     * Desc   : finset no_prepare 설정
     *        , 전문 no_fc_req(금융기관신청번호) 설정
     *        , TX_FC_RECEIVE DAO
     * input  : TxFcReceiveVO, TxFcTransmitVO
     * output : LinkedList<FinsetVO>
     * Date   : 2017.08.09
     */
    private ReturnClass setFinset(TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO){
        String no_person     = null;
        String no_bunch      = null;
        String no_prepare    = null;
        String hd_cd_partner = null;
        String cd_fc         = null;//금융사코드
        String no_fc_req     = null;//금융기관신청번호
        String loan_code     = null;//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출
        String cd_goods      = null;//상품코드
        String yn_loan       = null;//대출가능여부
        long   amt_limit     = 0   ;//대출가능한도
        String amt_limit_str = null;//대출가능한도
        String rto_loan      = null;//예상금리
        double rto_loan_dbl  = 0   ;//예상금리
        int    cd_loan_term  = 0   ;//대출기간

        int    cfsCnt        = 0;
        String cd_status     = "01";//01:정상, 02:금융사오류, 03:통신오류

        PrepareVO      prepareVO      = null;
        FinsetVO       finsetVO       = null;
        FcLoanInfo     fcLoanInfo     = null;
        TxFcTransmitVO txFcTransmitVO = null;
        List<FcLoanInfo> loan_info = null;     //대출상품 리스트(응답)

        ReturnClass      rc = null;
        if (fcMsgSendVO != null && txFcReceiveVO != null) {

            no_person  = fcMsgSendVO.getNo_person();
            no_bunch   = fcMsgSendVO.getNo_bunch ();
            loan_code  = fcMsgSendVO.getLoan_code();

            hd_cd_partner = txFcReceiveVO.getHd_cd_partner();
            cd_fc         = txFcReceiveVO.getCd_fc    ();
            no_fc_req     = txFcReceiveVO.getNo_fc_req();
            loan_info     = txFcReceiveVO.getLoan_info();
            cd_fc         = StringUtil.isEmpty(cd_fc) ? hd_cd_partner :  cd_fc;
            if(loan_info != null){
                cfsCnt = loan_info.size();
            }

            prepareVO = new PrepareVO();
            prepareVO.setNo_person       (no_person);
            prepareVO.setYn_overlap_chk  (Y        );
            prepareVO.setYn_exist_person (Y        );
            prepareVO.setId_frt          ("FINSET" );
            prepareVO.setCd_advertisement("21"     );//<!-- 21 홈페이지 -->
            prepareVO.setCd_goods_type   ("01"     );//<!-- 01 신용 -->
            prepareVO.setAmt_apply       ("0"      );
            prepareVO.setCd_used_apply   ("99"     );//<!-- 99 기타 -->
            prepareVO.setCd_collect_path ("01"     );
            prepareVO.setId_frt          (no_person);
            //01 : 신용대출(직장인), 02 : 신용대출(사업자), 03 : 담보대출(주택)
            if( StringUtil.isNotEmpty(loan_code) ){
                prepareVO.setCd_goods_gubun(loan_code);
            }else{
                prepareVO.setCd_goods_gubun("01"); // Default : 01(신용대출_직장인)
            }

            if( cfsCnt > 0 ){
                prepareVO.setCd_prepare_doc_box("10");
            }else{
                prepareVO.setCd_prepare_doc_box("60");
            }

            prepareVO.setCd_collect_path  ("");//<!-- 01 고객이 먼저 중개인에게 연락  -->
            prepareVO.setCd_collect_method("01");//<!-- 01 전화 1 02 SMS -->
            no_prepare = (String) prepareManager.createPrepareSummary(prepareVO).getReturnObj(); //PREPARE_INFO 생성

            logger.info("CREATE PrepareNo : "+no_prepare);
            logger.info("hd_cd_partner : "+hd_cd_partner);
            if( cfsCnt > 0 ) { //TX_FC_RECEIVE INSERT : 대출상품 DB 저장
                for(int i = 0; i < cfsCnt; i++) {

                    finsetVO = new FinsetVO();
                    finsetVO.setNo_bunch  (no_bunch  );
                    finsetVO.setNo_person (no_person );
                    finsetVO.setNo_prepare(no_prepare);
                    finsetVO.setCd_fc     (cd_fc     );
                    finsetVO.setNo_fc_req (no_fc_req );
                    finsetVO.setCd_status (cd_status );
                    finsetVO.setId_frt    (no_person);
                    fcLoanInfo = loan_info.get(i);

                    if(fcLoanInfo != null) {
                        logger.info("fcLoanInfo \n "+fcLoanInfo);
                        cd_goods     = fcLoanInfo.getCd_goods    ();
                        yn_loan      = fcLoanInfo.getYn_loan     ();
                        amt_limit    = fcLoanInfo.getAmt_limit   ();
                        rto_loan     = fcLoanInfo.getRto_loan    ();
                        cd_loan_term = fcLoanInfo.getCd_loan_term();
                        // 상품코드
                        cd_goods = (StringUtil.isEmpty(cd_goods))?A1:cd_goods;

                        finsetVO.setCd_goods(cd_goods);

                        // 대출 기간 (년 단위임 , 개월수로 환산하려면  x12 해야함)
                        finsetVO.setYear_term(cd_loan_term);

                        // 대출 금리
                        rto_loan_dbl = (StringUtil.isNotEmpty(rto_loan))? NumberUtil.parseDouble(rto_loan):0.0;

                        finsetVO.setRto_loan(rto_loan_dbl);

                        // 대출 한도
                        amt_limit_str = String.format("%d", amt_limit);
                        finsetVO.setAmt_limit(amt_limit_str);
                    }
                    amt_limit_str = finsetVO.getAmt_limit();
                    amt_limit     = (amt_limit_str != null)? NumberUtil.parseLong(amt_limit_str):0;

                    // 가능한도가 0보다 크면 yn_loan Y
                    if(amt_limit > 0) {
                        yn_loan=Y;
                    }
                    else {
                        yn_loan=N;
                    }

                    finsetVO.setYn_loan   (yn_loan);
                    finsetVO.setYn_receive(Y      );

                    finsetManager.insertTxFcReceive(finsetVO);
                }

                txFcTransmitVO = new TxFcTransmitVO();
                txFcTransmitVO.setNo_bunch(no_bunch);
                txFcTransmitVO.setNo_fc_req(no_fc_req);
                txFcTransmitVO.setId_lst   (no_person);

                rc = loanManager.modifyLoanForNoFcReq(txFcTransmitVO);
                if(!rc.isSuccess()){
                    return null;
                }
            }
        }
        rc = new ReturnClass(Constant.SUCCESS, "Prepare Create Success");//return listGoods;
        return rc;
    }
    /**
     * Name   : setFinset
     * Desc   : finset no_prepare 설정
     *        , 전문 no_fc_req(금융기관신청번호) 설정
     *        , TX_FC_RECEIVE DAO
     * input  : TxFcReceiveVO, TxFcTransmitVO
     * output : LinkedList<FinsetVO>
     * Date   : 2017.08.09
     */
    public void setFinsetForFail(TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO){
        removeFinsetForReady(fcMsgSendVO);
        GoodsVO goodsVO = null;
        List<GoodsVO> listFcGoods = fcMsgSendVO.getListGoods();
        for(int i=0;i<listFcGoods.size();i++) {
            goodsVO = listFcGoods.get(i);
            setFinsetForFail(goodsVO,txFcReceiveVO,fcMsgSendVO);
        }
    }
    /**
     * Name   : setFinset
     * Desc   : finset no_prepare 설정
     *        , 전문 no_fc_req(금융기관신청번호) 설정
     *        , TX_FC_RECEIVE DAO
     * input  : TxFcReceiveVO, TxFcTransmitVO
     * output : LinkedList<FinsetVO>
     * Date   : 2017.08.09
     */
    private ReturnClass setFinsetForFail(GoodsVO pGoodsVO, TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO){
        GoodsVO goodsVO = pGoodsVO;
        String no_person     = null;
        String no_bunch      = null;
        String no_prepare    = null;
        String hd_cd_partner = null;
        String cd_fc         = null;//금융사코드
        String no_fc_req     = ""  ;//금융기관신청번호
        String loan_code     = null;//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출
        String cd_goods      = null;//상품코드
        String cd_status     = null;// 전송상태
        String amt_limit_str = null;//대출가능한도
        double rto_loan_dbl  = 0   ;//예상금리
        int    cd_loan_term  = 0   ;//대출기간
        long   hd_cd_result  = 0   ;
        int    cfsCnt = 0;
        String hd_err_msg    = null; //오류메시지

        PrepareVO      prepareVO      = null;
        FinsetVO       finsetVO       = null;
        TxFcTransmitVO txFcTransmitVO = null;

        ReturnClass      rc = null;
        List<GoodsVO> listGoods = null; //대출신청상품정보
        if (fcMsgSendVO != null) {

            no_person  = fcMsgSendVO.getNo_person();
            no_bunch   = fcMsgSendVO.getNo_bunch ();
            loan_code  = fcMsgSendVO.getLoan_code();
            /**
             * 실패인경우 에러를 저장한다.
             */
            if(txFcReceiveVO != null) {
                hd_cd_result = txFcReceiveVO.getHd_cd_result();
                if(hd_cd_result > -1) {
                    cd_status = "02";//01:정상, 02:금융사오류, 03:통신오류
                    hd_err_msg = txFcReceiveVO.getHd_err_msg();
                } else {
                    cd_status = "03";//01:정상, 02:금융사오류, 03:통신오류나 시스템오류, 04:조회중
                    hd_err_msg = txFcReceiveVO.getHd_err_msg();
                }
            } else {
                hd_err_msg = "통신오류";
                cd_status = "03";//01:정상, 02:금융사오류, 03:통신오류
            }

            hd_cd_partner = fcMsgSendVO.getHd_cd_partner();
            no_fc_req     = "";
            cfsCnt = 0;


            prepareVO = new PrepareVO();
            prepareVO.setNo_person       (no_person);
            prepareVO.setYn_overlap_chk  (Y        );
            prepareVO.setYn_exist_person (Y        );
            prepareVO.setId_frt          (no_person);
            prepareVO.setCd_advertisement("21"     );//<!-- 21 홈페이지 -->
            prepareVO.setCd_goods_type   ("01"     );//<!-- 01 신용 -->
            prepareVO.setAmt_apply       ("0"      );
            prepareVO.setCd_used_apply   ("99"     );//<!-- 99 기타 -->
            prepareVO.setCd_collect_path ("01"     );
            //01 : 신용대출(직장인), 02 : 신용대출(사업자), 03 : 담보대출(주택)
            if( StringUtil.isNotEmpty(loan_code) ){
                prepareVO.setCd_goods_gubun(loan_code);
            } else {
                prepareVO.setCd_goods_gubun("01"); // Default : 01(신용대출_직장인)
            }

            if( cfsCnt > 0 ) {
                prepareVO.setCd_prepare_doc_box("10");
            } else {
                prepareVO.setCd_prepare_doc_box("60");
            }

            prepareVO.setCd_collect_path  ("");//<!-- 01 고객이 먼저 중개인에게 연락  -->
            prepareVO.setCd_collect_method("01");//<!-- 01 전화 1 02 SMS -->
            no_prepare = (String) prepareManager.createPrepareSummary(prepareVO).getReturnObj(); //PREPARE_INFO 생성

            logger.info("CREATE PrepareNo : "+no_prepare);
            logger.info("hd_cd_partner : "+hd_cd_partner);
            goodsVO = goodsMapper.getGoodsInfo(goodsVO);
            finsetVO = new FinsetVO();
            finsetVO.setNo_bunch  (no_bunch  );
            finsetVO.setNo_person (no_person );
            finsetVO.setNo_prepare(no_prepare);
            finsetVO.setCd_fc     (cd_fc     );
            finsetVO.setNo_fc_req (no_fc_req );
            finsetVO.setCd_status (cd_status );

            cd_goods     = goodsVO.getCd_goods();
            cd_fc        = goodsVO.getCd_fc   ();
            cd_loan_term = goodsVO.getCd_loan_term();
            rto_loan_dbl = 0.0;

            finsetVO.setCd_goods(cd_goods);
            finsetVO.setCd_fc   (cd_fc   );

            // 대출 기간 (년 단위임 , 개월수로 환산하려면  x12 해야함)
            finsetVO.setYear_term(1);

            // 대출 금리
            finsetVO.setRto_loan(rto_loan_dbl);
            // 대출 한도
            finsetVO.setAmt_limit(amt_limit_str);
            // 가능한도가 0보다 크면 yn_loan Y
            finsetVO.setYn_loan   (N);
            finsetVO.setYn_receive(N      );
            finsetVO.setReason(hd_err_msg);
            finsetManager.insertTxFcReceive(finsetVO);
        }
        rc = new ReturnClass(Constant.SUCCESS, "Prepare Create Success");//return listGoods;
        return rc;
    }
    /**
     * Name   : setFinsetForReady
     * Desc   : 조회전 임시데이터 등록 상품별
     */
    public void setFinsetForReady(TxFcTransmitVO fcMsgSendVO){
        List<GoodsVO>        listFcGoods = null;
        int    goods_size = 0;
        listFcGoods = (fcMsgSendVO != null)? fcMsgSendVO.getListGoods():null;
        goods_size  = (listFcGoods != null)? listFcGoods.size()         :0;
        GoodsVO goodsVO = null;
        for(int i=0;i<goods_size;i++) {
            goodsVO = listFcGoods.get(i);
            setFinsetForReadyByGoods(goodsVO,fcMsgSendVO);
        }
    }

    /**
     * 조회전 임시데이터 등록 상품별
     * @param pGoodsVO
     * @param fcMsgSendVO
     * @return
     */
    private ReturnClass setFinsetForReadyByGoods(GoodsVO pGoodsVO,TxFcTransmitVO fcMsgSendVO){

        GoodsVO goodsVO = pGoodsVO;
        String no_person     = null;
        String no_bunch      = null;
        String no_prepare    = null;
        String hd_cd_partner = null;
        String cd_fc         = null;//금융사코드
        String no_fc_req     = ""  ;//금융기관신청번호
        String loan_code     = null;//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출
        String cd_goods      = null;//상품코드
        String cd_status     = "04";//조회 요청대기중
        String amt_limit_str = null;//대출가능한도
        double rto_loan_dbl  = 0   ;//예상금리
        int    cd_loan_term  = 0   ;//대출기간

        int    cfsCnt = 0;
        String hd_err_msg    = "조회대기중"; //오류메시지

        PrepareVO      prepareVO      = null;
        FinsetVO       finsetVO       = null;
        TxFcTransmitVO txFcTransmitVO = null;

        ReturnClass      rc = null;
        List<GoodsVO> listGoods = null; //대출신청상품정보
        if (fcMsgSendVO != null) {
            no_person  = fcMsgSendVO.getNo_person();
            no_bunch   = fcMsgSendVO.getNo_bunch ();
            loan_code  = fcMsgSendVO.getLoan_code();
//            hd_cd_partner = fcMsgSendVO.getHd_cd_partner();
//            cd_fc         = fcMsgSendVO.getCd_fc        ();
            no_fc_req     = "";
//            cd_fc         = StringUtil.isEmpty(cd_fc) ? hd_cd_partner :  cd_fc;
            cfsCnt = 0;


            prepareVO = new PrepareVO();
            prepareVO.setNo_person       (no_person);
            prepareVO.setYn_overlap_chk  (Y        );
            prepareVO.setYn_exist_person (Y        );
            prepareVO.setId_frt          ("FINSET" );
            prepareVO.setCd_advertisement("21"     );//<!-- 21 홈페이지 -->
            prepareVO.setCd_goods_type   ("01"     );//<!-- 01 신용 -->
            prepareVO.setAmt_apply       ("0"      );
            prepareVO.setCd_used_apply   ("99"     );//<!-- 99 기타 -->
            prepareVO.setCd_collect_path ("01"     );
            //01 : 신용대출(직장인), 02 : 신용대출(사업자), 03 : 담보대출(주택)
            if( StringUtil.isNotEmpty(loan_code) ){
                prepareVO.setCd_goods_gubun(loan_code);
            } else {
                prepareVO.setCd_goods_gubun("01"); // Default : 01(신용대출_직장인)
            }

            if( cfsCnt > 0 ) {
                prepareVO.setCd_prepare_doc_box("10");
            } else {
                prepareVO.setCd_prepare_doc_box("60");
            }

            prepareVO.setCd_collect_path  ("");//<!-- 01 고객이 먼저 중개인에게 연락  -->
            prepareVO.setCd_collect_method("01");//<!-- 01 전화 1 02 SMS -->
            no_prepare = (String) prepareManager.createPrepareSummary(prepareVO).getReturnObj(); //PREPARE_INFO 생성

            logger.info("CREATE PrepareNo : "+no_prepare);
            logger.info("hd_cd_partner : "+hd_cd_partner);
            goodsVO = goodsMapper.getGoodsInfo(goodsVO);
            finsetVO = new FinsetVO();
            cd_fc = goodsVO.getCd_fc();
            finsetVO.setNo_bunch  (no_bunch  );
            finsetVO.setNo_person (no_person );
            finsetVO.setNo_prepare(no_prepare);
            finsetVO.setCd_fc     (cd_fc     );
            finsetVO.setNo_fc_req (no_fc_req );
            finsetVO.setCd_status (cd_status );

            cd_goods     = goodsVO.getCd_goods    ();
            amt_limit_str= "0";
            cd_loan_term = goodsVO.getCd_loan_term();
            rto_loan_dbl = 0.0;

            finsetVO.setCd_goods(cd_goods);

            // 대출 기간 (년 단위임 , 개월수로 환산하려면  x12 해야함)
            finsetVO.setYear_term(1);

            // 대출 금리
            finsetVO.setRto_loan(rto_loan_dbl);
            // 대출 한도
            finsetVO.setAmt_limit(amt_limit_str);
            // 가능한도가 0보다 크면 yn_loan Y
            finsetVO.setYn_loan   (N);
            finsetVO.setYn_receive(N      );
            finsetVO.setReason(hd_err_msg);
            finsetManager.insertTxFcReceive(finsetVO);
        }
        rc = new ReturnClass(Constant.SUCCESS, "Prepare Create Success");//return listGoods;
        return rc;
    }
    /**
     * Name   : removeFinsetForReady
     * Desc   : 조회중 데이터 삭제
     */
    public void removeFinsetForReady(TxFcTransmitVO fcMsgSendVO) {
        String no_bunch      = null;
        FinsetVO       finsetVO       = null;

        ReturnClass      rc = null;
        if (fcMsgSendVO != null) {
            no_bunch   = fcMsgSendVO.getNo_bunch ();
            finsetVO = new FinsetVO();
            finsetVO.setNo_bunch  (no_bunch  );
            prepareManager.deletePrepare(finsetVO); //PREPARE_INFO 삭제

            finsetManager.deleteTxFcReceive(finsetVO);
        }
    }

    /**
     * Name   : reqFcPersonInfo
     * Desc   : 금융사 2차 전문 송수신
     * input  : FinanceMsgSendVO
     * output : TxFcReceiveVO
     * Date   : 2017.07.06
     */
    public TxFcReceiveVO reqFcPersonInfo(TxFcTransmitVO pFcMsgSendVO,ApplyVO pApplyVO) throws ParseException, IOException, FinsetException, FinsetMessageException  {//이지론 전문 2차
        TxFcTransmitVO fcMsgSendVO      = pFcMsgSendVO;
        TxFcReceiveVO  txFcRecvVO       = null;
        FinsetDenyVO   finsetDenyVO     = new FinsetDenyVO();
        EdocForm       edocForm         = null;
        ApplyVO        applyVO          = null;
        EdocVO         edocDetail       = null;
        GoodsVO        goodsVO          = null;
        GoodsVO        goodsVOForSelect = null;

        String sDenyCode         = null;
        String sDenyDesc         = null;

        String no_bunch          = null;
        String rto_loan          = null;// 예상금리
        double dblRto_loan       = 0.00;// 예상금리
        String cd_fc             = null;// 금융사코드
        String nm_fc             = null;// 금융사명
        String no_edoc           = null;// 전문번호
        String type_txrx         = null;//송수신구분
        String type_flex         = null;//송수신구분
        long   hd_seq            = 0   ; //전문일련번호
        long   r_hd_seq          = 0   ; //전문일련번호
        String log               = null;
        String r_cd_fc           = null;// 금융사코드
        String partner_cd        = null;// 제휴사코드
        String no_apply          = null;// 접수번호
        String sts_loan          = null;//대출진행상태
        String amt_approval      = null;// 승인금액
        long   amt_approval_long = 0   ;// 승인금액
        String rto_approval      = null;// 승인금리
        double rto_approval_dbl  = 0   ;// 승인금리
        int    year_term         = 0   ;// 대출기간
        String g_year_term       = null;// 대출기간
        int    g_year_term_int   = 0   ;// 대출기간
        String cd_goods          = null;// 대출상품
        String url_edoc          = null;//전문URL
        long   amt_limit         = 0   ;// 한도금액

        String cd_apply_doc_box  = null;//서류함상태

        String no_person         = null;
        String no_prepare        = null;//사전접수번호
        String cd_fc_ap          = null;//금융사
        String cd_goods_ap       = null;//금융사
        long   hd_cd_result      = 0; //응답코드
        String hd_err_msg        = null; //응답메세지
        if (fcMsgSendVO != null) {
            ReturnClass returnClass = applyManager.createApplyForFinset(pApplyVO);
            fcMsgSendVO.setNo_apply((String) returnClass.getReturnObj());
            partner_cd = fcMsgSendVO.getPartner_cd();
            edocForm = new EdocForm();

            edocForm.setCd_fc(partner_cd);
//            edocForm.setSel_detail("no_edoc");
            edocForm.setTxt_detail("002");
            edocForm.setType_txrx(TX);

            edocDetail = edocManager.getEdocDetail(edocForm);

            logger.info("edocDetail : "+edocDetail);

            if (edocDetail != null) {
                cd_fc     = edocDetail.getCd_fc    ();
                no_edoc   = edocDetail.getNo_edoc  ();
                type_flex = edocDetail.getType_flex();
                url_edoc  = edocDetail.getUrl_edoc ();
                nm_fc     = fincorpManager.getNmFc(cd_fc);

                fcMsgSendVO.setCd_fc    (cd_fc    );
                fcMsgSendVO.setNo_edoc  (no_edoc  );
                fcMsgSendVO.setType_flex(type_flex);

                //금융사별 데이터 디폴트값 설정
                fcMsgSendVO = initFcEdocData(fcMsgSendVO);

                //전문생성 이전 input 값 채우기
                fcMsgSendVO = setFcEdocData(fcMsgSendVO, TRANS_2ND);

                hd_seq   = fcMsgSendVO.getHd_seq();
                no_bunch = fcMsgSendVO.getNo_bunch();
                no_apply = fcMsgSendVO.getNo_apply();

                log = "\n["+hd_seq+"]"+"reqFcPersonInfo no_bunch : "+no_bunch
                        +"\n["+hd_seq+"]"+nm_fc+"("+cd_fc+") "+no_edoc+" 요청";

                LogUtil.infoLn(logger,log);
                type_flex = edocDetail.getType_flex();
                if(TYPE_FLEX_TCP.equals(type_flex)) {//TCP
                    txFcRecvVO = procTcpEdoc(fcMsgSendVO, url_edoc);

                    if(txFcRecvVO != null) {
                        hd_cd_result = txFcRecvVO.getHd_cd_result();
                        if(hd_cd_result != 0) {
                            hd_err_msg = txFcRecvVO.getHd_err_msg();
                            throw new FinsetMessageException(hd_err_msg);
                        }
                        sts_loan     = txFcRecvVO.getSts_loan();
                        amt_approval = txFcRecvVO.getAmt_approval();
                        rto_approval = txFcRecvVO.getRto_approval();
                        year_term    = txFcRecvVO.getYear_term   ();
                        r_cd_fc      = txFcRecvVO.getCd_fc       ();

                        logger.info("Recv TCP : sts_loan     : "+sts_loan    );
                        logger.info("Recv TCP : amt_approval : "+amt_approval);
                        logger.info("Recv TCP : rto_approval : "+rto_approval);
                        logger.info("Recv TCP : year_term    : "+year_term   );
                        logger.info("Recv TCP : hd_seq       : "+hd_seq      );

                        cd_goods     = fcMsgSendVO.getCd_goods   ();

                        rto_approval_dbl = NumberUtil.parseDouble(rto_approval);
                        rto_approval = Double.toString(rto_approval_dbl);

                        txFcRecvVO.setRto_approval(rto_approval);

                        applyVO = new ApplyVO();
                        applyVO.setNo_apply        (no_apply        );
                        applyVO.setCd_apply_doc_box(sts_loan        );
                        applyVO.setAmt_approval    (amt_approval    );
                        applyVO.setRto_loan        (rto_approval_dbl);
                        applyVO.setYear_term       (year_term       );
                        applyVO.setNo_bunch        (no_bunch        );

                        applyManager.modifyApplyDoc(applyVO);

                        cd_apply_doc_box = applyVO.getCd_apply_doc_box();

                        //대출신청 완료 화면:대출금리 표시 (txFcReceiveVO.rto_loan)
                        txFcRecvVO.setRto_loan(rto_approval);

                        //대출신청 완료 화면:대출한도(금액)표시 (txFcReceiveVO.amt_approval)
                        if(amt_approval != null && !amt_approval.equals("")){
                            amt_approval_long = NumberUtil.parseLong(amt_approval);
                            txFcRecvVO.setAmt_limit(amt_approval_long);
                        }
                        //대출신청 완료 화면 :대출기간 표시 (txFcReceiveVO.year_term)
                        //get year_term from DB
                        if(r_cd_fc != null && cd_goods != null){

                            goodsVOForSelect = new GoodsVO();
                            goodsVOForSelect.setCd_fc   (r_cd_fc );
                            goodsVOForSelect.setCd_goods(cd_goods);

                            goodsVO = goodsMapper.getGoodsInfo(goodsVOForSelect);

                            g_year_term = (goodsVO != null)? goodsVO.getYear_term(): null;
                            if(g_year_term != null && !g_year_term.equals("")) {
                                g_year_term_int = NumberUtil.parseInt(amt_approval);
                                txFcRecvVO.setYear_term(g_year_term_int);
                            }
                        }
                        rto_loan  = txFcRecvVO.getRto_loan ();
                        amt_limit = txFcRecvVO.getAmt_limit();
                        r_hd_seq  = txFcRecvVO.getHd_seq   ();

                        logger.info("[reqFcPersonInfo:rto_loan ="+rto_loan +"]" );
                        logger.info("[reqFcPersonInfo:amt_limit="+amt_limit+"]" );
                        logger.info("[reqFcPersonInfo:year_term="+year_term+"]" );
                        logger.info("[reqFcPersonInfo:cd_fc    ="+r_cd_fc  +"]" );
                        logger.info("[reqFcPersonInfo:cd_goods ="+cd_goods +"]" );
                        logger.info("["+hd_seq+"]"+nm_fc+"("+cd_fc+") LOAN STATUS UPDATE "+cd_apply_doc_box);

                        //승인상태가 거절인 경우 거절사유코드 확인 "70"
                        if(ApplyVO.CD_APPLY_DOC_BOX_70.equals(sts_loan)){
                            sDenyCode = txFcRecvVO.getReason();
                            if( sDenyCode != null ) {
                                sDenyDesc = JSTLFunction.getStdCodeName("reason", sDenyCode);
                                logger.info("["+r_hd_seq+"]"+"LOAN DENIAL CODE["+sDenyCode+"] "+sDenyDesc);

                                applyVO = applyManager.getApply(applyVO);

                                if (applyVO != null) {

                                    no_person   = applyVO.getNo_person ();
                                    no_prepare  = applyVO.getNo_prepare();
                                    cd_fc_ap    = applyVO.getCd_fc     ();
                                    cd_goods_ap = applyVO.getCd_goods  ();

                                    finsetDenyVO.setNo_person (no_person  );
                                    finsetDenyVO.setNo_prepare(no_prepare );
                                    finsetDenyVO.setCd_fc     (cd_fc_ap   );
                                    finsetDenyVO.setCd_goods  (cd_goods_ap);
                                    finsetDenyVO.setDeny_desc (sDenyDesc  );

                                    finsetManager.insertFinsetDeny(finsetDenyVO);
                                }
                            }
                        }
                    } else {
                        logger.info("Recv txFcRecvVO : "+txFcRecvVO);
                        throw new FinsetMessageException("전문 통신 실패하였습니다.");
                    }
                } else if(TYPE_FLEX_JSON.equals(type_flex) ){ //JSON
                    txFcRecvVO = procJsonEdoc(fcMsgSendVO, edocDetail.getUrl_edoc());
                    if (txFcRecvVO != null) {
                        hd_cd_result = txFcRecvVO.getHd_cd_result();
                        if(hd_cd_result != 0) {
                            hd_err_msg = txFcRecvVO.getHd_err_msg();
                            throw new FinsetMessageException(hd_err_msg);
                        }
                        sts_loan     = txFcRecvVO.getSts_loan    ();
                        amt_approval = txFcRecvVO.getAmt_approval();
                        rto_approval = txFcRecvVO.getRto_approval();
                        year_term    = txFcRecvVO.getYear_term   ();
                        hd_seq       = txFcRecvVO.getHd_seq      ();
                        rto_loan     = txFcRecvVO.getRto_loan();

                        logger.info("Recv JSON : sts_loan     : "+sts_loan    );
                        logger.info("Recv JSON : amt_approval : "+amt_approval);
                        logger.info("Recv JSON : rto_approval : "+rto_approval);
                        logger.info("Recv JSON : year_term    : "+year_term   );
                        logger.info("Recv JSON : hd_seq       : "+hd_seq      );

                        dblRto_loan = (rto_loan != null)? NumberUtil.parseDouble(rto_loan):0.0;

                        applyVO = new ApplyVO();

                        applyVO.setNo_apply        (no_apply   );
                        applyVO.setCd_apply_doc_box(sts_loan   );
                        applyVO.setRto_loan        (dblRto_loan);
                        applyVO.setYear_term       (year_term  );
                        applyVO.setNo_bunch        (no_bunch   );

                        applyManager.modifyApplyDoc(applyVO);
                        cd_apply_doc_box = applyVO.getCd_apply_doc_box();

                        logger.info("["+hd_seq+"]"+nm_fc+"("+cd_fc+") LOAN STATUS UPDATE "+cd_apply_doc_box);

                        //승인상태가 거절인 경우 거절사유코드 확인 "70"
                        if(ApplyVO.CD_APPLY_DOC_BOX_70.equals(sts_loan)){
                            sDenyCode = txFcRecvVO.getReason();
                            sDenyDesc = JSTLFunction.getStdCodeName("reason", sDenyCode);
                            if(sDenyCode != null){
                                logger.info("["+hd_seq+"]"+"LOAN DENIAL CODE["+sDenyCode+"] "+sDenyDesc);
                            }
                        }
                    } else {
                        hd_err_msg = "통신오류";
                        logger.info("Recv txFcRecvVO : "+txFcRecvVO);
                        throw new FinsetMessageException(hd_err_msg);
                    }
                }
            }
        }
        return txFcRecvVO;
    }

    /**
     * Name   : getLoanProgSts
     * Desc   : 대출진행상태 확인
     * input  : applyForm
     * output : applyVO
     * Date   : 2017.09.26
     **/
    public ReturnClass getLoanProgSts(ApplyForm applyForm) throws IOException, ParseException, FinsetException, FinsetMessageException{

        String id_ageny = null;
        String no_bunch = null;
        String cd_fc    = null;// 금융사코드
        String cd_goods = null;// 대출상품
        String no_apply = null;//접수번호

        TxFcReceiveVO  txFcRecvVO     = null;
        TxFcTransmitVO txFcEdocSendVO = null;

        List<ApplyVO> listApply = null;

        if (applyForm != null) {
            id_ageny = applyForm.getId_agency();
            if(id_ageny != null && id_ageny.equals("P_TEST")) {

                txFcEdocSendVO = loanManager.getTxFcTransmitInfoForMsg( "00000000000000000756" );
                txFcEdocSendVO.setCd_fc   ("0010471");
                txFcEdocSendVO.setCd_goods("PIL100");
                txFcEdocSendVO.setCd_cancelation("0");

                //3차 전문 송수신
                txFcRecvVO = reqFcLoanSts(txFcEdocSendVO);

                if( txFcRecvVO == null ) {
                    throw new FinsetMessageException("전문 통신 실패하였습니다.");
//                    return new ReturnClass(Constant.FAILED, "대출 진행 조회 오류");
                }
                return new ReturnClass(Constant.SUCCESS, "대출 진행 상태 조회 성공");
            }

            //대출상태가 승인인 경우만 조회 : apply_doc_box = '50'
            applyForm.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_50);
            listApply = applyManager.listLoanProgSts(applyForm);

            if( listApply != null ) {
                logger.info("getLoanProgSts Result : "+listApply);
                for(ApplyVO list : listApply){
                    no_bunch = list.getNo_bunch();
                    cd_fc    = list.getCd_fc   ();
                    cd_goods = list.getCd_goods();
                    no_apply = list.getNo_apply();
                    if( no_bunch != null ) {
                        //TX_FC_TRANSMIT 조회
                        txFcEdocSendVO = loanManager.getTxFcTransmitInfoForMsg(no_bunch);
                        if(txFcEdocSendVO != null){

                            txFcEdocSendVO.setCd_fc   (cd_fc   );
                            txFcEdocSendVO.setCd_goods(cd_goods);
                            txFcEdocSendVO.setNo_apply(no_apply);

                            //3차 전문 송수신
                            txFcRecvVO = reqFcLoanSts(txFcEdocSendVO);

                            if( txFcRecvVO == null ){
                                throw new FinsetMessageException("전문 통신 실패하였습니다.");
//                                return new ReturnClass(Constant.FAILED, "대출 진행 조회 오류");
                            }
                        }
                    }
                }
            }
        }
        return new ReturnClass(Constant.SUCCESS, "대출 진행 상태 조회 성공");
    }

    /**
     * Name   : reqFcLoanSts
     * Desc   : 금융사 3차 전문 송수신
     * input  : FinanceMsgSendVO
     * output : TxFcReceiveVO
     * Date   : 2017.07.06
     */
    public TxFcReceiveVO reqFcLoanSts(TxFcTransmitVO pFcMsgSendVO)  throws ParseException, FinsetException, FinsetMessageException,IOException{
        TxFcReceiveVO  txFcRecvVO   = null;
        TxFcTransmitVO fcMsgSendVO  = pFcMsgSendVO;
        FinsetDenyVO   finsetDenyVO = new FinsetDenyVO();
        EdocForm       edocForm     = null;
        EdocVO         edocDetail   = null;
        ApplyVO        applyVO      = null;

        String sDenyCode  = null;
        String sDenyDesc  = null;
        String no_bunch   = null;
        String cd_fc      = null;// 금융사코드
        String no_edoc    = null;// 전문번호
        String nm_fc      = null;// 금융사명
        long   hd_seq     = 0   ; //전문일련번호

        String type_txrx  = "TX";
        String type_flex  = null;//송수신구분
        String url_edoc   = null;//전문URL
        String sel_detail = "no_edoc";
        String txt_detail = TRANS_3TH;

        String no_apply   = null; // 접수번호

        String sts_loan   = null;//대출진행상태
        String no_person  = null;//개인번호
        String no_prepare = null;//개인번호
        String cd_goods   = null;//개인번호
        long hd_cd_result = 0;
        String message = null;
        if (fcMsgSendVO !=null) {
            cd_fc = fcMsgSendVO.getCd_fc();

            edocForm = new EdocForm();
            edocForm.setCd_fc     (cd_fc     );
            edocForm.setSel_detail(sel_detail);
            edocForm.setTxt_detail(txt_detail);
            edocForm.setType_txrx (type_txrx );

            edocDetail = edocManager.getEdocDetail(edocForm);

            logger.info("edocDetail : "+edocDetail);

            cd_fc     = edocDetail.getCd_fc    ();
            no_edoc   = edocDetail.getNo_edoc  ();
            type_flex = edocDetail.getType_flex();
            url_edoc  = edocDetail.getUrl_edoc ();

            fcMsgSendVO.setCd_fc        (cd_fc    );
            fcMsgSendVO.setType_flex    (type_flex);
            fcMsgSendVO.setNo_edoc      (no_edoc  );
            fcMsgSendVO.setHd_cd_partner(cd_fc    );// HEADER 금융사코드

            nm_fc     = fincorpManager.getNmFc(cd_fc);

            //금융사별 데이터 디폴트값 설정
            fcMsgSendVO = initFcEdocData(fcMsgSendVO);

            //전문생성 이전 input 값 채우기
            fcMsgSendVO = setFcEdocData(fcMsgSendVO, TRANS_3TH);

            no_edoc  = fcMsgSendVO.getNo_edoc ();
            hd_seq   = fcMsgSendVO.getHd_seq  ();
            no_bunch = fcMsgSendVO.getNo_bunch();
            no_apply = fcMsgSendVO.getNo_apply();
            hd_seq   = fcMsgSendVO.getHd_seq  ();

            logger.info("["+hd_seq+"]"+nm_fc+"("+cd_fc+") "+ no_edoc+" 요청=============================");

            if(StringUtil.isNotEmpty(no_bunch)){
                logger.info("["+hd_seq+"]"+"reqFcLoanSts no_bunch : "+no_bunch);
            }

            /**
             * TCP
             */
            if(TYPE_FLEX_TCP.equals(type_flex)){//TCP
                txFcRecvVO = procTcpEdoc(fcMsgSendVO, url_edoc);
            }
            /**
             * JSON
             */
            else if(TYPE_FLEX_JSON.equals(type_flex)){
                txFcRecvVO = procJsonEdoc(fcMsgSendVO, url_edoc);
            }

            if(txFcRecvVO != null){
                hd_cd_result = txFcRecvVO.getHd_cd_result();
                if (hd_cd_result == 0) {
                    sts_loan = txFcRecvVO.getSts_loan();
                    // apply_info 기표값 변경 50
                    applyVO = new ApplyVO();
                    applyVO.setNo_apply        (no_apply);
                    applyVO.setCd_apply_doc_box(sts_loan);
                    logger.info("["+hd_seq+"]"+nm_fc+"("+cd_fc+") LOAN STATUS UPDATE "+sts_loan);
                    applyManager.modifyApplyDoc(applyVO);

                    if( ApplyVO.CD_APPLY_DOC_BOX_70.equals(sts_loan) ){
                        sDenyCode = txFcRecvVO.getReason();
                        if( sDenyCode != null ){
                            sDenyDesc = JSTLFunction.getStdCodeName("reason", sDenyCode);
                            logger.info("["+hd_seq+"]"+"LOAN DENIAL CODE["+sDenyCode+"] "+sDenyDesc);

                            applyVO = applyManager.getApply(applyVO);
                            no_person  = applyVO.getNo_person ();
                            no_prepare = applyVO.getNo_prepare();
                            cd_fc      = applyVO.getCd_fc     ();
                            cd_goods   = applyVO.getCd_goods  ();
                            finsetDenyVO.setNo_person (no_person );
                            finsetDenyVO.setNo_prepare(no_prepare);
                            finsetDenyVO.setCd_fc     (cd_fc     );
                            finsetDenyVO.setCd_goods  (cd_goods  );
                            finsetDenyVO.setDeny_desc (sDenyDesc );
                            finsetManager.insertFinsetDeny(finsetDenyVO);
                        }
                    }
                } else {
                    message = txFcRecvVO.getHd_err_msg();
                    throw new FinsetMessageException(message);
                }
            } else {
                logger.info("Recv txFcRecvVO : "+txFcRecvVO);
                throw new FinsetMessageException("전문 통신 실패하였습니다.");
            }
        }
        return txFcRecvVO;
    }

    /**
     * Name   : reqStatusNotiProcess
     * Desc   : 금융사 3차 실시간 조회 처리(
     * input  : FinanceMsgSendVO
     * output : TxFcReceiveVO
     * Date   : 2018.02.01
     */
    public boolean reqStatusNotiProcess(TxFcReceiveVO txFcRecvVO)  throws IOException, ParseException,FinsetException{

        String sDenyDesc = null;
        String no_apply = null;
        boolean retBool = false;
        TxFcReceiveVO txFcReceiveVO = null;
        txFcReceiveVO = txFcRecvVO;
        String sts_loan   = null;//대출진행상태
        String cd_fc      = null;// 금융사코드
        String no_person  = null;//개인번호
        String no_prepare = null;//개인번호
        String cd_goods   = null;//개인번호

        if(txFcReceiveVO != null){
            sts_loan = txFcRecvVO.getSts_loan();

            no_apply = financeMapper.getNoApplyFromFcReq(txFcReceiveVO);

            if(no_apply != null){
                //get apply_info
                ApplyVO applyVO = new ApplyVO();
                applyVO.setNo_apply(no_apply);

                applyVO = applyManager.getApply(applyVO);

                //update apply_info
                applyVO.setCd_apply_doc_box(sts_loan);

                applyManager.modifyApplyDoc(applyVO);

                logger.info("["+no_apply+"]"+"apply_info doc_box update->"+sts_loan);

                if( ApplyVO.CD_APPLY_DOC_BOX_70.equals(sts_loan) ){

                    sDenyDesc = txFcReceiveVO.getReason();
                    if( sDenyDesc != null ){

                        no_person  = applyVO.getNo_person ();
                        no_prepare = applyVO.getNo_prepare();
                        cd_fc      = applyVO.getCd_fc     ();
                        cd_goods   = applyVO.getCd_goods  ();

                        FinsetDenyVO finsetDenyVO = new FinsetDenyVO();
                        finsetDenyVO.setNo_person (no_person );
                        finsetDenyVO.setNo_prepare(no_prepare);
                        finsetDenyVO.setCd_fc     (cd_fc     );
                        finsetDenyVO.setCd_goods  (cd_goods  );
                        finsetDenyVO.setDeny_desc (sDenyDesc );
                        finsetManager.insertFinsetDeny(finsetDenyVO);
                        logger.info("["+no_apply+"]"+"deny_info insert->"+sts_loan);
                    }
                }
                retBool = true;
            }
        }
        return retBool;
    };

    /**
     * Name   : createBinaryData
     * Desc   : 전문 생성(binaryData)
     * input  : FinanceForm
     * output : byte[]
     * Date   : 2017.05.29
     */
    private byte[] createBinaryData(TxFcTransmitVO financeMsgSendVO, String targetUrl) throws FinsetException{
        // TODO Auto-generated method stub

        //inputData를 binaryData 전환
        byte[] bArrServerData = null; //금융사 서버 정보
        byte[] bArrHeadData = null;   //전문 공통부
        byte[] bArrBodyData = null;   //전문 요청부

        byte[] byArray =null;

        ByteBuffer bufFrameData = null;
        //logger.info("FinanceManagerImpl.createBinaryData: financeMsgSendVO = "+financeMsgSendVO);

        if (financeMsgSendVO != null) {

            bArrServerData = createServerPath   (targetUrl       );
            bArrHeadData   = createMsgCommHeader(financeMsgSendVO);
            bArrBodyData   = createMsgBody      (financeMsgSendVO);

            logger.info("header, Body length");
            if(    bArrServerData != null
                    && bArrHeadData   != null
                    && bArrBodyData   != null){
//              String temp = new String(bArrBodyData);
//                logger.debug("bArrBodyData       ="+temp                   );
                logger.debug("bArrHeadData.length="+bArrHeadData.length);
                logger.debug("bArrBodyData.length="+bArrBodyData.length);
                int nFrameDataLen = bArrServerData.length+bArrHeadData.length+bArrBodyData.length;
                if (nFrameDataLen > 0) {
                    byArray      = new byte[nFrameDataLen];
                    bufFrameData = ByteBuffer.allocate(nFrameDataLen);

                    if (bufFrameData != null) {
                        // 요청전문
                        bufFrameData.put(bArrServerData);
                        bufFrameData.put(bArrHeadData);
                        bufFrameData.put(bArrBodyData);
                        //ByteBuffer에 담아놓은 binaryData를 byte[]에 담아두기
                        bufFrameData.clear();
                        bufFrameData.get(byArray);
                    }
                }
            }
        }

        return byArray;
    }



    /**
     * Name   : createServerPath
     * Desc   : FEP에 전달할 금융사 서버 정보
     * input  : String
     * output : byte[]
     * Date   : 2017.05.29
     */
    private byte[] createServerPath(String pTargetUrl) {
        String targetUrl = pTargetUrl;
        String serverIp   = null;
        String serverPort = null;
        byte[] byArray = null;
        ByteBuffer buff = null;
        byte[] byIp = null;
        byte[] byPort = null;
        int    lenByIp   = 0;
        int    lenByPort = 0;
        int portStIndex = 0;
        String log = null;
        int lenServerInfo = 0;
        if (targetUrl != null && targetUrl.contains("://")) {
            //ex) http://211.252.84.96:52222/
            targetUrl   = targetUrl.substring(targetUrl.indexOf("://")+3);
            serverIp    = targetUrl.substring(0, targetUrl.lastIndexOf(":"));
            serverIp    = StringUtil.leftPad(serverIp, 15); //ex)192.168.xxx.xxx
            portStIndex = targetUrl.lastIndexOf(":")+1;
            serverPort  = targetUrl.substring(portStIndex, targetUrl.lastIndexOf("/"));
            serverPort  = StringUtil.leftPad(serverPort, 5); //ex)xxxxx

            if (serverIp != null) {
                byIp = serverIp.getBytes();
                lenByIp = byIp.length;
            }

            if (serverPort != null) {
                byPort = serverPort.getBytes();
                lenByPort = byPort.length;
            }
            //SERVER 정보 설정
            lenServerInfo = lenByIp + lenByPort;

            log = "FC SERVER INFO BINARY LENGTH : "+lenServerInfo
                    +"\n"+"FC SERVER IP["+serverIp+"] PORT["+serverPort+"]";

            LogUtil.infoLn(logger, log);

            if (lenServerInfo > 0) {
                byArray = new byte[lenServerInfo];
                buff = ByteBuffer.allocate(lenServerInfo);
                //FEP용 서버 정보
                if (byIp   != null) {buff.put(byIp  );}
                if (byPort != null) {buff.put(byPort);}

                //ByteBuffer에 담아놓은 binaryData를 byte[]에 담아두기
                buff.clear();
                buff.get(byArray);
            }
        }
        return byArray;
    }

    /**
     * Name   : createMsgCommHeader
     * Desc   : 전문 공통부 생성
     * input  :
     * output : byte[]
     * Date   : 2017.05.29
     */
    private byte[] createMsgCommHeader( TxFcTransmitVO fcEdocSendVO) {

        byte[]     byArray = null;
        ByteBuffer buff    = null;

        long   hd_length     = 0L  ; //전문전체길이
        String hd_cd_service = null; //서비스구분
        long   hd_cd_if      = 0L  ; //전문종별코드
        long   hd_cd_result  = 0L  ; //응답코드
        String hd_cd_partner = null; //제휴사코드
        String hd_err_msg    = null; //오류메시지
        long   hd_seq        = 0L  ; //전문일련번호
        long   hd_dt_send    = 0L  ; //전문전송시간
        String hd_filler     = null; //FILLER

        byte[] bt_hd_length     = null; //전문전체길이
        byte[] bt_hd_cd_service = null; //서비스구분
        byte[] bt_hd_cd_if      = null; //전문종별코드
        byte[] bt_hd_cd_result  = null; //응답코드
        byte[] bt_hd_cd_partner = null; //제휴사코드
        byte[] bt_hd_err_msg    = null; //오류메시지
        byte[] bt_hd_seq        = null; //전문일련번호
        byte[] bt_hd_dt_send    = null; //전문전송시간
        byte[] bt_hd_filler     = null; //FILLER

        String hd_length_str    = null; //전문전체길이
        String hd_cd_if_str     = null; //전문종별코드
        String hd_cd_result_str = null; //응답코드
        String hd_seq_str       = null; //FILLER
        String hd_dt_send_str   = null; //전문전송시간

        String cd_fc            = null;
        String no_edoc          = null;

        String code_value       = " ";
        String type_txrx        = TX;
        if (fcEdocSendVO != null) {
            //해당전문 타입에 따른 전문 컬럼들 조회
            cd_fc   = fcEdocSendVO.getHd_cd_partner();
            no_edoc = fcEdocSendVO.getNo_edoc();
            logger.info("cd_fc : "+cd_fc+"  no_edoc : "+no_edoc+" code_value : "+code_value+" type_txrx : "+type_txrx);
            FcEdocVO fcEdocVO = getListMsgInfo(cd_fc, no_edoc, code_value, type_txrx); //전문컬럼 정보

            int nReqLen = 0;
            int nMsgLen = TxFcTransmitVO.hd_comm_len;

            nReqLen = fcEdocVO.getMsgSize();

            fcEdocSendVO.setHd_length( (nMsgLen+nReqLen) );  
            hd_length         = fcEdocSendVO.getHd_length    ();
            hd_length_str     = String.format("%d",hd_length );
            hd_cd_service     = fcEdocSendVO.getHd_cd_service();
            hd_cd_if          = fcEdocSendVO.getHd_cd_if     ();
            hd_cd_if_str      = String.format("%d",hd_cd_if  );
            hd_cd_result      = fcEdocSendVO.getHd_cd_result ();
            hd_cd_result_str  = String.format("%d",hd_cd_result);
            hd_cd_partner     = fcEdocSendVO.getHd_cd_partner();
            hd_err_msg        = fcEdocSendVO.getHd_err_msg   ();
            hd_seq            = fcEdocSendVO.getHd_seq       ();
            hd_seq_str        = String.format("%d",hd_seq    );
            hd_dt_send        = fcEdocSendVO.getHd_dt_send   ();
            hd_dt_send_str    = String.format("%d",hd_dt_send);
            hd_filler         = fcEdocSendVO.getHd_filler    ();
            logger.info("["+hd_seq+"]"+"SEND EDOC HEADER=============================================================" );
            logger.info("["+hd_seq+"]"+"HD_LENGTH    : "+hd_length    );
            logger.info("["+hd_seq+"]"+"HD_CD_SERVICE: "+hd_cd_service);
            logger.info("["+hd_seq+"]"+"HD_CD_IF     : "+hd_cd_if     );
            logger.info("["+hd_seq+"]"+"HD_CD_RESULT : "+hd_cd_result );
            logger.info("["+hd_seq+"]"+"HD_CD_PARTNER: "+hd_cd_partner);
            logger.info("["+hd_seq+"]"+"HD_ERR_MSG   : "+hd_err_msg   );
            logger.info("["+hd_seq+"]"+"HD_SEQ       : "+hd_seq       );
            logger.info("["+hd_seq+"]"+"HD_DT_SEND   : "+hd_dt_send   );
            logger.info("["+hd_seq+"]"+"HD_FILLER    : "+hd_filler    );
            logger.info("["+hd_seq+"]"+"------------------------------------------------------------------------------------------------------------------------------------------" );

            byArray = new byte[nMsgLen];
            buff = ByteBuffer.allocate(nMsgLen);
            // 공통전문
            buff.put(setStrToByte(lpad(hd_length_str   ,  4, null),   4));
            buff.put(setStrToByte(rpad(hd_cd_service   ,  8, null),   8));
            buff.put(setStrToByte(lpad(hd_cd_if_str    ,  4, null),   4));
            buff.put(setStrToByte(lpad(hd_cd_result_str,  4, null),   4));
            buff.put(setStrToByte(rpad(hd_cd_partner   , 20, null),  20));
            buff.put(setStrToByte(rpad(hd_err_msg      ,200, null), 200));
            buff.put(setStrToByte(lpad(hd_seq_str      , 10, null),  10));
            buff.put(setStrToByte(lpad(hd_dt_send_str  , 14, null),  14));
            buff.put(setStrToByte(rpad(hd_filler       , 60, null),  60));
            //ByteBuffer에 담아놓은 binaryData를 byte[]에 담아두기
            buff.clear();
            buff.get(byArray);
        }

        return byArray;
    }

    /**
     * Name   : createMsgBody
     * Desc   : 전문 요청 내용
     * input  :
     * output : byte[]
     * Date   : 2017.05.29
     */
    private byte[] createMsgBody(TxFcTransmitVO fcEdocSendVO) throws FinsetException{

        int nReqLen = 0;
        byte[] byArray = null;
        ByteBuffer buff = null;
        //전문 타입 분석
        long hd_seq = 0L;
        String field_cd = null;        //전문필드코드\
        Object value = null;
        String log = null;
        String field_len = null;       //전문필드길이
        int len = 0;
        if (fcEdocSendVO != null) {
            hd_seq = fcEdocSendVO.getHd_seq();
            //해당전문 타입에 따른 전문 컬럼들 조회
            logger.info("CD_FC["+fcEdocSendVO.getHd_cd_partner()+"]  NO_EDOC[ "+fcEdocSendVO.getNo_edoc()+"] CODE_VALUE["+" "+"] TYPE_TXRX["+"TX"+"]");

            // TODO : fc_edoc_cd 테이블 읽어오는 부분
            FcEdocVO fcEdocVO = getListMsgInfo(fcEdocSendVO.getHd_cd_partner(), fcEdocSendVO.getNo_edoc(), " ", "TX"); //전문컬럼 정보
            logger.info("["+hd_seq+ "]"+"EDOC BODY LIST : "+fcEdocVO);

            //TxFcTransmitVO 객체 -> JSON 객체로 변환
            JSONObject json = convertClassToJson(fcEdocSendVO);

            if(json != null){
                //금융사표준코드값으로 설정된 정보를 각 금융사코드로 변환 작업 수행
                json = setFcCommCodeToFcCode(json, fcEdocVO.getFcEdocForm()); //금융사 공통 코드 -> 금융사 코드로 변경

                nReqLen = fcEdocVO.getMsgSize();
                //nReqLen = 871;
                logger.info("["+hd_seq+ "]"+"EDOC BODY LENGTH["+nReqLen+"]");

                buff= ByteBuffer.allocate(nReqLen);
                byArray = new byte[nReqLen];


                logger.info("["+hd_seq+ "]"+"SEND EDOC BODY----------------------------------------------------------------------------------------------------------------------" );
                List<FcEdocForm> fcEdocForm = fcEdocVO.getFcEdocForm();
                for(FcEdocForm list : fcEdocForm) {
                    field_cd = list.getField_cd();
                    field_len = list.getField_len();
                    len = NumberUtil.parseInt(field_len);
                    if(field_cd != null) {
                        field_cd = field_cd.toLowerCase();
                        value = json.get(field_cd);
                        if(value == null) {
                            String message = "There is no value of "+field_cd+".";
                            throw new FinsetException(message);
                        }
                        value = value.toString();
                    } else {
                        field_cd = "";
                        value = "";
                    }
                    log = "["+hd_seq+ "]"+"Field_cd["+field_cd+"] : " +value+")";
                    logger.info(log);
                    if(N.equals(list.getField_type_attr())) {
                        if(value instanceof String){
                            buff.put(setStrToByte(lpad((String)value, len, null), len));
                        } else {
                            buff.put(setStrToByte(lpad( String.format("%d", value), len, null), len));
                        }
                    } else {
                        buff.put(setStrToByte(rpad((String)value, len, null), len));
                    }
                }
                logger.info("["+hd_seq+ "]"+"=============================================================================" );

                //ByteBuffer에 담아놓은 binaryData를 byte[]에 담아두기
                buff.clear();
                buff.get(byArray);
            }

        }

        return byArray;
    }

    /**
     * Name   : analysisMsgCommHeader
     * Desc   : 응답받은 메시지에서 공통헤더 매핑
     * input  :
     * output : byte[]
     * Date   : 2017.05.29
     */
    private TxFcReceiveVO analysisMsgCommHeader( byte[] bArrRecvData ){

        TxFcReceiveVO txFcRecvVO = null;
        int    bodyLength = (bArrRecvData != null)? bArrRecvData.length:0;
        long   hd_length     = 0L  ; //전문전체길이
        String hd_cd_service = null; //서비스구분
        long   hd_cd_if      = 0L  ; //전문종별코드
        long   hd_cd_result  = 0L  ; //응답코드
        String hd_cd_partner = null; //제휴사코드
        String hd_err_msg    = null; //오류메시지
        long   hd_seq        = 0L  ; //전문일련번호
        long   hd_dt_send    = 0L  ; //전문전송시간
        String hd_filler     = null; //FILLER
        int[] arrLength = {
                4        /*0 hd_length     */
                ,8        /*1 hd_cd_service */
                ,4        /*2 hd_cd_if      */
                ,4        /*3 hd_cd_result  */
                ,20       /*4 hd_cd_partner */
                ,200      /*5 hd_err_msg    */
                ,10       /* hd_seq        */
                ,14       /* hd_dt_send    */
                ,60       /* hd_filler     */
                ,bodyLength /* 나머지 */
        };
        int allLength = 0;
        for(int i=0;i<arrLength.length - 1;i++){
            allLength+= arrLength[i];
        }
        bodyLength -= allLength;
        LogUtil.debugLn(logger,"BODY LENGTH="+bodyLength);
        int      countColumn = arrLength.length;
        arrLength[countColumn-1] = bodyLength;
        String[] arrStr      = new String[countColumn];
        byte[][] arrByteData = new byte[countColumn][];

        for(int i=0;i<arrByteData.length;i++) {
            arrByteData[i] = new byte[arrLength[i]];
        }

        int stPoint = 0;
        int index   = 0;
        if(bArrRecvData != null) {
            for(int i=0;i<countColumn;i++) {

                System.arraycopy(bArrRecvData, stPoint, arrByteData[i]   , 0, arrByteData[i].length);
                stPoint += arrByteData[i].length;

                switch (i) {
                    case 5:
                    case 8:
                        try {
                            arrStr[i] = new String(arrByteData[i], "EUC-KR");
                            arrStr[i] = arrStr[i].trim();
                        } catch (UnsupportedEncodingException e) {
                            LogUtil.error(logger,e);
                        }
                        break;
                    default:
                        arrStr[i] = new String(arrByteData[i]);
                        arrStr[i] = arrStr[i].trim();
                }
            }

            index = 0;

            hd_length     = NumberUtil.parseLong(arrStr[index++]);
            hd_cd_service = arrStr[index++];
            hd_cd_if      = NumberUtil.parseLong(arrStr[index++]);
            hd_cd_result  = NumberUtil.parseLong(arrStr[index++]);
            hd_cd_partner = arrStr[index++];
            hd_err_msg    = arrStr[index++];
            hd_seq        = NumberUtil.parseLong(arrStr[index++]);
            hd_dt_send    = NumberUtil.parseLong(arrStr[index++]);
            hd_filler     = arrStr[index++];

            txFcRecvVO = new TxFcReceiveVO();

            txFcRecvVO.setHd_length     (hd_length    );
            txFcRecvVO.setHd_cd_service (hd_cd_service);
            txFcRecvVO.setHd_cd_if      (hd_cd_if     );
            txFcRecvVO.setHd_cd_result  (hd_cd_result );
            txFcRecvVO.setHd_cd_partner (hd_cd_partner);
            txFcRecvVO.setHd_err_msg    (hd_err_msg   );
            txFcRecvVO.setHd_seq        (hd_seq       );
            txFcRecvVO.setHd_dt_send    (hd_dt_send   );
            txFcRecvVO.setHd_filler     (hd_filler    );

            logger.debug("["+hd_seq+"]"+fincorpManager.getNmFc(hd_cd_partner)+"("+hd_cd_partner+") "+ hd_cd_service+" 응답<==");
            logger.debug("["+hd_seq+"]"+"RECV EDOC HEADER=============================================================" );
            logger.debug("["+hd_seq+"]"+"HD_LENGTH    : "+hd_length    );
            logger.debug("["+hd_seq+"]"+"HD_CD_SERVICE: "+hd_cd_service);
            logger.debug("["+hd_seq+"]"+"HD_CD_IF     : "+hd_cd_if     );
            logger.debug("["+hd_seq+"]"+"HD_CD_RESULT : "+hd_cd_result );
            logger.debug("["+hd_seq+"]"+"HD_CD_PARTNER: "+hd_cd_partner);
            logger.debug("["+hd_seq+"]"+"HD_ERR_MSG   : "+hd_err_msg   );
            logger.debug("["+hd_seq+"]"+"HD_SEQ       : "+hd_seq       );
            logger.debug("["+hd_seq+"]"+"HD_DT_SEND   : "+hd_dt_send   );
            logger.debug("["+hd_seq+"]"+"HD_FILLER    : "+hd_filler    );
            logger.debug("["+hd_seq+"]"+"-------------------------------------------------------------------------------------------" );
            logger.debug("["+hd_seq+"]"+"RECV BODY DATA ["+arrStr+"]" );

            txFcRecvVO.setCd_fc(hd_cd_partner);
            txFcRecvVO.setNo_edoc(hd_cd_service);
            txFcRecvVO.setbArrData(arrByteData[index]);
        }
        return txFcRecvVO;
    }

    /**
     * Name   : setRecvMsgMapping
     * Desc   : 응답 받은 전문 매핑
     * input  : byte[], String, String
     * output : LinkedList<FinsetVO>
     * Date   : 2017.05.29
     */
    private TxFcReceiveVO setRecvByteMsgMapping(byte[] bArrRecvData, TxFcTransmitVO fcMsgSendVO) throws FinsetException, FinsetMessageException{

        //응답 공통헤더 분리
        TxFcReceiveVO txFcRecvVO = analysisMsgCommHeader(bArrRecvData);

        long     s_hd_length     = 0L  ; //전문전체길이
        String   s_hd_cd_service = null; //서비스구분
        long     s_hd_cd_if      = 0L  ; //전문종별코드
        long     s_hd_cd_result  = 0L  ; //응답코드
        String   s_hd_cd_partner = null; //제휴사코드
        String   s_hd_err_msg    = null; //오류메시지
        long     s_hd_seq        = 0L  ; //전문일련번호
        long     s_hd_dt_send    = 0L  ; //전문전송시간
        String   s_hd_filler     = null; //FILLER

        long     r_hd_length     = 0L  ; //전문전체길이
        String   r_hd_cd_service = null; //서비스구분
        long     r_hd_cd_if      = 0L  ; //전문종별코드
        long     r_hd_cd_result  = 0L  ; //응답코드
        String   r_hd_cd_partner = null; //제휴사코드
        String   r_hd_err_msg    = null; //오류메시지
        long     r_hd_seq        = 0L  ; //전문일련번호
        long     r_hd_dt_send    = 0L  ; //전문전송시간
        String   r_hd_filler     = null; //FILLER

        byte[]   bArrTemp        = null;

        FcEdocVO fcEdocVO        = null; //전문컬럼 정보
        String   no_edoc         = null; //
        List<FcEdocForm> fcEdocForm = null;
        List<FcLoanInfo> loan_info  = null;     //대출상품 리스트(응답)

        JSONObject jsonObj = null;

        String message     = null;

        if(txFcRecvVO != null && fcMsgSendVO != null) {

            s_hd_length     = fcMsgSendVO.getHd_length    ();
            s_hd_cd_service = fcMsgSendVO.getHd_cd_service();
            s_hd_cd_if      = fcMsgSendVO.getHd_cd_if     ();
            s_hd_cd_result  = fcMsgSendVO.getHd_cd_result ();
            s_hd_cd_partner = fcMsgSendVO.getHd_cd_partner();
            s_hd_err_msg    = fcMsgSendVO.getHd_err_msg   ();
            s_hd_seq        = fcMsgSendVO.getHd_seq       ();
            s_hd_dt_send    = fcMsgSendVO.getHd_dt_send   ();
            s_hd_filler     = fcMsgSendVO.getHd_filler    ();

            r_hd_length     = txFcRecvVO.getHd_length    ();
            r_hd_cd_service = txFcRecvVO.getHd_cd_service();
            r_hd_cd_if      = txFcRecvVO.getHd_cd_if     ();
            r_hd_cd_result  = txFcRecvVO.getHd_cd_result ();
            r_hd_cd_partner = txFcRecvVO.getHd_cd_partner();
            r_hd_err_msg    = txFcRecvVO.getHd_err_msg   ();
            r_hd_seq        = txFcRecvVO.getHd_seq       ();
            r_hd_dt_send    = txFcRecvVO.getHd_dt_send   ();
            r_hd_filler     = txFcRecvVO.getHd_filler    ();

            /* hgkim_imsi 페퍼 연동을 위해서 추가 */
            if(StringUtil.isEmpty(r_hd_cd_partner) && !StringUtil.isEmpty(s_hd_cd_partner)) {
                txFcRecvVO.setHd_cd_partner(s_hd_cd_partner);
            }
            /* end */

            bArrTemp = txFcRecvVO.getbArrData();

            /* hgkim_imsi - 1차~3차 연동 : 페퍼 연동 테스트 위해서.
            if( txFcRecvVO.getHd_cd_result() == 0 && fcMsgSendVO.getHd_seq() == txFcRecvVO.getHd_seq() ){
            */
            LogUtil.debugLn(logger,"FinanceManagerImpl.setRecvByteMsgMapping:r_hd_cd_result="+r_hd_cd_result);
            if( r_hd_cd_result == 0 ) {
                no_edoc = txFcRecvVO.getNo_edoc();
                //해당전문 타입에 따른 전문 컬럼들 조회
                fcEdocVO = getListMsgInfo(r_hd_cd_partner, no_edoc , " ", RX);

                //class -> json : class객체에서 jsonObject 타입으로 변경
                jsonObj    = convertClassToJson(txFcRecvVO);

                fcEdocForm = fcEdocVO.getFcEdocForm();

                jsonObj    = analysisMsgResponse(fcEdocForm, bArrTemp, jsonObj, s_hd_cd_partner);

                jsonObj    = setFcCodeToFcCommCode(jsonObj, fcEdocForm);

                //json -> class : JsonObject에서 class 객체로 변환
                Gson gson = new Gson();
                txFcRecvVO = gson.fromJson( jsonObj.toString(), TxFcReceiveVO.class );
                loan_info = txFcRecvVO.getLoan_info();
                LogUtil.debugLn(logger,"loan_info="+loan_info);
                //1차 전문 응답인경우
                if(r_hd_cd_if == 1100 ) {
                    if(loan_info != null && loan_info.size() > 0 ){
//                        txFcRecvVO = checkGoodsFilter(txFcRecvVO, fcMsgSendVO);
                    }
                }
            }
//            else {
//                LogUtil.debugLn(logger,"FinanceManagerImpl.setRecvByteMsgMapping:r_hd_cd_result="+r_hd_cd_result);
//                throw new FinsetMessageException(r_hd_err_msg);
//            }
        }
        return txFcRecvVO;
    }

    /**
     * Name   : setStrToByte
     * Desc   : String을 Byte[] 타입으로 변경하여 반환
     * input  : String, int
     * output : byte[]
     * Date   : 2017.09.26
     */
    private byte[] setStrToByte(String pValue, int bLength){
        String value = pValue;
        if(value == null){
            value = "";
        }

        byte[] bArrVal_01 = value.getBytes();
        byte[] bArrVal_02 = new byte[bLength];

        System.arraycopy(bArrVal_01, 0, bArrVal_02, 0, bArrVal_01.length);
        return bArrVal_02;
    }

    /**
     * 상품 cfs 처리
     * @param txFcRecvVO
     * @param fcMsgSendVO
     * @return
     */
    private TxFcReceiveVO checkGoodsFilter(TxFcReceiveVO txFcRecvVO, TxFcTransmitVO fcMsgSendVO){

        String filterDesc = "";
        int nItemCnt = 0;
        List<FinsetInfo> listFinsetInfo = new ArrayList<FinsetInfo>();
        List<FcLoanInfo> loan_info      = null; //대출상품 리스트(응답)

        String       yn_loan      = null;     //대출가능여부
        FcLoanInfo   fcLoanInfo   = null;
        String       cd_goods     = null;    //상품코드
        String       cd_fc        = null; // 금융사코드
        GoodsInfo    goodsInfo    = null;
        String yn_use                = null;//판매여부
        //CFS 신용 체크박스
        String chk_age_loan          = null;
        String chk_amt_apply         = null;
        String chk_no_month_apply    = null;
        String chk_cd_type_income    = null;
        String chk_cd_employ_type    = null;
        String chk_cd_loan_use       = null;
        String chk_amt_year_income   = null;
        String chk_amt_year_sale     = null;
        String chk_ymd_start_comp    = null;
        String chk_no_job_year       = null;
        String chk_cd_live_type      = null;
        String chk_cd_house_type     = null;
        String chk_yn_proof_income   = null;
        String chk_yn_4insu          = null;
        String chk_yn_bad_credit     = null;
        String chk_yn_delay_current  = null;
        String chk_day_delay_6month  = null;
        String chk_day_delay_12month = null;
        String chk_grade_kcb         = null;
        String chk_grade_nice        = null;
        String chk_dti_grade         = null;
        String chk_grade_ltv         = null;
        String chk_grade_dti         = null;
        String chk_cd_sex            = null;//체크박스 성별
        String chk_cash_service      = null;//현금서비스(건)
        String chk_cd_owncar_type    = null;// 담보소유형태차종류
        String chk_cd_car_type       = null;// 담보종류
        String chk_amt_carprice      = null;//차량가격
        String chk_no_caryear        = null;//차량연식
        FinsetDenyVO finsetDenyVO = null;
        if (fcMsgSendVO != null && txFcRecvVO !=null) {
            loan_info      = txFcRecvVO.getLoan_info();
            nItemCnt       = (loan_info !=null)? loan_info.size() : 0;
            // 응답받은 상품의 건수만큼 확인
            cd_fc    = txFcRecvVO.getCd_fc();
            for(int i=nItemCnt - 1; i>=0; i--){
                filterDesc = "";
                fcLoanInfo = loan_info.get(i);
                yn_loan = fcLoanInfo.getYn_loan();
                if("1".equals(yn_loan)){
                    cd_goods = fcLoanInfo.getCd_goods();
                    //응답 받은 전문의 금융사코드로 상품 테이블 조회
                    goodsInfo = new GoodsInfo();
                    goodsInfo.setCd_fc   (cd_fc   );
                    goodsInfo.setCd_goods(cd_goods);

                    GoodsVO goodsVO = new GoodsVO();
                    goodsVO = goodsMapper.getGoodsInfo(goodsInfo); // 상품 테이블 조회

                    if(goodsVO != null){
                        chk_age_loan          = goodsVO.getChk_age_loan         ();
                        chk_amt_apply         = goodsVO.getChk_amt_apply        ();
                        chk_no_month_apply    = goodsVO.getChk_no_month_apply   ();
                        chk_cd_type_income    = goodsVO.getChk_cd_type_income   ();
                        chk_cd_employ_type    = goodsVO.getChk_cd_employ_type   ();
                        chk_cd_loan_use       = goodsVO.getChk_cd_loan_use      ();
                        chk_amt_year_income   = goodsVO.getChk_amt_year_income  ();
                        chk_amt_year_sale     = goodsVO.getChk_amt_year_sale    ();
                        chk_ymd_start_comp    = goodsVO.getChk_ymd_start_comp   ();
                        chk_no_job_year       = goodsVO.getChk_no_job_year      ();
                        chk_cd_live_type      = goodsVO.getChk_cd_live_type     ();
                        chk_cd_house_type     = goodsVO.getChk_cd_house_type    ();
                        chk_yn_proof_income   = goodsVO.getChk_yn_proof_income  ();
                        chk_yn_4insu          = goodsVO.getChk_yn_4insu         ();
                        chk_yn_bad_credit     = goodsVO.getChk_yn_bad_credit    ();
                        chk_yn_delay_current  = goodsVO.getChk_yn_delay_current ();
                        chk_day_delay_6month  = goodsVO.getChk_day_delay_6month ();
                        chk_day_delay_12month = goodsVO.getChk_day_delay_12month();
                        chk_grade_kcb         = goodsVO.getChk_grade_kcb        ();
                        chk_grade_nice        = goodsVO.getChk_grade_nice       ();
                        chk_dti_grade         = goodsVO.getChk_dti_grade        ();
                        chk_grade_ltv         = goodsVO.getChk_grade_ltv        ();
                        chk_grade_dti         = goodsVO.getChk_grade_dti        ();
                        chk_cd_sex            = goodsVO.getChk_cd_sex           ();
                        chk_cash_service      = goodsVO.getChk_cash_service     ();
                        chk_cd_owncar_type    = goodsVO.getChk_cd_owncar_type   ();
                        chk_cd_car_type       = goodsVO.getChk_cd_car_type      ();
                        chk_amt_carprice      = goodsVO.getChk_amt_carprice     ();

                        finsetDenyVO = new FinsetDenyVO();
                        finsetDenyVO.setNo_person(fcMsgSendVO.getNo_person());
                        finsetDenyVO.setCd_fc(goodsInfo.getCd_fc());
                        finsetDenyVO.setCd_goods(goodsInfo.getCd_goods());

                        logger.info("CHECK GOODS FILTER 조회된 상품 테이블 "+goodsVO.toString());
                        logger.info("CHECK GOODS FILTER Yn_use() : "+goodsVO.getYn_use());
                        //상품판매유무 cfs
                        filterDesc = "";
                        if( Y.equals( goodsVO.getYn_use() ) ){
                            filterDesc = goodsManager.checkGoodsUseYn(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                        }

                        logger.info("CHECK GOODS FILTER Chk_age_loan() : "+chk_age_loan);
                        //나이 cfs
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_age_loan) ) { // 나이가 들어가 있고
                            if( Y.equals(chk_age_loan) ) { // 나이를 체크하도록 설정이 되어 있으면
                                filterDesc = goodsManager.checkAge(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER getChk_amt_apply() : "+chk_amt_apply);
                        filterDesc = "";
                        //신청금액
                        if(StringUtil.isNotEmpty(chk_amt_apply)) { //연소득이 들어가 있고
                            if(Y.equals(chk_amt_apply)){ //연소득을 체크하도록 설정이 되어 있으면
                                filterDesc = goodsManager.checkAmtApply(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_no_month_apply() : "+chk_no_month_apply);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty( chk_no_month_apply)) { // 신청기간(개월) 사용여부
                            if( Y.equals(chk_no_month_apply)){
                                filterDesc = goodsManager.checkNoMonthApply    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_type_income() : "+chk_cd_type_income);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_type_income)) { // 소득형태 사용여부
                            if( Y.equals(chk_cd_type_income)){
                                filterDesc = goodsManager.checkCdTypeIncome    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_employ_type() : "+chk_cd_employ_type);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_employ_type)) { // 고용형태 사용여부
                            if( Y.equals(chk_cd_employ_type)){
                                filterDesc = goodsManager.checkCdEmployType    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_loan_use() : "+chk_cd_loan_use);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_loan_use)) { // 자금용도 사용여부
                            if( Y.equals(chk_cd_loan_use)){
                                filterDesc = goodsManager.checkCdLoanUse      (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_amt_year_income() : "+chk_amt_year_income);
                        filterDesc = "";
                        //연소득 cfs
                        if(StringUtil.isNotEmpty(chk_amt_year_income)) { //연소득이 들어가 있고
                            if(Y.equals(chk_amt_year_income)) { //연소득을 체크하도록 설정이 되어 있으면
                                filterDesc = goodsManager.checkAmtYearIncome(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_amt_year_sale() : " + chk_amt_year_sale);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_amt_year_sale)) { // 연매출액 사용여부
                            if( Y.equals(chk_amt_year_sale)){
                                filterDesc = goodsManager.checkAmtYearSale    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_ymd_start_comp() : "+chk_ymd_start_comp);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_ymd_start_comp)) { // 입사일자 사용여부
                            if( Y.equals(chk_ymd_start_comp)){
                                filterDesc = goodsManager.checkYmdStartComp    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_no_job_year() : "+chk_no_job_year);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_no_job_year)) { // 근속 연수 사용여부
                            if( Y.equals(chk_no_job_year)){
                                filterDesc = goodsManager.checkNoJobYear      (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_live_type() : "+chk_cd_live_type);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_live_type)) { // 주거소유형태 사용여부
                            if( Y.equals(chk_cd_live_type)){
                                filterDesc = goodsManager.checkCdLiveType     (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_house_type() : "+chk_cd_house_type);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_house_type)) { // 주거종류 사용여부
                            if( Y.equals(chk_cd_house_type)){
                                filterDesc = goodsManager.checkCdHouseType    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_yn_proof_income() : "+chk_yn_proof_income);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_yn_proof_income)) { // 소득증빙여부 사용여부
                            if( Y.equals(chk_yn_proof_income)){
                                filterDesc = goodsManager.checkYnProofIncome   (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_yn_4insu() : "+chk_yn_4insu);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_yn_4insu)) { // 4대 보험 가입여부 사용여부
                            if( Y.equals(chk_yn_4insu)){
                                filterDesc = goodsManager.checkYn4insu        (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_yn_bad_credit() : "+chk_yn_bad_credit);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_yn_bad_credit)) { // 신용관리정보등재자 사용여부
                            if( Y.equals(chk_yn_bad_credit)){
                                filterDesc = goodsManager.checkYnBadCredit    (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_yn_delay_current() : "+chk_yn_delay_current);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_yn_delay_current)) { // 연체 여부(현재) 사용여부
                            if( Y.equals(chk_yn_delay_current)){
                                filterDesc = goodsManager.checkYnDelayCurrent  (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_day_delay_6month() : "+chk_day_delay_6month);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_day_delay_6month)) { // 연체 여부(최근 6개월) 사용여부
                            if( Y.equals(chk_day_delay_6month)){
                                filterDesc = goodsManager.checkDayDelay6month  (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_day_delay_12month() : "+chk_day_delay_12month);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_day_delay_12month)) { // 연체여부(최근1년) 사용여부
                            if( Y.equals(chk_day_delay_12month)){
                                filterDesc = goodsManager.checkDayDelay12month (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_grade_kcb() : "+chk_grade_kcb);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_grade_kcb)) { // 신용등급 (KCB ) 사용여부
                            if( Y.equals(chk_grade_kcb)){
                                filterDesc = goodsManager.checkGradeKcb           (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_grade_nice() : "+chk_grade_nice);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_grade_nice)) { // 신용등급 (NICE) 사용여부
                            if( Y.equals(chk_grade_nice)){
                                filterDesc = goodsManager.checkGradeNice      (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_dti_grade() : "+chk_dti_grade);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_dti_grade)) { // DTI_GRADE 사용여부
                            if( Y.equals(chk_dti_grade)){
                                filterDesc = goodsManager.checkDtiGrade           (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        //담보 - 주택
                        logger.info("CHECK GOODS FILTER Chk_grade_ltv() : "+chk_grade_ltv);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_grade_ltv)) { // LTV 사용여부
                            if( Y.equals(chk_grade_ltv)){
                                filterDesc = goodsManager.checkGradeLtv           (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_grade_dti() : "+chk_grade_dti);
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_grade_dti)) { // DTI 사용여부
                            if( Y.equals(chk_grade_dti)){
                                filterDesc = goodsManager.checkGradeDti           (fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        //담보 - 자동차
                        logger.info("CHECK GOODS FILTER Chk_cd_sex()        : "+chk_cd_sex);//성별
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_sex)) {
                            if( Y.equals(chk_cd_sex)){
                                filterDesc = goodsManager.checkCd_sex(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cash_service()  : "+chk_cash_service);//현금서비스(건)
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cash_service)) {
                            if( Y.equals(chk_cash_service)){
                                filterDesc = goodsManager.checkCash_service(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_owncar_type() : "+chk_cd_owncar_type     );// 담보소유형태차종류
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_owncar_type)) {
                            if( Y.equals(chk_cd_owncar_type)){
                                filterDesc = goodsManager.checkCd_owncar_type(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_cd_car_type()   : "+goodsVO.getChk_cd_car_type()        );// 담보종류
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_cd_car_type)) {
                            if( Y.equals(chk_cd_car_type)){
                                filterDesc = goodsManager.checkCd_car_type(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_amt_carprice()  : "+goodsVO.getChk_amt_carprice()   );;//차량가격
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_amt_carprice)) {
                            if( Y.equals(chk_amt_carprice)){
                                filterDesc = goodsManager.checkAmt_carprice(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }

                        logger.info("CHECK GOODS FILTER Chk_no_caryear()    : "+goodsVO.getChk_no_caryear()         );//차량연식
                        filterDesc = "";
                        if( StringUtil.isNotEmpty(chk_no_caryear)) {
                            if( Y.equals(chk_no_caryear)){
                                filterDesc = goodsManager.checkNo_caryear(fcMsgSendVO, goodsVO, listFinsetInfo, filterDesc, finsetDenyVO);
                            }
                        }
                    }



                    if( listFinsetInfo.size() > 0 ){
                        txFcRecvVO.getLoan_info().remove(i);
                        listFinsetInfo.clear();
                    }
                }

//              else if("2".equals(txFcRecvVO.getLoan_info().get(i).getYn_loan())){ //대출 상품이 거절 상태인 경우 삭제
//                  txFcRecvVO.getLoan_info().remove(i);
//                  listFinsetInfo.clear();
//              }
            }

            logger.info("CHECK GOODS FILTER RESULT : "+txFcRecvVO);
        } else {
            logger.info("CHECK GOODS FILTER RESULT :txFcRecvVO="+txFcRecvVO);
        }
        return txFcRecvVO;
    }

    /**
     * 해당 전문 리스트 가져오기
     * @param cd_fc
     * @param no_edoc
     * @param code_value
     * @param type_txrx
     * @return
     */
    public FcEdocVO getListMsgInfo(String cd_fc, String no_edoc, String code_value, String type_txrx){
        FcCodeForm fcCodeForm = new FcCodeForm();

        fcCodeForm.setCd_fc     (cd_fc     );
        fcCodeForm.setNo_edoc   (no_edoc   );
        fcCodeForm.setCode_value(code_value);
        fcCodeForm.setType_txrx (type_txrx );

        return fcCodeManager.listFcEdocInfo(fcCodeForm);
    }

    /**
     * 금융사표준코드값 -> 금융사코드로 변환 작업 수행
     * @param json
     * @param fcEdocForm
     * @return
     */
    private JSONObject setFcCommCodeToFcCode(JSONObject json, List<FcEdocForm> fcEdocForm)  throws FinsetException {

        logger.info("COMMON CODE JSON DATA : "+json.toString());

        FcCodeForm fcCodeForm = new FcCodeForm();
        JSONObject jsonResult = null;

        String field_cd       = null; //전문필드코드
        String field_nm       = null; //전문필드명
        String field_len      = null; //전문필드길이
        String field_tag      = null; //전문필드태그
        String field_type     = null; //전문필드타입
        String field_db_nm    = null; //전문필드DB명
        String repeat_yn      = null; //반복여부
        String field_type_attr= null; //전문필드데이터타입 //AN/N

        String data           = null; //데이터
        Object  objData        = null ; //데이터
        if (json != null && fcEdocForm !=null) {
            fcCodeForm.setCd_fc  ((String)json.get("hd_cd_partner")); //금융사코드
            fcCodeForm.setNo_edoc((String)json.get("no_edoc"));       //전문번호
            fcCodeForm.setType_txrx("TX");

            jsonResult = new JSONObject();
            String sValue = null;
            for(FcEdocForm list : fcEdocForm){

                field_cd        = list.getField_cd       ();
                field_nm        = list.getField_nm       ();
                field_len       = list.getField_len      ();
                field_tag       = list.getField_tag      ();
                field_type      = list.getField_type     ();
                field_db_nm     = list.getField_db_nm    ();
                repeat_yn       = list.getRepeat_yn      ();
                field_type_attr = list.getField_type_attr();

                field_cd        = field_cd   .toLowerCase();
                field_db_nm     = field_db_nm.toLowerCase();

                objData         = json.get(field_db_nm);
                if(objData == null) {
                    String message = "There is no value of "+field_db_nm+":field_db_nm.";
                    throw new FinsetException(message);
                }
                data            = objData.toString();
                field_type  = list.getField_type();
                logger.info(field_nm+"("+field_tag+") : "+data);

                // L 은 코드 타입 : 코드 타입은 실제 변환이 필요하지 않아도 표준 항목 코드로
                if( StringUtil.isNotEmpty(field_type) && L.equals(field_type)) { //타입이 "L" 이면 코드 타입 대상
                    if( StringUtil.isNotEmpty(data) ){

                        logger.info(field_nm+"("+field_tag+") FcCommCode value : "+data);

                        fcCodeForm.setItem_tag      (field_tag); //코스콤표준코드
                        fcCodeForm.setItem_tag_value(data     ); //코스콤표준코드값

                        sValue = fcCodeManager.getFcCommCdToFcCd(fcCodeForm);//금융사코드 값 조회
                        sValue =  N.equals(field_type_attr) ? NumberUtil.parseNumber(sValue).toString() : sValue;
                        jsonResult.put(field_cd,sValue);

                        data = jsonResult.get(field_cd).toString();

                        logger.info(field_nm+"("+field_tag+") FcCode value : "+data);
                    }
                }   else if( StringUtil.isNotEmpty(field_type) && T.equals(field_type)) {//타입이 "T" 이면 비코드 타입 대상
                    jsonResult.put(field_cd, data);
                }
            }
            logger.info("FC CODE JOSN DATA : "+jsonResult.toString());
        }
        return jsonResult;
    }

    /**
     * 금융사코드를 금융사표준코드값으로 변환 작업 수행
     * @param json
     * @param fcEdocForm
     * @return
     */
    private JSONObject setFcCodeToFcCommCode(JSONObject json, List<FcEdocForm> fcEdocForm) throws FinsetException {

        logger.info("FC CODE JOSN DATA : "+json.toString());

        String sValue = null;
        String cd_fc      = (json != null)?(String)json.get("cd_fc")  :"";
        String no_edoc    = (json != null)?(String)json.get("no_edoc"):"";
        String type_txrx  = RX;
        String sel_detail = null;
        String txt_detail = null;

        FcCodeForm fcCodeForm = new FcCodeForm();

        fcCodeForm.setCd_fc    (cd_fc    );    //금융사코드
        fcCodeForm.setNo_edoc  (no_edoc  );  //전문번호
        fcCodeForm.setType_txrx(type_txrx);

        String field_cd       = null; //전문필드코드
        String field_nm       = null; //전문필드명
        String field_len      = null; //전문필드길이
        String field_tag      = null; //전문필드태그
        String field_type     = null; //전문필드타입
        String field_db_nm    = null; //전문필드DB명
        String repeat_yn      = null; //반복여부
        String field_type_attr= null; //전문필드데이터타입 //AN/N

        String r_field_cd       = null; //전문필드코드
        String r_field_nm       = null; //전문필드명
        String r_field_len      = null; //전문필드길이
        String r_field_tag      = null; //전문필드태그
        String r_field_type     = null; //전문필드타입
        String r_field_db_nm    = null; //전문필드DB명
        String r_repeat_yn      = null; //반복여부
        String r_field_type_attr= null; //전문필드데이터타입 //AN/N

        Object  objData        = null ; //데이터
        String  data           = null ; //데이터
        String  r_data         = null ; //데이터
        Object  r_objData      = null ; //데이터
        boolean isBlank        = false; // 값이 있는지 여부
        FcEdocVO fcMsgRepeatVO = null ; //전문컬럼 정보

        JSONObject jObj;

        JSONArray jArrData;
        for(FcEdocForm list : fcEdocForm) {

            field_cd        = list.getField_cd       ();
            field_nm        = list.getField_nm       ();
            field_len       = list.getField_len      ();
            field_tag       = list.getField_tag      ();
            field_type      = list.getField_type     ();
            field_db_nm     = list.getField_db_nm    ();
            field_db_nm     = field_db_nm.toLowerCase();
            repeat_yn       = list.getRepeat_yn      ();
            field_type_attr = list.getField_type_attr();
            field_cd        = field_cd.toLowerCase();
            objData         = json.get(field_cd);
            if(objData == null) {
                String message = "There is no value of "+field_cd+".";
                throw new FinsetException(message);
            }
            data            = objData.toString();
            logger.info(list.getField_nm()+"("+field_cd+") : "+data+" : "+field_type);

            if( StringUtil.isNotEmpty(field_type)){ //타입이 "L" 이면 코스콤표준코드 대상

                isBlank = "AN".equals(field_type_attr) ? StringUtil.isNotEmpty(field_cd) : json.getInt(field_cd) != 0;

                if(isBlank) {

                    logger.info(field_nm+"("+field_cd+") FcCode value : " +data);

                    if( L.equals(field_type)){ //타입이 코드 타입인 경우

                        fcCodeForm.setItem_tag  (field_tag); //금융사코드
                        fcCodeForm.setCode_value(data     ); //코스콤표준코드값

                        //코스콤표준코드->금융사표준코드 값 조회
                        sValue = fcCodeManager.getFcCdToFcCommCd(fcCodeForm);


                        sValue =  N.equals(field_type_attr) ? NumberUtil.parseNumber(sValue).toString() : sValue;

                        json.put(field_db_nm, sValue); //금융사표준코드값으로 변경

                        json.remove(field_cd); //금융사표준코드값으로 변경
                        objData  = json.get(field_db_nm);
                        if(objData == null) {
                            String message = "There is no value of "+field_db_nm+":field_db_nm.";
                            throw new FinsetException(message);
                        }
                        data = objData.toString();

                        logger.info(field_nm+ "("+field_cd+") FcCommCode value : "+data);

                    }else if(T.equals(field_type)){ //타입이 비코드 타입인 경우
                        json.remove(field_cd); //금융사표준코드값으로 변경
                        json.put(field_db_nm, objData);
                    }
                    //====================================================================================
                    //반복코드의 처리 로직 2017.08.11 신규 생성
                    if( Y.equals(list.getRepeat_yn()) ){ //반복부가 있는 경우

                        sel_detail = "parent_code_group";
                        txt_detail = list.getField_cd();

                        fcMsgRepeatVO = getListEdocRepeatInfo(cd_fc, no_edoc, type_txrx, sel_detail, txt_detail);
                        logger.info("EDOC REPEAT INFO : "+fcMsgRepeatVO.toString());

                        jArrData = json.getJSONArray("loan_info");

                        int nSize = jArrData.size();

                        for(int i = 0; i < nSize; i++){

                            jObj = (JSONObject)jArrData.get(i);
                            for(FcEdocForm listRepeat : fcMsgRepeatVO.getFcEdocForm()){

                                r_field_cd        = listRepeat.getField_cd       ();
                                r_field_nm        = listRepeat.getField_nm       ();
                                r_field_len       = listRepeat.getField_len      ();
                                r_field_tag       = listRepeat.getField_tag      ();
                                r_field_type      = listRepeat.getField_type     ();
                                r_field_db_nm     = listRepeat.getField_db_nm    ();
                                r_repeat_yn       = listRepeat.getRepeat_yn      ();
                                r_field_type_attr = listRepeat.getField_type_attr();
                                r_field_cd        = r_field_cd.toLowerCase();
                                r_field_db_nm     = r_field_db_nm.toLowerCase();
                                r_objData         = jObj.get(r_field_cd);
                                if(r_objData == null) {
                                    String message = "There is no value of "+r_field_cd+" in REPEAT INFO.";
                                    throw new FinsetException(message);
                                }
                                r_data = "AN".equals(r_field_type_attr)?(String)r_objData:"";
                                if( StringUtil.isNotEmpty(r_field_type) ){

                                    logger.info("타입 : "+r_field_type_attr +" json 필드 : "+r_field_cd +"/ 코드 : "+r_objData);

                                    if("AN".equals(r_field_type_attr) ? StringUtil.isNotEmpty(r_data) : jObj.getInt(r_field_cd) != 0 ){

                                        logger.info(r_field_nm+"("+r_field_cd+") Repeat FcCode value : "+r_objData);

                                        if( L.equals(r_field_type)){// 타입이 코드 인경우
                                            fcCodeForm.setItem_tag(r_field_tag); //코스콤표준코드
                                            if( r_objData instanceof String ){
                                                fcCodeForm.setCode_value(r_data); //코스콤표준코드값
                                            }else{
                                                r_data =  String.format("%d",r_objData);
                                                fcCodeForm.setCode_value(r_data); //코스콤표준코드값
                                            }
                                            //코스콤표준코드->금융사표준코드 값 조회
                                            sValue = fcCodeManager.getFcCdToFcCommCd(fcCodeForm);

                                            sValue =  N.equals(r_field_type_attr) ? NumberUtil.parseNumber(sValue).toString() : sValue;

                                            jObj.put(r_field_db_nm, sValue); //금융사표준코드값으로 변경
                                            jObj.remove(r_field_cd);
                                        } else if( T.equals(r_field_type)) { // 타입이 비코드 인경우
                                            jObj.put(r_field_db_nm, r_objData);
                                            jObj.remove(r_field_cd);
                                        }
                                        r_objData = jObj.get(r_field_db_nm);
                                        logger.info(r_field_nm+"("+r_field_db_nm+") Repeat FcCommCode value : "+r_objData);
                                    }
                                }
                            }
                            jArrData.set(i,  jObj);
                        }
                    }
                }
            }
        }
        return json;
    }

    /**
     * class 객체에서 json 타입으로 변환
     * @param obj
     * @return
     */
    private JSONObject convertClassToJson(Object obj){

        Gson gson = new Gson();
        String objJson = gson.toJson(obj);
        logger.info("String objJson  : "+objJson);

        JSONObject jsonObj = null;
        jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(objJson));
        logger.info("class -> jsonData : "+jsonObj);
        return jsonObj;
    }

    /**
     * 응답전문 분석
     * @param fcEdocForm
     * @param bArrTemp
     * @param jsonObj
     * @return
     */
    private JSONObject analysisMsgResponse(List<FcEdocForm> fcEdocForm, byte[] bArrTemp, JSONObject jsonObj , String hd_cd_partner){

        JSONObject jLoanObj = new JSONObject();
        JSONArray jArray = new JSONArray();
        int nItemCnt = 0;
        Object hd_seq  = null;
        String   no_edoc        = null;

        String   field_cd       = null; //전문필드코드
        String   field_nm       = null; //전문필드명
        String   field_len      = null; //전문필드길이
        String   field_tag      = null; //전문필드태그
        String   field_type     = null; //전문필드타입
        String   field_db_nm    = null; //전문필드DB명
        String   repeat_yn      = null; //반복여부
        String   field_type_attr= null; //전문필드데이터타입 //AN/N


        String r_field_cd       = null; //전문필드코드
        String r_field_nm       = null; //전문필드명
        String r_field_len      = null; //전문필드길이
        String r_field_tag      = null; //전문필드태그
        String r_field_type     = null; //전문필드타입
        String r_field_db_nm    = null; //전문필드DB명
        String r_repeat_yn      = null; //반복여부
        String r_field_type_attr= null; //전문필드데이터타입 //AN/N

        int      int_field_len  = 0   ;
        int      int_r_field_len  = 0   ;
        int      int_param_len  = 0   ;
        Object   num_data       = 0   ;
        byte[]   bArrData       = null;
        byte[]   bArrRepeat     = null;
        String   strData        = null;
        FcEdocVO fcMsgRepeatVO  = null; //전문컬럼 정보
        if (jsonObj !=null && bArrTemp !=null && fcEdocForm != null) {
            int_param_len = bArrTemp.length;
            hd_seq  = jsonObj.get("hd_seq");
            no_edoc = (String)jsonObj.get("no_edoc");
            logger.info("["+hd_seq+"]"+"RECV EDOC BODY-----------------------------------------------------------------------" );
            for(FcEdocForm list : fcEdocForm){
                field_cd        = list.getField_cd       ();
                field_nm        = list.getField_nm       ();
                field_len       = list.getField_len      ();
                field_tag       = list.getField_tag      ();
                field_type      = list.getField_type     ();
                field_db_nm     = list.getField_db_nm    ();
                repeat_yn       = list.getRepeat_yn      ();
                field_type_attr = list.getField_type_attr();
                int_field_len   = NumberUtil.parseInt(field_len);

                field_cd    = field_cd.toLowerCase();
                field_db_nm = field_db_nm.toLowerCase();
                bArrData = new byte[int_field_len];
                System.arraycopy(bArrTemp , 0, bArrData , 0, int_field_len);
                int_param_len = bArrTemp.length;
                if( (int_param_len - int_field_len) > 0 ) {
                    System.arraycopy(bArrTemp, int_field_len, bArrTemp, 0, int_param_len - int_field_len);
                }
                strData = new String(bArrData);
                strData = strData.trim();
                logger.info( "["+hd_seq+ "]"+"Field_cd["+field_cd+"]["+strData+"]" );

                if( N.equals(field_type_attr) ) {
                    num_data = NumberUtil.parseNumber(ltrim(strData));
                    jsonObj.put(field_cd, num_data);
                }else if( "AN".equals(field_type_attr) ){
                    jsonObj.put(field_cd, rtrim(strData));
                }

                if(Y.equals(repeat_yn)){
                    if(StringUtil.isNotEmpty(strData)) {
                        nItemCnt = Integer.parseInt(strData);
                    }

                    fcMsgRepeatVO = getListEdocRepeatInfo(hd_cd_partner, (String)no_edoc, RX, "parent_code_group", field_cd);

                    logger.info("EDOC REPEAT LIST : "+fcMsgRepeatVO);

                    for(int i = 0; i < nItemCnt; i++){

                        List<FcEdocForm> fcMsgRepeatForm = fcMsgRepeatVO.getFcEdocForm();
                        for( FcEdocForm listRepeat : fcMsgRepeatForm ){

                            r_field_cd        = listRepeat.getField_cd       ();
                            r_field_nm        = listRepeat.getField_nm       ();
                            r_field_len       = listRepeat.getField_len      ();
                            r_field_tag       = listRepeat.getField_tag      ();
                            r_field_type      = listRepeat.getField_type     ();
                            r_field_db_nm     = listRepeat.getField_db_nm    ();
                            r_repeat_yn       = listRepeat.getRepeat_yn      ();
                            r_field_type_attr = listRepeat.getField_type_attr();
                            int_r_field_len   = NumberUtil.parseInt(r_field_len);

                            r_field_cd = r_field_cd.toLowerCase();
                            bArrRepeat = new byte[int_r_field_len];

                            int_param_len = bArrTemp.length;

                            System.arraycopy(bArrTemp   , 0, bArrRepeat       , 0, int_r_field_len);

                            if( (bArrTemp.length - int_r_field_len) > 0 ) {
                                System.arraycopy(bArrTemp   , int_r_field_len, bArrTemp, 0, int_param_len - int_r_field_len);
                            }
                            strData = new String(bArrRepeat);
                            strData = strData.trim();
                            strData = rtrim(strData);

                            logger.info("["+hd_seq+"]"+"Field_cd["+r_field_cd+"]["+strData+"]");

                            if(N.equals(r_field_type_attr)) {
                                num_data = NumberUtil.parseNumber(strData);
                                jLoanObj.put(r_field_cd, num_data);
                            } else if( "AN".equals(r_field_type_attr) ){
                                jLoanObj.put(r_field_cd, strData);
                            }
                        }
                        jArray.add(jLoanObj);
                    }
                    jsonObj.put( "loan_info", jArray );
                }
                bArrData = null;
            }
            logger.info("=============================================================" );
            logger.info("RECV EDOC DATA JSONDATA : "+jsonObj);
        }

        return jsonObj;
    }

    /**
     * 전문 리스트의 반복부를 가져오기
     * @param cd_fc
     * @param no_edoc
     * @param type_txrx
     * @param sel_detail
     * @param txt_detail
     * @return
     */
    public FcEdocVO getListEdocRepeatInfo(String cd_fc, String no_edoc, String type_txrx, String sel_detail, String txt_detail){
        FcCodeForm fcCodeForm = new FcCodeForm();
        fcCodeForm.setCd_fc     (cd_fc     );
        fcCodeForm.setNo_edoc   (no_edoc   );
        fcCodeForm.setType_txrx (type_txrx );
        fcCodeForm.setSel_detail(sel_detail);
        fcCodeForm.setTxt_detail(txt_detail);

        logger.info("getListEdocRepeatInfo - hd_cd_partner : "+cd_fc);
        logger.info("EDOC REPEAT INFO : "+fcCodeForm.toString());

        return fcCodeManager.listFcEdocRepeatInfo(fcCodeForm);
    }

    //JSON-------------------------------------------------------------------------------------

    /**
     * Name   : setRecvJsonEdocMapping
     * Desc   : 응답 받은 전문 매핑 1차전문
     * input  : byte[], String, String
     * output : LinkedList<FinsetVO>
     * Date   : 2017.05.29
     */
    private TxFcReceiveVO setRecvJsonEdocMapping(JSONObject pJson, TxFcTransmitVO fcMsgSendVO) throws FinsetException {

        TxFcReceiveVO txFcRecvVO = null;
        FcEdocVO      fcEdocVO  = null;
        JSONObject    json      = pJson;
        String        cd_fc     = null;
        String        no_edoc   = null;//전문번호

        List<FcEdocForm> fcEdocForm = null;
        //해당전문 타입에 따른 전문 컬럼들 조회
        if (fcMsgSendVO != null) {
            cd_fc   = fcMsgSendVO.getCd_fc();
            no_edoc = fcMsgSendVO.getNo_edoc();
            fcEdocVO = getListMsgInfo(cd_fc, no_edoc, " ", RX); //전문컬럼 정보
            logger.info("finance : "+cd_fc+" edoc : "+no_edoc+"FcEdoc List : "+fcEdocVO.toString());

            if(fcEdocVO != null) {
                cd_fc      = fcMsgSendVO.getCd_fc();
                no_edoc    = fcMsgSendVO.getNo_edoc();
                fcEdocForm = fcEdocVO.getFcEdocForm();
                json = analysisJsonEdocResponse(fcEdocForm, json, cd_fc, no_edoc, " ", RX);
                logger.info("after analysisJsonEdocResponse jsonObj : "+json.toString());
                if(json != null) {
                    json = setJsonFcCodeToFcCommCode(json, fcEdocForm, cd_fc, no_edoc);
                }
            }

            if(json != null){
                //JsonObject에서 class 객체로 변환
                Gson gson = new Gson();
                txFcRecvVO = gson.fromJson( json.toString(), TxFcReceiveVO.class );
                logger.info( "txFcRecvVO : "+txFcRecvVO.toString() );

                //1차 전문 응답인경우
                if(txFcRecvVO != null &&  HSB001.equals( txFcRecvVO.getCd_service() ) ){
                    txFcRecvVO = checkGoodsFilter(txFcRecvVO, fcMsgSendVO);
                }
            }
        }
        return txFcRecvVO;
    }

    /**
     * 금융사코드를 금융사표준코드값으로 변환 작업 수행
     * @param json
     * @param fcEdocForm
     * @param cd_fc
     * @param no_edoc
     * @return
     */
    private JSONObject setJsonFcCodeToFcCommCode(JSONObject json, List<FcEdocForm> fcEdocForm, String cd_fc, String no_edoc){
        LogUtil.debugLn(logger,"setJsonFcCodeToFcCommCode:no_edoc="+no_edoc);
        FcCodeForm fcCodeForm = new FcCodeForm();
        fcCodeForm.setCd_fc  (cd_fc);    //금융사코드
        fcCodeForm.setNo_edoc(no_edoc); //전문번호

        String sValue = null;

        String type_txrx  = null;
        String sel_detail = null;
        String txt_detail = null;

        String field_nm    = null;
        String field_cd    = null;        //전문필드코드
        String ufield_cd   = null;        //전문필드코드\
        String field_type  = null;      //전문필드타입
        String data        = null;
        Object r_data      = null;
        String field_tag   = null;       //전문필드태그
        String field_db_nm = null;     //전문필드DB명

        String r_field_cd       = null; //전문필드코드
        String r_field_nm       = null; //전문필드명
        String r_field_len      = null; //전문필드길이
        String r_field_tag      = null; //전문필드태그
        String r_field_type     = null; //전문필드타입
        String r_field_db_nm    = null; //전문필드DB명
        String r_repeat_yn      = null; //반복여부
        String r_field_type_attr= null; //전문필드데이터타입 //AN/N


        String log         = null;
        if (json != null && fcEdocForm != null) {
            for(FcEdocForm list : fcEdocForm){

                if (list != null) {
                    field_nm = list.getField_nm();
                    field_cd = list.getField_cd();        //전문필드코드
                    field_nm = (field_nm != null)? field_nm : "";
                    field_cd = (field_cd != null)? field_cd : "";
                    ufield_cd = field_cd.toLowerCase();
                    data = ""+json.get(ufield_cd);

                    logger.info(field_nm+"("+field_cd+") : "+data);
                    field_type = list.getField_type();
                    if( StringUtil.isNotEmpty(field_type)){ //타입이 "L" 이면 코스콤표준코드 대상
                        field_db_nm = list.getField_db_nm();
                        field_db_nm = field_db_nm.toLowerCase();

                        if(StringUtil.isNotEmpty(data) && L.equals(field_type)){
                            field_tag =  list.getField_tag();
                            logger.info(field_nm+"("+field_tag+") 금융사표준코드값 : "+data);

                            fcCodeForm.setItem_tag(field_tag); //코스콤표준코드
                            fcCodeForm.setCode_value(data); //코스콤표준코드값

                            //코스콤표준코드->금융사표준코드 값 조회
                            sValue = fcCodeManager.getFcCdToFcCommCd(fcCodeForm);
                            json.remove(field_db_nm); //금융사표준코드값으로 변경
                            json.put   (field_db_nm, sValue); //금융사표준코드값으로 변경
                            log = field_nm+"("+field_tag+ ") 코스콤표준전문코드값 : "+sValue;
                            logger.info(log);
                        }else if( StringUtil.isNotEmpty( field_type ) && T.equals(field_type)){
                            json.remove(field_db_nm); //금융사표준코드값으로 변경
                            json.put(field_db_nm, ufield_cd);
                        }

                        //====================================================================================
                        //반복코드의 처리 로직 2017.08.11 신규 생성
                        logger.info("@@@@ : "+list.getRepeat_yn());
                        if( Y.equals(list.getRepeat_yn()) ){
                            type_txrx  = RX;
                            sel_detail = "parent_code_group";
                            txt_detail = list.getField_cd();

                            FcEdocVO fcMsgRepeatVO = getListEdocRepeatInfo(cd_fc, no_edoc, type_txrx, sel_detail, txt_detail); //전문컬럼 정보
                            logger.info("MsgRepeatInfo -------------------------------------"+fcMsgRepeatVO.toString());

                            JSONArray jArrData = json.getJSONArray("loan_info");
                            logger.info("loan_info : "+jArrData.toString());
                            int nSize = jArrData.size();

                            for(int i = 0; i < nSize; i++){

                                logger.info("jArrData.get(i).toString()"+jArrData.get(i).toString());
                                JSONObject jObj = (JSONObject)jArrData.get(i);

                                for(FcEdocForm listRepeat : fcMsgRepeatVO.getFcEdocForm()){
                                    r_field_cd        = listRepeat.getField_cd       ();
                                    r_field_nm        = listRepeat.getField_nm       ();
                                    r_field_len       = listRepeat.getField_len      ();
                                    r_field_tag       = listRepeat.getField_tag      ();
                                    r_field_type      = listRepeat.getField_type     ();
                                    r_field_db_nm     = listRepeat.getField_db_nm    ();
                                    r_repeat_yn       = listRepeat.getRepeat_yn      ();
                                    r_field_type_attr = listRepeat.getField_type_attr();
                                    r_field_cd        = r_field_cd.toLowerCase();
                                    r_data            = json.get(r_field_cd);
                                    r_field_db_nm =r_field_db_nm.toLowerCase();
                                    if( StringUtil.isNotEmpty(r_field_type) && L.equals(r_field_type) ){ //타입이 L 이면 코스콤표준코드 대상
                                        if( StringUtil.isNotEmpty( (String)json.get(r_field_cd) ) ){
                                            logger.info(r_field_nm+"("+r_field_tag+") 금융사표준코드값 : "+r_data);

                                            fcCodeForm.setItem_tag  (listRepeat.getField_tag() ); //코스콤표준코드
                                            fcCodeForm.setCode_value((String)r_data); //코스콤표준코드값

                                            //코스콤표준코드->금융사표준코드 값 조회
                                            sValue = fcCodeManager.getFcCdToFcCommCd(fcCodeForm);

                                            jObj.remove(r_field_cd); //금융사표준코드값으로 변경
                                            jObj.put(r_field_db_nm, sValue); //금융사표준코드값으로 변경
                                            r_data            = json.get(r_field_db_nm);

                                            logger.info(r_field_nm+"("+r_field_tag+") 코스콤표준전문코드값 : "+r_data);
                                        }
                                    }else if( StringUtil.isNotEmpty(listRepeat.getField_type()) && T.equals(r_field_type)){
                                        jObj.put(r_field_db_nm, r_data); //금융사표준코드값으로 변경
                                    }

                                }
                                jArrData.set(i,  jObj);
                            }
                        }
                        //====================================================================================
                    }
                }
            }
        }
        logger.info("result : "+json);
        return json;
    }

    /**
     * Name   : procJsonEdoc
     * Desc   : JSON 전문 처리
     * input  : TxFcTransmitVO, String
     * output : TxFcReceiveVO
     * Date   : 2017.08.18
     */
    private TxFcReceiveVO procJsonEdoc(TxFcTransmitVO fcMsgSendVO, String strUrl) throws FinsetException, IOException {
        TxFcReceiveVO txFcRecvVO = null;
        ReturnClass returnClass = null;

        //금융사 요청 전문 생성
        String sSendData = createJsonData( fcMsgSendVO);

        String targetUrl = strUrl;
        logger.info("sendData length : "+sSendData.length());
        logger.info("sendData : "+sSendData);

        if(StringUtil.isNotEmpty(targetUrl)) {
            URLConnection url = new URLConnection();
            try {
                returnClass = url.sendReqPOST(targetUrl, sSendData);
                if(!returnClass.isSuccess()){
                    return null;
                }else{
                    logger.debug("Recv Frame Data Success");
                    logger.debug(" returnClassData : "+returnClass.getDes_message());
                }
            } catch (IOException e) {
                LogUtil.error(logger,e);
            }
        }

        //6. FEP에서 전달 받은 데이터
        JSONObject jsonObject = null;
        if( returnClass != null ) {
            jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message()));
        }
        if( jsonObject != null ){
            txFcRecvVO = setRecvJsonEdocMapping(JSONObject.fromObject(jsonObject.toString()), fcMsgSendVO);
            logger.info("recv success txFcRecvVO : "+txFcRecvVO.toString());
        }

        return txFcRecvVO;
    }

    /**
     * Name   : createJsonData
     * Desc   : 전문 생성(JsonData)
     * input  : FinanceForm
     * output : byte[]
     * Date   : 2017.05.29
     */
    private String createJsonData(TxFcTransmitVO fcMsgSendVO) throws FinsetException {
        // TODO Auto-generated method stub

        logger.info("JsonData Create Function Call");
        //inputData를 JsonData 전환

        JSONObject json = null;

        //해당전문 타입에 따른 전문 컬럼들 조회
        FcEdocVO fcEdocVO =null; //전문컬럼 정보
        String result = "";
        if (fcMsgSendVO !=null) {
            fcEdocVO = getListMsgInfo(fcMsgSendVO.getHd_cd_partner(), fcMsgSendVO.getNo_edoc(), " ", "TX");
            logger.info("namik Confirm -------------------------------------"+fcEdocVO.toString());

            //TxFcTransmitVO 객체 -> JSON 객체로 변환
            json = convertClassToJson(fcMsgSendVO);
            logger.info("namik JSONObject : "+json);

            //금융사표준코드값으로 설정된 정보를 각 금융사코드로 변환 작업 수행
            json = setFcCommCodeToFcCode(json, fcEdocVO.getFcEdocForm()); //금융사 공통 코드 -> 금융사 코드로 변경
            if (json != null) {
                result = json.toString();
            }
        }


        return result;
    }

    /**
     * 응답전문 분석 JSON
     * @param fcEdocForm
     * @param jsonObj
     * @param cd_fc
     * @param no_edoc
     * @param code_value
     * @param type_txrx
     * @return
     */
    private JSONObject analysisJsonEdocResponse(List<FcEdocForm> fcEdocForm, JSONObject jsonObj
            , String cd_fc, String no_edoc, String code_value, String type_txrx) throws FinsetException {
        logger.debug("analysisJsonEdocResponse!!!!");
        JSONArray  jArray   = new JSONArray ();
        JSONObject jLoanObj = new JSONObject();

        int nItemCnt = 0;

        String field_cd   = null;//전문필드코드
        String r_field_cd = null;//전문필드코드
        Object data       = null;
        Object r_data     = null;

        FcEdocVO fcMsgRepeatVO = null; //전문컬럼 정보

        List<FcEdocForm> fcMsgRepeatForm = null;
        // 반복부인 부분을 리스트로 변경
        for(FcEdocForm list : fcEdocForm){
            field_cd = list.getField_cd();
            field_cd = field_cd.toLowerCase();
            data = jsonObj.get(field_cd);
            if( Y.equals(list.getRepeat_yn()) ){
                if(StringUtil.isNotEmpty((String)data)){
                    nItemCnt = Integer.parseInt((String)data);
                }

                fcMsgRepeatVO = getListEdocRepeatInfo(cd_fc, no_edoc, type_txrx, parent_code_group, field_cd);

                logger.debug("MsgRepeatInfo -------------------------------------"+fcMsgRepeatVO);

                for(int i = 0; i < nItemCnt; i++){

                    fcMsgRepeatForm = fcMsgRepeatVO.getFcEdocForm();
                    for( FcEdocForm listRepeat : fcMsgRepeatForm ) {
                        r_field_cd = listRepeat.getField_cd();
                        r_field_cd = r_field_cd.toLowerCase();
                        r_data = jsonObj.get(r_field_cd);
                        if(r_data == null) {
                            String message = "There is no value of "+r_field_cd+"(r_field_cd).";
                            throw new FinsetException(message);
                        }
                        jLoanObj.put(r_field_cd, r_data);
                    }
                    jArray.add(jLoanObj);
                }

                logger.debug("RepeatData jArray : "+jArray);
                jsonObj.put( "loan_info", jArray );
            }
        }


        logger.debug("jsonObj return");
        return jsonObj;
    }
    //JSON-------------------------------------------------------------------------------------
    /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 왼쪽에 붙혀서 보내준다.
     * ex) lpad("abc", 5, '^') ==> "^^abc"
     *     lpad("abcdefghi", 5, '^') ==> "abcde"
     *     lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param pCPadder
     */
    private String lpad(String strSource, int iLength, String pCPadder){
        StringBuffer sbBuffer = null;
        String cPadder = pCPadder;
        if(cPadder == null){
            cPadder = "0";
        }

        if (strSource != null && !StringUtil.isEmpty(strSource)){
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength){
                return strSource.substring(0, iLength);
            }else if (iByteSize == iLength){
                return strSource;
            }else{
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer();
                for (int j = 0; j < iPadLength; j++){
                    sbBuffer.append(cPadder);
                }
                sbBuffer.append(strSource);
                return sbBuffer.toString();
            }
        }
        sbBuffer = new StringBuffer();
        for (int j = 0; j < iLength; j++){
            sbBuffer.append(cPadder);
        }
        logger.info("lpad : ["+sbBuffer.toString()+"]");
        return sbBuffer.toString();
    }
    /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 오른쪽에 붙혀서 보내준다.
     * ex) lpad("abc", 5, '^') ==> "abc^^"
     *     lpad("abcdefghi", 5, '^') ==> "abcde"
     *     lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param pCPadder
     */
    private String rpad(String strSource, int iLength, String pCPadder){
        StringBuffer sbBuffer = null;
        String cPadder = pCPadder;
        if(cPadder == null){
            cPadder = " ";
        }

        if (strSource !=null && !StringUtil.isEmpty(strSource)){
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength){
                return strSource.substring(0, iLength);
            }else if (iByteSize == iLength){
                return strSource;
            }else{
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer(strSource);
                for (int j = 0; j < iPadLength; j++){
                    sbBuffer.append(cPadder);
                }
                return sbBuffer.toString();
            }
        }
        sbBuffer = new StringBuffer();
        for (int j = 0; j < iLength; j++){
            sbBuffer.append(cPadder);
        }
        logger.info("rpad : ["+sbBuffer.toString()+"]");

        return sbBuffer.toString();
    }
    /**
     *  byte size를 가져온다.
     *
     * @param str String target
     * @return int bytelength
     */
    private int getByteSize(String str){
        if (str == null || str.length() == 0)
            return 0;

        byte[] byteArray = null;
        try{
            byteArray = str.getBytes("UTF-8");
        }catch (UnsupportedEncodingException ex){}

        if (byteArray == null) return 0;
        return byteArray.length;
    }

    /**
     * 문자열 좌측의 공백을 제거하는 메소드
     * @param  str 대상 문자열
     * @return trimed string with white space removed from the front.
     **/
    private static String ltrim(String str){
        int len = str.length();
        int idx = 0;
        while ((idx < len) && (str.charAt(idx) <= '0'))
        {
            idx++;
        }
        return str.substring(idx, len);
    }
    /**
     * 문자열 우측의 공백을 제거하는 메소드
     * @param  str 대상 문자열
     * @return trimed string with white space removed from the end.
     **/
    private static String rtrim(String str){
        int len = str.length();
        while ((0 < len) && (str.charAt(len-1) <= ' '))
        {
            len--;
        }
        return str.substring(0, len);
    }

    /**
     * 페퍼저축은행의 직업최종코드 및 Company Seg 구하기
     * @param  fcWorkPositionForm
     * @return FcWorkPositionVO
     **/
    private FcWorkPositionVO getFcWorkPosition(FcWorkPositionForm fcWorkPositionForm){
        return financeMapper.getFcWorkPosition(fcWorkPositionForm);
    }

    @Override
    public void parseRecvMsg(String no_bunch, byte[] bArrRecvData) throws FinsetException, FinsetMessageException{
        List<GoodsVO> listFcGoods = new ArrayList<GoodsVO>();

        //0010388 : 유진 , 760464
        //0010471 : 페퍼 , p1


        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setCd_fc("0010388");
        goodsVO.setCd_goods("760464");
        listFcGoods.add(goodsVO);
//

//      GoodsVO goodsVO = new GoodsVO();
//      goodsVO.setCd_fc("0010471");
//      goodsVO.setCd_goods("PIL");
//      listFcGoods.add(goodsVO);


        TxFcTransmitVO txFcTransmitVO = loanManager.getTxFcTransmitInfoForMsg(no_bunch);

        if(txFcTransmitVO == null)
        {
            LogUtil.error(logger,"[ERROR] can't find no_bunch: "+no_bunch);
            return;
        }

        txFcTransmitVO.setListGoods(listFcGoods);

        TxFcReceiveVO txFcRecvVO = setRecvByteMsgMapping(bArrRecvData, txFcTransmitVO);

        LogUtil.infoLn(logger,txFcRecvVO);

    }

}
