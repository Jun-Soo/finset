<template>
  <section v-if="seen">
    <div class="tab">
      <div class="wrap">
        <a @click="clickTab('inquiry')" :class="{on: this.listType=='inquiry'}">신용조회</a>
        <a @click="clickTab('loanCard')" :class="{on: this.listType=='loanCard'}">대출/카드</a>
        <a @click="clickTab('overdue')" :class="{on: this.listType=='overdue'}">연체정보</a>
      </div>
    </div>

    <!-- 신용조회 -->
    <template v-if="this.listType=='inquiry'">
      <div class="container">
        <div class="col-top">
          <div v-if="inquiryList.length == 0" class="nodata">등록 내역이 없습니다</div>
          <template v-else>
            <div>
              <p class="key">최근 1개월<em>(건)</em></p>
              <p class="value">{{inquiryMmCnt}}</p>
            </div>
            <div>
              <p class="key">최근 1년<em>(건)</em></p>
              <p class="value">{{inquiryYearCnt}}</p>
            </div>
          </template>
        </div>
      </div>

      <div v-if="inquiryList.length != 0" class="box-list list01">
        <div v-for="inquiryInfo in inquiryList" :key="inquiryInfo.index" class="item">
          <div class="top">
            <p class="symbol"><img :src="inquiryInfo.fcImg" alt="" />{{inquiryInfo.nm_fc}}</p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">조회일자</p>
              <p class="value">{{formatDateDot(inquiryInfo.dt_info)}}</p>
            </div>
            <div class="right">
              <p class="key">조회목적</p>
              <p class="value">{{inquiryInfo.change_contents}}</p>
            </div>
          </div>
        </div>

        <!-- noti -->
        <div class="info-massage">서민금융종합지원대책의 시행으로 주요 조회정보 중 2011년 10월 1일 이후에 조회된 내역은 금융사에 제공되지 않으며, 신용조회는 KCB신용등급에 영향을 주지 않습니다.<br>단, 금융사기방지 및 금융미거래자의 신용등급부여 목적으로 신용조회 정보가 활용 될 수 있습니다.
        </div>
        <div class="info-massage">조회 정보는 다음 기준에 해당하는 조회가 포함되지 않습니다.
          <ul class="list-style02">
            <li>중복정보 : 동일기관에서 30일 이내에 동일 조회목적으로 여러 번 조회한 경우 최초 조회만 포함됩니다.</li>
            <li>특정 조회 목적 : 서비스 테스트를 위한 조회, 단순 상담을 위한 조회, 민원, 민원상담, 프리워크아웃 관련, 증권계좌 개설, 법원명령, 본인조회등의 이유로 조회한 경우는 포함되지 않습니다.</li>
          </ul>
        </div>
      </div>
    </template>

    <!-- 대출/카드 -->
    <template v-if="this.listType=='loanCard'">
      <div class="container">
        <div class="col-top">
          <div v-if="loanCardList.length == 0" class="nodata">등록 내역이 없습니다</div>
          <template v-else>
            <div>
              <p class="key">최근 1개월<em>(건)</em></p>
              <p class="value">{{loanCardMmCnt}}</p>
            </div>
            <div>
              <p class="key">최근 1년<em>(건)</em></p>
              <p class="value">{{loanCardYearCnt}}</p>
            </div>
          </template>
        </div>
      </div>

      <div v-if="loanCardList.length != 0" class="box-list list01">
        <div v-for="loanCardInfo in loanCardList" :key="loanCardInfo.index" class="item">
          <div class="top">
            <p class="symbol"><img :src="loanCardInfo.fcImg" alt="" />{{loanCardInfo.nm_fc}}</p>
            <p class="text blue">{{loanCardInfo.change_contents}}</p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">정보등록일</p>
              <p class="value">{{formatDateDot(loanCardInfo.dt_info)}}</p>
            </div>
            <div class="right">
              <p class="key">수집처</p>
              <p class="value">{{loanCardInfo.collector}}</p>
            </div>
          </div>
        </div>

        <!-- noti -->
        <div class="info-massage">
          본 정보는 서비스 가입 이후에만 제공됩니다.
        </div>
        <div class="info-massage">
          정보변동일은 정보 발생일자 또는 한국신용정보원 등록일자입니다.
        </div>
        <div class="info-massage">
          APP Push를 통한 알람은 정보등록일 익영업일에 제공됩니다.
        </div>
      </div>
    </template>

    <!-- 연체 -->
    <template v-if="this.listType=='overdue'">
      <div class="container">
        <div class="col-top">
          <div v-if="overdueList.length == 0" class="nodata">등록 내역이 없습니다</div>
          <template v-else>
            <div>
              <p class="key">최근 1개월<em>(건)</em></p>
              <p class="value">{{overdueMmCnt}}</p>
            </div>
            <div>
              <p class="key">최근 1년<em>(건)</em></p>
              <p class="value">{{overdueYearCnt}}</p>
            </div>
          </template>
        </div>
      </div>

      <div v-if="overdueList.length != 0" class="box-list list01">
        <div v-for="overdueInfo in overdueList" :key="overdueInfo.index" class="item">
          <div class="top">
            <p class="symbol"><img :src="overdueInfo.fcImg" alt="" />{{overdueInfo.nm_fc}}</p>
            <p class="text blue">{{overdueInfo.change_contents}}</p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">정보등록일</p>
              <p class="value">{{formatDateDot(overdueInfo.dt_info)}}</p>
            </div>
            <div class="right">
              <p class="key">수집처</p>
              <p class="value">{{overdueInfo.collector}}</p>
            </div>
          </div>
        </div>

        <!-- noti -->
        <div class="info-massage">
          본 정보는 서비스 가입 이후에만 제공됩니다.
        </div>
        <div class="info-massage">
          정보변동일은 정보 발생일자 또는 한국신용정보원 등록일자입니다.
        </div>
        <div class="info-massage">
          APP Push를 통한 알람은 정보등록일 익영업일에 제공됩니다.
        </div>
      </div>
    </template>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditDetail",
  data() {
    return {
      errMsg: "",
      seen: false,
      listType: "inquiry",
      //신용조회
      inquiryMmCnt: "", //최근1개월건수
      inquiryYearCnt: "", //최근1년건수
      inquiryList: [], //list
      //대출/카드
      loanCardMmCnt: "", //최근1개월건수
      loanCardYearCnt: "", //최근1년건수
      loanCardList: [], //list
      //연체
      overdueMmCnt: "", //최근1개월건수
      overdueYearCnt: "", //최근1년건수
      overdueList: [] //list
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "변동이력";
    if (
      this.$route.query.listType != null &&
      this.$route.query.listType != ""
    ) {
      this.listType = this.$route.query.listType;
    }
    this.getDetailInfo();
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
    getDetailInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditInfoDetail.json", {
          params: {}
        })
        .then(response => {
          //신용조회
          _this.inquiryMmCnt = response.data.inquiryMmCnt;
          _this.inquiryYearCnt = response.data.inquiryYearCnt;

          //금융사ICON 셋팅
          var inqList = response.data.inquiryList;
          for (var i = 0; i < inqList.length; i++) {
            inqList[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + inqList[i].cd_fc;
          }
          _this.inquiryList = inqList;

          //대출/카드
          _this.loanCardMmCnt = response.data.loanCardMmCnt;
          _this.loanCardYearCnt = response.data.loanCardYearCnt;

          //금융사ICON 셋팅
          var lcList = response.data.loanCardList;
          for (var i = 0; i < lcList.length; i++) {
            lcList[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + lcList[i].cd_fc;
          }
          _this.loanCardList = lcList;

          //연체
          _this.overdueMmCnt = response.data.overdueMmCnt;
          _this.overdueYearCnt = response.data.overdueYearCnt;

          //금융사ICON 셋팅
          var ovdList = response.data.overdueList;
          for (var i = 0; i < ovdList.length; i++) {
            ovdList[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + ovdList[i].cd_fc;
          }
          _this.overdueList = ovdList;
          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
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
