<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
	window.setupValidateForm( frmBoardInfo );
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
    var modify = { 
        success : function (result) {
        	alert("처리되었습니다");
			listBoardInfo(result);
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
        url: "<c:url value='/board/modifyBoardInfo.json'/>",
        type: "POST",
        clearForm: true,
        resetForm: true
    }; 
    
    var create = { 
        success : function (result) {
        	alert("처리되었습니다");
        	frmListBoardInfo.page.value = '1';
			listBoardInfo(result);
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
        url: "<c:url value='/board/createBoardInfo.json'/>",
        type: "POST",
        clearForm: true,
        resetForm: true
    }; 
 
    $("#modifyBoardInfo").click(function(){
    	if ( !frmBoardInfo.validateForm() ) return false;
    	
    	//첨부파일 사용한다면
    	if('${fn:contains(ufn:getBoardInfo(boardInfo.id_board, "OPTION"), "10")}' == "true"){
	    	if(!frmBoardInfo.file1.value){
	    		$("#file1Space").html("<input type=\"hidden\" name=\"dumy_file1\" />");
	    	}
	    	
	    	if(!frmBoardInfo.file2.value){
	    		$("#file2Space").html("<input type=\"hidden\" name=\"dumy_file2\" />");
	    	}
    	}
    	
    	if($("#id_board").val() == "event"){
    		if(!eventImgChk()) return false;
    	}
    
    	frmBoardInfo.content.value = CKEDITOR.instances.content.getData();
    	
		$("#frmBoardInfo").ajaxSubmit(modify); 
		return false; 
    }); 
    
    $("#createBoardInfo").click(function(){
    	if ( !frmBoardInfo.validateForm() ) return false;
    	
    	//첨부파일 사용한다면
    	if('${fn:contains(ufn:getBoardInfo(boardInfo.id_board, "OPTION"), "10")}' == "true"){
	    	if(!frmBoardInfo.file1.value){
	    		$("#file1Space").html("<input type=\"hidden\" name=\"dumy_file1\" />");
	    	}
	    	if(!frmBoardInfo.file2.value){
	    		$("#file2Space").html("<input type=\"hidden\" name=\"dumy_file2\" />");
	    	}
    	}
    	
    	if($("#id_board").val() == "event"){
    		if(!eventImgChk()) return false;
    	}
    	
    	frmBoardInfo.content.value = CKEDITOR.instances.content.getData();
    	
		//id_board값 셋팅
    	frmBoardInfo.id_board.value = $("#id_board").val();
    	
        $("#frmBoardInfo").ajaxSubmit(create);
        return false; 
    });
    
  //datepicker
	$('.input-daterange').datepicker({
		format: "yyyy-mm-dd", //dateformat
		todayHighlight: true, //당일 표시
		todayBtn: "linked", //today button link 활성화
		autoclose: true, //달력 자동 닫힘
		beforeShowDay : nationalDays
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	
	// 확장검색조건 보이기
	$("#btn-expand").click(function(){
		$(this).toggleClass("open");
		$("#divExpandSearch").toggle("blind", 200);
	});
	
});

//이벤트 게시판 - 썸네일 이미지, 본문이미지 validation 체크
function eventImgChk(){
	var maxSize = 0;
	var size = 0;
	var fileName = "";
	
	//썸네일 이미지 validation
	if(!frmBoardInfo.imgfiles1.value){
		alert("썸네일 이미지를 등록해주세요");
		return false;
	}else{
		//확장자 체크
		fileName = $("#imgfiles1").val();
		fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
		if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" ){
			alert("이미지 파일은 (jpg, png, gif) 형식만 등록 가능합니다.");
			$("#imgfiles1").val("");
			$('#imgfiles1').focus();
			return false;
		}
		
		//사이즈 체크
		maxSize  = 312 * 1024;    //312KB
		size = document.getElementById("imgfiles1").files[0].size;
		if(maxSize < size){
		    alert("썸네일 이미지 사이즈는 312KB 이내로 등록 가능합니다.");
		    $('#imgfiles1').val("");
		    $('#imgfiles1').focus();
		    return false;
		}
	}
	
	//본문 이미지 validation
	if(!frmBoardInfo.imgfiles2.value){
		alert("본문 이미지를 등록해주세요");
		return false;
	}else{
		//확장자 체크
		fileName = $("#imgfiles2").val();
		fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
		if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" ){
			alert("이미지 파일은 (jpg, png, gif) 형식만 등록 가능합니다.");
			$("#imgfiles2").val("");
			$('#imgfiles2').focus();
			return false;
		}
		
		//사이즈 체크
		maxSize  = 5 * 1024 * 1024;    //5MB
 	    size = document.getElementById("imgfiles2").files[0].size;
        if(maxSize < size){
           alert("본문 이미지 사이즈는 5MB 이내로 등록 가능합니다.");
           $('#imgfiles2').val("");
           $('#imgfiles2').focus();
           return false;
        }
	}	
	
	return true;
}

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">${ufn:getBoardInfo(boardInfo.id_board, 'NM')}</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmBoardInfo');">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty boardVO.id_board}">
					<button type="button" class="btn btn-default btn-xs" id="createBoardInfo">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 등록
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-default btn-xs" id="modifyBoardInfo">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 수정
					</button>
					<button type="button" class="btn btn-success btn-xs" onclick="viewBoardInfo('${boardVO.seq}');">
						<span class="glyphicon glyphicon glyphicon-arrow-left" aria-hidden="true"></span> 뒤로
					</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="btn btn-success btn-xs" onclick="listBoardInfo('${boardInfo.id_board}');">
				<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록
			</button>
		</span>
	</div>
	<form name="frmBoardInfo" id="frmBoardInfo" enctype="multipart/form-data" >
		<input type="hidden" name="seq" value="${boardVO.seq}">
		<input type="hidden" name="id_frt" value="${boardVO.id_frt}">
		<input type="hidden" name="id_board" value="${boardVO.id_board}">
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="10%">
					<col width="10%">
					<col width="25%">
					<col width="20%">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="4">
							<input type="text" class="form-control" name="title" value="${boardVO.title}" validate="required; label:제목; maxbt:200;" maxlength="200"/>
						</td>
						<!-- 
						<td>
							<select name="id_board" class="selectpicker" validate="required; label:게시판선택;">
								${ufn:makeBoardOptions("게시판선택", boardInfo.id_board )}
							</select>
						</td>
						 -->
					</tr>
					<!-- srchou -->
					<c:if test="${boardInfo.id_board eq 'event' }">
					<tr>
						<th>기간</th>
						<td colspan="2">
							<div class="input-daterange input-group date-w" id="datepicker">
				   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							    <input type="text" class="input-sm form-control" name="ymd_post_strt" id="ymd_post_strt" onclick="defaultSetting('sel_dt_kind', 'dt_frt')" validate="date; required; label:시작일자;" maxlength="8" value="${boardVO.ymd_post_strt}"/>
							    <span class="input-group-addon">~</span>
							    <input type="text" class="input-sm form-control" name="ymd_post_end" id="ymd_post_end" onclick="defaultSetting('sel_dt_kind', 'dt_frt')" validate="date; required; label:종료일자;" maxlength="8" value="${boardVO.ymd_post_end}"/>
				   			</div>
				   		</td>
						<th >진행여부</th>
						<td colspan="2">
							<select id="cd_event_proc" name="cd_event_proc" class="selectpicker" validate="required; label:진행여부;">
								${ufn:makeOptions("cd_event_proc","진행여부", boardVO.cd_event_proc)}
							</select>
						</td>
					</tr>
					</c:if>
					<!-- srchou -->
					<c:if test="${fn:contains(ufn:getBoardInfo(boardInfo.id_board, 'OPTION'), '10')}">
					<tr>
						<th>첨부1</th>
						<td colspan="2">
							<c:if test="${!empty boardVO.url_files1}">
								<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${boardVO.url_files1}"><span class="glyphicon glyphicon-save"></span></a>
							</c:if>
							<span id="file1Space"><input type="file" name="file1" class="file-contol" /></span>
						</td>
						<th>첨부2</th>
						<td colspan="2">
							<c:if test="${!empty boardVO.url_files2}">
								<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${boardVO.url_files2}"><span class="glyphicon glyphicon-save"></span></a>
							</c:if>
							<span id="file2Space"><input type="file" name="file2" class="file-contol" /></span>
						</td>
					</tr>
					</c:if>
					<c:if test="${boardInfo.id_board eq 'event' }">
					<tr>
						<th>썸네일 첨부</th>
						<td colspan="2">
							<c:if test="${not empty boardInfo.seq}">
								<img src="/board/getBoardImg.crz?id_board=${boardInfo.id_board}&seq=${boardInfo.seq}&file_type=01" height="70px" width="52px"><br/>
							</c:if>
							<span id="img_files1Space"><input type="file" id="imgfiles1" name="imgfiles1" class="file-contol" /></span>
						</td>
						<th>본문 첨부</th>
						<td colspan="2">
							<c:if test="${not empty boardInfo.seq}">
								<img src="/board/getBoardImg.crz?id_board=${boardInfo.id_board}&seq=${boardInfo.seq}&file_type=02" height="100px" width="100px"><br/>
							</c:if>
							<span id="img_files2Space"><input type="file" id="imgfiles2" name="imgfiles2" class="file-contol" /></span>
						</td>
					</tr>
					</c:if>
					<tr>
						<td colspan="6" >
							<textarea name="content" id="content">${boardVO.content}</textarea>
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
						<th>팝업여부</th>
						<td colspan="5">
							${ufn:makeRadioAndCheckBoxs("yn_use", "yn_popup", "radio", boardVO.yn_popup, 0)}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>