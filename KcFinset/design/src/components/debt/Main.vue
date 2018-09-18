<template>
    <div>
        <button id="toggle" v-on:click="toggleTest()">토글!</button>
        <Component_test v-if="isTest"/>
        <Component_main v-else/>
    </div>

    
</template>

<script>
import router from "@/comm/router.js";
import Component_main from "./sub/Component_main";
import Component_test from "./sub/Component_main2";

export default {
  name: "DebtMain",
  data() {
    return {
      isTest: true
    };
  },
  components: {
    Component_main,
    Component_test
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
  methods: {
    toggleTest() {
      if (this.isTest == true) {
        this.isTest = false;
      } else {
        this.isTest = true;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
#toggle {
  position: fixed;
  top: 0px;
  right: 0px;
  z-index: 99;
}
</style>
