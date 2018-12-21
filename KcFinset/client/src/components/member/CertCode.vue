<template>
  <div v-if="seen">
    <!-- Content -->
    <section id="content">
      <div class="certcode-wrap">
        <p class="text">
          {{ certMessage }}
        </p>
        <div class="pass-wrap">
          <input type="password" v-bind:style="classPass1" name="pass_number" v-model="pw1" id="pass_number1" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass2" name="pass_number" v-model="pw2" id="pass_number2" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass3" name="pass_number" v-model="pw3" id="pass_number3" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass4" name="pass_number" v-model="pw4" id="pass_number4" maxlength="1" readonly />
        </div>
        <div class="number">
          <a v-on:click="btnClick('1')">1</a>
          <a v-on:click="btnClick('2')">2</a>
          <a v-on:click="btnClick('3')">3</a>
          <a v-on:click="btnClick('4')">4</a>
          <a v-on:click="btnClick('5')">5</a>
          <a v-on:click="btnClick('6')">6</a>
          <a v-on:click="btnClick('7')">7</a>
          <a v-on:click="btnClick('8')">8</a>
          <a v-on:click="btnClick('9')">9</a>
          <a disabled="disabled">&nbsp;</a>
          <a v-on:click="btnClick('0')">0</a>
          <a class="del" v-on:click="backClick()"></a>
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
      classPass4: "",
      pw1: "",
      pw2: "",
      pw3: "",
      pw4: "",
      seen: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    var _this = this;
    window.resultCheckFingerPrint = this.resultCheckFingerPrint;
    // window.resultCheckCert = this.resultCheckCert;
    // window.resultCheckPasswordCert = this.resultCheckPasswordCert;
    // window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;

    if (Constant.userAgent == "Android") {
      window.Android.checkFingerPrint();
    } else if (Constant.userAgent == "iOS") {
      //지문인식 가능여부 체크 결과 콜백 이벤트
      Jockey.on("resultCheckFingerPrint", function(param) {
        resultCheckFingerPrint(param);
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
    this.seen = true;
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
      _this.pw1 = "";
      _this.pw2 = "";
      _this.pw3 = "";
      _this.pw4 = "";
    },
    btnClick: function(val) {
      var _this = this;
      var type = "confirmPage";

      if (_this.password.length < 4) {
        _this.password += val;
      }
      if (_this.password.length > 0) {
        _this.classPass1 = "border-color: #111";
        _this.pw1 = val;
      }

      if (_this.password.length > 1) {
        _this.classPass2 = "border-color: #111";
        _this.pw2 = val;
      }
      if (_this.password.length > 2) {
        _this.classPass3 = "border-color: #111";
        _this.pw3 = val;
      }
      if (_this.password.length > 3) {
        _this.classPass4 = "border-color: #111";
        _this.pw4 = val;

        if (!_this.tempPwd) {
          localStorage.setItem("tempPwd", _this.password);
        } else {
          if (_this.tempPwd != _this.password) {
            //앞 비밀번호와 같은지 확인
            _this.password = "";
            _this.initClassPass();
            // _this.backClick();
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
      if (_this.password.length == 4) {
        _this.pw4 = "";
        _this.classPass4 = "";
      } else if (_this.password.length == 3) {
        _this.pw3 = "";
        _this.classPass3 = "";
      } else if (_this.password.length == 2) {
        _this.pw2 = "";
        _this.classPass2 = "";
      } else if (_this.password.length == 1) {
        _this.pw1 = "";
        _this.classPass1 = "";
      }
      _this.password = _this.password.substr(0, _this.password.length - 1);
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
            if (result.result == "00") {
              if (_this.chkFingerPrint == "Y") {
                setTimeout(function() {
                  _this.$router.push("/member/certFinger");
                }, 2000);
              } else {
                if (
                  Constant.userAgent == "Android" ||
                  Constant.userAgent == "iOS"
                ) {
                  //_this.checkExistCert();
                  _this.$router.push("/scrap/fcLink");
                } else {
                  _this.login();
                }
              }
              this.$toast.center("비밀번호설정이 완료 되었습니다.");
            } else {
              this.$toast.center(result.message);
              return false;
            }
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
      this.$store.state.isLoading = true;
      this.$http
        .post(_this.$store.state.loginPath, data, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
          console.log("login result");
          if (response.data.result == "10") {
            //정상
            localStorage.removeItem("tempPwd");
            _this.$store.commit("LOGIN", response.data);
            _this.$router.push("/main");
          } else {
            this.$toast.center(ko.messages.loginErr);
            return;
          }
        });
    },
    /***
     * Native Call function
     ***/
    resultCheckFingerPrint: function(res, result) {
      let isFingerPrintRegistered = "";
      let _result = "";

      if (Constant.userAgent == "Android") {
        isFingerPrintRegistered = res; //지문이 등록되어있지만, 지문data가 없는 경우
        _result = result;
      } else if (Constant.userAgent == "iOS") {
        isFingerPrintRegistered = res.isFingerPrintRegistered; //지문이 등록되어있지만, 지문data가 없는 경우
        _result = res.result;
      }

      if (
        (_result == true || _result == 1) &&
        isFingerPrintRegistered == true
      ) {
        this.chkFingerPrint = "Y";
      } else {
        this.chkFingerPrint = "N";
      }
    }
  }
};
</script>