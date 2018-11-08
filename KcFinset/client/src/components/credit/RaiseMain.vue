<template>
  <section>
    <div class="con-top credit-up">
      <p><em>몇번의 클릭으로</em><br><em class="red">신용점수를 UP하세요</em></p>
      <a href="#">제도 설명 보기</a>
    </div>

    <div class="box-list list01">
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p class="text blue">전송<em>2018.07.12</em></p>
        </div>
        <p class="text-result">최근 1년간 건강보험료 납부 내역 등록</p>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p class="text red">전송<em>2018.07.12</em></p>
        </div>
        <p class="text-result">최근 1년간 건강보험료 납부 내역 등록</p>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p class="text blue">대기</p>
        </div>
        <p class="text-result">최근 1년간 건강보험료 납부 내역 등록</p>
      </div>
      <div class="item">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p class="text">준비중</p>
        </div>
        <p class="text-result">최근 1년간 건강보험료 납부 내역 등록</p>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "CreditRaiseMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용 등급 올리기";
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
