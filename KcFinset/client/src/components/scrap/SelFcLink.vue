<template>
  <section>
    <div class="container mt30">
      <h3>연동 금융사를 선택해주세요.</h3>
      <div class="checks grid2 mt10">
        <p><input type="checkbox" id="chk1" :checked="isCheckBank" @click="clickCheck('bank')"><label for="chk1">은행</label></p>
        <p><input type="checkbox" id="chk2" :checked="isCheckCard" @click="clickCheck('card')"><label for="chk2">카드</label></p>
        <p class="mt10"><input type="checkbox" id="chk3" :checked="isCheckStock" @click="clickCheck('stock')"><label for="chk3">증권</label></p>
        <p class="mt10"><input type="checkbox" id="chk4" :checked="isCheckNts" @click="clickCheck('nts')"><label for="chk4">국세청</label></p>
      </div>

      <div class="cert-wrap" v-if="isCheckStock">
        <p class="mt40">증권사 연계를 위하여 이메일입력과 정보제공 동의가 필요합니다.</p>
        <h3 class="mt15">이메일</h3>
        <input type="text" class="mt15" v-model="emailtext" v-validate="'required'" autocomplete="off" placeholder="이메일을 입력하세요" data-vv-name='이메일'>
        <p class="warn" v-if="errors.has('이메일')">{{errors.first('이메일')}}</p>

        <div class="checks">
          <div class="box-agree solo">
            <p><input type="checkbox" id="chk5" :checked="isCheckCert" @click="clickCheckCert()"><label for="chk5">[필수] 금융정보 제공동의서</label></p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="btn-next" @click="clickNext()">다음</a>
    </div>
  </section>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "",
  data() {
    return {
      isCheckBank: true,
      isCheckCard: true,
      isCheckStock: true,
      isCheckNts: true,
      isCheckCert: false,
      isGetCertContent: false,
      emailtext: "",
      uuid: "",
      financeTerms: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금융사 연동";
  },
  created() {
    window.resultCheckDevicesUUID = this.resultCheckDevicesUUID;
    window.resultCertSignInfo = this.resultCertSignInfo;
    this.checkUUID();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickCheck: function(type) {
      switch (type) {
        case "bank":
          this.isCheckBank = !this.isCheckBank;
          break;
        case "card":
          this.isCheckCard = !this.isCheckCard;
          break;
        case "stock":
          this.isCheckStock = !this.isCheckStock;
          break;
        case "nts":
          this.isCheckNts = !this.isCheckNts;
          break;
      }
    },
    clickCheckCert: function(type) {
      var _this = this;
      this.isCheckCert = !this.isCheckCert;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (_this.isCheckCert && !_this.isGetCertContent) {
            _this.getTermsContent();
          }
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
    },
    clickNext: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          // 증권 연동이 아닐경우 로딩페이지로 이동
          if (_this.isCheckStock == false) {
            _this.nextStep();
          }
          // 증권연동 - 동의서 체크여부
          else if (_this.isCheckCert) {
            // 증권연동 - 동의서 다운여부
            if (_this.isGetCertContent) {
              _this.getCertSignInfo();
            } else {
              this.$toast.center("금융정보 제공동의서를 가져오지 못했습니다.");
            }
          } else {
            this.$toast.center("금융정보 제공동의서를 체크해주세요.");
          }
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
    },
    //금융정보제공동의서 조회
    getTermsContent: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("uuid", this.uuid);
      formData.append("dn", this.$route.params.dn);
      formData.append("email", this.emailtext);
      this.spinnerIsVisible = true; // 시작시 Spinner 보여주기
      this.$http
        .post("/m/scrap/getTermsContent.json", formData)
        .then(function(response) {
          if (response.data) {
            _this.isGetCertContent = true;
            _this.financeTerms = response.data.financeTerms;
          } else {
            this.$toast.center(ko.messages.error);
          }
          _this.spinnerIsVisible = false;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    sendTermsContent: function(jwsInfo) {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("uuid", this.uuid);
      formData.append("dn", this.$route.params.dn);
      formData.append("email", "test@gmail.com");
      formData.append("financeTerms", this.financeTerms);
      formData.append("jwsInfo", jwsInfo);

      this.$http
        .post("/m/scrap/sendTermsContent.json", formData)
        .then(function(response) {
          console.log(response.data);
          _this.nextStep();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    nextStep: function() {
      var _this = this;
      this.$router.push({
        name: "scrapLoading",
        params: {
          dn: this.$route.params.dn,
          cn: this.$route.params.cn,
          normalMessage: "연동 가능한 금융사를<br>확인 중 입니다.",
          smallMessage: "잠시만 기다려주세요.",
          isCheckBank: _this.isCheckBank,
          isCheckCard: _this.isCheckCard,
          isCheckStock: _this.isCheckStock,
          isCheckNts: _this.isCheckNts
        }
      });
    },
    // 인증정보 가져오기
    getCertSignInfo: function() {
      var _this = this;
      var financeTerms = JSON.parse(this.financeTerms);
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCertSignInfo", function(param) {
          _this.resultCertSignInfo(param.jwsInfo);
        });
        Jockey.send("getCertSignInfo", {
          payload: financeTerms.text
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.getCertSignInfo(financeTerms.text);
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCertSignInfo: function(jwsInfo) {
      this.sendTermsContent(jwsInfo);
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
    // UUID 체크 결과(모바일에서 호출)
    resultCheckDevicesUUID: function(uuid) {
      this.uuid = uuid;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
