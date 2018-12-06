package com.koscom.apply.dao;


import java.util.List;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.domain.ApplyInfo;

public interface ApplyMapper {

	/**
	 * 사전접수번호로 접수목록 조회
	 * @param no_prepare
	 * @return
	 */
	List<ApplyVO> listApplyByPrepare(String no_prepare);

	/**
	 * 접수신청서 저장
	 * @param applInfo
	 */
	void insertApply(ApplyInfo applyInfo);
	
	/**
	 * 접수신청서 조회
	 * @param applyVO
	 * @return
	 */
	ApplyVO getApply(ApplyVO applyVO);

	/**
	 * 접수신청서 상태 수정
	 * @param applyVO
	 * @return
	 */
	int modifyApplyDoc(ApplyVO applyVO);
	
	/**
	 * 접수 메모 수정
	 * @param applyVO
	 * @return
	 */
	int modifyApplyMemo(ApplyInfo applyInfo);
	
	/**
	 * 접수 삭제
	 * @param applyInfo
	 * @return
	 */
	int delApplyInfo(ApplyInfo applyInfo);
	
	/**
	 * 대출 진행 상황 조회
	 * @param applyForm
	 * @return ApplyVO 
	 */
	List<ApplyVO> listLoanProgSts(ApplyForm applyForm);
	
	/**
	 * 과거 진행 내역 조회
	 * @param applyForm
	 * @return List<ApplyVO>
	 */
	List<ApplyVO> listPastLoanHistory(ApplyForm applyForm);
	
	/**
	 * 과거 진행 내역 건수
	 * @param applyForm
	 * @return List<ApplyVO>
	 */
	int listPastLoanHistoryCount(ApplyForm applyForm);
}
