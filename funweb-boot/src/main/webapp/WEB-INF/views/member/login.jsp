<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<%-- head 영역  include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp"/>
</head>

<body>
<div id="wrap">
	<%-- header영역 include top.jsp --%>
	<jsp:include page="/WEB-INF/views/include/top.jsp"/>
	
	<div class="clear"></div>
	<div id="sub_img_member"></div>
	
	<div class="clear"></div>
	<nav id="sub_menu">  
        <ul>
            <li> <a href="#">Join us</a></li>
            <li> <a href="#">Privacy policy</a></li>
    	</ul>
	</nav>
	
	<article>
		<h1>Login</h1>
		<form action="/member/login" method="post" id="join">
			<fieldset>
				<legend>Login Info</legend>
				<label>User ID</label>
				<input type="text" name="id"><br>
				<label>Password</label>
				<input type="password" name="passwd"><br>
				<input type ="checkbox" name ="keepLogin" value ="true" id ="keepLogin"> <label for="keepLogin">로그인 상태 유지</label>
			</fieldset>
			
			<div class="clear"></div>
			<div id="buttons">
				<input type="submit" value="로그인" class="submit">
				<input type="reset" value="초기화" class="cancel">
			</div>
		</form>
	</article>
	
	<div class="clear"></div>
	<%--  footer영역 include bottom.jsp --%>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"/>
</div>

</body>
</html>   

