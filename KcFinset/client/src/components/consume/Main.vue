<template>
  <div class="main">
    <section>
      <div class="spend-top">
        <div class="date-wrap">
          <button class="prev" @click="setPrevMM"></button>
          <p>{{ym}}</p>
          <button class="next" @click="setNextMM"></button>
          <button class="setting" @click="clickSetting"></button>
        </div>
        <div class="wrap">
          <div class="item">
            <p class="key">지출<em>(원)</em></p>
            <p class="value">{{formatNumber(consume)}}</p>
          </div>
          <div class="item">
            <p class="key">수입<em>(원)</em></p>
            <p class="value">{{formatNumber(income)}}</p>
          </div>
        </div>
      </div>

      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <div class="left">
                  <p class="key">캘린더</p>
                  <p class="value">수입과 지출 내역이 확인가능한<br>습관 달력을 이용해 보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#" @click="clickBanner('payment')">
              <div class="banner">
                <div class="left">
                  <p class="key">카드 대금</p>
                  <p class="value">이전에 지출한 카드 대금을<br>한눈에 확인 하세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <div class="left">
                  <p class="key">소비통계분석</p>
                  <p class="value">지금까지 사용한 소비와 수입<br>통계를 확인해 보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
        </carousel>
      </div>

      <div class="tab">
        <div class="wrap col3">
          <a href="#" id="00" :class="{'on':curTab === '00'}" @click="clickTab">전체</a>
          <a href="#" id="02" :class="{'on':curTab === '02'}" @click="clickTab">지출</a>
          <a href="#" id="01" :class="{'on':curTab === '01'}" @click="clickTab">수입</a>
        </div>
      </div>

      <div class="spend-list box-list noMG">
        <div class="list-wrap">
          <div class="filter-wrap">
            <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
              <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
            </div>
          </div>
        </div>
        <div v-for="(subList, index) in consumeList" :key="index" class="list-wrap">
          <p class="date">{{formatDate(subList[0].dt_trd,"mmdd")}}</p>
          <div v-for="vo in subList" :key="vo.index" class="item" @click="clickConsumeList(vo.seq_consume, vo.no_person)">
            <div class="left">
              <p class="name">{{vo.contents}}</p>
              <p class="cate"><img src="../../assets/images/common/bu_list_shopping.png" alt="" /><span>{{vo.nm_class}} - {{vo.nm_type}}</span></p>
            </div>
            <div class="right">
              <p :class="chkType(vo.type_in_out)" class="number">{{formatNumber(vo.amt_in_out,vo.type_in_out=='02',vo.type_in_out=='01')}}<em>원</em></p>
              <p class="circle red"><span :class="settingList[shareList.findIndex(person => person.no_person === vo.no_person)].color"></span></p>
              <p class="text">{{formatMeansConsume(vo.means_consume)}}</p>
            </div>
          </div>
        </div>
        <button @click="regConsume" class="btn-spend-add"></button>
      </div>
    </section>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumeMain",
  data() {
    return {
      ym: "",
      consumeList: [],
      shareList: [],
      isScrap: false,
      curDate: "",
      curTab: "00",
      standardDt: new Date(),
      income: "",
      consume: "",
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "greend", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ]
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "consume";
    this.$parent.isBottom = true;
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeShareInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listConsumeShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeSharePersonInfo.json", { params: {} })
        .then(function(response) {
          var list = response.data.listConsumeSharePersonInfo;
          var test = new Object();
          test.no_person = "P000000109";
          test.nm_person = "테스트";
          list.push(test);
          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listConsumeInfo();
        });
    },
    listConsumeInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeInfo.json", {
          params: {
            ym: _this.ym.replace(".", ""),
            type_in_out: _this.curTab,
            no_person_list: _this.filterShareList()
          }
        })
        .then(function(response) {
          _this.consumeList = response.data.listConsumeInfo;
          _this.income = response.data.income;
          _this.consume = response.data.consume;
          _this.isScrap = response.data.isScrap;
        });
    },
    formatHead: function(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    getYm: function(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    setPrevMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    },
    setNextMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    },
    formatNumber: function(number, isMinus, isPlus) {
      return Common.formatNumber(number, isMinus, isPlus);
    },
    formatDate: function(date, pattern) {
      return Common.formatDate(date, pattern);
    },
    formatMeansConsume: function(means_consume) {
      switch (means_consume) {
        case "01":
          return "카드";
          break;

        case "02":
          return "현금";
          break;

        case "03":
          return "입출금계좌";
          break;

        default:
          return "기타";
          break;
      }
    },
    clickTab: function(tab) {
      this.curTab = tab.srcElement.id;
      this.listConsumeInfo();
    },
    chkType: function(type) {
      if (type === "01") {
        return "blue";
      } else {
        return "red";
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
      this.listConsumeInfo();
    },
    clickBanner: function(key) {
      var _this = this;
      switch (key) {
        case "payment":
          _this.$router.push("/consume/payment");
          break;
        default:
          break;
      }
    },
    clickSetting: function() {
      this.$router.push("/consume/setting");
    },
    clickConsumeList: function(seq_consume, no_person) {
      this.$router.push({
        path: "/consume/consumeDetail",
        query: { seq_consume: seq_consume, no_person: no_person }
      });
    },
    regConsume: function() {
      this.$router.push("/consume/consumeDetail");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
.active {
  background: green;
  color: white;
}
</style>
