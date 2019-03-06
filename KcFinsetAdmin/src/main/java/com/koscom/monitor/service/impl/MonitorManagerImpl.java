package com.koscom.monitor.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.koscom.util.*;

import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.koscom.monitor.dao.MonitorMapper;
import com.koscom.monitor.model.Ad01HeaderInfo;
import com.koscom.monitor.model.Ad01SegmentInfo;
import com.koscom.monitor.model.Ad01TrailerInfo;
import com.koscom.monitor.service.MonitorManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.service.PushEachManager;

@PropertySource("classpath:prop/monitor.properties")
@Service("monitorManager")
@Transactional
public class MonitorManagerImpl implements MonitorManager {
    
    private static final Logger logger = LoggerFactory.getLogger(MonitorManagerImpl.class);
    @Autowired
    PersonManager personManager;
    @Autowired
    private PushEachManager pushEachManager;
    @Resource
    private Environment environment;
    @Autowired
    MonitorMapper monitorMapper;
    public static String[] title={"신용거래정보","채무 불이행 정보","채무 불이행 정보","신용 조회 정보","대출 정보","기타 신용정보","기타 신용정보","기타 신용정보","기타 신용정보"};
    public static String[] url  ={
             "/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab1" /*신용관리->세부정보->신용조회 정보   */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
            ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
    };
    /**
     * 오전 10시에 1회 처리
     * @throws Exception
     */
    @Override
    public void autoAD01(String pType,String pId) {
        if(monitorMapper == null) {
            monitorMapper =  (MonitorMapper)SpringApplicationContext.getBean("monitorManager");
        }
        if(monitorMapper == null){
            LogUtil.infoLn(logger,"monitorMapper IS NULL.");
        }
        else {
            String type = (pType == null)?"1":pType;
            type = ("1".equals(type) || "2".equals(type))? type:"1";
            /**
             * 파일경로
             */
            String filePath = null;
            /**
             * 파일 목록
             */
            String[] fileList = null;

            /**
             * header
             */
            String recordType=null; //Record 구분
            String seqNo     =null; //일련번호
            String cpCode    =null; //회원사코드
            String fileName  =null; //FILE 이름
            String stdDate   =null; //기준일자
            String version   =null; //포맷버전정보
            String totalCnt  =null; //총건수
            String filler    =null; //FILLER
            /**
             * BODY
             */
    //      String recordType           = null;
    //      String seqNo                = null;
    //      String cpCode               = null;
            String ssn                  = null;
            String nm_person            = null;
            String no_person            = null;
    //      String filler               = null;
            String segmentId            = null;
            String svcCode              = null;
            String dt_info_change       = null;
            String hp                   = null;
            String email                = null;
            String yn_credit_info       = null;
            String yn_kfb_default       = null;
            String yn_kci_default       = null;
            String yn_ref_info          = null;
            String yn_loan_info         = null;
            String yn_guarantee_info    = null;
            String yn_fnco_disorder_info= null;
            String yn_public_info       = null;
            String kcbdi                = null;
            String fillerSeg            = null;
            /**
             * TAIL
             */
    //      String recordType=null; //Record 구분
    //      String seqNo     =null; //일련번호
    //      String cpCode    =null; //회원사코드
    //      String fileName  =null; //FILE 이름
    //      String stdDate   =null; //기준일자
    //      String totalCnt  =null; //총건수
            String madVeri   =null; //MAD검증 값
    //      String filler    =null; //FILLER

            int[] arLenHeader = {1    //Record 구분
                    ,10   //일련번호
                    ,8    //회원사코드
                    ,20   //FILE 이름
                    ,8    //기준일자
                    ,2    //포맷버전정보
                    ,10   //총건수
                    ,271  //FILLER
                    ,1  };

            int[] arLenSegment = {1   //1   Record 구분 recordType  AN  1   1
                    ,10  //2   일련번호    seqNo   N   10  11
                    ,8   //3   회원사코드  cpCode  AN  8   19
                    ,13  //4   주민등록번호    ssn N   13  32
                    ,50  //5   성명    nm_person   AN  50  82
                    ,20  //6   관리번호    no_person   AN  20  102
                    ,28  //7   FILLER  filler  AN  28  130
                    ,3   //1   Segment Identification      AN  3   133
                    ,3   //2   서비스코드      N   3   136
                    ,8   //3   정보변동일자        AN  8   144
                    ,12  //4   휴대폰      AN  12  156
                    ,50  //5   이메일      AN  50  206
                    ,1   //6   신용거래정보 변동여부       AN  1   207
                    ,1   //7   은행연합회 채무불이행 변동여부      AN  1   208
                    ,1   //8   신용정보사 채무불이행 변동여부      AN  1   209
                    ,1   //9   조회처정보 변동여부     AN  1   210
                    ,1   //10  대출정보 변동여부       AN  1   211
                    ,1   //11  보증정보 변동여부       AN  1   212
                    ,1   //12  금융질서문란정보 변동여부       AN  1   213
                    ,1   //13  공공정보 변동여부       AN  1   214
                    ,64  //14  DI      AN  64  278
                    ,52  //15  FILLER      AN  52  330
                    ,1
            };
            int[] arLenTail    = {1    //1   Record 구분 recordType  AN  1   1
                    ,10   //2   일련번호    seqNo   N   10  11
                    ,8    //3   회원사코드  cpCode  AN  8   19
                    ,20   //4   주민등록번호    ssn N   13  32
                    ,8    //5   성명    nm_person   AN  50  82
                    ,10   //6   관리번호    no_person   AN  20  102
                    ,10   //7   FILLER  filler  AN  28  130
                    ,263  //1   Segment Identification      AN  3   133
            };
            String[] arrDataHeader  = new String[arLenHeader .length];
            String[] arrDataSegment = new String[arLenSegment.length];
            String[] arrDataTail    = new String[arLenTail   .length];
            String[] arrYn          = new String[8];
            Ad01SegmentInfo ad01SegmentInfo = null;
            String fcm_token            = null;
            String yn_display = null;// 마이페이지에 보여지는 여부
            String yn_send    = null;// 푸시를 보낼지 여부(사용자 알림셋팅이 Y가 아닌 경우와 'Y' 가 여러건인경우 한건을 제외한 나머지건은 N이 된다.)
            int ynInx = 0;
            int stYnInx = -1;
            int cntYn = 0;
            int cnt2Yn = 0;
            int cnt3Yn = 0;
            String conts = null;
            String push_divcd = null; //푸쉬구분
            /**
             * 파싱할 데이터 파일명
             */
            String todayfileNm = null;
            String strFile = null;
            StringBuffer str = null;
            byte[] bt = null;
            String lang = "euc-kr";
            int bufferSize = 1024;
            int pos = 0;
            int len = 0;
            int inx=0;
            boolean ynTodayFile = false;
            int intTotalCnt = 0;
            PersonVO personVO = null;
            PushEachVO pushEachVO = null;
            Ad01TrailerInfo ad01TrailerInfo = null;

            filePath = environment.getProperty("monitor.filepath");
            if (filePath != null) {
                fileList = getFileList(filePath);
            }
            if(fileList != null && fileList.length > 0) {
    //            todayfileNm = "AD01S004700020180301";
                todayfileNm = "AD01S0047000" + DateUtil.getYesterday("yyyyMMdd");
                LogUtil.infoLn(logger,"파일명 : "+todayfileNm);
                for (String file : fileList) {
                    logger.debug("file="+file);
                    if(todayfileNm.equals(file)){
                        ynTodayFile = true;
                        break;
                    }
                }
                if(ynTodayFile) {
                    strFile = readFile(filePath + todayfileNm, lang, bufferSize);
                    logger.debug("파일의 데이터 :"+strFile);
                    bt = strFile.getBytes();
                    logger.debug("파일의 데이터 길이:"+bt.length);
                    str = new StringBuffer();
                    Ad01HeaderInfo ad01HeaderInfo = new Ad01HeaderInfo();
                    /**
                     * 헤더 파싱
                     */
                    for(int i=0;i<arLenHeader.length;i++) {
                        len = arLenHeader[i];
                        arrDataHeader[i]= StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                        pos += len;
                    }
                    recordType = arrDataHeader[inx++];
                    seqNo      = arrDataHeader[inx++];
                    cpCode     = arrDataHeader[inx++];
                    fileName   = arrDataHeader[inx++];
                    stdDate    = arrDataHeader[inx++];
                    version    = arrDataHeader[inx++];
                    totalCnt   = arrDataHeader[inx++];
                    filler     = arrDataHeader[inx++];
                    ad01HeaderInfo.setRecordType(recordType); //Record 구분
                    ad01HeaderInfo.setSeqNo     (seqNo     ); //일련번호
                    ad01HeaderInfo.setCpCode    (cpCode    ); //회원사코드
                    ad01HeaderInfo.setFileName  (fileName  ); //FILE 이름
                    ad01HeaderInfo.setStdDate   (stdDate   ); //기준일자
                    ad01HeaderInfo.setVersion   (version   ); //포맷버전정보
                    ad01HeaderInfo.setTotalCnt  (totalCnt  ); //총건수
                    ad01HeaderInfo.setFiller    (filler    ); //FILLER
                    logger.debug("i:HEADER_START#######################################################################");
                    logger.debug("recordType="+recordType);
                    logger.debug("seqNo     ="+seqNo     );
                    logger.debug("cpCode    ="+cpCode    );
                    logger.debug("fileName  ="+fileName  );
                    logger.debug("stdDate   ="+stdDate   );
                    logger.debug("version   ="+version   );
                    logger.debug("totalCnt  ="+totalCnt  );
                    logger.debug("filler    ="+filler    );
                    logger.debug("i:HEADER_END#######################################################################");
                    intTotalCnt = Integer.parseInt(totalCnt);
                    logger.debug("intTotalCnt  ="+intTotalCnt  );
    //                List<Ad01SegmentInfo> list = new ArrayList<Ad01SegmentInfo>() ;
                    for (int i = 0; i < intTotalCnt; i++) {
                        for(int j=0;j<arLenSegment.length;j++) {
                            len = arLenSegment[j];
                            arrDataSegment[j]= StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                            pos += len;
                        }
                        inx=0;
                        recordType            = arrDataSegment[inx++];
                        seqNo                 = arrDataSegment[inx++];
                        cpCode                = arrDataSegment[inx++];
                        ssn                   = arrDataSegment[inx++];
                        nm_person             = arrDataSegment[inx++];
                        no_person             = arrDataSegment[inx++];
                        filler                = arrDataSegment[inx++];
                        segmentId             = arrDataSegment[inx++];
                        svcCode               = arrDataSegment[inx++];
                        dt_info_change        = arrDataSegment[inx++];
                        hp                    = arrDataSegment[inx++];
                        email                 = arrDataSegment[inx++];
                        yn_credit_info        = arrDataSegment[inx++];
                        yn_kfb_default        = arrDataSegment[inx++];
                        yn_kci_default        = arrDataSegment[inx++];
                        yn_ref_info           = arrDataSegment[inx++];
                        yn_loan_info          = arrDataSegment[inx++];
                        yn_guarantee_info     = arrDataSegment[inx++];
                        yn_fnco_disorder_info = arrDataSegment[inx++];
                        yn_public_info        = arrDataSegment[inx++];
                        kcbdi                 = arrDataSegment[inx++];
                        fillerSeg             = arrDataSegment[inx++];

                        if(no_person != null && !no_person.equals("")) {

                            pushEachVO = new PushEachVO();
                            personVO = personManager.getPersonInfo(no_person);
                            fcm_token = (personVO != null)? personVO.getFcm_token():null;
                            if(personVO != null && fcm_token != null && !"".equals(fcm_token)) {
                                ad01SegmentInfo = new Ad01SegmentInfo();
                                ad01SegmentInfo.setRecordType           (recordType           );//1   Record 구분 recordType  AN  1   1
                                ad01SegmentInfo.setSeqNo                (seqNo                );//2   일련번호    seqNo   N   10  11
                                ad01SegmentInfo.setCpCode               (cpCode               );//3   회원사코드  cpCode  AN  8   19
                                ad01SegmentInfo.setSsn                  (ssn                  );//4   주민등록번호    ssn N   13  32
                                ad01SegmentInfo.setNm_person            (nm_person            );//5   성명    nm_person   AN  50  82
                                ad01SegmentInfo.setNo_person            (no_person            );//6   관리번호    no_person   AN  20  102
                                ad01SegmentInfo.setFiller               (filler               );//7   FILLER  filler  AN  28  130
                                ad01SegmentInfo.setSegmentId            (segmentId            );//1   Segment Identification      AN  3   133
                                ad01SegmentInfo.setSvcCode              (svcCode              );//2   서비스코드      N   3   136
                                ad01SegmentInfo.setDt_info_change       (dt_info_change       );//3   정보변동일자        AN  8   144
                                ad01SegmentInfo.setHp                   (hp                   );//4   휴대폰      AN  12  156
                                ad01SegmentInfo.setEmail                (email                );//5   이메일      AN  50  206
                                ad01SegmentInfo.setYn_credit_info       (yn_credit_info       );//6   신용거래정보 변동여부       AN  1   207
                                ad01SegmentInfo.setYn_kfb_default       (yn_kfb_default       );//7   은행연합회 채무불이행 변동여부      AN  1   208
                                ad01SegmentInfo.setYn_kci_default       (yn_kci_default       );//8   신용정보사 채무불이행 변동여부      AN  1   209
                                ad01SegmentInfo.setYn_ref_info          (yn_ref_info          );//9   조회처정보 변동여부     AN  1   210
                                ad01SegmentInfo.setYn_loan_info         (yn_loan_info         );//10  대출정보 변동여부       AN  1   211
                                ad01SegmentInfo.setYn_guarantee_info    (yn_guarantee_info    );//11  보증정보 변동여부       AN  1   212
                                ad01SegmentInfo.setYn_fnco_disorder_info(yn_fnco_disorder_info);//12  금융질서문란정보 변동여부       AN  1   213
                                ad01SegmentInfo.setYn_public_info       (yn_public_info       );//13  공공정보 변동여부       AN  1   214
                                ad01SegmentInfo.setKcbdi                (kcbdi                );//14  DI      AN  64  278
                                ad01SegmentInfo.setFillerSeg            (fillerSeg            );//15  FILLER      AN  52  330
                                ad01SegmentInfo.setId_frt               (pId                  );//등록자
                                //pos+=len; len=10;ad01SegmentInfo.setId_frt("");                       //15
                                //pos+=len; len=10;ad01SegmentInfo.setDt_frt("");                       //
                                logger.debug("i:BODYSTART" + i + "#######################################################################");
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("recordType           ="+recordType            +"||"); //1   Record 구분 recordType  AN  1   1
                                logger.debug("seqNo                ="+seqNo                 +"||"); //2   일련번호    seqNo   N   10  11
                                logger.debug("cpCode               ="+cpCode                +"||"); //3   회원사코드  cpCode  AN  8   19
                                logger.debug("ssn                  ="+ssn                   +"||"); //4   주민등록번호    ssn N   13  32
                                logger.debug("nm_person            ="+nm_person             +"||"); //5   성명    nm_person   AN  50  82
                                logger.debug("no_person            ="+no_person             +"||"); //6   관리번호    no_person   AN  20  102
                                logger.debug("filler               ="+filler                +"||"); //7   FILLER  filler  AN  28  130
                                logger.debug("segmentId            ="+segmentId             +"||"); //1   Segment Identification      AN  3   133
                                logger.debug("svcCode              ="+svcCode               +"||"); //2   서비스코드      N   3   136
                                logger.debug("dt_info_change       ="+dt_info_change        +"||"); //3   정보변동일자        AN  8   144
                                logger.debug("hp                   ="+hp                    +"||"); //4   휴대폰      AN  12  156
                                logger.debug("email                ="+email                 +"||"); //5   이메일      AN  50  206
                                logger.debug("yn_credit_info       ="+yn_credit_info        +"||"); //6   신용거래정보 변동여부       AN  1   207
                                logger.debug("yn_kfb_default       ="+yn_kfb_default        +"||"); //7   은행연합회 채무불이행 변동여부      AN  1   208
                                logger.debug("yn_kci_default       ="+yn_kci_default        +"||"); //8   신용정보사 채무불이행 변동여부      AN  1   209
                                logger.debug("yn_ref_info          ="+yn_ref_info           +"||"); //9   조회처정보 변동여부     AN  1   210
                                logger.debug("yn_loan_info         ="+yn_loan_info          +"||"); //10  대출정보 변동여부       AN  1   211
                                logger.debug("yn_guarantee_info    ="+yn_guarantee_info     +"||"); //11  보증정보 변동여부       AN  1   212
                                logger.debug("yn_fnco_disorder_info="+yn_fnco_disorder_info +"||"); //12  금융질서문란정보 변동여부       AN  1   213
                                logger.debug("yn_public_info       ="+yn_public_info        +"||"); //13  공공정보 변동여부       AN  1   214
                                logger.debug("kcbdi                ="+kcbdi                 +"||"); //14  DI      AN  64  278
                                logger.debug("fillerSeg            ="+fillerSeg             +"||"); //15  FILLER      AN  52  330
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("i:" + i + "#######################################################################");
                                logger.debug("i:BODYEND" + i + "#######################################################################");
                                Ad01SegmentInfo ad01SegmentInfo1 = monitorMapper.getPushAD01Info(ad01SegmentInfo);
                                /**
                                 * 기존에 등록된 내역이 있는지에 따른 처리
                                 */
                                if(ad01SegmentInfo1 == null) {
                                    monitorMapper.insertMonitorHist(ad01SegmentInfo);
                                }
                                /**
                                 * 전체다시
                                 */
                                else if(type.equals("1")){
                                    monitorMapper.updateMonitorHist(ad01SegmentInfo);
                                }
                                /**
                                 * 이미 등록된 건은 제외
                                 */
                                else if(type.equals("2")) {
                                    continue;
                                }
    //                            list.add(ad01SegmentInfo);
                                pushEachVO.setNo_person(no_person);
                                pushEachVO.setSendTo(fcm_token);
                                //TODO 실제로 사용할 메세지 등록
                                ynInx = 0;
                                arrYn[ynInx++] = yn_credit_info       ;
                                arrYn[ynInx++] = yn_kfb_default       ;
                                arrYn[ynInx++] = yn_kci_default       ;
                                arrYn[ynInx++] = yn_ref_info          ;
                                arrYn[ynInx++] = yn_loan_info         ;
                                arrYn[ynInx++] = yn_guarantee_info    ;
                                arrYn[ynInx++] = yn_fnco_disorder_info;
                                arrYn[ynInx  ] = yn_public_info       ;
                                stYnInx = -1;
                                cnt2Yn=0;
                                cnt3Yn=0;
                                for(int j=arrYn.length-1;j>-1;j--) {
                                    if("Y".equals(arrYn[j])) {
                                        stYnInx = j;
                                        cntYn++;
                                        switch(j) {
                                            case 0:
                                            case 1:
                                            case 2:
                                            case 3:
                                            case 5:
                                            case 6:
                                            case 7:
                                                cnt2Yn++;
                                                break;
                                            case 4:
                                                cnt3Yn++;
                                        }
                                    }
                                }

                                logger.debug("알림 내용 conts="+conts);
    //                            yn_process = "N";
                                yn_display = "Y";
                                for(int j=0;j<arrYn.length;j++) {
                                    if("Y".equals(arrYn[j])) {
                                        switch(j) {
                                            case 0:
                                            case 1:
                                            case 2:
                                            case 3:
                                            case 5:
                                            case 6:
                                            case 7:
                                                push_divcd = "02";/* 신용 */
                                                break;
                                            case 4:
                                                push_divcd = "03";/* 부채 */
                                        }
                                        /**
                                         * 여러건이면 보내지 않는다.
                                         */
                                        if(cntYn > 1) {
                                            yn_send = "N"; //보내지 않는다.
                                        }
                                        /**
                                         * 한건이면 보낸다.
                                         */
                                        else {
                                            yn_send = "Y"; //보낸다.
                                        }
                                        conts = nm_person +"님의 "+title[j] + "가 변경되었습니다";
                                        pushEachVO.setTitle("핀셋신용정보변경알리미");
                                        pushEachVO.setBody(conts);
                                        pushEachVO.setPush_divcd(push_divcd);
                                        pushEachVO.setLink_addr(url[j]);
                                        pushEachVO.setYn_send(yn_send);
                                        pushEachVO.setYn_display(yn_display);
                                        pushEachVO.setId_frt(pId                  );//등록자
                                        pushEachManager.createPushAD01Info(pushEachVO);
                                    }
                                }
                                /**
                                 * 여러건인경우 발송 메세지가 한건더
                                 */
                                if(cntYn > 1) {
                                    push_divcd = "";
                                    /**
                                     * 신용 부채 모두 변경 되었을 경우
                                     * 푸쉬구분을 23 으로 한다.
                                     */
                                    if(cnt2Yn>0 && cnt3Yn > 0){
                                        push_divcd = "05";
                                    }
                                    /**
                                     * 신용만 변경내역이 있을 경우
                                     */
                                    else if(cnt3Yn == 0 && cnt2Yn > 0) {
                                        push_divcd = "02";
                                    }
                                    /**
                                     * 부채만 변경내역이 있을 경우
                                     */
                                    else if(cnt2Yn == 0 && cnt3Yn > 0) {
                                        push_divcd = "03";
                                    }
                                    /** yn_display : 알림내역에 보여질 여부
                                     *    Y 건수 > 1
                                     *      처음건 : 'N'
                                     *      나머지 : 'Y'
                                     *    Y 건수 = 1  : 'Y'
                                     */
                                    conts = nm_person +"님의 "+title[stYnInx] + "(외 "+(cntYn-1)+"건)가 변경되었습니다";
                                    yn_send    = "Y";
                                    yn_display = "N";// 알림내역에 보여주지 않음.
                                    pushEachVO.setTitle("핀셋신용정보변경알리미");
                                    pushEachVO.setBody(conts);
                                    pushEachVO.setPush_divcd(push_divcd);
                                    pushEachVO.setLink_addr("");
                                    pushEachVO.setYn_send(yn_send);
                                    pushEachVO.setYn_display(yn_display);
                                    pushEachManager.createPushAD01Info(pushEachVO);
                                }
                            }
                        }
                    }
                    for(int i=0;i<arLenTail.length;i++) {
                        ad01TrailerInfo = new Ad01TrailerInfo();
                        len = arLenTail[i];
                        if(bt.length < pos+len) {
                            logger.error("길이초과: bt.length="+bt.length+",pos="+pos+",len="+len+",pos+len="+(pos+len));
                            break;
                        }
                        arrDataTail[i]= StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                        pos += len;
                    }
                    inx=0;
                    recordType = arrDataTail[inx++];
                    seqNo      = arrDataTail[inx++];
                    cpCode     = arrDataTail[inx++];
                    fileName   = arrDataTail[inx++];
                    stdDate    = arrDataTail[inx++];
                    totalCnt   = arrDataTail[inx++];
                    madVeri    = arrDataTail[inx++];
                    filler     = arrDataTail[inx  ];
                    ad01TrailerInfo.setRecordType(recordType);
                    ad01TrailerInfo.setSeqNo     (seqNo     );
                    ad01TrailerInfo.setCpCode    (cpCode    );
                    ad01TrailerInfo.setFileName  (fileName  );
                    ad01TrailerInfo.setStdDate   (stdDate   );
                    ad01TrailerInfo.setTotalCnt  (totalCnt  );
                    ad01TrailerInfo.setMadVeri   (madVeri   );
                    ad01TrailerInfo.setFiller    (filler    );
                    logger.debug("i:TAIL_START#######################################################################");
                    logger.debug("recordType="+recordType);//Record 구분
                    logger.debug("seqNo     ="+seqNo     );//일련번호
                    logger.debug("cpCode    ="+cpCode    );//회원사코드
                    logger.debug("fileName  ="+fileName  );//FILE 이름
                    logger.debug("stdDate   ="+stdDate   );//기준일자
                    logger.debug("totalCnt  ="+totalCnt  );//총건수
                    logger.debug("madVeri   ="+madVeri   );//MAD검증 값
                    logger.debug("filler    ="+filler    );//FILLER
                    logger.debug("i:TAIL_END#######################################################################");

                    pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;
    //      System.out.println("retStr:" + str.toString());
                } else{
                    logger.info("Today's File Not exist !!!");
                }
            } else {
                logger.info("Today's File Not exist !!!");
            }
        }
        System.gc();
    }
    public static String readFile(String filename, String lang, int bufferSize){
        logger.debug("MonitorManagerImpl.readFile="+filename);
        FileInputStream fileInputStream = null;
        StringBuilder sb = new StringBuilder();
        String result = null;
        byte[] data = new byte[bufferSize];
        File file = new File(filename);
        int cnt = 0;
        try {
            fileInputStream = new FileInputStream(file);
            while(true) {
//              System.out.println(cnt +":"+ new String(data, 0, cnt, lang));
//              System.out.println(cnt +":"+ new String(data, 0, cnt, lang));
                if((cnt = fileInputStream.read(data)) == -1){ 
                    break;
                }
                sb.append(new String(data, 0, cnt));
            }   
//          System.out.println(stringBuffer.toString());
            fileInputStream.close(); 
            fileInputStream=null;
        } catch (IOException e) {
            if(fileInputStream != null) try{
                fileInputStream.close(); 
                fileInputStream = null;
            } catch(IOException ee){
                ee.printStackTrace();
            }
        }
        result = sb.toString();
        logger.debug("MonitorManagerImpl.readFile : \n"+result);

        return result;
    }
    public static String[] getFileList(String path) {
        File dirFile = null;
        File []fileList = null;
        String[] result = null;
        if (path != null) {
            dirFile = new File(path);
            fileList = dirFile.listFiles();
            int i = 0;
            if (fileList != null) {
                for(File tempFile : fileList) {
                    if(tempFile.isFile())
                        i++;
                }
                result = new String[i];
                int j = 0;
                for(File tempFile : fileList) {
                    if(tempFile.isFile()) {
                        String tempPath = tempFile.getParent();
                        String tempFileName = tempFile.getName();
                        result[j] = tempFileName;
                        j++;
                    }
                }
            }
        }
        return result;
    }
}