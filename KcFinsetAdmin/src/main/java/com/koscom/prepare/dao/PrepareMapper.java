package com.koscom.prepare.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.domain.PrepareInfo;
import com.koscom.finset.model.FinsetVO;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.util.ReturnClass;

public interface PrepareMapper {

	/**
	 * 사전접수 리스트 조회
	 * @param prepareForm
	 * @return
	 */
	List<PrepareVO> listPrepareInfo(PrepareForm prepareForm);

	/**
	 * 사전접수 리스트 건수 조회
	 * @param prepareForm
	 * @return
	 */
	int listPrepareCount(PrepareForm prepareForm);
	
	/**
	 * 사전접수 리스트 조회(대리점)
	 * @param prepareForm
	 * @return
	 */
	List<PrepareVO> listPrepareInfoAgency(PrepareForm prepareForm);

	/**
	 * 사전접수 리스트 건수 조회(대리점)
	 * @param prepareForm
	 * @return
	 */
	int listPrepareCountAgency(PrepareForm prepareForm);

	/**
	 * 사전접수 신청서 조회
	 * @param no_prepare
	 * @return
	 */
	PrepareVO getPrepare(String no_prepare);

	/**
	 * 사전접수 신청서 등록
	 * @param prepareInfo
	 * @return
	 */
	void createPrepare(PrepareInfo prepareInfo);
	/**
	 * 사전접수 신청서 삭제
	 * @param prepareInfo
	 * @return
	 */
	void deletePrepare(FinsetVO finsetVO);

	/**
	 * 사전접수 신청서 등록(대리점)
	 * @param prepareInfo
	 * @return
	 */
	void createPrepareAgency(PrepareInfo prepareInfo);

	/**
	 * 중복확인 업데이트
	 * @param prepareVO
	 * @return
	 */
	int updateOverlapChk(PrepareInfo prepareInfo);

	/**
	 * 사전접수 신청서 수정
	 * @param prepareVO
	 * @return
	 */
	int modifyPrepare(PrepareInfo prepareInfo);
	
	/**
	 * 사전접수 신청서 수정(대리점)
	 * @param prepareVO
	 * @return
	 */
	int modifyPrepareAgency(PrepareVO prepareVO);

	/**
	 * 사전접수 목록(고객별)
	 * @param no_person
	 * @return
	 */
	List<PrepareVO> listPrepareByPerson(PrepareForm prepareForm);
	
	/**
	 * 사전접수 서류함 변경
	 * @param prepareInfo
	 * @return
	 */
	int modifyPrepareDoc(PrepareInfo prepareInfo);
	
	/**
	 * 사전접수 담당자 변경
	 * @param prepareInfo
	 * @return
	 */
	int modifyPrepareId(PrepareInfo prepareInfo);
	
	/**
	 * 고객번호 가져옴
	 * @param no_prepare
	 * @return
	 */
	String getNoPerson(String no_prepare);
	
	/**
	 * 서류함 카운트 조회
	 * @return
	 */
	List<HashMap<String, String>> getCntPrepareDoc(PrepareForm prepareForm);

	/**
	 * 상태서류함 카운트 조회
	 * @param prepareForm
	 * @return
	 */
	List<HashMap<String, String>> getCntPrepareClass(PrepareForm prepareForm);
	
	/**
	 * 접수건의 승인/거절 정보를 사전접수에 업데이트
	 * @param no_prepare
	 * @return
	 */
	int updatePrepareCnt(String no_prepare);

	/**
	 * 당일 휴대폰 사전접수건수 조회
	 * @param hp
	 * @return
	 */
	int getCntPrepareTodayByHP(String hp);

	/**
	 * 상담메모 사전접수에 업데이트
	 * @param prepareInfo
	 * @return
	 */
	int updateCounselMemo(PrepareInfo prepareInfo);
	
	/**
	 * 사전접수 목록 엑셀 다운로드
	 * @param prepareForm
	 * @return
	 */
	List<HashMap<String, Object>> listPrepareInfo_excel(PrepareForm prepareForm);
	
	/**
	 * SMS 전송시 dt_lst, id_lst 업데이트
	 * @param smsVo
	 * @return
	 */
	int updatePrepareLst(PrepareVO prepareVO);
	
	/**
	 * 사전접수 번호 조회
	 * @param alarmForm
	 * @return
	 */
	String getNoPrepare(PrepareForm prepareForm);
	
	/**
	 * 제휴사 사전접수 고유번호 중복조회
	 * @param no_agency
	 * @return
	 */
	PrepareVO getPrepareExist(PrepareVO prepareVO);
	
	/**
	 * 서류함 카운트 조회
	 * @return
	 */
	HashMap<String, String> getCntPrepare(PrepareVO prepareVO);
	
	/**
	 * 사전접수 신청서 확인처리
	 * @param no_person
	 */
	int updatePrepareChk(PrepareVO prepareVO);
	
	/**
	 * 접수경로 수정(prepare)
	 * @param prepareVO
	 * @return
	 */
	int procPreparePath(PrepareVO prepareVO);
}
