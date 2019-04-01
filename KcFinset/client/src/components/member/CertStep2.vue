<template>
  <div>
    <!-- Content -->
    <section id="content">
      <div class="cert-wrap">
        <p class="title">회원정보</p>
        <input type="text" class="form-control" name="nm_person" id="nm_person" ref="nmperson" v-model="nm_person" v-validate="'required|max:8'" v-bind:disabled="isDisabled" autocomplete="off" placeholder="이름을 입력하세요" data-vv-name='이름' />
        <p class="warn" v-if="errors.has('이름')">{{errors.first('이름')}}</p>
        <div class="grid">
          <div class="number"><input type="number" placeholder="생년월일6자리" name="ssn_birth" id="ssn_birth" v-model="ssn_birth" v-validate="'required|length:6|max:6'" v-bind:disabled="isDisabled" autocomplete="off" data-vv-name='생년월일'></div>
          <div class="dash">-</div>
          <div class="number last"><input type="password" pattern="[0-9]*" name="sex" id="sex" v-model="sex" inputmode="numeric" maxlength="1" style="-webkit-text-security:disc" v-bind:disabled="isDisabled" autocomplete="off" v-validate="'required|between:0,9|length:1|max:1'" data-vv-name='성별'>******</div>
        </div>
        <p class="warn" v-if="errors.has('생년월일')">{{errors.first('생년월일')}}</p>
        <p class="warn" v-if="errors.has('성별')">{{errors.first('성별')}}</p>
      </div>
      <div class="cert-wrap">
        <p class="title">휴대폰인증</p>
        <div class="grid phone">
          <multiselect v-bind:disabled="isDisabled" ref="telCom" v-model="telCom" label="text" :title="'통신사'" placeholder="통신사" :options="options" v-validate="'required'" data-vv-name='통신사'>
          </multiselect>
          <input type="tel" name="hp" id="hp" v-model="hp" v-validate="'required|max:11'" v-bind:disabled="isDisabled" placeholder="휴대폰 번호" data-vv-name='휴대폰 번호'>
        </div>
        <p class="warn" v-if="errors.has('통신사')">{{errors.first('통신사')}}</p>
        <p class="warn" v-if="errors.has('휴대폰 번호')">{{errors.first('휴대폰 번호')}}</p>
        <button id="req_certification" v-on:click="kcmRequestCertNo()">인증번호 전송</button>
        <div class="cert-num" id="cert_no_conteiner">
          <input type="number" name="smsCertNo" id="smsCertNo" v-model="smsCertNo" placeholder="인증번호를 입력하세요" autocomplete="off" readonly>
          <p class="time" id="countdown" aria-hidden="true">{{ timer }}</p>
        </div>
        <p class="warn" id="certNoWarning" name="certNoWarning" v-if="timer==='00:00'">인증번호를 재전송 해주세요.</p>
      </div>
      <div class="btn-wrap float" id="confirmed_div" v-if="smsCertNo&&timer!='00:00'">
        <a id="confirmed_certify" class="btn-next" v-on:click="confirmedCertify()">다음</a>
      </div>
    </section>
  </div>
</template>
<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certStep2",
  props: {},
  data() {
    return {
      errMsg: "",
      /* form */
      nm_person: "",
      ssn_birth: "",
      birthday: "",
      sex: "",
      telCom: null,
      hp: "",
      kcb_ci: "",
      kcb_di: "",
      kcb_cp: "",
      bgn: "",
      smsCertNo: "",
      svcTxSeqno: "",
      /* form etc */
      smsReSndYn: "",
      nation: "1",
      options: [
        { text: "SKT", value: "01" },
        { text: "KT", value: "02" },
        { text: "LG", value: "03" },
        { text: "SK알뜰폰", value: "04" },
        { text: "KT알뜰폰", value: "05" },
        { text: "LG알뜰폰", value: "06" }
      ],
      timeId: "",
      timerObj: null,
      timer: null,
      minutes: 3,
      secondes: 0,
      time: 0,
      /* class */
      isDisabled: false,
      /* changeLoginDB 함수 사용시 필요 */
      yn_logout: "",
      yn_use: ""
    };
  },
  watch: {
    ssn_birth: function() {
      if ((this.sex == null || this.sex == "") && this.ssn_birth.length >= 6) {
        $("#sex").focus();
      }
    },
    sex: function() {
      var _this = this;
      if ((this.telCom == null || this.telCom == "") && this.sex.length > 0) {
        $("#sex").blur();
        setTimeout(function() {
          _this.$refs.telCom.open();
        }, 300);
      }
    },
    telCom: function() {
      if ((this.hp == null || this.hp == "") && this.telCom != null) {
        $("#hp").focus();
      }
    }
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    window.Android.setEndApp("Y");

    window.setCertNumber = this.setCertNumber;
    window.setRequestPhoneNumber = this.setRequestPhoneNumber;
    if (Constant.userAgent == "Android") {
      window.Android.reqSMSPermission();
    }
    this.$store.state.title = "본인확인";
    this.time = this.minutes * 60;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {
    if (this.smsCertNo) window.scrollTo(0, window.innerHeight);
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //native call back
    setCertNumber: function(number) {
      number = number + "";
      if (this.smsCertNo.length == 0) {
        this.smsCertNo = number;
      }
    },
    //native call back
    setRequestPhoneNumber: function(phoneNumber) {
      this.hp = phoneNumber;
    },
    /**
     * 인증번호 요청
     */
    kcmRequestCertNo: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          if (_this.sex == "1" || _this.sex == "2") {
            _this.birthday = "19" + _this.ssn_birth;
          } else {
            _this.birthday = "20" + _this.ssn_birth;
          }

          var formData = new FormData();
          formData.append("nm_person", _this.nm_person);
          formData.append("birthday", _this.birthday);
          formData.append("sex", _this.sex);
          formData.append("telComCd", _this.telCom.value);
          formData.append("hp", _this.hp);
          formData.append("svcTxSeqno", _this.svcTxSeqno);
          formData.append("smsReSndYn", _this.smsReSndYn);
          formData.append("nation", _this.nation);
          this.$http
            .post("/m/login/kcmRequestCertNo.json", formData, {
              headers: {
                async: false,
                "Content-Type":
                  "application/x-www-form-urlencoded; charset=UTF-8"
              }
            })
            .then(response => {
              var result = response.data;
              console.log(result);
              if (result.result == "00") {
                _this.smsCertNo = "";
                this.$toast.center(result.message);
                if (_this.timeId != "") {
                  clearTimeout(_this.timeId);
                } else {
                  _this.smsReSndYn = "Y";
                }
                //이름 주민번호 앞 뒤 통신사선택 disable
                _this.isDisabled = true;

                if (!this.timerObj) this.stop();
                this.start();
                $("#req_certification").html("인증번호 재전송");
                _this.svcTxSeqno = result.svcTxSeqno;
                $("#smsCertNo").removeAttr("readonly");
                $("#smsCertNo").focus();
              } else if (result.result == "11") {
                this.$toast.center(result.message);
                _this.$router.push("/member/certStep1");
                return false;
              } else if (result.result == "01") {
                this.$toast.center(result.message);
              }
            });
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
    },
    /*
     * 인증 번호 확인
     */
    confirmedCertify: function() {
      var _this = this;

      if (this.smsCertNo == "" || this.smsCertNo == "undefined") return false;

      var formData = new FormData();
      formData.append("svcTxSeqno", _this.svcTxSeqno);
      formData.append("hp", _this.hp);
      formData.append("smsCertNo", _this.smsCertNo);
      this.$http.post("/m/login/kcmCertify.json", formData).then(response => {
        var result = response.data;
        console.log(result);
        if (result.result == "00") {
          _this.kcb_ci = result.kcb_ci;
          _this.kcb_di = result.kcb_di;
          _this.kcb_cp = result.kcb_cp;

          //기존 회원 여부 체크
          if (result.no_person) {
            Constant.params.hp = _this.hp;

            if (Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson", {
                noPerson: result.no_person,
                phNum: _this.hp
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.setNoPerson(result.no_person, _this.hp);
            }

            _this.$toast.center(
              "고객님은 기존 회원이므로 로그인 페이지로 이동합니다."
            );
            setTimeout(function() {
              _this.$router.push("/home?hp=" + _this.hp);
            }, 700);
          } else {
            _this.insertPerson();
          }
        } else {
          this.$toast.center(result.message);
          _this.smsCertNo = "";
          if (!_this.timerObj) _this.stop();
          return false;
        }
      });
    },
    insertPerson: function() {
      var _this = this;
      _this.bgn = _this.birthday + _this.sex;
      var formData = new FormData();
      formData.append("nm_person", _this.nm_person);
      formData.append("bgn", _this.bgn);
      formData.append("birthday", _this.birthday);
      formData.append("telComCd", _this.telCom.value);
      formData.append("hp", _this.hp);
      formData.append("kcb_ci", _this.kcb_ci);
      formData.append("kcb_di", _this.kcb_di);
      formData.append("kcb_cp", _this.kcb_cp);
      formData.append(
        "yn_eventPush",
        this.$store.state.user.isEventPush ? "Y" : "N"
      );
      formData.append("yn_logout", _this.yn_logout);

      this.$http
        .post("/m/person/insertPerson.json", formData)
        .then(response => {
          var result = response.data;
          var noPerson = result.no_person;
          this.$store.state.user.noPerson = result.no_person;
          this.$store.state.user.nmPerson = result.nm_person;
          if (result.result == "00") {
            //joinSuccess
            if (Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson", {
                noPerson: noPerson,
                phNum: this.hp
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.setNoPerson(noPerson, this.hp);
              window.Android.registerAdbrix(noPerson);
            }
            _this.$router.push("/member/certCode");
          } else if (result.result == "11") {
            //loginSuccess
            //  changeLoginDB();
            if (Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson", {
                noPerson: noPerson,
                phNum: _this.hp
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.setNoPerson(noPerson, _this.hp);
            }

            // _this.$toast.center(
            //   "고객님은 기존 회원이므로 로그인 페이지로 이동합니다."
            // );
            // _this.$router.push("/home?hp=" + _this.hp);
          }
        });
    },
    start: function() {
      var _this = this;
      $("#smsCertNo").removeAttr("readOnly");
      _this.time = _this.minutes * 60 + _this.secondes;
      console.log(_this.time);
      console.log(this.timerObj);
      if (!this.timerObj) {
        this.timerObj = setInterval(() => {
          if (_this.time > 0) {
            _this.time--;
            _this.timer = this.prettyTime();
          } else if (_this.time == 0) {
            $("#smsCertNo").attr("readonly", "true");
            // _this.smsCertNo="";
          } else {
            clearInterval(_this.timerObj);
            _this.reset();
          }
        }, 1000);
      }
    },
    stop() {
      clearInterval(this.timerObj);
      this.timerObj = null;
      this.timer = null;
    },
    reset: function() {
      this.time = 0;
      this.secondes = 0;
      this.minutes = 3;
    },
    prettyTime: function() {
      let time = this.time / 60;
      let minute = parseInt(time);
      let seconde = Math.round((time - minute) * 60);
      return this.twoDigits(minute) + ":" + this.twoDigits(seconde);
    },
    twoDigits: function(n) {
      return n <= 9 ? "0" + n : n;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>