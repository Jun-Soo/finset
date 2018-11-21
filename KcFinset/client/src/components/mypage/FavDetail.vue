<template>
  <section v-if="seen">
    <div class="goods-top">
      <p class="symbol"><img :src="goodsInfo.icon" alt="" />{{goodsInfo.nm_fc}}</p>
      <p class="title">{{goodsInfo.nm_goods}}</p>
      <p class="text">{{goodsInfo.desc_feature}}</p>
      <p class="opt"><span v-for="keyword in keyword_list" :key="keyword.index">{{keyword}}</span></p>
    </div>
    <div class="tab mt50">
      <div class="wrap">
        <a :class="{'on':curTab === 'interest'}" @click="tabOnClick('interest')">금리</a>
        <a :class="{'on':curTab === 'detail'}" @click="tabOnClick('detail')">상세</a>
      </div>
    </div>
    <div class="goods-detail" v-if="curTab === 'interest'">
      <div class="container">
        <div v-if="goodsInfo.content_interest && goodsInfo.content_interest.length">
          <p class="key">대출금리</p>
          <p class="value" v-html="goodsInfo.content_interest"></p>
        </div>
        <div v-if="goodsInfo.prefer_interest && goodsInfo.prefer_interest.length">
          <p class="key">우대금리</p>
          <p class="value" v-html="goodsInfo.prefer_interest"></p>
        </div>
        <div v-if="goodsInfo.desc_etc && goodsInfo.desc_etc.length">
          <p class="key">유의사항</p>
          <p class="value" v-html="goodsInfo.desc_etc"></p>
        </div>
        <div class="btn-wrap" v-if="yn_alliance=='Y'">
          <a class="solid blue">금리/한도 조회하기</a>
        </div>
      </div>
    </div>
    <div class="goods-detail" v-if="curTab === 'detail'">
      <div class="container">
        <div v-if="goodsInfo.desc_loan && goodsInfo.desc_loan.length">
          <p class="key">대상고객</p>
          <p class="value" v-html="goodsInfo.desc_loan"></p>
        </div>
        <div v-if="goodsInfo.desc_limit && goodsInfo.desc_limit.length">
          <p class="key">대출한도</p>
          <p class="value" v-html="goodsInfo.desc_limit"></p>
        </div>
        <div v-if="goodsInfo.desc_paymethod && goodsInfo.desc_paymethod.length">
          <p class="key">대출기간</p>
          <p class="value" v-html="goodsInfo.desc_paymethod"></p>
        </div>
        <div v-if="goodsInfo.desc_repaymethod && goodsInfo.desc_repaymethod.length">
          <p class="key">상환방법</p>
          <p class="value" v-html="goodsInfo.desc_repaymethod"></p>
        </div>
        <div v-if="goodsInfo.desc_commission && goodsInfo.desc_commission.length">
          <p class="key">수수료</p>
          <p class="value" v-html="goodsInfo.desc_commission"></p>
        </div>
        <div v-if="goodsInfo.desc_overdue_interest && goodsInfo.desc_overdue_interest.length">
          <p class="key">연체이자율</p>
          <p class="value" v-html="goodsInfo.desc_overdue_interest"></p>
        </div>
        <div v-if="goodsInfo.desc_loan_cost && goodsInfo.desc_loan_cost.length">
          <p class="key">부대비용</p>
          <p class="value" v-html="goodsInfo.desc_loan_cost"></p>
        </div>
        <div v-if="goodsInfo.time_interest && goodsInfo.time_interest.length">
          <p class="key">이자부과시기</p>
          <p class="value" v-html="goodsInfo.time_interest"></p>
        </div>
        <div v-if="goodsInfo.desc_etc && goodsInfo.desc_etc.length">
          <p class="key">유의사항</p>
          <p class="value" v-html="goodsInfo.desc_etc"></p>
        </div>
        <div class="btn-wrap" v-if="yn_alliance=='Y'">
          <a class="solid blue">금리/한도 조회하기</a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "GoodsDetail",
  data() {
    return {
      seen: false,
      yn_alliance: this.$route.params.yn_alliance,
      curTab: "interest",
      goodsInfo: "",
      keyword_list: ""
    };
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "관심상품";
  },
  created() {
    this.getGoodsDetail();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getGoodsDetail: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("cd_fc", this.$route.params.cd_fc);
      formData.append("cd_goods", this.$route.params.cd_goods);
      formData.append("yn_alliance", this.$route.params.yn_alliance);
      this.$http
        .post("/m/customercenter/getCustomerGoodsFavoriteDetail.json", formData)
        .then(function(response) {
          _this.goodsInfo = response.data.goodsInfo;
          if (_this.goodsInfo) {
            _this.goodsInfo.icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + _this.goodsInfo.cd_fc;
            _this.keyword_list = _this.goodsInfo.keyword_list.split(",");
          }
          _this.seen = true;
        });
    },
    tabOnClick: function(type) {
      this.curTab = type;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
