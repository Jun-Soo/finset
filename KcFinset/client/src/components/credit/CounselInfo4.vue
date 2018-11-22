<template>
  <section>
    <div class="container">
      <textarea></textarea>
      <div class="btn-wrap float">
        <a @click="$router.push('')" class="solid blue box">신청하기</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "CreditCounselInfo4",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청내용";
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
