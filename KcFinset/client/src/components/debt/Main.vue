<template>
    <div class="main">
      <section>
           <div class="top main">
              <ul>
                  <li><a href="index.html">MY</a></li>
                  <li><a href="index_credit.html">신용</a></li>
                  <li class="on"><a href="index_dept.html">부채</a></li>
                  <li><a href="index_spend.html">지출</a></li>
                  <li><a href="index_assets.html">자산</a></li>
              </ul>
          </div>
          <div class="dept-top">
              <div class="balance">
                  <p class="key">대출잔액</p>
                  <p class="value">{{formatNumber(debtSummaryData.amt_remain * 10000)}}<em>원</em></p>
              </div>
              <!-- <div class="graph"><img src="../../assets/images/main/dept_graph.png" width="100%" alt=""/></div> -->
							<div class="graph"><chartjs-line :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true"></chartjs-line></div>
							<div class="flex2 bar-graph">
                  <div class="item">
                      <p class="key">상환율</p>
                      <div class="text-wrap">
                          <p class="big">{{debtSummaryData.rate_amt_contract}}<em>%</em></p>
                          <p class="small">{{formatNumber(debtSummaryData.amt_contract)}}<em>원</em></p>
                      </div>
                      <div class="bar">
													<p class="active" :style="debtSummaryData.repayStyle"></p>
                      </div>
                  </div>
                  <div class="item">
                      <p class="key">상환능력<em>(소득대비)</em></p>
                      <div class="text-wrap">
                          <p class="big">{{calDsr(debtSummaryData.cur_mon_mid_rpy, debtSummaryData.amt_etm_income)}}<em>%</em></p>
                          <p class="small">{{formatNumber(debtSummaryData.amt_etm_income * 10000)}}<em>원</em></p>
                      </div>
                      <div class="bar">
                          <p class="active" :style="debtSummaryData.dsrStyle"></p>
                      </div>
                  </div>
              </div>
          </div>
          <swiper direction="horizontal"
          :mousewheel-control="true"
          :performance-mode="false"
          :pagination-visible="false"
          :pagination-clickable="false"
          :loop="true"
          >
              <div class="item">
                  <a href="#"><img src="../../assets/images/main/dept_banner1.png" alt=""/></a>
              </div>
              <div class="item">
                  <a href="#"><img src="../../assets/images/main/dept_banner1.png" alt=""/></a>
              </div>
              <div class="item">
                  <a href="#"><img src="../../assets/images/main/dept_banner1.png" alt=""/></a>
              </div>
          </swiper>

					<div class="list01 dept-list">
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
									<!-- <p class="value"><span>{{formatNumber(vo.amt_repay * 10000)}}<em>원</em></span><span>{{vo.ever_interest}}<em>%</em></span></p> -->
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
        scales: {
          yAxes: [
            {
              ticks: {
                stepSize: 10000
              }
            }
          ]
        }
      }
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    this.listDebtPg();
    this.getDebtSummary();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listDebtPg() {
      var _this = this;
      this.$http.get("/m/debt/listDebtPg.json").then(function(response) {
        var data = response.data.debtListData;
        for (var idx in data) {
          data[idx].eachStyle = "width:" + data[idx].rate_repay + "%";
          data[idx].imgSrc =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + data[idx].cd_fc;
        }
        _this.debtListData = data;
      });
    },
    getDebtSummary() {
      var _this = this;
      this.$http.get("/m/debt/getDebtSummary.json").then(function(response) {
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
    formatNumber(number) {
      return Common.formatNumber(number);
    },
    calDsr(repay, income) {
      if (repay == "-" || income == "-") {
        return 0;
      } else {
        return (repay / income) * 100;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
