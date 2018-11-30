<template>
  <section>
    <div class="balance-top">
      <div class="item">
        <div class="top">5,318,107<em>원</em></div>
        <div class="body">
          <div class="flex">
            <p>출금가능액</p>
            <p>8,000,000</p>
          </div>
          <div class="flex">
            <p>대용금</p>
            <p>8,000,000</p>
          </div>
          <div class="flex">
            <p>매수/미납금</p>
            <p>8,000,000</p>
          </div>
          <div class="flex">
            <p>대출/신용금</p>
            <p>8,000,000</p>
          </div>
          <div class="flex">
            <p>유자증권평가금액</p>
            <p>8,000,000 (+80,000)</p>
          </div>
        </div>
      </div>
    </div>

    <div class="box-list list02">

      <div class="select pb20">
        <select>
          <option>가맹점별</option>
        </select>
        <select>
          <option>평가금액순</option>
        </select>
        <select>
          <option>주식</option>
        </select>
      </div>

      <div class="item">
        <a @click="$router.push('')" class="block">
          <div class="flex">
            <p class="key">예수금</p>
            <p class="number">118,107<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p></p>
          </div>
        </a>
      </div>

      <div class="item">
        <a @click="$router.push('')" class="block">
          <div class="flex">
            <p class="key">현대자동차</p>
            <p class="number">1,000,000<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p class="red">+82,800 (8.23%)</p>
          </div>
        </a>
      </div>

      <div class="item">
        <a @click="$router.push('')" class="block">
          <div class="flex">
            <p class="key">현대자동차</p>
            <p class="number">1,000,000<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p class="blue">-82,800 (8.23%)</p>
          </div>
        </a>
      </div>

    </div>

  </section>
</template>

<script>
export default {
  name: "assetsStockBalcDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "전체 잔고상세";
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
