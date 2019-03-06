<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	$('.selectpicker').selectpicker();
});

</script>

<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="largeModalLabel">상품 상세정보</h4>
</div>
<div class="modal-body">
	
	<div class="panel panel-default toggle-panel">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">
				<a href="#none">${goodsInfo.nm_goods}</a>
			</h3>
		</div>
		<div class="panel-collapse toggle-cont">
			<div class="panel-collapse">
				<table class="table table-classic">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>상품구분</th>
							<td colspan="3">
								${ufn:getCodeName('cd_goods_type', goodsInfo.cd_goods_type)}
							</td>
						</tr>
						<tr>
							<th>요약설명</th>
							<td colspan="3">
								${goodsInfo.summary}
							</td>
						</tr>
						<%--<tr>--%>
							<%--<th>첨부파일1</th>--%>
							<%--<td>--%>
								<%--<a href="<c:url value='/attach/getFile.crz'/>?file_name=${goodsInfo.path_file1}">${goodsInfo.nm_file1}</a>--%>
							<%--</td>--%>
							<%--<th>첨부파일2</th>--%>
							<%--<td>--%>
								<%--<a href="<c:url value='/attach/getFile.crz'/>?file_name=${goodsInfo.path_file2}">${goodsInfo.nm_file2}</a>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<tr>
							<th>상세설명</th>
							<td colspan="3">
								<div class="editor-content">
									${goodsInfo.content}
								</div>
							</td>
						</tr>
						<tr>
							<th>연동금융사</th>
							<td>
								${ufn:getCodeName('cd_fc', goodsInfo.cd_fc)}
							</td>
							<th>연동코드</th>
							<td>
								${goodsInfo.c20_goods_comp}
							</td>
						</tr>
						<tr>
							<th>선순위 여부</th>
							<td>
								${ufn:getCodeName('yn_yes', goodsInfo.yn_first)}
							</td>
							<th>선인증 여부</th>
							<td>
								${ufn:getCodeName('yn_yes', goodsInfo.yn_check)}
							</td>
						</tr>
						<tr>
							<th>사용 여부</th>
							<td colspan="3">
								${ufn:getCodeName('yn_yes', goodsInfo.yn_use)}
							</td>
						</tr>
						<tr>
							<th>등록자</th>
							<td>
								${ufn:getWorkerInfo(goodsInfo.id_frt,'NM')}
							</td>
							<th>등록일</th>
							<td>${goodsInfo.dt_frt}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal-footer">
</div>