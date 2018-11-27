<template>
  <section v-if="seen">
    <div class="credit-top">
      <div class="time">{{ currentDate }}</div>
      <div class="wrap">
        <div class="item">
          <p class="key">신용등급</p>
          <p class="value">{{ b_grade_credit }}</p>
        </div>
        <div class="item">
          <p class="key">신용점수</p>
          <p class="value">{{b_rating_credit}}</p>
          <p v-if="b_rating_diff > 0" class="plus">+{{b_rating_diff}}</p>
          <p v-else-if="b_rating_diff < 0" class="minus">{{b_rating_diff}}</p>
        </div>
        <div class="item">
          <p class="key">상위</p>
          <p class="value">{{b_percentage}}%</p>
        </div>
      </div>
    </div>

    <div class="banner-wrap owl-carousel">
      <carousel :perPage=1>
        <slide class="item">
          <a @click="$router.push('/credit/raiseMain')">
            <div class="banner">
              <div class="left">
                <p class="key">신용등급 올리기</p>
                <p class="value">클릭 한번으로<br />신용점수를 올리세요</p>
              </div>
              <div class="right">
                <img src="../../assets/images/main/banner_ico.png" alt="" />
              </div>
            </div>
          </a>
        </slide>
        <slide class="item">
          <a @click="$router.push('/credit/smartReport')">
            <div class="banner">
              <div class="left">
                <p class="key">나의 신용 통계분석</p>
                <p class="value">나의 신용상태가 궁금하다면 ?</p>
              </div>
              <div class="right">
                <img src="../../assets/images/main/banner_ico.png" alt="" />
              </div>
            </div>
          </a>
        </slide>
        <slide class="item">
          <a @click="goMenu('news')">
            <div class="banner">
              <div class="left">
                <p class="key">신용 뉴스</p>
                <p class="value">신용을 관리하기 위한 꿀팁과<br />뉴스를 확인하세요</p>
              </div>
              <div class="right">
                <img src="../../assets/images/main/banner_ico.png" alt="" />
              </div>
            </div>
          </a>
        </slide>
      </carousel>
    </div>

    <div class="credit-change">
      <div class="top">
        <p class="title">나의 신용정보 변동</p>
      </div>
      <div class="link">
        <a @click="$router.push('/credit/detail')">
          {{ ch_dt_info+" "+ch_nm_fc+" "+ch_change_contents+"되었습니다" }}
        </a>
      </div>
    </div>

    <div class="credit-status">
      <p class="title">나의 신용거래 현황</p>
      <div class="wrap">
        <div class="item">
          <a @click="$router.push('/credit/cardInfo')">
            <p class="key">8월 카드이용</p>
            <p class="value">{{formatNumber(cardSumAmt)}}<em>원</em></p>
          </a>
        </div>
        <div class="item">
          <a @click="$router.push('/credit/loanInfo')">
            <p class="key">대출 잔액</p>
            <p class="value">{{formatNumber(debtSumAmtRemain)}}<em>원</em></p>
          </a>
        </div>
        <div class="item">
          <a @click="$router.push('/credit/overdueInfo')">
            <p class="key">연체 원금</p>
            <p class="value">{{formatNumber(overdueSumAmt)}}<em>원</em></p>
          </a>
        </div>
        <div class="item">
          <a @click="$router.push('/credit/guaranteeInfo')">
            <p class="key">연대보증 원금</p>
            <p class="value">{{formatNumber(guaranteeSumAmt)}}<em>원</em></p>
          </a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditMain",
  data() {
    return {
      seen: false,
      currentDate: "", //현재일자
      //신용 기본정보
      baseInfo: "",
      b_grade_credit: "", //신용등급
      b_rating_credit: "", //신용점수
      b_rating_diff: "", //전월 신용점수 차이
      b_percentage: "", //상위%
      //나의 신용정보 변동내역
      changeInfo: "",
      ch_dt_info: "", //일자
      ch_nm_fc: "", //금융사명
      ch_change_contents: "", //내용
      cardSumAmt: "0", //카드이용금액
      debtSumAmtRemain: "0", //대출잔액
      overdueSumAmt: "0", //연체원금
      guaranteeSumAmt: "0" //연대보증원금
    };
  },
  component: {},
  //   computed() {},
  beforeCreate() {},
  created() {
    // this.$store.state.isLoading = true;
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "credit";

    this.getCreditMainInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 신용정보 조회
    getCreditMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditMainInfo.json", {
          params: {}
        })
        .then(response => {
          _this.currentDate = response.data.currentDate;

          var baseInfo = response.data.baseInfo;
          if (baseInfo != null) {
            _this.b_grade_credit = baseInfo.grade_credit;
            _this.b_rating_credit = baseInfo.rating_credit;
            _this.b_rating_diff = baseInfo.rating_diff;
            _this.b_percentage = baseInfo.percentage;
          }
          _this.baseInfo = baseInfo;
          // console.log("baseInfo : "+JSON.stringify(_this.baseInfo));

          var changeInfo = response.data.changeInfo;
          if (changeInfo != null) {
            _this.ch_dt_info = changeInfo.dt_info;
            _this.ch_nm_fc = changeInfo.nm_fc;
            _this.ch_change_contents = changeInfo.change_contents;
          }
          _this.changeInfo = changeInfo;
          // console.log("changeInfo : "+JSON.stringify(_this.changeInfo));

          _this.cardSumAmt = response.data.cardSumAmt;
          _this.debtSumAmtRemain = response.data.debtSumAmtRemain;
          _this.overdueSumAmt = response.data.overdueSumAmt;
          _this.guaranteeSumAmt = response.data.guaranteeSumAmt;

          // _this.$store.state.isLoading = false;
          _this.seen = true;
        })
        .catch(e => {
          //this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    //메뉴이동
    goMenu: function(menu) {
      if ("news" == menu) {
        this.$router.push({
          name: "newsMain",
          query: { scKeyword: ["04"] }
        });
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
