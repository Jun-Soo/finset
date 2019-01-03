<template>
  <section>
    <div class="container">
      <div class="info-share">
        <p class="title mb25">공유를 요청할 사용자를 선택하세요</p>
        <!-- <p class="text">하루동안 응답이 없으면 자동으로 취소 됩니다</p> -->
        <div class="input-wrap">
          <input type="text" v-model="offer_nm_person" readonly="readonly">
          <a @click="srcPerson();"></a>
        </div>
      </div>
    </div>

    <div v-if="cd_share=='01'" class="box-list noMG list02">
      <p class="header">정보 제공 항목</p>
      <div class="item">
        <div class="flex">
          <p class="corp big">신용등급(연체정보 포함)</p>
          <p><button @click="changeItem('credit');" class="btn-onoff" :class="{on: yn_credit_info=='Y'}"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">대출개설 및 잔고 현황</p>
          <p><button @click="changeItem('debt');" class="btn-onoff" :class="{on: yn_debt_info=='Y'}"></button></p>
        </div>
      </div>
    </div>
    <div v-else-if="cd_share=='02'" class="box-list noMG list02">
      <p class="header">정보 통합 관리 항목</p>
      <div class="item">
        <div class="flex">
          <p class="corp big">자산</p>
          <p><button @click="changeItem('asset');" class="btn-onoff" :class="{on: yn_asset_info=='Y'}"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">소비</p>
          <p><button @click="changeItem('consume');" class="btn-onoff" :class="{on: yn_consume_info=='Y'}"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">부채</p>
          <p><button @click="changeItem('debt');" class="btn-onoff" :class="{on: yn_debt_info=='Y'}"></button></p>
        </div>
      </div>
    </div>

    <div v-if="isShowBtn" class="btn-wrap float">
      <a @click="createShareInfo();" class="solid blue box">공유 요청</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "shareNewRequest",
  data() {
    return {
      errMsg: "",
      cd_share: "01", //신용정보(01) / 금융정보(02) 구분
      share_status: "01", //공유상태(01 - 요청)
      offer_nm_person: "", //검색 사용자명
      offer_hp: "", //검색 사용자번호
      seq_share: "", //seq
      //checkbox
      yn_credit_info: "Y", //신용정보여부
      yn_debt_info: "Y", //부채정보여부
      yn_asset_info: "Y", //자산정보여부
      yn_consume_info: "Y", //소비정보여부
      isShowBtn: true //버튼보여주기
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    var cd_share = this.$route.query.cd_share;
    this.$store.state.header.type = "sub";
    if ("01" == cd_share) {
      this.$store.state.title = "신용정보 요청";
    } else if ("02" == cd_share) {
      this.$store.state.title = "금융정보 요청";
    }
    this.cd_share = cd_share;

    //android callback
    window.resultAddress = this.resultAddress;
    window.resultSendSms = this.resultSendSms;

    //test
    // this.resultAddress("박준수", "01026882453");
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //사용자검색
    srcPerson: function() {
      if (Constant.userAgent == "iOS") {
        Jockey.send("getAddressList");
        Jockey.on("resultAddress", function(param) {
          resultAddress(param.src_nm_person, param.src_hp);
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.getAddressList();
      }
    },
    //사용자 검색결과 셋팅
    resultAddress: function(src_nm_person, src_hp) {
      var _this = this;
      _this.offer_nm_person = src_nm_person;
      _this.offer_hp = src_hp;
    },
    //항목변경
    changeItem: function(nm_item) {
      var _this = this;

      if ("01" == _this.cd_share) {
        //신용정보
        if ("credit" == nm_item) {
          //신용
          if ("" == _this.yn_credit_info) {
            _this.yn_credit_info = "Y";
          } else {
            _this.yn_credit_info = "";
          }
        } else if ("debt" == nm_item) {
          if ("" == _this.yn_debt_info) {
            _this.yn_debt_info = "Y";
          } else {
            _this.yn_debt_info = "";
          }
        }
      } else {
        console.log(nm_item);
        if ("asset" == nm_item) {
          //자산
          if ("" == _this.yn_asset_info) {
            _this.yn_asset_info = "Y";
          } else {
            _this.yn_asset_info = "";
          }
        } else if ("consume" == nm_item) {
          //소비
          if ("" == _this.yn_consume_info) {
            _this.yn_consume_info = "Y";
          } else {
            _this.yn_consume_info = "";
          }
        } else if ("debt" == nm_item) {
          //부채
          if ("" == _this.yn_debt_info) {
            _this.yn_debt_info = "Y";
          } else {
            _this.yn_debt_info = "";
          }
        }
      }

      _this.isShowBtn = true;

      if ("01" == _this.cd_share) {
        if (!_this.yn_credit_info && !_this.yn_debt_info) {
          _this.isShowBtn = false;
        }
      } else {
        if (
          !_this.yn_asset_info &&
          !_this.yn_consume_info &&
          !_this.yn_debt_info
        ) {
          _this.isShowBtn = false;
        }
      }
    },
    //validate체크
    validShareInfoNewReq() {
      var _this = this;
      console.log("yn_credit_info" + _this.yn_credit_info);
      console.log("yn_debt_info" + _this.yn_debt_info);
      console.log("yn_asset_info" + _this.yn_asset_info);
      console.log("yn_consume_info" + _this.yn_consume_info);

      if ("" == _this.offer_hp) {
        _this.$toast.center("사용자를 검색해 주세요.");
        return false;
      }

      if (this.$store.state.user.hp == _this.offer_hp) {
        _this.$toast.center("본인에게 공유가 되지 않습니다.");
        return false;
      }
      return true;
    },
    //공유요청
    createShareInfo: function() {
      var _this = this;

      if (!_this.validShareInfoNewReq()) return false;

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm(
          _this.offer_nm_person + "님에게 공유 요청을 하시겠습니까?",
          Constant.options
        )
        .then(res => {
          if (res.ok) {
            console.log("cd_share" + _this.cd_share);
            console.log("share_status" + _this.share_status);
            console.log("offer_nm_person" + _this.offer_nm_person);
            console.log("offer_hp" + _this.offer_hp);

            var formData = new FormData();
            formData.append("cd_share", _this.cd_share);
            formData.append("share_status", _this.share_status);
            formData.append("offer_nm_person", _this.offer_nm_person);
            formData.append("offer_hp", _this.offer_hp);
            if (_this.cd_share == "01") {
              formData.append("yn_credit_info", _this.yn_credit_info);
              formData.append("yn_debt_info", _this.yn_debt_info);
            } else {
              formData.append("yn_asset_info", _this.yn_asset_info);
              formData.append("yn_consume_info", _this.yn_consume_info);
              formData.append("yn_debt_info", _this.yn_debt_info);
            }

            this.$http
              .post("/m/customercenter/createPersonShareInfo.json", formData)
              .then(function(response) {
                _this.$toast.center(response.data.message);
                if ("00" == response.data.cdResult) {
                  _this.seq_share = response.data.seq_share;
                  _this.sendMsg(
                    response.data.typeMessage,
                    response.data.req_nm_person
                  ); //문자발송
                }
              })
              .catch(e => {
                _this.$toast.center(ko.messages.error);
              });
          }
        });
    },
    //문자 / push발송
    sendMsg: function(typeMessage, req_nm_person) {
      var _this = this;

      if ("sms" == typeMessage) {
        //sms보내기
        var offer_hp = _this.offer_hp;
        var msg;

        msg = req_nm_person + "님으로부터 공유요청이 왔습니다. \n";
        msg += "아래의 링크를 선택하여 FINSET을 \n";
        msg += "설치하시기 바랍니다. \n";
        msg +=
          "https://play.google.com/store/apps/details?id=com.app.kc.koscom";

        if (Constant.userAgent == "iOS") {
          Jockey.send("sendSms", {
            offer_hp: offer_hp,
            msg: msg
          });
        } else if (Constant.userAgent == "Android") {
          window.Android.sendSms(offer_hp, msg);
        }
      } else if ("push" == typeMessage) {
        console.log("share_status" + _this.share_status);
        console.log("seq_share" + _this.seq_share);

        var formData = new FormData();
        formData.append("share_status", _this.share_status);
        formData.append("seq_share", _this.seq_share);
        this.$http
          .post("/m/customercenter/sendPersonShareInfoPush.json", formData)
          .then(function(response) {
            if ("00" == response.data.cdResult) {
              _this.$toast.center("푸시발송에 성공했습니다.");
              _this.goShareInfoMain();
            } else {
              _this.$toast.center("푸시발송에 실패했습니다.");
              return false;
            }
          })
          .catch(e => {
            _this.$toast.center(ko.messages.error);
          });
      }
    },
    //문자전송결과
    resultSendSms: function(resultVal) {
      var _this = this;

      if ("00000000" == resultVal) {
        _this.$toast.center("문자발송에 성공했습니다.");

        console.log("seq_share" + _this.seq_share);

        var formData = new FormData();
        formData.append("seq_share", _this.seq_share);
        this.$http
          .post("/m/customercenter/createPersonShareInfoSms.json", formData)
          .then(function(response) {
            if ("00" == response.data.cdResult) {
              console.log("문자발송 이력저장에 성공했습니다.");
            } else {
              console.log("문자발송 이력저장에 실패했습니다.");
            }
          })
          .catch(e => {
            _this.$toast.center(ko.messages.error);
          });

        this.goShareInfoMain();
      } else {
        _this.$toast.center("문자발송에 실패했습니다.");
        return false;
      }
    },
    //공유관리 메인으로 이동
    goShareInfoMain: function() {
      var _this = this;
      this.$router.push({
        name: "shareMain",
        query: { cd_share: _this.cd_share }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
