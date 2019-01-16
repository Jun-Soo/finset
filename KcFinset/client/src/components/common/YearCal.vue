<template>
  <section v-if="seen">
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev" @click="setYear('prev')"></button>
        <p>{{headDt}}</p>
        <button class="next" @click="setYear('next')"></button>
        <button class="month" @click="goMonth">월</button>
        <button class="today" @click="setYear('today')">TODAY</button>
      </div>
    </div>

    <div class="check-flex">
      <div class="income">
        <button class="on">수입</button>
        <em>{{sumIncome}}</em>
      </div>
      <div class="debt">
        <button class="on">지출</button>
        <em>{{sumConsume}}</em>
      </div>
      <div class="loan">
        <button class="on">대출</button>
        <em>{{sumDebt}}</em>
      </div>
    </div>
    <div class="container-wrap noMG">
      <div class="list-wrap" v-if="shareList.length != 1">
        <div class="filter-wrap">
          <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
            <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{formatSharePerson(person)}}</label>
          </div>
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
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      standardDt: new Date(), // 기준일
      // 합계로 계산된 데이터
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
      // 공유된 사용자의 색 class 및 id
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      shareList: [] // 공유된 사용자
    };
  },
  components: {},
  computed: {
    // 상단에 표시된 텍스트
    headDt: function() {
      return this.standardDt.getFullYear();
    },
    // 수입 합계
    sumIncome: function() {
      var sum = 0;
      for (var idx in this.calData) {
        sum += parseInt(this.calData[idx].income);
      }
      return Common.formatNumber(sum);
    },
    // 지출 합계
    sumConsume: function() {
      var sum = 0;
      for (var idx in this.calData) {
        sum += parseInt(this.calData[idx].consume);
      }
      return Common.formatNumber(sum);
    },
    // 부채 합계
    sumDebt: function() {
      var sum = 0;
      for (var idx in this.calData) {
        sum += parseInt(this.calData[idx].debt);
      }
      return Common.formatNumber(sum);
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "캘린더(년)";
  },
  created() {},
  beforeMount() {},
  mounted() {
    //화면 이동 로직
    var _this = this;
    let origMousePoint;
    $(document).on("mousedown", "section", function(event) {
      origMousePoint = event.clientX;
    });
    $(document).on("mouseup", "section", function(event) {
      if (origMousePoint - event.clientX > 150) {
        _this.setYear("next");
      } else if (origMousePoint - event.clientX < -150) {
        _this.setYear("prev");
      }
    });
    $(document).on("touchstart", "section", function(event) {
      origMousePoint = event.touches[0].clientX;
    });
    $(document).on("touchend", "section", function(event) {
      if (origMousePoint - event.changedTouches[0].clientX > 150) {
        _this.setYear("next");
      } else if (origMousePoint - event.changedTouches[0].clientX < -150) {
        _this.setYear("prev");
      }
    });
    ////화면 이동 로직
    this.calData = this.getDefaultCalData();
    this.listCalendarShareInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포멧---------------------
    // 이름 포맷 변경
    formatSharePerson: function(person) {
      if ((person || "") == "") {
        return "";
      } else if (
        (person.no_person || "") == "" ||
        (person.nm_person || "") == ""
      ) {
        return "";
      }

      var myNoPerson = this.$store.state.user.noPerson;
      if (person.no_person == myNoPerson) {
        return "나";
      } else {
        if (person.nm_person.length > 3) {
          return person.nm_person.substring(0, 3);
        } else {
          return person.nm_person;
        }
      }
    },
    // ---------------------//데이터 포멧---------------------
    // ---------------------화면 이벤트---------------------
    // 년도 세팅
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
    // 공유된 사용자 버튼 클릭
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listCalendarDataYear();
    },
    // 월 달력으로 이동
    goMonth: function() {
      this.$router.push("/common/monthCal");
    },
    // ---------------------//화면 이벤트---------------------
    // ---------------------데이터 이동---------------------
    // 공유된 사용자 조회
    listCalendarShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/debt/listCalendarShareInfo.json")
        .then(function(response) {
          var list = response.data.listCalendarShareInfo;

          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listCalendarDataYear();
        });
    },
    // 년 달력 데이터 리스트 조회
    listCalendarDataYear: function() {
      var _this = this;
      var calData = this.getDefaultCalData();
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
                calData[parseInt(list[idx].dt_trd.substring(4, 6)) - 1],
                "income",
                list[idx].amt_in_out
              );
            } else if (list[idx].type_in_out == "02") {
              _this.$set(
                calData[parseInt(list[idx].dt_trd.substring(4, 6)) - 1],
                "consume",
                list[idx].amt_in_out
              );
            }
          }
          list = response.data.listCalendarDebtDataYear;
          for (var idx in list) {
            _this.$set(
              calData[parseInt(list[idx].req_yyyymm.substring(4, 6)) - 1],
              "debt",
              list[idx].amt_repay
            );
          }
          _this.calData = calData;
          _this.seen = true;
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 달력을 초기화 시킬 데이터
    getDefaultCalData: function() {
      return [
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
<style lang="scss">
</style>
