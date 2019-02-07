package com.koscom.loanstock.model;

/**
 * 증권사BIZ - 스탁론상품 조회 출력 그리드 VO
 * @author EndFoint 개발팀 김학진
 * @since 2018.08.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2018.08.01 김학진 최초 생성
 *  </pre>
*/
public class LoanStock012OutGridVO {
	private String crdtIttCd=""; //여신사코드
	private String crdtIttNm=""; //여신사명
	private String prdtCd=""; //상품코드
	private String prdtNm=""; //상품명
	private String invstPlanTp=""; //투자유형
	private String loanTerm=""; //대출기간
	private String rfundMthd=""; //상환방식
	private double loanIntrstRt=0; //대출금리
	private double ovdIntrstRt=0; //연체이자율
	private long loanMinLmtAmt=0; //대출최소한도
	private long loanMaxLmtAmt=0; //대출최대한도
	private String loanAblYn=""; //대출가능여부
	
	public String getCrdtIttCd() {
		return crdtIttCd;
	}
	public void setCrdtIttCd(String crdtIttCd) {
		this.crdtIttCd = crdtIttCd;
	}
	public String getCrdtIttNm() {
		return crdtIttNm;
	}
	public void setCrdtIttNm(String crdtIttNm) {
		this.crdtIttNm = crdtIttNm;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
	}
	public String getInvstPlanTp() {
		return invstPlanTp;
	}
	public void setInvstPlanTp(String invstPlanTp) {
		this.invstPlanTp = invstPlanTp;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	public String getRfundMthd() {
		return rfundMthd;
	}
	public void setRfundMthd(String rfundMthd) {
		this.rfundMthd = rfundMthd;
	}
	public double getLoanIntrstRt() {
		return loanIntrstRt;
	}
	public void setLoanIntrstRt(double loanIntrstRt) {
		this.loanIntrstRt = loanIntrstRt;
	}
	public double getOvdIntrstRt() {
		return ovdIntrstRt;
	}
	public void setOvdIntrstRt(double ovdIntrstRt) {
		this.ovdIntrstRt = ovdIntrstRt;
	}
	public long getLoanMinLmtAmt() {
		return loanMinLmtAmt;
	}
	public void setLoanMinLmtAmt(long loanMinLmtAmt) {
		this.loanMinLmtAmt = loanMinLmtAmt;
	}
	public long getLoanMaxLmtAmt() {
		return loanMaxLmtAmt;
	}
	public void setLoanMaxLmtAmt(long loanMaxLmtAmt) {
		this.loanMaxLmtAmt = loanMaxLmtAmt;
	}
	public String getLoanAblYn() {
		return loanAblYn;
	}
	public void setLoanAblYn(String loanAblYn) {
		this.loanAblYn = loanAblYn;
	}
}
