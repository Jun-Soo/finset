package com.koscom.env.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.env.model.BusinessDayForm;
import com.koscom.env.model.BusinessDayVO;
import com.koscom.env.service.BusinessManager;
import com.koscom.env.service.BusinessWebManager;
import com.koscom.util.ReturnClass;

@Service("businessWebManager")
public class BusinessWebManagerImpl implements BusinessWebManager{
	
	@Autowired
	private BusinessManager businessManager;
	
	@Override
	public HashMap<String, String> listCacheBusinessDay() {
		return businessManager.listCacheBusinessDay();
	}

	@Override
	public boolean isHoliDay(String ymd) {
		// TODO Auto-generated method stub
		return businessManager.isHoliDay(ymd);
	}

	@Override
	public String nextWeekDay(String ymd) {
		// TODO Auto-generated method stub
		return businessManager.nextWeekDay(ymd);
	}

	@Override
	public String nextBusinessDay(String ymd_basic, int cnt) {
		// TODO Auto-generated method stub
		return businessManager.nextBusinessDay(ymd_basic, cnt);
	}

	@Override
	public ReturnClass procBusinessDayYnHoliday(BusinessDayForm businessDayForm) {
		// TODO Auto-generated method stub
		return businessManager.procBusinessDayYnHoliday(businessDayForm);
	}

	@Override
	public void clearCacheBusinessDay() {
		// TODO Auto-generated method stub
		businessManager.clearCacheBusinessDay();
	}

	@Override
	public HashMap<String, String> listCacheFcBusinessDay(BusinessDayForm businessDayForm) {
		// TODO Auto-generated method stub
		return businessManager.listCacheFcBusinessDay(businessDayForm);
	}

	@Override
	public ReturnClass procFcBusinessDayYnHoliday(BusinessDayForm businessDayForm) {
		// TODO Auto-generated method stub
		return businessManager.procFcBusinessDayYnHoliday(businessDayForm);
	}

	@Override
	public ReturnClass deleteFcBusinessDay(BusinessDayForm businessDayForm) {
		// TODO Auto-generated method stub
		return businessManager.deleteFcBusinessDay(businessDayForm);
	}

	@Override
	public BusinessDayVO getFcBusinessDayInfo(String cd_fc, String ymd) {
		// TODO Auto-generated method stub
		return businessManager.getFcBusinessDayInfo(cd_fc, ymd);
	}
}
