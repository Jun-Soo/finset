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
		$("#calendar").datepicker();
		$("#today").click(function() {
			goToday();
		});
		$('#calendar').datepicker().on('changeMonth', function(env) {
			getDebtListWithMonth(
				setDateForm(env['date'])
			);
		});
		goToday();
		
		//드래그 이벤트
		var origMousePoint = false;
		var isTouchDevice = 'ontouchstart' in document.documentElement;
		var calendarHeader = $(".datepicker-days").find(".table-condensed").find(".calendar-header");
		if(!isTouchDevice){
			//페이지 테스트용 mouse 이벤트
			$("#calendar").on({
				mousedown: function(event){
					origMousePoint = event.offsetX;
				},
				mouseup: function(event){
					if(origMousePoint-event.offsetX>150){calendarHeader.find(".next").click();}
					else if(origMousePoint-event.offsetX<-150){calendarHeader.find(".prev").click();}
				}
			});
		} else{
			//실제 모바일에서 사용하기 위해 toucheventlistner로 구현
			var calendar = document.getElementById('calendar');
			var clientX, clientY;
			calendar.addEventListener('touchstart',function(e){
				origMousePoint = e.touches[0].clientX;
			}, false);
			calendar.addEventListener('touchend',function(e){
				if(origMousePoint-e.changedTouches[0].clientX>150){calendarHeader.find(".next").click();}
				else if(origMousePoint-e.changedTouches[0].clientX<-150){calendarHeader.find(".prev").click();}
			}, false);
		}
	});
	/*알림페이지로 이동*/
	function goNoti() {
		location.href = "<c:url value='/m/customercenter/frameCustomerNotificationSetting.crz'/>";
	}
	//오늘 날짜로 이동
	function goToday() {
		$("#calendar").datepicker("setDate", new Date());
		getDebtListWithMonth(setDateForm(new Date()));
	}
	//db에 저장된 date형태에 맞게 변환
	var setDateForm = function(date) {
		var y = date.getFullYear().toString();
		var m = (date.getMonth() + 1).toString();
		if (m.length == 1) {m = '0' + m};
		return 'req_yyyymm=' + y + m;
	}
	//화면에 나타날 데이터를 가져오는 함수
	var getDebtListWithMonth = function(data) {
		$.ajax({
			url : "<c:url value='/m/debt/getDebtListWithMonth.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				switch(result.code){
				case '00':
					setDataToCal(result);
					setDataToList(result);
					break;
				case '99':
					toastMsg('페이지에서 오류가 발생했습니다. 다시 접속해주세요.');
					break;
				default:
					break;
				}
			},
			error : function(e) {
				toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
			}
		});
	}
	//상단 달력에 데이터를 뿌려주는 함수
	var setDataToCal = function(result) {
		var outerTable = $(".datepicker-days").find(".table-condensed");
		var list = result['listDebtCalendar'];
		//달력이 그려지기 전에 찾으려고 하다보니 일부 find가 작동하지 않는 현상이 있어서 setTimeout을 걸어둠
		setTimeout(function() {
			for (var i = 0; i < list.length; i++) {
				outerTable.find('td[data-date='+convertDateToMil(list[i]['payment_ymd'])+']').addClass(getClassNm(list[i]['pay_type']+'').trim());
			}
		}, 10);
	}
	//각 td에 표시를 하기 위해서 data-date속성에 맞게 milliseconds 값을 환산하는 과정. 서울 표준시간이 +9이기 때문에 더해줌
	var convertDateToMil = function(strDate) {
		var y = strDate.substring(0, 4);
		var m = strDate.substring(4, 6);
		var d = strDate.substring(6, 8);
		return Date.parse(m + "/" + d + "/" + y) + 32400000;
	}
	//하단 리스트에 데이터를 뿌려주는 함수
	var setDataToList = function(result) {
		$("#listDebtByMm").html("");
		var list = result['listDebtCalendar'];
		var template = '';
		//부채 내역이 있을 때
		if(list.length>0){
			for (var i = 0; i < list.length; i++) {
				template =
					'<div class="list-group-item'+getClassNm(list[i]['pay_type']+'')+'">'+'<ul>'
						+ '<li class="date"><i class="ico-status"></i>'
						+ list[i]['pay_yyyy'] + '.' + list[i]['pay_mm'] + '.'
						+ list[i]['pay_dd'] + '</li>' + '<li>' + '<div>' + '<span>'
						+ list[i]['nm_fc'] + '</span>' + '<p>상환금액: '
						+ setCommaInNumber(list[i]['amt_repay']) + ' 만원</p>' + '</div>' + '</li>'
						+ '<li><span class="label label-status status-blue">'
						+ list[i]['pay_type_nm'] + '</span></li>' + '</ul>'
						+ '</div>';
				$("#listDebtByMm").append(template);
			}
		//부채 내역이 없을 때
		}else{
			template = '<div class="list-group-item"><ul><li class="date">납입 혹은 예정된 부채 내역이 없습니다.</li></ul></div>';
			$("#listDebtByMm").append(template);
		}
	}
	//pay_type에 따른 class명이 지정되어 있기 때문에 이를 구분해주는 함수를 따로 구현
	var getClassNm = function(pay_type){
		switch(pay_type){
		case 'A':
			return ' p-blue';
		case 'B':
			return ' p-orange';
		case 'C':
			return ' p-green';
		case 'D':
			return ' p-gray';
		default:
			return '';
		}
	}
	//정규식을 이용해 3자리마다 콤마를 찍어줌
	function setCommaInNumber(num) {
		var regx = /\B(?=(\d{3})+(?!\d))/g;
		var numStr = num.toString();
		var point = numStr.indexOf(".");
		//소수점이 있을 경우
		if(point>0){return numStr.substring(0,point).replace(regx,",")+numStr.substring(point);}
		//소수점이 없는 경우
		else{return numStr.replace(regx, ",");}
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
				<h1>부채달력</h1>
				<div class="g-menu" id="src_menu">
					<button type="button" id="today" class="btn btn-gmenu">오늘</button>
					<button type="button" class="ico ico-notilist" onclick="goNoti();">알림내역</button>
				</div>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div id="calendar"></div>
			<div class="container-fluid">
				<div class="list-group calendar-list" id="listDebtByMm"></div>
			</div>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
