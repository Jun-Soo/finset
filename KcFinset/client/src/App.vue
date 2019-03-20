<template>
  <div id="app">
    <router-view />
    <Spinner v-bind:is-visible="spinnerIsVisible" :key="refreshKey" />
  </div>
</template>

<script>
import Common from "./assets/js/common.js";
import Spinner from "./components/common/Spinner.vue";
import ko from "vee-validate/dist/locale/ko.js";
import Constant from "./assets/js/constant.js";

export default {
  name: "App",
  data() {
    return {
      refreshKey: 0,
      isFcScrapDone: false, //금융사 Scraping 완료 여부(은행, 카드, 국세청) - Native
      isStScrapDone: false, //증권사 Scraping 완료 여부 - Back
      isScrapSuccess: true, //전체 Scraping 성공 여부
      reloadPageList: [
        //스크래핑 완료시 갱신할 화면 목록
        "/main",
        "/common/monthCal",
        "/common/yearCal",
        "/consume/main",
        //"/consume/consumeDetail",
        "/consume/main",
        //"/consume/transPop",
        //"/consume/analyze",
        "/consume/payment",
        "/consume/settlement",
        //"/consume/incomeStats",
        "/assets/main",
        "/assets/bankMain"
        //"/assets/bankActDetail",
        //"/assets/bankDepWdrlList",
        //"/assets/bankDepWdrlDetail"
      ]
    };
  },
  components: {
    Spinner
  },
  watch: {
    $route: function(to, from) {
      if (Constant._this != undefined) {
        Common.removeScroll();
      }
    }
  },
  computed: {
    spinnerIsVisible: function() {
      return this.$store.state.isLoading;
    }
  },
  created() {
    window.resultAutoScrap = this.resultAutoScrap;
    window.resultCheckDevicesUUID = this.resultCheckDevicesUUID;
    window.pushUrlLink = this.pushUrlLink;
    window.resultHasCertPassword = this.resultHasCertPassword;
  },
  mounted() {
    this.sortCssFile();
    var _this = this;
    if (Constant.userAgent == "iOS") {
      //스크래핑 완료 관련 iOS 함수 등록
      Jockey.off("resultAutoScrap");
      Jockey.on("resultAutoScrap", function(param) {
        console.log("iOS resultAutoScrap called");
        _this.resultAutoScrap(param.isSuccess);
      });
    }
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
      // formData.append("no_person", this.$store.state.user.noPerson);
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
    //상단
    hideProgressBanner: function() {
      var _this = this;
      console.log("hideProgressBanner called : " + this.isScrapSuccess);
      if (Constant.userAgent == "iOS") {
        Jockey.send("hideProgressBanner", {
          success: _this.isScrapSuccess
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.hideProgressBanner(_this.isScrapSuccess);
      }
      //스크래핑 성공 일 경우 화면 갱신
      if (this.isScrapSuccess) {
        for (var i = 0; i < this.reloadPageList.length; i++) {
          console.log("Page : " + this.reloadPageList[i]);
          if (this.$router.currentRoute.path == this.reloadPageList[i]) {
            console.log("reload page ~ !!!!!!");
            // _this.$toast.center(
            //   "스크래핑 완료로 인하여<br/>정보를 갱신하겠습니다."
            // );
            _this.$toast.center("데이터 업데이트를 완료하였습니다.");
            //모달 해제
            if (
              this.$modals != undefined &&
              this.$modals.shownModals != undefined &&
              this.$modals.shownModals.length > 0
            ) {
              var shownModals = this.$modals.shownModals;
              for (var idx in shownModals) {
                this.$modals.hide(shownModals[idx]);
              }
            }
            //alert, confirm 창 해제
            if (
              this.$dialogs != undefined &&
              this.$dialogs.items != undefined &&
              this.$dialogs.items.length > 0
            ) {
              var items = this.$dialogs.items;
              for (var idx in items) {
                this.$dialogs.remove(items[idx]);
              }
            }
            // 화면 갱신 처리
            setTimeout(function() {
              // proxy화면 이동 후 현재화면으로 이동 (화면 갱신하면 Store가 갱신되는 현상으로 인한 임시 방편)
              console.log("reload Page :", _this.$router.currentRoute.path);
              _this.$store.state.proxyUrl = _this.$router.currentRoute.path;
              _this.$router.push("/proxy");
              //_this.$router.push(_this.$router.currentRoute.path);
              //_this.$router.go(_this.$router.currentRoute);
              // _this.refreshKey++;
              // _this.$router.go();
              //alert(this.$store.state.user.noPerson);
              //location.reload();
            }, 2000);
            break;
          }
        }
      }
    },
    //증권사 스크래핑 요청 - back
    startScrapSt: function() {
      console.log("startScrapSt uuid : ", this.$store.state.uuid);
      //기존에 Device UUID 없을 경우만 chekck
      if (this.$store.state.uuid != "") {
        this.checkUUID();
      } else {
        this.resultCheckDevicesUUID(this.$store.state.uuid);
      }
    },
    //스크래핑 완료 (모바일에서 호출)
    resultAutoScrap: function(isSuccess) {
      console.log("resultAutoScrap called : ", isSuccess);
      if (isSuccess == "false") {
        this.isScrapSuccess = false;
      }
      this.isFcScrapDone = true;
      console.log(
        "isStScrapDone : ",
        this.isStScrapDone,
        " - isFcScrapDone : ",
        this.isFcScrapDone
      );
      if (this.isStScrapDone && this.isFcScrapDone) {
        this.saveScrapData();
        if (!this.isScrapSuccess) {
          this.sendPush();
        }
      }
    },
    // 스크래핑 데이터 가공 및 저장 5t6
    saveScrapData: function() {
      var _this = this;
      this.$http
        .post("/m/scrapData/saveScrapData.json")
        .then(function(response) {
          var result = response.data;
          console.log(
            "응답 코드:" + result.cd_err + "/응답 메세지:" + result.msg_err
          );
          _this.hideProgressBanner();
        });
    },
    // UUID 체크
    checkUUID: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckDevicesUUID", function(param) {
          _this.resultCheckDevicesUUID(param.uuid);
          Jockey.off("resultCheckDevicesUUID");
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
      // formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("uuid", uuid);
      this.$http
        .post("/m/scrap/startScrapSt.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result != "00") {
            _this.isScrapSuccess = false;
          }
          _this.isStScrapDone = true;
          console.log(
            "isStScrapDone : ",
            _this.isStScrapDone,
            " - isFcScrapDone : ",
            _this.isFcScrapDone
          );
          if (_this.isStScrapDone && _this.isFcScrapDone) {
            _this.saveScrapData();
            if (!_this.isScrapSuccess) {
              _this.sendPush();
            }
          }
        });
    },
    // linkUrl 페이지로 전환
    pushUrlLink: function(linkUrl) {
      this.$router.push(linkUrl);
    },
    // 임시로 css파일을 하단으로 내리는 로직
    sortCssFile: function() {
      var head = document.head.childNodes;
      for (var i in head) {
        if (head[i].nodeName == "LINK") {
          var link = document.head.removeChild(head[i]);
          document.head.appendChild(link);
        }
      }
    },
    autoScrapCallback: function(result) {
      console.log("autoScrapCallback call : " + result);
      if (result) {
        this.startScrapSt();
      }
    },
    startAutoScrap: function() {
      var _this = this;
      // 스트크래핑 대상 금융사 건수 조회
      console.log("startAutoScrap");
      this.$http
        .get(
          "/m/scrap/getLinkedFcCount.json"
          // , {params: { no_person: _this.$store.state.user.noPerson }}
        )
        .then(function(response) {
          var result = response.data;
          console.log("result.linkedFcCount : " + result.linkedFcCount);
          if (result.linkedFcCount > 0) {
            if (Constant.userAgent == "iOS") {
              Jockey.on("resultHasCertPassword", function(param) {
                _this.resultHasCertPassword(param.isExist);
                Jockey.off("resultHasCertPassword");
              });
              Jockey.send("hasCertPassword");
            } else if (Constant.userAgent == "Android") {
              console.log("hasCertPassword start");
              window.Android.hasCertPassword();
              console.log("hasCertPassword end");
            }
          } else {
            _this.autoScrapCallback(false);
          }
        })
        .catch(e => {
          _this.$toast.center(e);
        });
    },
    getAutoScrapInfo: function() {
      console.log("getAutoScrapInfo");
      var _this = this;
      // 스크래핑 대상 내역 조회
      this.$http
        .get(
          "/m/scrap/getAutoScrapInfo.json"
          // , {params: { no_person: _this.$store.state.user.noPerson }}
        )
        .then(function(response) {
          var result = response.data;
          // if (Constant.userAgent == "Android") {
          //   var smsStartDate = result.smsStartDate;
          //   var smsInclude = result.smsInclude;
          //   var smsExclude = result.smsExclude;
          //   window.Android.getSmsList(smsStartDate, smsInclude, smsExclude);
          // }

          var bankInfo = "";
          var cardInfo = "";
          var ntsInfo = "";
          //은행 스크래핑 내역
          if (result.autoScrapBankInfo) {
            bankInfo = result.autoScrapBankInfo;
          }
          //카드 스크래핑 내역
          if (result.autoScrapCardInfo) {
            cardInfo = result.autoScrapCardInfo;
          }
          //국세청 스크래핑 내역
          if (result.autoScrapNTSInfo) {
            ntsInfo = result.autoScrapNTSInfo;
          }

          if (Constant.userAgent == "iOS") {
            Jockey.send("startAutoScrap", JSON.parse(bankInfo));
            Jockey.send("startAutoScrap", JSON.parse(cardInfo));
            Jockey.send("startAutoScrap", JSON.parse(ntsInfo));
          } else if (Constant.userAgent == "Android") {
            window.Android.startAutoScrap("bank", bankInfo);
            window.Android.startAutoScrap("card", cardInfo);
            window.Android.startAutoScrap("nts", ntsInfo);
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    updateFcLinkInfoAll: function() {
      console.log("updateFcLinkInfoAll");
      var _this = this;
      var formData = new FormData();
      // formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("yn_link", "N");
      formData.append("cd_link_stat", "99");
      formData.append(
        "rsn_link_message",
        "금융사 연동 정보가 초기화 됐습니다."
      );
      this.$http
        .post("/m/scrap/updateFcLinkInfoAll.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result == "00") {
            console.log("금융사 연동 정보가 초기화 됐습니다.");
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //Native Call Function
    resultHasCertPassword: function(isExist) {
      console.log("resultHasCertPassword : " + isExist);
      if (isExist == "true") {
        //IOS의 경우 상단스크래핑 상태 배너가 없어서 토스트로 대체
        // if (Constant.userAgent == "iOS") {
        //   this.$toast.center("데이터 업데이트를 시작하겠습니다.");
        // }
        this.getAutoScrapInfo();
        this.autoScrapCallback(true);
      } else {
        this.updateFcLinkInfoAll();
        this.autoScrapCallback(false);
      }
    }
  }
};
</script>
