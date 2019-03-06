<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>기사등록</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
	window.setupValidateForm( frmNewsManageInfo );
	
    
});

//원문보기 팝업
function goNewsContentPopup() {
	var openWindow = window.open('${newsInfo.link}', "pop_newsContent", "width=1000, height="+(screen.availHeight-300)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

//저장
function saveNewsManage(){
	var frm = document.frmNewsManageInfo;
	var save = { 
	        url: "<c:url value='/contents/saveNewsManage.json'/>",
	        type: "POST",
	        clearForm: true,
	        resetForm: true,
	        success : function (result) {
	        	var returnData = result.returnData;
				alert(returnData.message);
				
				self.close();
				opener.goSearch(opener.document.getElementById("page").value);
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
	    }; 
	
	if(!frm.validateForm()) return false;
	if(!validImgfiles()) return false; //썸네일, 본문이미지 validation체크
	
	if(!frm.imgfiles1.value){ //썸네일 등록 안할 경우
		$("#thum_fileSpace").html("<input type=\"hidden\" name=\"dumy_thum_file\" />");
	}
	
// 	if(!frm.imgfiles2.value){ //본문 등록 안할 경우
// 		$("#body_fileSpace").html("<input type=\"hidden\" name=\"dumy_body_file\" />");
// 	}
	
	frmNewsManageInfo.contents.value = CKEDITOR.instances.contents.getData();
	
	$("#frmNewsManageInfo").ajaxSubmit(save);
	
}

//삭제
function delNewsManage(){
	var seq_news = $("#seq_news").val();
	
	$.ajax({
		url : "<c:url value='/contents/delNewsManage.json'/>",
		data : {"seq_news": seq_news },
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			self.close();
			opener.goSearch(opener.document.getElementById("page").value);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

//썸네일, 본문이미지 validation 체크
function validImgfiles(){
	var maxSize = 0;
	var size = 0;
	var fileName = "";
	
	//썸네일 이미지 validation
// 	if(!frmNewsManageInfo.imgfiles1.value){ //필수값 체크
// 		alert("썸네일 이미지를 등록해주세요");
// 		$('#imgfiles1').focus();
// 		return false;
// 	}else{
		//확장자 체크
		fileName = $("#imgfiles1").val();
		if(fileName != ""){
			fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
			if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" ){
				alert("이미지 파일은 (jpg, png, gif) 형식만 등록 가능합니다.");
				$("#imgfiles1").val("");
				$('#imgfiles1').focus();
				return false;
			}
			
			//사이즈 체크
			maxSize  = 1024 * 312;    //312KB
			size = document.getElementById("imgfiles1").files[0].size;
			if(maxSize < size){
			    alert("썸네일 이미지 사이즈는 312KB 이내로 등록 가능합니다.");
			    $('#imgfiles1').val("");
			    $('#imgfiles1').focus();
			    return false;
			}
		}
// 	}
	
// 		//본문 이미지 validation
// 		if(!frmNewsManageInfo.imgfiles2.value){ //필수값 체크
// 			alert("썸네일 이미지를 등록해주세요");
// 			$('#imgfiles2').focus();
// 			return false;
// 		}else{
// 		//확장자 체크
// 		fileName = $("#imgfiles2").val();
// 		if(fileName != ""){
// 			fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
// 			if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" ){
// 				alert("이미지 파일은 (jpg, png, gif) 형식만 등록 가능합니다.");
// 				$("#imgfiles2").val("");
// 				$('#imgfiles2').focus();
// 				return false;
// 			}
			
// 			//사이즈 체크
// 			maxSize  = 1024 * 1024 * 5;    //5MB
// 	 	    size = document.getElementById("imgfiles2").files[0].size;
// 	        if(maxSize < size){
// 	           alert("본문 이미지 사이즈는 5MB 이내로 등록 가능합니다.");
// 	           $('#imgfiles2').val("");
// 	           $('#imgfiles2').focus();
// 	           return false;
// 	        }
// 		}
// 	}
	
	return true;
}

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<body>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			기사등록
		</h3>
	</div>
	<div class="panel-heading">
		<span class="pull-left">
			<button type="button" class="btn btn-default btn-xs" id="popNewsContent" onclick="goNewsContentPopup();">
				<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> 원문보기
			</button>
		</span>
		<span class="pull-right">
			<button type="button" class="btn btn-default btn-xs" id="saveNewsManageBtn" onclick="saveNewsManage();">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
			</button>
			<button type="button" class="btn btn-default btn-xs" id="delNewsManageBtn" onclick="delNewsManage();">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 삭제
			</button>
		</span>
	</div>
	<form name="frmNewsManageInfo" id="frmNewsManageInfo" enctype="multipart/form-data" >
		<input type="hidden" id="seq_news" name="seq_news" value="${newsInfo.seq_news}">
		<input type="hidden" id="seq_thum_file" name="seq_thum_file" value="${newsInfo.seq_thum_file}">
<%-- 		<input type="hidden" id="seq_body_file" name="seq_body_file" value="${newsInfo.seq_body_file}"> --%>
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" class="form-control" name="title" value="${newsInfo.title}" validate="required; label:제목; maxbt:200;" maxlength="200"/>
						</td>
					</tr>
					<tr>
						<th>썸네일이미지</th>
						<td colspan="3">
							<c:if test="${not empty newsInfo.seq_thum_file}">
								<img src="/contents/getApiNewsImg.crz?seq_news=${newsInfo.seq_news}&file_type=01" height="70px" width="52px"><br/>
							</c:if>
							<span id="thum_fileSpace">
								<input type="file" id="imgfiles1" name="imgfiles1" value="" />
							</span>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<th>본문이미지</th> -->
<!-- 						<td colspan="3"> -->
<%-- 							<c:if test="${not empty newsInfo.seq_body_file}"> --%>
<%-- 								<img src="/contents/getApiNewsImg.crz?seq_news=${newsInfo.seq_news}&file_type=02" height="70px" width="52px"><br/> --%>
<%-- 							</c:if> --%>
<!-- 							<span id="body_fileSpace"> -->
<!-- 								<input type="file" id="imgfiles2" name="imgfiles2" value="" /> -->
<!-- 							</span> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td colspan="4">
							<textarea name="contents" id="contents">${newsInfo.contents}</textarea>
							<script type="text/javascript">
								var seq_news = "${newsInfo.seq_news}";
								CKEDITOR.replace( 'contents' ,{
									language : 'ko',
									enterMode:'2',
									height : '250px',  // 입력창의 높이
									filebrowserUploadUrl: '${pageContext.request.contextPath}/contents/fileUpload.json?seq_news='+seq_news,
									toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Image", "Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
								});							
								
								CKEDITOR.on('dialogDefinition', function( ev ){
						            var dialogName = ev.data.name;
						            var dialogDefinition = ev.data.definition;
						          
						            switch (dialogName) {
						                case 'image': //Image Properties dialog
						                    //dialogDefinition.removeContents('info');
						                    dialogDefinition.removeContents('Link');
						                    dialogDefinition.removeContents('advanced');
						                    break;
						            }
						        });
							</script>
						</td>
					</tr>
					<tr>
						<th>상태</th>
						<td>
							<select id="news_status" name="news_status" class="selectpicker" validate="required; label:상태;">
								${ufn:makeOptions("news_status","상태", newsInfo.news_status)}
							</select>
						</td>
						<th>사용여부</th>
						<td>
							<select id="yn_use" name="yn_use" class="selectpicker" validate="required; label:사용여부;">
								${ufn:makeOptions("yn_use","사용여부", newsInfo.yn_use)}
							</select>
						</td>
					</tr>
					<tr>
						<th>뉴스원</th>
						<td colspan="3">
							<input type="text" class="form-control" name="news_company" value="${newsInfo.news_company}" validate="required; label:뉴스원; maxbt:100;" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<th>키워드</th>
						<td colspan="3">
							<input type="text" class="form-control" name="keyword" value="${newsInfo.keyword}" validate="label:키워드; maxbt:100;" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<th rowspan="2">연계</th>
						<td colspan="3">
							<input type="text" class="form-control" name="local_link_text" value="${newsInfo.local_link_text}" validate="label:연계버튼메세지; maxbt:100;" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="text" class="form-control" name="local_link" value="${newsInfo.local_link}" validate="label:연계링크; maxbt:100;" maxlength="100"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>