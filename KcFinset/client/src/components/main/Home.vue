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
      chkFingerPrint: false
    };
  },
  created() {
    window.resultCheckFingerPrint = this.resultCheckFingerPrint;

    if (Constant.userAgent == "Android") {
      window.Android.checkFingerPrint();
    } else if (Constant.userAgent == "iOS") {
      //지문인식 가능여부 체크 결과 콜백 이벤트
      Jockey.on("resultCheckFingerPrint", function(param) {
        _this.resultCheckFingerPrint(param.result);
      });
      Jockey.send("checkFingerPrint");
    }

    // mobile 초기화
    Common.init();

    // 중복 로그인
    if (Constant.params.denied == "92") {
      this.$toast.center("이미 로그인되어 있습니다. 다시 시도해주세요.");
      return;
    }

    // hp
    this.$store.state.user.hp = Constant.params.hp;
    localStorage.setItem("hp", Constant.params.hp);

    // url
    console.log("url === " + Constant.params.url);
    this.$store.state.linkUrl = Constant.params.url;

    // 비밀번호, 지문인증 재확인
    this.$store.state.ynReload = Constant.params.yn_reload;
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
            // });

            // if (response.data.yn_fingerprint == "Y") {
            //   Jockey.send("initFingerPrint");
            // }
          }
          _this.$router.push(response.data.rtnPath);
        })
        .catch(e => {
          _this.$router.push("/error");
        });
    },
    /***
     * Native Call function
     ***/
    resultCheckFingerPrint: function(res) {
      // console.log(result);
      let isFingerPrintRegistered = res.isFingerPrintRegistered; //지문이 등록되어있지만, 지문data가 없는 경우
      let result = res.result;
      if ((result == true || result == 1) && isFingerPrintRegistered == true) {
        this.chkFingerPrint = "Y";
      } else {
        //지문인식 기능이 없거나, 등록된 지문이 없으면
        this.chkFingerPrint = "N";
      }
      // page call
      this.getUserPage();
    }
  }
};
</script>

<style>
</style>
