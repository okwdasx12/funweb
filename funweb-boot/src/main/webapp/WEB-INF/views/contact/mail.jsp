<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 발송</h1>
	<hr>
	
	<form action="/mail" method="post">
		<input type="text" name="address" placeholder="받는사람 이메일주소"><br>
		<input type="text" name="title" placeholder="메일 제목"><br>
		<textarea rows="20" cols="60" name="message" placeholder="메일내용"></textarea>
		<button type="submit">메일 발송</button>
		
	</form>
</body>
</html>