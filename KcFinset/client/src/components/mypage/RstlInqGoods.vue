<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a class="on">일반상품</a>
        <a>제휴상품</a>
      </div>
    </div>

    <div class="box-list list01">
      <div class="guide-box">
        <p><strong>대출 신청은 하나의 상품만 선택해 신청 가능합니다.</strong></p>
        <p class="mt10">대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도의 변동되거나 대출이 불가능 할 수 있습니다.</p>
        <a class="close"></a>
      </div>

      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <input type="checkbox" id="chk1"><label for="chk1"></label>
            <img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <p class="text blue">기타연체</p>
        </div>
        <p class="goods-name">KB 우량직장인 신용대출</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">대출한도</p>
            <p class="right">5,000 만원</p>
          </div>
          <div class="list">
            <p class="left">대출금리</p>
            <p class="right"><em class="state">변동</em>3.57 %</p>
          </div>
          <div class="list">
            <p class="left">대출기간</p>
            <p class="right">12개월</p>
          </div>
          <div class="list">
            <p class="left">상환방식</p>
            <p class="right">만기일시상환</p>
          </div>
        </div>
      </div>

      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <img src="../../assets/images/assets/ico_x.png" class="x" alt="" />
            <img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <p class="text red">기타연체</p>
        </div>
        <p class="goods-name">KB 우량직장인 신용대출</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">대출한도</p>
            <p class="right">5,000 만원</p>
          </div>
          <div class="list">
            <p class="left">대출금리</p>
            <p class="right"><em class="state">변동</em>3.57 %</p>
          </div>
          <div class="list">
            <p class="left">대출기간</p>
            <p class="right">12개월</p>
          </div>
          <div class="list">
            <p class="left">상환방식</p>
            <p class="right">만기일시상환</p>
          </div>
        </div>
      </div>

      <div class="item">
        <div class="top">
          <p class="symbol checks">
            <input type="checkbox" id="chk2"><label for="chk2"></label>
            <img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <p class="text">기타연체</p>
        </div>
        <p class="goods-name">KB 우량직장인 신용대출</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">대출한도</p>
            <p class="right">5,000 만원</p>
          </div>
          <div class="list">
            <p class="left">대출금리</p>
            <p class="right"><em class="state">변동</em>3.57 %</p>
          </div>
          <div class="list">
            <p class="left">대출기간</p>
            <p class="right">12개월</p>
          </div>
          <div class="list">
            <p class="left">상환방식</p>
            <p class="right">만기일시상환</p>
          </div>
        </div>
      </div>

    </div>

  </section>
</template>

<script>
export default {
  name: "MypageRstlInqGoods",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "관심상품";
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
