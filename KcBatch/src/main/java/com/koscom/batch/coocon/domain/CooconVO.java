package com.koscom.batch.coocon.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CooconVO  implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;

	private String cd_coocon_goods;
	private String nm_coocon_goods;
	private String seq;
	private String status;
	private String yn_reg;
	private String nm_goods;
	private String cd_non_goods;
	private String cd_type_fc;
	private String cd_org;
	private String cd_type_goods;
	private String cd_type_req;
	private String PRIME_RATE;
	private String REPAYMENT_METHOD;
	private String NECESSARY_DOCUMENTS;
	private String CREDIT_LINE;
	private String LENDING_RATE;
	private String CANCELLATION_FEE;
	private String PRODUCT_NAME;
	private String OUTLINE;
	private String FEE;
	private String INQUIRY_FOR_THE_GOODS;
	private String PERIOD_FOR_LOAN_APPLICATION;
	private String ANY_OTHER_CAUSE;
	private String MORTGAGE;
	private String OVERDUE_INTEREST;
	private String PERIOD_FOR_LOAN_PROVISION;
	private String LENDING_RATE_DETAIL;
	private String COST_OF_BORROWING;
	private String PAGE_URL;
	private String TARGET_CUSTOMER;
	private String LOAN_PERIOD;
	private String id_frt;
	private String id_lst;

	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getNm_coocon_goods() {
		return String.valueOf(PRODUCT_NAME).replaceAll(" ", "");
	}
	public void setNm_coocon_goods(String PRODUCT_NAME) {
		this.nm_coocon_goods = String.valueOf(PRODUCT_NAME).replaceAll(" ", "");
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYn_reg() {
		return yn_reg;
	}
	public void setYn_reg(String yn_reg) {
		this.yn_reg = yn_reg;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
	public String getCd_non_goods() {
		return cd_non_goods;
	}
	public void setCd_non_goods(String cd_non_goods) {
		this.cd_non_goods = cd_non_goods;
	}
	public String getCd_type_fc() {
		return cd_type_fc;
	}
	public void setCd_type_fc(String cd_type_fc) {
		this.cd_type_fc = cd_type_fc;
	}
	public String getCd_org() {
		return cd_org;
	}
	public void setCd_org(String cd_org) {
		this.cd_org = cd_org;
	}
	public String getCd_type_goods() {
		return cd_type_goods;
	}
	public void setCd_type_goods(String cd_type_goods) {
		this.cd_type_goods = cd_type_goods;
	}
	public String getCd_type_req() {
		return cd_type_req;
	}
	public void setCd_type_req(String cd_type_req) {
		this.cd_type_req = cd_type_req;
	}
	public String getPRIME_RATE() {
		return PRIME_RATE;
	}
	public void setPRIME_RATE(String pRIME_RATE) {
		PRIME_RATE = pRIME_RATE;
	}
	public String getREPAYMENT_METHOD() {
		return REPAYMENT_METHOD;
	}
	public void setREPAYMENT_METHOD(String rEPAYMENT_METHOD) {
		REPAYMENT_METHOD = rEPAYMENT_METHOD;
	}
	public String getNECESSARY_DOCUMENTS() {
		return NECESSARY_DOCUMENTS;
	}
	public void setNECESSARY_DOCUMENTS(String nECESSARY_DOCUMENTS) {
		NECESSARY_DOCUMENTS = nECESSARY_DOCUMENTS;
	}
	public String getCREDIT_LINE() {
		return CREDIT_LINE;
	}
	public void setCREDIT_LINE(String cREDIT_LINE) {
		CREDIT_LINE = cREDIT_LINE;
	}
	public String getLENDING_RATE() {
		return LENDING_RATE;
	}
	public void setLENDING_RATE(String lENDING_RATE) {
		LENDING_RATE = lENDING_RATE;
	}
	public String getCANCELLATION_FEE() {
		return CANCELLATION_FEE;
	}
	public void setCANCELLATION_FEE(String cANCELLATION_FEE) {
		CANCELLATION_FEE = cANCELLATION_FEE;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getOUTLINE() {
		return OUTLINE;
	}
	public void setOUTLINE(String oUTLINE) {
		OUTLINE = oUTLINE;
	}
	public String getFEE() {
		return FEE;
	}
	public void setFEE(String fEE) {
		FEE = fEE;
	}
	public String getINQUIRY_FOR_THE_GOODS() {
		return INQUIRY_FOR_THE_GOODS;
	}
	public void setINQUIRY_FOR_THE_GOODS(String iNQUIRY_FOR_THE_GOODS) {
		INQUIRY_FOR_THE_GOODS = iNQUIRY_FOR_THE_GOODS;
	}
	public String getPERIOD_FOR_LOAN_APPLICATION() {
		return PERIOD_FOR_LOAN_APPLICATION;
	}
	public void setPERIOD_FOR_LOAN_APPLICATION(String pERIOD_FOR_LOAN_APPLICATION) {
		PERIOD_FOR_LOAN_APPLICATION = pERIOD_FOR_LOAN_APPLICATION;
	}
	public String getANY_OTHER_CAUSE() {
		return ANY_OTHER_CAUSE;
	}
	public void setANY_OTHER_CAUSE(String aNY_OTHER_CAUSE) {
		ANY_OTHER_CAUSE = aNY_OTHER_CAUSE;
	}
	public String getMORTGAGE() {
		return MORTGAGE;
	}
	public void setMORTGAGE(String mORTGAGE) {
		MORTGAGE = mORTGAGE;
	}
	public String getOVERDUE_INTEREST() {
		return OVERDUE_INTEREST;
	}
	public void setOVERDUE_INTEREST(String oVERDUE_INTEREST) {
		OVERDUE_INTEREST = oVERDUE_INTEREST;
	}
	public String getPERIOD_FOR_LOAN_PROVISION() {
		return PERIOD_FOR_LOAN_PROVISION;
	}
	public void setPERIOD_FOR_LOAN_PROVISION(String pERIOD_FOR_LOAN_PROVISION) {
		PERIOD_FOR_LOAN_PROVISION = pERIOD_FOR_LOAN_PROVISION;
	}
	public String getLENDING_RATE_DETAIL() {
		return LENDING_RATE_DETAIL;
	}
	public void setLENDING_RATE_DETAIL(String lENDING_RATE_DETAIL) {
		LENDING_RATE_DETAIL = lENDING_RATE_DETAIL;
	}
	public String getCOST_OF_BORROWING() {
		return COST_OF_BORROWING;
	}
	public void setCOST_OF_BORROWING(String cOST_OF_BORROWING) {
		COST_OF_BORROWING = cOST_OF_BORROWING;
	}
	public String getPAGE_URL() {
		return PAGE_URL;
	}
	public void setPAGE_URL(String pAGE_URL) {
		PAGE_URL = pAGE_URL;
	}
	public String getTARGET_CUSTOMER() {
		return TARGET_CUSTOMER;
	}
	public void setTARGET_CUSTOMER(String tARGET_CUSTOMER) {
		TARGET_CUSTOMER = tARGET_CUSTOMER;
	}
	public String getLOAN_PERIOD() {
		return LOAN_PERIOD;
	}
	public void setLOAN_PERIOD(String lOAN_PERIOD) {
		LOAN_PERIOD = lOAN_PERIOD;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public void decodeData() throws UnsupportedEncodingException {
			String encode = "utf8";
			PRIME_RATE  	            = URLDecoder.decode(PRIME_RATE, encode);
			REPAYMENT_METHOD            = URLDecoder.decode(REPAYMENT_METHOD, encode);
			NECESSARY_DOCUMENTS         = URLDecoder.decode(NECESSARY_DOCUMENTS, encode);
			CREDIT_LINE                 = URLDecoder.decode(CREDIT_LINE, encode);
			LENDING_RATE                = URLDecoder.decode(LENDING_RATE, encode);
			CANCELLATION_FEE            = URLDecoder.decode(CANCELLATION_FEE, encode);
			PRODUCT_NAME                = URLDecoder.decode(PRODUCT_NAME, encode);
			OUTLINE                     = URLDecoder.decode(OUTLINE, encode);
			FEE                         = URLDecoder.decode(FEE, encode);
			INQUIRY_FOR_THE_GOODS       = URLDecoder.decode(INQUIRY_FOR_THE_GOODS, encode); //
			PERIOD_FOR_LOAN_APPLICATION = URLDecoder.decode(PERIOD_FOR_LOAN_APPLICATION, encode);
			ANY_OTHER_CAUSE             = URLDecoder.decode(ANY_OTHER_CAUSE, encode); //
			MORTGAGE                    = URLDecoder.decode(MORTGAGE, encode); //
			PERIOD_FOR_LOAN_PROVISION   = URLDecoder.decode(PERIOD_FOR_LOAN_PROVISION, encode); //
			LENDING_RATE_DETAIL         = URLDecoder.decode(LENDING_RATE_DETAIL, encode); //
			OVERDUE_INTEREST            = URLDecoder.decode(OVERDUE_INTEREST, encode);
			COST_OF_BORROWING           = URLDecoder.decode(COST_OF_BORROWING, encode);
			PAGE_URL 			        = URLDecoder.decode(PAGE_URL, encode);
			TARGET_CUSTOMER             = URLDecoder.decode(TARGET_CUSTOMER, encode);
			LOAN_PERIOD                 = URLDecoder.decode(LOAN_PERIOD, encode);//
	}

	public String toString(){
		String result =
				"\ncd_coocon_goods			   =" +cd_coocon_goods
				+"\nseq			   			   =" +seq
				+"\nstatus			   		   =" +status
				+"\nnm_coocon_goods   		   =" +nm_coocon_goods
				+"\ncd_non_goods			   =" +cd_non_goods
				+"\ncd_type_fc				   =" +cd_type_fc
				+"\ncd_org					   =" +cd_org
				+"\ncd_type_goods			   =" +cd_type_goods
				+"\ncd_type_req				   =" +cd_type_req
				+"\nPRIME_RATE        		   =" +PRIME_RATE
				+"\nREPAYMENT_METHOD		   =" +REPAYMENT_METHOD
				+"\nREPAYMENT_METHOD           =" +REPAYMENT_METHOD
				+"\nNECESSARY_DOCUMENTS        =" +NECESSARY_DOCUMENTS
				+"\nCREDIT_LINE                =" +CREDIT_LINE
				+"\nLENDING_RATE               =" +LENDING_RATE
				+"\nCANCELLATION_FEE           =" +CANCELLATION_FEE
				+"\nPRODUCT_NAME               =" +PRODUCT_NAME
				+"\nOUTLINE                    =" +OUTLINE
				+"\nFEE                        =" +FEE
				+"\nINQUIRY_FOR_THE_GOODS      =" +INQUIRY_FOR_THE_GOODS
				+"\nPERIOD_FOR_LOAN_APPLICATION=" +PERIOD_FOR_LOAN_APPLICATION
				+"\nANY_OTHER_CAUSE            =" +ANY_OTHER_CAUSE
				+"\nMORTGAGE                   =" +MORTGAGE
				+"\nPERIOD_FOR_LOAN_PROVISION  =" +PERIOD_FOR_LOAN_PROVISION
				+"\nLENDING_RATE_DETAIL        =" +LENDING_RATE_DETAIL
				+"\nOVERDUE_INTEREST           =" +OVERDUE_INTEREST
				+"\nCOST_OF_BORROWING          =" +COST_OF_BORROWING
				+"\nTARGET_CUSTOMER            =" +TARGET_CUSTOMER
				+"\nAGE_URL           		   =" +PAGE_URL
				+"\nLOAN_PERIOD                =" +LOAN_PERIOD
				;
		return result;
	}

}