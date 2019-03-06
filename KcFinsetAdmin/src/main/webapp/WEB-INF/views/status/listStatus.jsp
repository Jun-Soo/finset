<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	
	// 메모 팝업
	function goCouncelPopup(no_person, no_prepare, no_apply) {
		var openWindow = window.open("<c:url value='/counsel/popCounselInfo.crz'/>?no_person="+no_person+"&no_prepare="+no_prepare+"&no_apply="+no_apply, "openC"+no_person, "width=600, height="+(screen.availHeight-300)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
	
	// 첨부파일 팝업
	function goAttachPopup(no_apply){
		var openWindow = window.open("<c:url value='/attach/popAttachInfo.crz'/>?no_apply="+no_apply, "openA"+no_apply, "width=650, height="+(screen.availHeight-360)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
	
</script>

<div class="panel-collapse">
	<input type="hidden" name="listLength" value="${fn:length(pagedList.source)}" />
	<!-- List -->
	<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
		<thead>
	        <tr>
	          <th> 순번</th>
	          <th> 상품명</th>
	          <th> 금융사</th>                              
	          <th> 신청일시</th>                              
	          <th> 이름</th>
	          <th> 생년월일</th>
	          <th> 휴대폰</th>
	          <th> 신청금액</th>
	          <th> 승인금액</th>
	          <th> 승인일자</th>
	          <th> 처리일자</th>
	          <th> 담당자</th>               
	          <th> 메모</th>
	          <th> 상태</th>
	        </tr>                      
	      </thead>
	      
	      <tbody>
	      <!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:if test="${empty pagedList.source}">
		<tr>
			<td colspan="17" height="100" align="center">
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
	              <td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>  	   <!-- 순번 -->
	              <%-- <td>${ufn:getCodeName('cd_advertisement', List.cd_advertisement)}</td> --%>   <!-- 광고매체 -->
	              <td>${ufn:getGoodsDetail(List.cd_goods, '')}</td>						   <!-- 상품명 -->
	              <td>${ufn:getCodeName('cd_fc', List.cd_fc)}</td> 	       <!-- 금융사 -->            
	              <td>${List.dt_frt}</td>   											   <!-- 접수일시 -->            
	              <%-- <td>${ufn:getCodeName('cd_job_class', List.cd_job_class)}</td> --%>  		   <!-- 자격 -->
	              <td>
	              		<a href="#" onclick="goPrepareForm('${List.no_prepare}','${List.no_apply}'); return false;">
	              		<strong>${List.nm_person}</strong>
	              		<c:choose>
						<c:when test="${List.c1_gender eq '1'}">
		               		<span class="glyphicon glyphicon-user male" aria-hidden="true" title="남성"></span>
						</c:when>
						<c:when test="${List.c1_gender eq '2'}">
		               		<span class="glyphicon glyphicon-user female" aria-hidden="true" title="여성"></span>
						</c:when>
					</c:choose>
					</a>
	              </td>   <!-- 이름 -->
	              <td><span id="pop" onclick="test();">${List.ymd_birth}</span></td>   <!-- 생년월일 -->
	              <td>
	              	<c:choose>
	              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
	              		<c:otherwise>${List.hp}</c:otherwise>
	              	</c:choose>
	              	
	              	<c:if test="${ufn:getNvlCodeName('_CONF_CTI','YN_USE_CTI','') eq 'Y'}">
		              	<button type="button" onclick="clickTocall('${List.no_person}','HP');" class="btn btn-xs circle" title="전화하기">
		              		<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
		              	</button>
	              	</c:if>
	              </td>   <!-- 휴대폰 -->
	              <td>${ufn:formatNumber(List.amt_apply)}</td>   <!-- 신청금액 -->
	              <td>${ufn:formatNumber(List.amt_approval)}</td>   <!-- 승인금액 -->
	              <td>${ufn:formatDate(List.ymd_approval)}</td>	<!-- 승인일자 -->
	              <td>${List.dt_lst}</td>   <!-- 처리일자 -->
	              <td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>   <!-- 담당자 -->
	              <td><!-- 메모 -->
	              		<a href="#" class="btn btn-xs circle" id="popover${status.count}" onclick="goCouncelPopup('${List.no_person}','${List.no_prepare}','${List.no_apply}'); return false;">
	              			<span class="glyphicon glyphicon-comment"></span>
	              		</a>
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
	              <td>
	              	<c:choose>
	              		<c:when test="${List.cd_apply_doc_box eq '50'}">
	              			<strong style="color:red">${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
	              		</c:when>
	              		<c:otherwise>
	              			<span style="color:${ufn:getCodeEtc('cd_apply_doc_box', List.cd_apply_doc_box)};" >
	              				${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}
	              			</span>
	              		</c:otherwise>
	              	</c:choose>
	              </td>   <!-- 상태 -->
	          </tr>           
	      </c:forEach>
	   </tbody>
	</table>
	<!-- //List -->
</div>
<div class="panel-footer">
	<span class="pull-left">
		<span class="total-num em"><span></span>총건수 : <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
			<c:if test="${!empty pagedList.amtSumMap['AMTTOTAPPLY']}">
			<span class="total-num em"><span></span>신청금액 : <em>${ufn:formatNumber(pagedList.amtSumMap['AMTTOTAPPLY'])} 만원</em></span>
		</c:if> 
		<c:if test="${!empty pagedList.amtSumMap['AMTTOTAPPR']}">
			<span class="total-num em"><span></span>승인금액 : <em>${ufn:formatNumber(pagedList.amtSumMap['AMTTOTAPPR'])} 만원</em></span>
		</c:if>
	</span>
</div>
 
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>
