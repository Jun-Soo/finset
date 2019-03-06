package com.koscom.env.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.env.dao.BusinessMapper;
import com.koscom.env.model.BusinessDayForm;
import com.koscom.env.model.BusinessDayVO;
import com.koscom.env.service.BusinessManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;

@Service("businessManager")
public class BusinessManagerImpl implements BusinessManager {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessManagerImpl.class);
	
	private BusinessManager businessManager;
	
	@Autowired
	private BusinessMapper businessMapper;
	
	/**
	 * 휴일여부 체크
	 * @param ymd
	 * @return
	 */
	public boolean isHoliDay(String ymd) {
		if (StringUtil.isEmpty(ymd)) {
			return false;
		}
		
		// Cache 정보를 가져와서 쓰기 위함
		businessManager = (BusinessManager) SpringApplicationContext.getBean("businessManager");
		HashMap<String,String> ymd_map = businessManager.listCacheBusinessDay();
		
		if (ymd_map.containsKey(ymd)) {
			return "Y".equals(ymd_map.get(ymd)) ? true : false;					
		}
		
		String day = DateUtil.getDayOfWeek(ymd);
		
		if("토".equals(day) || "일".equals(day))
			return true;
		
		return false;
	}
	
	@Override
	public String nextWeekDay(String pYmd) {
		String ymd = pYmd;
		while(isHoliDay(ymd))
		{
			ymd = DateUtil.addDays(ymd, 1);
		}
		
		return ymd;
	}


	@Override
	public String nextBusinessDay(String pYmd_basic, int pCnt) {
        int cnt = pCnt;
		if (cnt <= 0) return pYmd_basic;
		String ymd_basic = pYmd_basic;
		if(isHoliDay(ymd_basic)) cnt = cnt - 1;
		
		for (int i = 0; i < cnt; i++) {
			ymd_basic = nextWeekDay(ymd_basic);
			ymd_basic = nextWeekDay(DateUtil.addDays(ymd_basic, 1));
		}
		
		return nextWeekDay(ymd_basic);
	}


	@Override
	@Cacheable("CacheBusiness")
	public HashMap<String,String> listCacheBusinessDay() {
		
		HashMap<String,String> ymd_map = new HashMap<String, String>();
		
		List<BusinessDayVO> list = businessMapper.listBusinessDay();
		for (BusinessDayVO businessDay : list)
		{
			ymd_map.put(businessDay.getYmd(), businessDay.getYn_holiday());
		}
		
		return ymd_map;
	}
	
	@Override
	public HashMap<String,String> listCacheFcBusinessDay(BusinessDayForm businessDayForm) {
		
		HashMap<String,String> ymd_map = new HashMap<String, String>();
		
		List<BusinessDayVO> list = businessMapper.listFcBusinessDay(businessDayForm);
		for (BusinessDayVO businessDay : list)
		{
			ymd_map.put(businessDay.getYmd(), businessDay.getYn_holiday());
		}
		
		return ymd_map;
	}

	

	@Override
	public ReturnClass procBusinessDayYnHoliday(BusinessDayForm businessDayForm) {
		
		String[] arr_ymd = businessDayForm.getArr_ymd();
		
		int nCntIn = 0;
		int nCntDel = 0;
		for (String ymd : arr_ymd) {
			if(StringUtil.isEmpty(ymd)) continue;
			
			// 휴일로 등록여부
			boolean isCreate = true;

			// 휴일로 등록이 되어있는지 조회
			BusinessDayVO busiDay = businessMapper.getBusinessDay(ymd);
			if ( busiDay != null && ymd.equals(busiDay.getYmd()) && "Y".equals(busiDay.getYn_holiday()) ) {
				isCreate = false;
			}

			businessDayForm.setYmd(ymd);
			
			if ( isCreate ) {
				nCntIn += businessMapper.createBusinessDay(businessDayForm);
			}
			else {
				nCntDel += businessMapper.deleteBusinessDay(businessDayForm);
			}
		}

		if ( nCntIn <= 0 && nCntDel <= 0 ) {
			return new ReturnClass(Constant.FAILED, "변경된 영업일 또는 휴일이 없습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "휴일 추가 "+ nCntIn+"건, 해제 "+nCntDel+"건 이 변경 처리 되었습니다.");
	}


	@Override
	@CacheEvict(value={"CacheBusiness"}, allEntries=true)
	public void clearCacheBusinessDay() {
		logger.info("Cache clear : [CacheBusiness]");
	}
	
	
	
	@Override
	public ReturnClass procFcBusinessDayYnHoliday(BusinessDayForm businessDayForm) {
		
		String[] arr_ymd = businessDayForm.getArr_ymd();
		
		int nCntIn = 0;
		int nCntDel = 0;
		for (String ymd : arr_ymd) {
			if(StringUtil.isEmpty(ymd)) continue;
			
			// 휴일로 등록여부
			boolean isCreate = true;

			// 휴일로 등록이 되어있는지 조회
			BusinessDayVO busiDay = businessMapper.getFcBusinessDay(ymd);
			if ( busiDay != null && ymd.equals(busiDay.getYmd()) && "Y".equals(busiDay.getYn_holiday()) ) {
				isCreate = false;
			}

			businessDayForm.setYmd(ymd);
			
			if ( isCreate ) {
				nCntIn += businessMapper.createFcBusinessDay(businessDayForm);
			}
			else {
				//nCntDel += businessMapper.deleteBusinessDay(businessDayForm);
				nCntIn += businessMapper.createFcBusinessDay(businessDayForm);
			}
		}

		if ( nCntIn <= 0 && nCntDel <= 0 ) {
			return new ReturnClass(Constant.FAILED, "변경된 영업일 또는 휴일이 없습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}


	
	@Override
	public BusinessDayVO getFcBusinessDayInfo(String cd_fc, String ymd) {
		BusinessDayForm businessDayForm = new BusinessDayForm();
		businessDayForm.setCd_fc(cd_fc);
		businessDayForm.setYmd(ymd);
		return businessMapper.getFcBusinessDayInfo(businessDayForm);
	}
	
	@Override
	public ReturnClass deleteFcBusinessDay(BusinessDayForm businessDayForm) {
		if (1 != businessMapper.deleteFcBusinessDay(businessDayForm)) {
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
}
