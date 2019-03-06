<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">수수료</a>
		</h3>
	</div>
	<div class="panel-collapse toggle-cont">
			<div class="panel-collapse">
				<input type="button" name="btnAddRow" class="btn btn-primary btn-xs" value="+행추가"	onclick="ftnRowAdd('ctable');">
				<input type="button" name="btnDelRow" class="btn btn-warning btn-xs" value="-행삭제"	onclick="ftnRowDel('ctable');">
				
				<table id="" class="table-striped table table-classic table-bordered tbl-info" cellspacing="0" width="100%">		
					<colgroup>
						<col width="5%">
						<col width="19%">
						<col width="19%">
						<col width="19%">
						<col width="19%">
						<col width="19%">
					</colgroup>
					<thead>
				         <tr>
				            <th rowspan="2"> idx</th>
				            <th colspan="2"> 한도 금액(만원)</th>
				            <th colspan="2"> 금리</th>               
				            <th rowspan="2"> 수수료율</th>
				        </tr>
				        <tr>
				        	<th>From</th>
				        	<th>To</th>
				        	<th>From</th>
				        	<th>To</th>
				        </tr>
				    </thead>
					<tbody id="ctable"> 
						
					<c:set var="grade" value="1" />
					<c:set var="tmp" value="0" />
					<c:set var="gradeMue" value="1" />
					<c:set var="doneLoop" value="false" />
				    <!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
					<c:if test="${empty rtoCommissionList}">
					<tr>
						<td><p>1</p><input type="hidden" name="grade_1" value="1"/></td>	
						<td><input type="text" name="amt_limit_min_1" class="form-control width-95" maxlength="5" onkeypress="keyNumeric(event);"/></td>
						<td><input type="text" name="amt_limit_max_1" class="form-control width-95" maxlength="5" onkeypress="keyNumeric(event);"/></td>
						<td><input type="text" name="rto_interest_min_1" class="form-control width-95" maxlength="5" onkeypress="keyNumeric(event);"/></td>
						<td><input type="text" name="rto_interest_max_1" class="form-control width-95" maxlength="5" onkeypress="keyNumeric(event);"/></td>
						<td><input type="text" name="rto_commission_1" class="form-control width-95" maxlength="5" onkeypress="keyNumeric(event);"/>%</td>
					</tr>
					</c:if>
					<c:forEach var="rtoCommissionList" items="${rtoCommissionList}">
						<tr>
							<td><p>${rtoCommissionList.grade}</p><input type="hidden" name="grade_${grade}" value="${rtoCommissionList.grade}"/></td>	
							<td><input type="text" name="amt_limit_min_${grade}" id="amt_limit_min_${grade}" value="${rtoCommissionList.amt_limit_min}" class="form-control width-95" maxlength="6"/></td>
							<td><input type="text" name="amt_limit_max_${grade}" id="amt_limit_max_${grade}" value="${rtoCommissionList.amt_limit_max}" class="form-control width-95" maxlength="6"/></td>
							<td><input type="text" name="rto_interest_min_${grade}" id="rto_interest_min_${grade}" value="${rtoCommissionList.rto_interest_min}" class="form-control width-95" maxlength="6"/></td>
							<td><input type="text" name="rto_interest_max_${grade}" id="rto_interest_max_${grade}" value="${rtoCommissionList.rto_interest_max}" class="form-control width-95" maxlength="6"/></td>
							<td><input type="text" name="rto_commission_${grade}" id="rto_commission_${grade}" value="${rtoCommissionList.rto_commission}" class="form-control width-95" maxlength="6"/>%</td>
						</tr>	
						<c:set var="grade" value="${grade + 1}" />	
					</c:forEach>
					</tbody>
				</table>			
			</div>
	</div>
</div>
<script>
		var st_r_cnt = 1; // row 미니멈 최소 1개부터 시작
		var st_c_cnt = <c:out value="${gradeMue}"/>; //현재 col 갯수 
		var max_r_cnt = 10; //row max값
		var max_c_cnt = 10; //col max값
		
		function ftnRowAdd(tname) {
		if (max_r_cnt <= st_r_cnt) {
			alert("최대 행 갯수 초과입니다. 최대 10행 입니다.");
			return false;
		} else {
			var objTbl = document.getElementById(tname);
			var objRow = objTbl.insertRow(objTbl.rows.length); //rows add
			var objCell;
			var cell_no = 0;
			var mue_index = 1;
			st_r_cnt = $("#ctable tr").length; // 현재 row 갯수
			for (var i = 1; i <= objTbl.rows[0].cells.length; i++) {
				objCell = objRow.insertCell(cell_no); //cells add
				
				if (i == 1) {
					objCell.innerHTML += "<input type='hidden' name='grade_"+st_r_cnt+"' value="+st_r_cnt+" size='7' class='form-control width-95'>";
					objCell.innerHTML += "<p>"+st_r_cnt+"</p>";
				}else if (i == 2){
					objCell.innerHTML += "<input type='text' name='amt_limit_min_"+st_r_cnt+"' size='7' class='form-control width-95' maxlength='6'>";
				}else if (i == 3){
					objCell.innerHTML += "<input type='text' name='amt_limit_max_"+st_r_cnt+"' size='7' class='form-control width-95' maxlength='6'>";
				}else if (i == 4){
					objCell.innerHTML += "<input type='text' name='rto_interest_min_"+st_r_cnt+"' size='7' class='form-control width-95' maxlength='6'>";
				}else if (i == 5){
					objCell.innerHTML += "<input type='text' name='rto_interest_max_"+st_r_cnt+"' size='7' class='form-control width-95' maxlength='6'>";
				}else if (i == 6){
					objCell.innerHTML += "<input type='text' name='rto_commission_"+st_r_cnt+"' size='7' class='form-control width-95' maxlength='6'>%";
				}else{
					mue_index++;
				}
				cell_no++;
			}
		}
		}
		function ftnRowDel(tname) {
			var r_cnt = $("#ctable tr").length;
			var objTbl = document.getElementById(tname);
			if (r_cnt < 2) {
				alert("1개 이상의 행은 존재해야합니다.");
			} else {
				objTbl.deleteRow(r_cnt - 1);
				st_r_cnt--;
			}
		}

	function procRtoCommission() {
	var obj = new Object();
	var arr = new Array();
	for (var i = 1; i <= st_r_cnt; i++) {
		for (var j = 1; j <= st_c_cnt; j++) {
		
			obj.cd_fc = $("[name='cd_fc']").val();
			obj.cd_goods = $("[name='cd_goods']").val();
		
			obj.grade = $("[name='grade_" + i + "']").val();
			obj.amt_limit_min = $("[name='amt_limit_min_" + i + "']").val();
			obj.amt_limit_max = $("[name='amt_limit_max_" + i + "']").val();
			obj.rto_interest_min = $("[name='rto_interest_min_" + i + "']").val();
			obj.rto_interest_max = $("[name='rto_interest_max_" + i + "']").val();
			obj.rto_commission = $("[name='rto_commission_" + i + "']").val();
			arr.push(obj);
			obj = new Object();
		}
	}
	var jsonData = JSON.stringify(arr);
	$.ajax({
		url : "<c:url value='/goods/procRtoCommissionVO.json'/>",
		data : jsonData,
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		success : function(result) {
			//alert('정상적으로 저장되었습니다.');
		},
		error : function(e) {
		
			alert(e.responseText);
		},
		clearForm: true,
    resetForm: true
	});
}
</script>