<template>
  <section>
    <div class="terms">
      <div>
        <h2>제1장 총칙</h2>
        <h3>제1조 (목적)</h3>
        <p class="text">본 약관은 주식회사 코스콤(이하”회사”라 함)가 제공하는 전자금융거래 서비스 및 관련 서비스(이하 ”서비스”라 함)의 이용과 관련하여, 회사와 회원간 또는 회원간의 권리 및 의무 및 책임사항 및 서비스 이용조건 및 절차 등 기본적인 사항은 규정함을 목적으로 합니다.</p>
      </div>

      <div>
        <h3>제2조 (용어의 정의)</h3>
        <ul class="list-style03">
          <li>본 약관에서 사용하는 용어의 정의는 다음과 같습 니다.
            <ul class="list-style04">
              <li><span>1)</span>“회원”이란 본약관에 따라 가입신청에 대한 회사의 승낙을 받아 회사가 제공하는 서비스를 이용하는 자를 말합니다.</li>
              <li><span>2)</span>“핀셋앱”이란 스마트폰, 태블릿 등 휴대용 단말기(이하 “모바일 디바이스”라 함)에 설치되어 회사가 제공하는 서비스를 구현하는 애플리케이션을 말합니다.</li>
            </ul>
          </li>
        </ul>
      </div>

    </div>
  </section>
</template>

<script>
export default {
  name: "EtcTermDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이용 정책 및 약관";
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
