package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FinanceForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = 2547584799302411233L;
	String financeNo;	//금융기관신청번호
	String rnno;		//주민등록번호
	int seq_board;
	public String getFinanceNo() {
		return financeNo;
	}
	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
	}
	public String getRnno() {
		return rnno;
	}
	public void setRnno(String rnno) {
		this.rnno = rnno;
	}
	public int getSeq_board() {
		return seq_board;
	}
	public void setSeq_board(int seq_board) {
		this.seq_board = seq_board;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}