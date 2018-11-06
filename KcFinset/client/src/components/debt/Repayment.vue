<template>
  <section>
    <div class="container">
      <ul class="debt-modify">
        <li>
          <p class="key">상환일</p>
          <p><input type="text" placeholder="상환일을 입력하세요"><button class="cal"></button></p>
        </li>
        <li>
          <p class="key">금액</p>
          <p><input type="text" placeholder="상환금액을 입력하세요"><em>만원</em></p>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a href="#" class="solid blue box">저장</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtRepayment",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상환정보 입력";
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
