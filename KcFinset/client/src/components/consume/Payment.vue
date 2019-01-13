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
            <p class="value">{{amt_payment}}</p>
          </div>
          <div class="item">
            <p class="key">청구내역 수</p>
            <p class="value">{{cnt_payment}}</p>
          </div>
        </div>
      </div>
      <a class="btn" v-if="!isScrap" @click="goCtrlFcLink">카드를 등록하고 간편하게 조회하세요</a>
    </div>

    <div class="box-list list01">
      <div class="filter-wrap" v-if="shareList.length != 1">
        <div v-for="(person, index) in shareList" :key="index" class="filter" :class="settingList[index].color">
          <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
        </div>
      </div>

      <div v-if="(paymentList||'')==''">
        <div class="nodata">등록 내역이 없습니다</div>
      </div>
      <div v-else v-for="(payment, index) in paymentList" :key="index" @click="goPaymentDetail(payment)" class="item">
        <div class="top">
          <p class="symbol"><img :src="payment.imgSrc" alt="" />{{payment.nm_fc}}</p>
          <p class="text" v-if="shareList.length != 1">
            <span class="circle" :class="settingList[shareList.findIndex(person => person.no_person === payment.no_person)].color">{{payment.nm_person}}</span>
          </p>
        </div>
        <div class="number-wrap">
          <div class="left">
            {{formatNmCard(payment.nm_card)}}
          </div>
          <div class="right">
            <p class="key">결제금액</p>
            <p class="number" style="text-align:right">{{Common.formatNumber(payment.monthly_charge)}}<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">{{formatNoCard(payment.no_card)}}</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">{{Common.formatDateDot(payment.dt_payment)}}</p>
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
      paymentList: [] // 청구내역 리스트
    };
  },
  components: {},
  computed: {
    amt_payment: function() {
      var amt = 0;
      for (var idx in this.paymentList) {
        amt += parseInt(this.paymentList[idx].monthly_charge);
      }
      return Common.formatNumber(amt);
    },
    cnt_payment: function() {
      if ((this.paymentList || "") != "") {
        return this.paymentList.length;
      } else {
        return 0;
      }
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드 대금 조회";
  },
  created() {
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
    this.chkExistPayment();
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
    // 리스트 형태 변환
    formatList: function(list) {
      var _this = this;
      for (var idx in list) {
        var shareIndex = _this.shareList.findIndex(
          person => person.no_person == list[idx].no_person
        );

        list[idx].imgSrc =
          "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[idx].cd_fc;
        list[idx].nm_person = _this.shareList[shareIndex].nm_person;
        list[idx].sortOrder = shareIndex;
      }
      list.sort(function(a, b) {
        if (
          a.sortOrder == b.sortOrder &&
          (a.dt_payment || "") != "" &&
          (b.dt_payment || "") != ""
        ) {
          return parseInt(a.dt_payment) - parseInt(b.dt_payment);
        }
        return a.sortOrder - b.sortOrder;
      });
      return list;
    },
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
    // 카드명 길이 자르기
    formatNmCard: function(nm_card) {
      if ((nm_card || "") == "") {
        return nm_card;
      } else if (nm_card.length < 25) {
        return nm_card;
      } else {
        return (nm_card + "").substring(0, 25) + "...";
      }
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
    // 카드대금 상세 화면으로 이동
    // goPaymentDetail: function(no_person, cd_fc, no_card) {
    goPaymentDetail: function(payment) {
      var _this = this;
      this.$router.push({
        name: "consumePaymentDetail",
        params: {
          payment: payment,
          charge_yyyymm: _this.ym.replace(".", "")
        }
      });
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
          list = _this.formatList(list);
          _this.isScrap = response.data.isScrap;
          _this.paymentList = list;
          _this.seen = true;
        });
    },
    // 카드대금조회 데이터 존재여부 확인 및 날짜 조회
    chkExistPayment: function() {
      var _this = this;
      this.$http
        .get("/m/consume/chkExistPayment.json")
        .then(function(response) {
          var charge_yyyymm = response.data.charge_yyyymm;

          if ((charge_yyyymm || "") == "") {
            if (_this.$store.state.user.dt_basic > _this.standardDt.getDate()) {
              _this.standardDt.setMonth(_this.standardDt.getMonth() - 1);
            }
          } else {
            _this.standardDt = _this.Common.formatDtObjFromStr(
              charge_yyyymm + "01"
            ); // 함수가 원하는 포맷은 yyyymmdd이다
          }
          _this.ym = _this.formatHead(_this.getYm(_this.standardDt));
          _this.listConsumeShareInfo();
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
.list01 .item .number-wrap .left {
  width: 60%;
}
.list01 .item .number-wrap .right {
  width: 40%;
}
</style>
