<template>
  <section>
    <div class="goods-state pb90">
      <p class="number">신청번호 : 2017101500023</p>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top deposit">
            <a href="#">
              <p class="title">대출금 입금</p>
              <p class="text">대출금 입금이 완료되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top cancel">
            <a href="#">
              <p class="title">대출 신청취소</p>
              <p class="text">대출 신청이 취소되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top reject">
            <a href="#">
              <p class="title">대출 심사 부결</p>
              <p class="text">대출 심사가 부결(거절)되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "MypageState",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상품신청현황";
    this.$store.state.isSetting = true;
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
