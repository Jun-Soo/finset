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
	                data: { APARTMENT : request.term, REGION1_CODE : frmKbmarketprice.REGION1_CODE.value,REGION2_CODE : frmKbmarketprice.REGION2_CODE.value,REGION3_CODE : frmKbmarketprice.REGION3_CODE.value },
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
	    }
	});
	
	ChangeSelect(document.frmKbmarketprice);
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
		data = {"BUILDING_TYPE":objVal};
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
	
	var target;

		target= frm.REGION1_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
		data = {"BUILDING_TYPE":1};
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
			<button type="submit" class="btn btn-default btn-xs" onclick="procKbMarketPrice();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장2</button>
		</div>
		<form name="frmKbmarketprice" id="frmKbmarketprice">
		<input type="hidden" name="yn_modal" value="${KbmarketpriceForm.yn_modal}"/>
		<div class="panel panel-primary">
			<div class="panel-heading">기본정보</div>
			<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>
						<span class="required">API_ID</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="API_ID" id="API_ID" value="9921" validate="label:API_ID"/>9921시세
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">ORGCD</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="ORGCD" id="ORGCD" value="007" validate="label:ORGCD"/>
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">BUILDING_TYPE</span>
					</th>
					<td>
						${ufn:makeRadioAndCheckBoxs("cd_building_type", "cd_building_type", "radio", kbMarketPriceInfo.BUILDING_TYPE, 0)}
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">REGION1_CODE</span>
					</th>
					<td>
							<select class="selectpicker" name="REGION1_CODE" id="REGION1_CODE" onchange="ChangeSelectBox(document.frmKbmarketprice, this)">
								<option value="">시/도</option>
							</select>
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">REGION2_CODE</span>
					</th>
					<td>
							<select class="selectpicker" name="REGION2_CODE" id="REGION2_CODE" onchange="ChangeSelectBox(document.frmKbmarketprice, this)">
								<option value="">시/군/구</option>
							</select>
					</td>
				</tr>
				
				<tr>
					<th>
						<span class="required">REGION3_CODE</span>
					</th>
					<td>
						<select class="selectpicker" name="REGION3_CODE" id="REGION3_CODE" onchange="ChangeSelectBox2(document.frmKbmarketprice, this)">
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
		<button type="submit" class="btn btn-default btn-xs" onclick="procKbMarketPrice();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
</div>
