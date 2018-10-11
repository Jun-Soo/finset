<template>
<div v-if="goodsList.length" >
  <swiper direction="horizontal"
        :mousewheel-control="true"
        :performance-mode="false"
        :pagination-visible="true"
        :pagination-clickable="true"
        :loop="true">
 	<div class="list-block" v-for="goods in goodsList" :key="goods.index">
		<div class="list-block prd-list">
			<div class="container-fluid prd-loan" id="loan_product">
					<div class="list-heading" v-on:click="loanGoodsBankDetail(goods.cd_fc, goods.cd_non_goods)">
						<li class="bank-title">
							<span class="thumb-logo" :style=goods.style></span>{{goods.nm_fc}}
							<span v-if="goods.yn_alliance === 'Y'" class="alliance-logo"/>
						</li>
						<input type="hidden" name="cd_fc_each" :value="goods.cd_fc"/>
						<input type="hidden" name="cd_goods_each" :value="goods.cd_non_goods"/>
						<h2 class="prd-title" v-html=goods.nm_goods></h2>
						<p v-html=goods.desc_feature>&nbsp;</p>
						<div class="loan-tag">
						</div>
					</div>
					<div class="list-info">
						<dl v-on:click="loanGoodsBankDetail(goods.cd_fc, goods.cd_non_goods)">
							<dt>대출금리</dt>
							<dd class="txt-point">
								<label v-if="goods.cd_ratio_type.length > 2">변동,고정</label>
								<label v-else-if="goods.cd_ratio_type.length == 2">{{Common.getCodeName("cd_ratio_type", goods.cd_ratio_type)}}</label>
								<span v-if="goods.rto_interest_from == null && goods.rto_interest_to == null">-</span>
								<span v-else-if="goods.rto_interest_from != null && goods.rto_interest_to != null">
									{{goods.rto_interest_from}}&nbsp;%&nbsp;~&nbsp;{{goods.rto_interest_to}}&nbsp;%
								</span>
								<span v-else-if="goods.rto_interest_from != null && goods.rto_interest_to == null">
									{{goods.rto_interest_from}}&nbsp;%&nbsp;~
								</span>
								<span v-else-if="goods.rto_interest_from == null && goods.rto_interest_to != null">
									~&nbsp;{{goods.rto_interest_to}}&nbsp;%
								</span>
							</dd> 
						</dl>
						<dl>
							<dt>대출한도</dt>
							<dd v-if="goods.desc_max_limit == '0' && goods.max_loan_term == '' "> 
								- / - </dd> 
							<dd v-else-if="goods.desc_max_limit != '0' && goods.max_loan_term != '' ">
								{{Common.formatNumber(goods.desc_max_limit)}}만원 / {{goods.max_loan_term}}년 </dd>
							<dd v-else-if="goods.desc_max_limit == '0' && goods.max_loan_term != '' ">
								- / {{goods.max_loan_term}}년 </dd>
							<dd v-else-if="goods.desc_max_limit != '0' && goods.max_loan_term == '' ">
								{{Common.formatNumber(goods.desc_max_limit)}}만원 / - </dd>
						</dl>
					</div>
					<div class="loan-btn">
						<div class="checkbox ico-loan ico-zzim">
							<input type="checkbox" :id=goods.checkId  :checked="favourite(goods.yn_favorite)"
								v-on:change="loanGoodsChoice(goods.cd_fc, goods.cd_non_goods, goods.checkId, 'N')"/>
							<label class="" :for=goods.checkId></label>
						</div>
					</div>
				</div>
		</div>
	</div>
  </swiper>
</div>
<div v-else class="data-none">
	<p>신청 가능한 상품이 없습니다.</p>
</div>
</template>
<script>
import Common from "./../../../assets/js/common.js";

export default {
  name: "listLoanNoAffiliates",
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
    favourite(yn_favourite) {
      return yn_favourite == "Y";
    },
    listGoods(callback) {
      var _this = this;
      var _parent = this.$parent;
      var formData = new FormData();
      formData.append("page", _parent.page);
      formData.append("cd_fin", _parent.cd_fin);
      formData.append("cd_goods_class_l", _parent.cd_goods_class_l);
      formData.append("cd_goods_class_m", _parent.cd_goods_class_m);
      formData.append("orderby", _parent.orderby);
      this.$store.state.isLoading = true;
      this.$http
        .post(
          "/m/loanworker/listLoanNoAffiliates.json",
          formData
          // {
          //   params: {
          //     page: _parent.page,
          //     cd_fin: _parent.cd_fin,
          //     cd_goods_class_l: _parent.cd_goods_class_l,
          //     cd_goods_class_m: _parent.cd_goods_class_m,
          //     orderby: _parent.orderby
          //   }
          // }
        )
        .then(function(response) {
          var list = response.data.pagedList.source;
          _this.$store.state.isLoading = false;
          for (var i = 0; i < list.length; i++) {
            list[i].style =
              "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
              list[i].cd_fc +
              "')";
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
          _parent.setListCount();
          _parent.page++;
        });
    },
    loanGoodsChoice(cd_fc, cd_goods, id, yn_alliance) {
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
    }
  }
};
</script>
<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
