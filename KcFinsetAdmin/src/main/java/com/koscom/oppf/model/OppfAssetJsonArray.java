package com.koscom.oppf.model;

import java.util.List;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfAssetJsonArray {
    public String secuNo;    //증권사번호
    public String accNo;     //가상계좌번호
    public String Dt;        //일자
    public OppfBalance balance; //계좌잔고요약 및 자산별잔고
    public List<OppfTransaction> transaction; //거래내역
    public String secuNm;    //증권사명

    /**
     *
     * @param secuNo 증권사번호
     * @param accNo  가상계좌번호
     * @param Dt     일자
     * @param balance 계좌잔고요약 및 자산별잔고
     */
    public OppfAssetJsonArray(String secuNo, String accNo, String Dt, OppfBalance balance, List<OppfTransaction> transaction, String secuNm){
        this.secuNo = secuNo;
        this.accNo = accNo;
        this.Dt = Dt;
        this.balance = balance;
        this.transaction = transaction;
        this.secuNm = secuNm;
    }
}