<template>
  <div id="menu-fixed-bottom">
    <ul class="menu-list">
      <li id="footer_credit" class=""><router-link to='/creditMain'>신용관리</router-link></li>
      <li id="footer_debt"><a class="menu-debt">부채관리</a></li>
      <li id="footer_loan" class=""><a class="menu-loan">상품조회</a></li>
      <li id="footer_mypage"><a class="menu-mypage">마이페이지</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'FinsetBottom',
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
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
