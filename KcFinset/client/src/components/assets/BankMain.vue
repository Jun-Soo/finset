<template>
  <section>
    <div class="assets-bank">
      <p class="key">나의 은행 예금은</p>
      <p class="value"><em>65,555</em>만원</p>

      <div class="filter-wrap">
        <div class="filter red">
          <input type="checkbox" id="chk1"><label for="chk1">박준수</label>
        </div>
        <div class="filter orange">
          <input type="checkbox" id="chk2"><label for="chk2">박준수</label>
        </div>
        <div class="filter green">
          <input type="checkbox" id="chk3"><label for="chk3">박준수</label>
        </div>
        <div class="filter blue">
          <input type="checkbox" id="chk4"><label for="chk4">박준수</label>
        </div>
        <div class="filter purple">
          <input type="checkbox" id="chk5"><label for="chk5">박준수</label>
        </div>
      </div>
    </div>

    <div class="assets-bank-recent">
      <p>7/2 10:53<em>KB카드출금</em></p>
      <p><em>-118,107</em>원</p>
    </div>

    <div class="box-list list01 noMG">

      <div class="item">
        <a @click="$router.push('')" class="block">
          <div class="top">
            <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
            <p class="text"><em class="blue bold">입출금</em><span class="circle red">준수</span></p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">110-125-700003</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">2,450,000</em>원</p>
            </div>
          </div>
        </a>
      </div>
      <div class="item">
        <a @click="$router.push('')" class="block">
          <div class="top">
            <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
            <p class="text"><em class="blue bold">입출금</em><span class="circle blue">준수</span></p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">110-125-700003</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">2,450,000</em>원</p>
            </div>
          </div>
        </a>
      </div>

    </div>

  </section>
</template>

<script>
export default {
  name: "AssetsAccountInfo",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "은행";
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
