<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/autocomplete.css"/>"/>
<script type="text/javascript">
// SelectPicker
$('.selectpicker').selectpicker();
//검색어 자동완성기능
$(function(){
	$( "#item_tag" ).autocomplete({
	    source : function( request, response ) {
	         $.ajax({
	                type: 'post',
	                url: '<c:url value="/fccode/listSrchFcCodeInfo.json"/>',
	                dataType: "json",
	                data: { item_tag : request.term },
	                success: function(data) {
	                    //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                    response( 
	                         $.map(data.data , function(item) {
	                            return {
	                            	label: item.auto_com_txt,
                                    value: item.stdcode_group,
                                    item_tag_value : item.stdnm_code
	                            }
	                        })
	                    );
	                }
	         });
	        },
	    //조회를 위한 최소글자수
	    minLength: 1,
	    select: function( event, ui ) {
	        // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
	    	$("#item_tag").val( ui.item.stdcode_group);
	    	//$("#item_tag_value").val(ui.item.item_tag_value);
	    	var yearHan = document.getElementById("item_tag_value");
	    	yearHan.innerHTML = ui.item.item_tag_value;
	    }
	});
})

function ChangeSelectBox2(frm, obj){
	if(!obj.value) {
		frm.no_edoc2.options.length=0;
		$('#no_edoc2').append("<option value=''>전문번호</option>");
		$('#no_edoc2').selectpicker('refresh');
		return;
	}
	var data = {"cd_fc":obj.value};
	if(data == null) return false;
//	if(!frm.validateForm()) return false;
	 var target= frm.no_edoc2;
	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : "<c:url value='/edoc/listNmEdoc.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
			boxOption = document.createElement("OPTION");
	        boxOption.value = '';     
	        boxOption.text = '전문번호';
	        target.options.add(boxOption);
			$.each(data.List, function (index, item) {
		        boxOption = document.createElement("OPTION");
		        boxOption.value = item;     
		        boxOption.text = item;
		        target.options.add(boxOption);
			});	
	        $('#no_edoc2').selectpicker('refresh');
			target.options.length = data.List.length + 1;
		}
	});
}

//부모코드명 검색어 자동완성기능
$(function(){
	$( "#item_repeat_tag" ).autocomplete({
	    source : function( request, response ) {
	         $.ajax({
	                type: 'post',
	                url: '<c:url value="/fccode/listSrchFcRepeat.json"/>',
	                dataType: "json",
	                data: { item_repeat_tag : request.term , "cd_fc" : frmTest.search_cd_fc.value,  "no_edoc" : frmTest.search_no_edoc.value, "type_txrx" : frmTest.search_type_txrx.value},
	                success: function(data) {
	                    //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                    response( 
	                         $.map(data.data , function(item) {
	                            return {
	                            	label: item.auto_com_txt,
                                    value: item.fcedoc_code_group,
                                    item_repeat_tag_value : item.fcedoc_nm_code
	                            }
	                        })
	                    );
	                }
	         });
	        },
	    //조회를 위한 최소글자수
	    minLength: 1,
	    select: function( event, ui ) {
	        // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
	    	$("#item_repeat_tag").val( ui.item.fcedoc_code_group);
	    	//$("#item_tag_value").val(ui.item.item_tag_value);
	    	document.getElementById("item_repeat_tag_value").innerHTML = ui.item.item_repeat_tag_value;
	    }
	});
})

</script>
<form name="frmFcCodeGroup">
<input type="hidden" name="yn_code_group" value="Y">

<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>순번</th>
			<td colspan="3">
				<input type="text" class="form-control width-100" name="seq_order" value="${fcCodeInfo.seq_order}" validate="label:순서;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
		<tr>
			<th>금융사</th>
			<td>
				<c:choose>
					<c:when test="${empty fcCodeInfo.cd_fc}">
						<select class="selectpicker" name="cd_fc" data-width="80%" onchange="ChangeSelectBox2(document.frmFcCodeGroup, this)">
							${ufn:makeFincorpOptions('금융사', '', 'type', '')}
						</select>
					</c:when>
					<c:otherwise>
<%-- 						<input type="text" class="form-control" name="cd_fc" value="${ufn:getNmFc(fcCodeInfo.cd_fc)}" readonly="readonly"/> --%>
						<input type="text" class="form-control" name="cd_fc" value="${fcCodeInfo.cd_fc}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
			<th>전문번호</th>
			<td>
				<c:choose>
					<c:when test="${empty fcCodeInfo.no_edoc}">
						<select class="selectpicker" name="no_edoc" id="no_edoc2" >
							<option value="">전문번호</option>
						</select>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="no_edoc" value="${fcCodeInfo.no_edoc}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>항목코드</th>
			<td>
				<c:choose>
					<c:when test="${empty fcCodeInfo.code_group}">
						<input type="hidden" name="cd_proc_type" value="C">
						<input type="text" class="form-control" name="code_group" validate="required;label:항목코드;maxbt:100;" maxlength="100"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="code_group" value="${fcCodeInfo.code_group}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			
			</td>
			<th>항목명</th>
			<td>
				<input type="text" class="form-control" name="nm_code" value="${fcCodeInfo.nm_code}" validate="required;label:항목명;maxbt:2000;" maxlength="2000"/>
			</td>
		</tr>
		<tr>
			<th>핀셋표준항목코드</th>
			<td>
				<input type="text" class="form-control" id="item_tag" name="item_tag" value="${fcCodeInfo.item_tag}"  type="text" placeholder="매핑자료를검색해주세요"  validate="label:item_tag;maxbt:30;" maxlength="30"/>
				&nbsp;<label id="item_tag_value"></label> 
			</td>
			<th>길이</th>
			<td>
				<input type="text" class="form-control width-100" name="field_length" value="${fcCodeInfo.field_length}" validate="label:길이;maxbt:30;" maxlength="30"/>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>
				<input type="text" class="form-control" name="etc" value="${fcCodeInfo.etc}" validate="label:표준데이터항목설명;maxbt:200;" maxlength="200"/>
			</td>
			<th>속성</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("cd_type_attr", "type_attr", "radio", fcCodeInfo.type_attr, 0)}
			</td>
		</tr>
		<tr>
			<th>송수신구분</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("cd_type_txrx", "type_txrx", "radio", fcCodeInfo.type_txrx, 0)}
			</td>
			<th>사용여부</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("yn_yes", "yn_use", "radio", fcCodeInfo.yn_use, 0)}
			</td>
		</tr>
<%-- 		<c:if test="${!empty List}"> --%>
			<tr>
				<th>부모코드명</th>
				<td colspan="3">
					<input type="text" class="form-control" id="item_repeat_tag" name="item_repeat_tag" value="${fcCodeInfo.parent_code_group}" placeholder="매핑자료를검색해주세요"  validate="label:item_tag;maxbt:30;" maxlength="30"/>
					<input type="hidden" class="form-control" id="parent_code_group" name="parent_code_group" value="${fcCodeInfo.parent_code_group}" placeholder="매핑자료를검색해주세요"  validate="label:item_tag;maxbt:30;" maxlength="30"/>
					&nbsp;<label id="item_repeat_tag_value"></label>
				</td>
			</tr>
<%-- 		</c:if> --%>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-default btn-xs" onclick="updateFcCodeSeq();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 순번변경</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="loadFcCodeGroup('','','','','', this);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procFcCodeInfo(document.frmFcCodeGroup,'G');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delFcCodeInfo(document.frmFcCodeGroup,'G');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>
