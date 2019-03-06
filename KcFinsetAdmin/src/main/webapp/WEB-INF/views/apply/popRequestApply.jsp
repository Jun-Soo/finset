<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>신청서 접수</title>
<script src="<c:url value="/script/jquery/jquery.min.js"/>"></script>
<script type="text/javascript">

$(document).ready(function () {
	
	showLoading();
	frm.no_apply.value = "${no_apply}";
	frm.submit();
	
});

function showLoading(){
	loading = $('<img alt="loading" src="../img/ajax-loader.gif" />').appendTo(document.body).show();
	loading.css({  top: $(document).scrollTop()+ ($(window).height() )/4 + 'px',
      left: ($(window).width() )/4 + 'px',
      'position':'absolute'});
		var height = $(document).scrollTop() + $(window).height();
	 	$div = $('<div id="progressDiv" style="position:absolute; top:0; left:0; width:100%; height:' + height + 'px; background:#000; opacity:.5; filter:alpha(opacity=20); z-index:1000;"></div>');
	 $('body').append($div);
}

</script>
</head>
<body>
<form name="frm" action="<c:url value='/apply/sendApply.json'/>" method="post">
<input type="hidden" name="no_apply"/>
</form>
<!--ui object -->
<div>
	<h4>접수 진행 중...<br/> 창을 닫지 마세요.</h4>
</div>
</body>
</html>