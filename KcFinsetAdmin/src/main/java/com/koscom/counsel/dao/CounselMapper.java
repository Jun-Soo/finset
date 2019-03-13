package com.koscom.counsel.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.koscom.counsel.model.CounselForm;
import com.koscom.counsel.model.CounselVO;

@Repository
public interface CounselMapper {

	/**
	 * 상담접수및현황 리스트 조회
	 * @param counselForm
	 * @return
	 */
	List<CounselVO> listCounselInfoPg(CounselForm counselForm);

	/**
	 *  상담접수및현황 리스트 건수 조회
	 * @param counselForm
	 * @return
	 */
	int listCounselCount(CounselForm counselForm);
	
	/**
	 * 상담정보조회
	 * @param counselVO
	 * @return
	 */
	CounselVO getCounselInfo(CounselVO counselVO);

	/**
	 * 신용정보 조회
	 * @param counselVO
	 * @return List<CounselVO>
	 */
	List<CounselVO> getCreditList(CounselVO counselVO);

	/**
	 * 부채정보 조회
	 * @param counselVO
	 * @return List<CounselVO>
	 */
	List<CounselVO> getDebtList(CounselVO counselVO);

	/**
	 * 상담이력 조회
	 * @param counselVO
	 * @return List<CounselVO>
	 */
	List<CounselVO> getCounselHist(CounselVO counselVO);
	
	/**
	 * 상담상태 변경
	 * 상담준비중(1) -> 상담처리중(2)
	 * @param counselVO
	 * @return
	 */
	int saveCounselStatus(CounselVO counselVO);
	
	/**
	 *  상담 내용 입력
	 * @param counselVO
	 * @return
	 */
	int saveCounselContents(CounselVO counselVO);
	
	/**
	 * 상담메모 추가, 수정
	 * 
	 * @param friendVO
	 * @return
	 */
	int procCounselInfo(CounselVO counselVO);

	/**
	 * 해당 파라미터로 조회된 모든 상담정보를 반환합니다.
	 * @param no_person
	 * @return List<CounselInfo>
	 */
	List<CounselVO> counselInfoList(CounselForm counselForm);
}
