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
				<img id="img"/>
			</div>	
			<form name="frmUpdateDebtInfo" id="frmUpdateDebtInfo" method="post">
				<div class="form-inline">
					<input type="hidden" id ="no_manage_info" name="no_manage_info" :value="debtVO.no_manage_info"/>
					<div class="form-group">
						<label for="interest">금 리</label>
						<input type="number" class="form-control" id="int_interest" maxlength="6" autocomplete="off" v-model="debtVO.interest"/>
						<input type="hidden" name="interest" id="interest"/>
						<span class="form-control-feedback" aria-hidden="true">%</span>
					</div>
					<div class="form-group">
						<label for="repayment">상환방법</label>
						<select class="" data-header="상환방법" name="rep_method" id="repayment" v-model="debtVO.rep_method">
							<option value="3">만기일시상환</option>
							<option value="1">원리금분할상환</option>
							<option value="2">원금분할상환</option>
						</select>
					</div>
					<div class="form-group">
						<label for="loan_mount">거치기간</label>
						<select class="" data-header="거치기간" name="loan_mount" id="loan_mount" v-model="debtVO.loan_mount">
							<option value=null>없음</option>
							<option value="1">1년</option>
							<option value="2">2년</option>
							<option value="3">3년</option>
							<option value="4">4년</option>
							<option value="5">5년</option>
							<option value="6">6년</option>
							<option value="7">7년</option>
							<option value="8">8년</option>
							<option value="9">9년</option>
							<option value="10">10년</option>
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_cycle">이자납입주기</label>
						<select class="" data-header="이자납입주기" name="inter_pay_cycle" id="inter_pay_cycle" v-model="debtVO.inter_pay_cycle">
							<option value="01">매월</option>
							<option value="02">분기</option>
							<option value="03">년</option>
							<option value="04">만기시</option>
							<option value="05">특정일</option>
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_day">이자납입일</label>
						<select class="" data-header="이자납입일" name="inter_pay_day" id="inter_pay_day" v-model="debtVO.inter_pay_day">
							<option value=null>없음</option>
							<option value="01">1일</option>
							<option value="02">2일</option>
							<option value="03">3일</option>
							<option value="04">4일</option>
							<option value="05">5일</option>
							<option value="06">6일</option>
							<option value="07">7일</option>
							<option value="08">8일</option>
							<option value="09">9일</option>
							<option value="10">10일</option>
							<option value="11">11일</option>
							<option value="12">12일</option>
							<option value="13">13일</option>
							<option value="14">14일</option>
							<option value="15">15일</option>
							<option value="16">16일</option>
							<option value="17">17일</option>
							<option value="18">18일</option>
							<option value="19">19일</option>
							<option value="20">20일</option>
							<option value="21">21일</option>
							<option value="22">22일</option>
							<option value="23">23일</option>
							<option value="24">24일</option>
							<option value="25">25일</option>
							<option value="26">26일</option>
							<option value="27">27일</option>
							<option value="28">28일</option>
							<option value="29">29일</option>
							<option value="30">30일</option>
							<option value="31">31일</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block"  @click="updateDebtInfo()">저장</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</template>

<script>
import router from "@/comm/router.js";
import Common from "./../../assets/js/common.js";

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
    this.getDebtInfoForUpdate();
    Common.enableBottom(true);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getDebtInfoForUpdate() {
      var thisObj = this;
      this.$http
        .get("/api/debt/getDebtInfoForUpdate.json", {
          params: { no_manage_info: thisObj.$store.state.no_manage_info }
        })
        .then(function(response) {
          var vo = response.data.debtVO;
          vo.style =
            "background-image:url('/api/fincorp/getFinCorpIcon.crz?cd_fc=" +
            vo.cd_fc +
            "')";
          thisObj.debtVO = vo;
          if ((vo.rep_method || "") === "") {
            vo.rep_method = "1";
          }
          vo.no_manage_info = thisObj.$store.state.no_manage_info;
        });
    },
    updateDebtInfo() {
      document.getElementById("interest").value = this.debtVO.interest;
      var thisObj = this;
      var form = document.getElementById("frmUpdateDebtInfo");
      var formData = new FormData(form);

      var vals = formData.keys();

      for (var a of vals) {
        console.log(a + "////////" + formData.get(a));
        if (formData.get(a) == "") {
          if (a == "interest") a = "int_interest";
          alert(
            $("#" + a)
              .prev()
              .text()
              .replace(" ", "") + "를 입력하세요"
          );
          $("#" + a).focus();
          return;
        }
      }

      this.$http
        .post("/api/debt/updateDebtInfo.json", formData)
        .then(function(response) {
          if (response.data.code == "00") {
            thisObj.$toast.center("저장에 성공했습니다.");
          } else {
            thisObj.$toast.center("저장에 실패했습니다.");
          }
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
select {
  left: 120px;
  position: relative;
}
</style>
