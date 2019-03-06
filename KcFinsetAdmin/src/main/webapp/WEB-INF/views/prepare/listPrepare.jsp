<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() { 
	$('[data-toggle="popover"]').popover(); 
});

function goCouncelPopup(no_person, no_prepare) {
	var openWindow = window.open("<c:url value='/counsel/popCounselInfo.crz'/>?no_person="+no_person+"&no_prepare="+no_prepare, "open"+no_person, "width=600, height="+(screen.availHeight-300)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

function goSmsPopup(no_person) {
	var openWindow = window.open("<c:url value='/sms/popSmsList.crz'/>?no_person="+no_person, "open"+no_person, "width=600, height=500, scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}


</script>
<%@ include file="/WEB-INF/views/cti/embed_cti.jsp"%>
<input type="hidden" name="cd_prepare_class" id="cd_prepare_class" value="${prepareForm.cd_prepare_class}" />
<c:choose>
	<c:when test="${prepareForm.cd_prepare_doc eq '20'}">
		<div role="tabpanel">
			<ul class="nav nav-tabs tab-sm" role="tablist">
				<li role="presentation" <c:if test="${empty prepareForm.cd_prepare_class}">class="active"</c:if> onclick="javascript:frmPrepareList.cd_prepare_class.value=''; goSearch();"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab"> 전체</a></li>
				<c:forEach var="List" items="${ufn:getCodeList('cd_prepare_class')}">
					<li role="presentation" <c:if test="${prepareForm.cd_prepare_class eq List.code_value}">class="active"</c:if> onclick="javascript:frmPrepareList.cd_prepare_class.value=${List.code_value}; goSearch();"><a href="#cont1" role="tab" data-toggle="tab">${List.nm_code}(${ufn:formatNumber(cntPrepareClass[List.code_value])})</a></li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:when test="${prepareForm.cd_prepare_doc eq '50'}">
		<div role="tabpanel">
			<ul class="nav nav-tabs tab-sm" role="tablist">
				<li role="presentation" <c:if test="${empty prepareForm.cd_prepare_class}">class="active"</c:if> onclick="javascript:frmPrepareList.cd_prepare_class.value=''; goSearch();"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab"> 전체</a></li>
				<c:forEach var="List" items="${ufn:getCodeList('cd_apply_class')}">
				
					<c:choose>
						<c:when test="${List.code_value ne '50' && List.code_value ne '60'}">
							<li role="presentation" <c:if test="${prepareForm.cd_prepare_class eq List.code_value}">class="active"</c:if> onclick="javascript:frmPrepareList.cd_prepare_class.value=${List.code_value}; goSearch();"><a href="#cont1" role="tab" data-toggle="tab">${List.nm_code}(${ufn:formatNumber(cntPrepareClass[List.code_value])})</a></li>
						</c:when>
						<c:otherwise>
							<li role="presentation" <c:if test="${prepareForm.cd_prepare_class eq List.code_value}">class="active"</c:if> onclick="javascript:frmPrepareList.cd_prepare_class.value=${List.code_value}; goSearch();"><a href="#cont1" role="tab" data-toggle="tab">${List.nm_code}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</c:when>
</c:choose>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<!-- List -->
		<input type="hidden" name="listLength" value="${fn:length(pagedList.source)}" />
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
		         <tr>
		            <th>
		            	<input type="checkbox" id="checkAll" onclick="checkBoxAllCheck(this,'no_prepare_list');"/><label for="checkAll" class="checkbox-inline"></label>
		            </th>
		            <th> 광고매체</th>              
		            <th> 상품구분</th>
					<th> 유입키워드</th>
		            <th> 신청일시</th>                              
		            <th> 직업구분</th>
		            <th> 이름</th>
		            <th> 생년월일</th>
		            <th> 휴대폰</th>
		            <th> 신청금액</th>
		            <th> 승/접</th>
		            <th> 승인금액</th>
		            <th> 처리일자</th>
		            <th> 담당자</th>               
		            <th> 메모</th>
		            <th> SMS</th>
		            <th> 상태</th>
		            <th> 상세상태</th>
		        </tr>                      
		    </thead>
		       
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="18" height="100" align="center">
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
		              <td>
		              	<input type="checkbox" name="no_prepare_list" id="no_prepare_list${status.count}" value="${List.no_prepare}"/><label for="no_prepare_list${status.count}" class="checkbox-inline"></label>
		              </td>   <!-- 선택 -->
		              <td>${ufn:getCodeName('cd_advertisement', List.cd_advertisement)}</td>   <!-- 광고매체 -->
		              <td>${ufn:getCodeName('cd_goods_type', List.cd_goods_type)}</td>   <!-- 상품구분 -->
		              
		              <td> <!-- 유입키워드 -->
						<c:if test="${!empty List.c6_keyword}">
							<c:set var="nm_keyword" value="${ufn:getCodeName('cd_keyword_comp', fn:substring(List.c6_keyword,0,1))}-${ufn:getCodeName('cd_keyword', fn:substring(List.c6_keyword,1,5))}" />
							<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-title="접수시 메모" data-content="${nm_keyword}">
							${fn:substring(nm_keyword,0,8)}
							</span>
						</c:if>
					  </td>
		              <td>${List.dt_frt}</td>   <!-- 접수일시 -->            
		              <td>${ufn:getCodeName('cd_job_class', List.cd_job_class)}</td>   <!-- 자격 -->
		              <td>
		             	<a href="#" onclick="goPrepareForm('${List.no_prepare}'); return false;">
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
		              <td>${List.ymd_birth}</td>   <!-- 생년월일 -->
		              <td>
		              	<c:choose>
		              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
		              		<c:otherwise>${List.hp}</c:otherwise>
		              	</c:choose>
		              	
		              	<c:if test="${List.yn_exist_person eq 'Y'}">
		              	<a href="#" class="btn btn-xs circle_small equals"><span class="glyphicon glyphicon-pause"></span></a>
		              	</c:if>
		              	<c:if test="${ufn:getNvlCodeName('_CONF_CTI','YN_USE_CTI','') eq 'Y'}">
			              	<button type="button" onclick="clickTocall('${List.no_person}','HP');" class="btn btn-xs circle" title="전화하기">
			              		<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
			              	</button>
		              	</c:if>
		              </td>   <!-- 휴대폰 -->
		              <td>${ufn:formatNumber(List.amt_apply)}</td>   <!-- 신청금액 -->
		              <td>${List.cnt_approval} / ${List.cnt_apply}</td>   <!-- 승인/접수 건수 -->
		              <td>
		              	<c:choose>
		              		<c:when test="${List.amt_approval > '0'}">
		              			<strong style="color:red">${ufn:formatNumber(List.amt_approval)}</strong>
		              		</c:when>
		              	</c:choose>
		              </td>   <!-- 승인금액 -->
		              <td>${List.dt_lst}</td>   <!-- 처리일자 -->
		              <td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>   <!-- 담당자 -->
		              <td class="list-group-item-text"><!-- 메모 -->
		              		<a href="#" class="btn btn-xs circle" id="popover${status.count}" onclick="goCouncelPopup('${List.no_person}','${List.no_prepare}'); return false;">
		              		<span class="glyphicon glyphicon-comment"></span>
		              		</a>
		              		<!-- popover 구현 -->
		              		<script type="text/javascript">
		              		$("#popover"+"${status.count}").popover({
		            			trigger:"hover",
		            			html : true,
		            			placement : "left",
		            			title:"상담메모",
		            	        content: function() {
		            				return $("#pop_content"+"${status.count}").html();
		            			}
		                    });
		              		</script>
							<div id="pop_content${status.count}" class="popover" role="tooltip"><div>${List.memo_from_counsel}</div></div>
		              		
		              </td>
		              <td>
		              	<a href="#" class="btn btn-xs circle" onclick="goSmsPopup('${List.no_person}'); return false;">
		              		<span class="glyphicon glyphicon-envelope"></span>
		              	</a>
		              </td>   
		              <td>
		              	${ufn:getCodeName('cd_prepare_doc_box', List.cd_prepare_doc_box)}
		              </td>   <!-- 상태 -->
		              <td>
		             	<c:choose>
		              		<c:when test="${List.cd_prepare_doc_box eq '20'}">
			              		<span style="color:${ufn:getCodeEtc('cd_prepare_class', List.cd_prepare_class)};" >
			              			${ufn:getCodeName('cd_prepare_class', List.cd_prepare_class)}
			              		</span>
		              		</c:when>
		              		<c:when test="${List.cd_prepare_doc_box eq '50'}">
		              			<span style="color:${ufn:getCodeEtc('cd_apply_class', List.cd_prepare_class)};" >
		              				${ufn:getCodeName('cd_apply_class', List.cd_prepare_class)}
		              			</span>
		              		</c:when>
		              		<c:when test="${List.cd_prepare_doc_box eq '60'}">
		              			<span style="color:${ufn:getCodeEtc('cd_reject_cause', List.cd_reject_cause)};" >
		              				${ufn:getCodeName('cd_reject_cause',List.cd_reject_cause)}
		              			</span>
		              		</c:when>
		              	</c:choose>
		              </td>   <!-- 상세 상태 -->
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
