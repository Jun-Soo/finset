<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Crizen Loan Counsel Agency</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({
			minSize:				40
		,	west__size:				200 //좌측 width
		,	east__size:				200 //우측 width
		
		,	north__spacing_open:	0
		,	north__resizable:		false
		,	south__spacing_open:	0
		,	south__resizable:		false
		
		,	spacing_open:			5
		,	spacing_closed:			5
		,   togglerLength_open:     50
		,   togglerLength_closed:   "100%"
		
		//,  initClosed:       		true
		,  north__initClosed: 		false
		,  south__initClosed: 		false
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	// 확장검색조건 보이기
	$("#btn-expand").click(function(){
		$(this).toggleClass("open");
		$("#divExpandSearch").toggle("blind", 200);
	});
	
});
</script>
</head>
<body>

<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- //Layout-North -->
<div class="ui-layout-west">
	<div class="ui-layout-content">
	<!-- Content -->
		Outer West
	<!-- //Content -->
	</div>
</div>
<div class="ui-layout-center">
	<div class="ui-layout-content">
	<!-- Content -->
		Outer Center
	<!-- //Content -->
	</div>
</div>
<div class="ui-layout-east">
	<div class="ui-layout-content">
	<!-- Content -->
		Outer East
	<!-- //Content -->
	</div>
</div>
<!-- Layout-South -->
<div class="ui-layout-south">
	<div class="ui-layout-content">
	<!-- Content -->
		Outer South
	<!-- //Content -->
	</div>
</div>
<!-- //Layout-South -->

</body>
</html>