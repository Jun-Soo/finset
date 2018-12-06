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
<script type="text/javascript">
	$(document).ready(function() {
		
		window.setupValidateForms(document.forms);
		
	});
	
	function getSmsList() {
		
		$.ajax({
			url : "<c:url value='/m/person/lastPersonSmsDt.json'/>",
			data : {"no_person":$("#no_person").val()},
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				// yyyyMMddHHmmss
				var param = result.dt_msg_format;
				if ( result.dt_msg_format == null || result.dt_msg_format == "" ) {
					param = minusMonthYMD(2) + "000000";
				}
				
				console.log("param: " + param);
				if(userAgent == "Android") {
					// {yyyyMMddHHmmss 포맷의 조회 시작일}, {SMS 내용 필터링 정규식}
					//window.Android.getSmsList(param, "\\[(\\s*)web(\\s*)발신(\\s*)\\]");
					window.Android.getSmsList(param);
				}
			},
			error : function (e) {
				alert(e.message);
			}
		});
		
	}
	
	var arrSmsList = null;	// * 샘플페이지이기때문에, 조회된 SMS 리스트를 화면에도 뿌리고 컨트롤러에도 전달하기 위해 전역변수 선언
									// 실제 응용시엔, SMS 조회 콜백에서 no_person만 세팅하여 컨트롤러에 전달하기를 권장
	/**
	* SMS 조회 콜백
	* smsList = [ { "msg_info" : "[WEB 발신] 테스트", "ymd_msg" : "20180115", "his_msg" : "145030" }, ... ]
	*/
	function resultGetSmsList( smsList ) {
		console.log("resultGetSmsList is called: " + smsList);
		
		if ( smsList == null || smsList == "" ) {
			alert("조회 결과값이 전달되지 않았습니다.");
			return;
		}
		
		$("#resultSmsList").html("");
		
		arrSmsList = JSON.parse(smsList);
		
		showLoading();
		$.each(arrSmsList, function(_idx, _val){
			var row = $("<div>").attr({"class":"row"});
			var msg = $("<div>").attr({"class":"col-xs-9"}).text(_val.msg_info);
			var dt = $("<div>").attr({"class":"col-xs-3"}).text(_val.ymd_msg + _val.his_msg);
			row.append(msg);
			row.append(dt);
			
			$("#resultSmsList").append(row);
		});
		showStop();
	}
	
	function recvPersonSms() {
		if ( arrSmsList == null) {
			alert("SMS 이력 조회 후 저장해주세요.");
			return;
		}
		
		// jsonMsgInfo, {"no_person" : "", "list_sms" : [ {"msg_info" : "[WEB 발신] 테스트", "ymd_msg" : "20180115", "his_msg" : "145030"}, ... ]}
		var data = {
				"no_person": $("#no_person").val()
				, "list_sms": arrSmsList
		}
		
		$.ajax({
			url : "<c:url value='/m/person/recvPersonSms.json'/>",
			data : {"jsonMsgInfo": JSON.stringify(data)},
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
			},
			error : function (e) {
				alert(e.message);
			}
		});
	}
	
	/*
	* 입력한 날짜 -month를 반환한다.
	* return YYYYMMDD
	*/
	function minusMonthYMD(minusMonth) {
		var date = new Date();
		date.setMonth(date.getMonth() - minusMonth);
		var year = date.getFullYear();
	    var mon = (date.getMonth()) > 9 ? '' + (date.getMonth() + 1) : '0' + (date.getMonth() + 1);
	    var day = (date.getDate()) > 9 ? '' + (date.getDate()) : '0' + (date.getDate());
	    var strYMD = year + mon + day;
	    return strYMD;
	}
	
	//loading 처리
	function showLoading(){
		if ( $("#progressDiv").length == 0 ) {
			var height = $(document).scrollTop() + $(window).height();
			$div = $('<div id="progressDiv" style="position:absolute; top:0; left:0; width:100%; height:' + height + 'px; background:#000; opacity:.5; filter:alpha(opacity=20); z-index:9998;"></div>');
			$('body').append($div);
		}
	}

	function showStop() {
		if ( $("#progressDiv").length > 0 ) {
			$("#progressDiv").remove();
		}
	}
	
	jQuery(function($){
	    $(document).ajaxStart(function() {
	    	showLoading();
	    }).ajaxStop(function() {
	    	showStop();
	    });
	});
	
</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button class="ui-nav nav-back" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>SMS 내역 조회</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<!-- //panel -->
		<div class="container">
			<form name="frmSmsList" method="post" validate="remove_tag;">
				<div class="form-block form-input-block-confrim">
					<div class="form-group">
						<label for="" class="sr-only">개인번호</label>
						<input type="text" name="no_person" id="no_person" class="form-control" placeholder="" validate="trim;strip_ws;" autocomplete="off" value="${no_person}"/>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-default" onclick="getSmsList()">SMS 내역 가져오기</button>
					</div>
					<div class="form-group">
						<label for="" class="sr-only">SMS 조회 결과</label>
						<div id="resultSmsList">
							<div class="row">
								<div class="col-xs-12">
									조회 결과 없음
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="confirmed_div">
			<button id="confirmed_certify" onclick="recvPersonSms()" class="btn btn-lg btn-disabled btn-block" >내역 저장하기</button>	
		</div>
	</section>
	
	
	<!-- //Content -->
</div>
</body>
</html>
