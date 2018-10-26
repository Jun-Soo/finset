<template>
  <div>
    <section>
      <div class="dept-top">
        <div class="wrap">
          <div class="balance">
            <p class="key">대출잔액</p>
            <p class="value">{{formatNumber(debtSummaryData.amt_remain * 10000)}}<em>원</em></p>
          </div>
          <div class="graph"><chartjs-line :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true"></chartjs-line></div>
          <div class="flex2 bar-graph">
            <div class="item">
              <p class="key">상환율</p>
              <div class="text-wrap">
                <p class="big">{{debtSummaryData.rate_amt_contract}}<em>%</em></p>
                <p class="small">{{formatNumber(debtSummaryData.amt_contract/10000)}}<em>만원</em></p>
              </div>
              <div class="bar">
                <p class="active" :style="debtSummaryData.repayStyle"></p>
              </div>
            </div>
            <div class="item">
              <p class="key">상환능력<em>(소득대비)</em></p>
              <div class="text-wrap">
                <p class="big">{{calDsr(debtSummaryData.cur_mon_mid_rpy, debtSummaryData.amt_etm_income)}}<em>%</em></p>
              </div>
              <div class="bar">
                <p class="active" :style="debtSummaryData.dsrStyle"></p>
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
                          <img src="../../assets/images/main/banner_ico.png" alt=""/>
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
                          <img src="../../assets/images/main/banner_ico.png" alt=""/>
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
                          <img src="../../assets/images/main/banner_ico.png" alt=""/>
                      </div>
                  </div>
              </a>
          </slide>
          <slide class="item">
              <a href="#">
                  <div class="banner">
                      <div class="left">
                          <p class="key">뉴스</p>
                          <p class="value">금리 등 관련 뉴스를<br>조회합니다</p>
                      </div>
                      <div class="right">
                          <img src="../../assets/images/main/banner_ico.png" alt=""/>
                      </div>
                  </div>
              </a>
          </slide>
          <slide class="item">
              <a href="#">
                  <div class="banner">
                      <div class="left">
                          <p class="key">금리인하요구</p>
                          <p class="value">금리인하요구 조건이 맞는지<br>확인해보세요</p>
                      </div>
                      <div class="right">
                          <img src="../../assets/images/main/banner_ico.png" alt=""/>
                      </div>
                  </div>
              </a>
          </slide>
        </carousel>
      </div>
      
      <div class="list01 dept-list">
				<div class="filter-wrap">
					<div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
							<input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{person.nm_person}}</label>
					</div>
				</div>
        <div class="item" v-for="vo in debtListData" :key="vo.no_manage_info">
          <div class="top">
            <p class="symbol"><img :src="vo.imgSrc" alt=""/>{{vo.nm_fc}}</p>
            <p class="text blue">{{vo.debt_type}}</p>
          </div>
          <div class="number-wrap">
            <div class="left">
              <p class="key">잔액</p>
              <p class="number">{{formatNumber(vo.amt_remain * 10000)}}<em>원</em></p>
          </div>
          </div>
          <div class="bar"><p :style="vo.eachStyle"></p></div>
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
        <p class="info-massage">안내 메세지가 출력되는 영역입니다</p>
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
      isTest: true,
      debtListData: [],
      debtSummaryData: "",
      dataList: [1, 2, 3, 4, 5, 6, 7],
      mylabels: [],
      mydatasets: [
        {
          label: " ",
          fill: false,
          lineTension: 0.1,
          backgroundColor: "rgba(75,192,192,0.4)",
          borderColor: "rgba(75,192,192,1)",
          borderCapStyle: "butt",
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: "miter",
          pointBorderColor: "rgba(75,192,192,1)",
          pointBackgroundColor: "#fff",
          pointBorderWidth: 1,
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(75,192,192,1)",
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
        }
        // scales: {
        //   yAxes: [
        //     {
        //       ticks: {
        //         stepSize: 10000
        //       }
        //     }
        //   ]
        // }
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
          _this.getDebtSummary();
          _this.listDebtPg();
        });
    },
    listDebtPg: function() {
      var _this = this;
      var no_person_list = _this.filterShareList();
      if (no_person_list.length == 0) {
        return;
      }
      this.$http
        .get("/m/debt/listDebtPg.json", {
          params: { no_person_list: no_person_list }
        })
        .then(function(response) {
          var data = response.data.debtListData;
          for (var idx in data) {
            data[idx].eachStyle = "width:" + data[idx].rate_repay + "%";
            data[idx].imgSrc =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + data[idx].cd_fc;
          }
          _this.debtListData = data;
        });
    },
    getDebtSummary: function() {
      var _this = this;
      var no_person_list = _this.filterShareList();
      if (no_person_list.length == 0) {
        return;
      }
      this.$http
        .get("/m/debt/getDebtSummary.json", {
          params: { no_person_list: no_person_list }
        })
        .then(function(response) {
          var data = response.data.debtSummaryData;
          _this.debtSummaryData = data;
          _this.debtSummaryData.repayStyle =
            "width:" + data.rate_amt_contract + "%";
          _this.debtSummaryData.dsrStyle =
            "width:" +
            _this.calDsr(data.cur_mon_mid_rpy, data.amt_etm_income) +
            "%";
          _this.mylabels = response.data.dateList;
          _this.$set(_this.mydatasets[0], "data", response.data.dataList);
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
      this.getDebtSummary();
      this.listDebtPg();
    },
    clickBanner: function(key) {
      var _this = this;
      switch (key) {
        case "goods":
          _this.$router.push("/goods/list");
          break;

        default:
          break;
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
