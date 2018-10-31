<template>
  <section>
    <div class="my-graph-wrap">
      <p class="title">DSR 계산 결과</p>
      <div class="graph">
        <img src="../../assets/images/main/graph_sample.png" width="100%" alt="" />
        <p class="graph-text">38.66<em>%</em></p>
      </div>
      <div class="container">
        <p class="info-massage">FINSET에서 계산하고 DSR은 금융기관별로 산충방식에 따라 결과 값이 상이할수도 있습니다.</p>
      </div>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">DSR</a>
        <a href="#">DTI</a>
        <a href="#">대출</a>
      </div>
    </div>

    <div class="box-list noMG">

      <div class="calc-top">
        <p class="key">연소득</p>
        <p class="value">3,400<em>만원</em></p>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><em>주택담보</em><em>원리금분할</em><em>3.75%</em></p>
          <p class="value">2,500<em>만원</em><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출유형</p>
            <p>
              <select>
                <option>주택담보</option>
              </select>
            </p>
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
            <p class="key">대출기간</p>
            <p><input type="text"></p>
            <p class="key">상환방법</p>
            <p>
              <select>
                <option>만기일시상상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><em>신용</em><em>만기일시</em><em>4.5%</em></p>
          <p class="value">2,500<em>만원</em><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출유형</p>
            <p>
              <select>
                <option>주택담보</option>
              </select>
            </p>
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
            <p class="key">대출기간</p>
            <p><input type="text"></p>
            <p class="key">상환방법</p>
            <p>
              <select>
                <option>만기일시상상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><em>마이너스</em><em>만기일시</em><em>6.8%</em></p>
          <p class="value">2,500<em>만원</em><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출유형</p>
            <p>
              <select>
                <option>주택담보</option>
              </select>
            </p>
            <p class="key">대출금액</p>
            <p><input type="text"></p>
            <p class="key">대출이율</p>
            <p><input type="text"></p>
            <p class="key">대출기간</p>
            <p><input type="text"></p>
            <p class="key">상환방법</p>
            <p>
              <select>
                <option>만기일시상상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>

      <div class="btn-wrap">
        <a href="#" class="solid">추가하기</a>
      </div>
      <p class="info-massage">현재 보유중인 모든 대출의 남은기간 및 금액을 건별로 입력하시면 계산됩니다.</p>
    </div>

    <div class="btn-wrap noMG">
      <a href="#" class="btn-next">계산하기</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "DebtCalc",
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
