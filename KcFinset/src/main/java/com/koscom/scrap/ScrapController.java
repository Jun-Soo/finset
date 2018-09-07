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
		
		List<LinkedFcInfoVO> linkedFcInfoList = scrapManager.frameFcLinkList(no_person, cn);

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
		logger.debug("updateFcLinkInfoList.json");
				
		ReturnClass returnClass = scrapManager.updateFcLinkInfoList(linkedFcInfoList);
		model.addAttribute("code", returnClass.getCd_result());
		
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
