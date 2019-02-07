<template>
  <section v-if="seen">
    <div class="inout-list">
      <ul class="flex noBD">
        <li class="symbol"><img src="../../assets/images/common/bu_kb.png" alt="" />{{outVO.mbrNm}} / {{outVO.crdtIttNm}}</li>
        <li class="blue">가능</li>
        <!-- <li class="blue">불가능</li>-->
      </ul>
      <p class="name">{{outVO.prdtNm}}</p>
      <ul class="flex">
        <li>거래시각<br>
          금리<br>
          기간
        </li>
        <li>{{outVO.loanIntrstRt}}%<br>
          {{outVO.loanMaxLmtAmt/10000}} 만원<br>
          {{outVO.loanTerm}}개월<br>
        </li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">상환방식</p>
        </li>
        <li v-if="outVO.rfundMthd=='0'">만기일시</li>
        <li v-else>만기</li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">한종목투자한도</p>
          동일종목투자한도<br>
          로스컷비율(최저담보유지비율)<br>
          현금인출비율<br>
          만기연장비율
        </li>
        <li><br>
          {{outVO.isuInvstLmt}} %<br>
          {{outVO.lscutSetRt}} %<br>
          {{outVO.mnyOutAblRt}} %<br>
          {{outVO.expExtndAblRt}} %
        </li>
      </ul>
      <ul class="flex">
        <li>
          <p class="title">연체이율</p>
        </li>
        <li>취급금리 + {{outVO.ovdIntrstRt}}%</li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock3",
  data() {
    return {
      inVO: null,
      outVO: null,
      errorMessage: null,
      inFieldJson: null,
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청 가능한 상품";
  },
  created() {
    var _this = this;
    var frm = new FormData();
    frm.append("mbrCd", this.$route.query.mbrCd);
    frm.append("crdtIttCd", this.$route.query.crdtIttCd);
    frm.append("prdtCd", this.$route.query.prdtCd);

    this.$http
      .post("/m/loanstock/loanStock013View.json", frm)
      .then(response => {
        debugger;
        _this.inVO = response.data.inVO;
        _this.outVO = response.data.outVO;
        _this.errorMessage = response.data.errorMessage;
        _this.inFieldJson = response.data.inFieldJson;
        if (_this.errorMessage != "") {
          _this.$toast.center(_this.errorMessage);
        }
        _this.seen = true;
      });
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
