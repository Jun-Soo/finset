<template>
  <section>
    <div class="mypage-top">
      <p class="key">이름</p>
      <p class="value">{{nm_person}}</p>
      <p class="key">휴대폰번호</p>
      <p class="value">{{hp}}</p>
      <p class="key">이메일주소</p>
      <p class="value email" id="email">{{email}}</p>
      <p class="link"><a @click="$router.push('/mypage/email')">수정</a></p>
    </div>

    <div class="mypage-links">
      <a @click="$router.push('')">개인설정</a>
      <a @click="$router.push('/mypage/regAlarm')">알림설정</a>
      <a @click="$router.push('/mypage/cert')">인증/보안</a>
      <a @click="$router.push('/share/main')">공유관리</a>
      <a @click="$router.push('/scrap/ctrlFcLink')">연동관리</a>
      <a @click="$router.push('/credit/counselInfo')">신용컨설팅</a>
      <a @click="$router.push('/mypage/favGoods')">관심상품</a>
      <a @click="$router.push('/mypage/rstlInqGoods')">상품조회결과</a>
      <a @click="$router.push('/mypage/state')">상품신청현황</a>
    </div>

    <div class="member-exit">
      서비스를 탈퇴하려면 <a @click="$router.push('/mypage/drop')">여기</a>를 눌러주세요
    </div>

  </section>
</template>

<script>
export default {
  name: "MypageInfo",
  data() {
    return {
      no_person: this.$store.state.user.noPerson,
      nm_person: this.$store.state.user.nmPerson,
      hp: this.$store.state.user.hp,
      email: ""
      /**
      amt_etm_income: null
      bgn: "199104112"
      birthday: null
      c1_gender: "2"
      cd_push: "1"
      cd_tel: "01"
      changePwd: null
      changePwdConfirm: null
      chk_pwd: 0
      cnt_fail: 0
      cnt_fail_finger: "0"
      cnt_fail_mode: null
      cnt_fail_pwd: "0"
      currentPwd: null
      dt_agree_using: "2018-11-02 10:11:10"
      dt_basic: null
      dt_frt: "2018-11-02 10:11:10"
      dt_install: null
      dt_lst: "2018-11-05 16:16:34"
      email: "abc@hhh.net"
      email_idx1: "abc"
      email_idx2: "hhh.net"
      etc_memo: null
      etc_memo1: null
      fcm_token: null
      file1: null
      fileArray: null
      fileName: null
      fileSize: 0
      hp: "01087897058"
      hp_idx1: "01087897058"
      hp_idx2: ""
      hp_idx3: ""
      id_agency: null
      id_frt: "P000000285"
      id_lst: "P000000285"
      item_push: null
      job: null
      kcb_ci: "kcb_ci"
      kcb_cp: "kcb_cp"
      kcb_di: "kcb_di"
      loan_code: null
      nation: null
      nick: null
      nm_file1: null
      nm_person: "김효진"
      no_bunch: null
      no_person: "P000000285"
      no_prepare: null
      pass_person: "5555"
      path_file1: null
      pbl_cert_ci: null
      pbl_cert_di: null
      rqstCausCd: null
      seq: null
      seq_new_debt_reg: null
      sex: null
      smsCertNo: null
      smsReSndYn: null
      ssn_person: null
      ssn_person_idx1: ""
      ssn_person_idx2: ""
      stat_push: null
      stock_token: null
      svcTxSeqno: null
      telComCd: null
      type_push: null
      ymd_birth: "19910411"
      yn_agency: null
      yn_agree_using: "Y"
      yn_eventPush: null
      yn_fingerprint: "Y"
      yn_grt: null
      yn_installment: null
      yn_logout: "N"
      yn_os: null
      yn_push: "Y"
      yn_use: "Y"
      zone_comp: null
      zone_etc: null
      zone_home: null
      zone_reg: null
      zone_univ: null
       */
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "MYPAGE";
  },
  created() {
    var _this = this;
    var frm = new FormData();
    var personInfo;
    frm.append("no_person", _this.no_person);
    frm.append("hp", _this.hp);
    _this.$http
      .post("/m/customercenter/frameCustomerCenterMain.crz", frm)
      .then(response => {
        personInfo = response.data.personVO;
        _this.email = personInfo.email;
        localStorage.setItem('email', _this.email);
      });
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
