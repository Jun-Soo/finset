<template>
 <div class="progress-wrap scraping show">
	<div class="loader">
		<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
			<path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
				<animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 25 25" to="360 25 25" dur="0.6s" repeatCount="indefinite"/>
			</path>
		</svg>
	</div>
	<div class="container progress-txt">
		<!-- <p class="lead">연동 가능한 금융사를<br>확인 중 입니다. <small>잠시만 기다려주세요.</small></p> -->
		<p class="lead"> <br/><span v-html=normalMessage></span> <br/>
      <small v-html=smallMessage></small>
    </p>
	</div>
</div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "loading",
  data() {
    return {
      dn: this.$route.params.dn,
      normalMessage: this.$route.params.normalMessage,
      smallMessage: this.$route.params.smallMessage,
      isScrapFcList: false,
      isScrapStList: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
  },
  beforeMount() {},
  mounted() {
    this.checkFcCertList();
    this.checkStCertList();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    nextStep: function() {
      this.$router.push({
        name: "scrapRegFcLink",
        params: {
          cn: this.$route.params.cn
        }
      });
    },
    //자동스크래핑 가능 금융사 조회
    checkFcCertList: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_person", this.noPerson);
      this.$http
        .post("/m/scrap/getScrapFcList.json", formData)
        .then(function(response) {
          _this.$store.state.bankCode = response.data.bank_code;
          _this.$store.state.cardCode = response.data.card_code;

          if (Constant.userAgent == "iOS") {
            Jockey.on("checkAvaliableScrapList", function(param) {
              frmFcListNextFromMobile();
            });
            Jockey.send("checkAvaliableScrapList", {
              noPerson: _this.$store.state.user.noPerson,
              bankCode: _this.$store.state.bankCode,
              cardCode: _this.$store.state.cardCode
            });
            //do nothing
          } else if (Constant.userAgent == "Android") {
            window.Android.checkAvaliableScrapList(
              _this.$store.state.user.noPerson,
              _this.$store.state.bankCode,
              _this.$store.state.cardCode
            );
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //자동스크래핑 가능 증권사 조회
    checkStCertList: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("dn", _this.dn);
      this.$http
        .post("/m/scrap/getScrapStList.json", formData)
        .then(function(response) {
          var result = response.data.result;
          _this.isScrapStList = true;
          if (_this.isScrapFcList && _this.isScrapStList) {
            _this.nextStep();
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    /***
     * Native Call function
     ***/
    //자동 스크래핑 등록 완료 시 (모바일에서 호출)
    resultCheckAvaliableScrapList: function() {
      this.isScrapFcList = true;
      if (this.isScrapFcList && this.isScrapStList) {
        this.nextStep();
      }
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
