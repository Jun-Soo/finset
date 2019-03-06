<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>금융사 테스트용</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {

				$('body').layout({
					minSize : 40

					,
					north__spacing_open : 0,
					north__resizable : false,
					south__spacing_open : 0,
					south__resizable : false

					,
					spacing_open : 5,
					spacing_closed : 5,
					togglerLength_open : 50,
					togglerLength_closed : "100%"

					,
					initClosed : true,
					north__initClosed : false
				});

				$(".toggle-panel").each(
						function(i) {
							$(this).find(".h-sec > a").click(
									function() {
										$(this).toggleClass("closed");
										$(this).closest(".toggle-panel").find(
												".toggle-cont").toggle("blind",
												200);
									});
						});

				window.setupValidateForm(frmMangeFinance);

				$('.selectpicker').selectpicker();
			});

	function sendFinanceInfo() {

		var data = frmMangeFinance.ajaxSubmit();

		alert(data);

		if (data == null)
			return false;
		//var data = {"id_board":id_board};

		vLoad("financeInfo", "<c:url value='/finance/listFinanceInfo.crz'/>",
				data);
	}
	
	function sendFinanceInfo_1() {

		var data = frmMangeFinance.ajaxSubmit();

		alert(data);

		if (data == null)
			return false;
		//var data = {"id_board":id_board};

		vLoad("financeInfo", "<c:url value='/finance/listFinanceInfo_1.crz'/>",
				data);
	}
	
	function sendFinanceInfo_2() {

		var data = frmMangeFinance.ajaxSubmit();

		alert(data);

		if (data == null)
			return false;
		//var data = {"id_board":id_board};

		vLoad("financeInfo", "<c:url value='/finance/listFinanceInfo_2.crz'/>",
				data);
	}

	function sendFinanceSendInfo() {

		var data = frmMangeFinance.ajaxSubmit();

		if (data == null)
			return false;
		//var data = {"id_board":id_board};

		vLoad("financeInfo", "<c:url value='/finance/listFcSendMsgInfo.crz'/>",
				data);
	}
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/include/header.jsp"%>
	<div class="ui-layout-center">
		<div class="ui-layout-content">
			<!-- Title -->
			<div class="h-title">
				<h1 class="pull-left">금융사 테스트용</h1>
				<ol class="breadcrumb pull-right">
					<!-- 네비게이션 -->
					<li><a href="<c:url value="/"/>"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
					<li>금융사 테스트용</li>
					<li class="active">금융사 테스트용</li>
				</ol>
			</div>

			<!-- 본문 -->
			<div class="panel panel-default toggle-panel">
				<div class="panel-heading">
					<h3 class="h-sec pull-left">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span><a
							href="#none"> 고객 정보 입력란</a>
					</h3>
				</div>
				<div id="formFinance">
					<div class="panel-collapse toggle-cont">
						<form name="frmMangeFinance" id="frmMangeFinance"
							enctype="multipart/form" class="navbar-form">
							<table id="manageFinance" class="table table-bordered"
								cellspacing="0" width="100%">
								<colgroup>
									<col width="50%">
									<col width="50%">
								</colgroup>
								<thead>
									<tr>
										<th>금융기관신청번호</th>
										<th>주민등록번호</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" class="form-control"
											name="financeNo" id="financeNo" value="2017052211"
											validate="required;label:금융기관신청번호; maxbt:10;" maxlength="10" />
											<!-- value="${agencyVO.id_agency}" --></td>
										<td><input type="text" class="form-control" name="rnno"
											id="rnno" value="1219845017101"
											validate="required;label:주민등록번호; maxbt:13;" maxlength="13" />
											<!-- value="${agencyVO.id_agency}" --></td>
									</tr>
								</tbody>
							</table>
							<div class="align-r">
								<button type="button" class="btn btn-primary btn-xs"
									onclick="sendFinanceInfo_1();">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									대출 상품 목록 조회 이지론1샘플
								</button>
								<button type="button" class="btn btn-primary btn-xs"
									onclick="sendFinanceInfo_2();">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									대출 상품 목록 조회 이지론2샘플
								</button>
								<button type="button" class="btn btn-primary btn-xs"
									onclick="sendFinanceInfo();">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									대출 상품 목록 조회 이지론3샘플
								</button>
								
								<button type="button" class="btn btn-primary btn-xs"
									onclick="sendFinanceSendInfo();">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									대출 상품 목록 조회1
								</button>
								
							</div>
						</form>
					</div>
				</div>
			</div>

			<div id="financeInfo">
				<%@ include file="/WEB-INF/views/finance/listFinanceInfo.jsp"%>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>