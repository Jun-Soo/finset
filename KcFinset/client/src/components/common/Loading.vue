<template>
  <div class="loading">
    <span v-html=normalMessage></span><br><small v-html=smallMessage></small>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "loading",
  data() {
    return {
      normalMessage: "",
      smallMessage: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    var params = Common.getParams();

    console.log(params);
    if (params.msg1) {
      this.normalMessage = decodeURI(params.msg1);
    }
    if (params.msg2) {
      this.smallMessage = decodeURI(params.msg2);
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
<style lang="scss">
</style>