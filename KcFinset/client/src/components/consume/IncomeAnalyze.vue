<template>
  <section>
    <div class="graph-wrapper mt30">
      <!-- <img src="../../assets/images/consume/graph.png" width="100%" alt="" /> -->
      <chartjs-line v-if="!isNone" :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true"></chartjs-line>
    </div>

    <div class="detail-list">
      <dl>
        <dt>월별</dt>
        <!-- <dd>
          <p>’18년 7월</p>
          <p>535,000 원 </p>
        </dd> -->
        <!-- <dd>
          <p>’18년 6월</p>
          <p>115,000 원 </p>
        </dd> -->
        <dd v-for="voMonth in listConsumeAnalyzeMonth" :key="voMonth.dt_trd">
          <p v-text="voMonth.dt_trd.substring(0,4) + '년 ' + voMonth.dt_trd.substring(4,6) + '월'"></p>
          <p>{{Common.formatNumber(voMonth.amt_in_out)}} 원</p>
        </dd>
      </dl>
      <dl>
        <dt>일별</dt>
        <!-- <dd>
          <p>7.12 15:00<em> 현금</em></p>
          <p>118,107 원</p>
        </dd>
        <dd>
          <p>7.11 22:00<em> 신한카드</em></p>
          <p>125,000 원</p>
        </dd>
        <dd>
          <p>7.08 21:00<em> 신한카드</em></p>
          <p>225,000 원</p>
        </dd> -->
        <dd v-for="voDay in listConsumeAnalyzeDay" :key="voDay.dt_trd">
          <p>{{Common.formatDateDot(voDay.dt_trd)}} {{formatTime(voDay.tm_trd)}} <em> {{voDay.nm_fc}}</em></p>
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

          console.log(response.data.listConsumeAnalyzeMonth);
          _this.listConsumeAnalyzeMonth = response.data.listConsumeAnalyzeMonth;
          console.log(response.data.listConsumeAnalyzeDay);
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
