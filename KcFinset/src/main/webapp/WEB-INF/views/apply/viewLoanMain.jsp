<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>대출 진행 상황 조회</title>
 <%@ include file="/WEB-INF/include/headComm.jsp"%> 
</head>
<body>
<form name="frmFinsetLoan">
	<input type="hidden" name="no_person" /> 
	<input type="hidden" name="no_prepare" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<h3>[OO은행]직장인 신용대출</h3>
						<table class="table table-bordered">
							<tr>
								<td>그림</td>
								<td>*한도 : 000 ~ 0,000천원<br> *금리 : 00% ! 00.00%<br> *상환기간 : 00~00개월<br>
									*상품설명 : 직장인을 위한 신용대출입니다.</td>
								<td>
									<button type="button" class="btn btn-default" onclick="createPrepareInfo()">신청하기
									</button>
									<br>
									<button type="button" class="btn btn-default" >상품설명
									</button>
								</td>
							</tr>
						</table>
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
		</div>
	</div>
	
		
	</form>
	<script>
function createPrepareInfo() {
		var data = frmFinsetLoan.ajaxSubmit();
		$.ajax({
			url : "<c:url value='/prepare/createPrepareInfo.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function () {
				alert('createPrepareInfo.');
				createApplyFinset();
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
function createApplyFinset() {
	var data = frmFinsetLoan.ajaxSubmit();
	$.ajax({
		url : "<c:url value='/apply/createApplyFinset.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function () {
			alert('createApplyFinset.');
		},
		error : function (e) {
			errMsg(e);
		}
	});
}
	</script>
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>