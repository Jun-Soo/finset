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

	//뒤로가기
	function goBack(){
		frmCustomerNoticeDetail.action = "<c:url value='/m/customercenter/frameCustomerNotice.crz'/>";
		frmCustomerNoticeDetail.submit();
    }
</script>

</head>
<body class="bg-white">
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>
				<c:choose>
				<c:when test="${boardInfo.id_board == 'notice'}">
				공지
				</c:when>
				<c:otherwise>
				이벤트
				</c:otherwise>
				</c:choose>
			</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCustomerNoticeDetail" id="frmCustomerNoticeDetail">
	<input type="hidden" id="id_board" name="id_board" value="${boardInfo.id_board}"/>
		
	<div id="content" class="board-view">
		<c:choose>
			<c:when test="${boardInfo.id_board eq 'notice'}">
			<!-- 공지 -->
				<div class="panel panel-default">
					<div class="panel-heading caption">
						<p class="date">${ufn:formatDate(boardInfo.dt_frt)}</p>
						<h3 class="panel-title">${boardInfo.title }</h3>
					</div>
					<div class="panel-body">
						<p>
							${boardInfo.content }
						</p>
					</div>	
				</div>
			</c:when>
			<c:otherwise>
			<!-- 이벤트ㅡ -->
				<div class="panel panel-default">
					<div class="panel-heading caption">
						<h3 class="panel-title">${boardInfo.title }</h3>
						<p class="date">${ufn:formatDate(boardInfo.ymd_post_strt)} ~ ${ufn:formatDate(boardInfo.ymd_post_end)}</p>
						<c:choose>
							<c:when test="${boardInfo.cd_event_proc eq '01'}">
								<span class="label label-status status-green">예정</span>
							</c:when>
							<c:when test="${boardInfo.cd_event_proc eq '02'}">
								<span class="label label-status status-blue">진행중</span>
							</c:when>
							<c:otherwise>
								<span class="label label-status status-gray">종료</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body">  
						<p>
							<c:if test="${!empty boardImgInfo}">
								<img src="/m/board/getBoardImg.crz?id_board=${boardImgInfo.id_board}&seq=${boardImgInfo.seq}&file_type=02">
								<br/>
							</c:if>
							${boardInfo.content}
						</p>
					</div>	
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	</form>
	<!-- //Content -->
</div>
</body>
</html>
