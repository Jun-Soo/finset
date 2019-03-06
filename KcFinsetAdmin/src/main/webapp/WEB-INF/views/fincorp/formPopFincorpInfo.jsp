<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	
	$('.selectpicker').selectpicker();
	
	//window.setupValidateForm( frmFincorp );
	
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
	var edit01 = frm.no_biz_comp.value;
	
	if( cd_fc != 0 ){
		frm.cd_fc.value = frm.cd_fc.value + edit01; 
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
                    data: { txt_detail : request.term, nm_fc : frmFincorp.nm_fc.value},
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
            var no_worker_sp1 = ui.item.label.split("]")[1];
            
            var no_person_re1 = no_worker_sp1.replace('[', '');

        	$("#cd_fc").val(no_person_re1);
        }
    });
})
</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">기본정보</a>
		</h3>
		<span class="pull-right">

			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmFincorp');">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<button type="button" class="btn btn-default btn-xs" onclick="createFincorp();">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
			</button>
			
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
	
	<!-- 기본정보 및 계약자 Start -->
		<form name="frmFincorp" id="frmFincorp" enctype="multipart/form-data">
			<div class="panel-collapse">
				<table class="table table-classic">
						<colgroup>
						<col width="25%">
						<col width="25%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tbody>
					
							<tr>
								<th>금융기관코드</th>
								<td colspan="3">
									<input type="text" class="form-control width-100" id="cd_fc" name="cd_fc" value="${fincorpInfo.cd_fc}" maxlength="11" readonly="readonly"/>
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
									<input type="text" class="form-control width-100" id="no_biz_comp" name="no_biz_comp" value="${fincorpInfo.no_biz_comp}" validate="required; label:사업자번호;" maxlength="10"/>
								</td>
							</tr>
							
							<tr>
								<th>금융기관명</th>
								<td>
									<input type="text" class="form-control width-100" name="nm_fc" id="srch_nm_fc" placeholder="금융기관 검색" value="${fincorpInfo.nm_fc}" validate="required; label:금융기관명;" maxlength="30"/>
								</td>
								<th>대표자명</th>
								<td>
									<input type="text" class="form-control width-100" name="nm_ceo" value="${fincorpInfo.nm_ceo}" validate="required; label:대표자명;" maxlength="10"/>
								</td>
							</tr>
						<tr>
							<th>연계여부</th>
							<td colspan="3">
								<select name="yn_use" class="selectpicker" validate="required; label:연계여부;">
									${ufn:makeOptions('yn_use','연계여부', fincorpInfo.yn_use)}
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
							<th>로   고</th>
							<td colspan="3">
								<c:if test="${!empty fincorpInfo.path_file1}">
									<img src="<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${fincorpInfo.path_file1}" alt="로고" />
									<a class="btn btn-xs circle" href="<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${fincorpInfo.path_file1}"><span class="glyphicon glyphicon-save"></span></a>
								</c:if>
								<br>
								<span id="file1Space"><input type="file" name="file1" class="file-contol" /></span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<!-- 기본정보 및 담당자 End -->
	</div>
</div>