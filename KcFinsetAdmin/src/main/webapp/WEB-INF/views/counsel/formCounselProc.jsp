<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<c:choose>
	<c:when test="${counselInfo.cd_counsel_status ne '3'}">
		<title>신용상담 결과 입력</title>
	</c:when>
	<c:otherwise>
		<title>신용상담 결과</title>
	</c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({
		minSize:				40
		,	west__size:				"550" //좌측 width
		//,	east__size:				"60%" //우측 width
 		,	north__spacing_open:	0
		,	north__resizable:		false
		,	west__resizable:		false
		,	spacing_open:			5
		,	spacing_closed:			5
		,	togglerLength_open:		50
		,	togglerLength_closed:	"100%"
		,	east__initClosed:		false
	});
	$('.selectpicker').selectpicker();
	//datepicker
	$('.input-daterange').datepicker({
		format: "yyyymmdd", //dateformat
		todayHighlight: true, //당일 표시
		todayBtn: "linked", //today button link 활성화
		autoclose: true, //달력 자동 닫힘
		beforeShowDay : nationalDays
	});
	
	window.setupValidateForm( frmCounsel );
	
	//상담메모 수정시 알람사용 disabled
	if('${counselVO.seq_counsel}' != ""){
		$("input[id=chk_alarm]").attr("disabled",true);
	}
});



function saveCounselContents() {
	var frm = document.frmCounsel;
	
 	frm.counsel_contents.value = CKEDITOR.instances.content.getData();
	
    if(!frm.validateForm()) return false;

	var data = frm.ajaxSubmit();
    
	if(data == null) return false; 

	$.ajax({
		url : "<c:url value='/counsel/saveCounselContents.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnDataCounsel = result.returnDataCounsel;
			alert(returnDataCounsel.message);
			self.close();
			opener.goSearch();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
</script>
</head>
<body>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<form name="frmCounsel" id="frmCounsel">
<input type="hidden" name="counsel_contents" value=""/>
	<!-- Layout-North -->
	<div class="ui-layout-north">
		<div class="navbar navbar-default color-bar">
		<div class="ui-layout-content">
			<div class="cust-credit pull-left">
				<div class="top-row">
				<c:choose>
					<c:when test="${counselInfo.cd_counsel_status ne '3'}">
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>신용상담 결과 입력</dt>
					</c:when>
					<c:otherwise>
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>신용상담 결과</dt>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		</div>
	</div>
	<!-- //Layout-North -->
	<!-- ui-layout-west -->
	<div class="ui-layout-west">
		<div class="ui-layout-content">
			<div class="modal-body">
				<div class="panel panel-primary">
					<div class="panel-heading">
						신청인 기본 정보
					</div>
					<div class="panel-collapse">
					<input type="hidden" name="counsel_seq" id="counsel_seq" value="${counselInfo.counsel_seq}" validate="required; label:상담번호"/>
					<table class="table table-classic">
						<colgroup>
							<col width="20%"/>
							<col width="30%"/>
							<col width="20%"/>
							<col width="30%"/>
						</colgroup>
						<tbody>
							<tr>
								<th><span >이름</span></th>
								<td>
									<input type="text" class="form-control width-120" name="nm_person" id="nm_person" value="${counselInfo.nm_person}" validate="required; label:이름" readonly="readonly"/>
								</td>
								<th><span >고유번호</span></th>
								<td>
									<input type="text" class="form-control width-120" name="no_person" id="no_person" value="${counselInfo.no_person}" validate="required; label:고유번호" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th><span >나이</span></th>
								<td>${counselInfo.age} 세</td>
								<th><span >성별</span></th>
								<td>${counselInfo.cd_gender}</td>
							</tr>
							<tr>
								<th><span >결혼여부</span></th>
								<td>
									<c:if test="${counselInfo.yn_wedding eq 'Y'}">기혼</c:if>
									<c:if test="${counselInfo.yn_wedding ne 'Y'}">미혼</c:if>
								</td>
								<th><span >부양가족</span></th>
								<td>${ufn:getCodeName('cd_family_cnt', counselInfo.cd_family_cnt)}</td>
							</tr>
							<tr>
								<th><span >직업</span></th>
								<td>${ufn:getCodeName('cd_job', counselInfo.cd_job)}</td>
								<th><span >주거형태</span></th>
								<td>${ufn:getCodeName('cd_living', counselInfo.cd_living)}</td>
							</tr>
							<tr>
								<th><span >소득</span></th>
								<td>${ufn:formatNumber(counselInfo.amt_mm_income)} 만원</td>
								<th><span >지출</span></th>
								<td>${ufn:formatNumber(counselInfo.amt_mm_expense)} 만원</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<!--신용정보  -->			
				<div id="creditInfo">
					<div class="panel panel-primary">
					<div class="panel-heading">신용정보</div>
					<div class="panel-collapse">
						<table id="tbl_listCredit" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
							<thead>
								<tr>
									<th>기준년월</th>
									<th>신용등급</th>
									<th>신용점수</th>
									<th>대비</th>
									<th>순위</th>
								</tr>
							</thead>
							<tbody>
								<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
								<c:if test="${empty creditList}">
								<tr>
									<td colspan="5" height="100" align="center">
										신용정보가 없습니다.
									</td>
								</tr>
								</c:if>
								<c:forEach var="List" items="${creditList}" varStatus="status">
									<c:choose>
										<c:when test="${status.count%2==0}">
											<c:set var="class_string" value="active"/>
										</c:when>
										<c:otherwise>
											<c:set var="class_string" value=""/>
										</c:otherwise>
									</c:choose>
								
									<tr class="${class_string}">
										<td>${List.req_yyyymm}</td>
										<td>${List.grade_credit}</td>
										<td>${List.rating_credit}</td>
										<td>${List.percentage} %</td>
										<td>${List.rank}</td>
							        </tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				</div>
				<!--부채정보  -->
				<div id="debtList">
					<div class="panel panel-primary">
					<div class="panel-heading">부채정보</div>
					<div class="panel-collapse">
						<table id="tbl_listDebt" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
							<thead>
								<tr>
									<th>약정일</th>
									<th>분류</th>
									<th>기관</th>
									<th>원금</th>
									<th>잔액</th>
									<th>월상환액</th>
									<th>이자율</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
								<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
								<c:if test="${empty debtList}">
								<tr>
									<td colspan="8" height="100" align="center">
										부채정보가 없습니다.
									</td>
								</tr>
								</c:if>
								<c:forEach var="List" items="${debtList}" varStatus="status">
									<c:choose>
										<c:when test="${status.count%2==0}">
											<c:set var="class_string" value="active"/>
										</c:when>
										<c:otherwise>
											<c:set var="class_string" value=""/>
										</c:otherwise>
									</c:choose>
								
									<tr class="${class_string}">
										<td>${ufn:formatDate(List.ymd_loan)}</td>
										<td>${List.debt_type}</td>
										<td>${List.nm_biz}</td>
										<td>${ufn:formatNumber(List.amt_contract)} 만원</td>
										<td>${ufn:formatNumber(List.amt_remain)} 만원</td>
										<td>${ufn:formatNumber(List.amt_repay)} 만원</td>
										<td>${List.interest} %</td>
										<td>${List.nm_account}</td>
							        </tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				</div>
				<!--연체정보  -->			
				<div id="delayInfo">
					<div class="panel panel-primary">
					<div class="panel-heading">연체정보</div>
						<div class="panel-collapse">
							<table id="tbl_listDelay" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
								<thead>
									<tr>
										<th>발생일</th>
										<th>분류</th>
										<th>대상 기관</th>
										<th>연체금액</th>
										<th>비고</th>
									</tr>
								</thead>
								<tbody>
									<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
									<c:if test="${empty delayList}">
									<tr>
										<td colspan="5" height="100" align="center">
											연체정보가 없습니다.
										</td>
									</tr>
									</c:if>
									<c:forEach var="List" items="${delayList}" varStatus="status">
										<c:choose>
											<c:when test="${status.count%2==0}">
												<c:set var="class_string" value="active"/>
											</c:when>
											<c:otherwise>
												<c:set var="class_string" value=""/>
											</c:otherwise>
										</c:choose>
									
										<tr class="${class_string}">
											<td>${ufn:formatDate(List.ymd_delay)}</td>
											<td>${List.nm_cd_type}</td>
											<td>${List.nm_fc}</td>
											<td>${ufn:formatNumber(List.amt_delay)} 원</td>
											<td><%-- 최초연체금액 : ${ufn:formatNumber(List.amt_frt_delay)}
												<br>상환금액 : ${ufn:formatNumber(List.amt_repay)} --%>
											</td>
								        </tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
			
					</div>
				</div>
				<!--카드 보유(사용) 정보  -->			
				<div id="cardInfo">
					<div class="panel panel-primary">
					<div class="panel-heading">카드 보유(사용) 정보</div>
						<div class="panel-collapse">
							<table id="tbl_listCard" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
								<thead>
									<tr>
										<th>분류</th>
										<th>발급기관</th>
										<th>연체금액</th>
										<th>이용금액합</th>
										<th>일시불</th>
										<th>할부</th>
										<th>현금</th>
									</tr>
								</thead>
								<tbody>
									<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
									<c:if test="${empty cardList}">
									<tr>
										<td colspan="9" height="100" align="center">
											카드정보가 없습니다.
										</td>
									</tr>
									</c:if>
									<c:forEach var="List" items="${cardList}" varStatus="status">
										<c:choose>
											<c:when test="${status.count%2==0}">
												<c:set var="class_string" value="active"/>
											</c:when>
											<c:otherwise>
												<c:set var="class_string" value=""/>
											</c:otherwise>
										</c:choose>
									
										<tr class="${class_string}">
											<td>${List.nm_cd_type_deal}</td><!-- 분류 -->
											<td>${List.nm_fc}</td><!-- 발급기관 -->
											<td>${ufn:formatNumber(List.amt_delay)} 원</td><!-- 연체금액 -->
											<td>${ufn:formatNumber(List.amt_total)} 원</td><!-- 이용금액합 -->
											<td>${ufn:formatNumber(List.amt_lump_sum)} 원</td><!-- 일시불 -->
											<td>${ufn:formatNumber(List.amt_installment)} 원</td><!-- 할부 -->
											<td>${ufn:formatNumber(List.amt_short_card_loan)} 원</td><!-- 현금 -->
								        </tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
			
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- //ui-layout-center -->
	<div class="ui-layout-center">
	
		<div class="ui-layout-content">
			<div id="counselHist" scroll="yes" height="10">
				<!-- 상담이력 -->
				<div class="panel panel-primary">
				<div class="panel-heading" style="cursor: pointer;">상담이력</div>
					<div class="panel-collapse">
						<table id="tbl_listCounselHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
							<thead>
								<tr>
									<th>상담번호</th>
									<th>신청일</th>
									<th>완료일</th>
									<th>상태</th>
									<th>상담자</th>
									<th>비고</th>
								</tr>
							</thead>
							<tbody>
								<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
								<c:if test="${empty counselHist}">
								<tr>
									<td colspan="5" height="100" align="center">
										실행 이력이 없습니다.
									</td>
								</tr>
								</c:if>
								<c:forEach var="List" items="${counselHist}" varStatus="status">
									<c:choose>
										<c:when test="${status.count%2==0}">
											<c:set var="class_string" value="active"/>
										</c:when>
										<c:otherwise>
											<c:set var="class_string" value=""/>
										</c:otherwise>
									</c:choose>
								
									<tr class="${class_string}">
										<td>${List.counsel_seq}</td>
										<td>${List.dt_apply}</td>
										<td>${List.dt_counsel}</td>
										<td>${ufn:getCodeName('cd_counsel_status',List.cd_counsel_status) }</td>
										<td>${List.nm_emp}</td>
										<td>${List.etc_contents}</td>
							        </tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
		
				</div>
					
				<!-- 상담메모 폼 -->
				<div id="formCounsel">
					<div class="panel panel-default">
						<div class="panel-heading">
						<c:choose>
							<c:when test="${counselInfo.cd_counsel_status ne '3'}">
							<h3 class="h-sec pull-left">상담 내용 입력</h3>
							<span class="pull-right">
					
								<button type="button" class="btn btn-default btn-xs" onclick="saveCounselContents();">저장</button>
								<!-- <button type="button" class="btn btn-default btn-xs" onclick="delCounselContents();">삭제</button> -->
							</span>
							</c:when>
							<c:otherwise>
							<h3 class="h-sec pull-left">상담 내용</h3>
							</c:otherwise>
						</c:choose>
						</div>
							<div class="panel-collapse">
								<table class="table table-classic">
									<colgroup>
										<col width="15%">
										<col width="85%">
									</colgroup>
									<tbody>
										<tr>
											<th>상담상태</th>
											<td>
												<select class="selectpicker" name="cd_counsel_status" id="cd_counsel_status" validate="required; label:상담상태;">
								${ufn:makeOptions("cd_counsel_status","상담상태", (empty counselInfo.cd_counsel_status? ufn:getNvlCodeName('_CONF_SYSTEM','CD_DEFAULT_COUNSEL','1') :counselInfo.cd_counsel_status))}
												</select>
											</td>
										</tr>
										<tr>
											<th>상담내용</th>
											<td>
											<%-- <textarea class="form-control w100 h50" name="counsel_contents" validate="required; label:상담내용; maxbt:40000;" maxlength="40000">${counselInfo.counsel_contents}</textarea> --%>
											<textarea name="content" id="content">${counselInfo.counsel_contents}</textarea>
											<script type="text/javascript">
												CKEDITOR.replace( 'content' ,{
													language : 'ko',
													enterMode:'2',
													height : '250px',  // 입력창의 높이
													toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
												});							
											</script>
											</td>
										</tr>
										<tr>
											<th>비고</th>
											<td>
												<textarea class="form-control w100 h50" name="etc_contents" id="etc_contents" validate="label:비고; maxbt:4000;" maxlength="4000" ><c:out value="${counselInfo.etc_contents}"/></textarea>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
				</div>	
				
			</div>
		</div>
	</div>
</form>
<!-- //Layout-West -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>