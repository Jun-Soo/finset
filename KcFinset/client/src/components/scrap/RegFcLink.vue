<template>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>연계 금융사 관리</h1>
		</div>
		<form name="frmFcLinkList" id="frmFcLinkList"></form>
	</header>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid scrap-group">
			<div class="list-group" v-for="linkedFcInfo in linkedFcInfoList" :key="linkedFcInfo.index">
				<h2 class="h2 block-container" v-if="checkType(linkedFcInfo.nm_code)">{{linkedFcInfo.nm_code}}</h2>
					
					<div class="list-group-item">
						<div class="list-block">
							<li class="bank-title">
								<p class="symbol"><img :src="linkedFcInfo.icon" alt=""/>{{linkedFcInfo.nm_fc}}</p>
               </li>
            </div>
						<div class="ui-switch" data-ischanged="false">
							<label data-form-control="toggle" class="pull-right">
								<input class="updateLink" type="checkbox" :id=linkedFcInfo.cd_fc  :checked="isLinked(linkedFcInfo.yn_link)"
								  v-on:change="changeLinked(linkedFcInfo.cd_fc)"/>
								<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
							</label>
						</div>
					</div>
			</div>
			<div class="btn-fixed-bottom affix-bottom" id="confirm" v-on:click="updateLinkedFcInfo()">
				<a role="button" class="btn btn-lg btn-block btn-disabled btn-primary">확인</a>
			</div>
		</div>
	</div>
</div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "regFcLink",
  data() {
    return {
      noPerson: this.$store.state.user.noPerson,
      password: localStorage.getItem("tempPwd"),
      cn: this.$route.params.cn,
      normalMessage: this.$route.params.normalMessage,
      smallMessage: this.$route.params.smallMessage,
      isScrapFcList: false,
      isScrapStList: false,
      linkedFcInfoList: [],
      type: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
  },
  beforeMount() {},
  mounted() {
    this.getLinkedFcInfo();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    isLinked: function(yn_linked) {
      return yn_linked == "Y";
    },
    changeLinked: function(cd_fc) {
      var chkZzim = $("#" + cd_fc).is(":checked");
      for (var i = 0; i < this.linkedFcInfoList.length; i++) {
        if (this.linkedFcInfoList[i].cd_fc === cd_fc) {
          if (chkZzim == true) {
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
        this.login();
        return;
      }
      var data = $("#frmFcLinkList").serialize();
      this.$http
        .post("/m/scrap/updateFcLinkInfoList.json", data)
        .then(function(response) {
          var result = response.data.code;
          this.login();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
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
.progress-wrap {
  text-align: center;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  /*z-index:999999;*/
  opacity: 0;
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  align-items: center;
  /*background-color:rgba(0,0,0,.5);*/
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}
.progress-wrap.scraping {
  background-color: #f2f3f7;
}
.progress-wrap.show {
  opacity: 1;
}
.loader {
  margin: 0 0 2em;
  height: 100px;
  width: 20%;
  text-align: center;
  padding: 1em;
  margin: 0 auto 1em;
  display: inline-block;
  vertical-align: top;
  position: absolute;
}
/*
  Set the color of the icon
*/
svg path,
svg rect {
  fill: #2b43ba;
}
.progress-txt {
  margin-top: 50px;
}
.progress-txt .lead {
  color: #777;
  font-size: 16px;
  font-weight: 600;
  text-align: center;
  line-height: 20px;
}
</style>
