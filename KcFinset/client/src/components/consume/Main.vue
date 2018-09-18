<template>
<div id="wrapper">
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<div class="g-menu">
				<button id="setting" type="button" style="color: white;">설정</button>
				<button id="delete" type="button" style="color: white;">삭제</button>
				<button id="save" type="button" style="color: white;">저장</button>
			</div>
			<h1>지출관리</h1>
		</div>
	</header>
	<section id="content">
		<div id="consume_list">
				<div class="sum-block consume_title">
				<div class="calendar_head">
					<form id="frmConsumeList" name="frmConsumeList" method="POST">
						<span id="prevMM" v-on:click="setPrevMM()">&lt;</span><input type="text" id="ym" name="ym" readonly="readonly" v-model="ym"/><span id="nextMM" v-on:click="setNextMM()">&gt;</span>
					</form>
				</div>
				<div class="sum-block-items">
					<div class="row">
						<dl class="col-xs-6">
							<dt>수입</dt>
							<dd id="text_income">{{income}} 원</dd>
						</dl>
						<dl class="col-xs-6">
							<dt>지출</dt>
							<dd id="text_consume">{{consume}} 원</dd>
						</dl>
					</div>
					<div class="progress-group progress-bar-sum">
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="progress-label">
							<label class="label">예산대비 소비율</label>
							<span>20%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="container consume_condition">
				<div id="trade_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
					<button type="button" id="btn_income" name="btn_income" class="btn btn-check active">수입</button>
					<button type="button" id="btn_consume" name="btn_consume" class="btn btn-check active">지출</button>
				</div>
			</div>
			<div class="list-group">
				<div :class="vo.class" v-for="vo in consumeList" :key="vo.index">
					<input type="hidden" class="means_consume" value="${vo.means_consume}"/>
					<input type="hidden" class="tm_trd" value="${vo.tm_trd}"/>
					<input type="hidden" class="nm_type" value="${vo.nm_type}"/>
					<input type="hidden" class="full_dt_trd" value="${vo.dt_trd}"/>
					<input type="hidden" class="cd_class" value="${vo.cd_class}"/>
					<input type="hidden" class="memo" value="${vo.memo}"/>
					<input type="hidden" class="grade" value="${vo.grade}"/>
					<div class="consume_head">
						<span class="dt_trd">{{vo.dt_trd}}</span>
						<span class="contents">{{vo.contents}}</span>
						<span class="amt_in_out">{{vo.amt_in_out}}</span>
					</div>
					<div class="consume_tail">
						<span class="subcontent_left">{{vo.nm_class}}</span>
						<span class="subcontent_right">{{vo.nm_card}}</span>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		<div id="consume_detail" class="div_hidden">
			<div class="container">
				<form id="frmConsumeInfo" name="frmConsumeInfo" method="post">
					<div class="form-inline">
		                <div class="form-group">
		                    <label for="means_consume" class="label_means_consume">결제 수단</label>
		                    <select class="selectpicker" data-header="결제 수단" name="means_consume" id="means_consume">
		                    	<option value="03">입출금 계좌</option>
		                    	<option value="01">카드</option>
		                    	<option value="02">현금</option>
		                    </select>
		                    <input type="hidden" class="form-control" name="an" id="an" autocomplete="off"/>
		                </div>
		                <div class="form-group">
		                    <label for="amt_in_out">금액</label>
		                    <input type="number" class="form-control" name="amt_in_out" id="amt_in_out" autocomplete="off"/>
		                    <span class="form-control-feedback" aria-hidden="true">원</span>
		                </div>
		                <div class="form-group">
		                    <label for="contents">결제처</label>
		                    <input type="text" class="form-control" name="contents" id="contents" autocomplete="off"/>
		                </div>
		                <div class="form-group">
		                    <label for="category">카테고리</label>
		                    <select class="selectpicker" data-header="카테고리" name="category" id="category">
								<c:forEach var="subList" items="${listPersonConsumeClassInfo}">
									<c:forEach var="vo" items="${subList }" varStatus="myIndex">
										<c:if test="${myIndex.index eq 0}">
											<option value="${vo.cd_class}">${vo.nm_class}</option>
										</c:if>
									</c:forEach>
									</div>
								</c:forEach>
		                    </select>
		                </div>
	  	                <div class="form-group">
		                    <label for="dt_trd">날짜</label>
		                    <input type="text" class="form-control" name="dt_trd" id="dt_trd" autocomplete="off"/>
		                </div>
	  	                <div class="form-group">
		                    <label for="memo">메모</label>
		                    <input type="text" class="form-control" name="memo" id="memo" autocomplete="off"/>
		                </div>
		        	</form>
				</div>
				<div class="btn-group bootstrap-select trans_detail">
					<div class="dropdown-menu open">
						<div class="popover-title">
							<button type="button" class="close" aria-hidden="true">x</button>
							입출금 내역
						</div>
						<ul class="dropdown-menu inner">
						</ul>
					</div>
				</div>
			</div>
		</div>
		-->
	</section>
</div>
</template>

<script>
import router from "@/comm/router.js";

export default {
  name: "ConsumeMain",
  data() {
    return {
      ym: "",
      consumeList: [],
      standardDt: new Date(),
      income: "",
      consume: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listConsumeInfo() {
      var thisObj = this;
      this.$http
        .get("/m/consume/listConsumeInfo.json", {
          params: { ym: thisObj.ym.replace(".", "") }
        })
        .then(function(response) {
          var list = response.data.listConsumeInfo;
          for (var i = 0; i < list.length; i++) {
            list[i].class =
              "list-group-item consume_list " + list[i].type_in_out;
          }
          thisObj.consumeList = list;
          thisObj.income = response.data.income;
          thisObj.consume = response.data.consume;
        });
    },

    formatHead(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    getYm(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    getTodayYm() {
      return this.getYm(new Date());
    },
    setPrevMM() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    },
    setNextMM() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.ym = this.formatHead(this.getYm(this.standardDt));
      this.listConsumeInfo();
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
