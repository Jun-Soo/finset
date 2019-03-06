package com.koscom.oppf.model;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfFundList {
    public String fundCode;        //펀드표준코드
    public String fundName;        //펀드이름
    public String firstDateBuy;    //최초매수일(YYYYMMDD)
    public String lastDateBuy;     //최종매수일(YYYYMMDD)
    public String maturity;        //만기일(YYYYMMDD)
    public double earningRate;     //수익률 (소수점 2째자리까지)
    public long qty;               //잔고수량
    public long valAtTrade;        //매수금액
    public long valAtCur;          //평가금액
    public long proLoss;           //평가손익
}
