<template>
  <div id="wrapper" class="service-intro">
    <!-- Content -->
    <section id="content">
      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_1.png" alt="" />
                <p class="value">금융거래에 활용되는 신용등급을 확인하고<br>신용점수도 올려보세요.</p>
              </div>
            </a> </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_2.png" alt="" />
                <p class="value">흩어져 있는 대출정보를 <br>보기 쉽게 한곳에서 관리하세요.</p>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_3.png" alt="" />
                <p class="value">가족의 지출내역을<br>모아서 관리하고 공유해보세요.</p>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_4.png" alt="" />
                <p class="value">금융자산을 모아보고<br>효율적으로 관리되고 있는지 진단해보세요.</p>
              </div>
            </a>
          </slide>
        </carousel>
      </div>
      <div class="btn-wrap float">
        <router-link to="/member/certStep1">
          <a class="btn-next" role="button">시작하기</a>
        </router-link>
      </div>
    </section>
    <!-- //Content-->

  </div>
</template>

<script>
import Constant from "@/assets/js/constant.js";

export default {
  name: "Intro",
  data() {
    return {
      errMsg: ""
    };
  },
  component: {
    // swiper:swiper
  },
  // computed () {
  // },
  beforeCreate() {},
  created() {
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
  },
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
.banner-wrap .item a .banner img {
  width: 100%;
}
.banner-wrap .item a .banner p.value {
  color: #222;
  font-size: 16px;
  font-weight: 700;
  margin-top: 10px;
  line-height: 22px;
  text-align: center;
}
</style>
