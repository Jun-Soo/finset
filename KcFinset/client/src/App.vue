<template>
  <div id="app">
    <router-view />
    <Spinner v-bind:is-visible="spinnerIsVisible" />
  </div>
</template>

<script>
import Spinner from "./components/common/Spinner.vue";
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "App",
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
  },
  methods: {
    //스크래핑 완료 (모바일에서 호출)
    resultAutoScrap: function(isSucccess) {
      var push_msg;

      //자동스크래핑 관련 처리
      if (isSucccess == "true") {
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
    }
  }
};
</script>
