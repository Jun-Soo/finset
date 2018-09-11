<template>
<div id="wrapper">
    <div class="list-block" v-for="oneDebt in debtListData" :key="oneDebt.index">
        <div class="container-fluid">
            <a href="/debt/detail?no_manage_info">
                <div class="list-heading">
                    <li class="bank-title">
                        <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>{{oneDebt.nm_fc}}
                    </li>
                    <label class="label-type">{{oneDebt.debt_type}}</label>
                </div>
                <div class="list-info">
                    <dl>
                        <dt>상환금액(당월)</dt>
                        <dd>{{oneDebt.amt_repay}}</dd>

                      
                    </dl>
                    <dl>
                        <dt>대출잔액</dt>
                        <dd>{{oneDebt.amt_remain}}</dd>
                    </dl>
                    <dl>
                        <dt>???</dt>
                        <dd>{{oneDebt.amt_contract}}</dd>
                    </dl>
                    <dl>
                        <dt>???</dt>
                        <dd>{{oneDebt.rate_repay}}%</dd>
                    </dl>
                </div>
            </a>
        </div>
    </div>
</div>
</template>

<script>
export default {
  name: 'DebtMain',
  data () {
    return {
        goods_ea : '',
        cur_mon_mid_rpy_p : '',
        amt_remain : '',
        rate_amt_contract : '',

        cur_mon_mid_rpy_i : '',
        ever_interest : '',
        rate_repay : '',

        cur_mon_mid_rpy : '',
        amt_etm_income : '',
        repay_pni_per_income : '',

        rest_term : '',
        loan_term : '',

        min_ymd_loan : '',
        max_ymd_loanend : '',

        debtListData : []
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created () {
    this.getDebtSummary();
    this.listDebtPg();
  },
  beforeMount() {
  },
  mounted () {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy () {
  },
  destroyed() {
  },
  methods: {

    // 신용정보 조회
    getDebtSummary () {
      var thisObj = this;
      this.$http.get('/api/debt/getDebtSummary.json', {
        params: {}
      }).then(function (response) {
        var debtSummaryData = response.data.debtSummaryData
        console.log(JSON.stringify(debtSummaryData));
        thisObj.setDebtSummary(debtSummaryData);
      })
    },
    setDebtSummary (debtSummaryData) {
        var thisObj = this;
        thisObj.goods_ea = debtSummaryData.cnt; //보유 수
        thisObj.cur_mon_mid_rpy_p = thisObj.formatAmt(debtSummaryData.cur_mon_mid_rpy_p,false); // 상환 원금
        thisObj.amt_remain = thisObj.formatAmt(debtSummaryData.amt_remain,false); //대출잔액
        thisObj.rate_amt_contract = debtSummaryData.rate_amt_contract; //원금 상환 비율

        thisObj.cur_mon_mid_rpy_i = thisObj.formatAmt(debtSummaryData.cur_mon_mid_rpy_i,false); //이자 금액
        thisObj.ever_interest = debtSummaryData.ever_interest; //금리(평균)
        thisObj.rate_repay = debtSummaryData.rate_repay; //이자 상환 비율

        thisObj.cur_mon_mid_rpy = thisObj.formatAmt(debtSummaryData.cur_mon_mid_rpy,false); //원리금 상환
        thisObj.amt_etm_income = thisObj.formatAmt(debtSummaryData.amt_etm_income,false); //소득(월평균)
        thisObj.repay_pni_per_income = debtSummaryData.repay_pni_per_income; //원리금상환/소득

        thisObj.rest_term = debtSummaryData.rest_term; //잔여 기간
        thisObj.loan_term = debtSummaryData.loan_term; //대출 기간

        thisObj.min_ymd_loan = thisObj.formatDate(debtSummaryData.min_ymd_loan,true); //?
        thisObj.max_ymd_loanend = thisObj.formatDate(debtSummaryData.max_ymd_loanend,true); //?
    },
    formatAmt (amt, isMinus) {
        var regex = /^[0-9]/g;
        if(!regex.test(amt)) {
            return amt;
        }
        if(isMinus) {
            return "-"+Number(amt).toLocaleString("en").split(".")[0];
        } else {
            return Number(amt).toLocaleString("en").split(".")[0];
        }
    },
    formatDate (date, isFull) {
        var yyyy = date.substring(0,4);
        var mm = date.substring(4,6);
        var dd = date.substring(6,8);
        if(isFull) {
            return yyyy+"-"+mm+"-"+dd;
        } else {
            return mm+"-"+dd;
        }
    },
    goCalendar () {
        location.href = "/debt/calendar"
    },
    listDebtPg () {
      var thisObj = this;
      this.$http.get('/api/debt/listDebtPg.json', {
        params: {}
      }).then(function (response) {
        thisObj.debtListData = response.data.debtListData
        console.log(JSON.stringify(thisObj.debtListData));
      })
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
