<template>
  <div id="wrapper" v-if="seen">
    <VueScheduler :events="events" event-display="name" @event-clicked="openDetail" :shareList="shareList" :clickShare="clickShare" :sumData="sumData" :type="type" :settingList="settingList" @curYM="curYM" ref="scheduler" />

    <vue-modal name="my-modal" transitionName="fade" theme="width: 90%" :height="'auto'">
      <EventPop slot="body" :modalDate="modalDate" :incomeList="incomeList" :consumeList="consumeList" :debtList="debtList" :incomeTotal="incomeTotal" :consumeTotal="consumeTotal" :debtTotal="debtTotal" :settingList="settingList" :shareList="shareList" @hideDetail="hideDetail" />
    </vue-modal>
  </div>
</template>

<script>
import Common from "../../assets/js/common.js";
import EventPop from "./sub/EventPop";
import VueScheduler from "../plugins/calendar/components/VueScheduler";
import "../plugins/calendar/lib/main.css";

export default {
  name: "CommonMonthCal",
  data() {
    return {
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      events: [], // 일별 수입, 지출, 대출 리스트
      modalDate: "", // 상세 내역 모달
      // 합계 데이터
      sumData: {
        sumIncome: 0,
        sumConsume: 0,
        sumDebt: 0
      },
      incomeTotal: "0", // 한달 수입 합계
      consumeTotal: "0", // 한달 지출 합계
      debtTotal: "0", // 한달 대출 합계
      incomeList: null, // 수입 리스트
      consumeList: null, // 지출 리스트
      debtList: null, // 부채 리스트
      shareList: [], // 공유된 사용자
      standardDt: new Date(), // 화면 기준 날짜
      type: "", // 달력 타입(어떤 화면에서 넘어 왔는지에 따라 다름, consume: 지출메인, debt: 부채메인)
      // 공유된 사용자의 색 class 및 id
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ]
    };
  },
  components: {
    VueScheduler,
    EventPop
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "캘린더(월)";
  },
  created() {
    this.standardDt = Common.formatDate(this.standardDt)
      .replace(/[-]/g, "")
      .substr(0, 6);
    this.type = this.$route.query.type;
    this.listCalendarShareInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포맷---------------------
    // 상세 모달에 표시될 데이터
    formatModalDate(date) {
      var mm = date.getMonth() + 1;
      var dd = date.getDate();

      var weekdays = new Array(7);
      weekdays[0] = "일요일";
      weekdays[1] = "월요일";
      weekdays[2] = "화요일";
      weekdays[3] = "수요일";
      weekdays[4] = "목요일";
      weekdays[5] = "금요일";
      weekdays[6] = "토요일";

      var weekday = weekdays[date.getDay()];
      return mm + "." + dd + ". " + weekday;
    },
    // ---------------------//데이터 포맷---------------------
    // ---------------------화면 이벤트---------------------
    // 상세 모달 열기
    openDetail(params) {
      this.modalDate = this.formatModalDate(params.date);
      var ymd = Common.formatDate(params.date, "yyyymmdd").replace(/-/g, "");
      this.listDetailCalendarData(
        ymd,
        params.isActiveIncome,
        params.isActiveConsume,
        params.isActiveDebt
      );
    },
    // 공유된 사용자 버튼 클릭 시
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listCalendarData(
        Common.formatDate(this.standardDt)
          .replace(/[-]/g, "")
          .substr(0, 6)
      );
    },
    // ---------------------//화면 이벤트---------------------
    // ---------------------데이터 이동---------------------
    // 캘린더용 공유된 사용자 리스트 조회
    listCalendarShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/debt/listCalendarShareInfo.json", { params: {} })
        .then(function(response) {
          var list = response.data.listCalendarShareInfo;

          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listCalendarData(_this.standardDt);
        });
    },
    // 캘린더 데이터 리스트 조회
    listCalendarData(ym) {
      var _this = this;
      this.$http
        .get("/m/debt/listCalendarData.json", {
          params: { ym: ym, no_person_list: _this.filterShareList() }
        })
        .then(function(response) {
          _this.events = [];
          var data = response.data;

          var incomeList = data.incomeList;
          var consumeList = data.consumeList;
          var debtList = data.debtList;

          _this.sumData.sumIncome = 0;
          _this.sumData.sumConsume = 0;
          _this.sumData.sumDebt = 0;
          // 금보원
          // for (var idx in incomeList) {
          //   _this.makeEvent(incomeList[idx], "income");
          //   _this.sumData.sumIncome += parseInt(incomeList[idx].amt_in_out);
          // }
          // for (var idx in consumeList) {
          //   _this.makeEvent(consumeList[idx], "consume");
          //   _this.sumData.sumConsume += parseInt(consumeList[idx].amt_in_out);
          // }

          for (var idx in debtList) {
            _this.makeEvent(debtList[idx], "debt");
            _this.sumData.sumDebt += parseInt(debtList[idx].amt_repay);
          }
          _this.seen = true;
        });
    },
    // 캘린더 데이터 상세내역 조회
    listDetailCalendarData(ymd, isActiveIncome, isActiveConsume, isActiveDebt) {
      var _this = this;
      this.$http
        .get("/m/debt/listDetailCalendarData.json", {
          params: {
            ymd: ymd,
            isActiveIncome: isActiveIncome,
            isActiveConsume: isActiveConsume,
            isActiveDebt: isActiveDebt,
            no_person_list: _this.filterShareList()
          }
        })
        .then(function(response) {
          if (isActiveIncome) {
            _this.incomeTotal = Common.formatNumber(response.data.incomeTotal);
            _this.incomeList = response.data.incomeList;
          } else {
            _this.incomeTotal = "0";
            _this.incomeList = null;
          }
          if (isActiveConsume) {
            _this.consumeTotal = Common.formatNumber(
              response.data.consumeTotal
            );
            _this.consumeList = response.data.consumeList;
          } else {
            _this.consumeTotal = "0";
            _this.consumeList = null;
          }
          if (isActiveDebt) {
            _this.debtTotal = Common.formatNumber(response.data.debtTotal);
            _this.debtList = response.data.debtList;
          } else {
            _this.debtTotal = "0";
            _this.debtList = null;
          }

          _this.$modals.show("my-modal");
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 상세내역을 데이터를 달력에서 사용할 수 있도록 가공
    makeEvent(vo, type) {
      var date, name, contents, amt, color;
      switch (type) {
        case "income":
          var ymd = vo.dt_trd;
          date = new Date(
            ymd.substring(0, 4),
            parseInt(ymd.substring(4, 6)) - 1,
            ymd.substring(6, 8)
          );
          name = Common.formatNumber(vo.amt_in_out);
          amt = vo.amt_in_out;
          color = "#4f82d6";
          type = type;
          break;
        case "consume":
          var ymd = vo.dt_trd;
          date = new Date(
            ymd.substring(0, 4),
            parseInt(ymd.substring(4, 6)) - 1,
            ymd.substring(6, 8)
          );
          name = Common.formatNumber(vo.amt_in_out);
          amt = vo.amt_in_out;
          color = "#25bdd5";
          type = type;
          break;
        case "debt":
          var ymd = vo.req_yyyymmdd;
          date = new Date(
            ymd.substring(0, 4),
            parseInt(ymd.substring(4, 6)) - 1,
            ymd.substring(6, 8)
          );
          name = Common.formatNumber(vo.amt_repay);
          amt = vo.amt_repay;
          color = "#e52638";
          type = type;
          break;
      }
      this.events.push({
        date: date,
        name: name,
        amt: amt,
        color: color,
        type: type
      });
    },
    // 날짜 세팅
    curYM(ym) {
      this.standardDt = ym;
      this.listCalendarData(ym);
    },
    // 상세내역 모달 감추기
    hideDetail() {
      this.$modals.hide("my-modal");
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
.modal-subHeader {
  margin: 15px 0;
}
.modal-list .left,
.modal-subHeader .left {
  display: inline;
}
.modal-list .right,
.modal-subHeader .right {
  float: right;
  margin: 0 auto;
}
.modal-eachTotal {
  margin-top: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #666;
}
.modal-label {
  color: #fff;
}
.modal-amt + .modal-label {
  margin-left: 20px;
}
.label-income {
  background: #3b86ff;
}
.label-consume {
  background: #47d147;
}
.label-debt {
  background: #ff3333;
}
.modal-list {
  margin: 4px;
  padding: 4px;
  border: thin solid #bbb;
  border-radius: 6px;
}
.list-text {
  margin-left: 14px;
}
</style>
