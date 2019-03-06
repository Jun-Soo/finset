<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/ufn.tld" prefix="ufn" %>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div id="cooconGoodsAPISetData" class="panel panel-default">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품 상세정보</a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<button type="button" class="btn btn-default btn-xs" id="duplcooconAPIinfo">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 중복 확인
			</button>
			<button type="button" class="btn btn-default btn-xs" id="createcooconAPIinfo">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
			</button>
			<button type="button" class="btn btn-default btn-xs" id="updcooconAPIinfo">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 수정
			</button>
			<button type="button" class="btn btn-warning btn-xs" id="delcooconAPIinfo">
				<span class="glyphicon" aria-hidden="true"></span> 삭제
			</button>
		</span>
	</div>
	
	<div class="panel-collapse toggle-cont">
			<div class="panel-default toggle-panel">
				<table class="table table-classic">
					<tbody>
						<tr>
							<th>조회구분</th>
							<td>
								<select id="gubun" name="gubun" class="selectpicker" value="">
									${ufn:makeOptions("cd_goods_l","선택", gubun)}
								</select>
							</td>
							<th>금융기관구분코드</th>
							<td>
								<select id="org_type" name="org_type" class="selectpicker">
									${ufn:makeOptions("CD_COOCON_ORGTYPE","금융기관구분", goodsbankInfo.org_type)}
								</select>
							</td> 
							<th>쿠콘은행코드</th>
							<td> 
								<input type="text" class="form-control width-200" id="cd_org" name="cd_org" value="" maxlength="10"/>
							</td>
							<th>금융사코드</th>
							<td>
								<%-- <select id="cd_fc" name="cd_fc" class="selectpicker" validate="label:업권코드;" value="">
									${ufn:makecooconOptions("선택", "", "")}
								</select> --%>
								<select id="cd_fc" name="cd_fc" class="selectpicker" value="">
									${ufn:makeFincorpOptions('금융사명', '', 'type', '')}
								</select>
							</td>
							<th>사용여부</th>
							<td>
								<select id="yn_use" name="yn_use" class="selectpicker" value="">
									${ufn:makeOptions("yn_use","선택", yn_use)}
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div>
</div>
<script>
function cooconAPIValid(){
	
	if($("#gubun").val() == ""){
		alert("조회구분코드를 선택해주세요.");
		$("#gubun").focus();
		return false;
	}
	if($("#org_type").val() == ""){
		alert("금융기관구분코드를 선택해주세요.");
		$("#org_type").focus();
		return false;
	}
	if($("#cd_org").val() == ""){
		alert("쿠콘은행코드를 입력해주세요.");
		$("#cd_org").focus();
		return false;
	}
	if($("#cd_fc").val() == ""){
		alert("금융사코드를 선택헤주세요.");
		$("#cd_fc").focus();
		return false;
	}
	if($("#yn_use").val() == ""){
		alert("사용여부를 선택헤주세요.");
		$("#yn_use").focus();
		return false;
	}
	
	return true;
}

$(document).ready(function(){
	    
    $("#createcooconAPIinfo").click( function(){

    	var cd_coocon_goods = $("#cd_coocon_goods").val();
    	var gubun = $("#gubun").val().substring(1);
    	var org_type = $("#org_type").val();
    	var cd_org = $("#cd_org").val();
    	var cd_fc = $("#cd_fc").val();
    	var yn_use = $("#yn_use").val();
    	
    	if ( !cooconAPIValid() ) return false;
    	
		var data = {"cd_coocon_goods":cd_coocon_goods,"gubun":gubun,"org_type":org_type,"cd_org":cd_org,"cd_fc":cd_fc,"yn_use":yn_use};
		
		 $.ajax({
			url : "<c:url value='/coocon/createcooconAPIinfo.crz'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData);
				
				initForm();
				goSearch();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
    }); 
    
    $("#updcooconAPIinfo").click( function(){

    	var cd_coocon_goods = $("#cd_coocon_goods").val();
    	var gubun = $("#gubun").val().substring(1);
    	var org_type = $("#org_type").val();
    	var cd_org = $("#cd_org").val();
    	var cd_fc = $("#cd_fc").val();
    	var yn_use = $("#yn_use").val();
    	
    	if ( !cooconAPIValid() ) return false;
    	
		var data = {"cd_coocon_goods":cd_coocon_goods,"gubun":gubun,"org_type":org_type,"cd_org":cd_org,"cd_fc":cd_fc,"yn_use":yn_use};
		
		 $.ajax({
			url : "<c:url value='/coocon/updcooconAPIinfo.crz'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData);
				
				initForm();
				goSearch();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
    }); 
    
    $("#delcooconAPIinfo").click( function(){

    	var gubun = $("#gubun").val().substring(1);
    	var cd_org = $("#cd_org").val();
    	var cd_fc = $("#cd_fc").val();
    	
		var data = {"gubun":gubun,"cd_org":cd_org,"cd_fc":cd_fc};
		
		 $.ajax({
			url : "<c:url value='/coocon/delcooconAPIinfo.crz'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData);
				
				initForm();
				goSearch();
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
    }); 
   
});

</script>
