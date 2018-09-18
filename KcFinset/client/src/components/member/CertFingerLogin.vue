<template>
  <section id="content">
		<div class="container security-code">
			<div class="security-code-wrap security-finger">
				<p>
					지문을 입력해 주세요.
          <span id="err_message" v-if="cntFailFinger > 0"> {{ errMsg }} </span>
				</p>
				<div class="fingerprt-cert"></div>
			</div>
			<p class="link-txt"><router-link to="/member/certCodeLogin"><u>비밀번호를 입력 하시겠습니까?</u></router-link></p>
		</div>
	</section>
</template>

<script>

import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: 'helloWorld',
  data() {
    return {
      errMsg: '',
      j_username: this.$store.state.user.noPerson,
      j_password: "",
      cntFailFinger: this.$store.state.user.cntFailFinger,
    }
  },
  component: {
  },
  computed: {
  },
  beforeCreate() {
  },
  created() {

    window.resultFingerPrint = this.resultFingerPrint
    if(Constant.userAgent == "Android") {
      window.Android.initFingerPrint();
    } else if(Constant.userAgent == "iOS") {
      //지문인식 결과 콜백 이벤트
      Jockey.on("resultFingerPrint" , function(param) {
        var result = false;
        if(param.result == 1) result = true;
        resultFingerPrint(result);
      });
      Jockey.send("initFingerPrint");
    }
    this.errMsg = "지문인증을 " + this.cntFailFinger + "회 실패한 이력이 있습니다.";
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
    login: function() {
      var _this = this;

      if (Constant.userAgent == "Android") {
        // 스플래시 ON
          window.Android.splash("Y");
      } else if (Constant.userAgent == "iOS") {
        Jockey.send("splashView", {
          yn_splash: "Y"
        });
      }

      var querystring = require('querystring')
      var data = querystring.stringify({
        j_username: _this.j_username,
        j_password: _this.$store.state.user.authToken
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
            _this.$store.state.user.authToken = null
            _this.$store.commit('LOGIN', response.data)
            _this.$router.push("/main");
          } else {
            this.$toast.center(ko.messages.loginErr)
            if (response.data.result == "21") {
              //ID오류
            } else if (response.data.result == "22") {
              //PASSWD오류
            }
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error)
        });
    },
    //비밀번호 틀린횟수 변경
    modifyPwdFailCnt: function(mode, cnt_fail) {

      var data = {"no_person": _this.j_username, "cnt_fail_mode":mode, "cnt_fail":cnt_fail};
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
    /***
     * Native Call function
     **/
    resultFingerPrint: function(result) {
      var _this = this;
      if (result == true || result == 1) {
        //지문인식 성공
        if (Common.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }
        _this.login();
      } else {
        //지문 틀린 누적횟수 증가
        _this.cntFailFinger += 1;
        modifyPwdFailCnt("finger", _this.cntFailFinger);

        if (_this.cntFailFinger < 5) {
          _this.errMsg = "다시 시도해 주세요. (" + _this.cntFailFinger + "/5)";
        }
        if (chk_finger == 5) {
          //지문인식 5번 모두 틀린 경우
          _this.errMsg = "지문이 비활성화 됩니다.";
          this.$toast.center(_this.errMsg);
          setTimeout(function() {
            _this.errMsg = "비밀번호를 입력하세요.";
          }, 1000);
          if (userAgent == "Android") {
            window.Android.closeFingerPrint();
          }

          var data = { yn_fingerprint: "N", no_person: _this.j_username };
          this.$http
            .get("/m/person/modifyFingerPrint.json", data)
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
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  .memberMain {background-color: #283593; height: 100%;}
</style>
