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
            <em>-</em>
          </li>
          <li>
            <em class="key">상태</em>
            <em class="state">대기중</em>
          </li>
        </ul>
      </div>
    </div>

    <div class="box-list noMG list02">
      <div class="item">
        <div class="flex">
          <p class="corp big">신용등급(연체정보 포함)</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">대출정보</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">자산정보</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">공유요청</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareSetting",
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
