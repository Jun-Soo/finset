package com.koscom.prepare.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;
import com.koscom.util.ReturnClass;

public interface PrepareManager {

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
	 * 사전접수 신청서 간편등록
	 * @param prepareVO
	 * @return
	 */
	ReturnClass createPrepareSummary(PrepareVO prepareVO);
//	/**
//	 * 사전접수 신청서 삭제
//	 * @param finsetVO
//	 * @return
//	 */
//	ReturnClass deletePrepare(FinsetVO finsetVO);

	/**
	 * 사전접수 신청서 등록(대리점)
	 * @param prepareVO
	 * @return
	 */
	ReturnClass createPrepareAgency(PrepareVO prepareVO);

	/**
	 * 중복확인 업데이트
	 * @param prepareVO
	 * @return
	 */
	ReturnClass updateOverlapChk(PrepareVO prepareVO);

	/**
	 * 사전접수 신청서 수정
	 * @param prepareVO
	 * @return
	 */
	ReturnClass modifyPrepare(PrepareVO prepareVO) throws  ParseException, IOException;
	
	/**
	 * 사전접수 신청서 수정(대리점)
	 * @param prepareVO
	 * @return
	 */
	ReturnClass modifyPrepareAgency(PrepareVO prepareVO);

	/**
	 * 사전접수 목록(고객별)
	 * @param prepareForm
	 * @return
	 */
	List<PrepareVO> listPrepareByPerson(PrepareForm prepareForm);

	/**
	 * 사전접수 서류함 변경
	 * @param prepareVo
	 * @return
	 */
	ReturnClass modifyPrepareDoc(PrepareVO prepareVo) throws  ParseException, IOException;
	
	/**
	 * 사전접수 담당자 변경
	 * @param prepareVo
	 * @return
	 */
	ReturnClass modifyPrepareId(PrepareVO prepareVo);
	
	/**
	 * 사전접수 일괄수정(서류함, 담당자)
	 * @param prepareVo
	 * @return
	 */
	ReturnClass modifyPrepareBatch(PrepareVO prepareVo) throws ParseException, IOException;
	
	/**
	 * 접수번호로 고객번호 가져옴
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
	ReturnClass updatePrepareCnt(String no_prepare);

	/**
	 * 당일 휴대폰 사전접수건수 조회
	 * @param hp
	 * @return
	 */
	int getCntPrepareTodayByHP(String hp);
	
	/**
	 * 상담메모 사전접수에 업데이트
	 * @param prepareVO
	 * @return
	 */
	ReturnClass updateCounselMemo(PrepareVO prepareVO);
	
	/**
	 * 사전접수 목록 엑셀 다운로드
	 * @param prepareForm
	 * @return
	 */
	List<HashMap<String, Object>> listPrepareInfo_excel(PrepareForm prepareForm);
	
	/**
	 * SMS 전송시 dt_lst, id_lst 업데이트
	 * @param prepareVO
	 * @return
	 */
	ReturnClass updatePrepareLst(PrepareVO prepareVO);
	
	/**
	 * 고객번호로 최근 사전접수 번호 조회
	 * @param prepareForm
	 * @return
	 */
	String getNoPrepare(PrepareForm prepareForm);
	
	/**
	 * 제휴사 사전접수 고유번호 중복조회
	 * @param prepareVO
	 * @return
	 */
	PrepareVO getPrepareExist(PrepareVO prepareVO);
	
	/**
	 * 사전접수 신청서 확인처리
	 * @param prepareVO
	 * @return
	 */
	ReturnClass updatePrepareChk(PrepareVO prepareVO)  throws ParseException, FinsetException, FinsetMessageException, IOException;
	
	/**
	 * 접수경로 수정(prepare)
	 * @param prepareVO
	 * @return
	 */
	ReturnClass procPreparePath(PrepareVO prepareVO);
	
}
