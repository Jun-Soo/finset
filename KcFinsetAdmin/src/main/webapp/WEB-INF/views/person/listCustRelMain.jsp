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
		
		if(no_person == null || no_person == "") {
			alert("고객을 선택하여 주세요.");
			return false;
		}
		$("#modal-lg").modal('hide');
		
		// 고객찾기 버튼이 기본정보에서 눌렸는지 보증인정보에서 눌렸는지 구분한다. P = 기본정보, GP = 보증인정보
		if(frmPrepare.yn_grt.value =="N") {
			// 사전접수신청서의 고객번호가 변하는것을 방지
			frmPrepare.no_person.value = no_person;
			if(IsNull(frmPrepare.no_prepare)) {
				createPrepareInfo();
			} else {
				updateOverlapChk();
			}
		} else if (frmPrepare.yn_grt.value =="Y") {
			if (frmPrepare.no_person.value != no_person){
				procGrtInfo(frmPrepare.no_prepare.value , no_person);
			} else {
				alert("차주는 보증인이 될 수 없습니다.");			
			}
		}
	}
	
	// 보증인 등록, 수정
	function procGrtInfo(no_prepare, no_person) {
	
		var data = {"no_prepare":no_prepare, "no_person":no_person, "cd_rel_grt":$("#cd_rel_grt").val()};
		
		$.ajax({
			url : "<c:url value='/grt/procGrtInfo.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
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
	
	// 고객등록 시 추가정보 추가정보를 보여줌
	function showDivAddtionPersonInfo() {
 		$('#display_addtion_personInfo').toggle();
		$('#display_addtion_personMemo').toggle(); 
		
	}
	
	// 신규 고객 생성 
	function createPerson() {
		
		if(IsNull(frmSchCust.nm_person)){
			alert("이름을 입력해주세요"); return false;
		}else if(IsNull(frmSchCust.hp)){
			alert("휴대폰번호를 입력해주세요"); return false;
		}
		
		frmSchCust.nm_person.value = frmSchCust.nm_person.value.replace(/ /g, '');
		frmSchCust.hp.value =frmSchCust.hp.value.replace(/ /g, '');
		
		// 검색 생년월일로 BGN 생성
		var ymd_birth = "18000101";
		if( !IsNull(frmSchCust.ymd_birth) )
		{
			ymd_birth = frmSchCust.ymd_birth.value.replace( /^\$|-/g, "");
			ymd_birth = ymd_birth.substring(0, 8);
		}
		
		var c1_gender = "0";
		if(null != getRadioCheckedVal(frmSchCust.c1_gender))
			c1_gender = getRadioCheckedVal(frmSchCust.c1_gender);
		
		frmSchCust.bgn.value = ymd_birth +""+ c1_gender;
		
		var data = frmSchCust.ajaxSubmit();
		if (data == null) return false;
		
		$.ajax({
			url : "<c:url value='/person/createPerson.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					$("#modal-lg").modal('hide');
					// loadPrepareForm을 하기 위해 no_prepare를 set
					frmPrepare.no_prepare.value = result.no_prepare;
					loadPrepareForm();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	//연계검사 팝업	
	function popPersonRelation() {
		var no_person = frmSchCust.no_person.value;
		var openWindow = window.open("", "popPersonRelation"+no_person, "width=900, height=600, scrollbars=yes, resizable=yes", "");
		
		frmSchCust.target = "popPersonRelation"+no_person;
		frmSchCust.action = "<c:url value='/person/listPersonRelMain.crz'/>";
		frmSchCust.method = "post";
		frmSchCust.submit();
		openWindow.focus();
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="largeModalLabel">고객 찾기</h4>
</div>
<div class="modal-body">
	<form name="frmSchCust" id="frmSchCust">
	<input type="hidden" name="no_person" value="${personForm.no_person}"/> 
	<input type="hidden" name="bgn"/> 
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">검색조건</h3>
				
				<span class="pull-right">
					<button type="button" class="btn btn-default btn-xs" onclick="goSearchPerson();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetCustForm('${personVO.c1_gender}');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
					<button type="button" class="btn btn-success btn-xs" onclick="popPersonRelation();"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 연계검사</button>
					<c:if test="${empty personForm.no_prepare && personForm.yn_grt ne 'Y'}">
						<button type="button" class="btn btn-primary btn-xs" onclick="showDivAddtionPersonInfo();"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 간편고객등록</button>
					</c:if>
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
						<tr id="display_addtion_personInfo" style="display:none; ">
							<th>광고매체</th>
							<td colspan="1">
								<select name="cd_advertisement" class="selectpicker">
									${ufn:makeOptions("cd_advertisement","광고매체", "11")}
								</select>
							</td>
							<th>상품구분</th>
							<td>
								<select name="cd_goods_type" class="selectpicker">
									${ufn:makeOptions("cd_goods_type","상품구분", "01")}
								</select>
							</td>
							<th>신청금액</th>
							<td colspan="3">
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control align-r" name="amt_apply" validate="label:신청금액;money;"/>
								</div> 만원
							</td>
						</tr>
						<tr id="display_addtion_personMemo" style="display:none; ">
							<th>접수 시 메모</th>
							<td colspan="6"><input type="text" name="memo_from_agency" class="form-control" validate="label:메모;maxbt:2000" maxlength="300"/></td>
							<td>
								<div class="align-r"><button type="button" class="btn btn-default" onclick="createPerson();">등 록</button></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
	<div id="listCustRel">
		<%@ include file="/WEB-INF/views/person/listCustRel.jsp"%>
	</div>
</div>

<div class="modal-footer">
	<c:if test="${empty personForm.no_prepare || personForm.yn_grt eq 'Y'}">
		<button type="button" class="btn btn-primary btn-xs" onclick="getPersonMain('');"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 고객등록</button>
	</c:if>
	<c:if test="${!empty personForm.no_prepare || !empty personForm.no_person}">
		<button type="button" class="btn btn-success btn-xs" onclick="setNoPerson('${personForm.no_person}');"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 고객확인</button>
	</c:if>
</div>