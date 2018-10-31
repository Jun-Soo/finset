<template>
  <section>
    <div class="container mt30">
      <h3>연동 금융사를 선택해주세요.</h3>
      <div class="checks grid2 mt10">
        <p><input type="checkbox" id="chk1"><label for="chk1">은행</label></p>
        <p><input type="checkbox" id="chk2"><label for="chk2">카드</label></p>
        <p class="mt10"><input type="checkbox" id="chk3"><label for="chk3">증권</label></p>
        <p class="mt10"><input type="checkbox" id="chk4"><label for="chk4">국세청</label></p>
      </div>

      <div>
        <p class="mt40">증권사 연계를 위하여 이메일입력과 정보제공 동의가 필요합니다.</p>
        <h3 class="mt15">이메일</h3>
        <input type="text" class="mt15">
        <div class="checks">
          <div class="box-agree solo">
            <p><input type="checkbox" id="chk5"><label for="chk5">[필수] 금융정보 제공동의서</label></p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="btn-next">다음</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금융사 연동";
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
