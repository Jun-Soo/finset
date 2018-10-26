<template>
  <section>
    <div class="tab">
    <div class="wrap">
              <a @click="clickTab('def')" :class="{on: this.listType=='def'}">연체</a>
              <a @click="clickTab('etc')" :class="{on: this.listType=='etc'}">기타</a>
          </div>
      </div>
      
      <template v-if="this.listType=='def'">
      <div class="container">
          <div v-if="overdueList.length == 0" class="nodata">등록 내역이 없습니다</div>
          <div v-else class="col-top">
              <div>
                  <p class="key">연체잔액<em>(원)</em></p>
                  <p class="value">{{formatNumber(overdueSumAmt)}}</p>
              </div>
              <div>
                  <p class="key">대지급잔액<em>(원)</em></p>
                  <p class="value">{{formatNumber(steadSumAmt)}}</p>
              </div>
          </div>
      </div>

      <div v-if="overdueList.length != 0" class="box-list list01">
          <div v-for="overdueInfo in overdueList" :key="overdueInfo.index" class="item">
              <div class="top">
                  <p class="symbol"><img :src="overdueInfo.fcImg" alt=""/>{{overdueInfo.nm_fc}}</p>
                  <p v-if="overdueInfo.cd_type == '01'" class="text red">연체</p>
                  <p v-else class="text blue">대지급</p>
              </div>
              <div class="number-wrap">
                  <div class="left">
                      <p class="key">연체잔액</p>
                      <p class="number">{{formatNumber(overdueInfo.amt_delay)}}<em>원</em></p>
                  </div>
                  <div class="right">
                      <p class="key">상환금액</p>
                      <p class="value">{{formatNumber(overdueInfo.amt_repay/10000)}}<em>만원</em></p>
                  </div>
              </div>
              <div class="text-wrap">
                  <div class="left">
                      <p class="key">최초연체금액</p>
                      <p class="value">{{formatNumber(overdueInfo.amt_frt_delay/10000)}} 만원</p>
                  </div>
                  <div class="right">
                      <p class="key">최초연체기산일자</p>
                      <p class="value">{{formatDateDot(overdueInfo.ymd_delay)}}</p>
                  </div>
              </div>
          </div>

          <!--noti-->
          <div class="info-massage">대지급 정보는 보증보험 증권을 담보로 한 금융거래(지급보증) 후 계약을 이행하지 않아 해당 보증보험사에서 대위 변제한 내역에 대한 정보입니다.</div>
		  <div class="info-massage">연체/대지급 정보의 제공 기준은 10만원 이상 5영업일 이상 연체 시에 제공됩니다.</div>
		  <div class="info-massage">한국장학재단 학자금연체정보는 상환 후 1년까지만 신용평가에 활용됩니다.</div>
		  <div class="info-massage">본 정보에서 제공하는 연체 금액은 금융기관에서 관리하는 금액으로, 실제로 상황해야 할 연체금액과는 차이가 있사오니 상환 시에는 필히 해당 금융기관으로 확인하시기 바랍니다.</div>
      </div>
      </template>

      <template v-else>
      <div class="container">
          <div v-if="etcOverdueList.length == 0" class="nodata">등록 내역이 없습니다</div>
          <div v-else class="col-top">
              <div>
                  <p class="key">연체잔액<em>(원)</em></p>
                  <p class="value">{{formatNumber(overdueEtcSumAmt)}}</p>
              </div>
          </div>
      </div>

      <div v-if="etcOverdueList != 0" class="box-list list01">
          <div v-for="etcOverdueInfo in etcOverdueList" :key="etcOverdueInfo.index" class="item">
              <div class="top">
                  <p class="symbol"><img :src="etcOverdueInfo.fcImg" alt=""/>{{etcOverdueInfo.nm_fc}}</p>
                  <p v-if="etcOverdueInfo.cd_type == '01'" class="text red">채무불이행</p>
                  <p v-else-if="etcOverdueInfo.cd_type == '02'" class="text red">공공정보</p>
                  <p v-else-if="etcOverInfo.cd_type == '03'" class="text red">금융질서문란</p>
              </div>
              <template v-if="etcOverdueInfo.cd_type == '01'"> <!-- 채무불이행 -->
              	<div class="text-wrap">
	                    <div class="left">
	                    <p class="key">연체잔액</p>
	                    <p class="number">6,920,000<em>원</em></p>
	                </div>
	                <div class="right">
	                    <p class="key">상환금액</p>
	                    <p class="value">50<em>만원</em></p>
	                </div>
	            </div>
	            <div class="text-wrap">
	                <div class="left">
	                    <p class="key">최초연체금액</p>
	                    <p class="value">4,000 만원</p>
	                </div>
	                <div class="right">
	                    <p class="key">최초연체기산일자</p>
	                    <p class="value">2018.04.15</p>
	                </div>
	            </div>
              </template>
              <template v-if="etcOverdueInfo.cd_type == '02'"> <!-- 공공정보 -->
              	<div class="text-wrap">
	                    <div class="left">
	                    <p class="key">연체잔액</p>
	                    <p class="number">6,920,000<em>원</em></p>
	                </div>
	                <div class="right">
	                    <p class="key">상환금액</p>
	                    <p class="value">50<em>만원</em></p>
	                </div>
	            </div>
	            <div class="text-wrap">
	                <div class="left">
	                    <p class="key">최초연체금액</p>
	                    <p class="value">4,000 만원</p>
	                </div>
	                <div class="right">
	                    <p class="key">최초연체기산일자</p>
	                    <p class="value">2018.04.15</p>
	                </div>
	            </div>
              </template>
              <template v-if="etcOverdueInfo.cd_type == '03'"> <!-- 공공정보 -->
              	<div class="text-wrap">
	                    <div class="left">
	                    <p class="key">연체잔액</p>
	                    <p class="number">6,920,000<em>원</em></p>
	                </div>
	                <div class="right">
	                    <p class="key">상환금액</p>
	                    <p class="value">50<em>만원</em></p>
	                </div>
	            </div>
	            <div class="text-wrap">
	                <div class="left">
	                    <p class="key">최초연체금액</p>
	                    <p class="value">4,000 만원</p>
	                </div>
	                <div class="right">
	                    <p class="key">최초연체기산일자</p>
	                    <p class="value">2018.04.15</p>
	                </div>
	            </div>
              </template>
              <template v-if="etcOverdueInfo.cd_type == '03'"> <!-- 금융질서문란 -->
              	<div class="text-wrap">
	                    <div class="left">
	                    <p class="key">연체잔액</p>
	                    <p class="number">6,920,000<em>원</em></p>
	                </div>
	                <div class="right">
	                    <p class="key">상환금액</p>
	                    <p class="value">50<em>만원</em></p>
	                </div>
	            </div>
	            <div class="text-wrap">
	                <div class="left">
	                    <p class="key">최초연체금액</p>
	                    <p class="value">4,000 만원</p>
	                </div>
	                <div class="right">
	                    <p class="key">최초연체기산일자</p>
	                    <p class="value">2018.04.15</p>
	                </div>
	            </div>
              </template>
          </div>
          
          <!--noti-->
          <div class="info-massage">채무불이행(신용정보사)는 신용회복지원을 받다가 연체로 인해 중도 탈락된 건도 포함됩니다.</div>
		  <div class="info-massage">채무불이행(신용정보사) 정보는 연체 상환 시 해제반영되어 화면상 조회되지 않더라도 신용평가에는 5년 이내에 활용됩니다.</div>
		  <div class="info-massage">공공정보는 국세, 지방세, 관세를 500만원 이상 체납한 경우와 법원의 판결에 의하여 채무불이행자로 결정된 경우, 신용회복지원 등의 이유로 등록되는 정보입니다.</div>
	 	  <div class="info-massage">세금체납정보는 상환 시 해제반영되어 화면상 조회되지 않더라도 신용평가에는 3년동안 활용됩니다.</div>
		  <div class="info-massage">금융질서문란정보는 금융사기 등과 관련하여 법원으로부터 판결을 받거나 사기 및 결탁 등 부정한 방법으로 대출을 받거나 거래 약정을 체결한 경우 등의 사유로 등록되는 정보입니다.</div>
	 	  <div class="info-massage">금융질서문란정보는 신용정보관리 규약에 따라 등록 사유가 해제된 이후에도 5년간 기록이 보존됩니다.</div>
      </div>
      </template>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditOverdueInfo",
  data() {
    return {
      errMsg: "",
      listType: "def",
      //연체현황
      overdueSumAmt: "", //연체잔액
      steadSumAmt: "", //대지급잔액
      overdueList: [], //연체list
      //연체현황(기타)
      overdueEtcSumAmt: "", //연체(기타)잔액
      etcOverdueList: [] //연체(기타) list
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "연체현황";
    this.getOverdueInfo();
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
    getOverdueInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditOverdueInfo.json", {
          params: {}
        })
        .then(response => {
          //연체현황
          _this.overdueSumAmt = response.data.overdueSumAmt;
          _this.steadSumAmt = response.data.steadSumAmt;

          //금융사ICON 셋팅
          var defList = response.data.overdueList;
          for (var i = 0; i < defList.length; i++) {
            defList[i].fcImg =
              "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
              defList[i].cd_fc +
              "')";
          }
          _this.overdueList = defList;

          //연체(기타) 현황
          _this.overdueEtcSumAmt = response.data.overdueEtcSumAmt;

          //금융사ICON 셋팅
          var etcList = response.data.etcOverdueList;
          for (var i = 0; i < etcList.length; i++) {
            etcList[i].fcImg =
              "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
              etcList[i].cd_fc +
              "')";
          }
          _this.etcOverdueList = etcList;
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
