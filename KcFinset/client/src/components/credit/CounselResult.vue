<template>
    <section>
        <div class="counsel-result">
            <p class="title">상담결과</p>
            <p>안녕하세요.</p>
            <p>홍길동님의 경우엔 주로 할부결제와 단기카드대출(현금서비스) 이용금액이 많아 카드 채무가 신용도에 악영향을 주고 있으므로 앞으로는 일시불로 카드를 사용하는 것이 바람직합니다.</p>
            <p>우선 현실적인 목표로 단기카드대출(현금서비스)이용을 점차 줄여 6개월 내에 300만원 이하로 낮추고 다시 6개월 내에 잔액을 모두 상환한다는 계획을 세워 실행에 옮기도록 하는 것이 좋습니다.</p>
            <p>잔액을 모두 상환하면 1등급 정도는 상승할 것으로 예상됩니다.</p>
        </div>
    </section>
</template>

<script>
export default {
  name: "CreditCounselResult",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용상담";
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
