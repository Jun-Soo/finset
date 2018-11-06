<template>
  <section>
    <div class="container memo-detail pb90">
      메모 샘플<br>이번달 월급이 늦게 들어올거 같아서 이자납입일을 낮춤
    </div>

    <div class="btn-wrap float">
      <a href="#" class="blue box solid">수정</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MemoDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모";
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
