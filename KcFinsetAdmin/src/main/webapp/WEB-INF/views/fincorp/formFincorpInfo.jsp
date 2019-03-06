<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	
	$('.selectpicker').selectpicker();
	
	//window.setupValidateForm( frmFincorpInfo );
	
});

//업권코드 표시
function CodeDisplay(frm) {

    var cd_fc = frm.cd_fin.selectedIndex;
    switch( cd_fc ){
	   case 0:
	     frm.cd_fc.value = '';
	     break;
	   case 2:
	     frm.cd_fc.value = 'B';
		 break;
	   case 3:
	     frm.cd_fc.value = 'S';
		 break;
	   case 4:
	     frm.cd_fc.value = 'C';
		 break;
	   case 5:
	     frm.cd_fc.value = 'D';
		 break;
	   case 6:
	     frm.cd_fc.value = 'E';
		 break;
    }
	
    return true;
}

// 업권코드+사업자번호 10자리
function CompDisplay(frm) {
	
	var edit01 = frm.cd_fin.value;
	
	if( frm.cd_fin.selectedIndex != 0 ){
		frm.cd_fc.value = edit01 + frm.no_biz_comp.value; 
	}
 }

$(function(){
    $( "#srch_nm_fc" ).autocomplete({
        source : function( request, response ) {
             $.ajax({
                    type: 'post',
                    url: '<c:url value="/fincorp/listNmfcInfoAuto.json"/>',
                    dataType: "json",
                    data: { txt_detail : request.term, nm_fc : frmFincorpInfo.nm_fc.value},
                    success: function(data) {
                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
                        response( 
                            $.map(data.data , function(item) {
                            	console.log(item);
                            	var data = { txt_detail : request.term, nm_fc : frmFincorpInfo.nm_fc.value};
                            	console.log(data);	
                                return {
                                    label: item.data,
                                    value: item.data.split("]")[0].replace('[', '')
                                }
                            })
                        );
                    }
               });
            },
        
        //조회를 위한 최소글자수  //item.data
        minLength: 1,
        select: function( event, ui ) {
            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
            var no_worker_sp1 = ui.item.label.split("]")[1];
            
            var no_person_re1 = no_worker_sp1.replace('[', '');

        	$("#cd_fc").val(no_person_re1);
        }
    });
});

$(function(){
    $( "#srch_no_worker" ).autocomplete({
        source : function( request, response ) {
        	//alert(request.term + ":" + cd_mapping_type + url_svc_map);
             $.ajax({
                    type: 'post',
//                     url: url_svc_map,
                    url: '<c:url value="/fincorp/listWorkerInfoAuto.json"/>',
                    dataType: "json",
                    //request.term = $("#autocomplete").val()
                    data: { txt_detail : request.term, cd_fc : frmFincorpInfo.cd_fc.value},
                    success: function(data) {
                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
                        response( 
                            $.map(data.data , function(item) {
                            	console.log(item);
                            		
                                return {
                                    label: item.data,
                                    value: item.data.split("]")[0].replace('[', '')
                                }
                            })
                        );
                    }
               });
            },
        
        //조회를 위한 최소글자수  //item.data
        minLength: 1,
        select: function( event, ui ) {
            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
            //var no_worker_sp  = ui.item.label.split("]")[0];
            var no_worker_sp1 = ui.item.label.split("]")[2];
            var no_worker_sp2 = ui.item.label.split("]")[3];
            var no_worker_sp3 = ui.item.label.split("]")[4];
            
            //var no_person_re = no_worker_sp.replace('[', '');
            var no_person_re1 = no_worker_sp1.replace('[', '');
            var no_person_re2 = no_worker_sp2.replace('[', '');
            var no_person_re3 = no_worker_sp3.replace('[', '');

            
            //$("#no_worker_auto").val(no_person_re);
        	$("#no_worker_auto1").val(no_person_re1);
        	$("#no_worker_auto2").val(no_person_re2);
        	$("#no_worker_auto3").val(no_person_re3);
        }
    });
})

//금융기관 코드 중복체크
function chk_value(){
    $.ajax({
           type: 'post',
           url: '<c:url value="/fincorp/chkCdfc.json"/>',
           dataType: "json",
           data: { cd_fc : frmFincorpInfo.cd_fc.value, nm_fc : frmFincorpInfo.nm_fc.value},
           success: function(data) {
	           	var data = data.result;
	           	if(data.returnObj == 0 ){
	           		alert(data.message);
	           	}else if(data.returnObj == 1 ){
	           		alert(data.message);
	           		$("#cd_fc").val("");
	           		if( $("#srch_nm_fc").val() != null){
    					$("#srch_nm_fc").val("");
  					}
	           	}
           }
      });
}



</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">기본정보</a>
		</h3>
		<span class="pull-right">

			<c:choose>
				<c:when test="${empty fincorpInfo.cd_fc}">
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmFincorpInfo');">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
					</button>
					<button type="button" class="btn btn-default btn-xs" onclick="createFincorp();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-default btn-xs" onclick="procFincorpInfo();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</button>
					<button type="button" class="btn btn-warning btn-xs" onclick="delFincorpInfo('${fincorpInfo.cd_fc}');">
						<span class="glyphicon" aria-hidden="true"></span> 삭제
					</button>
				</c:otherwise>
			</c:choose>
			
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
	<!-- 기본정보 및 계약자 Start -->
		<form name="frmFincorpInfo" id="frmFincorpInfo" enctype="multipart/form-data">
		<div  class="panel ">	
			<div class="panel-collapse">
			<table class="table table-classic">
			<thead>
				<colgroup>
					<col width="45%">
					<col width="5%">
					<col width="45%">
					<col width="5%">
				</colgroup>
			</thead>
			<tbody>
			<c:choose> 
				<c:when test="${empty fincorpInfo.cd_fc}"> <!-- 금융사정보 없는 경우 -->
					<tr>
						<th>금융기관코드</th>
						<td>
							<input type="text" class="form-control width-100" id="cd_fc" name="cd_fc" value="" maxlength="11" onchange="chk_value();"/>
						</td>
						<th>쿠콘금융사코드</th>
						<td>
							<input type="text" class="form-control width-100" id="cd_fc_coocon" name="cd_fc_coocon" value="" maxlength="15"/>
						</td>
					</tr>
					<tr>   
						<th>금융기관업권</th>
						<td>
							<select name="cd_fin" class="selectpicker" validate="required; label:업권코드;">
								${ufn:makeOptions("cd_fin","업권코드", "")}
							</select>
						</td>
						<th>사업자 번호</th>
						<td>
							<input type="text" class="form-control width-100" id="no_biz_comp" name="no_biz_comp" value="" validate="required; label:사업자번호;" maxlength="10"/>
						</td>
					</tr>
					<tr> 
						<th>금융기관명</th>
						<td>
							<input type="text" class="form-control width-100" name="nm_fc" id="srch_nm_fc" placeholder="금융기관 검색" value="" validate="required; label:금융기관명;" maxlength="30" onchange="chk_value();"/>
						</td>
						<th>대표자명</th>
						<td>
							<input type="text" class="form-control width-100" name="nm_ceo" value="" validate="required; label:대표자명;" maxlength="10"/>
						</td>
					</tr>
			</c:when>
			<c:otherwise> <!-- 금융사정보 있는경우 -->
					<tr>
						<th>금융기관코드</th>
						<td>
							<input type="text" class="form-control width-100" id="cd_fc" name="cd_fc" value="${fincorpInfo.cd_fc}" validate="required; label:금융기관코드;" maxlength="11" readonly="readonly"/>
						</td>
						<th>쿠콘금융사코드</th>
						<td>
							<input type="text" class="form-control width-100" id="cd_fc_coocon" name="cd_fc_coocon" value="${fincorpInfo.cd_fc_coocon}" maxlength="15"/>
						</td>
					</tr>
					<tr>
						<th>금융기관업권</th>
						<td>
							<select name="cd_fin" class="selectpicker" validate="required; label:업권코드;">
									${ufn:makeOptions("cd_fin","업권코드", fincorpInfo.cd_fin)}
							</select>
						</td>
						<th>사업자 번호</th>
						<td>
							<input type="text" class="form-control width-100" id="no_biz_comp" name="no_biz_comp" value="${fincorpInfo.no_biz_comp}" validate="reuired; label:사업자번호;" maxlength="10" />
						</td>
					</tr>
					<tr>
						<th>금융기관명</th>
						<td>
							<input type="text" class="form-control width-100" name="nm_fc" value="${fincorpInfo.nm_fc}" validate="required; label:금융기관명;" maxlength="30" />
						</td>
						<th>대표자명</th>
						<td>
							<input type="text" class="form-control width-100" name="nm_ceo" value="${fincorpInfo.nm_ceo}" validate="required; label:대표자명;" maxlength="10" />
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
					<tr>
						<th>사용여부</th>
						<td colspan="3"> 
							<select name="yn_use" class="selectpicker" validate="required; label:연계여부;">
								${ufn:makeOptions('yn_use','사용여부', fincorpInfo.yn_use)}
							</select> 
						</td>
					</tr>
					<tr>
						<th>제휴여부</th>
						<td>
							<select name="yn_alliance" class="selectpicker" validate="required; label:연계여부;">
								${ufn:makeOptions('yn_use','제휴여부', fincorpInfo.yn_alliance)}
							</select>
						</td>
						<th>스크래핑여부</th>
						<td>
							<select name="yn_scrap" class="selectpicker" validate="required; label:스크래핑연계여부;">
								${ufn:makeOptions('yn_use','스크래핑여부', fincorpInfo.yn_scrap)}
							</select>
						</td>
					</tr>
					<tr>
						<th><span>주  소</span></th>
						<td colspan="3">
							<input type="hidden" name="cd_addr_fc" id="cd_addr_fc" value="${fincorpInfo.cd_addr_fc}"/>
							<div class="input-group post-w">
								<input type="text" class="form-control" name="post_fc_tmp" placeholder="우편번호" value="${fincorpInfo.post6_fc}" validate="label:우편번호;numeric;maxbyte:6;"/>
								<input type="text" class="form-control" name="post_fc_tmp" placeholder="우편번호" value="${fincorpInfo.post5_fc}" readonly="readonly"/>
								<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="goPostCode('cd_addr_fc', 'post_fc_tmp' , 'addr1_fc', 'addr2_fc');">검색</button></span>
							</div>
							<input type="text" class="form-control width-280" name="addr1_fc" id="addr1_fc" value="${fincorpInfo.addr1_fc}" readonly="readonly"/>
							<input type="text" class="form-control width-280" name="addr2_fc" id="addr2_fc" value="${fincorpInfo.addr2_fc}"/>
						</td>
					</tr>
					<tr>
						<th>대표번호</th>
						<td colspan="3">
							<input type="text" class="form-control width-280" name="tel" value="${fincorpInfo.tel}" validate="label:대표번호;" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<th>홈페이지</th>
						<td colspan="3"> 
							<input type="text" class="form-control width-280" name="home_page" value="${fincorpInfo.home_page}" validate="label:홈페이지;" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>로   고</th>
						<td colspan="3">
							<c:if test="${!empty fincorpInfo.path_file1}">
								<img src="<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${fincorpInfo.path_file1}" alt="로고" />
								<a class="btn btn-xs circle" href="<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${fincorpInfo.path_file1}"><span class="glyphicon glyphicon-save"></span></a>
							</c:if>
							<br>
							<span id="file1Space"><input type="file" name="file1" id="file1" class="file-contol" /></span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>	
	<!-- srchou -->
	<div class="panel panel-primary" >
		<%@ include file="/WEB-INF/views/fincorp/formFincorpfcNminfo.jsp"%>
	</div>
	
	<div id="listFincorpfcNminfo" class="panel panel-primary" >
		<%@ include file="/WEB-INF/views/fincorp/listFincorpfcNminfo.jsp"%>
	</div>
	
	<div  class="panel panel-primary" style="float: left; width: 50%;">
	<!-- style="float: left; width: 50%;" -->
		<div class="panel-heading">계약 담당자</div>
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody> 
				<tr>
					<th>이  름</th>
					<td>
						<input type="text" class="form-control width-150" name="nm_staff_contract" value="${fincorpInfo.nm_staff_contract}" validate="label:계약 담당자 이름;" maxlength="30"/>
					</td>
				</tr>
				<tr>	
					<th>휴대폰</th>
					<td>
						<div class="input-group phone-w">
							<select name="hp_con_tmp" id="hp_con_tmp" class="selectpicker" validate="label:휴대폰;">
								${ufn:makeOptions("cd_hp_kind","구분", fincorpInfo.hp_con_idx1)}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="hp_con_tmp" id="hp_con_tmp" value="${fincorpInfo.hp_con_idx2}" validate="label:휴대폰;numeric;keyup-next:hp_con_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="hp_con_tmp" id="hp_con_tmp" value="${fincorpInfo.hp_con_idx3}" validate="label:휴대폰;numeric;" maxlength="4"/>
						</div>
					</td>
				</tr>
				
				<tr>
					<th>연 락 처</th>
				<td>
					<div class="input-group phone-w">
						<select name="home_con_tmp" id="home_con_tmp" class="selectpicker">
							${ufn:makeOptionsType("cd_ph_local", "지역번호", fincorpInfo.home_con_idx1,"")}
						</select>
						<span class="input-group-addon" id="basic-addon1">-</span>
						<input type="text" class="form-control" name="home_con_tmp" id="home_con_tmp" value="${fincorpInfo.home_con_idx2}" validate="label:직장전화;numeric;keyup-next:home_con_tmp" maxlength="4"/>
						<span class="input-group-addon" id="basic-addon1">-</span>
						<input type="text" class="form-control" name="home_con_tmp" id="home_con_tmp" value="${fincorpInfo.home_con_idx3}" validate="label:직장전화;numeric;" maxlength="4"/>	
					</div>
				</td>
				</tr>
				<tr>	
					<th>FAX</th>
					<td>
						<div class="input-group phone-w">
							<input type="text" class="form-control" name="fax_con_tmp" id="fax_con_tmp" value="${fincorpInfo.fax_con_idx1}" validate="label:팩스;numeric;keyup-next:fax_con_tmp" maxlength="3"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_con_tmp" id="fax_con_tmp" value="${fincorpInfo.fax_con_idx2}" validate="label:팩스;numeric;keyup-next:fax_con_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_con_tmp" id="fax_con_tmp" value="${fincorpInfo.fax_con_idx3}" validate="label:팩스;numeric;" maxlength="4"/>	
						</div>
					</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>
						<div class="input-group email-w">
							<input type="text" class="form-control" name="email_con_tmp" id="email_con_tmp" value="${fincorpInfo.email_con_idx1}" />
							<span class="input-group-addon" id="basic-addon1">@</span>
							<input type="text" class="form-control width-60" name="email_con_tmp" id="email_con_tmp_2" value="${fincorpInfo.email_con_idx2}" />
                            <input type="hidden" name="email_con" id="email_con"/>						
						</div>
						<select name="email_domain" id="email_domain" class="selectpicker" onchange="$('#email_con_tmp_2').val(this.value)" data-width="70px">
							${ufn:makeOptions("cd_email_vendor", "직접입력", "${fincorpInfo.email_con_idx2}")}
						</select>
					</td>
				</tr>
					
			</tbody>
		</table>
		</div>
	</div>
	
	<div class="panel panel-primary"  style="float: right; width: 50%;">
	<!--style="float: right; width: 50%;"  -->
		<div class="panel-heading">정산 담당자</div>
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody> 
			
				<tr>
					<th>이  름</th>
					<td>
						<input type="text" class="form-control width-150" name="nm_staff_adjust" value="${fincorpInfo.nm_staff_adjust}" validate="label:정산 담당자 이름;" maxlength="30"/>
					</td>
				</tr>
				<tr>	
					<th>휴대폰</th>
						<td>
							<div class="input-group phone-w">
								<select name="hp_adj_tmp" id="hp_adj_tmp" class="selectpicker" validate="label:정산 담당자 휴대폰;">
									${ufn:makeOptions("cd_hp_kind","구분", fincorpInfo.hp_adj_idx1)}
								</select>
								<span class="input-group-addon" id="basic-addon1">-</span>
								<input type="text" class="form-control" name="hp_adj_tmp" id="hp_adj_tmp" value="${fincorpInfo.hp_adj_idx2}" validate="label:정산 담당자 휴대폰;numeric;keyup-next:hp_adj_tmp" maxlength="4"/>
								<span class="input-group-addon" id="basic-addon1">-</span>
								<input type="text" class="form-control" name="hp_adj_tmp" id="hp_adj_tmp" value="${fincorpInfo.hp_adj_idx3}" validate="label:정산 담당자 휴대폰;numeric;" maxlength="4"/>
							</div>
						</td>
				</tr>
				
				<tr>
					<th>연 락 처</th>
					<td>
						<div class="input-group phone-w">
							<select name="home_adj_tmp" id="home_adj_tmp" class="selectpicker">
								${ufn:makeOptionsType("cd_ph_local", "지역번호", fincorpInfo.home_adj_idx1,"")}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="home_adj_tmp" id="home_adj_tmp" value="${fincorpInfo.home_adj_idx2}" validate="label:정산 담당자 연락처;numeric;keyup-next:home_adj_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="home_adj_tmp" id="home_adj_tmp" value="${fincorpInfo.home_adj_idx3}" validate="label:정산 담당자 연락처;numeric;" maxlength="4"/>	
						</div>
					</td>
				</tr>
				<tr>	
					<th>FAX</th>
					<td>
						<div class="input-group phone-w">
							<input type="text" class="form-control" name="fax_adj_tmp" id="fax_adj_tmp" value="${fincorpInfo.fax_adj_idx1}" validate="label:정산 담당자 팩스;numeric;keyup-next:fax_adj_tmp" maxlength="3"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_adj_tmp" id="fax_adj_tmp" value="${fincorpInfo.fax_adj_idx2}" validate="label:정산 담당자 팩스;numeric;keyup-next:fax_adj_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_adj_tmp" id="fax_adj_tmp" value="${fincorpInfo.fax_adj_idx3}" validate="label:정산 담당자 팩스;numeric;" maxlength="4"/>	
						</div>
					</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>
						<div class="input-group email-w">
							<input type="text" class="form-control" name="email_adj_tmp" id="email_adj_tmp" value="${fincorpInfo.email_adj_idx1}" />
							<span class="input-group-addon" id="basic-addon1">@</span>
							<input type="text" class="form-control width-100" name="email_adj_tmp" id="email_adj_tmp_2" value="${fincorpInfo.email_adj_idx2}" />
                            <input type="hidden" name="email_adj" id="email_adj"/>						
						</div>
						<select name="email_domain" id="email_domain" class="selectpicker" onchange="$('#email_adj_tmp_2').val(this.value)" data-width="70px">
							${ufn:makeOptions("cd_email_vendor", "직접입력", "${fincorpInfo.email_adj_idx2}")}
						</select>
					</td>
				</tr>
					
			</tbody>
		</table>
		</div>
	</div>
	
	
	<div class="panel panel-primary">
		<div class="panel-heading">상품 담당자</div>
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
				<col width="10%">
				<col width="90%">
			</colgroup>
			<tbody> 
				<tr>
					<th>아 이 디</th>
					<td colspan="3">
						<input type="text" class="form-control width-120" name="id_emp_goods" placeholder="아이디 검색"  id="srch_no_worker" value="${fincorpInfo.id_emp_goods}"/>
					</td>
				</tr>

				<tr>
					<th>이  름</th>
					<td>
						<input type="text" class="form-control width-150"  id="no_worker_auto1" name="nm_staff_goods" value="${fincorpInfo.nm_staff_goods}" validate="; label:상품 담당자 이름;" maxlength="30" readonly="readonly"/>
					</td>
				</tr>
				<tr>	
					<th>휴대폰</th>
					<td>
						<input type="text" class="form-control width-150"  id="no_worker_auto2" name="hp_staff_goods" value="${fincorpInfo.hp_staff_goods}" validate="; label:상품 담당자 휴대폰;" maxlength="30" readonly="readonly"/>
					</td>
				</tr>
				
				<tr>
					<th>연 락 처</th>
					<td>
						<input type="text" class="form-control width-150"   id="no_worker_auto3" name="home_staff_goods" value="${fincorpInfo.home_staff_goods}" validate="; label:상품 담당자 연락처;" maxlength="30" readonly="readonly"/>
					</td>
				</tr>
				<tr>	
					<th>FAX</th>
					<td>
						<div class="input-group phone-w">
							<input type="text" class="form-control" name="fax_god_tmp" id="fax_god_tmp" value="${fincorpInfo.fax_god_idx1}" validate="label:상품 담당자 팩스;numeric;keyup-next:fax_god_tmp" maxlength="3"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_god_tmp" id="fax_god_tmp" value="${fincorpInfo.fax_god_idx2}" validate="label:상품 담당자 팩스;numeric;keyup-next:fax_god_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" name="fax_god_tmp" id="fax_god_tmp" value="${fincorpInfo.fax_god_idx3}" validate="label:상품 담당자 팩스;numeric;" maxlength="4"/>	
						</div>
					</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>
						<div class="input-group email-w">
							<input type="text" class="form-control" name="email_god_tmp" id="email_god_tmp" value="${fincorpInfo.email_god_idx1}" />
							<span class="input-group-addon" id="basic-addon1">@</span>
							<input type="text" class="form-control width-100" name="email_god_tmp" id="email_god_tmp_2" value="${fincorpInfo.email_god_idx2}" />
                            <input type="hidden" name="email_god" id="email_god"/>						
						</div>
						<select name="email_domain" id="email_domain" class="selectpicker" onchange="$('#email_god_tmp_2').val(this.value)" data-width="70px">
							${ufn:makeOptions("cd_email_vendor", "직접입력", "${fincorpInfo.email_god_idx2}")}
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
		
	<div  class="panel panel-primary">
		<div class="panel-heading">금융사 약관정보</div>	
			<div class="panel-collapse">
					<table class="table table-classic">
	 					<colgroup>
							<col width="50%">
							<col width="50%">
						</colgroup>
						<tbody> 
							<tr>
								<td><strong>서비스이용약관</strong></td>
							</tr>
							<tr>
								<td><textarea class="form-control w100 h150" name="cl_service" validate="label:약관내용; maxbt:2000;" maxlength="2000">${fincorpInfo.cl_service}</textarea></td>
							</tr>
							
							<tr>
								<td><strong>개인신용정보 취급방침</strong></td>
							</tr>
							<tr>
								<td><textarea class="form-control w100 h150" name="cl_private" validate="label:약관내용; maxbt:2000;" maxlength="2000">${fincorpInfo.cl_private}</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<!-- 기본정보 및 담당자 End -->
	</div>
</div>

