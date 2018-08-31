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
	
	function updateIncomeInfo(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loan/updateTxFc.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanGoodsList.crz'/>"
					frmloanApplyStep.submit();
				} else {
					alert(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	
	//직업선택 모달
	function popJobChoice(){
		$("#popModal").modal("show");
		
		$.ajax({
			url : "<c:url value='/m/loan/popJobChoice.crz'/>",
			data : {},
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				$("#modal-content").html(result);
			},
			error : function(e) {
				errMsg(e);
			}
		});
	}
	
	//취소버튼 클릭시 대출 초기 페이지로
	function loanApplyCancel(){
		if(!confirm("취소를 하시겠습니까?")) return false;
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanApply.crz'/>"
		frmloanApplyStep.submit();
	}
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" method="post">
<input type="hidden" name="no_person" value="${personVO.no_person}"/>
<input type="hidden" name="nm_person" value="${personVO.nm_person}"/>
<input type="hidden" name="ssn_person" value="${personVO.ssn_person}"/>
<input type="hidden" name="hp" value="${personVO.hp}"/>
<input type="hidden" name="no_bunch" id="no_bunch" value="${personVO.no_bunch}"/>
<input type="hidden" name="loan_code" id="loan_code" value="${personVO.loan_code}"/>
<input type="hidden" name="cd_job_class_l" id="cd_job_class_l" value=""/>
<input type="hidden" name="cd_job_class_m" id="cd_job_class_m" value=""/>
<input type="hidden" name="cd_job_class_s" id="cd_job_class_s" value=""/>
<input type="hidden" name="cd_duty_comp" id="cd_duty_comp" value=""/>

<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>개인금융</h1>
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
			<li class="active"><span>3. 소득정보등록</span></li>
			<li><span>4</span></li>
		</ul>
		<h2 class="h2">소득정보 입력</h2>
		<div class="container">
			<p class="help-block">
				맞춤금융상품 조회에 사용될 중요한 정보입니다.<br />
				정확하게 내용을 입력하여 주시기 바랍니다.
			</p>
			<div class="form-group">
				<label class="control-label">직장명</label>
				<input type="text" class="form-control" name="nm_comp" value="${txFcTransmitVO.nm_comp }" readonly="readonly" />
			</div>
			
			<div class="form-group">
				<label for="" class="control-label">직/업종</label>
				<input type="text" class="form-control" value="${personVO.job }" readonly="readonly" />
			</div>
			
			<div class="form-group">
				<label class="control-label">고용형태</label>
				<input type="text" class="form-control" name="cd_employee_type" value="${ufn:getCodeName('cd_employ_type', txFcTransmitVO.cd_employee_type)}"  readonly="readonly" />
			</div>
		
			<div class="form-group">
				<label for="" class="control-label">연소득금액</label>
				<input type="tel" name="amt_year_income" class="form-control" maxlength="10" placeholder="숫자만 입력하세요" validate="required; label:연소득금액;" autocomplete="off"/>
			</div>
			
			<div class="form-group">
				<label for="" class="control-label">입사일자</label>
				<input type="tel" name="ymd_start_comp" class="form-control" maxlength="8" placeholder="숫자만 입력하세요" validate="required; label:입사일자;" autocomplete="off"/>
			</div>
			<div class="form-group">
				<label for="" class="control-label">의료보험가입여부</label>
				<select class="selectpicker" name="cd_medi_insu_comp">
					<option value=''>선택</option>
					${ufn:makeOptions("cd_medi_insu_comp","EMPTY", '')}
				</select>
			</div>
			<div class="form-group">
				<label for="" class="control-label">4대보험가입여부</label>
				<select class="selectpicker" name="yn_4insu">
					<option value=''>선택</option>
					${ufn:makeOptions("yn_yes","EMPTY", '')}
				</select>
			</div>
		</div>
		<div class=" bottom-over">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="loanApplyCancel()">취소</button>
			</div>
			<div class="col-xs-6">
				<a role="button" onclick="updateIncomeInfo();" class="btn btn-lg btn-primary btn-block">다음</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
	
</div>
</form>
</body>
</html>
