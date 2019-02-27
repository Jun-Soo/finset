<template>
  <div>
    <button class="open-menu">메뉴열기</button>
    <div class="gnb-wrap">
      <div class="top">
        <div class="left">
          <button class="alarm" @click="clickButton('/etc/alarmHistory', '/main')"></button>
          <button class="logout" @click="chkLogout"></button>
        </div>
        <div class="right">
          <button class="gnb-close"></button>
        </div>
      </div>
      <div class="scroll-wrap">
        <div class="info">
          <p class="name">{{this.$store.state.user.nmPerson}}</p>
          <p class="date">최근 접속 {{connectTime}} </p>
          <a @click="clickButton('/mypage/info', '/main')">MYPAGE</a>
        </div>
        <div class="links">
          <a @click="clickButton('/etc/faqMain', '/main')">고객센터</a>
          <a @click="clickButton('/mypage/cert', '/main')">인증보안</a>
          <a @click="clickButton('/etc/noticeMain', '/main')">공지/이벤트</a>
          <a @click="clickButton('/news/main', '/main')">금융뉴스</a>
          <a @click="clickButton('/scrap/ctrlFcLink', '/main')">금융사연동</a>
          <a @click="clickButton('/share/main?cd_share=02', '/credit/main')">금융정보공유</a>
          <a @click="clickButton('/common/monthCal', '/main')">금융달력</a>
          <!-- <a @click="clickButton('/debt/calc', '/main')">금융계산기</a> -->
        </div>
        <dl id="gnb">
          <dt>신용</dt>
          <dd><a @click="clickButton('/credit/main', '')">신용정보</a></dd>
          <!-- <dd><a @click="clickButton('/credit/raiseMain', '/credit/main')">신용등급올리기</a></dd> -->
          <dd><a @click="clickButton('/credit/smartReport', '/credit/main')">신용통계분석</a></dd>
          <!-- <dd><a @click="clickButton('/credit/counselMain', '/credit/main')">신용상담</a></dd> -->
          <dd><a @click="clickButton('/share/main?cd_share=01', '/credit/main')">신용정보제공</a></dd>

          <dt>부채</dt>
          <dd><a @click="clickButton('/debt/main', '')">부채현황</a></dd>
          <!-- <dd><a @click="clickButton('/debt/calc', '/debt/main')">계산기</a></dd> -->
          <dd><a @click="clickButton('/debt/reqIntrCut', '/debt/main')">금리인하요구</a></dd>
          <dd><a @click="clickButton('/goods/list', '/debt/main')">추천상품</a></dd>
          <!--           
          <dt>소비지출</dt>
          <dd><a @click="clickButton('/consume/main', '')">소비현황</a></dd>
          <dd><a @click="clickButton('/common/monthCal', '/consume/main')">지출캘린더</a></dd>
          <dd><a @click="clickButton('/consume/payment', '/consume/main')">카드대금조회</a></dd>
 -->
          <dt>자산</dt>
          <dd><a @click="clickButton('/assets/main', '')">자산현황</a></dd>
          <!-- <dd><a @click="clickButton('/assets/bankMain', '/assets/main')">은행</a></dd> -->
          <!-- <dd><a @click="clickButton('/assets/stockMain', '/assets/main')">증권</a></dd> -->
          <!-- <dd><a @click="clickButton('/assets/etcMain', '/assets/main')">기타</a></dd> -->
          <!-- <dd><a @click="clickButton('/assets/diagsMain', '/assets/main')">자산진단</a></dd> -->
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "./../../assets/js/constant.js";

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
    //logout 확인여부
    chkLogout: function() {
      let _this = this;
      _this.$dialogs
        .confirm("로그아웃 하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            _this.$router.push("/mypage/logout");
          } else {
            return false;
          }
        });
    },
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
    clickButton: function(page, back) {
      // GNB Close Event
      if (
        page === "/debt/calc" ||
        page === "/credit/counselMain" ||
        page === "/assets/stockMain"
      ) {
        this.$dialogs.alert("서비스 준비중 입니다.", Constant.options);
        return false;
      }
      $(".gnb-wrap").removeClass("on");
      $("body").removeClass("not-scroll");
      // if (this.$store.state.header.type === "sub" && back) {
      //   this.$store.state.header.fromPath = back;
      // }

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
