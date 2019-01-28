<template>
  <div id="wrapper">
    <!-- Content -->
    <section v-if="seen">
      <div class="certcode-wrap">
        <p class="text">
          {{certMessage}}
        </p>
        <p class="textred" v-if="errMsg"> {{ errMsg }} </p>

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
          <a disabled="disabled">&nbsp;</a>
          <a v-on:click="btnClick('0')">0</a>
          <a class="del" v-on:click="backClick()"></a>
        </div>
      </div>
    </section>
  </div>

</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "chgPwd",
  data() {
    return {
      errMsg: "",
      cntFailPwd: this.$store.state.user.cntFailPwd,
      cntFailFinger: this.$store.state.user.cntFailFinger,
      username: this.$store.state.user.noPerson,
      tempPwd: "",
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
      seen: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    window.resultLoginKeypad = this.resultLoginKeypad;

    this.$store.state.header.type = "noHeader";
    this.$store.state.title = "비밀번호 확인";

    if (this.$store.state.isLoggedIn) {
      this.$store.state.header.backPath = "/mypage/cert";
    } else {
      this.$store.state.header.backPath = "/member/certCodeLogin";
    }
  },
  beforeMount() {},
  mounted() {
    if (!localStorage.getItem("_tempPwd")) {
      this.$store.state.title = "비밀번호 설정";
      this.certMessage = "비밀번호를 입력해주세요.";
    } else {
      this.$store.state.title = "비밀번호 확인";
      this.certMessage = "비밀번호를 다시 한번 입력해주세요.";
      this.tempPwd = localStorage.getItem("_tempPwd");
    }
    if (
      (Constant.userAgent == "iOS" && Constant.userAppVersion == "1.1.7") ||
      (Constant.userAgent == "Android" && Constant.userAppVersion == "1.1.4")
    ) {
      this.showLoginKeypad();
    } else {
      this.seen = true;
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {
    // localStorage.removeItem("_tempPwd");
  },
  methods: {
    showLoginKeypad: function() {
      var _this = this;

      if (Constant.userAgent == "iOS") {
        Jockey.send("showLoginKeypad", {
          keypadType: "numeric",
          minInputLength: 4,
          maxInputLength: 4,
          subTitle: _this.certMessage,
          placeholderText: "숫자를 입력하세요.",
          message: _this.errMsg,
          exitOnBackPressed: "false",
          showFingerprintButton: "false"
        });
        //보안키패드 결과값 수신 콜백 이벤
        Jockey.on("resultLoginKeypad", function(param) {
          _this.resultLoginKeypad(param.inputResult);
          Jockey.off("resultLoginKeypad");
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.showLoginKeypad(
          "numeric",
          4,
          4,
          _this.certMessage,
          _this.errMsg,
          true,
          false
        );
      }
    },
    //native call
    resultLoginKeypad: function(inputResult) {
      //비밀번호 체크
      var _this = this;
      var type = "confirmPage";
      this.password = inputResult;

      if (!localStorage.getItem("_tempPwd")) {
        //비밀번호 복잡도 체크
        var frm = new FormData();
        frm.append("password", this.password);
        this.$http.post("/m/person/checkPassword.json", frm).then(response => {
          var result = response.data;

          if (result.result == "00") {
            localStorage.setItem("_tempPwd", this.password);
          } else {
            type = "reload";
            _this.password = "";
            _this.errMsg = result.message + "\n다시 설정해주세요.";
          }
          _this.nextPage(type); //confirmPage 일경우 redirect , 재확인일경우 changePwd
        });
        // if (Constant.userAgent == "iOS") {
        //   Jockey.send("loginKeypadClose");
        // } else if (Constant.userAgent == "Android") {
        //   window.Android.loginKeypadClose();
        // }
      } else {
        this.tempPwd = localStorage.getItem("_tempPwd");

        //비밀번호 체크
        var frm = new FormData();
        frm.append("password", this.tempPwd);
        frm.append("confirm_password", this.password);
        this.$http
          .post("/m/person/confirmPassword.json", frm)
          .then(response => {
            var result = response.data;

            if (result.result == "00") {
              type = "changePwd";
              _this.errMsg = "";
            } else {
              type = "reload";
              _this.password = "";
              _this.errMsg = ko.messages.notMatchPwd + "\n다시 설정해주세요.";
            }
            _this.nextPage(type); //confirmPage 일경우 redirect , 재확인일경우 changePwd
          });
        localStorage.removeItem("_tempPwd");
      }
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
          localStorage.setItem("_tempPwd", _this.password);
        } else {
          if (_this.tempPwd != _this.password) {
            //앞 비밀번호와 같은지 확인
            _this.password = "";
            _this.initClassPass();
            // _this.backClick();
            this.$toast.center(ko.messages.notMatchPwd + " 다시 설정해주세요.");
          } else {
            type = "changePwd";
          }
          localStorage.removeItem("_tempPwd");
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
      if (
        (Constant.userAgent == "iOS" && Constant.userAppVersion == "1.1.7") ||
        (Constant.userAgent == "Android" && Constant.userAppVersion == "1.1.4")
      ) {
        // 보안키보드 닫기
        console.log("nextPage :: ", type);
        if (Constant.userAgent == "iOS") {
          Jockey.send("loginKeypadClose");
        } else if (Constant.userAgent == "Android") {
          window.Android.loginKeypadClose();
        }
        if (type == "confirmPage") {
          this.errMsg = "";
          if (Constant.userAgent == "iOS" || Constant.userAgent == "Android") {
            this.showLoginKeypad();
          }
        } else if (type == "reload") {
          if (Constant.userAgent == "iOS" || Constant.userAgent == "Android") {
            this.showLoginKeypad();
          }
        } else if (type == "changePwd") {
          var frm = new FormData();
          frm.append("pass_person", _this.password);
          this.$http
            .post("/m/person/changePwd.json", frm)
            .then(response => {
              var result = response.data;
              localStorage.removeItem("_tempPwd");
              console.log("changePwd.json  : ", result.result);
              if (result.result == "00") {
                _this.$store.state.user.cntFailPwd = 0;
                _this.$store.state.user.cntFailFinger = 0;
                _this.$toast.center("비밀번호설정이 완료 되었습니다.");
                if (_this.$store.state.isLoggedIn) {
                  _this.$router.push("/mypage/cert");
                } else {
                  _this.$toast.center("로그인 페이지로 이동합니다.");
                  setTimeout(function() {
                    _this.$router.push("/member/certCodeLogin");
                  }, 1000);
                }
              } else {
                this.$toast.center(result.message);
                _this.password = "";
                _this.initClassPass();
                return false;
              }
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
              _this.password = "";
              _this.initClassPass();
            });
        }
      } else {
        for (var i = 0; i < _this.password.length; i++) {
          if (
            i < _this.password.length - 2 &&
            _this.password.charCodeAt(i) == _this.password.charCodeAt(i + 1)
          ) {
            if (
              i < _this.password.length - 1 &&
              _this.password.charCodeAt(i) == _this.password.charCodeAt(i + 2)
            ) {
              _this.errMsg = "비밀번호는 3자리 이상 연속될 수 없습니다.";
              _this.password = "";
              _this.initClassPass();
              localStorage.removeItem("_tempPwd");
              return;
            }
          }
        }
        if (type == "confirmPage") {
          this.$store.state.proxyUrl = "/mypage/chgPwd";
          this.$router.push("/proxy");
          return;
        } else {
          var frm = new FormData();
          frm.append("pass_person", _this.password);
          this.$http
            .post("/m/person/changePwd.json", frm)
            .then(response => {
              var result = response.data;
              localStorage.removeItem("_tempPwd");
              if (result.result == "00") {
                _this.$store.state.user.cntFailPwd = 0;
                _this.$store.state.user.cntFailFinger = 0;
                _this.$toast.center("비밀번호설정이 완료 되었습니다.");
                if (_this.$store.state.isLoggedIn) {
                  _this.$router.push("/mypage/cert");
                } else {
                  _this.$toast.center("로그인 페이지로 이동합니다.");
                  setTimeout(function() {
                    _this.$router.push("/member/certCodeLogin");
                  }, 1000);
                }
              } else {
                this.$toast.center(result.message);
                _this.password = "";
                _this.initClassPass();
                return false;
              }
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
              _this.password = "";
              _this.initClassPass();
            });
        }
      }
    }
  }
};
</script>