<template>
  <section>
    <div class="goods-top">
      <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
      <p class="title">국민은행 대출 상품명</p>
      <p class="text">넉넉한 한도화 낮은 금리, 소득증빙이 가능한 직장인이라면 최대 1억원까지 대출한도 모바일에서 바로 확인 가능</p>
      <p class="spec"><span>금리<em>2.7%</em></span><span>한도<em>130%</em></span><span>기간<em>6개월</em></span></p>
    </div>

    <div class="goods-detail mt50">
      <div class="container">
        <div>
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
        <div>
          <p class="key">동일 종목투자한도</p>
          <p class="value">100%</p>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodsStockDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상품상세";
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
