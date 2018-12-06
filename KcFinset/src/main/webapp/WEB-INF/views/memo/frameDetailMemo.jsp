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
 * 메모 리스트 상세, 수정 화면
 * 작성자: 김휘경
 * 작성일: 20180604~20180606
 * 수정: 20180619 김휘경 - 메모 알람 시간 수정 모달 생성
 */
 
$(document).ready(function () {
	//back키 방지
	if(userAgent == "Android") {
		window.Android.setEndApp('Y');
	}
	//알람에 대한 정보 세팅
	setAlarm();
	//각 리스트 클릭시
	$("#btn_update").on("click",function(event){
		event.preventDefault();
		setUpdate();
	});
	
	//쓰레기통 버튼 클릭 시
	$("#btn_confirm_delete").on("click",function(event) {
		event.preventDefault();
		//삭제 확인용 modal 열기
		$("#delConfirmModal").modal("show");
	});
	
	//삭제 확인용 modal에서 삭제를 클릭 시
	$("#btn_delete").on("click",function(event){
		event.preventDefault();
		//아예 이동시킴
		delMemo();
	});
	
	//시계 버튼 클릭 시
	$("#btn_alarm").on("click",function(event) {
		event.preventDefault();
		//modal 열기 - option은 modal 창 외부 클릭 방지
		$("#alarmModal").modal({
			backdrop: 'static',
			keyboard: false
		});
	});	
	
	//알람 시간 수정 확인 버튼 클릭시
	$("#btn_alarm_update").on("click",function(event){
		event.preventDefault();
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
		
		updateMemoAlarm();
	})
	
	//알람 시간 취소하면 input값을 없애준다
	$("#btn_cancel").on("click",function(){
		setAlarm();
		$("#alarm_date").val("");
		$("#alarm_time").val("");
	});
	
	
	//공유 버튼은 아직 미정
	$("#btn_share").on("click",function(event) {
		event.preventDefault();
		toastMsg("준비중 입니다");
	});
});

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

//메모 텍스트를 수정하는 함수
var updateMemoText = function(){
	var data = frmDetailMemo.serialize();
	$.ajax({
		url : "<c:url value='/m/memo/updateMemoText.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			switch(result.code){
			//성공
			case '00':
				toastMsg('수정이 완료되었습니다.');
				//메세지를 확인하고 넘어가도록 시간 설정
				setTimeout(function(){
					//새로고침
					location.reload();
				},500);
				break;
			//데이터 부족으로 인한 실패
			case '99':
				setDefault();
				toastMsg('수정에 필요한 정보가 없습니다. 관리자에게 연락 바랍니다.');
				break;
			default:
				break;
			}
		},
		error : function(e) {
			//db처리 중 에러발생
			setDefault();
			toastMsg('처리 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
		}
	});
};

//메모를 삭제하는 함수
var delMemo = function(){
	frmDetailMemo.action = "<c:url value='/m/memo/delMemo.crz'/>";
	frmDetailMemo.submit();
};

//수정 버튼을 클릭하기 전
var setDefault = function(){
	$("#memo_text").prop("disabled",true);
	$(".bottom-menu").removeClass("div_hidden");
	$("#g-menu").add("div_hidden");
	$("#memo_text").css("height","calc(100% - 55px);");
	$("#memo_text").blur();
};

//수정 버튼을 클릭한 후
var setUpdate = function(){
	$("#memo_text").prop("disabled",false);
	$(".bottom-menu").addClass("div_hidden");
	$("#g-menu").removeClass("div_hidden");
	$("#memo_text").css("height","100%");
	$("#memo_text").focus();
}

//이전 parameter를 확인하고 보냄(no_manage_info의 여부에 따라 갈림)
var goBack = function() {
	frmDetailMemo.action = "<c:url value='/m/memo/frameListMemo.crz'/>";
	frmDetailMemo.submit();
}

//알람 수정 창을 컨트롤 하는 함수
var setAlarm = function() {
	//input time에 대한 formatting 함수가 JSTLFunction에 없기 때문에 일괄적으로 스크립트로 구현
	var time = "<c:out value='${memoVO.alarm_time}'/>";
	//date 형식에 맞춰서 가져오기
	var date = "<c:out value='${ufn:formatDate(memoVO.alarm_date)}'/>";
	//time이 비어있지 않은 지 확인하기
	if((time||'')!=''){
		//중간에 : 집어넣어주기 (input type time 에 맞춰주기 위해)
		var formattedTime = time.substring(0,2) + ":" + time.substring(2);
		//valuie 집어넣기
		$("#outer_alarm_time").val(formattedTime);
		$("#outer_alarm_date").val(date);
	} else { //time이 비어있다면 input에 값 없애기
		setNow();
	}
}

var updateMemoAlarm = function() {
	var data = "seq_memo_info=" + $("#seq_memo_info").val() + "&alarm_date=" + $("#alarm_date").val() + "&alarm_time=" + $("#alarm_time").val();
	$.ajax({
		url : "<c:url value='/m/memo/updateMemoAlarm.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			switch(result.code){
			//성공
			case '00':
				toastMsg('알람 수정이 완료되었습니다.');
				setTimeout(function(){
					//새로고침 - 아이콘을 띄우기 위해서
					location.reload();
				},500);
				break;
			//데이터 부족으로 인한 실패
			case '99':
				setAlarm();
				toastMsg('수정에 필요한 정보가 없습니다. 관리자에게 연락 바랍니다.');
				break;
			default:
				break;
			}
		},
		error : function(e) {
			//db처리 중 에러발생
			setAlarm();
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
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<div id="g-menu" class="g-menu div_hidden">
				<button type="button" class="btn btn-gmenu" onclick="updateMemoText();">저장</button>
			</div>
			<h1>메모</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content_memo">
		<div class="form-group" id="memo-form-group">
			<form name="frmDetailMemo" id="frmDetailMemo" method="POST">
				<div class="list-info">
					<textarea class="form-control" name="memo_text" id="memo_text" disabled="disabled">${memoVO.memo_text }</textarea>
					<div class="bottom-menu">
						<ul class="bottom-ul">
							<li class="bottom-li"><button id="btn_confirm_delete" class="ico ico-delete"></button></li>
							<li class="bottom-li"><button id="btn_update" class="ico ico-update"></button></li>
							<c:choose>
								<c:when test="${empty memoVO.alarm_date}">
									<li class="bottom-li"><button id="btn_alarm" class="ico ico-alarm"></button></li>
								</c:when>
								<c:otherwise>
									<li class="bottom-li"><button id="btn_alarm" class="ico ico-alarm-check"></button></li>
								</c:otherwise>
							</c:choose>
							
						</ul>
					</div>
				</div>
				<div class="div_hidden">
					<input type="hidden" name="no_person" id="no_person" value="${memoVO.no_person }"/>
					<input type="hidden" name="seq_memo_info" id="seq_memo_info" value="${memoVO.seq_memo_info }"/>
					<input type="hidden" name="no_manage_info" id="no_manage_info" value="${memoVO.no_manage_info }"/>
				</div>
			</form>
		</div>
	</div>
	<!-- //Content -->
	
<!-- 	삭제를 확인하는 modal -->
	<div id="delConfirmModal" class="modal">
		<div class="alert-content del-content">
			<div class="alert-body">
			    <div id="body-header" class="alertText">메모를 삭제 할까요?</div>
			    <div id="body-sub">삭제된 메모는 다시 확인하실 수 없습니다.</div>
		    </div>
		    <div class="alert-footer">
		    	<button type="button" class="btn btn-lg btn-default" data-dismiss="modal">아니오</button> 
				<button type="button" id="btn_delete" class="btn btn-lg btn-default" data-dismiss="modal">네, 진행할게요</button>
		    </div>
		</div>
	</div>
	
<!-- 	알람 시간 수정용 modal -->
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
						<input type="time" class="form-control" id="outer_alarm_time" autocomplete="off"/>
						<input type="hidden" name="alarm_time" id="alarm_time" autocomplete="off"/>
					</div>
				</div>
		    </div>
		    <div class="alert-footer">
		    <button type="button" id="btn_cancel" class="btn btn-lg btn-default" data-dismiss="modal">취소</button> 
		    	<button type="button" id="btn_alarm_update" class="btn btn-lg btn-default">확인</button>
		    </div>
		</div>
	</div>
</div>
</body>
</html>
