<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a @click="clickTab('credit')" :class="{on: this.listType=='credit'}">신용카드</a>
        <a @click="clickTab('check')" :class="{on: this.listType=='check'}">체크카드</a>
      </div>
    </div>

    <!-- 신용카드 -->
    <template v-if="this.listType=='credit'">
      <div v-if="creditList.length == 0" class="container">
        <div class="nodata">등록 내역이 없습니다</div>
      </div>
      <div v-else class="container">
        <p class="total-cards">보유갯수<em>{{creditCnt}}</em></p>
        <div class="bar-top">
          <p class="key">{{useMonth}}월 이용금액</p>
          <div class="wrap">
            <div class="left">{{formatNumber(creditAmtTotal)}}<em>원</em></div>
            <div class="right">
              <p class="key">총한도</p>
              <p class="value">{{formatNumber(Math.round(creditLimitTotal/10000))}} <em>만원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="{width: creditTotLimitPc+'%'}"></p>
          </div>
          <div class="bar-text-wrap">
            <p class="bar-text">{{useMonth}}월 한도 이용율</p>
            <p class="bar-card"><em>{{creditTotLimitPc}}%</em></p>
          </div>
        </div>
      </div>

      <div v-if="creditList.length != 0" class="box-list list01">
        <div v-for="creditInfo in creditList" :key="creditInfo.index" class="item expand">
          <div class="top">
            <p class="symbol"><img :src="creditInfo.fcImg" alt="" />{{creditInfo.nm_fc}}</p>
          </div>
          <div class="number-wrap">
            <div class="left">
              <p class="key">이용금액</p>
              <p class="number">{{formatNumber(creditInfo.amt_total)}}<em>원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="{width: creditInfo.limitPc+'%'}"></p>
          </div>
          <div class="flex-wrap col3">
            <div class="left">
              <p class="key">개설일자</p>
              <p class="value">{{formatDateDot(creditInfo.ymd_open)}}</p>
            </div>
            <div class="center">
              <p class="key">총한도</p>
              <p class="value">{{formatNumber(Math.round(creditInfo.amt_limit/10000))}} <em>만원</em></p>
            </div>
            <div class="right">
              <p class="key">단기대출한도</p>
              <p class="value">{{formatNumber(Math.round(creditInfo.amt_ca_limit/10000))}} <em>만원</em></p>
            </div>
          </div>
          <div class="hide-con">
            <div class="list">
              <p class="left">일시불</p>
              <p class="right">{{formatNumber(Math.round(creditInfo.amt_lump_sum/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">할부</p>
              <p class="right">{{formatNumber(Math.round(creditInfo.amt_installment/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">단기대출(현금서비스)</p>
              <p class="right">{{formatNumber(Math.round(creditInfo.amt_short_card_loan/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">체크카드</p>
              <p class="right">{{formatNumber(Math.round(creditInfo.amt_check/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">연체금액</p>
              <p class="right">{{formatNumber(Math.round(creditInfo.amt_delay/10000))}} <em>만원</em></p>
            </div>
          </div>
          <button class="btn-expand"></button>
        </div>

        <!-- noti -->
        <div class="info-massage">카드 개설 정보는 현재 해지되지 않은 카드 정보입니다. 일부 금융 기관의 경우에는 고객별로 카드 정보를 합산하여 제공하므로 이러한 경우에는 복수개의 카드라도 한 개의 카드정보로 제공됩니다.</div>
        <div class="info-massage">총 한도는 단기카드대출(현금서비스) 한도를 포함하고 있습니다.</div>
        <div class="info-massage">“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</div>
        <div class="info-massage">총 한도는 단기카드대출(현금서비스)한도를 포함하고 있습니다.</div>
        <div class="info-massage">총 이용금액은 단기카드대출(현금서비스), 체크카드 이용금액을 포함하고 있습니다.</div>
        <div class="info-massage">“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</div>
        <div class="info-massage">카드이용금액은 해당월 1일부터 말일까지의 이용금액정보 입니다.</div>
        <div class="info-massage">금융기관에서 등록하지 않은 카드 정보 및 이용금액은 표시되지 않습니다.</div>
        <div class="info-massage">한도이용률은 해당월 카드이용금만을 반영한 값으로 현재시점의 한도이용률과 차이가 있을 수 있습니다.</div>
      </div>
    </template>

    <!-- 체크카드 -->
    <template v-else>
      <div v-if="checkList.length == 0" class="container">
        <div class="nodata">등록 내역이 없습니다</div>
      </div>
      <div v-else class="container">
        <p class="total-cards">보유갯수<em>{{checkCnt}}</em></p>
        <div class="bar-top">
          <p class="key">{{useMonth}}월 이용금액</p>
          <div class="wrap">
            <div class="left">{{formatNumber(checkAmtTotal)}}<em>원</em></div>
            <div class="right">
              <p class="key">총한도</p>
              <p class="value">{{formatNumber(Math.round(checkLimitTotal/10000))}} <em>만원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="{width: checkTotUsePc+'%'}"></p>
          </div>
          <div class="bar-text-wrap">
            <p class="bar-text">{{useMonth}}월 한도 이용율</p>
            <p class="bar-card"><em>{{checkTotUsePc}}%</em></p>
          </div>
        </div>
      </div>

      <div v-if="checkList.length != 0" class="box-list list01">
        <div v-for="checkInfo in checkList" :key="checkInfo.index" class="item expand">
          <div class="top">
            <p class="symbol"><img :src="checkInfo.fcImg" alt="" />{{checkInfo.nm_fc}}</p>
          </div>
          <div class="number-wrap">
            <div class="left">
              <p class="key">이용금액</p>
              <p class="number">{{formatNumber(checkInfo.amt_total)}}<em>원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="{width: checkInfo.usePc+'%'}"></p>
          </div>
          <div class="flex-wrap col3">
            <div class="left">
              <p class="key">개설일자</p>
              <p class="value">{{formatDateDot(checkInfo.ymd_open)}}</p>
            </div>
            <div class="center">
              <p class="key">총한도</p>
              <p class="value">{{formatNumber(Math.round(checkInfo.amt_limit/10000))}} <em>만원</em></p>
            </div>
            <div class="right">
              <p class="key">단기대출한도</p>
              <p class="value">{{formatNumber(Math.round(checkInfo.amt_ca_limit/10000))}} <em>만원</em></p>
            </div>
          </div>
          <div class="hide-con">
            <div class="list">
              <p class="left">일시불</p>
              <p class="right">{{formatNumber(Math.round(checkInfo.amt_lump_sum/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">할부</p>
              <p class="right">{{formatNumber(Math.round(checkInfo.amt_installment/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">단기대출(현금서비스)</p>
              <p class="right">{{formatNumber(Math.round(checkInfo.amt_short_card_loan/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">체크카드</p>
              <p class="right">{{formatNumber(Math.round(checkInfo.amt_check/10000))}} <em>만원</em></p>
            </div>
            <div class="list">
              <p class="left">연체금액</p>
              <p class="right">{{formatNumber(Math.round(checkInfo.amt_delay/10000))}} <em>만원</em></p>
            </div>
          </div>
          <button class="btn-expand"></button>
        </div>

        <!-- noti -->
        <div class="info-massage">카드 개설 정보는 현재 해지되지 않은 카드 정보입니다. 일부 금융 기관의 경우에는 고객별로 카드 정보를 합산하여 제공하므로 이러한 경우에는 복수개의 카드라도 한 개의 카드정보로 제공됩니다.</div>
        <div class="info-massage">총 한도는 단기카드대출(현금서비스) 한도를 포함하고 있습니다.</div>
        <div class="info-massage">“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</div>
        <div class="info-massage">총 한도는 단기카드대출(현금서비스)한도를 포함하고 있습니다.</div>
        <div class="info-massage">총 이용금액은 단기카드대출(현금서비스), 체크카드 이용금액을 포함하고 있습니다.</div>
        <div class="info-massage">“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</div>
        <div class="info-massage">카드이용금액은 해당월 1일부터 말일까지의 이용금액정보 입니다.</div>
        <div class="info-massage">카드이용금액은 매월 15일에 갱신됩니다.</div>
        <div class="info-massage">금융기관에서 등록하지 않은 카드 정보 및 이용금액은 표시되지 않습니다.</div>
        <div class="info-massage">체크카드이용비중은 전체 카드사용액(신용카드 포함)중 체크카드 사용액의 비중을 말합니다.</div>
      </div>
    </template>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditCardInfo",
  data() {
    return {
      errMsg: "",
      listType: "credit",
      useMonth: "",
      //신용카드
      creditCnt: "", //건수
      creditList: [], //list
      creditAmtTotal: "", //이용금액
      creditLimitTotal: "", //총한도
      creditTotLimitPc: "", //한도 이용율
      //체크카드
      checkCnt: "", //건수
      checkList: [], //list
      checkAmtTotal: "", //이용금액
      checkLimitTotal: "", //총한도
      checkTotUsePc: "" //이용율
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "카드현황";
    this.setUseMonth();
    this.getCardInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 탭클릭
    clickTab: function(listType) {
      var _this = this;
      _this.listType = listType;
    },
    //이용월 셋팅
    setUseMonth: function() {
      var _this = this;
      var date = new Date();

      if (date.getDate() < 15) {
        date.setMonth(date.getMonth() - 1);
      }

      if (date.getMonth() == 0) {
        _this.useMonth = "12";
      } else {
        _this.useMonth = date.getMonth();
      }
    },
    //카드정보조회
    getCardInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditCardInfo.json", {
          params: {}
        })
        .then(response => {
          //신용카드
          _this.creditCnt = response.data.creditCnt;

          var creditAmtTotal = response.data.creditAmtTotal;
          var creditLimitTotal = response.data.creditLimitTotal;
          _this.creditAmtTotal = creditAmtTotal;
          _this.creditLimitTotal = creditLimitTotal;

          //총한도 이용율
          if (creditLimitTotal > 0) {
            _this.creditTotLimitPc = (
              (creditAmtTotal / creditLimitTotal) *
              100
            ).toFixed(2);
          } else {
            _this.creditTotLimitPc = 0;
          }

          var cdList = response.data.creditList;
          for (var i = 0; i < cdList.length; i++) {
            //금융사ICON 셋팅
            cdList[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + cdList[i].cd_fc;

            //한도 이용율
            if (cdList[i].amt_limit > 0) {
              cdList[i].limitPc = (
                (cdList[i].amt_total / cdList[i].amt_limit) *
                100
              ).toFixed(2);
            } else {
              cdList[i].limitPc = 0;
            }
          }
          _this.creditList = cdList;

          //체크카드
          _this.checkCnt = response.data.checkCnt;

          var checkAmtTotal = response.data.checkAmtTotal;
          var checkLimitTotal = response.data.checkLimitTotal;
          _this.checkAmtTotal = checkAmtTotal;
          _this.checkLimitTotal = checkLimitTotal;

          //총 이용율
          if (checkAmtTotal + creditAmtTotal > 0) {
            _this.checkTotUsePc = (
              (checkAmtTotal / (checkAmtTotal + creditAmtTotal)) *
              100
            ).toFixed(2);
          } else {
            _this.checkTotUsePc = 0;
          }

          var ckList = response.data.checkList;
          for (var i = 0; i < ckList.length; i++) {
            //금융사ICON 셋팅
            ckList[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + ckList[i].cd_fc;

            //이용율
            ckList[i].usePc = (
              (ckList[i].amt_total / checkAmtTotal) *
              100
            ).toFixed(2);
          }

          _this.checkList = ckList;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
