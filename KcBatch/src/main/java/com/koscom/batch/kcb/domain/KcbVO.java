package com.koscom.batch.kcb.domain;

import java.io.Serializable;
import java.util.List;

public class KcbVO implements Serializable {
	
	public static final long serialVersionUID = -8912406207356126390L;
	
	public String 			no_person; 			//회원관리번호
	public String 			nm_person; 			//회원명
	public String 			kcb_id; 			//KCB ID
	public String 			hp;					//회원전화번호
	public String 			cd_req; 			//요청구분
	public String 			seq_scraping_result;//스크래핑일련번호

	/** 공통 **/
	public String 			dt_pbls;		//발급일자
	public String 			no_pbls;		//발급번호
	
	/** 소득금액증명정보 **/
	public String 			cert_division;	//소득금액증명서구분코드
	
	/** 건강보험납부정보 **/
	public String 			no_payer;		//납부자번호
	public String 			member_division;//가입자구분
	public String 			nm_comp;		//상호명
	
	/** 국민연금납부정보 **/
	public String 			no_verify;				//검증번호
	public String 			cnt_month_pay;			//총보험료납부월수
	public String 			amt_pay;				//총보험료납부금액
	public String 			cnt_month_not_pay;		//총보험료미납월수
	public String 			amt_not_pay;			//총보험료미납금액
	public String 			cnt_month_cannot_pay;	//총납부불가월수
	public String 			amt_cannot_pay;			//총납부불가금액
	public String 			amt_pay_return;			//반납금액
	public String 			amt_pay_primium;		//추가납부금액
	
	public List<KcbInfoVO>		KcbInfoList;	//스크래핑상세리스트
	public String status;
	public String cd_err;
	public String bit_err;
	
	public KcbVO() {}

	public String getNo_person() {
		return no_person;
	}

	public KcbVO(String no_person) {
		this.no_person = no_person;
	}
	
	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	
	public String getKcb_id() {
		return kcb_id;
	}

	public void setKcb_id(String kcb_id) {
		this.kcb_id = kcb_id;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCd_req() {
		return cd_req;
	}

	public void setCd_req(String cd_req) {
		this.cd_req = cd_req;
	}

	public String getSeq_scraping_result() {
		return seq_scraping_result;
	}

	public void setSeq_scraping_result(String seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getDt_pbls() {
		return dt_pbls;
	}

	public void setDt_pbls(String dt_pbls) {
		this.dt_pbls = dt_pbls;
	}

	public String getNo_pbls() {
		return no_pbls;
	}

	public void setNo_pbls(String no_pbls) {
		this.no_pbls = no_pbls;
	}

	public String getCert_division() {
		return cert_division;
	}

	public void setCert_division(String cert_division) {
		this.cert_division = cert_division;
	}

	public String getNo_payer() {
		return no_payer;
	}

	public void setNo_payer(String no_payer) {
		this.no_payer = no_payer;
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

	public List<KcbInfoVO> getKcbInfoList() {
		return KcbInfoList;
	}

	public void setKcbInfoList(List<KcbInfoVO> kcbInfoList) {
		KcbInfoList = kcbInfoList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCd_err() {
		return cd_err;
	}

	public void setCd_err(String cd_err) {
		this.cd_err = cd_err;
	}

	public String getBit_err() {
		return bit_err;
	}

	public void setBit_err(String bit_err) {
		this.bit_err = bit_err;
	}
	
	public String getNo_verify() {
		return no_verify;
	}

	public void setNo_verify(String no_verify) {
		this.no_verify = no_verify;
	}

	public String getCnt_month_pay() {
		return cnt_month_pay;
	}

	public void setCnt_month_pay(String cnt_month_pay) {
		this.cnt_month_pay = cnt_month_pay;
	}

	public String getAmt_pay() {
		return amt_pay;
	}

	public void setAmt_pay(String amt_pay) {
		this.amt_pay = amt_pay;
	}

	public String getCnt_month_not_pay() {
		return cnt_month_not_pay;
	}

	public void setCnt_month_not_pay(String cnt_month_not_pay) {
		this.cnt_month_not_pay = cnt_month_not_pay;
	}

	public String getAmt_not_pay() {
		return amt_not_pay;
	}

	public void setAmt_not_pay(String amt_not_pay) {
		this.amt_not_pay = amt_not_pay;
	}

	public String getCnt_month_cannot_pay() {
		return cnt_month_cannot_pay;
	}

	public void setCnt_month_cannot_pay(String cnt_month_cannot_pay) {
		this.cnt_month_cannot_pay = cnt_month_cannot_pay;
	}

	public String getAmt_cannot_pay() {
		return amt_cannot_pay;
	}

	public void setAmt_cannot_pay(String amt_cannot_pay) {
		this.amt_cannot_pay = amt_cannot_pay;
	}

	public String getAmt_pay_return() {
		return amt_pay_return;
	}

	public void setAmt_pay_return(String amt_pay_return) {
		this.amt_pay_return = amt_pay_return;
	}

	public String getAmt_pay_primium() {
		return amt_pay_primium;
	}

	public void setAmt_pay_primium(String amt_pay_primium) {
		this.amt_pay_primium = amt_pay_primium;
	}

	@Override
	public String toString() {
		return "KcbVO [no_person=" + no_person
				+ "]";
	}
}
