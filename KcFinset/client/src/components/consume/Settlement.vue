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
          <datepicker v-model="dt_from" :format="formatDateDot" :language="ko" class="div-date" :disabled="chkReadonly"></datepicker>
        </p>
        <p>
          <datepicker v-model="dt_to" :language="ko" :format="formatDateDot" class="div-date" :disabled="chkReadonly"></datepicker>
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
            <p class="value">4,350</p>
          </div>
          <div>
            <p class="key"><strong>지출</strong>(만원)</p>
            <p class="value">4,350</p>
          </div>
        </div>
        <!-- <div class="graph"> -->
        <Graph v-if="chartList" :chartList="chartList" :consumeForm="consumeForm" :dt_from="dt_from" :dt_to="dt_to" :dataPeriod="dataPeriod"></Graph>
      </div>
      <!-- </div> -->
    </div>

    <div class="tab">
      <div class="wrap">
        <a id="00" name="consume" :class="{'on':curTab === '00'}" @click="clickTab">지출</a>
        <a id="01" name="income" :class="{'on':curTab === '01'}" @click="clickTab">수입</a>
      </div>
    </div>

    <div class="box-list list02 noMG">

      <div class="select pb20">
        <!-- <multiselect v-model="repay" track-by="text" label="text" placeholder="카테고리별" :options="options" :searchable="false" :allow-empty="false" @select="onSelect">
          <template slot="singleLabel" slot-scope="{ repay_options }">{{ repay_options.text }}</template>
        </multiselect>
        <multiselect v-model="repay" track-by="text" label="text" placeholder="금액순" :options="options" :searchable="false" :allow-empty="false" @select="onSelect">
          <template slot="singleLabel" slot-scope="{ repay_options }">{{ repay_options.text }}</template>
        </multiselect> -->
      </div>

      <div class="item">
        <a @click="clickItem" class="block">
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />식비 <em>(120건)</em></p>
            <p class="number">118,107<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p></p>
          </div>
        </a>
      </div>

      <div class="item">
        <a @click="clickItem" class="block">
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />식비 <em>(120건)</em></p>
            <p class="number">118,107<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
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

export default {
  name: "ConsumeSettlement",
  data() {
    return {
      curTab: "00",
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
      consumeForm: null,
      seen: false
      // dataMonthList: [],
      // dataWeekList: []
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
        // console.log(today.getMonth());
        console.log(this.dt_basic);
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
        this.dt_from = new Date(this.$moment(today).add(-7, "days")); //7일전
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
        debugger;
        _this.chartList = response.data.listSettlementConsumeData;
        _this.consumeForm = response.data.consumeForm;
        _this.seen = true;
        // if (_this.dataPeriod == "yr") {
        //   _this.dataYearList = response.data.listSettlementConsumeDataYear;
        // } else if (_this.dataPeriod == "mon") {
        //   _this.dataMonthList = response.data.listSettlementConsumeDataWeek;
        // } else if (_this.dataPeriod == "week") {
        //   _this.dataWeekList = response.data.listSettlementConsumeDataDay;
        // }
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
          _this.dataPeriod = "yr";
          // _this.getChartList();
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
