<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/theme/timepicker.css"/>"/>
<script src="<c:url value="/script/jquery/timepicker.min.js"/>"></script>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>영업일 관리</title>
<%-- <%@ include file="/WEB-INF/include/headComm.jsp"%> --%>
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
	$("#listConfBusinessDay").load("<c:url value='/business/listConfFcBusinessDay.crz'/>",{year:year});
}
var arr_date = "";
function procFcBusinessDay(cd_fc){
	if (!formBusiness.validateForm()) return false;
	arr_date = "";
	strDate($('#day').datepicker('getDates'));
	
	$("#arr_ymd").val(arr_date);
	var frm = document.formBusiness;
	formBusiness.cd_fc.value = cd_fc;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/business/procFcBusinessDay.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);

			var search = {"cd_fc":formBusiness.cd_fc.value};
			vLoad("listConfFcBusinessDay","<c:url value='/business/listFcBusinessDay.crz'/>",search);
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
			arr_date = year + month + day;
 	}
	
}
function deleteFcBusinessDay(cd_fc) {
	if(confirm("삭제하시겠습니까?") == false) return false;
 	if (!formBusiness.validateForm()) return false;
	formBusiness.cd_fc.value = cd_fc;
	var data = {
		"cd_fc" : cd_fc,
		"ymd" : $('#date_piker').val()
	};
	if (data == null)
		return false;

	$.ajax({
		url : "<c:url value='/business/deleteFcBusinessDay.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			var returnData = result.returnData;
			alert(returnData.message);
			//refresh;
			var search = {"cd_fc":formBusiness.cd_fc.value};
			vLoad("listConfFcBusinessDay","<c:url value='/business/listFcBusinessDay.crz'/>",search);
		},
		error : function(e) {
			alert(e.responseText);
		}
	});

}

</script>
<style>
#left-box {
  float: left;
  width: 80%;
}
#right-box {
  float: right;
  width: 20%;
}
</style>
</head>
<body>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
			<h3 class="h-sec pull-left">
				<a href="#none">금융사 휴일 지정 및 시스템 차단 설정</a>
			</h3>
			<span class="pull-right">
				<button type="button" class="btn btn-default btn-xs" onclick="procFcBusinessDay('${fincorpInfo.cd_fc}');">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
				</button>
				<button type="button" class="btn btn-warning btn-xs" onclick="deleteFcBusinessDay('${fincorpInfo.cd_fc}');">
						<span class="glyphicon" aria-hidden="true"></span> 삭제
				</button>
			</span>
		</div>
<!-- Layout-Center -->
<div class="ui-layout-noth">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
	<!-- Alert -->
	<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
		<ul>
			<li>시스템 <strong>영업일</strong>을 확인할 수 있는 페이지입니다.</li>
		</ul>
	</div>
	<!--//Alert -->
	
	<div id="listConfFcBusinessDay">
		<%@ include file="/WEB-INF/views/fincorp/listBusinessDay.jsp"%>
	</div>
	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>