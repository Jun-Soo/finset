<template>
  <div id="wrapper" class="bg_default">
    <!-- Header -->
    <header id="header">
      <div class="input-group">
        <div class="input-group-btn">
          <button class="ui-nav nav-back" type="button" onclick="history.back();">뒤로가기</button>
        </div>
        <h1>본인인증</h1>
      </div>
    </header>
    <!-- Content -->
    <section id="content">
      <div class="container">
        <form name="frmCertifyStep" id="frmCertifyStep" method="post" validate="remove_tag;">
          <div class="form-block form-input-block-confrim">
            <div class="form-group">
              <label for="" class="sr-only">이름</label>
              <input type="text" name="nm_person" id="nm_person" class="form-control" maxlength="8" placeholder="이름을 입력하세요." validate="trim;strip_ws;label:이름;" autocomplete="off" onkeyup="chkValidate();" />
            </div>
            <div class="form-group">
              <label for="" class="sr-only">주민등록번호 앞 7자리</label>
              <div class="row">
                <div class="col-xs-5">
                  <input type="number" name="ssn_birth" id="ssn_birth" class="form-control xe_required" maxlength="6" validate=" label:주민등록번호 앞자리;numeric;" autocomplete="off" placeholder="주민등록번호 앞자리" onkeyup="ssnBirthChk(); chkValidate();" />
                  <input type="hidden" name="birthday" id="birthday" class="form-control" maxlength="8" autocomplete="off" />
                </div>
                <div class="col-xs-1">
                  <p class="form-control-static">-</p>
                </div>
                <div class="col-xs-2">
                  <label for="" class="sr-only">주민번호 7번째 자리</label>
                  <input type="number" pattern="[0-9]*" inputmode="numeric" style="-webkit-text-security:disc" maxlength="1" class="form-control" name="sex" id="sex" placeholder="" validate=" label:주민등록번호 뒷자리;numeric;" autocomplete="off" onkeyup="sexChk(); chkValidate();"
                  />
                </div>
                <div class="col-xs-4">
                  <p class="form-control-static">* * * * * *</p>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="form-input-inline">
                <span class="" aria-hidden="true">
  										<select name="telComCd" id="telComCd" validate="select-one;label:통신사" class="selectpicker" data-header="옵션선택" >
  											<option value="">통신사</option>
  											<option value="01">SKT</option>
  											<option value="02">KT</option>
  											<option value="03">LGU+</option>
  											<option value="04">SK알뜰폰</option>
  											<option value="05">KT알뜰폰</option>
  											<option value="06">LG알뜰폰</option>
  										</select>
  										<label for="hp" class="sr-only">휴대폰 번호</label>
  										<input type="tel" name="hp" id="hp" class="form-control" maxlength="11" />
  									</span>
                <span class="form-control-feedback" aria-hidden="true"><a role="button" id="req_certification" class="btn btn-primary btn-cert-no" onclick="kcmRequestCertNo();">인증요청</a></span>
              </div>
            </div>
            <div class="form-group has-feedback" id="cert_no_conteiner">
              <label for="smsCertNo" class="sr-only">인증번호</label>
              <input type="number" name="smsCertNo" id="smsCertNo" class="form-control" placeholder="인증번호를 입력하세요" autocomplete="off" readonly="readonly" onkeyup="smsCertNoChk();">
              <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">00:00</span>
            </div>
          </div>
        </form>
      </div>
    </section>
    <div class="btn-fixed-bottom" id="confirmed_div">
      <button id="confirmed_certify" onclick="confirmedCertify()" class="btn btn-lg btn-primary btn-block">확인</button>
    </div>
    <!-- //Content -->
  </div>
</template>

<script>
  import Common from './../../assets/js/common.js'
  
  // <input type="hidden" name="nation" value="1" >
  // <input type="hidden" name="kcb_ci" >
  // <input type="hidden" name="kcb_di" >
  // <input type="hidden" name="kcb_cp" >
  // <input type="hidden" name="svcTxSeqno" >
  // <input type="hidden" name="smsReSndYn" id="smsReSndYn">
  // <input type="hidden" name="bgn"  />
  // <input type="hidden" name="yn_logout" id="yn_logout"  />
  // <input type="hidden" name="yn_use" id="yn_use"  />
  // <input type="hidden" name="yn_eventPush" id="yn_eventPush" value="${yn_eventPush}"/>
  
  export default {
    name: "certStep2",
    data() {
      return {
        errors: [],
        errMsg: '',
        nation: '1',
        kcb_ci: '',
        kcb_di: '',
        kcb_cp: '',
        bgn: '',
        svcTxSeqno: '',
        smsReSndYn: ''
      };
    },
    component: {},
    // computed () {
    // },
    beforeCreate() {},
    created() {
  
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
      allChecked: function() {
        var _this = this;
      }
    }
  };
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  
</style>
