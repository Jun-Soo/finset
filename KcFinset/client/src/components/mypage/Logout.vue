<template>
  <section>
    <div class="loading success">
      안전하게 로그아웃 되었습니다
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push(' ')" class="solid box blue">다시 로그인하기</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageLogout",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "로그아웃 완료";
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
