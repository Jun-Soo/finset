<template>
  <section>
    <div class="portfolio">
      <div>
          <p><img src="./../../assets/images/common/portfolio.png" width="75" alt="" /></p>
          <p class="title">{{this.$store.state.user.nmPerson}}님의<br>포트폴리오 분석을 시작합니다</p>
          <p class="text">분석 시작하기를 눌러주세요</p>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="start()" class="blue box solid">분석 시작하기</a> 
    </div>

  </section>
</template>

<script>
export default {
  name: "assetsDiagsMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "분석시작";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    start: function() {
      var _this = this;
      this.$router.push("/assets/diagsReport");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
