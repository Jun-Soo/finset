<template>
  <section>
    <div class="container">
      <div class="history">
        <div class="item pb10">
          <p class="name">이말순</p>
          <p class="result">거절</p>
          <p class="number">010-2222-4444</p>
          <p class="date">2018.08.13</p>
          <p class="cate">
            <em>신용등급</em>
            <em>부채</em>
            <em>소비</em>
            <em>자산</em>
          </p>
        </div>
        <div class="item pb10">
          <p class="name">이말순</p>
          <p class="result">종료</p>
          <p class="number">010-2222-4444</p>
          <p class="date">2018.08.19~2018.10.15</p>
          <p class="cate">
            <em>신용등급</em>
            <em>부채</em>
            <em>소비</em>
            <em>자산</em>
          </p>
        </div>
        <div class="item pb10">
          <p class="name">이말순</p>
          <p class="result">취소</p>
          <p class="number">010-2222-4444</p>
          <p class="date">2018.08.19~2018.10.15</p>
          <p class="cate">
            <em>신용등급</em>
            <em>부채</em>
            <em>소비</em>
            <em>자산</em>
          </p>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareHistory",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "과거 내역 보기";
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
