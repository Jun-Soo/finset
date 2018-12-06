<template>
  <section>
    <div v-if="curTab!='02'" class="my-graph-wrap">
      <p class="title">DSR 계산 결과</p>
      <div class="graph">
        <Gauge :value="gaugeValue" :text="gaugeText" />
      </div>
      <div class="container">
        <p class="info-massage">FINSET에서 계산하고 DSR은 금융기관별로 산충방식에 따라 결과 값이 상이할수도 있습니다.</p>
      </div>
    </div>
    <div v-else class="debt-calc-top">
      <p class="key">총납부금액은</p>
      <p class="value"> <em>42,000만원</em>입니다</p>
      <div class="bar" style="grid-template-columns: 85% 15%">
        <p>원금</p>
        <p>이자</p>
      </div>
      <div class="flex">
        <p>37,000 만원</p>
        <p>5,000 만원</p>
      </div>
      <div class="graph">
        <loanChart></loanChart>
      </div>
      <a href="#" class="expand"></a>
    </div>

    <div class="tab">
      <div class="wrap">
        <a id="00" name="dsr" :class="{'on':curTab === '00'}" @click="clickTab">DSR</a>
        <a id="01" name="dti" :class="{'on':curTab === '01'}" @click="clickTab">DTI</a>
        <a id="02" name="loan" :class="{'on':curTab === '02'}" @click="clickTab">대출</a>
      </div>
    </div>
    <!--DSR-->
    <div v-if="curTab=='00'" class="box-list noMG">

      <div class="calc-top">
        <p class="key">연소득</p>
        <p class="value">3,400<em>만원</em></p>
      </div>

      <div class="calc-acco">
        <div class="top">
          <p class="key"><span>2,500<em>만원</em></span><em>주택담보</em><em>원리금분할</em><em>3.75%</em></p>
          <p class="value"><a @click="delDebt" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출유형</p>
            <p>
              <!-- <multiselect v-model="loanType" track-by="text" label="text" placeholder="대출유형" :options="options" :searchable="false" :allow-empty="false" @select="onSelect">
                <template slot="singleLabel" slot-scope="{ option }">{{ option.text }}</template>
              </multiselect> -->
              <select>
                <option>주택담보대출</option>
              </select>
            </p>
            <p class="key">대출금액</p>
            <p><input type="text" placeholder="만원"></p>
            <p class="key">대출이율</p>
            <p><input type="text" placeholder="%"></p>
            <p class="key">대출기간</p>
            <p><input type="text" placeholder="개월"></p>
            <p class="key">상환방법</p>
            <p>
              <!-- <multiselect v-model="repay" track-by="text" label="text" placeholder="상환방법" :options="options" :searchable="false" :allow-empty="false" @select="onSelect">
                <template slot="singleLabel" slot-scope="{ repay_options }">{{ repay_options.text }}</template>
              </multiselect> -->
              <select>
                <option>만기일시상상환</option>
              </select>
            </p>
          </div>
        </div>
      </div>
      <div class="btn-wrap">
        <a @click="addDebt" class="solid">추가하기</a>
      </div>
      <p class="info-massage">현재 보유중인 모든 대출의 남은기간 및 금액을 건별로 입력하시면 계산됩니다.</p>
    </div>
    <!--DTI-->
    <div v-if="curTab=='01'" class="box-list noMG">

      <div class="calc-top">
        <p class="key">연소득</p>
        <p class="value"><input type="number"><em>만원</em></p>
      </div>

      <div class="calc-search">
        <p class="key">주택정보</p>
        <p class="text">서울시 영등포구 여의도동 대림 (118.5m2)</p>
        <p><button @click="$router.push('/debt/calcSearch')"></button></p>
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
            <p><input type="text" placeholder="만원"></p>
            <p class="key">대출이율</p>
            <p><input type="text" placeholder="%"></p>
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
            <p><input type="text" placeholder="만원"></p>
            <p class="key">대출이율</p>
            <p><input type="text" placeholder="%"></p>
          </div>
        </div>
      </div>

      <div class="btn-wrap">
        <a href="#" class="solid">추가하기</a>
      </div>
    </div>
    <!-- LOAN -->
    <div v-if="curTab=='02'" class="box-list noMG pb90">

      <div class="calc-acco" v-for="each in loanList" :key="each.key">
        <div class="top">
          <p class="key"><span>2,500<em> 만원</em></span><em>원리금분할</em><em>3.75%</em></p>
          <p class="value"><a href="#" class="del"></a></p>
          <button class="ui"></button>
        </div>
        <div class="acco-body-wrap">
          <div class="body">
            <p class="key">대출금액</p>
            <p><input type="text" placeholder="만원"></p>
            <p class="key">대출이율</p>
            <p><input type="text" placeholder="%"></p>
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
      <div class="btn-wrap">
        <a href="#" class="solid">추가하기</a>
      </div>
    </div>
    <div class="btn-wrap noMG">
      <a href="#" class="btn-next">계산하기</a>
    </div>
  </section>
</template>

<script>
import Gauge from "./../common/Gauge";
import loanChart from "./CalcChart";
export default {
  name: "DebtCalc",
  data() {
    return {
      gaugeValue: 0.44,
      gaugeText: "44 %",
      curTab: "00",
      curTabName: "dsr",
      // tab 00
      options: [
        { text: "주택담보대출", value: "01" },
        { text: "전세자금", value: "02" },
        { text: "중도/이주자금 대출", value: "03" },
        { text: "비주택담보 대출", value: "04" },
        { text: "신용대출", value: "05" },
        { text: "마이너스(한도) 대출", value: "06" },
        { text: "기타 대출", value: "07" }
      ],
      repay_options: [
        { text: "원리금분할", value: "01" },
        { text: "원급분할", value: "02" },
        { text: "만기일시", value: "03" }
      ],
      loanType: "", //대출유형
      repay: "", //상환방법
      loanList: new Array(),
      dsrList: new Array(),
      dtiList: new Array()
    };
  },
  components: {
    Gauge: Gauge,
    loanChart: loanChart
  },
  computed: {},
  watch: {
    // whenever question changes, this function will run
    // question: function(newQuestion, oldQuestion) {
    //   this.answer = "Waiting for you to stop typing...";
    //   this.debouncedGetAnswer();
    // }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계산기";
  },
  created() {
    this.curTab = "00";
    this.curTabName = "dsr";
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickTab: function(tab) {
      let _this = this;
      _this.curTab = tab.srcElement.id;
      _this.curTabName = tab.srcElement.name;
    },
    initData: function() {},
    addDebt: function() {},
    delDebt: function() {}
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
._value {
  width: 70%;
  margin-right: 10px;
}
</style>
