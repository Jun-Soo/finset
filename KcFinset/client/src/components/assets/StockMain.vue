<template>
  <section>
    <div class="stock-number">
      <p class="key">나의 증권 계좌는</p>
      <p class="value">1,600<em>만원</em></p>
    </div>

    <div class="box-list list01 noMG">
      <div class="filter-wrap">
        <div class="filter red">
          <input
            type="checkbox"
            id="chk1"
          ><label for="chk1">박준수</label>
        </div>
        <div class="filter orange">
          <input
            type="checkbox"
            id="chk2"
          ><label for="chk2">박준수</label>
        </div>
        <div class="filter green">
          <input
            type="checkbox"
            id="chk3"
          ><label for="chk3">박준수</label>
        </div>
        <div class="filter blue">
          <input
            type="checkbox"
            id="chk4"
          ><label for="chk4">박준수</label>
        </div>
        <div class="filter purple">
          <input
            type="checkbox"
            id="chk5"
          ><label for="chk5">박준수</label>
        </div>
      </div>

      <div class="item">
        <a
          @click="$router.push('')"
          class="block"
        >
          <div class="top">
            <p class="symbol"><img
                src="../../assets/images/common/bu_kb.png"
                alt=""
              />국민은행</p>
            <p class="text"><span class="circle blue">준수</span></p>
          </div>
          <div class="number-wrap bi">
            <div class="left">
              <p class="number">2,450,000<em>원</em></p>
            </div>
            <div class="right">
              <p class="value red">+850,000 (2.14%)</p>
            </div>
          </div>
          <div class="goods-cate nor">
            <em>위탁종합</em>
            <em>110-125-700003</em>
          </div>
        </a>
      </div>
      <div class="item">
        <a
          @click="$router.push('')"
          class="block"
        >
          <div class="top">
            <p class="symbol"><img
                src="../../assets/images/common/bu_kb.png"
                alt=""
              />국민은행</p>
            <p class="text"><span class="circle red">준수</span></p>
          </div>
          <div class="number-wrap bi">
            <div class="left">
              <p class="number">2,450,000<em>원</em></p>
            </div>
            <div class="right">
              <p class="value blue">+850,000 (2.14%)</p>
            </div>
          </div>
          <div class="goods-cate nor">
            <em>상품</em>
            <em>110-125-700003</em>
          </div>
        </a>
      </div>

    </div>

  </section>
</template>

<script>
export default {
  name: "assetsStockMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "증권";
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
