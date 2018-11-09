<template>
  <section>
    <div class="loading success" v-if="result=='success'">
      <em>정상적으로 접수 되었습니다.</em><br>해당 자료를 분석 후<br>신용평가에 반영될 예정입니다.
    </div>
    <div class="loading fail" v-else>
      <em>조회내역이 없습니다.</em><br>금융기관에 납부내역이 없거나<br />납부내역을 불러올 수 없습니다.
    </div>
    <div class="btn-wrap col2">
      <a href="#" class="btn-solid">재전송</a>
      <a href="#" class="btn-stroke">홈으로</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "CreditRaiseResult",
  data() {
    return {
      result: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "";
  },
  created() {
    this.result = this.$route.params.result;
  },
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
