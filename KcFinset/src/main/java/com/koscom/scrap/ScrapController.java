package com.koscom.scrap;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koscom.env.service.CodeManager;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.LinkedFcInfoVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FcmUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.SkipLoginCheck;

@Controller
@RequestMapping("/m/scrap")
@PropertySource("classpath:prop/webservice.properties")
public class ScrapController {

	private static final Logger logger = LoggerFactory.getLogger(ScrapController.class);

	@Resource
	Environment environment;
	
	@Autowired
	private ScrapManager scrapManager;

	@Autowired
	private CodeManager codeManager;

	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private FincorpManager fincorpManager;
	
	@Autowired
	private KcbManager kcbManager;
	
	/** VUE
	 * 증권사 스크래핑 시작(OpenAPI)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/startScrapSt.json")
	public String startScrapSt(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person,
			String uuid,
			String token) {
		
		scrapManager.startScrapFinance(no_person, uuid, token);
		
		
		model.addAttribute("result", Constant.SUCCESS);
		return "jsonView";
	}
			
	
	/** VUE
	 * 스크래핑 관련 금융사 내역 조회
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getScrapFcList.json")
	public String getScrapFcList(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model) {
		//스크래핑 대상 은행 리스트 가져오기
		List<String> bankList = fincorpManager.listCooconFcCd(codeManager.getCodeId("cd_fin","은행"));
		String bankCode = String.join(",", bankList);
		
		//스크래핑 대상 카드 리스트 가져오기
		List<String> cardList = fincorpManager.listCooconFcCd(codeManager.getCodeId("cd_fin","카드"));
		String cardCode = String.join(",", cardList);
		
		logger.debug("bankCode : " + bankCode);
		logger.debug("cardCode : " + cardCode);
		
		model.addAttribute("bank_code", bankCode);
		model.addAttribute("card_code", cardCode);
		
		return "jsonView";
	}
	
	/** VUE
	 * 금융정보제공동의서 조회
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTermsContent.json")
	public String getTermsContent(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person,
			String uuid,
			String dn,
			String email) {
		logger.debug("================= no_person : " + no_person);
		logger.debug("================= uuid : " + uuid);
		logger.debug("================= dn : " + dn);
		logger.info("service.profile :" +environment.getProperty("service.profile"));
		
		no_person = (String) session.getAttribute("no_person");
		//scrapManager.getDirectFinanceSearch();
		
		String financeTerms = scrapManager.getFinanceTerms(no_person, uuid, dn, email);
		
		logger.debug("financeTerms : " + financeTerms);
		model.addAttribute("financeTerms", financeTerms);
		return "jsonView";
	}
	
	/** VUE
	 * 금융정보제공동의서 전송
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendTermsContent.json")
	public String sendTermsContent(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person,
			String uuid,
			String dn,
			String email,
			String financeTerms,
			String jwsInfo) {
		logger.debug("================= no_person : " + no_person);
		logger.debug("================= uuid : " + uuid);
		logger.debug("================= dn : " + dn);
		logger.debug("================= financeTerms : " + financeTerms);
		logger.debug("================= jwsInfo : " + jwsInfo);
		
		logger.info("service.profile :" +environment.getProperty("service.profile"));
		
		no_person = (String) session.getAttribute("no_person");
		//scrapManager.getDirectFinanceSearch();
		
		String result = scrapManager.sendFinanceTerms(no_person, uuid, dn, email, financeTerms, jwsInfo);
		
		logger.debug("result : " + result);
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	/** VUE
	 * 스크래핑 관련 증권사 내역 조회
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getScrapStList.json")
	public String getScrapStList(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person,
			String uuid,
			String dn) {
		logger.debug("================= no_person : " + no_person);
		logger.debug("================= dn : " + dn);
		logger.info("service.profile :" +environment.getProperty("service.profile"));
		
		String token = scrapManager.getAccessToken();
		logger.debug("================= token : " + token);
		
		token = "Bearer fbf794d3-9be6-4163-9df6-927d43736470";
		scrapManager.checkAllFinance(no_person, uuid, dn, token);

		model.addAttribute("token", token);
		return "jsonView";
	}
	
	/** VUE
	 * 스크래핑 연동 금융사 조회
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/scrapFcLinkList.json")
	public String scrapFcLinkList(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person,
			String cn,
			String dn)	{ 
		
		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.listFcLinkList(no_person, cn, dn);

		model.addAttribute("linkedFcInfoList", linkedFcInfoList);
		return "jsonView";
	}
	
	/** VUE
	 * 스크래핑 연동 금융사 수정 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateFcLinkInfoList.json")
	public String updateFcLinkInfoList(
		HttpServletResponse response,
		HttpServletRequest request, 
		HttpSession session, 
		Model model,
		FcLinkInfoVO linkedFcInfoList)	{ 
		logger.debug("updateFcLinkInfoList.json");
				
		ReturnClass returnClass = scrapManager.updateFcLinkInfoList(linkedFcInfoList);
		model.addAttribute("code", returnClass.getCd_result());
		
		logger.debug("code : "+returnClass.getCd_result());
		
		return "jsonView";
	}
	
	/** VUE
	 * 스크래핑 연동 금융사 관리 화면 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/listFcLinkInfo.json")
	public String listFcLinkInfo(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model)	{ 
		String no_person = (String) session.getAttribute("no_person");

		logger.debug("no_person : "+ no_person);
		PersonVO personVO = null;
		personVO = personManager.getPersonInfo(no_person);
		
		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());
		
		model.addAttribute("nm_person", personVO.getNm_person());
		
		LinkedFcInfoVO linkedFcInfo = new LinkedFcInfoVO();
		
		linkedFcInfo.setNo_person(no_person);

		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.getLinkFcInfo(linkedFcInfo);
		List<LinkedFcInfoVO> bankLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> cardLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> stockLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> etcLinkInfo = new ArrayList<LinkedFcInfoVO>();
		
		for (LinkedFcInfoVO linkedFcInfoVO : linkedFcInfoList) {
             logger.debug("linkedFcInfoVO.getNO_PERSON() :" + linkedFcInfoVO.getNo_person()  );
             logger.debug("linkedFcInfoVO.getCN()        :" + linkedFcInfoVO.getCn()         );
             logger.debug("linkedFcInfoVO.getNM_FC()     :" + linkedFcInfoVO.getNm_fc()      );
             logger.debug("linkedFcInfoVO.getNM_CODE()   :" + linkedFcInfoVO.getNm_code()    );
             linkedFcInfoVO.setNo_person(no_person);
             if(linkedFcInfoVO.getNm_code().equals("은행"))	{
            	 bankLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNm_code().equals("카드"))	{
            	 cardLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNm_code().equals("증권"))	{
            	 stockLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNm_code().equals("기타"))	{
            	 etcLinkInfo.add(linkedFcInfoVO);
             }
		}
		logger.debug("bankLinkInfo.size()   :" + bankLinkInfo.size()    );
		logger.debug("cardLinkInfo.size()   :" + cardLinkInfo.size()    );
		logger.debug("stockLinkInfo.size()   :" + stockLinkInfo.size()    );
		logger.debug("etcLinkInfo.size()    :" + etcLinkInfo.size()    );
		if(bankLinkInfo.size() > 0)	{
			model.addAttribute("bankList", bankLinkInfo);
		}
		if(cardLinkInfo.size() > 0)	{
			model.addAttribute("cardList", cardLinkInfo);
		}
		if(stockLinkInfo.size() > 0)	{
			model.addAttribute("stockList", stockLinkInfo);
		}
		if(etcLinkInfo.size() > 0)	{
			model.addAttribute("etcList", etcLinkInfo);
		}
		
		return "jsonView";
	}
	
	/** VUE
	 * 스크래핑 연동 금융사 관리 화면 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAutoScrapInfo.json")
	public String getAutoScrapInfo(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			String no_person)	{
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
    	
    	//SMS내역 및 스크래핑 내역 화면에 전송
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

    	return "jsonView"; 
	}
	
	/** VUE
	 * 개별 푸시 보내기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendPushMsg.json")
	public String sendPushMsg(HttpServletRequest request, HttpServletResponse response, Model model, String no_person, String push_msg){
		
		SessionUtil sessionUtil = new SessionUtil(request);
		sessionUtil.getUserId();
		
		logger.info("푸시 보내기 : ", no_person);

		boolean isSendPushResult;
		String 	os 			= null;
		String 	type 		= null;
		String 	sFcmToken 	= null;
		
		//토큰, os 등 조회
		PersonVO personVO = personManager.getPersonInfo(no_person);
			
		sFcmToken = personVO.getFcm_token();
		os = personVO.getYn_os();
			
		if( os == null || os.equals("")){
			os = "1";
		}
			
		type = personVO.getCd_push();
		logger.info(">>> FCM OS : " + os + ", type : "+ type + ", sFcmToken : " + sFcmToken);

		//push 발송
		isSendPushResult = FcmUtil.sendFcm(sFcmToken, push_msg, push_msg, "", os, type);
		logger.info("푸시 보내기 isSendPushResult  : " + isSendPushResult);
		
		if(isSendPushResult)	{
			model.addAttribute("result", Constant.SUCCESS);
		}
		else	{
			model.addAttribute("result", Constant.FAILED);
		}
		return "jsonView";
	}
	
	/**
	 * 금융사 스크랩핑 연동 정보 여부 insert
	 * @param request
	 * @param response
	 * @param model
	 * @param data
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createScrapFcList.crz")
	public String createScrapFcList(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		return scrapManager.createScrapFcList(data);
	}
	
	/**
	 * 금융사 스크랩핑 연동 정보 여부 update
	 * @param request
	 * @param response
	 * @param model
	 * @param data
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/updateScrapFc.crz")
	public String updateScrapFcList(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		return scrapManager.updateScrapFcList(data);
	}
	
	/**
	 * 스크래핑 연동 금융사 해지
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/unlinkScrapFc.json")
	public String unlinkScrapFc(HttpSession session, Model model, HttpServletRequest request, String no_person, String cd_fc) {
		logger.debug("no_person :" + no_person + "cd_fc :" + cd_fc  );
		ReturnClass returnClass = scrapManager.unlinkScrapFc(no_person, cd_fc);
		
		model.addAttribute("message", returnClass.getMessage());
		model.addAttribute("result", returnClass.getCd_result());
		model.addAttribute("no_person", no_person);
		return "jsonView";

	}
	
	
	/**
	 * 스크래핑 연동 금융사 조회 화면 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameFcLinkList.crz")
	public String frameFcLinkList(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		String cn = request.getParameter("cn");
		String dn = request.getParameter("dn");
		
		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.listFcLinkList(no_person, cn, dn);

		model.addAttribute("linkedFcInfoList", linkedFcInfoList);
		return "/scrap/frameFcLinkList";
	}
	
	/**
	 * 스크래핑 연동 금융사 관리 화면 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameFcLinkControl.crz")
	public String frameFcLinkControl(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");

		logger.debug("no_person : "+ no_person);
		PersonVO personVO = null;
		personVO = personManager.getPersonInfo(no_person);
		
		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());
		
		model.addAttribute("nm_person", personVO.getNm_person());
		
		LinkedFcInfoVO linkedFcInfo = new LinkedFcInfoVO();
		
		linkedFcInfo.setNo_person(no_person);

		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.getLinkFcInfo(linkedFcInfo);
		List<LinkedFcInfoVO> bankLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> cardLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> etcLinkInfo = new ArrayList<LinkedFcInfoVO>();
		
		for (LinkedFcInfoVO linkedFcInfoVO : linkedFcInfoList) {
             logger.debug("linkedFcInfoVO.getNO_PERSON() :" + linkedFcInfoVO.getNo_person()  );
             logger.debug("linkedFcInfoVO.getCN()        :" + linkedFcInfoVO.getCn()         );
             logger.debug("linkedFcInfoVO.getNM_FC()     :" + linkedFcInfoVO.getNm_fc()      );
             logger.debug("linkedFcInfoVO.getNM_CODE()   :" + linkedFcInfoVO.getNm_code()    );
             linkedFcInfoVO.setNo_person(no_person);
             if(linkedFcInfoVO.getNm_code().equals("은행"))	{
            	 bankLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNm_code().equals("카드"))	{
            	 cardLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNm_code().equals("기타"))	{
            	 etcLinkInfo.add(linkedFcInfoVO);
             }
		}
		logger.debug("bankLinkInfo.size()   :" + bankLinkInfo.size()    );
		logger.debug("cardLinkInfo.size()   :" + cardLinkInfo.size()    );
		logger.debug("etcLinkInfo.size()    :" + etcLinkInfo.size()    );
		if(bankLinkInfo.size() > 0)	{
			model.addAttribute("bankList", bankLinkInfo);
		}
		if(cardLinkInfo.size() > 0)	{
			model.addAttribute("cardList", cardLinkInfo);
		}
		if(etcLinkInfo.size() > 0)	{
			model.addAttribute("etcList", etcLinkInfo);
		}
		
		//스크래핑 대상 은행 리스트 가져오기
		List<String> bankList = fincorpManager.listCooconFcCd(codeManager.getCodeId("cd_fin","은행"));
		String bankCode = String.join(",", bankList);
			
		List<String> cardList = fincorpManager.listCooconFcCd(codeManager.getCodeId("cd_fin","카드"));
		String cardCode = String.join(",", cardList);
		
		logger.debug("bankCode : " + bankCode);
		logger.debug("cardCode : " + cardCode);
		
		model.addAttribute("bank_code", bankCode);
		model.addAttribute("card_code", cardCode);
		
		return "/scrap/frameFcLinkControl";
	}
	
	/**
	 * 은행 자동 스크래핑 내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createAutoBankScrap.crz")
	@Transactional
	public String createAutoBankScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		logger.debug("createAutoBankScrap.crz");
		
        ReturnClass returnClass = scrapManager.createAutoBankScrap(data);

        logger.debug("은행 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}

	/**
	 * 카드 자동 스크래핑 내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createAutoCardScrap.crz")
	@Transactional
	public String createAutoCardScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		logger.debug("createAutoCardScrap.crz");
		        
        ReturnClass returnClass = scrapManager.createAutoCardScrap(data);

        logger.debug("카드 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();

	}

	/**
	 * 국세청 자동 스크래핑 내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createAutoNTSScrap.crz")
	@Transactional
	public String createAutoNTSScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		
		JSONObject jsonObject = new JSONObject();
		logger.debug("createAutoNTSScrap.crz");
        
        ReturnClass returnClass = scrapManager.createAutoNTSScrap(data); 

        logger.debug("국세청 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
	
	/**
	 * 건강보험 스크래핑 내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createNHISScrap.crz")
	@Transactional
	public String createNHISScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		logger.debug("createNHISScrap.crz");
		
		ReturnClass returnClass = scrapManager.createNHISScrap(data);

        logger.debug("건강보험 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
	
	/**
	 * 국세청 스크래핑 내역 저장(소득금액증명) 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createNTSScrap.crz")
	@Transactional
	public String createNTSScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		logger.debug("createNTSScrap.crz");
		
		ReturnClass returnClass = scrapManager.createNTSScrap(data);

        logger.debug("국세청 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
	
	/**
	 * 국민연금 스크래핑 내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createNPSScrap.crz")
	@Transactional
	public String createNPSScrap(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody String data) {
		
		JSONObject jsonObject = new JSONObject();
		logger.debug("createNPSScrap.crz");
		
		ReturnClass returnClass = scrapManager.createNPSScrap(data);

        logger.debug("국민연금 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
}
