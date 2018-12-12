<template>
  <section v-if="seen">
    <div class="credit-inquiry-top">
      <p class="key">{{name}}님의 1년간</p>
      <p class="title">납입금액은 <em>{{ formatNumber(total_payment) }}만원</em>({{payment_size}}개월),<br>추정 소득은 <em>{{ formatNumber(amt_year_income) }} 만원</em>입니다</p>
      <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
    </div>

    <div class="box-list list02">
      <div class="header">납부내역</div>
      <div class="item" v-for="payment in paymentList" :key="payment.index">
        <div class="flex">
          <p class="date">{{formatDateDot(payment.pay_yyyymm)}}<em v-if="payment.amt_pay_health_insu == payment.amt_nt_health_insu">납부</em><em v-else>미납</em></p>
          <p class="number">{{formatNumber(payment.amt_nt_health_insu)}}<em>원</em></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a class="solid box blue" @click="clickSend()">전송하기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "CreditRaiseNhis",
  data() {
    return {
      seen: false,
      name: this.$store.state.user.nmPerson,
      amt_year_income: "",
      total_payment: "",
      payment_size: "",
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
    this.getCreditRaiseNhis();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getCreditRaiseNhis: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditRaiseNhis.json", {
          params: {}
        })
        .then(response => {
          var result = response.data;
          if (result != null) {
            _this.amt_year_income = result.amt_year_income;
            _this.total_payment = result.total_payment;
            _this.payment_size = result.payment_size;

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
      formData.append("scrap_code", "nhis");

      this.$http
        .post("/m/kcb/updateKcbReqNonfiInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          _this.$router.push({
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
