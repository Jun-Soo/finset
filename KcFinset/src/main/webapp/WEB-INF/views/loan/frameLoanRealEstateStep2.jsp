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
					scrapRealEstate();
				} else{
					alert(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	
	function scrapRealEstate(){
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/scrap/scrapRealEstate.crz'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanRealEstateStep3.crz'/>";
				frmloanApplyStep.submit();
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
<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
<input type="hidden" name="cd_job_class_l" id="cd_job_class_l" value=""/>
<input type="hidden" name="cd_job_class_m" id="cd_job_class_m" value=""/>
<input type="hidden" name="cd_job_class_s" id="cd_job_class_s" value=""/>
<input type="hidden" name="cd_duty_comp" id="cd_duty_comp" value=""/>
<input type="hidden" name="income_info" id="income_info" value="${txFcTransmitVO.income_info }"/>
<input type="hidden" name="loan_code" id="loan_code" value="${txFcTransmitVO.loan_code }"/>
<input type="hidden" name="region_1" id="region_1" value="${txFcTransmitVO.region_1 }"/>
<input type="hidden" name="region_2" id="region_2" value="${txFcTransmitVO.region_2 }"/>
<input type="hidden" name="region_3" id="region_3" value="${txFcTransmitVO.region_3 }"/>
<input type="hidden" name="building_no" id="building_no" value="${txFcTransmitVO.building_no }"/>
<input type="hidden" name="room_no" id="room_no" value="${txFcTransmitVO.room_no }"/>

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
			<li class="active"><span>2. 소득정보등록</span></li>
			<li><span>3</span></li>
			<li><span>4</span></li>
		</ul>
		<h2 class="h2">소득정보 입력</h2>
		<div class="container">
			<p class="help-block">
				맞춤금융상품 조회에 사용될 중요한 정보입니다.<br />
				정확하게 내용을 입력하여 주시기 바랍니다.
			</p>
			<c:choose>
				<c:when test="${txFcTransmitVO.income_info eq '01'}">
					<div class="form-group">
						<label class="control-label">직장명</label>
						<input type="text" class="form-control" name="nm_comp" value="" maxlength="10" validate="required; label:직장명;"/>
					</div>
					
					<div class="form-group">
						<label for="" class="control-label">직/업종</label>
						<div class="input-group">						
							<input type="text" class="form-control" name="job" id="job" placeholder="직업종을 선택하세요" readonly="readonly" validate="required; label:직업종;"/>
			                <label class="input-group-addon">
			                	<button type="button" class="btn btn-search" onclick="popJobChoice();"></button>
			                </label>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label">고용형태</label>
						<select class="selectpicker" name="cd_employee_type" validate="required;select-one;label:고용형태">
							<option value=''>고용형태</option>
							${ufn:makeOptions("cd_employ_type","EMPTY", '')}
						</select>
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
						<select class="selectpicker" name="cd_medi_insu_comp" validate="required;select-one;label:의료보험가입여부">
							<option value=''>선택</option>
							${ufn:makeOptions("cd_medi_insu_comp","EMPTY", '')}
						</select>
					</div>
					<div class="form-group">
						<label for="" class="control-label">4대보험가입여부</label>
						<select class="selectpicker" name="yn_4insu" validate="required;select-one;label:4대보험가입여부">
							<option value=''>선택</option>
							${ufn:makeOptions("yn_yes","EMPTY", '')}
						</select>
					</div>
				</c:when>
				<c:when test="${txFcTransmitVO.income_info eq '02'}">
					<div class="form-group">
						<label class="control-label">사업자번호</label>
						<input type="tel" class="form-control" name="no_biz_comp" value="" maxlength="10" validate="required; label:사업자번호;trim;strip_ws;"/>
					</div>
					<div class="form-group">
						<label class="control-label">업체명</label>
						<input type="text" class="form-control" name="nm_fc" maxlength="10" validate="required; label:업체명;trim;strip_ws;" autocomplete="off"/>
					</div>
					<div class="form-group">
						<label class="control-label">개업일자</label>
						<input type="tel" class="form-control" maxlength="8" name="ymd_start_biz" validate="required; label:개업일자;trim;strip_ws;numeric;" value="" />
					</div>
					<div class="form-group">
						<label for="" class="control-label">업종</label>
						<div class="input-group">						
							<input type="text" class="form-control" name="job" id="job" placeholder="직업종을 선택하세요" readonly="readonly" validate="required; label:직업종;"/>
			                <label class="input-group-addon">
			                	<button type="button" class="btn btn-search" onclick="popJobChoice();"></button>
			                </label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">연소득</label>
						<input type="tel" class="form-control" maxlength="12" name="amt_year_income_biz" value="" validate="required; label:연소득;trim;strip_ws;numeric;"/>
					</div>
					
<!-- 					<div class="form-group"> -->
<!-- 						<label class="control-label">연매출</label> -->
<!-- 						<input type="text" class="form-control" name="amt_year_income_biz" /> -->
<!-- 					</div> -->
					
					<div class="form-group">
						<label class="control-label">사업장소유형태</label>
						<input type="text" class="form-control" name="" maxlength="3" autocomplete="off" validate="required; label:사업장소유형태;trim;strip_ws;"/>
					</div>
				</c:when>
			</c:choose>
		</div>
		<div class="btn-fixed-bottom bottom-over">
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
