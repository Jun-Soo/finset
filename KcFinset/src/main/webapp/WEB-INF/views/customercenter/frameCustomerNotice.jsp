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
		$("#page").val(1);
	});

	/*
	* name : goSearch
	* desc : 리스트 조회
	* param :
	* output :
	*/
	function goSearch() {
		jumpPage();
	}

	/*
	* name : jumpPage
	* desc : 페이지 이동
	* param :
	* output : 리스트 추가
	*/
	function jumpPage(){
		var pageIndex = Number($("#page").val());
		$("#page").val(pageIndex + 1);

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerNotice.crz'/>",
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listNoticeInfo").empty().append(result);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	//탭클릭
	function noticeTab(gubun){
		
		$("#page").val(1);
		$("#id_board").val(gubun);

		var data = frmListNoticeList.ajaxSubmit();

		if(data == null) return false;

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerNotice.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listNoticeInfo").empty().append(result);
			},
			error : function (e) {
			}
		});
	}
	
	function CustomerNoticeDatail( seq,id_board ){
		frmListNoticeList.seq.value = seq;
		frmListNoticeList.id_board.value = id_board;
		frmListNoticeList.action = "<c:url value='/m/customercenter/frameCustomerNoticeDetail.crz'/>";
		frmListNoticeList.submit();
	}
	
	//뒤로가기
	function goBack(){
		frmListNoticeList.action = "/m/customercenter/frameCustomerCenterMain.crz";
		frmListNoticeList.submit();
	}
	</script>
</head>

<body>
	<div id="wrapper">
	<form method="post" name="frmListNoticeList" id="frmListNoticeList">
		<input type="hidden" name="id_board" id="id_board" value="" />
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="seq" id="seq" value="${seq}"/>

		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
				</div>
				<h1>공지/이벤트</h1>
			</div>
		</header>
		<!-- Content -->
		<div id="content">
		<!-- Content -->
		<section id="content">
			<ul class="nav nav-outline nav-justified tabs affix-fixed top-fixed-item">
				<li class="<c:if test="${boardForm.id_board eq 'notice'}">active</c:if>"><a href="#tab1" onclick="noticeTab('notice');">공지</a></li>
				<li class="<c:if test="${boardForm.id_board eq 'event'}">active</c:if>"><a href="#tab2" onclick="noticeTab('event');">이벤트</a></li>
			</ul>
			<div id="listNoticeInfo">
				<%@ include file="/WEB-INF/views/customercenter/sub/listNotice.jsp"%>
			</div>
		</section>
		<!-- //Content -->
		</div>
		<!-- //Content -->
	</form>
	</div>
</body>
</html>
