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
	//Google Chart
	google.charts.load('current', {'packages':['corechart']});
	//Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(drawDonut);
	
	if(userAgent == "Android") {
		window.Android.setBackKeyUse('N');
		window.Android.setEndApp('N');
		
	}
});

function drawDonut() {
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Assets');
	data.addColumn('number', 'percentage');
	data.addRows([
	  ['펀드', Number("${assetsInfoFund.total_evaluation_Amt}")],
	  ['주식', Number("${assetsInfoEquity.total_evaluation_Amt}")],
	  ['기타', Number("${assetsInfoEtc.total_evaluation_Amt}")]
	]);

	var options = {
		title: "${totalAssetsVal}".format() + '원',
		titleTextStyle: {fontSize: 16, color: '#FFFFFF'},
		titlePosition: 'none',
		fontSize: 12,
		sliceVisibilityThreshold : .0, // sliceVisibilityThreshold : .25 -> 25%이하의 항목들은 other로 표시
		colors: ['#00a9d3','#00d3b8','#46d300'],
		legend: {position: 'right', textStyle: {color: '#333', fontSize: 12}},
		pieSliceText: 'label',
		'width':400,
		'height':200
	};

	var chart = new google.visualization.PieChart(document.getElementById('chart_donut'));
		chart.draw(data, options);
}

//숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.format = function(){
    if(this==0) return 0;

    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');

    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

    return n;
};

// 문자열 타입에서 쓸 수 있도록 format() 함수 추가
String.prototype.format = function(){
    var num = parseFloat(this);
    if( isNaN(num) ) return "0";

    return num.format();
};

/*
* name : goodsFavoriteDetail
* desc : 관심상품 상세 조회
* param : cd_fc
* param : cd_goods
* output :
*/
function goMenu( type ){

	var url;

	if( type == "01" ){// 펀드
		url = "<c:url value='/m/assets/frameAssetsFundManagement.crz'/>";
	}else if( type == "02" ){//주식
		return ;//url = "<c:url value='/m/assets/frameAssetsStockManagement.crz'/>";
	}else if( type == "99" ){//기타
		return ;//url = "<c:url value='/m/assets/frameAssetsEtcManagement.crz'/>";
	}

	frmAssets.action = url;
	frmAssets.submit();
}

function goGoodsMain(){
	frmAssets.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
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
			<h1>자산관리</h1>

		</div>
	</header>
	<!-- Content -->
	<form id="frmAssets" name="frmAssets">
	</form>
	<section id="content">
		<div class="sum-block sum-assets">
			<h2 class="chart-header"><font color="#FFFFFF">${ufn:formatNumberPattern(totalAssetsVal, "###,###")} 원</font></h2>
			<div class="chart" id="chart_donut"></div>
		</div>
		<div class="prd-block prd-type item-assets-list">
			<div class="container-fluid">
				<a href="#" onclick="goMenu('01');">
					<h3 class="prdlist-heading">펀드 <span class="badge-counter">${assetsInfoFund.accno_cnt}</span></h3>
					<div class="prdlist-info">
						<dl>
							<dt>평균수익률</dt>
							<c:choose>
								<c:when test="${assetsInfoFund.rto_assets_tevenue > 0}">
									<dd class="txt-point">${ufn:formatNumberPattern(assetsInfoFund.rto_assets_tevenue, "###.##")} %</dd>
								</c:when>
								<c:otherwise>
									<dd class="txt-point-minus">${ufn:formatNumberPattern(assetsInfoFund.rto_assets_tevenue, "###.##")} %</dd>
								</c:otherwise>
							</c:choose>
						</dl>
						<dl>
							<dt>총 평가금액</dt>
							<dd>${ufn:formatNumberPattern(assetsInfoFund.total_evaluation_Amt, "###,###")} 원</dd>
						</dl>
					</div>
				</a>
			</div>
			<%--
			<div class="container-fluid">
				<a href="#" class="inner" onclick="goMenu('02');">
					<h3 class="prdlist-heading">주식 <span class="badge-counter">${assetsInfoEquity.accno_cnt}</span></h3>
					<div class="prdlist-info">
						<dl>
							<dt>평균수익률</dt>
							<c:choose>
								<c:when test="${assetsInfoEquity.rto_assets_tevenue > 0}">
									<dd class="txt-point">${ufn:formatNumberPattern(assetsInfoEquity.rto_assets_tevenue, "###.##")} %</dd>
								</c:when>
								<c:otherwise>
									<dd class="txt-point-minus">${ufn:formatNumberPattern(assetsInfoEquity.rto_assets_tevenue, "###.##")} %</dd>
								</c:otherwise>
							</c:choose>
						</dl>
						<dl>
							<dt>총 평가금액</dt>
							<dd>${ufn:formatNumberPattern(assetsInfoEquity.total_evaluation_Amt, "###,###")} 원</dd>
						</dl>
					</div>
				</a>
			</div>
			<div class="container-fluid">
				<a href="#" class="inner" onclick="goMenu('99');">
					<h3 class="prdlist-heading">기타 <span class="badge-counter">${assetsInfoEtc.accno_cnt}</span></h3>
					<div class="prdlist-info">
						<dl>
							<dt>평균수익률</dt>
							<c:choose>
								<c:when test="${assetsInfoEtc.rto_assets_tevenue > 0}">
									<dd class="txt-point">${ufn:formatNumberPattern(assetsInfoEtc.rto_assets_tevenue, "###.##")} %</dd>
								</c:when>
								<c:otherwise>
									<dd class="txt-point-minus">${ufn:formatNumberPattern(assetsInfoEtc.rto_assets_tevenue, "###.##")} %</dd>
								</c:otherwise>
							</c:choose>
						</dl>
						<dl>
							<dt>총 평가금액</dt>
							<dd>${ufn:formatNumberPattern(assetsInfoEtc.total_evaluation_Amt, "###,###")} 원</dd>
						</dl>
					</div>
				</a>
			</div>
			 --%>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
