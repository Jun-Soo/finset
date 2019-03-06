* camel case 
	설명 : 첫번째 글자는 소문자로, 나머지 각 단어의 첫번째 글자를 대문자로 쓴다. 
               마치 낙타 등과 같은 표기법이라 하여 camel case 라고 불리움 (가독성이 더 좋음) 
	예시 : fontSize , showWindow
	
	

* Hungarian Notation
	설명 : Pascal Case 유형 앞에 변수의 타입 약어를 표기하여 쓴다	
	예시 : sName , oSS , sType



* JSP 명명 규칙
•	파일의 확장자는 jsp로 하고 첫 글자는 소문자로 시작한다.
•	JSP 이름이 복합된 단어의 경우   Camel 표기법을 따른다 (단, index.jsp 처럼 일부 파일은 예외로 한다.)
•	popup 을 위한 jsp 인 경우, pop 라는 prefix 를 붙여준다.


	(pop)[기능][의미있는비지니스명].jsp
 

* [기능]
 	list		목록
	form		폼( view + input )	
	view		뷰(readOnly)
	
	create		등록
	modify		수정
	delete		삭제
	save		저장(create + modify )
	
	proc		처리
	
	confirm		확인
	cancel		취소
	
	request		요청
	send		송신
	recv		수신
	transfer	송수신
	
	call		호출
	callback	콜백
	
	calc		계산

 