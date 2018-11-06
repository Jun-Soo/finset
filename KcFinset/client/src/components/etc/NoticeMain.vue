<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">공지</a>
        <a href="#">이벤트</a>
      </div>
    </div>

    <div class="board">
      <ul class="list">
        <li>
          <p class="date">2018-04-11</p>
          <p class="subject"><a @click="$router.push('')">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</a></p>
        </li>
        <li>
          <p class="date">2018-04-11</p>
          <p class="subject"><a @click="$router.push('')">서비스 정기 점검 안내 (4/14(토) 19:00~ )</a></p>
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "EtcNoticeMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "공지/이벤트";
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
