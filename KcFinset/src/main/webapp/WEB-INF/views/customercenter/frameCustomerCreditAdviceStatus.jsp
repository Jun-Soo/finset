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
	 	var data = frmCreditAdviceStatusList.ajaxSubmit();
	
	 	if(data == null) return false;
	
		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerCreditAdviceStatus.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listCreditAdviceStatus").append(result);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	//상세보기
	function creditAdviceDetail( counsel_seq ){
		frmCreditAdviceStatusList.counsel_seq.value = counsel_seq;
		frmCreditAdviceStatusList.action = "<c:url value='/m/customercenter/frameCustomerCreditAdviceViewResults.crz'/>";
		frmCreditAdviceStatusList.submit();
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
			<h1>신용상담신청</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCreditAdviceStatusList" id="frmCreditAdviceStatusList">
	<input type="hidden" name="counsel_seq" id="counsel_seq" />
	<input type="hidden" name="page" id="page" />
	
	<section id="content">
	<div class="list-group-block status-list" id="listCreditAdviceStatus">
		<%@ include file="/WEB-INF/views/customercenter/sub/listCustomerCreditAdviceStatus.jsp"%>
	</div>
	</section>
	
	</form>
	<!-- //Content -->
</div>
</body>
</html>
