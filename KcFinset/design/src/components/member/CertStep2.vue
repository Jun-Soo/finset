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
  component: {
  },
  computed: {
  },
  beforeCreate() {
  },
  created() {
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.memberMain {
  background-color: transparent;
}
</style>
