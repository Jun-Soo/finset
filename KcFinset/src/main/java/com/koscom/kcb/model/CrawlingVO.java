package com.koscom.kcb.model;

import java.util.List;


import com.koscom.credit.model.CreditVO;
import com.koscom.debt.model.CrawlingLoanVO;

public class CrawlingVO extends CreditVO {

	private String no_person;       // 회원관리번호
	private String ctn_loan;		// 총 대출건수
	private String amt_loan;		// 총 약정금액

	private List<CrawlingLoanVO> crawlingLoanList;	//계좌별 대출 상세

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCtn_loan() {
		return ctn_loan;
	}

	public void setCtn_loan(String ctn_loan) {
		this.ctn_loan = ctn_loan;
	}

	public String getAmt_loan() {
		return amt_loan;
	}

	public void setAmt_loan(String amt_loan) {
		this.amt_loan = amt_loan;
	}

	public List<CrawlingLoanVO> getCrawlingLoanList() {
		return crawlingLoanList;
	}

	public void setCrawlingLoanList(List<CrawlingLoanVO> crawlingLoanList) {
		this.crawlingLoanList = crawlingLoanList;
	}


}