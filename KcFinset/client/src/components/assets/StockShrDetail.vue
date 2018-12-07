<template>
  <section v-if="seen">
    <div class="balance-top">
      <div class="item">
        <div class="top name">{{shrInfo.isincode_nm}}</div>
        <div class="body">
          <div class="flex">
            <p>평가금액</p>
            <p class="number"><em>{{formatNumber(shrInfo.valatcur)}}</em> 원</p>
          </div>
          <div class="flex">
            <p>평가손익</p>
            <p class="blue">{{formatNumber(shrInfo.proloss)}} ({{shrInfo.earningrate}}%)</p>
          </div>
          <div class="flex">
            <p>보유주수</p>
            <p>{{shrInfo.qty}}주</p>
          </div>
          <div class="flex">
            <p>매입금액</p>
            <p>{{formatNumber(shrInfo.valattrade)}} 원</p>
          </div>
          <div class="flex">
            <p>평균매입단가</p>
            <p>{{formatNumber(shrInfo.avg_valattrade)}} 원</p>
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
  name: "assetsStockShrDetail",
  data() {
    return {
      seen: false,
      no_person: "",
      no_account: "",
      acc_code: "",
      shrInfo: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "주식 상세";
  },
  created() {
    this.no_person = this.$route.query.no_person;
    this.no_account = this.$route.query.no_account;
    this.acc_code = this.$route.query.acc_code;

    this.getAssetsStockShrInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //주식정보조회
    getAssetsStockShrInfo: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("no_person", _this.no_person);
      formData.append("no_account", _this.no_account);
      formData.append("acc_code", _this.acc_code);

      this.$http
        .post("/m/assets/getAssetsStockShrInfo.json", formData)
        .then(function(response) {
          var shrInfo = response.data.shrInfo;
          if (shrInfo.proloss > 0) {
            shrInfo.proloss = "+" + shrInfo.proloss;
          }
          _this.shrInfo = shrInfo;
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
