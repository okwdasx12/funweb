<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	/*
	JSP의 el에서 영역객체 이름 및 값을 찾는 순서
	${id} 이렇게 앞에 Scope 없는 경우 자동으로 찾음 
	applicationScope
		↑
	sessionScope
		↑
	requestScope
		↑
	pageScope
	*/

%>

<c:choose>
	<c:when test="${ requestScope.isIdDup }">
		<p>아이디 중복, 사용중인 ID입니다.</p>
	</c:when>
	<c:otherwise>
		<p>
			사용가능한 ID입니다.
			<input type="button" value="사용" onclick="result()">
		</p>
	</c:otherwise>
</c:choose>


<form action="/member/joinIdDupCheck" method="post" name="cfrm">
	<input type="text" name="id" value="${requestScope.id}"><%-- <-- 같음 --> <%=request.getAttribute("id")  --%>
	<input type="submit" value="중복확인">
</form>


<script>
	function result() {
		// 현재창(자식창)의 id값을 부모창(join.jsp)의 id 입력상자에 넣기
		opener.document.frm.id.value = cfrm.id.value;
		close(); // window.close(); // 현재창 닫기
	}

</script>
</body>
</html>






