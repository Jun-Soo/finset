<template>
  <section>
    <div class="box-list list01 noMG pb90">
      <div class="item">
        <div class="top">
          <p class="corp">삼성전자</p>
          <p class="goal">
            <em>+650,000 원</em>
            <em>2.71%</em>
          </p>
        </div>
        <div class="number-single">6,800,000<em>원</em></div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage">
          <p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p>
        </div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage">
          <p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p>
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p class="corp">삼성전자</p>
          <p class="goal">
            <em>+650,000 원</em>
            <em>2.71%</em>
          </p>
        </div>
        <div class="number-single">6,800,000<em>원</em></div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage">
          <p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p>
        </div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage">
          <p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push('')" class="blue box solid">완료</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock8",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "목표 수익률 설정";
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
