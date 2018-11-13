<template>
  <div id="app">
    <router-view />
    <Spinner v-bind:is-visible="spinnerIsVisible" />
  </div>
</template>

<script>
import Spinner from "./components/common/Spinner.vue";
import ko from "vee-validate/dist/locale/ko.js";
import Constant from "./assets/js/constant.js";

export default {
  name: "App",
  data() {
    return {
      isFcScrapDone: false, //금융사 Scraping 완료 여부(은행, 카드, 국세청) - Native
      isStScrapDone: false, //증권사 Scraping 완료 여부 - Back
      isScrapSuccess: true //전체 Scraping 성공 여부
    };
  },
  components: {
    Spinner
  },
  computed: {
    spinnerIsVisible: function() {
      return this.$store.state.isLoading;
    }
  },
  created() {
    window.resultAutoScrap = this.resultAutoScrap;
    window.saveScrapData = this.saveScrapData;
    window.resultCheckDevicesUUID = this.resultCheckDevicesUUID;
    window.pushUrlLink = this.pushUrlLink;
  },
  methods: {
    sendPush: function() {
      var push_msg;

      //자동스크래핑 관련 처리
      if (this.isScrapSuccess) {
        push_msg = "자동 스크래핑이 완료되었습니다.";
      } else {
        push_msg = "자동 스크래핑이 실패하였습니다.";
      }

      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("push_msg", push_msg);
      this.$http
        .post("/m/scrap/sendPushMsg.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result == "00") {
            console.log("자동 스크래핑 결과 푸쉬전송이 완료되었습니다.");
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //증권사 스크래핑 요청 - back
    startScrapSt: function() {
      this.checkUUID();
    },
    //스크래핑 완료 (모바일에서 호출)
    resultAutoScrap: function(isSucccess) {
      if (isSucccess == "false") {
        this.isScrapSuccess = false;
      }
      this.isFcScrapDone = true;

      if (this.isStScrapDone && this.isFcScrapDone) {
        this.sendPush();
      }
    },
    saveScrapData: function() {
      this.$http
        .post("/m/scrapData/saveScrapData.json")
        .then(function(response) {
          var result = response.data;
          console.log(
            "응답 코드:" + result.cd_err + "/응답 메세지:" + result.msg_err
          );
        });
    },
    // UUID 체크
    checkUUID: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckDevicesUUID", function(param) {
          _this.resultCheckDevicesUUID(uuid);
        });
        Jockey.send("checkDevicesUUID");
      } else if (Constant.userAgent == "Android") {
        window.Android.checkDevicesUUID();
      }
    },

    /***
     * Native Call function
     **/
    // UUID 체크 결과(모바일에서 호출)
    resultCheckDevicesUUID: function(uuid) {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("uuid", uuid);
      this.$http
        .post("/m/scrap/startScrapSt.json", formData)
        .then(function(response) {
          var result = response.data;
          console.log(
            "응답 코드:" + result.cd_err + "/응답 메세지:" + result.msg_err
          );
          if (result.result != "00") {
            _this.isScrapSuccess = false;
          }
          _this.isStScrapDone = true;

          if (_this.isStScrapDone && _this.isFcScrapDone) {
            _this.sendPush();
          }
        });
    },
    // linkUrl 페이지로 전환
    pushUrlLink: function(linkUrl) {
      this.$router.push(linkUrl);
    }
  }
};
</script>
