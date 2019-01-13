<template>
  <section @click="clickClear" v-if="seen">
    <div class="con-top cert-top">
      <p>공인인증서로<em> 한번에 등록하세요</em></p>
      <a @click="checkExistCert(false, $event)">바로가기</a>
    </div>

    <div class="tab">
      <div class="wrap">
        <a :class="{'on':curTab === 'bank'}" @click="tabOnClick('bank', $event)">은행</a>
        <a :class="{'on':curTab === 'card'}" @click="tabOnClick('card', $event)">카드</a>
        <!-- <a :class="{'on':curTab === 'stock'}" @click="tabOnClick('stock', $event)">증권</a> -->
        <a :class="{'on':curTab === 'etc'}" @click="tabOnClick('etc', $event)">기타</a>
      </div>
    </div>

    <div class="box-list list02 noMG" v-if="'bank'==this.curTab">
      <div class="item" v-for="bank in bankList" :key="bank.index">
        <div class="flex">
          <p class="symbol"><img :src="bank.icon" alt="" />{{bank.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':bank.isClickMenu}" v-if="bank.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(bank.cd_fc, $event)"></button>
            <div class="menu" v-if="bank.isClickLink">
              <a @click="clickCert(bank, $event)">공인인증서</a>
              <a @click="clickId(bank, $event)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a @click="clickUnlink(bank, $event)">연결해제</a>
              <a @click="clickLink(bank.cd_fc, $event)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':bank.isClickLink}" v-else>
            <a class="btn-interlock" @click="clickLink(bank.cd_fc, $event)">연동하기</a>
            <div class="menu">
              <a @click="clickCert(bank, $event)">공인인증서</a>
              <a @click="clickId(bank, $event)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="bank.cd_link_stat=='99' && bank.rsn_link_message != null && bank.rsn_link_message.length > 0"><a>{{bank.rsn_link_message}}</a></p>
      </div>
    </div>

    <div class="box-list list02 noMG" v-else-if="'card'==this.curTab">
      <div class="item" v-for="card in cardList" :key="card.index">
        <div class="flex">
          <p class="symbol"><img :src="card.icon" alt="" />{{card.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':card.isClickMenu}" v-if="card.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(card.cd_fc, $event)"></button>
            <div class="menu" v-if="card.isClickLink">
              <a @click="clickCert(card, $event)">공인인증서</a>
              <a @click="clickId(card, $event)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a @click="clickUnlink(card, $event)">연결해제</a>
              <a @click="clickLink(card.cd_fc, $event)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':card.isClickLink}" v-else>
            <a class="btn-interlock" @click="clickLink(card.cd_fc, $event)">연동하기</a>
            <div class="menu">
              <a @click="clickCert(card, $event)">공인인증서</a>
              <a @click="clickId(card, $event)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="card.cd_link_stat=='99' && card.rsn_link_message != null && card.rsn_link_message.length> 0"><a>{{card.rsn_link_message}}</a></p>
      </div>
    </div>
    <div class="box-list list02 noMG" v-else-if="'stock'==this.curTab && stockList">
      <div class="item" v-for="stock in stockList" :key="stock.index">
        <div class="flex">
          <p class="symbol"><img :src="stock.icon" alt="" />{{stock.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':stock.isClickMenu}" v-if="stock.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(stock.cd_fc, $event)"></button>
            <div class="menu">
              <a @click="clickUnlink(stock, $event)">연결해제</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':stock.isClickLink}" v-else>
            <a class="btn-interlock" @click="clickCert(stock, $event)">연동하기</a>
          </div>
        </div>
        <p class="warn" v-if="stock.cd_link_stat=='99' && stock.rsn_link_message != null && stock.rsn_link_message.length> 0"><a>{{stock.rsn_link_message}}</a></p>
      </div>
    </div>
    <div class="box-list list02 noMG" v-else-if="'etc'==this.curTab && etcList">
      <div class="item" v-for="etc in etcList" :key="etc.index">
        <div class="flex">
          <p class="symbol"><img :src="etc.icon" alt="" />{{etc.nm_fc}}</p>
          <div class="btn-menu-wrap" :class="{'on':etc.isClickMenu}" v-if="etc.yn_link==='Y'">
            <button class="btn-menu-pop" @click="clickMenu(etc.cd_fc, $event)"></button>
            <div class="menu" v-if="etc.isClickLink">
              <a @click="clickCert(etc, $event)">공인인증서</a>
              <a @click="clickId(etc, $event)">아이디/비번</a>
            </div>
            <div class="menu" v-else>
              <a @click="clickUnlink(etc, $event)">연결해제</a>
              <a @click="clickLink(etc.cd_fc, $event)">연동정보 변경</a>
            </div>
          </div>
          <div class="btn-menu-wrap" :class="{'on':etc.isClickLink}" v-else>
            <a class="btn-interlock" @click="clickLink(etc.cd_fc, $event)">연동하기</a>
            <div class="menu">
              <a @click="clickCert(etc, $event)">공인인증서</a>
              <a @click="clickId(etc, $event)">아이디/비번</a>
            </div>
          </div>
        </div>
        <p class="warn" v-if="etc.cd_link_stat=='99' && etc.rsn_link_message != null && etc.rsn_link_message.length> 0"><a>{{etc.rsn_link_message}}</a></p>
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
      seen: false,
      curTab: "bank",
      bankList: [],
      cardList: [],
      stockList: [],
      etcList: [],
      //단일 update 용 Data
      isSingle: true,
      dn: "",
      agency: "",
      cd_coocon: "",
      isScrap: this.$route.query.isScrap
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
    window.resultCheckPasswordCertForUpdate = this.resultCheckPasswordCertForUpdate;
    window.resultUpdateScrapInfo = this.resultUpdateScrapInfo;
  },
  beforeMount() {},
  mounted() {
    if (this.$route.params.tab) {
      this.curTab = this.$route.params.tab;
    }
    this.tabOnClick(this.curTab, null);
    this.listFcLinkInfo();
    this.$store.state.header.backPath = "/main";

    //console.log("mount isScrap : " + this.isScrap);
    if (this.isScrap) {
      //console.log("mount autoScrap Start");
      this.$parent.$parent.isFcScrapDone = false;
      this.$parent.$parent.isStScrapDone = false;
      this.$parent.$parent.isScrapSuccess = true;
      this.$parent.$parent.startAutoScrap();
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},

  methods: {
    closeAllMenu: function() {
      // 전체 메뉴 닫기
      if ((this.bankList || "") != "") {
        for (var i = 0; i < this.bankList.length; i++) {
          let bank = this.bankList[i];
          bank.isClickMenu = false;
          bank.isClickLink = false;
          this.$set(this.bankList, i, bank);
        }
      }
      if ((this.cardList || "") != "") {
        for (var i = 0; i < this.cardList.length; i++) {
          let card = this.cardList[i];
          card.isClickMenu = false;
          card.isClickLink = false;
          this.$set(this.cardList, i, card);
        }
      }
      if ((this.stockList || "") != "") {
        for (var i = 0; i < this.stockList.length; i++) {
          let stock = this.stockList[i];
          stock.isClickMenu = false;
          stock.isClickLink = false;
          this.$set(this.stockList, i, stock);
        }
      }
      if ((this.etcList || "") != "") {
        for (var i = 0; i < this.etcList.length; i++) {
          let etc = this.etcList[i];
          etc.isClickMenu = false;
          etc.isClickLink = false;
          this.$set(this.etcList, i, etc);
        }
      }
    },
    tabOnClick: function(type, event) {
      if (event) {
        event.stopPropagation();
      }
      this.curTab = type;
      this.closeAllMenu();
    },
    clickClear: function() {
      //console.log("clickClear");
      this.closeAllMenu();
    },
    clickMenu: function(cd_fc, event) {
      event.stopPropagation();
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
      } else if (this.curTab == "stock") {
        for (var i = 0; i < this.stockList.length; i++) {
          if (this.stockList[i].cd_fc == cd_fc) {
            let stock = this.stockList[i];
            stock.isClickMenu = !stock.isClickMenu;
            this.$set(this.stockList, i, stock);
          } else {
            this.stockList[i].isClickMenu = false;
            this.stockList[i].isClickLink = false;
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
    clickLink: function(cd_fc, event) {
      event.stopPropagation();
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
      }
    },
    clickUnlink: function(bank, event) {
      event.stopPropagation();
      var _this = this;
      var formData = new FormData();
      //console.log(this.$store.state);
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
            }, 1000);
          }
        });
    },
    clickCert: function(fcInfo, event) {
      event.stopPropagation();
      this.closeAllMenu();
      var no_person = this.$store.state.user.noPerson;
      var cd_coocon = fcInfo.cd_coocon;
      var nm_code = fcInfo.nm_code;
      var nm_person = this.$store.state.user.nmPerson;
      var agency = null;
      if (nm_code == "은행") {
        agency = "bank";
      } else if (nm_code == "카드") {
        agency = "card";
      } else if (nm_code == "증권") {
        agency = "stock";
      } else if (nm_code == "기타") {
        agency = "etc";
      }

      this.agency = agency;
      this.cd_coocon = cd_coocon;

      this.checkExistCert(true, null);

      // if (Constant.userAgent == "iOS") {
      //   Jockey.on("updateAvaliableCertScrapInfo", function(param) {
      //     _this.resultUpdateScrapInfo(param.cd_err, param.msg_err);
      //   });
      //   Jockey.send("updateAvaliableCertScrapInfo", {
      //     noPerson: no_person,
      //     nmPerson: agency,
      //     cd_coocon: cd_coocon
      //   });
      // } else if (Constant.userAgent == "Android") {
      //   window.Android.updateAvaliableCertScrapInfo(
      //     no_person,
      //     agency,
      //     cd_coocon,
      //     nm_person
      //   );
      // }
    },
    clickId: function(bank, event) {
      event.stopPropagation();
      this.closeAllMenu();
      var _this = this;
      var no_person = this.$store.state.user.noPerson;
      var cd_coocon = bank.cd_coocon;
      var nm_code = bank.nm_code;

      var agency = null;
      if (nm_code == "은행") {
        agency = "bank";
      } else if (nm_code == "카드") {
        agency = "card";
      } else if (nm_code == "증권") {
        agency = "stock";
      } else if (nm_code == "기타") {
        agency = "etc";
      }

      if (Constant.userAgent == "iOS") {
        Jockey.on("resultUpdateScrapInfo", function(param) {
          _this.resultUpdateScrapInfo(param.cd_err, param.msg_err);
          Jockey.off("resultUpdateScrapInfo");
        });
        Jockey.send("updateAvaliableLoginScrapInfo", {
          noPerson: no_person,
          agency: agency,
          cdCoocon: cd_coocon
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
          _this.seen = true;
        });
    },
    // 공인인증서 유무 체크
    checkExistCert: function(isSingle, event) {
      if (event) {
        event.stopPropagation();
      }
      var _this = this;
      this.isSingle = isSingle;
      //console.log("userAgent::" + Constant.userAgent);
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert", function(param) {
          //console.log("Jockey.on  : resultCheckCert");
          var iscert = "false";
          if (param.isCert == 1) iscert = "true";
          _this.resultCheckCert(iscert);
          Jockey.off("resultCheckCert");
        });
        Jockey.send("checkExistCert");
      } else if (Constant.userAgent == "Android") {
        window.Android.checkExistCert();
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      var _this = this;
      // 공인인증서가 있을 경우
      if (isCert == "true") {
        //console.log("this.isSingle  : " + this.isSingle);
        // 한개 금융사의 경우
        if (this.isSingle) {
          if (Constant.userAgent == "iOS") {
            Jockey.on("resultCheckPasswordCertForUpdate", function(param) {
              _this.resultCheckPasswordCertForUpdate(param.dn, param.cn);
              Jockey.off("resultCheckPasswordCertForUpdate");
            });
            Jockey.send("checkPasswordCertForUpdate", {
              noPerson: this.$store.state.user.noPerson,
              nmPerson: this.$store.state.user.nmPerson
            });
          } else if (Constant.userAgent == "Android") {
            window.Android.checkPasswordCertForUpdate(
              this.$store.state.user.noPerson,
              this.$store.state.user.nmPerson
            );
          }
        }
        // 전체 금융사의 경우
        else {
          if (Constant.userAgent == "iOS") {
            Jockey.on("resultCheckPasswordCert", function(param) {
              //console.log("param.dn : " + param.dn + " param.cn : " + param.cn);
              _this.resultCheckPasswordCert(param.dn, param.cn);
              Jockey.off("resultCheckPasswordCert");
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
        }
      } else {
        // 공인인증서가 없을 경우
        this.$toast.center("공인인증서가 없습니다.");
        this.login();
      }
    },
    resultCheckPasswordCert: function(dn, cn) {
      // 금융정보제공동의서 확인여부 체크 필요
      this.$router.push({
        name: "scrapSelFcLink",
        params: { isSingle: false, dn: dn, cn: cn }
      });
    },
    resultCheckPasswordCertForUpdate: function(dn, cn) {
      // 증권인 경우 금융정보제공동의서 화면으로 이동
      //console.log("this.agency  : " + this.agency);
      if (this.agency == "stock") {
        this.$router.push({
          name: "scrapSelFcLink",
          params: {
            isSingle: true,
            dn: dn,
            cn: cn,
            agency: this.agency,
            cd_coocon: this.cd_coocon
          }
        });
      }
      // 증권이 아닌 경우 스크래핑 로딩 화면으로 이동
      else {
        this.$router.push({
          name: "scrapLoading",
          params: {
            isSingle: true,
            dn: dn,
            cn: cn,
            normalMessage: "금융사 연동 가능여부를<br>확인 중 입니다.",
            smallMessage: "잠시만 기다려주세요.",
            agency: this.agency,
            cd_coocon: this.cd_coocon
          }
        });
      }
    },
    resultUpdateScrapInfo: function(cd_err, msg_err) {
      // 금융정보제공동의서 확인여부 체크 필요
      var _this = this;
      if (cd_err == "00000000") {
        this.$toast.center("금융사 연동이 완료되었습니다.");
        setTimeout(function() {
          //console.log("resultUpdateScrapInfo autoScrap Start");
          _this.$parent.$parent.isFcScrapDone = false;
          _this.$parent.$parent.isStScrapDone = false;
          _this.$parent.$parent.isScrapSuccess = true;
          _this.$parent.$parent.startAutoScrap();

          _this.listFcLinkInfo();
        }, 1000);
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
