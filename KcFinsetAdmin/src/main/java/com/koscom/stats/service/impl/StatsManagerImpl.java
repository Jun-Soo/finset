package com.koscom.stats.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.stats.dao.StatsMapper;
import com.koscom.stats.model.StatsChartForm;
import com.koscom.stats.model.StatsForm;
import com.koscom.stats.model.StatsVO;
import com.koscom.stats.service.StatsManager;

@Service("statsManager")
public class StatsManagerImpl implements StatsManager {

	@Autowired
	private StatsMapper statsMapper;

	@Override
	public List<StatsVO> listStatApplyComp(StatsForm statsForm) {
		return statsMapper.listStatApplyComp(statsForm);
	}

	@Override
	public List<StatsVO> listStatWorker(StatsForm statsForm) {
		return statsMapper.listStatWorker(statsForm);
	}

	@Override
	public List<StatsVO> listStatKeyword(StatsForm statsForm) {
		return statsMapper.listStatKeyword(statsForm);
	}

	@Override
	public List<StatsVO> listStatAdvertisement(StatsForm statsForm) {
		return statsMapper.listStatAdvertisement(statsForm);
	}

	@Override
	public List<StatsVO> listStatAdvertisementApply(StatsForm statsForm) {
		return statsMapper.listStatAdvertisementApply(statsForm);
	}

	@Override
	public List<StatsVO> listStatSms(StatsForm statsForm) {
		return statsMapper.listStatSms(statsForm);
	}

	@Override
	public List<StatsVO> listStatDailyUse(StatsForm statsForm) {
		return statsMapper.listStatDailyUse(statsForm);
	}
	@Override
	public int listStatDailyUseCount(StatsForm statsForm) {
		return statsMapper.listStatDailyUseCount(statsForm);
	}
	@Override
	public List<StatsVO> listStatDailyUseDetail(StatsForm statsForm) {
		return statsMapper.listStatDailyUseDetail(statsForm);
	}
	@Override
	public List<StatsVO> listStatDailyUseChart(StatsChartForm statsChartForm) {
		return statsMapper.listStatDailyUseChart(statsChartForm);
	}

}
