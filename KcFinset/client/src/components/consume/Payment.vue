<template>
  <section>

    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev" @click="setPrevMM"></button>
        <p>{{ym}}</p>
        <button class="next" @click="setNextMM"></button>
      </div>
      <div class="owl-carousel spend-wrap">
        <carousel :perPage=1>
          <slide class="wrap">
            <div class="item">
              <p class="key">대금<em>(원)</em></p>
              <p class="value">4,550,000</p>
            </div>
            <div class="item">
              <p class="key">청구카드수</p>
              <p class="value">3</p>
            </div>
          </slide>
          <slide class="wrap">
            <div class="item">
              <p class="key">청구카드수</p>
              <p class="value">4,550,000</p>
            </div>
            <div class="item">
              <p class="key">수입<em>(원)</em></p>
              <p class="value">3</p>
            </div>
          </slide>
          <slide class="wrap">
            <div class="item">
              <p class="key">대금<em>(원)</em></p>
              <p class="value">4,550,000</p>
            </div>
            <div class="item">
              <p class="key">청구카드수</p>
              <p class="value">3</p>
            </div>
          </slide>
        </carousel>
      </div>
      <a href="#" class="btn">카드를 등록하고 간편하게 조회하세요</a>
    </div>

    <div class="box-list list01">
      <div class="filter-wrap">
        <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
            <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)" :for="settingList[index].id">{{person.nm_person}}</label>
        </div>
      </div>

      <div v-for="payment in paymentList" :key="payment.no_person" class="item">
        <div class="top">
          <p class="symbol"><img :src="payment.imgSrc" alt=""/>{{payment.nm_fc}}</p>
          <p class="circle"><span class="blue">{{payment.nm_person}}</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">결제금액</p>
            <p class="number">{{formatNumber(payment.monthly_charge)}}<em>원</em></p>
          </div>
        </div>
        <!-- <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">1234-1234-1234-****</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">2018.04.15</p>
          </div>
        </div> -->
      </div>
      <!-- <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
          <p class="circle red"><span class="red">준수</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">결제금액</p>
            <p class="number">2,450,000<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">1234-1234-1234-****</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">2018.04.15</p>
          </div>
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
          <p class="circle"><span class="blue">준수</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">결제금액</p>
            <p class="number">2,450,000<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">1234-1234-1234-****</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">2018.04.15</p>
          </div>
        </div>
      </div> -->

    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumePayment",
  data() {
    return {
      ym: "",
      standardDt: new Date(),
      shareList: [],
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "greend", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      paymentList: []
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드 대금 조회";
  },
  created() {
    this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeShareInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getYm: function(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    formatHead: function(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    setPrevMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listPayment();
    },
    setNextMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listPayment();
    },
    listConsumeShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeSharePersonInfo.json", { params: {} })
        .then(function(response) {
          var list = response.data.listConsumeSharePersonInfo;
          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listPayment();
        });
    },
    listPayment: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPayment.json", {
          params: {
            charge_yyyymm: _this.ym.replace(".", ""),
            no_person_list: _this.filterShareList()
          }
        })
        .then(function(response) {
          var list = response.data.payment;
          for (var idx in list) {
            list[idx].imgSrc =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[idx].cd_fc;
          }
          _this.paymentList = list;
        });
    },
    filterShareList: function() {
      var shareList = new Array();
      var _this = this;
      for (var idx in _this.shareList) {
        if (_this.shareList[idx].isShow) {
          shareList.push(_this.shareList[idx].no_person);
        }
      }
      return shareList;
    },
    formatNumber: function(number) {
      return Common.formatNumber(number);
    },
    clickShare: function(params) {
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listPayment();
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
