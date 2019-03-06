package com.koscom.env.service;

import java.util.HashMap;

import com.koscom.env.model.BusinessDayForm;
import com.koscom.env.model.BusinessDayVO;
import com.koscom.util.ReturnClass;

public interface BusinessWebManager {
	
	/**
	 * 휴일여부 체크
	 * @param ymd
	 * @return
	 */
	public boolean isHoliDay(String ymd) ;
	
	/**
	 * 입력받은 일자가 휴일이라면 다음에 돌아오는 평일을 찾아 리턴한다.
	 * @param ymd
	 * @return
	 */
	public String nextWeekDay(String ymd);
	
	/**
	 * 영업일 cnt 이후 일자 반환 
	 * @param ymd_basic
	 * @param cnt
	 * @return
	 */
	public String nextBusinessDay(String ymd_basic, int cnt);

	/**
	 * 영업일 목록 조회(캐시)
	 * @return
	 */
	public HashMap<String,String> listCacheBusinessDay();
	

	/**
	 * 영업일 추가,삭제
	 * @param businessDayForm
	 * @return
	 */
	public ReturnClass procBusinessDayYnHoliday(BusinessDayForm businessDayForm);

	/**
	 * 영업일 캐시 초기화
	 */
	public void clearCacheBusinessDay();

	/**
	 * 금융사 영업일 목록 조회
	 * @return
	 */
	public HashMap<String,String> listCacheFcBusinessDay(BusinessDayForm businessDayForm);
	
	/**
	 * 금융사 영업일 등록, 수정
	 * @param businessDayForm
	 * @return
	 */
	public ReturnClass procFcBusinessDayYnHoliday(BusinessDayForm businessDayForm);

	/**
	 * 금융사 영업일 삭제
	 * @param BusinessDayForm
	 * @return
	 */
	public ReturnClass deleteFcBusinessDay(BusinessDayForm businessDayForm);

	/**
	 *
	 * 금융사 영업일 상세정보 조회
	 * @param BusinessDay
	 * @return
	 */
	BusinessDayVO getFcBusinessDayInfo(String cd_fc, String ymd);
}
