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
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>상담신청</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="lead">
			<p>이용 시 궁금하신 점이나 불편 사항을 문의하세요.</p>
		</div>
		<div class="container">
			<div class="form-block">
				<div class="form-group">
					<label for="">문의 유형</label>
					<select class="form-control">
						<option>문의 유형을 선택하세요</option>
					</select>
				</div>
				<div class="form-group">
					<label for="">제목</label>
					<input type="text" class="form-control" placeholder="제목을 입력하세요" />
				</div>
				<div class="form-group">
					<label for="">내용</label>
					<textarea class="form-control" rows="5" placeholder="내용을 입력하세요(최대 1,000자)"></textarea>
				</div>
				<div class="form-group">
					<label for="">회신용 메일</label>
					<input type="text" class="form-control" placeholder="상담결과 회신용 메일 주소를 입력하세요" />
				</div>
			</div>
			<div class="checkbox">
				<label><input type="checkbox"> 동의</label>
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block">취소</button>
			</div>
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block">등록완료</button>
			</div>
		</div>
	</section>
	
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>