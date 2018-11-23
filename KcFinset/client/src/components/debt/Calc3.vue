<template>
  <section>
    <div class="debt-calc-top">
      <p class="key">총납부금액은</p>
      <p class="value"><em>42,000만원</em>입니다</p>
      <div class="bar" style="grid-template-columns: 85% 15%">
        <p>원금</p>
        <p>이자</p>
      </div>
      <div class="flex">
        <p>37,000 만원</p>
        <p>5,000 만원</p>
      </div>
      <div class="graph">
        <img src="../../assets/images/debt/graph.png" alt="" />
      </div>
      <a href="#" class="expand"></a>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#">DSR</a>
        <a href="#">DTI</a>
        <a href="#" class="on">대출</a>
      </div>
    </div>

    <div class="box-list noMG pb90">

      <div class="calc-acco">
        <div class="top">
          <p class="key"><span>2,500<em> 만원</em></span><em>원리금분할</em><em>3.75%</em></p>
          <p class="value"><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
            <p class="key">대출기간</p>
            <p>
              <select>
                <option>5년</option>
              </select>
            </p>
            <p class="key">거치기간</p>
            <p>
              <select>
                <option>5년</option>
              </select>
            </p>
            <p class="key">상환방식</p>
            <p>
              <select>
                <option>만기일시상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><span>2,500<em> 만원</em></span><em>원리금분할</em><em>3.75%</em></p>
          <p class="value"><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
            <p class="key">대출기간</p>
            <p>
              <select>
                <option>5년</option>
              </select>
            </p>
            <p class="key">거치기간</p>
            <p>
              <select>
                <option>5년</option>
              </select>
            </p>
            <p class="key">상환방식</p>
            <p>
              <select>
                <option>만기일시상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid box blue">계산하기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtCalc3",
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
