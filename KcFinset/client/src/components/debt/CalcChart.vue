<template>
  <div style="margin-top:100px;">
    <chartjs-line :labels="mylabels" :datasets="mydatasets"></chartjs-line>
    <!-- <canvas id="mycanvas" count="2"></canvas> -->
    <!-- <chartjs-line :fill="myboolean" :backgroundcolor="mybackgroundcolor" :bordercolor="mybordercolor" :pointborderwidth="mywidth" :pointbordercolor="mypointbordercolor" :pointhoverborderwidth="hoverwidth" :pointhoverbackgroundcolor="hoverbackgroundcolor" :pointhoverbordercolor="hoverbordercolor" :data="first_data" target="mycanvas"></chartjs-line> -->
    <!-- <chartjs-line :datalabel="second_label" :data="second_data" target="mycanvas"></chartjs-line> -->
  </div>
</template>

<script>
export default {
  name: "ChartExamSingleLine",
  data() {
    return {
      mylabels: [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July"
      ],
      mydatasets: [
        {
          label: "My first dataset",
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
          data: [65, 59, 80, 81, 56, 55, 40],
          spanGaps: false
        },
        {
          label: "My second dataset",
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
          data: [0, 20, 40, 41, 66, 25, 80],
          spanGaps: false
        }
      ]
      // myboolean: true,
      // mybackgroundcolor: "rgba(75,192,192,0.1)",
      // mybordercolor: "#00c853",
      // mywidth: 4,
      // mypointbordercolor: "#f35009",
      // hoverwidth: 3,
      // hoverbackgroundcolor: "#636b6f",
      // hoverbordercolor: "#ffd663",
      // first_label: "first",
      // first_data: [50, 10, 0, 80],
      // second_label: "second",
      // second_data: [50, 40, 90, 60]
    };
  },
  components: {}
};
</script>
