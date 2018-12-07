<template>
  <section>
    <div class="container">
      <div class="info-share">
        <p class="title">공유를 요청할 사용자를 선택하세요</p>
        <p class="text">하루동안 응답이 없으면 자동으로 취소 됩니다</p>
        <div class="input-wrap">
          <input type="text" v-model="offer_nm_person" readonly="readonly">
          <a @click="srcPerson();"></a>
        </div>
      </div>
    </div>

    <div class="box-list noMG list02">
      <div v-if="this.cd_share=='01'" class="item pb10">
        <div class="checks">
          <p class="title">정보 제공 항목</p>
          <div class="small">
            <p><input type="checkbox" id="credit" v-model="yn_credit_info" true-value="Y" false-value=""><label for="credit">신용등급(연체정보 포함)</label></p>
            <p><input type="checkbox" id="cDebt" v-model="yn_debt_info" true-value="Y" false-value=""><label for="cDebt">대출개설 및 잔고 현황</label></p>
          </div>
        </div>
      </div>
      <div v-else-if="this.cd_share=='02'" class="item pb10">
        <div class="checks">
          <p class="title">정보 통합 관리 항목</p>
          <div class="small">
            <p><input type="checkbox" id="asset" v-model="yn_asset_info" true-value="Y" false-value=""><label for="asset">자산</label></p>
            <p><input type="checkbox" id="consume" v-model="yn_consume_info" true-value="Y" false-value=""><label for="consume">소비</label></p>
            <p><input type="checkbox" id="fDebt" v-model="yn_debt_info" true-value="Y" false-value=""><label for="fDebt">부채</label></p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
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
      yn_credit_info: "", //신용정보여부
      yn_debt_info: "", //부채정보여부
      yn_asset_info: "", //자산정보여부
      yn_consume_info: "" //소비정보여부
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
    this.resultAddress("박준수", "01026882453");
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

      if ("01" == _this.cd_share) {
        if (!_this.yn_credit_info && !_this.yn_debt_info) {
          _this.$toast.center("항목을 선택해 주세요.");
          return false;
        }
      } else {
        if (
          !_this.yn_asset_info &&
          !_this.yn_consume_info &&
          !_this.yn_debt_info
        ) {
          _this.$toast.center("항목을 선택해 주세요.");
          return false;
        }
      }
      return true;
    },
    //공유요청
    createShareInfo: function() {
      var _this = this;

      if (!_this.validShareInfoNewReq()) return false;

      Constant.options.title =
        _this.offer_nm_person + "님에게 공유 요청을 하시겠습니까?";
      this.$dialogs.confirm("", Constant.options).then(res => {
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
