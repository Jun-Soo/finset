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
    var nm_comp     ='';
    var no_biz_comp ='';
    function loanApplyStepStep(){
        var nm_comp     = $("#nm_comp"    ).val();
        var no_biz_comp = $("#no_biz_comp").val();
		if(nm_comp == '' || nm_comp == null) {
			toastMsg('직장명을 선택해주세요.');
			return false;
		} else if(no_biz_comp == '' || no_biz_comp == null) {
			toastMsg('사업자번호를 선택해주세요.');
			return false;
		}
		var isValid = checkBizID(no_biz_comp);
        if(isValid == false) {
            toastMsg('사업자번호가 잘못되었습니다.');
            return false;
        }
        setCookie('nm_comp'    ,nm_comp    );
        setCookie('no_biz_comp',no_biz_comp);
        history.go(-2);
	}
    function checkBizID(bizID) { //사업자등록번호 체크
        // bizID는 숫자만 10자리로 해서 문자열로 넘긴다.
        var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
        var tmpBizID, i, chkSum=0, c2, remander;
        bizID = bizID.replace(/-/gi,'');

        for (i=0; i<=7; i++) chkSum += checkID[i] * bizID.charAt(i);
        c2 = "0" + (checkID[8] * bizID.charAt(8));
        c2 = c2.substring(c2.length - 2, c2.length);
        chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
        remander = (10 - (chkSum % 10)) % 10 ;

        if (Math.floor(bizID.charAt(9)) == remander) return true ; // OK!
        return false;
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
			<input type="hidden" name="amt_year_income" id="amt_year_income" value="${txFcTransmitVO.amt_year_income}"/>
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<div class="container">
				<div class="lead">
					<p>직장명을 직접 입력해 주세요.</p>
					<small>사업자번호는 “-” 없이 입력해 주세요.</small>
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label for="">직장명</label>
						<input type="text" class="form-control" name="nm_comp" id="nm_comp" autocomplete="off" placeholder="직장명을 입력해주세요." onkeyup="chkValidate();"/>
					</div>
					<div class="form-group">
						<label for="">사업자번호</label>
						<input type="tel" class="form-control" name="no_biz_comp" id="no_biz_comp" autocomplete="off" placeholder="사업자번호를 입력해주세요." onkeyup="chkValidate();"/>
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
