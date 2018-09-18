<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="container security-code">
        <div class="security-code-wrap security-passw">
          <p>
            {{ certMessage }}
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
            <li class="btn-none"><button type="button" class="btn btn-lg btn-block">&nbsp;</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-backspace" v-on:click="backClick()">←</button></li>
          </ul>
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
      j_password: "",
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
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
      window.Android.checkFingerPrint();
    } else if(Constant.userAgent == "iOS") {
      //지문인식 가능여부 체크 결과 콜백 이벤트
      Jockey.on("resultCheckFingerPrint", function(param) {
        resultCheckFingerPrint(param.result);
      });
      Jockey.send("checkFingerPrint");
    }
  },
  beforeMount() {},
  mounted() {
    if(!localStorage.getItem('tempPwd')) {
      this.$store.state.title = "비밀번호 설정 (3/7)"
      this.certMessage = '비밀번호를 입력해주세요.'
    } else {
      this.$store.state.title = "비밀번호 확인 (4/7)"
      this.certMessage = '비밀번호를 다시 한번 입력해주세요.'
      this.tempPwd = localStorage.getItem('tempPwd')
      localStorage.removeItem('tempPwd')
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
      var _this = this
      var type = 'confirmPage'
      _this.j_password += val;
      if (_this.j_password.length > 0) _this.classPass1 = "active";
      if (_this.j_password.length > 1) _this.classPass2 = "active";
      if (_this.j_password.length > 2) _this.classPass3 = "active";
      if (_this.j_password.length > 3) {
        _this.classPass4 = "active";

        if(!_this.tempPwd) {
          localStorage.setItem('tempPwd', _this.j_password)
        } else {
          if(_this.tempPwd != _this.j_password) {
            _this.j_password = ''
            this.$toast.center(ko.messages.notMatchPwd)
            return
          } else {
            type = 'changePwd'
          }
        }
        this.nextPage(type)
      }
    },
    backClick: function() {
      var _this = this
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
    nextPage: function(type) {
      var _this = this
      if(type == 'confirmPage') {
        this.$store.state.proxyUrl = '/member/certCode'
        this.$router.push("/proxy")
        return;
      } else {
        var data = {
          no_person: _this.noPerson,
          pass_person: _this.j_password
        };
        this.$http
          .get("/api/person/changePwd.json", {
            params: data
          })
          .then(response => {
            var result = response.data;
            console.log(result);
            if (result.result == "00") {
              if(_this.chkFingerPrint == 'Y') {
                setTimeout(function(){
                  this.$router.push("/member/certFinger")
                }, 2000);
              } else {
                if(Constant.userAgent == "Android" && localStorage.getItem("site") != "REAL") {
                  checkExistCert();
                } else {
                  this.login();
                }
              }
              toastMsg('비밀번호설정이 완료 되었습니다.');
            } else {
              this.$toast.center(result.message)
              return false;
            }
          })
          .catch(e => {
            this.$toast.center(ko.messages.error)
          });
      }
    },
    login: function() {
      var _this = this;

      var querystring = require('querystring')
      var data = querystring.stringify({
        j_username: _this.noPerson,
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
          if (response.data.result == "10") {
            //정상
            _this.$store.commit('LOGIN', response.data)
            _this.$router.push("/main");
          } else {
            this.$toast.center(ko.messages.loginErr)
            return
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error)
        });
    },
    //Native Call function
    resultCheckFingerPrint: function(result) {
      if(result == true || result == 1){
        this.chkFingerPrint = 'Y'
      } else {
        this.chkFingerPrint = 'N'
      }
    },
    // 공인인증서 유무 체크
    checkExistCert: function() {
      if(Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert" , function(param) {
          var iscert = false;
          if(param.isCert == 1) iscert = true;
          resultCheckCert(iscert);
        });
        Jockey.send("checkExistCert");
      }
      else if(Constant.userAgent == "Android") {
        window.Android.checkExistCert();
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      if(isCert) {  // 공인인증서가 있을 경우
        frmFcCertList();
      } else {      // 공인인증서가 없을 경우
        this.$toast.center('공인인증서가 없습니다.');
        this.login();
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  .memberMain {height: 100%;}
</style>
