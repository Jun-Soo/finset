<template>
  <div id="menu-fixed-bottom">
    <ul class="menu-list">
      <li id="footer_credit" v-bind:class="{active: creditActive}" v-on:click="activeChange('credit')"><router-link to='/credit/main' class="menu-credit">신용관리</router-link></li>
      <li id="footer_debt" v-bind:class="{active: debtActive}" v-on:click="activeChange('debt')"><router-link to='/debt/main' class="menu-debt">부채관리</router-link></li>
      <li id="footer_goods" v-bind:class="{active: loanActive}" v-on:click="activeChange('loan')"><router-link to='/goods/main' class="menu-loan">상품조회</router-link></li>
      <li id="footer_mypage" v-bind:class="{active: mypageActive}" v-on:click="activeChange('mypage')"><router-link to='/mypage/main' class="menu-mypage">마이페이지</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'finsetBottom',
  data() {
    return {
      creditActive: false,
      debtActive: false,
      loanActive: false,
      mypageActive: false
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
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
    activeChange: function(type) {

      this.creditActive = false, this.debtActive = false, this.loanActive = false, this.mypageActive = false
      if(type == 'credit') this.creditActive = true
      if(type == 'debt') this.debtActive = true
      if(type == 'loan') this.loanActive = true
      if(type == 'mypage') this.mypageActive = true
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
