<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="certcode-wrap">
        <p class="text">
          {{ certMessage }}
        </p>
        <div class="pass-wrap">
          <input type="password" v-bind:style="classPass1" name="pass_number" v-model="classPass1" id="pass_number1" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass2" name="pass_number" v-model="classPass2" id="pass_number2" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass3" name="pass_number" v-model="classPass3" id="pass_number3" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass4" name="pass_number" v-model="classPass4" id="pass_number4" maxlength="1" readonly />
        </div>
        <div class="number">
          <button v-on:click="btnClick('1')">1</button>
          <button v-on:click="btnClick('2')">2</button>
          <button v-on:click="btnClick('3')">3</button>
          <button v-on:click="btnClick('4')">4</button>
          <button v-on:click="btnClick('5')">5</button>
          <button v-on:click="btnClick('6')">6</button>
          <button v-on:click="btnClick('7')">7</button>
          <button v-on:click="btnClick('8')">8</button>
          <button v-on:click="btnClick('9')">9</button>
          <button disabled="disabled">&nbsp;</button>
          <button v-on:click="btnClick('0')">0</button>
          <button class="del" v-on:click="backClick()"></button>
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
  name: "certCode",
  data() {
    return {
      errMsg: "",
      certMessage: "",
      noPerson: this.$store.state.user.noPerson,
      password: "",
      tempPwd: "",
      chkPwd: false,
      chkFingerPrint: "",
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
    window.resultCheckFingerPrint = this.resultCheckFingerPrint;
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;

    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
      window.Android.checkFingerPrint();
    } else if (Constant.userAgent == "iOS") {
      //지문인식 가능여부 체크 결과 콜백 이벤트
      Jockey.on("resultCheckFingerPrint", function(param) {
        this.resultCheckFingerPrint(param.result);
      });
      Jockey.send("checkFingerPrint");
    }
  },
  beforeMount() {},
  mounted() {
    if (!localStorage.getItem("tempPwd")) {
      this.$store.state.title = "비밀번호 설정";
      this.certMessage = "비밀번호를 입력해주세요.";
    } else {
      this.$store.state.title = "비밀번호 확인";
      this.certMessage = "비밀번호를 다시 한번 입력해주세요.";
      this.tempPwd = localStorage.getItem("tempPwd");
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    initClassPass: function() {
      var _this = this;
      _this.classPass1 = "";
      _this.classPass2 = "";
      _this.classPass3 = "";
      _this.classPass4 = "";
    },
    btnClick: function(val) {
      var _this = this;
      var type = "confirmPage";
      if(_this.password.length < 4){
        _this.password += val;
      }
      if (_this.password.length > 0) _this.classPass1 = "active";
      if (_this.password.length > 1) _this.classPass2 = "active";
      if (_this.password.length > 2) _this.classPass3 = "active";
      if (_this.password.length > 3) {
        _this.classPass4 = "active";

        if (!_this.tempPwd) {
          localStorage.setItem("tempPwd", _this.password);
        } else {
          if (_this.tempPwd != _this.password) {
            //앞 비밀번호와 같은지 확인
            _this.password = "";
            _this.backClick();
            this.$toast.center(ko.messages.notMatchPwd);
            return;
          } else {
            type = "changePwd";
          }
        }
        this.nextPage(type); //confirmPage 일경우 redirect , 재확인일경우 changePwd
      }
    },
    backClick: function() {
      var _this = this;
      this.initClassPass();
      _this.password = _this.password.substr(0, _this.password.length - 1);
      if (_this.password.length > 0) _this.classPass1 = "active";
      if (_this.password.length > 1) _this.classPass2 = "active";
      if (_this.password.length > 2) _this.classPass3 = "active";
      if (_this.password.length > 3) _this.classPass4 = "active";
    },
    nextPage: function(type) {
      var _this = this;
      if (type == "confirmPage") {
        this.$store.state.proxyUrl = "/member/certCode";
        this.$router.push("/proxy");
        return;
      } else {
        var data = {
          no_person: _this.noPerson,
          pass_person: _this.password
        };
        this.$http
          .get("/m/person/changePwd.json", {
            params: data
          })
          .then(response => {
            var result = response.data;
            console.log(result);
            if (result.result == "00") {
              if (_this.chkFingerPrint == "Y") {
                setTimeout(function() {
                  _this.$router.push("/member/certFinger");
                }, 2000);
              } else {
                if (
                  Constant.userAgent == "Android" &&
                  localStorage.getItem("site") != "REAL"
                ) {
                  this.checkExistCert();
                } else {
                  this.login();
                }
              }
              this.$toast.center("비밀번호설정이 완료 되었습니다.");
            } else {
              this.$toast.center(result.message);
              return false;
            }
          })
          .catch(e => {
            this.$toast.center(ko.messages.error);
          });
      }
    },
    login: function() {
      var _this = this;

      var querystring = require("querystring");
      var data = querystring.stringify({
        j_username: _this.noPerson,
        j_password: _this.password
      });
      this.$http
        .post(_this.$store.state.loginPath, data, {
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
    /***
     * Native Call function
     ***/
    resultCheckFingerPrint: function(result) {
      console.log(result);
      if (result == true || result == 1) {
        this.chkFingerPrint = "Y";
      } else {
        this.chkFingerPrint = "N";
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      if (isCert) {
        // 공인인증서가 있을 경우
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
    resultCheckPasswordCert: function(dn, cn) {
      // 금융정보제공동의서 확인여부 체크 필요
      this.$router.push({ name: "scrapSelFcLink", params: { dn: dn, cn: cn } });
    }
  }
};
</script>