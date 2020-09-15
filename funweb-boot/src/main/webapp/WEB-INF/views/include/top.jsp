<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
// 	//로그인 상태유지 쿠키정보 가져오기
// 	Cookie[] cookies = request.getCookies();
// 	//name이 "id"인 쿠키객체 찾기
// 	if(cookies != null){
// 		for(Cookie cookie :cookies){
// 			if(cookie.getName().equals("id")){
// 				String id = cookie.getValue();

// 				//로그인 인증(세션에 id값 추가)
// 				session.setAttribute("id", id);
// 			}
// 		}
// 	}

%>
<%-- ${cookie.쿠키이름.value} --%>
<%-- <c:if test="${not empty cookie.id.value}"> --%>
<%-- 	<c:set var="id"  value="${cookie.id.value }"  scope="session"/> --%>
<%-- </c:if> --%>

<%
//세션값 가져오기
// String id = (String)session.getAttribute("id");

//세션값 있으면 ...님 반가워요~ loggout logout join 없어짐
//세션값 없으면 login join

%>  
	<header>
        <div id="login"> 
        	<c:choose>
        		<c:when test="${ not empty sessionScope.id }">
					${ sessionScope.id }님 반가워요~
        			<a href="/member/logout">로그아웃</a>
        		</c:when>
        		<c:otherwise>
        			<a href="/member/login">로그인</a> 
            		| <a href="/member/join">회원가입</a>
        		</c:otherwise>
        	</c:choose>
        	
        </div>
        <div class="clear"></div>
        <div id="logo">
        	<a href="/">
        		<img src="/images/logo.gif" width="265" height="62" alt="Fun Web">
        	</a>
        </div>
        <nav id="top_menu">
            <ul>
                <li><a href="/">HOME</a></li>
                <li><a href="/company/welcome">COMPANY</a></li>
                <li><a href="/chat/chat">CHATTING</a></li>
                <li><a href="/board/list">CUSTOMER CENTER</a></li>
                <li><a href="/mail">E-MAIL</a></li>
            </ul>
        </nav>
	</header>