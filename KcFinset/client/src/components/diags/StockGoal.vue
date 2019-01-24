<template>
  <section>

    <div class="box-list list01 noMG pb90">
      <form name="frmStockGoals" id="frmStockGoals"></form>
      <div class="item"  v-for="stockGoal in stockGoals" :key="stockGoal.index">
        <div class="top">
          <p class="corp">{{stockGoal.isuKorNm}}</p>
          <p class="number">{{formatNumber(stockGoal.valAtCur)}}<em>원</em></p>
        </div>
        <div class="flex-wrap">
          <p class="goal">
            <em>{{formatNumber(stockGoal.proLoss)}} 원</em>
            <em>{{formatNumber(stockGoal.earningRate)}}%</em>
          </p>
        </div>
        
        <div class="hide-con show">
          <div class="list">
            <p class="left">목표수익률 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" align="right" style="height:20px; width:30%;" v-model="stockGoal.profitGoal"/> %</p>
            <p class="right">
               ({{formatNumber(proLossGoal(stockGoal))}}원)
            </p>
          </div>
        </div>
        <div class="hide-con show">
          <div class="list">
            <p class="left">예상보유기간&nbsp;&nbsp; <input type="text" align="right" style="height:20px; width:30%;" v-model="stockGoal.holdGoal"/> 일</p>
            <p class="right">
               ({{holdEnd(stockGoal)}})
            </p>
          </div>
        </div>
      </div>

      <div class="btn-wrap">
        <a @click="startSurvey()"  class="blue solid">나의 투자성향 알아보기</a>
      </div> 
    </div>

 
    <div class="btn-wrap float">
      <a @click="saveStockGoals()"  class="blue box solid">완료</a>
    </div> 

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsDiagsMain",
  data() {
    return {
      stockGoals: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "목표수익률 설정";
  },
  created() {
    this.getStockGoals();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    startSurvey: function() {
      this.$router.push("/assets/investSurvey");
    },
    getStockGoals: function() {
      var _this = this;
      this.$http
        .get("/m/diags/getStockGoals.json", {
          params: {}
        })
        .then(response => {
          _this.stockGoals = response.data.stockGoals;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    saveStockGoals: function() {
      var _this = this;

      var cnt = 0;
      $("#frmStockGoals").html("");
      for (var i = 0; i < this.stockGoals.length; i++) {
        $("#frmStockGoals").append(
          this.getInputStr("noPerson", cnt, this.stockGoals[i].noPerson)
        );
        $("#frmStockGoals").append(
          this.getInputStr("isincode", cnt, this.stockGoals[i].isincode)
        );
        $("#frmStockGoals").append(
          this.getInputStr("profitGoal", cnt, this.stockGoals[i].profitGoal)
        );
        $("#frmStockGoals").append(
          this.getInputStr("holdGoal", cnt, this.stockGoals[i].holdGoal)
        );
        cnt++;
      }

      var data = $("#frmStockGoals").serialize();
      this.$http
        .post("/m/diags/saveStockGoals.json", data)
        .then(response => {
          this.$router.push("/assets/diagsStart");
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    getInputStr: function(name, idx, value) {
      return (
        "<input type='hidden' name='stockGoals[" +
        idx +
        "]." +
        name +
        "' value='" +
        value +
        "'/>"
      );
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    proLossGoal: function(stockGoal) {
      return stockGoal.profitGoal * stockGoal.valAtTrade / 100;
    },
    holdEnd: function(stockGoal) {
      var holdGoal = parseInt(stockGoal.holdGoal);
      if (holdGoal == undefined) return "";

      var d = new Date();

      d.setDate(d.getDate() + holdGoal);

      var y = d.getFullYear();
      var m = d.getMonth() + 1;
      var d = d.getDate();

      var ymd = y + "년 " + m + "월 " + d + "일";

      return ymd;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
