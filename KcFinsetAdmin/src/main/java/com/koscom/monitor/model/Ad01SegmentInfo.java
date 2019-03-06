package com.koscom.monitor.model;

public class Ad01SegmentInfo {
    protected String recordType           ; //Record 구분
    protected String seqNo                ; //일련번호
    protected String cpCode               ; //회원사코드  
    protected String ssn                  ; //주민등록번호   
    protected String nm_person            ; //성명    
    protected String no_person            ; //관리번호
    protected String filler               ; //FILLER   
    protected String segmentId            ; //Segment Identification
    protected String svcCode              ; //서비스코드
    protected String dt_info_change       ; //정보변동일자
    protected String hp                   ; //휴대폰
    protected String email                ; //이메일
    protected String yn_credit_info       ; //신용거래정보 변동여부
    protected String yn_kfb_default       ; //은행연합회 채무불이행 변동여부
    protected String yn_kci_default       ; //신용정보사 채무불이행 변동여부
    protected String yn_ref_info          ; //조회처정보 변동여부
    protected String yn_loan_info         ; //대출정보 변동여부
    protected String yn_guarantee_info    ; //보증정보 변동여부
    protected String yn_fnco_disorder_info; //금융질서문란정보 변동여부
    protected String yn_public_info       ; //공공정보 변동여부
    protected String kcbdi                ; //KCBDI
    protected String fillerSeg            ; //FILLER 
    
    protected String id_frt= "";    //최초입력아이디
    protected String dt_frt= "";    //최초입력시간
    public String getRecordType() {
        return recordType;
    }
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    public String getCpCode() {
        return cpCode;
    }
    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getNm_person() {
        return nm_person;
    }
    public void setNm_person(String nm_person) {
        this.nm_person = nm_person;
    }
    public String getNo_person() {
        return no_person;
    }
    public void setNo_person(String no_person) {
        this.no_person = no_person;
    }
    public String getFiller() {
        return filler;
    }
    public void setFiller(String filler) {
        this.filler = filler;
    }
    public String getSegmentId() {
        return segmentId;
    }
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }
    public String getSvcCode() {
        return svcCode;
    }
    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }
    public String getDt_info_change() {
        return dt_info_change;
    }
    public void setDt_info_change(String dt_info_change) {
        this.dt_info_change = dt_info_change;
    }
    public String getHp() {
        return hp;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getYn_credit_info() {
        return yn_credit_info;
    }
    public void setYn_credit_info(String yn_credit_info) {
        this.yn_credit_info = yn_credit_info;
    }
    public String getYn_kfb_default() {
        return yn_kfb_default;
    }
    public void setYn_kfb_default(String yn_kfb_default) {
        this.yn_kfb_default = yn_kfb_default;
    }
    public String getYn_kci_default() {
        return yn_kci_default;
    }
    public void setYn_kci_default(String yn_kci_default) {
        this.yn_kci_default = yn_kci_default;
    }
    public String getYn_ref_info() {
        return yn_ref_info;
    }
    public void setYn_ref_info(String yn_ref_info) {
        this.yn_ref_info = yn_ref_info;
    }
    public String getYn_loan_info() {
        return yn_loan_info;
    }
    public void setYn_loan_info(String yn_loan_info) {
        this.yn_loan_info = yn_loan_info;
    }
    public String getYn_guarantee_info() {
        return yn_guarantee_info;
    }
    public void setYn_guarantee_info(String yn_guarantee_info) {
        this.yn_guarantee_info = yn_guarantee_info;
    }
    public String getYn_fnco_disorder_info() {
        return yn_fnco_disorder_info;
    }
    public void setYn_fnco_disorder_info(String yn_fnco_disorder_info) {
        this.yn_fnco_disorder_info = yn_fnco_disorder_info;
    }
    public String getYn_public_info() {
        return yn_public_info;
    }
    public void setYn_public_info(String yn_public_info) {
        this.yn_public_info = yn_public_info;
    }
    public String getKcbdi() {
        return kcbdi;
    }
    public void setKcbdi(String kcbdi) {
        this.kcbdi = kcbdi;
    }
    public String getId_frt() {
        return id_frt;
    }
    public void setId_frt(String id_frt) {
        this.id_frt = id_frt;
    }
    public String getFillerSeg() {
        return fillerSeg;
    }
    public void setFillerSeg(String fillerSeg) {
        this.fillerSeg = fillerSeg;
    }
    public String getDt_frt() {
        return dt_frt;
    }
    public void setDt_frt(String dt_frt) {
        this.dt_frt = dt_frt;
    }
}