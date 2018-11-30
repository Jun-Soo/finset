<template>
  <section>
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev" @click="setYear('prev')"></button>
        <p>{{headDt}}</p>
        <button class="next" @click="setYear('next')"></button>
        <button class="setting" @click="goMonth"></button>
        <button class="today" @click="setYear('today')">TODAY</button>
      </div>
    </div>

    <div class="check-flex">
      <div class="income">
        <!-- <button class="on">수입</button> -->
        <em>1,234,565,000</em>
      </div>
      <div class="debt">
        <!-- <button class="on">지출</button> -->
        <em>1,234,565,000</em>
      </div>
      <div class="loan">
        <!-- <button>대출</button> -->
        <em>1,234,565,000</em>
      </div>
    </div>
    <div class="list-wrap">
      <div class="filter-wrap">
        <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
          <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
        </div>
      </div>
    </div>
    <div class="calendar">
      <div class="year">
        <div v-for="(n, index) in 12" :key="index">
          <p class="mon" v-text="n + '월'"></p>
          <em class="income" v-if="calData[index].income != 0">{{Common.formatNumber(calData[index].income)}}</em>
          <em class="debt" v-if="calData[index].consume != 0">{{Common.formatNumber(calData[index].consume)}}</em>
          <em class="loan" v-if="calData[index].debt != 0">{{Common.formatNumber(calData[index].debt)}}</em>
          <p class="sum" v-if="calData[index].income != 0 || calData[index].consume != 0 || calData[index].debt != 0" v-text="Common.formatNumber(calData[index].income - calData[index].consume - calData[index].debt)"></p>
        </div>
      </div>
      <!-- <button class="btn-spend-add"></button> -->
    </div>
  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "CommonYearCal",
  data() {
    return {
      standardDt: new Date(),
      calData: [
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 },
        { income: 0, consume: 0, debt: 0 }
      ],
      Common: Common,
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      shareList: []
    };
  },
  components: {},
  computed: {
    headDt: function() {
      return this.standardDt.getFullYear();
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "습관달력";
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.setDefaultCalData();
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
          _this.listCalendarDataYear();
        });
    },
    listCalendarDataYear: function() {
      this.setDefaultCalData();

      var _this = this;

      this.$http
        .get("/m/debt/listCalendarDataYear.json", {
          params: {
            y_trd: _this.headDt,
            no_person_list: _this.filterShareList(),
            seq_consume: 0
          }
        })
        .then(function(response) {
          var list = response.data.listCalendarConsumeDataYear;
          for (var idx in list) {
            if (list[idx].type_in_out == "01") {
              _this.$set(
                _this.calData[parseInt(list[idx].dt_trd.substring(4, 6)) - 1],
                "income",
                list[idx].amt_in_out
              );
            } else if (list[idx].type_in_out == "02") {
              _this.$set(
                _this.calData[parseInt(list[idx].dt_trd.substring(4, 6)) - 1],
                "consume",
                list[idx].amt_in_out
              );
            }
          }
          list = response.data.listCalendarDebtDataYear;
          for (var idx in list) {
            _this.$set(
              _this.calData[parseInt(list[idx].req_yyyymm.substring(4, 6)) - 1],
              "debt",
              list[idx].amt_repay
            );
          }
        });
    },
    setYear(key) {
      switch (key) {
        case "prev":
          this.standardDt = new Date(
            this.standardDt.setFullYear(this.standardDt.getFullYear() - 1)
          );
          this.listCalendarDataYear();
          break;
        case "next":
          this.standardDt = new Date(
            this.standardDt.setFullYear(this.standardDt.getFullYear() + 1)
          );
          this.listCalendarDataYear();
          break;
        case "today":
          this.standardDt = new Date();
          this.listCalendarDataYear();
          break;
        default:
          break;
      }
    },
    setDefaultCalData: function() {
      this.calData = [
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 },
        { income: 0, consume: 0, debt: 0, sum: 0 }
      ];
    },
    setCalData: function(list) {
      for (var idx in list) {
        this.$set(this.calData[idx], "income", list[idx].income);
        this.$set(this.calData[idx], "consume", list[idx].consume);
        this.$set(this.calData[idx], "debt", list[idx].debt);
      }
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
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listCalendarDataYear();
    },
    goMonth: function() {
      this.$router.push("/common/monthCal");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
