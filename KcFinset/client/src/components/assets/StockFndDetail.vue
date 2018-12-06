<template>
  <section>
    <div class="balance-top">
      <div class="item">
        <div class="top name">삼성인도중소형Focus증권자H (주식-파생)-Ce</div>
        <div class="body">
          <div class="flex">
            <p>평가금액</p>
            <p class="number"><em>1,200,000</em> 원</p>
          </div>
          <div class="flex">
            <p>평가손익</p>
            <p class="red">+111,800 (10.23%)</p>
          </div>
          <div class="flex">
            <p>보유주수</p>
            <p>10주</p>
          </div>
          <div class="flex">
            <p>매입단가</p>
            <p>3,082,800 원</p>
          </div>
          <div class="flex">
            <p>평균매입단가</p>
            <p>308,280 원</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "assetsStockFndDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "펀드 상세";
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
