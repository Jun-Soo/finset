package com.koscom.st.debt018.model;

/**
 * FINSET - 부채관리/부채상세 출력 그리드 VO
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
public class Debt018OutGridVO {
	private String isuNo=""; //종목코드
	private String shtnIsuNm=""; //종목명
	private String evalAmt=""; //평가금액
	private String curPrc=""; //현재가
	private String updnRat=""; //등락률
	private String buyPrc=""; //평단가
	private String hldQty=""; //보유수량
	private String evalPnl=""; //평가손익
	private String evalRat=""; //평가손익률
	
	public String getIsuNo() {
		return isuNo;
	}
	public void setIsuNo(String isuNo) {
		this.isuNo = isuNo;
	}
	public String getShtnIsuNm() {
		return shtnIsuNm;
	}
	public void setShtnIsuNm(String shtnIsuNm) {
		this.shtnIsuNm = shtnIsuNm;
	}
	public String getEvalAmt() {
		return evalAmt;
	}
	public void setEvalAmt(String evalAmt) {
		this.evalAmt = evalAmt;
	}
	public String getCurPrc() {
		return curPrc;
	}
	public void setCurPrc(String curPrc) {
		this.curPrc = curPrc;
	}
	public String getUpdnRat() {
		return updnRat;
	}
	public void setUpdnRat(String updnRat) {
		this.updnRat = updnRat;
	}
	public String getBuyPrc() {
		return buyPrc;
	}
	public void setBuyPrc(String buyPrc) {
		this.buyPrc = buyPrc;
	}
	public String getHldQty() {
		return hldQty;
	}
	public void setHldQty(String hldQty) {
		this.hldQty = hldQty;
	}
	public String getEvalPnl() {
		return evalPnl;
	}
	public void setEvalPnl(String evalPnl) {
		this.evalPnl = evalPnl;
	}
	public String getEvalRat() {
		return evalRat;
	}
	public void setEvalRat(String evalRat) {
		this.evalRat = evalRat;
	}

}
