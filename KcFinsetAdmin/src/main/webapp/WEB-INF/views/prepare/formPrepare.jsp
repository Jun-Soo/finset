<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		// toggle panel
		$(".toggle-panel").each(function(i){
			$(this).find(".h-sec > a").click(function(){
				$(this).toggleClass("closed");
				$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
			});
		});
		
		// 중복확인이 된 경우에 frmPrepareForm 내용이 나타남
		<c:if test="${prepareVO.yn_overlap_chk eq 'Y'}">
			// 서류함변경에 따른 select 노출
			showDocCause('${prepareVO.cd_prepare_doc_box}');
			// 적법수집 select 노출
			window.setupValidateForm( frmPrepareForm );
		</c:if>
	});


	// 보증인 삭제
	function delGrtInfo(no_person) {
		
		var no_prepare = frmPrepare.no_prepare.value;
		if(no_prepare == null) return false;
		
		var data = {"no_prepare":no_prepare,"no_person":no_person};
		if(data == null) return false; 
		
		$.ajax({
			url : "<c:url value='/grt/delGrtInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});  
	}
	
	function showDocCause(cd_prepare_doc){
		if(cd_prepare_doc == "60"){
			$("#div_reject_cause").show();
			$("#div_prepare_class_20").hide();
		}else if(cd_prepare_doc == "20"){
			$("#div_reject_cause").hide();
			$("#div_prepare_class_20").show();
		}else{
			$("#div_reject_cause").hide();
			$("#div_prepare_class_20").hide();
		}
	}
	
	function viewContact() {
		if("01" == frmPrepareForm.cd_collect_path.value) {
			$("#cd_contact_path_01").show();
			$("#cd_contact_path_02").hide();
		} else if("02" == frmPrepareForm.cd_collect_path.value) {
			$("#cd_contact_path_02").show();
			$("#cd_contact_path_01").hide();
		} else {
			$("#cd_contact_path_01").hide();
			$("#cd_contact_path_02").hide();
		}
	}
	
	// 보증인 등록, 수정
	function procGrtInfo(cd_rel_grt) {
		
		var data = {"no_prepare":frmPrepare.no_prepare.value, "no_person":frmGrtInfo.no_person.value, "cd_rel_grt":cd_rel_grt};
		
		$.ajax({
			url : "<c:url value='/grt/procGrtInfo.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function getCarInfo(no_prepare) {
		var data = {"no_prepare":no_prepare};
		vLoad("modal-content-lg","<c:url value='/car/getCarInfo.crz'/>",data,false);
	}
	
	// 차량담보 삭제
	function delCarInfo(no_prepare) {

		var data = {"no_prepare":no_prepare};

		$.ajax({
			url : "<c:url value='/car/delCarInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				loadPrepareForm();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	//개인정보 수정이력
	function getPersonInfoHist(no_person) {
		var data = {"no_person":no_person};
		vLoad("modal-content-lg","<c:url value='/person/modalPersonInfoHist.crz'/>",data,false);
	}
	
	function printArea(no_prepare, no_person) {
		var openPrint = window.open("<c:url value='/prepare/printFormPrepare.crz'/>?no_prepare="+no_prepare+"&no_person="+no_person, "print", "width=770, height=600, scrollbars=yes, resizable=yes", "");
		openPrint.focus();
		openPrint.print();
	}
	
	function delPrepare() {
		
		frmPrepareForm.cd_prepare_doc_box.value = '99';
		
		if ( !frmPrepareForm.validateForm() ) return false;
		var data = frmPrepareForm.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/prepare/modifyPrepareDoc.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				self.close();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	//접수경로 수정
	function getApplyPath(no_prepare) {
		var data = {"no_prepare":no_prepare};
		vLoad("modal-content-lg","<c:url value='/prepare/getApplyPath.crz'/>",data,false);
	}
 </script>
<form name="frmPrepare">
	<input type="hidden" name="no_prepare" value="${prepareVO.no_prepare}"/>
	<input type="hidden" name="no_person" value="${prepareVO.no_person}" />
	<input type="hidden" name="no_apply"/>
	<input type="hidden" name="yn_grt" value="" />
</form>

<c:choose>
	<c:when test="${prepareVO.yn_overlap_chk ne 'Y'}">
		<!-- Alert -->
		<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
			<ul>
				<li>고객찾기를 통하여 고객 목록을 확인하고, 기존 고객정보로 작성 할 수 있습니다.</li>
				<li>고객찾기를 완료 하신 후 고객을 <strong>선택</strong>하여 사전접수를 진행하여 주시기 바랍니다.</li>
			</ul>
		</div>
		<!--//Alert -->
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 신청정보</a>
				</h3>
			</div>
			<div class="panel-collapse toggle-cont">
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>신청금액</th>
							<td>
								${prepareVO.amt_apply} 만원
							</td>
							<th>신청일시</th>
							<td>${prepareVO.dt_frt}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 기본정보</a>
				</h3>
				<span class="pull-right">
					<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target=".bs-modal-lg" onclick="goCustRel('${personVO.no_person}','${prepareVO.no_prepare}','N');">
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
					</button>
				</span>
			</div>
			<div class="panel-collapse toggle-cont">
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="15%">
						<col width="20%">
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="*">
					</colgroup>
					<tbody>
						<c:choose>
							<c:when test="${empty personVO.no_person}">
								<tr>
									<td colspan="6" class="align-c">신규 작성할 고객을 선택하여주세요.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th>성명</th>
									<td>
										${personVO.nm_person}
									</td>
									<th>생년월일</th>
									<td>${ufn:formatDate(personVO.ymd_birth)}</td>
									<th>휴대전화</th>
									<td>
										<c:choose>
						              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
							              		${ufn:formatMaskHp(personVO.hp)}
							              		<button type="button" class="btn btn-default" onclick="viewPersonnalInfo('${personVO.no_person}','HP');"><span class="glyphicon glyphicon-zoom-in"></span></button>
						              		</c:when>
						              		<c:otherwise>${personVO.hp}</c:otherwise>
						              	</c:choose>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<form name="frmPrepareForm">
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 신청정보</a>
				</h3>
				<span class="pull-right">
					<button type="button" class="btn btn-primary btn-xs" data-target=".bs-modal-lg" onclick="modifyPrepare();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</button>
					<c:if test="${prepareVO.cd_prepare_doc_box eq '50' && prepareVO.cd_prepare_class eq '60' && ufn:getNvlCodeName('cd_prepare_doc_modify','99','') ne ''}">
						<button type="button" class="btn btn-danger btn-xs" onclick="delPrepare();">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 삭제
						</button>
					</c:if>
				</span>
			</div>
			<div class="panel-collapse toggle-cont">
				<input type="hidden" name="no_prepare" value="${prepareVO.no_prepare}"/>
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>광고매체</th>
							<td>
								<select name="cd_advertisement" class="selectpicker">
									${ufn:makeOptions("cd_advertisement","광고매체", prepareVO.cd_advertisement)}
								</select>
							</td>
							<th>상품구분</th>
							<td>
								<select name="cd_goods_type" class="selectpicker">
									${ufn:makeOptions("cd_goods_type","상품구분", prepareVO.cd_goods_type)}
								</select>
							</td>
						</tr>
						<tr>
							<th>신청금액</th>
							<td>
								<div class="input-group input-group-inline">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control align-r" name="amt_apply" value="${prepareVO.amt_apply}" validate="label:신청금액;money"/>
								</div> 만원
							</td>
							<th>신청일시</th>
							<td>${prepareVO.dt_frt}</td>
						</tr>
						<tr>
							<th>서류함</th>
							<td>
								<c:choose>
									<c:when test="${prepareVO.cd_prepare_doc_box eq '50'}">
										<input type="hidden" name="cd_prepare_doc_box" value="${prepareVO.cd_prepare_doc_box}"/>
										<input type="hidden" name="cd_prepare_class" value="${prepareVO.cd_prepare_class}"/>
										${ufn:getCodeName('cd_prepare_doc_box', prepareVO.cd_prepare_doc_box)}
									</c:when>
									<c:otherwise>
										<!-- 서류함 -->
										<div id="div_prepare_doc" style="float:left;">
										<select class="selectpicker" name="cd_prepare_doc_box" id="prepare_doc" onChange="showDocCause(this.value);" >
											${ufn:makeOptions("cd_prepare_doc_modify", "서류함", prepareVO.cd_prepare_doc_box)}
										</select>
										</div>				
										<!-- 접수불가 사유 -->				
										<div id="div_reject_cause" style="float:left;display:none;">
											<select class="selectpicker" name="cd_reject_cause" id="reject_cause">
												${ufn:makeOptions("cd_reject_cause", "접수불가 사유", prepareVO.cd_reject_cause)}
											</select>
										</div>
										<!-- 상담 사유 -->				
										<div id="div_prepare_class_20" style="float:left;display:none;">
											<select class="selectpicker" name="cd_prepare_class" id="prepare_class">
												${ufn:makeOptions("cd_prepare_class", "상담사유", prepareVO.cd_prepare_class)}
											</select>
										</div>
									</c:otherwise>
								</c:choose>
							
							
							</td>
							<th>담당자</th>
							<td>
								<c:choose>
									<c:when test="${ufn:isApprAuth('102002',sessionScope.STR_APPROVAL)}">
										<!--  담당자 -->
										<select name="id_prepare" class="selectpicker" id="id_prepare">
											${ufn:makeIdOptions('502001', '담당자', prepareVO.id_prepare)}
										</select>
									</c:when>
									<c:otherwise>
										${ufn:getWorkerInfo(prepareVO.id_prepare,'NM')}
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th><span class="required">접수 시 메모</span></th>
							<td colspan="3">
								${prepareVO.memo_from_agency}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		
		</form>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 기본정보</a>
				</h3>
				<span class="pull-right">
					<c:if test="${ufn:isApprAuth('302002',sessionScope.STR_APPROVAL)}">
						<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target=".bs-modal-lg" onclick="getPersonInfoHist('${personVO.no_person}');">
							<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 수정이력
						</button>
					</c:if>
					<c:if test="${apply_size eq 0}">
						<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target=".bs-modal-lg" onclick="goCustRel('${personVO.no_person}','${prepareVO.no_prepare}','N');">
							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
						</button>
					</c:if>
					<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target=".bs-modal-lg" onclick="getPerson('${personVO.no_person}');">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 정보수정
					</button>
				</span>
			</div>
			<div class="panel-collapse toggle-cont">
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="13%">
						<col width="12%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="10%">
						<col width="13%">
						<col width="*%">
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td colspan="2">
							<c:if test="${1 lt same_person}">
									<a href="#" class="btn circle_small warning">
										<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
									</a>
							</c:if>
							${personVO.nm_person}
							</td>
							<th>생년월일</th>
							<td colspan="4">${ufn:formatDate(personVO.ymd_birth)}</td>
						</tr>
						<tr>
							<th>휴대전화
								<c:if test="${ufn:getNvlCodeName('_CONF_CTI','YN_USE_CTI','') eq 'Y'}">
								<button type="button" onclick="clickTocall('${prepareVO.no_person}','HP');" class="btn btn-xs circle" title="전화하기"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span></button>
								</c:if>
							</th>
							<td colspan="2">
								<c:choose>
				              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
				              			${ufn:formatMaskHp(personVO.hp)}
				              			<button type="button" class="btn btn-default" onclick="viewPersonnalInfo('${personVO.no_person}','HP');"><span class="glyphicon glyphicon-zoom-in"></span></button>
				              		</c:when>
				              		<c:otherwise>${personVO.hp}</c:otherwise>
				              	</c:choose>
							</td>							
							<th>이메일</th>
							<td colspan="4">
								${personVO.email}
							</td>
						</tr>
						<tr>
							<th>자택주소</th>
							<td colspan="7">
								[${personVO.post6_home},${personVO.post5_home}] 
								${personVO.addr1_home} 
								${personVO.addr2_home} 
							</td>
						</tr>
						<tr>
							<th>주거종류</th>
							<td colspan="2">
								[${ufn:getCodeName('cd_house_type', personVO.cd_house_type_home)}] ${personVO.ym_house_reg_idx1}년 ${personVO.ym_house_reg_idx2}월
							</td>
							<th>주거형태</th>
							<td colspan="4">
								[${ufn:getCodeName('cd_live_type', personVO.cd_live_type_reg)}] 보증금:${personVO.amt_rent_deposit_reg} ,월세:${personVO.amt_rent_monthly_reg}
							</td>
						</tr>
						<tr>
							<th>결혼여부</th>
							<td colspan="2">${ufn:getCodeName("cd_marry", personVO.cd_marry)}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!--급여, 주부-급여, 자유소득, 기타-->
		<c:if test="${'10' eq personVO.cd_job_class || '30' eq personVO.cd_job_class || '60' eq personVO.cd_job_class || 
							'99' eq personVO.cd_job_class || empty personVO.cd_job_class}">
			<div class="panel panel-default toggle-panel">
				<div class="panel-heading">
					<h3 class="h-sec pull-left">
						<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 직장정보</a>
					</h3>
				</div>
				<div class="panel-collapse toggle-cont">
					<table class="table table-classic tbl-info">
						<colgroup>
							<col width="13%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
						</colgroup>
						<tbody>
							<tr>
								<th>직장명</th>
								<td>${personVO.nm_comp}</td>
								<th>부서명</th>
								<td>${personVO.nm_part_comp}</td>
								<th>직책</th>
								<td>${personVO.nm_duty_comp}</td>
								<th>고용형태</th>
								<td>${ufn:getCodeName('cd_employ_type', personVO.cd_employ_type)}</td>
							</tr>
							<tr>
								<th>직장전화
									<c:if test="${ufn:getNvlCodeName('_CONF_CTI','YN_USE_CTI','') eq 'Y'}">
									<button type="button" onclick="clickTocall('${prepareVO.no_person}','PH_COMP');" class="btn btn-xs circle" title="전화하기"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span></button>
									</c:if>
								</th>
								<td colspan="3">
									${personVO.ph_comp}
								</td>
								<th>직장팩스</th>
								<td colspan="3">
									${personVO.fax_comp}
								</td>
							</tr>
							<tr>
								<th>직장주소</th>
								<td colspan="7">
									[${personVO.post6_comp},${personVO.post5_comp}]
									${personVO.addr1_comp}
									${personVO.addr2_comp}
								</td>
							</tr>
							<tr>
								<th>입사년월</th>
								<td colspan="3">
									${personVO.ym_start_comp_idx1}년
									${personVO.ym_start_comp_idx2}월
								</td>
								<th>연소득</th>
								<td colspan="3">
									${ufn:formatNumber(personVO.amt_year_income)} 천원
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		
		<!-- 자영업, 주부-자영업 -->
		<c:if test="${'20' eq personVO.cd_job_class || '40' eq personVO.cd_job_class}">
			<div class="panel panel-default toggle-panel">
				<div class="panel-heading">
					<h3 class="h-sec pull-left">
						<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 사업체정보</a>
					</h3>
				</div>
				<div class="panel-collapse toggle-cont">
					<table class="table table-classic tbl-info">
						<colgroup>
							<col width="12%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
							<col width="12%">
							<col width="13%">
						</colgroup>
						<tbody>
							<tr>
								<th>직장명</th>
								<td colspan="2">${personVO.nm_comp}</td>
								<th>사업자번호</th>
								<td colspan="2">${personVO.no_biz_licence}</td>
								<th>업종</th>
								<td>${ufn:getCodeName('cd_industry', personVO.cd_industry)}</td>
								
							</tr>
							<tr>
								<th>직장전화</th>
								<td colspan="2">
									${personVO.ph_comp}
								</td>
								<th>직장팩스</th>
								<td colspan="2">
									${personVO.fax_comp}
								</td>
								<th>근무인원</th>
								<td>${personVO.cnt_worker} 명</td>
								
							</tr>
							<tr>
								<th>직장주소</th>
								<td colspan="7">
									[${personVO.post6_comp},${personVO.post5_comp}]
									${personVO.addr1_comp}
									${personVO.addr2_comp}
								</td>
							</tr>
							<tr>
								<th>입사년월</th>
								<td colspan="3">
									${personVO.ym_start_comp_idx1}년
									${personVO.ym_start_comp_idx2}월
								</td>
								<th>연/월소득</th>
								<td colspan="3">
									${personVO.amt_year_income} 천원 / ${personVO.amt_monthly_income} 천원
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>