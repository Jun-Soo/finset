package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class FactorAnalysis extends AnalysisBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	
	private String isincode; //종목코드  
	// 
	private int value;			// 가치 
	private int growth;			// 성장 
	private int quality;			// 안정성 
	private int profitability;	// 수익성 
	private int momentum;		// 모멘텀 
		
	private int cnt45 = 0;
	private int cnt12 = 0;
	private int cnt3 = 0;
	
	private StringBuilder sb45 = new StringBuilder();
	private StringBuilder sb12 = new StringBuilder();
	private StringBuilder sb3 = new StringBuilder();
	
	private void incRateCnt(int rate, String rateNm) {
		if(rate==1 || rate==2) {
			if(cnt12>0) sb12.append(", ");
			cnt12++;
			sb12.append(rateNm);
		}
		else if(rate==4 || rate==5) {
			if(cnt45>0) sb45.append(", ");
			cnt45++;
			sb45.append(rateNm);
		}
		else if(rate==3) {
			if(cnt3>0) sb3.append(", ");
			cnt3++;
			sb3.append(rateNm);
		}
	}
		
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		incRateCnt(value, "가치");
	}
	public int getGrowth() {
		return growth;
	}
	public void setGrowth(int growth) {
		this.growth = growth;
		incRateCnt(growth, "성장성");
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
		incRateCnt(quality, "안정성");
	}
	public int getProfitability() {
		return profitability;
	}
	public void setProfitability(int profitability) {
		this.profitability = profitability;
		incRateCnt(profitability, "수익성");
	}
	public int getMomentum() {
		return momentum;
	}
	public void setMomentum(int momentum) {
		this.momentum = momentum;
		incRateCnt(momentum, "모멘텀");
	}
	
	public String getMainComment() {
		int avg = getAvg();
		if(avg<1 || avg>5) return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 포트폴리오 편입 종목들의 평균등급이 ");
		
		if(avg==1) sb.append("시장대비 과도하게 낮으므로 포트폴리오 재구성을 강력히 추천합니다.");
		else if(avg==2) sb.append("시장평균 이하로 포트폴리오 조정을 고려하는게 좋습니다.");
		else if(avg==3) sb.append("시장평균 수준입니다.");
		else if(avg==4) sb.append("4등급으로 시장대비 종목선정이 비교적 우수합니다.");
		else if(avg==5) sb.append("5등급으로 시장대비 종목선정이 매우 우수합니다.");
		
		return sb.toString();
	}
	
	@Override
	public String getComment() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 포트폴리오의 스타일 분석 결과 ");
		
		if(cnt45>0) {	// 4,5 가 있는 경우 
			sb.append(sb45.toString());
			sb.append(" 위주로 편성된 포트폴리오입니다.");
		} else { // 4,5 가 없는 경우 
			if(cnt3>0) { // 3 이 있는 경우 
				sb.append("시장평균 정도로 ");
				sb.append(sb3.toString());
				sb.append(" 에 노출되어 있습니다.");
			} else { // 1,2 만 있는 경우 
				sb.append("factor 투자 측면에서 시장가격 움직임을 설명하는 어떤 factor에도 시장평균이상으로 노출되지 않았습니다. 종목 당 특별한 이벤트를 기대하지 않는 한 포트폴리오 재구축을 추천합니다.");
			}
		}
		
		comment = sb.toString();
		return comment;
	}
	public String getStockComment() {
		StringBuilder sb = new StringBuilder();
		sb.append("스타일 분석 결과 ");
		
		if(cnt45>0) {	// 4,5 가 있는 경우 
			sb.append(sb45.toString());
			sb.append(" 중심의 종목입니다.");
		} else { // 4,5 가 없는 경우 
			if(cnt3>0) { // 3 이 있는 경우 
				sb.append("시장평균 정도로 ");
				sb.append(sb3.toString());
				sb.append(" 에 노출되어 있습니다.");
			} else { // 1,2 만 있는 경우 
				sb.append("factor 투자 측면에서 시장가격 움직임을 설명하는 어떤 factor에도 시장평균이상으로 노출되지 않았습니다. 특별한 이벤트를 기대하지 않는한 종복 변경을 추천합니다.");
			}
		}
		
		comment = sb.toString();
		return comment;
	}
	
	/*
	 * factor 평균 
	 */
	public int getAvg() {
		return (value+growth+quality+profitability+momentum)/5;
	}



}
