<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="cert-finger">
        <p class="text">지문인증을 사용 하시겠습니까?</p>
      </div>

      <div class="btn-wrap col2">
        <a v-on:click="chkFinger('N')" class="btn-stroke">아니오</a>
        <a v-on:click="chkFinger('Y')" class="btn-solid">네</a>
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
      password: localStorage.getItem("tempPwd"),
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
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.$store.state.title = "지문인증 설정 (5/7)";
    // this.$store.state.header.type = "sub";

    window.resultFingerPrint = this.resultFingerPrint;
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
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
      this.$http
        .post("/check/j_spring_security_check", data, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
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
    chkFinger: function(gubun) {
      let _this = this;
      if (gubun == "Y") {
        _this.ynFingerprint = "Y";
        if (Constant.userAgent == "Android") {
          window.Android.initFingerPrint();
        } else if (Constant.userAgent == "iOS") {
          //지문인식 결과 콜백 이벤트
          Jockey.on("resultFingerPrint", function(param) {
            resultFingerPrint(param.result);
          });
          Jockey.send("initFingerPrint");
        }
      } else if (gubun == "N") {
        _this.ynFingerprint = "N";
      }

      var data = {
        no_person: _this.noPerson,
        yn_fingerprint: _this.ynFingerprint
      };
      this.$http
        .get("/m/person/modifyFingerPrint.json", {
          params: data
        })
        .then(response => {
          var result = response.data;
          if (result.result == "00") {
            this.$toast.center("지문 로그인 설정이 완료되었습니다.");
            if (
              Constant.userAgent == "Android" ||
              Constant.userAgent == "IOS"
            ) {
              _this.checkExistCert();
            } else {
              setTimeout(function() {
                _this.login();
              }, 2000);
            }
          } else {
            this.$toast.center(result.message);
            return false;
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    // 공인인증서 유무 체크
    checkExistCert: function() {
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert", function(param) {
          var iscert = false;
          if (param.isCert == 1) iscert = true;
          resultCheckCert(iscert);
        });
        Jockey.send("checkExistCert");
      } else if (Constant.userAgent == "Android") {
        window.Android.checkExistCert();
      }
    },
    //자동스크래핑 가능 금융사 조회
    frmFcCertList: function() {
      var noPerson = this.$store.state.user.noPerson;
      var nmPerson = this.$store.state.user.nmPerson;
      var bankCode = this.$store.state.bankCode;
      var cardCode = this.$store.state.cardCode;

      if (Constant.userAgent == "iOS") {
        /* Jockey.on("frmFcListNextFromMobile" , function(param) {
          frmFcListNextFromMobile();
        });
        Jockey.send("checkAvaliableScrapList" , {
          noPerson : noPerson,
          bankCode : bankCode
        }); */
        //do nothing
      } else if (Constant.userAgent == "Android") {
        window.Android.checkAvaliableScrapList(
          noPerson,
          bankCode,
          cardCode,
          nmPerson
        );
      }
    },
    /***
     * Native Call function
     **/
    resultFingerPrint: function(result) {
      console.log(result);
      if (result == true || result == 1) {
        if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }
      } else {
        return false;
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      if (isCert) {
        // 공인인증서가 있을 경우
        //this.frmFcCertList();
        if (Constant.userAgent == "iOS") {
          Jockey.on("checkPasswordCert", function(param) {
            resultCheckPasswordCert();
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
    //공인인증서 비밀번호 체크 결과 (모바일에서 호출)
    resultCheckPasswordCert: function(dn, cn) {
      // 금융정보제공동의서 확인여부 체크 필요
      this.$router.push({ name: "scrapSelFcLink", params: { dn: dn, cn: cn } });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.memberMain {
  background-color: #283593;
  height: 100%;
}
</style>