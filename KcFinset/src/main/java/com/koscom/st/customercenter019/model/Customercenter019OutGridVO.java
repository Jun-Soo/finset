package com.koscom.st.customercenter019.model;

/**
 * FINSET - 마이페이지/반대매매 출력 그리드 VO
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
public class Customercenter019OutGridVO {
	private String crdtIttCd=""; //여신사코드
	private String crdtIttNm=""; //여신사명
	private String mbrCd=""; //증권사코드
	private String mbrNm=""; //증권사명
	private String prdtCd=""; //상품코드
	private String prdtNm=""; //상품명
	private String cvrgOcrDttm=""; //발생일
	
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
	public String getCvrgOcrDttm() {
		return cvrgOcrDttm;
	}
	public void setCvrgOcrDttm(String cvrgOcrDttm) {
		this.cvrgOcrDttm = cvrgOcrDttm;
	}
}
