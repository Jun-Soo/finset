package com.koscom.pusheach.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.dao.PushEachMapper;
import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.util.Constant;
import com.koscom.util.FcmUtil;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;

@Service("pushEachManager")
public class PushEachManagerImpl implements PushEachManager {
	
	@Autowired
	private PushEachMapper pushEachMapper;
	
	@Resource
	Environment environment;

	@Override
	public List<PushEachVO> listPushAD01Info(PushEachForm eventEachForm) {
		return pushEachMapper.listPushAD01Info(eventEachForm);
	}
	@Override
	public List<PushEachVO> listPushEachInfo(PushEachForm eventEachForm) {
		return pushEachMapper.listPushEachInfo(eventEachForm);
	}

	@Override
	public ReturnClass createPushAD01Info(PushEachVO pushEachVO) {
		
		pushEachMapper.createPushAD01Info(pushEachVO);
		
		if(pushEachVO.getSeq_push() > 0) {
			return new ReturnClass(Constant.SUCCESS);
		} else {
			return new ReturnClass(Constant.FAILED);
		}
	}
	@Override
	public ReturnClass createPushEachInfo(PushEachVO pushEachVO) {
		
		pushEachMapper.createPushEachInfo(pushEachVO);
		
		if(pushEachVO.getSeq_push() > 0) {
			return new ReturnClass(Constant.SUCCESS);
		} else {
			return new ReturnClass(Constant.FAILED);
		}
	}

	@Override
	public int listPushEachInfoCount(PushEachForm pushEachForm) {
		return pushEachMapper.listPushEachInfoCount(pushEachForm);
	}

	@Override
	public PushEachVO viewPushEachInfo(PushEachVO pushEachVO) {
		return pushEachMapper.viewPushEachInfo(pushEachVO);
	}

	@Override
	public List<PushEachVO> listPushNotification(PushEachForm pushEachForm) {
		return pushEachMapper.listPushNotification(pushEachForm);
	}

	@Override
	public int listPushNotificationCount(PushEachForm pushEachForm) {
		return pushEachMapper.listPushNotificationCount(pushEachForm);
	}
	@Override
	public int modifyYnPushAD01(PushEachVO pushEachVO) {
		// TODO Auto-generated method stub
		return pushEachMapper.modifyYnPushAD01(pushEachVO);
	}
	
	/**
	 * 신용정보 조회에서 등록한 push 정보에 따라서 푸시 전송
	 * @param pPushEachVO
	 */
	@Override
	public void pushCreditInfo(PushEachVO pPushEachVO) {

		boolean isPushSendResult = true;
		String no_person = pPushEachVO.getNo_person();
		String curDate    = null;
		String yn_send    = null;// 푸쉬보낼지여부(변동내역에서)
		String yn_push    = null;// 푸쉬보낸여부
		String yn_push2    = null;// 푸쉬보낼지여부(신용)
		String yn_push3    = null;// 푸쉬보낼지여부(부채)
		String yn_display = null;// 마이페이지에 보여지는 여부
		String push_divcd = null;// 알림구분
		String sendTo     = null;
		String title      = null;// 제목
		String body       = null;// 내용
		String linkAddr   = null;
		String yn_os      = null;
		String type = "1";
		String  cd_push = null;
		PushEachForm eventEachForm = new PushEachForm();
		List<PushEachVO> list = null;

		list = listPushAD01Info(eventEachForm);
		//LogUtil.debugLn(logger,"PUSH 대상 ="+list);
		if(list != null && list.size() > 0) {
			//LogUtil.debugLn(logger,"PUSH 대상수 ="+list.size());
			for (PushEachVO pushEachVO : list) {
                pushEachVO.setId_frt(no_person);
                pushEachVO.setId_lst(no_person);
				yn_send    = pushEachVO.getYn_send();
				yn_display = pushEachVO.getYn_display();
				yn_push2   = pushEachVO.getYn_push2();
				yn_push3   = pushEachVO.getYn_push3();
				sendTo     = pushEachVO.getFcm_token();
				title      = pushEachVO.getTitle();
				body       = pushEachVO.getBody();
				linkAddr   = pushEachVO.getLink_addr();
				push_divcd = pushEachVO.getPush_divcd();
				yn_os      = pushEachVO.getYn_os();
				yn_os      = StringUtil.nullToString(yn_os, "1");
				cd_push    = StringUtil.nullToString(cd_push, "");
				/**
				 * 보낼 대상인경우
				 */
				if("Y".equals(yn_send)) {
					isPushSendResult = true;
					/**
					 * 푸시를 발송해야하는 경우에만 발송을 한다.
					 */
					if("2".equals(push_divcd) && "Y".equals(yn_push2)
							|| "3".equals(push_divcd) && "Y".equals(yn_push3)
							|| "23".equals(push_divcd) && ("Y".equals(yn_push2)||"Y".equals(yn_push3))) {
						isPushSendResult = FcmUtil.sendFcm(sendTo, title, body, linkAddr, yn_os, cd_push, environment.getProperty("push.fcm"));
					}
					if(isPushSendResult == true) {
						modifyYnPushAD01(pushEachVO);
					}
				} else {
					modifyYnPushAD01(pushEachVO);
				}
				createPushEachInfo(pushEachVO);
			}
		}
		else {
			//LogUtil.debugLn(logger,"PUSH 대상 없음.");
		}
	}

}
