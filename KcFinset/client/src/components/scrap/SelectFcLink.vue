<template>
  <section>
    <div class="box-list noMG list02 pb90">
      <p class="header">은행</p>
      <div class="item">
        <div class="flex">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><button class="btn-onoff"></button></p>
        </div>
      </div>
      <p class="header">카드</p>
      <div class="item">
        <div class="flex">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><button class="btn-onoff"></button></p>
        </div>
      </div>
      <p class="header">기타</p>
      <div class="item">
        <div class="flex">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><button class="btn-onoff"></button></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap col2">
      <a @click="$router.push('')">취소</a>
      <a href="#" class="btn-solid">연결</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "ScrapFcLink",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "연동가능 금융사";
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
