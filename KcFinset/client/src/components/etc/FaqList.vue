<template>
  <section>
    <div class="cs-top">
      <div class="cs-search">
        <input type="search" placeholder="자주 묻는 질문 검색">
      </div>
    </div>

    <div class="cs-links">
      <p><a @click="$router.push('')">신용관리</a></p>
      <p><a @click="$router.push('')">부채관리</a></p>
      <p><a @click="$router.push('')">상품조회</a></p>
      <p><a @click="$router.push('')">보안</a></p>
      <p><a @click="$router.push('')">기타</a></p>
    </div>
  </section>
</template>

<script>
export default {
  name: "EtcFaqList",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "자주 묻는 질문";
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
