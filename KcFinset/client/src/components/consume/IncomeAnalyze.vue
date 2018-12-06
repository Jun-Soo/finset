<template>
  <section>
    <div class="graph-wrapper mt30">
      <!-- <img src="../../assets/images/consume/graph.png" width="100%" alt="" /> -->
      <chartjs-bar v-if="!isNone" :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true">
      </chartjs-bar>
    </div>

    <div class="detail-list">
      <dl>
        <dt>월별</dt>
        <dd v-for="voMonth in listConsumeAnalyzeMonth" :key="voMonth.dt_trd">
          <p v-text="voMonth.dt_trd.substring(0,4) + '년 ' + voMonth.dt_trd.substring(4,6) + '월'"></p>
          <p>{{Common.formatNumber(voMonth.amt_in_out)}} 원</p>
        </dd>
      </dl>
      <dl>
        <dt>일별</dt>
        <dd v-for="(voDay, index) in listConsumeAnalyzeDay" :key="index">
          <p>{{Common.formatDateDot(voDay.dt_trd)}} {{formatTime(voDay.tm_trd)}}
            <em v-text="voDay.means_consume=='01'||voDay.means_consume=='04'?voDay.nm_fc:'현금'"></em>
          </p>
          <p>{{Common.formatNumber(voDay.amt_in_out)}} 원</p>
        </dd>
      </dl>
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumeIncomeAnalyze",
  data() {
    return {
      isNone: true,
      mylabels: [],
      mydatasets: [
        {
          label: " ",
          backgroundColor: "rgba(228,42,59,0.6)",
          borderColor: "rgba(228,42,59,0.6)",
          borderCapStyle: "butt",
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: "miter",
          // pointBorderColor: "rgba(234,85,100,1)",
          // pointBackgroundColor: "#fff",
          // pointBorderWidth: 1,
          // pointHoverRadius: 5,
          // pointHoverBackgroundColor: "rgba(234,85,100,1)",
          // pointHoverBorderColor: "rgba(220,220,220,1)",
          // pointHoverBorderWidth: 2,
          // pointRadius: 1,
          // pointHitRadius: 10,
          data: [],
          spanGaps: false
        }
      ],
      chartData: [],
      labelData: [],
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
      listConsumeAnalyzeMonth: {},
      listConsumeAnalyzeDay: {},
      Common: Common
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = this.$route.query.contents + " 이력 상세";
    this.listConsumeAnalyze();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listConsumeAnalyze: function() {
      var _this = this;
      var formData = new FormData();

      formData.append("type_in_out", this.$route.query.type_in_out);
      formData.append("contents", this.$route.query.contents);

      this.$http
        .post("/m/consume/listConsumeAnalyze.json", formData)
        .then(function(response) {
          _this.labelData = [];
          _this.chartData = [];

          var list = response.data.listConsumeAnalyzeMonth;
          for (var idx in list) {
            _this.labelData.push(Common.formatDate(list[idx].dt_trd, "yyyymm"));
            _this.chartData.push(list[idx].amt_in_out);
          }
          _this.labelData.reverse();
          _this.chartData.reverse();

          _this.mylabels = _this.labelData;
          _this.$set(_this.mydatasets[0], "data", _this.chartData);

          _this.listConsumeAnalyzeMonth = response.data.listConsumeAnalyzeMonth;
          _this.listConsumeAnalyzeDay = response.data.listConsumeAnalyzeDay;

          _this.isNone = false;
        });
    },
    formatTime: function(time) {
      var hh = time.substring(0, 2);
      var mm = time.substring(2, 4);
      var ss = time.substring(4, 6);
      return hh + ":" + mm;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
