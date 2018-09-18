package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeVO implements Serializable{

	private static final long serialVersionUID = 2461482684071336886L;

	private String 	no_person;					// 회원관리번호
	private int		seq_consume;				// 일련번호
	private String 	type_in_out;				// 수입지출구분 - 01:수입, 02:지출
	private String 	means_consume;				// 소비수단항목 - 01:카드, 02:현금, 03:입출금계좌
	private String 	cd_fc;						// 금융사코드
	private String	nm_card;					// 카드(은행)명
	private String	no_card;					// 카드(계좌)번호
	private String	type_card;					// 카드 분류
	private String 	dt_trd;						// 거래일자
	private String 	tm_trd;						// 거래시간
	private String	no_biz;						// 업종 번호
	private String	nm_biz;						// 업종명
	private String 	cd_class;					// 분류코드
	private String	nm_class;					// 분류명
	private String 	cd_type;					// 항목코드
	private String	nm_type;					// 항목명
	private String	cd_consume_class;			// 소비항목코드 - 데이터를 집어넣을 때 사용하기 위함
	private String 	contents;					// 내용
	private String	memo;						// 메모
	private String	grade;						// 평가
	private String 	amt_in_out;					// 금액
	private String 	no_approval;				// 승인번호
	private String 	mon_installment;			// 할부기간
	private String	mon_remaining;				// 잔여기간
	private String 	yn_pay_installment;			// 할부완납여부
	private String 	yn_cancel;					// 취소여부
	private String	yn_delete;					// 삭제여부
	private String 	yn_auto;					// 자동등록여부
	private String	yn_budget_except;			// 예산 제외 여부
	private String 	id_frt;						// 최초입력아이디
	private Date 	dt_frt;						// 최초입력시간
	private String 	id_lst;						// 최종수정아이디
	private Date 	dt_lst;						// 최종수정시간

	public ConsumeVO() {
	}
	public ConsumeVO(String no_person, int seq_consume, String type_in_out,
			String means_consume, String cd_fc, String nm_card, String no_card,
			String type_card, String dt_trd, String tm_trd, String no_biz,
			String nm_biz, String cd_class, String nm_class, String cd_type,
			String nm_type, String cd_consume_class, String contents,
			String memo, String grade, String amt_in_out, String no_approval,
			String mon_installment, String mon_remaining,
			String yn_pay_installment, String yn_cancel, String yn_delete,
			String yn_auto, String yn_budget_except, String id_frt,
			Date dt_frt, String id_lst, Date dt_lst) {
		this.no_person = no_person;
		this.seq_consume = seq_consume;
		this.type_in_out = type_in_out;
		this.means_consume = means_consume;
		this.cd_fc = cd_fc;
		this.nm_card = nm_card;
		this.no_card = no_card;
		this.type_card = type_card;
		this.dt_trd = dt_trd;
		this.tm_trd = tm_trd;
		this.no_biz = no_biz;
		this.nm_biz = nm_biz;
		this.cd_class = cd_class;
		this.nm_class = nm_class;
		this.cd_type = cd_type;
		this.nm_type = nm_type;
		this.cd_consume_class = cd_consume_class;
		this.contents = contents;
		this.memo = memo;
		this.grade = grade;
		this.amt_in_out = amt_in_out;
		this.no_approval = no_approval;
		this.mon_installment = mon_installment;
		this.mon_remaining = mon_remaining;
		this.yn_pay_installment = yn_pay_installment;
		this.yn_cancel = yn_cancel;
		this.yn_delete = yn_delete;
		this.yn_auto = yn_auto;
		this.yn_budget_except = yn_budget_except;
		this.id_frt = id_frt;
		this.dt_frt = dt_frt;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
	}

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public int getSeq_consume() {
		return seq_consume;
	}
	public void setSeq_consume(int seq_consume) {
		this.seq_consume = seq_consume;
	}
	public String getType_in_out() {
		return type_in_out;
	}
	public void setType_in_out(String type_in_out) {
		this.type_in_out = type_in_out;
	}
	public String getMeans_consume() {
		return means_consume;
	}
	public void setMeans_consume(String means_consume) {
		this.means_consume = means_consume;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_card() {
		return nm_card;
	}
	public void setNm_card(String nm_card) {
		this.nm_card = nm_card;
	}
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getType_card() {
		return type_card;
	}
	public void setType_card(String type_card) {
		this.type_card = type_card;
	}
	public String getDt_trd() {
		return dt_trd;
	}
	public void setDt_trd(String dt_trd) {
		this.dt_trd = dt_trd;
	}
	public String getTm_trd() {
		return tm_trd;
	}
	public void setTm_trd(String tm_trd) {
		this.tm_trd = tm_trd;
	}
	public String getNo_biz() {
		return no_biz;
	}
	public void setNo_biz(String no_biz) {
		this.no_biz = no_biz;
	}
	public String getNm_biz() {
		return nm_biz;
	}
	public void setNm_biz(String nm_biz) {
		this.nm_biz = nm_biz;
	}
	public String getCd_class() {
		return cd_class;
	}
	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}
	public String getNm_class() {
		return nm_class;
	}
	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}
	public String getCd_type() {
		return cd_type;
	}
	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}
	public String getNm_type() {
		return nm_type;
	}
	public void setNm_type(String nm_type) {
		this.nm_type = nm_type;
	}
	public String getCd_consume_class() {
		return cd_consume_class;
	}
	public void setCd_consume_class(String cd_consume_class) {
		this.cd_consume_class = cd_consume_class;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAmt_in_out() {
		return amt_in_out;
	}
	public void setAmt_in_out(String amt_in_out) {
		this.amt_in_out = amt_in_out;
	}
	public String getNo_approval() {
		return no_approval;
	}
	public void setNo_approval(String no_approval) {
		this.no_approval = no_approval;
	}
	public String getMon_installment() {
		return mon_installment;
	}
	public void setMon_installment(String mon_installment) {
		this.mon_installment = mon_installment;
	}
	public String getMon_remaining() {
		return mon_remaining;
	}
	public void setMon_remaining(String mon_remaining) {
		this.mon_remaining = mon_remaining;
	}
	public String getYn_pay_installment() {
		return yn_pay_installment;
	}
	public void setYn_pay_installment(String yn_pay_installment) {
		this.yn_pay_installment = yn_pay_installment;
	}
	public String getYn_cancel() {
		return yn_cancel;
	}
	public void setYn_cancel(String yn_cancel) {
		this.yn_cancel = yn_cancel;
	}
	public String getYn_delete() {
		return yn_delete;
	}
	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
	}
	public String getYn_auto() {
		return yn_auto;
	}
	public void setYn_auto(String yn_auto) {
		this.yn_auto = yn_auto;
	}
	public String getYn_budget_except() {
		return yn_budget_except;
	}
	public void setYn_budget_except(String yn_budget_except) {
		this.yn_budget_except = yn_budget_except;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public Date getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(Date dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}