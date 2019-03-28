package com.koscom.push;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.push.model.PushForm;
import com.koscom.push.model.PushVO;
import com.koscom.push.service.PushManager;
import com.koscom.util.FcmUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/push")
public class PushController {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	@Autowired
	private PushManager pushManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Resource
	Environment environment;
	
	/**
	 * push 목록
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameSendPush.crz")
	public String sendFcmPush(HttpServletRequest request, Model model) {
		return "/push/frameSendPush";
	}
	
	/**
	 * push 목록
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPushInfo.crz")
	public String listPushInfo(PushForm pushForm, Model model) {
		
		pushForm.setPosition();
		logger.info(">>> listEventInfo" + pushForm.getPageSize());
		
		Pagination pagedList = (Pagination) pushForm.setPagedList(pushManager.listPushInfo(pushForm), pushManager.listPushInfoCount(pushForm));
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("pushForm", pushForm);
		return "/push/sub/listPushInfo";
	}
//	
	/**
	 * 푸시 보내기 팝업창
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/popSendPush.crz")
	public String popSendPush(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.info("푸시 보내기 팝업창");
		return "/push/popSendPush";
	}
	
	/**
	 * 푸시 보내기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendPushMsg.json")
	public String sendPushMsg(HttpServletRequest request, HttpServletResponse response, Model model, PushVO pushVO, PushForm pushForm){
		
		SessionUtil sessionUtil = new SessionUtil(request);
		sessionUtil.getUserId();
		
		String sFcmToken = null;
		String os = null;
		String type = null;
		int iSeqPush = 0;
		pushVO.setId_frt(sessionUtil.getUserId());
		pushVO.setPush_divcd("01"); //01:일반
		boolean isSendPushResult;
		
		List<PersonVO> allpersoninfo = personManager.getAllPersonInfo();
		for(int i = 0; i<allpersoninfo.size(); i++){
			if("Y".equals(allpersoninfo.get(i).getYn_push())){
				sFcmToken = allpersoninfo.get(i).getFcm_token();
				os = allpersoninfo.get(i).getYn_os();
				if( os == null || os.equals("")){
					os = "1";
				}
				type = allpersoninfo.get(i).getCd_push();
				logger.info(">>> AllFCM OS : " + os + ", type : "+ type + ", sFcmToken : " + sFcmToken);
				pushVO.setNo_person(allpersoninfo.get(i).getNo_person());
				pushVO.setSendTo(sFcmToken);
				
				isSendPushResult = FcmUtil.sendFcm(pushVO.getSendTo(), pushVO.getTitle(), pushVO.getBody(), pushVO.getLink_addr(), os, type, environment.getProperty("push.fcm"));
				logger.info("푸시 보내기 isSendPushResult  : " + isSendPushResult);
				
				//seq_push 채번
				if("".equals(pushVO.getSeq_push()) || pushVO.getSeq_push() < 1) {
					iSeqPush = pushManager.getNewSeqPush(pushForm);
					pushVO.setSeq_push(iSeqPush);
				}
				logger.debug("푸시 보내기 seq_push  : " + pushVO.getSeq_push() + ", no_person : " + pushVO.getNo_person());
				
				//table 에 저장
//				ReturnClass returnClass = pushManager.createPushInfo(pushVO);
				ReturnClass returnClass = pushManager.createPushEachInfo(pushVO);
				logger.info("푸시 보내기  :   {}", returnClass.getCd_result());
				
				model.addAttribute("result", returnClass.getCd_result());
			}
		}
		
		/*logger.info("푸시 보내기  pushVO :   {}", pushVO.toString());
		boolean isSendPushResult = FcmUtil.sendFcm(pushVO.getSendTo(), pushVO.getTitle(), pushVO.getBody(),"1","");
		logger.info("푸시 보내기 isSendPushResult  : " + isSendPushResult);
				isSendPushResult = FcmUtil.sendFcm(pushVO.getSendTo(), pushVO.getTitle(), pushVO.getBody(),"2","");
		
		pushVO.setId_frt(sessionUtil.getUserId());
		ReturnClass returnClass = pushManager.createPushInfo(pushVO);
		logger.info("푸시 보내기  :   {}", returnClass.getCd_result());
		
		model.addAttribute("result", returnClass.getCd_result());*/
		return "jsonView";
	}
	
	/**
	 * 푸시 상세
	 * @param request
	 * @param model
	 * @param pushVO
	 * @return
	 */
	@RequestMapping("/popViewPushInfo.crz")
	public String popViewPushInfo(HttpServletRequest request, Model model, PushVO pushVO) {
		pushVO = pushManager.viewPushInfo(pushVO);
		model.addAttribute("resultData", pushVO);
		
		//개별푸시상세인 경우 발송고객 리스트 조회
//		if("E".equals(pushVO.getPush_gubun())) {
//		}
		List<PushVO> personList = pushManager.viewPushEachInfoPerson(pushVO);
		model.addAttribute("resultPersonList", personList);
		
		return "/push/popViewPushInfo";
	}
	
	/**
	 * 푸시 보내기 팝업창
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/popSendPushEach.crz")
	public String popSendPushEach(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.info("푸시 보내기 팝업창");
		return "/push/popSendPushEach";
	}

	/**
	 * 고객검색 목록
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCustRel.crz")
	public String listCustRel(PersonForm personForm, Model model) {
		
		List<PersonVO> list = personManager.listPersonInfo(personForm);
		model.addAttribute("listPerson", list);
		model.addAttribute("personForm", personForm);
		
		return "/push/listCustRel";
	}
	
	/**
	 * 개별 푸시 보내기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendPushEachMsg.json")
	public String sendPushEachMsg(HttpServletRequest request, HttpServletResponse response, Model model, PushVO pushVO, PushForm pushForm){
		
		SessionUtil sessionUtil = new SessionUtil(request);
		sessionUtil.getUserId();
		
		logger.info("개별 푸시 보내기  pushVO :   {}", pushVO.toString());
		String[] 	arry_no_person 	= pushVO.getArray_no_person();
		String 		s_no_person 	= "";
		int 		iSeqPush 		= 0;
		
		boolean isSendPushResult;
		String 	os 			= null;
		String 	type 		= null;
		String 	sFcmToken 	= null;
		
		pushVO.setId_frt(sessionUtil.getUserId());
		pushVO.setPush_divcd("01"); //01:일반
		
		for(int i=0; i<arry_no_person.length; i++) {
			
			logger.debug("★ i : " + i);
			s_no_person = arry_no_person[i];
			pushVO.setNo_person(s_no_person);

			//토큰, os 등 조회
			PersonVO personVO = personManager.getPersonInfo(s_no_person);
			
			sFcmToken = personVO.getFcm_token();
			os = personVO.getYn_os();
			
			if( os == null || os.equals("")){
				os = "1";
			}
			
			type = personVO.getCd_push();
			logger.info(">>> FCM OS : " + os + ", type : "+ type + ", sFcmToken : " + sFcmToken);

			model.addAttribute("personFcm_token", sFcmToken);
			pushVO.setSendTo(sFcmToken);
			
			//push 발송
			isSendPushResult = FcmUtil.sendFcm(pushVO.getSendTo(), pushVO.getTitle(), pushVO.getBody(), pushVO.getLink_addr(), os, type, environment.getProperty("push.fcm"));
			logger.info("푸시 보내기 isSendPushResult  : " + isSendPushResult);
			
			
			//seq_push 채번
			if("".equals(pushVO.getSeq_push()) || pushVO.getSeq_push() < 1) {
				iSeqPush = pushManager.getNewSeqPush(pushForm);
				pushVO.setSeq_push(iSeqPush);
			}
			logger.debug("푸시 보내기 seq_push  : " + pushVO.getSeq_push() + ", no_person : " + pushVO.getNo_person());
			
			//table 에 저장
			ReturnClass returnClass = pushManager.createPushEachInfo(pushVO);
			logger.info("푸시 보내기  :   {}", returnClass.getCd_result());
			
			model.addAttribute("result", returnClass.getCd_result());
		}
		
		return "jsonView";
	}
}
