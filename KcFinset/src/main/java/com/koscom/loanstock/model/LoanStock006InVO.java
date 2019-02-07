package com.koscom.loanstock.model;

import java.util.List;

import com.koscom.common.fulltext.FulltextVO;

/**
 * 증권사BIZ - 스탁론상품 조회 입력 VO
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
public class LoanStock006InVO extends FulltextVO <Object> {
	private String mbrCd = ""; //증권기관코드
	private List<LoanStock006InVO> inVOList = null; //voList
	
	public String getMbrCd() {
		return mbrCd;
	}
	public void setMbrCd(String mbrCd) {
		this.mbrCd = mbrCd;
	}
	public List<LoanStock006InVO> getInVOList() {
		return inVOList;
	}
	public void setInVOList(List<LoanStock006InVO> inVOList) {
		this.inVOList = inVOList;
	}
	
	
}
