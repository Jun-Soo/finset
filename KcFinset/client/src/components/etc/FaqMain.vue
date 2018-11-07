<template>
  <section>
    <div class="cs-top">
      <em>서비스 이용 관련<br>문의 및 의견</em>을 보내주세요
      <div class="links">
        <a href="#" class="kakao">카카오톡 문의</a>
        <a href="#" class="email">이메일 문의</a>
      </div>
    </div>

    <div class="cs-links">
      <p><a href="#">자주 묻는 질문</a></p>
      <p><a href="#">이용약관 및 정책</a></p>
      <p>
        <em>버전정보</em>
        <em>1.1.1</em>
      </p>
      <p>
        <em>제휴 문의</em>
        <em>finset.@koscom.co.kr</em>
      </p>
    </div>

  </section>
</template>

<script>
export default {
  name: "EtcFaqMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "고객센터";
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
