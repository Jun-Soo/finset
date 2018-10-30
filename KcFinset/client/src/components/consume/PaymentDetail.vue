<template>
    <section>
        <div class="card-detail-top list01">
            <div class="item expand">
                <div class="top">
                    <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
                    <p class="corp">카드명</p>
                </div>
                <div class="number-wrap card">
                    <div class="left">
                        <p class="key">결제금액</p>
                    </div>
                    <div class="right">
                        <p class="number">2,450,000<em>원</em></p>
                    </div>
                </div>
                <div class="text-wrap card">
                    <div class="left">
                        <p class="key">계좌번호</p>
                    </div>
                    <div class="right">
                        <p class="value">1234********1234</p>
                    </div>
                </div>
                <div class="hide-con card">
                    <div class="list">
                        <p class="left">청구일</p>
                        <p class="right">2018.08.25</p>
                    </div>
                    <div class="list">
                        <p class="left">사용한도</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                    <div class="list">
                        <p class="left">일시불</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                    <div class="list">
                        <p class="left">할부</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                    <div class="list">
                        <p class="left">해외</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                    <div class="list">
                        <p class="left">현금서비스</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                    <div class="list">
                        <p class="left">보유포인트</p>
                        <p class="right">2,300,000원/2,300,000원</p>
                    </div>
                </div>
                <button class="btn-expand"></button>
            </div>
        </div>
        
        <div class="card-detail-graph mt30">
        <img src="../../assets/images/consume/card-detail-graph.png" width="100%" alt=""/> </div>
        
        <div class="box-list noMG">
            <div class="card-detail-list pb30">
                <ul>
                    <li>
                        <p>연소득</p>
                        <p>18,107<em>원</em></p>
                    </li>
                    <li>
                        <p>온누리 학원<em>할부</em></p>
                        <p>118,107<em>원</em></p>
                    </li>
                    <li>
                        <p>ATM출금<em>현금</em></p>
                        <p>118,107<em>원</em></p>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</template>

<script>
export default {
  name: "ConsumePaymentDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드 대금 청구 상세";
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
