<template>
  <div @click="closeMenu" v-if="seen">
    <section>
      <div class="container">
        <div class="bar-top noMG">
          <div class="top">
            <p class="symbol"><img :src="'/m/fincorp/getFinCorpIcon.crz?cd_fc='+debtVO.cd_fc" alt="" />{{debtVO.nm_fc}}</p>
            <div class="btn-menu-wrap" :class="{'on':isOpen}" v-if="isMine">
              <button id="btn-menu-pop" class="btn-menu-pop" @click="openMenu"></button>
              <div class="menu">
                <!-- <a @click="clickMenu('modify')">수정</a> -->
                <a @click="clickMenu('delete')">삭제</a>
                <a @click="clickMenu('memo')">메모</a>
                <a @click="clickMenu('tel')">전화걸기</a>
              </div>
            </div>
          </div>
          <p class="key">상환금액(당월)</p>
          <div class="wrap">
            <div class="left">{{Common.formatNumber(debtVO.cur_mm_amt_repay * 10000)}}<em>원</em></div>
            <div class="right">
              <p class="key">잔액</p>
              <p class="value">{{Common.formatNumber(debtVO.amt_remain)}}<em>원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="'width:'+ debtVO.rate_amt_contract+'%'"></p>
          </div>
          <div class="bar-text-wrap">
            <p class="bar-text">원금상환비율 <em>{{debtVO.rate_amt_contract}}%</em></p>
            <p class="bar-card">금리<em>{{debtVO.ever_interest}}%</em>
              <!--<button class="btn-info"></button>-->
            </p>
          </div>
        </div>
      </div>

      <div class="tab">
        <div class="wrap">
          <a @click="clickTab('contract')" :class="{'on':curTab == 'contract'}">계약정보</a>
          <a @click="clickTab('repay')" :class="{'on':curTab == 'repay'}">상환정보</a>
        </div>
      </div>

      <div v-if="curTab == 'contract'" class="block-list noMG">
        <ul class="list">
          <li>
            <p class="key">대출구분</p>
            <p class="value">{{debtVO.type_deal}}</p>
          </li>
          <li>
            <p class="key">개설일자</p>
            <p class="value">{{formatDateDot(debtVO.ymd_loan)}}</p>
          </li>
          <li>
            <p class="key">만기일자</p>
            <p class="value">{{formatDateDot(debtVO.ymd_loanend)}}</p>
          </li>
          <li>
            <p class="key">금리</p>
            <p class="value">{{debtVO.ever_interest}}%</p>
          </li>
          <li>
            <p class="key">대출원금</p>
            <p class="value">{{Common.formatNumber(debtVO.amt_contract/10000)}}만원</p>
          </li>
          <li>
            <p class="key">대출기간</p>
            <p class="value">{{debtVO.term_all}}개월</p>
          </li>
          <li>
            <p class="key">상환방법</p>
            <p class="value">{{debtVO.rep_method}}</p>
          </li>
          <li>
            <p class="key">대출잔액</p>
            <p class="value">{{Common.formatNumber(debtVO.amt_remain/10000)}}만원</p>
          </li>
          <li>
            <p class="key">잔여기간</p>
            <p class="value">{{debtVO.term_remain}}개월</p>
          </li>
        </ul>
      </div>

      <div v-if="curTab == 'repay'" class="block-list2 noMG">
        <div v-if="(listDebtRepay||'')!=''">
          <div class="index">
            <p class="normal">정상</p>
            <p class="overdue">연체</p>
            <p class="etc">기타</p>
          </div>

          <ul class="list">
            <li :class="getCdStateColor(debtRepay.cd_state)" @click="openRepPop(index)" v-for="(debtRepay, index) in listDebtRepay" :key="index">{{debtRepay.req_yyyymm}}</li>
          </ul>
        </div>
        <div v-else class="nodata">
          상환 내역이 없습니다
        </div>
      </div>

    </section>
    <vue-modal transitionName="fade" name="repModal" :height="'auto'">
      <RepPop slot="body" :curRepay="curRepay" :close="closeRepPop" />
    </vue-modal>

    <!-- <div v-if="!isAuto && isMine" class="btn-wrap float">
      <a @click="goRepayment" class="blue box solid">상환금 입력</a>
    </div> -->
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import RepPop from "./sub/RepPop.vue";

export default {
  name: "DebtDetail",
  data() {
    return {
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      curTab: "contract", // 현재 탭(contract: 계약 정보, repaymnet: 상환 정보)
      isMine: true, // 보인 부채 여주
      debtVO: "", // 부채 데이터
      listDebtRepay: "", // 12개월 상환 정보
      curRepay: "", // 상세 상환 정보로 나올 데이터
      isOpen: false, // 모달 표시 여부
      isAuto: true // kcb 등록 여부(아니면 개인 등록 부채로 현재 사용하지 않음)
    };
  },
  components: {
    RepPop
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상세정보";
  },
  created() {
    this.getDebtInfo();
    this.isMine =
      this.$route.query.isMine == "true" || this.$route.query.isMine == true;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포맷---------------------
    // 잘못된 데이터 체크가 들어간 날짜 포맷
    formatDateDot: function(date) {
      if ((date || "") == "") {
        return;
      }
      return Common.formatDateDot(date.replace("-", ""));
    },
    // ---------------------//데이터 포맷---------------------
    // ---------------------화면 이벤트---------------------
    // 탭 클릭 시
    clickTab: function(key) {
      if (key == this.curTab) {
        return;
      }
      if (key == "contract") {
        this.curTab = "contract";
      } else {
        this.curTab = "repay";
      }
    },
    // 메뉴 클릭 시
    clickMenu: function(key) {
      var _this = this;
      if (key == "modify") {
        this.$router.push({
          path: "/debt/modify",
          query: {
            // no_person: _this.$route.query.no_person,
            no_manage_info: _this.$route.query.no_manage_info,
            interest: _this.debtVO.ever_interest
          }
        });
      } else if (key == "delete") {
        var options = Constant.options;
        options.size = "lg";
        this.$dialogs
          .confirm(
            "선택하신 부채 정보는 모두 삭제되어 이후 조회가 불가능합니다.\n\n해당 정보를 삭제할까요?",
            options
          )
          .then(res => {
            // console.log(res); // {ok: true|false|undefined}
            _this.isAuto = true;
            if (res.ok) {
              var formData = new FormData();
              formData.append("no_person", this.$route.query.no_person);
              formData.append(
                "no_manage_info",
                this.$route.query.no_manage_info
              );
              this.$http
                .post("/m/debt/deleteDebt.json", formData)
                .then(function(response) {
                  _this.$router.push("/debt/main");
                });
            }
          });
      } else if (key == "memo") {
        this.$router.push({
          path: "/memo/list",
          query: {
            // no_person: _this.$route.query.no_person,
            no_manage_info: _this.$route.query.no_manage_info
          }
        });
      } else if (key == "tel") {
        this.callFc(this.debtVO.tel);
      }
    },
    // 메뉴 열기
    openMenu: function(e) {
      this.isOpen = true;
      // 부모로의 이벤트 전달을 막아 closeMenu() 를 실행시키지 않도록
      e.stopPropagation();
    },
    // 메뉴 닫기
    closeMenu: function() {
      this.isOpen = false;
    },
    // 상환 모달 표출
    openRepPop: function(index) {
      this.curRepay = this.listDebtRepay[index];
      this.$modals.show("repModal");
    },
    // 상환 모달 닫기
    closeRepPop: function() {
      this.$modals.hide("repModal");
    },
    // 상환금 입력(현재 사용하지 않음)
    goRepayment: function() {
      var _this = this;
      this.$router.push({
        path: "/debt/repayment",
        query: {
          // no_person: _this.$route.query.no_person,
          no_manage_info: _this.$route.query.no_manage_info
        }
      });
    },
    // ---------------------//화면 이벤트---------------------
    // ---------------------데이터 이동---------------------
    // 부채정보 조회
    getDebtInfo: function() {
      var _this = this;
      this.$http
        .get("/m/debt/getDebtInfo.json", {
          params: {
            // no_person: this.$route.query.no_person,
            no_manage_info: this.$route.query.no_manage_info
          }
        })
        .then(function(response) {
          if ((response.data.debtVO || "") != "") {
            _this.debtVO = response.data.debtVO;
            _this.listDebtRepay = response.data.listDebtRepay;
            _this.isAuto =
              _this.debtVO.debt_yn == null || _this.debtVO.debt_yn == "N"
                ? true
                : false;
          }
          _this.seen = true;
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 부채 상환 상태에 따른 class 결정
    getCdStateColor: function(cd_state) {
      switch (cd_state) {
        case "0":
          return "normal";
          break;
        case "2":
        case "3":
          return "overdue";
          break;
        case "1":
        case "4":
        case "5":
          return "etc";
      }
    },
    //금융사 연결
    callFc: function(tel) {
      if (Constant.userAgent == "iOS") {
        Jockey.send("phoneCall", {
          phNum: tel
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.phoneCall(tel);
      }
    }
    // ---------------------//기타---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
