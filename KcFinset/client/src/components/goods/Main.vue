<template>
 	<div id="content">
		<div class="visual-banner">
			<h2 class="title">
				다양한 금융사의 대출금리와<br />한도를 한눈에
			</h2>		
		</div>
		<div class="container-fluid">
			<div class="list-group list-group-bnr">
				<a v-on:click="loanApplyStep('WORKER')" class="list-group-item">
					<h3 class="h3">신용대출(직장인)</h3>
					<p>소득이있는 직장인</p>
				</a>
				
        <a v-on:click="loanApplyStep('EMPLOYED')" class="list-group-item">
					<h3 class="h3">신용대출(개인사업자)</h3>
					<p>소득이있는  개인사업자</p>
				</a>
				
				<a href="javascript:loanApplyStep('HOMEMORTGAGE')" class="list-group-item">
					<h3 class="h3">주택담보대출</h3>
					<p>아파트, 오피스텔, 일반주택 구입 및 보유자</p>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  name: "FinsetMain",
  data() {
    return {
      toDate: "",
      ratingCredit: "",
      list: []
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
    loanApplyStep(gubun) {
      var _this = this;
      var url = "";
      if (gubun == "WORKER") {
        //직장인 신용대출
        url = "/goods/WorkerGoods";
      } else if (gubun == "EMPLOYED") {
        //자영업자 신용대출
        url = "/goods/Spinner";
      } else if (gubun == "HOMEMORTGAGE") {
        //부동산 담보대출
        url = "/m/loanhomemortgage/frameLoanHomeMortgageStep1.crz";
      }
      _this.$router.push(url);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
