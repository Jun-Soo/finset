<template>
  <section>
    <div class="loading share">
      공인인증서를 등록하여<br>흩어져 있는 금융정보를<br>확인해보세요.<br><em class="pt10">연동하시겠습니까?</em>
    </div>

    <!-- <div class="btn-wrap float">
      <a href="#" class="solid box blue">확인</a>
    </div> -->
    <div class="btn-wrap col2">
      <a class="btn-stroke" @click="clickNo()">아니요</a>
      <a class="btn-solid" @click="clickYes()">예</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "ScrapSelectFcLink",
  data() {
    return {
      noPerson: this.$store.state.user.noPerson,
      password: localStorage.getItem("tempPwd")
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "";
  },
  created() {
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    login: function() {
      var _this = this;
      var querystring = require("querystring");
      var data = querystring.stringify({
        j_username: _this.noPerson,
        j_password: _this.password
      });
      this.$store.state.isLoading = true;
      this.$http
        .post(_this.$store.state.loginPath, data, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
          //console.log("login result");
          if (response.data.result == "10") {
            //정상
            localStorage.removeItem("tempPwd");
            _this.$store.commit("LOGIN", response.data);
            _this.$router.push("/main");
          } else {
            this.$toast.center(ko.messages.loginErr);
            return;
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    clickYes: function() {
      this.checkExistCert();
    },
    clickNo: function() {
      this.login();
    },
    // 공인인증서 유무 체크
    checkExistCert: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert", function(param) {
          var iscert = "false";
          if (param.isCert == 1) iscert = "true";
          _this.resultCheckCert(iscert);
          Jockey.off("resultCheckCert");
        });
        Jockey.send("checkExistCert");
      } else if (Constant.userAgent == "Android") {
        window.Android.checkExistCert();
      }
    },
    /***
     * Native Call function
     ***/
    resultCheckFingerPrint: function(result) {
      //console.log(result);
      if (result == true || result == 1) {
        this.chkFingerPrint = "Y";
      } else {
        this.chkFingerPrint = "N";
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      //console.log("isCert : " + isCert);
      var _this = this;
      if (isCert == "true") {
        // 공인인증서가 있을 경우
        if (Constant.userAgent == "iOS") {
          Jockey.on("resultCheckPasswordCert", function(param) {
            _this.resultCheckPasswordCert(param.dn, param.cn);
            Jockey.off("resultCheckPasswordCert");
          });
          Jockey.send("checkPasswordCert", {
            noPerson: this.$store.state.user.noPerson,
            nmPerson: this.$store.state.user.nmPerson
          });
          //do nothing
        } else if (Constant.userAgent == "Android") {
          window.Android.checkPasswordCert(
            this.$store.state.user.noPerson,
            this.$store.state.user.nmPerson
          );
        }
      } else {
        // 공인인증서가 없을 경우
        this.$toast.center("공인인증서가 없습니다.");
        this.login();
      }
    },
    resultCheckPasswordCert: function(dn, cn) {
      // 금융정보제공동의서 확인여부 체크 필요
      this.$router.push({
        name: "scrapSelFcLink",
        params: { isSingle: false, dn: dn, cn: cn }
      });
    },
    // Native에서 건너뛰기 눌렀을 경우 호출
    resultCheckAvaliableScrapList: function() {
      this.login();
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
