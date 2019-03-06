<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function goPostCode(in_cd_addr , cd_post , addr1 , addr2) {
	new daum.Postcode({
	    oncomplete: function(data) {
	    	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수
			var cd_addr = ''; //주소구분(도로명,지번)
            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;
                cd_addr = '01';
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            	cd_addr = '02';
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }
            
            document.getElementsByName(in_cd_addr)[0].value = cd_addr;
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementsByName(cd_post)[0].value = data.postcode1 + data.postcode2;
            document.getElementsByName(cd_post)[0].value = data.postcode1 + data.postcode2;
            document.getElementsByName(cd_post)[1].value = data.zonecode;
            document.getElementsByName(addr1)[0].value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementsByName(addr2)[0].focus();
            
	    }
	}).open();	
}
</script>