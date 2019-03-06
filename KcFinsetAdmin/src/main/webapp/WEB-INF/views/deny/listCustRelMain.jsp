<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
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
	
	function goSearchPerson() {
		
		window.setupValidateForm( frmSchCust );
		if ( !frmSchCust.validateForm() ) return false;
		
		var data = frmSchCust.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("listCustRel", "<c:url value='/person/listCustRel.crz'/>" ,data);
	}
	
	function setNoPerson(no_person) {
		var frm_nm = frmSchCust.frm_nm.value;
		var txt_detail = frmSchCust.txt_detail.value;
		
		if(no_person == null || no_person == "") {
			alert("고객을 선택하여 주세요.");
			return false;
		}else{
			$("#modal-lg").modal('hide');
			if(txt_detail == "txt_detail"){
			eval(frm_nm).txt_detail.value = no_person;
			}else{
				eval(frm_nm).no_person.value = no_person;
			}
		}
	}
	
	function getPersonMain(no_person) {
		var openWindow = window.open("<c:url value='/person/formPersonMain.crz'/>?no_person="+no_person, "popPerson"+no_person, "width=950, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
	
	// 모달 input data 초기화
	function resetCustForm(c1_gender) {
		$("form[name=frmSchCust] input[name=nm_person]").val("");
		$("form[name=frmSchCust] input[name=hp]").val("");
		$("form[name=frmSchCust] input[name=ymd_birth]").val("");
		$("form[name=frmSchCust] input:radio[name=c1_gender]").prop("checked",false);
		
		// 1 : 남자, 2: 여자 라디오버튼 초기화
		$("input:radio[name=c1_gender]:radio[value=1]").parent("label").attr("class","btn");
		$("input:radio[name=c1_gender]:radio[value=2]").parent("label").attr("class","btn");
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="largeModalLabel">고객 찾기</h4>
</div>
<div class="modal-body">
	<form name="frmSchCust" id="frmSchCust">
	<input type="hidden" name="frm_nm" value="${personForm.frm_nm}"/> 
	<input type="hidden" name="txt_detail" value="${personForm.txt_detail}"/> 
	<input type="hidden" name="bgn"/> 
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">검색조건</h3>
				
				<span class="pull-right">
					<button type="button" class="btn btn-default btn-xs" onclick="goSearchPerson();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				</span>
			</div>
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
							<th>성별</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("c1_gender", "c1_gender", "radio", personVO.c1_gender, 0)}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
	<div id="listCustRel">
		<%@ include file="/WEB-INF/views/deny/listCustRel.jsp"%>
	</div>
</div>
