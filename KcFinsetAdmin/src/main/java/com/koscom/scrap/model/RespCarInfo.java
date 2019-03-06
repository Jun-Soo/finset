package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespCarInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2494390886548364514L;

	public int SEQ_CAR_REGISTER;
	public String BOND_VALUE;
	public String DEBTOR_NAME;
	public String OWNER_NAME;
	public String EXPIRY_DATE;
	public String WORKTYPE;
	public String RECEIPT_DATE;
	public String SECURITY_NO;
	public String MORTGAGE_NAME;
	public String RESULT_CD;
	public String CAR_VINARY_NO;
	public String ORG_PHONE;
	public String CAR_NUM;
	public String RESULT_MG;
	public String ORG_NAME;
	public String FILENAME;
	public String MORTGAGEE_NAME;
	public String MORTGAGE_DATE;
	public String RECEIPT_NO;
	public String MORTGAGE_NO;
	public String ERROR_CD;
	public String ERROR_MSG;
	public String REGISTER_DT;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public int getSEQ_CAR_REGISTER() {
		return SEQ_CAR_REGISTER;
	}
	public void setSEQ_CAR_REGISTER(int SEQ_CAR_REGISTER) {
		this.SEQ_CAR_REGISTER = SEQ_CAR_REGISTER;
	}
	public String getBOND_VALUE() {
		return BOND_VALUE;
	}
	public void setBOND_VALUE(String BOND_VALUE) {
		this.BOND_VALUE = BOND_VALUE;
	}
	public String getDEBTOR_NAME() {
		return DEBTOR_NAME;
	}
	public void setDEBTOR_NAME(String DEBTOR_NAME) {
		this.DEBTOR_NAME = DEBTOR_NAME;
	}
	public String getOWNER_NAME() {
		return OWNER_NAME;
	}
	public void setOWNER_NAME(String OWNER_NAME) {
		this.OWNER_NAME = OWNER_NAME;
	}
	public String getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}
	public void setEXPIRY_DATE(String EXPIRY_DATE) {
		this.EXPIRY_DATE = EXPIRY_DATE;
	}
	public String getWORKTYPE() {
		return WORKTYPE;
	}
	public void setWORKTYPE(String WORKTYPE) {
		this.WORKTYPE = WORKTYPE;
	}
	public String getRECEIPT_DATE() {
		return RECEIPT_DATE;
	}
	public void setRECEIPT_DATE(String RECEIPT_DATE) {
		this.RECEIPT_DATE = RECEIPT_DATE;
	}
	public String getSECURITY_NO() {
		return SECURITY_NO;
	}
	public void setSECURITY_NO(String SECURITY_NO) {
		this.SECURITY_NO = SECURITY_NO;
	}
	public String getMORTGAGE_NAME() {
		return MORTGAGE_NAME;
	}
	public void setMORTGAGE_NAME(String MORTGAGE_NAME) {
		this.MORTGAGE_NAME = MORTGAGE_NAME;
	}
	public String getRESULT_CD() {
		return RESULT_CD;
	}
	public void setRESULT_CD(String RESULT_CD) {
		this.RESULT_CD = RESULT_CD;
	}
	public String getCAR_VINARY_NO() {
		return CAR_VINARY_NO;
	}
	public void setCAR_VINARY_NO(String CAR_VINARY_NO) {
		this.CAR_VINARY_NO = CAR_VINARY_NO;
	}
	public String getORG_PHONE() {
		return ORG_PHONE;
	}
	public void setORG_PHONE(String ORG_PHONE) {
		this.ORG_PHONE = ORG_PHONE;
	}
	public String getCAR_NUM() {
		return CAR_NUM;
	}
	public void setCAR_NUM(String CAR_NUM) {
		this.CAR_NUM = CAR_NUM;
	}
	public String getRESULT_MG() {
		return RESULT_MG;
	}
	public void setRESULT_MG(String RESULT_MG) {
		this.RESULT_MG = RESULT_MG;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String ORG_NAME) {
		this.ORG_NAME = ORG_NAME;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		this.FILENAME = fILENAME;
	}
	public String getMORTGAGEE_NAME() {
		return MORTGAGEE_NAME;
	}
	public void setMORTGAGEE_NAME(String MORTGAGEE_NAME) {
		this.MORTGAGEE_NAME = MORTGAGEE_NAME;
	}
	public String getMORTGAGE_DATE() {
		return MORTGAGE_DATE;
	}
	public void setMORTGAGE_DATE(String MORTGAGE_DATE) {
		this.MORTGAGE_DATE = MORTGAGE_DATE;
	}
	public String getRECEIPT_NO() {
		return RECEIPT_NO;
	}
	public void setRECEIPT_NO(String RECEIPT_NO) {
		this.RECEIPT_NO = RECEIPT_NO;
	}
	public String getMORTGAGE_NO() {
		return MORTGAGE_NO;
	}
	public void setMORTGAGE_NO(String MORTGAGE_NO) {
		this.MORTGAGE_NO = MORTGAGE_NO;
	}
	public String getERROR_CD() {
		return ERROR_CD;
	}
	public void setERROR_CD(String ERROR_CD) {
		this.ERROR_CD = ERROR_CD;
	}
	public String getERROR_MSG() {
		return ERROR_MSG;
	}
	public void setERROR_MSG(String ERROR_MSG) {
		this.ERROR_MSG = ERROR_MSG;
	}
	public String getREGISTER_DT() {
		return REGISTER_DT;
	}
	public void setREGISTER_DT(String REGISTER_DT) {
		this.REGISTER_DT = REGISTER_DT;
	}
	
	
	
}
