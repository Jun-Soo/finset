<template>
  <main>
    <FinsetHeader></FinsetHeader>
      <div id='wrapper'>
        <div id='content'>
          <h1>FINSET MAIN</h1>
        </div>
      </div>
    <FinsetBottom></FinsetBottom>
  </main>
</template>

<script>
import FinsetHeader from "./../common/FinsetHeader";
import FinsetBottom from "./../common/FinsetBottom";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "FinsetMain",
  data() {
    return {
      errors: [],
      errMsg: ''
    };
  },
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom
  },
  // computed () {
  // },
  beforeCreate() {},
  created() {
    if (Constant.userAgent == "Android") {
      // 스플래시 ON
        window.Android.splash("N");
    } else if (Constant.userAgent == "iOS") {
      Jockey.send("splashView", {
        yn_splash: "N"
      });
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
h1 {
  text-align: center;
  color: #666;
  font-size: 5em;
} 
</style>
