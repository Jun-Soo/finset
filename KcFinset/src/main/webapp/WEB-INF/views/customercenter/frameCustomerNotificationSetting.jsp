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
		//전체알림으로 인한 설정값
// 		if("${personVO.yn_push}" == "Y"){
// 			$("input[name='each_push']").prop("disabled",false);
// 		}else{
// 			$("input[name='each_push']").prop("disabled",true);
// 		}
			
	});

	//방해금지모드 설정
	function allChkPush(obj){
		$("#type_push").val("all");
		
		if($(obj).is(":checked")){ //소리+진동X 푸시수신
			if(userAgent == "Android") {
				window.Android.settingPushType("0");
			}
			$("#cd_push").val('0');
				
//	 		$('#yn_push').val('N');  
//	 		$("input[name='each_push']").prop("disabled",true);
	
		}else{ //소리+진동O 푸시수신
			if(userAgent == "Android") {
				window.Android.settingPushType("1"); 
		    }
		       
			$("#cd_push").val('1');
			
// 			$('#yn_push').val("Y");
// 			$("input[name='each_push']").prop("disabled",false);
			 
			
		}
		
		$("#item_push").val('');
		
		var data = frmPushStep.ajaxSubmit();
		$.ajax({
			url : "<c:url value='/m/person/modifyPushNoti.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	//개별알림 설정
	function eachChkPush(obj){
		$("#type_push").val("each");
		
		$("#item_push").val($(obj).val());
		if($(obj).is(":checked")){
			$("#stat_push").val("Y");
		}else{
			$("#stat_push").val("N");
		}
		
		var cnt=0;
		$.each($("input[name='each_push']"), function (index, item) {
			if($(this).is(":checked")){
				cnt++;
			}
		});
		if(cnt == 0){
			$('#yn_push').val("N");	//모든 푸쉬 수신 안함
// 			$("input[name='each_push']").prop("disabled",true);
		}else{
			$('#yn_push').val("Y");	//푸쉬 수신
		}
		
		var data = frmPushStep.ajaxSubmit();
		$.ajax({
			url : "<c:url value='/m/person/modifyPushNoti.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	
	//뒤로가기
	function goBack(){
		frmPushStep.action = "/m/customercenter/frameCustomerCenterMain.crz";
		frmPushStep.submit();
	}
</script>
</head>
<body>
<form id="frmPushStep" name="frmPushStep">
<!-- TO DO all푸시 - 소리진동타입update , each푸시 푸시항목명, yn체크, yn_push값 update -->
<input type="hidden" id="type_push" name="type_push" value=""/> <!-- all / each -->
<input type="hidden" id="cd_push" name="cd_push" value=""/> <!-- all-소리/진동타입 -->
<input type="hidden" id="item_push" name="item_push" value=""/> <!-- each-푸시항목명 -->
<input type="hidden" id="stat_push" name="stat_push" value=""/> <!-- each푸시 yn 체크 -->
<input type="hidden" id="yn_push" name="yn_push" value=""/> <!-- all푸시 yn체크 -->
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>알림설정</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content" class="push-bnr">
		<div class="list-group all-push">
			<div class="list-group-item">
				<h3 class="h3">방해금지모드</h3>
				<p>소리/진동 없이 알림 받기</p>
				<div class="ui-switch">
					<label data-form-control="toggle" class="pull-right">
						<input type="checkbox" id="all_push" name="all_push" <c:if test="${personVO.cd_push eq '0'}"> checked="checked" </c:if> onclick="allChkPush(this);">
						<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
					</label>
				</div>
			</div>
			
		</div>
		<div class="container-fluid">
			<div class="list-group">
				<c:forEach var="list" items="${listCdPush}" varStatus="status">
					<div class="list-group-item">
						<h3 class="h3"><c:out value="${list.nm_code}"/> 알림</h3>
						<p><c:out value="${list.etc}"/></p>
						<div class="ui-switch">
							<label data-form-control="toggle" class="pull-right">
								<input type="checkbox" name="each_push" id="each_push_<c:out value='${list.code_value}'/>" value="${list.code_value}" 
								<c:forEach var="pushSetting" items="${listPushSetting}" varStatus="status">
								<c:if test="${pushSetting.item_push eq list.code_value and pushSetting.yn_push eq 'Y'}"> checked="checked" </c:if>
								</c:forEach> onclick="eachChkPush(this)">
								<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
							</label>
						</div>
					</div>
					
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
