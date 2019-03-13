package com.koscom.goods.model;

import java.io.Serializable;

import com.koscom.domain.GoodsInfo;

public class MueVO extends GoodsInfo implements Serializable{

	private static final long serialVersionUID = -2250114387004110736L;
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
