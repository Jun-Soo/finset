<template>
  <section>
    <div class="debt-calc-top">
      <p class="key">총납부금액은</p>
      <p class="value"><em>42,000만원</em>입니다</p>
      <div class="bar">
        <p>원금</p>
        <p>이자</p>
      </div>
      <div class="flex">
        <p>37,000 만원</p>
        <p>5,000 만원</p>
      </div>
      <a href="#" class="expand"></a>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#">DSR</a>
        <a href="#" class="on">DTI</a>
        <a href="#">대출</a>
      </div>
    </div>

    <div class="box-list noMG">

      <div class="calc-top">
        <p class="key">연소득</p>
        <p class="value">3,400<em>만원</em></p>
      </div>

      <div class="calc-search">
        <p class="key">주택정보</p>
        <p class="text">서울시 영등포구 여의도동 대림 (118.5m2)</p>
        <p><button></button></p>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><span>2,500<em> 만원</em></span><em>4.5%</em></p>
          <p class="value"><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
          </div>
        </div>
      </div>
      <div class="calc-acco">
        <div class="top">
          <p class="key"><span>2,500<em> 만원</em></span><em>4.5%</em></p>
          <p class="value"><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
          </div>
        </div>
      </div>

      <div class="btn-wrap">
        <a href="#" class="solid">추가하기</a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid box blue">계산하기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtCalc2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계산기";
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
