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

	//페이지 이동
	function jumpPage(){
		var pageIndex = Number($("#page").val());
		$("#page").val(pageIndex + 1);
		var data = frmAssets.ajaxSubmit();
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
			url : "<c:url value='/m/assets/listAssetsEtc.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listAssetsEtc").append(result);
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
	
	function goGoodsMain(){
		frmAssets.action = "<c:url value='/m/assets/frameAssetsManagementMain.crz'/>";
		frmAssets.submit();
	}
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goGoodsMain();">뒤로가기</button>
			</div>
			<h1>기타</h1>
		</div>
	</header>
	<!-- Content -->
	<form id="frmAssets" name="frmAssets">
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
	</form>
	<section id="content">
		<div class="chart" id="chart_bar"></div>
		
		<div id="listAssetsEtc">
			<%@ include file="/WEB-INF/views/assets/sub/listAssetsEtc.jsp"%>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
