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

import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "finsetMain",
  data() {
    return {
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
  created() {},
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
