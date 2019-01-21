<template>
  <section v-if="seen">
    <div class="container">
      <div v-if="debtList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="bar-top">
        <p class="key">대출잔액</p>
        <div class="wrap">
          <div class="left">{{formatNumber(ds_sum_amt_remain)}}<em>원</em></div>
          <div class="right">
            <p class="key">대출금액</p>
            <p class="value">{{formatNumber(Math.round(ds_sum_amt_contract/10000))}} <em>만원</em></p>
          </div>
        </div>
        <div class="bar">
          <p :style="{width: ds_tot_repay_pc+'%'}"></p>
        </div>
        <div class="bar-text-wrap">
          <p class="bar-text">대출금 상환비율<em>{{ds_tot_repay_pc}}%</em></p>
          <p class="bar-card">보유갯수<em>{{ds_debt_cnt}}</em></p>
        </div>
      </div>
    </div>

    <div v-if="debtList.length != 0" class="box-list list01">
      <div v-for="debtInfo in debtList" :key="debtInfo.index" class="item" @click="viewDetailDebt(debtInfo.no_manage_info)">
        <div class="top">
          <p class="symbol"><img :src="debtInfo.fcImg" alt="" />{{debtInfo.nm_fc}}</p>
          <p class="text blue">{{debtInfo.cd_debt == '01'? '신용' : '담보'}}</p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">잔액</p>
            <p class="number">{{formatNumber(debtInfo.amt_remain)}}<em>원</em></p>
          </div>
        </div>
        <div class="bar">
          <p :style="{width: debtInfo.repayPc+'%'}"></p>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">대출금액</p>
            <p class="value">{{formatNumber(Math.round(debtInfo.amt_contract/10000))}} <em>만원</em></p>
          </div>
          <div class="right">
            <p class="key">개설일자</p>
            <p class="value">{{formatDateDot(debtInfo.ymd_loan)}}</p>
          </div>
        </div>
      </div>

      <div class="btn-wrap">
        <a @click="$router.push('/debt/main')" class="solid blue">자세히보기</a>
      </div>

      <!-- noti -->
      <div class="info-massage">대출개설정보는 현재 해지되지 않은 대출정보입니다.</div>
      <div class="info-massage">일부 기관의 대출정보는 “개인신용정보 제공 및 활용동의서” 미제출 경우거나 해당 금융기관의 기준 및 정책에 의해 대출정보가 제공되지 않을 수 있습니다.</div>
      <div class="info-massage">금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</div>
      <div class="info-massage">“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</div>
      <div class="info-massage">금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditLoanInfo",
  data() {
    return {
      errMsg: "",
      seen: false,
      //상단정보
      debtSum: "",
      ds_sum_amt_remain: "0", //대출잔액
      ds_sum_amt_contract: "0", //대출금액
      ds_debt_cnt: "0", //보유갯수
      ds_tot_repay_pc: "0", //전체 대출금 상환비율
      debtList: [] //대출현황list
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "대출현황";
    this.getLoanInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getLoanInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditLoanInfo.json", {
          params: {}
        })
        .then(response => {
          var debtSumInfo = response.data.debtSum;
          if (debtSumInfo != null) {
            _this.ds_sum_amt_remain = debtSumInfo.sum_amt_remain;
            _this.ds_sum_amt_contract = debtSumInfo.sum_amt_contract;
            _this.ds_debt_cnt = debtSumInfo.debt_cnt;
            //전체 대출금 상환비율
            if (debtSumInfo.sum_amt_contract > 0) {
              _this.ds_tot_repay_pc = Math.round(
                ((debtSumInfo.sum_amt_contract - debtSumInfo.sum_amt_remain) /
                  debtSumInfo.sum_amt_contract) *
                  100
              );
            }
          }
          _this.debtSum = debtSumInfo;

          var list = response.data.debtList;
          for (var i = 0; i < list.length; i++) {
            //금융사ICON 셋팅
            list[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;

            //개별 대출금 상환비율
            if (list[i].amt_contract > 0) {
              list[i].repayPc = Math.round(
                ((list[i].amt_contract - list[i].amt_remain) /
                  list[i].amt_contract) *
                  100
              );
            } else {
              list[i].repayPc = 0;
            }
          }

          _this.debtList = list;
          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //부채 상세페이지로 이동
    viewDetailDebt: function(no_manage_info) {
      this.$router.push({
        name: "debtDetail",
        query: {
          no_manage_info: no_manage_info,
          isMine: true
        }
      });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
