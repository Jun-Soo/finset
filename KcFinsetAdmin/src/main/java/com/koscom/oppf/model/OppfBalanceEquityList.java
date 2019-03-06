package com.koscom.oppf.model;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfBalanceEquityList {
    public String assetType;       //상품구분
    public String isinCode;        //ISINCODE
    public String tradeType;       //잔고구분
    public long valAtTrade;        //매수금액
    public long valAtCur;          //평가금액
    public long proLoss;           //평가손익
    public long qty;               //잔고수량
    public double earningRate;     //수익률
}
