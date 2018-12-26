<template>
  <section>
    <div class="loading fail">
      일시적으로 서비스를 이용하실 수 없습니다.<br>잠시 후 다시 시도해주세요.
    </div>
    <div class="btn-wrap float">
      <a @click="goBack()" class="solid box blue">홈으로 가기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "error",
  data() {
    return {
      errMsg: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    goBack: function() {
      var historySize = history.length;
      if (historySize == undefined || historySize == 0) {
        if (Constant.userAgent == "Android") {
          window.Android.exitApp();
        }
      } else {
        this.$router.push("/main");
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
