<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div id="ChangeData" class="panel panel-primary" >
	<div class="panel-collapse">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상세 내용</a>
		</h3>
	</div>
	<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
		<thead>
	         <tr>
				<th>변경 전</th>
				<th>변경 후</th>
	        </tr>
	    </thead>
		<tbody>
			<tr class="${class_string}" style="cursor: pointer;">
				<c:choose>
					<c:when test="${empty pastResult}">
					<td width="50%" height="100" align="center">
						검색결과가 없습니다
					</td>
					</c:when>
					<c:otherwise>
					<td id="pastData" width="50%" height="100" align="center">
						${pastResult.column_name }
					</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty currentResult}">
					<td width="50%" height="100" align="center">
						검색결과가 없습니다
					</td>
					</c:when>
					<c:otherwise>
					<td id="currentData" width="50%" height="100" align="center">
						${currentResult.column_name }
					</td>
					</c:otherwise>
				</c:choose>
				
           	</tr>
		</tbody>
	</table>
	</div>
</div>