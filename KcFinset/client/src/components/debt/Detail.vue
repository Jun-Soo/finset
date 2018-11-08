<template>
  <div @click="closeMenu">
    <section>
      <div class="container">
        <div class="bar-top noMG">
          <div class="top">
            <p class="symbol"><img :src="'/m/fincorp/getFinCorpIcon.crz?cd_fc='+debtVO.cd_fc" alt="" />{{debtVO.nm_fc}}</p>
            <div class="btn-menu-wrap" :class="{'on':isOpen}">
              <button id="btn-menu-pop" class="btn-menu-pop" @click="openMenu"></button>
              <div class="menu">
                <a @click="clickMenu('modify')">수정</a>
                <a @click="clickMenu('delete')">삭제</a>
                <a @click="clickMenu('memo')">메모</a>
                <a :href="'tel:'+debtVO.tel">전화걸기</a>
              </div>
            </div>
          </div>
          <p class="key">상환금액(당월)</p>
          <div class="wrap">
            <div class="left">{{debtVO.cur_mm_amt_repay}}<em>원</em></div>
            <div class="right">
              <p class="key">잔액</p>
              <p class="value">{{formatNumber(debtVO.amt_remain)}}<em>원</em></p>
            </div>
          </div>
          <div class="bar">
            <p :style="'width:'+ debtVO.rate_amt_contract+'%'"></p>
          </div>
          <div class="bar-text-wrap">
            <p class="bar-text">원금상환비율 <em>{{debtVO.rate_amt_contract}}%</em></p>
            <p class="bar-card">금리<em>{{debtVO.ever_interest}}%</em><button class="btn-info"></button></p>
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
            <p class="value">{{formatDate(debtVO.ymd_loan)}}</p>
          </li>
          <li>
            <p class="key">만기일자</p>
            <p class="value">{{formatDate(debtVO.ymd_loanend)}}</p>
          </li>
          <li>
            <p class="key">금리</p>
            <p class="value">{{debtVO.ever_interest}}%</p>
          </li>
          <li>
            <p class="key">대출원금</p>
            <p class="value">{{formatNumber(debtVO.amt_contract/1000)}}만원</p>
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
            <p class="value">{{formatNumber(debtVO.amt_remain/1000)}}만원</p>
          </li>
          <li>
            <p class="key">잔여기간</p>
            <p class="value">{{debtVO.term_remain}}개월</p>
          </li>
        </ul>
      </div>

      <div v-if="curTab == 'repay'" class="block-list2 noMG">
        <div class="index">
          <p class="normal">정상</p>
          <p class="overdue">연체</p>
          <p class="etc">기타</p>
          <p></p>
        </div>

        <ul class="list">
          <!-- <li class="normal" data-modal="modal01">18.02</li>
          <li class="overdue" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="etc" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li> -->
          <li class="normal" data-modal="modal01" v-for="(debtRepay, index) in listDebtRepay" :key="index">{{debtRepay.req_yyyymm}}</li>
        </ul>
      </div>

    </section>
    <div id="modal-wrap">
      <div class="modal-con" data-modal-con="modal01">
        <div class="top">
          {{formatDate(curRepay.req_yyyymm,"yyyymm")}}
          <button class="modal-close"></button>
        </div>
        <div class="debt-modal-body">
          <p class="number">{{formatNumber(curRepay.amt_repay)}}<em>원</em></p>
          <p class="text"><em>원금</em> {{formatNumber(curRepay.amt_repay_p)}}원</p>
          <p class="text"><em>이자</em>{{formatNumber(curRepay.amt_repay_i)}} 원</p>
        </div>
        <a href="#" class="btn-confirm">확인</a>
      </div>
    </div>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "DebtDetail",
  data() {
    return {
      curTab: "contract",
      debtVO: "",
      listDebtRepay: "",
      curIndex: "",
      curRepay: "",
      isOpen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상세정보";
  },
  created() {
    this.getDebtInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDebtInfo: function() {
      var _this = this;
      this.$http
        .get("/m/debt/getDebtInfo.json", {
          params: {
            no_person: this.$route.query.no_person,
            no_manage_info: this.$route.query.no_manage_info
          }
        })
        .then(function(response) {
          _this.debtVO = response.data.debtVO;
          _this.listDebtRepay = response.data.listDebtRepay;
          console.log(_this.listDebtRepay);
        });
    },
    formatNumber: function(number) {
      return Common.formatNumber(number);
    },
    formatDate: function(date) {
      return Common.formatDate(date);
    },
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
    clickMenu: function(key) {
      var _this = this;
      if (key == "modify") {
        this.$router.push({
          path: "/debt/modify",
          query: {
            no_person: _this.$route.query.no_person,
            no_manage_info: _this.$route.query.no_manage_info
          }
        });
      } else if (key == "delete") {
        console.log("delete");
      } else if (key == "memo") {
        this.$router.push({
          path: "/memo/list",
          query: {
            no_person: _this.$route.query.no_person,
            no_manage_info: _this.$route.query.no_manage_info
          }
        });
      }
    },
    openMenu: function(e) {
      this.isOpen = true;
      e.stopPropagation();
    },
    closeMenu: function() {
      this.isOpen = false;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
