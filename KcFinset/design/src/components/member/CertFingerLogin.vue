<template>
  <section id="content">
		<div class="container security-code">
			<div class="security-code-wrap security-finger">
				<p>
					지문을 입력해 주세요.
          <span id="err_message" v-if="cntFailFinger > 0"> {{ errMsg }} </span>
				</p>
				<div class="fingerprt-cert"></div>
			</div>
			<p class="link-txt"><router-link to="/member/certCodeLogin"><u>비밀번호를 입력 하시겠습니까?</u></router-link></p>
		</div>
	</section>
</template>

<script>

import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: 'helloWorld',
  data() {
    return {
      errMsg: '',
      j_username: this.$store.state.user.noPerson,
      j_password: "",
      cntFailFinger: this.$store.state.user.cntFailFinger,
    }
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
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  .memberMain {background-color: #283593; height: 100%;}
</style>
