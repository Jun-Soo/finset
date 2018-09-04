package com.koscom.memo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.memo.dao.MemoMapper;
import com.koscom.memo.model.MemoVO;
import com.koscom.memo.service.MemoManager;

@Service("memoManager")
public class MemoManagerImpl implements MemoManager{

	private static final Logger logger = LoggerFactory.getLogger(MemoManagerImpl.class);
	
	@Autowired
	private MemoMapper memoMapper;
	
	@Override
	public int createMemo(MemoVO memoVO){
		logger.debug("createMemoParam : "+memoVO);
		return memoMapper.createMemo(memoVO);
	}
	
	@Override
	public List<MemoVO> listMemo(MemoVO memoVO){
		logger.debug("listMemoParam : "+memoVO);
		return memoMapper.listMemo(memoVO);
	}
	
	@Override
	public MemoVO getMemoDetail(MemoVO memoVO) {
		logger.debug("getMemoDetailParam : "+memoVO);
		return memoMapper.getMemoDetail(memoVO);
	}

	@Override
	public int updateMemoText(MemoVO memoVO) {
		logger.debug("updateMemoTextParam : "+memoVO);
		return memoMapper.updateMemoText(memoVO);
	}
	
	@Override
	public int updateMemoAlarm(MemoVO memoVO){
		logger.debug("updateMemoAlarParam : "+memoVO);
		return memoMapper.updateMemoAlarm(memoVO);
	}

	@Override
	public int delMemo(MemoVO memoVO) {
		logger.debug("delMemoTextParam : "+memoVO);
		return memoMapper.delMemo(memoVO);
	}
}
