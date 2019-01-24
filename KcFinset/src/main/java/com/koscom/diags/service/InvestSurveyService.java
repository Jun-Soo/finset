package com.koscom.diags.service;

import com.koscom.diags.model.InvestSurvey;

public interface InvestSurveyService {

	/**
	 * 투자성향 조회 
	 * @param String
	 * @return InvestSurvey
	 */
	public InvestSurvey getInvestSurvey(String noPerson);

	/**
	 * 투자성향 등록  
	 * @param InvestSurvey
	 * @return void
	 */
	public void insertInvestSurvey(InvestSurvey investSurvey);

	/**
	 * 투자성향 수정 
	 * @param InvestSurvey
	 * @return void
	 */
	public void updateInvestSurvey(InvestSurvey investSurvey);

}
