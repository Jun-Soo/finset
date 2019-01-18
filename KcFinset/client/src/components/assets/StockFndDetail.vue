<template>
  <section v-if="seen">
    <div class="balance-top">
      <div class="item">
        <div class="top name">{{fndInfo.fundname}}</div>
        <div class="body">
          <div class="flex">
            <p>평가금액</p>
            <p class="number"><em>{{formatNumber(fndInfo.valatcur)}}</em> 원</p>
          </div>
          <div class="flex">
            <p>평가손익</p>
            <p class="red">{{formatNumber(fndInfo.proloss)}} ({{fndInfo.earningrate}}%)</p>
          </div>
          <div class="flex">
            <p>보유주수</p>
            <p>-주</p>
          </div>
          <div class="flex">
            <p>매수금액</p>
            <p>{{formatNumber(fndInfo.valattrade)}} 원</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsStockFndDetail",
  data() {
    return {
      seen: false,
      no_person: "",
      no_account: "",
      acc_code: "",
      fndInfo: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "펀드 상세";
  },
  created() {
    this.no_person = this.$route.query.no_person;
    this.no_account = this.$route.query.no_account;
    this.acc_code = this.$route.query.acc_code;

    this.getAssetsStockFndInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //펀드정보조회
    getAssetsStockFndInfo: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);
      formData.append("acc_code", _this.acc_code);

      this.$http
        .post("/m/assets/getAssetsStockFndInfo.json", formData)
        .then(function(response) {
          if ("00" != response.data.cdResult) {
            _this.$toast.center("권한이 없습니다");
            return false;
          }

          var fndInfo = response.data.fndInfo;
          if (fndInfo.proloss > 0) {
            fndInfo.proloss = "+" + fndInfo.proloss;
          }
          _this.fndInfo = fndInfo;
          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
