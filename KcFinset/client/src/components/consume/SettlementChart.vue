<template>
  <div>
    <chartjs-bar ref="chart" :labels="mylabels" :datasets="mydatasets" :scalesdisplay="false" :option="options"></chartjs-bar>
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
      dateFrom: null,
      dateTo: null
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
  // watch: {
  //   chartList:{

  //   }
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입·지출 보고서";
  },
  created() {},
  beforeMount() {},
  mounted() {
    this.$set(this.$refs.chart.option, "onClick", this.clickChart);
    this.$refs.chart.renderChart();

    console.log(this.chartList);
    console.log(moment(this.dt_from).weekday(0));
    // console.log(Common.formatDat(this.dt_from));
    let _chartList = this.chartList;
    let _dataList1 = [];
    let _dataList2 = [];
    let fromMon = this.dt_from.getMonth();
    let toMon = this.dt_to.getMonth();
    if (this.dataPeriod == "yr") {
      // if (this.dt_from.getFullYear() === this.dt_to.getFullYear()) {// 12개월 이내에서 돼야함
      for (let k = fromMon; k <= toMon; k++) {
        this.mylabels.push(k + 1 + "월");
        for (var i in _chartList) {
          if (_chartList[i].type_in_out == "02") {
            // console.log( _chartList[i].dt_trd.substring(4, 6));
            if (k + 1 == _chartList[i].dt_trd.substring(4, 6)) {
              _dataList1.push(_chartList[i].amt_in_out); //지출
            } else {
              _dataList1.push("");
            }
          } else {
            if (k + 1 == _chartList[i].dt_trd.substring(4, 6)) {
              _dataList2.push(_chartList[i].amt_in_out); //지출
            } else {
              _dataList2.push("");
            }
          } //else
        } //for
      } //for

      //   // } else {
      //   //코딩해야됭 -> 머냐먼
      //   // }
    } else if (this.dataPeriod == "mon") {
      //   //   // console.log(today.getMonth());
      //   for (let k = fromMon; k <= toMon; k++) {
      //     this.mylabels.push(k + 1 + "");
      //     for (var i in _chartList) {
      //       if (_chartList[i].type_in_out == "02") {
      //         if (k + 1 == _chartList[i].dt_trd.substring(4, 6)) {
      //           _dataList1.push(_chartList[i].amt_in_out); //지출
      //         } else {
      //           _dataList1.push("");
      //         }
    } else {
      //         if (k + 1 == _chartList[i].dt_trd.substring(4, 6)) {
      //           _dataList2.push(_chartList[i].amt_in_out); //지출
      //         } else {
      //           _dataList2.push("");
      //         }
      //       } //else
      //     } //for
      //   } //for
      //   for (var k = this.fromMon; k <= this.toMon; k++) {
      //     this.mylabels.push(k + "월");
      //   }
      //   // } else if (this.dataPeriod == "week") {
    }
    debugger;
    _dataList1 = [
      "",
      "",
      "",
      "10285",
      "",
      "1111",
      "22222",
      "",
      "",
      "",
      "",
      "22221"
    ];
    _dataList2 = [
      "",
      "",
      "",
      "10285",
      "",
      "120000",
      "300000",
      "",
      "30000",
      "",
      "",
      "22221"
    ];
    this.$set(this.mydatasets[0], "data", _dataList1);
    this.$set(this.mydatasets[1], "data", _dataList2);
    console.log(this.mydatasets[1].data);
    // this.mydatasets[0].data = _dataList1;
    // this.mydatasets[1].data = _dataList2;
  },
  beforeUpdate() {},
  updated() {
    debugger;
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    testAAAA: function() {
      console.log("IAM HERE");
    },
    clickChart: function(event, el) {
      //아무것도 없는 곳을 클릭하면 undefined 떨어짐
      if (el[0] != undefined) {
        var index = el[0]._index;
        var label = el[0]._model.label;
        //사용해야 되는 데이터
        console.log(index + "////" + label);
      }
    }
  }
};
</script>
