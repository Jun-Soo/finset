<template>
  <section v-if="seen">
    <div class="balance-top">
      <div class="item">
        <div class="top">{{formatNumber(stockSumInfo.amt_stock)}}<em>원</em></div>
        <div class="body">
          <div class="flex">
            <p>출금가능액</p>
            <p>{{formatNumber(stockSumInfo.cashavwithdraw)}}</p>
          </div>
          <div class="flex">
            <p>대용금</p>
            <p>{{formatNumber(stockSumInfo.subsmargin)}}</p>
          </div>
          <div class="flex">
            <p>매수/미납금</p>
            <p>{{formatNumber(stockSumInfo.receivable)}}</p>
          </div>
          <div class="flex">
            <p>대출/신용금</p>
            <p>{{formatNumber(stockSumInfo.loancredit)}}</p>
          </div>
          <div class="flex">
            <p>유가증권평가금액</p>
            <p>{{formatNumber(stockSumInfo.valueatcur)}} {{(stockSumInfo.proloss!='0')?'('+stockSumInfo.proloss+')':''}}</p>
          </div>
        </div>
      </div>
    </div>

    <div class="box-list list02">

      <div class="select pb20">
        <multiselect v-model="scAccType" ref="scAccType" placeholder="분류선택" track-by="text" label="text" :options="scAccTypeOptions" :searchable="false" :allow-empty="false" @select="onSelectAcc">
        </multiselect>
        <multiselect v-model="scCompany" ref="scCompany" placeholder="증권사선택" track-by="text" label="text" :options="scCompanyOptions" :searchable="false" :allow-empty="false" @select="onSelectComp">
        </multiselect>
        <multiselect v-model="orderBy" ref="orderBy" placeholder="정렬선택" track-by="text" label="text" :options="orderByOptions" :searchable="false" :allow-empty="false" @select="onSelectOrder">
        </multiselect>
      </div>
      <template v-if="accountList.length == 0" class="nodata">등록 내역이 없습니다</template>
      <template v-for="accountInfo in accountList">
        <div :key="accountInfo.index" class="item">
          <a class="block">
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
  name: "assetsStockBalcDetail",
  data() {
    return {
      seen: false,
      person_share_list: [], //공유자list
      stockSumInfo: "",
      scAccTypeOptions: [
        { text: "전체", value: "" },
        { text: "주식", value: "shr" },
        { text: "펀드", value: "fnd" }
      ],
      scAccType: "",
      scCompanyOptions: [],
      scCompany: "",
      orderByOptions: [
        { text: "선택", value: "" },
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
    this.$store.state.title = "전체 잔고상세";
  },
  created() {
    this.person_share_list = this.$route.params.person_share_list;

    this.getAssetsStockSumInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //전체잔고정보조회
    getAssetsStockSumInfo: function() {
      var _this = this;

      console.log("person_share_list", _this.person_share_list);

      var formData = new FormData();
      formData.append("person_share_list", _this.person_share_list);

      this.$http
        .post("/m/assets/getAssetsStockSumInfo.json", formData)
        .then(function(response) {
          //금액 합산정보
          var stockSumInfo = response.data.stockSumInfo;
          //유가증권평가손익 셋팅
          if (stockSumInfo.proloss > 0) {
            stockSumInfo.proloss =
              "+" + Common.formatNumber(stockSumInfo.proloss);
          } else {
            stockSumInfo.proloss = Common.formatNumber(stockSumInfo.proloss);
          }
          _this.stockSumInfo = stockSumInfo;

          //증권사list(검색용)
          var scCompanyList = response.data.scCompanyList;
          _this.scCompanyOptions.push({
            text: "전체",
            value: ""
          });
          for (var i = 0; i < scCompanyList.length; i++) {
            _this.scCompanyOptions.push({
              text: scCompanyList[i].nm_fc,
              value: scCompanyList[i].cd_fc
            });
          }

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
    onSelectComp: function(option) {
      var _this = this;
      _this.scCompany = option;
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
      _this.accountList = [];
      Common.pagination(_this.listAccount);
    },
    listAccount: function(callback) {
      var _this = this;

      console.log("page" + _this.page);
      console.log("person_share_list" + _this.person_share_list);
      console.log("scAccType" + _this.scAccType);
      console.log("scCompany" + _this.scCompany);
      console.log("orderBy" + _this.orderBy);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("person_share_list", _this.person_share_list);
      formData.append(
        "scAccType",
        _this.scAccType != "" ? _this.scAccType.value : ""
      );
      formData.append(
        "scCompany",
        _this.scCompany != "" ? _this.scCompany.value : ""
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
