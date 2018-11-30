<template>
  <section v-if="seen">
    <div class="assets-bank">
      <p class="key">나의 보유금액은</p>
      <p class="value"><em>{{(sumAmt.sum_amt_evaluation==null)? '-' : formatNumber(Math.round(sumAmt.sum_amt_evaluation/10000))}}</em>만원</p>
    </div>

    <div v-if="etcList.length == 0 && etcList.length == 0" class="nodata">내역이 없습니다</div>
    <div v-else class="box-list list01 noMG">
      <div v-for="etcInfo in etcList" :key="etcInfo.index" class="item">
        <a class="block">
          <div class="top">
            <p class="symbol">{{etcInfo.nm_code}}</p>
            <p class="text">
              <em class="blue bold">{{etcInfo.cnt_item}}건</em>
            </p>
          </div>
          <div class="text-wrap">
            <!-- <div class="left">
              <p class="key"></p>
            </div> -->
            <div class="right">
              <p class="value noMG"><em class="number">{{formatNumber(etcInfo.sum_amt_evaluation)}}</em>원</p>
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
      etcList: [] //계좌list
    };
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "기타";
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
          _this.etcList = response.data.etcList;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    getCodeName: function(code_group, code_value) {
      return Common.getCodeName(code_group, code_value);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
