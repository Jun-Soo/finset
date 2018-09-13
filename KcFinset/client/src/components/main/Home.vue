<template>
  <main>
    <router-view/>
  </main>
</template>

<script>
  import Common from './../../assets/js/common.js'
  import Constant from './../../assets/js/constant.js'

  export default {
    created() {
      // mobile 초기화
      Common.init()

      // hp
      this.$store.state.user.hp = Constant.params.hp
      localStorage.setItem('hp', Constant.params.hp)

      // page call
      this.getUserPage()
    },
    methods: {
  
      getUserPage: function() {
  
        var _this = this;
        var data = {
          hp: Constant.params.hp
        };
  
        this.$http.get('/m//base/frameBase.json', {
          params: data
        }).then(response => {
          console.log(JSON.stringify(response.data));
  
          _this.$store.state.user.noPerson = response.data.no_person
          _this.$store.state.user.cntFailPwd = Number(response.data.cnt_fail_pwd)
          _this.$store.state.user.cntFailFinger = Number(response.data.cnt_fail_finger)
          _this.$store.state.user.ynFingerprint = response.data.yn_fingerprint
  
          if (Constant.userAgent == "Android") {
            window.Android.settingPush(response.data.yn_push);
            window.Android.settingPushType(response.data.cd_push);
            if (response.data.yn_fingerprint == 'Y') {
              window.Android.initFingerPrint();
            }
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
            Jockey.on("resultFingerPrint", function(param) {
              resultFingerPrint(param.result);
            });
  
            if (response.data.yn_fingerprint == 'Y') {
              Jockey.send("initFingerPrint");
            }
          }
  
          _this.$router.push(response.data.rtnPath)
        }).catch(e => {
          _this.errors.push(e)
        })
      }
    }
  }
</script>

<style>
</style>
