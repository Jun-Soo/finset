<template>
  <section>
    <div class="loading success">
      <em>서비스 탈퇴 완료</em><br>이용해 주셔서 감사합니다
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid box blue">앱 종료하기</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageDropDone",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "서비스 탈퇴";
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
