<template>
  <section>
    <div class="container">
      <div class="bar-top noMG">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
          <div class="btn-menu-wrap">
            <button class="btn-menu-pop"></button>
            <div class="menu">
              <a href="#">수정</a>
              <a href="#">삭제</a>
              <a href="#">메모</a>
              <a href="#">전화걸기</a>
            </div>
          </div>
        </div>
        <p class="key">상환금액(당월)</p>
        <div class="wrap">
          <div class="left">650,000<em>원</em></div>
          <div class="right">
            <p class="key">잔액</p>
            <p class="value">130,000,000 원</p>
          </div>
        </div>
        <div class="bar">
          <p style="width: 25%"></p>
        </div>
        <div class="bar-text-wrap">
          <p class="bar-text">원금상환비율 <em>44%</em></p>
          <p class="bar-card">금리<em>2.75%</em><button class="btn-info"></button></p>
        </div>
      </div>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">계약정보</a>
        <a href="#">상환정보</a>
      </div>
    </div>

    <div class="block-list noMG">
      <ul class="list">
        <li>
          <p class="key">대출구분</p>
          <p class="value">담보대출</p>
        </li>
        <li>
          <p class="key">개설일자</p>
          <p class="value">2017.6.12</p>
        </li>
        <li>
          <p class="key">만기일자</p>
          <p class="value">2034.2.12</p>
        </li>
        <li>
          <p class="key">금리</p>
          <p class="value">2.72%</p>
        </li>
        <li>
          <p class="key">대출원금</p>
          <p class="value">23,000만원</p>
        </li>
        <li>
          <p class="key">대출기간</p>
          <p class="value">200개월</p>
        </li>
        <li>
          <p class="key">상환방법</p>
          <p class="value">분할상환</p>
        </li>
        <li>
          <p class="key">대출잔액</p>
          <p class="value">13,000만원</p>
        </li>
        <li>
          <p class="key">잔여기간</p>
          <p class="value">185개월</p>
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상세정보";
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
