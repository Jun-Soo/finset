package com.koscom.batch.kcb.mapper;

import java.util.List;

import com.koscom.batch.kcb.domain.KcbInfoVO;
import com.koscom.batch.kcb.domain.KcbVO;

public interface KcbMapper {

	/**
	 * KCB 비금융정보 대상자 목록 조회
	 * @return List<KcbVO>
	 */
	List<KcbVO> listKcbSendPerson();
	List<KcbVO> listKcbSendInfo();

	/**
	 * 소득금액증명정보 내역 조회
	 * @param kcbVO 
	 * @return List<KcbInfoVO>
	 */
	List<KcbInfoVO> listScrIncome(KcbVO kcbVO);
	
	/**
	 * 건강보험납부정보 내역 조회
	 * @return List<KcbInfoVO>
	 */
	List<KcbInfoVO> listScrHealthPayment(KcbVO kcbVO);
	
	/**
	 * KCB 상태 update
	 * @return kcbVO
	 */
	void updateReqKcbNonFi(KcbVO kcbVO);
	
	/**
	 * 국민연금납부정보 내역 조회
	 * @return List<KcbInfoVO>
	 */
	List<KcbInfoVO> listScrPensionPayment(KcbVO kcbVO);
	
	
}
