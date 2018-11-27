<template>
  <section>
    <div class="report-top">
      <div class="checks round">
        <input type="radio" id="rds1" name="RadioGroup1"><label for="rds1">년</label>
        <input type="radio" id="rds2" name="RadioGroup1"><label for="rds2">월</label>
        <input type="radio" id="rds3" name="RadioGroup1"><label for="rds3">주</label>
      </div>
      <div class="date">
        <p>
          <input type="text">
          <button class="cal"></button>
        </p>
        <p>
          <input type="text">
          <button class="cal"></button>
        </p>
      </div>
    </div>

    <div class="report-con">
      <div class="flex">
        <div>
          <p class="key"><strong>수입</strong>(만원)</p>
          <p class="value">4,350</p>
        </div>
        <div>
          <p class="key"><strong>지출</strong>(만원)</p>
          <p class="value">4,350</p>
        </div>
      </div>
      <div class="graph">
        <!-- <img src="../../assets/images/consume/graph2.png" alt="" /> -->
        <Graph></Graph>
      </div>
    </div>

    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">지출</a>
        <a href="#">수입</a>
      </div>
    </div>

    <div class="box-list list02 noMG">

      <div class="select pb20">
        <select>
          <option>카테고리별</option>
        </select>
        <select>
          <option>금액순</option>
        </select>
      </div>

      <div class="item">
        <a @click="clickItem" class="block">
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />식비 <em>(120건)</em></p>
            <p class="number">118,107<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p></p>
          </div>
        </a>
      </div>

      <div class="item">
        <a @click="clickItem" class="block">
          <div class="flex">
            <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />식비 <em>(120건)</em></p>
            <p class="number">118,107<em>원</em></p>
          </div>
          <div class="bar">
            <p style="width: 50%"></p>
          </div>
          <div class="flex">
            <p>2%</p>
            <p></p>
          </div>
        </a>
      </div>

    </div>

  </section>
</template>

<script>
import Graph from "./SettlementChart";
export default {
  name: "ConsumeSettlement",
  data() {
    return {};
  },
  components: {
    Graph: Graph
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입·지출 보고서";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickItem: function() {
      this.$router.push("/consume/consumeIncomeStats");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
