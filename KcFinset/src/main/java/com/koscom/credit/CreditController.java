package com.koscom.credit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.model.Kcb_600420;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.scrap.model.ScrReqHealthVO;
import com.koscom.scrap.model.ScrReqPensionVO;
import com.koscom.scrap.model.ScrRespHealthPaymentdtlVO;
import com.koscom.scrap.model.ScrRespIncomeDtlVO;
import com.koscom.scrap.model.ScrRespPensionPaymentVO;
import com.koscom.scrap.model.ScrRespPensionPaymentdtlVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.NumberUtil;
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
    KcbManager kcbManager;

    @Autowired
    ScrapManager scrapManager;

    @Autowired
    CodeManager codeManager;

    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);

    /** VUE
     * 신용관리 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws UnsupportedEncodingException, FinsetException, IOException
     */
    @RequestMapping("/getCreditMainInfo.json")
    public String getCreditMainInfo(
    		HttpServletRequest request,
    		HttpSession session,
    		Model model) throws UnsupportedEncodingException, FinsetException, IOException {

        String      no_person   = (String)session.getAttribute("no_person");

        model.addAttribute("noPerson", no_person);
        model.addAttribute("currentDate",DateUtil.getCurrentDate(DateUtil.DATE_PATTERN_DOT)); //현재일자
        model.addAttribute("baseInfo", creditManager.getCreditMainBaseInfo(no_person)); //신용등급, 신용점수, 상위%

        model.addAttribute("changeInfo",creditManager.getCreditMainGradeChangeInfo(no_person)); //나의신용정보변동 내역

        //카드이용금액
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        String listCardUseStr = "";
        ArrayList<Map<String, String>> listCardUseGson = new ArrayList<Map<String, String>>();

        int cardSumAmt = 0; //카드 총 이용금액

        if(creditDetailJsonInfoMap != null
        	&& (!("[]".equals(creditDetailJsonInfoMap.get("list_card_use"))) && creditDetailJsonInfoMap.get("list_card_use") != null)) {
	        listCardUseStr = creditDetailJsonInfoMap.get("list_card_use");
	        listCardUseGson = gson.fromJson(listCardUseStr, type);

	        //카드 이용금액 계산
	        for (int i = 0; i < listCardUseGson.size(); i++) {
	        	cardSumAmt += Integer.parseInt(StringUtil.NVL(listCardUseGson.get(i).get("amt_total"), "0"));
		    }
        }
        model.addAttribute("cardSumAmt", cardSumAmt);

        //대출잔액
        CreditInfo debtInfo = creditManager.getCreditDetailDEBTSum(no_person);
        String debtSumAmtRemain = "0";
        debtSumAmtRemain = StringUtil.NVL(debtInfo.getSum_amt_remain(), "0");
        model.addAttribute("debtSumAmtRemain", debtSumAmtRemain);

        //연체원금
        CreditInfo overdueSumAmtInfo = creditManager.getCreditOverdueSumAmt(no_person);
        int overdueSumAmt = 0;
        if(overdueSumAmtInfo != null){
        	overdueSumAmt+= overdueSumAmtInfo.getKoi_sum_amt();
        	overdueSumAmt+= overdueSumAmtInfo.getKosi_sum_amt();
        	overdueSumAmt+= overdueSumAmtInfo.getKodi_sum_amt();
        	overdueSumAmt+= overdueSumAmtInfo.getKopi_sum_amt();
        	overdueSumAmt+= overdueSumAmtInfo.getKfdi_sum_amt();
        }
        model.addAttribute("overdueSumAmt", overdueSumAmt);

        //연대보증원금
        String guaranteeSumAmt = "0";
        if(creditDetailJsonInfoMap != null){
        	guaranteeSumAmt = StringUtil.NVL(creditDetailJsonInfoMap.get("amt_guarantee"), "0");
        }
        model.addAttribute("guaranteeSumAmt", guaranteeSumAmt);

        return "jsonView";
    }










    /**
     * 신용관리 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws UnsupportedEncodingException, FinsetException, IOException
     */
    @RequestMapping("/frameCreditInfoMain.crz")
    public String frameCreditInfoMain(
    		HttpServletRequest request,
    		HttpSession session,
    		Model model) throws UnsupportedEncodingException, FinsetException, IOException {

        String      no_person   = (String)session.getAttribute("no_person");
        String		auto_Scrap	= (String)session.getAttribute("AutoScrap");

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

        //자동스크래핑 은행 내역 조회 및 설정 - 로그인 후 한번만 실행
        if(auto_Scrap.equals("true"))	{
        	String smsStartDate = null;
        	String smsInclude = null;
        	String smsExclude = null;
        	//마지막 문자내역 시간 체크
        	smsStartDate = personManager.getLastPersonSmsDt(no_person);
        	//문자내역이 없을 경우 기본 3달 전으로 셋팅
        	if(smsStartDate == null || smsStartDate.length() == 0)	{
        		String toDay = DateUtil.getCurrentDateTime("yyyyMMdd");
        		smsStartDate = DateUtil.addMonths(toDay, -3);
        		//시분초 추가
        		smsStartDate += "000000";
        	}
        	String site = (environment != null)?environment.getProperty("service.profile"):"";
    		model.addAttribute("site", site);

        	// 초기 접속일 경우에만 SMS내역 및 스크래핑 내역 화면에 전송
        	String      isAutoScrap   = (String)session.getAttribute("AutoScrap");
        	logger.debug("isAutoScrap : " + isAutoScrap);

        	if(isAutoScrap.equals("true") && !site.equals("REAL"))	{
        		logger.debug("=======================================");
	        	model.addAttribute("smsStartDate", smsStartDate);
	        	logger.debug("SMS Start Date : " + smsStartDate);
	        	smsInclude = codeManager.getCodeName("_CONF_SMS", "INCLUDE");
	        	model.addAttribute("smsInclude", smsInclude);
	        	logger.debug("SMS Include : " + smsInclude);
	        	smsExclude = codeManager.getCodeName("_CONF_SMS", "EXCLUDE");
	        	model.addAttribute("smsExclude", smsExclude);
	        	logger.debug("SMS Exclude : " + smsExclude);

	        	String autoScrapInfo = null;
	        	String cd_agency = codeManager.getCodeId("cd_agency","은행");
	        	autoScrapInfo = scrapManager.getAutoScrapInfo(cd_agency, no_person);
	        	logger.debug("Bank autoScrapInfo : " + autoScrapInfo);
	        	if(autoScrapInfo != null && autoScrapInfo.length() > 0)	{
	        		model.addAttribute("autoScrapBankInfo", autoScrapInfo);
	        	}
	        	cd_agency = codeManager.getCodeId("cd_agency","카드");
	        	autoScrapInfo = scrapManager.getAutoScrapInfo(cd_agency, no_person);
	        	logger.debug("Card autoScrapInfo : " + autoScrapInfo);
	        	if(autoScrapInfo != null && autoScrapInfo.length() > 0)	{
	        		model.addAttribute("autoScrapCardInfo", autoScrapInfo);
	        	}
	        	cd_agency = codeManager.getCodeId("cd_agency","국세청");
	        	autoScrapInfo = scrapManager.getAutoScrapInfo(cd_agency, no_person);
	        	logger.debug("NTS autoScrapInfo : " + autoScrapInfo);
	        	if(autoScrapInfo != null && autoScrapInfo.length() > 0)	{
	        		model.addAttribute("autoScrapNTSInfo", autoScrapInfo);
	        	}
	        	logger.debug("=======================================");
        	}

        }
        return "/credit/frameCreditInfoOrgMain";
    }

    /**
     * 신용관리 스마트리포트
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws UnsupportedEncodingException,FinsetException,IOException
     */
    @RequestMapping("/frameCreditSmartReportInfo.crz")
    public String frameCreditSmartReportInfo(HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException,FinsetException,IOException {

        String      no_person   = (String)session.getAttribute("no_person");
        PersonVO    vo          = personManager.getPersonInfo(no_person);

        String rtnPage = "";
        String profile = environment.getProperty("service.profile");

        if(!"LOCAL".equals(profile)) {

        	//1. 600전문 신용정보 URL 조회 (DEV, REAL)
    		KcbCreditInfoVO info = new KcbCreditInfoVO();

    		info.setNmIf("600420");

    		info.setCd_regist("09"); // 01 신규, 09 URL
    		info.setBgn(vo.getBgn()); // 생년월일, 성별
    		info.setNoPerson(no_person); // 회원번호(TEST)
    		info.setNmCust(vo.getNm_person()); // 회원명
    		info.setDi(vo.getKcb_di()); // 회원 KCB DI
    		info.setHp(vo.getHp()); // 회원 휴대폰번호

    		info.setNmIfSub("220");
    		info.setReq_menu_code("220"); // 200 신용관리(모바일 제휴 보고서), 210 부채관리(제휴사 가공
    		info.setReq_view_code("s06158737277"); // s07143331300 신용관리, s02173986405 부채관리, s06158737277 신용리포트

    		logger.info("[KCB ]START == \n" + info);

    		ReturnClass kcbCbData = kcbManager.procKcbCb(info);
    		if (kcbCbData != null) {
    			logger.info("[KCB ]Data == \n" + kcbCbData.getReturnObj());

    			if (info.getNmIf() != null && info.getNmIf().equals("600420")) {
    				Object retObject = kcbCbData.getReturnObj();
    				if (retObject != null && retObject instanceof Kcb_600420) {

    					Kcb_600420 kcb_600420 = (Kcb_600420) retObject;
    					model.addAttribute("kcbURI", kcb_600420.getKcbURI());
    					logger.info("[KCB ]kcbURI == \n" + kcb_600420.getKcbURI());
    				}
    			}
    		} else {
    			logger.info("[KCB ]Data == \n" + kcbCbData);
    		}
        }

		rtnPage = "/credit/frameCreditSmartReportInfo";

        return rtnPage;
    }


    /**
     * VUE
     * 신용관리 스마트리포트
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws UnsupportedEncodingException,FinsetException,IOException
     */
    @RequestMapping("/getCreditSmartReportInfo.json")
    public String getCreditSmartReportInfo(HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException,FinsetException,IOException {

        String      no_person   = (String)session.getAttribute("no_person");
        PersonVO    vo          = personManager.getPersonInfo(no_person);

        String profile = environment.getProperty("service.profile");

        if(!"LOCAL".equals(profile)) {

        	//1. 600전문 신용정보 URL 조회 (DEV, REAL)
    		KcbCreditInfoVO info = new KcbCreditInfoVO();

    		info.setNmIf("600420");

    		info.setCd_regist("09"); // 01 신규, 09 URL
    		info.setBgn(vo.getBgn()); // 생년월일, 성별
    		info.setNoPerson(no_person); // 회원번호(TEST)
    		info.setNmCust(vo.getNm_person()); // 회원명
    		info.setDi(vo.getKcb_di()); // 회원 KCB DI
    		info.setHp(vo.getHp()); // 회원 휴대폰번호

    		info.setNmIfSub("220");
    		info.setReq_menu_code("220"); // 200 신용관리(모바일 제휴 보고서), 210 부채관리(제휴사 가공
    		info.setReq_view_code("s06158737277"); // s07143331300 신용관리, s02173986405 부채관리, s06158737277 신용리포트

    		logger.info("[KCB ]START == \n" + info);

    		ReturnClass kcbCbData = kcbManager.procKcbCb(info);
    		if (kcbCbData != null) {
    			logger.info("[KCB ]Data == \n" + kcbCbData.getReturnObj());

    			if (info.getNmIf() != null && info.getNmIf().equals("600420")) {
    				Object retObject = kcbCbData.getReturnObj();
    				if (retObject != null && retObject instanceof Kcb_600420) {

    					Kcb_600420 kcb_600420 = (Kcb_600420) retObject;
    					model.addAttribute("kcbURI", kcb_600420.getKcbURI());
    					logger.info("[KCB ]kcbURI == \n" + kcb_600420.getKcbURI());
    				}
    			}
    		} else {
    			logger.info("[KCB ]Data == \n" + kcbCbData);
    		}
        }

        return "jsonView";
    }



    /**
     * VUE
     * 신용관리 변동이력
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/getCreditInfoDetail.json")
    public String getCreditInfoDetail(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
    	String      no_person   = (String)session.getAttribute("no_person");

        List<CreditInfo> inquiryList = creditManager.getCreditDetailGradeInquiryList(no_person);

        CreditInfo creditInfoParam = new CreditInfo();
        creditInfoParam.setNoPerson(no_person);
        creditInfoParam.setCdChangeInfo("01");
        List<CreditInfo> loanCardList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);
        creditInfoParam.setCdChangeInfo("02");
        List<CreditInfo> overdueList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);

        //신용조회정보
        String inquiryMmCnt = "0";
        String inquiryYearCnt = "0";
        if(inquiryList.size() > 0 && inquiryList != null) {
        	inquiryMmCnt = inquiryList.get(0).getMm_cnt();
        	inquiryYearCnt = inquiryList.get(0).getYear_cnt();
        }
        model.addAttribute("inquiryMmCnt",inquiryMmCnt); //최근1개월건수
        model.addAttribute("inquiryYearCnt",inquiryYearCnt); //최근1년건수
        model.addAttribute("inquiryList",inquiryList); //list

        //대출/카드정보
        String loanCardMmCnt = "0";
        String loanCardYearCnt = "0";
        if(loanCardList.size() > 0 && loanCardList != null) {
        	loanCardMmCnt = loanCardList.get(0).getMm_cnt();
        	loanCardYearCnt = loanCardList.get(0).getYear_cnt();
        }
        model.addAttribute("loanCardMmCnt",loanCardMmCnt); //최근1개월건수
        model.addAttribute("loanCardYearCnt",loanCardYearCnt); //최근1년건수
        model.addAttribute("loanCardList",loanCardList); //list

        //연체정보
        String overdueMmCnt = "0";
        String overdueYearCnt = "0";
        if(overdueList.size() > 0 && overdueList != null) {
        	overdueMmCnt = overdueList.get(0).getMm_cnt();
        	overdueYearCnt = overdueList.get(0).getYear_cnt();
        }
        model.addAttribute("overdueMmCnt",overdueMmCnt); //최근1개월건수
        model.addAttribute("overdueYearCnt",overdueYearCnt); //최근1년건수
        model.addAttribute("overdueList",overdueList); //list

    	return "jsonView";
    }



    /**
     * 신용관리 신용등급상세
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/frameCreditInfoDetail.crz")
    public String frameCreditInfoDetail(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
    	String      no_person   = (String)session.getAttribute("no_person");

    	List<CreditInfo> chartList = creditManager.getCreditDetailGradeChartList(no_person);
    	model.addAttribute("chartList",chartList);

        List<CreditInfo> inquiryList = creditManager.getCreditDetailGradeInquiryList(no_person);

        CreditInfo creditInfoParam = new CreditInfo();
        creditInfoParam.setNoPerson(no_person);
        creditInfoParam.setCdChangeInfo("01");
        List<CreditInfo> loanCardList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);
        creditInfoParam.setCdChangeInfo("02");
        List<CreditInfo> overdueList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);

        //신용조회정보
        String inquiryMmCnt = "0";
        String inquiryYearCnt = "0";
        if(inquiryList.size() > 0 && inquiryList != null) {
        	inquiryMmCnt = inquiryList.get(0).getMm_cnt();
        	inquiryYearCnt = inquiryList.get(0).getYear_cnt();
        }
        model.addAttribute("inquiryMmCnt",inquiryMmCnt);
        model.addAttribute("inquiryYearCnt",inquiryYearCnt);
        model.addAttribute("inquiryList",inquiryList);
        //대출/카드정보
        String loanCardMmCnt = "0";
        String loanCardYearCnt = "0";
        if(loanCardList.size() > 0 && loanCardList != null) {
        	loanCardMmCnt = loanCardList.get(0).getMm_cnt();
        	loanCardYearCnt = loanCardList.get(0).getYear_cnt();
        }
        model.addAttribute("loanCardMmCnt",loanCardMmCnt);
        model.addAttribute("loanCardYearCnt",loanCardYearCnt);
        model.addAttribute("loanCardList",loanCardList);
        //연체정보
        String overdueMmCnt = "0";
        String overdueYearCnt = "0";
        if(overdueList.size() > 0 && overdueList != null) {
        	overdueMmCnt = overdueList.get(0).getMm_cnt();
        	overdueYearCnt = overdueList.get(0).getYear_cnt();
        }
        model.addAttribute("overdueMmCnt",overdueMmCnt);
        model.addAttribute("overdueYearCnt",overdueYearCnt);
        model.addAttribute("overdueList",overdueList);

        //메인페이지 탭정보
        String tabNm = "";
        if(request.getParameter("detailTabNm") == null || "".equals(request.getParameter("detailTabNm"))) {
        	tabNm = "tab1";
        }else {
        	tabNm = request.getParameter("detailTabNm");
        }
        model.addAttribute("tabNm",tabNm);

    	return "/credit/frameCreditInfoDetail";
    }



    /**
     * VUE
     * 신용관리 카드현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException, ParseException
     */
    @RequestMapping("/getCreditCardInfo.json")
    public String getCreditCardInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException, ParseException {
        String      no_person   = (String)session.getAttribute("no_person");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        //카드개설내역
        String listCardOpenStr = "";
        ArrayList<Map<String, String>> listCardOpenGson = new ArrayList<Map<String, String>>();
        //카드이용내역
        String listCardUseStr = "";
        ArrayList<Map<String, String>> listCardUseGson = new ArrayList<Map<String, String>>();

        //신용카드parameter
        int creditCnt = 0; //신용 건수
        ArrayList<Map<String, String>> creditList = new ArrayList<Map<String, String>>(); //신용list
        int creditAmtTotal = 0; //신용카드 총 이용금액
        int creditLimitTotal = 0; //신용카드 총 한도

        //체크카드parameter
        int checkCnt = 0; //체크 건수
        ArrayList<Map<String, String>> checkList = new ArrayList<Map<String, String>>(); //체크list
        int checkAmtTotal = 0; //체크카드 총 이용금액
        int checkLimitTotal = 0; //체크카드 총 한도

        if(creditDetailJsonInfoMap != null
        	&& (!("[]".equals(creditDetailJsonInfoMap.get("list_card_open"))) && creditDetailJsonInfoMap.get("list_card_open") != null)) {
	        listCardOpenStr = creditDetailJsonInfoMap.get("list_card_open");
	        listCardUseStr = creditDetailJsonInfoMap.get("list_card_use");

	        listCardOpenGson = gson.fromJson(listCardOpenStr, type);
	        listCardUseGson = gson.fromJson(listCardUseStr, type);

	        //개설내역, 이용내역 list병합
	        //기관명과 카드타입이 모두 일치하는 경우
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
	        	for (int j = 0; j < listCardUseGson.size(); j++) {
	        		if(listCardOpenGson.get(i).get("nm_fc").equals(listCardUseGson.get(j).get("nm_fc"))) {
	        			if(listCardOpenGson.get(i).get("cd_type_deal").equals(listCardUseGson.get(j).get("cd_type_deal"))) {
			        		 listCardOpenGson.get(i).put("amt_total", StringUtil.NVL(listCardUseGson.get(j).get("amt_total"), "0")); //총이용금액
			                 listCardOpenGson.get(i).put("amt_lump_sum", StringUtil.NVL(listCardUseGson.get(j).get("amt_lump_sum"), "0")); //신용일시불 이용금액
			                 listCardOpenGson.get(i).put("amt_installment", StringUtil.NVL(listCardUseGson.get(j).get("amt_installment"), "0")); //신용할부 이용금액
			                 listCardOpenGson.get(i).put("amt_short_card_loan", StringUtil.NVL(listCardUseGson.get(j).get("amt_short_card_loan"), "0")); //단기카드대출 이용금액
			                 listCardOpenGson.get(i).put("amt_check", StringUtil.NVL(listCardUseGson.get(j).get("amt_check"), "0")); //체크 이용금액
			                 listCardOpenGson.get(i).put("amt_delay", StringUtil.NVL(listCardUseGson.get(j).get("amt_delay"), "0")); //연체금액
			                 listCardUseGson.remove(j);
			                 break;
	        			}
	        		}
				}
	        }
	        //기관명은 일치하지만 카드타입이 일치하지 않는 경우
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
	        	if(!(listCardOpenGson.get(i).containsKey("amt_total"))) {
		        	for (int j = 0; j < listCardUseGson.size(); j++) {
		        		if(listCardOpenGson.get(i).get("nm_fc").equals(listCardUseGson.get(j).get("nm_fc"))) {
		        			listCardOpenGson.get(i).put("amt_total", StringUtil.NVL(listCardUseGson.get(j).get("amt_total"), "0")); //총이용금액
				            listCardOpenGson.get(i).put("amt_lump_sum", StringUtil.NVL(listCardUseGson.get(j).get("amt_lump_sum"), "0")); //신용일시불 이용금액
				            listCardOpenGson.get(i).put("amt_installment", StringUtil.NVL(listCardUseGson.get(j).get("amt_installment"), "0")); //신용할부 이용금액
				            listCardOpenGson.get(i).put("amt_short_card_loan", StringUtil.NVL(listCardUseGson.get(j).get("amt_short_card_loan"), "0")); //단기카드대출 이용금액
				            listCardOpenGson.get(i).put("amt_check", StringUtil.NVL(listCardUseGson.get(j).get("amt_check"), "0")); //체크 이용금액
				            listCardOpenGson.get(i).put("amt_delay", StringUtil.NVL(listCardUseGson.get(j).get("amt_delay"), "0")); //연체금액
				            listCardUseGson.remove(j);
				            break;
		        		}
					}
	        	}
	        }

	        //이용금액 순으로 정렬
	        MapIntegerComparator comp = new MapIntegerComparator("amt_total");
	        Collections.sort(listCardOpenGson, comp);

	        //신용/체크list분리
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
		        if("02".equals(listCardOpenGson.get(i).get("cd_type_deal"))) {
		            checkList.add(checkCnt, listCardOpenGson.get(i)); //체크list
		            checkAmtTotal += Integer.parseInt(StringUtil.NVL(listCardOpenGson.get(i).get("amt_total"), "0")); //체크 총이용금액
		            checkLimitTotal += Integer.parseInt(StringUtil.NVL(listCardOpenGson.get(i).get("amt_limit"), "0")); //체크 총한도
		            checkCnt++; //체크건수
		        }else{
		            creditList.add(creditCnt, listCardOpenGson.get(i)); //신용list

		            creditAmtTotal += Integer.parseInt(StringUtil.NVL(listCardOpenGson.get(i).get("amt_total"), "0")); //신용 총이용금액
		            creditLimitTotal += Integer.parseInt(StringUtil.NVL(listCardOpenGson.get(i).get("amt_limit"), "0")); //신용 총한도
		            creditCnt++; //신용건수
		        }
	        }
        }

        //신용카드
        model.addAttribute("creditCnt",creditCnt); //건수
        model.addAttribute("creditList",creditList); //list
        model.addAttribute("creditAmtTotal", creditAmtTotal); //이용금액
        model.addAttribute("creditLimitTotal", creditLimitTotal); //총한도

        //체크카드
        model.addAttribute("checkCnt",checkCnt); //건수
        model.addAttribute("checkList",checkList); //list
        model.addAttribute("checkAmtTotal", checkAmtTotal); //이용금액
        model.addAttribute("checkLimitTotal", checkLimitTotal); //총한도

        return "jsonView";
    }



    /**
     * 신용관리 카드현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException, ParseException
     */
    @RequestMapping("/frameCreditCardInfo.crz")
    public String frameCreditCardInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException, ParseException {
        String      no_person   = (String)session.getAttribute("no_person");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        //카드개설내역
        String listCardOpenStr = "";
        ArrayList<Map<String, String>> listCardOpenGson = new ArrayList<Map<String, String>>();
        //카드이용내역
        String listCardUseStr = "";
        ArrayList<Map<String, String>> listCardUseGson = new ArrayList<Map<String, String>>();

        //신용카드parameter
        int creditCnt = 0; //신용 건수
        ArrayList<Map<String, String>> creditList = new ArrayList<Map<String, String>>(); //신용list
        int creditAmtTotal = 0; //신용카드 총 이용금액
        int creditLimitTotal = 0; //신용카드 총 한도

        //체크카드parameter
        int checkCnt = 0; //체크 건수
        ArrayList<Map<String, String>> checkList = new ArrayList<Map<String, String>>(); //체크list
        int checkAmtTotal = 0; //체크카드 총 이용금액
        int checkLimitTotal = 0; //체크카드 총 한도

        if(creditDetailJsonInfoMap != null
        	&& (!("[]".equals(creditDetailJsonInfoMap.get("list_card_open"))) && creditDetailJsonInfoMap.get("list_card_open") != null)) {
	        listCardOpenStr = creditDetailJsonInfoMap.get("list_card_open");
	        listCardUseStr = creditDetailJsonInfoMap.get("list_card_use");

	        listCardOpenGson = gson.fromJson(listCardOpenStr, type);
	        listCardUseGson = gson.fromJson(listCardUseStr, type);

	        //개설내역, 이용내역 list병합
	        //기관명과 카드타입이 모두 일치하는 경우
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
	        	for (int j = 0; j < listCardUseGson.size(); j++) {
	        		if(listCardOpenGson.get(i).get("nm_fc").equals(listCardUseGson.get(j).get("nm_fc"))) {
	        			if(listCardOpenGson.get(i).get("cd_type_deal").equals(listCardUseGson.get(j).get("cd_type_deal"))) {
			        		 listCardOpenGson.get(i).put("amt_total", StringUtil.NVL(listCardUseGson.get(j).get("amt_total"), "0")); //총이용금액
			                 listCardOpenGson.get(i).put("amt_lump_sum", StringUtil.NVL(listCardUseGson.get(j).get("amt_lump_sum"), "0")); //신용일시불 이용금액
			                 listCardOpenGson.get(i).put("amt_installment", StringUtil.NVL(listCardUseGson.get(j).get("amt_installment"), "0")); //신용할부 이용금액
			                 listCardOpenGson.get(i).put("amt_short_card_loan", StringUtil.NVL(listCardUseGson.get(j).get("amt_short_card_loan"), "0")); //단기카드대출 이용금액
			                 listCardOpenGson.get(i).put("amt_check", StringUtil.NVL(listCardUseGson.get(j).get("amt_check"), "0")); //체크 이용금액
			                 listCardOpenGson.get(i).put("amt_delay", StringUtil.NVL(listCardUseGson.get(j).get("amt_delay"), "0")); //연체금액
			                 listCardUseGson.remove(j);
			                 break;
	        			}
	        		}
				}
	        }
	        //기관명은 일치하지만 카드타입이 일치하지 않는 경우
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
	        	if(!(listCardOpenGson.get(i).containsKey("amt_total"))) {
		        	for (int j = 0; j < listCardUseGson.size(); j++) {
		        		if(listCardOpenGson.get(i).get("nm_fc").equals(listCardUseGson.get(j).get("nm_fc"))) {
		        			listCardOpenGson.get(i).put("amt_total", StringUtil.NVL(listCardUseGson.get(j).get("amt_total"), "0")); //총이용금액
				            listCardOpenGson.get(i).put("amt_lump_sum", StringUtil.NVL(listCardUseGson.get(j).get("amt_lump_sum"), "0")); //신용일시불 이용금액
				            listCardOpenGson.get(i).put("amt_installment", StringUtil.NVL(listCardUseGson.get(j).get("amt_installment"), "0")); //신용할부 이용금액
				            listCardOpenGson.get(i).put("amt_short_card_loan", StringUtil.NVL(listCardUseGson.get(j).get("amt_short_card_loan"), "0")); //단기카드대출 이용금액
				            listCardOpenGson.get(i).put("amt_check", StringUtil.NVL(listCardUseGson.get(j).get("amt_check"), "0")); //체크 이용금액
				            listCardOpenGson.get(i).put("amt_delay", StringUtil.NVL(listCardUseGson.get(j).get("amt_delay"), "0")); //연체금액
				            listCardUseGson.remove(j);
				            break;
		        		}
					}
	        	}
	        }

	        //이용금액 순으로 정렬
	        MapIntegerComparator comp = new MapIntegerComparator("amt_total");
	        Collections.sort(listCardOpenGson, comp);

	        //신용/체크list분리
	        for (int i = 0; i < listCardOpenGson.size(); i++) {
		        if("02".equals(listCardOpenGson.get(i).get("cd_type_deal"))) {
		            checkList.add(checkCnt, listCardOpenGson.get(i)); //체크list
		            checkAmtTotal += Math.round(Double.valueOf(StringUtil.NVL(listCardOpenGson.get(i).get("amt_total"), "0"))/10000); //체크 총이용금액
		            checkLimitTotal += Math.round(Double.valueOf(StringUtil.NVL(listCardOpenGson.get(i).get("amt_limit"), "0"))/10000); //체크 총한도
		            checkCnt++; //체크건수
		        }else{
		            creditList.add(creditCnt, listCardOpenGson.get(i)); //신용list

		            creditAmtTotal += Math.round(Double.valueOf(StringUtil.NVL(listCardOpenGson.get(i).get("amt_total"), "0"))/10000); //신용 총이용금액
		            creditLimitTotal += Math.round(Double.valueOf(StringUtil.NVL(listCardOpenGson.get(i).get("amt_limit"), "0"))/10000); //신용 총한도
		            creditCnt++; //신용건수
		        }
	        }
        }

        //신용카드
        model.addAttribute("creditCnt",creditCnt);
        model.addAttribute("creditList",creditList);
        model.addAttribute("creditAmtTotal", creditAmtTotal);
        model.addAttribute("creditLimitTotal", creditLimitTotal);

        //체크카드
        model.addAttribute("checkCnt",checkCnt);
        model.addAttribute("checkList",checkList);
        model.addAttribute("checkAmtTotal", checkAmtTotal);
        model.addAttribute("checkLimitTotal", checkLimitTotal);

        return "/credit/frameCreditCardInfo";
    }



    /**
     * VUE
     * 신용관리 대출현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/getCreditLoanInfo.json")
    public String getCreditLoanInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String      no_person   = (String)session.getAttribute("no_person");

        //신용+담보대출
        model.addAttribute("debtSum", creditManager.getCreditDetailDEBTSum(no_person)); //건수, 총잔액, 총금액
        model.addAttribute("debtList", creditManager.getCreditDetailDEBTList(no_person)); //list(cd_debt 01신용 / 02담보)

        return "jsonView";
    }



    /**
     * 신용관리 대출현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/frameCreditLoanInfo.crz")
    public String frameCreditLoanInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String      no_person   = (String)session.getAttribute("no_person");

        //신용대출
        model.addAttribute("creditSum", creditManager.getCreditDetailDEBTCreditSum(no_person));
        model.addAttribute("creditList", creditManager.getCreditDetailDEBTCreditList(no_person));
        //담보대출
        model.addAttribute("loanSum", creditManager.getCreditDetailDEBTLoanSum(no_person));
        model.addAttribute("loanList", creditManager.getCreditDetailDEBTLoanList(no_person));

        //신용+담보대출
//        model.addAttribute("debtSum", creditManager.getCreditDetailDEBTSum(no_person));
//        model.addAttribute("debtList", creditManager.getCreditDetailDEBTList(no_person));

        return "/credit/frameCreditLoanInfo";
    }



    /**
     * VUE
     * 신용관리 연체현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/getCreditOverdueInfo.json")
    public String getCreditOverdueInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String no_person    = (String)session.getAttribute("no_person");

        //잔액 정보
        CreditInfo overdueSumAmtInfo = creditManager.getCreditOverdueSumAmt(no_person);

        int overdueSumAmt = 0; //연체(기타) 잔액
        int steadSumAmt = 0; //연체 잔액
        int overdueEtcSumAmt = 0; //대지급 잔액
        if(overdueSumAmtInfo != null){
        	overdueSumAmt = overdueSumAmtInfo.getKoi_sum_amt();
        	steadSumAmt = overdueSumAmtInfo.getKosi_sum_amt();
        	overdueEtcSumAmt+= overdueSumAmtInfo.getKodi_sum_amt();
        	overdueEtcSumAmt+= overdueSumAmtInfo.getKopi_sum_amt();
        	overdueEtcSumAmt+= overdueSumAmtInfo.getKfdi_sum_amt();
        }

        //연체, 대지급 내역
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        String listOverdueInfoStr = "";
        ArrayList<Map<String, String>> listOverdueInfoGson = new ArrayList<Map<String, String>>();

        if(creditDetailJsonInfoMap != null
        		&& (!("[]".equals(creditDetailJsonInfoMap.get("list_overdue_info"))) && creditDetailJsonInfoMap.get("list_overdue_info") != null)) {
	        listOverdueInfoStr = creditDetailJsonInfoMap.get("list_overdue_info");
	        listOverdueInfoGson = gson.fromJson(listOverdueInfoStr, type);

	      //연체, 대지급내역 날짜 순으로 정렬
	      MapStringComparator compOverdueList = new MapStringComparator("ymd_delay");
	      Collections.sort(listOverdueInfoGson, compOverdueList);

      }

      model.addAttribute("overdueSumAmt", overdueSumAmt); //연체잔액
      model.addAttribute("steadSumAmt", steadSumAmt); //대지급잔액
      model.addAttribute("overdueCnt", listOverdueInfoGson.size()); //연체+대지급 건수
      model.addAttribute("overdueList", listOverdueInfoGson); //연체+대지급 내역(cd_type 01연체 / 02대지급)

      //연체(기타) 건수
      String etcCntDefault = "0"; //채무불이행 건수(DB)
      String etcCntPublic = "0"; //공공정보 건수(DB)
      String etcCntFinDisorder = "0"; //금융질서문란 건수(DB)

      //연체(기타) 내역
      String listOverdueEtcStr = "";
      ArrayList<Map<String, String>> listOverdueEtcGson = new ArrayList<Map<String, String>>();

      if(creditDetailJsonInfoMap != null
    		  && (!("[]".equals(creditDetailJsonInfoMap.get("list_overdue_etc"))) && creditDetailJsonInfoMap.get("list_overdue_etc") != null)) {
    	  etcCntDefault = creditDetailJsonInfoMap.get("cnt_default"); //채무불이행 건수(DB)
          etcCntPublic = creditDetailJsonInfoMap.get("cnt_public"); //공공정보 건수(DB)
          etcCntFinDisorder = creditDetailJsonInfoMap.get("cnt_fin_disorder"); //금융질서문란 건수(DB)

	      listOverdueEtcStr = creditDetailJsonInfoMap.get("list_overdue_etc");
	      listOverdueEtcGson = gson.fromJson(listOverdueEtcStr, type);

	      //기타내역 날짜 순으로 정렬
	      MapStringComparator compEtcList = new MapStringComparator("ymd_delay");
	      Collections.sort(listOverdueEtcGson, compEtcList);

      }

      model.addAttribute("overdueEtcSumAmt",overdueEtcSumAmt); //연체(기타) 잔액

      model.addAttribute("etcCntDefault", etcCntDefault); //채무불이행 건수(DB)
      model.addAttribute("etcCntPublic", etcCntPublic); //공공정보 건수(DB)
      model.addAttribute("etcCntFinDisorder", etcCntFinDisorder); //금융질서문란 건수(DB)

      model.addAttribute("etcOverdueCnt", listOverdueEtcGson.size()); //연체(기타) 건수
      model.addAttribute("etcOverdueList", listOverdueEtcGson); //연체(기타) 내역(cd_type 01채무불이행 / 02공공정보 / 03금융질서문란)

      return "jsonView";
    }



    /**
     * 신용관리 연체현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/frameCreditOverdueInfo.crz")
    public String frameCreditOverdueInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String no_person    = (String)session.getAttribute("no_person");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        //연체 / 대지급 List
        String listOverdueInfoStr = "";
        ArrayList<Map<String, String>> listOverdueInfoGson = new ArrayList<Map<String, String>>();

        //연체parameter
        int overdueCnt = 0; //연체 건수
        ArrayList<Map<String, String>> overdueList = new ArrayList<Map<String, String>>(); //연체list
        int overAmtDelay = 0; //연체잔액

        //대지급parameter
        int steadCnt = 0; //대지급 건수
        ArrayList<Map<String, String>> steadList = new ArrayList<Map<String, String>>(); //대지급list
        int steadAmtDelay = 0; //대지급잔액

        if(creditDetailJsonInfoMap != null
        		&& (!("[]".equals(creditDetailJsonInfoMap.get("list_overdue_info"))) && creditDetailJsonInfoMap.get("list_overdue_info") != null)) {
	        listOverdueInfoStr = creditDetailJsonInfoMap.get("list_overdue_info");
	        listOverdueInfoGson = gson.fromJson(listOverdueInfoStr, type);

	//        ArrayList<Map<String, String>> bsList = new ArrayList<Map<String, String>>(); //연체list
	//        ArrayList<Map<String, String>> steadList = new ArrayList<Map<String, String>>(); //대지급list
	//
	//        //연체,대지급내역 list분리
	//        int bsCnt = 0;
	//        int steadCnt = 0;
	//        for (int i = 0; i < listOverdueInfoGson.size(); i++) {
	//            if("01".equals(listOverdueInfoGson.get(i).get("cd_type"))) {
	//                bsList.add(bsCnt, listOverdueInfoGson.get(i));
	//                bsCnt++;
	//            }else{
	//                steadList.add(steadCnt, listOverdueInfoGson.get(i));
	//                steadCnt++;
	//            }
	//        }
	//
	//        model.addAttribute("bsCnt",bsCnt);
	//        model.addAttribute("bsList",bsList);
	//        model.addAttribute("steadCnt",steadCnt);
	//        model.addAttribute("steadList",steadList);
	//
	//        //연체 연체잔액, 상환금액 계산
	//        int bsAmtDelay = 0;
	//        int bsAmtRepay = 0;
	//        for (int i = 0; i < bsList.size(); i++) {
	//            bsAmtDelay += Integer.parseInt(bsList.get(i).get("amt_delay"));
	//            bsAmtRepay += Integer.parseInt(bsList.get(i).get("amt_repay"));
	//        }
	//        model.addAttribute("bsAmtDelay", bsAmtDelay);
	//        model.addAttribute("bsAmtRepay", bsAmtRepay);
	//
	//        //대지급 대지급잔액, 상환금액 계산
	//        int steadAmtDelay = 0;
	//        int steadAmtRepay = 0;
	//        for (int i = 0; i < steadList.size(); i++) {
	//            steadAmtDelay += Integer.parseInt(steadList.get(i).get("amt_delay"));
	//            steadAmtRepay += Integer.parseInt(steadList.get(i).get("amt_repay"));
	//        }
	//        model.addAttribute("steadAmtDelay", steadAmtDelay);
	//        model.addAttribute("steadAmtRepay", steadAmtRepay);


	      //연체, 대지급내역 날짜 순으로 정렬
	      MapStringComparator compOverdueList = new MapStringComparator("ymd_delay");
	      Collections.sort(listOverdueInfoGson, compOverdueList);

		  //연체 / 대지급list분리
		  for (int i = 0; i < listOverdueInfoGson.size(); i++) {
			if("01".equals(listOverdueInfoGson.get(i).get("cd_type"))) {
				overdueList.add(overdueCnt, listOverdueInfoGson.get(i)); //연체list
				overAmtDelay += Math.round(Double.valueOf(listOverdueInfoGson.get(i).get("amt_delay"))/10000); //연체잔액
				overdueCnt++; //연체건수
			}else{
				steadList.add(steadCnt, listOverdueInfoGson.get(i)); //대지급list
				steadAmtDelay += Math.round(Double.valueOf(listOverdueInfoGson.get(i).get("amt_delay"))/10000); //대지급잔액
			    steadCnt++; //대지급건수
			}
		  }
      }

      model.addAttribute("overdueCnt", overdueCnt);
      model.addAttribute("overdueList", overdueList);
      model.addAttribute("overAmtDelay", overAmtDelay);

      model.addAttribute("steadCnt", steadCnt);
      model.addAttribute("steadList", steadList);
	  model.addAttribute("steadAmtDelay", steadAmtDelay);

	  //기타내역
      String etcCntDefault = "0"; //채무불이행 건수(DB)
      String etcCntPublic = "0"; //공공정보 건수(DB)
      String etcCntFinDisorder = "0"; //금융질서문란 건수(DB)

      String listOverdueEtcStr = "";
      ArrayList<Map<String, String>> listOverdueEtcGson = new ArrayList<Map<String, String>>();

      //채무불이행parameter
      int etcDefaultCnt = 0; //채무불이행 건수
      ArrayList<Map<String, String>> etcDefaultList = new ArrayList<Map<String, String>>(); //채무불이행list

      //공공정보parameter
      int etcPublicCnt = 0; //공공정보 건수
      ArrayList<Map<String, String>> etcPublicList = new ArrayList<Map<String, String>>(); //공공정보list

      //금융질서문란parameter
      int etcFinDisorderCnt = 0; //금융질서문란 건수
      ArrayList<Map<String, String>> etcFinDisorderList = new ArrayList<Map<String, String>>(); //금융질서문란list

      if(creditDetailJsonInfoMap != null
    		  && (!("[]".equals(creditDetailJsonInfoMap.get("list_overdue_etc"))) && creditDetailJsonInfoMap.get("list_overdue_etc") != null)) {
    	  etcCntDefault = creditDetailJsonInfoMap.get("cnt_default"); //채무불이행 건수(DB)
          etcCntPublic = creditDetailJsonInfoMap.get("cnt_public"); //공공정보 건수(DB)
          etcCntFinDisorder = creditDetailJsonInfoMap.get("cnt_fin_disorder"); //금융질서문란 건수(DB)

	      listOverdueEtcStr = creditDetailJsonInfoMap.get("list_overdue_etc");
	      listOverdueEtcGson = gson.fromJson(listOverdueEtcStr, type);

	      //기타내역 날짜 순으로 정렬
	      MapStringComparator compEtcList = new MapStringComparator("ymd_delay");
	      Collections.sort(listOverdueEtcGson, compEtcList);

	      //채무불이행 / 공공정보 / 금융질서문란list분리
	        for (int i = 0; i < listOverdueEtcGson.size(); i++) {
		        if("01".equals(listOverdueEtcGson.get(i).get("cd_type"))) {
		        	etcDefaultList.add(etcDefaultCnt, listOverdueEtcGson.get(i));
		        	etcDefaultCnt++;
		        }else if("02".equals(listOverdueEtcGson.get(i).get("cd_type"))) {
		        	etcPublicList.add(etcPublicCnt, listOverdueEtcGson.get(i));
		        	etcPublicCnt++;
		        }else{
		            etcFinDisorderList.add(etcFinDisorderCnt, listOverdueEtcGson.get(i));
		            etcFinDisorderCnt++;
		        }
	        }
      }

      model.addAttribute("etcCntDefault", etcCntDefault); //채무불이행 건수(DB)
      model.addAttribute("etcCntPublic", etcCntPublic); //공공정보 건수(DB)
      model.addAttribute("etcCntFinDisorder", etcCntFinDisorder); //금융질서문란 건수(DB)

      //채무불이행
      model.addAttribute("etcDefaultCnt", etcDefaultCnt);
      model.addAttribute("etcDefaultList", etcDefaultList);

      //공공정보
      model.addAttribute("etcPublicCnt", etcPublicCnt);
      model.addAttribute("etcPublicList", etcPublicList);

      //금융질서문란
      model.addAttribute("etcFinDisorderCnt", etcFinDisorderCnt);
      model.addAttribute("etcFinDisorderList", etcFinDisorderList);

      return "/credit/frameCreditOverdueInfo";
    }



    /**
     * VUE
     * 신용관리 연대보증현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/getCreditGuaranteeInfo.json")
    public String getCreditGuaranteeInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String no_person    = (String)session.getAttribute("no_person");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        //연대보증내역
        String listGuaranteeStr = "";
        ArrayList<Map<String, String>> listGuaranteeGson = new ArrayList<Map<String,String>>();

        String amtGuarantee = "0";

        if(creditDetailJsonInfoMap != null
        	&& (!("[]".equals(creditDetailJsonInfoMap.get("list_guarantee"))) && creditDetailJsonInfoMap.get("list_guarantee") != null)) {
        	amtGuarantee = StringUtil.NVL(creditDetailJsonInfoMap.get("amt_guarantee"), "0");

	        listGuaranteeStr = creditDetailJsonInfoMap.get("list_guarantee");
	        listGuaranteeGson = gson.fromJson(listGuaranteeStr, type);

	        //연대보증내역 날짜 순으로 정렬
	        MapStringComparator comp = new MapStringComparator("dt_guar_agree");
	        Collections.sort(listGuaranteeGson, comp);
        }

        model.addAttribute("amtGuarantee", amtGuarantee); //보증금액
        model.addAttribute("guaranteeCnt",listGuaranteeGson.size()); //보증건수
        model.addAttribute("guaranteeList",listGuaranteeGson); //연대보증list

        return "jsonView";
    }



    /**
     * 신용관리 연대보증현황
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException,IOException
     */
    @RequestMapping("/frameCreditGuaranteeInfo.crz")
    public String frameCreditGuaranteeInfo(HttpServletRequest request, HttpSession session, Model model) throws FinsetException,IOException {
        String no_person    = (String)session.getAttribute("no_person");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        HashMap<String, String> creditDetailJsonInfoMap = creditManager.getCreditDetailJsonInfo(no_person);

        //연대보증내역
        String listGuaranteeStr = "";
        ArrayList<Map<String, String>> listGuaranteeGson = new ArrayList<Map<String,String>>();

        //연대보증 - 보증금액sum
        String amtGuarantee = "0";

        if(creditDetailJsonInfoMap != null
        	&& (!("[]".equals(creditDetailJsonInfoMap.get("list_guarantee"))) && creditDetailJsonInfoMap.get("list_guarantee") != null)) {
        	amtGuarantee = creditDetailJsonInfoMap.get("amt_guarantee");

	        listGuaranteeStr = creditDetailJsonInfoMap.get("list_guarantee");
	        listGuaranteeGson = gson.fromJson(listGuaranteeStr, type);

	        //연대보증내역 날짜 순으로 정렬
	        MapStringComparator comp = new MapStringComparator("dt_guar_agree");
	        Collections.sort(listGuaranteeGson, comp);
        }

        model.addAttribute("amtGuarantee", amtGuarantee);

        model.addAttribute("guaranteeCnt",listGuaranteeGson.size());
        model.addAttribute("guaranteeList",listGuaranteeGson);

        return "/credit/frameCreditGuaranteeInfo";
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

    /**
     * 신용등급 올리기
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaise.crz")
    public String frameCreditRaise(HttpSession session, Model model, HttpServletRequest request) {
        String no_person    = (String)session.getAttribute("no_person");
        logger.info("frameCreditRaise");
        KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
        kcbReqNonfiInfoVO.setNo_person(no_person);

        List<KcbReqNonfiInfoVO> kcbReqNonfiInfoList = kcbManager.getKcbReqNonfiInfo(kcbReqNonfiInfoVO);
        if(kcbReqNonfiInfoList != null){
        	logger.info("kcbReqNonfiInfoList.size() : " + kcbReqNonfiInfoList.size());
        	for (KcbReqNonfiInfoVO kcbReqNonfiInfo : kcbReqNonfiInfoList) {
        		String cd_req = kcbReqNonfiInfo.getCd_req();
        		int status = Integer.parseInt(kcbReqNonfiInfo.getStatus());
        		String type = "";
        		String statusText = "";
        		String dateText = "";
        		if(cd_req.equals("01"))	{ //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
        			type = "nts";
        		}
        		else if(cd_req.equals("02"))	{
        			type = "nhis";
        		}
        		else if(cd_req.equals("03"))	{
        			type = "nps";
        		}

    			switch(status)	{//01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
    			case 1: //대기
    				statusText = "대기";
    				dateText = kcbReqNonfiInfo.getDt_reg();
    				break;
    			case 2:	//요청
    				statusText = "요청";
    				dateText = kcbReqNonfiInfo.getDt_req();
    				break;
    			case 3:	//전송성공
    				statusText = "전송성공";
    				dateText = kcbReqNonfiInfo.getDt_send();
    				break;
    			case 4:	//전송실패
    				statusText = "전송실패";
    				dateText = kcbReqNonfiInfo.getDt_send();
    				break;
    			}
    			if(DateUtil.getCurrentDate().substring(0,6).equals(dateText.substring(0,6)))	{
    				model.addAttribute(type+"_button", "false");
    			}

    			model.addAttribute(type+"_status", statusText);
    			model.addAttribute(type+"_date", dateText);
        	}


        }
        model.addAttribute("no_person", no_person);

        return "/credit/frameCreditRaise";
    }

    /**
     * 신용등급 올리기 상세
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseDetail.crz")
    public String frameCreditRaiseDetail(HttpSession session, Model model, HttpServletRequest request) {
    	return "/credit/frameCreditRaiseDetail";
    }

    /**
     * 신용등급 올리기 결과
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseResult.crz")
    public String frameCreditRaiseResult(HttpSession session, Model model, HttpServletRequest request) {

    	logger.debug("frameCreditRaiseResult : " + request.getParameter("result"));
    	model.addAttribute("result", request.getParameter("result"));
    	return "/credit/frameCreditRaiseResult";
    }

    /**
     * 신용등급 올리기 조회 실패(조회내역이 없음_
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseEmpty.crz")
    public String frameCreditRaiseEmpty(HttpSession session, Model model, HttpServletRequest request) {
    	logger.debug("frameCreditRaiseEmpty" );
    	return "/credit/frameCreditRaiseEmpty";
    }

    /**
     * 국민건강보험 주민번호
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditSsnInfo.crz")
    public String frameCreditNhisSsn(HttpSession session, Model model, HttpServletRequest request) {
		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);

		if(no_person != null && !no_person.equals("")){
			PersonVO personVO = personManager.getPersonInfo(no_person);
			personVO.setSsn_person(personVO.getBgn().substring(2, 8));
			model.addAttribute("personVO", personVO);

			String currentDate = DateUtil.getCurrentDate();

			String nhisStartYm = DateUtil.getFirstDateOfPrevMonth(currentDate,12).substring(0,6);
			String nhisEndYm = DateUtil.getFirstDateOfPrevMonth(currentDate,1).substring(0,6);

			String npsStartYm = DateUtil.getFirstDateOfPrevMonth(currentDate,12).substring(0,6);
			String npsEndYm = DateUtil.getFirstDateOfPrevMonth(currentDate,1).substring(0,6);

			//  1월~2월 : 직직전년(*), 직직전년-1
		    //  3월~12월 : 직전년(*), 직전년-1
			List<String> inquiryYears = new ArrayList<String>();

			if( Integer.parseInt(currentDate.substring(4,6)) < 03)	{
				inquiryYears.add(DateUtil.addYears(currentDate, -2).substring(0,4));
				inquiryYears.add(DateUtil.addYears(currentDate, -3).substring(0,4));
			}
			else	{
				inquiryYears.add(DateUtil.addYears(currentDate, -1).substring(0,4));
				inquiryYears.add(DateUtil.addYears(currentDate, -2).substring(0,4));
			}

			ScrReqHealthVO scrReqHealthVO  = new ScrReqHealthVO();
			String nhisLastYm = null;
			scrReqHealthVO.setNo_person(no_person);
			ScrReqHealthVO scrReqHealth = scrapManager.getScrReqHealth(scrReqHealthVO);
			if(scrReqHealth != null && "00000000".equals(scrReqHealth.getError_cd()))	{
				nhisLastYm = scrReqHealth.getInquiry_end_yearmonth();
			}
			if(nhisLastYm != null && nhisLastYm.length() == 6   && Integer.parseInt(nhisLastYm) > Integer.parseInt(nhisStartYm))	{
				nhisStartYm = DateUtil.getFirstDateOfPrevMonth(nhisLastYm+"01", -1).substring(0,6);

			}

			ScrReqPensionVO scrReqPensionVO  = new ScrReqPensionVO();
			String npsLastYm = null;
			scrReqPensionVO.setNo_person(no_person);
			ScrReqPensionVO scrReqPension = scrapManager.getScrReqPension(scrReqPensionVO);
			if(scrReqPension != null && "00000000".equals(scrReqPension.getError_cd()))	{
				npsLastYm = scrReqPension.getInquiry_end_yearmonth();
			}

			if(npsLastYm != null && npsLastYm.length() == 6 && Integer.parseInt(npsLastYm) > Integer.parseInt(npsStartYm))	{
				npsStartYm = DateUtil.getFirstDateOfPrevMonth(npsLastYm+"01", -1).substring(0,6);

			}
			logger.debug("nhisStartYm : " + nhisStartYm);
			logger.debug("nhisEndYm   : " + nhisEndYm);
			logger.debug("npsStartYm : " + npsStartYm);
			logger.debug("npsEndYm   : " + npsEndYm);
			model.addAttribute("nhis_start_ym", nhisStartYm);
			model.addAttribute("nhis_end_ym", nhisEndYm);

			model.addAttribute("nps_start_ym", npsStartYm);
			model.addAttribute("nps_end_ym", npsEndYm);

			model.addAttribute("inquiry_years", inquiryYears);
			model.addAttribute("scrap_code", request.getParameter("scrap_code"));
		}
		return "/credit/frameCreditSsnInfo";
    }

    /**
     * 건강보험 납부내역 조회
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseNhis.crz")
    public String frameCreditRaiseNhis(HttpSession session, Model model, HttpServletRequest request) {
    	String no_person = (String) session.getAttribute("no_person");

        PersonVO personVO = personManager.getPersonInfo(no_person);

		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());

        ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO = new ScrRespHealthPaymentdtlVO();
        scrRespHealthPaymentdtlVO.setNo_person(no_person);
        scrRespHealthPaymentdtlVO.setPay_yyyymm(DateUtil.getFirstDateOfPrevMonth(DateUtil.getCurrentDate(),12).substring(0,6));

        List<ScrRespHealthPaymentdtlVO> scrRespHealthPaymentdtlList = scrapManager.getScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
        if(scrRespHealthPaymentdtlList != null)	{
        	String   loan_code = "01";//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출
        	int	nAveCnt = 0;
        	long lTotPayment = 0;
        	long lAmtYearIncome = 0;
        	double insuRate = NumberUtil.stringToDouble(codeManager.getCodeName("cd_premium_rate", "NHIS_PREMIUM_RATE"), 0); // 건강보험보험료율
        	/*
            	직장인인 경우         : 보험료율 / 100 / 2
            	개인사업자의 경우 : 보험료율 / 100
            */
            double dInsuRate = "01".equals(loan_code) ? (insuRate / 100 / 2) : (insuRate / 100);
            nAveCnt = scrRespHealthPaymentdtlList.size();
            //내역 건수가 3건 이상 인 경우 최신 3건으로 설정
            if( nAveCnt >= 3 ) {
                nAveCnt = 3;
            }

        	for(int i = 0; i < scrRespHealthPaymentdtlList.size(); i++)	{
        		ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtl = scrRespHealthPaymentdtlList.get(i);

        		lTotPayment += Integer.parseInt(scrRespHealthPaymentdtl.getAmt_pay_health_insu());
        		if(i+1 == nAveCnt)	{
        			lAmtYearIncome = (long)(((double)((lTotPayment/nAveCnt) / dInsuRate)) * (double)12);
        		}

        	}
        	model.addAttribute("name", personVO.getNm_person());
        	model.addAttribute("amt_year_income", lAmtYearIncome / 10000);
        	model.addAttribute("total_payment", lTotPayment / 10000);
        	model.addAttribute("payment_size", scrRespHealthPaymentdtlList.size());
        	model.addAttribute("payment", scrRespHealthPaymentdtlList);
        }
      	return "/credit/frameCreditRaiseNhis";
    }

    /**
     * 국세청 소득금액 증명 조회
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseNts.crz")
    public String frameCreditRaiseNts(HttpSession session, Model model, HttpServletRequest request) {
    	String no_person = (String) session.getAttribute("no_person");

        PersonVO personVO = personManager.getPersonInfo(no_person);

		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());

		ScrRespIncomeDtlVO scrRespIncomeDtlVO = new ScrRespIncomeDtlVO();
		scrRespIncomeDtlVO.setNo_person(no_person);

        List<ScrRespIncomeDtlVO> scrRespIncomeDtlList = scrapManager.getScrRespIncomeDtl(scrRespIncomeDtlVO);
        if(scrRespIncomeDtlList != null)	{
        	ScrRespIncomeDtlVO scrRespIncomeDtl = scrRespIncomeDtlList.get(0);
        	model.addAttribute("year", scrRespIncomeDtl.getReversion_year());
        	model.addAttribute("income_div", Integer.parseInt(scrRespIncomeDtl.getAmt_income())/10000);
        	model.addAttribute("income", scrRespIncomeDtl.getAmt_income());
        	String incomeDivision = scrRespIncomeDtl.getIncome_division();
        	if(incomeDivision.equals("01"))	{
        		model.addAttribute("income_division", "근로소득 연말정산");
        	}
        	else if(incomeDivision.equals("08"))	{
        		model.addAttribute("income_division", "근로소득 연말정산");
        	}
        	else if(incomeDivision.equals("08"))	{
        		model.addAttribute("income_division", "사업소득 연말정산");
        	}
        	else if(incomeDivision.equals("04"))	{
        		model.addAttribute("income_division", "일용근로소득");
        	}
        	else if(incomeDivision.equals("03"))	{
        		model.addAttribute("income_division", "종합소득세");
        	}
        	else if(incomeDivision.equals("99"))	{
        		model.addAttribute("income_division", "기타");
        	}
        	model.addAttribute("corp_name", scrRespIncomeDtl.getCorp_nm());
        }

		model.addAttribute("name", personVO.getNm_person());
      	return "/credit/frameCreditRaiseNts";
    }

    /**
     * 국민연금 납부내역 조회
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/frameCreditRaiseNps.crz")
    public String frameCreditRaiseNps(HttpSession session, Model model, HttpServletRequest request) {
    	String no_person = (String) session.getAttribute("no_person");

        PersonVO personVO = personManager.getPersonInfo(no_person);

		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());

		model.addAttribute("name", personVO.getNm_person());

		ScrRespPensionPaymentVO scrRespPensionPaymentVO = new ScrRespPensionPaymentVO();
        scrRespPensionPaymentVO.setNo_person(no_person);
        ScrRespPensionPaymentVO scrRespPensionPayment = scrapManager.getScrRespPensionPayment(scrRespPensionPaymentVO);
        if(scrRespPensionPayment != null)	{
        	model.addAttribute("amt_pay", Integer.parseInt(scrRespPensionPayment.getAmt_pay())/10000);
        	model.addAttribute("cnt_month_pay", scrRespPensionPayment.getCnt_month_pay());
        	model.addAttribute("start_year", scrRespPensionPayment.getStart_receipt_yyyymm().substring(0, 4));
        	model.addAttribute("start_month", scrRespPensionPayment.getStart_receipt_yyyymm().substring(4, 6));
        	model.addAttribute("amt_est_pns_month", Integer.parseInt(scrRespPensionPayment.getAmt_est_pns_month())/10000);
        }

        ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO = new ScrRespPensionPaymentdtlVO();
        scrRespPensionPaymentdtlVO.setNo_person(no_person);
        scrRespPensionPaymentdtlVO.setStart_yyyymm(DateUtil.getFirstDateOfPrevMonth(DateUtil.getCurrentDate(),12).substring(0,6));
        scrRespPensionPaymentdtlVO.setEnd_yyyymm(DateUtil.getFirstDateOfPrevMonth(DateUtil.getCurrentDate(),1).substring(0,6));

        List<ScrRespPensionPaymentdtlVO> scrRespPensionPaymentdtlList = scrapManager.getScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
        if(scrRespPensionPaymentdtlList != null)	{
        	model.addAttribute("payment", scrRespPensionPaymentdtlList);
        }

      	return "/credit/frameCreditRaiseNps";
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