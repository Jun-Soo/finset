/*
 * Mapper를 호출하기 위한 인터페이스
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 * 수정: 20180619 김휘경 - 메모 알람 시간 수정 추가
 */

package com.koscom.memo.service;

import java.util.List;

import com.koscom.memo.model.MemoVO;

public interface MemoManager {
	int createMemo(MemoVO memoVO); //메모생성-알람은 update 형식, 이미지는 이후에 추가할 예정
	List<MemoVO> listMemo(MemoVO memoVO); //메모 리스트 가져오기
	MemoVO getMemoDetail(MemoVO memoVO); //seq_memo_info 에 따라 하나의 메모 정보를 가져옴(조건: seq_memo_info)
	int updateMemoText(MemoVO memoVO); //텍스트 내용을 update
	int updateMemoAlarm(MemoVO memoVO); //메모 알람 update
	int delMemo(MemoVO memoVO); //메모 삭제(조건: no_person, seq_memo_info)
}
