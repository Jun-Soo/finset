package com.koscom.credit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FcmUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SkipLoginCheck;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/credit")
@PropertySource("classpath:prop/webservice.properties")
public class CreditController {

    @Resource
    private Environment environment;
    
    @Autowired
    PersonManager personManager;
    
    @Autowired
    CreditManager creditManager;
    
    @Autowired
    ScrapManager scrapManager;
    
    @Autowired
    CodeManager codeManager;
    
    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
        
    /**
     * 신용관리 메인
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/CreditInfoMain.json")
    public String frameCreditInfoMain(
    		HttpServletRequest request,
    		HttpSession session, 
    		Model model) throws UnsupportedEncodingException, FinsetException, IOException {
        
        String      no_person   = (String)session.getAttribute("no_person");
        
        model.addAttribute("noPerson", no_person);
        model.addAttribute("baseInfo", creditManager.getCreditMainBaseInfo(no_person));
        model.addAttribute("cntInfo", creditManager.getCreditMainCntInfo(no_person));
        
        //나의신용정보 변동(최근1개월) 건수
        List<CreditInfo> inquiryList = creditManager.getCreditDetailGradeInquiryList(no_person);
        
        CreditInfo creditInfoParam = new CreditInfo();
        creditInfoParam.setNoPerson(no_person);
        creditInfoParam.setCdChangeInfo("01");
        List<CreditInfo> loanCardList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);
        creditInfoParam.setCdChangeInfo("02");
        List<CreditInfo> overdueList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);
        
        String inquiryCnt = "";
        String loanCardCnt = "";
        String overdueCnt = "";
        if(inquiryList.size() > 0 && inquiryList != null) {
        	inquiryCnt = inquiryList.get(0).getMm_cnt();
        }
        if(loanCardList.size() > 0 && loanCardList != null) {
        	loanCardCnt = loanCardList.get(0).getMm_cnt();
        }
        if(overdueList.size() > 0 && overdueList != null) {
        	overdueCnt = overdueList.get(0).getMm_cnt();
        }
        
        model.addAttribute("inquiryCnt", inquiryCnt);
        model.addAttribute("loanCardCnt", loanCardCnt);
        model.addAttribute("overdueCnt", overdueCnt);
        
        return "jsonView";
    }
    
    /**
     * 본인인증 인증번호 요청
     * @param request
     * @param response
     * @param session
     * @param model
     * @param personVO
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/kcmRequestCertNo.json")
    public String kcmRequestCertNo(
    		HttpServletRequest request, 
    		HttpSession session, 
    		PersonVO personVO,
    		Model model) throws Exception {

        if (personVO != null) {
        	
            logger.info("본인인증 인증번호 요청 이름 : " + personVO.toString());
            logger.info("본인인증 인증번호 요청 이름 : " + personVO.getNm_person());

            if(personVO.getSex().equals("2")){
                personVO.setSex("0");
            }

            // 요청파라미터
            String name =personVO.getNm_person();                       // 성명
            String birthday = personVO.getBirthday();                               // 생년월일
            String sex = personVO.getSex();                                         // 성별
            String nation = personVO.getNation();                                   // 내외국인구분
            String telComCd = personVO.getTelComCd();                               // 통신사코드
            String mbphnNo = personVO.getHp();                                      // 휴대폰번호
            String rqstCausCd = "00";                                               // 인증요청사유코드 (00:회원가입, 01:성인인증, 02:회원정보수정, 03:비밀번호찾기, 04:상품구매, 99:기타)
            String smsReSndYn = StringUtil.isEmpty(personVO.getSmsReSndYn()) ? "N" : personVO.getSmsReSndYn();

            // 거래고유번호. 동일문자열을 두번 사용할 수 없음.
            String svcTxSeqno;

            if(personVO != null && "Y".equals(personVO.getSmsReSndYn())) {
                svcTxSeqno = personVO.getSvcTxSeqno();
            } else {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                svcTxSeqno = df.format(cal.getTime());
            }
            
            logger.info("유효성검증 start");
            boolean isValid = true;
            // 유효성검증
            if (name != null && !name.matches("^[가-힝a-zA-Z ]*")) {
                logger.info("성명에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "성명에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            } // EUC-KR인 경우
            if (birthday != null && !birthday.matches("^[0-9x]*")) {
                logger.info("생년월일에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "생년월일에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;

            }
            if (sex != null && !sex.matches("^[01x]")) {
                logger.info("성별에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "성별에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (nation != null && !nation.matches("^[12x]")) {
                logger.info("내외국인구분에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "내외국인구분에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (telComCd!= null && !telComCd.matches("^[0-9x]*")) {
                logger.info("통신사코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "통신사코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (mbphnNo != null && !mbphnNo.matches("^[0-9]*")) {
                logger.info("휴대폰번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "휴대폰번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (rqstCausCd != null && !rqstCausCd.matches("^[0-9x]*")) {
                logger.info("인증요청사유코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "인증요청사유코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (smsReSndYn != null && !smsReSndYn.matches("^[YN]")) {
                logger.info("재전송여부에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "재전송여부에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (svcTxSeqno !=null && !svcTxSeqno.matches("^[0-9a-zA-Z]*")) {
                logger.info("거래고유번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "거래고유번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            
            if(isValid == true) {
            	
            	logger.info("본인인증 start");
                // ########################################################################
                // # KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리)
                // ########################################################################
                String memId = "P18760000000";                                      // 회원사코드(아이디)

                // ########################################################################
                // # 회원사 모듈설치서버 IP 및 회원사 도메인 설정
                // ########################################################################
                String serverIp = "x";                  // 모듈이 설치된 서버IP (서버IP검증을 무시하려면 'x'로 설정)
                String siteUrl = environment.getProperty("okName.siteUrl"); // 회원사 사이트 URL
                LogUtil.debugLn(logger,"본인인증 siteUrl="+siteUrl);
                String siteDomain = "finset";       // 회원사 도메인명, SMS인증번호문자에 표시됨 **

                // 예비
                String rsv1= "0";
                String rsv2= "0";

                String rqstMsrCd= "10";                             // 요청수단코드 (10:핸드폰)

                // ########################################################################
                // # 운영전환시 변경 필요
                // ########################################################################
                String endPointUrl  = environment.getProperty("okName.endPointUrl");
                LogUtil.debugLn(logger,"본인인증 endPointUrl="+endPointUrl);

                // ########################################################################
                // # 로그 경로 지정 및 권한 부여 (절대경로)
                // ########################################################################
                String logPath = "/okname/log";     // 로그경로. 로그파일을 만들지 않더라도 경로는 지정하도록 한다.

                // ########################################################################
                // # 옵션값에 'L'을 추가하는 경우에만 로그가 생성됨.
                // # 시스템(환경변수 LANG설정)이 UTF-8인 경우 'U'옵션 추가 ex)$option='JLU'
                // ########################################################################
                String options = "JLU";     // L:파일로그생성


                String[] cmd = new String[19];
                cmd[0]=svcTxSeqno;      // 거래일련번호
                cmd[1]=name;            // 성명
                cmd[2]=birthday;        // 생년월일
                cmd[3]=sex;             // 성별
                cmd[4]=nation;          // 내외국인구분
                cmd[5]=telComCd;        // 통신사코드
                cmd[6]=mbphnNo;         // 휴대폰번호
                cmd[7]=smsReSndYn;      // SMS재전송여부
                cmd[8]=rsv1;            // 예약
                cmd[9]=rsv2;            // 예약
                cmd[10]=rqstMsrCd;      // 요청수단코드
                cmd[11]=rqstCausCd;     // 요청사유코드
                cmd[12]=memId;          // 회원사코드
                cmd[13]=serverIp;       // 회원사 서버 IP
                cmd[14]=siteUrl;        // 회원사 사이트 URL
                cmd[15]=siteDomain;     // 회원사 사이트 도메인
                cmd[16]=endPointUrl;    // KCB 서비스 주소
                cmd[17]=logPath;        // 로그경로
                cmd[18]=options;        // 옵션

                /**************************************************************************
                okname 실행
                **************************************************************************/
                List result = new ArrayList();  // 인증결과
                int ret = -999;         // 프로세스 리턴값
                kcb.jni.Okname okname = null;
                
                String site = (environment != null) ? environment.getProperty("service.profile") : "";
                if(!"LOCAL".equals(site)) {
                    okname = new kcb.jni.Okname();
                    ret = okname.exec(cmd, result);
                } else {
                    ret = 0;
                    svcTxSeqno = "15031654";
                }
                
                logger.info(">>>> 인증 결과 코드  ret : " + ret);
                if (ret == 0) {//성공일 경우 변수를 결과에서 얻음
                    model.addAttribute("message", "인증 번호가 전송 되었습니다.");
                    model.addAttribute("result", Constant.SUCCESS);
                    model.addAttribute("svcTxSeqno", svcTxSeqno);
                } else {
                    model.addAttribute("message", "입력정보가 잘못되었습니다.");
                    model.addAttribute("result", Constant.FAILED);
                }
            }
        }

        return "jsonView";
    }
    
    @SkipLoginCheck
    @RequestMapping("/kcmCertify.json")
    public String kcmCertify(
    		HttpServletRequest request, 
    		HttpSession session, 
    		PersonVO personVO,
    		Model model) throws Exception{
        
        
        logger.info("본인인증 인증번호 확인 요청: " + personVO.toString());
        
        // 요청파라미터
        String svcTxSeqno = personVO.getSvcTxSeqno();   // 거래고유번호
        String mbphnNo = personVO.getHp();              // 휴대폰번호
        String smsCertNo = personVO.getSmsCertNo();     // SMS인증번호
        boolean valid = true;
        // 파라미터에 대한 유효성 검증
        if (svcTxSeqno != null && !svcTxSeqno.matches("^[0-9a-zA-Z]+$")) {
            logger.info("거래고유번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "거래고유번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if (mbphnNo != null && !mbphnNo.matches("^[0-9]+$")) {
            logger.info("휴대폰번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "휴대폰번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if (smsCertNo != null && !smsCertNo.matches("^[0-9]+$")) {
            logger.info("SMS인증번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "SMS인증번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if(valid == true) {
            // ########################################################################
            // # KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리)
            // ########################################################################
            String memId = "P18760000000";                                      // 회원사코드(아이디)

            // ########################################################################
            // # 운영전환시 확인 필요
            // ########################################################################
            String endPointUrl  = environment.getProperty("okName.endPointUrl");

            // ########################################################################
            // # 회원사 모듈설치서버 IP 설정
            // ########################################################################
            String serverIp = "x";                  // 모듈이 설치된 서버IP (서버IP검증을 무시하려면 'x'로 설정)

            // ########################################################################
            // # 로그 경로 지정 및 권한 부여 (절대경로)
            // ########################################################################
            String logPath = "/okname/log";     // 로그경로. 로그파일을 만들지 않더라도 경로는 지정하도록 한다.

            // ########################################################################
            // # 옵션값에 'L'을 추가하는 경우에만 로그가 생성됨.
            // # 시스템(환경변수 LANG설정)이 UTF-8인 경우 'U'옵션 추가 ex)$option='MLU'
            // ########################################################################
            String options = "MLU";     // L:파일로그생성

            String[] cmd = new String[8];
            cmd[0]=svcTxSeqno;
            cmd[1]=mbphnNo;
            cmd[2]=smsCertNo;
            cmd[3]=memId;
            cmd[4]=serverIp;
            cmd[5]=endPointUrl;
            cmd[6]=logPath;
            cmd[7]=options;

            /**************************************************************************
            okname 실행
            **************************************************************************/
            List result = new ArrayList();  // 인증결과
            int ret = -999;         // 프로세스 리턴값
            kcb.jni.Okname okname = null;
            String site = (environment != null)?environment.getProperty("service.profile"):"";
            LogUtil.debugLn(logger,"site="+site);
            if(!"LOCAL".equals(site)) {
                okname = new kcb.jni.Okname();
                ret = okname.exec(cmd, result);
            } else {
                ret = 0;
                result.add("1");
                result.add("1");
                result.add("kcb_cp");
                result.add("1");
                result.add("kcb_di");
                result.add("kcb_ci");
            }
            logger.info(">>>> 본인인증 인증번호 확인  ret : " + ret);
            if (ret == 0) {//성공일 경우 변수를 결과에서 얻음
                model.addAttribute("result", Constant.SUCCESS);
                int x = 0;
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                for (Object object : result) {
                    logger.debug("" + x++ + ":" +  object.toString());
                }
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                model.addAttribute("kcb_di", result.get(4));
                model.addAttribute("kcb_ci", result.get(5));
                model.addAttribute("kcb_cp", result.get(2));
                
                session.setAttribute("cert_result_value", Constant.SUCCESS);
            } else {
                model.addAttribute("message", "인증번호가 잘못되었습니다.");
                model.addAttribute("result", Constant.FAILED);
            }
        }
        return "jsonView";
    }
    
    /**
     * 서비스 이용약관
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms1.crz")
    public String frameAcceptTerms1() {
        return "/base/sub/frameAcceptTerms1";
    }
    /**
     * 개인정보 처리방침
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms2.crz")
    public String frameAcceptTerms2() {
        return "/base/sub/frameAcceptTerms2";
    }
    /**
     * KCB 올크래딧 이용약관
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms3.crz")
    public String frameAcceptTerms3() {
        return "/base/sub/frameAcceptTerms3";
    }
    /**
     * 개인정보 수집 · 이용 동의
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms4.crz")
    public String frameAcceptTerms4() {
        return "/base/sub/frameAcceptTerms4";
    }
    /**
     * 개인정보 제3자 제공 동의
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms5.crz")
    public String frameAcceptTerms5() {
        return "/base/sub/frameAcceptTerms5";
    }
    /**
     * 본인확인서비스 이용약관
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms6.crz")
    public String frameAcceptTerms6() {
        return "/base/sub/frameAcceptTerms6";
    }
    /**
     * 개인정보 수집 · 이용/취급위탁 동의
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms7.crz")
    public String frameAcceptTerms7() {
        return "/base/sub/frameAcceptTerms7";
    }
    /**
     * 고유식별정보처리 동의 
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms8.crz")
    public String frameAcceptTerms8() {
        return "/base/sub/frameAcceptTerms8";
    }
    /**
     * 통신사 본인확인 이용약관
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms9.crz")
    public String frameAcceptTerms9() {
        return "/base/sub/frameAcceptTerms9";
    }
    /**
     * 마케팅 정보 수신 동의
     * @param model
     * @param request
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/frameAcceptTerms10.crz")
    public String frameAcceptTerms10() {
        return "/base/sub/frameAcceptTerms10";
    }
    
}


class MapIntegerComparator implements Comparator<Map<String, String>> {
	 
    private final String key;
    
    public MapIntegerComparator(String key) {
        this.key = key;
    }
    
    @Override
    public int compare(Map<String, String> first, Map<String, String> second) {
    	int firstValue = Integer.valueOf(StringUtil.NVL(first.get(key), "0"));
        int secondValue = Integer.valueOf(StringUtil.NVL(second.get(key), "0"));
        
        // 내림차순 정렬
        if (firstValue > secondValue) {
            return -1;
        } else if (firstValue < secondValue) {
            return 1;
        } else {
            return 0;
        }

    }
}

class MapStringComparator implements Comparator<Map<String, String>> {
	 
    private final String key;
    
    public MapStringComparator(String key) {
        this.key = key;
    }
    
    @Override
    public int compare(Map<String, String> first, Map<String, String> second) {
        int result = second.get(key).compareTo(first.get(key));
        return result;
    }
}