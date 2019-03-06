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
		<colgroup>
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="330px" />
			<col width="" />
		</colgroup>
		<thead>
	        <tr>
	          <th> 순번</th>
	          <th> 광고매체</th>               
	          <th> 상품명</th>
	          <th> 금융사</th>
	          <th> 상태</th>                             
	          <th> 신청일시</th>                              
	          <th> 이름</th>
	          <th> 생년월일</th>
	          <th> 신청금액</th>
	          <th> 승인금액</th>
	          <th> 승인일자</th>
	          <th> 처리일자</th>
	          <th> 담당자</th>               
	          <th> 사유</th>
	          <th> 파일</th>
	        </tr>                      
	      </thead>
	      
	      <tbody>
	      <!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:if test="${empty pagedList.source}">
		<tr>
			<td colspan="16" height="100" align="center">
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
	              <td>${fn:substring(ufn:getCodeName('cd_advertisement', List.cd_advertisement),0,6)}</td>   <!-- 광고매체 -->
	              <td>
	              	<a id="popover_goods${status.count}"><span>${fn:substring(ufn:getGoodsDetail(List.cd_goods, ''),0,8)}</span></a>
	              
	              	<!-- popover 구현 -->
	             	<script type="text/javascript">
	             		$("#popover_goods"+"${status.count}").popover({
		           			trigger:"hover",
		           			html : true,
		           			placement : "right",
		           	        content: function() {
		           				return $("#pop_goods"+"${status.count}").html();
		           			}
	                   });
	             	</script>
					<div id="pop_goods${status.count}" class="popover" role="tooltip"><div>${ufn:getGoodsDetail(List.cd_goods, 'SUMMARY') }</div></div>
	              </td>		<!-- 상품명 -->
	              <td>${ufn:getCodeName('cd_fc', List.cd_fc)}</td> 	       <!-- 금융사 --> 
	              <td>
	              	<c:choose>
	              		<c:when test="${List.cd_apply_doc_box eq '50'}">
	              			<strong style="color:red">${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
	              		</c:when>
	              		<c:otherwise>
	              			${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}
	              		</c:otherwise>
	              	</c:choose>
	              </td>   <!-- 상태 -->           
	              <td>
	              	${fn:substring(List.dt_frt,2,16)}
	              </td>   											   <!-- 접수일시 -->            
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
	              <td>${ufn:formatNumber(List.amt_apply)}</td>   <!-- 신청금액 -->
	              <td>${ufn:formatNumber(List.amt_approval)}</td>   <!-- 승인금액 -->
	              <td>${ufn:formatDate(List.ymd_approval)}</td>	<!-- 승인일자 -->
	              <td>${fn:substring(List.dt_lst,5,16)}</td>   <!-- 처리일자 -->
	              <td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>   <!-- 담당자 -->
	              <td class="align-l pointer" onclick="goCouncelPopup('${List.no_person}','${List.no_prepare}','${List.no_apply}'); return false;"><!-- 사유 -->
	              		<a href="#" id="popover${status.count}"><span>${ufn:getSummaryContent(List.memo_from_apply, 32)}</span></a>
	              		
	              		<!-- popover 구현 -->
	              		<script type="text/javascript">
	              		$("#popover"+"${status.count}").popover({
	            			trigger:"hover",
	            			html : true,
	            			placement : "left",
	            			title:"사유",
	            	        content: function() {
	            				return $("#pop_content"+"${status.count}").html();
	            			}
	                    });
	              		</script>
						<div id="pop_content${status.count}" class="popover" role="tooltip"><div>${List.memo_from_apply}</div></div>
	              </td>   
	              <td><!-- 파일 -->
		              <a href="#" class="btn btn-xs circle" onclick="goAttachPopup('${List.no_apply}'); return false;">
			              <span class="glyphicon glyphicon-floppy-disk"></span>
		              </a>
	              </td>  
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
