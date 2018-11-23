package com.koscom.prepare.service;


import java.io.IOException;
import java.text.ParseException;



import com.koscom.finset.model.FinsetVO;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.util.ReturnClass;

public interface PrepareManager {

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
	/**
	 * 사전접수 신청서 삭제
	 * @param finsetVO
	 * @return
	 */
	ReturnClass deletePrepare(FinsetVO finsetVO);
	
	/**
	 * 사전접수 서류함 변경
	 * @param prepareVo
	 * @return
	 */
	ReturnClass modifyPrepareDoc(PrepareVO prepareVo) throws  ParseException, IOException;
	
	/**
	 * 접수건의 승인/거절 정보를 사전접수에 업데이트
	 * @param no_prepare
	 * @return
	 */
	ReturnClass updatePrepareCnt(String no_prepare);
	
	/**
	 * 상담메모 사전접수에 업데이트
	 * @param prepareVO
	 * @return
	 */
	ReturnClass updateCounselMemo(PrepareVO prepareVO);
}
