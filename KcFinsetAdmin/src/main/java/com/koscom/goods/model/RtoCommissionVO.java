package com.koscom.goods.model;

import java.io.Serializable;

import com.koscom.domain.GoodsInfo;

public class RtoCommissionVO extends GoodsInfo implements Serializable{
	int creditGrade = 0;
	double amtYearIncome = 0;
	public int getCreditGrade() {
		return creditGrade;
	}
	public void setCreditGrade(int creditGrade) {
		this.creditGrade = creditGrade;
	}
	public double getAmtYearIncome() {
		return amtYearIncome;
	}
	public void setAmtYearIncome(double amtYearIncome) {
		this.amtYearIncome = amtYearIncome;
	}
}
