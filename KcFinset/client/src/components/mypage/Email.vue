<template>
  <section>
    <div class="cert-wrap">
      <p class="title">이메일주소</p>
      <input type="text">
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push(' ')" class="solid box blue">다음</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageEmail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이메일 변경";
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
