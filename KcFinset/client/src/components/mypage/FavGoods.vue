
<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">일반상품</a>
        <a href="#">제휴상품</a>
      </div>
    </div>

    <div class="box-list list01 noMG pb90">
      <div class="search">
        <div class="left">
          <p class="total">총 <em>2</em>개의 상품</p>
          <p class="total">선택 (<em>1</em>개)</p>
        </div>
        <div class="right">
          <select>
            <option>신용대출(직장인)</option>
          </select>
        </div>
      </div>
      <div class="item">
        <a @click="$router.push('')">
          <div class="top">
            <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
            <p><button class="btn-star"></button></p>
          </div>
          <p class="goods-name">KB 우량직장인 신용대출</p>
          <p class="mt10">특징 및 간략설명 특징 및 간략설명 특징 및 간략설명</p>
          <p class="goods-cate"><em>금리우대</em><em>모바일</em><em>직장인</em></p>
          <div class="detail">
            <div class="wrap">
              <p>대출금리</p>
              <p class="rate"><em>변동</em>3.23~4.65%</p>
            </div>
            <div class="wrap">
              <p>대출한도/기간</p>
              <p>최대 20,000만원/60개월</p>
            </div>
          </div>
        </a>
      </div>
      <div class="item">
        <a @click="$router.push('')">
          <div class="top">
            <p class="symbol checks">
              <input type="checkbox" id="chk1"><label for="chk1"></label>
              <img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험
            </p>
            <p><button class="btn-star"></button></p>
          </div>
          <p class="goods-name">KB 우량직장인 신용대출</p>
          <p class="mt10">특징 및 간략설명 특징 및 간략설명 특징 및 간략설명</p>
          <p class="goods-cate"><em>금리우대</em><em>모바일</em><em>직장인</em></p>
          <div class="detail">
            <div class="wrap">
              <p>대출금리</p>
              <p class="rate"><em>변동</em>3.23~4.65%</p>
            </div>
            <div class="wrap">
              <p>대출한도/기간</p>
              <p>최대 20,000만원/60개월</p>
            </div>
          </div>
        </a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid box blue">금리/한도 조회하기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "MypageFavGoods",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "추천상품";
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
