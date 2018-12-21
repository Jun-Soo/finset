<template>
  <div id="wrapper">
    <!-- Content -->
    <section>
      <div class="certcode-wrap">
        <p class="text">
          비밀번호를 입력해주세요.
        </p>
        <p class="textred" v-if="cntFailPwd > 0"> {{ errMsg }} </p>

        <div class="pass-wrap">
          <input type="password" v-bind:style="classPass1" v-model="pw1" id="pass_number1" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass2" v-model="pw2" id="pass_number2" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass3" v-model="pw3" id="pass_number3" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass4" v-model="pw4" id="pass_number4" maxlength="1" readonly />
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
          <a v-if="ynFingerprint === 'Y'" v-on:click="gotoFingerPrint()" class="finger"></a>
          <a v-else disabled="disabled">&nbsp;</a>
          <a v-on:click="btnClick('0')">0</a>
          <a class="del" v-on:click="backClick()"></a>
        </div>
        <p class="text"><a @click="goCertPerson"><u>비밀번호를 재설정 하시겠습니까?</u></a></p>
      </div>
    </section>
  </div>

</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certCodeLogin",
  data() {
    return {
      errMsg: "",
      cntFailPwd: this.$store.state.user.cntFailPwd,
      cntFailFinger: this.$store.state.user.cntFailFinger,
      ynFingerprint: this.$store.state.user.ynFingerprint,
      username: this.$store.state.user.noPerson,
      password: "",
      hp: this.$store.state.user.hp,
      //class
      classPass1: "",
      classPass2: "",
      classPass3: "",
      classPass4: "",
      pw1: "",
      pw2: "",
      pw3: "",
      pw4: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    var _this = this;
    window.resultFingerPrint = this.resultFingerPrint;

    this.$store.state.title = "비밀번호 확인";
  },
  beforeMount() {},
  mounted() {
    this.errMsg =
      "비밀번호를 " + this.cntFailPwd + "회 실패한 이력이 있습니다.";
    if (this.$store.state.user.ynFingerprint == "Y") {
      if (Constant.userAgent == "Android") {
        window.Android.initFingerPrint();
      } else if (Constant.userAgent == "iOS") {
        Jockey.on("resultFingerPrint", function(param) {
          var result = false;
          if (param.result == 1) result = true;
          resultFingerPrint(param.result);
        });
        Jockey.send("initFingerPrint");
      }
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    goCertPerson: function() {
      let _this = this;
      this.errMsg = "비밀번호 재설정 화면으로 이동합니다.";
      this.$toast.center(this.errMsg);
      setTimeout(function() {
        _this.$router.push("/mypage/certPerson");
      }, 1000);
    },
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

        //validator
        if (this.$store.state.ynReload == "Y") {
          _this.passCheck();
        } else {
          _this.login();
        }
      }
    },
    gotoFingerPrint: function() {
      var _this = this;
      if (Constant.userAgent == "Android") {
        window.Android.closeFingerPrint();
      }
      _this.$router.push("/member/certFingerLogin");
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
    //비밀번호 틀린횟수 변경
    modifyPwdFailCnt: function(mode) {
      var _this = this;
      var data = {
        no_person: _this.username,
        cnt_fail_mode: mode,
        cnt_fail: _this.cntFailPwd
      };
      this.$http
        .get("/m/person/modifyPwdFailCnt.json", {
          params: data
        })
        .then(response => {
          var result = response.data;
          console.log("modifyPwdFailCnt :" + result);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    login: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("j_username", _this.username);
      formData.append("j_password", _this.password);
      this.$toast.center("u:" + _this.username + "p: " + _this.password);

      var querystring = require("querystring");
      var data = querystring.stringify({
        j_username: _this.username,
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
          console.log("login: " + response.data);
          if (response.data.result == "10") {
            //정상
            if (Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson", {
                noPerson: _this.username,
                phNum: _this.hp
              });
              Jockey.send("loginFlag", {
                flag: "Y"
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.setNoPerson(_this.username, _this.hp);
              window.Android.loginFlag("Y");
            }
            _this.$store.state.user.authToken = null;
            _this.$store.state.user.cntFailPwd = 0;
            _this.$store.state.user.cntFailFinger = 0;
            // _this.$store.state.user.cntFailFinger = 0;
            _this.$store.commit("LOGIN", response.data);

            _this.changeLoginDB();

            if (_this.$store.state.linkUrl) {
              _this.$router.push(_this.$store.state.linkUrl);
            } else {
              _this.$router.push("/main");
            }
          } else {
            _this.$store.state.isLoading = false;
            _this.initClassPass();
            _this.password = "";
            //비밀번호 틀린 누적횟수 증가
            console.log("login failed : ");
            _this.cntFailPwd += 1;
            if (_this.cntFailPwd < 5) {
              _this.errMsg =
                "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)";
            } else if (_this.cntFailPwd == 5) {
              //지문인식 5번 모두 틀린 경우
              _this.errMsg = "비밀번호 재설정 화면으로 이동합니다.";
              this.$toast.center(_this.errMsg);
              setTimeout(function() {
                _this.$router.push("/mypage/certPerson");
              }, 1000);
            }
            if (response.data.result == "21" || response.data.result == "22") {
              _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
            }
          }
        })
        .catch(e => {
          // this.$toast.center(ko.messages.error);
          this.$toast.center(e);
          this.initClassPass();
          _this.password = "";
        });
    },
    passCheck: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", _this.username);
      formData.append("pass_person", _this.password);

      this.$http
        .post("/m/login/loginChkCode.json", formData)
        .then(response => {
          console.log("passCheck :" + response.data.result);
          if (response.data.result == "00") {
            //정상
            if (Constant.userAgent == "iOS") {
              Jockey.send("closeWebView");
            } else if (Constant.userAgent == "Android") {
              window.Android.closeWebView();
              return false;
            }
          } else {
            _this.$store.state.isLoading = false;
            _this.initClassPass();
            _this.password = "";
            //비밀번호 틀린 누적횟수 증가
            console.log("login failed : ");
            _this.cntFailPwd += 1;
            if (_this.cntFailPwd < 5) {
              _this.errMsg =
                "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)";
            } else if (_this.cntFailPwd == 5) {
              //지문인식 5번 모두 틀린 경우
              // _this.errMsg = "비밀번호 재설정 화면으로 이동합니다.";
              // this.$toast.center(_this.errMsg);
              // setTimeout(function() {
              //   _this.$router.push("/mypage/certPerson");
              // }, 1000);
            }
            if (response.data.result == "21" || response.data.result == "22") {
              _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
            }
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //로그인값 dB 변경
    changeLoginDB: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("yn_logout", "N");
      formData.append("yn_use", "Y");

      _this.$http
        .post("/m/person/modifyYnUseAndLogout.json", formData)
        .then(response => {
          var result = response.data;
          var noPerson = result.returnData;
          if (result.result != "00") {
            this.$toast.center(result.messages);
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    /***
     * Native Call function
     **/
    resultFingerPrint: function(result) {
      var _this = this;

      if (result == true || result == 1) {
        //지문인식 성공
        if (ConstAant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }

        if (_this.$store.state.ynReload == "Y") {
          if (Constant.userAgent == "Android") {
            window.Android.closeWebView();
          } else if (Constant.userAgent == "iOS") {
            Jockey.send("closeWebView");
          }
          _this.$toast.center("???");
          return false;
        } else {
          if (Constant.userAgent == "iOS") {
            Jockey.send("loginFlag", {
              flag: "Y"
            });
          } else if (Constant.userAgent == "Android") {
            window.Android.loginFlag("Y");
          }

          _this.password = _this.$store.state.user.authToken;
          _this.$toast.center(_this.password);
          // setTimeout(function() {
          _this.login();
          // }, 500);
        }
      } else {
        // this.$toast.center(loginTrue);
        //지문 틀린 누적횟수 증가

        _this.cntFailFinger += 1;
        _this.$store.state.user.cntFailFinger = _this.cntFailFinger;
        _this.$dialogs.alert(_this.cntFailFinger, Constant.options);
        // _this.modifyPwdFailCnt("finger", _this.cntFailFinger);
        // _this.$store.state.isLoading = false;
        if (_this.cntFailFinger < 5) {
          _this.errMsg = "다시 시도해 주세요. (" + _this.cntFailFinger + "/5)";
          return false;
          _this.fingerSVG.reset();
        } else if (_this.cntFailFinger >= 5) {
          //지문인식 5번 모두 틀린 경우
          _this.errMsg = "지문이 비활성화 됩니다.";
          this.$toast.center(_this.errMsg);
          if (Constant.userAgent == "Android") {
            window.Android.closeFingerPrint();
          }

          var data = { no_person: _this.username, yn_fingerprint: "N" };
          _this.$http
            .get("/m/person/modifyFingerPrint.json", {
              params: data
            })
            .then(response => {
              _this.$store.state.user.ynFingerprint = "N";
              _this.$router.push("/member/certCodeLogin");
            })
            .catch(e => {
              _this.$toast.center(ko.messages.error);
              // _this.$toast.center(e);
            });
        }
        return false;
      }
    }
  }
};
</script>