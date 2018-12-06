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
	var pageCnt = 1;
	$(document).ready(function() {
		var totalPage = "${pagedList.pageCount}";
		$(window).scroll(function(){
			if($(document).height() <= $(window).scrollTop() + $(window).height()){
					if(totalPage > pageCnt){
						  jumpPage();
					}
				  pageCnt++;
			  }
			});

		$("#page").val(1);
	});
	
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
			url : "<c:url value='/m/customercenter/listNotification.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listNoti").append(result);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
		
	//탭클릭
	function notificationTab(push_divcd){
		
		$("#page").val(1);
		$("#push_divcd").val(push_divcd);

		var data = {"page":$("#page").val(), "push_divcd":$("#push_divcd").val()};

		if(data == null) return false;

		$.ajax({
			url : "<c:url value='/m/customercenter/listNotification.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listNoti").empty().append(result);
			},
			error : function (e) {
			}
		});
	}
	
	function NotiSet(){
		frmNotiList.action="<c:url value='/m/customercenter/frameCustomerNotificationSetting.crz'/>";
		frmNotiList.submit();
	} 

	
</script>

</head>
<body>
<div id="wrapper">
<form method="post" name="frmNotiList" id="frmNotiList">
	<input type="hidden" name="push_divcd" id="push_divcd" value ="" />
	<input type="hidden" name="page" id="page"/>
	<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
	<input type="hidden" name="rnum" id="rnum"/>
	
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>알림내역</h1>
			<div class="g-menu">
				<button type="button" class="ico ico-notiset" onclick="NotiSet();">알림설정</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<ul class="nav nav-outline nav-justified tabs affix-fixed top-fixed-item">
			<li class="active"><a href="#tab1" onclick="notificationTab('02');">신용관리</a></li>
			<li><a href="#tab2" onclick="notificationTab('03');">부채관리</a></li>
			<!-- <li><a href="#tab3">부채</a></li>
			<li><a href="#tab4">상품</a></li> -->
		</ul>
		<div class="tab-content">
				<div id="listNoti" class="tab-pane active">
					<%@ include file="/WEB-INF/views/customercenter/sub/listNotification.jsp"%>
				</div>
<!-- 			<div class="tab-pane" id="tab2"> -->
<!-- 				신용관리 -->
<!-- 				<div class="list-group thumb-item"> -->
<!-- 					<h2 class="h2 h-date">17.10.17</h2> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-search"> -->
<!-- 						<strong>신용조회 발생</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-debtcard"> -->
<!-- 						<strong>신용개설정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-trade"> -->
<!-- 						<strong>대출거래정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-secured"> -->
<!-- 						<strong>채무보증정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-warning"> -->
<!-- 						<strong>금융질서문란정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-warning"> -->
<!-- 						<strong>공공정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-warning"> -->
<!-- 						<strong>채무불이행정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-warning"> -->
<!-- 						<strong>채무불이행정보 변동(신용정보사)</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-credit label-credit-warning"> -->
<!-- 						<strong>단기연체정보 변동</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 				//신용관리	 -->
<!-- 				부채관리 -->
<!-- 				<div class="list-group thumb-item"> -->
<!-- 					<h2 class="h2 h-date">17.10.17</h2> -->
<!-- 					<a href="#" class="list-group-item label-debt label-debt-open"> -->
<!-- 						<strong>대출계좌 개설</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-debt label-debt-end"> -->
<!-- 						<strong>대출계좌 해지</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 					<a href="#" class="list-group-item label-debt label-debt-repay"> -->
<!-- 						<strong>상환 예정 원리금(이달)</strong> -->
<!-- 						<p>신한은행</p> -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 				//부채관리 -->
<!-- 			</div> -->
		</div>
	</section>
	<!-- //Content -->
</form>
</div>
</body>
</html>
