<template>
  <section>
    <div class="con-top counsel-info">
      <p class="text">신용등급을 올리고 싶으세요?<br>나에게 맞는 소비,지출이 궁금하세요?</p>
      <p class="pt10">지금 FINSET<br>신용상담서비스를 만나보세요</p>
      <a @click="$router.push('')">신용상담이란?</a>
    </div>

    <div class="box-list counsel-list noMG">
      <div class="item">
        <div class="top">
          <p>2017.10.16</p>
          <p><a @click="$router.push('')">신청내역</a></p>
        </div>
        <div class="result apply">
          <p class="title">상담 신청 접수</p>
          <p class="text">홍길동님의 신용상담 신청이 접수되었습니다.</p>
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p>2017.10.16</p>
          <p><a @click="$router.push('')">신청내역</a></p>
        </div>
        <div class="result ing">
          <p class="title">상담 대기중</p>
          <p class="text">홍길동님의 신용상담을 준비중입니다.</p>
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p>2017.10.16</p>
          <p><a @click="$router.push('')">상담결과</a></p>
        </div>
        <div class="result done">
          <p class="title">상담 완료</p>
          <p class="text">홍길동님의 신용상담이 완료되었습니다.</p>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "CreditCounselInfo",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용상담";
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
