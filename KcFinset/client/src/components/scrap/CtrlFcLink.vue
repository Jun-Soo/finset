<template>
  <section>
    <div class="con-top cert-top">
      <p>공인인증서로<em>한번에 등록하세요</em></p>
      <a href="#" @click="checkExistCert()">바로가기</a>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#" :class="{'on':curTab === 'bank'}" @click="tabOnClick('bank')">은행</a>
        <a href="#" :class="{'on':curTab === 'card'}" @click="tabOnClick('card')">카드</a>
        <a href="#" :class="{'on':curTab === 'stock'}" @click="tabOnClick('stock')">증권</a>
        <a href="#" :class="{'on':curTab === 'etc'}" @click="tabOnClick('etc')">기타</a>
      </div>
    </div>

    <div class="box-list list02 noMG" v-if="'bank'==this.curTab">
      <div class="item" v-for="bank in bankList" :key="bank.index">
        <div class="flex">
          <p class="symbol"><img :src="bank.icon" alt="" />{{bank.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':bank.isClickMenu}" v-if="bank.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(bank.cd_fc)"></button>
            <div class="menu" v-if="bank.isClickLink">
              <a href="#" @click="clickCert(bank)">공인인증서</a>
              <a href="#" @click="clickId(bank)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a href="#" @click="clickUnlink(bank)">연결해제</a>
              <a href="#" @click="clickLink(bank.cd_fc)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':bank.isClickLink}" v-else>
            <a href="#" class="btn-interlock" @click="clickLink(bank.cd_fc)">연동하기</a>
            <div class="menu">
              <a href="#" @click="clickCert(bank)">공인인증서</a>
              <a href="#" @click="clickId(bank)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="bank.cd_link_stat=='99' && bank.rsn_link_message != null"><a href="#">{{bank.rsn_link_message}}</a></p>
      </div>
    </div>

    <div class="box-list list02 noMG" v-else-if="'card'==this.curTab">
      <div class="item" v-for="card in cardList" :key="card.index">
        <div class="flex">
          <p class="symbol"><img :src="card.icon" alt="" />{{card.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':card.isClickMenu}" v-if="card.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(card.cd_fc)"></button>
            <div class="menu" v-if="card.isClickLink">
              <a href="#" @click="clickCert(card)">공인인증서</a>
              <a href="#" @click="clickId(card)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a href="#" @click="clickUnlink(card)">연결해제</a>
              <a href="#" @click="clickLink(card.cd_fc)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':card.isClickLink}" v-else>
            <a href="#" class="btn-interlock" @click="clickLink(card.cd_fc)">연동하기</a>
            <div class="menu">
              <a href="#" @click="clickCert(card)">공인인증서</a>
              <a href="#" @click="clickId(card)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="card.cd_link_stat=='99' && card.rsn_link_message != null"><a href="#">{{card.rsn_link_message}}</a></p>
      </div>
    </div>
    <div class="box-list list02 noMG" v-else-if="'stock'==this.curTab && stockList">
      <div class="item" v-for="stock in stockList" :key="stock.index">
        <div class="flex">
          <p class="symbol"><img :src="stock.icon" alt="" />{{stock.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':stock.isClickMenu}" v-if="stock.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(stock.cd_fc)"></button>
            <div class="menu">
              <a href="#" @click="clickUnlink(stock)">연결해제</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':stock.isClickLink}" v-else>
            <a href="#" class="btn-interlock" @click="clickLink(stock.cd_fc)">연동하기</a>
          </div>
        </div>
        <p class="warn" v-if="stock.cd_link_stat=='99' && stock.rsn_link_message != null"><a href="#">{{stock.rsn_link_message}}</a></p>
      </div>
    </div>
    <div class="box-list list02 noMG" v-else-if="'etc'==this.curTab && etcList">
      <div class="item" v-for="etc in etcList" :key="etc.index">
        <div class="flex">
          <p class="symbol"><img :src="etc.icon" alt="" />{{etc.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':etc.isClickMenu}" v-if="etc.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(etc.cd_fc)"></button>
            <div class="menu" v-if="etc.isClickLink">
              <a href="#" @click="clickCert(etc)">공인인증서</a>
              <a href="#" @click="clickId(etc)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a href="#" @click="clickUnlink(etc)">연결해제</a>
              <a href="#" @click="clickLink(etc.cd_fc)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':etc.isClickLink}" v-else>
            <a href="#" class="btn-interlock" @click="clickLink(etc.cd_fc)">연동하기</a>
            <div class="menu">
              <a href="#" @click="clickCert(etc)">공인인증서</a>
              <a href="#" @click="clickId(etc)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="etc.cd_link_stat=='99' && etc.rsn_link_message != null"><a href="#">{{etc.rsn_link_message}}</a></p>
      </div>
    </div>
    <div class="nodata" v-else>
      등록 내역이 없습니다.
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";

export default {
  name: "ScrapCtrlFcLink",
  data() {
    return {
      curTab: "bank",
      bankList: [],
      cardList: [],
      etcList: []
    };
  },
  components: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금융사 연동";
  },
  created() {
    // Native 연동 스크립트 등록
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
    window.resultUpdateScrapInfo = this.resultUpdateScrapInfo;
  },
  beforeMount() {},
  mounted() {
    this.tabOnClick(this.curTab);
    this.listFcLinkInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},

  methods: {
    tabOnClick: function(type) {
      this.curTab = type;
    },
    clickMenu: function(cd_fc) {
      if (this.curTab == "bank") {
        for (var i = 0; i < this.bankList.length; i++) {
          if (this.bankList[i].cd_fc == cd_fc) {
            let bank = this.bankList[i];
            bank.isClickMenu = !bank.isClickMenu;
            this.$set(this.bankList, i, bank);
          } else {
            this.bankList[i].isClickMenu = false;
            this.bankList[i].isClickLink = false;
          }
        }
      } else if (this.curTab == "card") {
        for (var i = 0; i < this.cardList.length; i++) {
          if (this.cardList[i].cd_fc == cd_fc) {
            let card = this.cardList[i];
            card.isClickMenu = !card.isClickMenu;
            this.$set(this.cardList, i, card);
          } else {
            this.cardList[i].isClickMenu = false;
            this.cardList[i].isClickLink = false;
          }
        }
      } else if (this.curTab == "etc") {
        for (var i = 0; i < this.etcList.length; i++) {
          if (this.etcList[i].cd_fc == cd_fc) {
            let etc = this.etcList[i];
            etc.isClickMenu = !etc.isClickMenu;
            this.$set(this.etcList, i, etc);
          } else {
            this.etcList[i].isClickMenu = false;
            this.etcList[i].isClickLink = false;
          }
        }
      }
    },
    clickLink: function(cd_fc) {
      if (this.curTab == "bank") {
        for (var i = 0; i < this.bankList.length; i++) {
          if (this.bankList[i].cd_fc == cd_fc) {
            let bank = this.bankList[i];
            bank.isClickLink = !bank.isClickLink;
            this.$set(this.bankList, i, bank);
          } else {
            this.bankList[i].isClickLink = false;
            this.bankList[i].isClickMenu = false;
          }
        }
      } else if (this.curTab == "card") {
        for (var i = 0; i < this.cardList.length; i++) {
          if (this.cardList[i].cd_fc == cd_fc) {
            let card = this.cardList[i];
            card.isClickLink = !card.isClickLink;
            this.$set(this.cardList, i, card);
          } else {
            this.cardList[i].isClickLink = false;
            this.cardList[i].isClickMenu = false;
          }
        }
      } else if (this.curTab == "etc") {
        for (var i = 0; i < this.etcList.length; i++) {
          if (this.etcList[i].cd_fc == cd_fc) {
            let etc = this.etcList[i];
            etc.isClickLink = !etc.isClickLink;
            this.$set(this.etcList, i, etc);
          } else {
            this.etcList[i].isClickLink = false;
            this.etcList[i].isClickMenu = false;
          }
        }
      } else if (this.curTab == "stock") {
        // 공인인증서 연동(증권 전용)
      }
    },
    clickUnlink: function(bank) {
      var _this = this;
      var formData = new FormData();
      console.log(this.$store.state);
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("cd_fc", bank.cd_fc);
      this.$http
        .post("/m/scrap/unlinkScrapFc.json", formData)
        .then(function(response) {
          var result = response.data.result;
          if (result == "00") {
            _this.$toast.center("연결해제가 완료되었습니다.");
            setTimeout(function() {
              _this.listFcLinkInfo();
            }, 2000);
          }
        });
    },
    clickCert: function(bank) {
      var no_person = this.$store.state.user.noPerson;
      var cd_coocon = bank.cd_coocon;
      var nm_code = bank.nm_code;
      var nm_person = this.$store.state.user.nmPerson;
      var agency = null;
      if (nm_code == "은행") {
        agency = "bank";
      } else if (nm_code == "카드") {
        agency = "card";
      } else if (nm_code == "기타") {
        agency = "etc";
      }
      if (Constant.userAgent == "IOS") {
        // do nothing
      } else if (Constant.userAgent == "Android") {
        window.Android.updateAvaliableCertScrapInfo(
          no_person,
          agency,
          cd_coocon,
          nm_person
        );
      }
    },
    clickId: function(bank) {
      var no_person = this.$store.state.user.noPerson;
      var cd_coocon = bank.cd_coocon;
      var nm_code = bank.nm_code;

      var agency = null;
      if (nm_code == "은행") {
        agency = "bank";
      } else if (nm_code == "카드") {
        agency = "card";
      } else if (nm_code == "기타") {
        agency = "etc";
      }

      if (Constant.userAgent == "IOS") {
        Jockey.on("checkPasswordCert", function(param) {
          resultCheckPasswordCert();
        });
        Jockey.send("updateAvaliableLoginScrapInfo", {
          noPerson: no_person,
          nmPerson: agency,
          cd_coocon: cd_coocon
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.updateAvaliableLoginScrapInfo(
          no_person,
          agency,
          cd_coocon
        );
      }
    },
    listFcLinkInfo: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.$store.state.user.noPerson);
      this.$http
        .post("/m/scrap/listFcLinkInfo.json", formData)
        .then(function(response) {
          _this.bankList = response.data.bankList;
          _this.cardList = response.data.cardList;
          _this.stockList = response.data.stockList;
          _this.etcList = response.data.etcList;
          if (_this.bankList) {
            for (var i = 0; i < _this.bankList.length; i++) {
              _this.bankList[i].icon =
                "/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
                _this.bankList[i].cd_fc;
              _this.bankList[i].isClickMenu = false;
              _this.bankList[i].isClickLink = false;
            }
          }
          if (_this.cardList) {
            for (var i = 0; i < _this.cardList.length; i++) {
              _this.cardList[i].icon =
                "/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
                _this.cardList[i].cd_fc;
              _this.cardList[i].isClickMenu = false;
              _this.cardList[i].isClickLink = false;
            }
          }
          if (_this.stockList) {
            for (var i = 0; i < _this.stockList.length; i++) {
              _this.stockList[i].icon =
                "/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
                _this.stockList[i].cd_fc;
            }
          }
          if (_this.etcList) {
            for (var i = 0; i < _this.etcList.length; i++) {
              _this.etcList[i].icon =
                "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + _this.etcList[i].cd_fc;
              _this.etcList[i].isClickMenu = false;
              _this.etcList[i].isClickLink = false;
            }
          }
        });
    },
    // 공인인증서 유무 체크
    checkExistCert: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert", function(param) {
          var iscert = false;
          if (param.isCert == 1) iscert = true;
          _this.resultCheckCert(iscert);
        });
        Jockey.send("checkExistCert");
      } else if (Constant.userAgent == "Android") {
        window.Android.checkExistCert();
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      var _this = this;
      if (isCert) {
        // 공인인증서가 있을 경우
        if (Constant.userAgent == "iOS") {
          Jockey.on("checkPasswordCert", function(param) {
            _this.resultCheckPasswordCert();
          });
          Jockey.send("checkPasswordCert", {
            noPerson: this.$store.state.user.noPerson,
            nmPerson: this.$store.state.user.nmPerson
          });
          //do nothing
        } else if (Constant.userAgent == "Android") {
          window.Android.checkPasswordCert(
            this.$store.state.user.noPerson,
            this.$store.state.user.nmPerson
          );
        }
      } else {
        // 공인인증서가 없을 경우
        this.$toast.center("공인인증서가 없습니다.");
        this.login();
      }
    },
    resultCheckPasswordCert: function(dn, cn) {
      // 금융정보제공동의서 확인여부 체크 필요
      this.$router.push({ name: "scrapSelFcLink", params: { dn: dn, cn: cn } });
    },
    resultUpdateScrapInfo: function(cd_err, msg_err) {
      // 금융정보제공동의서 확인여부 체크 필요
      var _this = this;
      if (cd_err == "00000000") {
        this.$toast.center("금융사 연동이 완료되었습니다.");
        setTimeout(function() {
          _this.listFcLinkInfo();
        }, 2000);
      } else {
        this.$toast.center(
          "금융사 연동이 실패하였습니다. </br>(" + msg_err + ")"
        );
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
