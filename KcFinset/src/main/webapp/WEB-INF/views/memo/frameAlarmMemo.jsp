<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
/*
날짜: 20180509~20180511
수정자: 김휘경
수정내용: 부채 상환 내역 동적 로딩과 예외처리, 사용하지 않는 구문 삭제 
*/
	$(document).ready(function() {
		//datepicker로 집언엏기
		$("#calendarMemo").datepicker();
		$("#calendarMemo").datepicker().on('changeDate', function(env) {
			//날짜가 변경될 때 집어넣기
			$("#alarm_date").val(setDateInputForm(env['date']));
			//시계를 띄워주는데 시간을 좀 두고 띄우는 구문
			setTimeout(function(){
				$("#alarm_time").click();
			},100);
			
		});
		//오늘 버튼 클릭
		$("#today").click(function() {
			goToday();
		});
		//드래그 이벤트
		var origMousePoint = false;
		var isTouchDevice = 'ontouchstart' in document.documentElement;
		var calendarHeader = $(".datepicker-days").find(".table-condensed").find(".calendar-header");
		if(!isTouchDevice){
			//페이지 테스트용 mouse 이벤트
			$("#calendarMemo").on({
				mousedown: function(event){
					origMousePoint = event.screenX;
				},
				mouseup: function(event){
					if(origMousePoint-event.screenX>150){calendarHeader.find(".next").click();}
					else if(origMousePoint-event.screenX<-150){calendarHeader.find(".prev").click();}
				}
			});
		} else{
			//실제 모바일에서 사용하기 위해 toucheventlistner로 구현
			var calendar = document.getElementById('calendarMemo');
			var clientX, clientY;
			calendar.addEventListener('touchstart',function(e){
				origMousePoint = e.touches[0].clientX;
			}, false);
			calendar.addEventListener('touchend',function(e){
				if(origMousePoint-e.changedTouches[0].clientX>150){calendarHeader.find(".next").click();}
				else if(origMousePoint-e.changedTouches[0].clientX<-150){calendarHeader.find(".prev").click();}
			}, false);
		}
		//오늘 날짜 넣기
		goToday();
	});
	
	//오늘 날짜로 이동
	function goToday() {
		//달력에 오늘 넣기
		$("#calendarMemo").datepicker("setDate", new Date());
		//날짜에 현재 날짜 넣기
		$("#alarm_date").val(setDateInputForm(new Date()));
		//알람 시간에 현재 시간 넣기
		$("#alarm_time").val(setTimeInputForm(new Date()));
	};
	//db에 저장된 date형태에 맞게 변환
	var setDateDBForm = function(date) {
		var y = date.getFullYear().toString();
		var m = (date.getMonth() + 1).toString();
		if (m.length == 1) {m = '0' + m};
		var d = date.getDate().toString();
		if (d.length == 1) {d = '0' + d};
		return 'req_yyyymm=' + y + m + d;
	};
	//input[type='date']에 맞게 변환
	var setDateInputForm = function(date) {
		var y = date.getFullYear().toString();
		var m = (date.getMonth() + 1).toString();
		if (m.length == 1) {m = '0' + m};
		var d = date.getDate().toString();
		if (d.length == 1) {d = '0' + d};
		return y + "-" + m + "-" + d;
	};
	//input[type='time']에 맞게 변환
	var setTimeInputForm = function(date) {
		var h = date.getHours().toString();
		var m = date.getMinutes().toString();
		if (m.length == 1) {m = '0' + m};
		return h + ":" + m;
	};
	//각 td에 표시를 하기 위해서 data-date속성에 맞게 milliseconds 값을 환산하는 과정. 서울 표준시간이 +9이기 때문에 더해줌
	var convertDateToMil = function(strDate) {
		var y = strDate.substring(0, 4);
		var m = strDate.substring(4, 6);
		var d = strDate.substring(6, 8);
		return Date.parse(m + "/" + d + "/" + y) + 32400000;
	};
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
				<h1>알람설정</h1>
				<div class="g-menu" id="src_menu">
					<button type="button" id="today" class="btn btn-gmenu">오늘</button>
				</div>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div id="calendarMemo"></div>
			<div class="container">
				<div class="list-group calendar-list" id="listDebtByMm"></div>
			</div>
				<div class="form-inline">
					<div class="form-group">
						<label for="alarm_date">날짜</label>
						<input type="date" class="form-control" name="alarm_date" id="alarm_date" autocomplete="off"/>
					</div>
					<div class="form-group">
						<label for="alarm_time">시간</label>
						<input type="time" class="form-control" name="alarm_time" id="alarm_time" autocomplete="off"/>
					</div>
				</div>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
