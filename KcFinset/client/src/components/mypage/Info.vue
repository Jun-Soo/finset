<template>
  <section>
    <div class="mypage-top">
      <p class="key">이름</p>
      <p class="value">홍길동</p>
      <p class="key">휴대폰번호</p>
      <p class="value">010-1234-5678</p>
      <p class="key">이메일주소</p>
      <p class="value email">abcdemail@email.com</p>
      <p class="link"><a @click="$router.push(' ')">수정</a></p>
    </div>

    <div class="mypage-links">
      <a @click="$router.push(' ')">개인설정</a>
      <a @click="$router.push(' ')">알림설정</a>
      <a @click="$router.push(' ')">인증/보안</a>
      <a @click="$router.push(' ')">공유관리</a>
      <a @click="$router.push('/scrap/ctrlFcLink')">연동관리</a>
      <a @click="$router.push(' ')">신용컨설팅</a>
      <a @click="$router.push(' ')">관심상품</a>
      <a @click="$router.push(' ')">상품조회결과</a>
      <a @click="$router.push(' ')">상품신청현황</a>
    </div>

    <div class="member-exit">
      서비스를 탈퇴하려면 <a @click="$router.push(' ')">여기</a>를 눌러주세요
    </div>

  </section>
</template>

<script>
export default {
  name: "MypageInfo",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "MYPAGE";
  },
  created() {},
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
