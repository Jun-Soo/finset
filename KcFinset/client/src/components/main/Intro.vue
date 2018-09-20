<template>
  <div id="wrapper" class="service-intro">
    <!-- Content -->
    <section id="content">
      <div class="slide-item">
        <div class="slider">
          <swiper :pagination-visible="true" :loop="true">
            <!-- slide1 -->
            <div class="intro-item intro-01">
              <p class="intro-copy">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
              <img src="./../../assets/images/intro_phone_1.png" alt="" title="">
            </div>
            <!-- slide2 -->
            <div class="intro-item intro-02">
              <p class="intro-copy">수많은 <strong>대출정보를<br>한곳에서</strong> 관리하세요.</p>
              <img src="./../../assets/images/intro_phone_2.png" alt="" title="">
            </div>
            <!-- slide3 -->
            <div class="intro-item intro-03">
              <p class="intro-copy">다양한 금융기관의 <strong>대출금리와<br>한도를 확인</strong>해보세요.</p>
              <img src="./../../assets/images/intro_phone_3.png" alt="" title="">
            </div>
          </swiper>
        </div>
      </div>
      <div class="btn-fixed-bottom affix-bottom">
        <router-link to="/member/certStep1">
          <a href="#" role="button" class="btn btn-lg btn-block btn-primary">시작하기</a>
        </router-link>
      </div>
    </section>
    <!-- //Content -->
  </div>
</template>

<script>
export default {
  name: 'Intro',
  data() {
    return {
      errMsg: ''
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
