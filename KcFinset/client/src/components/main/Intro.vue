<template>
  <div id="wrapper" class="service-intro">
    <!-- Content -->
    <section id="content">
      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_phone_1.png" alt="" />
                <p class="value">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_phone_1.png" alt="" />
                <p class="value">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_phone_1.png" alt="" />
                <p class="value">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a href="#">
              <div class="banner">
                <img src="./../../assets/images/intro_phone_1.png" alt="" />
                <p class="value">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
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
