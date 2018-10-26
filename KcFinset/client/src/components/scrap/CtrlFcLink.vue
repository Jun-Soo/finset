<template>
  <section>
      
      <div class="con-top cert-top">
          <p>공인인증서로<em>한번에 등록하세요</em></p>
          <a href="#">바로가기</a>
      </div>
      
      <div class="tab">
          <div class="wrap">
              <a href="#" class="on">은행</a>
              <a href="#">카드</a>
              <a href="#">증권</a>
              <a href="#">기타</a>
          </div>
      </div>
      
      <div class="box-list list02 noMG">
          <div class="item">
              <div class="flex">
                  <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
                  <div class="btn">
                      <button></button>
                      <div class="menu">
                          <a href="#">연결해제</a>
                          <a href="#">연동정보 변경</a>
                      </div>
                  </div>
              </div>
          </div>
          <div class="item">
              <div class="flex">
                  <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
                  <div class="btn">
                      <button></button>
                      <div class="menu">
                          <a href="#">연결해제</a>
                          <a href="#">연동정보 변경</a>
                      </div>
                  </div>
              </div>
              <p class="warn"><a href="#">인증서 비밀번호가 일치하지 않습니다.</a></p>
          </div>
          <div class="item">
              <div class="flex">
                  <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt=""/>국민은행</p>
                  <div class="btn"><a href="#">연동하기</a></div>
              </div>
          </div>
      </div>
      
      
  </section>
</template>

<script>
export default {
  name: "ScrapCtrlFcLink",
  data() {
    return {};
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금융사 연동";
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
