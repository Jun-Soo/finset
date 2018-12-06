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
	
	//tx_fc_transmit테이블에 신청금액, 대출용도, 대출기간 등 insert 하기 위한 함수
	function insertTxFc(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
		frmloanApplyStep.cd_sex.value =  frmloanApplyStep.ssn2.value.charAt(0);
		frmloanApplyStep.ymd_birth.value = frmloanApplyStep.bgn.value.substring(0,8);
		if(frmloanApplyStep.hp.value.length == 10){
			$('#hp1').val(frmloanApplyStep.hp.value.substring(0,3));
			$('#hp2').val(frmloanApplyStep.hp.value.substring(3,6));
			$('#hp3').val(frmloanApplyStep.hp.value.substring(6,10));
		} else if (frmloanApplyStep.hp.value.length == 11){
			$('#hp1').val(frmloanApplyStep.hp.value.substring(0,3));
			$('#hp2').val(frmloanApplyStep.hp.value.substring(3,7));
			$('#hp3').val(frmloanApplyStep.hp.value.substring(7,11));
		}
		
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loan/insertTxFc.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					frmloanApplyStep.no_bunch.value = result.no_bunch;
					loanApplyStepStep();
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function loanApplyStepStep() {
		frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
		
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanSelfEmployedStep2.crz'/>";
		frmloanApplyStep.submit();
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
<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
<input type="hidden" name="no_person" value="${personVO.no_person}"/>
<input type="hidden" name="ssn_person" value=""/>
<input type="hidden" name="nm_person" value="${personVO.nm_person}"/>
<input type="hidden" name="hp" value="${personVO.hp}"/>
<input type="hidden" name="hp1" id="hp1"/>
<input type="hidden" name="hp2" id="hp2"/>
<input type="hidden" name="hp3" id="hp3"/>
<input type="hidden" name="ssn1" id="ssn1" value="${personVO.ssn_person }"/>
<input type="hidden" name="bgn" id="bgn" value="${personVO.bgn }"/>
<input type="hidden" name="ymd_birth" id="ymd_birth" value=""/>
<input type="hidden" name="no_bunch" id="no_bunch" value=""/>
<input type="hidden" name="loan_code" value="${loan_code }">
<input type="hidden" name="cd_job_class_l" id="cd_job_class_l" value=""/>
<input type="hidden" name="cd_job_class_m" id="cd_job_class_m" value=""/>
<input type="hidden" name="cd_job_class_s" id="cd_job_class_s" value=""/>
<input type="hidden" name="cd_duty_comp" id="cd_duty_comp" value=""/>
<input type="hidden" name="cd_sex" id="cd_sex" value=""/>
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
			<li class="active"><span>2. 신청정보입력</span></li>
			<li><span>3</span></li>
			<li><span>4</span></li>
		</ul>
		<h2 class="h2">신청인 정보</h2>
		<div class="container">
			<div class="form-group">
				<label class="control-label">고객명</label>
				<p class="form-control">${personVO.nm_person}</p>
			</div>
			<div class="form-group">
				<label class="control-label">생년월일</label>
				<div class="row">
					<div class="col-xs-5">
						<input type="text" value="${personVO.ssn_person}" class="form-control" name="" placeholder="" readonly="readonly" />
					</div>
					<div class="col-xs-7">
						<input type="password" class="form-control" name="ssn2" id="ssn2" placeholder="주민번호뒷자리" maxlength="7" validate="required; label:주민등록번호뒷자리;numeric;minlength:7;" autocomplete="off"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label">성별</label>
				<p class="form-control">
					<c:choose>
						<c:when test="${personVO.c1_gender eq '1'}">
							남자
						</c:when>
						<c:when test="${personVO.c1_gender eq '2'}">
							여자
						</c:when>
					</c:choose>
				</p>
			</div>
		</div>
		<h2 class="h2">신청정보 입력</h2>
		<div class="container">
			<p class="help-block">
				맞춤금융상품 조회에 사용될 중요한 정보입니다.<br />
				정확하게 내용을 입력하여 주시기 바랍니다.
			</p>
			<div class="form-group">
				<label class="control-label">신청금액</label>
				<input type="tel" class="form-control" name="amt_wanted" maxlength="10" placeholder="숫자만 입력하세요" validate="required; label:신청금액;numeric;" autocomplete="off"/>
			</div>
			<div class="form-group">
				<label class="control-label">대출용도</label>
				<select class="selectpicker" name="cd_loan_use" validate="required;select-one;label:대출용도">
					<option value=''>대출용도</option>
					${ufn:makeOptions("cd_loan_use","EMPTY", '')}
				</select>
			</div>
			<div class="form-group">
				<label class="control-label">대출기간</label>
				<select class="selectpicker" name="cd_loan_term" validate="required;select-one;label:대출기간">
					<option value=''>대출기간</option>
					${ufn:makeOptions("cd_loan_term","EMPTY", '')}
				</select>
			</div>
			<div class="form-group">
				<label class="control-label">사업자번호</label>
				<input type="tel" class="form-control" name="no_biz_comp" maxlength="10" placeholder="숫자만 입력하세요" validate="required; label:사업자번호;minlength:10;" autocomplete="off"/>
			</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-xs-4 control-label">사업장명</label> -->
<!-- 						<div class="col-xs-8"> -->
<!-- 							<input type="tel" class="form-control" name="nm_comp" placeholder="" /> -->
<!-- 						</div> -->
<!-- 					</div> -->
			<div class="form-group">
				<label class="control-label">개업일자</label>
				<input type="tel" class="form-control" maxlength="8" name="ymd_start_biz" placeholder="숫자만 입력하세요" validate="required; label:개업일자;numeric;" autocomplete="off"/>
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
				<label class="control-label">연소득</label>
				<input type="tel" class="form-control" name="amt_year_income_biz" maxlength="12" placeholder="숫자만 입력하세요" validate="required; label:연소득;numeric" autocomplete="off"/>
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-6">
			
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="loanApplyCancel()">취소</button>
			</div>
			<div class="col-xs-6">
				<a role="button" onclick="insertTxFc();" class="btn btn-lg btn-primary btn-block">다음</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</form>
</body>
</html>
