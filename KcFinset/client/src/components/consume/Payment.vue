<template>
  <section v-if="seen">
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
            <p class="value">{{Common.formatNumber(paymentSummary.sum_charge_yyyymm)}}</p>
          </div>
          <div class="item">
            <p class="key">청구내역 수</p>
            <p class="value">{{paymentSummary.count_fc}}</p>
          </div>
        </div>
      </div>
      <a class="btn" v-if="!isScrap" @click="goCtrlFcLink">카드를 등록하고 간편하게 조회하세요</a>
    </div>

    <div class="box-list list01">
      <div class="filter-wrap" v-if="shareList.length != 1">
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
          <p class="text" v-if="shareList.length != 1">
            <span class="circle" :class="settingList[shareList.findIndex(person => person.no_person === payment.no_person)].color">{{payment.nm_person}}</span>
          </p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">결제금액</p>
            <p class="number">{{Common.formatNumber(payment.monthly_charge)}}<em>원</em></p>
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
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      ym: "", // 상단에 들어갈 월 텍스트
      standardDt: new Date(), // 기준일
      shareList: [], // 공유된 사용자 리스트
      // 공유된 사용자의 색 class 및 id
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      isScrap: false, // 스크래핑 여부
      paymentSummary: { sum_charge_yyyymm: 0, count_fc: 0 }, // 대금액 및 청구내역 수
      paymentList: [] // 청구내역 리스트
    };
  },
  components: {},
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
    // ---------------------데이터 포멧---------------------
    // 상단에 표출될 년 월 텍스트
    formatHead: function(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    // ---------------------//데이터 포멧---------------------
    // ---------------------화면 컨트롤---------------------
    // 공유된 사용자 버튼 클릭 시
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listPayment();
    },
    // 금융사 연동 화면으로 이동
    goCtrlFcLink: function() {
      this.$router.push("/scrap/CtrlFcLink");
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 공유된 사용자 정보 리스트 조회
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
    // 카드 대금 리스트 조회
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
          _this.seen = true;
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // Date 에서 YYYYMM 형식의 String으로 데이터 변형
    getYm: function(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    // 이전 달 세팅
    setPrevMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listPayment();
    },
    // 다음 달 세팅
    setNextMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listPayment();
    },
    // 공유된 사용자 중 on 처리 되어 있는 사용자 리스트
    filterShareList: function() {
      var shareList = new Array();
      var _this = this;
      for (var idx in _this.shareList) {
        if (_this.shareList[idx].isShow) {
          shareList.push(_this.shareList[idx].no_person);
        }
      }
      return shareList;
    }
    // ---------------------//기타---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
