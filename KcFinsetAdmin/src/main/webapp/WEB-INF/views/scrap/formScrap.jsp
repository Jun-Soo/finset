<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
//검색어 자동완성기능
$(function(){
	$( "#apartment_nm" ).autocomplete({
	    source : function( request, response ) {
	         $.ajax({
	                type: 'post',
	                url: '<c:url value="/kbrealestate/listSrchApartmentInfo.json"/>',
	                dataType: "json",
	                data: { APARTMENT : request.term, REGION1_CODE : frmScrap.REGION1_CODE.value, REGION2_CODE : frmScrap.REGION2_CODE.value, REGION3_CODE : frmScrap.REGION3_CODE.value },
	                success: function(data) {
	                    //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                    response( 
	                         $.map(data.data , function(item) {
	                            return {
	                            	label: item.auto_com_txt,
                                    code : item.APARTMENT,
                                    value: item.APARTMENT_NAME/*,
                                    APARTMENT_value: item.APARTMENT_NAME*/
	                            }
	                        })
	                    );
	                }
	         });
	        },
	    //조회를 위한 최소글자수
	    minLength: 1,
	    select: function( event, ui ) {
	    	$("#APARTMENT").val(ui.item.code);
	    	// 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
	    	//$("#APARTMENT").val(ui.item.value);
	    	//$("#APARTMENT_value").val(ui.item.APARTMENT_NAME);
	    	//$("#apartment_nm").val(ui.item.label);
	    	//alert($("#apartment_nm").val());
	    }
	});
	
	ChangeSelect(document.frmScrap);
})

function ChangeSelectBox(frm, obj){
	var url;
	var data;
	
	var objId = $(obj).attr('id');
	var objVal = $("#"+objId).val();
	var target;
	if(objId == "BUILDING_TYPE"){ //	빌딩 종류
		target= frm.REGION1_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
		data = {"BUILDING_TYPE":1};
// 		alert("111 :"+JSON.stringify(data));
		frm.REGION1_CODE.options.length=0;
		$('#REGION1_CODE').append("<option value=''>시/도</option>");
		$('#REGION1_CODE').selectpicker('refresh');
		
		frm.REGION2_CODE.options.length=0
		$('#REGION2_CODE').append("<option value=''>시/군/구</option>");
		$('#REGION2_CODE').selectpicker('refresh');
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#apartment_nm').val('');
	}
		else if(objId == "REGION1_CODE")
	{
		target= frm.REGION2_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion2.json'/>";
		data = {"REGION1_CODE":objVal};
// 		alert("222 :"+JSON.stringify(data));
		frm.REGION2_CODE.options.length=0
		$('#REGION2_CODE').append("<option value=''>시/군/구</option>");
		$('#REGION2_CODE').selectpicker('refresh');
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#apartment_nm').val('');
	}
		else if(objId == "REGION2_CODE")
	{
		target= frm.REGION3_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion3.json'/>";
		data = {"REGION2_CODE":objVal};
// 		alert("333 :"+JSON.stringify(data));
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#apartment_nm').val('');
	}
	
	if(data == null) return false;

	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : url,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
		
			if(objId == "BUILDING_TYPE"){ //	빌딩 종류
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/도';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION1_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;				
				
			}
				else if(objId == "REGION1_CODE")// 시/도
			{
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/군/구';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION2_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;	
				
			}
				else if(objId == "REGION2_CODE")// 시/군/구
			{
					boxOption = document.createElement("OPTION");
			        boxOption.value = '';     
			        boxOption.text = '읍/면/동';
			        target.options.add(boxOption);
					$.each(data.List, function (index, item) {
				        boxOption = document.createElement("OPTION");
				        boxOption.value = item.code_value;     
				        boxOption.text = item.nm_code;
				        target.options.add(boxOption);
					});	
			        $('#REGION3_CODE').selectpicker('refresh');
					target.options.length = data.List.length + 1;
			}
		}
	});
}

function ChangeSelectBox2(frm, obj){
	$('#apartment_nm').val('');
}

function ChangeSelect(frm){
	var url;
	var data;
	
// 	var objId = $(obj).attr('id');
// 	var objVal = $("#"+objId).val();
	var target;

		target= frm.REGION1_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
		data = {"BUILDING_TYPE":1};
// 		alert("111 :"+JSON.stringify(data));
		frm.REGION1_CODE.options.length=0;
		$('#REGION1_CODE').append("<option value=''>시/도</option>");
		$('#REGION1_CODE').selectpicker('refresh');
		
		frm.REGION2_CODE.options.length=0
		$('#REGION2_CODE').append("<option value=''>시/군/구</option>");
		$('#REGION2_CODE').selectpicker('refresh');
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#APARTMENT').val('');

	
	if(data == null) return false;

	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : url,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
		
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/도';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION1_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;				
		}
			
	});
}
</script>
<div class="popup">
	<div class="modal-body">
		<div class="align-r">
			<button type="submit" class="btn btn-default btn-xs" onclick="procScrap();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장2</button>
		</div>
		<form name="frmScrap" id="frmScrap">
		<input type="hidden" name="yn_modal" value="${ScrapForm.yn_modal}"/>
		<div class="panel panel-primary">
			<div class="panel-heading">기본정보</div>
			<div class="panel-collapse">
			<table class="table table-classic">
				<tbody>
				<tr><th>API_ID</th><td>
				<select class="selectpicker" name="API_ID" id="API_ID">
	 				<option value="9920" selected >9920:주소검색</option>
	 				<option value="9921">9921:시세검색</option>
	 			</select>
				<!-- <input type="text" class="form-control width-120" name="API_ID" id="API_ID" value="9920" validate="label:API_ID"/> -->
				9920:주소, 9921시세</td></tr>
				<tr><th>ORGCD</th><td><input type="text" class="form-control width-120" name="ORGCD" id="ORGCD" value="007" validate="label:ORGCD"/></td></tr>
				
				<tr><th>BUILDING_TYPE</th><td>
<!-- 				<select class="selectpicker" name="BUILDING_TYPE" id="BUILDING_TYPE"> -->
<!-- 	 				<option value="1" selected >1.아파트</option> -->
<!-- 	 				<option value="2">2.오피스텔</option> -->
<!-- 	 			</select> -->
<!-- 					<select name="BUILDING_TYPE"  id="BUILDING_TYPE" data-width="20%" class="selectpicker" validate=" label:BUILDING_TYPE" onchange="ChangeSelectBox(document.frmScrap, this)"> -->
<%-- 						${ufn:makeOptions("cd_building_type","선택", kbMarketPriceInfo.BUILDING_TYPE)} --%>
<!-- 					</select> -->
						${ufn:makeRadioAndCheckBoxs("cd_building_type", "building_type", "radio", kbMarketPriceInfo.BUILDING_TYPE, 0)}
<!--				<input type="text" class="form-control width-120" name="building_type" id="building_type" value="1" validate="label:BUILDING_TYPE"/>-->
				1 : 아파트, 2 : 오피스텔</td></tr>
				<tr><th>SEARCH_TYPE</th><td>
				<select class="selectpicker" name="SEARCH_TYPE" id="SEARCH_TYPE">
	 				<option value="1" selected >1.대지역,중지역조회</option>
	 				<option value="2">2.소지역,물건식별자 조회</option>
	 			</select>
				<!-- <input type="text" class="form-control width-120" name="SEARCH_TYPE" id="SEARCH_TYPE" value="2" validate="label:SEARCH_TYPE"/> -->
				1: 대지역,중지역조회	2: 소지역,물건식별자 조회</td></tr>
				<!-- <tr><th>시세는 REGION1_INFO</td></tr>
				<tr><th>REGION1_INFO</th><td><input type="text" class="form-control width-120" name="REGION1_INFO" id="REGION1_INFO" value="160000" validate="label:REGION1_INFO"/></td></tr>
				<tr><th>REGION2_INFO</th><td><input type="text" class="form-control width-120" name="REGION2_INFO" id="REGION2_INFO" value="160300" validate="label:REGION2_INFO"/></td></tr>
				 -->
				<tr><th>시세는 REGION1_CODE</td></tr>
<!-- 				<tr><th>REGION1_CODE</th><td><input type="text" class="form-control width-120" name="REGION1_CODE" id="REGION1_CODE" value="010000" validate="label:REGION1_CODE"/></td></tr> -->
<!-- 				<tr><th>REGION2_CODE</th><td><input type="text" class="form-control width-120" name="REGION2_CODE" id="REGION2_CODE" value="010700" validate="label:REGION2_CODE"/></td></tr> -->
<!-- 				<tr><th>REGION3_CODE</th><td><input type="text" class="form-control width-120" name="REGION3_CODE" id="REGION3_CODE" value="010701" validate="label:REGION3_CODE"/></td></tr> -->
<!-- 				<tr><th>APARTMENT</th><td><input type="text" class="form-control width-120" name="APARTMENT" id="APARTMENT" value="KBM021519" validate="label:APARTMENT     "/></td></tr> -->
<!-- 				<tr><th>PRICE_TYPE</th><td><input type="text" class="form-control width-120" name="PRICE_TYPE" id="PRICE_TYPE" value="" validate="label:PRICE_TYPE     "/></td></tr> -->
<tr>
					<th>
						<span class="required">REGION1_CODE</span>
					</th>
					<td>
<!-- 						<input type="text" class="form-control width-120" name="REGION1_CODE" id="REGION1_CODE" value="010000" validate="label:REGION1_CODE"/> -->
							<select class="selectpicker" name="REGION1_CODE" id="REGION1_CODE" onchange="ChangeSelectBox(document.frmScrap, this)">
								<option value="">시/도</option>
							</select>
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">REGION2_CODE</span>
					</th>
					<td>
<!-- 						<input type="text" class="form-control width-120" name="REGION2_CODE" id="REGION2_CODE" value="010700" validate="label:REGION2_CODE"/> -->
							<select class="selectpicker" name="REGION2_CODE" id="REGION2_CODE" onchange="ChangeSelectBox(document.frmScrap, this)">
								<option value="">시/군/구</option>
							</select>
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">REGION3_CODE</span>
					</th>
					<td>
<!-- 						<input type="text" class="form-control width-120" name="REGION3_CODE" id="REGION3_CODE" value="010701" validate="label:REGION3_CODE"/> -->
						<select class="selectpicker" name="REGION3_CODE" id="REGION3_CODE" onchange="ChangeSelectBox2(document.frmScrap, this)">
							<option value="">읍/면/동</option>
						</select>
					</td>
				</tr>

				<tr>
					<th>
						<span class="required">APARTMENT</span>
					</th>
					<td>
						<input type="hidden" class="form-control width-250" id="APARTMENT" name="APARTMENT" />
						<input type="text" class="form-control width-250" id="apartment_nm" name="apartment_nm"  placeholder="검색해주세요" validate="label:apartment_nm;maxbt:30;" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="required">PRICE_TYPE</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="PRICE_TYPE" id="PRICE_TYPE" value="" validate="; label:PRICE_TYPE     "/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn btn-default btn-xs" onclick="procScrap();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
</div>
