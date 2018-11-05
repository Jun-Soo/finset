<template>
  <section>
    <div class="credit-info">
      <p class="title">신용 등급 및 연체 정보</p>
      <div class="wrap">
        <ul>
          <li>
            <p>등급</p>
            <p>3등급</p>
          </li>
          <li>
            <p>점수</p>
            <p>848</p>
          </li>
          <li>
            <p>연체현황</p>
            <p>1건, 신용회복위원회</p>
          </li>
        </ul>
        <p class="right">정보등록일 : 2018.01.01, 수집처 : 신용정보원</p>
      </div>
    </div>

    <div class="credit-info pb90">
      <p class="title">부채정보</p>
      <div class="wrap">
        <p class="key">국민은행</p>
        <ul class="left">
          <li>
            <p>계약정보</p>
            <p>대출형태 : 장기카드대출(카드론) 분할상환<br>
              개설일자 : 2018.01.01<br>
              만기일자 : 2018.01.01<br>
              약정금액 : 123,456 만원<br>
              구분 : 담보
            </p>
          </li>
          <li>
            <p>이용현황</p>
            <p>잔액 : 123,456 만원<br>
              금리 : 123,456 만원<br>
              월상환액 : 123,456 만원
            </p>
          </li>
        </ul>
        <p class="key">신한은행</p>
        <ul class="left">
          <li>
            <p>계약정보</p>
            <p>대출형태 : 장기카드대출(카드론) 분할상환<br>
              개설일자 : 2018.01.01<br>
              만기일자 : 2018.01.01<br>
              약정금액 : 123,456 만원<br>
              구분 : 담보
            </p>
          </li>
          <li>
            <p>이용현황</p>
            <p>잔액 : 123,456 만원<br>
              금리 : 123,456 만원<br>
              월상환액 : 123,456 만원
            </p>
          </li>
        </ul>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">공유 관리 하기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "홍길동님 신용정보";
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
