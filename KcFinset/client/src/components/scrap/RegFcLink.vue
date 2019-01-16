<template>
  <section v-if="seen">
    <form name="frmFcLinkList" id="frmFcLinkList"></form>
    <div v-if="isData" class="box-list noMG list02 pb90">
      <div v-for="linkedFcInfo in linkedFcInfoList" :key="linkedFcInfo.index">
        <p class="header" v-if="linkedFcInfo.nm_code != ''">{{linkedFcInfo.nm_code}}</p>
        <div class="item">
          <div class="flex">
            <p class="symbol"><img :src="linkedFcInfo.icon" alt="" />{{linkedFcInfo.nm_fc}}</p>
            <p><button class="btn-onoff" :class="{'on':isLinked(linkedFcInfo.yn_link)}" @click="changeLinked(linkedFcInfo.cd_fc)"></button></p>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <div class="nodata">연동가능한 금융사가 없습니다</div>
    </div>

    <div class="btn-wrap col2">
      <a @click="clickCancel()">취소</a>
      <a class="btn-solid" @click="updateLinkedFcInfo()">연결</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "regFcLink",
  data() {
    return {
      seen: false,
      isSignup: this.$route.params.isSignup, //초기 등록 여부
      isData: false,
      noPerson: this.$store.state.user.noPerson,
      cn: this.$route.params.cn,
      dn: this.$route.params.dn,
      normalMessage: this.$route.params.normalMessage,
      smallMessage: this.$route.params.smallMessage,
      isScrapFcList: false,
      isScrapStList: false,
      linkedFcInfoList: [],
      type: ""
    };
  },
  computed: {
    getType() {
      return this.type;
    },
    setType() {
      return this.type;
    }
  },
  component: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "연동가능 금융사";
  },
  created() {
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
    window.resultIsMultipleCert = this.resultIsMultipleCert;

    this.getLinkedFcInfo();
  },
  beforeMount() {},
  mounted() {
    // 로그인이 되어있으면 관리화면으로 안되어 있으면 어플 종료
    if (this.$store.state.isLoggedIn) {
      this.$store.state.header.backPath = "/scrap/CtrlFcLink";
    } else {
      if (Constant.userAgent == "Android") {
        window.Android.setEndApp("Y");
      }
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    isLinked: function(yn_linked) {
      //console.log("isLinked : " + yn_linked);
      return yn_linked == "Y";
    },
    changeLinked: function(cd_fc) {
      //console.log("changeLinked : " + cd_fc);
      for (var i = 0; i < this.linkedFcInfoList.length; i++) {
        if (this.linkedFcInfoList[i].cd_fc == cd_fc) {
          if (this.linkedFcInfoList[i].yn_link == "N") {
            this.linkedFcInfoList[i].yn_link = "Y";
          } else {
            this.linkedFcInfoList[i].yn_link = "N";
          }
        }
      }
    },
    //자동스크래핑 가능 금융사 조회
    getLinkedFcInfo: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.noPerson);
      formData.append("cn", this.cn);
      formData.append("dn", this.dn);
      this.$http
        .post("/m/scrap/scrapFcLinkList.json", formData)
        .then(function(response) {
          var list = response.data.linkedFcInfoList;
          if ((list || "") != "") {
            for (var i = 0; i < list.length; i++) {
              list[i].icon =
                "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
              list[i].yn_link_origin = list[i].yn_link;
              if (i > 0 && list[i].nm_code == list[i - 1].nm_code) {
                list[i].nm_code = "";
              }
            }
            _this.linkedFcInfoList = list;
            _this.isData = true;
          }
          _this.seen = true;
        })
        .catch(e => {
          _this.seen = true;
          _this.$toast.center(ko.messages.error);
        });
    },
    updateLinkedFcInfo: function() {
      var _this = this;
      var cnt = 0;
      //form태그 초기화
      $("#frmFcLinkList").html("");
      for (var i = 0; i < this.linkedFcInfoList.length; i++) {
        if (
          _this.linkedFcInfoList[i].yn_link_origin !=
          _this.linkedFcInfoList[i].yn_link
        ) {
          //실제 action을 보낼 form 태그 안에 input 태그를 생성
          $("#frmFcLinkList").append(
            _this.getInputStr(
              "no_person",
              cnt,
              _this.linkedFcInfoList[i].no_person
            )
          );
          $("#frmFcLinkList").append(
            _this.getInputStr(
              "cd_agency",
              cnt,
              _this.linkedFcInfoList[i].cd_agency
            )
          );
          $("#frmFcLinkList").append(
            _this.getInputStr("cd_fc", cnt, _this.linkedFcInfoList[i].cd_fc)
          );
          $("#frmFcLinkList").append(
            _this.getInputStr("cn", cnt, _this.linkedFcInfoList[i].cn)
          );
          $("#frmFcLinkList").append(
            _this.getInputStr(
              "type_login",
              cnt,
              _this.linkedFcInfoList[i].type_login
            )
          );
          $("#frmFcLinkList").append(
            _this.getInputStr("yn_link", cnt, this.linkedFcInfoList[i].yn_link)
          );
          cnt++;
        }
      }
      if (cnt == 0) {
        if (this.isSignup) {
          //this.$router.push("/scrap/ctrlFcLink");
          this.$router.push({
            name: "scrapCtrlFcLink",
            query: {
              isScrap: true // 자동스크래핑 필요 여부
            }
          });
        } else {
          // 공인인증서 추가 등록
          if (this.$store.state.certAddCnt > 0) {
            this.$store.state.certAddCnt--;
            if (Constant.userAgent == "iOS") {
              //공인인증서 여러개인지 확인 콜백 이벤트
              Jockey.on("resultIsMultipleCert", function(param) {
                _this.resultIsMultipleCert(param.isMultiple);
                Jockey.off("resultIsMultipleCert");
              });
              Jockey.send("isMultipleCert");
            } else if (Constant.userAgent == "Android") {
              window.Android.isMultipleCert();
            }
          } else {
            this.$store.state.isLoading = true;
            this.$router.push("/main");
          }
        }
        return;
      }
      var data = $("#frmFcLinkList").serialize();
      this.$http
        .post("/m/scrap/updateFcLinkInfoList.json", data)
        .then(function(response) {
          var result = response.data.code;
          if (_this.isSignup) {
            //_this.$router.push("/scrap/ctrlFcLink");
            _this.$router.push({
              name: "scrapCtrlFcLink",
              query: {
                isScrap: true // 자동스크래핑 필요 여부
              }
            });
          } else {
            // 공인인증서 추가 등록
            if (_this.$store.state.certAddCnt > 0) {
              _this.$store.state.certAddCnt--;
              if (Constant.userAgent == "iOS") {
                //공인인증서 여러개인지 확인 콜백 이벤트
                Jockey.on("resultIsMultipleCert", function(param) {
                  _this.resultIsMultipleCert(param.isMultiple);
                  Jockey.off("resultIsMultipleCert");
                });
                Jockey.send("isMultipleCert");
              } else if (Constant.userAgent == "Android") {
                window.Android.isMultipleCert();
              }
            } else {
              _this.$store.state.isLoading = true;
              _this.$router.push("/main");
            }
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    clickCancel: function() {
      for (var i = 0; i < this.linkedFcInfoList.length; i++) {
        this.linkedFcInfoList[i].yn_link = "N";
      }
      this.updateLinkedFcInfo();
    },
    //각 no_manage_info와 display_yn에 맞는 input 태그를 만들 함수
    getInputStr: function(name, idx, value) {
      return (
        "<input type='hidden' name='list[" +
        idx +
        "]." +
        name +
        "' value='" +
        value +
        "'/>"
      );
    },
    // 공인인증서 유무 체크
    checkExistCert: function() {
      var _this = this;
      if (Constant.userAgent == "iOS") {
        //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert", function(param) {
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
    /***
     * Native Call function
     ***/
    //공인인증서 여러개인지 확인 (모바일에서 호출)
    resultIsMultipleCert: function(isMultiple) {
      var _this = this;
      if (isMultiple == "true") {
        this.$dialogs
          .confirm("추가 공인인증서를\n등록하시겠습니까?", Constant.options)
          .then(res => {
            if (res.ok) {
              _this.checkExistCert();
            } else {
              _this.$store.state.isLoading = true;
              _this.$router.push("/main");
            }
          });
      } else {
        this.$store.state.isLoading = true;
        this.$router.push("/main");
      }
    },
    //공인인증서 유무 결과 (모바일에서 호출)
    resultCheckCert: function(isCert) {
      //console.log("isCert : " + isCert);
      var _this = this;
      if (isCert == "true") {
        // 공인인증서가 있을 경우
        if (Constant.userAgent == "iOS") {
          Jockey.on("resultCheckPasswordCert", function(param) {
            _this.resultCheckPasswordCert(param.dn, param.cn);
            Jockey.off("resultCheckPasswordCert");
          });
          Jockey.send("checkPasswordCert", {
            noPerson: this.$store.state.user.noPerson,
            nmPerson: this.$store.state.user.nmPerson
          });
        } else if (Constant.userAgent == "Android") {
          window.Android.checkPasswordCert(
            this.$store.state.user.noPerson,
            this.$store.state.user.nmPerson
          );
        }
      } else {
        // 공인인증서가 없을 경우
        this.$toast.center("공인인증서가 없습니다.");
        setTimeout(function() {
          this.$store.state.isLoading = true;
          _this.$router.push("/main");
        }, 1000);
      }
    },
    resultCheckPasswordCert: function(dn, cn) {
      // 공인인증서 비밀번호 체크 후 연동 금융사 선택 화면으로 이동
      this.$router.push({
        name: "scrapSelFcLink",
        params: { isSignup: false, isSingle: false, dn: dn, cn: cn }
      });
    },
    // Native에서 건너뛰기 눌렀을 경우 호출
    resultCheckAvaliableScrapList: function() {
      this.$store.state.isLoading = true;
      this.$router.push("/main");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>

