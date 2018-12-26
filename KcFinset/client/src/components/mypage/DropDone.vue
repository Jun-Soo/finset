<template>
  <section>
    <div class="loading success">
      <em>서비스 탈퇴 완료</em><br>이용해 주셔서 감사합니다
    </div>

    <div v-if="isAndroid" class="btn-wrap float">
      <a @click="appQuit" class="solid box blue">앱 종료하기</a>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";

export default {
  name: "MypageDropDone",
  data() {
    return {
      isAndroid: true
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "other";
    this.$store.state.title = "서비스 탈퇴";
  },
  created() {
    localStorage.clear();
    this.$store.state.isLoggedIn = false;
    this.$store.state.accessToken = null;
    this.$store.state.site = null;
    if (Constant.userAgent == "Android") {
      this.isAndroid = true;
    } else {
      this.isAndroid = false;
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    appQuit: function() {
      window.Android.exitApp(); //앱종료
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
