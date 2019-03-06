package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BusinessDay implements Serializable {
	private static final long serialVersionUID = -4635472856635220379L;

	private String cd_fc;
	private String ymd;					//날짜yyyymmdd
	private String time_start;			//시작시각
	private String time_end;			//종료시각
	private String cd_reason;			//사유
	private String memo;				//메모
	private String yn_cutoff;			//차단여부
	private String yn_holiday;			//휴일여부
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	private String id_lst;				// 최종수정자
	private String dt_lst;				// 최종수정일
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	
	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getCd_reason() {
		return cd_reason;
	}

	public void setCd_reason(String cd_reason) {
		this.cd_reason = cd_reason;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getYn_cutoff() {
		return yn_cutoff;
	}

	public void setYn_cutoff(String yn_cutoff) {
		this.yn_cutoff = yn_cutoff;
	}

	public String getYn_holiday() {
		return yn_holiday;
	}

	public void setYn_holiday(String yn_holiday) {
		this.yn_holiday = yn_holiday;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public String getId_lst() {
		return id_lst;
	}

	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}

	public String getDt_lst() {
		return dt_lst;
	}

	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	
}
