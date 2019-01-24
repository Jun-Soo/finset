<template>
  <section>
    <div class="portfolio">
      <div>
        <p><img src="./../../assets/images/common/portfolio.png" width="75" alt="" /></p>
        <p class="title">{{this.$store.state.user.nmPerson}}님의<br>포트폴리오를 분석하기 위해서<br>증권 계좌 연동이 필요합니다</p>
        <p class="text">간단하게 내 계좌를 조회 하신 후<br>진단을 받아 보세요</p>
      </div>
    </div>  

    <div class="btn-wrap float">
      <a @click="callMyStock()" class="blue box solid">내 계좌 불러오기</a>
    </div>     

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsDiagsMyStock",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "내계좌불러오기";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    callMyStock: function() {
      var _this = this;
      /*** 
      this.$http
        .get("/m/diags/getDiagsReport.json", {
          params: {}
        })
        .then(response => {
          _this.sumAmt = response.data.sumAmt;
          _this.depWdrlInfo = response.data.depWdrlInfo;

          _this.personShareList = response.data.personShareList;
          for (var idx in _this.personShareList) {
            _this.person_share_list.push(_this.personShareList[idx].no_person);
          }

          _this.searchAccountList();
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
      ***/

      this.$router.push("/assets/stockGoal");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
