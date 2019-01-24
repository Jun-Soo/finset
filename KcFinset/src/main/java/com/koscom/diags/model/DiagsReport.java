package com.koscom.diags.model;

import java.io.Serializable;

import com.koscom.diags.model.analysis.FactorAnalysis;
import com.koscom.diags.model.analysis.RiskAnalysis;
import com.koscom.diags.model.analysis.SectorAnalysis;

public class DiagsReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	//
	private FactorAnalysis 	factorAnalysis;
	
	private RiskAnalysis		riskAnalysis;

	private SectorAnalysis	sectorAnalysis;

	public FactorAnalysis getFactorAnalysis() {
		return factorAnalysis;
	}

	public void setFactorAnalysis(FactorAnalysis factorAnalysis) {
		this.factorAnalysis = factorAnalysis;
	}

	public RiskAnalysis getRiskAnalysis() {
		return riskAnalysis;
	}

	public void setRiskAnalysis(RiskAnalysis riskAnalysis) {
		this.riskAnalysis = riskAnalysis;
	}

	public SectorAnalysis getSectorAnalysis() {
		return sectorAnalysis;
	}

	public void setSectorAnalysis(SectorAnalysis sectorAnalysis) {
		this.sectorAnalysis = sectorAnalysis;
	}

	
	
}
