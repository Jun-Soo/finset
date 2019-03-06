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
	 * 접수 리스트 조회
	 * @param applyForm
	 * @return
	 */
	List<ApplyVO> listApplyInfo(ApplyForm applyForm);
	
	/**
	 * 접수 리스트 건수 조회
	 * @param applyForm
	 * @return
	 */
	HashMap<String, String> listApplyCount(ApplyForm applyForm);
	
	/**
	 * 접수 리스트 조회(대리점)
	 * @param applyForm
	 * @return
	 */
	List<ApplyVO> listApplyInfoAgency(ApplyForm applyForm);
	
	/**
	 * 접수 리스트 건수 조회(대리점)
	 * @param applyForm
	 * @return
	 */
	int listApplyCountAgency(ApplyForm applyForm);

	/**
	 * 사전접수번호로 접수목록 조회
	 * @param no_prepare
	 * @return
	 */
	List<ApplyVO> listApplyByPrepare(String no_prepare);
	
	/**
	 * 사전접수번호로 접수목록 조회(대리점)
	 * @param no_prepare
	 * @return
	 */
	List<ApplyVO> listApplyByPrepareAgency(String no_prepare);

	/**
	 * 접수신청서 생성
	 * @param applyVO
	 * @return
	 */
	ReturnClass createApply(ApplyVO applyVO) throws IOException, FinsetException, FinsetMessageException;
	
	/**
	 * 접수신청서 생성(대리점)
	 * @param applyVO
	 * @return
	 */
	ReturnClass createApplyAgency(ApplyVO applyVO) throws FinsetException;

	/**
	 * 접수신청서 전송
	 * @param applyVO
	 * @return
	 */
	ReturnClass sendApply(ApplyVO applyVO) throws IOException, FinsetMessageException, FinsetException;
	
	/**
	 * 접수정보 조회
	 * @param applyVO
	 * @return
	 */
	ApplyVO getApply(ApplyVO applyVO);
	
	/**
	 * 신청정보 조회
	 * @param no_apply
	 * @return
	 */
	ApplyVO getApplyInfo(String no_apply);
	
	/**
	 * 서류함 카운트 조회
	 * @return
	 */
	List<HashMap<String, String>> getCntApplyDoc(ApplyForm applyForm);

	/**
	 * 접수신청서 상태 수정
	 * @param applyVO
	 * @return
	 */
	ReturnClass modifyApplyDoc(ApplyVO applyVO) throws ParseException, FinsetException, IOException;
	
	/**
	 * 금융사 분리 자동접수
	 * @param no_apply
	 * @param listAutoApply
	 * @return
	 */
	ReturnClass procAutoApply(String no_apply, HashMap<String,HashMap<String,String>> listAutoApply) throws IOException, FinsetException, FinsetMessageException;
	
	/**
	 * 매체사 접수 리턴
	 * @param prepareForm
	 */
	void agencyReturn(PrepareForm prepareForm) throws UnsupportedEncodingException, ParseException, IOException;

	/**
	 * 접수 메모 수정
	 * @param applyVO
	 * @return
	 */
	ReturnClass modifyApplyMemo(ApplyVO applyVO);
	
	/**
	 * 적법수집 수정
	 * @param applyVO
	 * @return
	 */
	ReturnClass modifyApplyLegal(ApplyVO applyVO);
	
	/**
	 * 접수 삭제
	 * @param applyVO
	 * @return
	 */
	ReturnClass delApplyInfo(ApplyVO applyVO) throws ParseException, IOException;
	
	/**
	 * 사전접수 번호에 해당하는 접수번호 모두 가져옴
	 * @param no_prepare
	 * @return
	 */
	List<String> getNoApply(String no_prepare);

	/**
	 * 접수리스트 엑셀출력
	 * @param applyForm
	 * @return
	 */
	List<HashMap<String, Object>> listApplyInfo_excel(ApplyForm applyForm);
	
	/**
	 * 첨부파일 및 파기대상자조회
	 * @param destroyTerm
	 * @return
	 */
	List<ApplyVO> listApplyDestory(int destroyTerm);
	
	/**
	 * 접수 서류함 카운트
	 * @param id_prepare
	 * @param cd_apply_doc_box
	 */
	void sendCntApplyDoc(String id_prepare, String cd_apply_doc_box);
	
	/**
	 * 접수신청서 확인 처리
	 * @param applyVO
	 * @return
	 */
	ReturnClass updateApplyChk(ApplyVO applyVO)  throws IOException, FinsetException, FinsetMessageException;
	
	/**
	 * 월별 접수건수 조회
	 * @param applyForm
	 * @return
	 */
	List<HashMap<String, String>> getCntMonthApply(ApplyForm applyForm);
	
	/**
	 * 일별 접수건수 조회
	 * @param applyForm
	 * @return
	 */
	List<HashMap<String, String>> getCntDayApply(ApplyForm applyForm);
	
	/**
	 * 대리점 접수현황 조회
	 * @param applyForm
	 * @return
	 */
	HashMap<String, String> getCntSummaryApplyByAgency(ApplyForm applyForm);
	
	/**
	 * 접수경로 수정(apply)
	 * @param applyVO
	 * @return
	 */
	ReturnClass procApplyPath(ApplyVO applyVO);
	
	/**
	 * 당일 중복 상품 접수건수 조회
	 * @param applyVO
	 * @return
	 */
	int getCntApplyTodayByInfo(ApplyVO applyVO);
	
	/**
	 * 접수요약정보 조회
	 * @param applyForm
	 * @return
	 */
	HashMap<String, String> getCntApplySummary(ApplyForm applyForm);

	ReturnClass createApplyForFinset(ApplyVO applyVO);

	List<ApplyVO> listPastInfo(ApplyForm applyForm);
	
	List<ApplyVO> listPastInfoPg(ApplyForm applyForm);

	HashMap<String, String> listPastCount(ApplyForm applyForm);
	
	HashMap<String, String> listPastPgCount(ApplyForm applyForm);

	List<ApplyVO> listStatusInfo(ApplyForm applyForm);

	HashMap<String, String> listStatusCount(ApplyForm applyForm);

	List<ApplyVO> listStatusInfoPg(ApplyForm applyForm);
	
	/**
	 * 조회이력 조회
	 * @param applyForm
	 * @return
	 */
	List<ApplyVO> listLookupInfoPg(ApplyForm applyForm);
	
	/**
	 * 최신 1건 대출 건 조회
	 * @param applyForm
	 * @return ApplyVO
	 */
	public ApplyVO getLoanProgSts(ApplyForm applyForm);
	
	/**
	 * 대출진행상황 조회
	 * @param applyForm
	 * @return ApplyVO
	 */
	public List<ApplyVO> listLoanProgSts(ApplyForm applyForm);
	
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
	
	/**
	 * 상품조회 이력 조회
	 * @param applyForm
	 * @return List<ApplyVO>
	 */
	List<ApplyResultVO> ListApplyResult(ApplyResultForm applyResultForm);
	
	/**
	 * 상품조회 이력 건수
	 * @param applyForm
	 * @return List<ApplyVO>
	 */
	int ListApplyResultCount(ApplyResultForm applyResultForm);
}
