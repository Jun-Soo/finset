<template>
  <div>
    <button class="open-menu">메뉴열기</button>
    <div class="gnb-wrap">
      <div class="top">
        <div class="left">
          <button class="alarm" @click="$router.push('/etc/alarmHistory')"></button>
          <button class="logout" @click="$router.push('/mypage/logout')"></button>
        </div>
        <div class="right">
          <button class="gnb-close"></button>
        </div>
      </div>
      <div class="scroll-wrap">
        <div class="info">
          <p class="name">{{this.$store.state.user.nmPerson}}</p>
          <p class="date">최근 접속 {{connectTime}} </p>
          <a @click="clickButton('/mypage/info')">MYPAGE</a>
        </div>
        <div class="links">
          <a href="#">고객센터</a>
          <a href="#">인증보안</a>
          <a href="#">공지/이벤트</a>
          <a href="#">개인설정</a>
          <a href="#">금융달력</a>
          <a @click="clickButton('/share/main')">정보공유관리</a>
          <a href="#">금융계산기</a>
          <a href="#">뉴스</a>
        </div>
        <dl id="gnb">
          <dt>신용</dt>
          <dd><a href="#">신용정보</a></dd>
          <dd><a href="#">신용등급올리기</a></dd>
          <dd><a href="#">스마트리포트</a></dd>
          <dd><a href="#">신용상담</a></dd>
          <dd><a href="#">신용정보제공</a></dd>

          <dt>부채</dt>
          <dd><a href="#">부채현황</a></dd>
          <dd><a href="#">계산기</a></dd>
          <dd><a href="#">금리인하요구</a></dd>
          <dd><a href="#">추천상품</a></dd>

          <dt>소비지출</dt>
          <dd><a href="#">소비/지출 현황</a></dd>
          <dd><a href="#">지출캘린더</a></dd>
          <dd><a href="#">카드대금 조회</a></dd>

          <dt>자산</dt>
          <dd><a href="#">자산현황</a></dd>
          <dd><a href="#">은행</a></dd>
          <dd><a href="#">증권</a></dd>
          <dd><a href="#">기타</a></dd>
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "error",
  data() {
    return {
      errMsg: "",
      connectTime: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.getConnectTime();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 최근접속시간 조회
    getConnectTime: function() {
      var _this = this;
      this.$http
        .get("/m/person/getPersonConnectTime.json", {
          params: {}
        })
        .then(response => {
          _this.connectTime = response.data.connectTime;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    clickButton: function(page) {
      // GNB Close Event
      $(".gnb-wrap").removeClass("on");
      $("body").removeClass("not-scroll");
      this.$router.push(page);
    }
    // goBack: function() {
    //   var historySize = history.length;
    //   if (historySize == undefined || historySize == 0) {
    //     if (Constant.userAgent == "Android") {
    //       window.Android.exitApp();
    //     }
    //   } else {
    //     history.back();
    //   }
    // }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
