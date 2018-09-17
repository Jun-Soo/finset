<template>
  <div class="data-none">
    <em>죄송합니다.</em>
    <p>일시적으로 서비스를 이용하실 수 없습니다.<br>잠시 후 다시 접속해주세요.</p>
    <div class="btn-area">
      <a class="btn btn-outline" v-on:click="goBack()">뒤로가기</a>
    </div>
</div>
</template>

<script>

import Common from './../../assets/js/common.js'
import Constant from './../../assets/js/constant.js'

export default {
  name: 'error',
  data() {
    return {
      errors: [],
      errMsg: ''
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
    if(Constant.userAgent == "Android") {
      window.Android.setEndApp('Y')
    }
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
    goBack: function() {
      var historySize = history.length
      if(historySize == undefined || historySize == 0) {
          if(userAgent == "Android") {
              window.Android.exitApp();
          }
      } else {
          history.back()
      }
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
