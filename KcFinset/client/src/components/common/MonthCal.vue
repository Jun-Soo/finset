<template>
  <div id="wrapper">
    <VueScheduler
      :events="events"
      event-display="name"
      @event-clicked="openDetail"
      :shareList="shareList"
      :clickShare="clickShare"
      @curYM="curYM"
    />
    <vue-modal
      name="my-modal"
      transitionName="zoom-in"
      theme="width:100% !important; max-height: 80%; top: 134px; position:absolute"
    >
      <h2
        slot="header"
        @click="hide"
      >FINSET</h2>
      <div class="modal-subHeader">
        <p class="left">{{modalDate}}</p>
        <p class="right">{{sumTotal}} 원</p><br />
        <div class="modal-eachTotal">
          <p
            v-if="incomeTotal!=0"
            class="left modal-label label-income"
          >수입</p>
          <p
            v-if="incomeTotal!=0"
            class="left modal-amt amt-income"
          > {{incomeTotal}} 원</p>
          <p
            v-if="consumeTotal!=0"
            class="left modal-label label-consume"
          >지출</p>
          <p
            v-if="consumeTotal!=0"
            class="left modal-amt amt-consume"
          > {{consumeTotal}} 원</p>
          <p
            v-if="debtTotal!=0"
            class="left modal-label label-debt"
          >부채</p>
          <p
            v-if="debtTotal!=0"
            class="left modal-amt amt-debt"
          > {{debtTotal}} 원</p>
        </div>
      </div>
      <div
        class="modal-income"
        v-if="incomeList!=null"
      >
        <div
          class="modal-list"
          v-for="incomeVO in incomeList"
          :key="incomeVO.index"
        >
          <p class="left modal-label label-income">수입</p>
          <p class="left list-text">{{incomeVO.contents}}</p>
          <p class="right">{{formatNumber(incomeVO.amt_in_out)}} 원</p>
        </div>
      </div>
      <div
        class="modal-consume"
        v-if="consumeList!=null"
      >
        <div
          class="modal-list"
          v-for="consumeVO in consumeList"
          :key="consumeVO.index"
        >
          <p class="left modal-label label-consume">지출</p>
          <p class="left list-text">{{consumeVO.contents}}</p>
          <p class="right">{{formatNumber(consumeVO.amt_in_out)}} 원</p>
        </div>
      </div>
      <div
        class="modal-debt"
        v-if="debtList!=null"
      >
        <div
          class="modal-list"
          v-for="debtVO in debtList"
          :key="debtVO.index"
        >
          <p class="left modal-label label-debt">부채</p>
          <p class="left list-text">{{debtVO.nm_biz}}</p>
          <p class="right">{{formatNumber(debtVO.amt_repay)}} 원</p>
        </div>
      </div>
    </vue-modal>
  </div>
</template>

<script>
import Common from "../../assets/js/common.js";
import VueScheduler from "../plugins/calendar/components/VueScheduler";
import "../plugins/calendar/lib/main.css";

export default {
  name: "CommonCalendar",
  data() {
    return {
      events: [],
      modalDate: "",
      sumTotal: 0,
      incomeTotal: 0,
      consumeTotal: 0,
      debtTotal: 0,
      incomeList: null,
      consumeList: null,
      debtList: null,
      shareList: []
    };
  },
  components: {
    VueScheduler
  },
  // computed: {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "캘린더";
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.listCalendarShareInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
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
          _this.getCalendarData(
            Common.formatDate(new Date())
              .replace(/[-]/g, "")
              .substr(0, 6)
          );
        });
    },
    openDetail(params) {
      this.modalDate = this.formatModalDate(params.date);
      var ymd = Common.formatDate(params.date, "yyyymmdd").replace(/-/g, "");
      this.listCalendarData(
        ymd,
        params.isActiveIncome,
        params.isActiveConsume,
        params.isActiveDebt
      );
    },
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
    getCalendarData(ym) {
      var _this = this;
      this.$http
        .get("/m/debt/getCalendarData.json", {
          params: { ym: ym }
        })
        .then(function(response) {
          _this.events = [];
          var data = response.data;

          var incomeList = data.incomeList;
          var consumeList = data.consumeList;
          var debtList = data.debtList;

          for (var idx in incomeList) {
            _this.makeEvent(incomeList[idx], "income");
          }
          for (var idx in consumeList) {
            _this.makeEvent(consumeList[idx], "consume");
          }
          for (var idx in debtList) {
            _this.makeEvent(debtList[idx], "debt");
          }
        });
    },
    listCalendarData(ymd, isActiveIncome, isActiveConsume, isActiveDebt) {
      var _this = this;
      this.$http
        .get("/m/debt/listCalendarData.json", {
          params: {
            ymd: ymd,
            isActiveIncome: isActiveIncome,
            isActiveConsume: isActiveConsume,
            isActiveDebt: isActiveDebt
          }
        })
        .then(function(response) {
          _this.sumTotal = Common.formatNumber(response.data.sumTotal);
          if (isActiveIncome) {
            _this.incomeTotal = Common.formatNumber(response.data.incomeTotal);
            _this.incomeList = response.data.incomeList;
          } else {
            _this.incomeTotal = 0;
            _this.incomeList = null;
          }
          if (isActiveConsume) {
            _this.consumeTotal = Common.formatNumber(
              response.data.consumeTotal
            );
            _this.consumeList = response.data.consumeList;
          } else {
            _this.consumeTotal = 0;
            _this.consumeList = null;
          }
          if (isActiveDebt) {
            _this.debtTotal = Common.formatNumber(response.data.debtTotal);
            _this.debtList = response.data.debtList;
          } else {
            _this.debtTotal = 0;
            _this.debtList = null;
          }

          _this.$modals.show("my-modal");
        });
    },
    curYM(ym) {
      this.getCalendarData(ym);
    },
    formatModalDate(date) {
      var mm = date.getMonth() + 1;
      var dd = date.getDate();

      var weekdays = new Array(7);
      weekdays[0] = "월요일";
      weekdays[1] = "화요일";
      weekdays[2] = "수요일";
      weekdays[3] = "목요일";
      weekdays[4] = "금요일";
      weekdays[5] = "토요일";
      weekdays[6] = "일요일";

      var weekday = weekdays[date.getDay()];
      return mm + "." + dd + ". " + weekday;
    },
    formatNumber(number) {
      return Common.formatNumber(number);
    },
    hide() {
      console.log("ab");
      debugger;
      this.$modals.hide("my-modal");

      console.log("cd");
    },
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      // this.listConsumeInfo();
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
    }
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
