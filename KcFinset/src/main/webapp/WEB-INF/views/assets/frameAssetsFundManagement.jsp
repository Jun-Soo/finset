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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
	//Google Chart
	google.charts.load('current', {'packages':['corechart']});
	//Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(drawBar);
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
		url : "<c:url value='/m/assets/listAssetsFund.crz'/>",
		data : data,
		async: true,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			$("#listAssetsFund").append(result);
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

function drawBar() 
{
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Element');
	data.addColumn('number', 'percentage');
	data.addColumn({type:'string', role:'style'});

	$.ajax({
		url : "<c:url value='/m/assets/AssetsFund.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var idx = result.jsonData.data.length;
			data.addRows(idx);
			for(var i = 0; i < idx; i++ ){
				data.setValue(i, 0, result.jsonData.data[i].fundname);
				data.setValue(i, 1, result.jsonData.data[i].amt);
				data.setValue(i, 2, result.jsonData.data[i].style);
			}
		}
	});

    // Set chart options
    var options = {
		legend: 'none',
		animation: {
			duration: 2000,
    		easing: 'out',
    		startup: true
		},
		annotations: {
			alwaysOutside: true, // bar 텍스트 위치
		    textStyle: {
				fontName: 'dotum',
				fontSize: 11,
				bold: true,
				italic: false,
				color: '#871b47',
				opacity: 0.8
			}
		},
		lineWidth: 2,
		fontSize: 11,
		bar: {
			groupWidth: "40%"

		},
		seriesType: 'bars',
		series: {1: {type: 'line'}},
		'width':400,
		'height':300
	};
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_bar'));
    	chart.draw(data, options);
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
			<h1>펀드</h1>
		</div>
	</header>
	<!-- Content -->
	<form id="frmAssets" name="frmAssets">
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
	</form>
	<section id="content">
		<div class="sum-block sum-assets">
			<div class="chart" id="chart_bar"></div>
		</div>
		<div id="listAssetsFund">
			<%@ include file="/WEB-INF/views/assets/sub/listAssetsFund.jsp"%>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
