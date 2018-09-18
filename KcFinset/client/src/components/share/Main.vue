<template>
  
</template>

<script>

// import Common from './../../assets/js/common.js'
import router from '@/comm/router.js'

export default {
  name: 'ShareMain',
  data () {
    return {
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created () {
    this.getShareInfoMain()
  },
  beforeMount() {
  },
  mounted () {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy () {
  },
  destroyed() {
  },
  methods: {
    // 공유관리 조회
    getShareInfoMain () {
      var vm = this
      this.$http.get('/api/customercenter/ShareInfoMain.json', {
        params: {}
      }).then(function (response) {
        // var baseInfo = response.data.baseInfo
        // console.log(JSON.stringify(baseInfo));

        // vm.creditGrade(baseInfo.grade_credit);
        // vm.ratingCredit = baseInfo.rating_credit
      })
    },
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
