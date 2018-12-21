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
            <multiselect :id="'scAccount'" v-model="scAccount" class="multiselect-basic" :title="'계좌'" placeholder="계좌선택" :options="scAccountOptions" :onClose="onSelectAcc">
            </multiselect>
          </p>
        </div>
        <div class="wrap">
          <div class="date-pick">
            <p>
              <datepicker v-model="txt_dt_from" ref="txtDtFromOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
              <button @click="openDtFromPicker"></button>
            </p>
            <p>
              <datepicker v-model="txt_dt_to" ref="txtDtToOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
              <button @click="openDtToPicker"></button>
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
            <multiselect :id="'scTrnsType'" v-model="scTrnsType" :title="'유형'" placeholder="유형선택" :options="scTrnsTypeOptions" :onClose="onSelectTrns" :alignLeft="true">
            </multiselect>
          </div>
          <div class="right">
            <span v-if="scKeyword!=''" @click="initScKeyword();" class="pr10">{{scKeyword}}</span>
            <button class="btn-search" @click="openScKeywordMd();"></button>
          </div>
        </div>

        <div class="inout">
          <div>
            <p class="key blue">입금<em>(원)</em></p>
            <p class="number">{{(totalAmt.cnt_account=="0")? '-' : formatNumber(totalAmt.total_amt_dep)}}</p>
          </div>
          <div>
            <p class="key red">출금<em>(원)</em></p>
            <p class="number">{{(totalAmt.cnt_account=="0")? '-' : formatNumber(totalAmt.total_amt_wdrl)}}</p>
          </div>
        </div>

        <div v-if="depWdrlList.length == 0" class="nodata">조회 내역이 없습니다</div>
        <div v-else class="nobox-list">
          <template v-for="depWdrlInfo in depWdrlList">
            <p :key="depWdrlInfo.index" v-if="depWdrlInfo.dateCol" class="date">{{formatDateDot(depWdrlInfo.dt_trd)}}</p>
            <div :key="depWdrlInfo.index" @click="viewDetail(depWdrlInfo.seq_tran, depWdrlInfo.rk);" class="item">
              <div class="flex">
                <p><em v-if="yn_share=='Y'" class="circle" :class="colorList[depWdrlInfo.rk]">{{depWdrlInfo.nm_person}}</em><em>{{depWdrlInfo.doc1}}</em></p>
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

    <aside class="search-wrap" :class="{on: isShowScKeyword}">
      <div class="top">
        <button @click="closeScKeywordMd();">검색</button>
      </div>
      <div class="wrap">
        <div class="hash">
          <a @click="clickScKeyword(scKeywordInfo.doc1)" v-for="scKeywordInfo in scKeywordList" :key="scKeywordInfo.index" :class="{on: scKeywordInfo.doc1==scKeyword}"># {{scKeywordInfo.doc1}}</a>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import korean from "vee-validate/dist/locale/ko.js";

import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

export default {
  name: "assetsBankDepWdrlList",
  data() {
    return {
      seen: false,
      Common: Common,
      ko: ko,
      scTermType: "", //기간type
      scAccountList: [], //검색 계좌list
      scAccountOptions: [],
      scAccount: "", //검색 계좌
      currentDate: "", //오늘 날짜
      txt_dt_from: "", //검색 시작일
      txt_dt_to: "", //검색 종료일
      isShowScKeyword: false, //검색키워드 modal 보여주기
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
      yn_share: "", //공유여부
      person_share_list: "", //공유자list
      colorList: ["red", "orange", "green", "blue", "purple"]
    };
  },
  components: {
    datepicker: datepicker
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "입출금내역";
  },
  created() {
    this.yn_share = this.$route.query.yn_share;
    this.person_share_list = this.$route.query.person_share_list;

    this.getSearchCondition();
  },
  beforeMount() {},
  mounted() {
    Common.datepickerInit("div-date", this);
  },
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
      var day = myDate.getDate();
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      return myDate.getFullYear() + "-" + month + "-" + day;
    },
    //검색조건 조회
    getSearchCondition: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsBankDepWdrlSc.json", {
          params: { yn_share: _this.yn_share }
        })
        .then(response => {
          //계좌list 셋팅(검색용)
          var scAccountList = response.data.scAccountList;
          _this.scAccountList = scAccountList;
          _this.scAccountOptions.push({ text: "전체", value: "" });
          for (var i = 0; i < scAccountList.length; i++) {
            _this.scAccountOptions.push({
              text:
                scAccountList[i].nm_fc +
                "(" +
                scAccountList[i].no_account +
                ")",
              value: scAccountList[i].no_account
            });
          }

          _this.currentDate = response.data.currentDate;
          _this.getScKeywordList();

          //store값 셋팅
          //날짜유형
          if (typeof this.$store.state.scListParam.query1 != "undefined") {
            _this.scTermType = this.$store.state.scListParam.query1;
            _this.setTermType(_this.scTermType);
          } else {
            //1주일 default 셋팅
            _this.setTermType("01");
          }
          //계좌(multiselect)
          if (typeof this.$store.state.scListParam.query2 != "undefined") {
            for (var i = 0; i < _this.scAccountOptions.length; i++) {
              if (
                _this.scAccountOptions[i].value ==
                _this.$store.state.scListParam.query2
              ) {
                _this.scAccount = _this.scAccountOptions[i];
              }
            }
          }
          //날짜시작일
          if (typeof this.$store.state.scListParam.query3 != "undefined") {
            _this.txt_dt_from = this.$store.state.scListParam.query3;
          }
          //날짜종료일
          if (typeof this.$store.state.scListParam.query4 != "undefined") {
            _this.txt_dt_to = this.$store.state.scListParam.query4;
          } else {
            _this.txt_dt_to = _this.currentDate;
          }
          //계좌유형(multiselect)
          if (typeof this.$store.state.scListParam.query5 != "undefined") {
            for (var i = 0; i < _this.scTrnsTypeOptions.length; i++) {
              if (
                _this.scTrnsTypeOptions[i].value ==
                _this.$store.state.scListParam.query5
              ) {
                _this.scTrnsType = _this.scTrnsTypeOptions[i];
              }
            }
          }
          //검색키워드
          if (typeof this.$store.state.scListParam.query6 != "undefined") {
            _this.scKeyword = this.$store.state.scListParam.query6;
          }

          //store 검색조건 초기화
          this.$store.state.scListParam.query1 = undefined; //날짜유형
          this.$store.state.scListParam.query2 = undefined; //계좌
          this.$store.state.scListParam.query3 = undefined; //날짜시작일
          this.$store.state.scListParam.query4 = undefined; //날짜종료일
          this.$store.state.scListParam.query5 = undefined; //계좌유형
          this.$store.state.scListParam.query6 = undefined; //검색키워드

          _this.searchDepWdrlList();
        })
        .catch(e => {
          this.$toast.center(korean.messages.error);
        });
    },
    //multiselect
    onSelectAcc: function(option) {
      var _this = this;
      _this.scAccount = option;
      console.log(option);
      _this.searchDepWdrlList();
    },
    onSelectTrns: function(option) {
      var _this = this;
      _this.scTrnsType = option;
      console.log(option);
      _this.getScKeywordList();
      _this.searchDepWdrlList();
    },
    //datePicker
    openDtFromPicker: function() {
      this.$refs.txtDtFromOpen.showCalendar();
    },
    openDtToPicker: function() {
      this.$refs.txtDtToOpen.showCalendar();
    },
    //검색키워드
    openScKeywordMd: function() {
      var _this = this;
      _this.isShowScKeyword = true;
    },
    closeScKeywordMd: function() {
      var _this = this;
      _this.isShowScKeyword = false;
    },
    clickScKeyword: function(doc1) {
      var _this = this;
      _this.scKeyword = doc1;
      _this.closeScKeywordMd();
      _this.searchDepWdrlList();
    },
    initScKeyword: function() {
      var _this = this;
      _this.scKeyword = "";
      _this.searchDepWdrlList();
    },
    //키워드목록 조회
    getScKeywordList: function() {
      var _this = this;

      console.log("scTrnsType" + _this.scTrnsType.value);

      var formData = new FormData();
      formData.append("scTrnsType", _this.scTrnsType.value);
      formData.append("no_person", this.$store.state.user.noPerson);
      formData.append("person_share_list", _this.person_share_list);

      this.$http
        .post("/m/assets/getAssetsBankScKeywordList.json", formData)
        .then(response => {
          _this.scKeywordList = response.data.scKeywordList;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //검색
    searchDepWdrlList: function() {
      var _this = this;
      if (!_this.validBankDepWdrlList()) return false;
      _this.page = 1;
      _this.getDepWdrlTotalAmt();
      Common.pagination(_this.listDepWdrl);
    },
    validBankDepWdrlList: function() {
      var _this = this;
      var txt_dt_from = Common.formatDateDB(_this.txt_dt_from);
      var txt_dt_to = Common.formatDateDB(_this.txt_dt_to);
      if (txt_dt_from == "" && txt_dt_to != "") {
        _this.$toast.center("조회 시작일을 입력해 주세요");
        return false;
      }
      if (txt_dt_from != "" && txt_dt_to == "") {
        _this.$toast.center("조회 종료일을 입력해 주세요");
        return false;
      }
      if (Number(txt_dt_from) > Number(txt_dt_to)) {
        _this.$toast.center("조회 시작일이 종료일보다 큽니다");
        return false;
      }
      return true;
    },
    //입금 / 출금 총액
    getDepWdrlTotalAmt: function() {
      var _this = this;

      console.log("txt_dt_from" + Common.formatDateDB(_this.txt_dt_from));
      console.log("txt_dt_to" + Common.formatDateDB(_this.txt_dt_to));
      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("yn_share", _this.yn_share);
      formData.append(
        "scAccount",
        _this.scAccount != "" ? _this.scAccount.value : ""
      );
      formData.append("txt_dt_from", Common.formatDateDB(_this.txt_dt_from));
      formData.append("txt_dt_to", Common.formatDateDB(_this.txt_dt_to));
      formData.append(
        "scTrnsType",
        _this.scTrnsType != "" ? _this.scTrnsType.value : ""
      );
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/assets/getAssetsBankDepWdrlTotalAmt.json", formData)
        .then(function(response) {
          _this.totalAmt = response.data.totalAmt;
        })
        .catch(e => {
          _this.$toast.center(korean.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDate: function(data) {
      return Common.formatDate(data);
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

      console.log("txt_dt_from" + Common.formatDateDB(_this.txt_dt_from));
      console.log("txt_dt_to" + Common.formatDateDB(_this.txt_dt_to));
      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("yn_share", _this.yn_share);
      formData.append(
        "scAccount",
        _this.scAccount != "" ? _this.scAccount.value : ""
      );
      formData.append("txt_dt_from", Common.formatDateDB(_this.txt_dt_from));
      formData.append("txt_dt_to", Common.formatDateDB(_this.txt_dt_to));
      formData.append(
        "scTrnsType",
        _this.scTrnsType != "" ? _this.scTrnsType.value : ""
      );
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
            if (_this.page == 1) {
              _this.depWdrlList = [];
            }
            callback();
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
          _this.$toast.center(korean.messages.error);
        });
    },
    //상세페이지로 이동
    viewDetail: function(seq_tran, rk) {
      var _this = this;

      //store 검색조건 유지
      this.$store.state.scListParam.query1 = _this.scTermType; //날짜유형
      this.$store.state.scListParam.query2 = _this.scAccount.value; //계좌
      this.$store.state.scListParam.query3 = _this.txt_dt_from; //날짜시작일
      this.$store.state.scListParam.query4 = _this.txt_dt_to; //날짜종료일
      this.$store.state.scListParam.query5 = _this.scTrnsType.value; //계좌유형
      this.$store.state.scListParam.query6 = _this.scKeyword; //검색키워드

      this.$router.push({
        name: "assetsBankDepWdrlDetail",
        query: {
          seq_tran: seq_tran,
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
