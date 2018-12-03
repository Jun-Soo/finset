<template>
  <div class="main" v-if="seen">
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
        <Progress :max="progressOption.max" :text="progressOption.text" :click="clickProgress" />
      </div>

      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
            <a @click="clickBanner('calendar')">
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
            <a @click="clickBanner('payment')">
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
            <a @click="clickBanner('settlement')">
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
          <a :class="{'on':curTab === '00'}" @click="clickTab('00')">전체</a>
          <a :class="{'on':curTab === '02'}" @click="clickTab('02')">지출</a>
          <a :class="{'on':curTab === '01'}" @click="clickTab('01')">수입</a>
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
        <div v-if="(consumeList||'')==''">
          <div class="nodata">수입, 소비 내역이 없습니다</div>
        </div>
        <div v-else v-for="(subList, index) in consumeList" :key="index" class="list-wrap">
          <p class="date">{{formatDate(subList[0].dt_trd,"mmdd")}}</p>
          <div v-for="vo in subList" :key="vo.index" class="item" @click="clickConsumeList(vo.seq_consume, vo.no_person, vo.type_in_out, vo.yn_person_regist)">
            <div class="left">
              <p class="name">{{vo.contents}}</p>
              <p class="cate">
                <img :src="getConsumeIconSrc(vo.type_in_out, vo.cd_class)" alt="" />
                <span v-text="vo.type_in_out == '02'?vo.nm_class+' - '+vo.nm_type:vo.nm_class"></span>
              </p>
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
import Progress from "@/components/plugins/progress/Progress.vue";

export default {
  name: "ConsumeMain",
  data() {
    return {
      seen: false,
      ym: "",
      consumeList: [],
      shareList: [],
      isScrap: false,
      isGoal: false,
      curDate: "",
      curTab: "00",
      standardDt: new Date(),
      income: "",
      consume: "",
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      progressOption: {
        text: "예산을 설정하여 목표를 이루세요",
        max: 0
      }
    };
  },
  components: {
    Progress
  },
  // computed () {
  // },
  watch: {
    isScrap: function(param) {
      if (param) {
      } else {
        this.progressOption = {
          text: "공인 인증서를 등록하여 소비내역을 관리하세요",
          max: 0
        };
      }
    },
    isGoal: function(param) {
      if (this.isScrap == true) {
        if (param) {
        } else {
          this.progressOption = {
            text: "설정된 예산이 없습니다",
            max: 0
          };
        }
      } else {
        this.progressOption = {
          text: "공인 인증서를 등록하여 소비내역을 관리하세요",
          max: 0
        };
      }
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "consume";
    this.$parent.isBottom = true;
  },
  created() {
    if (this.$store.state.user.dt_basic > this.standardDt.getDate()) {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
    }
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
          _this.income = response.data.income;
          _this.consume = response.data.consume;
          var consumeGoal = response.data.consumeGoal;
          if ((consumeGoal || "") == "") {
            _this.isGoal = false;
          } else {
            _this.isGoal = true;
            _this.progressOption.text =
              consumeGoal.amt_expense / consumeGoal.amt_budget > 1
                ? Common.formatNumber(
                    consumeGoal.amt_expense - consumeGoal.amt_budget + ""
                  ) +
                  "원 초과 (" +
                  Common.formatNumber(consumeGoal.amt_budget) +
                  "원)"
                : Common.formatNumber(
                    consumeGoal.amt_expense - consumeGoal.amt_budget
                  ) +
                  "원 (" +
                  Common.formatNumber(consumeGoal.amt_budget) +
                  "원)";
            _this.progressOption.max =
              consumeGoal.amt_expense / consumeGoal.amt_budget > 1
                ? 1
                : consumeGoal.amt_expense / consumeGoal.amt_budget;
          }
          _this.isScrap = response.data.isScrap;
          _this.consumeList = response.data.listConsumeInfo;
          _this.seen = true;
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
          return "현금영수증";
          break;

        case "04":
          return "입출금계좌";
          break;

        default:
          return "기타";
          break;
      }
    },
    clickTab: function(code) {
      this.curTab = code;
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
        case "calendar":
          _this.$router.push("/common/monthCal");
          break;
        case "settlement":
          _this.$router.push("/consume/settlement");
          break;
        default:
          break;
      }
    },
    clickSetting: function() {
      this.$router.push("/consume/setting");
    },
    clickConsumeList: function(
      seq_consume,
      no_person,
      type_in_out,
      yn_person_regist
    ) {
      var _this = this;

      this.$router.push({
        path: "/consume/consumeDetail",
        query: {
          seq_consume: seq_consume,
          no_person: no_person,
          type_in_out: type_in_out,
          isMine:
            _this.shareList.findIndex(
              person => person.no_person === no_person
            ) == 0,
          isPersonRegist: yn_person_regist == "Y"
        }
      });
    },
    regConsume: function() {
      this.$router.push("/consume/consumeDetail");
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
    clickProgress: function() {
      if (!this.isScrap) {
        this.$router.push("/scrap/CtrlFcLink");
      } else if (!this.isGoal) {
        this.$router.push("/consume/regGoal");
      }
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
