<template>
  <section v-if="seen">
    <div class="report-top">
      <div class="checks round">
        <input type="radio" id="rds1" :class="{'checked':dataPeriod === 'yr'}" v-model="dataPeriod" value="yr"><label for="rds1">년</label>
        <input type="radio" id="rds2" :class="{'checked':dataPeriod === 'mon'}" v-model="dataPeriod" value="mon"><label for="rds2">월</label>
        <input type="radio" id="rds3" :class="{'checked':dataPeriod === 'week'}" v-model="dataPeriod" value="week"><label for="rds3">주</label>
      </div>
      <div class="date">
        <p>
          <datepicker v-model="dt_from" ref="datepicker1" :opend="Common.datepickerInit('div-date', this)" :format="formatDateDot" :language="ko" class="div-date"></datepicker>
          <button class="cal" @click="openDatepicker1"></button>
        </p>
        <p>
          <datepicker v-model="dt_to" ref="datepicker2" :opend="Common.datepickerInit('div-date', this)" :language="ko" :format="formatDateDot" class="div-date"></datepicker>
          <button class="cal" @click="openDatepicker2"></button>
        </p>
      </div>
      <div class="filter-wrap mt20">
        <!-- <div class="filter" :class="settingList[index].color">
        <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
      </div> -->
        <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
          <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="report-con">
        <div class="flex">
          <div>
            <p class="key"><strong>수입</strong>(만원)</p>
            <p class="value">{{incomeSum}}</p>
          </div>
          <div>
            <p class="key"><strong>지출</strong>(만원)</p>
            <p class="value">{{consumeSum}}</p>
          </div>
        </div>
        <!-- <div class="graph"> -->
        <Graph v-if="chartList" ref="graph" v-model="chartList" :chartList="chartList" :consumeForm="consumeForm" :dt_from="dt_from" :dt_to="dt_to" :dataPeriod="dataPeriod"></Graph>
      </div>
      <!-- </div> -->
    </div>

    <div class="tab">
      <div class="wrap">
        <a id="02" name="consume" :class="{'on':curTab === '02'}" @click="clickTab">지출</a>
        <a id="01" name="income" :class="{'on':curTab === '01'}" @click="clickTab">수입</a>
      </div>
    </div>

    <div class="box-list list02 noMG">

      <div class="select pb20">
        <multiselect track-by="text" v-model="listType" label="text" :preselect-first="true" :options="type1" :searchable="false" :allow-empty="false">
        </multiselect>
        <multiselect track-by="text" v-model="orderType" label="text" :preselect-first="true" :options="type2" :searchable="false" :allow-empty="false">
        </multiselect>
      </div>

      <div class="item" v-for="(item, idx) in rangeList" :key="idx">
        <a @click="clickItem" class="block">
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />{{item.nm_class}} <em>({{item.grade}})</em></p>
            <p class="number">{{item.amt_in_out}}<em>원</em></p>
          </div>
          <div class="bar">
            <p v-bind:style="{width:item.percentage+'%'}"></p>
          </div>
          <div class="flex">
            <p>{{item.percentage}}%</p>
            <p></p>
          </div>
        </a>
      </div>
    </div>

  </section>
</template>

<script>
import Graph from "./SettlementChart";
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";
import moment from "moment";

export default {
  name: "ConsumeSettlement",
  data() {
    return {
      curTab: "02",
      curTabName: "consume",
      dataPeriod: "",
      shareList: [],
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      dt_from: new Date(),
      dt_to: new Date(),
      ko: ko,
      chkReadonly: false,
      dt_basic: this.$store.state.user.dt_basic,
      chartList: [],
      seen: false,
      Common: Common,
      consumeSum: 0,
      incomeSum: 0,
      consumeForm: {},
      listType: "",
      orderType: "",
      type1: [
        { text: "카테고리별", value: "category" },
        { text: "가맹점별", value: "store" },
        { text: "수단별", value: "means" }
      ],
      type2: [
        { text: "금액순", value: "sum" },
        { text: "건수순", value: "count" }
      ],
      prdFromDt: "",
      prdToDt: "",
      rangeList: []
    };
  },
  components: {
    Graph: Graph
  },
  watch: {
    dataPeriod: function() {
      let today = new Date();
      let dt_basic = this.dt_basic;
      //datePicker setting
      if (this.dataPeriod == "yr") {
        this.dt_from = new Date(today.getFullYear().toString());
      } else if (this.dataPeriod == "mon") {
        let mon = null;
        if (dt_basic < today.getDate()) {
          mon = (today.getMonth() + 1).toString();
        } else {
          //(dt_basic >= today.getDate())
          mon = today.getMonth().toString();
        }
        this.dt_from = new Date(
          today.getFullYear().toString() + "/" + mon + "/" + dt_basic
        ); //기준일
      } else if (this.dataPeriod == "week") {
        // console.log(this.$moment(today).isoWeekday(7));
        this.dt_from = new Date(moment(today).add(-7, "days")); //7일전
        // this.dt_from = new Date(this.$moment(today).isoWeekday(0)); //주 초 (일요일부터)
      }

      //chart setting
      this.getChartList();
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입·지출 보고서";
  },
  created() {
    this.listConsumeShareInfo();
    // this.getChartList();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    changeDt: function() {
      // this.dataPeriod =
    },
    clickItem: function() {
      this.$router.push("/consume/consumeIncomeStats");
    },
    formatDateDot: function(date, pattern) {
      return Common.formatDateDot(date, pattern);
    },
    clickTab: function(tab) {
      let _this = this;
      _this.curTab = tab.srcElement.id;
      _this.curTabName = tab.srcElement.name;
    },
    initData: function() {},
    getChartList: function() {
      let _this = this;
      let url = "/m/consume/listConsumeforSettlement.json";
      let param = new FormData();
      param.append("no_person", this.$store.state.user.noPerson);
      param.append("contents", _this.dataPeriod);
      param.append(
        "dt_from",
        Common.formatDateDot(_this.dt_from).replace(/[.]/g, "")
      );
      param.append(
        "dt_to",
        Common.formatDateDot(_this.dt_to).replace(/[.]/g, "")
      );
      param.append("no_person_list", _this.filterShareList());
      console.log(param);
      _this.$http.post(url, param).then(function(response) {
        _this.chartList = response.data.listSettlementConsumeData;
        _this.consumeForm = response.data.consumeForm;
        _this.seen = true;
        _this.getSum();
      });
    },
    /**
     * 수입, 지출 합계 계산
     */
    getSum: function() {
      let _this = this;
      let chartList = _this.chartList;
      this.consumeSum = 0;
      this.incomeSum = 0;
      for (var k in chartList) {
        if (chartList[k].type_in_out == "02") {
          this.consumeSum += parseInt(chartList[k].amt_in_out);
        } else if (chartList[k].type_in_out == "01") {
          this.incomeSum += parseInt(chartList[k].amt_in_out);
        }
      }
      //만원단위
      this.consumeSum = Math.round(this.consumeSum / 10000);
      this.incomeSum = Math.round(this.incomeSum / 10000);
      // this.consumeSum = Math.round(this.consumeSum);
      // this.incomeSum = Math.round(this.incomeSum);
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
          _this.dataPeriod = "mon";
          // _this.getChartList();
        });
    },
    getRangeList: function() {
      //event는 마우스 이벤트(클릭), el은 해당 데이터를 묶어서 던져줌
      var _this = this;
      var frm = new FormData();
      frm.append("listType", _this.listType.value);
      frm.append("orderType", _this.orderType.value);
      frm.append("type_in_out", _this.curTab);
      frm.append("no_person_list", _this.filterShareList());
      frm.append("no_person", _this.$store.state.user.noPerson);
      frm.append("dt_from", _this.prdFromDt);
      frm.append("dt_to", _this.prdToDt);
      frm.append("chartType", _this.dataPeriod);

      this.$http
        .post("/m/consume/getRangeListforSettlement.json", frm)
        .then(function(response) {
          debugger;
          _this.rangeList = response.data.rangeList;
          _this.calcPercentage(_this.rangeList);
          for (var i in _this.rangeList) {
            _this.rangeList[i].amt_in_out = _this.numberWithCommas(
              _this.rangeList[i].amt_in_out
            );
          }
          console.log(_this.rangeList);
        });
    },
    openDatepicker1: function() {
      this.$refs.datepicker1.showCalendar();
    },
    openDatepicker2: function() {
      this.$refs.datepicker2.showCalendar();
    },
    clickChart: function(rangeDate, typePeriod) {
      if (typePeriod == "yr") {
        //기준일인지 아닌지 확인
        let yearNmon = rangeDate.substring(0, 6);
        this.prdFromDt = yearNmon;
        this.prdToDt = moment(yearNmon + this.dt_basic, "YYYYMMDD")
          .subtract(1, "days")
          .add(1, "month")
          .format("YYYYMMDD");
        // console.log(this.shareList);
        this.getRangeList();
      } else if (typePeriod == "mon") {
        this.prdFromDt = rangeDate;
        this.prdToDt = moment(rangeDate, "YYYYMMDD")
          .add(6, "days")
          .format("YYYYMMDD");
        console.log(this.prdToDt);
        this.getRangeList();
      } else {
      }
    },
    calcPercentage: function(obj) {
      let total = 0;
      for (var h in obj) {
        total += parseInt(obj[h].amt_in_out);
      }
      for (var h in obj) {
        obj[h].percentage = Math.round((obj[h].amt_in_out / total) * 100);
      }
    },
    numberWithCommas: function(x) {
      debugger;
      return x.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
