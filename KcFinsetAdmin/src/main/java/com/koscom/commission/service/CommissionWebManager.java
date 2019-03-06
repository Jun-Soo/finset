package com.koscom.commission.service;

import java.util.List;

import com.koscom.commission.model.CommissionForm;
import com.koscom.commission.model.CommissionVO;
import com.koscom.util.ReturnClass;

public interface CommissionWebManager {
	/**
	 * 수수료율 정보 조회
	 * @param no_apply
	 * @return
	 */
	CommissionVO getCommissionInfo(String no_apply);
	
	/**
	 * 수수료율 조회
	 * @param CommissionVO
	 * @return
	 */
	List<CommissionVO> listCommissionInfo(CommissionForm commissionForm);
	
	/**
	 * 수수료율 카운트 조회
	 * @param commissionForm
	 * @return
	 */
	int listCommissionCount(CommissionForm commissionForm);
	
	/**
	 * 수수료율 추가,수정
	 * @param commissionVO
	 */
	ReturnClass procCommissionDetails(CommissionVO commissionVO);
	
	/**
	 * 수수료율 정보 삭제
	 * @param commissionVO
	 * @return
	 */
	ReturnClass delCommissionDetails(CommissionVO commissionVO);

}
