package com.koscom.scrap.model;

import java.io.Serializable;

public class UserBankOutputVO implements Serializable{
	private static final long serialVersionUID = 3983890694732901290L;
	//	{
//		"BANK_CODE":"은행코드",
//		"ErrorCode":"00000000",
//		"ErrorMessage":"",
//		"AN_ALL":{},
//		"DEPOSIT_AN":{},
//		"FUND_AN":{},
//		"LOAN_AN":{}
//		}
	String BANK_CODE = "";			//은행코드,
	String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
	String ERROR_MESSAGE = "";		//결과메시지
	AnAllVO AN_ALL = null;			//개인뱅킹 계좌조회
	DepositAnVO DEPOSIT_AN;	//예적금계좌조회
	FundAnVO FUND_AN;			//펀드계좌조회
	LoanAnVO LOAN_AN;			//대출계좌조회
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	public String getERROR_CODE() {
		return ERROR_CODE;
	}
	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}
	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}
	public void setERROR_MESSAGE(String eRROR_MESSAGE) {
		ERROR_MESSAGE = eRROR_MESSAGE;
	}
	public AnAllVO getAN_ALL() {
		return AN_ALL;
	}
	public void setAN_ALL(AnAllVO aN_ALL) {
		AN_ALL = aN_ALL;
	}
	public DepositAnVO getDEPOSIT_AN() {
		return DEPOSIT_AN;
	}
	public void setDEPOSIT_AN(DepositAnVO dEPOSIT_AN) {
		DEPOSIT_AN = dEPOSIT_AN;
	}
	public FundAnVO getFUND_AN() {
		return FUND_AN;
	}
	public void setFUND_AN(FundAnVO fUND_AN) {
		FUND_AN = fUND_AN;
	}
	public LoanAnVO getLOAN_AN() {
		return LOAN_AN;
	}
	public void setLOAN_AN(LoanAnVO lOAN_AN) {
		LOAN_AN = lOAN_AN;
	}

//	public List<DepositAnVO> getDEPOSIT_AN() {
//		return DEPOSIT_AN;
//	}
//	public void setDEPOSIT_AN(List<DepositAnVO> dEPOSIT_AN) {
//		DEPOSIT_AN = dEPOSIT_AN;
//	}
//	public List<FundAnVO> getFUND_AN() {
//		return FUND_AN;
//	}
//	public void setFUND_AN(List<FundAnVO> fUND_AN) {
//		FUND_AN = fUND_AN;
//	}
//	public List<LoanAnVO> getLOAN_AN() {
//		return LOAN_AN;
//	}
//	public void setLOAN_AN(List<LoanAnVO> lOAN_AN) {
//		LOAN_AN = lOAN_AN;
//	}
}