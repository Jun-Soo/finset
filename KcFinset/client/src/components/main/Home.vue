<template>
  <div id="mainHome">
    <router-view />
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "MainHome",
  data() {
    return {
      chkFingerPrint: true
    };
  },
  created() {
    window.resultCheckFingerPrint = this.resultCheckFingerPrint;

    // mobile 초기화
    Common.init();

    // 중복 로그인
    if (Constant.params.denied == "92") {
      this.$toast.center("이미 로그인되어 있습니다. 다시 시도해주세요.");
      return;
    }
    debugger;
    // 앱버전 체크
    var chkVersion = "";
    if (Constant.userAgent == "Android") {
      chkVersion = window.Android.checkAppVersion();
    } else if (Constant.userAgent == "iOS") {
      //앱버전 조회결과 콜백
      Jockey.on("receiveAppVersion", function(param) {
        chkVersion = param.appVersion;
      });
      //앱버전 조회 네이티브 호출
      Jockey.send("checkAppVersion");
    }

    var data = { app_version: chkVersion, user_agent: Constant.userAgent };
    this.$http
      .get("/m/login/appVersionCheck.json", {
        params: data
      })
      .then(response => {
        if (response.data.result == "update") {
          this.$dialogs
            .alert(
              "핀셋이 업데이트 되었습니다. 새버전으로 업데이트 하셔야 서비스를 정상적으로 이용하실 수 있습니다.",
              Constant.options
            )
            .then(res => {
              if (Constant.userAgent == "Android") {
                window.Android.updateApp();
                window.Android.exitApp(); //앱종료
              } else if (Constant.userAgent == "iOS") {
                Jockey.send("updateApp", {
                  app_id: "1385967472"
                });
              }
            });
        } else {
          // hp
          this.$store.state.user.hp = Constant.params.hp;
          localStorage.setItem("hp", Constant.params.hp);

          // url
          console.log("url === " + Constant.params.url);
          this.$store.state.linkUrl = Constant.params.url;

          // 비밀번호, 지문인증 재확인
          this.$store.state.ynReload = Constant.params.yn_reload;
          // this.$toast.center("ynReload : " + Constant.params.yn_reload);
          if (this.$store.state.ynReload == "Y") {
            this.getUserPage();
          } else if (Constant.userAgent == "Android") {
            window.Android.checkFingerPrint();
          } else if (Constant.userAgent == "iOS") {
            //지문인식 가능여부 체크 결과 콜백 이벤트
            Jockey.on("resultCheckFingerPrint", function(param) {
              resultCheckFingerPrint(param);
              Jockey.off("resultCheckFingerPrint");
            });
            Jockey.send("checkFingerPrint");
          } else {
            this.getUserPage();
          }
        }
      });
  },
  methods: {
    getUserPage: function() {
      var _this = this;
      var data = {
        hp: Constant.params.hp,
        chkFingerPrint: this.chkFingerPrint
      };

      this.$http
        .get("/m/base/frameBase.json", {
          params: data
        })
        .then(response => {
          _this.$store.commit("INIT", response.data);

          if (Constant.userAgent == "Android") {
            window.Android.setBackKeyUse("Y");
            window.Android.settingPush(response.data.yn_push);
            window.Android.settingPushType(response.data.cd_push);
            // if (response.data.yn_fingerprint == "Y") {
            //   window.Android.initFingerPrint();
            // }
          } else if (Constant.userAgent == "iOS") {
            //앱 푸쉬 설정
            Jockey.send("settingPush", {
              yn_push: response.data.yn_push
            });
            //앱 알림 설정
            // Jockey.send("settingPushType" , {
            //   cd_push : "${cd_push}"
            // });
            //지문인식 결과 콜백 이벤트
            // Jockey.on("resultFingerPrint", function(param) {
            //   resultFingerPrint(param.result);
            //   Jockey.off("resultFingerPrint");
            // });

            // if (response.data.yn_fingerprint == "Y") {
            //   Jockey.send("initFingerPrint");
            // }
          }
          _this.$store.state.user.ynFingerprint = response.data.yn_fingerprint;
          _this.$router.push(response.data.rtnPath);
        })
        .catch(e => {
          _this.$router.push("/error");
        });
    },
    /***
     * Native Call function
     ***/
    resultCheckFingerPrint: function(res, result) {
      let isFingerPrintRegistered = "";
      let _result = "";
      // this.$toast.center("init : " + res + "===" + result);
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
        this.chkFingerPrint = true;
      } else {
        //지문인식 기능이 없거나, 등록된 지문이 없으면
        this.chkFingerPrint = false;
      }

      // page call
      this.getUserPage();
    }
  }
};
</script>

<style>
</style>
