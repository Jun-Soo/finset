<template>
  <section>
    <!--
    <div class="sub-top">
      <a href="#" class="btn-back"></a>
      <p class="title">투자 성향 알아보기</p>
    </div>
    -->
    <div class="gray-box">
      <p class="title">귀하가 보유한 포트폴리오의 분석을 위해 설문조사를 진행합니다.</p>
      <p class="text">개인 투자 성향에 맞는 정확한 진단을 위함이니 아래 질문을 꼼꼼하게 읽어 보시고 솔직하게 답변을 선택해 주시길 바랍니다.</p>
    </div>

    <div class="basic-list">
      <dl>
        <dt>1. 귀하의 투자 경험은 몇 년입니까?</dt>
        <dd>
          <select>
            <option>3년</option>
          </select>
        </dd>
        <dt>2. 투자 종목 선정 방법을 선택하세요.</dt>
        <dd>
          <select>
            <option>주변 추천(지인)</option>
          </select>
        </dd>
        <dt>3. 감당할 수 있는 최대한의 손실은 얼마인가요?</dt>
        <dd>
          <select>
            <option>원금의 10%</option>
          </select>
        </dd>
        <dt>4. 보유 주식의 평균 보유 예정 기간은 얼마인가요?</dt>
        <dd>
          <select>
            <option>3개월</option>
          </select>
        </dd>
        <dt>5. 목표 수익률은 얼마인가요? (1종목 당)</dt>
        <dd>
          <select>
            <option>5%</option>
          </select>
        </dd>
        <dt>6. 목표 수익률은 얼마인가요? (연간)</dt>
        <dd>
          <select>
            <option>10%</option>
          </select>
        </dd>
        <dt>7. 주식 투자에 할애하는 시간은 얼마나 되나요? (1주일 당)</dt>
        <dd>
          <select>
            <option>5시간</option>
          </select>
        </dd>
        <dt>8. 손절매를 실행하시나요?</dt>
        <dd>
          <select>
            <option>원금 손실 즉시</option>
          </select>
        </dd>
      </dl>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="blue box solid">다음</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "stockGoalSet",
  data() {
    return {
      errMsg: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "투자 성향 알아보기";
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
