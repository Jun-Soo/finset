<template>
  <section>
    <div class="inout-list">
      <ul class="flex">
        <li class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</li>
        <li class="num">110-01-234532</li>
      </ul>
      <ul class="flex">
        <li>거래시각</li>
        <li>2018.6.1 19:00</li>
      </ul>
      <ul class="flex">
        <li>거래구분</li>
        <li>출금</li>
      </ul>
      <ul class="flex">
        <li>적요</li>
        <li>인터넷</li>
      </ul>
      <ul class="flex">
        <li>내용</li>
        <li>용돈</li>
      </ul>
      <ul class="flex">
        <li>거래금액</li>
        <li>118,107</li>
      </ul>
      <ul class="flex">
        <li>거래후 금액</li>
        <li>7,876,839</li>
      </ul>
    </div>

    <div class="inout-setting">
      <ul class="flex">
        <li>지출 설정</li>
        <li>
          <select>
            <option>신한카드</option>
          </select>
        </li>
      </ul>
      <ul class="flex">
        <li>공유차단</li>
        <li><button class="btn-onoff"></button></li>
      </ul>
      <p>차단시 상대방에게 입출금 내역을 보이지 않습니다.</p>
    </div>

    <div class="cs-top">
      <div class="links">
        <a @click="$router.push('')" class="kakao">카카오톡 문의</a>
        <a @click="$router.push('')" class="copy">복사</a>

      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "AssetsAccountDepDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "입출금상세";
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
