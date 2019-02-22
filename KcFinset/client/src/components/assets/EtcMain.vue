<template>
  <section v-if="seen">
    <div class="assets-bank">
      <p class="key">나의 보유금액은</p>
      <p class="value"><em>{{(sumAmt.sum_amt_evaluation==null)? '-' : formatNumber(Math.round(sumAmt.sum_amt_evaluation/10000))}}</em>만원</p>
    </div>

    <div v-if="etcList.length == 0 && etcList.length == 0" class="nodata">내역이 없습니다</div>
    <div v-else class="box-list list01 noMG pb90">
      <div v-for="etcInfo in etcList" :key="etcInfo.index" class="item">
        <a @click="frmAssets(etcInfo.cd_assets_class, etcInfo.sort)" class="block">
          <div class="top">
            <p class="symbol">{{getCodeName('cd_assets_class',etcInfo.cd_assets_class)}}</p>
            <p class="text">
              <em v-if="etcInfo.cd_assets_class=='30'" class="blue bold">{{getCodeName('cd_assets_prop',etcInfo.cd_detail_class)}}</em>
              <em v-if="etcInfo.cd_assets_class=='40'" class="blue bold">{{etcInfo.nm_model}}</em>
              <em v-if="etcInfo.cd_assets_class=='50'" class="blue bold">{{getCodeName('cd_assets_nbmt',etcInfo.cd_detail_class)}}</em>
              <em v-if="etcInfo.cd_assets_class=='60'" class="blue bold">{{getCodeName('cd_assets_frcr',etcInfo.cd_detail_class)}}</em>
              <em v-if="etcInfo.cd_assets_class=='90'" class="blue bold">{{etcInfo.etc_assets}}</em>
            </p>
          </div>
          <div class="text-wrap">
            <div class="left" style="width:70%">
              <p v-if="etcInfo.cd_assets_class=='30'" class="key">{{etcInfo.real_estate_addr}}</p>
              <p v-else-if="etcInfo.cd_assets_class=='50'" class="key">{{etcInfo.amount_jewelry}}g</p>
              <p v-else-if="etcInfo.cd_assets_class=='60'" class="key"><em class="number">{{formatNumber(etcInfo.amt_balance)}}</em>원</p>
            </div>
            <div class="right">
              <p class="value noMG"><em class="number">{{formatNumber(etcInfo.amt_evaluation)}}</em>원</p>
            </div>
          </div>
        </a>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsEtcMain",
  data() {
    return {
      seen: false,
      sumAmt: "", //총금액
      etcList: [], //계좌list
      totalPage: "",
      page: 1
    };
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "기타";
    this.$store.state.header.backPath = "/assets/main";
  },
  created() {
    this.getAssetsEtcMainInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 자산 기타메인 정보조회
    getAssetsEtcMainInfo: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsEtcMainInfo.json", {
          params: {}
        })
        .then(response => {
          _this.sumAmt = response.data.sumAmt;
          _this.searchList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //자산 기타메인 list조회
    searchList: function() {
      var _this = this;
      _this.page = 1;
      Common.pagination(_this.listAssetsEtcMain);
    },
    listAssetsEtcMain: function(callback) {
      var _this = this;

      var formData = new FormData();
      formData.append("page", _this.page);

      this.$http
        .post("/m/assets/listAssetsEtcMain.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;

          //pagination
          if (list.length === 0) {
            if (_this.page == 1) {
              _this.etcList = [];
            }
            callback();
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.etcList = list;
          } else {
            for (var key in list) {
              _this.etcList.push(list[key]);
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
    frmAssets: function(cd_assets_class, sort) {
      this.$router.push({
        path: "/assets/dirInput",
        query: {
          cd_assets_class: cd_assets_class,
          sort: sort
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
