<template>
  <section>
    <div class="cs-top">
      <em>서비스 이용 관련<br>문의 및 의견</em>을 보내주세요
      <div class="links">
        <a href="https://goto.kakao.com/@신용부채관리핀셋" class="kakao" target="_blank">카카오톡 문의</a>
        <a @click="sendEmail('00', 'finset@koscom.co.kr')" class="email">이메일 문의</a>
      </div>
    </div>

    <div class="cs-links">
      <p><a @click="$router.push('/etc/faqList')">자주 묻는 질문</a></p>
      <p><a @click="$router.push('/etc/term')">이용약관 및 정책</a></p>
      <p>
        <em>버전정보</em>
        <em>{{app_version}}</em>
      </p>
      <p>
        <em>제휴 문의</em>
        <em>finset.@koscom.co.kr</em>
      </p>
    </div>

  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";

export default {
  name: "EtcFaqMain",
  data() {
    return {
      app_version: "1.1"
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "고객센터";
  },
  created() {
    if (Constant.userAgent == "Android") {
      this.app_version = window.Android.checkAppVersion();
      //checkAppVersion(this.app_version);
    } else if (Constant.userAgent == "iOS") {
      //앱버전 조회결과 콜백
      Jockey.on("receiveAppVersion", function(param) {
        this.app_version = param.appVersion;
        // checkAppVersion(param.appVersion);
      });
      //앱버전 조회 네이티브 호출
      Jockey.send("checkAppVersion");
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    sendEmail: function(code, addr) {
      if (Constant.userAgent == "iOS") {
        Jockey.send("sendEmail", {
          code: code,
          addr: addr
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.sendEmail(code, addr);
      }
    },
    //기능 미정 ** 물어봐야함
    updateApp: function() {
      if (Constant.userAgent == "Android") {
        window.Android.updateApp();
      } else if (Constant.userAgent == "iOS") {
        Jockey.send("updateApp", {
          app_id: ""
        });
      }
    },
    /**
     * NATIVE CALL BACK
     */
    checkAppVersion: function(appVersion) {
      $("#app_version").html(appVersion);

      var version = "${newest_version}";
      var versionSplit = version.split("."); //최신버전
      var chkVersionSplit = appVersion.split("."); //현재버전
      // for (var i = 0; i < versionSplit.length; i++) {
      //   if (Number(versionSplit[i]) > Number(chkVersionSplit[i])) {
      //     $("#update").show();
      //     break;
      //   }
      // }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
