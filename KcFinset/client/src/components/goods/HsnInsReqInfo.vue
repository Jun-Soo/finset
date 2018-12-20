<template>
  <section>
    <div class="container">
      <p>가족(본인 및 세대원) 중에 주택담보대출이 있나요 ?</p>
      <div class="yesno">
        <a :class="ynLoan=='Y'?'solid':'stroke'" @click="clickIsLoan('Y')">예</a>
        <a :class="ynLoan=='N'?'solid':'stroke'" @click="clickIsLoan('N')">아니오</a>
      </div>
      <p class="mt30">서민/실수요자 인가요 ?</p>
      <p class="info-massage">무주택 세대주</p>
      <p class="info-massage">부부합산 연소득 6천만원(생애최초 구입자는 7천만원)이하</p>
      <div class="yesno">
        <a :class="ynIncome=='Y'?'solid':'stroke'" @click="clickChkIncome('Y')">예</a>
        <a :class="ynIncome=='N'?'solid':'stroke'" @click="clickChkIncome('N')">아니오</a>
      </div>
      <div class="btn-wrap float">
        <a class="solid blue box" @click="clickNext()">다음</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person,
      ynLoan: "",
      ynIncome: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청인 조건";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickIsLoan: function(flag) {
      this.ynLoan = flag;
    },
    clickChkIncome: function(flag) {
      this.ynIncome = flag;
    },
    clickNext: function() {
      if (this.ynLoan == "") {
        this.$toast.center("주택담보대출여부를 선택해주세요.");
        return;
      }
      if (this.ynIncome == "") {
        this.$toast.center("서민/실수요자여부를 선택해주세요.");
        return;
      }
      this.updateTxFc();
    },
    updateTxFc: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("cd_fc", this.cd_fc);
      formData.append("cd_goods", this.cd_goods);
      formData.append("no_bunch", this.no_bunch);
      formData.append("yn_loan_already", this.ynLoan);
      formData.append("yn_user_end", this.ynIncome);
      this.$http
        .post("/m/loanhomemortgage/modifyLoanREConditionInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result == "00") {
            _this.$router.push({
              name: "GoodsHsnInsHsnInfo",
              params: {
                cd_fc: _this.cd_fc,
                cd_goods: _this.cd_goods,
                no_bunch: _this.no_bunch,
                kcb_di: _this.kcb_di,
                ssn_person: _this.ssn_person
              }
            });
          }
        });
      // .catch(e => {
      //   this.$toast.center(ko.messages.error);
      // });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
