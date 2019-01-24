package com.koscom.diags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.diags.model.DiagsReport;
import com.koscom.diags.model.DiagsResult;
import com.koscom.diags.model.DiagsStockReport;
import com.koscom.diags.model.InvestSurvey;
import com.koscom.diags.model.StockGoal;
import com.koscom.diags.model.StockGoalExt;
import com.koscom.diags.model.StockGoalReq;
import com.koscom.diags.service.DiagsService;
import com.koscom.diags.service.InvestSurveyService;
import com.koscom.diags.service.StockGoalService;
import com.koscom.util.FinsetException;


@Controller
@RequestMapping("/m/diags")
public class DiagsController {

	private static final Logger logger = LoggerFactory.getLogger(DiagsController.class);
	
	@Autowired
	private InvestSurveyService investSurveyService;
	@Autowired 
	private StockGoalService stockGoalService;
	@Autowired
	private DiagsService diagsService;

	@RequestMapping("/getDiagsResult.json")
	public String getDiagsResult(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		DiagsResult diagsResult = diagsService.diagsResult(no_person);

        model.addAttribute("diagsResult", diagsResult); // 보유종목 목록 (투자목표 포함)

		return "jsonView";
	}
	
	/**
	 * VUE
     * 보유종목 조회 (종목별 투자목표)
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getStockGoals.json")
	public String getStockGoals(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		List<StockGoalExt> stockGoals = stockGoalService.getGoals(no_person);

        model.addAttribute("stockGoals", stockGoals); // 보유종목 목록 (투자목표 포함)

		return "jsonView";
	}

	@RequestMapping("/saveStockGoals.json")
	public String saveStockGoals(
		HttpServletRequest request,
		HttpSession session,
		StockGoalReq stockGoalReq,
		Model model) throws FinsetException, IOException{
		
		String no_person = (String)session.getAttribute("no_person");

		List<StockGoal> stockGoals = stockGoalReq.getStockGoals();
		if(stockGoals!=null) {
			stockGoalService.saveStockGoals(no_person, stockGoals);
		} else {
			logger.info("stockGoals is missing");
		}

		return "jsonView";
	}

	/**
	 * VUE
     * 투자성향 설문 저장
     * @param request
     * @param session
     * @param InvestSurvey
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/saveInvestSurvey.json")
	public String saveInvestSurvey(
	HttpServletRequest request,
	HttpSession session,
	InvestSurvey investSurvey,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		InvestSurvey saved = investSurveyService.getInvestSurvey(no_person);
		if(saved!=null) {
			// update
			if( !saved.getInvestYr().equals(investSurvey.getInvestYr()) ) saved.setInvestYr(investSurvey.getInvestYr());
			if( !saved.getStockSelect().equals(investSurvey.getStockSelect()) ) saved.setStockSelect(investSurvey.getStockSelect());
			if( !saved.getRiskLimit().equals(investSurvey.getRiskLimit()) ) saved.setRiskLimit(investSurvey.getRiskLimit());
			if( !saved.getHoldPeriod().equals(investSurvey.getHoldPeriod()) ) saved.setHoldPeriod(investSurvey.getHoldPeriod());
			if( !saved.getYrProfit().equals(investSurvey.getYrProfit()) ) saved.setYrProfit(investSurvey.getYrProfit());
			if( !saved.getInvestTime().equals(investSurvey.getInvestTime()) ) saved.setInvestTime(investSurvey.getInvestTime());
			if( !saved.getLosscut().equals(investSurvey.getLosscut()) ) saved.setLosscut(investSurvey.getLosscut());
			if( !saved.getStockProfit().equals(investSurvey.getStockProfit()) ) saved.setStockProfit(investSurvey.getStockProfit());
			investSurveyService.updateInvestSurvey(saved);
		} else {
			investSurvey.setNoPerson(no_person);
			investSurveyService.insertInvestSurvey(investSurvey);
		}

		return "jsonView";
	}

	@RequestMapping("/getDiagsReport.json")
	public String getDiagsReport(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		DiagsReport diagsReport = diagsService.diagsReport(no_person);

        model.addAttribute("diagsReport", diagsReport); // 진단결과 

		return "jsonView";
	}
	
	@RequestMapping("/getDiagsStockReport.json")
	public String getDiagsStockReport(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		DiagsStockReport diagsStockReport = diagsService.diagsStockReport(no_person);

        model.addAttribute("diagsStockReport", diagsStockReport); // 진단결과 

		return "jsonView";
	}

}
