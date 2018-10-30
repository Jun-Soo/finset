<template>
    <section>
        <div class="assets-top">
            <div class="wrap">
                <div class="item">
                    <p class="key">자산<em>(원)</em></p>
                    <p class="value">165,550,000</p>
                </div>
                <div class="item">
                    <p class="key">부채<em>(원)</em></p>
                    <p class="value">89,000,000</p>
                </div>
            </div>
        </div>
        
        <div class="banner-wrap owl-carousel">
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="banner">
                        <div class="left">
                            <p class="key">우리가족 가계부</p>
                            <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                        </div>
                        <div class="right">
                            <img src="../../assets/images/main/banner_ico.png" alt=""/>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        
        <div class="list02 box-list pb90">
            
            <div class="item">
                <div class="flex">
                    <p class="corp big">은행<em>3건</em></p>
                    <p class="number big">46,570,000<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="corp big">은행<em>3건</em></p>
                    <p class="number big">46,570,000<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="corp big">은행<em>3건</em></p>
                    <p class="number big">46,570,000<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="corp big">은행<em>3건</em></p>
                    <p class="number big">46,570,000<em>원</em></p>
                </div>
            </div>
            <div class="item">
                <div class="flex">
                    <p class="corp big">은행<em>3건</em></p>
                    <p class="number big">46,570,000<em>원</em></p>
                </div>
            </div>
            <button class="btn-spend-add"></button>
        </div>
        
    </section>
</template>

<script>
export default {
  name: "AssetsMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "assets";
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
