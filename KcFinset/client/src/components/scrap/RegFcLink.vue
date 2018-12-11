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
      isData: false,
      noPerson: this.$store.state.user.noPerson,
      password: localStorage.getItem("tempPwd"),
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
      console.log("isLinked : " + yn_linked);
      return yn_linked == "Y";
    },
    changeLinked: function(cd_fc) {
      console.log("changeLinked : " + cd_fc);
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
    login: function() {
      var _this = this;

      var querystring = require("querystring");
      var data = querystring.stringify({
        j_username: _this.noPerson,
        j_password: _this.password
      });
      this.$store.state.isLoading = true;
      this.$http
        .post(_this.$store.state.loginPath, data, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded"
          }
        })
        .then(response => {
          if (response.data.result == "10") {
            //정상
            localStorage.removeItem("tempPwd");
            _this.$store.commit("LOGIN", response.data);
            _this.$router.push("/main");
          } else {
            _this.$toast.center(ko.messages.loginErr);
            return;
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
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
                list[i].nm_code == "";
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
        if (this.$store.state.isLoggedIn) {
          this.$router.push("/scrap/ctrlFcLink");
        } else {
          this.login();
        }
        return;
      }
      var data = $("#frmFcLinkList").serialize();
      this.$http
        .post("/m/scrap/updateFcLinkInfoList.json", data)
        .then(function(response) {
          var result = response.data.code;
          if (_this.$store.state.isLoggedIn) {
            _this.$router.push("/scrap/ctrlFcLink");
          } else {
            _this.login();
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>

