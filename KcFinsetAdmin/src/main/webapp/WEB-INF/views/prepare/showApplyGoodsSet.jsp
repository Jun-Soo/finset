<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
});

function viewContact(code) {
	cd_type = document.getElementById("cd_collect_path_arr_"+code).value;
	if("01" == cd_type) {
		$("#cd_contact_path_01_"+code).show();
		$("#cd_contact_path_02_"+code).hide();
	} else if("02" == cd_type) {
		$("#cd_contact_path_02_"+code).show();
		$("#cd_contact_path_01_"+code).hide();
	} else {
		$("#cd_contact_path_01_"+code).hide();
		$("#cd_contact_path_02_"+code).hide();
	}
}
</script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품별 입력사항</a>
		</h3>
	</div>
	<div class="panel-collapse toggle-cont">
		<table class="table table-classic">
			<colgroup>
				<col width="">
				<col width="">
			</colgroup>
			<tbody id="goods_tbody">
				<!-- 접수경로에 따라 rowspan 계산 -->
				<c:choose>
					<c:when test="${ufn:getCodeName('_CONF_SYSTEM', 'YN_ADD_APPLYPATH') eq 'Y'}">
						<c:set var="cntRow" value="2"/>
					</c:when>
					<c:otherwise>
						<c:set var="cntRow" value="0"/>
						<c:set var="cntPath" value="1"/>
					</c:otherwise>
				</c:choose>
				<c:forEach items="${cd_goods_arr}" var="cd_goods">
					<tr class="tr_${cd_goods}">
						<th class="align-c" rowspan="${cntRow+cntPath}">${ufn:getGoodsDetail(cd_goods, '')}</th>
						<th>파일</th>
						<td colspan="3"><div class="div_file"><input type="file" name="file" /><input type="hidden" name="flg_file_arr" /></div></td>
						<td colspan="3"><div class="div_file"><input type="file" name="file2" /><input type="hidden" name="flg_file2_arr" /></div></td>
					</tr>
					<c:if test="${ufn:getCodeName('_CONF_SYSTEM', 'YN_ADD_APPLYPATH') eq 'Y'}">		
						<tr class="tr_${cd_goods}">
							<th rowspan="${1+cntPath}">중개경로</th><th>접수순위</th><th>접수일자</th><th>상호</th><th>등록번호</th><th>협회등록번호</th><th>전화번호</th>
						</tr>
						<c:forEach var="List" items="${ufn:getApplyPath(pathInfo)}" varStatus="status">
							<tr class="tr_${cd_goods}">
								<c:set var="path_data" value="${fn:split(List,'||')}"/>
								<td>${path_data[0]}</td>
								<td>${ufn:formatDate(path_data[1])}</td>
								<td>${path_data[2]}</td>
								<td>${path_data[3]}</td>
								<td>${path_data[4]}</td>
								<td>${path_data[5]}</td>
							</tr>							
						</c:forEach>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>