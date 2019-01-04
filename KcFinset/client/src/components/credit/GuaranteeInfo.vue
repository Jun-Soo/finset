<template>
  <section v-if="seen">
    <div class="container">
      <div v-if="guaranteeList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="col-top">
        <div>
          <p class="key">보증건수</p>
          <p class="value">{{guaranteeCnt}}</p>
        </div>
        <div>
          <p class="key">보증금액</p>
          <p class="value">{{amtGuarantee}}</p>
        </div>
      </div>
    </div>

    <div v-if="guaranteeList.length != 0" class="box-list list01">
      <div class="item" v-for="guaranteeInfo in guaranteeList" :key="guaranteeInfo.index">
        <div class="top">
          <p class="symbol"><img :src="guaranteeInfo.fcImg" alt="" />{{guaranteeInfo.nm_fc}}</p>
        </div>
        <div class="text-wrap">
          <div class="left">
            <p class="key">보증금액</p>
            <p class="value">{{formatNumber(guaranteeInfo.amt_guar_object)}}</p>
          </div>
          <div class="right">
            <p class="key">보증약정일자</p>
            <p class="value">{{formatDateDot(guaranteeInfo.dtGuarAgree)}}</p>
          </div>
        </div>
      </div>

      <!--noti-->
      <div class="info-massage">연대보증 정보는 미해지 기준으로 제공되기 때문에 일부 연대보증 정보는 만기일이 지났어도 제공될 수 있습니다.</div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditGuranteeInfo",
  data() {
    return {
      errMsg: "",
      seen: false,
      guaranteeCnt: "", //연대보증건수
      amtGuarantee: "", //연대보증금액
      guaranteeList: [] //연대보증list
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "연대보증현황";
    this.getGuaranteeInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 연대보증내역 조회
    getGuaranteeInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditGuaranteeInfo.json", {
          params: {}
        })
        .then(response => {
          _this.guaranteeCnt = response.data.guaranteeCnt;
          _this.amtGuarantee = response.data.amtGuarantee;

          //금융사ICON 셋팅
          var list = response.data.guaranteeList;
          for (var i = 0; i < list.length; i++) {
            list[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
          }
          _this.guaranteeList = response.data.guaranteeList;
          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
