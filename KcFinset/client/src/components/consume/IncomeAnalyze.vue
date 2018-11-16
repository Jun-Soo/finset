<template>
  <section>
    <div class="graph-wrapper mt30">
      <img src="../../assets/images/consume/graph.png" width="100%" alt="" />
    </div>

    <div class="detail-list">
      <dl>
        <dt>월별</dt>
        <dd>
          <p>’18년 7월</p>
          <p>535,000 원 </p>
        </dd>
        <dd>
          <p>’18년 6월</p>
          <p>115,000 원 </p>
        </dd>
      </dl>
      <dl>
        <dt>월별</dt>
        <dd>
          <p>7.12 15:00<em>현금</em></p>
          <p>118,107 원</p>
        </dd>
        <dd>
          <p>7.11 22:00<em>신한카드</em></p>
          <p>125,000 원</p>
        </dd>
        <dd>
          <p>7.08 21:00<em>신한카드</em></p>
          <p>225,000 원</p>
        </dd>
      </dl>
    </div>

  </section>
</template>

<script>
export default {
  name: "ConsumeIncomeAnalyze",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "지출(GS마트) 이력 상세";
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
