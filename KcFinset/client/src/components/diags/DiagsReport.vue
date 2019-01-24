<template>
  <section>
    <div class="portfolio-top">
      <p class="key">{{this.$store.state.user.nmPerson}}님이 보유하고 계신</p>
      <p class="value">포트폴리오의 평균등급</p>
      <p class="star">
        <img src="../../assets/images/common/portfolio_star.png" width="31" alt=""  v-for="i in factorAnalysis.avg" :key="i"/>
      </p>
      <a @click="stockGoals()">목표수익률 수정하기</a>
    </div>

    <div class="box-list pb90">
      <div class="portfolio-list">
        <p class="title">Style Factor 분석</p>
        <p class="text">{{factorAnalysis.comment}}</p>
        <div class="graph">
          <chartjs-radar :labels="factorLabel" :datasets="factorDataSets" :option="factorOption" :bind="true">
          </chartjs-radar>
        </div>
      </div>
      <div class="portfolio-list">
        <p class="title">위험도 분석</p>
        <p class="text">{{riskAnalysis.comment}}</p>
        <p class="text">{{riskAnalysis.riskReturnComment}}</p>
        <div class="graph">
          <chartjs-horizontal-bar :labels="riskLabels" :datasets="riskDataSets" :option="riskOption" :bind="true">
          </chartjs-horizontal-bar>
        </div>        
      </div>
      <div class="portfolio-list">
        <p class="title">섹터 집중도 분석</p>
        <p class="text">{{sectorAnalysis.comment}}</p>
        <div class="graph">
          <chartjs-pie :labels="sectorLabels" :data="sectorData" :option="sectorOption" :bind="true">
          </chartjs-pie>          
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="stockReport()" class="blue box solid">종목별 진단 결과 보기</a> 
    </div>

  </section>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "diagsResult",
  data() {
    return {
      factorAnalysis: {},
      riskAnalysis: {},
      sectorAnalysis: {},

      factorLabel: ["Value", "Growth", "Quality", "Profitability", "Momentum"],
      factorDataSets: [
        {
          label: "",
          backgroundColor: "rgba(179,181,198,0.2)",
          borderColor: "rgba(179,181,198,1)",
          data: []
        }
      ],
      factorOption: {
        legend: {
          display: false
        },
        scale: {
          ticks: {
            beginAtZero: true,
            min: 0,
            max: 5,
            stepSize: 1
          }
        },
        tooltips: {}
      },
      riskLabels: [],
      riskDataSets: [
        {
          label: "Risk",
          backgroundColor: "rgba(250,0,0,1)",
          data: []
        }
      ],
      riskOption: {
        legend: {
          display: true,
          position: "right"
        },
        scales: {
          yAxes: [
            {
              display: true
            }
          ]
        },
        tooltips: {
          callbacks: {
            label: function(obj) {
              var value = obj.yLabel;
              value = "risk:" + obj.xLabel;
              return value;
            }
          }
        }
      },
      sectorLabels: [],
      sectorData: [],
      sectorOption: {
        legend: {
          display: true,
          position: "right"
        },
        tooltips: {
          callbacks: {
            label: function(tooltipItem, data) {
              var nm = data.labels[tooltipItem.index];
              var d =
                data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index];

              return nm + " : " + d + "%";
            }
          }
        }
      }
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "포트폴리오 진단";
  },
  created() {
    this.getDiagsReport();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDiagsReport: function() {
      var _this = this;

      this.$http
        .get("/m/diags/getDiagsReport.json", {
          params: {}
        })
        .then(response => {
          var diagsReport = response.data.diagsReport;
          _this.showFactorAnalysis(diagsReport.factorAnalysis);
          _this.showRiskAnalysis(diagsReport.riskAnalysis);
          _this.showSectorAnalysis(diagsReport.sectorAnalysis);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    showFactorAnalysis: function(factorAnalysis) {
      this.factorAnalysis = factorAnalysis;

      var data = [
        factorAnalysis.value,
        factorAnalysis.growth,
        factorAnalysis.quality,
        factorAnalysis.profitability,
        factorAnalysis.momentum
      ];
      this.factorDataSets[0].data = data;
    },
    showRiskAnalysis: function(riskAnalysis) {
      this.riskAnalysis = riskAnalysis;

      var stockRisks = riskAnalysis.stockRisks;
      var data = [];
      for (var i = 0; i < stockRisks.length; i++) {
        //console.log(stockRisks[i].isuKorNm);
        this.riskLabels.push(stockRisks[i].isuKorNm);
        data.push(stockRisks[i].riskRate);
      }
      //console.log(data);
      this.riskDataSets[0].data = data;
    },
    showSectorAnalysis: function(sectorAnalysis) {
      this.sectorAnalysis = sectorAnalysis;

      var sectorExposures = sectorAnalysis.sectorExposures;
      var data = [];
      var total = 0;
      for (var i = 0; i < sectorExposures.length; i++) {
        this.sectorLabels.push(sectorExposures[i].sectorNm);
        data.push(sectorExposures[i].invest);
        total += sectorExposures[i].invest;
      }
      for (var i = 0; i < data.length; i++) {
        this.sectorData.push(Math.round(100 * data[i] / total));
      }
    },
    stockGoals: function() {
      this.$router.push("/assets/stockGoal");
    },
    stockReport: function() {
      this.$router.push("/assets/diagsStockReport");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
