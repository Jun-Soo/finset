<template>
  <section v-if="seen">
    <div class="con-top share-top">
      <p v-if="shareInfo.share_status=='01'">{{shareInfo.offer_nm_person}}님<em>에게 요청한 항목입니다.</em></p>
      <p v-else>{{shareInfo.offer_nm_person}}님<em>과 공유한 항목입니다.</em></p>
      <div class="request">
        <ul>
          <li>
            <em class="key">요청일</em>
            <em>{{formatDateDot(shareInfo.dt_frt)}}</em>
          </li>
          <li v-if="shareInfo.share_status=='02'">
            <em class="key">허용일</em>
            <em>{{formatDateDot(shareInfo.dt_stt_offer)}}</em>
          </li>
          <li>
            <em class="key">상태</em>
            <em class="state">{{shareInfo.share_status=='01'? '대기중' : '공유중'}}</em>
          </li>
        </ul>
      </div>
    </div>

    <div v-if="this.cd_share=='01'" class="box-list noMG list02">
      <p class="header">정보 제공 항목</p>
      <div v-if="shareInfo.yn_credit_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">신용등급(연체정보 포함)</p>
          <p></p>
        </div>
      </div>
      <div v-if="shareInfo.yn_debt_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">대출개설 및 잔고 현황</p>
          <p></p>
        </div>
      </div>
    </div>
    <div v-else-if="this.cd_share=='02'" class="box-list noMG list02">
      <p class="header">통합관리용 정보 항목</p>
      <div v-if="shareInfo.yn_asset_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">자산 정보</p>
          <p></p>
        </div>
      </div>
      <div v-if="shareInfo.yn_consume_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">소비 정보</p>
          <p></p>
        </div>
      </div>
      <div v-if="shareInfo.yn_debt_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">부채 정보</p>
          <p></p>
        </div>
      </div>
    </div>

    <div v-if="this.cd_share=='02' && shareInfo.share_status=='02'" class="btn-wrap">
      <a @click="reqUpdate();" class="stroke blue">업데이트 요청하기</a>
    </div>
    
    <div class="btn-wrap float">
      <!-- 공유상태에 따라 달라짐-->
      <a v-if="shareInfo.share_status=='01'" @click="settingReq('05');" class="solid blue box">요청 취소</a>
      <a v-else @click="settingReq('04');" class="solid blue box">공유 종료</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "ShareReqSetting",
  data() {
    return {
      errMsg: "",
      seen: false,
      cd_share: "01", //신용정보(01) / 금융정보(02) 구분
      seq_share: "", //seq
      share_status: "", //공유상태
      reqUpdateYn: "N", //업데이트요청여부
      shareInfo: "" //설정정보
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    var cd_share = this.$route.query.cd_share;
    this.$store.state.header.type = "sub";
    if ("01" == cd_share) {
      this.$store.state.title = "신용정보 제공항목";
    } else if ("02" == cd_share) {
      this.$store.state.title = "금융정보 공유항목";
    }
    this.cd_share = cd_share;

    this.seq_share = this.$route.query.seq_share;
    this.getSettingInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //설정정보
    getSettingInfo: function() {
      var _this = this;
      this.$http
        .get("/m/customercenter/getShareInfoSetting.json", {
          params: { seq_share: _this.seq_share }
        })
        .then(response => {
          _this.shareInfo = response.data.shareInfo;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });

      _this.seen = true;
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //요청취소, 공유종료
    settingReq: function(share_status) {
      var _this = this;

      console.log("seq_share" + _this.seq_share);

      var formData = new FormData();
      formData.append("seq_share", _this.seq_share);
      _this.share_status = share_status;
      formData.append("share_status", share_status);

      if ("05" == share_status) {
        console.log("offer_hp" + _this.shareInfo.offer_hp);
        formData.append("offer_hp", _this.shareInfo.offer_hp);
      }

      this.$http
        .post("/m/customercenter/updatePersonShareInfoSetStatus.json", formData)
        .then(function(response) {
          _this.$toast.center(response.data.message);
          if ("00" == response.data.cdResult) {
            var typeMsg;
            if ("05" == share_status) {
              //요청취소인경우 발송type flag값 존재
              typeMsg = response.data.typeMessage; //문자발송
            } else if ("04" == share_status) {
              //공유종료인경우 push
              typeMsg = "push";
              _this.share_status = "04_1";
            }
            _this.sendMsg(typeMsg); //문자발송
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    //문자 / push발송
    sendMsg: function(typeMessage) {
      var _this = this;

      if ("sms" == typeMessage) {
        //sms보내기
        var offer_hp = _this.shareInfo.offer_hp;
        var msg;

        msg = _this.shareInfo.req_nm_person + "님이 공유를 취소하셨습니다.";

        if (Constant.userAgent == "Android") {
          window.Android.sendSms(offer_hp, msg);
        } else if (Constant.userAgent == "iOS") {
          //TODO ios sms문자발송 추가
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

        _this.goShareInfoMain();
      } else {
        _this.$toast.center("문자발송에 실패했습니다.");
        return false;
      }
    },
    //업데이트 요청하기
    reqUpdate: function() {
      var _this = this;
      if ("Y" == _this.reqUpdateYn) {
        _this.$toast.center("이미 업데이트 요청 하셨습니다.");
        return false;
      }

      console.log("seq_share" + _this.seq_share);

      var formData = new FormData();
      formData.append("share_status", "06");
      formData.append("seq_share", _this.seq_share);
      this.$http
        .post("/m/customercenter/sendPersonShareInfoPush.json", formData)
        .then(function(response) {
          if ("00" == response.data.cdResult) {
            _this.$toast.center("업데이트 요청되었습니다.");
            _this.reqUpdateYn = "Y";
          } else if ("01" == response.data.cdResult) {
            _this.$toast.center("하루에 한번 <br/>업데이트 요청 가능합니다.");
            _this.reqUpdateYn = "Y";
          } else {
            _this.$toast.center("업데이트 요청 실패했습니다.");
            return false;
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    //공유관리 메인으로 이동
    goShareInfoMain: function() {
      var _this = this;
      this.$router.push({
        name: "shareMain",
        query: { cd_share: _this.cd_share, type_list: "req" }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
