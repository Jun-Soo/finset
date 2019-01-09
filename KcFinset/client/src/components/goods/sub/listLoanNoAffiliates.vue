<template>
  <div v-if="goodsList.length && seen">
    <div class="item" v-for="goods in goodsList" :key="goods.index">
      <a @click="loanGoodsBankDetail(goods.cd_fc, goods.cd_non_goods)">
        <div class="top">
          <p class="symbol"><img :src="goods.icon" alt="" />{{goods.nm_fc}}</p>
          <p class="text blue">{{goods.nm_goods}} <button class="btn-star" :class="{'on':goods.isChecked}" @click="loanGoodsChoice(goods, $event)"></button></p>
        </div>
        <div class="goods-benefit">
          <div>{{goods.rto_interest_from}}~{{goods.rto_interest_to}}<em> %</em></div>
          <div><em>최대 </em>{{Common.formatNumber(goods.desc_max_limit)}}<em> 만원</em></div>
        </div>
        <p class="goods-text1" v-html=goods.desc_feature></p>
        <p class="goods-text2" v-html=goods.deliberate></p>
      </a>
    </div>
  </div>
  <div class="nodata" v-else-if="seen">
    <p>신청 가능한 상품이 없습니다.</p>
  </div>
</template>

<script>
import Common from "./../../../assets/js/common.js";
export default {
  name: "listLoanNoAffiliates",
  props: ["item"],
  data() {
    return {
      seen: false,
      goodsList: [],
      Common: Common
    };
  },
  components: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    favourite: function(yn_favourite) {
      return yn_favourite == "Y";
    },
    listBankGoods: function(callback) {
      var _this = this;
      var _parent = this.$parent;
      var formData = new FormData();
      formData.append("page", _parent.page);
      formData.append("cd_goods_class_l", _parent.cd_goods_class_l);
      formData.append("cd_goods_class_m", _parent.cd_goods_class_m);
      formData.append("cd_ratio_type", _parent.cd_ratio_type);
      formData.append("cd_type_pay", _parent.cd_type_pay);
      formData.append("orderby", _parent.orderby.value);
      this.$http
        .post(_parent.urlPath + "listLoanNoAffiliates.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          // 초기화
          if (_parent.page == 1) {
            _this.goodsList = [];
          }
          if ((list || "") == "" || list.length === 0) {
            _this.seen = true;
            callback();
            return;
          }

          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            if (list[i].yn_favorite == "Y") {
              list[i].isChecked = true;
            } else {
              list[i].isChecked = false;
            }
          }
          _this.seen = true;
          if (_parent.page == 1) {
            _this.goodsList = list;
          } else {
            for (var key in list) {
              _this.goodsList.push(list[key]);
            }
          }
          _parent.totalPage = response.data.pagedList.pageCount;
          _parent.count = response.data.count;
          _parent.page++;
        });
    },
    loanGoodsChoice: function(goods, event) {
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
    loanGoodsBankDetail: function(cd_fc, cd_non_goods) {
      this.$router.push({
        name: "GoodsDetail",
        query: {
          cd_fc: cd_fc,
          cd_goods: cd_non_goods,
          urlPath: this.$parent.urlPath,
          isAffiliates: false
        }
      });
    }
  }
};
</script>
<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
