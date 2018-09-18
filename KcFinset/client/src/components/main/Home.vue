<template>
  <div id="mainHome">
    <router-view/>
  </div>
</template>

<script>
  import Common from './../../assets/js/common.js'
  import Constant from './../../assets/js/constant.js'

  export default {
    name: 'MainHome',
    data() {
      return {
      }
    },
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
  
        this.$http.get('/m/base/frameBase.json', {
          params: data
        }).then(response => {
          this.$store.state.user.noPerson = response.data.no_person
          this.$store.state.user.nmPerson = response.data.nm_person
          this.$store.state.user.cntFailPwd = Number(response.data.cnt_fail_pwd)
          this.$store.state.user.cntFailFinger = Number(response.data.cnt_fail_finger)
          this.$store.state.user.ynFingerprint = Common.nvl(response.data.yn_fingerprint, 'N')
          this.$store.state.user.dt_basic = response.data.dt_basic

          this.$store.state.bankCode = response.data.bank_code
          this.$store.state.cardCode = response.data.card_code
          this.$store.state.site = response.data.site

          if(response.data.rtnPath == '/member/certFingerLogin') {
            this.$store.state.user.authToken = response.data.authToken
          }
          localStorage.setItem('site', response.data.site)

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
          _this.$router.push('/error')
        })
      }
    }
  }
</script>

<style>
</style>
