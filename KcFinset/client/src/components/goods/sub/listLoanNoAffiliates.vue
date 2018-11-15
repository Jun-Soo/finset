<template>
  <div v-if="goodsList.length">
    <div class="item" v-for="goods in goodsList" :key="goods.index">
      <a @click="loanGoodsBankDetail(goods.cd_fc, goods.cd_non_goods)">
        <div class="top">
          <p class="symbol"><img :src="goods.icon" alt="" />{{goods.nm_fc}}</p>
          <p class="text blue" v-html=goods.nm_goods></p>
        </div>
        <div class="goods-benefit">
          <div>{{goods.rto_interest_from}}~{{goods.rto_interest_to}}<em> %</em></div>
          <div><em>최대 </em>{{Common.formatNumber(goods.desc_max_limit)}}<em> 만원</em></div>
        </div>
        <p class="goods-text1" v-html=goods.desc_feature></p>
        <p class="goods-text2">저축은행중앙회 심의필 2018-00404호(2018.8.12)</p>
      </a>
    </div>
  </div>
  <div class="nodata" v-else>
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
    listGoods: function(callback) {
      var _this = this;
      var _parent = this.$parent;
      var formData = new FormData();
      formData.append("page", _parent.page);
      formData.append("cd_goods_class_l", _parent.cd_goods_class_l);
      formData.append("cd_goods_class_m", _parent.cd_goods_class_m);
      formData.append("orderby", _parent.orderby);
      this.$http
        .post(_parent.urlPath + "listLoanNoAffiliates.json", formData)
        .then(function(response) {
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            list[i].checkId = "z" + list[i].cd_fc + list[i].cd_non_goods;
          }

          if (list.length === 0) {
            callback();
            return;
          }
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
    loanGoodsChoice: function(cd_fc, cd_goods, id, yn_alliance) {
      var _this = this;
      var chkZzim = $("#" + id).is(":checked");
      var url = "";

      if (chkZzim == true) {
        url = "/m/loan/insertLoanGoodsChoice.json";
      } else {
        url = "/m/loan/deleteLoanGoodsChoice.json";
      }
      this.$http
        .get(url, {
          params: {
            cd_fc: cd_fc,
            cd_goods: cd_goods,
            yn_alliance: yn_alliance
          }
        })
        .then(function(response) {
          var returnData = response.data.returnData;
        });
    },
    loanGoodsBankDetail: function(cd_fc, cd_non_goods) {
      this.$router.push({
        name: "GoodsDetail",
        params: {
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
