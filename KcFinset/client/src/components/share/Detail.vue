<template>
  <section v-if="seen">
    <div class="credit-info">
      <p class="title">정보</p>
      <div class="wrap">
        <ul>
          <li>
            <p>요청일</p>
            <p>{{formatDateDot(shareInfo.dt_frt)}}</p>
          </li>
          <li>
            <p>허용일</p>
            <p>{{formatDateDot(shareInfo.dt_stt_offer)}}</p>
          </li>
        </ul>
      </div>
    </div>
    <div class="credit-info">
      <p class="title">신용 등급 및 연체 정보</p>
      <div class="wrap">
        <ul>
          <li>
            <p>등급</p>
            <p>{{c_grade_credit!="" ? c_grade_credit : "-"}}등급</p>
          </li>
          <li>
            <p>점수</p>
            <p>{{c_rating_credit!="" ? c_rating_credit : "-"}}</p>
          </li>
          <li>
            <p>연체현황</p>
            <p>{{overdueCnt}}건</p>
          </li>
        </ul>
        <template v-for="overdueInfo in overdueList">
          <p :key="overdueInfo.index">{{overdueInfo.change_contents}}</p>
          <p :key="overdueInfo.index" class="right">정보등록일 : {{formatDateDot(overdueInfo.dt_info)}}, 수집처 : {{overdueInfo.collector}}</p>
        </template>
      </div>
    </div>

    <div class="credit-info pb90">
      <p class="title">대출정보</p>
      <div class="wrap">
        <template v-for="debtInfo in debtList">
          <p :key="debtInfo.index" class="key">{{debtInfo.nm_fc}}</p>
          <ul :key="debtInfo.index" class="left">
            <li>
              <p>계약정보</p>
              <p>대출형태 : {{debtInfo.debt_type}}<br>
                개설일자 : {{formatDateDot(debtInfo.ymd_loan)}}<br>
                만기일자 : {{formatDateDot(debtInfo.ymd_loanend)}}<br>
                <template v-if="debtInfo.cd_type_deal != '3' && debtInfo.cd_type_deal != '6'">대출원금 : </template>
                <template v-else>대출한도 : </template>
                {{debtInfo.amt_contract}} 만원
              </p>
            </li>
            <li>
              <p>이용현황</p>
              <p>잔액 : {{debtInfo.amt_remain}} 만원<br>
                금리 : {{debtInfo.ever_interest}} %<br>
                월상환액 :
                <template v-if="debtInfo.cd_type_deal != '3' && debtInfo.cd_type_deal != '6'">{{debtInfo.amt_repay}} 만원</template>
                <template v-else>-</template>
              </p>
            </li>
          </ul>
        </template>
      </div>
    </div>

    <div class="btn-wrap" style="margin:0px 0px 100px 0px;">
      <a @click="reqUpdate();" class="stroke blue">업데이트 요청하기</a>
    </div>

    <div class="btn-wrap float">
      <a @click="settingExit();" class="solid blue box">공유 종료</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "ShareDetail",
  data() {
    return {
      errMsg: "",
      seen: false,
      cd_share: "01", //공유구분 - 신용정보(01)
      seq_share: "", //seq
      share_status: "", //공유상태
      reqUpdateYn: "N", //업데이트요청여부
      shareInfo: "", //공유정보
      creditInfo: "", //신용정보
      c_grade_credit: "", //신용정보_등급
      c_rating_credit: "", //신용정보_점수
      overdueCnt: "", //연체건수
      overdueList: [], //연체list
      debtList: [] //부채list
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "님 신용정보";
    this.$store.state.header.backPath =
      "/share/main?cd_share=" + this.cd_share + "&type_list=req";

    this.seq_share = this.$route.query.seq_share;
    this.getDetailInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //상세정보
    getDetailInfo: function() {
      var _this = this;
      this.$http
        .get("/m/customercenter/getShareInfoDetail.json", {
          params: { seq_share: _this.seq_share }
        })
        .then(response => {
          this.$store.state.title =
            response.data.shareInfo.offer_nm_person + this.$store.state.title; //title 정보제공자명 setting
          _this.shareInfo = response.data.shareInfo;
          _this.creditInfo = response.data.creditInfo;
          _this.c_grade_credit = response.data.creditInfo.grade_credit;
          _this.c_rating_credit = response.data.creditInfo.rating_credit;
          _this.overdueCnt = response.data.overdueCnt;
          _this.overdueList = response.data.overdueList;
          _this.debtList = response.data.debtList;

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //업데이트 요청하기
    reqUpdate: function() {
      var _this = this;
      if ("Y" == _this.reqUpdateYn) {
        _this.$toast.center("이미 업데이트 요청 하셨습니다.");
        return false;
      }

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("업데이트 요청하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
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
                  _this.$toast.center(
                    "하루에 한번 <br/>업데이트 요청 가능합니다."
                  );
                  _this.reqUpdateYn = "Y";
                } else {
                  _this.$toast.center("업데이트 요청 실패했습니다.");
                  return false;
                }
              })
              .catch(e => {
                _this.$toast.center(ko.messages.error);
              });
          }
        });
    },
    //공유종료
    settingExit: function() {
      var _this = this;

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("공유를 취소하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            console.log("seq_share" + _this.seq_share);

            var formData = new FormData();
            formData.append("seq_share", _this.seq_share);
            formData.append("share_status", "04");

            this.$http
              .post(
                "/m/customercenter/updatePersonShareInfoSetStatus.json",
                formData
              )
              .then(function(response) {
                _this.$toast.center(response.data.message);
                if ("00" == response.data.cdResult) {
                  _this.share_status = "04_1";
                  _this.sendPush(); //문자발송
                }
              })
              .catch(e => {
                _this.$toast.center(ko.messages.error);
              });
          }
        });
    },
    //push발송
    sendPush: function() {
      var _this = this;

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
