<template>
    <section>
        <div class="credit-inquiry-top">
            <p class="key">이현중님의</p>
            <p class="title">2017년도 소득금액은<br><em>5,102만원</em> 입니다</p>
            <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
        </div>
        
        <div class="box-list list02">
            <div class="header">납부내역</div>
            <div class="item">
                <div class="flex">
                    <p><strong>근로소득 연말정산</strong></p>
                    <p class="corp">(주)코스콤</p>
                </div>
                <div class="flex">
                    <p>신고금액</p>
                    <p class="number">51,020,440<em>원</em></p>
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
  name: "CreditRaiseNts",
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
