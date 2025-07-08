<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jstl 선언부 --%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>step4-jstl-forEach</title>
<style>
table {
	width: 100%;
	border-collapse: collapse; /* 테두리 겹치게 설정 */
}

th {
	background-color: rgb(72, 158, 158);
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}
</style>
</head>
<body>
	<%--
			jstl forEach : java or jsp 의 for 문에 해당함 
			items : 배열 또는 컬렉션 
			var : 요소를 담을 임시변수 
 --%>
	<h3>step4 jstl forEach</h3>
	<c:forEach items="${foods}" var="food">
	${food}<br>
	</c:forEach>
	<hr>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
		
			<tr>
				<td></td>
				<td></td>
			</tr>
			
		</tbody>
	</table>
</body>
</html>