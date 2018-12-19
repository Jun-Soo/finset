<template>
  <div class="loading">
    <span v-html="normalMessage"></span><br><small v-html="smallMessage"></small>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "loading",
  data() {
    return {
      isSingle: this.$route.params.isSingle,
      noPerson: this.$store.state.user.noPerson,
      nmPerson: this.$store.state.user.nmPerson,
      dn: this.$route.params.dn,
      normalMessage: this.$route.params.normalMessage,
      smallMessage: this.$route.params.smallMessage,
      isScrapFcList: false,
      isScrapStList: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "";
    this.$store.state.title = "금융사 연동";
  },
  created() {
    window.resultUpdateScrapInfo = this.resultUpdateScrapInfo;
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
  },
  beforeMount() {},
  mounted() {
    //단일 금융사 연동 체크
    if (this.isSingle) {
      if (this.$route.params.agency == "stock") {
        this.checkStUpdate();
      } else {
        this.checkFcUpdate();
      }
    }
    //전체 금융사 연동 체크
    else {
      this.checkFcCertList();
      if (this.$route.params.isCheckStock) {
        this.checkStCertList();
      } else {
        this.isScrapStList = true;
      }
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    nextStep: function() {
      this.$router.push({
        name: "scrapRegFcLink",
        params: {
          cn: this.$route.params.cn,
          dn: this.$route.params.dn
        }
      });
    },
    //자동스크래핑 가능 금융사 조회
    checkFcCertList: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.noPerson);
      this.$http
        .post("/m/scrap/getScrapFcList.json", formData)
        .then(function(response) {
          //각 금융사 그룹별 체크여부에 따라 연동 여부 체크
          if (_this.$route.params.isCheckBank) {
            _this.$store.state.bankCode = response.data.bank_code;
          }
          if (_this.$route.params.isCheckCard) {
            _this.$store.state.cardCode = response.data.card_code;
          }
          if (_this.$route.params.isCheckNts) {
            _this.$store.state.etcCode = "nts";
          }

          if (Constant.userAgent == "iOS") {
            Jockey.on("resultCheckAvaliableScrapList", function(param) {
              _this.resultCheckAvaliableScrapList();
            });
            Jockey.send("checkAvaliableScrapList", {
              noPerson: _this.$store.state.user.noPerson,
              bankCode: _this.$store.state.bankCode,
              cardCode: _this.$store.state.cardCode,
              etcCode: _this.$store.state.etcCode
            });
          } else if (Constant.userAgent == "Android") {
            window.Android.checkAvaliableScrapList(
              _this.$store.state.user.noPerson,
              _this.$store.state.bankCode,
              _this.$store.state.cardCode,
              _this.$store.state.etcCode
            );
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //자동스크래핑 가능 증권사 조회
    checkStCertList: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.noPerson);
      formData.append("uuid", this.uuid);
      formData.append("dn", _this.dn);
      this.$http
        .post("/m/scrap/getScrapStList.json", formData)
        .then(function(response) {
          var returnData = response.data;
          _this.isScrapStList = true;
          if (_this.isScrapFcList && _this.isScrapStList) {
            _this.nextStep();
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    checkFcUpdate: function() {
      var _this = this;
      console.log("checkFcUpdate start1");
      if (Constant.userAgent == "iOS") {
        console.log("checkFcUpdate start2");
        Jockey.on("resultUpdateScrapInfo", function(param) {
          _this.resultUpdateScrapInfo(param.cd_err, param.msg_err);
        });
        Jockey.send("updateAvaliableCertScrapInfo", {
          noPerson: _this.noPerson,
          agency: _this.$route.params.agency,
          cdCoocon: _this.$route.params.cd_coocon,
          nmPerson: _this.nmPerson
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.updateAvaliableCertScrapInfo(
          _this.noPerson,
          _this.$route.params.agency,
          _this.$route.params.cd_coocon,
          _this.nmPerson
        );
      }
    },
    checkStUpdate: function() {
      var _this = this;
      var isScrap = true;
      var formData = new FormData();
      formData.append("no_person", this.noPerson);
      formData.append("uuid", this.uuid);
      formData.append("dn", _this.dn);
      formData.append("cd_fc", _this.$route.params.cd_coocon);
      this.$http
        .post("/m/scrap/getScrapStList.json", formData)
        .then(function(response) {
          var returnData = response.data;
          if (returnData.cd_err == "00") {
            _this.$toast.center("금융사 연동이 완료되었습니다.");
            isScrap = true;
          } else {
            _this.$toast.center(
              "금융사 연동이 실패하였습니다. </br>(" + returnData.msg_err + ")"
            );
            isScrap = false;
          }
          //금융사 연동 관리화면으로 이동
          setTimeout(function() {
            //_this.$router.push("/scrap/ctrlFcLink");
            _this.$router.push({
              name: "scrapCtrlFcLink",
              query: {
                isScrap: isScrap
              }
            });
          }, 1000);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    /***
     * Native Call function
     ***/
    //자동 스크래핑 등록 완료 시 (모바일에서 호출)
    resultCheckAvaliableScrapList: function() {
      this.isScrapFcList = true;
      if (this.isScrapFcList && this.isScrapStList) {
        this.nextStep();
      }
    },
    resultUpdateScrapInfo: function(cd_err, msg_err) {
      // 금융정보제공동의서 확인여부 체크 필요
      console.log("resultUpdateScrapInfo called");
      var _this = this;
      var isScrap = true;
      if (cd_err == "00000000") {
        this.$toast.center("금융사 연동이 완료되었습니다.");
        isScrap = true;
      } else {
        this.$toast.center(
          "금융사 연동이 실패하였습니다. </br>(" + msg_err + ")"
        );
        isScrap = false;
      }
      //금융사 연동 관리화면으로 이동
      setTimeout(function() {
        //_this.$router.push("/scrap/ctrlFcLink");
        _this.$router.push({
          name: "scrapCtrlFcLink",
          query: {
            isScrap: isScrap
          }
        });
      }, 1000);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>