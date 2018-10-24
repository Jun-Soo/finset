<template>
  <section>

    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev"></button>
        <p>2018.07</p>
        <button class="next"></button>
      </div>
      <div class="owl-carousel spend-wrap">
        <div class="wrap">
          <div class="item">
            <p class="key">대금<em>(원)</em></p>
            <p class="value">4,550,000</p>
          </div>
          <div class="item">
            <p class="key">청구카드수</p>
            <p class="value">3</p>
          </div>
        </div>
        <div class="wrap">
          <div class="item">
            <p class="key">청구카드수</p>
            <p class="value">4,550,000</p>
          </div>
          <div class="item">
            <p class="key">수입<em>(원)</em></p>
            <p class="value">3</p>
          </div>
        </div>
        <div class="wrap">
          <div class="item">
            <p class="key">대금<em>(원)</em></p>
            <p class="value">4,550,000</p>
          </div>
          <div class="item">
            <p class="key">청구카드수</p>
            <p class="value">3</p>
          </div>
        </div>
      </div>
      <a href="#" class="btn">카드를 등록하고 간편하게 조회하세요</a>
    </div>

    <div class="box-list list01">
      <div class="filter-wrap">
        <div class="filter red">
          <input type="checkbox" id="chk1"><label for="chk1">박준수</label>
        </div>
        <div class="filter orange">
          <input type="checkbox" id="chk2"><label for="chk2">박준수</label>
        </div>
        <div class="filter green">
          <input type="checkbox" id="chk3"><label for="chk3">박준수</label>
        </div>
        <div class="filter blue">
          <input type="checkbox" id="chk4"><label for="chk4">박준수</label>
        </div>
        <div class="filter purple">
         <input type="checkbox" id="chk5"><label for="chk5">박준수</label>
        </div>
      </div>

      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
          <p class="circle red"><span class="red">준수</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">연체잔액</p>
            <p class="number">2,450,000<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">1234-1234-1234-****</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">2018.04.15</p>
          </div>
        </div>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
          <p class="circle"><span class="blue">준수</span></p>
        </div>
        <div class="number-wrap">
          <div class="left">
            <p class="key">연체잔액</p>
            <p class="number">2,450,000<em>원</em></p>
          </div>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">카드번호</p>
            <p class="value">1234-1234-1234-****</p>
          </div>
          <div class="right">
            <p class="key">결제일</p>
            <p class="value">2018.04.15</p>
          </div>
        </div>
      </div>

    </div>

  </section>
</template>

<script>
export default {
  name: "ConsumePayment",
  data() {
    return {
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드 대금 조회";
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
<style scoped>
</style>
