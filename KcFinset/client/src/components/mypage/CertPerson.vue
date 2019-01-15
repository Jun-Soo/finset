<template>
  <!-- Content -->
  <section id="content">
    <div class="container mt30">
      <div class="checks">
        <!--전체약관동의-->
        <input type="checkbox" v-validate="'required'" id="check-all" ref="chkAll" data-vv-name='약관동의' v-model="chkAll" v-on:click="allChecked()"><label for="check-all">전체 약관 동의</label>
        <div class="box-agree" v-if="!chkAll">
          <p><input type="checkbox" name="checkbox1" id="checkbox1" v-model="chkBox1"><label for="checkbox1">[필수] 서비스 이용동의</label></p>
          <ul>
            <li><a v-on:click="openPop('1')">서비스 이용약관</a></li>
            <li><a v-on:click="openPop('2')">개인정보 수집·이용 동의</a></li>
          </ul>
        </div>
        <div class="box-agree" v-if="!chkAll">
          <p><input type="checkbox" name="checkbox2" id="checkbox2" v-model="chkBox2"><label for="checkbox2">[필수] 통신사/본인확인 서비스 이용 동의</label></p>
          <ul>
            <li><a v-on:click="openPop('3')">고유식별정보처리 동의</a></li>
            <li><a v-on:click="openPop('4')">통신사 본인확인 이용약관 동의</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="cert-wrap">
      <p class="title">회원정보</p>
      <input type="text" ref="nm_person" class="form-control" name="nm_person" id="nm_person" v-model="nm_person" v-validate="'required|max:8'" v-bind:disabled="isDisabled" autocomplete="off" placeholder="이름을 입력하세요" data-vv-name='이름' />
      <p class="warn" v-if="errors.has('이름')">{{errors.first('이름')}}</p>
      <div class="grid">
        <div class="number"><input type="number" placeholder="생년월일6자리" name="ssn_birth" id="ssn_birth" v-model="ssn_birth" v-validate="'required|length:6|max:6'" v-bind:disabled="isDisabled" autocomplete="off" data-vv-name='생년월일'></div>
        <div class="dash">-</div>
        <div class="number last"><input type="password" style="'-webkit-text-security: disc;'" pattern="[0-9]*" name="sex" id="sex" v-model="sex" inputmode="numeric" maxlength="1" v-bind:disabled="isDisabled" autocomplete="off" v-validate="'required|between:0,9|length:1|max:1'" data-vv-name='성별'>******</div>
      </div>
      <p class="warn" v-if="errors.has('생년월일')">{{errors.first('생년월일')}}</p>
      <p class="warn" v-if="errors.has('성별')">{{errors.first('성별')}}</p>
    </div>
    <div class="cert-wrap pb90">
      <p class="title">휴대폰인증</p>
      <div class="grid phone">
        <multiselect ref="telCom" v-bind:disabled="isDisabled" v-model="telCom" label="text" placeholder="통신사" :options="options" :title="'통신사'">
        </multiselect>
        <input type="tel" name="hp" id="hp" v-model="hp" v-validate="'required|max:11'" v-bind:disabled="isDisabled" placeholder="휴대폰 번호" data-vv-name='휴대폰 번호'>
      </div>
      <button id="req_certification" v-on:click="personCertify()">인증번호 전송</button>
      <div class="cert-num" id="cert_no_conteiner">
        <input type="number" ref="smsCertNo" name="smsCertNo" id="smsCertNo" v-model="smsCertNo" placeholder="인증번호를 입력하세요" autocomplete="off" v-bind:readonly="isReadOnly">
        <p class="time" id="countdown" aria-hidden="true">{{ timer }}</p>
      </div>
      <p class="warn" id="certNoWarning" name="certNoWarning" v-if="timer==='00:00'">인증번호를 재전송 해주세요.</p>
    </div>
    <div class="btn-wrap float" id="confirmed_div" v-if="smsCertNo&&timer!='00:00'">
      <a id="confirmed_certify" class="btn-next" v-on:click="confirmedCertify()">다음</a>
    </div>
    <vue-modal transitionName="zoom-in" name="my-modal1" v-on:popclose="closePop('1')">
      <Terms1 slot="body" v-on:popclose="closePop('1')"></Terms1>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal2" v-on:popclose="closePop('2')">
      <Terms4 slot="body" v-on:popclose="closePop('2')"></Terms4>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal3" v-on:popclose="closePop('3')">
      <Terms9 slot="body" v-on:popclose="closePop('3')"></Terms9>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal4" v-on:popclose="closePop('4')">
      <Terms10 slot="body" v-on:popclose="closePop('4')"></Terms10>
    </vue-modal>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";
import Terms1 from "../member/Terms1.vue";
import Terms4 from "../member/Terms4.vue";
import Terms9 from "../member/Terms9.vue";
import Terms10 from "../member/Terms10.vue";

export default {
  name: "certPerson",
  data() {
    return {
      errMsg: "",
      no_person: "",
      /* form */
      nm_person: "",
      ssn_birth: "",
      birthday: "",
      sex: "",
      telCom: "",
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
      isReadOnly: true,
      /* class */
      isDisabled: false,
      clicked: "",
      checked: "",
      term_db: "",
      nm_code: "",
      chkAll: false,
      chkBox1: false,
      chkBox2: false,
      telDisabled: false
    };
  },
  components: {
    Terms1: Terms1,
    Terms4: Terms4,
    Terms9: Terms9,
    Terms10: Terms10
  },
  watch: {
    chkAll: function() {
      if (this.chkAll) {
        $("#nm_person").focus();
      }
    },
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
        }, 500);
      }
    },
    telCom: function() {
      if ((this.hp == null || this.hp == "") && this.telCom != null) {
        $("#hp").focus();
      }
    }
  },
  computed: {},
  beforeCreate() {},
  created() {
    window.setCertNumber = this.setCertNumber;
    window.setRequestPhoneNumber = this.setRequestPhoneNumber;

    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
      window.Android.reqSMSPermission();
    }
    this.$store.state.title = "본인확인";
    this.time = this.minutes * 60;
    this.$store.state.header.type = "sub";

    if (this.$store.state.isLoggedIn) {
      this.$store.state.header.backPath = "/mypage/cert";
    } else {
      this.$store.state.header.backPath = "/member/certCodeLogin";
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {
    let _this = this;
    $(":checkbox").change(function() {
      if (_this.chkBox1 && _this.chkBox2) {
        _this.chkAll = true;
        _this.checked = "check";
      } else {
        _this.chkAll = false;
        _this.checked = "";
      }

      if (_this.chkBox1 && _this.chkBox2) {
        _this.checked = "check";
      }
    });

    if (this.smsCertNo) window.scrollTo(0, window.innerHeight);
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    allChecked: function() {
      var _this = this;
      if (_this.chkAll) {
        _this.chkAll = false;
      } else {
        _this.chkAll = true;
      }

      if (_this.chkAll) {
        _this.checked = "check";
        _this.chkBox1 = true;
        _this.chkBox2 = true;
      } else {
        _this.checked = "";
        _this.chkBox1 = false;
        _this.chkBox2 = false;
      }
    },
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
    // nextFocus: function(val) {
    //   var _this = this;
    //   if (val == "birth" && _this.ssn_birth.length == 6) $("#sex").focus();
    //   if (val == "telCom" && _this.telCom) $("#hp").focus();
    // },
    personCertify: function() {
      let _this = this;
      let frm = new FormData();
      if (!_this.chkAll) {
        _this.$dialogs.alert(
          "약관동의는 필수 체크 항목입니다.",
          Constant.options
        );
        $("#check-all").focus();
        return false;
      }
      this.$validator.validateAll().then(res => {
        if (res) {
          frm.append("hp", _this.hp);
          frm.append("nm_person", _this.nm_person);
          this.$http
            .post("/m/person/personCertify.json", frm, {
              headers: {
                async: false,
                "Content-Type":
                  "application/x-www-form-urlencoded; charset=UTF-8"
              }
            })
            .then(response => {
              if (response.data.result == "01") {
                this.$toast.center(response.data.message);
                return false;
              }
              _this.kcmRequestCertNo();
            });
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
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
          formData.append("smsReSndYn", _this.smsReSndYn);
          formData.append("svcTxSeqno", _this.svcTxSeqno);
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
                _this.svcTxSeqno = result.svcTxSeqno;
                $("#req_certification").html("인증번호 재전송");
                _this.isReadOnly = false;
                _this.$refs.smsCertNo.focus();
                _this.clicked = true;
              } else if (result.result == "11") {
                this.$toast.center(result.message);
                if (!this.timerObj) this.stop();
                _this.svcTxSeqno = "";
                $("#req_certification").html("인증번호 재전송");
                _this.isReadOnly = true;
                _this.clicked = false;
                return false;
              } else if (result.result == "01") {
                this.$toast.center(result.message);
                if (!this.timerObj) this.stop();
                _this.svcTxSeqno = "";
                $("#req_certification").html("인증번호 재전송");
                _this.isReadOnly = true;
                _this.clicked = false;
                return false;
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
      var regExp = /^[0-9]+$/;

      if (this.smsCertNo == "" || this.smsCertNo == "undefined") return false;

      if (!regExp.test(_this.smsCertNo)) {
        _this.$toast.center("숫자만 입력해주세요.");
        _this.smsCertNo = "";
        return false;
      }
      var formData = new FormData();
      formData.append("svcTxSeqno", _this.svcTxSeqno);
      formData.append("hp", _this.hp);
      formData.append("smsCertNo", _this.smsCertNo);
      this.$http.post("/m/login/kcmCertify.json", formData).then(response => {
        var result = response.data;
        if (result.result == "00") {
          _this.kcb_ci = result.kcb_ci;
          _this.kcb_di = result.kcb_di;
          _this.kcb_cp = result.kcb_cp;

          _this.$router.push("/mypage/chgPwd");
        } else {
          this.$toast.center(result.message);
          _this.smsCertNo = "";
          return false;
        }
      });
    },
    start: function() {
      var _this = this;
      _this.time = _this.minutes * 60 + _this.secondes;
      if (!this.timerObj) {
        this.timerObj = setInterval(() => {
          if (_this.time > 0) {
            _this.time--;
            _this.timer = this.prettyTime();
          } else if (_this.time == 0) {
            _this.isReadOnly = true;
            this.clicked = false;
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
    },
    closePop: function(gubun) {
      var _this = this;
      _this.$modals.hide("my-modal" + gubun);
    },
    openPop: function(gubun) {
      var _this = this;
      _this.$modals.show("my-modal" + gubun);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
