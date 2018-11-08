<template>
  <div class="loading">
    <span v-html=normalMessage></span><br><small v-html=smallMessage></small>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "loading",
  data() {
    return {
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
  beforeCreate() {},
  created() {
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
  },
  beforeMount() {},
  mounted() {
    this.checkFcCertList();
    if (this.$route.params.isCheckStock) {
      this.checkStCertList();
    } else {
      this.isScrapStList = true;
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
          cn: this.$route.params.cn
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
            Jockey.on("checkAvaliableScrapList", function(param) {
              _this.frmFcListNextFromMobile();
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
          _this.$store.state.token = returnData.token;
          if (_this.isScrapFcList && _this.isScrapStList) {
            _this.nextStep();
          }
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>