package com.koscom.env.dao;

import java.util.List;

import com.koscom.domain.BusinessDay;
import com.koscom.env.model.BusinessDayForm;
import com.koscom.env.model.BusinessDayVO;

public interface BusinessMapper {
	
	/**
	 * 영업일 목록 조회 
	 * @return
	 */
	List<BusinessDayVO> listBusinessDay();

	/**
	 *
	 * 영업일 단건 조회
	 * @param ymd
	 * @return
	 */
	BusinessDayVO getBusinessDay(String ymd);
	
	/**
	 * 휴일 추가 
	 * @param businessDayForm
	 * @return
	 */
	int createBusinessDay(BusinessDayForm businessDayForm);

	/**
	 * 휴일 삭제 
	 * @param businessDayForm
	 * @return
	 */
	int deleteBusinessDay(BusinessDayForm businessDayForm);
	
	
	/**
	 * 금융사 영업일 목록 조회 
	 * @return
	 */
	List<BusinessDayVO> listFcBusinessDay(BusinessDayForm businessDayForm);
	
	/**
	 *
	 * 금융사영업일 단건 조회
	 * @param ymd
	 * @return
	 */
	BusinessDayVO getFcBusinessDay(String ymd);
	
	/**
	 *
	 *  금융사 영업일 상세정보 조회
	 * @param BusinessDay
	 * @return
	 */
	BusinessDayVO getFcBusinessDayInfo(BusinessDay businessDay);
	
	
	/**
	 * 금융사 휴일 추가 
	 * @param businessDayForm
	 * @return
	 */
	int createFcBusinessDay(BusinessDayForm businessDayForm);

	/**
	 * 금융사 휴일 삭제 
	 * @param businessDayForm
	 * @return
	 */
	int deleteFcBusinessDay(BusinessDayForm businessDayForm);

}
