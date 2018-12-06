<template>
  <div class="loading">
    <span>선택하신 상품의 금리/한도를<br>조회하고 있습니다.</span><br><small>(최대 5분 소요)</small>
  </div>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "";
  },
  created() {
    this.reqFinanceInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    reqFinanceInfo: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_bunch", _this.no_bunch);
      formData.append("cd_fc", _this.cd_fc);
      formData.append("cd_goods", _this.cd_goods);
      this.$http
        .post("/m/loan/reqFinanceInfo.json", formData, {
          headers: {
            async: true,
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {
          // var result = response.data;
          // var isSuccess = result.isSuccess;
          // var message = result.message;
          // console.log(result);
          // if (isSuccess == "false" || message != "") {
          //   _this.$toast.center(message);
          // } else {
          //   _this.$toast.center("한도조회 신청하였습니다.");
          // }
          // setTimeout(function() {
          //   _this.$router.push("/goods/list");
          // }, 100);
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
      _this.$toast.center("한도조회 신청하였습니다.");
      setTimeout(function() {
        _this.$router.push("/goods/list");
      }, 100);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
