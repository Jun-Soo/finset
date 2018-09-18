<template>
  <div id="wrapper" class="bg-white">
    <!-- Header -->
    <header id="header">
      <div class="input-group">
        <div class="input-group-btn">
          <button type="button" class="ui-nav nav-back" @click="history.back();">뒤로가기</button>
        </div>
        <h1>사용자 선택</h1>
      </div>
    </header>
    <!-- //Header -->
    <!-- Content -->
    <section id="content">
    <form id="frmShareInfoNewRequest" name="frmShareInfoNewRequest" action="post">
    <input type="hidden" id="setting_mode" name="setting_mode" value="00"/>
    <input type="hidden" id="share_status" name="share_status" value="01"/>
    <input type="hidden" id="seq_share" name="seq_share"/>
    <input type="hidden" id="type_list" name="type_list"/>

      <div class="visual-banner middle-banner">
        <p class="msg">공유를 요청할 사용자를 선택해주세요</p>
      </div>
      <div class="container">
          <div class="form-inline">
            <div class="form-group">
              <label for="person_req">사용자</label>
              <input id="offer_nm_person" name="offer_nm_person" class="form-control" type="text" readonly="readonly"/>
              <input id="offer_hp" name="offer_hp" class="form-control" type="hidden" value="" />
              <div class="person_req" @click="srcPerson();"></div>
            </div>
          </div>
      </div>
      <div class="container mt-3">
        <ul class="ul-share">
          <li class="li-share li-share-head">
            <h2 class="h2">정보 제공 항목</h2>
          </li>
          <li class="li-share li-share-tail">
            <div class="chk-share">
              <input type="checkbox" id="yn_credit_offer" name="yn_credit_offer" v-model="yn_credit_offer" value="Y"/> <label for="chk1">신용등급 및 연체 정보</label>
            </div>
            <div class="chk-share">
              <input type="checkbox" id="yn_debt_offer" name="yn_debt_offer" v-model="yn_debt_offer" value="Y"/> <label for="chk2">대출개설 및 잔고 현황</label>
            </div>
            <div class="chk-share">
              <input type="checkbox" id="yn_income_offer" name="yn_income_offer" v-model="yn_income_offer" value="Y"/> <label for="chk3">소득정보</label>
            </div>
          </li>
          <li class="li-share li-share-head">
            <h2 class="h2">정보 통합 관리 항목</h2>
          </li>
          <li class="li-share li-share-tail">
            <div class="chk-share">
              <input type="checkbox" id="yn_asset_itgt_mngm" name="yn_asset_itgt_mngm" v-model="yn_asset_itgt_mngm" value="Y"/> <label for="chk4">자산</label>
            </div>
            <div class="chk-share">
              <input type="checkbox" id="yn_consume_itgt_mngm" name="yn_consume_itgt_mngm" v-model="yn_consume_itgt_mngm" value="Y"/> <label for="chk5">소비</label>
            </div>
            <div class="chk-share">
              <input type="checkbox" id="yn_debt_itgt_mngm" name="yn_debt_itgt_mngm" v-model="yn_debt_itgt_mngm" value="Y"/> <label for="chk6">부채</label>
            </div>
          </li>
        </ul>
      </div>
      <div class="btn-fixed-bottom affix-bottom">
        <a role="button" class="btn btn-lg btn-block btn-primary" @click="createShareInfo();">공유 요청</a>
      </div>
    </form>
    </section>
    <!-- //Content -->
</div>
</template>

<script>

// import Common from './../../assets/js/common.js'
import Constant from "./../../assets/js/constant.js";

export default {
  name: 'shareNewRequest',
  data() {
    return {
      errors: [],
      errMsg: '',
      offer_nm_person: '',
      offer_hp: ''
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
    //test
    //this.resultAddress('박준수','01026882453');
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
    srcPerson() {
      if(Constant.userAgent == "Android"){
        window.Android.getAddressList();
      }else if(Constant.userAgent == "iOS"){
        //TODO ios 사용자검색 추가
        //Jockey.on("getAddressList" , function (param) {
        //resultAddress(param.src_nm_person, param.src_hp);
        //});
      }
    },
    resultAddress(src_nm_person,src_hp){
      var _this = this
      _this.offer_nm_person = src_nm_person
      _this.offer_hp = src_hp
    },
    //validate체크
    validShareInfoNewReq(){
      var _this = this
      var creditOfferChk = vm.yn_credit_offer //신용등급 및 연체정보 체크여부
      var debtOfferChk = vm.yn_debt_offer //대출개설 및 잔고현황 체크여부
      var incomeOfferChk = vm.yn_income_offer //소득정보 체크여부
      var assetItgtMngmChk = vm.yn_asset_itgt_mngm //자산 체크여부
      var consumeItgtMngmChk = vm.yn_consume_itgt_mngm //소비 체크여부
      var debtItgtMngmChk = vm.yn_debt_itgt_mngm //부채 체크여부

      if(_this.offer_hp === ""){
        _this.$toast.center("사용자를 검색해 주세요.");
        return false;
      }

      if(!creditOfferChk && !debtOfferChk && !incomeOfferChk
          && !assetItgtMngmChk && !consumeItgtMngmChk && !debtItgtMngmChk){
        _this.$toast.center("항목을 선택해 주세요.");
        return false;
      }

      return true;
    },
    createShareInfo(){
    }

  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
