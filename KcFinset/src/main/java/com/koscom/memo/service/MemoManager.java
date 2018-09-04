package com.koscom.memo.service;

import java.util.List;

import com.koscom.memo.model.MemoVO;

public interface MemoManager {
	
	/**
	 * 메모생성
	 * @param memoVO
	 * @return
	 */
	int createMemo(MemoVO memoVO);
	
	/**
	 * 메모 리스트 조회
	 * @param memoVO
	 * @return
	 */
	List<MemoVO> listMemo(MemoVO memoVO);
	
	/**
	 * 메모 정보 조회
	 * @param memoVO
	 * @return
	 */
	MemoVO getMemoDetail(MemoVO memoVO);
	
	/**
	 * 텍스트 내용 update
	 * @param memoVO
	 * @return
	 */
	int updateMemoText(MemoVO memoVO);
	
	/**
	 * 메모 알람 update
	 * @param memoVO
	 * @return
	 */
	int updateMemoAlarm(MemoVO memoVO);
	
	/**
	 * 메모 삭제
	 * @param memoVO
	 * @return
	 */
	int delMemo(MemoVO memoVO);
}
