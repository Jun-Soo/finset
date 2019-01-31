<template>
  <section>

    <div class="box-list list02 noMG">

      <div class="item"  v-for="stockAnalysis in stockAnalysisList" :key="stockAnalysis.index">
        <a href="#" class="block">
          <div class="flex">
            <p class="corp">{{stockAnalysis.isuKorNm}}</p>
            <p class="number">{{formatNumber(stockAnalysis.valAtCur)}}<em>원</em></p>
          </div>
          <div class="bar">
            <p :style="{width: (parseInt(stockAnalysis.weight*10000)/100)+'%'}"></p>
          </div>
          <div class="flex bb">
            <p>{{ parseInt(stockAnalysis.weight*10000)/100 }}%</p>
            <p class="red">{{formatNumber(stockAnalysis.proLoss)}} ({{formatNumber(stockAnalysis.earningRate)}}%)</p>
          </div>
          <div class="star-list">
            <div class="list">
              <p class="total">Total</p>
              <p class="total">
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.avg" :key="i"/>
              </p>
            </div>
            <div class="list">
              <p>Value</p>
              <p>
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.value" :key="i"/>
              </p>
              <p>Growth</p>
              <p>
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.growth" :key="i"/>
              </p>
            </div>
            <div class="list">
              <p>Momentum</p>
              <p>
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.momentum" :key="i"/>
              </p>
              <p>Profitability</p>
              <p>
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.profitability" :key="i"/>
              </p>
            </div>
            <div class="list">
              <p>Quality</p>
              <p>
                <img src="./../../assets/images/common/star_list.png" width="11" alt="" v-for="i in stockAnalysis.factorAnalysis.quality" :key="i"/>
              </p>
              <p>Risk</p>
              <p>{{ parseFloat(parseInt(stockAnalysis.riskRate*100)/100) }} %
              </p>
            </div>
          </div>
          <p class="pt20">{{stockAnalysis.factorAnalysis.stockComment}}</p>
        </a>
      </div>

    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "diagsStockResult",
  data() {
    return {
      stockAnalysisList: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "종목분석";
  },
  created() {
    this.getDiagsStockReport();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDiagsStockReport: function() {
      var _this = this;

      this.$http
        .get("/m/diags/getDiagsStockReport.json", {
          params: {}
        })
        .then(response => {
          _this.stockAnalysisList =
            response.data.diagsStockReport.stockAnalysisList;
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
