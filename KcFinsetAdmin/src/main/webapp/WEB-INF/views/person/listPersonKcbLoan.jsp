<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('[data-toggle="popover"]').popover();
});

function goApplyForm() {
	var no_prepare = frmPrepare.no_prepare.value;
	
	// no_prepare가 null이면 접수팝업 호출 X
	if(no_prepare != null && no_prepare != "") {
		var openWindow = window.open("<c:url value='/apply/formApplyMain.crz'/>?no_prepare="+no_prepare, "applyForm"+no_prepare, "width=980, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
}

function goApplyLegal(no_apply) {
	
	var data = {"no_apply":no_apply};
	vLoad("modal-content-lg","<c:url value='/legal/getLegalInfo.crz'/>",data);
	
}

function sendApply(no_apply) {
	// 요청 팝업 (2015.07.26 팝업에서 iframe 으로 타겟 변경)
	//window.open("<c:url value='/apply/requestApply.crz'/>?no_apply="+no_apply, "send"+no_apply, "width=150, height=150, scrollbars=no, resizable=no", "");
	
	// 암전
	showDark();
	
	// 접수기표 submit
	frmSend.target = "_proc";
	frmSend.no_apply.value = no_apply;
	frmSend.submit();
	
	// 2.5초 후 이력 로딩
	setTimeout(function(){
		loadPrepareTab('HIST');
		stopDark();
	}, 2500);
	
}

//상세정보 팝업
function goApplyDetailPopup(no_apply){
	var openWindow = window.open("<c:url value='/apply/popApplyDetail.crz'/>?no_apply="+no_apply, "open"+no_apply, "width=800, height="+(screen.availHeight-300)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

</script>
<form name="frmSend" action="<c:url value='/apply/sendApply.json'/>" method="POST">
<input type="1hidden" name="no_apply" value="">
</form>
<div class="panel panel-primary">
	<div class="panel-heading">접수이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<colgroup>
				<col width="70px">
				<col width="15%">
				<col width="*">
				<col width="17%">
				<col width="70px">
				<col width="45px">
				<col width="35px">
				<col width="35px">
			</colgroup>
			<thead>
				<tr>
					<th>신청일</th>
					<th>금융사</th>
					<th>상품명</th>
					<th>승인금/신청금</th>
					<th>처리일</th>
					<th>상태</th>
					<th>메모</th>
					<th>상세</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listApply}">
				<tr>
					<td colspan="7">접수목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="List" items="${listApply}" varStatus="status">
				<c:set var="tr_class" value=""/>
				<c:if test="${no_apply eq List.no_apply}">
					<c:set var="tr_class" value="warning"/>
				</c:if>
			
				<c:choose>
					<c:when test="${ufn:isApprAuth('102001',sessionScope.STR_APPROVAL)}">
						<tr class="${tr_class}" onclick="loadApplyMemo(this,'${List.no_apply}');loadApplyForm(this,'${List.no_apply}');" style="cursor: pointer;">
					</c:when>
					<c:otherwise>
						<tr class="${tr_class}" onclick="loadApplyMemo(this,'${List.no_apply}');" style="cursor: pointer;">
					</c:otherwise>
				</c:choose>
					<td>${ufn:formatShortDate(List.ymd_apply)}</td>
					<td>
						<c:choose>
							<c:when test="${fn:length(ufn:getGoodsDetail(List.cd_goods, 'NM_COMP')) > 4}">
								<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${ufn:getGoodsDetail(List.cd_goods, 'NM_COMP')}">
									${fn:substring(ufn:getGoodsDetail(List.cd_goods, 'NM_COMP'),0,4)}
								</span>
							</c:when>
							<c:otherwise>
								${ufn:getGoodsDetail(List.cd_goods, 'NM_COMP')}
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${fn:length(ufn:getGoodsDetail(List.cd_goods, '')) > 8}">
								<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${ufn:getGoodsDetail(List.cd_goods, '')}">
									${fn:substring(ufn:getGoodsDetail(List.cd_goods, ''),0,8)}
								</span>
							</c:when>
							<c:otherwise>
								${ufn:getGoodsDetail(List.cd_goods, '')}
							</c:otherwise>
						</c:choose>
					</td>
					<td>${ufn:formatNumber(List.amt_approval)} / ${ufn:formatNumber(List.amt_apply)}</td>
					<td>
						<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${List.his_lst}">
							${ufn:formatShortDate(List.ymd_lst)}
						</span>
					</td>
					
					<c:choose>
						<c:when test="${List.cd_apply_doc_box eq '01'}">
							<td>
								<button type="button" class="btn btn-primary btn-xs" onclick="sendApply('${List.no_apply}')">
									<span class="glyphicon glyphicon-send" aria-hidden="true"></span> 접수
								</button>
							</td>
						</c:when>
						<c:otherwise>
							<td>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</td>
						</c:otherwise>
					</c:choose>
					
					<!-- 메모 -->
					<td>
	              		<span class="glyphicon glyphicon-comment" id="popover${status.count}"></span>
	              		<!-- popover 구현 -->
	              		<script type="text/javascript">
	              		$("#popover"+"${status.count}").popover({
	            			trigger:"hover",
	            			html : true,
	            			placement : "left",
	            			title:"금융사메모",
	            	        content: function() {
	            				return $("#pop_content"+"${status.count}").html();
	            			}
	                    });
	              		</script>
						<div id="pop_content${status.count}" class="popover" role="tooltip"><div>${List.memo_from_apply}</div></div>
						
					</td>
					<!-- 상세 -->
					<td>
		             	<a href="#" class="btn btn-xs circle" onclick="goApplyDetailPopup('${List.no_apply}'); return false;">
			              	<span class="glyphicon glyphicon-list-alt"></span>
		              	</a>
	              	</td>  
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="align-c mg-bt10">
	<button type="button" class="btn btn-default btn-xs" onclick="goApplyForm();">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 접수 추가
	</button> --%>
</div>