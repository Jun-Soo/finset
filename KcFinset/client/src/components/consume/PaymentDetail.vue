<template>
  <section v-if="seen">
    <div class="card-detail-top list01">
      <div class="item expand">
        <div class="top">
          <p class="symbol"><img :src="payment.imgSrc" alt="" />{{payment.nm_fc}}</p>
          <p class="corp">{{payment.nm_card}}</p>
        </div>
        <div class="number-wrap card">
          <div class="left">
            <p class="key">결제금액</p>
          </div>
          <div class="right">
            <p class="number">{{Common.formatNumber(payment.monthly_charge)}}<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap card pb20">
          <div class="left">
            <p class="key">카드번호</p>
          </div>
          <div class="right">
            <p class="value">{{formatNoCard(payment.no_card)}}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="box-list noMG">
      <div v-if="!isNone" class="card-detail-list pb30">
        <ul>
          <li v-for="(detail, index) in listDetail" :key="index">
            <em>{{Common.formatDateDot(detail.dt_use,"mmdd")}}</em>
            <p>{{formatNmMember(detail.nm_member)}}</p>
            <p>{{Common.formatNumber(detail.amt_charge)}}<em v-if="detail.month_installment != 0">할부</em></p>
          </li>
        </ul>
      </div>
      <div v-else class="nodata">
        청구 내역이 없습니다
      </div>
    </div>
  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumePaymentDetail",
  data() {
    return {
      seen: false, // 화면 표시 여부
      Common: Common, // 공통
      isNone: true, // 데이터 비존재 여부
      payment: {}, // 청구된 카드 정보
      listDetail: {} // 청구 상세 리스트
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드 대금 청구 상세";
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.payment = this.$route.params.payment;
    this.listPaymentDetail();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포맷---------------------
    // 카드번호 포맷
    formatNoCard: function(no_card) {
      if ((no_card || "") == "" || no_card.length != 16) {
        return no_card;
      } else {
        return (
          no_card.substring(0, 4) +
          "-" +
          no_card.substring(4, 8) +
          "-" +
          no_card.substring(8, 12) +
          "-" +
          no_card.substring(12, 16)
        );
      }
    },
    // 사용처 길이 자르기
    formatNmMember: function(nm_member) {
      if ((nm_member || "") == "") {
        return nm_member;
      } else if (nm_member.length < 20) {
        return nm_member;
      } else {
        return (nm_member + "").substring(0, 20) + "...";
      }
    },
    // ---------------------//데이터 포맷---------------------
    // ---------------------데이터 이동---------------------
    // 청구 상세 내역 리스트 조회
    listPaymentDetail: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", _this.payment.no_person);
      formData.append("no_card", _this.payment.no_card);
      formData.append("charge_yyyymm", _this.$route.params.charge_yyyymm);
      formData.append("cd_fc", _this.payment.cd_fc);
      this.$http
        .post("/m/consume/listPaymentDetail.json", formData)
        .then(function(response) {
          if ("00" != response.data.cdResult) {
            _this.$toast.center("권한이 없습니다.");
            return false;
          }
          var list = response.data.listPaymentDetail;
          if ((list || "") == "" || list.length == 0) {
            _this.isNone = true;
          } else {
            _this.isNone = false;
            _this.listDetail = list;
          }
          _this.seen = true;
        });
    }
    // ---------------------//데이터 이동---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
