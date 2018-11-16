<template>
  <div>
    <section>
      <div class="assets-bank-detail-top">
        <div class="item">
          <div class="top">
            <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
            <p class="text"><em class="blue bold">입출금</em><span class="circle red">준수</span></p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">110-125-700003</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">2,450,000</em>원</p>
            </div>
          </div>
        </div>
      </div>

      <div class="bank-detail">
        <div class="select">
          <div class="left">
            <select>
              <option>금리순</option>
            </select>
          </div>
          <div class="right">
            <button class="btn-search"></button>
          </div>
        </div>
        <div class="nobox-list">
          <p class="date">07.12</p>
          <div class="item">
            <div class="flex">
              <p><em class="circle red">준수</em><em>급여</em></p>
              <p><em class="number red">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">8,560,000원</p>
            </div>
          </div>
          <div class="item">
            <div class="flex">
              <p><em class="circle blue">준수</em><em>급여</em></p>
              <p><em class="number blue">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">8,560,000원</p>
            </div>
          </div>

          <p class="date">07.12</p>
          <div class="item">
            <div class="flex">
              <p><em class="circle red">준수</em><em>급여</em></p>
              <p><em class="number red">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">8,560,000원</p>
            </div>
          </div>
          <div class="item">
            <div class="flex">
              <p><em class="circle blue">준수</em><em>급여</em></p>
              <p><em class="number blue">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">8,560,000원</p>
            </div>
          </div>
        </div>
      </div>

    </section>

    <aside class="search-wrap">
      <div class="top">
        <button>검색</button>
      </div>
      <div class="wrap">
        <div class="hash">
          <a># 이체</a>
          <a># 국민대출</a>
          <a># 급여</a>
          <a># 황</a>
          <a># 통신비</a>
          <a># N빵</a>
          <a># 보험료</a>
          <a># 쇼파</a>
          <a># 신용카드</a>
          <a># 어머니 생신</a>
          <a># 입출금이자</a>
          <a># 자전거 계약금</a>
          <a># 대출</a>
          <a># 김수진</a>
          <a># 국민입금</a>
          <a># 공기청청기</a>
          <a># 집대출</a>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
export default {
  name: "AssetsDepWdrlDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "은행";
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
