<template>
  <section v-if="seen">
    <div class="credit-inquiry-top">
      <p class="key">{{name}}님의 1년간</p>
      <p class="title"><em>{{formatNumber(amt_pay)}}만원</em>({{cnt_month_pay}}개월)입니다</p>
      <p class="sub">{{start_year}}년 {{start_month}}월부터 매월 {{amt_est_pns_month}}만원<br>지급 예상됩니다.(현재기준)</p>
      <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
    </div>

    <div class="box-list list01">
      <div class="header">납부내역</div>
      <div class="item" v-for="payment in paymentList" :key="payment.index">
        <div class="top">
          <p>{{formatDateDot(payment.start_yyyymm)}} ~ {{formatDateDot(payment.end_yyyymm)}}</p>
          <p class="corp">{{payment.etc}}</p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">납부({{payment.cnt_month_pay}}개월)</p>
            <p class="number">{{formatNumber(payment.amt_pay)}}<em>원</em></p>
          </div>
          <div class="right">
            <p class="key">미납({{payment.cnt_month_not_pay}}개월)</p>
            <p class="value">{{formatNumber(payment.amt_not_pay)}}<em>원</em></p>
          </div>
        </div>
      </div>
    </div>
    <div class="btn-wrap noMG" @click="clickSend()">
      <a class="solid box blue">전송하기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "CreditRaiseNps",
  data() {
    return {
      seen: false,
      name: this.$store.state.user.nmPerson,
      amt_pay: "",
      cnt_month_pay: "",
      start_year: "",
      start_month: "",
      amt_est_pns_month: "",
      paymentList: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "납부 확인 및 전송하기";
  },
  created() {
    this.getCreditRaiseNps();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getCreditRaiseNps: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditRaiseNps.json", {
          params: {}
        })
        .then(response => {
          var result = response.data;
          if (result != null) {
            _this.amt_pay = result.amt_pay;
            _this.cnt_month_pay = result.cnt_month_pay;
            _this.start_year = result.start_year;
            _this.start_month = result.start_month;
            _this.amt_est_pns_month = result.amt_est_pns_month;

            _this.paymentList = result.payment;
            _this.seen = true;
          } else {
            this.$toast.center(ko.messages.error);
          }
        });
    },
    clickSend: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("scrap_code", "nps");

      this.$http
        .post("/m/kcb/updateKcbReqNonfiInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          this.$router.push({
            name: "creditRaiseResult",
            params: { result: "success" }
          });
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
