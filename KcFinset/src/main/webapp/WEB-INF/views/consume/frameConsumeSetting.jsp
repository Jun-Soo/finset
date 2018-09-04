<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var dt_basic = "<c:out value='${personSetInfoVO.dt_basic}'/>";
	if((dt_basic||"")==""){
		$(".selectpicker").selectpicker();
	} else {
		$(".selectpicker").selectpicker("val",dt_basic);
	}
	
	
	$("#dt_basic").on("change",function(){
		modifyPersonSetInfo();
	});
	
	$("#yn_installment").on("change",function(){
		modifyPersonSetInfo();
	});
});

var goBudget = function(){
	frmConsumeSetting.action = "<c:url value='/m/consume/frameConsumeBudget.crz'/>";
	frmConsumeSetting.submit();
}

var goCategory = function(){
	frmConsumeSetting.action = "<c:url value='/m/consume/frameConsumeCategory.crz'/>";
	frmConsumeSetting.submit();
}

var modifyPersonSetInfo = function(){
	var data = "dt_basic="+$("#dt_basic").val()+"&yn_installment="+($("#yn_installment").is(":checked")?'Y':'N');
	$.ajax({
		url : "<c:url value='/m/consume/modifyPersonSetInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			toastMsg('변경사항이 저장되었습니다.');
		},
		error : function(e) {
			toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
		}
	});
}
</script>
</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
				</div>
				<h1>부채관리</h1>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<form name="frmConsumeSetting" id="frmConsumeSetting">
				<div class="container dt_basic">
					<div class="head form-group">
						<label for="dt_basic">기준일</label>
						<select class="selectpicker" data-header="기준일" name="dt_basic" id="dt_basic">
							<option value="1">매월 1일</option>
							<option value="2">매월 2일</option>
							<option value="3">매월 3일</option>
							<option value="4">매월 4일</option>
							<option value="5">매월 5일</option>
							<option value="6">매월 6일</option>
							<option value="7">매월 7일</option>
							<option value="8">매월 8일</option>
							<option value="9">매월 9일</option>
							<option value="10">매월 10일</option>
							<option value="11">매월 11일</option>
							<option value="12">매월 12일</option>
							<option value="13">매월 13일</option>
							<option value="14">매월 14일</option>
							<option value="15">매월 15일</option>
							<option value="16">매월 16일</option>
							<option value="17">매월 17일</option>
							<option value="18">매월 18일</option>
							<option value="19">매월 19일</option>
							<option value="20">매월 20일</option>
							<option value="21">매월 21일</option>
							<option value="22">매월 22일</option>
							<option value="23">매월 23일</option>
							<option value="24">매월 24일</option>
							<option value="25">매월 25일</option>
							<option value="26">매월 26일</option>
							<option value="27">매월 27일</option>
							<option value="28">매월 28일</option>
							<option value="29">매월 29일</option>
							<option value="30">매월 30일</option>
							<option value="31">매월 31일</option>
						</select>
					</div>
				</div>
				<div>
					<div class="setting_consume container">
						<div>
							<h2 class="h2">지출</h2>
						</div>
						<div class="container-fluid">
							<div class="list-group">			
								<a onclick="goBudget();" class="list-group-item">
									<h3 class="h3">지출 예산 설정</h3>
								</a>
								<a onclick="goCategory();" class="list-group-item">
									<h3 class="h3">지출 카테고리 설정</h3>
								</a>
								<div id="installment" class="list-group-item">
									<h3 class="h3">할부 개월 수 월별 분할</h3>
									<div class="ui-switch">
										<label data-form-control="toggle" class="pull-right">
											<input type="checkbox" name="yn_installment" id="yn_installment"
												<c:if test="${personSetInfoVO.yn_installment eq 'Y'}">checked="checked"</c:if>
											/>
											<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
										</label>
									</div>
								</div>				
							</div>
						</div>
					</div>
					<div class="setting_income container">
						<div>
							<h2 class="h2">수입</h2>
						</div>
						<div class="container-fluid">
							<div class="list-group">
								<a onclick="" class="list-group-item">
									<h3 class="h3">수입 카테고리 설정</h3>
								</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
