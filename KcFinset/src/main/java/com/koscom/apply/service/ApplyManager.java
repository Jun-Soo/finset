package com.koscom.apply.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyResultForm;
import com.koscom.apply.model.ApplyResultVO;
import com.koscom.apply.model.ApplyVO;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;
import com.koscom.util.ReturnClass;

import java.text.ParseException;

public interface ApplyManager {
	/**
	 * 매체사 접수 리턴
	 * @param prepareForm
	 */
	void agencyReturn(PrepareForm prepareForm) throws UnsupportedEncodingException, ParseException, IOException;
	
	/**
	 * 접수정보 조회
	 * @param applyVO
	 * @return
	 */
	ApplyVO getApply(ApplyVO applyVO);
	
	/**
	 * 접수 메모 수정
	 * @param applyVO
	 * @return
	 */
	ReturnClass modifyApplyMemo(ApplyVO applyVO);

	/**
	 * 접수신청서 상태 수정
	 * @param applyVO
	 * @return
	 */
	ReturnClass modifyApplyDoc(ApplyVO applyVO) throws ParseException, FinsetException, IOException;

	ReturnClass createApplyForFinset(ApplyVO applyVO);

	/**
	 * 대출진행상황 조회
	 * @param applyForm
	 * @return ApplyVO
	 */
	public List<ApplyVO> listLoanProgSts(ApplyForm applyForm);
	
	/**
	 * 사전접수번호로 접수목록 조회
	 * @param no_prepare
	 * @return
	 */
	List<ApplyVO> listApplyByPrepare(String no_prepare);
	
	/**
	 * 접수 삭제
	 * @param applyVO
	 * @return
	 */
	ReturnClass delApplyInfo(ApplyVO applyVO) throws ParseException, IOException;
	
	/**
	 * 과거 진행 내역 조회
	 * @param applyForm
	 * @return ApplyVO
	 */
	public List<ApplyVO> listPastLoanHistory(ApplyForm applyForm);
	
	/**
	 * 과거 진행 내역 건수
	 * @param applyForm
	 * @return int
	 */
	public int listPastLoanHistoryCount(ApplyForm applyForm);
}
