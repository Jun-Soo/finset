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
var totalPage;
$(document).ready(function() {
	 totalPage = "${pagedList.pageCount}";
	$(window).scroll(function(){
		if($(document).height() <= $(window).scrollTop() + $(window).height()){
				if(totalPage > pageCnt){
					  jumpPage();
				}
			  pageCnt++;
		  }
		});

	$("#page").val(1);
	
	$("#txt_detail").keydown(function (key) {
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
        	
        	if(frmFaqSearch.txt_detail.value.length >= 2){
        		goSearch();
        	} else {
        		toastMsg('검색어를 두자이상 입력하세요.');
        	}
        }
    });
});

function setTotalPageCnt(e){
	totalPage = e;
}
//리스트 조회
function goSearch() {
	$("#page").val(1);
	loadFaqSearch();
}
//페이지 이동
function jumpPage(){
	var pageIndex = Number($("#page").val());
	$("#page").val(pageIndex + 1);
	var data = {txt_detail : $('#txt_detail').val(), page : $('#page').val(), totalPage : $('#totalPage').val()};
	if(data == null){
		return false;
	}
	if(userAgent == "Android") {
		window.Android.loding('Y');
	}
	else if(userAgent == "iOS") {
		Jockey.send("showLoading");
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/listFaqSearch.crz'/>",
		data : data,
		async: true,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			$("#listFaqSearch").append(result);
			if(userAgent == "Android") {
				window.Android.loding('N');
			}
			else if(userAgent == "iOS") {
				Jockey.send("stopLoading");
			}
		},
		error : function (e) {
			errMsg(e);
		}
	});
}

function loadFaqSearch(){
	var data = {txt_detail : $('#txt_detail').val(), page : $('#page').val(), totalPage : $('#totalPage').val()};
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/m/customercenter/listFaqSearch.crz'/>",
		data : data,
		async: true,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			pageCnt = 1;
			$("#listFaqSearch").empty();
			$("#listFaqSearch").append(result);
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
	<form method="post" name="frmFaqSearch" id="frmFaqSearch" onsubmit="return false;">
	<input type="hidden" name="page" id="page"/>
	<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
	
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>자주묻는질문</h1>
		</div>
	</header>
	<div class="search-block affix-fixed">
		<div class="form-group has-search">	
			<label for="" class="sr-only">자주묻는 질문 검색</label>			
			<input type="search" class="form-control" maxlength="255" name="txt_detail" id="txt_detail" value="${boardForm.txt_detail }" placeholder="자주묻는 질문 검색" autocomplete="off">
		</div>
	</div>
	<!-- Content -->
	<div id="content" class="faq">
		<!-- Content -->
		<section id="content">
			<div id="listFaqSearch">
				<%@ include file="/WEB-INF/views/customercenter/sub/listFaqSearch.jsp"%>
			</div>
		</section>
		
		<!-- //Content -->
		
		
	</div>
	<!-- //Content -->
	</form>
</div>
</body>
</html>
