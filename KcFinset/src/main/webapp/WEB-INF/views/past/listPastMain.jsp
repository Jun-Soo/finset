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
        if('${isSuccess}' == 'false') {
            toastMsg(${message});
        }
	});
	
	function showAdd(totalPage, currPage){
		//alert("totalPage : " + totalPage + " currPage : " + currPage);
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
			url : "<c:url value='/m/past/listPastHist.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listPastLoanHist").append(result);
				
			},
			error : function (e) {
				errMsg(e);
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
				<h1>이용내역</h1>
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
				</div>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<form name="frmPastList" id="frmPastList" enctype="multipart/form">
				<input type="hidden" name="page" id="page"/>
					<!-- tab1 -->
					<h2 class="h2">신청상품 진행 현황</h2>
					<div class="container">
						<p class="help-block">
							고객님이 신청하신 대출신청 정보를 확인할 수 있습니다.
						</p>
					</div>
					<h2 class="h2">신청상품 진행 현황</h2>
					<div id="listPastLoan"> <!--  class="data-none" -->
						<%@ include file="/WEB-INF/views/past/listPast.jsp"%>
					</div>
					<!-- //tab1 -->
					<div>
						<h2 class="h2">과거 진행 이력</h2>
						<div id="listPastLoanHist">
							<%@ include file="/WEB-INF/views/past/listPastHist.jsp"%>
						</div>
						<div class="btn-more-block" id="list_add">
							<button type="button" class="btn btn-block btn-more" onclick="jumpPage();">+ 더보기</button>
						</div>
					</div>						
			</form>
		</section>
		<!-- //Content -->
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
	</body>
</html>

