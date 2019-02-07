package com.koscom.st.customercenter019.model;

import com.koscom.common.fulltext.FulltextVO;

/**
 * FINSET - 마이페이지/반대매매 입력 VO
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
public class Customercenter019InVO extends FulltextVO <Object> {
	private String mbrCd=""; //증권사코드
	private String acntNo=""; //계좌번호
	private String nextKey = super.DEFAULT_NEXTKEY; //nextkey
	
	public String getMbrCd() {
		return mbrCd;
	}
	public void setMbrCd(String mbrCd) {
		this.mbrCd = mbrCd;
	}
	public String getAcntNo() {
		return acntNo;
	}
	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}
	public String getNextKey() {
		return nextKey;
	}
	public void setNextKey(String nextKey) {
		this.nextKey = nextKey;
	}
}
