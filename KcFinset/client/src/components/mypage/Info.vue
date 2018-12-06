<template>
  <section v-if="seen">
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
      <!-- <a @click="$router.push('')">개인설정</a> -->
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
      email: "",
      seen: false
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
      .post("/m/customercenter/getCustomerCenterMain.json", frm)
      .then(response => {
        personInfo = response.data.personVO;
        var tEmail = personInfo.email;
        if (tEmail != null && tEmail != "") {
          _this.email = tEmail;
          localStorage.removeItem("email");
          localStorage.setItem("email", tEmail);
        } else {
          _this.email = "등록안됨";
        }
        _this.seen = true;
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
