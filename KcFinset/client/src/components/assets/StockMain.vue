<template>
  <section v-if="seen">
    <div class="stock-number">
      <p class="key">나의 증권 계좌는</p>
      <p class="value">{{(sumAmt.sum_amt_stock==null)? '-' : formatNumber(Math.round(sumAmt.sum_amt_stock/10000))}}<em>만원</em></p>
    </div>

    <div class="box-list list01 noMG pb90">
      <div v-if="personShareList.length!=0" class="filter-wrap">
        <div class="filter red">
          <input type="checkbox" checked="checked" readonly="readonly"><label for="">{{this.$store.state.user.nmPerson}}</label>
        </div>
        <div v-for="personShareInfo in personShareList" :key="personShareInfo.index" class="filter" :class="colorList[personShareInfo.rk]">
          <input type="checkbox" v-model="person_share_list" :value="personShareInfo.no_person" :id="'chk'+personShareInfo.rk" @change="searchAccountList();">
          <label :for="'chk'+personShareInfo.rk">{{personShareInfo.nm_person}}</label>
        </div>
      </div>

      <div class="align-item mb20">
        <em class="mr10">정렬하기</em><button @click="setOrder();" class="btn-onoff" :class="{on: isSetOrder}" :disabled="accountList.length==0"></button>
      </div>

      <template v-if="isSetOrder">
        <div v-if="accountMyList.length == 0 && accountShareList.length == 0" class="nodata">계좌 내역이 없습니다</div>

        <draggable v-model="accountMyList" @start="drag=true" :options="draggableOptions" @update="changeSort()">
          <div v-for="accountMyInfo in accountMyList" :key="accountMyInfo.index" class="item sortClass">
            <a @click="viewDetail(accountMyInfo.no_person, accountMyInfo.no_account, accountMyInfo.nm_detail_class)" class="block handle">
              <div class="top">
                <p class="symbol"><img :src="accountMyInfo.fcImg" alt="" />{{accountMyInfo.nm_fc}}</p>
                <p v-if="personShareList.length!=0" class="text"><span class="circle" :class="colorList[accountMyInfo.rk]">{{accountMyInfo.nm_person}}</span></p>
              </div>
              <div class="number-wrap bi">
                <div class="left">
                  <p class="number">{{formatNumber(accountMyInfo.amt_stock)}}<em>원</em></p>
                </div>
                <div class="right">
                  <p class="value blue">{{accountMyInfo.proloss}} ({{accountMyInfo.rate_return}}%)</p>
                </div>
              </div>
              <div class="goods-cate nor">
                <em>{{accountMyInfo.nm_detail_class}}</em>
                <em>{{accountMyInfo.no_account}}</em>
              </div>
            </a>
          </div>
        </draggable>

        <div v-for="accountShareInfo in accountShareList" :key="accountShareInfo.index" class="item">
          <a @click="viewDetail(accountShareInfo.no_person, accountShareInfo.no_account, accountShareInfo.nm_detail_class)" class="block">
            <div class="top">
              <p class="symbol"><img :src="accountShareInfo.fcImg" alt="" />{{accountShareInfo.nm_fc}}</p>
              <p class="text"><span class="circle" :class="colorList[accountShareInfo.rk]">{{accountShareInfo.nm_person}}</span></p>
            </div>
            <div class="number-wrap bi">
              <div class="left">
                <p class="number">{{formatNumber(accountShareInfo.amt_stock)}}<em>원</em></p>
              </div>
              <div class="right">
                <p class="value blue">{{accountShareInfo.proloss}} ({{accountShareInfo.rate_return}}%)</p>
              </div>
            </div>
            <div class="goods-cate nor">
              <em>{{accountShareInfo.nm_detail_class}}</em>
              <em>{{accountShareInfo.no_account}}</em>
            </div>
          </a>
        </div>
      </template>
      <template v-else>
        <div v-if="accountList.length == 0" class="nodata">계좌 내역이 없습니다</div>
        <div v-for="accountInfo in accountList" :key="accountInfo.index" class="item">
          <a @click="viewDetail(accountInfo.no_person, accountInfo.no_account, accountInfo.nm_detail_class)" class="block">
            <div class="top">
              <p class="symbol"><img :src="accountInfo.fcImg" alt="" />{{accountInfo.nm_fc}}</p>
              <p v-if="personShareList.length!=0" class="text"><span class="circle" :class="colorList[accountInfo.rk]">{{accountInfo.nm_person}}</span></p>
            </div>
            <div class="number-wrap bi">
              <div class="left">
                <p class="number">{{formatNumber(accountInfo.amt_stock)}}<em>원</em></p>
              </div>
              <div class="right">
                <p class="value blue">{{accountInfo.proloss}} ({{accountInfo.rate_return}}%)</p>
              </div>
            </div>
            <div class="goods-cate nor">
              <em>{{accountInfo.nm_detail_class}}</em>
              <em>{{accountInfo.no_account}}</em>
            </div>
          </a>
        </div>
      </template>
    </div>

    <div class="btn-wrap float">
      <a @click="viewBalc();" class="blue box solid">계좌 통합 보기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

import draggable from "vuedraggable";

export default {
  name: "assetsStockMain",
  data() {
    return {
      seen: false,
      draggableOptions: {
        handle: ".handle",
        touchStartThreshold: 200
      },
      sumAmt: "", //총금액
      isSetOrder: false, //정렬셋팅OnOff
      colorList: ["red", "orange", "green", "blue", "purple"],
      personShareList: [], //공유자list
      person_share_list: [], //공유자list(조회용)
      accountMyList: [], //계좌list(my)
      accountShareList: [], //계좌list(share)
      accountList: [], //계좌list(통합)
      totalPage: "",
      page: 1
    };
  },
  components: {
    draggable
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "증권";
  },
  created() {
    this.getAssetsStockMainInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 자산 증권메인 정보조회
    getAssetsStockMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsStockMainInfo.json", {
          params: {}
        })
        .then(response => {
          _this.sumAmt = response.data.sumAmt;

          _this.personShareList = response.data.personShareList;
          for (var idx in _this.personShareList) {
            _this.person_share_list.push(_this.personShareList[idx].no_person);
          }

          _this.searchAccountList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
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
      console.log("person_share_list" + _this.person_share_list);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("person_share_list", _this.person_share_list);

      this.$http
        .post("/m/assets/listAssetsStockMainAccount.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            //금융사 ICON 셋팅
            list[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            //유가증권평가손익 셋팅
            if (list[i].proloss > 0) {
              list[i].proloss = "+" + Common.formatNumber(list[i].proloss);
            } else {
              list[i].proloss = Common.formatNumber(list[i].proloss);
            }
          }

          //pagination
          if (_this.page == 1) {
            _this.accountMyList = [];
            _this.accountShareList = [];
            _this.accountList = [];
          }

          if (list.length === 0) {
            callback();
            _this.seen = true;
            return;
          }

          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            for (var key in list) {
              if ("0" == list[key].rk) {
                _this.accountMyList.push(list[key]);
              } else {
                _this.accountShareList.push(list[key]);
              }
            }

            _this.accountList = list;
          } else {
            for (var key in list) {
              if ("0" == list[key].rk) {
                _this.accountMyList.push(list[key]);
              } else {
                _this.accountShareList.push(list[key]);
              }

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
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    //정렬하기 설정
    setOrder: function() {
      var _this = this;
      _this.isSetOrder = !_this.isSetOrder;
      if (!_this.isSetOrder) _this.searchAccountList();
    },
    //정렬순서 변경
    changeSort: function() {
      var _this = this;
      var accountMyList = _this.accountMyList;
      var formData = new FormData();
      for (var idx in accountMyList) {
        formData.append(
          "sortList[" + idx + "].no_account",
          accountMyList[idx].no_account
        );
        formData.append("sortList[" + idx + "].sort", parseInt(idx) + 1);
      }
      this.$http
        .post("/m/assets/updateAssetsSortInfo.json", formData)
        .then(function(response) {});
    },
    //상세보기
    viewDetail: function(no_person, no_account, nm_detail_class) {
      var _this = this;
      this.$router.push({
        name: "assetsStockActDetail",
        query: {
          no_person: no_person,
          no_account: no_account,
          nm_detail_class: nm_detail_class
        }
      });
    },
    //계좌통합보기(전체잔고상세페이지로 이동)
    viewBalc: function() {
      var _this = this;
      var person_share_list = [];
      for (var idx in _this.personShareList) {
        person_share_list.push(_this.personShareList[idx].no_person);
      }

      this.$router.push({
        name: "assetsStockBalcDetail",
        params: {
          person_share_list: person_share_list
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
