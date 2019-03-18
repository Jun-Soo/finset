package com.koscom.commission.dao;

import java.util.List;

import com.koscom.commission.model.CommissionForm;
import com.koscom.commission.model.CommissionVO;
import com.koscom.domain.CommissionInfo;

public interface CommissionMapper {

	/**
	 * 수수료율 조회
	 * @param no_apply
	 * @return
	 */
	CommissionVO getCommissionInfo(String no_apply);

	/**
	 * 수수료율 상품목록 조회
	 * @param commissionVO
	 * @return
	 */
	List<CommissionVO> listCommissionInfo(CommissionForm commissionForm);

	/**
	 * 수수료율 조회(검색)
	 * @param commissionForm
	 * @return
	 */
	List<CommissionVO> listCommission(CommissionForm commissionForm);
	
	/**
	 * 수수료율 카운트 조회
	 * @param commissionForm
	 * @return
	 */
	int listCommissionCount(CommissionForm commissionForm);
	
	/**
	 * 수수료율 추가, 수정
	 * @param commissionVO
	 */
	int procCommissionDetails(CommissionVO commissionVO);

	/**
	 * 수수료율 정보 삭제
	 * @param CommissionVO
	 * @return
	 */
	int delCommissionDetails(CommissionVO commissionVO);
	
}