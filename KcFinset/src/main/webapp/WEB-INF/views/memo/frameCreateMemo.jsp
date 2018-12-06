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
 * 메모 추가 화면
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 */
 
$(document).ready(function () {
	//textarea에 focus가 가도록
	$("#memo_text").focus();
	//알람시간 설정
	$("#btn_alarm").on("click",function(){
		//현재 시각 세팅
		setNow();
		//option은 modal 창 외부 클릭 방지
		$("#alarmModal").modal({
			backdrop: 'static',
			keyboard: false
		});
	});
	//확인버튼 클릭 시
	$("#btn_confirm").on("click",function(){
		//날짜 체크
		if(($("#outer_alarm_date").val()||"")==""){
			toastMsg("날짜를 선택해주세요");
			return;
		//시간 체크
		} else if (($("#outer_alarm_time").val()||"")=="") {
			toastMsg("시간을 선택해주세요");
			return;
		}
		//date와 time 값 가져오기
		var alarm_date = $("#outer_alarm_date").val();
		var alarm_time = $("#outer_alarm_time").val();
		
		//숫자가 아닌 걸 체크하는 정규식
		var regex = /[^0-9]/g;
		
		//숫자가 아닌 모든걸 없앰
		alarm_date = alarm_date.replace(regex,"");
		alarm_time = alarm_time.replace(regex,"");
		
		//각 수정된 값을 input에 넣기
		$("#alarm_date").val(alarm_date);
		$("#alarm_time").val(alarm_time);
		
		//모달 닫기
		$("#alarmModal").modal("hide");
	});
	//알람 시간 취소하면 input값을 없애준다
	$("#btn_cancel").on("click",function(){
		$("#outer_alarm_date").val("");
		$("#outer_alarm_time").val("");
		$("#alarm_date").val("");
		$("#alarm_time").val("");
	});
});

var createMemo = function(event){
	
	$("#outer_alarm_date").prop("disabled",true);
	$("#outer_alarm_time").prop("disabled",true);
	//데이터 존재 여부는 자바단에서 걸러냄
	frmCreateMemo.action = "<c:url value='/m/memo/createMemo.crz'/>";
	frmCreateMemo.submit();
}


//today 날짜 세팅용
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

Date.prototype.toTimeInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(11,16);
});

var setNow = function(){
	$("#outer_alarm_date").val(new Date().toDateInputValue());
	$("#outer_alarm_time").val(new Date().toTimeInputValue());
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
			<div id="g-menu" class="g-menu">
				<button type="button" id="btn_alarm" class="ico-alarm">
				<button type="button" class="btn btn-gmenu" onclick="createMemo();">저장</button>
			</div>
			<h1>메모</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content_memo">
		<div class="form-group">
			<form name="frmCreateMemo" id="frmCreateMemo" method="POST">
				<div class="list-info">
					<textarea class="form-control" name="memo_text" id="memo_text"></textarea>
				<div class="div_hidden">
					<input type="hidden" name="no_person" id="no_person" value="${memoVO.no_person }"/>
					<input type="hidden" name="no_manage_info" id="no_manage_info" value="${memoVO.no_manage_info }"/>
				</div>
				<div id="alarmModal" class="modal">
					<div class="alert-content">
						<div class="alert-body">
						    <div id="body-header" class="alertText">날짜와 시간을 선택해주세요<br/><br/></div>
							<div class="form-inline">
								<div class="form-group">
									<label for="outer_alarm_date">날짜</label>
									<input type="date" class="form-control" id="outer_alarm_date" autocomplete="off"/>
									<input type="hidden" name="alarm_date" id="alarm_date" autocomplete="off"/>
								</div>
								<div class="form-group">
									<label for="outer_alarm_time">시간</label>
									<input type="time" class="form-control" id="outer_alarm_time" autocomplete="off" />
									<input type="hidden" name="alarm_time" id="alarm_time" autocomplete="off"/>
								</div>
							</div>
					    </div>
					    <div class="alert-footer">
					    	<button type="button" id="btn_cancel" class="btn btn-lg btn-default" data-dismiss="modal">취소</button> 
					    	<button type="button" id="btn_confirm" class="btn btn-lg btn-default">확인</button>
					    </div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
