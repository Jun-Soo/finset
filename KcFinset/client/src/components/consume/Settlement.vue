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
      <div v-if="shareList.length>1" class="filter-wrap mt20">
        <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
          <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.viewName}}</label>
        </div>
      </div>
    </div>
    <div class="container">
      <div v-if="chartList.length!=0" class="report-con">
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
        <Graph v-if="chartList" ref="graph" v-model="chartList" :chartList="chartList" :consumeForm="consumeForm" :dt_from="dt_from" :dt_to="dt_to" :dataPeriod="dataPeriod"></Graph>
      </div>
      <div class="nodata" v-else-if="chartList.length==0">
        조회하신 범위에 수입, 지출 내역이 없습니다
      </div>
    </div>

    <div class="tab">
      <div class="wrap">
        <a id="02" name="consume" :class="{'on':curTab === '02'}" @change="changeTab">지출</a>
        <a id="01" name="income" :class="{'on':curTab === '01'}" @change="changeTab">수입</a>
      </div>
    </div>
    <div class="box-list list02 noMG">
      <div class="select pb20">
        <multiselect v-model="listType" label="text" :title="'종류별'" :preselect-first="true" :options="type1">
        </multiselect>
        <p></p>
        <multiselect v-model="orderType" label="text" :title="'조회순'" :preselect-first="true" :options="type2">
        </multiselect>
      </div>

      <div class="item" v-for="(item, idx) in rangeList" :key="idx">
        <a @click="goDetail(item)" class="block">
          <div class="flex">

            <p class="key" v-if="listType.value=='category'"><img :src="getConsumeIconSrc(item.type_in_out, item.cd_class)" width="15px" class="mr5" alt="" />{{item.nm_class}} <em>({{item.grade}})</em></p>
            <p class="key" v-else-if="listType.value=='store'"><img :src="getConsumeIconSrc(item.type_in_out, item.cd_class)" width="15px" class="mr5" alt="" />{{item.contents}} <em>({{item.grade}})</em></p>
            <p class="key" v-else-if="listType.value=='means'"><img :src="getConsumeIconSrc(item.type_in_out, item.cd_class)" width="15px" class="mr5" alt="" />{{item.nm_card}} <em>({{item.grade}})</em></p>
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
      rangeList: [],
      incomeList: [],
      consumeList: [],
      chartEl: {},
      initYN: true,
      lastToolTip: {}
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
        if (dt_basic == null || dt_basic == "") {
          //기준일이 null일 경우
          dt_basic = "01";
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
    },
    listType: function() {
      // if (!this.initYN) {
      // console.log(this.listType.value);
      this.getRangeList();
      // }
    },
    orderType: function() {
      if (!this.initYN) {
        // console.log(this.orderType.value);
        this.getRangeList();
      }
    },
    chartList: function() {
      this.initRangeList();
      // this.getRangeList();
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
  updated() {
    this.initYN = false;
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getConsumeIconSrc: function(type_in_out, cd_class) {
      let cd;
      if ((cd_class || "") == "" || (type_in_out || "") == "") {
        cd = "99";
      } else if (type_in_out == "01") {
      } else {
        //default로 설정된 cd_class가 24까지밖에 없음
        if (parseInt(cd_class) > 24) {
          cd = "99";
        } else {
          cd = cd_class;
        }
      }
      if (cd == undefined) {
        return require("@/assets/images/consume/icon/99.png");
      }
      return require("@/assets/images/consume/icon/" + cd + ".png");
    },
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.getChartList();
    },
    goDetail: function(idx) {
      let param = "";
      let listType = this.listType.value;
      if (listType == "category") {
        param =
          "?dt_trd=" +
          idx.dt_trd +
          "&listType=" +
          listType +
          "&nm_class=" +
          idx.nm_class +
          "&type_in_out=" +
          idx.type_in_out +
          "&chartType=" +
          this.dataPeriod +
          "&personList=" +
          this.filterShareList();
      } else if (listType == "store") {
        param =
          "?dt_trd=" +
          idx.dt_trd +
          "&listType=" +
          listType +
          "&contents=" +
          idx.contents +
          "&type_in_out=" +
          idx.type_in_out +
          "&chartType=" +
          this.dataPeriod +
          "&personList=" +
          this.filterShareList();
      } else if (listType == "means") {
        param =
          "?dt_trd=" +
          idx.dt_trd +
          "&listType=" +
          listType +
          "&type_in_out=" +
          idx.type_in_out +
          "&chartType=" +
          this.dataPeriod +
          "&personList=" +
          this.filterShareList();
        localStorage.setItem("no_card", idx.no_card);
        localStorage.setItem("nm_card", idx.nm_card);
      }
      localStorage.setItem("shareList", this.shareList);
      this.$router.push("/consume/consumeIncomeStats" + param);
    },
    formatDateDot: function(date, pattern) {
      return Common.formatDateDot(date, pattern);
    },
    changeTab: function(tab) {
      let _this = this;
      _this.curTab = tab.srcElement.id;
      _this.curTabName = tab.srcElement.name;
      if (_this.curTab == "01") {
        _this.rangeList = _this.incomeList;
      } else {
        _this.rangeList = _this.consumeList;
      }
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
      // console.log(param);
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
            list[idx]["viewName"] = "";
            if (list[idx].no_person == _this.$store.state.user.noPerson) {
              list[idx]["viewName"] = "나";
            } else {
              list[idx]["viewName"] = list[idx].nm_person.substring(1);
            }
          }
          _this.shareList = list;
          _this.dataPeriod = "yr";
          // _this.getChartList();
        });
    },
    initRangeList: function() {
      let _this = this;
      _this.rangeList = [];
      _this.consumeList = [];
      _this.incomeList = [];
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
          let totalRangeList = response.data.rangeList;
          _this.initRangeList();

          _this.calcPercentage(totalRangeList);

          for (var i in totalRangeList) {
            //금액에 , 찍기
            totalRangeList[i].amt_in_out = _this.numberWithCommas(
              totalRangeList[i].amt_in_out
            );

            //수입 지출 List 나눠담기
            if (totalRangeList[i].type_in_out == "02") {
              _this.consumeList.push(totalRangeList[i]);
            } else {
              _this.incomeList.push(totalRangeList[i]);
            }
          } //for
          // console.log(_this.listType);

          // 차트 선택시 수입 지출 탭바꾸기
          let datasetIdx =
            _this.chartEl[0]._chart.chart.tooltip._active[0]._datasetIndex; //tooltip이 없어지면 index값 undefined 남****고치기
          if (datasetIdx != "undefined") {
            let _tab = { srcElement: {} };
            if (datasetIdx == 0) {
              _tab.srcElement.id = "02";
              _tab.srcElement.name = "지출";
            } else {
              _tab.srcElement.id = "01";
              _tab.srcElement.name = "수입";
            }
            _this.changeTab(_tab);
          } else {
            _this.rangeList = [];
          }
          // console.log(_this.rangeList);
        });
    },
    openDatepicker1: function() {
      this.$refs.datepicker1.showCalendar();
    },
    openDatepicker2: function() {
      this.$refs.datepicker2.showCalendar();
    },
    clickChart: function(rangeDate, typePeriod, el) {
      if (typePeriod == "yr") {
        //기준일인지 아닌지 확인
        let yearNmon = rangeDate.substring(0, 6);
        this.prdFromDt = yearNmon;
        this.prdToDt = moment(yearNmon + this.dt_basic, "YYYYMMDD")
          .subtract(1, "days")
          .add(1, "month")
          .format("YYYYMMDD");
      } else if (typePeriod == "mon") {
        this.prdFromDt = rangeDate;
        this.prdToDt = moment(rangeDate, "YYYYMMDD")
          .add(6, "days")
          .format("YYYYMMDD");
      } else {
        this.prdFromDt = rangeDate;
        this.prdToDt = rangeDate;
      }
      this.chartEl = el;
      // console.log(el);
      this.getRangeList();
    },
    calcPercentage: function(obj) {
      let incomeTotal = 0;
      let consumeTotal = 0;
      for (var h in obj) {
        if (obj[h].type_in_out == "02") {
          consumeTotal += parseInt(obj[h].amt_in_out);
        } else {
          incomeTotal += parseInt(obj[h].amt_in_out);
        }
      }
      for (var h in obj) {
        if (obj[h].type_in_out == "02") {
          obj[h].percentage = Math.round(
            (obj[h].amt_in_out / consumeTotal) * 100
          );
        } else {
          obj[h].percentage = Math.round(
            (obj[h].amt_in_out / incomeTotal) * 100
          );
        }
      }
    },
    numberWithCommas: function(x) {
      return x.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.div-date {
  display: inline;
}
</style>
