package com.koscom.pusheach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.dao.PushEachMapper;
import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("pushEachManager")
public class PushEachManagerImpl implements PushEachManager {
	
	@Autowired
	private PushEachMapper pushEachMapper;

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

}
