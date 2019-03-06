<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>접수 신청서</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function () {
		
		opener.loadPrepareTab('HIST');
		
		<c:if test="${yn_close eq 'Y'}">
			//사전접수 폼 로드
			opener.loadPrepareForm();
			self.close();
		</c:if>
		
		$('.selectpicker').selectpicker();
		
		// toggle panel
		$(".toggle-panel").each(function(i){
			$(this).find(".h-sec > a").click(function(){
				$(this).toggleClass("closed");
				$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
			});
		});
		
		//loadApplyTab('01');
	});
	
	function moveTab(idx) {
		var tabItem = $(".tab li").eq(idx);
			tabItem.siblings().removeClass();
			tabItem.addClass("active");
			
		var tabConts = tabItem.parent("ul").parent("div").next("div").children(".tab_cont");
		var className = tabConts.attr("class");
		
		if(className === "tab_cont"){
			var tabContIdx = tabConts.children("div").eq(idx);
			tabConts.children("div").siblings().css("display", "none");
			tabContIdx.css("display", "block");
		}
		return false;
	};
	
	function appendFile(obj) {
		var cd_goods = obj.children[0].value;
		if($("#tr_"+cd_goods).length > 0) {
			$("#tr_"+cd_goods).remove();
		} else {
			var $newItem = $("<tr id='tr_"+cd_goods+"'><th class='align-c'>${ufn:getGoodsDetail('"+cd_goods+"', 'NM')} ${ufn:getCodeName('cd_goods','"+cd_goods+"')}</th><td><input type='file' name='file' validate='required;label:첨부파일;' /></td></tr>");
	        $("#target_tbody").append($newItem);
		}
	}
	
	function createApply() {
		for(var i=0; i<frmApply.file.length; i++) {
			frmApply.flg_file_arr[i].value = IsNull(frmApply.file[i]);
		}
		
		for(var i=0; i<frmApply.file2.length; i++) {
			frmApply.flg_file2_arr[i].value = IsNull(frmApply.file2[i]);
		}
		
		if ( !frmApply.validateForm() ) return false;
		
		frmApply.action = "<c:url value='/apply/createApply.json'/>";
		frmApply.method = "POST";
		frmApply.submit();
		
	}
	
	
	function loadApplyTab(type) {
		frmApply.cd_goods_type.value = type;
		frmApply.cd_fc.value = type;
		
		var data = frmApply.serialize();
		vLoad("formGoodsCompTab","<c:url value='/prepare/showApplySet.json'/>",data,false);
	}
	
	
	function loadInfoSet(obj) {
		$("#setGoods").show();
		var data = frmApply.serialize();
		vLoad("setGoods","<c:url value='/prepare/showApplyGoodsSet.json'/>",data,false);
	}
	
	function goGoodsModal(cd_goods){
		var data = {"cd_goods":cd_goods};
		vLoad("modal-content-lg","<c:url value='/goods/viewGoodsDetails.crz'/>",data);
		
		setTimeout(function() {
			$("#modal-lg").modal('show');
		}, 1000);
	}

</script>
</head>
<body>

<div class="content">
	<form name="frmApply" id="frmApply" enctype="multipart/form-data">
	<input type="hidden" name="no_prepare" value="${applyForm.no_prepare}"/>
	<input type="hidden" name="cd_goods_type"/>
	<input type="hidden" name="cd_fc"/>
	<input type="hidden" name="path_common_file" value="${attach.path_save_file}"/>
	<input type="hidden" name="url_common_file" value="${attach.url_attach}"/>
	<input type="hidden" name="nm_common_file" value="${attach.nm_save_file}"/>
	<input type="hidden" name="dis_goods"/>
	<div class="ui-layout-content">
		<div role="tabpanel">
			<!-- CD_GOODS_TAB 01:상품구분기준 , 02:금융사기준 -->
			<ul class="nav nav-tabs tab-sm" role="tablist">
				<c:if test="${ufn:getCodeName('_CONF_SYSTEM', 'CD_GOODS_TAB') eq '01'}">
					<c:forEach var="List" items="${ufn:getCodeList('cd_goods_type')}" >
						<li role="presentation" ${ List.seq_order == '1' ? 'class="active"':''} onclick="loadApplyTab('${List.code_value}','${ufn:getCodeName('_CONF_SYSTEM', 'CD_GOODS_TAB')}');"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab">${List.nm_code}</a></li>
					</c:forEach>
				</c:if>
				<c:if test="${ufn:getCodeName('_CONF_SYSTEM', 'CD_GOODS_TAB') eq '02'}">
						<li role="presentation" class="active" onclick="loadApplyTab('${List.code_value}');"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab">전체</a></li>
					<c:forEach var="List" items="${ufn:getCodeList('cd_fc')}" >
						<li role="presentation" onclick="loadApplyTab('${List.code_value}');"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab">${List.nm_code}</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		
		
		<div id="formGoodsCompTab">
			<%@ include file="/WEB-INF/views/prepare/showApplySet.jsp"%>
		</div>
		
		
		<div id="setCommon">
			<div class="panel panel-default toggle-panel">
				<div class="panel-heading">
					<h3 class="h-sec pull-left">
						<a href="#none">공통 입력사항</a>
					</h3>
				</div>
				<div class="panel-collapse toggle-cont">
					<table class="table table-classic">
						<colgroup>
							<col width="15%">
							<col width="25%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>파일</th>
								<td>
									<input type="file" name="file_common" validate="label:첨부파일;" />
								</td>
								<td>
									<c:if test="${!empty attach.path_save_file}">
										<input type="checkbox" name="yn_commonFile" id="yn_commonFile" value="Y"/><label for="yn_commonFile" class="checkbox-inline">등록된 공통파일과 동일</label>
										(<a href="<c:url value='/attach/getFile.crz'/>?file_name=${attach.path_save_file}">${attach.nm_save_file}</a>)
									</c:if>
									<c:if test="${empty attach.path_save_file && !empty attach.url_attach}">
										<input type="checkbox" name="yn_commonFile" id="yn_commonFile" value="Y"/><label for="yn_commonFile" class="checkbox-inline">등록된 공통파일과 동일</label>
										(<a href="${attach.url_attach}">${attach.nm_save_file}</a>)
									</c:if>
								</td>
							</tr>
							<tr>
								<th>금융사메모</th>
								<td colspan="2">
									<textarea class="form-control w100 h50" name="memo_to_apply" validate="label:접수시메모; maxbt:700;" maxlength="700"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
		
		<div id="setGoods" style="display:none;">
			<%@ include file="/WEB-INF/views/prepare/showApplyGoodsSet.jsp"%>
			
		</div>
		 
		 <div class="align-r">
			<button type="button" class="btn btn-primary btn-xs" onclick="createApply();">
				<span class="glyphicon glyphicon-saved" aria-hidden="true"></span> 작성완료
			</button>
		</div>
	</div>
	<input type="file" name="file" style="visibility:hidden;"/><input type="hidden" name="flg_file_arr" />
	<input type="file" name="file2" style="visibility:hidden;"/><input type="hidden" name="flg_file2_arr" />
	</form>
</div>


<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>