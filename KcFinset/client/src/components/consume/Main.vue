<template>
  <div class="main">
    <section>
        <div class="spend-top">
            <div class="date-wrap">
                <button class="prev" @click="setPrevMM"></button>
                <p>{{ym}}</p>
                <button class="next" @click="setNextMM"></button>
                <button class="setting"></button>
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
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <div>
          <div v-for="(person, index) in shareList" :key="person.no_person">
            <button @click="clickShare(index)" :class="{'active': person.isShow === true}">{{person.nm_person}}</button>
          </div>
        </div>
        <div class="tab">
            <div class="wrap col3">
                <a href="#" id="00" :class="{'on':curTab === '00'}" @click="clickTab">전체</a>
                <a href="#" id="02" :class="{'on':curTab === '02'}" @click="clickTab">지출</a>
                <a href="#" id="01" :class="{'on':curTab === '01'}" @click="clickTab">수입</a>
            </div>
        </div>
        <div v-if="consumeList.length!=0" class="list02 spend-list">
          <div v-for="subList in consumeList" :key="subList.index" class="list-wrap">
            <p class="date">{{formatDate(subList[0].dt_trd,"mmdd")}}</p>
            <div v-for="vo in subList" :key="vo.index" class="item">
              <div class="left">
                  <p class="name">{{vo.contents}}</p>
                  <p class="cate"><img src="../../assets/images/common/bu_list_shopping.png" alt=""/><span>{{vo.nm_class}} - {{vo.nm_type}}</span></p>
              </div>
              <div class="right">
                  <p :class="chkType(vo.type_in_out)" class="number">{{formatNumber(vo.amt_in_out,vo.type_in_out=='02',vo.type_in_out=='01')}}<em>원</em></p>
                  <p class="text">{{formatMeansConsume(vo.means_consume)}}</p>
              </div>
            </div>
          </div>
          <button class="btn-spend-add"></button>
        </div>
    </section>
  </div>
</template>

<script>
import router from "@/comm/router.js";
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumeMain",
  data() {
    return {
      ym: "",
      consumeList: [],
      shareList: [],
      exceptList: [],
      curDate: "",
      curTab: "00",
      standardDt: new Date(),
      income: "",
      consume: ""
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "consume";
  },
  created() {
    this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeShareInfo();
  },
  beforeMount() {},
  mounted() {},
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
          var list = response.data.listConsumeInfo;

          _this.consumeList = list;
          _this.income = response.data.income;
          _this.consume = response.data.consume;
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
        if(_this.shareList[idx].isShow){
          shareList.push(_this.shareList[idx].no_person);
        }
      }
      return shareList;
    },
    clickShare: function(params) {
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listConsumeInfo();
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
