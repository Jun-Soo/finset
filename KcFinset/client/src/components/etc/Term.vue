<template>
  <section>
    <div class="cs-links">
      <p><a @click="$router.push('')">서비스 이용약관</a></p>
      <p><a @click="$router.push('')">개인정보 처리방침</a></p>
    </div>
  </section>
</template>

<script>
export default {
  name: "EtcTerm",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이용 정책 및 약관";
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
