<template>
  <div id="wrapper">
    <VueScheduler v-if="isMonth" :events="events" event-display="name" @event-clicked="openDetail" :shareList="shareList" :clickShare="clickShare" :sumData="sumData" @curYM="curYM" ref="scheduler" />

    <vue-modal name="my-modal" transitionName="zoom-in" theme="width: 90%">
      <div slot="body" class="modal-con" data-modal-con="modal01">
        <div class="top">
          {{modalDate}}
          <button class="modal-close"></button>
        </div>
        <div class="cal-con">
          <div class="cal-top">
            <div>
              <em class="income">수입</em>
              <p>{{incomeTotal}} 원</p>
            </div>
            <div>
              <em class="debt">지출</em>
              <p>{{consumeTotal}} 원</p>
            </div>
            <div>
              <em class="loan">대출</em>
              <p>{{debtTotal}} 원</p>
            </div>
          </div>
          <div class="body">
            <!-- <div class="list">
              <div>
                <em class="red">준수</em>
                <em class="debt">지출</em>
                <em class="text">여의도 떡볶이</em>
              </div>
              <div class="won">650,000 원</div>
            </div>
            <div class="list">
              <div>
                <em class="orange">길동</em>
                <em class="debt">지출</em>
                <em class="text">파리크라상</em>
              </div>
              <div class="won">650,000 원</div>
            </div>
            <div class="list">
              <div>
                <em class="red">준수</em>
                <em class="debt">수입</em>
                <em class="text">과외비</em>
              </div>
              <div class="won">650,000 원</div>
            </div> -->
            <div v-for="(incomeVO, index) in incomeList" :key="'income-'+index" class="list">
              <div>
                <em :class="$refs.scheduler.settingList[shareList.findIndex(person => person.no_person === incomeVO.no_person)].color">
                  {{shareList.filter(person => person.no_person === incomeVO.no_person)[0].nm_person}}
                </em>
                <em class="income">수입</em>
                <em class="text">{{incomeVO.contents}}</em>
              </div>
              <div class="won">{{Common.formatNumber(incomeVO.amt_in_out)}} 원</div>
            </div>

            <div v-for="(consumeVO, index) in consumeList" :key="'consume-'+index" class="list">
              <div>
                <em :class="$refs.scheduler.settingList[shareList.findIndex(person => person.no_person === consumeVO.no_person)].color">
                  {{shareList.filter(person => person.no_person === consumeVO.no_person)[0].nm_person}}
                </em>
                <em class="debt">지출</em>
                <em class="text">{{consumeVO.contents}}</em>
              </div>
              <div class="won">{{Common.formatNumber(consumeVO.amt_in_out)}} 원</div>
            </div>

            <div v-for="(debtVO, index) in debtList" :key="'debt-'+index" class="list">
              <div>
                <em :class="$refs.scheduler.settingList[shareList.findIndex(person => person.no_person === debtVO.no_person)].color">
                  {{shareList.filter(person => person.no_person === debtVO.no_person)[0].nm_person}}
                </em>
                <em class="loan">부채</em>
                <em class="text" v-text="(debtVO.nm_fc||'')==''?debtVO.creditor:debtVO.nm_fc"></em>
              </div>
              <div class="won">{{Common.formatNumber(debtVO.amt_repay)}} 원</div>
            </div>
          </div>
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
      sumData: {
        sumIncome: 0,
        sumConsume: 0,
        sumDebt: 0
      },
      incomeTotal: 0,
      consumeTotal: 0,
      debtTotal: 0,
      incomeList: null,
      consumeList: null,
      debtList: null,
      shareList: [],
      standardDt: new Date(),
      isMonth: true,
      Common: Common
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
  created() {
    this.standardDt = Common.formatDate(this.standardDt)
      .replace(/[-]/g, "")
      .substr(0, 6);
    this.listCalendarShareInfo();
  },
  beforeMount() {},
  mounted() {},
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
          _this.listCalendarData(_this.standardDt);
        });
    },
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

          for (var idx in incomeList) {
            _this.makeEvent(incomeList[idx], "income");
            _this.sumData.sumIncome += parseInt(incomeList[idx].amt_in_out);
          }
          for (var idx in consumeList) {
            _this.makeEvent(consumeList[idx], "consume");
            _this.sumData.sumConsume += parseInt(consumeList[idx].amt_in_out);
          }
          for (var idx in debtList) {
            _this.makeEvent(debtList[idx], "debt");
            _this.sumData.sumDebt += parseInt(debtList[idx].amt_in_out);
          }
        });
    },
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
      this.standardDt = ym;
      this.listCalendarData(ym);
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
      this.listCalendarData(
        Common.formatDate(this.standardDt)
          .replace(/[-]/g, "")
          .substr(0, 6)
      );
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
