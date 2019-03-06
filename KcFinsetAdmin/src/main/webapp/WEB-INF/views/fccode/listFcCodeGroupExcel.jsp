<%@ page import="com.koscom.fccode.model.FcCodeForm" %>
<%@ page language="java" contentType="application/vnd.ms-excel; name='code_list.xls';charset=euc-kr" pageEncoding="UTF-8"%>
<%
    FcCodeForm fcCodeForm = (FcCodeForm)request.getAttribute("fcCodeForm");
    String cd_fc      = fcCodeForm.getCd_fc();
    String no_edoc    = fcCodeForm.getNo_edoc();
    String code_group = fcCodeForm.getCode_group();
    String code_value = fcCodeForm.getCode_value();
    String type_txrx  = fcCodeForm.getType_txrx();
    String fileName   = cd_fc+"_"+no_edoc+"_"+type_txrx;

    response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls;charset=euc-kr");
	response.setHeader("Content-Description", "JSP Generated Data");
%>

<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script src="<c:url value="/script/jquery/jquery.tablednd.js"/>"></script>
<table id="tbl_listFcCodeGroup" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
	<tr>
		<th>순번</th>
		<th>금융사</th>
		<th>금융사코드</th>
		<th>전문번호</th>
		<th>송수신구분</th>
		<th>항목그룹ID</th>
		<th>항목그룹명</th>
		<th>핀셋표준항목</th>
		<th>속성</th>
		<th>길이</th>
		<th>사용</th>
		<th>부모코드</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<tr style="cursor: pointer;">
			<td>${List.seq_order}<%-- ${status.count} --%></td>
			<td>${ufn:getNmFc(List.cd_fc)}</td>
			<td>${List.cd_fc}</td>
			<td>${List.no_edoc}</td>
			<td>${ufn:getCodeName('cd_type_txrx', List.type_txrx)}</td>
			<td>${List.code_group}</td>
			<td>${List.nm_code}</td>
			<td>${List.item_tag}</td>
			<td>${List.type_attr}</td>
			<td>${List.field_length}</td>
			<td>${List.yn_use}</td>
			<td>${List.parent_code_group}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>