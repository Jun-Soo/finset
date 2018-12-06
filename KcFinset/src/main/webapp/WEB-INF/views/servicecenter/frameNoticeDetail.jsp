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
			<h1>공지사항</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="board-block">
			<div class="board-header">
				<h2>공지사항 제목 <span class="board-date">2017.01.01</span><span class="board-count">조회수 : 21</span></h2>
			</div>
			<div class="board-body">
				<span class="notice-date">00-00-00</span>
				Nullam quis risus eget urna mollis ornare 
				vel eu leo. Cum sociis natoque penatibus 
				et magnis dis parturient montes, nascetur 
				ridiculus mus.
				Nullam id dolor id nibh ultricies vehicula. 
				Cum sociis natoque penatibus et magnis 
				dis parturient montes, nascetur ridiculus mus. 
				Donec ullamcorper nulla non metus auctor 
				fringilla.
				Duis mollis, est non commodo luctus,
				nisi erat porttitor ligula, eget lacinia odio
			</div>
			<ul class="board-nav">
				<li class="prev">
					<span>이전글</span>
					<a href="#none">공지사항 제목</a>
				</li>
				<li class="prev">
					<span>다음글</span>
					<a href="#none">공지사항 제목</a>
				</li>
			</ul>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-12">
				<button type="button" class="btn btn-lg btn-default btn-block">목록</button>
			</div>
		</div>
	</section>
	
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>