<template>
  <div id="wrapper">
			<!-- Header -->
			<header id="header">
				<div class="input-group">
					<div class="input-group-btn blind">
						<button type="button" class="ui-nav nav-back" onclick="">뒤로가기</button>
					</div>
					<h1>로그아웃 완료</h1>
				</div>
			</header>
			<section id="content">
				<div class="container">
					<div class="msg-block">
						<span class="msg-img logout-complete"></span>
						<strong>안전하게 로그아웃 되었습니다.</strong>
					</div>
				</div>
				<div class="btn-fixed-bottom affix-bottom" id="">
					<a role="button" class="btn btn-lg btn-block btn-primary" v-on:click="reLogin();">다시 로그인하기</a>
				</div>
			</section>
		</div>
</template>

<script>

import Constant from "./../../assets/js/constant.js";
import Common from './../../assets/js/common.js'

export default {
  name: 'helloWorld',
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
    _this.$store.commit('LOGOUT')
    if(Constant.userAgent == "Android") {
      window.Android.setEndApp('Y');
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
    reLogin: function() {
      this.$router.push("/home?hp="+localStorage.getItem('hp'));
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
