<template>
  <section>
    <div class="graph-wrapper mt30" v-if="!isNoData">
      <chartjs-bar v-if="!isNone" :labels="mylabels" :datasets="mydatasets" :option="myoption" :bind="true">
      </chartjs-bar>
    </div>

    <div class="detail-list" v-if="!isNoData">
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
    <div class="nodata" v-if="isNoData">
      3개월 이내에 상세 내역이 없습니다
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumeAnalyze",
  data() {
    return {
      isNone: true,
      mylabels: ["0", "0", "0"],
      mydatasets: [
        {
          label: " ",
          backgroundColor: "rgba(228,42,59,0.6)",
          borderColor: "rgba(228,42,59,0.6)",
          borderCapStyle: "butt",
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: "miter",
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
      listConsumeAnalyzeMonth: {},
      listConsumeAnalyzeDay: {},
      Common: Common,
      isNoData: true
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = this.$route.query.contents + " 이력 상세";
    this.createDefaultLabel();
    this.listConsumeAnalyze();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    createDefaultLabel: function() {
      var labelDate = new Date();
      if (this.$store.state.user.dt_basic > labelDate.getDate()) {
        labelDate.setMonth(new Date().getMonth() - 4);
      } else {
        labelDate.setMonth(new Date().getMonth() - 3);
      }
      for (var i = 0; i < 3; i++) {
        labelDate.setMonth(labelDate.getMonth() + 1);
        this.$set(this.mylabels, i, Common.formatDate(labelDate, "yyyymm"));
      }
    },
    listConsumeAnalyze: function() {
      var _this = this;
      var formData = new FormData();

      formData.append("type_in_out", this.$route.query.type_in_out);
      formData.append("contents", this.$route.query.contents);

      this.$http
        .post("/m/consume/listConsumeAnalyze.json", formData)
        .then(function(response) {
          var list = response.data.listConsumeAnalyzeMonth;
          if (list == null || list.length == 0) {
            _this.isNoData = true;
            return;
          } else {
            _this.isNoData = false;
          }
          for (var idx in list) {
            _this.$set(
              _this.mydatasets[0].data,
              _this.mylabels.indexOf(
                Common.formatDate(list[idx].dt_trd, "yyyymm")
              ),
              list[idx].amt_in_out
            );
          }
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
