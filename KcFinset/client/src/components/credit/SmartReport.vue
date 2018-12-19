<template>
  <section>
    <div id="progress" class="progress-wrap scraping show">
      <div class="loader">
        <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
          <path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
            <animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 25 25" to="360 25 25" dur="0.6s" repeatCount="indefinite" />
          </path>
        </svg>
      </div>
    </div>
    <!-- Content -->
    <div id="content" class="ifm-content" style="overflow-x:hidden;overflow-y:scroll;width:100%;height:1400px;">
      <iframe id="ifrContents" name="ifrContents" scrolling="no" frameborder="0" style="width:100%;height:100%;"></iframe>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "creditSmartReport",
  data() {
    return {
      // kcbURI : ""
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용 스마트리포트";

    if (Constant.userAgent == "Android") {
      window.Android.setBackKeyUse("Y");
    }

    this.getSmartReportInfo();
    this.fnShow();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    fnShow: function() {
      setTimeout(function() {
        $("#progress").remove();
      }, 3000);
    },
    // smartReport 조회
    getSmartReportInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditSmartReportInfo.json", {
          params: {}
        })
        .then(response => {
          var kcbURI = response.data.kcbURI;
          if (kcbURI != null && kcbURI != "") {
            ifrContents.location.href = kcbURI;
          }
          console.log(kcbURI);
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
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
// svg path,
// svg rect {
//   fill: #2b43ba;
// }
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
