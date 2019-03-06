<template>
  <section>
    <div class="loading fail">
      전송에 실패 했습니다.<br>다시 전송하려면 재전송 버튼을 눌러주세요
    </div>

    <div class="btn-wrap col2">
      <a class="btn-solid">재전송</a>
      <a class="btn-stroke">홈으로</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "CreditLoading",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "";
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
