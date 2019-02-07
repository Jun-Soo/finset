package com.koscom.loanstock.model;

import com.koscom.common.fulltext.FulltextVO;

/**
 * FINSET - 상품 조회/조회결과 상세 출력 VO
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
public class LoanStock013OutVO extends FulltextVO<LoanStock013OutVO> {
	private String prdtNm=""; //상품명
	private String crdtIttNm=""; //여신사명
	private String mbrNm=""; //증권사명
	private String invstPlanTp=""; //투자유형 0:단기분산, 1:장기분산, 2:단기집중, 3:장기집중
	private String minusTp=""; //마이너스구분 0-불가, 1-가능
	private String regDt=""; //등록일자
	private String prdtStrDt=""; //상품개시일자
	private String prdtEndDt=""; //상품만기일자
	private String loanTerm=""; //대출기간
	private String rfundMthd=""; //상환방식 0-만기일시, 1-만기
	private double avgIntrstRt=0; //평균금리
	private double hndIntrstRtt=0; //취급금리
	private double loanIntrstRt=0; //대출금리
	private double ovdIntrstRt=0; //연체이자율
	private double extndIntrstRt=0; //연장금리
	private double rfundRat=0; //중도상환율
	private long stmptax=0; //인지세
	private String isuCmpstRat=""; //한종목집중율
	private String intrstRatTp=""; //금리구분 0-고정금리, 1-변동금리
	private long loanMinLmtAmt=0; //대출최소한도
	private long loanMaxLmtAmt=0; //대출최대한도
	private String intrmdFeeTp=""; //중개료구분 0-정율, 1-정액
	private double intrmdFeeRt=0; //중개요율
	private long intrmdFee=0; //중개요금
	private long isuInvstLmt=0; //한족목투자한도
	private long lscutSetRt=0; //로스컷비율
	private long mnyOutAblRt=0; //현금인출비율  출금가능 비율로 하면 됨
	private long expExtndAblRt=0; //만기연장비율
	
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
	}
	public String getCrdtIttNm() {
		return crdtIttNm;
	}
	public void setCrdtIttNm(String crdtIttNm) {
		this.crdtIttNm = crdtIttNm;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getInvstPlanTp() {
		return invstPlanTp;
	}
	public void setInvstPlanTp(String invstPlanTp) {
		this.invstPlanTp = invstPlanTp;
	}
	public String getMinusTp() {
		return minusTp;
	}
	public void setMinusTp(String minusTp) {
		this.minusTp = minusTp;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getPrdtStrDt() {
		return prdtStrDt;
	}
	public void setPrdtStrDt(String prdtStrDt) {
		this.prdtStrDt = prdtStrDt;
	}
	public String getPrdtEndDt() {
		return prdtEndDt;
	}
	public void setPrdtEndDt(String prdtEndDt) {
		this.prdtEndDt = prdtEndDt;
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
	public double getAvgIntrstRt() {
		return avgIntrstRt;
	}
	public void setAvgIntrstRt(double avgIntrstRt) {
		this.avgIntrstRt = avgIntrstRt;
	}
	public double getHndIntrstRtt() {
		return hndIntrstRtt;
	}
	public void setHndIntrstRtt(double hndIntrstRtt) {
		this.hndIntrstRtt = hndIntrstRtt;
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
	public double getExtndIntrstRt() {
		return extndIntrstRt;
	}
	public void setExtndIntrstRt(double extndIntrstRt) {
		this.extndIntrstRt = extndIntrstRt;
	}
	public double getRfundRat() {
		return rfundRat;
	}
	public void setRfundRat(double rfundRat) {
		this.rfundRat = rfundRat;
	}
	public long getStmptax() {
		return stmptax;
	}
	public void setStmptax(long stmptax) {
		this.stmptax = stmptax;
	}
	public String getIsuCmpstRat() {
		return isuCmpstRat;
	}
	public void setIsuCmpstRat(String isuCmpstRat) {
		this.isuCmpstRat = isuCmpstRat;
	}
	public String getIntrstRatTp() {
		return intrstRatTp;
	}
	public void setIntrstRatTp(String intrstRatTp) {
		this.intrstRatTp = intrstRatTp;
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
	public String getIntrmdFeeTp() {
		return intrmdFeeTp;
	}
	public void setIntrmdFeeTp(String intrmdFeeTp) {
		this.intrmdFeeTp = intrmdFeeTp;
	}
	public double getIntrmdFeeRt() {
		return intrmdFeeRt;
	}
	public void setIntrmdFeeRt(double intrmdFeeRt) {
		this.intrmdFeeRt = intrmdFeeRt;
	}
	public long getIntrmdFee() {
		return intrmdFee;
	}
	public void setIntrmdFee(long intrmdFee) {
		this.intrmdFee = intrmdFee;
	}
	public long getIsuInvstLmt() {
		return isuInvstLmt;
	}
	public void setIsuInvstLmt(long isuInvstLmt) {
		this.isuInvstLmt = isuInvstLmt;
	}
	public long getLscutSetRt() {
		return lscutSetRt;
	}
	public void setLscutSetRt(long lscutSetRt) {
		this.lscutSetRt = lscutSetRt;
	}
	public long getMnyOutAblRt() {
		return mnyOutAblRt;
	}
	public void setMnyOutAblRt(long mnyOutAblRt) {
		this.mnyOutAblRt = mnyOutAblRt;
	}
	public long getExpExtndAblRt() {
		return expExtndAblRt;
	}
	public void setExpExtndAblRt(long expExtndAblRt) {
		this.expExtndAblRt = expExtndAblRt;
	}
}
