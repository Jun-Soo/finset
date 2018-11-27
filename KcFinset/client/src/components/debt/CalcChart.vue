<template>
  <div style="margin-top:100px;">
    <canvas id="mycanvas" count="2"></canvas>
    <chartjs-line :fill="myboolean" :backgroundcolor="mybackgroundcolor" :bordercolor="mybordercolor" :pointborderwidth="mywidth" :pointbordercolor="mypointbordercolor" :pointhoverborderwidth="hoverwidth" :pointhoverbackgroundcolor="hoverbackgroundcolor" :pointhoverbordercolor="hoverbordercolor" target="mycanvas"></chartjs-line>
    <chartjs-line :datalabel="second_label" :data="second_data" target="mycanvas"></chartjs-line>
  </div>
</template>

<script>
export default {
  name: "ChartExamSingleLine",
  data() {
    return {
      myboolean: true,
      mybackgroundcolor: "rgba(75,192,192,0.1)",
      mybordercolor: "#00c853",
      mywidth: 4,
      mypointbordercolor: "#f35009",
      hoverwidth: 3,
      hoverbackgroundcolor: "#636b6f",
      hoverbordercolor: "#ffd663",
      second_label: "second",
      second_data: [50, 40, 90, 60]
    };
  },
  components: {}
};
</script>
