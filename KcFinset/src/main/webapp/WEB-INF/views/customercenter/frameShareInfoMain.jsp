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

});

//리스트 조회
function goSearch(type_list) {
	$("#type_list").val(type_list);
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);

	var data = frmShareInfoMain.ajaxSubmit();
	if(data == null) return false;
	if(userAgent == "Android") {
		window.Android.loding('Y');
	}else if(userAgent == "iOS") {
		Jockey.send("showLoading");
	}
	vLoad("listShareInfoMain","<c:url value='/m/customercenter/listShareInfoMain.crz'/>",data);
}

function vLoad(id,uri,data) {
	vLoad(id,uri,data,true);
}
function vLoad(id,uri,data,sync) {
	$.ajax({
		url : uri,
		data : data,
		async: sync,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
            var expr = new RegExp('>[ \t\r\n\v\f]*<', 'g');
			result = result.replace(expr, '> <');
            var page = $("#page").val();
            if(page != 1) {
                $("#"+id).append(result);
            } else {
                $("#"+id).html(result);
            }
			if(userAgent == "Android") {
                window.Android.loding('N');
			}else if(userAgent == "iOS") {
				Jockey.send("stopLoading");
			}
            isSearching = false;
		},
		error : function (e) {
			errMsg(e);
		}
	});
}

//공유설정화면 이동
function goSetting(setting_mode, seq_share){
	$("#setting_mode").val(setting_mode);
	$("#seq_share").val(seq_share);
	frmShareInfoMain.action = "/m/customercenter/frameShareInfoSetting.crz";
	frmShareInfoMain.submit();
}

//정보상세보기
function viewDetail(seq_share){
	$("#seq_share").val(seq_share);
	frmShareInfoMain.action = "/m/customercenter/frameShareInfoDetail.crz";
	frmShareInfoMain.submit();
}

//신규요청하기
function createNewRequest(){
	location.href = "/m/customercenter/frameShareInfoNewRequest.crz";
}

//모두 해지하기
function cancelAllItems(){

	var data = frmShareInfoMain.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/updateShareInfoAllCancel.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			toastMsg("해지되었습니다.");
			goSearch("req");
		},
		error : function (e) {
            errMsg(e);

		}
	});
}

//마이페이지 메인으로 이동
function goCustomercenterMain(){
	frmShareInfoMain.action = "/m/customercenter/frameCustomerCenterMain.crz";
	frmShareInfoMain.submit();
}
</script>
</head>
<body>
<form method="post" name="frmShareInfoMain" id="frmShareInfoMain">
	<input type="hidden" name="type_list" id="type_list" value="${type_list}">
	<input type="hidden" name="page" id="page" />
	<input type="hidden" id="setting_mode" name="setting_mode" />
	<input type="hidden" id="seq_share" name="seq_share" />
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goCustomerCenterMain();">뒤로가기</button>
			</div>
			<h1>정보 공유 관리</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content">
		<div class="visual-banner middle-banner">
			<p class="msg">가족과 지출 정보 등을 공유하여</p>
			<p class="msg">통합 관리 하세요</p>
			<br/>
			<p class="msg">공유 신청 시 핀셋에서 보유하고 있는 정보와</p>
			<p class="msg">일치해야 공유가 가능합니다</p>
		</div>
		<div class="affix-fixed top-fixed-item">
			<ul class="nav nav-outline nav-justified tabs">
				<li data-tab="require" class="<c:if test="${type_list eq 'offer'}">active</c:if>"><a id="li_require" onclick="goSearch('offer');">요청</a></li>
				<li data-tab="allow" class="<c:if test="${type_list eq 'req'}">active</c:if>"><a id="li_allow" onclick="goSearch('req');">허용</a></li>
			</ul>
		</div>
		<div id="listShareInfoMain" class="list-block">
			<%@ include file="/WEB-INF/views/customercenter/sub/listShareInfoMain.jsp"%>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</body>
</html>
