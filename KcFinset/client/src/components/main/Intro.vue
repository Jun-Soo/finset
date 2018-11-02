<template>
  <div id="wrapper" class="service-intro">
    <!-- Content -->
    <section id="content">
      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
              <a href="#">
                  <div class="banner">
                    <div class="left">
                      <p class="value">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
                    </div>
                    <div class="right">
                      <img src="./../../assets/images/intro_phone_1.png" alt=""/>
                    </div>
                  </div>
              </a>
          </slide>
          <slide class="item">
              <a href="#">
                  <div class="banner">
                    <div class="left">
                    <p class="value">수많은 <strong>대출정보를<br>한곳에서</strong> 관리하세요.</p>
                    </div>
                    <div class="right">
                    <img src="./../../assets/images/intro_phone_2.png" alt=""/>
                     </div>
                  </div>
              </a>
          </slide>
          <slide class="item">
              <a href="#">
                  <div class="banner">
                    <div class="left">
                    <p class="value">다양한 금융기관의 <strong>대출금리와<br>한도를 확인</strong>해보세요.</p>
                    </div>
                    <div class="right">
                    <img src="./../../assets/images/intro_phone_3.png" alt=""/>
                     </div>
                  </div>
              </a>
          </slide>
        </carousel>
      </div>
      <div class="btn-wrap">
        <router-link to="/member/certStep1">
          <a class="btn-next" role="button">시작하기</a>
        </router-link>
      </div>
    </section>
    <!-- //Content-->
    
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
    // swiper:swiper
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
