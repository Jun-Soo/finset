<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="container">
        <form name="frmCertifyStep" id="frmCertifyStep" method="post">
          <div class="form-block form-input-block-confrim">
            <div class="form-group">
              <label for="" class="sr-only">이름</label>
              <input type="text" class="form-control" name="nm_person" id="nm_person" v-model="nm_person" v-validate="'required|max:8'" v-bind:disabled="isDisabled" autocomplete="off" placeholder="이름을 입력하세요" data-vv-name='이름' />
              <div><span class="error" v-if="errors.has('이름')">{{errors.first('이름')}}</span></div>
            </div>
            <div class="form-group">
              <label for="" class="sr-only">주민등록번호 앞 7자리</label>
              <div class="row">
                <div class="col-xs-5">
                  <input type="number" class="form-control xe_required" name="ssn_birth" id="ssn_birth" v-model="ssn_birth" v-validate="'required|length:6|max:6'" v-on:keyup="nextFocus('birth')" v-bind:disabled="isDisabled" autocomplete="off" placeholder="주민등록번호 앞자리" data-vv-name='생년월일' />
                </div>
                <div class="col-xs-1">
                  <p class="form-control-static">-</p>
                </div>
                <div class="col-xs-2">
                  <label for="" class="sr-only">주민번호 7번째 자리</label>
                  <input type="number" class="form-control" pattern="[0-9]*" name="sex" id="sex" v-model="sex" inputmode="numeric" style="-webkit-text-security:disc" v-validate="'required|length:1|max:1'" v-on:keyup="nextFocus('sex')" v-bind:disabled="isDisabled" autocomplete="off" data-vv-name='성별' />
                </div>
                <div class="col-xs-4">
                  <p class="form-control-static">* * * * * *</p>
                </div>
              </div>
              <div class="row"><span class="error" v-if="errors.has('생년월일')">{{errors.first('생년월일')}}</span></div>
              <div class="row"><span class="error" v-if="errors.has('성별')">{{errors.first('성별')}}</span></div>
            </div>
            <div class="form-group">
              <div class="row">
                <div class="col-xs-5">
                  <select class="form-input-inline bootstrap-select" id="telComCd" v-model="telComCd" v-validate="'required'" v-on:change="nextFocus('telCom')" v-bind:disabled="isDisabled" placeholder="통신사" data-vv-name='통신사'>
                    <option v-for="option in options" v-bind:key="option.value" v-bind:value="option.value">
                      {{ option.text }}
                    </option>
                  </select>
                </div>
                <div class="col-xs-7">
  								<label for="hp" class="sr-only">휴대폰 번호</label>
  								<input type="tel" name="hp" id="hp" class="form-control" v-model="hp" v-validate="'required|max:11'" v-bind:disabled="isDisabled" placeholder="휴대폰 번호" data-vv-name='휴대폰 번호'/>
                  <span class="form-control-feedback" aria-hidden="true"><a role="button" id="req_certification" class="btn btn-primary btn-cert-no" v-on:click="kcmRequestCertNo()">인증요청</a></span>
                </div>
              </div>
              <div class="row">
                <span class="error" v-if="errors.has('통신사')">{{errors.first('통신사')}}</span>
                <span class="error" v-if="errors.has('휴대폰 번호')">{{errors.first('휴대폰 번호')}}</span>
              </div>
            </div>
            <div class="form-group has-feedback" id="cert_no_conteiner">
              <label for="smsCertNo" class="sr-only">인증번호</label>
              <input type="number" name="smsCertNo" id="smsCertNo" class="form-control" v-model="smsCertNo" placeholder="인증번호를 입력하세요" autocomplete="off" readonly="readonly" v-on:keyup="smsCertNoChk()">
              <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">{{ timer }}</span>
            </div>
          </div>
        </form>
      </div>
    </section>
    <div class="btn-fixed-bottom" id="confirmed_div">
      <button id="confirmed_certify" class="btn btn-lg btn-primary btn-block" v-on:click="confirmedCertify()">확인</button>
    </div>
    <!-- //Content -->
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certStep2",
  data() {
    return {
      errMsg: "",
      /* form */
      nm_person: "",
      ssn_birth: "",
      birthday: "",
      sex: "",
      telComCd: "",
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
        { text: "통신사", value: "" },
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
      minutes: 5,
      secondes: 0,
      time: 0,
      /* class */
      isDisabled: false
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {

    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
    this.$store.state.title = "본인확인 (2/7)"
    this.time = this.minutes * 60
  },
  beforeMount() {},
  mounted() {
    var _this = this;
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    nextFocus: function(val) {
      var _this = this;
      if (val == "birth" && _this.ssn_birth.length == 6) $("#sex").focus();
      if (val == "sex" && _this.sex.length == 1) $("#telComCd").focus();
      if (val == "telCom" && telComCd) $("#hp").focus();
    },
    kcmRequestCertNo: function() {
      var _this = this;
      console.log(_this.$validator.validateAll());
      this.$validator.validateAll().then(res => {
        if (res) {
          if (_this.sex == "1" || _this.sex == "2") {
            _this.birthday = "19" + _this.ssn_birth;
          } else {
            _this.birthday = "20" + _this.ssn_birth;
          }
          var data = {
            nm_person: _this.nm_person,
            birthday: _this.birthday,
            sex: _this.sex,
            telComCd: _this.telComCd,
            hp: _this.hp,
            smsReSndYn: _this.smsReSndYn,
            nation: _this.nation
          };
          this.$http
            .get("/api/login/kcmRequestCertNo.json", {
              params: data
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
                frmCertifyStep.smsCertNo.readOnly = false;
                $("#smsCertNo").focus();
              } else if (result.result == "11") {
                this.$toast.center(result.message);
                return false;
              } else if (result.result == "01") {
                this.$toast.center(result.message);
              }
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
            });
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
    },
    setCertNumber: function(number) {
      number = number + "";
      if (this.smsCertNo) {
        this.smsCertNo = number;
        this.smsCertNoChk();
      }
    },
    smsCertNoChk: function() {
      if (this.smsCertNo) {
        Common.affixBottom("show");
      } else {
        Common.affixBottom("hide");
      }
    },
    confirmedCertify: function() {
      var _this = this;
      if (this.smsCertNo == "" || this.smsCertNo == "undefined") return false;
      var regExp = /^[0-9]+$/;
      if (!regExp.test(this.smsCertNo)) {
        this.$toast.center("숫자만 입력해주세요.");
        this.smsCertNo = "";
        return false;
      }
      var data = {
        svcTxSeqno: _this.svcTxSeqno,
        hp: _this.hp,
        smsCertNo: _this.smsCertNo
      };
      this.$http
        .get("/api/login/kcmCertify.json", {
          params: data
        })
        .then(response => {
          var result = response.data;
          console.log(result);
          if (result.result == "00") {
            _this.kcb_ci = result.kcb_ci;
            _this.kcb_di = result.kcb_di;
            _this.kcb_cp = result.kcb_cp;
            this.insertPerson();
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
    insertPerson: function() {
      var _this = this;
      _this.bgn = _this.birthday + _this.sex;

      var data = {
        nm_person: _this.nm_person,
        bgn: _this.bgn,
        birthday: _this.birthday,
        telComCd: _this.telComCd,
        hp: _this.hp,
        kcb_ci: _this.kcb_ci,
        kcb_di: _this.kcb_di,
        kcb_cp: _this.kcb_cp,
        yn_eventPush: (this.$store.state.user.isEventPush ? 'Y' : 'N')
      };

      this.$http
        .get("/api/person/insertPerson.json", {
          params: data
        })
        .then(response => {
          var result = response.data;
          var noPerson = result.returnData;
          this.$store.state.user.noPerson = result.returnData;
          if (result.result == "00") {
            if(Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson",{
                noPerson : noPerson,
                phNum : this.hp
              });
            } else if(Constant.userAgent == "Android") {
              window.Android.setNoPerson(noPerson, this.hp);
            }
            // frmCertifyStep.action = "<c:url value='/m/base/frameSecurityCode.crz'/>";
            _this.$router.push('/member/certCode')

          } else if (result.result == "11") {
            if(Constant.userAgent == "iOS") {
              Jockey.send("setNoPerson",{
                noPerson : noPerson,
                phNum : _this.hp
              });
            } else if(Constant.userAgent == "Android") {
              window.Android.setNoPerson(noPerson, _this.hp);
            }
            _this.$router.push('/home?hp='+_this.hp)
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    start: function() {
      var _this = this;
      _this.time = _this.minutes * 60 + _this.secondes;
      console.log(_this.time);
      if (!this.timerObj) {
        this.timerObj = setInterval(() => {
          if (_this.time > 0) {
            _this.time--;
            _this.timer = this.prettyTime();
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
      this.minutes = 5;
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
<style lang="scss">
.memberMain {
  background-color: transparent;
}
</style>
