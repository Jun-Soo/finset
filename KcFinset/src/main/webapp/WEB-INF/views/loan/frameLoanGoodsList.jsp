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

function loanApplyStepStep() {
	//if ( !frmloanApplyStep.validateForm() ) return false;
	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanIncomeSuccessAfter.crz'/>";
	frmloanApplyStep.submit();
}
function inCome() {
    var cd_goods = $('ul#common_goods').find('li.active').data('cd_goods');
    var cd_fc = $('ul#common_goods').find('li.active').data('cd_fc');
    var amtLimit = $('ul#common_goods').find('li.active').data('amt_limit');
    var no_prepare = $('ul#common_goods').find('li.active').data('no_prepare');

    //상품코드, 금융사 코드 값 체크
    if(cd_goods == null && cd_fc == null) {
        alert('대출상품을 선택해주세요.')
        return false;
    } else {
// 		alert("진짜끝!!:" +cd_goods + ":"+cd_fc + ":"+amtLimit + ":"+no_prepare);
        frmloanApplyStep.cd_goods.value = cd_goods;
        frmloanApplyStep.cd_fc.value = cd_fc;
        frmloanApplyStep.amt_limit.value = amtLimit;
        frmloanApplyStep.no_prepare.value = no_prepare;
    }
    var data = $("#frmloanApplyStep").serialize();
    <c:if test="${site ne 'REAL'}">
    alert("inCome:data="+data);
    </c:if>
    if(data == null) return false;
    $.ajax({
        url : "<c:url value='/m/loan/frameLoanIncomeSuccess.json'/>",
        data : data,
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        type : "POST",
        async : true,
        success : function (result) {
            // debug("insertTxFc:result.result="+result.result+",result.no_bunch="+result.no_bunch);
            // alert("result.result="+result.result+",errorMsg="+result.errorMsg);
            loanApplyStepStep();
        },
        error : function (e) {
            toastMsg("서비스 오류입니다.");
            setTimeout(function() {
                history.back();
            },1500);
        }
    });

}
function loanGoodsDetailStep() {
	if ( !frmloanApplyStep.validateForm() ) return false;
	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanIncomeDetail.crz'/>"
	frmloanApplyStep.submit();
}
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep"  method="post">
<input type="hidden" name="loan_code" value="${loan_code }">
<input type="hidden" name="no_bunch" id="no_bunch" value="${no_bunch}"/>
<input type="hidden" name="title">
<input type="hidden" name="cd_goods">
<input type="hidden" name="cd_fc">
<input type="hidden" name="amt_limit">
<input type="hidden" name="no_prepare">

</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>추천상품</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<ul class="step">
			<li><span>1</span></li>
			<li><span>2</span></li>
			<li><span>3</span></li>
			<li class="active"><span>4. 대출상품안내</span></li>
		</ul>
		<div class="form-lg-block">
			<select class="form-control input-lg">
				<option>정렬방식선택</option>
			</select>
		</div>
		<h2 class="h2 mb0">특별 우대 상품</h2>
		<ul class="loan-block loan-check special-loan">
			<li class="loan-product">
				<a href="#url" class="inner">
					<h3>
						직장인 대출
						<div class="thumb-logo" style="background-image:url('https://www.kbsavings.com/images/common/logo_header.png');"></div>
					</h3>
					<div class="label-badge-group">
						<span class="label-badge badge-primary">우대</span> 
						<span class="label-badge badge-default">담보</span>
					</div>	
					<dl class="loan-info">
						<dt>금리</dt>
						<dd>(변동) 27.9%</dd>
						<dt>한도</dt>
						<dd>500~1,000만원</dd>
						<dt>상환기간</dt>
						<dd>12~24개월</dd>
					</dl>
				</a>
			</li>
		</ul>
		<h2 class="h2 mb0">일반 상품</h2>
		<ul class="loan-block loan-check" id="common_goods">
		<c:forEach var="List" items="${linkedMap}" varStatus="status">
<%-- 			<c:choose>
				<c:when test="${status.count==1}">
					<input type="hidden" name="no_prepare" value="${List.value[0].no_prepare}">
				</c:when>
			</c:choose> --%>
			<li class="loan-product" data-cd_goods="${List.key.cd_goods }" data-cd_fc="${List.key.cd_fc}" data-amt_limit="${List.value[1].amt_limit}" data-no_prepare="${List.value[0].no_prepare}">
				<h3>
					${ufn:getGoodsDetail(List.key.cd_goods, 'NM_GOODS')}
<%-- 					${ufn:getGoodsDetail('A1', 'NM_GOODS')} --%>
					<div class="thumb-logo" style="background-image:url('https://www.standardchartered.co.kr/np/assets/images/kr/base/chb_log_reb.png');"></div>
				</h3>				
				<div class="label-badge-group">
					<span class="label-badge badge-default">신용</span>
				</div>
				 <c:forEach begin="1" end="2" step="2">
					<dl class="loan-info">
						<dt>금리</dt>
						<dd>(변동) ${List.value[1].rto_loan}%</dd>
						<dt>한도</dt>
						<dd>${ufn:formatNumber(List.value[1].amt_limit)} 원</dd>
<%-- 						<dd>${List.amt_limit}만원</dd> --%>
						<dt>상환기간</dt>
						<dd>${List.value[1].year_term}년</dd>
<%-- 						<dd>${List.year_term}년</dd> --%>
					</dl>
				 </c:forEach>
			</li>
		</c:forEach>
			
		</ul>
		<div class="btn-area">
			<button type="button" class="btn btn-lg btn-block btn-arr">다른 추천상품 목록</button>
		</div>
		<div class="bottom-over">
			<div class="col-xs-12">
				<a role="button" onclick="inCome();" class="btn btn-lg btn-primary btn-block">대출신청</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
