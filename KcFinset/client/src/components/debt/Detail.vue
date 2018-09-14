<template>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goDebtMain();">뒤로가기</button>
			</div>
			<h1>부채상세정보</h1>
			
			<form name="frmDebtUpdate" id="frmDebtUpdate" method="post" validate="remove_tag;">
				<input type="hidden" name="no_manage_info" value="${debtData.no_manage_info }"/>
				<input type="hidden" name="interest" value="${debtData.ever_interest}"/>
			</form>
			
			<div class="rightDiv" id="debt_rightDiv" v-on:click="menuToggle()">
				<button type="button" id="submenu" class="ico-submenu"></button>
			</div>
			<div id="hiddenSelectbox" style="position: fixed; right: 0px">
				<div class="btn-group bootstrap-select selectbox pull-right open">
					<div class="dropdown-menu open" id="dropdown-menu">
						<ul class="dropdown-menu inner" id="dropdown-inner">
							<li v-on:click="updateDebt()"><a id="debt-update"><span class="text">수정</span></a></li>
							<li v-on:click="deleteDebt()"><a id="modalConfirm"><span class="text">삭제</span></a></li>
							<li v-on:click="goMemo()"><a><span class="text">메모</span></a></li>
						</ul>
					</div>
				</div>
            </div>
		</div>
	</header>
	<section id="content">
		<div class="list-block">
			<div class="container-fluid prd-debt">
				<div class="list-heading">
					<li class="bank-title">
						<span class="thumb-logo" :style = debtVO.style></span>{{debtVO.nm_fc}}
					</li>
					<label class="label-type">{{debtVO.debt_type}}</label>
				</div>
				<div class="list-info">
					<dl>
						<dt>상환금액(당월)</dt>
						<dd class="txt-strong">{{debtVO.cur_mm_amt_repay}}</dd>
					</dl>
					<dl>
						<dt>대출잔액</dt>
						<dd>{{debtVO.amt_remain}}</dd>
					</dl>
					<dl>
						<dt>금리</dt>
						<dd>{{debtVO.ever_interest}} %</dd>
					</dl>
					<div class="progress-group progress-bar-sum">
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="progress-label">
							<label class="label">원금상환비율<a role="button" data-toggle="collapse" data-layer="popup" href="#help-sub-sum" class="icon-link"><i class="pop-info ico-alert">정보</i></a></label>
							<span>{{debtVO.rate_amt_contract}} %</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-content">
			<ul class="nav nav-outline nav-justified tabs">
				<li class="active"><a href="#tab2">계약정보</a></li>
				<li><a href="#tab1">상환정보</a></li>
			</ul>
			<div class="tab-pane active" id="tab2">
				<!-- tab2 -->
				<div class="container-fluid">
					<div class="cont-cube">
						<ul>
							<li>
								<dl>
									<dt>대출구분</dt>
									<dd>{{debtVO.type_deal}}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>개설일자</dt>
									<dd>{{debtVO.ymd_loan}}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>만기일자</dt>
									<dd>{{debtVO.ymd_loanend}}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>금리</dt>
									<dd>{{debtVO.ever_interest}}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출원금</dt>
                                    <dd>{{debtVO.amt_contract}} 만원</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출기간</dt>
									<dd>{{debtVO.term_all}} 개월</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>상환방식</dt>
									<dd>{{debtVO.rep_method}}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출잔액</dt>
                                    <dd>{{debtVO.amt_remain}} 만원</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>잔여기간</dt>
									<dd>{{debtVO.term_remain}} 개월</dd>
								</dl>
							</li>
						</ul>
					</div>
				</div>
				<div class="container">
					<p>* 금리는 핀셋에서 추정한 값으로 실제와 차이가 발생할 수 있습니다.</p>
				</div>
				<!-- //tab2 -->
			</div>
			<div class="tab-pane" id="tab1">
				<!-- tab1 -->
				<div class="container-fluid">
                    <div class="repay-status">
                        <span class="label label-info">정상</span>
                        <span class="label label-danger">연체</span>
                        <span class="label label-default">기타</span>
                    </div>
                    <div class="repay-date-area">
                        <div class="row">
                            <div id="collapseExample_${i}" class="collapse popup-layer">
                                <div class="popup-layer-header"><span>${oneMonth.req_yyyymm}</span></div>
                                <span class="title">원리금</span>
                                <p>${amt_repay}</p>
                                <p><span><strong>원금</strong> : ${amt_repay_p }</span>
                                    / <span><strong>이자</strong> : ${amt_repay_i }</span></p>
                                <button data-toggle="collapse" data-target="#collapseExample_${i}" aria-expanded="false" aria-controls="collapseExample_${i}" class="btn-close"><i class="icon-close">레이어 닫기</i></button>
                            </div>
                            <div class="col-xs-3">
                                <div data-toggle="collapse" href="#collapseExample_${i}" aria-expanded="false" aria-controls="collapseExample_${i}" class="repay-date ${status_color}" data-layer="popupMonth">
                                    <span>${oneMonth.req_yyyymm}</span>
                                </div>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</section>
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
    this.$parent.isBottom = false;
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
        .get("/m/debt/getDebtInfo.json", {
          params: { no_manage_info: thisObj.$store.state.no_manage_info }
        })
        .then(function(response) {
          var vo = response.data.debtVO;
          vo.style =
            "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc=" +
            vo.cd_fc +
            "')";
          thisObj.debtVO = vo;
        });
    },
    menuToggle() {
      $("#dropdown-menu").toggle();
    },
    updateDebt() {
      var thisObj = this;

      router.push({
        name: "debtUpdate",
        params: { debtVO: thisObj.debtVO }
      });
    },
    deleteDebt() {
      var thisObj = this;

      this.$http
        .get("/m/debt/deleteDebt.json", {
          // params: { no_manage_info: thisObj.$route.params.no_manage_info }
          params: { no_manage_info: thisObj.$store.state.no_manage_info }
        })
        .then(function(response) {
          var code = response.data.code;
          if (code == "00") {
            thisObj.$toast.center("저장에 성공했습니다.");
            setTimeout(function() {
              router.push("/debt/main");
            }, 700);
          } else {
            thisObj.$toast.center("저장에 실패하였습니다.");
          }
        });
    },
    goMemo() {
      router.push("/memo/main");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
