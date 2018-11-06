<template>
  <section>
    <div class="board event">
      <ul class="view">
        <li>
          <p class="subject">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</p>
          <p class="date"><em>한경</em>2018-04-11</p>
        </li>
        <li>
          게시판 본문영역
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "NewsDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "뉴스";
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
