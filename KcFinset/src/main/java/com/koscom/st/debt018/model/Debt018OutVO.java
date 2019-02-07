package com.koscom.st.debt018.model;

import com.koscom.common.fulltext.FulltextVO;

/**
 * FINSET - 부채관리/부채상세 출력 VO
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
public class Debt018OutVO extends FulltextVO<Debt018OutGridVO> {
	private String loanSeq=""; //대출일련번호
	private String lastUpdtDt=""; //최종갱신일자
	private String crdtIttCd=""; //여신사코드
	private String crdtIttNm=""; //여신사명
	private String mbrCd=""; //증권사코드
	private String mbrNm=""; //증권사명
	private String prdtCd=""; //상품코드
	private String prdtNm=""; //상품명
	private String mobilNo=""; //핸드폰번호
	private String smsNotiYn=""; //SMS통지여부
	private String acntActvTp=""; //계좌활동구분
	private String regDt=""; //등록일자
	private String closeDt=""; //폐쇄일자
	private String acntStatCd=""; //계좌상태코드
	private String lscutStat=""; //로스컷상태
	private String ovdOcrDt=""; //연체발생일
	private String trdRestrcPtnCode=""; //거래제한유형코드
	private String loanTp=""; //대출구분
	private String loanSrtDt=""; //대출시작일자
	private String loadEndDt=""; //대출종료일자
	private String expExtndAblTp=""; //만기연장가능여부
	private long lscutSetRt=0; //로스컷설정비율
	private long mnyOutAblRt=0; //출금가능비율
	private long expExtndAblRt=0; //만기연장가능비율
	private long intrstOutAblRt=0; //이자출금가능비율
	private long acntResumRt=0; //계좌재개비율
	private long ptflRt=0; //포트폴리오비율
	private long loanLmtAmt=0; //대출한도
	private long loanAmt=0; //대출금액
	private long acmLoanAmt=0; //누적대출금액
	private long addLoanAbleAmt=0; //추가대출가능금액
	private long ordAblMnyInAmt=0; //재매매가능입금액
	private String lscutChkTm=""; //로스컷체크일시
	private String lscutOcrDttm=""; //로스컷발생일
	private String lscutOrdDttm=""; //로스컷주문일
	private String tstAcntTp=""; //테스트계좌구분
	private String frcLscutTp=""; //강제로스컷구분
	private String frcLscutRsn=""; //강제로스컷사유
	private String mnyoutAbleAmt=""; //인출가능금액
	private String loanIntrstRt=""; //금리
	private String rfundMthd=""; //상환방식
	private String termDays=""; //잔여기간
	private String isuInvstLmt=""; //동일종목투자
	private String totEvalAmt=""; //총평가금
	private String assetEvalAmt=""; //주식평가금
	private String dps=""; //예수금
	private double ernrat=0; //수익률
	private String ernAmt=""; //수익금액
	private String nextKey=""; //nextkey

	
	public String getLoanSeq() {
		return loanSeq;
	}
	public void setLoanSeq(String loanSeq) {
		this.loanSeq = loanSeq;
	}
	public String getLastUpdtDt() {
		return lastUpdtDt;
	}
	public void setLastUpdtDt(String lastUpdtDt) {
		this.lastUpdtDt = lastUpdtDt;
	}
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
	public String getMbrCd() {
		return mbrCd;
	}
	public void setMbrCd(String mbrCd) {
		this.mbrCd = mbrCd;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
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
	public String getMobilNo() {
		return mobilNo;
	}
	public void setMobilNo(String mobilNo) {
		this.mobilNo = mobilNo;
	}
	public String getSmsNotiYn() {
		return smsNotiYn;
	}
	public void setSmsNotiYn(String smsNotiYn) {
		this.smsNotiYn = smsNotiYn;
	}
	public String getAcntActvTp() {
		return acntActvTp;
	}
	public void setAcntActvTp(String acntActvTp) {
		this.acntActvTp = acntActvTp;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getCloseDt() {
		return closeDt;
	}
	public void setCloseDt(String closeDt) {
		this.closeDt = closeDt;
	}
	public String getAcntStatCd() {
		return acntStatCd;
	}
	public void setAcntStatCd(String acntStatCd) {
		this.acntStatCd = acntStatCd;
	}
	public String getLscutStat() {
		return lscutStat;
	}
	public void setLscutStat(String lscutStat) {
		this.lscutStat = lscutStat;
	}
	public String getOvdOcrDt() {
		return ovdOcrDt;
	}
	public void setOvdOcrDt(String ovdOcrDt) {
		this.ovdOcrDt = ovdOcrDt;
	}
	public String getTrdRestrcPtnCode() {
		return trdRestrcPtnCode;
	}
	public void setTrdRestrcPtnCode(String trdRestrcPtnCode) {
		this.trdRestrcPtnCode = trdRestrcPtnCode;
	}
	public String getLoanTp() {
		return loanTp;
	}
	public void setLoanTp(String loanTp) {
		this.loanTp = loanTp;
	}
	public String getLoanSrtDt() {
		return loanSrtDt;
	}
	public void setLoanSrtDt(String loanSrtDt) {
		this.loanSrtDt = loanSrtDt;
	}
	public String getLoadEndDt() {
		return loadEndDt;
	}
	public void setLoadEndDt(String loadEndDt) {
		this.loadEndDt = loadEndDt;
	}
	public String getExpExtndAblTp() {
		return expExtndAblTp;
	}
	public void setExpExtndAblTp(String expExtndAblTp) {
		this.expExtndAblTp = expExtndAblTp;
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
	public long getIntrstOutAblRt() {
		return intrstOutAblRt;
	}
	public void setIntrstOutAblRt(long intrstOutAblRt) {
		this.intrstOutAblRt = intrstOutAblRt;
	}
	public long getAcntResumRt() {
		return acntResumRt;
	}
	public void setAcntResumRt(long acntResumRt) {
		this.acntResumRt = acntResumRt;
	}
	public long getPtflRt() {
		return ptflRt;
	}
	public void setPtflRt(long ptflRt) {
		this.ptflRt = ptflRt;
	}
	public long getLoanLmtAmt() {
		return loanLmtAmt;
	}
	public void setLoanLmtAmt(long loanLmtAmt) {
		this.loanLmtAmt = loanLmtAmt;
	}
	public long getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(long loanAmt) {
		this.loanAmt = loanAmt;
	}
	public long getAcmLoanAmt() {
		return acmLoanAmt;
	}
	public void setAcmLoanAmt(long acmLoanAmt) {
		this.acmLoanAmt = acmLoanAmt;
	}
	public long getAddLoanAbleAmt() {
		return addLoanAbleAmt;
	}
	public void setAddLoanAbleAmt(long addLoanAbleAmt) {
		this.addLoanAbleAmt = addLoanAbleAmt;
	}
	public long getOrdAblMnyInAmt() {
		return ordAblMnyInAmt;
	}
	public void setOrdAblMnyInAmt(long ordAblMnyInAmt) {
		this.ordAblMnyInAmt = ordAblMnyInAmt;
	}
	public String getLscutChkTm() {
		return lscutChkTm;
	}
	public void setLscutChkTm(String lscutChkTm) {
		this.lscutChkTm = lscutChkTm;
	}
	public String getLscutOcrDttm() {
		return lscutOcrDttm;
	}
	public void setLscutOcrDttm(String lscutOcrDttm) {
		this.lscutOcrDttm = lscutOcrDttm;
	}
	public String getLscutOrdDttm() {
		return lscutOrdDttm;
	}
	public void setLscutOrdDttm(String lscutOrdDttm) {
		this.lscutOrdDttm = lscutOrdDttm;
	}
	public String getTstAcntTp() {
		return tstAcntTp;
	}
	public void setTstAcntTp(String tstAcntTp) {
		this.tstAcntTp = tstAcntTp;
	}
	public String getFrcLscutTp() {
		return frcLscutTp;
	}
	public void setFrcLscutTp(String frcLscutTp) {
		this.frcLscutTp = frcLscutTp;
	}
	public String getFrcLscutRsn() {
		return frcLscutRsn;
	}
	public void setFrcLscutRsn(String frcLscutRsn) {
		this.frcLscutRsn = frcLscutRsn;
	}
	public String getMnyoutAbleAmt() {
		return mnyoutAbleAmt;
	}
	public void setMnyoutAbleAmt(String mnyoutAbleAmt) {
		this.mnyoutAbleAmt = mnyoutAbleAmt;
	}
	public String getLoanIntrstRt() {
		return loanIntrstRt;
	}
	public void setLoanIntrstRt(String loanIntrstRt) {
		this.loanIntrstRt = loanIntrstRt;
	}
	public String getRfundMthd() {
		return rfundMthd;
	}
	public void setRfundMthd(String rfundMthd) {
		this.rfundMthd = rfundMthd;
	}
	public String getTermDays() {
		return termDays;
	}
	public void setTermDays(String termDays) {
		this.termDays = termDays;
	}
	public String getIsuInvstLmt() {
		return isuInvstLmt;
	}
	public void setIsuInvstLmt(String isuInvstLmt) {
		this.isuInvstLmt = isuInvstLmt;
	}
	public String getTotEvalAmt() {
		return totEvalAmt;
	}
	public void setTotEvalAmt(String totEvalAmt) {
		this.totEvalAmt = totEvalAmt;
	}
	public String getAssetEvalAmt() {
		return assetEvalAmt;
	}
	public void setAssetEvalAmt(String assetEvalAmt) {
		this.assetEvalAmt = assetEvalAmt;
	}
	public String getDps() {
		return dps;
	}
	public void setDps(String dps) {
		this.dps = dps;
	}
	public double getErnrat() {
		return ernrat;
	}
	public void setErnrat(double ernrat) {
		this.ernrat = ernrat;
	}
	public String getErnAmt() {
		return ernAmt;
	}
	public void setErnAmt(String ernAmt) {
		this.ernAmt = ernAmt;
	}
	public String getNextKey() {
		return nextKey;
	}
	public void setNextKey(String nextKey) {
		this.nextKey = nextKey;
	}
}
