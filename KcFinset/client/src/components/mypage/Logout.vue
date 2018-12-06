<template>
  <section>
    <div class="loading success">
      안전하게 로그아웃 되었습니다
    </div>

    <div class="btn-wrap float">
      <a @click="reLogin()" class="solid box blue">다시 로그인하기</a>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
import Common from "./../../assets/js/common.js";

export default {
  name: "MypageLogout",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "로그아웃 완료";
  },
  created() {
    var _this = this;
    var frm = new FormData();
    _this.$http.post("/m/login/framePersonLogout.json", frm).then(response => {
      _this.$store.commit("LOGOUT");

      if (Constant.userAgent == "iOS") {
        Jockey.send("loginFlag", {
          flag: "N"
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.loginFlag("N");
      }
    });
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    reLogin: function() {
      this.$router.push("/home?hp=" + localStorage.getItem("hp"));
    }
  }
};
</script>