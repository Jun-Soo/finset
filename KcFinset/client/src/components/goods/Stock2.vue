<template>
  <section>
    <div class="con-top credit-up">
      <p><em>홍길동님이 신청 가능한<br>상품과 금리입니다<br>신청은</em> 증권사 HTS<em>를 <br>접속하시어 이용하시기 <br>바랍니다</em></p>
    </div>

    <div class="box-list list01 noMG pb90">

      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <p class="text blue">가능</p>
        </div>
        <p class="goods-name">하이스탁론</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">금리</p>
            <p class="right">2.7% ( 41만원)</p>
          </div>
          <div class="list">
            <p class="left">한도</p>
            <p class="right">4,000 만원</p>
          </div>
          <div class="list">
            <p class="left">기간</p>
            <p class="right">6개월</p>
          </div>
        </div>
      </div>

      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <p class="text red">불가능</p>
        </div>
        <p class="goods-name">하이스탁론</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">금리</p>
            <p class="right">2.7% ( 41만원)</p>
          </div>
          <div class="list">
            <p class="left">한도</p>
            <p class="right">4,000 만원</p>
          </div>
          <div class="list">
            <p class="left">기간</p>
            <p class="right">6개월</p>
          </div>
        </div>
      </div>

    </div>
    <div class="btn-wrap float">
      <a @click="$router.push('')" class="box blue solid">홈으로</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청 가능한 상품";
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
