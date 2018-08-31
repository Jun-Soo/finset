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

//공유설정화면 이동
function goSetting(setting_mode, seq_share){
	$("#setting_mode").val(setting_mode);
	$("#seq_share").val(seq_share);
	frmShareInfoSummary.action = "/m/customercenter/frameShareInfoSetting.crz";
	frmShareInfoSummary.submit();
}

//정보상세보기
function viewDetail(seq_share){
	$("#seq_share").val(seq_share);
	frmShareInfoSummary.action = "/m/customercenter/frameShareInfoDetail.crz";
	frmShareInfoSummary.submit();
}

//신규요청하기
function createNewRequest(){
	location.href = "/m/customercenter/frameShareInfoNewRequest.crz";
}
</script>
</head>
<body>
<form name="frmShareInfoSummary" id="frmShareInfoSummary" method="post">
<input type="hidden" id="setting_mode" name="setting_mode"/>
<input type="hidden" id="seq_share" name="seq_share"/>
<input type="hidden" id="no_person" name="no_person"/>
</form>
<div id="wrapper" class="bg-white">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>정보공유</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content">
		<div class="visual-banner middle-banner">
			<p class="msg">finset 정보 공유를</p>
			<p class="msg">통해 가족간의 흩어진 정보를</p>
			<p class="msg">한눈에</p>
			<br/>
			<p class="msg">비대면 거래에 필요한 정보를</p>
			<p class="msg">안전하게 제공</p>
			<br/>
			<p class="msg">한번의 클릭으로 간편하게</p>
			<p class="msg">정보를 공유 및 통합 관리할 수 있습니다.</p>
		</div>
		<div class="container">
			<table class="table tbl_status">
				<thead>
					<tr>
						<th colspan="3">타인 정보 열람</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="List" items="${offerList}" varStatus="status">
					<tr onclick="
						<c:choose>
						<c:when test="${List.share_status eq '01'}"> <!-- 재요청 화면으로 이동 -->
							goSetting('01',${List.seq_share});
						</c:when>
						<c:when test="${List.share_status eq '02'}"> <!-- 상세화면으로 이동 -->
							<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 -->
								<c:if test="${List.dt_end_offer >= currentDate}"> <!-- 정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음 -->
									viewDetail(${List.seq_share});
								</c:if>
							</c:if>
							<c:if test="${List.yn_itgt_mngm eq 'Y'}"> <!-- 정보통합항목이 있는경우 -->
								viewDetail(${List.seq_share});
							</c:if>
						</c:when>
						</c:choose>
						"
					>
						<td>${List.offer_nm_person}</td>
						<td>
							<c:choose>
							<c:when test="${List.share_status eq '01'}">
								대기
							</c:when>
							<c:when test="${List.share_status eq '02'}">
								완료
							</c:when>
							</c:choose>
						</td>
						<td>${List.offer_hp}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<table class="table tbl_status">
				<thead>
					<tr>
						<th colspan="3">내 정보 공유</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="List" items="${reqList}" varStatus="status">
					<tr onclick="
						<c:choose>
						<c:when test="${List.share_status eq '01'}"> <!-- 허용/거절 화면으로 이동 -->
							goSetting('02',${List.seq_share});
						</c:when>
						<c:when test="${List.share_status eq '02'}"> <!-- 변경/해지 화면으로 이동 -->
						<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 -->
							<c:if test="${List.dt_end_offer >= currentDate}"> <!-- 정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음 -->
								goSetting('03',${List.seq_share});
							</c:if>
						</c:if>
						<c:if test="${List.yn_itgt_mngm eq 'Y'}"> <!-- 정보통합항목이 있는경우 -->
							goSetting('03',${List.seq_share});
						</c:if>
						</c:when>
						</c:choose>
						"
					>
						<td>${List.req_nm_person}</td>
						<td>
							<c:choose>
							<c:when test="${List.share_status eq '01'}">
								대기
							</c:when>
							<c:when test="${List.share_status eq '02'}">
								완료
							</c:when>
							</c:choose>
						</td>
						<td>${List.req_hp}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="btn-fixed-bottom affix-bottom">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="createNewRequest();">신규 요청하기</a>
		</div>
	</section>
	<!-- //Content -->
	
</div>
</body>
</body>
</html>
