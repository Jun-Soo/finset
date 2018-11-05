<template>
  <section>
    <div class="con-top share-top">
      <p><em>finset 정보 공유를 통해</em> <br>가족간의 흩어진 정보를<br>한눈에 볼수 있으며, <br><em>한번의 클릭으로 간편하게</em><br>정보를 통합 관리<em>할 수 있습니다.</em></p>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">나 → 타인</a>
        <a href="#">타인 → 나</a>
      </div>
    </div>

    <div class="box-list noMG list02 pb90">
      <p class="header col">허용 대기</p>
      <div class="item">
        <a @click="$router.go('')" class="share">
          <span class="name">홍길동</span>
          <span class="state">대기</span>
          <span>010-2222-4444</span>
        </a>
      </div>
      <p class="header col">공유된 타인</p>
      <div class="item">
        <a @click="$router.go('')" class="share">
          <p>
            <span class="name">이말순</span>
            <span>010-2222-4444</span>
          </p>
          <p class="mt10">
            <span><strong>2018.08.13</strong></span>
            <span class="state">대기<em>부채</em></span>
          </p>
        </a>
      </div>
      <div class="btn-wrap">
        <a @click="$router.go('')" class="stroke blue">이전 내역 보기</a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="$router.go('')" class="solid blue box">신규 요청하기</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용정보 공유";
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
