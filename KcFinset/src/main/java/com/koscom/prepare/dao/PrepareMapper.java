package com.koscom.prepare.dao;



import com.koscom.domain.PrepareInfo;
import com.koscom.finset.model.FinsetVO;
import com.koscom.prepare.model.PrepareVO;

public interface PrepareMapper {
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
	 * 사전접수 서류함 변경
	 * @param prepareInfo
	 * @return
	 */
	int modifyPrepareDoc(PrepareInfo prepareInfo);
	
	/**
	 * 접수건의 승인/거절 정보를 사전접수에 업데이트
	 * @param no_prepare
	 * @return
	 */
	int updatePrepareCnt(String no_prepare);
	
	/**
	 * 상담메모 사전접수에 업데이트
	 * @param prepareInfo
	 * @return
	 */
	int updateCounselMemo(PrepareInfo prepareInfo);
}
