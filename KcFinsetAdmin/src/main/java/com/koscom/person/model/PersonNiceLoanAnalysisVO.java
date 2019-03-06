package com.koscom.person.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceLoanInfo;
import com.koscom.env.model.CodeInfo;

public class PersonNiceLoanAnalysisVO extends PersonNiceLoanInfo implements Serializable {
	private static final long serialVersionUID = -4455929436686146515L;
	String h_1;				//'주택담보대출 1금융 '
	String h_n1;			//'주택담보대출 1금융 외'
	String nh_1;			//'주택담보대출외 담보 1금융 '
	String nh_n1;			//'주택담보대출외 담보 1금융 외'
	String c_1;				//'신용 1금융'
	String c_n1;			//'신용 1금융 외'
	String ec;				//'신용 기타'
	String d_d;				//'대부업 담보'
	String d_c;				//'대부업 신용'
	int cnt_h_1;				//'주택담보대출 1금융 '
	int cnt_h_n1;			//'주택담보대출 1금융 외'
	int cnt_nh_1;			//'주택담보대출외 담보 1금융 '
	int cnt_nh_n1;			//'주택담보대출외 담보 1금융 외'
	int cnt_c_1;				//'신용 1금융'
	int cnt_c_n1;			//'신용 1금융 외'
	int cnt_ec;				//'신용 기타'
	int cnt_d_d;				//'대부업 담보'
	int cnt_d_c;				//'대부업 신용'
	String no_person;
	public String getH_1() {
		return h_1;
	}
	public void setH_1(String h_1) {
		this.h_1 = h_1;
	}
	public String getH_n1() {
		return h_n1;
	}
	public void setH_n1(String h_n1) {
		this.h_n1 = h_n1;
	}
	public String getNh_1() {
		return nh_1;
	}
	public void setNh_1(String nh_1) {
		this.nh_1 = nh_1;
	}
	public String getNh_n1() {
		return nh_n1;
	}
	public void setNh_n1(String nh_n1) {
		this.nh_n1 = nh_n1;
	}
	public String getC_1() {
		return c_1;
	}
	public void setC_1(String c_1) {
		this.c_1 = c_1;
	}
	public String getC_n1() {
		return c_n1;
	}
	public void setC_n1(String c_n1) {
		this.c_n1 = c_n1;
	}
	public String getEc() {
		return ec;
	}
	public void setEc(String ec) {
		this.ec = ec;
	}
	public String getD_d() {
		return d_d;
	}
	public void setD_d(String d_d) {
		this.d_d = d_d;
	}
	public String getD_c() {
		return d_c;
	}
	public void setD_c(String d_c) {
		this.d_c = d_c;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public int getCnt_h_1() {
		return cnt_h_1;
	}
	public void setCnt_h_1(int cnt_h_1) {
		this.cnt_h_1 = cnt_h_1;
	}
	public int getCnt_h_n1() {
		return cnt_h_n1;
	}
	public void setCnt_h_n1(int cnt_h_n1) {
		this.cnt_h_n1 = cnt_h_n1;
	}
	public int getCnt_nh_1() {
		return cnt_nh_1;
	}
	public void setCnt_nh_1(int cnt_nh_1) {
		this.cnt_nh_1 = cnt_nh_1;
	}
	public int getCnt_nh_n1() {
		return cnt_nh_n1;
	}
	public void setCnt_nh_n1(int cnt_nh_n1) {
		this.cnt_nh_n1 = cnt_nh_n1;
	}
	public int getCnt_c_1() {
		return cnt_c_1;
	}
	public void setCnt_c_1(int cnt_c_1) {
		this.cnt_c_1 = cnt_c_1;
	}
	public int getCnt_c_n1() {
		return cnt_c_n1;
	}
	public void setCnt_c_n1(int cnt_c_n1) {
		this.cnt_c_n1 = cnt_c_n1;
	}
	public int getCnt_ec() {
		return cnt_ec;
	}
	public void setCnt_ec(int cnt_ec) {
		this.cnt_ec = cnt_ec;
	}
	public int getCnt_d_d() {
		return cnt_d_d;
	}
	public void setCnt_d_d(int cnt_d_d) {
		this.cnt_d_d = cnt_d_d;
	}
	public int getCnt_d_c() {
		return cnt_d_c;
	}
	public void setCnt_d_c(int cnt_d_c) {
		this.cnt_d_c = cnt_d_c;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}