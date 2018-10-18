<template>
  <div class="main">
    <section>
        <div class="top main">
            <ul>
                <li><a href="index.html">MY</a></li>
                <li><a href="index_credit.html">신용</a></li>
                <li><a href="index_dept.html">부채</a></li>
                <li class="on"><a href="index_spend.html">지출</a></li>
                <li><a href="index_assets.html">자산</a></li>
            </ul>
        </div>
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
        <swiper direction="horizontal"
        :mousewheel-control="true"
        :performance-mode="false"
        :pagination-visible="false"
        :pagination-clickable="false"
        :loop="true"
        @slide-change-start="onSlideChangeStart"
        @slide-change-end="onSlideChangeEnd"
				class="banner-wrap"
				>
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
        </swiper>
        <div class="tab">
            <div class="wrap col3">
                <a href="#" id="00" :class="{'on':curTab === '00'}" @click="clickTab">전체</a>
                <a href="#" id="02" :class="{'on':curTab === '02'}" @click="clickTab">지출</a>
                <a href="#" id="01" :class="{'on':curTab === '01'}" @click="clickTab">수입</a>
            </div>
        </div>
        <div v-if="list.length!=0" class="list02 spend-list">
          <div v-for="subList in list" :key="subList.index" class="list-wrap">
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
      list: [],
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
  beforeCreate() {},
  created() {
    this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listConsumeInfo() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeInfo.json", {
          params: { ym: _this.ym.replace(".", ""), type_in_out: _this.curTab }
        })
        .then(function(response) {
          var list = response.data.listConsumeInfo;

          _this.list = list;
          _this.income = response.data.income;
          _this.consume = response.data.consume;
        });
    },
    formatHead(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    getYm(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    getTodayYm() {
      return this.getYm(new Date());
    },
    setPrevMM() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    },
    setNextMM() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    },
    onSlideChangeStart() {
      console.log("slide-start");
    },
    onSlideChangeEnd() {
      console.log("slide-end");
    },
    formatNumber(number, isMinus, isPlus) {
      return Common.formatNumber(number, isMinus, isPlus);
    },
    formatDate(date, pattern) {
      return Common.formatDate(date, pattern);
    },
    formatMeansConsume(means_consume) {
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
    clickTab(tab) {
      this.curTab = tab.srcElement.id;
      this.listConsumeInfo();
    },
    chkType(type) {
      if (type === "01") {
        return "blue";
      } else {
        return "red";
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
