<template>

<div id="test">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<div class="list-block" v-for="goods in goodsList" :key="goods.index">
		<div class="list-block prd-list">
			<div class="container-fluid prd-loan" id="loan_product" v-on:click="loanGoodsBankDetail(goods.cd_fc, goods.cd_non_goods)">
					<div class="list-heading">
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
						<dl>
							<dt>대출금리</dt>
							<dd class="txt-point">
								<label v-if="goods.ratio_length > 2">변동,고정</label>
								<label v-else-if="goods.ratio_length == 2">{{Common.getCodeName("cd_ratio_type", goods.cd_ratio_type)}}</label>
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
						<!-- <div class="checkbox ico-loan ico-zzim">
							<input type="checkbox" id="z${List.cd_fc}${List.cd_non_goods}" value="Y"
                                   onchange="loanGoodsChoice('${List.cd_fc}','${List.cd_non_goods}', 'z${List.cd_fc}${List.cd_non_goods}', 'N');"
                                <c:out value="${List.yn_favorite eq 'Y' ? 'checked' : ''}"/> ><label class="" for="z${List.cd_fc}${List.cd_non_goods}"></label>
						</div> -->
					</div>
			</div>
		</div>
		</div>
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
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    loadData() {
      var _this = this;
      var _parent = this.$parent;
      this.$http
        .get("/m/loanworker/listLoanNoAffiliates.json", {
          params: {
            page: _parent.page,
            cd_fin: _parent.cd_fin,
            cd_goods_class_l: _parent.cd_goods_class_l,
            cd_goods_class_m: _parent.cd_goods_class_m,
            orderby: _parent.orderby
          }
        })
        .then(function(response) {
          _this.goodsList = response.data.pagedList.source;
          for (var i = 0; i < _this.goodsList.length; i++) {
            _this.goodsList[i].style =
              "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
              _this.goodsList[i].cd_fc +
              "')";
            _this.goodsList[i].ratio_length =
              _this.goodsList[i].cd_ratio_type.length;
          }
          _parent.totalPage = response.data.pagedList.pageCount;
          _parent.count = response.data.count;
          _parent.setListCount();
        });
    }
  }
};
</script>
<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
