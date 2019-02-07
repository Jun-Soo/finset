<template>
  <section v-if="seen">
    <div class="box-list list01 noMG">
      <div class="item" v-for="(item, index) in listInVO" :key="index">
        <div class="top">
          <p class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />국민은행</p>
          <p class="number">6,920,000<em>원</em></p>
        </div>
        <form id="formList">
          <div class="form">
            <p class="key">계좌번호</p>
            <input type="number" v-model="item.acntNo" v-validate="'required|max:20|min:8'" name="inVOList[0].acntNo" data-vv-name='계좌번호'>
            <input type="hidden" v-model="item.mbrCd" name="inVOList[0].mbrCd" />
            <p class="warn" v-if="errors.has('계좌번호')">{{errors.first('계좌번호')}}</p>
          </div>
        </form>
      </div>
    </div>
    <div class="btn-wrap float">
      <a @click="nextStep" class="blue box solid">다음</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "GoodStock1",
  data() {
    return {
      // acntNo: "30110100001",
      // mbrCd: "S063",
      listInVO: [],
      isValidated: false,
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "계좌 번호 확인";
  },
  created() {
    this.listInVO = [];
    this.listInVO.push({ acntNo: "30110100001", mbrCd: "S063" });
    // this.listInVO.push({ acntNo: "30110100003", mbrCd: "S062" });
    this.seen = true;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    nextStep: function() {
      var _this = this;
      var url = "/m/loanstock/loanStock012List.json";
      var frm = new FormData(document.getElementById("formList"));
      // frm.append("accnum01");
      _this.$validator.validateAll().then(res => {
        if (res) {
          //test data : _this.listInVO[0]
          _this.$http.post(url, frm).then(res => {
            debugger;
            var result = res.data;
            if (result.errorMessage != "") {
              _this.$toast.center(result.errorMessage);
            } else {
              // _this.$router.push("/goods/stock2");
              // _this.$route.query
              _this.$router.push({
                name: "goodsStock2",
                query: {
                  inVO: result.inVO,
                  outVOList: result.inVOype,
                  inFieldJson: result.inFieldJson
                }
              });
            }
          });
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
