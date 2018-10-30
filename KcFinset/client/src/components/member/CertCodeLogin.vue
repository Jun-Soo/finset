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
          <input type="password" v-bind:style="classPass1" v-model="classPass1" id="pass_number1" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass2" v-model="classPass2" id="pass_number2" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass3" v-model="classPass3" id="pass_number3" maxlength="1" readonly />
          <input type="password" v-bind:style="classPass4" v-model="classPass4" id="pass_number4" maxlength="1" readonly />
        </div>
        <div class="number">
          <button type="button" v-on:click="btnClick('1')">1</button>
          <button type="button" v-on:click="btnClick('2')">2</button>
          <button type="button" v-on:click="btnClick('3')">3</button>
          <button type="button" v-on:click="btnClick('4')">4</button>
          <button type="button" v-on:click="btnClick('5')">5</button>
          <button type="button" v-on:click="btnClick('6')">6</button>
          <button type="button" v-on:click="btnClick('7')">7</button>
          <button type="button" v-on:click="btnClick('8')">8</button>
          <button type="button" v-on:click="btnClick('9')">9</button>
          <router-link to="/member/certFingerLogin"><button v-if="ynFingerprint === 'Y'" class="finger">&nbsp;</button></router-link>
          <button type="button" v-on:click="btnClick('0')">0</button>
          <button type="button" class="del" v-on:click="backClick()"></button>
        </div>
        <p class="text"><a href=""><u>비밀번호를 재설정 하시겠습니까?</u></a></p>
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
    this.$store.state.title = "비밀번호 확인";
    window.resultFingerPrint = this.resultFingerPrint;
    // window.resultFingerPrint = this.resultFingerPrint
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");

      if (this.$store.state.user.ynFingerprint == "Y") {
        window.Android.initFingerPrint();
      }
    }
  },
  beforeMount() {},
  mounted() {
    this.errMsg =
      "비밀번호를 " + this.cntFailPwd + "회 실패한 이력이 있습니다.";
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
      _this.password += val;
      if (_this.password.length > 0) _this.classPass1 = "border-color: #111";
      if (_this.password.length > 1) _this.classPass2 = "border-color: #111";
      if (_this.password.length > 2) _this.classPass3 = "border-color: #111";
      if (_this.password.length > 3) {
        _this.classPass4 = "border-color: #111";

        _this.login();
      }
    },
    backClick: function() {
      var _this = this;
      this.initClassPass();
      _this.password = _this.password.substr(0, _this.password.length - 1);
      if (_this.password.length > 0) _this.classPass1 = "border-color: #111";
      if (_this.password.length > 1) _this.classPass2 = "border-color: #111";
      if (_this.password.length > 2) _this.classPass3 = "border-color: #111";
      if (_this.password.length > 3) _this.classPass4 = "border-color: #111";
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
          console.log(result);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    login: function() {
      var _this = this;

      var querystring = require("querystring");
      var data = querystring.stringify({
        j_username: _this.username,
        j_password: _this.password
      });
      this.$http
        .post("/check/j_spring_security_check", data, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
          console.log(response.data.result);
          if (response.data.result == "10") {
            //정상
            _this.$store.state.user.authToken = null;
            _this.$store.commit("LOGIN", response.data);
            _this.$router.push("/main");
          } else {
            this.initClassPass();
            _this.password = "";
            //비밀번호 틀린 누적횟수 증가
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
              _this.modifyPwdFailCnt("pwd");
            }
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    }
  },
  /***
   * Native Call function
   **/
  resultFingerPrint: function(result) {
    var _this = this;
    if (result == true || result == 1) {
      //지문인식 성공
      if (Constant.userAgent == "Android") {
        window.Android.closeFingerPrint();
      }
      _this.login();
    } else {
      //지문 틀린 누적횟수 증가
      _this.cntFailFinger += 1;
      this.modifyPwdFailCnt("finger", _this.cntFailFinger);

      if (_this.cntFailFinger < 5) {
        _this.errMsg =
          "지문이 일치하지 않습니다. (" + _this.cntFailFinger + "/5)";
      } else if (_this.cntFailFinger == 5) {
        //지문인식 5번 모두 틀린 경우
        _this.errMsg = "지문이 비활성화 됩니다.";
        this.$toast.center(_this.errMsg);
        if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }

        var data = {
          yn_fingerprint: "N",
          no_person: _this.username
        };
        this.$http
          .get("/m/person/modifyFingerPrint.json", {
            params: data
          })
          .then(response => {
            this.$store.state.user.ynFingerprint = "N";
          })
          .catch(e => {
            this.$toast.center(ko.messages.error);
          });
      }
      return false;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
