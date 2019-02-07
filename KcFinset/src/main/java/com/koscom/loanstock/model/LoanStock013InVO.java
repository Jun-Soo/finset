package com.koscom.loanstock.model;

import com.koscom.common.fulltext.FulltextVO;

/**
 * FINSET - 상품 조회/조회결과 상세 입력 VO
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
public class LoanStock013InVO extends FulltextVO <Object> {
	private String mbrCd; //증권사코드
	private String crdtIttCd; //여신사코드
	private String prdtCd; //상품코드
	
	public String getCrdtIttCd() {
		return crdtIttCd;
	}
	public void setCrdtIttCd(String crdtIttCd) {
		this.crdtIttCd = crdtIttCd;
	}
	public String getMbrCd() {
		return mbrCd;
	}
	public void setMbrCd(String mbrCd) {
		this.mbrCd = mbrCd;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}


	

}
