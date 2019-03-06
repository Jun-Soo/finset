package com.koscom.apply.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyResultForm;
import com.koscom.apply.model.ApplyResultVO;
import com.koscom.apply.model.ApplyVO;
import com.koscom.domain.ApplyInfo;

public interface ApplyMapper {

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
	 * 사전접수번호로 접수목록 조회
	 * @param no_prepare
	 * @return
	 */
	List<ApplyVO> listApplyByPrepareAgency(String no_prepare);

	/**
	 * 접수신청서 저장
	 * @param applInfo
	 */
	void insertApply(ApplyInfo applyInfo);

	/**
	 * 접수신청서 저장(대리점)
	 * @param applInfo
	 */
	void insertApplyAgency(ApplyInfo applyInfo);
	
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
	 * 서류함 카운트 조회
	 * @return
	 */
	List<HashMap<String, String>> getCntApplyDoc(ApplyForm applyForm);

	/**
	 * 접수 메모 수정
	 * @param applyVO
	 * @return
	 */
	int modifyApplyMemo(ApplyInfo applyInfo);
	
	/**
	 * 적법수집 수정
	 * @param applyVO
	 * @return
	 */
	int modifyApplyLegal(ApplyInfo applyInfo);
	
	/**
	 * 접수 삭제
	 * @param applyInfo
	 * @return
	 */
	int delApplyInfo(ApplyInfo applyInfo);
	
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
	 * @param destroyCycle
	 * @return
	 */
	List<ApplyVO> listApplyDestory(int destroyTerm);
	
	/**
	 * 서류함 카운트 조회
	 * @return
	 */
	HashMap<String, String> getCntApply(ApplyVO ApplyVO);

	/**
	 * 접수 신청서 확인처리
	 * @param applyVO
	 * @return
	 */
	int updateApplyChk(ApplyVO applyVO);
	
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
	 * 대리점 종합현황 조회
	 * @param applyForm
	 * @return
	 */
	HashMap<String, String> getCntSummaryApplyByAgency(ApplyForm applyForm);
	
	/**
	 * 접수경로 수정(apply)
	 * @param applyVO
	 * @return
	 */
	int procApplyPath(ApplyVO applyVO);
	
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
	ApplyVO getLoanProgSts(ApplyForm applyForm);
	
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
	
	/**
	 * 상품조회 이력 조회
	 * @param ApplyResultForm
	 * @return List<ApplyResultVO>
	 */
	List<ApplyResultVO> ListApplyResult(ApplyResultForm applyResultForm);
	
	/**
	 * 상품조회 이력 건수
	 * @param ApplyResultForm
	 * @return int
	 */
	int ListApplyResultCount(ApplyResultForm applyResultForm);
}
