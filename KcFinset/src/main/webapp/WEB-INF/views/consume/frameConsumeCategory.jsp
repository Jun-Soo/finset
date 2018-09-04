<%@page import="com.koscom.util.JSTLFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	setClass();
	$(".class_modify").on("click",function(){
		var typeList = $(this).parents(".ui-sortable-handle").find(".nm_type");
		for(var i=0;i<typeList.length; i++) {
			var tags = 
				"<li class='li'>"+
					"<input type='hidden' class='cd_type' value='"+$(typeList[i]).data("cd_type")+"'/>"+
					"<div class='ico_sort float_left ena'>=</div>"+
					"<span>"+$(typeList[i]).val()+"</span>"+
// 					"<span class='float_right modify'>수정</span>"+
					"<span class='float_right delete'>삭제</span>"+
				"</li>";
			$("#sortable_type").append(tags);
		}
		setType();
		$("#frmSaveCategory").append("<input name='nm_class' value='"+$(this).parents(".li").find(".nm_class").text()+"'/>");
		$("#frmSaveCategory").append("<input name='cd_class' value='"+$(this).parents(".li").find(".cd_class").val()+"'/>");
	});
});

var setClass = function(){
	//분류 화면 가리기
	$(".category_type").hide();
	//분류 화면에서 이벤트와 sortable 해제
	$(document).off("mouseover",".category_type .ena",typeEnable);
	$(document).off("mouseout",".category_type .ena",typeDisable);
	$(document).off("touchstart",".category_type .ena",typeEnable);
	$(document).off("touchend",".category_type .ena",typeDisable);
	try{
		$("#sortable_type").sortable("destroy");
	} catch (e) {
		if(!((e.message+"").indexOf("destroy")>-1)) {
			//처음 화면에서는 sortable_type 에 sortable 설정이 되어 있지 않기 때문에 에러가 발생하고, 이 경우를 제외하고 에러를 확인할 수 있도록 콘솔에 기록을 남김
			console.error(e.stack);
		}
	}
	//항목 화면 설정부분
	//y축으로만 이동하도록 설정
	$("#sortable_class").sortable({axis:"y"});
	//li태그 전체를 잡아서 움직일 수 있으므로 우선 disable
	$("#sortable_class").sortable("disable");
	//테스트용 마우스 이벤트 설정
	$(document).on("mouseover",".category_class .ena",classEnable);
	$(document).on("mouseout",".category_class .ena",classDisable);
	//실제 핸드폰에서 적용될  이벤트 설정
	$(document).on("touchstart",".category_class .ena",classEnable);
	$(document).on("touchend",".category_class .ena",classDisable);
	
	$("#ico-plus").data("yn_class",true);
	
	//항목 화면 보이기
	$(".category_class").show();
}

var setType = function(){
	//항목 화면 가리기
	$(".category_class").hide();
	//항목 화면에서 이벤트와 sortable 해제
	$(document).off("mouseover",".category_class .ena");
	$(document).off("mouseout",".category_class .ena");
	$(document).off("touchstart",".category_class .ena");
	$(document).off("touchend",".category_class .ena");
	$("#sortable_class").sortable("destroy");
	
	//분류 화면 설정부분
	//y축으로만 이동하도록 설정
	$("#sortable_type").sortable({axis:"y"});
	//li태그 전체를 잡아서 움직일 수 있으므로 우선 disable
	$("#sortable_type").sortable("disable");
	//테스트용 마우스 이벤트 설정
	$(document).on("mouseover",".category_type .ena",classEnable);
	$(document).on("mouseout",".category_type .ena",classDisable);
	//실제 핸드폰에서 적용될  이벤트 설정
	$(document).on("mouseover",".category_type .ena",classEnable);
	$(document).on("mouseout",".category_type .ena",classDisable);
	
	$("#ico-plus").data("yn_class",false);
	
	//항목 화면 보이기
	$(".category_type").show();
}
var classEnable = function(){$("#sortable_class").sortable("enable");}
var classDisable = function(){$("#sortable_class").sortable("disable");}
var typeEnable = function(){$("#sortable_type").sortable("enable");}
var typeDisable = function(){$("#sortable_type").sortable("disable");}

var categoryModal = function(){
	//modal 열기 - option은 modal 창 외부 클릭 방지
	$("#modal_save").modal();
}

var createCategory = function() {
	if($("#ico-plus").data("yn_class")) {
		$("#frmSaveCategory").append("<input name='nm_class' value='"+$("#name").val()+"'/>");
	} else {
		$("#frmSaveCategory").append("<input name='nm_type' value='"+$("#name").val()+"'/>");
	}
	
	$.ajax({
		url : "<c:url value='/m/consume/createCategory.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			toastMsg('변경사항이 저장되었습니다.');
		},
		error : function(e) {
			toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
		}
	});
}

</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>부채관리</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container-fluid category_class mt-3">
			<ul id="sortable_class">
				<c:forEach var="subList" items="${list}">
					<div>
					<c:forEach var="vo" items="${subList }" varStatus="myIndex">
						<c:if test="${myIndex.index eq 0}">
								<li class="li">
									<input type="hidden" class="cd_class" value="${vo.cd_class}"/>
									<div class="ico_sort float_left ena">=</div>
									<span class="nm_class">${vo.nm_class}</span>
									<span class="float_right class_modify">수정</span>
									<span class="float_right class_delete">삭제</span>
								</li>
						</c:if>
						<input type="hidden" data-cd_type="${vo.cd_type}" class="nm_type" value="${vo.nm_type}"/>
					</c:forEach>
					</div>
				</c:forEach>
			</ul>
		</div>
		<div class="container-fluid category_type mt-3 div_hidden">
			<ul id="sortable_type">
			</ul>
		</div>
		<div id="ico-plus-div">
			<button id="ico-plus" onclick="categoryModal();"></button>
		</div>
		<div id="modal_save" class="modal modal_save">
			<div class="div_form div_hidden">
				<form id="frmSaveCategory" name="frmSaveCategory"></form>
			</div>
			<div class="content_save">
				<div class="body_save">
				    <div class="text_save">카테고리 추가<br/><br/></div>
					<div class="form-inline">
						<div class="form-group">
							<label for="name">이름</label>
							<input type="text" class="form-control" id="name" autocomplete="off"/>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn_save">저장하기</button>
						</div>
					</div>
			    </div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>	
</div>
</body>
</html>
