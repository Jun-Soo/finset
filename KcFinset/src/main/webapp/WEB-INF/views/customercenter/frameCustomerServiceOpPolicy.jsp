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
			
			//서비스 이용약관
			function goServiceUseTerms(gubun) {
				
				if(gubun == 'hp1') {
					frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms1.crz'/>";
					frmTermsStep.submit();
				} else if(gubun == 'hp2') {
					frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms2.crz'/>";
					frmTermsStep.submit();
				} else if(gubun == 'hp3') {
					frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms3.crz'/>";
					frmTermsStep.submit();
				} else if(gubun == 'hp4') {
					frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms4.crz'/>";
					frmTermsStep.submit();
				}
			}
		
		</script>
	</head>
	<body>
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
				</div>
				<h1>서비스 운영방침</h1>
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
				</div>
			</div>
		</header>
		<div id="content">
			<form name="frmTermsStep" id="frmTermsStep" method="post">
				<div class="panel-group agree-panel" id="termsList">
					<div class="panel panel-default">
						<div class="panel-heading">
							<label><a href="#panel1" class="collapsed" role="button" data-toggle="collapse">서비스 이용약관</a></label>
						</div>
						<div class="panel-collapse collapse in" id="panel1">
							<ul class="list-group">
								<li class="list-group-item">
									<label>개인정보수집 및 이용동의</label>
									<button type="button" class="btn-terms" onclick="goServiceUseTerms('hp1');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>고유식별처리 동의</label>
									<button type="button" class="btn-terms" onclick="goServiceUseTerms('hp2');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>통신사 이용약관 동의</label>
									<button type="button" class="btn-terms" onclick="goServiceUseTerms('hp3');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>서비스 이용약관 동의</label>
									<button type="button" class="btn-terms" onclick="goServiceUseTerms('hp4');">약관보기</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<label><a href="#panel1" class="collapsed" role="button" data-toggle="collapse">개인정보 처리 방침</a></label>
						</div>
						<div class="panel-collapse collapse in" id="panel2">
							<ul class="list-group">
								<li class="list-group-item">
									<label>개인정보수집 및 이용동의</label>
									<button type="button" class="btn-terms" disabled onclick="popAcceptTerms('hp1');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>고유식별처리 동의</label>
									<button type="button" class="btn-terms" disabled onclick="popAcceptTerms('hp2');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>통신사 이용약관 동의</label>
									<button type="button" class="btn-terms" disabled onclick="popAcceptTerms('hp3');">약관보기</button>
								</li>
								<li class="list-group-item">
									<label>서비스 이용약관 동의</label>
									<button type="button" class="btn-terms" disabled onclick="popAcceptTerms('hp4');">약관보기</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
