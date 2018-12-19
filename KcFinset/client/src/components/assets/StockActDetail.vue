<template>
  <section v-if="seen">
    <div class="balance-top">
      <div class="item">
        <div class="flex-top">
          <p class="symbol"><img :src="stockInfo.fcImg" alt="" />{{stockInfo.nm_fc}}</p>
          <p class="text"><em>{{nm_detail_class}}</em><em>{{stockInfo.accno}}</em></p>
        </div>
        <div class="top">{{formatNumber(stockInfo.amt_stock)}}<em>원</em></div>
        <div class="body">
          <div class="flex">
            <p>출금가능액</p>
            <p>{{formatNumber(stockInfo.cashavwithdraw)}} <em>원</em></p>
          </div>
          <div class="flex">
            <p>대용금</p>
            <p>{{formatNumber(stockInfo.subsmargin)}} <em>원</em></p>
          </div>
          <div class="flex">
            <p>매수/미납금</p>
            <p>{{formatNumber(stockInfo.receivable)}} <em>원</em></p>
          </div>
          <div class="flex">
            <p>대출/신용금</p>
            <p>{{formatNumber(stockInfo.loancredit)}} <em>원</em></p>
          </div>
          <div class="flex">
            <p>유가증권평가금액</p>
            <p>{{formatNumber(stockInfo.valueatcur)}} {{(stockInfo.proloss!='0')?'('+stockInfo.proloss+')':''}}<em>원</em></p>
          </div>
        </div>
      </div>
    </div>

    <div class="box-list list02">

      <div class="select pb20">
        <multiselect :id="'scAccType'" v-model="scAccType" ref="scAccType" class="multiselect-basic" placeholder="분류선택" :title="'분류'" :options="scAccTypeOptions" :onClose="onSelectAcc">
        </multiselect>
        <p></p>
        <multiselect :id="'orderBy'" v-model="orderBy" ref="orderBy" class="multiselect-basic" placeholder="정렬선택" :title="'정렬'" :options="orderByOptions" :onClose="onSelectOrder">
        </multiselect>
      </div>

      <template v-if="accountList.length == 0" class="nodata">조회 내역이 없습니다</template>
      <template v-for="accountInfo in accountList">
        <div :key="accountInfo.index" class="item">
          <a @click="viewDetail(accountInfo.acc_type, accountInfo.acc_code)" class="block">
            <div class="flex">
              <p class="key">{{accountInfo.acc_nm}}</p>
              <p class="number">{{formatNumber(accountInfo.valatcur)}}<em>원</em></p>
            </div>
            <div class="bar">
              <p :style="{width: accountInfo.percent+'%'}"></p>
            </div>
            <div class="flex">
              <p>{{accountInfo.percent}}%</p>
              <p v-if="accountInfo.acc_type!='cash'">{{accountInfo.proloss}} ({{accountInfo.earningrate}}%)</p>
              <p v-else></p>
            </div>
          </a>
        </div>
      </template>

    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsStockActDetail",
  data() {
    return {
      seen: false,
      no_person: "",
      no_account: "",
      nm_detail_class: "",
      stockInfo: "",
      scAccTypeOptions: [
        { text: "전체", value: "" },
        { text: "주식", value: "shr" },
        { text: "펀드", value: "fnd" }
      ],
      scAccType: "",
      orderByOptions: [
        { text: "평가금액순", value: "01" },
        { text: "상승률순", value: "02" },
        { text: "비중순", value: "03" }
      ],
      orderBy: "",
      accountList: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계좌상세";
  },
  created() {
    this.no_person = this.$route.query.no_person;
    this.no_account = this.$route.query.no_account;
    this.nm_detail_class = this.$route.query.nm_detail_class;

    //분류 셋팅
    if (typeof this.$store.state.scListParam.query1 != "undefined") {
      for (var i = 0; i < this.scAccTypeOptions.length; i++) {
        if (
          this.scAccTypeOptions[i].value == this.$store.state.scListParam.query1
        ) {
          this.scAccType = this.scAccTypeOptions[i];
        }
      }
    } else if (
      "" != this.$route.query.scAccType &&
      this.$route.query.scAccType != null
    ) {
      for (var i = 0; i < this.scAccTypeOptions.length; i++) {
        if (this.scAccTypeOptions[i].value == this.$route.query.scAccType) {
          this.scAccType = this.scAccTypeOptions[i];
        }
      }
    }

    //정렬기준 셋팅
    if (typeof this.$store.state.scListParam.query2 != "undefined") {
      for (var i = 0; i < this.orderByOptions.length; i++) {
        if (
          this.orderByOptions[i].value == this.$store.state.scListParam.query2
        ) {
          this.orderBy = this.orderByOptions[i];
        }
      }
    } else if (
      "" != this.$route.query.orderBy &&
      this.$route.query.orderBy != null
    ) {
      for (var i = 0; i < this.orderByOptions.length; i++) {
        if (this.orderByOptions[i].value == this.$route.query.orderBy) {
          this.orderBy = this.orderByOptions[i];
        }
      }
    } else {
      this.orderBy = this.orderByOptions[0]; //기본정렬 - 평가금액순 셋팅
    }

    //store 검색조건 초기화
    this.$store.state.scListParam.query1 = undefined; //분류
    this.$store.state.scListParam.query2 = undefined; //정렬기준

    this.getAssetsStockInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //계좌정보조회
    getAssetsStockInfo: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);

      this.$http
        .post("/m/assets/getAssetsStockInfo.json", formData)
        .then(function(response) {
          var stockInfo = response.data.stockInfo;
          //증권사이미지 셋팅
          stockInfo.fcImg =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + stockInfo.cd_fc;
          //유가증권평가손익 셋팅
          if (stockInfo.proloss > 0) {
            stockInfo.proloss = "+" + Common.formatNumber(stockInfo.proloss);
          } else {
            stockInfo.proloss = Common.formatNumber(stockInfo.proloss);
          }
          _this.stockInfo = stockInfo;
          _this.searchAccountList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    onSelectAcc: function(option) {
      var _this = this;
      _this.scAccType = option;
      console.log(option);
      _this.searchAccountList();
    },
    onSelectOrder: function(option) {
      var _this = this;
      _this.orderBy = option;
      _this.searchAccountList();
    },
    //계좌 내역 조회
    searchAccountList: function() {
      var _this = this;
      _this.page = 1;
      Common.pagination(_this.listAccount);
    },
    listAccount: function(callback) {
      var _this = this;

      console.log("page" + _this.page);
      console.log("scAccType" + _this.scAccType);
      console.log("orderBy" + _this.orderBy);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);
      formData.append(
        "scAccType",
        _this.scAccType != "" ? _this.scAccType.value : ""
      );
      formData.append(
        "orderBy",
        _this.orderBy != "" ? _this.orderBy.value : ""
      );

      this.$http
        .post("/m/assets/listAssetsStockDetailAccount.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            //유가증권평가손익 셋팅
            if (list[i].proloss > 0) {
              list[i].proloss = "+" + Common.formatNumber(list[i].proloss);
            } else {
              list[i].proloss = Common.formatNumber(list[i].proloss);
            }
          }

          //pagination
          if (list.length === 0) {
            if (_this.page == 1) {
              _this.accountList = [];
            }
            callback();
            _this.seen = true;
            return;
          }

          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.accountList = list;
          } else {
            for (var key in list) {
              _this.accountList.push(list[key]);
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
    //상세페이지 이동
    viewDetail: function(acc_type, acc_code) {
      var _this = this;

      this.$store.state.scListParam.query1 = _this.scAccType.value;
      this.$store.state.scListParam.query2 = _this.orderBy.value;

      console.log(acc_code);
      if ("shr" == acc_type) {
        this.$router.push({
          name: "assetsStockShrDetail",
          query: {
            no_person: _this.no_person,
            no_account: _this.no_account,
            acc_code: acc_code
          }
        });
      } else if ("fnd" == acc_type) {
        this.$router.push({
          name: "assetsStockFndDetail",
          query: {
            no_person: _this.no_person,
            no_account: _this.no_account,
            acc_code: acc_code
          }
        });
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
