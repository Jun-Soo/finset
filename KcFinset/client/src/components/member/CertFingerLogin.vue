<template>
  <section id="content">
    <div class="cert-finger" aria-readonly="true">
      <p class="text">지문을 입력해 주세요.</p>
      <div class="red" id="err_message" v-if="cntFailFinger> 0">{{ errMsg }}</div>
    </div>
    <div class="certcode-wrap">
      <p class="text">
        <router-link to="/member/certCodeLogin"><u>
            비밀번호를 입력 하시겠습니까?
          </u></router-link>
      </p>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certFinger",
  data() {
    return {
      errMsg: "",
      username: this.$store.state.user.noPerson,
      password: "",
      cntFailFinger: this.$store.state.user.cntFailFinger,
      hp: this.$store.state.user.hp
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.title = "지문인증";

    window.resultFingerPrint = this.resultFingerPrint;
    if (Constant.userAgent == "Android") {
      window.Android.initFingerPrint();
    } else if (Constant.userAgent == "iOS") {
      //지문인식 결과 콜백 이벤트
      Jockey.on("resultFingerPrint", function(param) {
        var result = false;
        if (param.result == 1) result = true;
        resultFingerPrint(result);
      });
      Jockey.send("initFingerPrint");
    }
    this.errMsg =
      "지문인증을 " + this.cntFailFinger + "회 실패한 이력이 있습니다.";
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    login: function() {
      var _this = this;
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
          if (response.data.result == "10") {
            //정상
            if (Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson", {
                noPerson: _this.username,
                phNum: _this.hp
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.setNoPerson(_this.username, _this.hp);
            }
            _this.$store.state.user.authToken = null;
            _this.$store.commit("LOGIN", response.data);
            _this.changeLoginDB();
            _this.$router.push("/main");
          } else {
            this.$toast.center(ko.messages.loginErr);
            if (response.data.result == "21") {
              //ID오류
            } else if (response.data.result == "22") {
              //PASSWD오류
            }
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //비밀번호 틀린횟수 변경
    modifyPwdFailCnt: function(mode, cnt_fail) {
      var data = {
        no_person: this.username,
        cnt_fail_mode: mode,
        cnt_fail: cnt_fail
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
    //로그인값 db 변경
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
        if (Constant.userAgent == "Android") {
          window.Android.closeFingerPrint();
        }

        if (this.$store.state.ynReload == "Y") {
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

          _this.password = _this.$store.state.user.authToken;
          this.login();
        }
      } else {
        //지문 틀린 누적횟수 증가
        _this.cntFailFinger += 1;
        this.modifyPwdFailCnt("finger", _this.cntFailFinger);

        if (_this.cntFailFinger < 5) {
          _this.errMsg = "다시 시도해 주세요. (" + _this.cntFailFinger + "/5)";
          return false;
        } else if (_this.cntFailFinger == 5) {
          //지문인식 5번 모두 틀린 경우
          _this.errMsg = "지문이 비활성화 됩니다.";
          this.$toast.center(_this.errMsg);
          if (Constant.userAgent == "Android") {
            window.Android.closeFingerPrint();
          }

          var data = { no_person: _this.username, yn_fingerprint: "N" };
          this.$http
            .get("/m/person/modifyFingerPrint.json", {
              params: data
            })
            .then(response => {
              this.$store.state.user.ynFingerprint = "N";
              this.$router.push("/member/certCodeLogin");
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
            });
        }
        return false;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.red {
  color: #e52638;
  font-size: 12px;
  margin-top: 10px;
  text-align: center;
}
</style>