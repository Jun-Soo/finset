<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm( frmIpinfo );
	});
	
	// ip정보 전체를 가져옴
	function listIpInfo() {
		
 		var data = {};
		vLoad("listIpInfo","<c:url value='/ip/listIpInfo.crz'/>", data);
		
		window.setupValidateForm( frmIpinfo );
	}
	
	// 폼 새로고침
	function resetIpForm() {
		var data = {};
		vLoad("formIpInfo","<c:url value='/ip/initIpInfoForm.crz'/>", data);
		
		window.setupValidateForm( frmIpinfo );
	}
	
	// 하나의 ip정보 추가, 수정
	function procIpInfo() {
		
		var frm = document.frmIpinfo;
	    if(!frm.validateForm()) return false;
		
		var data = frm.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/ip/procIpInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
						
				if(parseInt(returnData.cd_result,10) == 0) {
					listIpInfo(); 
				} 
				
				resetIpForm();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	// 하나의 ip정보 삭제
	function delIpInfo() {
		
		var frm = document.frmIpinfo;
	    if(!frm.validateForm()) return false;

	    var data = frm.ajaxSubmit();
		if(data == null) return false; 

		$.ajax({
			url : "<c:url value='/ip/delIpInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					listIpInfo();
				} 
				
				resetIpForm();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});		
	}

	// ip캐시를 초기화함
	function clearCacheIp() {
		
		$.ajax({
			url : "<c:url value='/ip/clearCacheIp.json'/>",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		
	}
</script>
<!-- Layout-Content -->
<div class="panel panel-default panel-sub">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">IP 정보</h3>
		<span class="pull-right">
			<c:choose>
				<c:when test="${empty ipInfo}">
					<button type="button" class="btn btn-primary btn-xs" onclick="procIpInfo();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 등록</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-primary btn-xs" onclick="procIpInfo();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 변경</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="delIpInfo();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 삭제</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="btn btn-default btn-xs" onclick="resetIpForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			<button type="button" class="btn btn-warning btn-xs" onclick="clearCacheIp();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 적용</button>
		</span>
	</div>
	<div class="panel-collapse">
		<form name="frmIpinfo" id="frmIpinfo">
		<table class="table table-classic">
			<colgroup>
				<col width="8%"/>
				<col width="10%"/>
				<col width="8%"/>
				<col width="25%"/>
				<col width="8%"/>
				<col width="41%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><span class="required">시스템구분</span></th>
					<td>
						<c:choose>
							<c:when test="${empty ipInfo}">
								<select class="selectpicker" name="cd_system" id="cd_system" validate="required;label:시스템">
									${ufn:makeOptions('cd_system','시스템구분', '60')} 
								</select>		
							</c:when>
							<c:otherwise>
								<select class="selectpicker" name="cd_system" id="cd_system" >
									<option value="${ipInfo.cd_system}">${ufn:getCodeName('cd_system', ipInfo.cd_system)}</option>
								</select>
							</c:otherwise>
						</c:choose>
					</td>
					<th><span class="required">IP 범위</span></th>
					<td>
						<c:choose>
							<c:when test="${empty ipInfo}">
								<input type="text" class="form-control" name="ip" id="ip" validate="required;label:IP주소;maxbyte:30"/>
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control" name="ip" id="ip" value="${ipInfo.ip}" readonly="readonly"/>
							</c:otherwise>
						</c:choose>
					</td>
					<th><span class="required">메모</span></th>
					<td>
						<input type="text" class="form-control" name="memo" id="memo" value="${ipInfo.memo}" validate="required;label:메모;maxbyte:30"/>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>