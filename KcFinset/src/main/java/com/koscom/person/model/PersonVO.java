package com.koscom.person.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonInfo;
import com.koscom.util.StringUtil;

public class PersonVO extends PersonInfo implements Serializable {
	
	// 앱용 
	public final static String PERSON_EXIST = "11";  // 이미 존재하는 경우
	
	private String no_bunch; 			//tx_fc_transmit 테이블 시퀀스 번호
	private String sex; 				//성별
	private String nation; 				//내외국인구분
	private String telComCd; 			//통신사구분
	private String svcTxSeqno;			//거래일련번호
	private String birthday;			//생년월일
	private String smsCertNo;			//
	private String smsReSndYn;  		//재요청 여부 Y, N
	private String rqstCausCd;			//인증요청사유코드 (00:회원가입, 01:성인인증, 02:회원정보수정, 03:비밀번호찾기, 04:상품구매, 99:기타)
	private String loan_code;			//대출 구분 코드   01 : 직장인 신용대출  02 : 자영업자 신용대출
	private String job;	
	private String currentPwd;			//현재비밀번호
	private String changePwd;			//변경할비밀번호
	private String ChangePwdConfirm;	//변경할비밀번호 확인
	private int chk_pwd;
	private List<String> pass_number;	
	
	private String	seq; 					//push_setting seq값
	private	String	type_push; 				//push_setting type
	private String	cnt_fail_mode;			//누적카운트 mode
	private	int		cnt_fail; 				//비밀번호/지문 틀린횟수 insert parameter
	private	String	item_push;				//푸쉬 항목명
	private	String	stat_push; 				//개별 push yn여부
	
	private	String	dt_basic;
	private	String	yn_installment;
	
	//for file transformation
	private byte[] fileArray;
	private String fileName;
	private int fileSize;
	
	//이벤트푸시 수신여부
	private String yn_eventPush;
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTelComCd() {
		return telComCd;
	}

	public void setTelComCd(String telComCd) {
		this.telComCd = telComCd;
	}

	public String getSvcTxSeqno() {
		return svcTxSeqno;
	}

	public void setSvcTxSeqno(String svcTxSeqno) {
		this.svcTxSeqno = svcTxSeqno;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getSmsCertNo() {
		return smsCertNo;
	}

	public void setSmsCertNo(String smsCertNo) {
		this.smsCertNo = smsCertNo;
	}

	public String getRqstCausCd() {
		return rqstCausCd;
	}

	public void setRqstCausCd(String rqstCausCd) {
		this.rqstCausCd = rqstCausCd;
	}

	public String getLoan_code() {
		return loan_code;
	}

	public void setLoan_code(String loan_code) {
		this.loan_code = loan_code;
	}

















	
	

	public String getSmsReSndYn() {
		return smsReSndYn;
	}

	public void setSmsReSndYn(String smsReSndYn) {
		this.smsReSndYn = smsReSndYn;
	}


























	private String zone_home;
	private String zone_reg;
	private String zone_comp;
	private String zone_etc;
	private String zone_univ;
	private String yn_grt;		// 보증인 여부
	private String no_prepare;	// 사전접수 번호
	private	String id_agency;	// 매체사 id
	private	String yn_agency;	// 대리점 유무
	private	String etc_memo1;
	
	private static final long serialVersionUID = -1372438333901338373L;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getC1_gender() {
		return StringUtil.splitBgn(super.bgn, "G");
	}
	
	public String getYmd_birth() {
		return StringUtil.splitBgn(super.bgn, "BOD");
	}
	
	public String getSsn_person_idx1() {
		return StringUtil.splitSsn(super.ssn_person, 1);
	}
	
	public String getSsn_person_idx2() {
		return StringUtil.splitSsn(super.ssn_person, 2);
	}
	
	public String getHp_idx1() {
		return StringUtil.splitStr(super.hp, "-", 1);
	}

	public String getHp_idx2() {
		return StringUtil.splitStr(super.hp, "-", 2);
	}

	public String getHp_idx3() {
		return StringUtil.splitStr(super.hp, "-", 3);
	}

	public String getEmail_idx1() {
		return StringUtil.splitStr(super.email, "@", 1);
	}

	public String getEmail_idx2() {
		return StringUtil.splitStr(super.email, "@", 2);
	}

	public void setSsn_person_tmp(String[] ssn_person_tmp) {
		super.ssn_person = StringUtil.addChar(ssn_person_tmp, "");
	}
	
	public void setHp_tmp(String[] hp_tmp) {
		super.hp = StringUtil.addChar(hp_tmp, "-");
	}

	public void setEmail_tmp(String[] email_tmp) {
		super.email = StringUtil.addChar(email_tmp, "@");
	}

	public String getZone_home() {
		return zone_home;
	}

	public void setZone_home(String zone_home) {
		this.zone_home = zone_home;
	}

	public String getZone_reg() {
		return zone_reg;
	}

	public void setZone_reg(String zone_reg) {
		this.zone_reg = zone_reg;
	}

	public String getZone_comp() {
		return zone_comp;
	}

	public void setZone_comp(String zone_comp) {
		this.zone_comp = zone_comp;
	}

	public String getZone_etc() {
		return zone_etc;
	}

	public void setZone_etc(String zone_etc) {
		this.zone_etc = zone_etc;
	}

	public String getZone_univ() {
		return zone_univ;
	}

	public void setZone_univ(String zone_univ) {
		this.zone_univ = zone_univ;
	}
	
	public String getYn_grt() {
		return yn_grt;
	}

	public void setYn_grt(String yn_grt) {
		this.yn_grt = yn_grt;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}
	
	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getYn_agency() {
		return yn_agency;
	}

	public void setYn_agency(String yn_agency) {
		this.yn_agency = yn_agency;
	}

	public String getEtc_memo1() {
		return etc_memo1;
	}

	public void setEtc_memo1(String etc_memo1) {
		this.etc_memo1 = etc_memo1;
	}
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCurrentPwd() {
		return currentPwd;
	}

	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}

	public String getChangePwd() {
		return changePwd;
	}

	public void setChangePwd(String changePwd) {
		this.changePwd = changePwd;
	}

	public String getChangePwdConfirm() {
		return ChangePwdConfirm;
	}

	public void setChangePwdConfirm(String changePwdConfirm) {
		ChangePwdConfirm = changePwdConfirm;
	}
	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}

	public int getChk_pwd() {
		return chk_pwd;
	}

	public void setChk_pwd(int chk_pwd) {
		this.chk_pwd = chk_pwd;
	}

	public byte[] getFileArray() {
		return fileArray;
	}

	public void setFileArray(byte[] fileArray) {
		this.fileArray = fileArray;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getYn_eventPush() {
		return yn_eventPush;
	}

	public void setYn_eventPush(String yn_eventPush) {
		this.yn_eventPush = yn_eventPush;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getType_push() {
		return type_push;
	}

	public void setType_push(String type_push) {
		this.type_push = type_push;
	}

	public String getCnt_fail_mode() {
		return cnt_fail_mode;
	}

	public void setCnt_fail_mode(String cnt_fail_mode) {
		this.cnt_fail_mode = cnt_fail_mode;
	}

	public int getCnt_fail() {
		return cnt_fail;
	}

	public void setCnt_fail(int cnt_fail) {
		this.cnt_fail = cnt_fail;
	}

	public String getItem_push() {
		return item_push;
	}

	public void setItem_push(String item_push) {
		this.item_push = item_push;
	}

	public String getStat_push() {
		return stat_push;
	}

	public void setStat_push(String stat_push) {
		this.stat_push = stat_push;
	}

	public String getDt_basic() {
		return dt_basic;
	}

	public void setDt_basic(String dt_basic) {
		this.dt_basic = dt_basic;
	}

	public String getYn_installment() {
		return yn_installment;
	}

	public void setYn_installment(String yn_installment) {
		this.yn_installment = yn_installment;
	}
	
	public List<String> getPass_number() {
		return pass_number;
	}

	public void setPass_number(List<String> pass_number) {
		this.pass_number = pass_number;
	}
}
