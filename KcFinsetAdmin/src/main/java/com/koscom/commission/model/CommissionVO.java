package com.koscom.commission.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.CommissionInfo;
import com.koscom.domain.GoodsInfo;

public class CommissionVO extends CommissionInfo implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
//	private static final long serialVersionUID = -5283835109034749303L;
	private String cd_fc;
	private String cd_goods;
	private String nm_goods;
	private String rto_loan;
	private String amt_approval;
	private String ymd_approval;
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
	public String getRto_loan() {
		return rto_loan;
	}
	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}
	public String getAmt_approval() {
		return amt_approval;
	}
	public String getYmd_approval() {
		return ymd_approval;
	}
	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}
	public void setYmd_approval(String ymd_approval) {
		this.ymd_approval = ymd_approval;
	}
	
}