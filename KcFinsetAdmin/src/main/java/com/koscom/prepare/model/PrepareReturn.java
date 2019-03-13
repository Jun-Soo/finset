package com.koscom.prepare.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 매체사 리턴 모델
 */

public class PrepareReturn {
	
	private String seq;						//매체사 신청서 고유번호
	private String ymd;						//처리일자
	private String his;						//처리시간
	private String ymd_approval;			//승인일자
	private String memo;					//메모
	private String status;					//등록 처리 결과 상태
	private String amt;						//승인금액
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getHis() {
		return his;
	}
	public void setHis(String his) {
		this.his = his;
	}
	public String getYmd_approval() {
		return ymd_approval;
	}
	public void setYmd_approval(String ymd_approval) {
		this.ymd_approval = ymd_approval;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	

}
