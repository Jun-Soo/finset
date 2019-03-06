package com.koscom.push.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.push.dao.PushMapper;
import com.koscom.push.model.PushForm;
import com.koscom.push.model.PushVO;
import com.koscom.push.service.PushManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("pushManager")
public class PushManagerImpl implements PushManager {
	@Autowired
	private PushMapper pushMapper;

	@Override
	public List<PushVO> listPushInfo(PushForm eventForm) {
		return pushMapper.listPushInfo(eventForm);
	}

	@Override
	public ReturnClass createPushInfo(PushVO pushVO) {
		
		pushMapper.createPushInfo(pushVO);
		
		if(pushVO.getSeq_push() > 0) {
			return new ReturnClass(Constant.SUCCESS);
		} else {
			return new ReturnClass(Constant.FAILED);
		}
	}

	@Override
	public int listPushInfoCount(PushForm pushForm) {
		return pushMapper.listPushInfoCount(pushForm);
	}

	@Override
	public PushVO viewPushInfo(PushVO pushVO) {
		return pushMapper.viewPushInfo(pushVO);
	}

	@Override
	public List<PushVO> viewPushEachInfoPerson(PushVO pushVO) {
		return pushMapper.viewPushEachInfoPerson(pushVO);
	}

	@Override
	public int getNewSeqPush(PushForm pushForm) {
		return pushMapper.getNewSeqPush(pushForm);
	}

	@Override
	public ReturnClass createPushEachInfo(PushVO pushVO) {
		
		pushMapper.createPushEachInfo(pushVO);
		
		if(pushVO.getSeq_push() > 0) {
			return new ReturnClass(Constant.SUCCESS);
		} else {
			return new ReturnClass(Constant.FAILED);
		}
	}

}
