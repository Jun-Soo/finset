<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <div class="my-graph-wrap">
      <p class="title">신용등급</p>
      <div class="graph">
        <Gauge :value="gaugeValue" :text="gaugeText" />
      </div>
      <a @click="$router.push('/credit/main')">자세히보기</a>
      <div class="info-wrap">
        <div class="left">
          <p class="key">내 상태(상위)</p>
          <p class="value">{{ci_percentage}}<em>%</em></p>
        </div>
        <div class="right">
          <p class="key">신용점수</p>
          <p class="value">{{ci_rating_credit}}</p>
        </div>

      </div>
    </div>

    <div class="my-main-list">
      <div class="list">
        <div class="item">
          <div class="left">
            <a @click="$router.push('/consume/main')">지출</a>
            <p>{{ formatNumber(consumeSumAmt) }}<em>원</em></p>
          </div>
          <div class="right">
            <a @click="$router.push(' ')"></a>
          </div>
        </div>
      </div>
      <div class="list">
        <div class="item">
          <div class="left">
            <a @click="$router.push('/assets/main')">자산</a>
            <p>{{ formatNumber(assetsSumAmt) }}<em>원</em></p>
          </div>
          <div class="right">
            <a @click="$router.push(' ')"></a>
          </div>
        </div>
      </div>
      <div class="list">
        <div class="item">
          <div class="left">
            <a @click="$router.push('/debt/main')">부채</a>
            <p>{{ (debtSumAmt==null)? '-' : formatNumber(debtSumAmt) }}<em>원</em></p>
          </div>
          <div class="right">
            <a @click="$router.push('/debt/register')"></a>
          </div>
        </div>
      </div>
    </div>
    <div class="my-links">
      <div class="wrap">
        <a @click="$router.push('/credit/raiseMain')">신용등급<br>올리기</a>
        <a @click="$router.push('/credit/smartReport')">신용<br>리포트</a>
        <a @click="goShareInfoMain();">정보<br>공유하기</a>
      </div>
    </div>
    <FinsetBottom></FinsetBottom>
  </main>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

import FinsetHeader from "./../common/FinsetHeader";
import FinsetBottom from "./../common/FinsetBottom";

import Gauge from "./../common/Gauge";

export default {
  name: "FinsetMain",
  data() {
    return {
      //신용정보
      creditInfo: "",
      ci_percentage: "", //상위%
      ci_rating_credit: "", //신용점수
      consumeSumAmt: "", //지출총금액
      assetsSumAmt: "", //자산총금액
      debtSumAmt: "", //부채총금액
      gaugeValue: 0,
      gaugeText: ""
    };
  },
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom,
    Gauge
  },
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "main";
    this.getMainInfo();
    //로그인 처리 할때만 자동스트래핑 요청
    if (this.$store.state.isScrap) {
      this.startAutoScrap();
      this.$store.state.isScrap = false;
      this.$parent.startScrapSt();
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/main/getMainInfo.json", {
          params: {}
        })
        .then(response => {
          var creditInfo = response.data.creditInfo;
          if (creditInfo != null) {
            _this.ci_percentage = creditInfo.percentage;
            _this.ci_rating_credit = creditInfo.rating_credit;
          }
          _this.creditInfo = creditInfo;
          _this.consumeSumAmt = response.data.consumeSumAmt;
          _this.assetsSumAmt = response.data.assetsSumAmt;
          _this.debtSumAmt = response.data.debtSumAmt;
          if (_this.creditInfo) {
            _this.gaugeValue = _this.creditInfo.rating_credit / 1000;
            _this.gaugeText = _this.creditInfo.grade_credit + "등급";
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    startAutoScrap: function() {
      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      this.$http
        .post("/m/scrap/getAutoScrapInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          if (Constant.userAgent == "Android") {
            var smsStartDate = result.smsStartDate;
            var smsInclude = result.smsInclude;
            var smsExclude = result.smsExclude;
            window.Android.getSmsList(smsStartDate, smsInclude, smsExclude);
          }
          var bankInfo = "";
          var cardInfo = "";
          var ntsInfo = "";
          //은행 스크래핑 내역
          if (result.autoScrapBankInfo) {
            bankInfo = result.autoScrapBankInfo;
          }
          //카드 스크래핑 내역
          if (result.autoScrapCardInfo) {
            cardInfo = result.autoScrapCardInfo;
          }
          //국세청 스크래핑 내역
          if (result.autoScrapNTSInfo) {
            ntsInfo = result.autoScrapNTSInfo;
          }

          if (Constant.userAgent == "iOS") {
            Jockey.send("startAutoScrap", JSON.parse(bankInfo));
            Jockey.send("startAutoScrap", JSON.parse(cardInfo));
            Jockey.send("startAutoScrap", JSON.parse(ntsInfo));
          } else if (Constant.userAgent == "Android") {
            window.Android.startAutoScrap("bank", bankInfo);
            window.Android.startAutoScrap("card", cardInfo);
            window.Android.startAutoScrap("nts", ntsInfo);
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //공유관리 메인으로 이동
    goShareInfoMain: function() {
      this.$router.push({
        name: "shareMain",
        params: { cd_share: "02" }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
