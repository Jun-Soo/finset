package com.koscom.diags.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.diags.dao.InvestSurveyMapper;
import com.koscom.diags.model.InvestSurvey;
import com.koscom.diags.service.InvestSurveyService;

@Service("investSurvey")
public class InvestSurveyServiceImpl implements InvestSurveyService {

//	private static final Logger logger = LoggerFactory.getLogger(InvestSurveyServiceImpl.class);

	@Autowired
	private InvestSurveyMapper investSurveyMapper;

	@Override
	public InvestSurvey getInvestSurvey(String noPerson) {
		return investSurveyMapper.getInvestSurvey(noPerson);
	}

	@Override
	public void insertInvestSurvey(InvestSurvey investSurvey) {
		investSurveyMapper.insertInvestSurvey(investSurvey);
	}

	@Override
	public void updateInvestSurvey(InvestSurvey investSurvey) {
		investSurveyMapper.updateInvestSurvey(investSurvey);
	}

}
