<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css link -->
<link rel="stylesheet" href="<c:url value="/style/theme/jquery-ui.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/theme/layout-default-latest.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/theme/dataTables.bootstrap.css"/>"/>

<link rel="stylesheet" href="<c:url value="/style/dataTables/dataTables.responsive.css"/>"/>


<link rel="stylesheet" href="<c:url value="/style/theme/bootstrap-select.css"/>" />
<link rel="stylesheet" href="<c:url value="/style/theme/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/bootstrap-theme-xe.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/theme/bootstrap-datepicker.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/jstree/style.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/style.css"/>"/>
<link rel="stylesheet" href="<c:url value="/style/skins/${ufn:getNvlCodeName('_CONF_SYSTEM','MAIN_COLOR','blue')}.css"/>"/>

<!-- js include -->
<script src="<c:url value="/script/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/script/jquery/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/script/jquery/jquery.layout-latest.js"/>"></script>
<script src="<c:url value="/script/jquery/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/script/jquery/jquery.tablednd.js"/>"></script>


<script src="<c:url value="/script/dataTables/dataTables.responsive.min.js"/>"></script>


<script src="<c:url value="/script/jquery/jquery.form.js"/>"></script>
<script src="<c:url value="/script/jquery/jquery.hotkeys.js"/>"></script>
<script src="<c:url value="/script/bs/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/script/bs/bootstrap-select.min.js"/>"></script>
<script src="<c:url value="/script/bs/bootstrap.min.js"/>"></script>
<script src="<c:url value="/script/bs/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/script/jstree/jstree.min.js"/>"></script>
<script src="<c:url value="/script/comm.js"/>"></script>
<script src="<c:url value="/script/quickYmd.js"/>"></script>

<script type="text/javascript">
var holidays = [];
// 영업일 목록 load
$(document).ready(function(){
	
	$.ajax({
		type : "post",
		url : "<c:url value='/business/listBusinessDay.json'/>",
		async : false,
		dataType : "json",
		success : function(data){
			$.each(data.List, function (index, item) {
				var arr = [item.substring(0, 4), item.substring(4, 6), item.substring(6, 8)];
				holidays.push(arr);
			});
		}
	});
	
	//datepicker
	$('.input-daterange').datepicker({
		format: "yyyy-mm-dd", //dateformat
		todayHighlight: true, //당일 표시
		todayBtn: "linked", //today button link 활성화
		autoclose: true, //달력 자동 닫힘
		beforeShowDay : nationalDays
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	
	// 확장검색조건 보이기
	$("#btn-expand").click(function(){
		$(this).toggleClass("open");
		$("#divExpandSearch").toggle("blind", 200);
	});
	
});

//공휴일 체크
function nationalDays(date) {
	for (var i = 0; i < holidays.length; i++) {
		if (date.getFullYear() == holidays[i][0] && date.getMonth() == holidays[i][1] - 1
		                    && date.getDate() == holidays[i][2] || date.getDay() == 0 || date.getDay() == 6) { 
			return "date-holiday";
		}
	}
	return "";
}
</script>