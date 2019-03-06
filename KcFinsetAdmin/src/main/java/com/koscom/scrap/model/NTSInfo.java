package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.scrap.model.sub.NTSBizCert;
import com.koscom.scrap.model.sub.NTSBizStatus;
import com.koscom.scrap.model.sub.NTSFinance;
import com.koscom.scrap.model.sub.NTSIncome;
import com.koscom.scrap.model.sub.NTSTaxPayment;
import com.koscom.scrap.model.sub.NTSVat;

/**
 * 국세청 스크래핑 VO
 * @author HSJ
 *
 */
public class NTSInfo implements Serializable {	// com.koscom.scrap.model.NTSInfo

	private static final long serialVersionUID = 122395226578923249L;

	private int SEQ_REQ;						// 일련번호 국세청
	
	private String INQUIRY_DIVISION;			// 조회구분
	
	private String TAXATION_START_MONTH; 		// 과세 시작년
	private String TAXATION_END_MONTH;  		// 과세 종료년
	
	private String RCPT_START_MONTH;
	private String RCPT_END_MONTH;
	
	private String TAXATION_YEAR;  				// 과세 과세연도
	
	private String CERT_DIVISION;  				// 증명구분
	
	private String OPEN_YN_CD;					// 공개여부코드
	
	private String ERROR_CODE;					// 에러코드
	private String ERROR_MESSAGE;				// 에러메세지
	
	private String INQUIRY_PATH;				// 조회경로
	
	private int SEQ_SCRAPING_RESULT; 		// 일련번호_스크래핑결과
	
	private NTSVat VAT;							// 부가가치세
	private NTSBizCert BIZCERT;					// 사업자등록증명
	private NTSBizStatus BIZSTATUS;				// 사업자상태조회
	private NTSTaxPayment TAXPAYMENT;			// 납부내역증명(납세사실증명)
	//private NTSIncome INCOME;					// 소득금액증명
	private List<NTSFinance> FINANCE;			// 재무제표
	
	private List<ScrRespIncomeDtlVO> INCOME;		// 소득금액증명
	private List<ScrRespCashReceiptVO> CASH_USE_HISTORY;	// 현금영수증

	public String getINQUIRY_PATH() {
		return INQUIRY_PATH;
	}

	public void setINQUIRY_PATH(String iNQUIRY_PATH) {
		INQUIRY_PATH = iNQUIRY_PATH;
	}

	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public String getINQUIRY_DIVISION() {
		return INQUIRY_DIVISION;
	}

	public void setINQUIRY_DIVISION(String iNQUIRY_DIVISION) {
		INQUIRY_DIVISION = iNQUIRY_DIVISION;
	}

	public String getTAXATION_START_MONTH() {
		return TAXATION_START_MONTH;
	}

	public void setTAXATION_START_MONTH(String tAXATION_START_MONTH) {
		TAXATION_START_MONTH = tAXATION_START_MONTH;
	}

	public String getTAXATION_END_MONTH() {
		return TAXATION_END_MONTH;
	}

	public void setTAXATION_END_MONTH(String tAXATION_END_MONTH) {
		TAXATION_END_MONTH = tAXATION_END_MONTH;
	}

	public String getRCPT_START_MONTH() {
		return RCPT_START_MONTH;
	}

	public void setRCPT_START_MONTH(String rCPT_START_MONTH) {
		RCPT_START_MONTH = rCPT_START_MONTH;
	}

	public String getRCPT_END_MONTH() {
		return RCPT_END_MONTH;
	}

	public void setRCPT_END_MONTH(String rCPT_END_MONTH) {
		RCPT_END_MONTH = rCPT_END_MONTH;
	}

	public String getTAXATION_YEAR() {
		return TAXATION_YEAR;
	}

	public void setTAXATION_YEAR(String tAXATION_YEAR) {
		TAXATION_YEAR = tAXATION_YEAR;
	}

	public String getCERT_DIVISION() {
		return CERT_DIVISION;
	}

	public void setCERT_DIVISION(String cERT_DIVISION) {
		CERT_DIVISION = cERT_DIVISION;
	}

	public String getOPEN_YN_CD() {
		return OPEN_YN_CD;
	}

	public void setOPEN_YN_CD(String oPEN_YN_CD) {
		OPEN_YN_CD = oPEN_YN_CD;
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

	public List<ScrRespIncomeDtlVO> getINCOME() {
		return INCOME;
	}

	public void setINCOME(List<ScrRespIncomeDtlVO> iNCOME) {
		INCOME = iNCOME;
	}

	public NTSVat getVAT() {
		return VAT;
	}

	public void setVAT(NTSVat vAT) {
		VAT = vAT;
	}

	public int getSEQ_SCRAPING_RESULT() {
		return SEQ_SCRAPING_RESULT;
	}

	public void setSEQ_SCRAPING_RESULT(int sEQ_SCRAPING_RESULT) {
		SEQ_SCRAPING_RESULT = sEQ_SCRAPING_RESULT;
	}

	public NTSTaxPayment getTAXPAYMENT() {
		return TAXPAYMENT;
	}

	public void setTAXPAYMENT(NTSTaxPayment tAXPAYMENT) {
		TAXPAYMENT = tAXPAYMENT;
	}

	public List<NTSFinance> getFINANCE() {
		return FINANCE;
	}

	public void setFINANCE(List<NTSFinance> fINANCE) {
		FINANCE = fINANCE;
	}

	public NTSBizCert getBIZCERT() {
		return BIZCERT;
	}

	public void setBIZCERT(NTSBizCert bIZCERT) {
		BIZCERT = bIZCERT;
	}

	public NTSBizStatus getBIZSTATUS() {
		return BIZSTATUS;
	}

	public void setBIZSTATUS(NTSBizStatus bIZSTATUS) {
		BIZSTATUS = bIZSTATUS;
	}
	
	public List<ScrRespCashReceiptVO> getCASH_USE_HISTORY() {
		return CASH_USE_HISTORY;
	}

	public void setCASH_USE_HISTORY(List<ScrRespCashReceiptVO> cASH_USE_HISTORY) {
		CASH_USE_HISTORY = cASH_USE_HISTORY;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
