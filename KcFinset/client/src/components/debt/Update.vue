<template>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>정보 수정</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>금리 등 부채 정보를 수정합니다.</p>
			</div>	
			<form name="frmUpdateDebtInfo" id="frmUpdateDebtInfo" method="post" validate="remove_tag;">
				<div class="form-inline">
					<input type="hidden" name="no_person" value="${debtVO.no_person }"/>
					<input type="hidden" id ="no_manage_info" name="no_manage_info" value="${debtVO.no_manage_info }"/>
					<div class="form-group">
						<label for="interest">금 리</label>
						<input type="number" class="form-control" id="int_interest" maxlength="6" autocomplete="off" value="${debtVO.interest }"/>
						<input type="hidden" name="interest" id="interest" value="${debtVO.interest }"/>
						<span class="form-control-feedback" aria-hidden="true">%</span>
					</div>
					<div class="form-group">
						<label for="repayment">상환방법</label>
						<select class="selectpicker" data-header="상환방법" name="rep_method" id="repayment">
						</select>
					</div>
					<div class="form-group">
						<label for="loan_mount">거치기간</label>
						<select class="selectpicker" data-header="거치기간" name="loan_mount" id="loan_mount">
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_cycle">이자납입주기</label>
						<select class="selectpicker" data-header="이자납입주기" name="inter_pay_cycle" id="inter_pay_cycle">
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_day">이자납입일</label>
						<select class="selectpicker" data-header="이자납입일" name="inter_pay_day" id="inter_pay_day">
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="updateDebtInfo();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</template>

<script>
import router from "@/comm/router.js";

export default {
  name: "DebtDetail",
  data() {
    return {
      debtVO: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.getDebtInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDebtInfo() {
      var thisObj = this;
      this.$http
        .get("/api/debt/getDebtInfo.json", {
          params: { no_manage_info: thisObj.$route.params.no_manage_info }
        })
        .then(function(response) {
          var vo = response.data.debtVO;
          vo.style =
            "background-image:url('/api/fincorp/getFinCorpIcon.crz?cd_fc=" +
            vo.cd_fc +
            "')";
          thisObj.debtVO = vo;
        });
    },
    menuToggle() {
      $("#dropdown-menu").toggle();
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
