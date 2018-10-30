<template>
    <section>
        <div class="credit-inquiry-top">
            <p class="key">이현중님의 1년간</p>
            <p class="title">납입금액은 <em>203만원</em>(16개월),<br>추정 소득은 <em>6,721 만원</em>입니다</p>
            <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
        </div>
        
        <div class="box-list list02">
            <div class="header">납부내역</div>
            <div class="item">
                <div class="flex">
                    <p class="date">2018.10<em>납부</em></p>
                    <p class="number">171,400<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="date">2018.10<em>납부</em></p>
                    <p class="number">171,400<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="date">2018.10<em>납부</em></p>
                    <p class="number">171,400<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="date">2018.10<em>납부</em></p>
                    <p class="number">171,400<em>원</em></p>
                </div>
            </div>
        </div>
        
        <div class="btn-wrap noMG">
            <a href="#" class="solid box blue">전송하기</a>
        </div>
    </section>
</template>

<script>
export default {
  name: "CreditRaiseNhis",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "납부 확인 및 전송하기";
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
