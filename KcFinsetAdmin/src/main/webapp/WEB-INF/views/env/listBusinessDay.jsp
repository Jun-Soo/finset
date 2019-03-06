<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	holidays = [];
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
	
	$('#jan').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 0, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#feb').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 1, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#mar').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 2, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#apr').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 3, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#may').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 4, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#jun').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 5, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#jul').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 6, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#aug').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 7, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#sep').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 8, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#oct').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 9, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#nov').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 10, day: 1 },
		beforeShowDay : nationalDays
	});
	$('#dec').datepicker({
		format: "yyyy-mm-dd",
	    todayHighlight: true,
	    multidate: true,
	    defaultViewDate: { year: "${businessDayForm.year}", month: 11, day: 1 },
		beforeShowDay : nationalDays
	});
	
});

</script>
<div  class="calendar-grid">
	<div class="month" id="jan"></div>
	<div class="month" id="feb"></div>
	<div class="month" id="mar"></div>
	<div class="month" id="apr"></div>
	<div class="month" id="may"></div>
	<div class="month" id="jun"></div>
	<div class="month" id="jul"></div>
	<div class="month" id="aug"></div>
	<div class="month" id="sep"></div>
	<div class="month" id="oct"></div>
	<div class="month" id="nov"></div>
	<div class="month" id="dec"></div>
</div>