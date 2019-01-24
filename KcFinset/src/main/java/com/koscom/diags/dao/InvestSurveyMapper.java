package com.koscom.diags.dao;

import com.koscom.diags.model.InvestSurvey;

public interface InvestSurveyMapper { // com.koscom.scrap.dao.ScrapMapper

	/**
	 * 투자성향 조회 
	 * @param String
	 */
	InvestSurvey getInvestSurvey(String noPerson);
	
	/**
	 * 투자성향 등록
	 * @param InvestSurvey
	 */
	int insertInvestSurvey(InvestSurvey investSurvey);
	
	/**
	 * 투자성향 수정 
	 * @param InvestSurvey
	 */
	int updateInvestSurvey(InvestSurvey investSurvey);
	
}