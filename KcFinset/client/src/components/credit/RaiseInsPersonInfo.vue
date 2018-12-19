<template>
  <section v-if="seen">
    <div class="cert-check-wrap" v-if="scrap_code=='nhis' || scrap_code=='nps'">
      <p class="text">본인 확인을 위해<br>주민 등록 번호 뒷자리를 입력하여 주세요</p>
      <p class="title">이름</p>
      <input type="text" :value="personVO.nm_person">
      <p class="title">주민등록번호</p>
      <div class="grid">
        <div class="number"><input type="number" :value="personVO.ssn_person"></div>
        <div class="dash">-</div>
        <div class="number last"><input type="password" name="ssn2" id="ssn2" @click="showSecureKeypad()" placeholder="주민번호뒷자리" maxlength="7" autocomplete="off" readonly="readonly" v-validate="'required|length:7|max:7'" data-vv-name='주민번호뒷자리'></div>
      </div>
      <p class="warn" v-if="errors.has('주민번호뒷자리')">{{errors.first('주민번호뒷자리')}}</p>
    </div>
    <div class="cert-check-wrap" v-if="scrap_code=='nts'">
      <p class="title">가입형태</p>
      <multiselect class="multiselect-basic mt10" :title="'가입형태'" v-model="cert_division" label="text" :show-labels="false" :options="options_division" :searchable="false" :allow-empty="false" />
      <!-- <select v-model="cert_division">
        <option v-for="option in options" v-bind:key="option.value" v-bind:value="option.value">
          {{ option.text }}
        </option>
      </select> -->
      <p class="title">대상기간</p>
      <multiselect class="multiselect-basic mt10" :title="'대상기간'" v-model="inquiry_year" label="text" :show-labels="false" :options="options_year" :searchable="false" :allow-empty="false" />
      <!-- <select v-model="inquiry_year">
        <option v-for="year in inquiry_years" :key="year.index" :value=year>{{year}}년 </option>
      </select> -->
    </div>

    <div class="btn-wrap float" v-if="isShowButton">
      <a class="solid blue box" @click="checkExistCert()">확인</a>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
export default {
  name: "CreditRaiseInsPersonInfo",
  data() {
    return {
      seen: false,
      scrap_code: this.$route.params.scrap_code,
      isShowButton: false,
      encPwd: "",
      personVO: "",
      nhis_start_ym: "",
      nhis_end_ym: "",
      nps_start_ym: "",
      nps_end_ym: "",
      cert_division: { text: "근로소득자용", value: "1" },
      inquiry_year: {},
      options_division: [
        { text: "근로소득자용", value: "1" },
        { text: "연말정산한 사업소득자용", value: "2" },
        { text: "종합소득세 신고자용", value: "3" }
      ],
      options_year: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "본인 확인";
  },
  created() {
    // Native 연동 스크립트 등록
    window.resultKeypad = this.resultKeypad;
    window.resultCheckCert = this.resultCheckCert;
    window.resultCreditRatingUpgrade = this.resultCreditRatingUpgrade;

    // 국세청의 경우 확인 버튼 활성화
    if (this.scrap_code == "nts") {
      this.isShowButton = true;
    }
    this.getCreditSsnInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getCreditSsnInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditSsnInfo.json", {
          params: {}
        })
        .then(response => {
          var result = response.data;
          if (result != null) {
            _this.personVO = result.personVO;

            _this.nhis_start_ym = result.nhis_start_ym;
            _this.nhis_end_ym = result.nhis_end_ym;

            _this.nps_start_ym = result.nps_start_ym;
            _this.nps_end_ym = result.nps_end_ym;

            var list = result.inquiry_years;
            for (var i = 0; i < list.length; i++) {
              _this.options_year.push({
                text: list[i] + "년",
                value: list[i]
              });
            }
            _this.inquiry_year = { text: list[0] + "년", value: list[0] };

            _this.seen = true;
          } else {
            this.$toast.center(ko.messages.error);
          }
        });
    },
    frmSimpleDoc: function() {
      var scrapCode = this.scrap_code;
      var ssnPerson = "";

      // 주민번호 뒷자리 복호화 :국세청이 아닌경우 호출
      // TODO LoginController.getDecodedPassword에 사용자 본인만 접근할 수 있도록 제한해야함
      var _this = this;
      if (this.encPwd != "" && scrapCode != "nts") {
        var formData = new FormData();
        formData.append("encPwd", this.encPwd);
        this.$http
          .post("/m/login/getDecodedPassword.json", formData)
          .then(function(response) {
            var result = response.data;
            ssnPerson = _this.personVO.ssn_person + result.message;
            _this.creditRatingUpgrade(ssnPerson);
          });
      } else if (scrapCode == "nts") {
        this.creditRatingUpgrade(ssnPerson);
      }
    },
    creditRatingUpgrade: function(ssnPerson) {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        Jockey.on("resultCreditRatingUpgrade", function(param) {
          resultCreditRatingUpgrade(param.result, param.scrapCode);
        });
        Jockey.send("creditRatingUpgrade", {
          scrapCode: _this.scrap_code,
          noPerson: _this.personVO.no_person,
          nmPerson: _this.personVO.nm_person,
          ssnPerson: ssnPerson,
          nhisStartYm: _this.nhis_start_ym,
          nhisEndYm: _this.nhis_end_ym,
          certDivision: _this.cert_division.value,
          ntsStartIncomeY: _this.inquiry_year.value,
          ntsEndIncomeY: _this.inquiry_year.value,
          npsStartYm: _this.nps_start_ym,
          npsEndYm: _this.nps_end_ym
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.creditRatingUpgrade(
          _this.scrap_code,
          _this.personVO.no_person,
          _this.personVO.nm_person,
          ssnPerson,
          _this.nhis_start_ym,
          _this.nhis_end_ym,
          _this.cert_division.value,
          _this.inquiry_year.value,
          _this.inquiry_year.value,
          _this.nps_start_ym,
          _this.nps_end_ym
        );
      }
    },
    checkExistCert: function() {
      var scrapCode = this.scrap_code;
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (Constant.userAgent == "iOS") {
            //공인인증서 유무 체크 결과 콜백 이벤트
            Jockey.on("resultCheckCert", function(param) {
              var iscert = "false";
              if (param.isCert == 1) iscert = "true";
              _this.resultCheckCert(iscert);
            });
            Jockey.send("checkExistCert");
          } else if (Constant.userAgent == "Android") {
            window.Android.checkExistCert();
          }
        }
      });
    },
    showSecureKeypad: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        Jockey.send("showSecureKeypad", {
          keypadType: "numeric",
          maxInputLength: 7,
          subTitle: "주민등록번호 뒷자리",
          placeholderText: "숫자를 입력하세요."
        });
        //보안키패드 결과값 수신 콜백 이벤
        Jockey.on("resultKeypad", function(param) {
          _this.resultKeypad(param.encPwd);
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.showSecureKeypad("numeric", 7, 7, "주민등록번호 뒷자리");
      }
    },
    resultKeypad: function(encPwd) {
      if (encPwd != null && encPwd != "") {
        $("#ssn2").val("1111111"); // 임의의 숫자 7자 입력
        this.encPwd = encPwd;
      } else {
        $("#ssn2").val("");
        this.encPwd = "";
      }
      this.isShowButton = true;
    },
    resultCheckCert: function(isCert) {
      if (isCert == "true") {
        // 공인인증서가 있을 경우
        this.frmSimpleDoc();
      } else {
        // 공인인증서가 없을 경우
        this.$dialogs
          .alert("공인인증서가 없습니다.", Constant.options)
          .then(res => {
            this.$router.push("/credit/raiseMain");
          });
      }
    },
    resultCreditRatingUpgrade: function(result, scrapCode) {
      var url = "/credit/raiseMain";
      if (result == "true") {
        switch (scrapCode) {
          case "nhis":
            url = "/credit/raiseNhis";
            break;
          case "nts":
            url = "/credit/raiseNts";
            break;
          case "nps":
            url = "/credit/raiseNps";
            break;
        }
      } else if (result == "empty") {
        url = "/credit/raiseResult";
      }
      this.$router.push(url);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
