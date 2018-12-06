<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#ym_trd").val(getTodayYm() - 1);
		listStatsConsumeInfo();
		listPeriodConsumeInfo();
	});

	var listStatsConsumeInfo = function() {
		//frmConsumeList.serialize()는 화면이 뜨자마자 적용이 안됨
		var data = $("#frmStatsConsume").serialize();

		$.ajax({
			url : "<c:url value='/m/consume/listStatsConsumeInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				makePieChart(result);
			},
			error : function(e) {
				toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
			}
		});
	}
	var makePieChart = function(result) {
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(function() {
			drawPieChart(result);
		});
	}
	var drawPieChart = function(result) {
		var listStatsConsumeInfo = result.listStatsConsumeInfo;
		var data = google.visualization.arrayToDataTable(listStatsConsumeInfo);
		var options = {
			title : '통계',
			pieStartAngle : 90
		};
		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
		chart.draw(data, options);
	}
	
	var listPeriodConsumeInfo = function() {
		//frmConsumeList.serialize()는 화면이 뜨자마자 적용이 안됨
		var data = $("#frmStatsConsume").serialize();
		$.ajax({
			url : "<c:url value='/m/consume/listPeriodConsumeInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				makeLineChart(result);
			},
			error : function(e) {
				toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
			}
		});
	}
	var makeLineChart = function(result) {
		google.charts.load('current', {
			packages : ['corechart' , 'line' ],
			language : 'kr'
		});
		google.charts.setOnLoadCallback(function(){
			drawLineChart(result);
		});
	}
	var drawLineChart = function(result) {
		var listPeriodConsumeInfo = result.listPeriodConsumeInfo;
		var columnList = result.listColumn;
		var data = new google.visualization.DataTable();
		for ( var a in columnList) {
			if ((columnList[a] + "") == "Date") {
				data.addColumn('string', columnList[a]);
			} else {
				data.addColumn('number', columnList[a]);
			}
		}
		data.addRows(listPeriodConsumeInfo);
		var options = {
			chart : {
				title : 'Box Office Earnings in First Two Weeks of Opening',
				subtitle : 'in millions of dollars (USD)'
			},
			width : 2000,
			height : 500,
			vAxis : {
				format : 'decimal'
			},
			hAxis : {
				title : '날짜',
				textStyle : {
					fontSize : 12
				},
				titleTextStyle : {
					italic : false
				}
			}
		};
// 		var chart = new google.charts.Line(document.getElementById('linechart'));
		var chart = new google.visualization.LineChart(document.getElementById('linechart'));
// 		chart.draw(data, google.charts.Line.convertOptions(options));
		chart.draw(data, options);
	}
	
	var getTodayYm = function() {
		var date = new Date();
		return date.getFullYear()+((date.getMonth()+"").length==1?"0":"")+(date.getMonth()+1);
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
				<h1>부채관리</h1>
			</div>
			<div>
				<form id="frmStatsConsume" name="frmStatsConsume">
					<input id="ym_trd" name="ym_trd" />
					<input id="dt_from" name="dt_from" value="20180701"/>
					<input id="dt_to" name="dt_to" value="20180731"/>
				</form>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div class="container">
				<div id="piechart" style="width: 100%; height: 500px;"></div>
				<div id="linechart" style="width: 100%; height: 500px;"></div>
			</div>
		</section>
		<!-- //Content -->
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>
