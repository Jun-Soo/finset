<template>
  <div>
    <section>
      <div class="dept-top">
        <div class="wrap">
          <div class="balance">
            <p class="key">대출잔액</p>
            <p class="value">{{formatNumber(debtSummary.amt_remain * 10000)}}<em>원</em></p>
          </div>
          <div class="graph">
            <chartjs-line v-if="!isNone" :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true"></chartjs-line>
          </div>
          <div class="flex2 bar-graph">
            <div class="item">
              <p class="key">상환율</p>
              <div class="text-wrap">
                <p class="big">{{debtSummary.rate_amt_contract}}<em>%</em></p>
                <p class="small">{{formatNumber(debtSummary.amt_contract/10000)}}<em>만원</em></p>
              </div>
              <div class="bar">
                <p class="active" :style="debtSummary.repayStyle"></p>
              </div>
            </div>
            <div class="item">
              <p class="key">상환능력<em>(소득대비)</em></p>
              <div class="text-wrap">
                <!-- <p class="big">{{calDsr(debtSummary.cur_mon_mid_rpy, debtSummary.amt_etm_income)}}<em>%</em></p> -->
                <p class="big">0<em>%</em></p>
              </div>
              <div class="bar">
                <!-- <p class="active" :style="debtSummary.dsrStyle"></p> -->
                <p class="active"></p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
            <a href="#" @click="clickBanner('goods')">
              <div class="banner">
                <div class="left">
                  <p class="key">추천 상품</p>
                  <p class="value">나의 상황에 맞는<br>상품을 추천합니다</p>
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
                  <p class="key">계산기</p>
                  <p class="value">DSR(상환능력), LTV 등을<br>직접 계산해 보세요</p>
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
                  <p class="key">캘린더</p>
                  <p class="value">상환일, 이자납입일을 달력으로<br>관리하시면 더 ~ 편리합니다</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a @click="clickBanner('news')">
              <div class="banner">
                <div class="left">
                  <p class="key">뉴스</p>
                  <p class="value">금리 등 관련 뉴스를<br>조회합니다</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a @click="clickBanner('reqIntrCut')">
              <div class="banner">
                <div class="left">
                  <p class="key">금리인하요구</p>
                  <p class="value">금리인하요구 조건이 맞는지<br>확인해보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/main/banner_ico.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
        </carousel>
      </div>

      <div class="list01 box-list pb90">
        <div class="filter-wrap">
          <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
            <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
          </div>
        </div>
        <div class="item" v-for="vo in debtList" :key="vo.no_manage_info" @click="goDetail(vo.no_person, vo.no_manage_info)">
          <div class="top">
            <p class="symbol"><img :src="vo.imgSrc" alt="" />{{vo.nm_fc}}</p>
            <p class="text blue">{{vo.debt_type}}
              <span class="circle" :class="settingList[shareList.findIndex(person => person.no_person === vo.no_person)].color"></span>
            </p>
          </div>
          <div class="number-wrap">
            <div class="left">
              <p class="key">잔액</p>
              <p class="number">{{formatNumber(vo.amt_remain * 10000)}}<em>원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="vo.eachStyle"></p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key"><span>상환금액</span><span>이자율</span></p>
              <p class="value"><span>{{formatNumber((vo.amt_contract-vo.amt_remain) * 10000)}}<em>원</em></span><span>{{vo.ever_interest}}<em>%</em></span></p>
            </div>
            <div class="right">
              <p class="key"><span>원금</span></p>
              <p class="value"><span>{{formatNumber(vo.amt_contract * 10000)}}<em>원</em></span></p>
            </div>
          </div>
        </div>
        <div class="btn-wrap">
          <a href="#" class="solid">부채등록</a>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";
export default {
  name: "DebtMain",
  data() {
    return {
      isNone: true,
      debtList: [],
      debtSummary: "",
      dataList: [1, 2, 3, 4, 5, 6, 7],
      mylabels: [],
      mydatasets: [
        {
          label: " ",
          fill: false,
          lineTension: 0.2,
          backgroundColor: "rgba(75,192,192,0.4)",
          borderColor: "rgba(228,42,59,1)",
          borderCapStyle: "butt",
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: "miter",
          pointBorderColor: "rgba(234,85,100,1)",
          pointBackgroundColor: "#fff",
          pointBorderWidth: 1,
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(234,85,100,1)",
          pointHoverBorderColor: "rgba(220,220,220,1)",
          pointHoverBorderWidth: 2,
          pointRadius: 1,
          pointHitRadius: 10,
          data: [],
          spanGaps: false
        }
      ],
      myoption: {
        legend: {
          display: false
        },
        scales: {
          yAxes: [
            {
              display: false
            }
          ]
        },
        tooltips: {
          callbacks: {
            label: function(obj) {
              var value = obj.yLabel;
              value = value.toString();
              value = value.split(/(?=(?:...)*$)/);
              value = value.join(",");
              return value;
            }
          }
        }
      },
      shareList: [],
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
    this.$store.state.header.active = "debt";
    this.$parent.isBottom = true;
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.listDebtSharePersonInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listDebtSharePersonInfo: function() {
      var _this = this;
      this.$http
        .get("/m/debt/listDebtSharePersonInfo.json")
        .then(function(response) {
          var list = response.data.listDebtSharePersonInfo;
          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listDebtPg();
        });
    },
    listDebtPg: function() {
      var _this = this;
      var no_person_list = this.filterShareList();
      if (no_person_list.length == 0) {
        return;
      }
      this.$http
        .get("/m/debt/listDebtPg.json", {
          params: { no_person_list: no_person_list }
        })
        .then(function(response) {
          var debtList = response.data.debtList;
          for (var idx in debtList) {
            debtList[idx].eachStyle = "width:" + debtList[idx].rate_repay + "%";
            debtList[idx].imgSrc =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + debtList[idx].cd_fc;
          }
          _this.debtList = debtList;

          var debtSummary = response.data.debtSummary;

          if ((debtSummary || "") != "") {
            debtSummary.repayStyle =
              "width:" + debtSummary.rate_amt_contract + "%";
            debtSummary.dsrStyle =
              "width:" +
              _this.calDsr(
                debtSummary.cur_mon_mid_rpy,
                debtSummary.amt_etm_income
              ) +
              "%";
            _this.mylabels = response.data.dateList;
            _this.$set(_this.mydatasets[0], "data", response.data.dataList);
            _this.isNone = false;
          } else {
            debtSummary = new Object();
            debtSummary.rate_amt_contract = "0";
            debtSummary.repayStyle = "width:0%";
            debtSummary.dsrStyle = "width:0%";
          }
          _this.debtSummary = debtSummary;
        });
    },
    formatNumber: function(number) {
      return Common.formatNumber(number);
    },
    calDsr: function(repay, income) {
      if (repay == "-" || income == "-") {
        return 0;
      } else {
        return (repay / income) * 100;
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
      this.listDebtPg();
    },
    clickBanner: function(key) {
      var _this = this;
      switch (key) {
        case "goods":
          _this.$router.push("/goods/list");
          break;
        case "news":
          _this.$router.push({
            name: "newsMain",
            query: { scKeyword: ["02"] }
          });
          break;
        case "reqIntrCut":
           _this.$router.push("/debt/reqIntrCut");
          break;
        default:
          break;
      }
    },
    goDetail: function(no_person, no_manage_info) {
      this.$router.push({
        path: "/debt/detail",
        query: { no_person: no_person, no_manage_info: no_manage_info }
      });
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
