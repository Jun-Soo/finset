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
<script type="text/javascript">
	$(document).ready(function() {
		//input 버튼 클릭 시에 작동할 함수 - 단 data 함수의 특성 상 HTML의 코드에는 변화가 보이지 않으니 주의!!!!!!!!!!!!!!
		$(".updateLink").on("click",function(){
			//표시로 바꿀 때
			if($(this).prop("checked")){
				//checked 로 바뀌었을 때 data-present를 Y로
				$(this).data("present",'Y');
			} else {
				//data-present를 N으로
				$(this).data("present","N");
			}
			//만약 data-origin과 data-present가 같다면 ui-switch클래스인 div에 data-ischanged 속성을 부여
			if($(this).data("origin")==$(this).data("present")) {
				$(this).parent().parent().data("ischanged","false");
			} else {
				$(this).parent().parent().data("ischanged","true");
			}
		});
		
		//확인 버튼 클릭 시
		$("#confirm").on("click",function(){
			//form태그 초기화
			$("#frmFcLinkList").html("");
		
			//각 금융사 연계 정보를 감싸고 있는 div를 가져온다
			var divs = $(".ui-switch");
			var cnt = 0;
			for(var i=0; i< divs.length; i++) {
				//각 div별로 
				var outerDiv = $(divs[i]);
				//data-ischanged가 true라면 (변화가 있다면)
				if(outerDiv.data("ischanged")=="true") {
					//실제 action을 보낼 form 태그 안에 input 태그를 생성
					$("#frmFcLinkList").append(getInputStr("NO_PERSON",cnt,outerDiv.find(".NO_PERSON").val()));
					$("#frmFcLinkList").append(getInputStr("CD_AGENCY",cnt,outerDiv.find(".CD_AGENCY").val()));
					$("#frmFcLinkList").append(getInputStr("CD_FC",cnt,outerDiv.find(".CD_FC").val()));
					$("#frmFcLinkList").append(getInputStr("CN",cnt,outerDiv.find(".CN").val()));
					$("#frmFcLinkList").append(getInputStr("TYPE_LOGIN",cnt,outerDiv.find(".TYPE_LOGIN").val()));
					$("#frmFcLinkList").append(getInputStr("YN_LINK",cnt,outerDiv.find(".updateLink").data("present")));
					cnt++;
				}
			}
			var data = frmFcLinkList.serialize();
			if(data.length > 0) {
				$.ajax({
					url : "<c:url value='/m/scrap/updateFcLinkInfoList.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function(result) {
						switch(result.code){
						//성공
						case '00':
							toastMsg("수정이 완료되었습니다.</br>메인 화면으로 이동합니다");
							//메세지를 확인하고 넘어가도록 시간 설정
							break;
						//데이터 부족으로 인한 실패
						case '99':
							toastMsg('필요한 정보가 없습니다.</br>관리자에게 연락 바랍니다.');
							break;
						default:
							break;
						}
					},
					error : function(e) {
						//db처리 중 에러발생
						toastMsg('처리 중 오류가 발생했습니다.</br>관리자에게 연락 바랍니다.');
					}
				});
			}
			setTimeout(function(){
				//이전 화면으로
				if(userAgent == "Android") {
					window.Android.closeWebView();
				}
			},500);
		});
	});
	function goBack(){
		if(userAgent == "Android") {
			window.Android.closeWebView();
		}
	}
	//각 no_manage_info와 display_yn에 맞는 input 태그를 만들 함수
	var getInputStr = function(name, idx, value) {
		return "<input type='hidden' name='list["+idx+"]."+name+"' value='"+value+"'/>";
	}
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>연계 금융사 관리</h1>
		</div>
		<form name="frmFcLinkList" id="frmFcLinkList"></form>
		<c:set var="type" value="시작" />
	</header>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid scrap-group">
			<div class="list-group ">
				<c:forEach var="linkedFcInfoVO" items="${linkedFcInfoList}" varStatus="status">
				<c:if test="${type ne  linkedFcInfoVO.NM_CODE}">
					<c:set var="type" value="${linkedFcInfoVO.NM_CODE}" />
					<h2 class="h2 block-container">${type}</h2>
					
				</c:if>
					
					<div class="list-group-item">
						<div class="list-block">
							<li class="bank-title">
								<span class="thumb-logo" style="background-image: url('/fincorp/getFinCorpIcon.crz?cd_fc=${linkedFcInfoVO.CD_FC}');"></span>
								<span>${linkedFcInfoVO.NM_FC}</span>
								
                            </li>
                        </div>
						<div class="ui-switch" data-ischanged="false">
							<label data-form-control="toggle" class="pull-right">
								<input class="updateLink" type="checkbox"
									<c:if test="${linkedFcInfoVO.YN_LINK eq 'Y' }">checked="checked"</c:if>
									data-origin="${linkedFcInfoVO.YN_LINK}"
									data-present="${linkedFcInfoVO.YN_LINK}"
									/>
								<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
							</label>
							<input type="hidden" class=NO_PERSON name="NO_PERSON" value="${linkedFcInfoVO.NO_PERSON }"/>
							<input type="hidden" class=CD_AGENCY name="CD_AGENCY" value="${linkedFcInfoVO.CD_AGENCY }"/>
							<input type="hidden" class=CD_FC name="CD_FC" value="${linkedFcInfoVO.CD_FC }"/>
							<input type="hidden" class=CN name="CN" value="${linkedFcInfoVO.CN }"/>
							<input type="hidden" class=TYPE_LOGIN name="TYPE_LOGIN" value="${linkedFcInfoVO.TYPE_LOGIN }"/>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="btn-fixed-bottom affix-bottom" id="confirm">
				<a role="button" class="btn btn-lg btn-block btn-disabled btn-primary"">확인</a>
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
