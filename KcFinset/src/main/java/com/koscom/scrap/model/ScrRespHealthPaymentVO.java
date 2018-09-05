package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ScrRespHealthPaymentVO implements Serializable{
	private static final long serialVersionUID = 8430628754613070608L;
	protected String no_person= "";				//회원고유번호
	@SerializedName("NO_PAYER")
	protected String no_payer= "";				//납부자번호
	@SerializedName("NO_PBLS")
	protected String no_pbls= "";				//발급번호
	@SerializedName("MEMBER_DIVISION")
	protected String member_division= "";		//가입자구분
	@SerializedName("NM_COMP")
	protected String nm_comp= "";				//회사명
	@SerializedName("YMD_GAIN")
	protected String ymd_gain= "";				//취득일자
	@SerializedName("YMD_LOSS")
	protected String ymd_loss= "";				//상실일자
	@SerializedName("BELONG_BRANCH")
	protected String belong_branch= "";			//소속지사
	@SerializedName("NM_MEMBER")
	protected String nm_member= "";				//가입자성명
	@SerializedName("BGN")
	protected String bgn= "";					//생년월일
	protected String dt_frt= "";				//등록일시
	
	protected List<ScrRespHealthPaymentdtlVO> PAYMENT_DTL;	//납부상세내역
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_payer() {
		return no_payer;
	}
	public void setNo_payer(String no_payer) {
		this.no_payer = no_payer;
	}
	public String getNo_pbls() {
		return no_pbls;
	}
	public void setNo_pbls(String no_pbls) {
		this.no_pbls = no_pbls;
	}
	public String getMember_division() {
		return member_division;
	}
	public void setMember_division(String member_division) {
		this.member_division = member_division;
	}
	public String getNm_comp() {
		return nm_comp;
	}
	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}
	public String getYmd_gain() {
		return ymd_gain;
	}
	public void setYmd_gain(String ymd_gain) {
		this.ymd_gain = ymd_gain;
	}
	public String getYmd_loss() {
		return ymd_loss;
	}
	public void setYmd_loss(String ymd_loss) {
		this.ymd_loss = ymd_loss;
	}
	public String getBelong_branch() {
		return belong_branch;
	}
	public void setBelong_branch(String belong_branch) {
		this.belong_branch = belong_branch;
	}
	public String getNm_member() {
		return nm_member;
	}
	public void setNm_member(String nm_member) {
		this.nm_member = nm_member;
	}
	public String getBgn() {
		return bgn;
	}
	public void setBgn(String bgn) {
		this.bgn = bgn;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public List<ScrRespHealthPaymentdtlVO> getPAYMENT_DTL() {
		return PAYMENT_DTL;
	}
	public void setPAYMENT_DTL(List<ScrRespHealthPaymentdtlVO> pAYMENT_DTL) {
		PAYMENT_DTL = pAYMENT_DTL;
	}
}
