<template>
  <section>
      <div class="container">
          <div class="search">
              <div class="left">
                  <select>
                      <option>최신날짜</option>
                      <option>많이 본 뉴스</option>
                  </select>
              </div>
              <div class="right">
                  <button class="btn-search"></button>
              </div>
          </div>
          <div class="board-list">
              <div class="list">
                  <div class="left">
                      <p class="title"><a href="#">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용 2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</a></p>
                      <div class="sub">
                          <p>한경</p>
                          <p>5/29</p>
                      </div>
                  </div>
                  <div class="right">
                      <img src="../../assets/images/common/news_dummy.png" alt=""/>
                  </div>
              </div>
              <div class="list">
                  <div class="left">
                      <p class="title"><a href="#">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용 2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</a></p>
                      <div class="sub">
                          <p>한경</p>
                          <p>5/29</p>
                      </div>
                  </div>
                  <div class="right">
                      <img src="../../assets/images/common/news_dummy.png" alt=""/>
                  </div>
              </div>
              <div class="list">
                  <div class="left">
                      <p class="title"><a href="#">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용 2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</a></p>
                      <div class="sub">
                          <p>한경</p>
                          <p>5/29</p>
                      </div>
                  </div>
                  <div class="right">
                      <img src="../../assets/images/common/news_dummy.png" alt=""/>
                  </div>
              </div>
              <div class="list">
                  <div class="left">
                      <p class="title"><a href="#">2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용 2%대 주택대출 확인…은행별 아파트 담보 대출 금리 비교 최저금리 활용</a></p>
                      <div class="sub">
                          <p>한경</p>
                          <p>5/29</p>
                      </div>
                  </div>
                  <div class="right">
                      <img src="../../assets/images/common/news_dummy.png" alt=""/>
                  </div>
              </div>
          </div>
      </div>
      <aside class="search-wrap">
          <div class="top">
              <button>검색</button>
          </div>
          <div class="wrap">
              <div class="btns">
                  <button>금리</button>
                  <button>대출</button>
                  <button>상품</button>
                  <button>신용</button>
              </div>
          </div>
      </aside>
  </section>
</template>

<script>
export default {
  name: "NewsMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "뉴스";
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
