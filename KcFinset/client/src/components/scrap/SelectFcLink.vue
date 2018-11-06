<template>
  <section>
    <div class="loading success">
      <em>금융사 연동 완료</em><br>금융사 연동이 완료되었습니다
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push('')" class="solid box blue">확인</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "ScrapSelectFcLink",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "";
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
