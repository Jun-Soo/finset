<template>
  <div>
    <!-- Content -->
    <section id="content">
      <div class="cert-wrap">
        <p class="title">회원정보</p>
        <input type="text" class="form-control" name="nm_person" id="nm_person" v-model="nm_person" v-validate="'required|max:8'" v-bind:disabled="isDisabled" autocomplete="off" placeholder="이름을 입력하세요" data-vv-name='이름' />
        <p class="warn" v-if="errors.has('이름')">{{errors.first('이름')}}</p>
        <div class="grid">
          <div class="number"><input type="number" placeholder="생년월일6자리" name="ssn_birth" id="ssn_birth" v-model="ssn_birth" v-validate="'required|length:6|max:6'" v-on:keyup="nextFocus('birth')" v-bind:disabled="isDisabled" autocomplete="off" data-vv-name='생년월일'></div>
          <div class="dash">-</div>
          <!-- <div class="number last"><input type="password" pattern="[0-9]*" name="sex" id="sex" v-model="sex" inputmode="numeric" maxlength="1" style="-webkit-text-security:disc" v-on:change="nextFocus('sex')" v-bind:disabled="isDisabled" autocomplete="off" v-validate="'required|between:0,9|length:1|max:1'" data-vv-name='성별'>******</div> -->
          <div class="number"><input type="password" name="ssn2" id="ssn2" @click="showSecureKeypad()" placeholder="주민번호뒷자리" maxlength="7" autocomplete="off" readonly="readonly" v-validate="'required|length:7|max:7'" data-vv-name='주민번호뒷자리'></div>
        </div>
        <p class="warn" v-if="errors.has('생년월일')">{{errors.first('생년월일')}}</p>
        <p class="warn" v-if="errors.has('주민번호뒷자리')">{{errors.first('주민번호뒷자리')}}</p>
      </div>
      <div class="cert-wrap">
        <p class="title">휴대폰인증</p>
        <div class="grid phone">
          <!-- <select id="telComCd" name="telComCd" v-model="telComCd" v-validate="'required'" v-on:change="nextFocus('telComCd')" v-bind:disabled="isDisabled" placeholder="통신사" data-vv-name='통신사'>
            <option v-for="option in options" v-bind:key="option.value" v-bind:value="option.value">
              {{ option.text }}
            </option>
          </select> -->
          <!-- <select v-model="telComCd" placeholder="통신사" @select="onSelect">
            <option v-for="option in options" :key="option.index" v-bind:value="option.value">
              {{ option.text }}
            </option>
          </select> -->
          <multiselect v-model="telComCd" :title="'통신사'" placeholder="통신사" :options="options" :onClose="onClose" v-validate="'required'" data-vv-name='통신사'>
            <template slot="singleLabel" slot-scope="{ option }">{{ option.text }}</template>
          </multiselect>
          <input type="tel" name="hp" id="hp" v-model="hp" v-bind:disabled="isDisabled" placeholder="휴대폰 번호" v-validate="'required|max:11'" data-vv-name='휴대폰 번호'>
          <p class="warn" v-if="errors.has('통신사')">{{errors.first('통신사')}}</p>
          <p class="warn" v-if="errors.has('휴대폰 번호')">{{errors.first('휴대폰 번호')}}</p>
          <!-- <p class="warn" v-if="errors.has('성별')">{{errors.first('성별')}}</p> -->
        </div>
        <button id="req_certification" v-on:click="kcmRequestCertNo()">인증번호 전송</button>
        <div class="cert-num" id="cert_no_conteiner">
          <input type="number" name="smsCertNo" id="smsCertNo" v-model="smsCertNo" placeholder="인증번호를 입력하세요" autocomplete="off" readonly>
          <p class="time" id="countdown" aria-hidden="true">{{ timer }}</p>
        </div>
        <p class="warn" id="certNoWarning" name="certNoWarning" v-if="timer==='00:00'">인증번호를 재전송 해주세요.</p>
      </div>
      <div class="btn-wrap" id="confirmed_div" v-if="smsCertNo&&timer!='00:00'">
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
      type: this.$route.params.type,
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: "",
      encPwd: "",
      errMsg: "",
      /* form */
      nm_person: "",
      ssn_birth: "",
      birthday: "",
      sex: "",
      telComCd: null,
      telComNm: "",
      hp: "",
      kcb_ci: "",
      kcb_di: "",
      kcb_cp: "",
      bgn: "",
      smsCertNo: "",
      svcTxSeqno: "",
      ssn_person: "",
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
      isDisabled: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    window.resultKeypad = this.resultKeypad;
    window.setRequestPhoneNumber = this.setRequestPhoneNumber;
    window.setCertNumber = this.setCertNumber;

    if (Constant.userAgent == "Android") {
      window.Android.reqSMSPermission();
    }
    this.$store.state.title = "본인확인";
    this.$store.state.header.type = "sub";
    this.time = this.minutes * 60;
    this.getLoanPersonCertInfo();
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
    getLoanPersonCertInfo: function(number) {
      var _this = this;
      this.$http
        .post("/m/loan/getLoanPersonCertInfo.json", {
          headers: {
            async: false,
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {
          var result = response.data;
          _this.nm_person = result.nm_person;
          _this.ssn_birth = result.birthDay;
          _this.sex = result.sex;
          for (var i = 0; i < _this.options.length; i++) {
            if (_this.options[i].value == result.telComCd) {
              _this.telComCd = _this.options[i];
            }
          }
          //_this.telComCd.value = result.telComCd;
          _this.hp = result.hp;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    onClose: function(option) {
      this.telComCd = option;
    },
    nextFocus: function(val) {
      var _this = this;
      if (val == "birth" && _this.ssn_birth.length == 6) $("#sex").focus();
      if (val == "sex" && _this.sex.length == 1) {
        $("#telComCd").focus();
        this.$children[0].isOpen = true;
      }
      if (val == "telComCd" && _this.telComCd.value) $("#hp").focus();
      if (val == "hp" && _this.hp) $("").focus();
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
          formData.append("telComCd", _this.telComCd.value);
          formData.append("hp", _this.hp);
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
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
            });
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
      this.$http
        .post("/m/login/kcmCertify.json", formData)
        .then(response => {
          var result = response.data;
          console.log(result);
          if (result.result == "00") {
            _this.kcb_ci = result.kcb_ci;
            _this.kcb_di = result.kcb_di;
            _this.kcb_cp = result.kcb_cp;

            _this.insertTxFc();

            //기사용자 통신사 코드가 없어서 추가
            _this.modifyPersonCdTel();
          } else {
            this.$toast.center(result.message);
            _this.smsCertNo = "";
            return false;
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    insertTxFc: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("cd_fc", this.cd_fc);
      formData.append("cd_goods", this.cd_goods);
      formData.append("encPwd", this.encPwd);
      formData.append("nm_person", _this.nm_person);
      formData.append("ymd_birth", _this.birthday);
      formData.append("cd_sex", _this.sex);
      formData.append("hp1", _this.hp);
      this.$http
        .post("/m/loan/insertTxFc.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result == "00") {
            _this.no_bunch = result.no_bunch;
            _this.ssn_person = result.ssn_person;
            //신용대출
            if (_this.type == "01") {
              _this.$router.push({
                name: "GoodsCreditReqInfo",
                params: {
                  cd_fc: _this.cd_fc,
                  cd_goods: _this.cd_goods,
                  no_bunch: _this.no_bunch,
                  kcb_di: _this.kcb_di,
                  ssn_person: _this.ssn_person
                }
              });
              //담보대출
            } else if (_this.type == "03") {
              _this.$router.push({
                name: "GoodsHsnInsReqInfo",
                params: {
                  cd_fc: _this.cd_fc,
                  cd_goods: _this.cd_goods,
                  no_bunch: _this.no_bunch,
                  kcb_di: _this.kcb_di,
                  ssn_person: _this.ssn_person
                }
              });
            }
          }
        });
      // .catch(e => {
      //   this.$toast.center(ko.messages.error);
      // });
    },
    modifyPersonCdTel: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("telComCd", _this.telComCd.value);
      console.log("modifyPersonCdTel start");
      this.$http
        .post("/m/person/modifyPersonCdTel.json", formData)
        .then(response => {
          var result = response.data;
          if (result.result == "00") {
            console.log("modifyPersonCdTel success");
          } else {
            console.log("modifyPersonCdTel failed");
          }
        });
      // .catch(e => {
      //   this.$toast.center(ko.messages.error);
      // });
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
        window.Android.showSecureKeypad("numeric", 7, "주민등록번호 뒷자리");
      }
    },
    //native call
    resultKeypad: function(encPwd) {
      if (encPwd != null && encPwd != "") {
        $("#ssn2").val("1111111"); // 임의의 숫자 7자 입력
        this.encPwd = encPwd;
      } else {
        $("#ssn2").val("");
        this.encPwd = "";
      }
    },
    //native call
    setRequestPhoneNumber: function(phoneNumber) {
      this.hp = phoneNumber;
    },
    //native call back
    setCertNumber: function(number) {
      number = number + "";
      if (this.smsCertNo.length == 0) {
        this.smsCertNo = number;
      }
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