<template>
  <section v-if="seen">
    <form name="frmFcLinkList" id="frmFcLinkList"></form>
    <div class="box-list noMG list02 pb90">
      <div v-for="linkedFcInfo in linkedFcInfoList" :key="linkedFcInfo.index">
        <p class="header" v-if="checkType(linkedFcInfo.nm_code)">{{linkedFcInfo.nm_code}}</p>
        <div class="item">
          <div class="flex">
            <p class="symbol"><img :src="linkedFcInfo.icon" alt="" />{{linkedFcInfo.nm_fc}}</p>
            <p><button class="btn-onoff" :class="{'on':isLinked(linkedFcInfo.yn_link)}" :id=linkedFcInfo.cd_fc @click="changeLinked(linkedFcInfo.cd_fc)"></button></p>
          </div>
        </div>
      </div>
    </div>
    <div class="btn-wrap col2">
      <a @click="clickCancel()">취소</a>
      <a class="btn-solid" v-on:click="updateLinkedFcInfo()">연결</a>
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
  component: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "연동가능 금융사";
  },
  created() {
    this.getLinkedFcInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    isLinked: function(yn_linked) {
      return yn_linked == "Y";
    },
    changeLinked: function(cd_fc) {
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
    checkType: function(type) {
      if (this.type != type) {
        this.type = type;
        return true;
      }
      return false;
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
            this.$toast.center(ko.messages.loginErr);
            return;
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
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
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            list[i].yn_link_origin = list[i].yn_link;
          }
          _this.linkedFcInfoList = list;
          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    updateLinkedFcInfo: function() {
      var _this = this;
      var cnt = 0;
      //form태그 초기화
      $("#frmFcLinkList").html("");
      for (var i = 0; i < this.linkedFcInfoList.length; i++) {
        if (
          this.linkedFcInfoList[i].yn_link_origin !=
          this.linkedFcInfoList[i].yn_link
        ) {
          //실제 action을 보낼 form 태그 안에 input 태그를 생성
          $("#frmFcLinkList").append(
            this.getInputStr(
              "no_person",
              cnt,
              this.linkedFcInfoList[i].no_person
            )
          );
          $("#frmFcLinkList").append(
            this.getInputStr(
              "cd_agency",
              cnt,
              this.linkedFcInfoList[i].cd_agency
            )
          );
          $("#frmFcLinkList").append(
            this.getInputStr("cd_fc", cnt, this.linkedFcInfoList[i].cd_fc)
          );
          $("#frmFcLinkList").append(
            this.getInputStr("cn", cnt, this.linkedFcInfoList[i].cn)
          );
          $("#frmFcLinkList").append(
            this.getInputStr(
              "type_login",
              cnt,
              this.linkedFcInfoList[i].type_login
            )
          );
          $("#frmFcLinkList").append(
            this.getInputStr("yn_link", cnt, this.linkedFcInfoList[i].yn_link)
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
          this.$toast.center(ko.messages.error);
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

