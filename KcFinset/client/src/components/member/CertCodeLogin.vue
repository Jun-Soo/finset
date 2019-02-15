<template>
  <div id="wrapper">
    <!-- Content -->
    <section v-if="seen">
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
      pw4: "",
      showFingerprintButton: false,
      seen: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    window.resultFingerPrint = this.resultFingerPrint;
    window.resultLoginKeypad = this.resultLoginKeypad;
    window.goToFingerprint = this.goToFingerprint;

    this.$store.state.title = "비밀번호 확인";

    if (this.$store.state.user.ynFingerprint == "Y") {
      this.showFingerprintButton = true;
    }

    if (Constant.userAgent == "Android") window.Android.setEndApp("Y");
  },
  beforeMount() {},
  mounted() {
    var _this = this;
    if (
      (Constant.userAgent == "iOS" && Constant.userAppVersion == "1.1.7") ||
      (Constant.userAgent == "Android" && Constant.userAppVersion == "1.1.4")
    ) {
      this.errMsg = "";
      if (this.cntFailPwd) {
        this.errMsg =
          "비밀번호를 " + this.cntFailPwd + "회 실패한 이력이 있습니다.";
      }
      if (Constant.userAgent == "iOS") {
        Jockey.off("goToFingerprint");
        Jockey.on("goToFingerprint", function(param) {
          _this.goToFingerprint();
        });
      }
      //console.log("showLoginKeypad");
      this.showLoginKeypad();
    } else {
      if (this.$store.state.user.ynFingerprint == "Y") {
        if (Constant.userAgent == "Android") {
          window.Android.initFingerPrint();
        } else if (Constant.userAgent == "iOS") {
          Jockey.off("resultFingerPrint");
          Jockey.on("resultFingerPrint", function(param) {
            var result = false;
            if (param.result == 1) result = true;
            _this.resultFingerPrint(param.result);
          });
          Jockey.send("initFingerPrint");
        }
      }
      this.seen = true;
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    showLoginKeypad: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        Jockey.send("showLoginKeypad", {
          keypadType: "numeric",
          minInputLength: 4,
          maxInputLength: 4,
          subTitle: "비밀번호 4자리를 입력해주세요.",
          placeholderText: "숫자를 입력하세요.",
          message: _this.errMsg,
          exitOnBackPressed: "true",
          showFingerprintButton: _this.showFingerprintButton
        });
        //보안키패드 결과값 수신 콜백 이벤
        Jockey.off("resultLoginKeypad");
        Jockey.on("resultLoginKeypad", function(param) {
          _this.resultLoginKeypad(param.inputResult);
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.showLoginKeypad(
          "numeric",
          4,
          4,
          "비밀번호 4자리를 입력해주세요.",
          _this.errMsg,
          true,
          _this.showFingerprintButton
        );
      }
    },
    //native call
    resultLoginKeypad: function(inputResult) {
      //비밀번호 체크
      this.password = inputResult;

      // 백그라운드에서 돌아온 경우에는 비밀번호만 체크
      if (this.$store.state.ynReload == "Y") {
        this.passCheck();
      } else {
        this.login();
      }
    },
    goToFingerprint: function() {
      this.gotoFingerPrint();
    },
    goCertPerson: function() {
      let _this = this;
      if (Constant.userAgent == "Android") {
        window.Android.closeFingerPrint();
      }
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
      //console.log("gotoFingerPrint called");
      if (
        (Constant.userAgent == "iOS" && Constant.userAppVersion == "1.1.7") ||
        (Constant.userAgent == "Android" && Constant.userAppVersion == "1.1.4")
      ) {
        if (Constant.userAgent == "iOS") {
          Jockey.send("loginKeypadClose");
          Jockey.send("closeFingerPrint");
        } else if (Constant.userAgent == "Android") {
          window.Android.loginKeypadClose();
          window.Android.closeFingerPrint();
        }
      } else {
        if (Constant.userAgent == "iOS") {
          Jockey.send("closeFingerPrint");
        } else if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }
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
      var frm = new FormData();
      //frm.append("no_person", _this.username);
      frm.append("cnt_fail_mode", mode);
      frm.append("cnt_fail", _this.cntFailPwd);
      this.$http.post("/m/person/modifyPwdFailCnt.json", frm).then(response => {
        var result = response.data;
        //console.log("modifyPwdFailCnt :" + result);
      });
    },
    login: function() {
      var _this = this;
      // var formData = new FormData();
      // formData.append("j_username", _this.username);
      // formData.append("j_password", _this.password);

      // seq_login AES256 암호화 후 암호에 추가
      var CryptoJS = require("crypto-js");
      var encKey =
        _this.$store.state.user.noPerson + "." + _this.$store.state.user.hp;
      var iv = encKey.substring(0, 16);
      iv = CryptoJS.enc.Utf8.parse(iv); // todo hello
      encKey = CryptoJS.enc.Utf8.parse(encKey.substring(0, 16)); // todo hello
      //console.log("login seq : ", _this.$store.state.user.seq_login);
      var ciphertext = CryptoJS.AES.encrypt(
        _this.$store.state.user.seq_login,
        iv,
        {
          mode: CryptoJS.mode.CBC,
          padding: CryptoJS.pad.Pkcs7,
          iv: iv
        }
      ).ciphertext.toString(CryptoJS.enc.Base64); // todo hello
      //console.log("login seq enc : ", ciphertext);

      //console.log("password : ", _this.password);
      _this.password = ciphertext + _this.password;
      //console.log("password : ", _this.password);

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
          //console.log("login: " + response.data);
          if (
            (Constant.userAgent == "iOS" &&
              Constant.userAppVersion == "1.1.7") ||
            (Constant.userAgent == "Android" &&
              Constant.userAppVersion == "1.1.4")
          ) {
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
                Jockey.send("loginKeypadClose");
              } else if (Constant.userAgent == "Android") {
                window.Android.setNoPerson(_this.username, _this.hp);
                window.Android.loginFlag("Y");
                window.Android.loginKeypadClose();
              }
              _this.$store.state.user.authToken = null;
              _this.$store.state.user.cntFailPwd = 0;
              _this.$store.state.user.cntFailFinger = 0;
              // _this.$store.state.user.cntFailFinger = 0;
              _this.$store.commit("LOGIN", response.data);

              _this.changeLoginDB();
              _this.chkYNagreement();
            } else {
              _this.$store.state.isLoading = false;
              _this.initClassPass();
              _this.password = "";
              //비밀번호 틀린 누적횟수 증가
              //console.log("login failed : ");
              _this.cntFailPwd++;
              _this.$store.state.user.cntFailPwd = _this.cntFailPwd;
              _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
              if (_this.cntFailPwd < 5) {
                _this.errMsg =
                  "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)";
                if (Constant.userAgent == "iOS") {
                  Jockey.send("loginKeypadFailure", {
                    message: _this.errMsg
                  });
                } else if (Constant.userAgent == "Android") {
                  if (
                    (Constant.userAgent == "iOS" &&
                      Constant.userAppVersion == "1.1.7") ||
                    (Constant.userAgent == "Android" &&
                      Constant.userAppVersion == "1.1.4")
                  ) {
                    window.Android.loginKeypadFailure(_this.errMsg);
                  }
                }
              } else if (_this.cntFailPwd == 5) {
                //지문인식 5번 모두 틀린 경우
                _this.errMsg = "비밀번호 재설정 화면으로 이동합니다.";
                this.$toast.center(_this.errMsg);
                if (Constant.userAgent == "iOS") {
                  Jockey.send("loginKeypadClose");
                } else if (Constant.userAgent == "Android") {
                  window.Android.loginKeypadClose();
                }
                setTimeout(function() {
                  _this.$router.push("/mypage/certPerson");
                }, 1000);
              }
              // if (
              //   response.data.result == "21" ||
              //   response.data.result == "22"
              // ) {
              //   _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
              // }
            }
          } else {
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
              _this.chkYNagreement();
            } else {
              _this.$store.state.isLoading = false;
              _this.initClassPass();
              _this.password = "";
              //비밀번호 틀린 누적횟수 증가
              //console.log("login failed : ");
              _this.cntFailPwd++;
              _this.$store.state.user.cntFailPwd = _this.cntFailPwd;
              _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
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
              // if (
              //   response.data.result == "21" ||
              //   response.data.result == "22"
              // ) {
              //   _this.modifyPwdFailCnt("pwd"); //cnt값 db에 저장
              // }
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
    chkYNagreement: function() {
      var _this = this;
      var url = "/m/person/getPersonAgreeHist.json";

      _this.$http.get(url).then(response => {
        var result = response.data.PersonAgreeHist;
        if (result == 0) {
          //약관DB에 데이터가 없을 경우
          _this.$store.state.isLoading = false;
          _this.$toast.center("약관 변경으로 재동의가 필요합니다.");
          setTimeout(_this.$router.push("/member/certStep1"), 1000);
        } else if (_this.$store.state.linkUrl) {
          // push 클릭후 들어왔을 경우
          _this.$router.push(_this.$store.state.linkUrl);
        } else {
          _this.$store.state.isLoading = true;
          _this.$router.push("/main");
        }
      });
    },
    passCheck: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("pass_person", _this.password);

      this.$http.post("/m/login/loginChkCode.json", formData).then(response => {
        //console.log("passCheck :" + response.data.result);
        if (response.data.result == "00") {
          //정상
          if (Constant.userAgent == "iOS") {
            Jockey.send("loginKeypadClose");
            Jockey.send("closeWebView");
          } else if (Constant.userAgent == "Android") {
            window.Android.loginKeypadClose();
            window.Android.closeWebView();
            return false;
          }
        } else {
          _this.$store.state.isLoading = false;
          _this.initClassPass();
          _this.password = "";
          //비밀번호 틀린 누적횟수 증가
          //console.log("login failed : ");
          _this.cntFailPwd += 1;
          if (_this.cntFailPwd < 5) {
            _this.errMsg =
              "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)";
            if (Constant.userAgent == "iOS") {
              Jockey.send("loginKeypadFailure", {
                message: _this.errMsg
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.loginKeypadFailure(_this.errMsg);
            }
          } else if (_this.cntFailPwd >= 5) {
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
        });
    },
    /***
     * Native Call function
     **/
    resultFingerPrint: function(result) {
      var _this = this;

      if (result === true || result === 1) {
        //지문인식 성공
        if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }

        if (_this.$store.state.ynReload == "Y") {
          if (Constant.userAgent == "Android") {
            window.Android.closeWebView();
          } else if (Constant.userAgent == "iOS") {
            Jockey.send("closeWebView");
          }

          return false;
        } else {
          if (Constant.userAgent == "iOS") {
            Jockey.send("loginFlag", {
              flag: "Y"
            });
          } else if (Constant.userAgent == "Android") {
            window.Android.loginFlag("Y");
          }
          _this.password = "999999" + _this.$store.state.user.authToken;
          // setTimeout(function() {
          _this.login();
          // }, 500);
        }
      } else {
        //지문 틀린 누적횟수 증가

        _this.cntFailFinger += 1;
        _this.$store.state.user.cntFailFinger = _this.cntFailFinger;
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

          var data = new FormData();
          data.append("no_person", _this.username);
          data.append("yn_fingerprint", "N");
          _this.$http
            .post("/m/person/modifyFingerPrint.json", data)
            .then(response => {
              _this.$store.state.user.ynFingerprint = "N";
              _this.$router.push("/member/certCodeLogin");
            });
        }
        return false;
      }
    }
  }
};
</script>