package com.koscom.loanstock.model;

import com.koscom.common.fulltext.FulltextVO;

/**
 * 증권사BIZ - 스탁론상품 조회 출력 VO
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
public class LoanStock012OutVO extends FulltextVO <LoanStock012OutGridVO> {
	private String mbrCd=""; //증권사코드
	private String mbrNm=""; //증권사명
	
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

}
