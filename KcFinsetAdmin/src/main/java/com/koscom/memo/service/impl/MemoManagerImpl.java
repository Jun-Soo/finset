/*
 * Mapper를 호출하는 클래스
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 */

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
	private MemoMapper memoMapper; //xml을 호출하는 Mapper 인터페이스 의존성 주입
	
	@Override
	public int createMemo(MemoVO memoVO){ //메모생성-알람은 update 형식, 이미지는 이후에 추가할 예정
		logger.debug("createMemoParam : "+memoVO);
		return memoMapper.createMemo(memoVO);
	}
	
	@Override
	public List<MemoVO> listMemo(MemoVO memoVO){ //메모 리스트
		logger.debug("listMemoParam : "+memoVO);
		return memoMapper.listMemo(memoVO);
	}
	
	@Override
	public MemoVO getMemoDetail(MemoVO memoVO) { //seq_memo_info 에 따라 하나의 메모 정보를 가져옴(조건: seq_memo_info)
		logger.debug("getMemoDetailParam : "+memoVO);
		return memoMapper.getMemoDetail(memoVO);
	}

	@Override
	public int updateMemoText(MemoVO memoVO) { //텍스트 내용을 update
		logger.debug("updateMemoTextParam : "+memoVO);
		return memoMapper.updateMemoText(memoVO);
	}
	
	@Override
	public int updateMemoAlarm(MemoVO memoVO){ //메모 알람 update
		logger.debug("updateMemoAlarParam : "+memoVO);
		return memoMapper.updateMemoAlarm(memoVO);
	}

	@Override
	public int delMemo(MemoVO memoVO) { //메모 삭제(조건: no_person, seq_memo_info)
		logger.debug("delMemoTextParam : "+memoVO);
		return memoMapper.delMemo(memoVO);
	}
}
