<template>
  <div>
    <chartjs-bar :labels="mylabels" :datasets="mydatasets" :scalesdisplay="false" :option="options"></chartjs-bar>
  </div>
</template>



<script type="text/javascript">
import Common from "@/assets/js/common.js";
import moment from "moment";

export default {
  name: "ConsumeSettlement",
  data() {
    return {
      mylabels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
      mydatasets: [
        {
          // label: "My First dataset",
          backgroundColor: "#e52638",
          borderColor: "#e52638",
          borderWidth: 1,
          data: [65, 59, 80, 81, 56, 55, 40, 20, 56, 55, 40, 20]
        },
        {
          backgroundColor: "#62c1d0",
          borderColor: "#62c1d0",
          borderWidth: 1,
          data: [20, 50, 20, 41, 26, 85, 20, 20, 50, 20, 41, 26]
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
    console.log(this.chartList);
    console.log(moment(this.dt_from).weekday(0));
    // console.log(Common.formatDat(this.dt_from));
    debugger;
    if (this.dataPeriod == "yr") {
      if (this.dt_from.getFullYear() === this.dt_to.getFullYear()) {
        this.fromMon = this.dt_from.getMonth();
        this.toMon = this.dt_to.getMonth();
        for (var k = this.fromMon; k <= this.toMon; k++) {
          this.mylabels.push(k + "월");
        }
      } else {
        //코딩해야됭 -> 머냐먼
      }
    } else if (this.dataPeriod == "mon") {
      //   // console.log(today.getMonth());
      for (var k = this.fromMon; k <= this.toMon; k++) {
        this.mylabels.push(k + "월");
      }
      // } else if (this.dataPeriod == "week") {
    }

    // for(var k in this.chartList){
    //   this.mylabels.push(this.chartList[k].);
    // }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    testAAAA: function() {
      console.log("IAM HERE");
    }
  }
};
</script>
