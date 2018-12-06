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
/*
 * 작성자: 김휘경
 * 작성일: 20180530
 * 설명: 부채 정보를 수정하기 위한 함수
 */


	$(document).ready(function() {
        //selectbox 구성을 위한 배열 선언 -변경이 없을 것이라 생각하고 하드코딩
        var repaymentNmArr = ["만기일시상환","원리금분할상환","원금분할상환"];
        var repaymentValArr = ["03","01","02"];
        
        var loanMountNmArr = ["없음","1년","2년","3년","4년","5년","6년","7년","8년","9년","10년"];
        var loanMountValArr = [null,"1","2","3","4","5","6","7","8","9","10"];
        
        var interPayCycleNmArr = ["매월","분기","년","만기시","특정일"];
        var interPayCycleValArr = ["01","02","03","04","05"];
        
        var interPayDayNmArr =
        ["없음","1일","2일","3일","4일","5일","6일","7일","8일","9일","10일"
        		,"11일","12일","13일","14일","15일","16일","17일","18일","19일","20일"
        		,"21일","22일","23일","24일","25일","26일","27일","28일","29일","30일","31일"];
        var interPayDayValArr = 
        [null,"1","2","3","4","5","6","7","8","9","10"
        		,"11","12","13","14","15","16","17","18","19","20"
        		,"21","22","23","24","25","26","27","28","29","30","31"];
        
        //각 select 태그에 option 넣기
		makeOptions("repayment", repaymentNmArr, repaymentValArr, '<c:out value="${debtVO.rep_method}"/>');
		makeOptions("loan_mount", loanMountNmArr, loanMountValArr, '<c:out value="${debtVO.loan_mount}"/>');
		makeOptions("inter_pay_cycle", interPayCycleNmArr, interPayCycleValArr, '<c:out value="${debtVO.inter_pay_cycle}"/>');
		makeOptions("inter_pay_day", interPayDayNmArr, interPayDayValArr, '<c:out value="${debtVO.inter_pay_day}"/>');
        
		//수정 화면이기 때문에 하단 확인 버튼은 항상 떠 있는 것으로
		affixBottom('show');
		
		//현재 금리의 type이 number이기 때문에 DebtVO가 받지 못하는 현상이 있어서 숨겨진 hidden 타입의 input에 값을 넣어서 전달
		$("#int_interest").on("change",function(){
			$("#interest").val($(this).val());
		});
    });
	
	//frameLoanWorkerStep9 의 713번 째 줄 참조 - 20180530 김휘경
	var makeOptions = function(targetId, optionNmArr, optionValArr, defaultOption){
		var target = document.getElementById(targetId);
		//target을 찾지 못하거나			배열의 길이가 다르면 실행시키지 않음
		if( ((target||'')=='') || (optionNmArr.length != optionValArr.length)){return;}
		for(var i=0; i<optionNmArr.length; i++){
			var option = document.createElement("option");
			option.value = optionValArr[i];
			option.innerHTML = optionNmArr[i];
			target.appendChild(option);
		}
		$(target).val(defaultOption);
		$(target).selectpicker('refresh');
	};
	
	//DEBT_PERSONAL_INFO를 update를 하기 위한 함수
	var updateDebtInfo = function(){
		if(($("#int_interest").val()||"")=="") {
			toastMsg("금리를 입력해주세요");
			return;
		}
		var data = frmUpdateDebtInfo.serialize();
		$.ajax({
			url : "<c:url value='/m/debt/updateDebtInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				switch(result.code){
				//성공
				case '00':
					toastMsg('수정이 완료되었습니다. 이전 화면으로 이동합니다');
					//메세지를 확인하고 넘어가도록 시간 설정
					setTimeout(function(){
						//이전 화면으로
						location = "../debt/frameInDebtDetail.crz?no_manage_info="+$("#no_manage_info").val();
					},500);
					break;
				//debtVO가 null이 떨어진 경우
				case '99':
					toastMsg('필요한 정보가 없습니다. 관리자에게 연락 바랍니다.');
					break;
				default:
					break;
				}
			},
			error : function(e) {
				//db처리 중 에러발생
				toastMsg('처리 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
			}
		});
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
			<h1>정보 수정</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>금리 등 부채 정보를 수정합니다.</p>
			</div>	
			<form name="frmUpdateDebtInfo" id="frmUpdateDebtInfo" method="post" validate="remove_tag;">
				<div class="form-inline">
					<input type="hidden" name="no_person" value="${debtVO.no_person }"/>
					<input type="hidden" id ="no_manage_info" name="no_manage_info" value="${debtVO.no_manage_info }"/>
					<div class="form-group">
						<label for="interest">금 리</label>
						<input type="number" class="form-control" id="int_interest" maxlength="6" autocomplete="off" value="${debtVO.interest }"/>
						<input type="hidden" name="interest" id="interest" value="${debtVO.interest }"/>
						<span class="form-control-feedback" aria-hidden="true">%</span>
					</div>
					<div class="form-group">
						<label for="repayment">상환방법</label>
						<select class="selectpicker" data-header="상환방법" name="rep_method" id="repayment">
						</select>
					</div>
					<div class="form-group">
						<label for="loan_mount">거치기간</label>
						<select class="selectpicker" data-header="거치기간" name="loan_mount" id="loan_mount">
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_cycle">이자납입주기</label>
						<select class="selectpicker" data-header="이자납입주기" name="inter_pay_cycle" id="inter_pay_cycle">
						</select>
					</div>
					<div class="form-group">
						<label for="inter_pay_day">이자납입일</label>
						<select class="selectpicker" data-header="이자납입일" name="inter_pay_day" id="inter_pay_day">
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="updateDebtInfo();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
