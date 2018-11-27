<template>
  <div v-if="seen">
    <section>
      <div class="gray-search-box">
        <div class="search">
          <div class="left">
            <button @click="setTermType('01'); searchDepWdrlList();" :class="{on:scTermType=='01'}">1주일</button>
            <button @click="setTermType('02'); searchDepWdrlList();" :class="{on:scTermType=='02'}">3개월</button>
            <button @click="setTermType('03'); searchDepWdrlList();" :class="{on:scTermType=='03'}">6개월</button>
          </div>
          <div class="right">
            <button class="acco">조건검색</button>
          </div>
        </div>
        <div class="form">
          <p>은행계좌선택</p>
          <p>
            <select v-model="scAccount" @change="searchDepWdrlList()">
              <option value="">전체</option>
              <template v-for="scAccountInfo in scAccountList">
                <option :key="scAccountInfo.index" :value="scAccountInfo.no_account">{{scAccountInfo.nm_fc}}({{scAccountInfo.no_account}})</option>
              </template>
            </select>
          </p>
        </div>
        <div class="wrap">
          <div class="date-pick">
            <p>
              <input v-model="txt_dt_from" type="text" readonly="readonly"><button></button>
            </p>
            <p>
              <input v-model="txt_dt_to" type="text" readonly="readonly"><button></button>
            </p>
          </div>
          <div @click="searchDepWdrlList()" class="btn-wrap mt20">
            <a class="solid blue">검색</a>
          </div>
        </div>
      </div>

      <div class="bank-detail noMG">
        <div class="select">
          <div class="left">
            <select v-model="scTrnsType" @change="searchDepWdrlList()">
              <option v-for="scTrnsTypeOption in scTrnsTypeOptions" :key="scTrnsTypeOption.index" :value="scTrnsTypeOption.value">
                {{ scTrnsTypeOption.text }}
              </option>
            </select>
          </div>
          <div class="right">
            <button class="btn-search"></button>
          </div>
        </div>

        <div class="inout">
          <div>
            <p class="key red">입금<em>(원)</em></p>
            <p class="number">{{(totalAmt.cnt_account=="0")? '-' : formatNumber(totalAmt.total_amt_dep)}}</p>
          </div>
          <div>
            <p class="key blue">출금<em>(원)</em></p>
            <p class="number">{{(totalAmt.cnt_account=="0")? '-' : formatNumber(totalAmt.total_amt_wdrl)}}</p>
          </div>
        </div>

        <div v-if="depWdrlList.length == 0" class="nodata">등록 내역이 없습니다</div>
        <div v-else class="nobox-list">
          <template v-for="depWdrlInfo in depWdrlList">
            <p :key="depWdrlInfo.index" v-if="depWdrlInfo.dateCol" class="date">{{formatDateDot(depWdrlInfo.dt_trd)}}</p>
            <div :key="depWdrlInfo.index" @click="viewDetail(depWdrlInfo.no_person, depWdrlInfo.no_account, depWdrlInfo.dt_trd, depWdrlInfo.tm_trd, depWdrlInfo.rk);" class="item">
              <div class="flex">
                <p><em class="circle" :class="colorList[depWdrlInfo.rk]">{{depWdrlInfo.nm_person}}</em><em>{{depWdrlInfo.doc1}}</em></p>
                <p v-if="'0'!=depWdrlInfo.amt_dep"><em class="number blue">{{formatNumber(depWdrlInfo.amt_dep)}}</em>원</p>
                <p v-else><em class="number red">{{('-'+formatNumber(depWdrlInfo.amt_wdrl))}}</em>원</p>
              </div>
              <div class="flex">
                <p class="key">{{depWdrlInfo.nm_fc}}</p>
                <p class="key">{{depWdrlInfo.an}}</p>
              </div>
            </div>
          </template>
        </div>
      </div>
    </section>

    <aside class="search-wrap">
      <div class="top">
        <button>검색</button>
      </div>
      <div class="wrap">
        <div class="hash">
          <a v-for="scKeywordInfo in scKeywordList" :key="scKeywordInfo.index"># {{scKeywordInfo.doc1}}</a>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "AssetsAccountWdrlDetail",
  data() {
    return {
      seen: "",
      scTermType: "", //기간type
      scAccountList: [], //검색 계좌list
      scAccount: "", //검색 계좌
      currentDate: "", //오늘 날짜
      txt_dt_from: "", //검색 시작일
      txt_dt_to: "", //검색 종료일
      scKeywordList: [],
      scKeyword: "",
      scTrnsTypeOptions: [
        { text: "전체", value: "" },
        { text: "입금", value: "01" },
        { text: "출금", value: "02" }
      ],
      scTrnsType: "",
      totalAmt: "", //입금 / 출금 총액
      page: 1,
      depWdrlList: [], //입출금list
      colorList: ["red", "orange", "green", "blue", "purple"]
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "입출금내역";
  },
  created() {
    this.getSearchCondition();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //기간 선택
    setTermType: function(termType) {
      var _this = this;
      var d = new Date();

      //1주일
      if ("01" == termType) {
        var dayOfMonth = d.getDate();
        d.setDate(dayOfMonth - 7);

        //3개월
      } else if ("02" == termType) {
        var monthOfYear = d.getMonth();
        d.setMonth(monthOfYear - 3);

        //6개월
      } else if ("03" == termType) {
        var monthOfYear = d.getMonth();
        d.setMonth(monthOfYear - 6);
      }
      _this.scTermType = termType;
      _this.txt_dt_from = _this.getDateStr(d);
    },
    getDateStr(myDate) {
      var month = myDate.getMonth() + 1;
      if (month < 10) {
        month = "0" + month;
      }
      return myDate.getFullYear() + "-" + month + "-" + myDate.getDate();
    },
    //검색조건 조회
    getSearchCondition: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsBankDepWdrlSc.json", {
          params: {}
        })
        .then(response => {
          _this.scAccountList = response.data.scAccountList;
          _this.currentDate = response.data.currentDate;
          _this.scKeywordList = response.data.scKeywordList;

          //store값 셋팅
          if ("" != this.$store.state.scListParam.scTermType) {
            _this.scTermType = this.$store.state.scListParam.scTermType;
            _this.setTermType(this.scTermType);
          }
          if ("" != this.$store.state.scListParam.scAccount) {
            _this.scAccount = this.$store.state.scListParam.scAccount;
          }
          if ("" != this.$store.state.scListParam.txt_dt_from) {
            _this.txt_dt_from = this.$store.state.scListParam.txt_dt_from;
          } else {
            _this.txt_dt_from = _this.currentDate;
          }
          if ("" != this.$store.state.scListParam.txt_dt_to) {
            _this.txt_dt_to = this.$store.state.scListParam.txt_dt_to;
          } else {
            _this.txt_dt_to = _this.currentDate;
          }
          if ("" != this.$store.state.scListParam.scKeyword) {
            _this.scKeyword = this.$store.state.scListParam.scKeyword;
          }
          if ("" != this.$store.state.scListParam.scTrnsType) {
            _this.scTrnsType = this.$store.state.scListParam.scTrnsType;
          }

          //store 검색조건 초기화
          this.$store.state.scListParam.scTermType = "";
          this.$store.state.scListParam.scAccount = "";
          this.$store.state.scListParam.txt_dt_from = "";
          this.$store.state.scListParam.txt_dt_to = "";
          this.$store.state.scListParam.scKeyword = "";
          this.$store.state.scListParam.scTrnsType = "";

          _this.searchDepWdrlList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //검색
    searchDepWdrlList: function() {
      var _this = this;
      _this.page = 1;
      _this.depWdrlList = [];
      _this.getDepWdrlTotalAmt();
      Common.pagination(_this.listDepWdrl);
    },
    //입금 / 출금 총액
    getDepWdrlTotalAmt: function() {
      var _this = this;

      console.log("txt_dt_from" + _this.txt_dt_from);
      console.log("txt_dt_to" + _this.txt_dt_to);
      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("scAccount", _this.scAccount);
      formData.append("txt_dt_from", _this.txt_dt_from);
      formData.append("txt_dt_to", _this.txt_dt_to);
      formData.append("scTrnsType", _this.scTrnsType);
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/assets/getAssetsBankDepWdrlTotalAmt.json", formData)
        .then(function(response) {
          _this.totalAmt = response.data.totalAmt;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    getCodeName: function(code_group, code_value) {
      return Common.getCodeName(code_group, code_value);
    },
    //입출금list
    listDepWdrl: function(callback) {
      var _this = this;

      console.log("txt_dt_from" + _this.txt_dt_from);
      console.log("txt_dt_to" + _this.txt_dt_to);
      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("scAccount", _this.scAccount);
      formData.append("txt_dt_from", _this.txt_dt_from);
      formData.append("txt_dt_to", _this.txt_dt_to);
      formData.append("scTrnsType", _this.scTrnsType);
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/assets/listAssetsBankDepWdrl.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          //날짜 칼럼 셋팅
          var dtTrd = "";
          for (var i = 0; i < list.length; i++) {
            list[i];
            if (dtTrd != list[i].dt_trd) {
              list[i].dateCol = true;
            } else {
              list[i].dateCol = false;
            }
            dtTrd = list[i].dt_trd;
          }

          //pagination
          if (list.length === 0) {
            callback();
            _this.depWdrlList = [];
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.depWdrlList = list;
          } else {
            for (var key in list) {
              _this.depWdrlList.push(list[key]);
            }
          }
          _this.totalPage = response.data.pagedList.pageCount;
          _this.page++;
          //pagination

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    //상세페이지로 이동
    viewDetail: function(no_person, no_account, dt_trd, tm_trd, rk) {
      var _this = this;

      //store 검색조건 유지
      this.$store.state.scListParam.scTermType = _this.scTermType;
      this.$store.state.scListParam.scAccount = _this.scAccount;
      this.$store.state.scListParam.txt_dt_from = _this.txt_dt_from;
      this.$store.state.scListParam.txt_dt_to = _this.txt_dt_to;
      this.$store.state.scListParam.scKeyword = _this.scKeyword;
      this.$store.state.scListParam.scTrnsType = _this.scTrnsType;

      this.$router.push({
        name: "assetsBankDepWdrlDetail",
        query: {
          no_person: no_person,
          no_account: no_account,
          dt_trd: dt_trd,
          tm_trd: tm_trd,
          rk: rk
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
