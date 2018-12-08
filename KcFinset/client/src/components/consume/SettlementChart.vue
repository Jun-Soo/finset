<template>
  <div>
    <chartjs-bar ref="chart" :labels="mylabels" :datasets="mydatasets" :scalesdisplay="false" :option="options" :bind="true"></chartjs-bar>
  </div>
</template>



<script type="text/javascript">
import Common from "@/assets/js/common.js";
import moment from "moment";

export default {
  name: "ConsumeSettlement",
  data() {
    return {
      mylabels: [],
      mydatasets: [
        {
          //지출     // label: "My First dataset",
          backgroundColor: "#e52638",
          borderColor: "#e52638",
          borderWidth: 1,
          data: []
        },
        {
          //수입
          backgroundColor: "#62c1d0",
          borderColor: "#62c1d0",
          borderWidth: 1,
          data: []
        }
      ],
      options: {
        legend: {
          display: false
        },
        events: ["click"]
      },
      dateFrom: null, //받는 값
      dateTo: null, //받는 값
      rangeDate: [] //차트에 뿌려지는 label 날짜값
    };
  },
  props: {
    chartList: {
      type: Array,
      required: true
    },
    consumeForm: {
      type: Object,
      required: true
    },
    dt_from: {
      type: Date,
      required: true
    },
    dt_to: {
      type: Date,
      required: true
    },
    dataPeriod: {
      type: String,
      required: true
    }
  },
  watch: {
    chartList: function() {
      console.log("watched");
      // this.createChart('chartjs-bar', this.planetChartData);
      // this.$set(this.mylabels, []);

      this.drawChart();
      // this.$refs.chart.update();
      // this.$refs.chart.renderChart();
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입·지출 보고서";
  },
  created() {},
  beforeMount() {},
  mounted() {
    //chart 클릭시 이벤트 발생하는 부분
    this.drawChart();
    this.$set(this.$refs.chart.option, "onClick", this._clickChart);
    this.$refs.chart.renderChart();
  },
  beforeUpdate() {},
  updated() {
    // this.$refs.chart.renderChart();
    console.log("updated");
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    /**
     * 차트 그릴때,
     * 1. 현재 type, dt_from , dt_to 날짜 가져옴
     * 2. 라벨값 for문 돌려서 입력
     * 3. 라벨값과 데이터값 같으면 dataList에 push, 같지 않으면 continue
     * 4. dataList1 : 지출, dataList2 : 수입
     * 5. year 일땐, 달별로 sum
     * 6. mon 일땐, 월요일 기준으로 주별로
     * 7. day 일땐, 일별로 chart를 그린다
     */
    drawChart: function() {
      console.log("drawchart!");
      let _chartList = this.chartList;
      let _dataList1 = [];
      let _dataList2 = [];
      this.mylabels = [];
      this.dateFrom = null;
      this.dateTo = null;
      this.rangeDate = [];

      if (this.dataPeriod == "yr") {
        let fromMon = this.dt_from.getMonth();
        let toMon = this.dt_to.getMonth();
        for (let k = fromMon; k <= toMon; k++) {
          this.mylabels.push(k + 1 + "월");
          this.rangeDate.push(
            moment(this.dt_from)
              .add(k, "month")
              .format("YYYYMMDD")
          );
          for (var i in _chartList) {
            if (k + 1 == _chartList[i].dt_trd.substring(4, 6)) {
              if (_chartList[i].type_in_out == "02") {
                _dataList1.push(_chartList[i].amt_in_out);
              } else {
                _dataList2.push(_chartList[i].amt_in_out);
              }
            } else {
              continue;
            }
          } //for
          if (k + 1 != _dataList1.length) {
            _dataList1.push("");
          }
          if (k + 1 != _dataList2.length) {
            _dataList2.push("");
          }
        } //for
      } else if (this.dataPeriod == "mon") {
        // console.log(moment(moment(this.dt_from).weekday(1)).diff(moment(this.dt_to).weekday(1), "days"));
        // console.log(Math.abs(moment(moment(this.dt_from).weekday(1)).diff(moment(this.dt_to).weekday(1), "days")) + 2);
        let range = Math.ceil(
          (Math.abs(
            moment(moment(this.dt_from).weekday(1)).diff(
              moment(this.dt_to).weekday(1),
              "days"
            )
          ) +
            2) /
            7
        ); //기간
        // let date = [];
        for (let k = 0; k < range; k++) {
          let dtFrom_Monday = moment(this.dt_from).weekday(1);
          // console.log(dtFrom_Monday.add((7*k), "days").format("YYYYMMDD"));
          // console.log(dtFrom_Monday.format('YYYYMMDD'));
          this.rangeDate.push(
            dtFrom_Monday.add(7 * k, "days").format("YYYYMMDD")
          );
          this.mylabels.push(dtFrom_Monday.format("MM[월]DD[일]"));
          for (let j in _chartList) {
            if (this.rangeDate[k] == _chartList[j].dt_trd) {
              if (_chartList[j].type_in_out == "02") {
                _dataList1.push(_chartList[j].amt_in_out);
              } else {
                _dataList2.push(_chartList[j].amt_in_out);
              }
            } else {
              continue;
            }
          } //for

          if (k + 1 != _dataList1.length) {
            _dataList1.push("");
          }
          if (k + 1 != _dataList2.length) {
            _dataList2.push("");
          }
        } //for
      } else {
        let range = Math.abs(moment(this.dt_to).diff(this.dt_from, "days")) + 2; //기간
        // let date = []; //날짜 형태
        for (let k = 0; k < range; k++) {
          this.rangeDate.push(
            moment(this.dt_from)
              .add(k, "days")
              .format("YYYYMMDD")
          );
          let day = moment(this.dt_from)
            .add(k, "days")
            .format("DD")
            .toString();
          if (day.startsWith("0")) {
            day = day.substring(1);
          }
          this.mylabels.push(day + "일");

          for (let j in _chartList) {
            if (this.rangeDate[k] == _chartList[j].dt_trd) {
              if (_chartList[j].type_in_out == "02") {
                _dataList1.push(_chartList[j].amt_in_out);
              } else {
                _dataList2.push(_chartList[j].amt_in_out);
              }
            } else {
              continue;
            }
          } //for

          if (k + 1 != _dataList1.length) {
            _dataList1.push("");
          }
          if (k + 1 != _dataList2.length) {
            _dataList2.push("");
          }
        } //for
        console.log(this.rangeDate);
      }

      this.$set(this.mydatasets[0], "data", _dataList1);
      this.$set(this.mydatasets[1], "data", _dataList2);
    },
    _clickChart: function(event, el) {
      //아무것도 없는 곳을 클릭하면 undefined 떨어짐
      if (el[0] != undefined) {
        var index = el[0]._index;
        var label = el[0]._model.label;
        //사용해야 되는 데이터
        console.log(
          index +
            "////" +
            label +
            "////" +
            this.rangeDate[index] +
            "////" +
            this.dataPeriod
        );
        this.$parent.clickChart(this.rangeDate[index], this.dataPeriod);
      }
    }
  }
};
</script>
