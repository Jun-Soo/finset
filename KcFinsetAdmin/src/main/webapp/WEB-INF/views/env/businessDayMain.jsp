<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>영업일 관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var  thisDate = new Date();
	$("#cd_yyyy_date").append($("<option></option>").text("[년도]"));
	var  num = thisDate.getFullYear() - 5; 
	for(var i = 0; i<11; i++){
		$("#cd_yyyy_date").append($("<option></option>").val(num+i).text((num+i)));
	}
	$("#cd_yyyy_date").val(thisDate.getFullYear());
	$("#cd_yyyy_date").attr('class','selectpicker');
});

function changeYear(year) {
	$("#listConfBusinessDay").load("<c:url value='/business/listConfBusinessDay.crz'/>",{year:year});
}

var arr_date = "";
function procBusinessDay(){
	arr_date = "";
	strDate($('#jan').datepicker('getDates'));
	strDate($('#feb').datepicker('getDates'));
	strDate($('#mar').datepicker('getDates'));
	strDate($('#apr').datepicker('getDates'));
	strDate($('#may').datepicker('getDates'));
	strDate($('#jun').datepicker('getDates'));
	strDate($('#jul').datepicker('getDates'));
	strDate($('#aug').datepicker('getDates'));
	strDate($('#sep').datepicker('getDates'));
	strDate($('#oct').datepicker('getDates'));
	strDate($('#nov').datepicker('getDates'));
	strDate($('#dec').datepicker('getDates'));
	
	$("#arr_ymd").val(arr_date);
	var frm = document.formBusiness;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/business/procBusinessDay.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			$("#rt_msg").html(returnData.message);
			
			$(".alert.rt").fadeTo(2000, 2000).slideUp(500, function(){
				$("alert.rt").alert('close');
			});
			
			$("#listConfBusinessDay").load("<c:url value='/business/listConfBusinessDay.crz'/>");
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function strDate(dates){
	
	if(dates == "")
		return "";
	
	for(var i=0;i<dates.length;i++) {
		var year = String(dates[i].getFullYear());
	    var month = String(dates[i].getMonth()+1);
	    var day = String(dates[i].getDate());
	    
	    if ( month.length <= 1 )
			month = "0" + month;
		if ( day.length <= 1 )
			day = "0" + day;
	    
		if(arr_date == "")
			arr_date = year + month + day;
		else
			arr_date += "," + year + month + day;
	}
	
}

</script>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->


<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
	<!-- title -->
	<div class="h-title">
		<h1 class="pull-left">영업일 관리</h1>
		<ol class="breadcrumb pull-right">
			<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
			<li><a href="#">환경관리</a></li>
			<li class="active">영업일관리</li>
		</ol>
	</div>
	<!--// title -->
	
	<!-- Alert -->
	<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
		<ul>
			<li>시스템 <strong>영업일</strong>을 확인할 수 있는 페이지입니다.</li>
			<li>평일을 선택 후 적용을 누르면 휴일로 지정되며, 휴일을 선택 후 적용을 누르면 평일로 지정됩니다.</li>
		</ul>
	</div>
	<div class="alert rt alert-success alert-dismissible exclamation-list" role="alert" style="display: none;">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
		<ul>
			<li id="rt_msg"></li>
		</ul>
	</div>
	<!--//Alert -->
	
	<!-- Search -->
	<div class="srch">
		<form name = "formBusiness" class="navbar-form">
			<input type="hidden" name="arr_ymd" id="arr_ymd">
			<div class="form-group" id="sandbox-container">
				<select id="cd_yyyy_date" name="cd_yyyy_date" data-style="btn-primary" onchange="changeYear(this.value);">
				</select>
				<strong>년도 영업일 관리</strong>
				
				<!-- <button type="button" class="btn btn-default" onclick=""><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button> -->
				<button type="button" class="btn btn-warning btn-xs" onclick="procBusinessDay();"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> 적용</button>
			</div>
		</form>
	</div>
	<!--// Search -->
	
	<div id="listConfBusinessDay">
		<%@ include file="/WEB-INF/views/env/listBusinessDay.jsp"%>
	</div>
	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>