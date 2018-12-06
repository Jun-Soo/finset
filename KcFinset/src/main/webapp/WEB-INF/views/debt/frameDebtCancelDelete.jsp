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
	/* 	부채 삭제 관리 화면
		20180612 김휘경
		
		div나 여타 다른 태그에 click 이벤트는 2 번씩 타기 때문에 input태그에 class를 잡아와 click 이벤트를 주었다.
		input의 checked가 true 일 때 Y로 바꿔줘야 한다.
	*/
	$(document).ready(function() {
		//input 버튼 클릭 시에 작동할 함수 - 단 data 함수의 특성 상 HTML의 코드에는 변화가 보이지 않으니 주의!!!!!!!!!!!!!!
		$(".updateDebt").on("click",function(){
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
		$("#cancelDelete").on("click",function(){
			//form태그 초기화
			$("#frmDebtCancelDelete").html("");
			
			//각 대출정보를 감싸고 있는 div를 가져온다
			var divs = $(".ui-switch");
			for(var i=0; i< divs.length; i++) {
				//각 div별로 
				var outerDiv = $(divs[i]);
				//data-ischanged가 true라면 (변화가 있다면)
				if(outerDiv.data("ischanged")=="true") {
					//실제 action을 보낼 form 태그 안에 input 태그를 생성
					$("#frmDebtCancelDelete").append(getInputStr("no_manage_info",i,outerDiv.find(".no_manage_info").val()));
					$("#frmDebtCancelDelete").append(getInputStr("display_yn",i,outerDiv.find(".updateDebt").data("present")));
				}
			}
			var data = frmDebtCancelDelete.serialize();
			if(data.length==0) {
				toastMsg("수정할 사항이 없습니다");
				return;
			}
			$.ajax({
				url : "<c:url value='/m/debt/updateDebtDisplayList.json'/>",
				data : data,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : "GET",
				async : false,
				success : function(result) {
					switch(result.code){
					//성공
					case '00':
						toastMsg("수정이 완료되었습니다.</br>부채 관리 화면으로 이동합니다");
						//메세지를 확인하고 넘어가도록 시간 설정
						setTimeout(function(){
							//이전 화면으로
							location = "../debt/frameDebt.crz";
						},500);
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
		});
		if(userAgent == "Android") {
			window.Android.backKeySendUrl("/m/customercenter/frameCustomerMyInfo.crz");
		}
	});
	function goBack(){
		history.go(-2);
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
			<h1>부채 삭제 관리</h1>
		</div>
		<form name="frmDebtCancelDelete" id="frmDebtCancelDelete"></form>
	</header>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid">
			<div class="list-group">
				<c:forEach var="debtVO" items="${debtList}" varStatus="status">
					<div class="list-group-item">
						<div class="list-block">
							<li class="bank-title">
								<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${debtVO.cd_fc}');"></span>
								<span>${debtVO.nm_fc}</span>
								
                            </li>
                            <div>
                            	<span class="sub-header">개설일: ${ufn:formatDate(debtVO.ymd_loan)}</span>
                            </div>
                            <div>
                            	<span class="sub-header">약정금액: <c:out value="${ufn:formatNumberPattern(debtVO.amt_contract,'###,###.##')}"/> 만원</span>
                            </div>
                        </div>
						<div class="ui-switch" data-ischanged="false">
							<label data-form-control="toggle" class="pull-right">
								<input class="updateDebt" type="checkbox"
									<c:if test="${debtVO.display_yn eq 'Y' }">checked="checked"</c:if>
									data-origin="${debtVO.display_yn}"
									data-present="${debtVO.display_yn}"
									/>
								<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
							</label>
							<input type="hidden" class="no_manage_info" name="no_manage_info" value="${debtVO.no_manage_info }"/>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="btn-fixed-bottom affix-bottom" id="cancelDelete">
				<a role="button" class="btn btn-lg btn-block btn-disabled btn-primary"">확인</a>
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
