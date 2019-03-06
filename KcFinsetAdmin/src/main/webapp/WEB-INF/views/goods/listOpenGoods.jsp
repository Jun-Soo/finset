<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	// SelectPicker
	$('.selectpicker').selectpicker();
});

function procAgencyGoods(yn_open){
	if ( countCheckBox(frmList.id_agency_list) == 0 ) {
		alert("선택된 대상이 없습니다.");
		return false;
	}
	
	frmList.id_agency.value = getRadioOption(frmList.id_agency_list);
	frmList.yn_open.value = yn_open;
	
	var data = frmList.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/goods/procAgencyOpen.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(returnData.cd_result == '00'){
				loadGoodsList();
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
	});  
}

</script>

<!-- Search -->
<div class="srch">
	<div class="form-group" id="sandbox-container">
		<!-- 상품 -->
		<select name="cd_goods" class="selectpicker">
			${ufn:makeGoodsOptions("상품명", goodsForm.cd_goods, "ID.NM")}
		</select>
		<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
	</div>
</div>
<!--// Search -->

<div class="pull-right">
	<button type="button" class="btn btn-primary btn-xs" onclick="procAgencyGoods('Y');"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 일괄적용</button>
	<button type="button" class="btn btn-default btn-xs" onclick="procAgencyGoods('N');"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 일괄해제</button>
</div>
<!-- List -->
<table id="tbl_listGoods" class="table table-bordered tbl-info" cellspacing="0" width="100%">
	<input type="hidden" name="id_agency" />
	<colgroup>
		<col witdh="25%" />
		<col witdh="25%" />
		<col witdh="25%" />
		<col witdh="25%" />
	</colgroup>
	<thead>
		<tr>
			<th> <input type="checkbox" id="checkAll" onclick="checkBoxAllCheck(this,'id_agency_list');"/><label for="checkAll" class="checkbox-inline"></label></th>
            <th> 상품명</th>               
            <th> 매체사명</th>
            <th> 노출여부</th>
		</tr>
    </thead>
       
	<tbody>
    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
    	<c:choose>
			<c:when test="${goodsForm.cd_goods == ''}">
				<tr>
					<td colspan="4" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				${ufn:showOpenGoodsAgency(goodsForm.tab_type, goodsForm.cd_goods)}
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<!-- //List -->
