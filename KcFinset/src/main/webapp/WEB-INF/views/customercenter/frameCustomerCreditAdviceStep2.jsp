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

//뒤로가기
function goBack(){
	frmCreditAdviceStep2.action = "<c:url value='/m/customercenter/frameCustomerCreditAdviceStep1.crz'/>";
	frmCreditAdviceStep2.submit();
}

//신용상담 신청
function goStepCreditAdvice(){
	frmCreditAdviceStep2.action = "<c:url value='/m/customercenter/frameCustomerCreditAdviceStep3.crz'/>";
	frmCreditAdviceStep2.submit();
}
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>신용상담신청</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCreditAdviceStep2" id="frmCreditAdviceStep2">
	
	<section id="content">
		<div class="container">
			<div class="form-inline">
				<div class="form-group">
					<label>이름</label>
 					<!-- <p class="form-control-static">텍스트</p> -->
					<input type="text" value="${counselInfo.nm_person}" class="form-control" readonly disabled/>
				</div>
				<div class="form-group">
					<label for="">연령</label>
					<input type="text" value="만 ${counselInfo.age}세(${counselInfo.sex})" class="form-control"  readonly disabled/>
				</div>
			</div>
			<h2 class="h2">신용</h2>
			<div class="form-inline">
				<div class="form-group">
					<label>신용등급</label>
					<input type="text" value="${counselInfo.grade_credit}등급(${counselInfo.rating_credit})" class="form-control"  readonly disabled/>
				</div> 
				<div class="form-group">
					<label>연체금액</label>
					<input type="number" value="${counselInfo.bal_overdue}" class="form-control" readonly disabled>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>
			<h2 class="h2">부채</h2>
			<div class="form-inline">
				<div class="form-group">
					<label>대출원금</label>
					<input type="number" value="${counselInfo.amt_contract}" class="form-control" readonly disabled/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
				<div class="form-group">
					<label>대출잔액</label>
					<input type="number" value="${counselInfo.amt_remain}" class="form-control" readonly disabled/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
				<div class="form-group">
					<label>월상환액</label>
					<input type="number" value="${counselInfo.cur_mm_amt_repay}" class="form-control" readonly disabled/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>
			<h2 class="h2">신용카드</h2>
			<div class="form-inline">
				<div class="form-group">
					<label>이용카드</label>
					<input type="number" value="${counselInfo.cnt_card_use}" class="form-control" readonly disabled/>
					<span class="form-control-feedback" aria-hidden="true">개</span>
				</div>
				<div class="form-group">
					<label>월이용액</label>
					<input type="number" value="${counselInfo.amt_card_total}" class="form-control" readonly disabled/>
					<span class="form-control-feedback" aria-hidden="true"> 만원</span>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a onclick="goStepCreditAdvice();" role="button" class="btn btn-lg btn-primary btn-block">추가정보입력</a>
		</div>
	</section>
	
	</form>
	<!-- //Content -->
</div>
</body>
</html>
