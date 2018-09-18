<template>
</template>

<script>

import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: 'proxyPage',
  data() {
    return {
      errMsg: ''
    }
  },
  component: {
  },
  computed: {
  },
  beforeCreate() {
  },
  created() {
    this.$router.push(this.$store.state.proxyUrl)
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
