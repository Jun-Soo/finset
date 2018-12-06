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
</script>

</head>
<body>
<div id="wrapper">
<form method="post" name="frmSetting" id="frmSetting">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>환경설정</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="panel-box">
			<div class="panel-group" id="faq">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq1" class="collapsed" data-toggle="collapse" data-parent="#faq">
							<h3 class="panel-title">로그인 방식</h3>
						</a>
					</div>
					<div class="panel-collapse collapse" id="faq1">
						<div class="panel-body">
							<div class="panel-answer">
								<th>
									<label for="chk_grade_nice">비밀번호입력</label> &nbsp;&nbsp;&nbsp;
									<input type="radio" name="chk_grade_nice" id="chk_grade_nice" value="Y" <c:out value="${goodsInfo.chk_grade_nice eq 'Y' ? 'checked' : ''}"  />/>
								</th>
								<br/>
								<th>
									<label for="chk_grade_nice">지문입력</label> &nbsp;&nbsp;&nbsp;
									<input type="radio" name="chk_grade_nice" id="chk_grade_nice" value="Y" <c:out value="${goodsInfo.chk_grade_nice eq 'Y' ? 'checked' : ''}"  />/>
								</th>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq2" class="collapsed" data-toggle="collapse" data-parent="#faq">
							<h3 class="panel-title">알림 표시</h3>
						</a>
					</div>
					<div class="panel-collapse collapse" id="faq2">
						<div class="panel-body">
							<div class="panel-answer">
								<th>
									<label for="chk_grade_nice">항상표시</label>&nbsp;&nbsp;&nbsp;
									<input type="radio" name="yn_push" id="chk_grade_nice" value="Y" <c:out value="${goodsInfo.chk_grade_nice eq 'Y' ? 'checked' : ''}"  />/>
								</th>
								<br/>
								<th>
									<label for="chk_grade_nice">표시하지 않음</label>&nbsp;&nbsp;&nbsp;
									<input type="radio" name="yn_push" id="chk_grade_nice" value="N" <c:out value="${goodsInfo.chk_grade_nice eq 'N' ? 'checked' : ''}"  />/>
								</th>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq3" class="collapsed" data-toggle="collapse" data-parent="#faq">
							<h3 class="panel-title">알림 형태</h3>
						</a>
					</div>
					<div class="panel-collapse collapse" id="faq3">
						<div class="panel-body">
							<div class="panel-answer">
								<th>
									<label for="chk_grade_nice">소리 + 진동</label>&nbsp;&nbsp;&nbsp;
									<input type="radio" name="cd_push" id="chk_grade_nice" value="1" <c:out value="${goodsInfo.cd_push eq '1' ? 'checked' : ''}"  />/>
								</th>
								<br/>
								<th>
									<label for="chk_grade_nice">소리</label>&nbsp;&nbsp;&nbsp;
									<input type="radio" name="cd_push" id="chk_grade_nice" value="2" <c:out value="${goodsInfo.cd_push eq '2' ? 'checked' : ''}"  />/>
								</th>
								<br/>
								<th>	
									<label for="chk_grade_nice">진동</label>&nbsp;&nbsp;&nbsp;
									<input type="radio" name="cd_push" id="chk_grade_nice" value="3" <c:out value="${goodsInfo.cd_push eq '3' ? 'checked' : ''}"  />/>
								</th>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
</form>
</div>
</body>
</html>
