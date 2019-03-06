package com.koscom.stats.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.stats.model.StatsChartForm;
import com.koscom.stats.model.StatsForm;
import com.koscom.stats.model.StatsVO;
import com.koscom.stats.service.StatsManager;
import com.koscom.stats.service.StatsWebManager;

@Service("statsWebManager")
public class StatsWebManagerImpl implements StatsWebManager{
	@Autowired
	StatsManager statsManager;
	@Override
	public List<StatsVO> listStatApplyComp(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatApplyComp(statsForm);
	}
	@Override
	public List<StatsVO> listStatWorker(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatWorker(statsForm);
	}
	@Override
	public List<StatsVO> listStatKeyword(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatKeyword(statsForm);
	}
	@Override
	public List<StatsVO> listStatAdvertisement(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatAdvertisement(statsForm);
	}
	@Override
	public List<StatsVO> listStatAdvertisementApply(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatAdvertisementApply(statsForm);
	}
	@Override
	public List<StatsVO> listStatSms(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatSms(statsForm);
	}

	@Override
	public List<StatsVO> listStatDailyUse(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatDailyUse(statsForm);
	}
	@Override
	public int listStatDailyUseCount(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatDailyUseCount(statsForm);
	}
	@Override
	public List<StatsVO> listStatDailyUseDetail(StatsForm statsForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatDailyUseDetail(statsForm);
	}
	@Override
	public List<StatsVO> listStatDailyUseChart(StatsChartForm statsChartForm) {
		// TODO Auto-generated method stub
		return statsManager.listStatDailyUseChart(statsChartForm);
	}

}