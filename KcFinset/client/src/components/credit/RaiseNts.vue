<template>
  <section v-if="seen">
    <div class="credit-inquiry-top">
      <p class="key">{{name}}님의</p>
      <p class="title">{{year}}년도 소득금액은<br><em>{{formatNumber(income_div)}}만원</em> 입니다</p>
      <p class="text">해당정보를 신용평가사로 전송하시겠습니까?</p>
    </div>

    <div class="box-list list02">
      <div class="header">납부내역</div>
      <div class="item">
        <div class="flex">
          <p><strong>{{income_division}}</strong></p>
          <p class="corp">{{corp_name}}</p>
        </div>
        <div class="flex">
          <p>신고금액</p>
          <p class="number">{{formatNumber(income)}}<em>원</em></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap float" @click="clickSend()">
      <a class="solid box blue">전송하기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "CreditRaiseNts",
  data() {
    return {
      seen: false,
      name: this.$store.state.user.nmPerson,
      year: "",
      income_div: "",
      income: "",
      income_division: "",
      corp_name: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "납부 확인 및 전송하기";
  },
  created() {
    this.getCreditRaiseNts();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getCreditRaiseNts: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditRaiseNts.json", {
          params: {}
        })
        .then(response => {
          var result = response.data;
          if (result != null) {
            _this.year = result.year;
            _this.income_div = result.income_div;
            _this.income = result.income;
            _this.income_division = result.income_division;
            _this.corp_name = result.corp_name;

            _this.seen = true;
          } else {
            this.$toast.center(ko.messages.error);
          }
        });
    },
    clickSend: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("scrap_code", "nts");

      this.$http
        .post("/m/kcb/updateKcbReqNonfiInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          _this.$router.push({
            name: "creditRaiseResult",
            params: { result: "success" }
          });
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
