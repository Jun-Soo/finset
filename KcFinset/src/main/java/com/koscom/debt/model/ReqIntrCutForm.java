package com.koscom.debt.model;

import java.util.List;

import com.koscom.comm.model.SearchForm;

public class ReqIntrCutForm extends SearchForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 3089949391787193322L;

	private String no_person; //회원번호
	private List<String> cutItems; //항목
	private String creditFixDate; //신용 기준일
	private String turnoverDate; //이직일
	private String income; //연소득
	private String debtFixDate; //부채 기준일
	private String posFixDate; //직위 기준일
	private String certFixDate; //자격증 기준일
	private String fixDate; //기준일

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public List<String> getCutItems() {
		return cutItems;
	}
	public void setCutItems(List<String> cutItems) {
		this.cutItems = cutItems;
	}
	public String getCreditFixDate() {
		return creditFixDate;
	}
	public void setCreditFixDate(String creditFixDate) {
		this.creditFixDate = creditFixDate;
	}
	public String getTurnoverDate() {
		return turnoverDate;
	}
	public void setTurnoverDate(String turnoverDate) {
		this.turnoverDate = turnoverDate;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getDebtFixDate() {
		return debtFixDate;
	}
	public void setDebtFixDate(String debtFixDate) {
		this.debtFixDate = debtFixDate;
	}
	public String getPosFixDate() {
		return posFixDate;
	}
	public void setPosFixDate(String posFixDate) {
		this.posFixDate = posFixDate;
	}
	public String getCertFixDate() {
		return certFixDate;
	}
	public void setCertFixDate(String certFixDate) {
		this.certFixDate = certFixDate;
	}
	public String getFixDate() {
		return fixDate;
	}
	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}

}