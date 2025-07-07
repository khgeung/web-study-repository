<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.kosa.member.model.*"%>
 <% 
	MemberDao dao = new MemberDao();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model1 아키텍처</title>
</head>
<body>
<h2>Model 1 Architecture 를 적용한 총 회원수 보기</h2>
<h3>총 회원수: <%=dao.getAllMemberCount() %>명</h3>
</body>
</html>