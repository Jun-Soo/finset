<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>개별 푸시 보내기</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
	//	window.setupValidateForm(frmSendPushEach);
		$('body').layout({
			minSize:				40
			,	west__size:				"700" //좌측 width
			//,	east__size:				"60%" //우측 width
	 		,	north__spacing_open:	0
			,	north__resizable:		false
			,	west__resizable:		false
			,	spacing_open:			5
			,	spacing_closed:			5
			,	togglerLength_open:		50
			,	togglerLength_closed:	"100%"
			,	east__initClosed:		false
		});
		//datepicker
		$('.input-daterange').datepicker({
			format: "yyyy-mm-dd", //dateformat
			todayHighlight: true, //당일 표시
			todayBtn: "linked", //today button link 활성화
			autoclose: true, //달력 자동 닫힘
			beforeShowDay : nationalDays
		});
		
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm(frmSchCust);
	});
	
	//푸시 발송
	function sendPushMsg() {

		if (!frmSendPushEach.validateForm()) {
			return false;
		}
	    var list_tbody = document.getElementById('list_tbody');
	    var list_tbody_rows = list_tbody.rows.length;
	    if (list_tbody_rows < 1){
			alert("발송 대상 고객을 선택하십시오.");
	    	return;
	    }
	    else{
	    	var arrText = new Array(list_tbody_rows);
	    	var v_tbody_no_person = "";
	    	
		    for(var i=0; i < list_tbody_rows; i++){

		    	v_tbody_no_person = list_tbody.rows[i].cells[1].innerHTML;
		    	arrText[i] = v_tbody_no_person;
		    }
		    $("#array_no_person").val(arrText);
	    }
	    
	    var data = frmSendPushEach.ajaxSubmit();

		$.ajax({
			url : "<c:url value='/push/sendPushEachMsg.json'/>",
			data : data,
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				if (result.result == "00") {
					alert("푸시 메시지 전송에 성공 했습니다.");
					self.close();
					opener.goSearch();
				} else if(result.result == "11"){
					alert("알림을 허용하지 않은 고객입니다.");
				} else {
					alert("푸시 메시지 전송에 실패 했습니다.");
				}
			},
			error : function(e) {
				alert("푸시 메시지 전송에 실패 했습니다." + e.responseText);
			}
		});
	}
	
	//고객 검색
	function goSearchPerson() {
		
		window.setupValidateForm( frmSchCust );
		if ( !frmSchCust.validateForm() ) return false;
		
		var data = frmSchCust.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("listCustRel", "<c:url value='/push/listCustRel.crz'/>" ,data);
	}

	//발송대상고객 리스트에 추가
	function setNoPerson(no_person,nm_person,ymd_birth) {
		 
		if(no_person == null || no_person == "") {
			alert("고객을 선택하여 주세요.");
			return false;
		}else{
	    	var list_tbody = document.getElementById("list_tbody");
		    var v_tbody_no_person = "";
		    for(var i=0; i < list_tbody.rows.length; i++){
		    	v_tbody_no_person = list_tbody.rows[i].cells[1].innerHTML;
		    	if(v_tbody_no_person == no_person) {
		    		alert("이미 선택한 고객입니다.");
		    		return;
		    	}
		    }
		    
		    var row = list_tbody.insertRow( list_tbody.rows.length ); // 하단에 추가
		    var cell1 = row.insertCell(0);
		    var cell2 = row.insertCell(1);
		    var cell3 = row.insertCell(2);
		    var cell4 = row.insertCell(3);
		    cell1.innerHTML = '<button type="button" class="btn btn-primary btn-xs" onclick="deleteNoPerson(\''+no_person+'\');"> 삭제</button>'; //삭제
		    cell2.innerHTML = no_person; //회원번호
		    cell3.innerHTML = nm_person; //이름
		    cell4.innerHTML = ymd_birth; //생년월일
		}
	}
	//발송대상고객 리스트에서 삭제
	function deleteNoPerson(no_person){
	    var list_tbody = document.getElementById('list_tbody');
	    if (list_tbody.rows.length < 1 || no_person == "" || no_person == null) return;
	    
	    var findRow = 0;
	    var v_tbody_no_person = "";
	    for(var i=0; i < list_tbody.rows.length; i++){
	    	v_tbody_no_person = list_tbody.rows[i].cells[1].innerHTML;
	    	if(v_tbody_no_person == no_person) {
	    		findRow = i;
	    		break;
	    	}
	    }
	    list_tbody.deleteRow( findRow );
	}
	
/* 	// 모달 input data 초기화
	function resetCustForm() {
		$("form[name=frmSchCust] input[name=nm_person]").val("");
		$("form[name=frmSchCust] input[name=hp]").val("");
		$("form[name=frmSchCust] input[name=ymd_birth]").val("");
	}
 */
	// 발송 대상 고객 초기화
	function resetTbody(){
	    var list_tbody = document.getElementById('list_tbody');
	    var list_tbody_rows = list_tbody.rows.length;
	    if (list_tbody_rows > 0){
		    for(var i=0; i < list_tbody_rows; i++){
			    list_tbody.deleteRow(0);
		    }
	    }
	}

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<body>
<!-- Layout-West -->
<div class="ui-layout-west">
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">고객 검색</h1>
		</div>
		<!--// title -->
		<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCust" id="frmSchCust">
			<input type="hidden" name="frm_nm" value="${personForm.frm_nm}"/> 
			<input type="hidden" name="txt_detail" value="${personForm.txt_detail}"/> 
			<input type="hidden" name="bgn"/> 
				<div class="panel panel-default toggle-panel">
					<div class="panel-collapse">
						<table class="table table-classic">
							<colgroup>
								<col width="8%"/>
								<col width="10%"/>
								<col width="8%"/>
								<col width="15%"/>
								<col width="8%"/>
								<col width="11%"/>
								<col width="5%"/>
								<col width="13%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>이름</th>
									<td>
										<input type="text" class="form-control width-120" name="nm_person" value="${personVO.nm_person}" validate="label:이름;keydown-enter:goSearchPerson;"/>
									</td>
									<th>휴대폰</th>
									<td>
										<c:choose>
						              		<c:when test="${(!empty personVO.no_person) && ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
						              			${ufn:formatMaskHp(personVO.hp)} 
						              		</c:when>
						              		<c:otherwise>
												<input type="text" class="form-control width-120" name="hp" value="${personVO.hp}" validate="label:휴대폰;keydown-enter:goSearchPerson;"/>
						              		</c:otherwise>
						              	</c:choose>
									</td>
									<th>생년월일</th>
									<td>
										<div class="input-daterange input-group date-w" id="datepicker">
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
											<input type="text" class="input-sm form-control" name="ymd_birth" value="${personVO.ymd_birth}" validate="date"/>
										</div>
									</td>
									<td>
										<span class="pull-right">
											<button type="button" class="btn btn-default btn-xs" onclick="goSearchPerson();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
		<div id="listCustRel">
			<%@ include file="/WEB-INF/views/push/listCustRel.jsp"%>
		</div>
	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
<!-- 		title -->
		<div class="h-title">
			<h1 class="pull-left">발송 메시지</h1>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none">메시지 발송</a>
				</h3>
				<span class="pull-right">
				
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmSendPushEach');resetTbody();">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
					</button>
					
					<button type="button" class="btn btn-default btn-xs" onclick="sendPushMsg();">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 발송
					</button>
					
				</span>
			</div>
			<div class="panel-collapse toggle-cont">
				<form name="frmSendPushEach" id="frmSendPushEach">
					<input type="hidden" class="form-control" name="array_no_person" id="array_no_person" />
					<div class=" panel-primary">
						<table class="table table-classic">
		 					<colgroup>
								<col width="20%">
								<col width="80%">
							</colgroup>
							<tbody> 
								<tr>
									<th>제목</th>
									<td>
										<input type="text" class="form-control" name="title" validate="required;label:제목;maxbt:100;" maxlength="100" />
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<textarea  class="form-control" rows="10" name="body" validate="required;label:내용;maxbt:1000;" maxlength="1000"></textarea>
									</td>
								</tr>
								<tr>
									<th>URL</th>
									<td>
										<input type="text" class="form-control" name="link_addr" maxlength="100" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="divListSendPerson" class="d-table">
						<h3 class="h-sec pull-left">
							<a href="#none">발송 대상 고객</a>
						</h3>
						<table id="listSendPersonDetail" class="table table-bordered" cellspacing="0" width="100%">
							<colgroup>
								<col width="50px"/>
								<col width="100px"/>
								<col width="100px"/>
								<col width="*"/>
							</colgroup>
							<thead>
								<tr>
									<th>선택</th>
									<th>회원번호</th>
									<th>이름</th>
									<th>생년월일</th>
								</tr>
							</thead>
							<tbody id="list_tbody">
							</tbody>
						</table>
					</div>
				
				</form>
			</div>
		</div>
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

</body>
</html>