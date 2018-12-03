package com.koscom.batch.push.domain;

public class Ad01TrailerInfo {
    protected String recordType; //Record 구분 
    protected String seqNo     ; //일련번호    
    protected String cpCode    ; //회원사코드  
    protected String fileName  ; //FILE 이름   
    protected String stdDate   ; //기준일자    
    protected String totalCnt  ; //총건수      
    protected String madVeri   ; //MAD검증 값
    protected String filler    ; //FILLER
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
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getStdDate() {
        return stdDate;
    }
    public void setStdDate(String stdDate) {
        this.stdDate = stdDate;
    }
    public String getTotalCnt() {
        return totalCnt;
    }
    public void setTotalCnt(String totalCnt) {
        this.totalCnt = totalCnt;
    }
    public String getMadVeri() {
        return madVeri;
    }
    public void setMadVeri(String madVeri) {
        this.madVeri = madVeri;
    }
    public String getFiller() {
        return filler;
    }
    public void setFiller(String filler) {
        this.filler = filler;
    }
}