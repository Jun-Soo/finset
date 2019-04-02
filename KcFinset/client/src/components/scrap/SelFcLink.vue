<template>
  <div>
    <section>
      <div class="container mt30">
        <h3 v-if="isSingle!=true">연동 금융사를 선택해주세요.</h3>
        <div class="checks grid2 mt10" v-if="isSingle!=true">
          <!-- 금보원 관련 수정 -->
          <!-- <p><input type="checkbox" id="chk1" :checked="isCheckBank" @click="clickCheck('bank')"><label for="chk1">은행</label></p> -->
          <!-- <p><input type="checkbox" id="chk2" :checked="isCheckCard" @click="clickCheck('card')"><label for="chk2">카드</label></p> -->
          <!-- <p v-if="showStock" class="mt10"><input type="checkbox" id="chk3" :checked="isCheckStock" @click="clickCheck('stock')"><label for="chk3">증권</label></p> -->
          <!-- <p class="mt10"><input type="checkbox" id="chk4" :checked="isCheckNts" @click="clickCheck('nts')"><label for="chk4">국세청</label></p> -->
        </div>

        <div class="pb90" v-if="isCheckStock">
          <p class="mt40">증권사 연계를 위하여 이메일입력과 정보제공 동의가 필요합니다.</p>
          <h3 class="mt15">이메일</h3>
          <input type="text" class="mt15" v-model="emailtext" v-validate="'required'" autocomplete="off" placeholder="이메일을 입력하세요" data-vv-name='이메일'>
          <p class="warn" v-if="errors.has('이메일')">{{errors.first('이메일')}}</p>

          <div class="checks">
            <div class="box-agree solo">
              <p @click="clickShowCert()"><input type="checkbox" id="chk5" :checked="isCheckCert"><label @click="clickCheckCert($event)">[필수] 금융정보 제공동의서</label></p>
            </div>
          </div>
        </div>

      </div>

      <div class="btn-wrap float" v-if="showButton">
        <a class="btn-next" @click="clickNext()">다음</a>
      </div>
    </section>
    <vue-modal transitionName="zoom-in" name="my-modal" v-on:popclose="closePop()">
      <Terms slot="body" v-on:popclose="closePop()" :text="financeTermsText"></Terms>
    </vue-modal>
  </div>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
import Constant from "./../../assets/js/constant.js";
import Terms from "./Terms.vue";

export default {
  name: "",
  data() {
    return {
      // 금보원 관련 수정
      isCheckBank: false,
      isCheckCard: false,
      isCheckStock: true, //증권은 추후 서비스 제공시에 true로 변경 필요
      isCheckNts: false,
      isCheckCert: false,
      showButton: true,
      isGetCertContent: false,
      emailFromDB: "",
      emailtext: "",
      uuid: "",
      financeTerms: "",
      financeTermsText: "",
      showStock: false,
      //단일 금융사 관련
      isSingle: this.$route.params.isSingle,
      agency: this.$route.params.agency,
      cd_coocon: this.$route.params.cd_coocon
    };
  },
  components: {
    Terms: Terms
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금융사 연동";
  },
  created() {
    window.resultCheckDevicesUUID = this.resultCheckDevicesUUID;
    window.resultCertSignInfo = this.resultCertSignInfo;
    this.checkUUID();
    this.getPersonEmail();
  },
  beforeMount() {},
  mounted() {
    if (Constant.tester.indexOf(this.$store.state.user.hp) > -1) {
      this.showStock = true;
      this.isCheckStock = true;
    } else {
      this.showStock = false;
    }
    if (this.isSingle) {
      this.isCheckBank = false;
      this.isCheckCard = false;
      this.isCheckEtc = false;
      this.isCheckStock = true;
    }
    this.checkButton();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getPersonEmail() {
      var _this = this;
      this.$http.get("/m/person/getPersonEmail.json").then(function(response) {
        //console.log("response.data.email : " + response.data.email);
        _this.emailFromDB = response.data.email || "";
        //console.log("_this.emailFromDB : " + _this.emailFromDB);
        if (_this.emailFromDB != "") {
          _this.emailtext = _this.emailFromDB;
        }
      });
    },
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
      this.checkButton();
    },
    checkButton: function() {
      if (
        !this.isCheckBank &&
        !this.isCheckCard &&
        !this.isCheckStock &&
        !this.isCheckNts
      ) {
        this.showButton = false;
      } else {
        this.showButton = true;
      }
    },
    clickCheckCert: function(event) {
      event.stopPropagation();
      //console.log(event);
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (!_this.isGetCertContent) {
            _this.getTermsContent(false);
          }
          _this.isCheckCert = !_this.isCheckCert;
        } else {
          _this.$toast.center(ko.messages.require);
        }
      });
    },
    clickShowCert: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (!_this.isGetCertContent) {
            _this.getTermsContent(true);
          } else {
            _this.openPop();
          }
        } else {
          _this.$toast.center(ko.messages.require);
        }
      });
    },
    clickNext: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (_this.emailtext != _this.emailFromDB) {
            _this.updateEmail();
          }

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
              _this.$toast.center("금융정보 제공동의서를 가져오지 못했습니다.");
            }
          } else {
            _this.$toast.center("금융정보 제공동의서를 체크해주세요.");
          }
        } else {
          _this.$toast.center(ko.messages.require);
        }
      });
    },
    updateEmail: function() {
      var _this = this;
      var formData = new FormData();
      //console.log("updateEmail call");
      formData.append("email", this.emailtext);
      this.$http
        .post("/m/person/modifyPersonEmail.json", formData)
        .then(function(response) {
          var result = response.data.result;
          //console.log("updateEmail result : " + result);
        });
    },
    //금융정보제공동의서 조회
    getTermsContent: function(isShow) {
      var _this = this;
      var formData = new FormData();
      formData.append("uuid", this.uuid);
      formData.append("dn", this.$route.params.dn);
      formData.append("email", this.emailtext);
      //단일 금융사 조회 일 경우에만 추가
      if (this.isSingle) {
        formData.append("cd_fc", this.cd_coocon);
      }
      _this.$store.state.isLoading = true; // 시작시 Spinner 보여주기
      this.$http
        .post("/m/scrap/getTermsContent.json", formData)
        .then(function(response) {
          _this.$store.state.isLoading = false;
          if (response.data && response.data.result == "00") {
            _this.isGetCertContent = true;
            _this.financeTerms = response.data.financeTerms;
            var financeTerms = JSON.parse(_this.financeTerms);
            _this.financeTermsText = financeTerms.text;

            if (isShow) {
              _this.openPop();
            }
          } else {
            _this.$toast.center("금융정보제공동의서 조회를 실패하였습니다.");
          }
        })
        .catch(e => {
          _this.$store.state.isLoading = false;
          _this.$toast.center(ko.messages.error);
        });
    },
    sendTermsContent: function(jwsInfo) {
      var _this = this;
      var formData = new FormData();
      formData.append("uuid", this.uuid);
      formData.append("dn", this.$route.params.dn);
      formData.append("email", this.emailtext);
      formData.append("financeTerms", this.financeTerms);
      formData.append("jwsInfo", jwsInfo);
      //formData.append("jwsInfo", '{"jwsinfo" : ""}');
      _this.$store.state.isLoading = true; // 시작시 Spinner 보여주기
      this.$http
        .post("/m/scrap/sendTermsContent.json", formData)
        .then(function(response) {
          //console.log(response.data);
          _this.$store.state.isLoading = false;
          if (response.data && response.data.result == "00") {
            _this.nextStep();
          } else {
            _this.$toast.center(
              "금융정보제공동의서 전자서명을 실패하였습니다."
            );
          }
        })
        .catch(e => {
          _this.$store.state.isLoading = false;
          this.$toast.center(ko.messages.error);
        });
    },
    nextStep: function() {
      var _this = this;
      this.$router.push({
        name: "scrapLoading",
        params: {
          isSignup: this.$route.params.isSignup, //초기 등록 여부
          dn: this.$route.params.dn,
          cn: this.$route.params.cn,
          normalMessage: "연동 가능한 금융사를<br>확인 중 입니다.",
          smallMessage: "잠시만 기다려주세요.",
          isCheckBank: _this.isCheckBank,
          isCheckCard: _this.isCheckCard,
          isCheckStock: _this.isCheckStock,
          isCheckNts: _this.isCheckNts,
          //단일 금융사 관련
          isSingle: _this.isSingle,
          agency: _this.agency,
          cd_coocon: _this.cd_coocon
        }
      });
    },
    closePop: function() {
      var _this = this;
      _this.$modals.hide("my-modal");
    },
    openPop: function() {
      var _this = this;
      _this.$modals.show("my-modal");
    },
    // 인증정보 가져오기
    getCertSignInfo: function() {
      var _this = this;
      var financeTerms = JSON.parse(this.financeTerms);
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCertSignInfo", function(param) {
          _this.resultCertSignInfo(param.jwsInfo);
          Jockey.off("resultCertSignInfo");
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
          Jockey.off("resultCheckDevicesUUID");
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
