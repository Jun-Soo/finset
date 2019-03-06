package com.koscom.stats.service;

import java.util.List;

import com.koscom.stats.model.StatsChartForm;
import com.koscom.stats.model.StatsForm;
import com.koscom.stats.model.StatsVO;

public interface StatsWebManager {

	/**
	 * 금융사별 통계 리스트
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatApplyComp(StatsForm statsForm);

	/**
	 * 담당자별 통계 리스트
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatWorker(StatsForm statsForm);

	/**
	 * 키워드별 통계 리스트
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatKeyword(StatsForm statsForm);

	/**
	 * 광고매체별 통계 리스트(신청인)
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatAdvertisement(StatsForm statsForm);

	/**
	 * 광고매체별 통계 리스트(신청서)
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatAdvertisementApply(StatsForm statsForm);

	/**
	 * SMS 발송통계 리스트
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatSms(StatsForm statsForm);

	/**
	 * 일별 사용 현황
	 * @param statsForm
	 * @return
	 */
	List<StatsVO> listStatDailyUse(StatsForm statsForm);
	int listStatDailyUseCount(StatsForm statsForm);
	List<StatsVO> listStatDailyUseDetail(StatsForm statsForm);
	List<StatsVO> listStatDailyUseChart(StatsChartForm statsChartForm);

}