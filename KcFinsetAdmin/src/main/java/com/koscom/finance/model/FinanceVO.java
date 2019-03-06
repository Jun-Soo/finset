package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FinanceVO extends SearchForm implements Serializable {

	private static final long serialVersionUID = -4967373441977930507L;
	
	/**
	 * DB INSERT, UPDATE, DELETE, SELECT RESULTList 
	 */
	String lonProgressStatus; //대출진행상태
	String lonAmt; //대출지급금액
	String lonRate; //대출금리
	String lonDate; //대출일자
	String lonReson; //사우	
	
	/**
	 * @method : getLonProgressStatus
	 * @desc   : 
	 * @param  : 
	 * @return : lonProgressStatus
	 */
	public String getLonProgressStatus() {
		return lonProgressStatus;
	}

	/**
	 * @method : setLonProgressStatus
	 * @desc   : 
	 * @param  : lonProgressStatus the lonProgressStatus to set
	 * @return : lonProgressStatus
	 */
	public void setLonProgressStatus(String lonProgressStatus) {
		this.lonProgressStatus = lonProgressStatus;
	}

	/**
	 * @method : getLonAmt
	 * @desc   : 
	 * @param  : 
	 * @return : lonAmt
	 */
	public String getLonAmt() {
		return lonAmt;
	}

	/**
	 * @method : setLonAmt
	 * @desc   : 
	 * @param  : lonAmt the lonAmt to set
	 * @return : lonAmt
	 */
	public void setLonAmt(String lonAmt) {
		this.lonAmt = lonAmt;
	}

	/**
	 * @method : getLonRate
	 * @desc   : 
	 * @param  : 
	 * @return : lonRate
	 */
	public String getLonRate() {
		return lonRate;
	}

	/**
	 * @method : setLonRate
	 * @desc   : 
	 * @param  : lonRate the lonRate to set
	 * @return : lonRate
	 */
	public void setLonRate(String lonRate) {
		this.lonRate = lonRate;
	}

	/**
	 * @method : getLonDate
	 * @desc   : 
	 * @param  : 
	 * @return : lonDate
	 */
	public String getLonDate() {
		return lonDate;
	}

	/**
	 * @method : setLonDate
	 * @desc   : 
	 * @param  : lonDate the lonDate to set
	 * @return : lonDate
	 */
	public void setLonDate(String lonDate) {
		this.lonDate = lonDate;
	}

	/**
	 * @method : getLonReson
	 * @desc   : 
	 * @param  : 
	 * @return : lonReson
	 */
	public String getLonReson() {
		return lonReson;
	}

	/**
	 * @method : setLonReson
	 * @desc   : 
	 * @param  : lonReson the lonReson to set
	 * @return : lonReson
	 */
	public void setLonReson(String lonReson) {
		this.lonReson = lonReson;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}