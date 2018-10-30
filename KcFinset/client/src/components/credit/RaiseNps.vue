<template>
    <section>
        <div class="credit-inquiry-top">
            <p class="key">이현중님의 1년간</p>
            <p class="title"><em>488만원</em>(12개월)입니다</p>
            <p class="sub">2049년 11월부터 매월 113만원<br>지급 예상됩니다.(현재기준)</p>
            <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
        </div>
        
        <div class="box-list list01">
            <div class="header">납부내역</div>
            <div class="item">
                <div class="top">
                    <p>2018.07 ~ 2018.08</p>
                    <p class="corp">(주)코스콤</p>
                </div>
                <div class="number-wrap">
                    <div class="left">
                        <p class="key">납부(2개월)</p>
                        <p class="number">842,400<em>원</em></p>
                    </div>
                    <div class="right">
                        <p class="key">미납(0개월)</p>
                        <p class="value">0<em>원</em></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="top">
                    <p>2018.07 ~ 2018.08</p>
                    <p class="corp">(주)코스콤</p>
                </div>
                <div class="number-wrap">
                    <div class="left">
                        <p class="key">납부(2개월)</p>
                        <p class="number">842,400<em>원</em></p>
                    </div>
                    <div class="right">
                        <p class="key">미납(0개월)</p>
                        <p class="value">0<em>원</em></p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="btn-wrap noMG">
            <a href="#" class="solid box blue">전송하기</a>
        </div>
    </section>
</template>

<script>
export default {
  name: "CreditRaiseNps",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "납부 확인 및 전송하기";
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
