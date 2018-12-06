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
/*
 * 메모 리스트 화면
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 */
 
$(document).ready(function () {
	//back키 방지
	if(userAgent == "Android") {
		window.Android.setEndApp('Y');
	}
	//각 리스트 클릭시
	$(".container-fluid").on("click",function(event){
		event.preventDefault();
		var seq_memo_info = $(this).find("#seq_memo_info_each").val();
		detailMemo(seq_memo_info);
	});
});

var detailMemo = function(seq_memo_info){
	$(frmMemo).find("#seq_memo_info").val(seq_memo_info);
	frmMemo.action = "<c:url value='/m/memo/frameDetailMemo.crz'/>";
	frmMemo.submit();
}

var frameCreateMemo = function(){
	frmMemo.action = "<c:url value='/m/memo/frameCreateMemo.crz'/>";
	frmMemo.submit();
}

var chkHistory = function() {
	if(($("#no_manage_info").val()||'')==''){
		//TODO: Set where the back point
		location = "<c:url value='/m/debt/frameDebtInfoMain.crz'/>"; 
	} else {
		location = "<c:url value='/m/debt/frameInDebtDetail.crz'/>"+"?no_manage_info="+$("#no_manage_info").val();
	}
}

</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="chkHistory();">뒤로가기</button>
			</div>
			<h1>메모</h1>
<!-- 		각 요소들을 form 태그 내에 선언된 input에 집어넣고 이동하는 것이 나을거라 판단 -->
			<form id="frmMemo" method="post">
				<input type="hidden" name="seq_memo_info" id="seq_memo_info"/>
				<input type="hidden" name="no_manage_info" id="no_manage_info" value="${no_manage_info }"/>
			</form>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<div id="ico-plus-div">
			<button id="ico-plus" onclick="frameCreateMemo();"></button>
		</div>
		<div class="list-block">
			<c:forEach var="memo" items="${listMemo }">
				<div class="container-fluid container_short_padding">
					<div class="list-info memo_list_limited">
						<div class="memo_div">
							${memo.memo_text }
						</div>
						<div class="memo_date">
							${memo.dt_lst }
						</div>
					</div>
					<div class="div_hidden">
						<input type="hidden" id="seq_memo_info_each" value="${memo.seq_memo_info }"/>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</div>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
