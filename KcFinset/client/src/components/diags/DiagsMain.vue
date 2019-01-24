<template>
  <section>


  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsDiagsMain",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "포트폴리오진단";
  },
  created() {
    this.getDiagsResult();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDiagsResult: function() {
      var _this = this;

      this.$http
        .get("/m/diags/getDiagsResult.json", {
          params: {}
        })
        .then(response => {
          var diagsResult = response.data.diagsResult;
          if (diagsResult.stocks < 1) {
            this.$router.push("/assets/diagsMyStock");
          } else if (diagsResult.surveys < 1) {
            this.$router.push("/assets/investSurvey");
          } else if (
            diagsResult.stockGoals < 1 ||
            diagsResult.stockGoals < diagsResult.stocks
          ) {
            this.$router.push("/assets/stockGoal");
          } else {
            this.$router.push("/assets/diagsReport");
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
