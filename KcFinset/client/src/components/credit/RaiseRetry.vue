<template>
  <section>
    <div class="loading fail">
      <em v-html=message1></em>
    </div>
    <div class="btn-wrap col2">
      <a class="btn-solid" @click="clickRetry()">재전송</a>
      <a href="#" class="btn-stroke" @click="clickGohome()">홈으로</a>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
export default {
  name: "CreditRaiseRetry",
  data() {
    return {
      message1: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "";
  },
  created() {
    var params = Common.getParams();

    console.log(params);
    if (params.msg1) {
      this.message1 = decodeURI(params.msg1);
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickRetry: function() {
      if (Constant.userAgent == "iOS") {
        Jockey.send("creditRatingUpgradeRetry");
      } else if (Constant.userAgent == "Android") {
        window.Android.creditRatingUpgradeRetry();
      }
    },
    clickGohome: function() {
      if (Constant.userAgent == "iOS") {
        Jockey.send("closeWebView");
      } else if (Constant.userAgent == "Android") {
        window.Android.closeWebView();
      }
      this.$router.push("/credit/raiseMain");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
