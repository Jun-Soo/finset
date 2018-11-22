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
      classPass4: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.$store.state.title = "비밀번호 확인";
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");

      if (this.$store.state.user.ynFingerprint == "Y") {
        window.Android.initFingerPrint();
      }
    }
    // localStorage.removeItem("tempPwd");
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
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {
    // localStorage.removeItem("_tempPwd");
  },
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
      if (_this.password.length < 4) {
        _this.password += val;
      }
      if (_this.password.length > 0) _this.classPass1 = "active";
      if (_this.password.length > 1) _this.classPass2 = "active";
      if (_this.password.length > 2) _this.classPass3 = "active";
      if (_this.password.length > 3) {
        _this.classPass4 = "active";

        if (!_this.tempPwd) {
          localStorage.setItem("_tempPwd", _this.password);
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
        this.$store.state.proxyUrl = "/mypage/chgPwd";
        this.$router.push("/proxy");
        return;
      } else {
        var data = {
          no_person: _this.username,
          pass_person: _this.password
        };
        this.$http
          .get("/m/person/changePwd.json", {
            params: data
          })
          .then(response => {
            var result = response.data;
            localStorage.removeItem("_tempPwd");
            if (result.result == "00") {
              this.$toast.center("비밀번호설정이 완료 되었습니다.");
              this.$router.push("/mypage/cert");
            } else {
              this.$toast.center(result.message);
              _this.password = "";
              _this.initClassPass();
              return false;
            }
          })
          .catch(e => {
            this.$toast.center(ko.messages.error);
            this.$toast.center(e);
            _this.password = "";
            _this.initClassPass();
          });
      }
    }
  }
};
</script>