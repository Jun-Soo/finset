<template>
  <section>
    <div class="inout-list">
      <ul class="flex noBD">
        <li class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</li>
        <li class="blue">가능</li>
      </ul>
      <p class="name">하이스탁론</p>
      <ul class="flex">
        <li>거래시각<br>
          금리<br>
          기간
        </li>
        <li>2.7% ( 41만원)<br>
          4,000 만원<br>
          6개월<br>
        </li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">상환방식</p>
        </li>
        <li>만기일시상환</li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">한종목투자한도</p>
          동일종목투자한도<br>
          로스컷비율(최저담보유지비율)<br>
          현금인출비율<br>
          만기연장비율
        </li>
        <li><br>
          100 %<br>
          125 %<br>
          140 %<br>
          127 %
        </li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">연체이율</p>
        </li>
        <li>취급금리 + 3%</li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock3",
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
