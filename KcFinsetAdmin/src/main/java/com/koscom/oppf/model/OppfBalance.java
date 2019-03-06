package com.koscom.oppf.model;

import java.util.List;

/**
 * Created by lee on 2017-10-20.
 * 계좌잔고
 */

public class OppfBalance {
    public OppfBalanceSummary summary;               //잔고요약
    public List<OppfBalanceEquityList> equityList;   //
    public List<OppfFundList> fundList;
    public List<OppfBalanceEtcList> etcList;
}
