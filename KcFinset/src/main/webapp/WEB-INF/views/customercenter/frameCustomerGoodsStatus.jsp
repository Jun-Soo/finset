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
		$("#page").val(1);
        <c:if test="${site ne 'REAL'}">
        //alert("isSuccess=${isSuccess}\nmessage=${message}");
        </c:if>
		if('${isSuccess}' == 'false') {
		    toastMsg('${message}');
        }
	});

	//더보기 버튼 보여주기
	function showAdd(totalPage, currPage){
		if( currPage == totalPage){
			$("#list_add").hide();
		}
	}

	//리스트 조회
	function goSearch() {
		jumpPage();
	}

	// 페이지 이동
	function jumpPage(){
		var pageIndex = Number($("#page").val());
		$("#page").val(pageIndex + 1);

		var data = {"page":$("#page").val()};

		if(data == null){
			return false;
		}

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerGoodsStatus.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listPastLoanHist").append(result);

			},
			error : function (e) {
			}
		});
	}
	
	//뒤로가기
	function goBack(){
		frmGoodsStstus.action = "/m/customercenter/frameCustomerCenterMain.crz";
		frmGoodsStstus.submit();
	}
</script>

</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
				</div>
				<h1>신청/진행현황</h1>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<form name="frmGoodsStstus" id="frmGoodsStstus">
				<input type="hidden" name="page" id="page"/>
				<div id="listPastLoanHist">
					<%@ include file="/WEB-INF/views/customercenter/sub/listCustomerGoodsStatus.jsp"%>
				</div>
				<div class="btn-more-block" id="list_add">
					<button type="button" class="btn btn-block btn-more" onclick="jumpPage();">+ 더보기</button>
				</div>
			</form>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
