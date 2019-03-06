package com.koscom.oppf.model;
/**
 * Created by lee on 2017-10-20.
 */

public class OppfTransaction {
    public String isinCode;     //종목코드 (입출금은 CASH로 표기)
    public String transDate;    //거래일자 (YYYYMMDD)
    public String transType;    //거래구분이며 값은:  BID(매 도), ASK(매수), DEP(이체입금), WID(이체출금
    public long changeAmt;      //금액증감 (매도/매수/이체에 따른 금액변동)
    public long changeQty;      //수량증감 (매도/매수량, 이체 시는 0)
    public double qty;          //잔고수량 (거래 후 잔량)
}
