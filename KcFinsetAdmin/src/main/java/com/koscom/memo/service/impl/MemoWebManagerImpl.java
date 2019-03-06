/*
 * WebManager를 상속받아 실제로 동작하는 클래스
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 * 수정: 20180619 김휘경 - 메모 알람 시간 수정 추가
 */

package com.koscom.memo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.memo.model.MemoVO;
import com.koscom.memo.service.MemoManager;
import com.koscom.memo.service.MemoWebManager;

@Service("memoWebManager")
public class MemoWebManagerImpl implements MemoWebManager{
	
	@Autowired
	MemoManager memoManager; //메모 Mapper와 연결되어 있는 Manager 의존성 주입
	
	@Override
	public int createMemo(MemoVO memoVO){ //메모생성-알람은 update 형식, 이미지는 이후에 추가할 예정
		return memoManager.createMemo(memoVO);
	}
	
	@Override
	public List<MemoVO> listMemo(MemoVO memoVO){ //메모 리스트 가져오기
		return memoManager.listMemo(memoVO);
	}
	
	@Override
	public MemoVO getMemoDetail(MemoVO memoVO) { //seq_memo_info 에 따라 하나의 메모 정보를 가져옴(조건: seq_memo_info)
		return memoManager.getMemoDetail(memoVO);
	}
	
	@Override
	public int updateMemoText(MemoVO memoVO) { //텍스트 내용을 update
		return memoManager.updateMemoText(memoVO);
	}
	
	@Override
	public int updateMemoAlarm(MemoVO memoVO){ //메모 알람 update
		return memoManager.updateMemoAlarm(memoVO);
	}

	@Override
	public int delMemo(MemoVO memoVO) {	//메모 삭제(조건: no_person, seq_memo_info)
		return memoManager.delMemo(memoVO);
	}
}
