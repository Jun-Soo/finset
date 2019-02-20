<template>
  <section v-if="seen">
    <div class="assets-top">
      <div class="wrap">
        <div class="item">
          <p class="key">자산<em>(원)</em></p>
          <p class="value">{{(assetsSumAmt==null)? '-' : formatNumber(assetsSumAmt)}}</p>
        </div>
        <div class="item">
          <p class="key">부채<em>(원)</em></p>
          <p class="value">{{(debtSumAmt==null)? '-' : formatNumber(debtSumAmt)}}</p>
        </div>
      </div>
    </div>

    <div class="banner-wrap owl-carousel">
      <carousel :perPage=1 :autoplay=true :autoplayTimeout=4000 :speed=700 :loop=true>
        <!-- <slide class="item">
          <a @click="goMenu('ctrlFcLink')">
            <div class="banner">
              <div class="left">
                <p class="key">나의 주식계좌 찾기</p>
                <p class="value">잠자는 주식 계좌를<br>찾아드립니다</p>
              </div>
              <div class="right">
                <img src="../../assets/images/assets/main_banner1.png" alt="" />
              </div>
            </div>
          </a>
        </slide> -->
        <!-- 준비중 -->
        <!-- <slide class="item">
          <a @click="goMenu('ctrlFcLink')">
            <div class="banner">
              <div class="left">
                <p class="key">내 토지 찾기</p>
                <p class="value">공인 인증 한번으로<br>나의 토지를 찾아보세요</p>
              </div>
              <div class="right">
                <img src="../../assets/images/assets/main_banner2.png" alt="" />
              </div>
            </div>
          </a>
        </slide> -->
        <!-- 준비중 
        <slide class="item">
          <a @click="goMenu('assetsAnalysis')">
            <div class="banner">
              <div class="left">
                <p class="key">자산분석</p>
                <p class="value">자산별 진단을 받아보세요</p>
              </div>
              <div class="right">
                <img src="../../assets/images/assets/main_banner3.png" alt="" />
              </div>
            </div>
          </a>
        </slide>
        -->
        <slide class="item">
          <a @click="goMenu('etc')">
            <div class="banner">
              <div class="left">
                <p class="key">자산관리</p>
                <p class="value">금융 자산 및 기타자산을 관리해보세요.</p>
              </div>
              <div class="right">
                <img src="../../assets/images/assets/main_banner3.png" alt="" />
              </div>
            </div>
          </a>
        </slide>
      </carousel>
    </div>

    <div class="list02 box-list pb90">

      <div @click="goMenu('bank')" class="item">
        <div class="flex">
          <p class="corp big">은행<em>{{assetsBankInfo.cnt_account}}건</em></p>
          <p class="number big">{{(assetsBankInfo.sum_amt_balance == null)? '-' : formatNumber(assetsBankInfo.sum_amt_balance)}}<em>원</em></p>
        </div>
      </div>

      <div v-if="showStock" @click="goMenu('stock')" class="item">
        <div class="flex">
          <p class="corp big">증권(주식/펀드/CMA)<em>{{assetsStockInfo.cnt_account}}건</em></p>
          <p class="number big">{{(assetsStockInfo.sum_amt_stock == null)? '-' : formatNumber(assetsStockInfo.sum_amt_stock)}}<em>원</em></p>
        </div>
      </div>

      <!--
      <div class="item">
        <div class="flex">
          <p class="corp big">부동산<em>3건</em></p>
          <p class="number big">46,570,000<em>원</em></p>
        </div>
      </div>
      -->
      <div @click="goMenu('etc')" class="item">
        <div class="flex">
          <p class="corp big">기타<em>{{assetsEtcInfo.cnt_item}}건</em></p>
          <p class="number big">{{(assetsEtcInfo.sum_amt_evaluation == null)? '-' : formatNumber(assetsEtcInfo.sum_amt_evaluation)}}<em>원</em></p>
        </div>
      </div>
      <button class="btn-spend-add" @click="$router.push('/assets/dirInput')"></button>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsMain",
  data() {
    return {
      seen: false,
      assetsSumAmt: "", //자산총금액
      debtSumAmt: "", //부채총금액
      assetsBankInfo: "", //은행정보
      assetsStockInfo: "", //증권정보
      assetsEtcInfo: "", //기타정보
      showStock: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "assets";
  },
  created() {
    this.getAssetsMainInfo();
  },
  beforeMount() {},
  mounted() {
    if (Constant.tester.indexOf(this.$store.state.user.hp) > -1) {
      this.showStock = true;
    } else {
      this.showStock = false;
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 자산메인 정보조회
    getAssetsMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsMainInfo.json", {
          params: {}
        })
        .then(response => {
          _this.assetsSumAmt = response.data.assetsSumAmt;
          _this.debtSumAmt = response.data.debtSumAmt;
          _this.assetsBankInfo = response.data.assetsBankInfo;
          _this.assetsStockInfo = response.data.assetsStockInfo;
          _this.assetsEtcInfo = response.data.assetsEtcInfo;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    //메뉴이동
    goMenu: function(menu) {
      //주식계좌찾기
      if ("ctrlFcLink" == menu) {
        this.$router.push({
          name: "scrapCtrlFcLink",
          params: { tab: "stock" }
        });
        //자산분석
      } else if ("assetsAnalysis" == menu) {
        this.$dialogs.alert("서비스 준비중 입니다.", Constant.options);
        return false;
        // this.$router.push({
        //   name: "assetsBankMain",
        //   params: {}
        // });
        //은행메인
      } else if ("bank" == menu) {
        this.$router.push({
          name: "assetsBankMain",
          params: {}
        });
        //증권메인
      } else if ("stock" == menu) {
        this.$router.push({
          name: "assetsStockMain",
          params: {}
        });
      } else if ("etc" == menu) {
        this.$router.push({
          name: "assetsEtcMain",
          params: {}
        });
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
