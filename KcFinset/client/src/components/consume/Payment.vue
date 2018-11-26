<template>
  <section>
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev" @click="setPrevMM"></button>
        <p>{{ym}}</p>
        <button class="next" @click="setNextMM"></button>
      </div>
      <div class="owl-carousel spend-wrap">
        <div class="wrap">
          <div class="item">
            <p class="key">대금<em>(원)</em></p>
            <p class="value">{{formatNumber(paymentSummary.sum_charge_yyyymm)}}</p>
          </div>
          <div class="item">
            <p class="key">청구내역 수</p>
            <p class="value">{{paymentSummary.count_fc}}</p>
          </div>
        </div>
      </div>
      <a class="btn" v-if="!isScrap">카드를 등록하고 간편하게 조회하세요</a>
    </div>

    <div class="box-list list01">
      <div class="filter-wrap">
        <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
          <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
        </div>
      </div>

      <div v-if="(paymentList||'')==''">
        <div class="nodata">등록 내역이 없습니다</div>
      </div>
      <div v-else v-for="(payment, index) in paymentList" :key="index" class="item">
        <div class="top">
          <p class="symbol"><img :src="payment.imgSrc" alt="" />{{payment.nm_fc}}</p>
          <!-- <p class="text"><span class="circle" :class="settingList[shareList.findIndex(person => person.no_person === payment.no_person)].color">{{payment.nm_person.substring(payment.nm_person.length-2)}}</span></p> -->
          <p class="text"><span class="circle" :class="settingList[shareList.findIndex(person => person.no_person === payment.no_person)].color">{{payment.nm_person}}</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">결제금액</p>
            <p class="number">{{formatNumber(payment.monthly_charge)}}<em>원</em></p>
          </div>
        </div>
      </div>

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
      isScrap: false,
      paymentSummary: { sum_charge_yyyymm: 0, count_fc: 0 },
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
    if (this.$store.state.user.dt_basic > this.standardDt.getDate()) {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
    }
    this.ym = this.formatHead(this.getYm(this.standardDt));

    //화면 이동 로직
    var _this = this;
    let origMousePoint;
    $(document).on("mousedown", "#app", function(event) {
      origMousePoint = event.clientX;
    });
    $(document).on("mouseup", "#app", function(event) {
      if (origMousePoint - event.clientX > 150) {
        _this.setNextMM();
      } else if (origMousePoint - event.clientX < -150) {
        _this.setPrevMM();
      }
    });
    $(document).on("touchstart", "#app", function(event) {
      origMousePoint = event.touches[0].clientX;
    });
    $(document).on("touchend", "#app", function(event) {
      if (origMousePoint - event.changedTouches[0].clientX > 150) {
        _this.setNextMM();
      } else if (origMousePoint - event.changedTouches[0].clientX < -150) {
        _this.setPrevMM();
      }
    });
    ////화면 이동 로직

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
          var list = response.data.paymentList;
          for (var idx in list) {
            list[idx].imgSrc =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[idx].cd_fc;
          }
          _this.isScrap = response.data.isScrap;
          _this.paymentSummary = response.data.paymentSummary;
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
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listPayment();
    },
    formatDateDot: function(date) {
      return Common.formatDateDot(date);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
