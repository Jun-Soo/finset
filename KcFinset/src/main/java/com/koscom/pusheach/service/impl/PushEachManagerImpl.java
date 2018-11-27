package com.koscom.pusheach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.pusheach.dao.PushEachMapper;
import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("pushEachManager")
public class PushEachManagerImpl implements PushEachManager {

	@Autowired
	private PushEachMapper pushEachMapper;

	@Override
	public List<PushEachVO> listPushNotification(PushEachForm pushEachForm) {
		return pushEachMapper.listPushNotification(pushEachForm);
	}
	
	@Override
	public int listPushNotificationCount(PushEachForm pushEachForm) {
		return pushEachMapper.listPushNotificationCount(pushEachForm);
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
}
