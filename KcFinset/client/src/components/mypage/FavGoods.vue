
<template>
  <section v-if="seen">
    <div class="tab">
      <div class="wrap">
        <a :class="{'on':curTab == 'noAffiliates'}" @click="tabOnClick('noAffiliates')">일반상품</a>
        <a :class="{'on':curTab == 'affiliates'}" @click="tabOnClick('affiliates')">제휴상품</a>
      </div>
    </div>

    <div class="box-list list01 noMG pb90">
      <div class="search">
        <div class="left">
          <p class="total">총 <em>{{totalCount}}</em>개의 상품</p>
          <p class="total" v-if="cd_goods_alliance=='02'">선택 (<em>{{selectedCount}}</em>개)</p>
        </div>
        <div class="right">
          <select v-model="cd_goods_class" @change="optingOnChange()">
            <option v-for="option in options" :key="option.index" v-bind:value="option.value">
              {{ option.text }}
            </option>
          </select>
        </div>
      </div>
      <div class="item" v-for="goods in goodsList" :key="goods.index">
        <a @click="favGoodsDetail(goods)">
          <div class="top">
            <p class="symbol" v-if="cd_goods_alliance=='01'"><img :src="goods.icon" alt="" />{{goods.nm_fc}}</p>
            <p class="symbol checks" v-else>
              <input type="checkbox" :checked="goods.isSelectChecked"><label for="chk1" @click="clickCheck(goods, $event)"></label>
              <img :src="goods.icon" alt="" />{{goods.nm_fc}}
            </p>
            <p><button class="btn-star" :class="{'on':goods.isChecked}" @click="favGoodsChoice(goods, $event)"></button></p>
          </div>
          <p class="goods-name">{{goods.nm_goods}}</p>
          <p class="mt10" v-html=goods.desc_feature></p>
          <p class="goods-cate"><em v-for="keyword in goods.keywords" :key="keyword.index">{{keyword}}</em></p>
          <div class="detail">
            <div class="wrap">
              <p>대출금리</p>
              <p class="rate">
                <em v-if="goods.cd_type_interest && goods.cd_type_interest.length > 2">변동, 고정</em>
                <em v-else-if="goods.cd_type_interest && goods.cd_type_interest.length == 2">{{Common.getCodeName("cd_ratio_type", goods.cd_type_interest)}}</em>
                {{goods.rto_interest_from}}%~{{goods.rto_interest_to}}%
              </p>
            </div>
            <div class="wrap">
              <p>대출한도/기간</p>
              <p>최대 {{Common.formatNumber(goods.amt_limit/10000)}}만원/{{goods.cd_loan_term}}개월</p>
            </div>
          </div>
        </a>
      </div>
    </div>
    <div class="btn-wrap float" v-if="cd_goods_alliance=='02' && selectedCount > 0">
      <a class="solid box blue">금리/한도 조회하기</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "MypageFavGoods",
  data() {
    return {
      seen: false,
      curTab: "noAffiliates",
      cd_goods_class: "01",
      cd_goods_alliance: "01",
      totalCount: 0,
      selectedCount: 0,
      page: 1,
      goodsList: [],
      Common: Common,
      options: [
        { text: "신용대출(직장인)", value: "01" },
        { text: "신용대출(개인사업자)", value: "02" },
        { text: "담보대출", value: "03" }
      ]
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "관심상품";
  },
  created() {
    this.tabOnClick(this.curTab);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    tabOnClick: function(type) {
      //초기화
      this.page = 1;
      this.goodsList = [];
      this.totalCount = 0;
      this.selectedCount = 0;
      if (type == "noAffiliates") {
        this.curTab = "noAffiliates";
        this.cd_goods_alliance = "01";
      } else if (type == "affiliates") {
        this.curTab = "affiliates";
        this.cd_goods_alliance = "02";
      }
      Common.pagination(this.listGoods);
    },
    listGoods: function(callback) {
      var _this = this;
      var formData = new FormData();
      formData.append("page", this.page);
      formData.append("cd_goods_class", this.cd_goods_class);
      formData.append("cd_goods_alliance", this.cd_goods_alliance);

      this.$http
        .post("/m/customercenter/listCustomerGoodsFavorite.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          if (list.length === 0) {
            _this.seen = true;
            callback();
            return;
          }
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            //관심상품 여부
            list[i].isChecked = true;
            //제휴상품의 경우 선택체크박스 추가
            if (_this.cd_goods_alliance == "02") {
              list[i].isSelectChecked = false;
            }
            list[i].keywords = list[i].keyword_list.split(",");
          }
          _this.seen = true;

          if (_this.page == 1) {
            _this.goodsList = list;
          } else {
            for (var key in list) {
              _this.goodsList.push(list[key]);
            }
          }
          if (_this.goodsList.length) {
            _this.totalCount = _this.goodsList.length;
          }
          _this.page++;
        });
    },
    clickCheck: function(goodsInfo, event) {
      event.stopPropagation();
      goodsInfo.isSelectChecked = !goodsInfo.isSelectChecked;
      if (goodsInfo.isSelectChecked) {
        this.selectedCount++;
      } else {
        this.selectedCount--;
      }
    },
    optingOnChange: function() {
      this.tabOnClick(this.curTab);
    },
    favGoodsChoice: function(goods, event) {
      var _this = this;
      var url = "";
      event.stopPropagation();
      goods.isChecked = !goods.isChecked;
      var formData = new FormData();
      formData.append("cd_fc", goods.cd_fc);
      formData.append("cd_goods", goods.cd_non_goods);
      formData.append("yn_alliance", "N");
      if (goods.isChecked) {
        url = "/m/loan/insertLoanGoodsChoice.json";
      } else {
        url = "/m/loan/deleteLoanGoodsChoice.json";
      }
      this.$http.post(url, formData).then(function(response) {
        var returnData = response.data.returnData;
      });
    },
    favGoodsDetail: function(goods) {
      this.$router.push({
        name: "MypageFavDetail",
        params: {
          cd_fc: goods.cd_fc,
          cd_goods: goods.cd_goods,
          yn_alliance: goods.yn_alliance
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
