package com.koscom.oppf.model;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfBalanceEtcList {
    public String assetType;         //상품구분자
    public String assetName;         //상품명
    public long qty;                 //수량
    public String tradeType;         //잔고구분
    public String isinCode;          //현재는 지원 안 함 (1.0부터 지원예정)
    public long valAtTrade;          //매수금액
    public long valueAtCur;          //평가금액
    public double earningRate;       //수익률 (소수점 2째자리까지)

    /**
     * 상품구분자 : BOND(채권), CD, CP, DLS, ELS, STB(사채), RP(미분류), CRP(약정 식RP), RRP(수시RP), WRT(워런트)
     * 잔고구분 : NRM(일반/현금), CRD(신용), LOAN(대출), SUM(분류가 불가한 경우 구 분 없이 합산한 경우며 대출잔고는 제 외)
     */
}
