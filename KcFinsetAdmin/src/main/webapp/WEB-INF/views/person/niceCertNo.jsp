<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<input type="hidden" name="listLength" value="${fn:length(pagedList.source)}" />
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<colgroup>
				<col width="50px">
				<col width="120px">
				<col width="160px">
				<col width="80px">
				<col width="*">
				<col width="120px">
				<col width="160px">
				<col width="160px">
				<col width="160px">
				<col width="160px">
				<col width="160px">
				<col width="160px">
			</colgroup>
		       <thead>
		           <tr>
		               <th> 순번</th>
		               <th> 고유번호</th>
		               <th> Safe-Key</th>
		               <th> 소셜업체</th>
		               <th> 소셜ID</th>
		               <th> 이름</th>
		               <th> 생년월일</th>
		               <th> 휴대폰</th>
		               <th> 직장명</th>
		               <th> 이메일</th>
		               <th> 등록일시</th>
		               <th> 동의일시</th>
		           </tr>
		       </thead>

		       <tbody>
		       <!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="11" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
				</c:if>
		       <c:forEach var="List" items="${pagedList.source}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="class_string" value="active"/>
						</c:when>
						<c:otherwise>
							<c:set var="class_string" value=""/>
						</c:otherwise>
					</c:choose>

				<tr class="${class_string}">
	               <td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>   <!-- 순번 -->
	               <td>${List.no_person}</td>   <!-- 고유번호 -->
	               <td><a href="#" onclick="sendNiceCredit('${List.nice_safekey}');"><strong>${List.nice_safekey}</strong></td>   <!-- Safe-Key -->
	               <td>${List.comp_social}</td>   <!-- 소셜업체 -->
	               <td><a href="#" onclick="goPersonForm('${List.no_person}');"><strong>${List.id_social}</strong></td>   <!-- 소셜ID -->
	               <td>
	               		<a href="#" onclick="goPersonForm('${List.no_person}');"><strong>${List.nm_person}</strong></a>

	               		<c:choose>
							<c:when test="${List.c1_gender eq '1'}">
			               		<span class="glyphicon glyphicon-user male" aria-hidden="true" title="남성"></span>
							</c:when>
							<c:when test="${List.c1_gender eq '2'}">
			               		<span class="glyphicon glyphicon-user female" aria-hidden="true" title="여성"></span>
							</c:when>
						</c:choose>
	               </td>   <!-- 이름 -->
	               <td>${List.ymd_birth}</td>   <!-- 생년월일 -->
	               <td>
						<c:choose>
		              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
		              		<c:otherwise>${List.hp}</c:otherwise>
		              	</c:choose>
	               </td>   <!-- 휴대폰 -->
	               <td>${List.nm_comp}</td>   <!-- 직장명 -->
	               <td>${List.email}</td>   <!-- 이메일 -->
	               <td>${List.dt_frt}</td>   <!-- 등록일시 -->
	               <td>${List.dt_agree_social}</td>   <!-- 동의일시 -->
		       </tr>
		       </c:forEach>
		       </tbody>
		</table>
		<!-- //List -->
	</div>
	<div class="panel-footer">
		<span class="pull-left">
			<span class="total-num em"><span></span>총건수 : <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
	</div>
</div>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>

