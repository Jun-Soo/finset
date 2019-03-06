package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespCertVatdtl implements Serializable{


	/**
	 * 민원증명통합조회결과_부가가치세과세표준증명상세
	 */
	private static final long serialVersionUID = -5706720122040874495L;

	protected int    order_vat ; //순서_부가가치세
	protected int    pay_tax_amt ; //납부세금금액
	protected String register_dt ; //등록일시
	protected int    sales_taxation_standard_sum ; //매출과세표준합계
	protected int    sales_txtn_stndrd_txtn_amt ; //매출과세표준과세금액
	protected int    seq_req ; //일련번호_요청
	protected int    sls_txtn_stndrd_exmptn_amt ; //매출과세표준면제금액
	protected String taxation_end_dd ; //과세종료일
	protected String taxation_start_dd ; //과세시작일
	
	public int getOrder_vat() {
		return order_vat;
	}

	public void setOrder_vat(int order_vat) {
		this.order_vat = order_vat;
	}

	public int getPay_tax_amt() {
		return pay_tax_amt;
	}

	public void setPay_tax_amt(int pay_tax_amt) {
		this.pay_tax_amt = pay_tax_amt;
	}

	public String getRegister_dt() {
		return register_dt;
	}

	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}

	public int getSales_taxation_standard_sum() {
		return sales_taxation_standard_sum;
	}

	public void setSales_taxation_standard_sum(int sales_taxation_standard_sum) {
		this.sales_taxation_standard_sum = sales_taxation_standard_sum;
	}

	public int getSales_txtn_stndrd_txtn_amt() {
		return sales_txtn_stndrd_txtn_amt;
	}

	public void setSales_txtn_stndrd_txtn_amt(int sales_txtn_stndrd_txtn_amt) {
		this.sales_txtn_stndrd_txtn_amt = sales_txtn_stndrd_txtn_amt;
	}

	public int getSeq_req() {
		return seq_req;
	}

	public void setSeq_req(int seq_req) {
		this.seq_req = seq_req;
	}

	public int getSls_txtn_stndrd_exmptn_amt() {
		return sls_txtn_stndrd_exmptn_amt;
	}

	public void setSls_txtn_stndrd_exmptn_amt(int sls_txtn_stndrd_exmptn_amt) {
		this.sls_txtn_stndrd_exmptn_amt = sls_txtn_stndrd_exmptn_amt;
	}

	public String getTaxation_end_dd() {
		return taxation_end_dd;
	}

	public void setTaxation_end_dd(String taxation_end_dd) {
		this.taxation_end_dd = taxation_end_dd;
	}

	public String getTaxation_start_dd() {
		return taxation_start_dd;
	}

	public void setTaxation_start_dd(String taxation_start_dd) {
		this.taxation_start_dd = taxation_start_dd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
