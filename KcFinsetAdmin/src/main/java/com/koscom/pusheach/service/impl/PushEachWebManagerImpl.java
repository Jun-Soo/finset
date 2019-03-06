package com.koscom.pusheach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.pusheach.service.PushEachWebManager;
import com.koscom.util.ReturnClass;

@Service("pushEachWebManager")
public class PushEachWebManagerImpl implements PushEachWebManager{
	@Autowired
	PushEachManager pushEachManager;
	@Override
	public List<PushEachVO> listPushAD01Info(PushEachForm eventEachForm) {
		// TODO Auto-generated method stub
		return pushEachManager.listPushAD01Info(eventEachForm);
	}
	@Override
	public ReturnClass createPushAD01Info(PushEachVO pushEachVO) {
		// TODO Auto-generated method stub
		return pushEachManager.createPushAD01Info(pushEachVO);
	}
	@Override
	public List<PushEachVO> listPushEachInfo(PushEachForm eventEachForm) {
		// TODO Auto-generated method stub
		return pushEachManager.listPushEachInfo(eventEachForm);
	}
	@Override
	public ReturnClass createPushEachInfo(PushEachVO pushEachVO) {
		// TODO Auto-generated method stub
		return pushEachManager.createPushEachInfo(pushEachVO);
	}
	@Override
	public int listPushEachInfoCount(PushEachForm pushEachForm) {
		// TODO Auto-generated method stub
		return pushEachManager.listPushEachInfoCount(pushEachForm);
	}
	@Override
	public PushEachVO viewPushEachInfo(PushEachVO pushEachVO) {
		// TODO Auto-generated method stub
		return pushEachManager.viewPushEachInfo(pushEachVO);
	}
	@Override
	public List<PushEachVO> listPushNotification(PushEachForm pushEachForm) {
		// TODO Auto-generated method stub
		return pushEachManager.listPushNotification(pushEachForm);
	}
	@Override
	public int listPushNotificationCount(PushEachForm pushEachForm) {
		// TODO Auto-generated method stub
		return pushEachManager.listPushNotificationCount(pushEachForm);
	}
	@Override
	public int modifyYnPushAD01(PushEachVO pushEachVO) {
		// TODO Auto-generated method stub
		return pushEachManager.modifyYnPushAD01(pushEachVO);
	}
}
