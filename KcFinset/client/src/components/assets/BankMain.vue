<template>
  <section v-if="seen">
    <div class="assets-bank">
      <p class="key">나의 은행 예금은</p>
      <p class="value"><em>{{(sumAmt.sum_amt_balance==null)? '-' : formatNumber(Math.round(sumAmt.sum_amt_balance/10000))}}</em>만원</p>
    </div>

    <div v-if="depWdrlInfo != null" @click="viewDetail('dwl')" class="assets-bank-recent">
      <p>{{depWdrlInfo.dt_trd}}<em>{{depWdrlInfo.doc1}}</em></p>
      <p v-if="'0'!=depWdrlInfo.amt_dep"><em class="number blue">{{formatNumber(depWdrlInfo.amt_dep)}}</em>원</p>
      <p v-else><em class="number red">{{('-'+formatNumber(depWdrlInfo.amt_wdrl))}}</em>원</p>
    </div>

    <div class="box-list list01 noMG pb90">
      <div class="align-item">
        <em class="mr10">정렬하기</em><button @click="setOrder()" class="btn-onoff" :class="{on: isSetOrder}" :disabled="accountList.length==0"></button>
      </div>

      <div v-if="personShareList.length!=0" class="filter-wrap">
        <div class="filter red">
          <input type="checkbox" checked="checked" readonly="readonly"><label for="">{{this.$store.state.user.nmPerson}}</label>
        </div>
        <div v-for="personShareInfo in personShareList" :key="personShareInfo.index" class="filter" :class="colorList[personShareInfo.rk]">
          <input type="checkbox" v-model="person_share_list" :value="personShareInfo.no_person" :id="'chk'+personShareInfo.rk" @change="searchAccountList();">
          <label :for="'chk'+personShareInfo.rk">{{personShareInfo.nm_person}}</label>
        </div>
      </div>

      <template v-if="isSetOrder">
        <div v-if="accountMyList.length == 0 && accountShareList.length == 0" class="nodata">계좌 내역이 없습니다</div>
        <draggable v-model="accountMyList" @start="drag=true" :options="draggableOptions" @update="changeSort()">
          <div v-for="accountMyInfo in accountMyList" :key="accountMyInfo.index" class="item sortClass">
            <a @click="viewDetail('act', accountMyInfo.no_person, accountMyInfo.nm_person, accountMyInfo.no_account, accountMyInfo.cd_detail_class, accountMyInfo.rk)" class="block handle">
              <div class="top">
                <p class="symbol"><img :src="accountMyInfo.fcImg" alt="" />{{accountMyInfo.nm_fc}}</p>
                <p class="text">
                  <em class="blue bold">{{getCodeName('cd_assets_bank',accountMyInfo.cd_detail_class)}}</em>
                  <span v-if="personShareList.length!=0" class="circle" :class="colorList[accountMyInfo.rk]">{{accountMyInfo.nm_person}}</span>
                </p>
              </div>
              <div class="text-wrap">
                <div class="left" style="width:70%">
                  <p class="key">{{accountMyInfo.nm_account}}</p>
                  <p class="key">{{accountMyInfo.no_account}}</p>
                </div>
                <div class="right">
                  <p class="value noMG"><em class="number">{{formatNumber(accountMyInfo.amt_balance)}}</em>원</p>
                </div>
              </div>
            </a>
          </div>
        </draggable>

        <div v-for="accountShareInfo in accountShareList" :key="accountShareInfo.index" class="item">
          <a @click="viewDetail('act', accountShareInfo.no_person, accountShareInfo.nm_person, accountShareInfo.no_account, accountShareInfo.cd_detail_class, accountShareInfo.rk)" class="block">
            <div class="top">
              <p class="symbol"><img :src="accountShareInfo.fcImg" alt="" />{{accountShareInfo.nm_fc}}</p>
              <p class="text">
                <em class="blue bold">{{getCodeName('cd_assets_bank',accountShareInfo.cd_detail_class)}}</em>
                <span class="circle" :class="colorList[accountShareInfo.rk]">{{accountShareInfo.nm_person}}</span>
              </p>
            </div>
            <div class="text-wrap">
              <div class="left" style="width:70%">
                <p class="key">{{accountShareInfo.nm_account}}</p>
                <p class="key">{{accountShareInfo.no_account}}</p>
              </div>
              <div class="right">
                <p class="value noMG"><em class="number">{{formatNumber(accountShareInfo.amt_balance)}}</em>원</p>
              </div>
            </div>
          </a>
        </div>
      </template>
      <template v-else>
        <div v-if="accountList.length == 0" class="nodata">계좌 내역이 없습니다</div>
        <div v-for="accountInfo in accountList" :key="accountInfo.index" class="item">
          <a @click="viewDetail('act', accountInfo.no_person, accountInfo.nm_person, accountInfo.no_account, accountInfo.cd_detail_class, accountInfo.rk)" class="block">
            <div class="top">
              <p class="symbol"><img :src="accountInfo.fcImg" alt="" />{{accountInfo.nm_fc}}</p>
              <p class="text">
                <em class="blue bold">{{getCodeName('cd_assets_bank',accountInfo.cd_detail_class)}}</em>
                <span v-if="personShareList.length!=0" class="circle" :class="colorList[accountInfo.rk]">{{accountInfo.nm_person}}</span>
              </p>
            </div>
            <div class="text-wrap">
              <div class="left" style="width:70%">
                <p class="key">{{accountInfo.nm_account}}</p>
                <p class="key">{{accountInfo.no_account}}</p>
              </div>
              <div class="right">
                <p class="value noMG"><em class="number">{{formatNumber(accountInfo.amt_balance)}}</em>원</p>
              </div>
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

import draggable from "vuedraggable";

export default {
  name: "assetsBankMain",
  data() {
    return {
      seen: false,
      draggableOptions: {
        handle: ".handle",
        touchStartThreshold: 200
      },
      sumAmt: "", //총금액
      depWdrlInfo: "", //최근 입출금내역
      isSetOrder: false, //정렬셋팅OnOff
      colorList: ["red", "orange", "green", "blue", "purple"],
      personShareList: [], //공유자list
      person_share_list: [], //공유자list(조회용)
      accountMyList: [], //계좌list(My)
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
    this.$store.state.title = "은행";
  },
  created() {
    this.getAssetsBankMainInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 자산 은행메인 정보조회
    getAssetsBankMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsBankMainInfo.json", {
          params: {}
        })
        .then(response => {
          _this.sumAmt = response.data.sumAmt;
          _this.depWdrlInfo = response.data.depWdrlInfo;

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
        .post("/m/assets/listAssetsBankMainAccount.json", formData)
        .then(function(response) {
          //금융사 ICON 셋팅
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            list[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
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
    getCodeName: function(code_group, code_value) {
      return Common.getCodeName(code_group, code_value);
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
    //상세페이지로 이동
    viewDetail: function(
      menu,
      no_person,
      nm_person,
      no_account,
      cd_detail_class,
      colorIndex
    ) {
      var _this = this;

      var yn_share = "";
      if (_this.personShareList.length != 0) {
        yn_share = "Y";
      } else {
        yn_share = "N";
      }

      if ("act" == menu) {
        //입출금, 예금/적금인 경우
        if (
          cd_detail_class == "01" ||
          cd_detail_class == "02" ||
          cd_detail_class == "03"
        ) {
          this.$router.push({
            name: "assetsBankActDetail",
            query: {
              no_person: no_person,
              nm_person: nm_person,
              no_account: no_account,
              yn_share: yn_share,
              colorIndex: colorIndex
            }
          });
        }
      } else if ("dwl" == menu) {
        //전체입출금내역
        var person_share_list = [];
        for (var idx in _this.personShareList) {
          person_share_list.push(_this.personShareList[idx].no_person);
        }

        this.$router.push({
          name: "assetsBankDepWdrlList",
          query: {
            yn_share: yn_share,
            person_share_list: person_share_list
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
