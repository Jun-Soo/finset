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
			<h1>조회결과</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container-fluid collapse in top-block" id="collapseExample">
			<div class="alert-desc">
				<em>대출 신청은 하나의 상품만 선택해 신청 가능합니다.</em>
				<p>대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도의 변동되거나 대출이 불가능 할  수 있습니다.</p>
				<button data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" class="btn-close"><i class="icon-close">레이어 닫기</i></button>
			</div>
		</div>
		<div class="sort-block affix-fixed top-fixed-item">
			<div class="form-input-inline result-item">
				<div class="result-txt">
					<span class="prd-ea">총 <em>0</em>건</span>
				</div>
				<select class="selectbox" data-header="옵션선택">
					<option>금리낮은순</option>
					<option value="01">한도높은순</option>
					<option value="02">대출기간높은순</option>
				</select>
			</div>
		</div>
		<div class="prd-block prd-type">
			<div class="container-fluid prd-loan">
				<a href="#url">
					<div class="prdlist-heading">
						<span class="thumb-logo" style="background-image:url('https://www.kbsavings.com/images/common/logo_header.png');"></span>
						<!-- <li class="bank-title">금융사명</li> -->
						<h2 class="prd-title">상품명</h2>
					</div>
					<div class="prdlist-info">
						<dl>
							<dt>대출금리</dt>
							<dd class="txt-point"><label>변동</label>00.00%</dd>
						</dl>
						<dl>
							<dt>대출한도</dt>
							<dd class="txt-point">00,000만원</dd>
						</dl>
						<dl>
							<dt>대출기간</dt>
							<dd>60개월(연장시 최대 36개월)</dd>
						</dl>
						<dl>
							<dt>상환방식</dt>
							<dd >원리금균등상환</dd>
						</dl>
					</div>
					<div class="loan-btn">
						<div class="checkbox ico-loan ico-choice">
							<input type="checkbox" id="s1"><label class="" for="s1"></label>
						</div>
					</div>
				</a>
			</div>
			<div class="container-fluid prd-loan">
				<a href="#url">
					<div class="prdlist-heading">
						<span class="thumb-logo" style="background-image:url('https://www.kbsavings.com/images/common/logo_header.png');"></span>
						<!-- <li class="bank-title">금융사명</li> -->
						<h2 class="prd-title">상품명</h2>
					</div>
					<div class="prdlist-info">
						<dl>
							<dt>대출금리</dt>
							<dd class="txt-point"><label>고정</label>00.00%</dd>
						</dl>
						<dl>
							<dt>대출한도</dt>
							<dd class="txt-point">00,000만원</dd>
						</dl>
						<dl>
							<dt>대출기간</dt>
							<dd>60개월(연장시 최대 36개월)</dd>
						</dl>
						<dl>
							<dt>상환방식</dt>
							<dd >원리금균등상환</dd>
						</dl>
					</div>
					<div class="loan-btn">
						<div class="checkbox ico-loan ico-choice">
							<input type="checkbox" id="s2"><label class="" for="s2"></label>
						</div>
					</div>
				</a>
			</div>
			<div class="container-fluid prd-loan">
				<a href="#url">
					<div class="prdlist-heading">
						<span class="thumb-logo" style="background-image:url('https://www.kbsavings.com/images/common/logo_header.png');"></span>
						<!-- <li class="bank-title">금융사명</li> -->
						<h2 class="prd-title">Prime Loan</h2>
					</div>
					<div class="prdlist-info">
						<dl>
							<dt>대출금리</dt>
							<dd class="txt-point"><label>혼합</label>00.00%</dd>
						</dl>
						<dl>
							<dt>대출한도</dt>
							<dd class="txt-point">00,000만원</dd>
						</dl>
						<dl>
							<dt>대출기간</dt>
							<dd>60개월(연장시 최대 36개월)</dd>
						</dl>
						<dl>
							<dt>상환방식</dt>
							<dd >원리금균등상환</dd>
						</dl>
					</div>
					<div class="loan-btn">
						<div class="checkbox ico-loan ico-choice">
							<input type="checkbox" id="s3"><label class="" for="s3"></label>
						</div>
					</div>
				</a>
			</div>
		</div>
		<div class="btn-fixed-bottom">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block">홈으로</button>
			</div>
			<div class="col-xs-6">
				<a href="#none" class="btn btn-lg btn-disabled btn-block">대출신청하기</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
