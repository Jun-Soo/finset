<template>
  <section v-if="seen">

    <div v-if="cd_share=='01'" class="con-top share-top">
      <p>신용정보 공유로<br>신뢰를 나누세요.</p>
      <p class="text">신용정보는 당일에 한해서만 정보를 볼 수 있습니다.</p>
    </div>
    <div v-else-if="cd_share=='02'" class="con-top share-top">
      <p><em>몇번의 클릭으로</em><br>가족간의 흩어진 정보를<br><em>관리 하세요.</em></p>
    </div>

    <div class="tab">
      <div class="wrap">
        <a @click="clickTab('req')" :class="{on: type_list=='req'}">나 → {{cd_share=='01'? '타인' : '가족'}}</a>
        <a @click="clickTab('offer')" :class="{on: type_list=='offer'}">{{cd_share=='01'? '타인' : '가족'}} → 나</a>
      </div>
    </div>

    <template v-if="type_list=='req'">
      <div v-if="reqSbList.length == 0 && reqPmList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="box-list noMG list02">
        <p class="header col">허용 대기</p>
        <div v-if="reqSbList.length == 0" class="mb20">등록된 내역이 없습니다.</div>
        <div v-for="reqSbInfo in reqSbList" :key="reqSbInfo.index" class="item">
          <a @click="goSettingDetail('req', '01', reqSbInfo.seq_share, '')" class="share">
            <span class="name">{{reqSbInfo.offer_nm_person}}</span>
            <span class="state">대기</span>
            <span>{{reqSbInfo.offer_hp}}</span>
          </a>
        </div>
        <p class="header col">공유된 {{cd_share=='01'? '타인' : '가족'}}</p>
        <div v-if="reqPmList.length == 0">등록된 내역이 없습니다.</div>
        <div v-for="reqPmInfo in reqPmList" :key="reqPmInfo.index" class="item">
          <a @click="goSettingDetail('req', '02', reqPmInfo.seq_share, reqPmInfo.dt_stt_offer)" class="share">
            <p>
              <span class="name">{{reqPmInfo.offer_nm_person}}</span>
              <span>{{reqPmInfo.offer_hp}}</span>
            </p>
            <p class="mt10">
              <span><strong>{{formatDateDot(reqPmInfo.dt_stt_offer)}}</strong></span>
              <span class="state">
                <em v-if="reqPmInfo.yn_credit_info=='Y'">신용등급</em>
                <em v-if="reqPmInfo.cd_share=='01' && reqPmInfo.yn_debt_info=='Y'">대출</em>
                <em v-if="reqPmInfo.yn_asset_info=='Y'">자산</em>
                <em v-if="reqPmInfo.yn_consume_info=='Y'">소비</em>
                <em v-if="reqPmInfo.cd_share=='02' && reqPmInfo.yn_debt_info=='Y'">부채</em>
              </span>
            </p>
          </a>
        </div>
      </div>

      <div class="btn-wrap mb80">
        <a @click="goHistory('req')" class="stroke blue">이전 내역 보기</a>
      </div>

      <div class="btn-wrap float">
        <a @click="goNewRequest();" class="solid blue box">신규 요청하기</a>
      </div>
    </template>

    <template v-else-if="type_list=='offer'">
      <div v-if="offerSbList.length == 0 && offerPmList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="box-list noMG list02">
        <p class="header col">허용 대기</p>
        <div v-if="offerSbList.length == 0" class="mb20">등록된 내역이 없습니다.</div>
        <div v-for="offerSbInfo in offerSbList" :key="offerSbInfo.index" class="item">
          <a @click="goSettingDetail('offer','01', offerSbInfo.seq_share, '')" class="share">
            <span class="name">{{offerSbInfo.req_nm_person}}</span>
            <span class="state">대기</span>
            <span>{{offerSbInfo.req_hp}}</span>
          </a>
        </div>
        <p class="header col">공유된 {{cd_share=='01'? '타인' : '가족'}}</p>
        <div v-if="offerPmList.length == 0">등록된 내역이 없습니다.</div>
        <div v-for="offerPmInfo in offerPmList" :key="offerPmInfo.index" c class="item">
          <a @click="goSettingDetail('offer','02', offerPmInfo.seq_share, offerPmInfo.dt_stt_offer)" class="share">
            <p>
              <span class="name">{{offerPmInfo.req_nm_person}}</span>
              <span>{{offerPmInfo.req_hp}}</span>
            </p>
            <p class="mt10">
              <span><strong>{{formatDateDot(offerPmInfo.dt_stt_offer)}}</strong></span>
              <span class="state">
                <em v-if="offerPmInfo.yn_credit_info=='Y'">신용등급</em>
                <em v-if="offerPmInfo.cd_share=='01' && offerPmInfo.yn_debt_info=='Y'">대출</em>
                <em v-if="offerPmInfo.yn_asset_info=='Y'">자산</em>
                <em v-if="offerPmInfo.yn_consume_info=='Y'">소비</em>
                <em v-if="offerPmInfo.cd_share=='02' && offerPmInfo.yn_debt_info=='Y'">부채</em>
              </span>
            </p>
          </a>
        </div>
      </div>

      <div class="btn-wrap mb35">
        <a @click="goHistory('offer')" class="stroke blue">이전 내역 보기</a>
      </div>
    </template>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "ShareMain",
  data() {
    return {
      errMsg: "",
      seen: false,
      cd_share: "01", //신용정보(01) / 금융정보(02) 구분
      type_list: "req", //요청(req) / 정보제공(offer) 구분
      currentDate: "", //오늘날짜
      reqSbList: [], //요청list(대기)
      reqPmList: [], //요청list(공유중)
      offerSbList: [], //정보제공list(대기)
      offerPmList: [] //정보제공list(공유중)
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    if (
      "" != this.$route.query.cd_share &&
      this.$route.query.cd_share != null
    ) {
      this.cd_share = this.$route.query.cd_share;
    }
    if (
      "" != this.$route.query.type_list &&
      this.$route.query.type_list != null
    ) {
      this.type_list = this.$route.query.type_list;
    }

    this.$store.state.header.type = "sub";
    if ("01" == this.cd_share) {
      this.$store.state.title = "신용정보 제공";
    } else if ("02" == this.cd_share) {
      this.$store.state.title = "금융정보 공유";
    }

    this.getMainInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 탭클릭
    clickTab: function(type_list) {
      var _this = this;
      _this.type_list = type_list;
    },
    //메인정보
    getMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/customercenter/listShareInfoMain.json", {
          params: { cd_share: _this.cd_share }
        })
        .then(response => {
          _this.currentDate = response.data.currentDate;
          _this.reqSbList = response.data.reqSbList;
          _this.reqPmList = response.data.reqPmList;
          _this.offerSbList = response.data.offerSbList;
          _this.offerPmList = response.data.offerPmList;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });

      _this.seen = true;
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //설정/상세 페이지로 이동
    goSettingDetail: function(
      type_list,
      share_status,
      seq_share,
      dt_stt_offer
    ) {
      var _this = this;

      if ("01" == _this.cd_share) {
        //신용
        if ("req" == type_list) {
          //요청탭에서 이동
          if ("01" == share_status) {
            //대기
            this.$router.push({
              name: "shareReqSetting",
              query: { cd_share: _this.cd_share, seq_share: seq_share }
            });
          } else if ("02" == share_status) {
            //공유중
            if (dt_stt_offer == _this.currentDate) {
              this.$router.push({
                name: "shareDetail",
                query: { seq_share: seq_share }
              });
            }
          }
        } else {
          //허용탭에서 이동
          if ("01" == share_status) {
            //대기
            this.$router.push({
              name: "shareOfferSetting",
              query: { cd_share: _this.cd_share, seq_share: seq_share }
            });
          } else if ("02" == share_status) {
            //공유중
            if (dt_stt_offer == _this.currentDate) {
              this.$router.push({
                name: "shareOfferSetting",
                query: { cd_share: _this.cd_share, seq_share: seq_share }
              });
            }
          }
        }
      } else {
        //금융
        if ("req" == type_list) {
          //요청탭에서 이동
          this.$router.push({
            name: "shareReqSetting",
            query: { cd_share: _this.cd_share, seq_share: seq_share }
          });
        } else {
          //허용탭에서 이동
          this.$router.push({
            name: "shareOfferSetting",
            query: { cd_share: _this.cd_share, seq_share: seq_share }
          });
        }
      }
    },
    //이전내역보기페이지로 이동
    goHistory: function(type_list) {
      var _this = this;
      this.$router.push({
        name: "shareHistory",
        query: { cd_share: _this.cd_share, type_list: type_list }
      });
    },
    //신규요청페이지로 이동
    goNewRequest: function() {
      var _this = this;
      this.$router.push({
        name: "shareNewRequest",
        query: { cd_share: _this.cd_share }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
