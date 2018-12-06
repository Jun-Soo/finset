<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
	});
	
	function updateRealEstateInfo(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loan/updateRealEstateInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanGoodsList.crz'/>"
					frmloanApplyStep.submit();
				} else{
					alert(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	
	//취소버튼 클릭시 대출 초기 페이지로
	function loanApplyCancel(){
		if(!confirm("취소를 하시겠습니까?")) return false;
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanApply.crz'/>"
		frmloanApplyStep.submit();
	}
</script>

</head>           

<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
<input type="hidden" name="no_bunch" id="no_bunch" value="${no_bunch }"/>
<input type="hidden" name="loan_code" id="loan_code" value="${loan_code }"/>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>개인금융</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<ul class="step">
			<li><span>1</span></li>
			<li><span>2</span></li>
			<li class="active"><span>3. 자동차정보등록</span></li>
			<li><span>4</span></li>
		</ul>
		<h2 class="h2">소득정보 입력</h2>
		<div class="container">
			<p class="help-block">
				맞춤금융상품 조회에 사용될 중요한 정보입니다.<br />
				정확하게 내용을 입력하여 주시기 바랍니다.
			</p>
			<div class="form-group">
				<label class="control-label">담보물건주소</label>
				<input type="text" class="form-control" maxlength="50" name="security_addr" value="" />
			</div>
			<div class="form-group">
				<label class="control-label">담보물건층수</label>
				<input type="text" class="form-control" maxlength="3" name="security_floor" value="" />
			</div>
			<div class="form-group">
				<label class="control-label">담보물건면적</label>
				<input type="text" class="form-control" maxlength="8" name="security_exclusive_area" value="" />
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="loanApplyCancel()">취소</button>
			</div>
			<div class="col-xs-6">
				<a role="button" onclick="updateRealEstateInfo();" class="btn btn-lg btn-primary btn-block">다음</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</form>
</body>
</html>
