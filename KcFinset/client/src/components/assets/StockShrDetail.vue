<template>
  <section>
    <div class="balance-top">
      <div class="item">
        <div class="top name">삼성전자</div>
        <div class="body">
          <div class="flex">
            <p>평가금액</p>
            <p class="number"><em>3,000,000</em> 원</p>
          </div>
          <div class="flex">
            <p>평가손익</p>
            <p class="blue">-82,800 (8.23%)</p>
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
  name: "AssetsStockShrDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "주식 상세";
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
