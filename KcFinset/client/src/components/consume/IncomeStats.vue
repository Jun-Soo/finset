<template>
  <section>
    <div class="container">
      <select>
        <option>카테고리별</option>
      </select>
      <div class="nobox-list">
        <p class="date">07.12</p>
        <div class="item">
          <div class="flex">
            <p><em class="circle red">준수</em>후이비인후과</p>
            <p><em class="number">-5,340,000</em>원</p>
          </div>
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />의료건강</p>
            <p class="key">삼성카드</p>
          </div>
        </div>

        <p class="date">07.12</p>
        <div class="item">
          <div class="flex">
            <p><em class="circle blue">준수</em>푸르1806</p>
            <p><em class="number">-5,340,000</em>원</p>
          </div>
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />의료건강</p>
            <p class="key">삼성카드</p>
          </div>
        </div>
        <div class="item">
          <div class="flex">
            <p><em class="circle orange">준수</em>푸르1806</p>
            <p><em class="number">-5,340,000</em>원</p>
          </div>
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />의료건강</p>
            <p class="key">삼성카드</p>
          </div>
        </div>
        <div class="item">
          <div class="flex">
            <p><em class="circle green">준수</em>푸르1806</p>
            <p><em class="number">-5,340,000</em>원</p>
          </div>
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />의료건강</p>
            <p class="key">삼성카드</p>
          </div>
        </div>
        <div class="item">
          <div class="flex">
            <p><em class="circle purple">준수</em>푸르1806</p>
            <p><em class="number">-5,340,000</em>원</p>
          </div>
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />의료건강</p>
            <p class="key">삼성카드</p>
          </div>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "ConsumeIncomeStats",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "지출상세";
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
