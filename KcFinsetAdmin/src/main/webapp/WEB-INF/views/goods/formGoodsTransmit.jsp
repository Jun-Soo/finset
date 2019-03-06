<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	window.setupValidateForm( frmGoodsTransmit );
	
    var proc = { 
         success : function (result) {
        	if(result == "00"){
 				initForm();
 				loadGoodsList();
 			}else if(result == "01"){
 				alert("수정에 실패하였습니다.")
 			}
 		},
 		error:function(request,status,error){
 			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 		},
         url: "<c:url value='/goods/modifyGoodsTransmit.json'/>",
         type: "POST",
         clearForm: true,
         resetForm: true
     }; 
     
     var create = { 
         success : function (result) {
        	if(result == "00"){
 				initForm();
 				loadGoodsList();
 			}else if(result == "01"){
 				alert("등록에 실패하였습니다.")
 			}
 		},
 		error:function(request,status,error){
 			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 		},
         url: "<c:url value='/goods/createGoods.json'/>",
         type: "POST",
         dataType: "json",
         clearForm: true,
         resetForm: true
     }; 
  
     $("#procGoodsTransmit").click(function(){
    	 if ( !frmGoodsTransmit.validateForm() ) return false;
    	 
         $("#frmGoodsTransmit").ajaxSubmit(proc); 
         return false; 
     }); 
     
     $("#createGoodsTransmit").click(function(){
    	 if ( !frmGoodsTransmit.validateForm() ) return false;
    	 
         $("#frmGoodsTransmit").ajaxSubmit(create); 
         return false; 
     }); 
});

//금융사 연동정보 셋팅
function getTransmitComp() {
	
	var transmitData = frmGoodsTransmit.cd_transmit_comp.value;
	if(transmitData == "") return false;
	
	 if(transmitData != ""){
		var dataArr = transmitData.split('||');
		frmGoodsTransmit.nm_request_form.value = dataArr[0];
		frmGoodsTransmit.url_request.value = dataArr[1];
		frmGoodsTransmit.id_request.value = dataArr[2];
	 }
}

</script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품 연동정보</a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty goodsInfo.cd_goods}">
					<button type="button" class="btn btn-default btn-xs" id="createGoodsTransmit">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-default btn-xs" id="procGoodsTransmit">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="btn btn-warning btn-xs" onclick="clearCacheGoods();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 적용</button>
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmGoodsTransmit" id="frmGoodsTransmit">
			<div class="panel-collapse">
				<table class="table table-classic">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<c:choose>
							<c:when test="${empty goodsInfo.cd_goods}">
								<tr>
									<th>상품코드</th>
									<td colspan="3">
										<input type="text" class="form-control" name="cd_goods" value="${goodsInfo.cd_goods}" validate="required; label:상품코드;"/>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="cd_goods" value="${goodsInfo.cd_goods}"/>
							</c:otherwise>
						</c:choose>
						<tr>
							<th>상품명</th>
							<td colspan="3">
								<input type="text" class="form-control" name="nm_goods" value="${goodsInfo.nm_goods}" validate="required; label:상품명;"/>
							</td>
						</tr>
						<tr>
							<th>상품구분</th>
							<td colspan="3">
								<select name="cd_goods_type" class="selectpicker" validate="required; label:상품구분;">
								${ufn:makeOptions("cd_goods_type","상품구분", goodsInfo.cd_goods_type)}
								</select>
							</td>
						</tr>
						<tr>
							<th>금융사</th>
							<td>
								<select name="cd_fc" class="selectpicker" validate="required; label:금융사;">
								${ufn:makeOptions("cd_fc","연동금융사", goodsInfo.cd_fc)}
								</select>
								<c:set var="transmit_comp" value="${goodsInfo.nm_request_form}||${goodsInfo.url_request}||${goodsInfo.id_request}" />
								<select name="cd_transmit_comp" class="selectpicker" onchange="getTransmitComp();" validate="label:연동정보;">
									${ufn:makeOptionsEtc("cd_transmit_comp","연동정보", transmit_comp, "")}
								</select>
							</td>
							<th>연동폼</th>
							<td>
								<input type="text" class="form-control" name="nm_request_form" value="${goodsInfo.nm_request_form}"/>
							</td>
						</tr>
						<tr>
							<th>접수URL</th>
							<td colspan="3">
								<input type="text" class="form-control" name="url_request" value="${goodsInfo.url_request}"/>
							</td>
						</tr>
						<tr>
							<th>접수ID</th>
							<td>
								<input type="text" class="form-control" name="id_request" value="${goodsInfo.id_request}"/>
							</td>
							<th>연동코드</th>
							<td>
								<input type="text" class="form-control" name="c20_goods_comp" value="${goodsInfo.c20_goods_comp}"/>
							</td>
						</tr>
						<tr>
							<th>자동접수 여부</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("yn_yes", "yn_auto", "radio", goodsInfo.yn_auto, 0)}
							</td>
							<th>사용여부</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("yn_yes", "yn_use", "radio", goodsInfo.yn_use, 0)}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</div>