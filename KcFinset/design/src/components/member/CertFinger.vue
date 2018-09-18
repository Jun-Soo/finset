<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
		<div class="container security-code">
			<div class="security-code-wrap security-finger">
				<div class="fingerprt-cert"></div>
				<p>
					지문인증을 사용하시겠습니까?
				</p>
			</div>
			<div class="btn-fixed-bottom affix-bottom">
				<div class="col-xs-6">
					<button type="button" class="btn btn-lg btn-default btn-block" v-on:click="chkFinger('N')">아니오</button>
				</div>
				<div class="col-xs-6">
					<button type="button" class="btn btn-lg btn-primary btn-block" v-on:click="chkFinger('Y')">예</button>
				</div>
			</div>
		</div>
	</section>
  	<!-- //Content -->
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certFinger",
  data() {
    return {
      errMsg: "",
      certMessage: "",
      noPerson: this.$store.state.user.noPerson,
      j_password: localStorage.getItem('tempPwd'),
      tempPwd: "",
      chkPwd: false,
      ynFingerprint: "",
      //class
      classPass1: "",
      classPass2: "",
      classPass3: "",
      classPass4: ""
    };
  },
  component: {
  },
  computed: {
  },
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
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  .memberMain {background-color: #283593; height: 100%;}
</style>
