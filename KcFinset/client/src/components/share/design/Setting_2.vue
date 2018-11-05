<template>
  <section>
    <div class="con-top share-top">
      <p>홍길동님<em>에게 요청한 항목입니다.</em></p>
      <div class="request">
        <ul>
          <li>
            <em class="key">요청일</em>
            <em>2018.08.19</em>
          </li>
          <li>
            <em class="key">허용일</em>
            <em>2018.08.20</em>
          </li>
          <li>
            <em class="key">상태</em>
            <em class="state">공유중</em>
          </li>
        </ul>
      </div>
    </div>

    <div class="box-list noMG list02">
      <div class="item">
        <ul class="list-style06">
          <li>신용등급(연체정보 포함)</li>
          <li>대출정보</li>
          <li>자산정보</li>
        </ul>
      </div>
      <div class="btn-wrap">
        <a @click="$router.go('')" class="stroke blue">업데이트 요청하기</a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">공유 종료</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareSetting_2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용정보 제공항목";
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
