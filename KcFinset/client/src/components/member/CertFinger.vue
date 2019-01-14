<template>
  <div>
    <section>
      <div class="cert-finger" aria-readonly="true">
        <!-- <div> -->
        <div class="wrap">
        </div>
        <div class="svg">
          <svg id="my-svg" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 280 280" style="enable-background:new 0 0 280 280;" xml:space="preserve" width="100%" height="120">
            <g>
              <g>
                <path class="st0" d="M20.8,79.1c-13.3,26-18.2,56.5-12,87.4" />
                <path class="st0" d="M198.8,19.9c-25.5-12.5-55.3-17.1-85.3-11c-34.1,6.9-62.6,26.2-81.6,52.3" />
                <path class="st0" d="M271.1,113.6c-7.1-35-27.2-64.1-54.3-83.1" />
              </g>
            </g>
            <g>
              <g>
                <path class="st0" d="M273.8,142.7c0.2-9.6-0.7-19.4-2.6-29.1" />
                <path class="st0" d="M8.9,166.5c0.4,1.9,0.8,3.8,1.3,5.7" />
              </g>
            </g>
            <path class="st0" d="M105.4,269.3c30-34.1,44.3-81.3,34.6-129.3" />
            <path class="st0" d="M117.1,218.4c-6.5,16.8-16.4,32.1-29,45" />
            <path class="st0" d="M124.2,272.9c29-37.2,42.3-86.3,32.3-136.2l0,0c-1.8-9.1-10.7-15-19.8-13.2c-9.1,1.8-15,10.7-13.2,19.8l0.1,0
                    c3.8,18.6,3.5,37-0.3,54.5" />
            <path class="st0" d="M108.5,154.2c5.2,38.7-9.2,76.1-36.2,101.3" />
            <path class="st0" d="M176.5,169.5c0-11.9-1.2-24-3.7-36l0,0c-3.7-18.1-21.3-29.9-39.5-26.2c-13.6,2.7-23.5,13.3-26.2,26" />
            <path class="st0" d="M144.2,273.8c16.7-24.5,27.6-52.9,31-83.1" />
            <path class="st0" d="M143.9,89.9c-4.5-0.4-9.2-0.1-13.9,0.9c-27.2,5.5-44.8,32-39.3,59.2l0.1,0c7.4,36.8-6.3,72.9-32.8,95.9" />
            <path class="st0" d="M165.6,271.4c24.1-41.1,33.8-90.9,23.6-141.3l0,0c-3-15-12.4-27.1-24.8-34" />
            <path class="st0" d="M70.4,200c-5.2,13.5-13.9,25.4-25.1,34.6" />
            <path class="st0" d="M207,205.1c4.2-25.3,3.9-51.7-1.5-78.3l0,0c-7.3-36.2-42.6-59.7-78.9-52.4S67,117,74.3,153.3l0.1,0
                    c1.7,8.6,2.1,17.2,1.2,25.5" />
            <path class="st0" d="M188,265c6.1-12.5,11-25.5,14.6-39" />
            <path class="st0" d="M207.6,90.8c6.9,9.4,11.9,20.5,14.4,32.7l0,0c9.1,45.1,4.6,89.8-10.7,129.8" />
            <path class="st0" d="M105,64c5.8-2.6,11.9-4.7,18.4-6c25.6-5.2,50.8,2,69.6,17.3" />
            <path class="st0" d="M34.2,222.1C53.1,206.9,63.1,182,58,156.6l-0.1,0C51.6,125,63.9,94,87.3,75" />
            <path class="st0" d="M39.6,144c0.2,5.2,0.8,10.5,1.9,15.9l0,0c3.8,18.6-3.3,36.9-16.8,48.3" />
            <path class="st0" d="M240.7,133.2c-0.6-4.3-1.4-8.7-2.3-13h0c-11-54.4-63.9-89.5-118.2-78.6C78.4,50.1,47.9,83.3,41,122.9" />
            <path class="st0" d="M234.9,234.3c6.8-25.6,9.7-52.6,7.9-80" />
            <path class="st0" d="M155.7,24c-12.6-1.7-25.7-1.4-38.8,1.3C53.4,38,12.4,99.8,25.2,163.2l0,0c2.2,11-1.2,21.8-8.3,29.5" />
            <path class="st0" d="M258.1,202.9c3.5-27.9,2.6-56.9-3.3-86h0c-8.7-43.1-40-75.9-79.1-88.4" />
          </svg>
          <p class="text">지문인증을 사용 하시겠습니까?</p>
        </div>
        <!-- </div> -->
      </div>

      <div class="btn-wrap col2">
        <a v-on:click="chkFinger('N')" class="btn-stroke">아니오</a>
        <a v-on:click="chkFinger('Y')" class="btn-solid">예</a>
      </div>

    </section>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";
import "@/assets/images/member/fingerprint.svg";
import "@/assets/images/member/fingerprint_red.svg";
import "@/assets/js/vivus.min.js";

export default {
  name: "certFinger",
  data() {
    return {
      errMsg: "",
      certMessage: "",
      noPerson: this.$store.state.user.noPerson,
      password: localStorage.getItem("tempPwd"),
      tempPwd: "",
      chkPwd: false,
      ynFingerprint: "",
      //class
      classPass1: "",
      classPass2: "",
      classPass3: "",
      classPass4: "",
      fingerSVG: {}
    };
  },
  component: {},
  beforeCreate() {},
  created() {
    this.$store.state.title = "지문인증 설정";

    window.resultFingerPrint = this.resultFingerPrint;
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
    window.resultCheckAvaliableScrapList = this.resultCheckAvaliableScrapList;
  },
  beforeMount() {},
  mounted() {
    this.fingerSVG = new Vivus("my-svg", {
      type: "delayed",
      duration: 50,
      start: "manual",
      animTimingFunction: Vivus.EASE
    });
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
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
        });
    },
    chkFinger: function(gubun) {
      let _this = this;
      if (gubun == "Y") {
        _this.ynFingerprint = "Y";
        // if (Constant.userAgent == "Android") {
        //   window.Android.initFingerPrint();
        // } else if (Constant.userAgent == "iOS") {
        //   //지문인식 결과 콜백 이벤트
        //   Jockey.on("resultFingerPrint", function(param) {
        //     _this.resultFingerPrint(param.result);
        //     Jockey.off("resultFingerPrint");
        //   });
        //   Jockey.send("initFingerPrint");
        // }

        var frm = new FormData();
        frm.append("no_person", _this.noPerson);
        frm.append("yn_fingerprint", _this.ynFingerprint);
        this.$http
          .post("/m/person/modifyFingerPrint.json", frm)
          .then(response => {
            var result = response.data;
            if (result.result == "00") {
              this.$toast.center("지문 로그인 설정이 완료되었습니다.");
              this.$store.state.user.ynFingerprint = _this.ynFingerprint;
              if (
                Constant.userAgent == "Android" ||
                Constant.userAgent == "iOS"
              ) {
                //_this.checkExistCert();
                setTimeout(function() {
                  _this.$router.push("/scrap/fcLink");
                }, 1000);
              } else {
                setTimeout(function() {
                  _this.login();
                }, 2000);
              }
            } else {
              this.$toast.center(result.message);
              return false;
            }
          });
      } else if (gubun == "N") {
        _this.ynFingerprint = "N";
        if (Constant.userAgent == "Android" || Constant.userAgent == "iOS") {
          _this.$router.push("/scrap/fcLink");
        } else {
          setTimeout(function() {
            _this.login();
          }, 2000);
        }
      }
    }
    /***
     * Native Call function
     **/
    // resultFingerPrint: function(result) {
    //   var _this = this;
    //   if (result == true || result == 1) {
    //     if (Constant.userAgent == "Android") {
    //       window.Android.closeFingerPrint();
    //     }
    //     _this.fingerSVG.reset().play();

    //     var data = {
    //       no_person: _this.noPerson,
    //       yn_fingerprint: _this.ynFingerprint
    //     };
    //     this.$http
    //       .get("/m/person/modifyFingerPrint.json", {
    //         params: data
    //       })
    //       .then(response => {
    //         var result = response.data;
    //         if (result.result == "00") {
    //           this.$toast.center("지문 로그인 설정이 완료되었습니다.");
    //           this.$store.state.user.ynFingerprint = _this.ynFingerprint;
    //           if (
    //             Constant.userAgent == "Android" ||
    //             Constant.userAgent == "iOS"
    //           ) {
    //             //_this.checkExistCert();
    //             setTimeout(function() {
    //               _this.$router.push("/scrap/fcLink");
    //             }, 1000);
    //           } else {
    //             setTimeout(function() {
    //               _this.login();
    //             }, 2000);
    //           }
    //         } else {
    //           this.$toast.center(result.message);
    //           return false;
    //         }
    //       });
    //   }
    // }
  }
};
</script>
<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.st0 {
  fill: none;
  stroke: #e52638;
  stroke-width: 8;
  stroke-linecap: round;
  stroke-miterlimit: 10;
}
</style>
