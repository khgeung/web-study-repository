<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL과 JSTL</title>
</head>
<body>
<h2>Model2 MVC 기반의 EL과 JSTL Study</h2>
<%-- 
		Model2 MVC 구조로 EL과 JSTL을 테스트 한다
 --%>
<a href="TestO1Servlet?id=java">EL Test1</a><br><br>
<a href="Test02Servlet?nick=아이유&age=33">JSTL if TEST2</a><br><br>
<a href="Test03Servlet?id=java">JSTL choose TEST3</a><br><br>
<a href="Test04Servlet?">JSTL forEach Test4</a><br><br>
<a href="Test05Servlet?address=종로">JSTL forEach Test5</a><br><br>
<%-- 
	index.jsp --- Controller 인 TestO5Servlet --- MemberDao
					address 를 사용자로부터
					받아와서 Model 연동 후 
					Member List 를 반환받는다
					반환받은 list 를 requset 영역에
					공유한다
					이후 forward로 step5-jstl-forEach-member-list jsp로
					제어를 이동하여 응답한다.
 --%>
</body>
</html>



