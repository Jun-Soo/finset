package com.koscom.scrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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

import com.google.gson.Gson;
import com.koscom.env.service.CodeManager;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.scrap.model.AnAllVO;
import com.koscom.scrap.model.AppBankInfo;
import com.koscom.scrap.model.AppCardInfo;
import com.koscom.scrap.model.AppFcLinkInfo;
import com.koscom.scrap.model.AppNHISInfo;
import com.koscom.scrap.model.AppNPSInfo;
import com.koscom.scrap.model.AppNTSInfo;
import com.koscom.scrap.model.DepositAnVO;
import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.FundAnVO;
import com.koscom.scrap.model.LinkedFcInfoVO;
import com.koscom.scrap.model.LoanAnVO;
import com.koscom.scrap.model.NHISInfo;
import com.koscom.scrap.model.NPSInfo;
import com.koscom.scrap.model.NTSInfo;
import com.koscom.scrap.model.ScrBankApiAnInfoVO;
import com.koscom.scrap.model.ScrCardApprovalInfoVO;
import com.koscom.scrap.model.ScrCardInfoVO;
import com.koscom.scrap.model.ScrReqBankVO;
import com.koscom.scrap.model.ScrReqCardVO;
import com.koscom.scrap.model.ScrReqCertificationVO;
import com.koscom.scrap.model.ScrReqHealthVO;
import com.koscom.scrap.model.ScrReqPensionVO;
import com.koscom.scrap.model.ScrRespCashReceiptVO;
import com.koscom.scrap.model.ScrRespHealthPaymentVO;
import com.koscom.scrap.model.ScrRespHealthPaymentdtlVO;
import com.koscom.scrap.model.ScrRespIncomeDtlVO;
import com.koscom.scrap.model.ScrRespPensionPaymentVO;
import com.koscom.scrap.model.ScrRespPensionPaymentdtlVO;
import com.koscom.scrap.model.ScrRsltScrapVO;
import com.koscom.scrap.model.UserBankOutputVO;
import com.koscom.scrap.model.UserCardOutputVO;
import com.koscom.scrap.model.sub.AnAllListHistoryVO;
import com.koscom.scrap.model.sub.AnAllListVO;
import com.koscom.scrap.model.sub.DepositAnListHistoryVO;
import com.koscom.scrap.model.sub.DepositAnListVO;
import com.koscom.scrap.model.sub.FundAnListVO;
import com.koscom.scrap.model.sub.LoanAnListVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;
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
		JSONObject jsonObject = new JSONObject();
		logger.debug("createScrapFcList.crz");
		logger.info("금융사 스크래핑 정보   : " + data);
		
		Gson gson = new Gson();
		AppFcLinkInfo appFcLinkInfo = new AppFcLinkInfo();
		FcLinkInfoVO fcLinkInfo = new FcLinkInfoVO();
		PersonCertificateInfoVO personCertificateInfoVO = new PersonCertificateInfoVO(); 
		
		String no_person = "";
		String cn = "";
		
		ReturnClass returnClass;

		appFcLinkInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppFcLinkInfo.class);
        no_person 	= appFcLinkInfo.getNO_PERSON();
        cn 	= appFcLinkInfo.getCN();
        
        logger.debug("no_person  :" + no_person);
        logger.debug("cn         :" + cn);

        //고객 인증서 정보 테이블에 저장
        personCertificateInfoVO.setNo_person(no_person);
        personCertificateInfoVO.setCn(cn);
        personCertificateInfoVO.setCertificate_agency(appFcLinkInfo.getCERTIFICATE_AGENCY());
        personCertificateInfoVO.setDt_expire(appFcLinkInfo.getDT_EXPIRE());
        personCertificateInfoVO.setYn_use("Y");
        personCertificateInfoVO.setId_frt(no_person);
        personManager.createPersonCertificateInfo(personCertificateInfoVO);
        
        //기존 금융사 연계 정보 히스토리 테이블에 저장
        fcLinkInfo.setNO_PERSON(no_person);
        fcLinkInfo.setCN(cn);
        returnClass = scrapManager.createFcLinkInfoHist(fcLinkInfo);
        logger.debug("scrapManager.createFcLinkInfoHist return  :" + returnClass.getCd_result());
        
        //국세청 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getNTS_LINK_INFO() != null){
        	List<FcLinkInfoVO> FC_LINK_INFO = new ArrayList<FcLinkInfoVO>();
        	FcLinkInfoVO fcLinkInfoVO = appFcLinkInfo.getNTS_LINK_INFO();
        	String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCD_FC());
        	String cd_agency = codeManager.getCodeId("cd_agency", "국세청");
        	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getTYPE_LOGIN());
        	fcLinkInfoVO.setNO_PERSON(no_person);
        	fcLinkInfoVO.setCN(cn);
        	fcLinkInfoVO.setCD_FC(cd_fc);
        	fcLinkInfoVO.setCD_AGENCY(cd_agency);
        	fcLinkInfoVO.setTYPE_LOGIN(type_login);
        	if(fcLinkInfoVO.getYN_LINK().equals("Y"))	{
        		fcLinkInfoVO.setCD_LINK_STAT("00");
        	}
        	else	{
        		fcLinkInfoVO.setCD_LINK_STAT("99");
        	}
        	fcLinkInfoVO.setRSN_LINK_MESSAGE(fcLinkInfoVO.getERROR_MESSAGE());
        	fcLinkInfoVO.setID_FRT(no_person);
        	fcLinkInfoVO.setID_LST(no_person);
        	
        	FC_LINK_INFO.add(fcLinkInfoVO);
        	
        	returnClass = scrapManager.createFcLinkInfo(FC_LINK_INFO);
        	logger.debug("scrapManager.createFcLinkInfo return  :" + returnClass.getCd_result());
        }

        //은행사 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getBANK_LINK_INFO() != null){
        	List<FcLinkInfoVO> FC_LINK_INFO = appFcLinkInfo.getBANK_LINK_INFO();
            for (FcLinkInfoVO fcLinkInfoVO : FC_LINK_INFO) {
            	String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCD_FC());
            	String cd_agency = codeManager.getCodeId("cd_agency", "은행");
            	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getTYPE_LOGIN());
            	fcLinkInfoVO.setNO_PERSON(no_person);
            	fcLinkInfoVO.setCN(cn);
            	fcLinkInfoVO.setCD_FC(cd_fc);
            	fcLinkInfoVO.setCD_AGENCY(cd_agency);
            	fcLinkInfoVO.setTYPE_LOGIN(type_login);
            	if(fcLinkInfoVO.getYN_LINK().equals("Y"))	{
            		fcLinkInfoVO.setCD_LINK_STAT("00");
            	}
            	else	{
            		fcLinkInfoVO.setCD_LINK_STAT("99");
            	}
            	fcLinkInfoVO.setRSN_LINK_MESSAGE(fcLinkInfoVO.getERROR_MESSAGE());
            	fcLinkInfoVO.setID_FRT(no_person);
            	fcLinkInfoVO.setID_LST(no_person);
            }
            returnClass = scrapManager.createFcLinkInfo(FC_LINK_INFO);
    		jsonObject.put("result", returnClass.getCd_result());
        }
        
        //카드사 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getCARD_LINK_INFO() != null){
        	List<FcLinkInfoVO> FC_LINK_INFO = appFcLinkInfo.getCARD_LINK_INFO();
            for (FcLinkInfoVO fcLinkInfoVO : FC_LINK_INFO) {
            	String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCD_FC());
            	String cd_agency = codeManager.getCodeId("cd_agency", "카드");
            	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getTYPE_LOGIN());
            	fcLinkInfoVO.setNO_PERSON(no_person);
            	fcLinkInfoVO.setCN(cn);
            	fcLinkInfoVO.setCD_FC(cd_fc);
            	fcLinkInfoVO.setCD_AGENCY(cd_agency);
            	fcLinkInfoVO.setTYPE_LOGIN(type_login);
            	if(fcLinkInfoVO.getYN_LINK().equals("Y"))	{
            		fcLinkInfoVO.setCD_LINK_STAT("00");
            	}
            	else	{
            		fcLinkInfoVO.setCD_LINK_STAT("99");
            	}
            	fcLinkInfoVO.setRSN_LINK_MESSAGE(fcLinkInfoVO.getERROR_MESSAGE());
            	fcLinkInfoVO.setID_FRT(no_person);
            	fcLinkInfoVO.setID_LST(no_person);
            }
            returnClass = scrapManager.createFcLinkInfo(FC_LINK_INFO);
    		jsonObject.put("result", returnClass.getCd_result());
        }
        return jsonObject.toString();
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
		JSONObject jsonObject = new JSONObject();
		 ReturnClass returnClass = null;
		logger.debug("updateScrapFc.crz");
		logger.info("updateScrapFc Data   : " + data);
		
		Gson gson = new Gson();
		FcLinkInfoVO fcLinkInfoVO	= new FcLinkInfoVO();
		
		fcLinkInfoVO = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), FcLinkInfoVO.class);
        
        logger.debug("no_person  :" + fcLinkInfoVO.getNO_PERSON());
        logger.debug("cd_fc      :" + fcLinkInfoVO.getCD_FC());
        logger.debug("cn         :" + fcLinkInfoVO.getCN());
        logger.debug("error_code :" + fcLinkInfoVO.getERROR_CODE());
        logger.debug("error_msg  :" + fcLinkInfoVO.getERROR_MESSAGE());
        
        String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCD_FC());
        fcLinkInfoVO.setCD_FC(cd_fc);
        
        // 스크래핑 변동 내역 History Table에 Insert
        returnClass = scrapManager.createFcLinkInfoHist(fcLinkInfoVO);
        logger.debug("scrapManager.createFcLinkInfoHist return  :" + returnClass.getCd_result());
        
        if(fcLinkInfoVO.getYN_LINK().equals("Y"))	{
    		fcLinkInfoVO.setCD_LINK_STAT("00");
    	}
    	else	{
    		fcLinkInfoVO.setCD_LINK_STAT("99");
    	}
        fcLinkInfoVO.setRSN_LINK_MESSAGE(fcLinkInfoVO.getERROR_MESSAGE());
        
        String cd_agency = null;
        if(fcLinkInfoVO.getCD_AGENCY().equals("bank"))	{
        	cd_agency = codeManager.getCodeId("cd_agency", "은행");
        }
        else if(fcLinkInfoVO.getCD_AGENCY().equals("card"))	{
        	cd_agency = codeManager.getCodeId("cd_agency", "카드");
        }
        else if(fcLinkInfoVO.getCD_AGENCY().equals("nts"))	{
        	cd_agency = codeManager.getCodeId("cd_agency", "국세청");
        }
        fcLinkInfoVO.setCD_AGENCY(cd_agency);
        
        String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getTYPE_LOGIN());
        fcLinkInfoVO.setTYPE_LOGIN(type_login);
        fcLinkInfoVO.setID_FRT(fcLinkInfoVO.getNO_PERSON());
        fcLinkInfoVO.setID_LST(fcLinkInfoVO.getNO_PERSON());
        
        List<FcLinkInfoVO> fcLinkInfoList = new ArrayList<FcLinkInfoVO>();
        
        fcLinkInfoList.add(fcLinkInfoVO);
       
        // 스크래핑 변동 내역  Table에 Update
        returnClass = scrapManager.createFcLinkInfo(fcLinkInfoList);
   		jsonObject.put("result", returnClass.getCd_result());
  
        return jsonObject.toString();
	}
	
	/**
	 * 스크래핑 연동 금융사 해지
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/unlinkScrapFc.json")
	public String unlinkScrapFc(HttpSession session, Model model, HttpServletRequest request, String no_person, String cd_fc) {

		logger.debug("no_person : " + no_person);
		logger.debug("cd_fc     : " + cd_fc);
		
		FcLinkInfoVO fcLinkInfoVO	= new FcLinkInfoVO();
		
		
		fcLinkInfoVO.setNO_PERSON(no_person);
		fcLinkInfoVO.setCD_FC(cd_fc);
		fcLinkInfoVO.setYN_LINK("N");
		fcLinkInfoVO.setCD_LINK_STAT("99");
		fcLinkInfoVO.setID_LST(no_person);
		
		List<FcLinkInfoVO> fcLinkInfoList = new ArrayList<FcLinkInfoVO>();
        fcLinkInfoList.add(fcLinkInfoVO);
        
		ReturnClass returnClass = scrapManager.createFcLinkInfo(fcLinkInfoList);
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
//		no_person = "P000000027";
//		dn = "김주혁()0003047201401020052947";
		logger.debug("no_person : "+ no_person);
		logger.debug("cn        : "+ cn);
		LinkedFcInfoVO linkedFcInfo = new LinkedFcInfoVO();
		
		linkedFcInfo.setNO_PERSON(no_person);
		linkedFcInfo.setCN(cn);
		
		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.getLinkedFcInfo(linkedFcInfo);
		for (LinkedFcInfoVO linkedFcInfoVO : linkedFcInfoList) {
             logger.debug("linkedFcInfoVO.getNO_PERSON() :" + linkedFcInfoVO.getNO_PERSON()  );
             logger.debug("linkedFcInfoVO.getCN()        :" + linkedFcInfoVO.getCN()         );
             logger.debug("linkedFcInfoVO.getNM_FC()     :" + linkedFcInfoVO.getNM_FC()      );
             logger.debug("linkedFcInfoVO.getNM_CODE()   :" + linkedFcInfoVO.getNM_CODE()    );
		}

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
		
		linkedFcInfo.setNO_PERSON(no_person);

		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.getLinkFcInfo(linkedFcInfo);
		List<LinkedFcInfoVO> bankLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> cardLinkInfo = new ArrayList<LinkedFcInfoVO>();
		List<LinkedFcInfoVO> etcLinkInfo = new ArrayList<LinkedFcInfoVO>();
		
		for (LinkedFcInfoVO linkedFcInfoVO : linkedFcInfoList) {
             logger.debug("linkedFcInfoVO.getNO_PERSON() :" + linkedFcInfoVO.getNO_PERSON()  );
             logger.debug("linkedFcInfoVO.getCN()        :" + linkedFcInfoVO.getCN()         );
             logger.debug("linkedFcInfoVO.getNM_FC()     :" + linkedFcInfoVO.getNM_FC()      );
             logger.debug("linkedFcInfoVO.getNM_CODE()   :" + linkedFcInfoVO.getNM_CODE()    );
             linkedFcInfoVO.setNO_PERSON(no_person);
             if(linkedFcInfoVO.getNM_CODE().equals("은행"))	{
            	 bankLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNM_CODE().equals("카드"))	{
            	 cardLinkInfo.add(linkedFcInfoVO);
             }
             else if(linkedFcInfoVO.getNM_CODE().equals("기타"))	{
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
		List<String> bankList = fincorpManager.getCooconFcCd(codeManager.getCodeId("cd_fin","은행"));
		String bankCode = String.join(",", bankList);
			
		List<String> cardList = fincorpManager.getCooconFcCd(codeManager.getCodeId("cd_fin","카드"));
		String cardCode = String.join(",", cardList);
		
		logger.debug("bankCode : " + bankCode);
		logger.debug("cardCode : " + cardCode);
		
		model.addAttribute("bank_code", bankCode);
		model.addAttribute("card_code", cardCode);
		
		return "/scrap/frameFcLinkControl";
	}
	
	/**
	 * 스크래핑 연동 금융사 수정 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateFcLinkInfoList.json")
	public String updateFcLinkInfoList(HttpSession session, FcLinkInfoVO linkedFcInfoList, Model model) throws FinsetException {
		//String no_person = (String) session.getAttribute("no_person");
		logger.debug("updateFcLinkInfoList.json");
		List<FcLinkInfoVO> list = linkedFcInfoList.getList();
		logger.debug("list.size() : "+ list.size());
		for (FcLinkInfoVO fcLinkInfoVO : list) {
			fcLinkInfoVO.setID_LST(fcLinkInfoVO.getNO_PERSON());
			if(fcLinkInfoVO.getYN_LINK().equals("Y"))	{
				fcLinkInfoVO.setCD_LINK_STAT("00");
			}
			else	{
				fcLinkInfoVO.setCD_LINK_STAT("99");
			}
			logger.debug("fcLinkInfoVO.getNO_PERSON() : "+ fcLinkInfoVO.getNO_PERSON());
			logger.debug("fcLinkInfoVO.getCD_FC()     : "+ fcLinkInfoVO.getCD_FC());
		}
		try{
			ReturnClass returnClass = scrapManager.createFcLinkInfo(list);
			model.addAttribute("code", returnClass.getCd_result());
		} catch (Exception e){
			model.addAttribute("code","99");
		}
		return "jsonView";
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
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppBankInfo appBankInfo = new AppBankInfo();
		
		List<LoanAnListVO> loanAnVOLIST = null;
		List<AnAllListVO> anAllVOLIST = null;
		List<DepositAnListVO> depositAnVOLIST = null;
		List<FundAnListVO> fundAnVOLIST = null;

		String no_person = "";
		long seq_scrap = 0;
		String cd_fc = "";
		String max_date = "";
		String error_code = "";
		
		appBankInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppBankInfo.class);
        no_person 	= appBankInfo.getNO_PERSON();
        seq_scrap 	= appBankInfo.getSEQ_SCRAP();
        error_code	= appBankInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);

        if(appBankInfo.getUSER_BANK_OUTPUT() != null){
            
        	List<UserBankOutputVO> USER_BANK_OUTPUT = appBankInfo.getUSER_BANK_OUTPUT();
            for (UserBankOutputVO userBankOutputVO : USER_BANK_OUTPUT) {
                
            	if(userBankOutputVO != null){
            		cd_fc = codeManager.getCodeId("cd_coocon_fc", userBankOutputVO.getBANK_CODE());
            		
                    logger.debug("getBankCode    : " + cd_fc);
                    logger.debug("getERROR_CODE   : " + userBankOutputVO.getERROR_CODE());
                    logger.debug("getERROR_MESSAGE: " + userBankOutputVO.getERROR_MESSAGE());
                    
                    FcLinkInfoVO fcLinkInfoVO  = new FcLinkInfoVO();
                    fcLinkInfoVO.setNO_PERSON(no_person);
                    fcLinkInfoVO.setCD_FC(cd_fc);
                    fcLinkInfoVO.setRSN_LINK_MESSAGE(userBankOutputVO.getERROR_MESSAGE());
                    if(userBankOutputVO.getERROR_CODE().equals("00000000"))	{
                    	fcLinkInfoVO.setCD_LINK_STAT("00");
                    }
                    else	{
                    	fcLinkInfoVO.setCD_LINK_STAT("99");
                    }
                    scrapManager.updateFcLinkInfo(fcLinkInfoVO);
                    
                    List<AnAllListHistoryVO> anAllhistList = new ArrayList<AnAllListHistoryVO>();
                    List<DepositAnListHistoryVO> depositAnhistList = new ArrayList<DepositAnListHistoryVO>();
                    //입출금 계좌 내역
                    if(userBankOutputVO.getAN_ALL() != null){
                        AnAllVO anAllVO = userBankOutputVO.getAN_ALL();
                        logger.debug("anAllVO.getERROR_CODE    : "+ anAllVO.getERROR_CODE());
                        logger.debug("anAllVO.getERROR_MESSAGE : "+ anAllVO.getERROR_MESSAGE());
                        logger.debug("anAllVO.getSTART_DATE	   : "+ anAllVO.getSTART_DATE());
                        logger.debug("anAllVO.getEND_DATE      : "+ anAllVO.getEND_DATE());
                        if(anAllVO.getLIST() != null){
                            anAllVOLIST = anAllVO.getLIST();
                            List<ScrBankApiAnInfoVO> list = new ArrayList<ScrBankApiAnInfoVO>();
                            List<ScrReqBankVO> reqBanklist = new ArrayList<ScrReqBankVO>();
                        	String accountType = codeManager.getCodeName("cd_account_type","입출금계좌");
                            for (AnAllListVO anAllListVO : anAllVOLIST) {
                                logger.debug("anAllVO.ERROR_CODE    : "+ anAllListVO.getERROR_CODE());
                                logger.debug("anAllVO.ERROR_MESSAGE : "+ anAllListVO.getERROR_MESSAGE());
                                logger.debug("anAllVO.AN            : "+ anAllListVO.getAN());
                                logger.debug("anAllVO.BALANCE       : "+ anAllListVO.getBALANCE());
                                logger.debug("anAllVO.CODE          : "+ anAllListVO.getCODE());
                                logger.debug("anAllVO.DATE          : "+ anAllListVO.getDATE());
                                logger.debug("anAllVO.NAME          : "+ anAllListVO.getNAME());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                                scrBankApiAnInfo.setCd_fc(cd_fc);;
                                scrBankApiAnInfo.setNo_person(no_person);
                                scrBankApiAnInfo.setAn(anAllListVO.getAN());
                                scrBankApiAnInfo.setType_an(accountType);
                                scrBankApiAnInfo.setCd_currency(anAllListVO.getCODE());
                                scrBankApiAnInfo.setCurrent_balance(anAllListVO.getBALANCE());
                                scrBankApiAnInfo.setDt_new(anAllListVO.getDATE());
                                scrBankApiAnInfo.setNm_an(anAllListVO.getNAME());
                                list.add(scrBankApiAnInfo);
                                
                                ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                                scrReqBankVO.setSeq_scraping_result(seq_scrap);
                                scrReqBankVO.setCd_fc(cd_fc);
                                scrReqBankVO.setCd_account(accountType);
                                scrReqBankVO.setNo_account(anAllListVO.getAN());
                                scrReqBankVO.setYmd_stt(anAllListVO.getDT_START());
                                scrReqBankVO.setYmd_end(anAllListVO.getDT_END());
                                scrReqBankVO.setError_cd(anAllListVO.getERROR_CODE());
                                scrReqBankVO.setError_msg(anAllListVO.getERROR_MESSAGE());
                                scrReqBankVO.setNo_person(no_person);
                                reqBanklist.add(scrReqBankVO);
                         
                                //해당 고객의 입출금 계좌 중에 Insert된 계좌 상세내역의 날짜/시간 
                                max_date = scrapManager.getMaxDateSrcTransactionDetail(scrBankApiAnInfo);
                                
                                logger.debug(scrBankApiAnInfo.getAn() + " : max_date   :" + max_date);
                                
                                List<AnAllListHistoryVO> anAllListVOHISTORY = anAllListVO.getHISTORY();
                                for (AnAllListHistoryVO anAllListHistoryVO : anAllListVOHISTORY) {
                                	// 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                                	String time = anAllListHistoryVO.getTIME();
                                	if(time == null || time.length() == 0)	{
                                		anAllListHistoryVO.setTIME("000000");
                                	}
                                	String date_time = anAllListHistoryVO.getDATE()+anAllListHistoryVO.getTIME();
                                    logger.debug("anAllVO.AN          : "+ anAllListHistoryVO.getAN());
                                    logger.debug("anAllVO.BALANCE     : "+ anAllListHistoryVO.getBALANCE());
                                    logger.debug("anAllVO.CODE        : "+ anAllListHistoryVO.getCODE());
                                    logger.debug("anAllVO.DATE        : "+ anAllListHistoryVO.getDATE());
                                    logger.debug("anAllVO.TIME        : "+ anAllListHistoryVO.getTIME());
                                    logger.debug("anAllVO.DEALWAY1    : "+ anAllListHistoryVO.getDEALWAY1());
                                    logger.debug("anAllVO.DEALWAY2    : "+ anAllListHistoryVO.getDEALWAY2());
                                    logger.debug("anAllVO.DOC1        : "+ anAllListHistoryVO.getDOC1());
                                    logger.debug("anAllVO.DOC2        : "+ anAllListHistoryVO.getDOC2());
                                    logger.debug("anAllVO.IN_PAYMENT  : "+ anAllListHistoryVO.getIN_PAYMENT());
                                    logger.debug("anAllVO.OUT_PAYMENT : "+ anAllListHistoryVO.getOUT_PAYMENT());
                                    anAllListHistoryVO.setNO_PERSON(no_person);
                                    anAllListHistoryVO.setID_FRT(no_person);
                                    
                                    // 계좌 상세 내역의 마지막 날짜/시간 이후의 데이터만 List에 추가
                                    if(max_date != null && max_date != "" && date_time != null && date_time != ""
                                    	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                                    	continue;
                                    }
                                    anAllhistList.add(anAllListHistoryVO);
                                }
                            }
                            //은행스크래핑 조회 내역 Insert(기존 내역 history 테이블에 저장)
                            ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                            scrReqBankVO.setNo_person(no_person);
                            scrReqBankVO.setCd_fc(cd_fc);
                            scrReqBankVO.setCd_account(codeManager.getCodeName("cd_account_type","입출금계좌"));
                            scrapManager.insertScrReqBankHist(scrReqBankVO);
                            if(reqBanklist.size() > 0)	{
                            	scrapManager.createScrReqBank(reqBanklist);
                            }
                            
                            //입출금 계좌 내역 Insert
                            logger.debug("list.size() : "+ list.size());
                            if(list.size() > 0)	{
                            	scrapManager.createScrBankApiAnInfo(list);
                                list.clear();
                            }
                            
                            
                            //입출금 계좌 입출금 내역 Insert
                            logger.debug("anAllhistList.size() : "+ anAllhistList.size());
                            if(anAllhistList.size() > 0)	{
                            	scrapManager.createScrTransactionDetail(anAllhistList);
                            	anAllhistList.clear();
                            }
                        }
                    }
                    //예적금 계좌 내역
                    if(userBankOutputVO.getDEPOSIT_AN() != null){
                        DepositAnVO depositAnVO = userBankOutputVO.getDEPOSIT_AN();
                        logger.debug("depositAnVO.getERROR_CODE    :"+ depositAnVO.getERROR_CODE());
                        logger.debug("depositAnVO.getERROR_MESSAGE :"+ depositAnVO.getERROR_MESSAGE());
                        if(depositAnVO.getLIST() != null){
                        	// 해당 고객의 예적금 계좌 중에 Insert된 계좌 상세내역의 날짜/시간 
                            List<ScrBankApiAnInfoVO> list = new ArrayList<ScrBankApiAnInfoVO>();
                            List<ScrReqBankVO> reqBanklist = new ArrayList<ScrReqBankVO>();
                        	String accountType = codeManager.getCodeName("cd_account_type","예적금계좌");
                            depositAnVOLIST = depositAnVO.getLIST();
                            for (DepositAnListVO depositAnListVO : depositAnVOLIST) {
                                logger.debug("depositAnListVO.getAN()       :" + depositAnListVO.getAN()       );
                                logger.debug("depositAnListVO.getTYPE()     :" + depositAnListVO.getTYPE()     );
                                logger.debug("depositAnListVO.getNEW_DATE() :" + depositAnListVO.getNEW_DATE() );
                                logger.debug("depositAnListVO.getLAST_DATE():" + depositAnListVO.getLAST_DATE());
                                logger.debug("depositAnListVO.getBALANCE()  :" + depositAnListVO.getBALANCE()  );
                                logger.debug("depositAnListVO.getINTEREST() :" + depositAnListVO.getINTEREST());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(depositAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setCurrent_balance(depositAnListVO.getBALANCE());
                            	scrBankApiAnInfo.setDt_new(depositAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(depositAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setNm_an(depositAnListVO.getTYPE());
                            	scrBankApiAnInfo.setInterest_rate(depositAnListVO.getINTEREST());
                                list.add(scrBankApiAnInfo);
                                
                                ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                                scrReqBankVO.setSeq_scraping_result(seq_scrap);
                                scrReqBankVO.setCd_fc(cd_fc);
                                scrReqBankVO.setCd_account(accountType);
                                scrReqBankVO.setNo_account(depositAnListVO.getAN());
                                scrReqBankVO.setYmd_stt(depositAnListVO.getDT_START());
                                scrReqBankVO.setYmd_end(depositAnListVO.getDT_END());
                                scrReqBankVO.setError_cd(depositAnListVO.getERROR_CODE());
                                scrReqBankVO.setError_msg(depositAnListVO.getERROR_MESSAGE());
                                scrReqBankVO.setNo_person(no_person);
                                reqBanklist.add(scrReqBankVO);

                                max_date = scrapManager.getMaxDateScrSvngSvninDetail(scrBankApiAnInfo);
                                logger.debug(scrBankApiAnInfo.getAn() + "   : max_date   :" + max_date);
                                List<DepositAnListHistoryVO> depositAnListVOHISTORY = depositAnListVO.getHISTORY();
                                for (DepositAnListHistoryVO depositAnListHistoryVO : depositAnListVOHISTORY) {
                                	// 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                                	String time = depositAnListHistoryVO.getTIME();
                                	if(time == null || time.length() == 0)	{
                                		depositAnListHistoryVO.setTIME("000000");
                                	}
                                	String date_time = depositAnListHistoryVO.getDATE()+depositAnListHistoryVO.getTIME();
                                    logger.debug("depositAnVO.AN          : "+ depositAnListHistoryVO.getAN());
                                    logger.debug("depositAnVO.DATE        : "+ depositAnListHistoryVO.getDATE());
                                    logger.debug("depositAnVO.TIME        : "+ depositAnListHistoryVO.getTIME());
                                    logger.debug("depositAnVO.BATCH       : "+ depositAnListHistoryVO.getBATCH());
                                    logger.debug("depositAnVO.MONTH       : "+ depositAnListHistoryVO.getMONTH());
                                    logger.debug("depositAnVO.OUT_PAYMENT : "+ depositAnListHistoryVO.getOUT_PAYMENT());
                                    logger.debug("depositAnVO.IN_PAYMENT  : "+ depositAnListHistoryVO.getIN_PAYMENT());
                                    logger.debug("depositAnVO.BALANCE     : "+ depositAnListHistoryVO.getBALANCE());
                                    logger.debug("depositAnVO.DESCRIPTION : "+ depositAnListHistoryVO.getDESCRIPTION());
                                    logger.debug("depositAnVO.DISTRIBUTOR : "+ depositAnListHistoryVO.getDISTRIBUTOR());
                                    
                                    depositAnListHistoryVO.setNO_PERSON(no_person);
                                    depositAnListHistoryVO.setID_FRT(no_person);
                                    
                                    // 계좌 상세 내역의 마지막 날짜/시간 이후의 데이터만 List에 추가
                                    if(max_date != null && max_date != "" && date_time != null && date_time != ""
                                    	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                                    	continue;
                                    }
                                    depositAnhistList.add(depositAnListHistoryVO);
                                }
                            }
                            //은행스크래핑 조회 내역 Insert(기존 내역 history 테이블에 저장)
                            ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                            scrReqBankVO.setNo_person(no_person);
                            scrReqBankVO.setCd_fc(cd_fc);
                            scrReqBankVO.setCd_account(codeManager.getCodeName("cd_account_type","예적금계좌"));
                            scrapManager.insertScrReqBankHist(scrReqBankVO);
                            if(reqBanklist.size() > 0)	{
                            	scrapManager.createScrReqBank(reqBanklist);
                            }
                            //예적금 계좌 내역 Insert
                            logger.debug("list.size() : "+ list.size());
                            if(list.size() > 0)	{
                            	scrapManager.createScrBankApiAnInfo(list);
                                list.clear();
                            }
                            //예적금 계좌 입출금 내역 Insert
                            logger.debug("depositAnhistList.size() : "+ depositAnhistList.size());
                            if(depositAnhistList.size() > 0)	{
                            	scrapManager.createScrSvngSvninDetail(depositAnhistList);
                            	depositAnhistList.clear();
                            }
                        }
                    }
                    //펀드 계좌 내역
                    if(userBankOutputVO.getFUND_AN() != null){
                    	List<ScrBankApiAnInfoVO> list = new ArrayList<ScrBankApiAnInfoVO>();
                    	String accountType = codeManager.getCodeName("cd_account_type","펀드계좌");
                        FundAnVO fundAnVO = userBankOutputVO.getFUND_AN();
                        logger.debug("fundAnVO.getERROR_CODE()) :" +fundAnVO.getERROR_CODE());
                        logger.debug("fundAnVO.getERROR_MESSAGE(:"+ fundAnVO.getERROR_MESSAGE());
                        if(fundAnVO.getLIST() != null){
                            fundAnVOLIST = fundAnVO.getLIST();
                            for (FundAnListVO fundAnListVO : fundAnVOLIST) {
                                logger.debug("fundAnListVO.AN               :" + fundAnListVO.getAN()               );
                                logger.debug("fundAnListVO.TYPE             :" + fundAnListVO.getTYPE()             );
                                logger.debug("fundAnListVO.PRINCIPAL        :" + fundAnListVO.getPRINCIPAL()        );
                                logger.debug("fundAnListVO.EVALUATION_AMOUNT:" + fundAnListVO.getEVALUATION_AMOUNT());
                                logger.debug("fundAnListVO.NEW_DATE         :" + fundAnListVO.getNEW_DATE()         );
                                logger.debug("fundAnListVO.LAST_DATE        :" + fundAnListVO.getLAST_DATE()        );
                                logger.debug("fundAnListVO.YIELD            :" + fundAnListVO.getYIELD()            );
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(fundAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setPrincipal(fundAnListVO.getPRINCIPAL());
                            	scrBankApiAnInfo.setCurrent_balance(fundAnListVO.getEVALUATION_AMOUNT());
                            	scrBankApiAnInfo.setDt_new(fundAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(fundAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setNm_an(fundAnListVO.getTYPE());
                            	scrBankApiAnInfo.setProfit_rate(fundAnListVO.getYIELD());
                                list.add(scrBankApiAnInfo);
                            }
                            //펀드 계좌 내역 Insert
                            logger.debug("list.size() : "+ list.size());
                            if(list.size() > 0)	{
                            	scrapManager.createScrBankApiAnInfo(list);
                                list.clear();
                            }
                        }
                    }
                    //대출 계좌 내역
                    if(userBankOutputVO.getLOAN_AN() != null){
                    	List<ScrBankApiAnInfoVO> list = new ArrayList<ScrBankApiAnInfoVO>();
                    	String accountType = codeManager.getCodeName("cd_account_type","대출계좌");
                        LoanAnVO loanAnVO = userBankOutputVO.getLOAN_AN();
                        logger.debug("loanAnVO.getERROR_CODE    :" +loanAnVO.getERROR_CODE());
                        logger.debug("loanAnVO.getERROR_MESSAGE :"+ loanAnVO.getERROR_MESSAGE());
                        if(loanAnVO.getLIST() != null){
                            loanAnVOLIST = loanAnVO.getLIST();
                            for (LoanAnListVO loanAnListVO : loanAnVOLIST) {
                                logger.debug("loanAnListVO.getAN           :" + loanAnListVO.getAN()           );
                                logger.debug("loanAnListVO.getTYPE         :" + loanAnListVO.getTYPE()         );
                                logger.debug("loanAnListVO.getLOAN_BALANCE :" + loanAnListVO.getLOAN_BALANCE() );
                                logger.debug("loanAnListVO.getLOAN_CEILING :" + loanAnListVO.getLOAN_CEILING() );
                                logger.debug("loanAnListVO.getNEW_DATE     :" + loanAnListVO.getNEW_DATE()     );
                                logger.debug("loanAnListVO.getLAST_DATE    :" + loanAnListVO.getLAST_DATE()    );
                                logger.debug("loanAnListVO.getLENDING_RATE :" + loanAnListVO.getLENDING_RATE() );
                                logger.debug("loanAnListVO.getFEW_DAYS     :" + loanAnListVO.getFEW_DAYS()     );
                                logger.debug("loanAnListVO.getINTEREST_DATE:" + loanAnListVO.getINTEREST_DATE());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(loanAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setNm_an(loanAnListVO.getTYPE());
                            	scrBankApiAnInfo.setLoan_balance(loanAnListVO.getLOAN_BALANCE());
                            	scrBankApiAnInfo.setLoan_ceiling(loanAnListVO.getLOAN_CEILING());
                            	scrBankApiAnInfo.setDt_new(loanAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(loanAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setInterest_rate(loanAnListVO.getLENDING_RATE());
                            	scrBankApiAnInfo.setFew_days(loanAnListVO.getFEW_DAYS());
                            	scrBankApiAnInfo.setInterest_date(loanAnListVO.getINTEREST_DATE());
                                list.add(scrBankApiAnInfo);
                            }
                            //대출 계좌 내역 Insert
                            logger.debug("list.size() : "+ list.size());
                            if(list.size() > 0)	{
                            	scrapManager.createScrBankApiAnInfo(list);
                                list.clear();
                            }
                        }
                    }
                }
            }
        }
        
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapManager.updateScrRsltScrap(scrRsltScrapVO);

        ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

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
		logger.debug("DATA ::: " + data);
	
		Gson gson = new Gson();
		AppCardInfo appCardInfo = new AppCardInfo();

		String no_person = "";
		long seq_scrap = 0;
		String max_date = "";
		String error_code = "";
		
		appCardInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppCardInfo.class);
        no_person 	= appCardInfo.getNO_PERSON();
        seq_scrap 	= appCardInfo.getSEQ_SCRAP();
        error_code	= appCardInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);
       
        if(appCardInfo.getUSER_CARD_OUTPUT() != null){
        	List<UserCardOutputVO> USER_CARD_OUTPUT = appCardInfo.getUSER_CARD_OUTPUT();
            for (UserCardOutputVO userCardOutputVO : USER_CARD_OUTPUT) {
            	String cd_fc = codeManager.getCodeId("cd_coocon_fc", userCardOutputVO.getCARD_CODE());
            	
            	FcLinkInfoVO fcLinkInfoVO  = new FcLinkInfoVO();
                fcLinkInfoVO.setNO_PERSON(no_person);
                fcLinkInfoVO.setCD_FC(cd_fc);
                fcLinkInfoVO.setRSN_LINK_MESSAGE(userCardOutputVO.getERROR_MESSAGE());
                if(userCardOutputVO.getERROR_CODE().equals("00000000"))	{
                	fcLinkInfoVO.setCD_LINK_STAT("00");
                }
                else	{
                	fcLinkInfoVO.setCD_LINK_STAT("99");
                }
                scrapManager.updateFcLinkInfo(fcLinkInfoVO);
            	
            	//카드내역 저장
            	List<ScrCardInfoVO> ScrCardInfoList = userCardOutputVO.getCARD_INFO();
            	if(ScrCardInfoList != null && ScrCardInfoList.size() > 0){
            		for (ScrCardInfoVO scrCardInfo : ScrCardInfoList) {		
            			logger.debug("scrCardInfo.getNo_card     : "+ scrCardInfo.getNo_card());
                        logger.debug("scrCardInfo.getNm_card     : "+ scrCardInfo.getNm_card());
                        logger.debug("scrCardInfo.getDt_payment  : "+ scrCardInfo.getDt_payment());
                        logger.debug("scrCardInfo.getAmt_payment : "+ scrCardInfo.getAmt_payment());
                        
                        scrCardInfo.setNo_person(no_person);
                        scrCardInfo.setCd_fc(cd_fc);
            		}
            		scrapManager.createScrCardInfo(ScrCardInfoList);
            	}
            	
            	//카드 승인내역 마지막 조회 내역 조회
            	Map<String, Object> paramMap = new HashMap<String, Object>();
            	paramMap.put("no_person", no_person);
            	paramMap.put("cd_fc", cd_fc);
            	max_date = scrapManager.getMaxDateScrCardApprovalInfo(paramMap);
            	//카드 승인내역 저장
            	List<ScrCardApprovalInfoVO> scrCardApprovalInfoList = userCardOutputVO.getCARD_APPROVAL();
            	if(scrCardApprovalInfoList != null){
            		List<ScrCardApprovalInfoVO> list = new ArrayList<ScrCardApprovalInfoVO>();
            		for (ScrCardApprovalInfoVO scrCardApprovalInfo : scrCardApprovalInfoList) {
                         logger.debug("scrCardApprovalInfo.getNo_card      : "+ scrCardApprovalInfo.getNo_card());
                         logger.debug("scrCardApprovalInfo.getDt_approval  : "+ scrCardApprovalInfo.getDt_approval());
                         logger.debug("scrCardApprovalInfo.getTm_approval  : "+ scrCardApprovalInfo.getTm_approval());
                         logger.debug("scrCardApprovalInfo.getNo_approval  : "+ scrCardApprovalInfo.getNo_approval());
                         logger.debug("scrCardApprovalInfo.getAmt_approval : "+ scrCardApprovalInfo.getAmt_approval());
                         scrCardApprovalInfo.setNo_person(no_person);
                         scrCardApprovalInfo.setCd_fc(cd_fc);;
                         // 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                         String time = scrCardApprovalInfo.getTm_approval();
                         if(time == null || time.length() == 0)	{
                        	 scrCardApprovalInfo.setTm_approval("000000");
                         }
                         String date_time = scrCardApprovalInfo.getDt_approval()+scrCardApprovalInfo.getTm_approval();
                         
                         // 카드 승인내역 조회 마지막 날짜 이후 마지막 날짜/시간 이후의 데이터만 List에 추가
                         if(max_date != null && max_date != "" && date_time != null && date_time != ""
                         	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                         	continue;
                         }
                         list.add(scrCardApprovalInfo);
            		}
            		//현금영수증 내역 Insert
                    logger.debug("list.size() : "+ list.size());
                    if(list.size() > 0)	{
                    	scrapManager.createScrCardApprovalInfo(list);
                        list.clear();
                    }
            	}

            	//카드조회 내역 History 저장 및 갱신
            	ScrReqCardVO scrReqCardVO = new ScrReqCardVO();
            	scrReqCardVO.setNo_person(no_person);
            	scrReqCardVO.setCd_fc(cd_fc);
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setYmd_stt(userCardOutputVO.getDT_START());
            	scrReqCardVO.setYmd_end(userCardOutputVO.getDT_END());
            	scrReqCardVO.setError_cd(userCardOutputVO.getAPPROVAL_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getAPPROVAL_ERROR_MESSAGE());
                scrapManager.insertScrReqCardHist(scrReqCardVO);
                scrapManager.createScrReqCard(scrReqCardVO);
            }
        }
        //스크래핑 내역에 결과 수정
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapManager.updateScrRsltScrap(scrRsltScrapVO);
        
        ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

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
		logger.debug("DATA ::: " + data);
	
		Gson gson = new Gson();
		AppNTSInfo appNTSInfo = new AppNTSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String max_date = "";
		String error_code = "";
		
		appNTSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNTSInfo.class);
        no_person 	= appNTSInfo.getNO_PERSON();
        seq_scrap 	= appNTSInfo.getSEQ_SCRAP();
        error_code	= appNTSInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);
       
        if(appNTSInfo.getUSER_NTS_OUTPUT() != null){
        	NTSInfo USER_NTS_OUTPUT = appNTSInfo.getUSER_NTS_OUTPUT();
            if(USER_NTS_OUTPUT != null){
                // 국세청 현금영수증 마지막 조회 내역 조회
            	max_date = scrapManager.getMaxDateScrRespCashReceipt(no_person);
            	List<ScrRespCashReceiptVO> scrRespCashReceiptList = USER_NTS_OUTPUT.getCASH_USE_HISTORY();
            	if(scrRespCashReceiptList != null){
            		List<ScrRespCashReceiptVO> list = new ArrayList<ScrRespCashReceiptVO>();
            		for (ScrRespCashReceiptVO scrRespCashReceipt : scrRespCashReceiptList) {
                         logger.debug("scrRespCashReceipt.getYmd_deal    : "+ scrRespCashReceipt.getYmd_deal());
                         logger.debug("scrRespCashReceipt.getTime_deal   : "+ scrRespCashReceipt.getTime_deal());
                         logger.debug("scrRespCashReceipt.getNm_affiliate: "+ scrRespCashReceipt.getNm_affiliate());
                         logger.debug("scrRespCashReceipt.getAmt_use     : "+ scrRespCashReceipt.getAmt_use());
                         logger.debug("scrRespCashReceipt.getNo_approval : "+ scrRespCashReceipt.getNo_approval());
                                              
                         String date_time = scrRespCashReceipt.getYmd_deal()+scrRespCashReceipt.getTime_deal();
                         // 국세청 현금영수증 조회 마지막 날짜 이후 마지막 날짜/시간 이후의 데이터만 List에 추가
                         if(max_date != null && max_date != "" && date_time != null && date_time != ""
                         	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                         	continue;
                         }
                         
                         scrRespCashReceipt.setNo_person(no_person);
                         String deducation = scrRespCashReceipt.getYn_deduction();
                         if(deducation.equals("공제"))	{
                        	 scrRespCashReceipt.setYn_deduction("Y");
                         }
                         else	{
                        	 scrRespCashReceipt.setYn_deduction("N");
                         }
                         list.add(scrRespCashReceipt);
            		}
            		//현금영수증 내역 Insert
                    logger.debug("list.size() : "+ list.size());
                    if(list.size() > 0)	{
                    	scrapManager.createScrRespCashReceipt(list);
                        list.clear();
                    }
            	}
            	ScrReqCertificationVO scrReqCertificationVO = new ScrReqCertificationVO();
                scrReqCertificationVO.setNo_person(no_person);
                scrReqCertificationVO.setSeq_scraping_result(seq_scrap);
                scrReqCertificationVO.setCd_type("01"); //01 현금영수증 사용내역, 02 소득금액증명, 03 사업자등록증명, 04 사업자등록상태, 05 부가가치세 과세표준증명원, 06 표준재무제표증명, 07 납세사실증명
                scrReqCertificationVO.setRcpt_start_month(USER_NTS_OUTPUT.getRCPT_START_MONTH());
                scrReqCertificationVO.setRcpt_end_month(USER_NTS_OUTPUT.getRCPT_END_MONTH());
                scrReqCertificationVO.setError_cd(USER_NTS_OUTPUT.getERROR_CODE());
                scrReqCertificationVO.setError_msg(USER_NTS_OUTPUT.getERROR_MESSAGE());
                scrapManager.createScrReqCertification(scrReqCertificationVO);
            }
        }
        //스크래핑 내역에 결과 수정
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapManager.updateScrRsltScrap(scrRsltScrapVO);
        
        ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

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
		logger.debug("DATA ::: " + data);
	
		Gson gson = new Gson();
		AppNHISInfo appNHISInfo = new AppNHISInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNHISInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNHISInfo.class);
        no_person 	= appNHISInfo.getNO_PERSON();
        error_code	= appNHISInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "건강보험공단");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapManager.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

        if(appNHISInfo.getUSER_NHIS_OUTPUT() != null){
        	NHISInfo USER_NHIS_OUTPUT = appNHISInfo.getUSER_NHIS_OUTPUT();
        	ScrReqHealthVO scrReqHealthVO = new ScrReqHealthVO();
        	//정상 조회 시에만 조회내역 insert
        	if(USER_NHIS_OUTPUT.getERROR_CODE().equals("00000000"))	{
	        	List<ScrRespHealthPaymentVO> scrRespHealthPaymentVO = USER_NHIS_OUTPUT.getPAYMENT();
	        	if(scrRespHealthPaymentVO != null){
	        		List<ScrRespHealthPaymentVO> list = new ArrayList<ScrRespHealthPaymentVO>();
	        		for (ScrRespHealthPaymentVO scrRespHealthPayment : scrRespHealthPaymentVO) {
	                     logger.debug("scrRespHealthPayment.getNm_comp   : "+ scrRespHealthPayment.getNm_comp());
	                     logger.debug("scrRespHealthPayment.getNm_member : "+ scrRespHealthPayment.getNm_member());
	                     logger.debug("scrRespHealthPayment.getNo_payer  : "+ scrRespHealthPayment.getNo_payer());
	                     logger.debug("scrRespHealthPayment.getYmd_gain  : "+ scrRespHealthPayment.getYmd_gain());
	                     logger.debug("scrRespHealthPayment.getYmd_loss  : "+ scrRespHealthPayment.getYmd_loss());
	                     scrRespHealthPayment.setNo_person(no_person);
	                     
	                     List<ScrRespHealthPaymentdtlVO> scrRespHealthPaymentdtl = scrRespHealthPayment.getPAYMENT_DTL();
	                     if(scrRespHealthPaymentdtl != null)	{
	                    	 for (ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO : scrRespHealthPaymentdtl) {
	                    		 scrRespHealthPaymentdtlVO.setNo_person(no_person);
	                    	 }
	                    	 scrapManager.createScrRespHealthPaymentdtl(scrRespHealthPaymentdtl);
	                     }
	                     
	                     list.add(scrRespHealthPayment);
	                     scrReqHealthVO.setNo_pbls(scrRespHealthPayment.getNo_pbls());
	        		}
	                
	                
	        		//건강보험 내역 Insert
	                logger.debug("list.size() : "+ list.size());
	                if(list.size() > 0)	{
	                	scrapManager.createScrRespHealthPayment(list);
	                    list.clear();
	                }
	        	}
	        	KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
            	kcbReqNonfiInfoVO.setNo_person(no_person);
            	kcbReqNonfiInfoVO.setCd_req("02");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
            	kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
            	kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
            	kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
            	kcbReqNonfiInfoVO.setId_frt(no_person);
            	kcbManager.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
        	}
        	scrReqHealthVO.setNo_person(no_person);
            scrReqHealthVO.setCd_confirm(codeManager.getCodeId("cd_confirm", "납부확인서"));
            scrReqHealthVO.setSeq_scraping_result(seq_scrap);
            scrReqHealthVO.setInquiry_start_yearmonth(USER_NHIS_OUTPUT.getPAYMENT_START_YM());
            scrReqHealthVO.setInquiry_end_yearmonth(USER_NHIS_OUTPUT.getPAYMENT_END_YM());
            scrReqHealthVO.setError_cd(USER_NHIS_OUTPUT.getERROR_CODE());
            scrReqHealthVO.setError_msg(USER_NHIS_OUTPUT.getERROR_MESSAGE());
            scrReqHealthVO.setError_msg(USER_NHIS_OUTPUT.getERROR_MESSAGE());
            scrapManager.createScrReqHealth(scrReqHealthVO);
        }
        
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

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
		logger.debug("DATA ::: " + data);
	
		Gson gson = new Gson();
		AppNTSInfo appNTSInfo = new AppNTSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNTSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNTSInfo.class);
        no_person 	= appNTSInfo.getNO_PERSON();
        error_code	= appNTSInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "국세청");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapManager.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

		
        if(appNTSInfo.getUSER_NTS_OUTPUT() != null){
        	NTSInfo USER_NTS_OUTPUT = appNTSInfo.getUSER_NTS_OUTPUT();
        	String taxation_year = "";
        	String cd_income = "";
        	
            //정상 조회 시에만  조회 내역 insert
            if(USER_NTS_OUTPUT.getERROR_CODE().equals("00000000"))	{
            	List<ScrRespIncomeDtlVO> scrRespIncomeDtlVO = USER_NTS_OUTPUT.getINCOME();
	        	if(scrRespIncomeDtlVO != null){
	        		for (ScrRespIncomeDtlVO scrRespIncomeDtl : scrRespIncomeDtlVO) {
	        			scrRespIncomeDtl.setNo_person(no_person);
	                     logger.debug("scrRespIncomeDtl.getReversion_year   : "+ scrRespIncomeDtl.getReversion_year());
	                     logger.debug("scrRespIncomeDtl.getBiz_licence      : "+ scrRespIncomeDtl.getBiz_licence());
	                     logger.debug("scrRespIncomeDtl.getIncome_division  : "+ scrRespIncomeDtl.getIncome_division());
	                     logger.debug("scrRespIncomeDtl.getCorp_nm          : "+ scrRespIncomeDtl.getCorp_nm());
	                     logger.debug("scrRespIncomeDtl.getAmt_income       : "+ scrRespIncomeDtl.getAmt_income());
	                     taxation_year = scrRespIncomeDtl.getReversion_year();
	                     
	                     // * KCB 코드
	                     // 01:근로소득(갑종) 연말정산, 02:근로소득(을종) 연말정산, 03:종합소득세,  04:일용직근로소득, 05:퇴직소득, 06:양도소득, 07:기타소득, 08:사업소득 연말정산, 99:기타
	                     
	                     if(scrRespIncomeDtl.getIncome_division().equals("근로소득 연말정산"))	{
	                    	 cd_income = "01";
	                     }
	                     else if(scrRespIncomeDtl.getIncome_division().equals("사업소득 연말정산"))	{
	                    	 cd_income = "08";
	                     }
	                     else if(scrRespIncomeDtl.getIncome_division().equals("일용근로소득(국세청 제출)"))	{
	                    	 cd_income = "04";
	                     }
	                     else if(scrRespIncomeDtl.getIncome_division().equals("일용근로소득(고용노동부 제출)"))	{
	                    	 cd_income = "04";
	                     }
	                     else if(scrRespIncomeDtl.getIncome_division().equals("종합소득세"))	{
	                    	 cd_income = "03";
	                     }
	                     else	{
	                    	 cd_income = "99";
	                     }
	                     scrRespIncomeDtl.setIncome_division(cd_income);
	                     scrRespIncomeDtl.setNo_person(no_person);
	                     scrRespIncomeDtl.setCert_division(USER_NTS_OUTPUT.getCERT_DIVISION());
	        		}
	        		//건강보험 내역 Insert
	        		scrapManager.createScrRespIncomeDtl(scrRespIncomeDtlVO);
	        	}
        	
        	  	KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
            	kcbReqNonfiInfoVO.setNo_person(no_person);
            	kcbReqNonfiInfoVO.setCd_req("01");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
            	kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
            	kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
            	kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
            	kcbReqNonfiInfoVO.setId_frt(no_person);
            	kcbManager.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
        	}
            //국세청 스크래핑 조회 내역 저장
            ScrReqCertificationVO scrReqCertificationVO = new ScrReqCertificationVO();
            scrReqCertificationVO.setNo_person(no_person);
            scrReqCertificationVO.setSeq_scraping_result(seq_scrap);
            scrReqCertificationVO.setCd_type("02"); //01 현금영수증 사용내역, 02 소득금액증명, 03 사업자등록증명, 04 사업자등록상태, 05 부가가치세 과세표준증명원, 06 표준재무제표증명, 07 납세사실증명
            scrReqCertificationVO.setTaxation_start_month(USER_NTS_OUTPUT.getTAXATION_START_MONTH());
            scrReqCertificationVO.setTaxation_end_month(USER_NTS_OUTPUT.getTAXATION_END_MONTH());
            scrReqCertificationVO.setCert_division(USER_NTS_OUTPUT.getCERT_DIVISION());
            scrReqCertificationVO.setTaxation_year(taxation_year);
            scrReqCertificationVO.setCd_income(cd_income);
            scrReqCertificationVO.setError_cd(USER_NTS_OUTPUT.getERROR_CODE());
            scrReqCertificationVO.setError_msg(USER_NTS_OUTPUT.getERROR_MESSAGE());
            scrapManager.createScrReqCertification(scrReqCertificationVO);
        }
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

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
		logger.debug("DATA ::: " + data);
	
		Gson gson = new Gson();
		AppNPSInfo appNPSInfo = new AppNPSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNPSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNPSInfo.class);
        no_person 	= appNPSInfo.getNO_PERSON();
        error_code	= appNPSInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "국민연금공단");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapManager.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

        if(appNPSInfo.getUSER_NPS_OUTPUT() != null){
        	NPSInfo USER_NPS_OUTPUT = appNPSInfo.getUSER_NPS_OUTPUT();
        	
        	ScrReqPensionVO scrReqPensionVO = new ScrReqPensionVO();
            scrReqPensionVO.setNo_person(no_person);
            scrReqPensionVO.setCd_confirm("01"); //01 가입내역조회, 02 연금지급내역 증명서, 03 가입증명서, 04 연금산정용 가입내역확인서
            scrReqPensionVO.setSeq_scraping_result(seq_scrap);
            scrReqPensionVO.setInquiry_start_yearmonth(USER_NPS_OUTPUT.getPAYMENT_START_YM());
            scrReqPensionVO.setInquiry_end_yearmonth(USER_NPS_OUTPUT.getPAYMENT_END_YM());
            scrReqPensionVO.setError_cd(USER_NPS_OUTPUT.getERROR_CODE());
            scrReqPensionVO.setError_msg(USER_NPS_OUTPUT.getERROR_MESSAGE());
			scrapManager.createScrReqPension(scrReqPensionVO);
			
			//정상 조회 일 경우에만 조회내역 Insert
			if(USER_NPS_OUTPUT.getERROR_CODE().equals("00000000"))	{
	        	ScrRespPensionPaymentVO scrRespPensionPaymentVO = USER_NPS_OUTPUT.getPAYMENT();
	        	if(scrRespPensionPaymentVO != null){
	        		logger.debug("scrRespPensionPaymentVO.getCnt_month_pay        : "+ scrRespPensionPaymentVO.getCnt_month_pay());
	        		logger.debug("scrRespPensionPaymentVO.getAmt_pay              : "+ scrRespPensionPaymentVO.getAmt_pay());
	        		logger.debug("scrRespPensionPaymentVO.getStart_receipt_yyyymm : "+ scrRespPensionPaymentVO.getStart_receipt_yyyymm());
	        		logger.debug("scrRespPensionPaymentVO.getAmt_est_pns_month    : "+ scrRespPensionPaymentVO.getAmt_est_pns_month());
	        		scrRespPensionPaymentVO.setNo_person(no_person);
	        		scrapManager.createScrRespPensionPayment(scrRespPensionPaymentVO);
	        	}
	        		
	        	List<ScrRespPensionPaymentdtlVO> scrRespPensionPaymentdtl = USER_NPS_OUTPUT.getPAYMENT_DTL();
	            if(scrRespPensionPaymentdtl != null)	{
	               	for (ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO : scrRespPensionPaymentdtl) {
	               		scrRespPensionPaymentdtlVO.setNo_person(no_person);
	               	}
	               	scrapManager.createScrRespPensionPaymentdtl(scrRespPensionPaymentdtl);
	            }
	            KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
	            kcbReqNonfiInfoVO.setNo_person(no_person);
	            kcbReqNonfiInfoVO.setCd_req("03");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
	            kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
	            kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
	            kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
	            kcbReqNonfiInfoVO.setId_frt(no_person);
	            kcbManager.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
			}
        }
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS);

        logger.debug("국민연금 스크래핑 결과 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
}
