<template>
  <section v-if="seen">
    <div class="apply-done">
      신청이 완료되었습니다
    </div>
    <div class="box-list list01 noMG">
      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <img :src="goods.icon" alt="" />{{fincorp.nm_fc}}</p>
          <p class="text blue">신청완료</p>
        </div>
        <p class="goods-name">{{goods.nm_goods}}</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">대출한도</p>
            <p class="right">{{Common.formatNumber(txFcReceive.amt_limit/10000)}} 만원</p>
          </div>
          <div class="list">
            <p class="left">대출금리</p>
            <p class="right">
              <em class="state" v-if="txFcReceive.type_interest_length > '2'">변동,고정"</em>
              <em class="state" v-else-if="txFcReceive.type_interest_length == '2'">{{Common.getCodeName("cd_ratio_type", txFcReceive.cd_type_interest)}}</em>
              {{txFcReceive.rto_loan}} %</p>
          </div>
          <div class="list">
            <p class="left">대출기간</p>
            <p class="right">{{goods.cd_loan_term * 12}} 개월</p>
          </div>
          <div class="list">
            <p class="left">상환방식</p>
            <p class="right">{{goods.typePay}}</p>
          </div>
        </div>
      </div>

      <p class="info-massage">신청 후 신용등급 변동, 입력 정보 오류 등으로 인해 금리 및 한도가 변경되거나 대출이 불가능 할수도 있습니다.</p>
      <p class="info-massage">신청이후 진행 사항은 마이페이지(상품신청현황)에서 확인 가능합니다.</p>

    </div>

    <div class="btn-wrap float">
      <a class="box blue solid" @click="clickConfirm">확인</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      seen: false,
      Common: Common,
      goods: null,
      txFcReceive: null,
      fincorp: null
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청완료";
  },
  created() {
    this.getLoanIncomeSuccess();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getLoanIncomeSuccess: function() {
      var _this = this;
      this.$store.state.isLoading = true;

      this.$http
        .get("/m/loan/getLoanIncomeSuccess.json", { params: {} })
        .then(response => {
          var result = response.data;
          _this.goods = result.goodsVO;
          _this.txFcReceive = result.txFcReceiveVO;
          _this.fincorp = result.fincorpVO;
          _this.goods.icon =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + _this.fincorp.cd_fc;
          if ((_this.fincorp.cd_type_interest || "") != "") {
            _this.fincorp.type_interest_length =
              _this.fincorp.cd_type_interest.length;
          }
          _this.goods.typePay = "";
          if ((_this.goods.cd_type_pay || "") != "") {
            var cdTypePay = _this.goods.cd_type_pay.split(",");
            for (var i = 0; i < cdTypePay.length; i++) {
              if (i) {
                _this.goods.typePay += ", ";
              }
              _this.goods.typePay += Common.getCodeName(
                "cd_type_pay",
                cdTypePay[i]
              );
            }
          }

          _this.seen = true;
          _this.$store.state.isLoading = false;
        })
        .catch(e => {
          _this.$store.state.isLoading = false;
          _this.$toast.center(ko.messages.error);
        });
    },
    clickConfirm: function() {
      var _this = this;
      setTimeout(function() {
        _this.$router.push("/mypage/state");
      }, 100);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
