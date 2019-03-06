package com.koscom.oppf.model;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfBalanceSummary {
    public long cashBalance;      //현금잔고
    public long substitute;       //대용금
    public long receivable;       //미수/미납금
    public long subsMargin;       //대용증거금
    public long loanCredit;       //대출/신용금
    public long valAtTrade;       //유가증권매수금액
    public long valueAtCur;       //유가증권평가금액
    public long proLoss;          //유가증권평가손익
    public long totalAccVal;      //총평가금액
    public long cashAvWithdraw;   //출금가능액
    public long d1;               //D+1잔고
    public long d2;               //D+2잔고
}
