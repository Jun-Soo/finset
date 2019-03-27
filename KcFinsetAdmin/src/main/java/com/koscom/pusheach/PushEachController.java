package com.koscom.pusheach;

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

import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.util.FcmUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.SkipLoginCheck;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/pusheach")
public class PushEachController {

	private static final Logger logger = LoggerFactory.getLogger(PushEachController.class);
	
	@Autowired
	private PushEachManager pushEachManager;
	
	@Autowired
	PersonManager personManager;
	
	@Resource
	Environment environment;
	
	/**
	 * 푸시 보내기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SkipLoginCheck
	@RequestMapping("/sendPushAD01Msg.json")
	public String sendPushAD01Msg(HttpServletRequest request, HttpServletResponse response, Model model){
		
		PushEachForm eventEachForm = new PushEachForm();
		List<PushEachVO> list = pushEachManager.listPushAD01Info(eventEachForm);
		PersonVO personVO = null;
		long   seq_push = 0L  ;// 푸시번호
		String no_person= null;// 고객번호
		String title    = null;// 제목
        String body     = null;// 내용
        String link_addr= null;// 링크주소
        String yn_push  = null;// 푸쉬보낸여부
//        String sendTo   = null;
        String fcm_token=null;
		for (PushEachVO pushEachVO : list) {
			if(pushEachVO.getNo_person() != null) {
			    seq_push  = pushEachVO.getSeq_push ();
                no_person = pushEachVO.getNo_person();
                title     = pushEachVO.getTitle    ();
                body      = pushEachVO.getBody     ();
                link_addr = pushEachVO.getLink_addr();
                yn_push   = pushEachVO.getYn_push  ();
				logger.debug("seq_push ="+seq_push );
				logger.debug("no_person="+no_person);
				logger.debug("title    ="+title    );
				logger.debug("body     ="+body     );
				logger.debug("link_addr="+link_addr);
				logger.debug("yn_push  ="+yn_push  );
                personVO = personManager.getPersonInfo(no_person);
                if (personVO != null) {
                    fcm_token = personVO.getFcm_token();
                    if (fcm_token != null && !fcm_token.equals("")) {
                        pushEachVO.setSendTo(fcm_token);
                        System.out.println("@@@@SendTo())"+fcm_token);

                        if(FcmUtil.sendFcm(  pushEachVO.getSendTo()
                                           , pushEachVO.getTitle()
                                           , pushEachVO.getBody()
                                           , pushEachVO.getLink_addr()
                                , StringUtil.nullToString(personVO.getYn_os(), "1")
                                , StringUtil.nullToString(personVO.getCd_push(), "")
                                , environment.getProperty("push.fcm"))){
                            pushEachManager.modifyYnPushAD01(pushEachVO);
                        }
                    }
                }
            }
		}
		
		return "jsonView";
	}
	/**
	 * push 목록
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameSendPushEach.crz")
	public String sendFcmPush(HttpServletRequest request, Model model) {
		return "/pusheach/frameSendPushEach";
	}
	
	/**
	 * push 목록
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPushEachInfo.crz")
	public String listPushInfo(PushEachForm pushEachForm, Model model) {
		
		pushEachForm.setPosition();
		logger.info(">>> listEventEachInfo" + pushEachForm.getPageSize());
		
		Pagination pagedList = (Pagination) pushEachForm.setPagedList(pushEachManager.listPushEachInfo(pushEachForm), pushEachManager.listPushEachInfoCount(pushEachForm));
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("pushForm", pushEachForm);
		return "/pusheach/sub/listPushEachInfo";
	}
	
	/**
	 * 푸시 보내기 팝업창
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/popSendPushEach.crz")
	public String popSendPush(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.info("푸시 보내기 팝업창");
		return "/pusheach/popSendPushEach";
	}
	
	/**
	 * 푸시 보내기
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendPushEachMsg.json")
	public String sendPushMsg(HttpServletRequest request, HttpServletResponse response, Model model, PushEachVO pushEachVO){
		
		SessionUtil sessionUtil = new SessionUtil(request);
		sessionUtil.getUserId();
		String no_person = pushEachVO.getNo_person();
		PersonVO personVO = personManager.getPersonInfo(no_person);
		
		model.addAttribute("personFcm_token", personVO.getFcm_token());
		logger.info("personFcm_token : " + personVO.getFcm_token());
		
		String os = personVO.getYn_os();
		// logger.info(">>> FCM OS        : " + os.toString());
		String type = personVO.getCd_push();
		logger.info(">>> FCM type        : " + type.toString());
		
		
		logger.info("푸시 보내기  pushVO :   {}", pushEachVO.toString());
		pushEachVO.setSendTo(personVO.getFcm_token());
		boolean isSendPushResult = FcmUtil.sendFcm(pushEachVO.getSendTo(), pushEachVO.getTitle(), pushEachVO.getLink_addr(), pushEachVO.getBody(), os, type, environment.getProperty("push.fcm"));
		logger.info("푸시 보내기 isSendPushResult  : " + isSendPushResult);
		
		pushEachVO.setId_frt(sessionUtil.getUserId());
		ReturnClass returnClass = pushEachManager.createPushEachInfo(pushEachVO);
		logger.info("푸시 보내기  :   {}", returnClass.getCd_result());
		
		model.addAttribute("result", returnClass.getCd_result());
		return "jsonView";
	}
	
	/** 푸시 상세
	 * @param request
	 * @param model
	 * @param pushVO
	 * @return
	 */
	@RequestMapping("/popViewPushEachInfo.crz")
	public String popViewPushEachInfo(HttpServletRequest request, Model model, PushEachVO pushEachVO) {
		pushEachVO = pushEachManager.viewPushEachInfo(pushEachVO);
		model.addAttribute("resultData", pushEachVO);
		return "/pusheach/popViewPushEachInfo";
	}
	/**
	 * 신용변동내역 푸시보내기
	 * @return
	 */
	@RequestMapping("/pushCreditInfo.crz")
	public String pushCreditInfo(PushEachVO pushEachVO) {
		pushEachManager.pushCreditInfo(pushEachVO);
		return "jsonView";
	}

}
