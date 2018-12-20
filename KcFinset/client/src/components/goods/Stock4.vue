<template>
  <section>
    <div class="portfolio">
      <div>
        <p><img src="../../assets/images/common/portfolio.png" width="75" alt="" /></p>
        <p class="title">이현중님의<br>포트폴리오를 분석하기 위해서<br>증권 계좌 연동이 필요합니다</p>
        <p class="text">간단하게 내 계좌를 조회 하신 후<br>진단을 받아 보세요</p>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push('')" class="blue box solid">내 계좌 불러오기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock4",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "포트폴리오 진단";
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
