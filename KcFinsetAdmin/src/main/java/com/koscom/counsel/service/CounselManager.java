package com.koscom.counsel.service;

import java.util.List;

import com.koscom.counsel.model.CounselForm;
import com.koscom.counsel.model.CounselVO;
import com.koscom.util.ReturnClass;

public interface CounselManager {

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
	 * 하나의 상담메모를 반환합니다.
	 * @param counselVO
	 * @return
	 */
	CounselVO getCounselInfo(CounselVO counselVO);
	
	/**
	 * 신용정보 조회
	 * @param CounselVO
	 * @return
	 */
	List<CounselVO> getCreditList(CounselVO counselVO);
	
	/**
	 * 부채정보 조회
	 * @param CounselVO
	 * @return
	 */
	List<CounselVO> getDebtList(CounselVO counselVO);
	
	/**
	 * 상담이력 조회
	 * @param CounselVO
	 * @return
	 */
	List<CounselVO> getCounselHist(CounselVO counselVO);
	
	/**
	 * 상담상태 변경 
	 * @param counselVO
	 * @return
	 */
	ReturnClass saveCounselStatus(CounselVO counselVO);
	
	/**
	 * 상담 내용 입력
	 * @param counselVO
	 * @return
	 */
	ReturnClass saveCounselContents(CounselVO counselVO);
	
	/**
	 * 상담메모 추가, 수정
	 * 
	 * @param friendVO
	 * @return
	 */
	ReturnClass procCounselInfo(CounselVO counselVO);	

	/**
	 * 해당 파라미터로 조회된 모든 상담정보를 반환합니다.
	 * @param no_person
	 * @return List<CounselInfo>
	 */
	List<CounselVO> counselInfoList(CounselForm counselForm);
}
