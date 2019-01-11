<template>
  <section v-if="seen">
    <div class="report-top">
      <div class="checks round" style="';display:grid;grid-template-columns: 70% 1fr;background: #efeeec;'">
        <p>
          <input type="radio" id="rds1" :class="{'checked':dataPeriod === 'yr'}" v-model="dataPeriod" @click="clickPeriod" value="yr"><label for="rds1">년</label>
          <input type="radio" id="rds2" :class="{'checked':dataPeriod === 'mon'}" v-model="dataPeriod" @click="clickPeriod" value="mon"><label for="rds2">월</label>
          <input type="radio" id="rds3" :class="{'checked':dataPeriod === 'week'}" v-model="dataPeriod" @click="clickPeriod" value="week"><label for="rds3">주</label>
        </p>
        <p>
          <input type="label" v-bind:class="detailSelection?'on':''" @click="detailSelection?detailSelection=false:detailSelection=true" value="조건설정" readonly>
        </p>
      </div>
      <div v-if="detailSelection">
        <div class="date">
          <p>
            <datepicker :minimum-view="dp_minimumView" v-model="dt_from" ref="datepicker1" :disabledDates="disabledDate1" :format="dp_format" :language="ko" class="div-date"></datepicker>
            <button class="cal" @click="openDatepicker1"></button>
          </p>
          <p>
            <datepicker :minimum-view="dp_minimumView" v-model="dt_to" ref="datepicker2" :disabledDates="disabledDate2" :format="dp_format" :language="ko" class="div-date"></datepicker>
            <button class="cal" @click="openDatepicker2"></button>
          </p>
        </div>
        <!-- <div v-else class="date">
          <p>
            <datepicker v-model="dt_from" ref="datepicker1" :disabledDates="disabledDate1" :format="formatDateDot" :language="ko" class="div-date"></datepicker>
            <button class="cal" @click="openDatepicker1"></button>
          </p>
          <p>
            <datepicker v-model="dt_to" ref="datepicker2" :disabledDates="disabledDate2" :language="ko" :format="formatDateDot" class="div-date"></datepicker>
            <button class="cal" @click="openDatepicker2"></button>
          </p>
        </div> -->
        <div v-if="shareList.length>1" class="filter-wrap mt20">
          <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
            <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.viewName}}</label>
          </div>
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
        <!-- <TEST v-if="chartList" ref="graph" v-model="chartList" :chartList="chartList" :consumeForm="consumeForm" :dt_from="dt_from" :dt_to="dt_to" :dataPeriod="dataPeriod"></TEST> -->
      </div>
      <div class="nodata" v-else-if="chartList.length==0">
        조회하신 범위에 수입, 지출 내역이 없습니다
      </div>
    </div>

    <div class="tab">
      <div class="wrap">
        <a id="02" name="consume" :class="{'on':curTab === '02'}" @click="changeTab">지출</a>
        <a id="01" name="income" :class="{'on':curTab === '01'}" @click="changeTab">수입</a>
      </div>
    </div>
    <div class="box-list list02 noMG">
      <div class="select pb20">
        <multiselect class="multiselect-basic" v-model="listType" label="text" :title="'종류별'" :preselect-first="true" :options="type1">
        </multiselect>
        <p></p>
        <multiselect class="multiselect-basic" v-model="orderType" label="text" :title="'조회순'" :preselect-first="true" :options="type2">
        </multiselect>
      </div>

      <div class="nodata" v-if="seen2 && rangeList.length==0">
        조회하신 범위에 수입 또는 지출 내역이 없습니다
      </div>
      <div class="item" v-else v-for="(item, idx) in rangeList" :key="idx">
        <a @click="goDetail(item)" class="block">
          <div class="flex">

            <p class="key" v-if="listType.value=='category'"><img :src="getConsumeIconSrc(item.type_in_out, item.cd_class)" width="15px" class="mr5" alt="" />{{item.nm_class}} <em>({{item.grade}})</em></p>
            <p class="key" v-else-if="listType.value=='store'">{{item.contents}} <em>({{item.grade}})</em></p>
            <p class="key" v-else-if="listType.value=='means'">{{item.nm_card}} <em>({{item.grade}})</em></p>
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
// import TEST from "./SettlementChart_test";
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";
import moment from "moment";

export default {
  name: "ConsumeSettlement",
  data() {
    return {
      ko: ko,
      seen: false,
      seen2: false,
      Common: Common,
      curTab: "02",
      curTabName: "consume",
      dataPeriod: "yr",
      dp_minimumView: "month",
      dp_format: "yyyy.MM",
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
      today: new Date(),
      dt_basic: this.$store.state.user.dt_basic,
      chartList: [],
      consumeSum: 0,
      incomeSum: 0,
      consumeForm: {},
      type1: [
        { text: "카테고리별", value: "category" },
        { text: "가맹점별", value: "store" },
        { text: "수단별", value: "means" }
      ],
      type2: [
        { text: "금액순", value: "sum" },
        { text: "건수순", value: "count" }
      ],
      listType: { text: "카테고리별", value: "category" },
      orderType: { text: "금액순", value: "sum" },
      //range select 위한 data
      prdFromDt: "",
      prdToDt: "",
      rangeList: [], //보여지는 실제값
      incomeList: [],
      consumeList: [],
      //기타기능
      isRangeList: false, //chart범위 선택했는지 여부
      chartEl: {},
      initYN: true,
      lastToolTip: {},
      detailSelection: false,
      //datepicker disabled 범위
      disabledDate0: {
        to: new Date(moment(this.dt_to).add(-1, "years")),
        from: new Date(this.dt_to)
      }, //year disabled dates from
      disabledDate00: {}, //year disabled dates to
      disabledDate1: {}, //mon, day disabled dates from
      disabledDate2: {} //mon, day disabled dates to
    };
  },
  components: {
    Graph: Graph
    // TEST: TEST,
  },
  watch: {
    dataPeriod: function() {
      let today = new Date();
      this.isRangeList = false;
      if (this.dataPeriod == "yr") {
        this.dp_minimumView = "month";
        this.dp_format = "yyyy.MM";
        this.dt_from = new Date(moment(this.dt_to).add(-3, "month"));
      } else if (this.dataPeriod == "mon") {
        this.dp_minimumView = "day";
        this.dp_format = "yyyy.MM.dd";
        this.dt_from = new Date(moment(today).add(-1, "month"));
      } else if (this.dataPeriod == "week") {
        this.dp_minimumView = "day";
        this.dp_format = "yyyy.MM.dd";
        this.dt_from = new Date(moment(today).add(-7, "days")); //7일전
      }
    },
    listType: function() {
      if (!this.initYN) {
        this.getRangeList();
      }
    },
    orderType: function() {
      if (!this.initYN) {
        this.getRangeList();
      }
    },
    chartList: function() {
      this.initRangeList();
    },
    dt_from: function() {
      this.isRangeList = false;
      //chart setting
      this.getChartList();
    },
    dt_to: function() {
      this.isRangeList = false;
      this.getChartList();
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입·지출 보고서";
    this.$store.state.header.backPath = "/consume/main";
  },
  created() {
    console.log(this.$route.query);
    if (Object.keys(this.$route.query).length != 0) {
      this.chartEl = this.$route.params.chartEl;
      //dt_to, dt_from 보다 dataPeriod를 먼저 setting 해야함 (dataPeriod =>Yr로 바뀔때마다 dt_from을 3개월전으로 바꿈)
      this.dataPeriod = this.$route.query.dataPeriod;

      //시작 순서 dataPeriod -> dt_to ->dt_from
      this.dt_to = new Date(moment(this.$route.query.date_to, "YYYYMMDD"));
      this.dt_from = new Date(moment(this.$route.query.date_from, "YYYYMMDD"));
      this.type_in_out = this.$route.query.type_in_out;
      this.prdFromDt = this.$route.query.prdFromDt;
      this.prdToDt = this.$route.query.prdToDt;

      this.listType = JSON.parse(localStorage.getItem("listType"));
      this.orderType = JSON.parse(localStorage.getItem("orderType"));
      this.shareList = JSON.parse(localStorage.getItem("shareList"));
     // if (Array.isArray(this.chartEl)) {
        // chartEl 이 있을 경우 url있고, refresh될때
      //  this.clickChart(this.prdFromDt, this.dataPeriod, this.chartEl);
     // }
      //refresh되면 에러남 -> 추후 해결필요
      localStorage.removeItem("listType");
      localStorage.removeItem("orderType");
    } else {
      this.listConsumeShareInfo();
    }
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
    clickPeriod: function() {
      let today = new Date();
      let dt_basic = this.dt_basic;
      //datePicker setting
      this.dt_to = today;
      if (this.dataPeriod == "yr") {
        this.dt_from = new Date(moment(this.dt_to).add(-3, "month"));
      } else if (this.dataPeriod == "mon") {
        /* 기준일 base month
        let yrmon = null;
        if (Number(dt_basic) < today.getDate()) {
          yrmon = moment().format("YYYYMM");
        } else {
          //(dt_basic >= today.getDate())
          yrmon = moment()
            .add(-1, "month")
            .format("YYYYMM");
        }
        if (dt_basic == null || dt_basic == "") {
          //기준일이 null일 경우
          dt_basic = "01";
        }
        this.dt_from = new Date(moment(yrmon + dt_basic, "YYYYMMDD"));
*/
      } else if (this.dataPeriod == "week") {
        // console.log(this.$moment(today).isoWeekday(7));
        this.dt_from = new Date(moment(today).add(-7, "days")); //7일전
        // this.dt_from = new Date(this.$moment(today).isoWeekday(0)); //주 초 (일요일부터)
      }
    },
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
      let param = {};
      var _this = this;
      let listType = this.listType.value;
      // if (listType == "category") {
      //   param["nm_class"] = idx.nm_class;
      // } else if (listType == "store") {
      //   param["contents"] = idx.contents;
      // } else
      if (listType == "means") {
        localStorage.setItem("no_card", idx.no_card);
        localStorage.setItem("nm_card", idx.nm_card);
      }
      localStorage.setItem("listType", JSON.stringify(this.listType));
      localStorage.setItem("orderType", JSON.stringify(this.orderType));
      localStorage.setItem("shareList", JSON.stringify(this.shareList));
      // localStorage.setItem("chartEl", JSON.stringify(this.chartEl));
      console.log(
        "prdFromDt : " +
          _this.prdFromDt +
          " // dt_from : " +
          _this.dt_from +
          "// prdToDt : " +
          _this.prdToDt +
          " // dt_to : " +
          _this.dt_to
      );
      console.log(
        "dt_from: " +
          Common.formatDateDot(_this.dt_from).replace(/[.]/g, "") +
          "dt_to: " +
          Common.formatDateDot(_this.dt_to).replace(/[.]/g, "")
      );
      this.$router.push({
        name: "consumeIncomeStats", //"/consume/consumeIncomeStats" + param,
        params: { chartEl: this.chartEl },
        query: {
          dt_trd: idx.dt_trd,
          listType: listType,
          type_in_out: idx.type_in_out,
          chartType: _this.dataPeriod,
          personList: _this.filterShareList(),
          date_from: moment(_this.dt_from).format("YYYYMMDD"), //this.dt_from,
          date_to: moment(_this.dt_to).format("YYYYMMDD"),
          prdFromDt: _this.prdFromDt,
          prdToDt: _this.prdToDt,
          nm_class: idx.nm_class,
          contents: idx.contents
        }
      });
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
    getChartList: function() {
      let _this = this;
      let url = "/m/consume/listConsumeforSettlement.json";
      let param = new FormData();
      _this.seen2 = false;
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
        if (response.data.result == "00") {
          _this.chartList = response.data.listSettlementConsumeData;
          _this.consumeForm = response.data.consumeForm;
          // _this.seen = true;
          _this.getSum();

          if (_this.isRangeList) {
            //Array.isArray(_this.chartEl)
            //chart 클릭시에만 타야함 (현재 상세보기갔다가 뒤로오면 chartEl에 data를 담기때문에 이쪽으로 넘어옴)
            _this.getRangeList();
          } else {
            if (_this.dataPeriod == "yr") {
              //기준일인지 아닌지 확인
              _this.prdFromDt = moment(_this.dt_from).format("YYYYMM");
              _this.prdToDt = moment(_this.dt_to).format("YYYYMM");
            } else if (_this.dataPeriod == "mon") {
              _this.prdFromDt = moment(_this.dt_from).format("YYYYMMDD");
              _this.prdToDt = moment(_this.dt_to).format("YYYYMMDD");
            } else {
              _this.prdFromDt = moment(_this.dt_from).format("YYYYMMDD");
              _this.prdToDt = moment(_this.dt_to).format("YYYYMMDD");
            }
            console.log(
              "prdFromDt : " +
                _this.prdFromDt +
                " // dt_from : " +
                _this.dt_from +
                "// prdToDt : " +
                _this.prdToDt +
                " // dt_to : " +
                _this.dt_to
            );
            _this.getRangeList();
          }
        } else {
          _this.$toast.center(response.data.message);
          // _this.$refs.datepicker0.$el.focus();
        }
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
          console.log(list);
          // _this.dataPeriod = "yr";
          _this.clickPeriod();
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
      if (_this.isRangeList) {
        //chart 클릭시에만 타야함 (현재 상세보기갔다가 뒤로오면 chartEl에 data를 담기때문에 이쪽으로 넘어옴)
        frm.append("contents", "total");
      }
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
          _this.seen2 = false;
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
          let _tab = { srcElement: {} };
          if (_this.isRangeList) {
            // chart 클릭시에만 타야함 (현재 상세보기갔다가 뒤로오면 chartEl에 data를 담기때문에 이쪽으로 넘어옴)
            let datasetIdx =
              _this.chartEl[0]._chart.chart.tooltip._active[0]._datasetIndex; //tooltip이 없어지면 index값 undefined 남****고치기
            if (datasetIdx != "undefined") {
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
          } else {
            //기본 tab 설정
            _tab.srcElement.id = "02";
            _tab.srcElement.name = "지출";
            _this.changeTab(_tab);
          }
          _this.seen = true;
          _this.seen2 = true;
          // console.log(_this.rangeList);
        });
    },
    /**
     * 탭 선택시 챠트 및 탭바꾸기
     */
    changeTabByTooltip: function(e) {
      this.changeTab(e);
    },
    // openDatepicker0: function() {
    //   this.getDateDisabledRange();
    //   this.$refs.datepicker0.showCalendar();
    // },
    // openDatepicker00: function() {
    //   this.getDateDisabledRange();
    //   this.$refs.datepicker00.showCalendar();
    // },
    openDatepicker1: function() {
      this.getDateDisabledRange();
      this.$refs.datepicker1.showCalendar();
    },
    openDatepicker2: function() {
      this.getDateDisabledRange();
      this.$refs.datepicker2.showCalendar();
    },
    getDateDisabledRange: function() {
      if (this.dataPeriod == "yr") {
        this.disabledDate1 = {
          to: new Date(moment(this.dt_to).add(-1, "year")),
          from: new Date(this.dt_to)
        };
        this.disabledDate2 = {
          from: new Date(this.today) // Disable all dates after specific date
        };
      } else if (this.dataPeriod == "mon") {
        this.disabledDate1 = {
          to: new Date(moment(this.dt_to).add(-2, "month")),
          from: new Date(this.dt_to),
          days: [6, 0, 2, 3, 4, 5]
        };
        this.disabledDate2 = {
          from: new Date(this.today), // Disable all dates after specific date
          days: [6, 1, 2, 3, 4, 5]
        };
      } else if (this.dataPeriod == "week") {
        this.disabledDate1 = {
          to: new Date(moment(this.dt_to).add(-14, "days")),
          from: new Date(this.dt_to)
        };
        this.disabledDate2 = {
          from: new Date(this.today) // Disable all dates after specific date
        };
      }
    },
    //** call from SettlementChart.clickChart */
    clickChart: function(rangeDate, typePeriod, el) {
      var _this = this;
      this.isRangeList = true;
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
      console.log(
        "prdFromDt : " +
          _this.prdFromDt +
          " // dt_from : " +
          _this.dt_from +
          "// prdToDt : " +
          _this.prdToDt +
          " // dt_to : " +
          _this.dt_to
      );
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
.filter-wrap {
  margin-bottom: 0px;
}
.btn-arrow {
  padding-right: 20px;
  background: url("./../../assets/images/consume/btn_cate.png") no-repeat 95%
    center/10px;
  color: #454545;
  text-align: left;
  width: 100%;
  padding-left: 10px;
}
input[type="label"] {
  // url("../images/assets/search_arrow.png") no-repeat 65px 0/9px;
  background: url("./../../assets/images/assets/search_arrow.png") no-repeat
    100% top/12px #efeeec;
  line-height: 38px;
  height: 40px;
  text-indent: 10px;
  width: 100%;
  font-size: 14px;
  vertical-align: middle;
  padding-left: 5px;
  box-sizing: border-box;
  // -moz-appearance: none;
  // -webkit-appearance: none;
}
input[type="label"].on {
  // url("../images/assets/search_arrow.png") no-repeat 65px 0/9px;
  background: url("./../../assets/images/assets/search_arrow.png") no-repeat
    100% bottom/12px #efeeec;
  line-height: 38px;
  height: 40px;
  text-indent: 10px;
  width: 100%;
  font-size: 14px;
  vertical-align: middle;
  padding-left: 5px;
  box-sizing: border-box;
  // -moz-appearance: none;
  // -webkit-appearance: none;
}
</style>
