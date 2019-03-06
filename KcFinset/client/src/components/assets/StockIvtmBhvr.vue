<template>
  <section>
    <!--
    <div class="sub-top">
      <a class="btn-back"></a>
      <p class="title">목표 수익률 설정</p>
    </div>
    -->
    <div class="box-list list01 noMG pb90">
      <div class="item">
        <div class="top">
          <p class="corp">삼성전자</p>
          <p class="number">6,800,000<em>원</em></p>
        </div>
        <div class="flex-wrap">
          <p class="goal">
            <em>+650,000 원</em>
            <em>2.71%</em>
          </p>
        </div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage"><input type="range"></div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage"><input type="range"></div>
      </div>
      <div class="item">
        <div class="top">
          <p class="corp">삼성전자</p>
          <p class="number">6,800,000<em>원</em></p>
        </div>
        <div class="flex-wrap">
          <p class="goal">
            <em>+650,000 원</em>
            <em>2.71%</em>
          </p>
        </div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage"><input type="range"></div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률</p>
            <p class="right">15% (35,000원)</p>
          </div>
        </div>
        <div class="gage"><input type="range"></div>
      </div>
      <div class="btn-wrap">
        <a class="blue solid">나의 투자 성향 알아보기</a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a class="blue box solid">완료</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "stockSpcfAnls",
  data() {
    return {
      errMsg: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "목표 수익률 설정";
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
