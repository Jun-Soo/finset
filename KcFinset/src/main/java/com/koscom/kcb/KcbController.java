package com.koscom.kcb;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
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
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;


@Controller
@RequestMapping("/m/kcb/")
@PropertySource("classpath:prop/webservice.properties")
public class KcbController {

	private static final Logger logger = LoggerFactory.getLogger(KcbController.class);

	@Autowired
	private KcbManager kcbManager;

	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private CreditManager creditManager;
	
	@Resource
	Environment environment;

	@RequestMapping("updateKcbReqNonfiInfo.json")
	public String updateKcbReqNonfiInfo(HttpSession session, Model model, String scrap_code) throws UnsupportedEncodingException,FinsetException,IOException{
		String no_person = (String) session.getAttribute("no_person");
		logger.info("[KCB]updateKcbReqNonfiInfo : "+no_person);
		
		Boolean kcbRegFlag = true;
		
		//요청상태 변경 이전에 KCB에 등록 되어있는지 체크 후 등록
		
		HashMap<String, String> schMap = new HashMap<String, String>();
		schMap.put("sch_no_person", no_person);
		schMap.put("nm_if", 		"600");
		HashMap<String, String> clobMap = creditManager.getKcbInfoCLOB(schMap);
		
		//KCB 등록 정상 처리 내역이 있을 경우 등록 절차 스킵
		if(clobMap != null){
			kcbRegFlag = false;
		}

		String profile  = environment.getProperty("service.profile");
		logger.info("kcbRegFlag : " + kcbRegFlag + " profile : " + profile + " == " + kcbRegFlag);
		
		//KCB 미등록일 경우 등록 절차 수행(로컬에서는 미수행)
		if(kcbRegFlag && !"LOCAL".equals(profile))	{
			//KCB 회원 등록 처리
			PersonVO personVO = personManager.getPersonInfo(no_person);
			
			KcbCreditInfoVO info = new KcbCreditInfoVO();
			info.setNmIf("600");
			info.setCd_regist("01");						// 01 신규, 09 URL 
			info.setBgn(personVO.getBgn());					// 생년월일, 성별
			info.setNoPerson(personVO.getNo_person());		// 회원번호
			info.setNmCust(personVO.getNm_person());		// 회원명
			info.setDi(personVO.getKcb_di());				// 회원 KCB DI
			info.setCp(personVO.getKcb_cp());				// 회원 KCB CP
			info.setHp(personVO.getHp());					// 회원 휴대폰번호
			logger.info("info === " + info.toString());
			
			ReturnClass returnClas = kcbManager.procKcbCb(info);
			// 등록 실패
			if (returnClas.getCd_result() == Constant.FAILED) {
				logger.error("600 전문 처리 실패");
				model.addAttribute("result", Constant.FAILED);
				return "jsonView";
			}

			personManager.modifyKcbId(info);
			logger.info("600 전문 처리 완료");
		}
		
		
		KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
		kcbReqNonfiInfoVO.setNo_person(no_person);
		
		if(scrap_code.equals("nts"))	{ //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
			kcbReqNonfiInfoVO.setCd_req("01");
		}
		else if(scrap_code.equals("nhis"))	{
			kcbReqNonfiInfoVO.setCd_req("02");
		}
		else if(scrap_code.equals("nps"))	{
			kcbReqNonfiInfoVO.setCd_req("03");
		}
		kcbReqNonfiInfoVO.setStatus("02");  //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
		kcbReqNonfiInfoVO.setDt_req(DateUtil.getCurrentDate());
		if( kcbManager.updateKcbReqNonfiInfo(kcbReqNonfiInfoVO) > 0)	{
			model.addAttribute("result", Constant.SUCCESS);
		}
		else	{
			model.addAttribute("result", Constant.FAILED);
		}
				
		
		
		
		return "jsonView";
	}
}
