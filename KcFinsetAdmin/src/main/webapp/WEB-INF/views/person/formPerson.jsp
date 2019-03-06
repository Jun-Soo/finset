<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/autocomplete.css"/>"/>
<script type="text/javascript">
function modifyYnUse(){
	window.setupValidateForm( frmPerson );
	if ( !frmPerson.validateForm() ) return false;
	var data = frmPerson.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/modifyYnUse.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			if(result.result == "05") {
				alert(result.message);
			} else {
				var returnData = result.returnData;
				alert(returnData.message);
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}


$(function(){
    $( "#srch_no_person" ).autocomplete({
        source : function( request, response ) {
        	//alert(request.term + ":" + cd_mapping_type + url_svc_map);
             $.ajax({
                    type: 'post',
//                     url: url_svc_map,
                    url: '<c:url value="/person/listPersonInfoAuto.json"/>',
                    dataType: "json",
                    //request.term = $("#autocomplete").val()
                    data: { txt_detail : request.term },
                    success: function(data) {
                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
                        response( 
                            $.map(data.data , function(item) {
                                return {
                                    label: item.data,
                                    value: item.data
                                }
                            })
                        );
                    }
               });
            },
        
        //조회를 위한 최소글자수
        minLength: 2,
        select: function( event, ui ) {
            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
            var no_person_sp = ui.item.value.split("]")[0];
            
            var no_person_re = no_person_sp.replace('[', '');
        	$("#no_person_auto").val(no_person_re);
            
        }
    });
})

</script>
<!-- <div class="popup"> -->
	<div class="modal-body">
		<form name="frmPerson" id="frmPerson">
		<input type="hidden" name="yn_modal" value="${personForm.yn_modal}"/>
		<div class="panel panel-primary">
			<div class="panel-heading">
				기본정보
				<span class="pull-right">
					<button type="button" class="btn btn-default btn-xs" onclick="modifyYnUse();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 수정
					</button>
				</span>
			</div>
			<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><span >고유번호</span></th>
						<td>
							<input type="text" class="form-control width-120" name="no_person" id="no_person" value="${person.no_person}" validate="required; label:고유번호" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th><span >이름</span></th>
						<td>
							<c:choose>
								<c:when test="${empty person.nm_person || ufn:isApprAuth('202001',sessionScope.STR_APPROVAL)}">
									<input type="text" class="form-control width-120" name="nm_person" id="nm_person" value="${person.nm_person}" validate="required; label:이름" readonly="readonly"/>
								</c:when>
								<c:otherwise>
									${person.nm_person}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th><span >휴대폰</span></th>
						<td>
							<c:choose>
			              		<c:when test="${(!empty person.no_person) && ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
			              			${ufn:formatMaskHp(person.hp)} 
			              			<button type="button" class="btn btn-default" onclick="viewPersonnalInfo('${person.no_person}','HP');"><span class="glyphicon glyphicon-zoom-in"></span></button> 
			              		</c:when>
			              		<c:otherwise>
										<input type="text" class="form-control width-120" name="hp" id="hp" value="${person.hp}" validate="required;label:휴대폰;numeric;" readonly="readonly"/>
			              		</c:otherwise>
			              	</c:choose>
						</td>
					</tr>
					<tr>
						<th><span >생년월일</span></th>
						<td>
							<c:choose>
								<c:when test="${empty person.nm_person || ufn:isApprAuth('202001',sessionScope.STR_APPROVAL)}">
									<div class="input-group ssn-w">
										<input type="text" class="form-control" name="ssn_person_tmp" id="ssn_person_tmp" value="${person.ssn_person_idx1}" validate="required;label:주민번호;numeric;keyup-next:ssn_person_tmp" maxlength="6" readonly="readonly"/>
										<span class="input-group-addon" id="basic-addon1">-</span>
										<input type="text" class="form-control" name="ssn_person_tmp" id="ssn_person_tmp" value="${person.ssn_person_idx2}" validate="required;label:주민번호;numeric;" maxlength="1" readonly="readonly"/>	
									</div>
								</c:when>
								<c:otherwise>
									${ufn:formatDate(person.ymd_birth)}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th><span >등록일자</span></th>
						<td>
							<input type="text" class="form-control width-120" name="reg_frt" id="reg_frt" value="${ufn:formatDate(person.dt_frt)}" validate="required; label:등록일자" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th><span >이용약관동의여부</span></th>
						<td>
							<input type="text" class="form-control width-120" name="ins_frt" id="ins_frt" value="${ufn:getCodeName('yn_yes', person.yn_agree_using)}" validate="required; label:설치일자" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th><span >사용여부</span></th>
						<td>
							<select name="yn_use" id="yn_use" class="selectpicker" validate="required;label:사용여부;">
								${ufn:makeOptions("yn_use", "사용여부", person.yn_use)}
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		</form>
		<!--접속 로그  -->			
		<div id="loginHist">
			<%@ include file="/WEB-INF/views/person/listLoginHist.jsp"%>
		</div>
		<!--활동 로그  -->
		<div id="activityHist">
			<%@ include file="/WEB-INF/views/person/listActivityHist.jsp"%>
		</div>
	</div>
<!-- </div> -->
