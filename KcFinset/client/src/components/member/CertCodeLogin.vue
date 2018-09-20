<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="container security-code">
        <div class="security-code-wrap security-passw">
          <p>
            비밀번호를 입력해주세요.
            <span id="err_message" v-if="cntFailPwd > 0"> {{ errMsg }} </span>
          </p>
          <div class="code-group clearfix">
            <div class="code form-shake">
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass1">코드번호</label>
                <input type="password" class="form-control code-no active" name="pass_number" id="pass_number1" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass2">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number2" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass3">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number3" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass4">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number4" maxlength="1" value="" placeholder="" />
              </div>
              <!-- <div class="mark-group">
                <label for="" class="code-mark">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number5" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number6" maxlength="1" value="" placeholder="" />
              </div> -->
            </div>
          </div>
          <ul class="keypad">
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('1')">1</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('2')">2</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('3')">3</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('4')">4</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('5')">5</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('6')">6</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('7')">7</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('8')">8</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('9')">9</button></li>
            <li v-if="ynFingerprint === 'Y'"><router-link to="/member/certFingerLogin"><button class="btn btn-lg btn-block btn-fingerfrt">&nbsp;</button></router-link></li>
            <li class="btn-none" v-if="ynFingerprint === 'N'"><button type="button" class="btn btn-lg btn-block">&nbsp;</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('0')">0</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-backspace" v-on:click="backClick()">←</button></li>
          </ul>
        </div>
        <p class="link-txt"><a href=""><u>비밀번호를 재설정 하시겠습니까?</u></a></p>
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
  name: "certCodeLogin",
  data() {
    return {
      errMsg: "",
      cntFailPwd: this.$store.state.user.cntFailPwd,
      cntFailFinger: this.$store.state.user.cntFailFinger,
      ynFingerprint: this.$store.state.user.ynFingerprint,
      j_username: this.$store.state.user.noPerson,
      j_password: "",
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

    this.$store.state.title = '비밀번호 입력'
    window.resultFingerPrint = this.resultFingerPrint
    // window.resultFingerPrint = this.resultFingerPrint
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y")
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
      _this.j_password += val;
      if (_this.j_password.length > 0) _this.classPass1 = "active";
      if (_this.j_password.length > 1) _this.classPass2 = "active";
      if (_this.j_password.length > 2) _this.classPass3 = "active";
      if (_this.j_password.length > 3) {
        _this.classPass4 = "active";

        _this.login();
      }
    },
    backClick: function() {
      var _this = this;
      this.initClassPass();
      _this.j_password = _this.j_password.substr(
        0,
        _this.j_password.length - 1
      );
      if (_this.j_password.length > 0) _this.classPass1 = "active";
      if (_this.j_password.length > 1) _this.classPass2 = "active";
      if (_this.j_password.length > 2) _this.classPass3 = "active";
      if (_this.j_password.length > 3) _this.classPass4 = "active";
    },
    //비밀번호 틀린횟수 변경
    modifyPwdFailCnt: function(mode) {
      var _this = this
      var data = {no_person: _this.j_username,
                cnt_fail_mode: mode,
                cnt_fail: _this.cntFailPwd}
      this.$http
        .get("/m/person/modifyPwdFailCnt.json", {
          params: data
        })
        .then(response => {
          var result = response.data;
          console.log(result);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error)
        });
    },
    login: function() {
      var _this = this;

      var querystring = require('querystring')
      var data = querystring.stringify({
        j_username: _this.j_username,
        j_password: _this.j_password
      });
      this.$http
        .post("/check/j_spring_security_check", data
        ,{
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
          console.log(response.data.result)
          if (response.data.result == "10") {
            //정상
            _this.$store.state.user.authToken = null
            _this.$store.commit('LOGIN', response.data)
            _this.$router.push("/main")
          } else {
            this.initClassPass()
            _this.j_password = ""
            //비밀번호 틀린 누적횟수 증가
            _this.cntFailPwd += 1
            if (_this.cntFailPwd < 5) {
              _this.errMsg = "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)";
            } else if (_this.cntFailPwd == 5) {
              //지문인식 5번 모두 틀린 경우
              _this.errMsg = "비밀번호 재설정 화면으로 이동합니다.";
              this.$toast.center(_this.errMsg);
              setTimeout(function () {
                _this.$router.push("/mypage/certPerson");
              }, 1000)
            }
            //비밀번호 틀린 누적횟수 증가
            _this.cntFailPwd += 1
            _this.errMsg = "비밀번호가 일치하지 않습니다. (" + _this.cntFailPwd + "/5)"
            if (response.data.result == "21" || response.data.result == "22") {
              _this.modifyPwdFailCnt("pwd")
            }
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error)
        })
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
      this.modifyPwdFailCnt("finger", _this.cntFailFinger)

      if (_this.cntFailFinger < 5) {
        _this.errMsg = "지문이 일치하지 않습니다. (" + _this.cntFailFinger + "/5)";
      } else if (_this.cntFailFinger == 5) {
        //지문인식 5번 모두 틀린 경우
        _this.errMsg = "지문이 비활성화 됩니다.";
        this.$toast.center(_this.errMsg);
        if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }

        var data = { yn_fingerprint: "N", no_person: _this.j_username };
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
  .memberMain {background-color: #283593; height: 100%;}
</style>
