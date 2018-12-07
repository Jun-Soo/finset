<template>
  <div v-if="seen">
    <section>
      <div class="assets-bank-detail-top">
        <div class="item">
          <div class="top">
            <p class="symbol"><img :src="assetsInfo.fcImg" alt="" />{{assetsInfo.nm_fc}}</p>
            <p class="text">
              <em class="blue bold">{{assetsInfo.nm_account}}({{getCodeName('cd_assets_bank',assetsInfo.cd_detail_class)}})</em>
              <span class="circle" :class="colorList[colorIndex]">{{nm_person}}</span>
            </p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">{{assetsInfo.no_account}}</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">{{formatNumber(assetsInfo.amt_balance)}}</em>원</p>
            </div>
          </div>
        </div>
      </div>

      <div class="bank-detail">
        <div class="select">
          <div class="left">
            <multiselect v-model="scTrnsType" ref="scTrnsType" placeholder="유형선택" track-by="text" label="text" :options="scTrnsTypeOptions" :searchable="false" :allow-empty="false" @select="onSelectTrns" :alignLeft="true">
            </multiselect>
          </div>
          <div class="right">
            <span>{{scKeyword}}</span>
            <button class="btn-search" @click="openScKeywordMd();"></button>
          </div>
        </div>
        <div v-if="trnsList.length == 0" class="nodata">등록 내역이 없습니다</div>
        <div v-else class="nobox-list">
          <template v-for="trnsInfo in trnsList">
            <p :key="trnsInfo.index" v-if="trnsInfo.dateCol" class="date">{{formatDateDot(trnsInfo.dt_trd)}}</p>
            <div :key="trnsInfo.index" class="item">
              <div class="flex">
                <p><em>{{trnsInfo.doc1}}</em></p>
                <p v-if="'0'!=trnsInfo.amt_dep"><em class="number blue">{{formatNumber(trnsInfo.amt_dep)}}</em>원</p>
                <p v-else><em class="number red">{{('-'+formatNumber(trnsInfo.amt_wdrl))}}</em>원</p>
              </div>
              <div class="flex">
                <p class="key">{{formatNumber(trnsInfo.balance)}}원</p>
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

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsBankActDetail",
  data() {
    return {
      seen: false,
      isShowScKeyword: false, //검색키워드 modal 보여주기
      scKeywordList: [],
      scKeyword: "",
      scTrnsTypeOptions: [
        { text: "전체", value: "" },
        { text: "입금", value: "01" },
        { text: "출금", value: "02" }
      ],
      scTrnsType: "",
      no_person: "", //회원번호
      nm_person: "", //회원명
      no_account: "", //계좌번호
      colorList: ["red", "orange", "green", "blue", "purple"],
      colorIndex: "",
      assetsInfo: "",
      page: 1,
      trnsList: [] //입출금list
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계좌 상세";
  },
  created() {
    this.no_person = this.$route.params.no_person;
    this.nm_person = this.$route.params.nm_person;
    this.no_account = this.$route.params.no_account;
    this.colorIndex = this.$route.params.colorIndex;

    this.getAssetsBankActDetail();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //계좌정보 조회
    getAssetsBankActDetail: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", this.$route.params.no_person);
      formData.append("no_account", this.$route.params.no_account);

      this.$http
        .post("/m/assets/getAssetsBankActDetail.json", formData)
        .then(response => {
          var assetsInfo = response.data.assetsInfo;
          assetsInfo.fcImg =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + assetsInfo.cd_fc;
          _this.assetsInfo = assetsInfo;

          _this.scKeywordList = response.data.scKeywordList;

          _this.searchActTrnsList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    getCodeName: function(code_group, code_value) {
      return Common.getCodeName(code_group, code_value);
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //multiselect
    onSelectTrns: function(option) {
      var _this = this;
      _this.scTrnsType = option;
      console.log(option);
      _this.searchActTrnsList();
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
      _this.searchActTrnsList();
    },
    //입출금 내역 조회
    searchActTrnsList: function() {
      var _this = this;
      _this.page = 1;
      _this.trnsList = [];
      Common.pagination(_this.listActTrns);
    },
    listActTrns: function(callback) {
      var _this = this;

      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);
      formData.append(
        "scTrnsType ",
        _this.scTrnsType != "" ? _this.scTrnsType.value : ""
      );
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/assets/listAssetsBankActTrns.json", formData)
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
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.trnsList = list;
          } else {
            for (var key in list) {
              _this.trnsList.push(list[key]);
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
<template>
  <div v-if="seen">
    <section>
      <div class="assets-bank-detail-top">
        <div class="item">
          <div class="top">
            <p class="symbol"><img :src="assetsInfo.fcImg" alt="" />{{assetsInfo.nm_fc}}</p>
            <p class="text">
              <em class="blue bold">{{assetsInfo.nm_account}}({{getCodeName('cd_assets_bank',assetsInfo.cd_detail_class)}})</em>
              <span class="circle" :class="colorList[colorIndex]">{{nm_person}}</span>
            </p>
          </div>
          <div class="text-wrap">
            <div class="left">
              <p class="key">{{assetsInfo.no_account}}</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">{{formatNumber(assetsInfo.amt_balance)}}</em>원</p>
            </div>
          </div>
        </div>
      </div>

      <div class="bank-detail">
        <div class="select">
          <div class="left">
            <multiselect v-model="scTrnsType" ref="scTrnsType" placeholder="유형선택" track-by="text" label="text" :options="scTrnsTypeOptions" :searchable="false" :allow-empty="false" @select="onSelectTrns" :alignLeft="true">
            </multiselect>
          </div>
          <div class="right">
            <span>{{scKeyword}}</span>
            <button class="btn-search" @click="openScKeywordMd();"></button>
          </div>
        </div>
        <div v-if="trnsList.length == 0" class="nodata">등록 내역이 없습니다</div>
        <div v-else class="nobox-list">
          <template v-for="trnsInfo in trnsList">
            <p :key="trnsInfo.index" v-if="trnsInfo.dateCol" class="date">{{formatDateDot(trnsInfo.dt_trd)}}</p>
            <div :key="trnsInfo.index" class="item">
              <div class="flex">
                <p><em>{{trnsInfo.doc1}}</em></p>
                <p v-if="'0'!=trnsInfo.amt_dep"><em class="number blue">{{formatNumber(trnsInfo.amt_dep)}}</em>원</p>
                <p v-else><em class="number red">{{('-'+formatNumber(trnsInfo.amt_wdrl))}}</em>원</p>
              </div>
              <div class="flex">
                <p class="key">{{formatNumber(trnsInfo.balance)}}원</p>
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

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsBankActDetail",
  data() {
    return {
      seen: false,
      isShowScKeyword: false, //검색키워드 modal 보여주기
      scKeywordList: [],
      scKeyword: "",
      scTrnsTypeOptions: [
        { text: "전체", value: "" },
        { text: "입금", value: "01" },
        { text: "출금", value: "02" }
      ],
      scTrnsType: "",
      no_person: "", //회원번호
      nm_person: "", //회원명
      no_account: "", //계좌번호
      colorList: ["red", "orange", "green", "blue", "purple"],
      colorIndex: "",
      assetsInfo: "",
      page: 1,
      trnsList: [] //입출금list
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계좌 상세";
  },
  created() {
    this.no_person = this.$route.params.no_person;
    this.nm_person = this.$route.params.nm_person;
    this.no_account = this.$route.params.no_account;
    this.colorIndex = this.$route.params.colorIndex;

    this.getAssetsBankActDetail();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //계좌정보 조회
    getAssetsBankActDetail: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", this.$route.params.no_person);
      formData.append("no_account", this.$route.params.no_account);

      this.$http
        .post("/m/assets/getAssetsBankActDetail.json", formData)
        .then(response => {
          var assetsInfo = response.data.assetsInfo;
          assetsInfo.fcImg =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + assetsInfo.cd_fc;
          _this.assetsInfo = assetsInfo;

          _this.scKeywordList = response.data.scKeywordList;

          _this.searchActTrnsList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    getCodeName: function(code_group, code_value) {
      return Common.getCodeName(code_group, code_value);
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //multiselect
    onSelectTrns: function(option) {
      var _this = this;
      _this.scTrnsType = option;
      console.log(option);
      _this.searchActTrnsList();
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
      _this.searchActTrnsList();
    },
    //입출금 내역 조회
    searchActTrnsList: function() {
      var _this = this;
      _this.page = 1;
      _this.trnsList = [];
      Common.pagination(_this.listActTrns);
    },
    listActTrns: function(callback) {
      var _this = this;

      console.log("scTrnsType" + _this.scTrnsType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);
      formData.append(
        "scTrnsType ",
        _this.scTrnsType != "" ? _this.scTrnsType.value : ""
      );
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/assets/listAssetsBankActTrns.json", formData)
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
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.trnsList = list;
          } else {
            for (var key in list) {
              _this.trnsList.push(list[key]);
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
