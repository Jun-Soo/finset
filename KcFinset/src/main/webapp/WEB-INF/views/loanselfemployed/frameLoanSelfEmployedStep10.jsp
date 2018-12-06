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

	function chkValidate(){
		if(frmloanApplyStep.nm_comp.value == null || frmloanApplyStep.nm_comp.value == ''){
			affixBottom('hide');
		} else if(frmloanApplyStep.no_biz_comp.value == null || frmloanApplyStep.no_biz_comp.value == ''){
			affixBottom('hide');
		} else {
			affixBottom('show');
		}
	}
	
	function loanApplyStepStep(){
		if($("#nm_comp").val() == '' || $("#nm_comp").val() == null) {
			toastMsg('사업자명을 선택해주세요.');
			return false;
		} else if($("#no_biz_comp").val() == '' || $("#no_biz_comp").val() == null) {
			toastMsg('사업자번호를 선택해주세요.');
			return false;
		}
        setCookie('nm_comp'    ,nm_comp    );
        setCookie('no_biz_comp',no_biz_comp);
        history.go(-2);
        <%--frmloanApplyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep8.crz'/>";--%>
        // frmloanApplyStep.submit();
	}
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
			<h1>직장명 직접입력</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
		
		<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
		<input type="hidden" name="cd_occupational" id="cd_occupational" value="${txFcTransmitVO.cd_occupational}"/>
		<input type="hidden" name="amt_year_sale" id="amt_year_sale" value="${txFcTransmitVO.amt_year_sale}"/>
		<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
		<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
		<div class="container">
			<div class="lead">
				<p>사업자명을 직접 입력해 주세요.</p>
				<small>사업자번호는 “-” 없이 입력해 주세요.</small>
			</div>
			<div class="form-inline">
				<div class="form-group">
					<label for="">사업자명</label>
					<input type="text" class="form-control" name="nm_comp" id="nm_comp" autocomplete="off" placeholder="사업자명을 입력해주세요." onkeyup="chkValidate();"/>
				</div>
				<div class="form-group">
					<label for="">사업자번호</label>
					<input type="tel" class="form-control" name="no_biz_comp" id="no_biz_comp" maxlength="10" autocomplete="off" placeholder="사업자번호를 입력해주세요." onkeyup="chkValidate();"/>
				</div>
			</div>
		</div>
		</form>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="loanApplyStepStep();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
