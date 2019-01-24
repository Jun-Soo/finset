package com.koscom.diags.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.diags.dao.StockGoalMapper;
import com.koscom.diags.model.InvestSurvey;
import com.koscom.diags.model.StockGoal;
import com.koscom.diags.model.StockGoalExt;
import com.koscom.diags.model.analysis.StockSector;
import com.koscom.diags.service.InvestSurveyService;
import com.koscom.diags.service.StockGoalService;

@Service("stockGoal")
public class StockGoalServiceImpl implements StockGoalService {

	private static final Logger logger = LoggerFactory.getLogger(StockGoalServiceImpl.class);

	@Autowired
	private StockGoalMapper stockGoalMapper;
	
	@Autowired InvestSurveyService investSurveyService;

	@Override
	public List<StockGoalExt> getGoals(String noPerson) {
		if(noPerson==null) return null;
		
		List<StockGoalExt> stockGoals = stockGoalMapper.getStockGoals(noPerson);
		if(stockGoals==null) return null;
		
		InvestSurvey investSurvey = investSurveyService.getInvestSurvey(noPerson);
		int defaultProfitGoal = investSurvey!=null ? investSurvey.getStockProfit() : 10;
		int defaultHoldGoal = investSurvey!=null ? investSurvey.getHoldGoal() : 30;
		
		boolean changed=false;
		for(int i=0; i<stockGoals.size(); i++) {
			StockGoalExt sge = stockGoals.get(i);
			if(sge.getProfitGoal()<0) {
				sge.setProfitGoal(defaultProfitGoal);
				changed=true;
			}
			if(sge.getHoldGoal()<0) {
				sge.setHoldGoal(defaultHoldGoal);
				changed=true;
			}
			if(changed) {
				changed=false;
				stockGoals.set(i, sge);
			}
		}
		return stockGoals;
	}

	@Override
	public void saveStockGoals(String noPerson, List<StockGoal> stockGoals) {
		if(noPerson==null || stockGoals==null) return;
		
		// delete first
		stockGoalMapper.deleteStockGoals(noPerson);
		stockGoalMapper.insertStockGoals(stockGoals);
	}

	@Override
	public List<StockSector> getStockSectors(String noPerson) {
		if(noPerson==null) return null;
		
		return stockGoalMapper.getStockSectors(noPerson);
	}

}
