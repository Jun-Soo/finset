<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmNmBizMemberMng">
<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>가맹점명</th>
			<td>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${empty cardInfo.cd_fc}"> --%>
<!-- 						<input type="hidden" id="card_cd_proc_type" name="cd_proc_type" value="C"> -->
<!-- 						<select class="selectpicker" id="card_cd_fc" name="cd_fc" validate="label:카드사;required;" data-width="100%"> -->
<!-- 								<option value="">선택</option>  -->
<%-- 							<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status"> --%>
<%-- 								<option value="${schCardFcList.cd_fc}">${schCardFcList.nm_fc}</option>  --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
<%-- 						<input type="hidden" id="card_cd_fc" name="cd_fc" value="${cardInfo.cd_fc}"> --%>
<!-- 						<select class="selectpicker" id="card_view_cd_fc" name="view_cd_fc" data-width="100%" disabled="disabled"> -->
<%-- 							<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status"> --%>
<%-- 								<option value="${schCardFcList.cd_fc}" <c:if test="${cardInfo.cd_fc eq schCardFcList.cd_fc}">selected="selected"</c:if>> --%>
<%-- 								${schCardFcList.nm_fc} --%>
<!-- 								</option>  -->
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
				<c:choose>
					<c:when test="${empty nmBizInfo.no_biz_member}">
						<input type="hidden" id="biz_cd_proc_type" name="cd_proc_type" value="C">
						<input type="text" class="form-control" id="biz_nm_member" name="nm_member" validate="label:가맹점명;required;"  readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" id="biz_nm_member" name="nm_member" value="${nmBizInfo.nm_member}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
			<th>사업자번호</th>
			<td>
				<c:choose>
					<c:when test="${empty nmBizInfo.no_biz_member}">
						<input type="hidden" id="biz_cd_proc_type" name="cd_proc_type" value="C">
						<input type="text" class="form-control" id="biz_no_biz_member" name="no_biz_member" validate="label:사업자번호;required;" readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" id="biz_no_biz_member" name="no_biz_member" value="${nmBizInfo.no_biz_member}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<c:choose>
					<c:when test="${empty nmBizInfo.cd_class}">
						<select class="selectpicker" id="biz_member_cd_class" name="cd_class" validate="label:분류;required;" data-width="100%" onchange="changeCdClass();">
								<option value="">선택</option>
							<c:forEach var="cdClassVO" items="${cdClassList}" varStatus="status">
								<option value="${cdClassVO.cd_class}">${cdClassVO.nm_class}</option>
							</c:forEach>
						</select>
					</c:when>
					<c:otherwise>
						<select class="selectpicker" id="biz_member_cd_class" name="cd_class" data-width="100%" onchange="changeCdClass();">
							<option value="">선택</option>
							<c:forEach var="cdClassVO" items="${cdClassList}" varStatus="status">
								<option value="${cdClassVO.cd_class}" <c:if test="${cdClassVO.cd_class eq nmBizInfo.cd_class}">selected="selected"</c:if>>
									${cdClassVO.nm_class}
								</option> 
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
			</td>
			<th>항목</th>
			<td>
				<c:choose>
					<c:when test="${empty nmBizInfo.cd_type}">
						<select class="selectpicker" id="biz_member_cd_type" name="cd_type" validate="label:항목;required;" data-width="100%">
								<option value="">선택</option> 
						</select>
					</c:when>
					<c:otherwise>
						<select class="selectpicker" id="biz_member_cd_type" name="cd_type" data-width="100%">
							<c:forEach var="cdTypeList" items="${cdTypeMap}" varStatus="status">
								<c:if test="${nmBizInfo.cd_class eq cdTypeList.key }">
									<c:forEach var="cdTypeVO" items="${cdTypeList.value}" varStatus="subStatus">
										<option value="${cdTypeVO.cd_type}" <c:if test="${cdTypeVO.cd_type eq nmBizInfo.cd_type}">selected="selected"</c:if>>
											${cdTypeVO.nm_type}
										</option> 
									</c:forEach>
								</c:if>
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="getNmBizMemberMulti();"><span class="glyphicon glyphicon-export" aria-hidden="true"></span> 일괄등록</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="getNmBizMemberMng('','');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procNmBizMemberMng();"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delNmBizMemberMng();"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>