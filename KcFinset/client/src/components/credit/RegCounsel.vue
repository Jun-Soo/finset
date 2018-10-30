<template>
    <section>
        <div class="con-top counsel-top">
            <p>홍길동님의<br><em>신용상담이 완료되었습니다</em></p>
        </div>
        
        <div class="box-list counsel-list noMG">
            <div class="item">
                <p class="date">2018.10.15</p>
                <p class="text">이상혁님의 신용상담 신청이 접수되었습니다.</p>
            </div>
            <div class="item">
                <p class="date">2018.10.15</p>
                <p class="text">이상혁님의 신용상담을  준비중입니다.</p>
            </div>
            <div class="item">
                <p class="date">2018.10.15</p>
                <p class="text">이상혁님의 신용상담이 완료되었습니다.</p>
                <a href="#" class="btn-result">상담결과</a>
            </div>
        </div>
    </section>
</template>

<script>
export default {
  name: "CreditRegCounsel",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용상담";
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
