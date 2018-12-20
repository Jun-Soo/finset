<template>
  <section>
    <div class="box-list list01 noMG">
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
          <p class="number">6,920,000<em>원</em></p>
        </div>
        <div class="form">
          <p class="key">계좌번호</p>
          <input type="number">
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
          <p class="number">6,920,000<em>원</em></p>
        </div>
        <div class="form">
          <p class="key">계좌번호</p>
          <input type="number">
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock1",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계좌 번호 확인";
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
