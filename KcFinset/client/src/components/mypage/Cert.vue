<template>
  <section>
    <div class="alarm-setting">
      <ul>
        <li>
          <a @click="importCert" class="block">
            <p><em>인증서 가져오기</em>PC에 있는 인증서를 휴대폰으로 복사</p>
          </a>
        </li>
        <li>
          <a @click="goChangePwd" class="block">
            <p><em>비밀번호 변경</em>휴대폰 보인 확인 필요</p>
          </a>
        </li>
        <li v-if="fingerSettingSeen">
          <p><em>지문인증 설정</em>비밀번호없이 지문으로 인증</p>
          <p><button v-bind:class="yn_fingerprint=='Y'?btnOn:btnOff" @click="modifyFingerPrint"></button></p>
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "MypageCert",
  data() {
    return {
      personVo: new Array(),
      btnOn: "btn-onoff on",
      btnOff: "btn-onoff",
      yn_fingerprint: this.$store.state.user.ynFingerprint,
      fingerSettingSeen: "true"
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "인증/보안";
  },
  created() {
    if (Constant.userAgent == "Android") {
      window.Android.checkFingerPrint();
    } else if (Constant.userAgent == "iOS") {
      //지문인식 가능여부 체크 결과 콜백 이벤트
      Jockey.on("resultCheckFingerPrint", function(param) {
        resultCheckFingerPrint(param.result);
      });

      Jockey.send("checkFingerPrint");
    }

    if (Constant.userAgent == "Android") {
      //물어보깅
      window.Android.backKeySendUrl(
        "/m/customercenter/getCustomerCenterMain.json"
      );
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    modifyFingerPrint: function(obj) {
      let _this = this;
      if (obj.target.className == _this.btnOff) {
        //킬때
        this.$router.push("/mypage/regCertLogin");
      } else {
        //끌때
        _this.yn_fingerprint = "N";
        _this.$store.state.user.ynFingerprint = "N";
        let form = new FormData();
        form.append("yn_fingerprint", _this.yn_fingerprint);
        _this.$http
          .post("/m/person/modifyFingerPrint.json", form, {
            header: {
              contentType: "application/x-www-form-urlencoded; charset=UTF-8",
              async: false
            }
          })
          .then(response => {})
          .catch(e => {
            this.$toast.center(ko.messages.error);
          });
      }
    },
    importCert: function() {
      if (Constant.userAgent == "Android") {
        window.Android.importCert();
      } else if (Constant.userAgent == "iOS") {
        Jockey.send("getCertVCEasy");
      } else {
        //
      }
    },
    goChangePwd: function() {
      this.$router.push("/mypage/certPerson");
    },
    /**
     * NATIVE CALL BACK
     */
    resultCheckFingerPrint: function(result) {
      if (result == true || result == 1) {
        fingerSettingSeen = true;
      } else {
        fingerSettingSeen = false;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
