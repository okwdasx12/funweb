<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%-- head 영역 head.jsp--%>
<jsp:include page="/WEB-INF/views/include/head.jsp"/>


<body>
<div id="wrap">
	<%-- include top.jsp --%>
	<jsp:include page="/WEB-INF/views/include/top.jsp"/>
	
	<div class="clear"></div>
	<div id="sub_img"></div>
	
	<div class="clear"></div>
	<nav id="sub_menu">
        <ul>
            <li> <a href="/company/welcome">Welcome</a></li>
            <li> <a href="/company/history">History</a></li>
            <li> <a href="#">Newsroom</a></li>
            <li> <a href="#">Public Policy</a></li>
    	</ul>
    </nav>
	
	<article>
		<h1>History</h1>
		<div class="y2011"> 
			<h3> 2011 </h3>
			<dl>
				<dt>may</dt>
				<dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl>  
				<dt>apr</dt>
				<dd>Cras felis lectus, gravida ac tincidunt eget</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl class="dot_none">   
				<dt>jan</dt>
				<dd>Fusce scelerisque dictum magna eget viverra.</dd>
			</dl>
			<div class="clear"></div>
		</div>
		
		<div class="y2010"> 
			<h3> 2010 </h3>
			<dl>
				<dt>dec</dt>
				<dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl>
				<dt>nov</dt>
				<dd>Cras felis lectus, gravida ac tincidunt eget</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl class="dot_none">   
				<dt>jan</dt>
				<dd>Fusce scelerisque dictum magna eget viverra.</dd>
			</dl>
			<div class="clear"></div>
		</div>
		
		<div class="y2009"> 
			<h3> 2009 </h3>
			<dl>
				<dt>Jul</dt>
				<dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl>
				<dt>jun</dt>
				<dd>Cras felis lectus, gravida ac tincidunt eget</dd>
				<dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
				<dd>Donec mollis dapibus risus volutpat mattis. </dd>
			</dl>
			<dl class="dot_none">   
				<dt>jan</dt>
				<dd>Fusce scelerisque dictum magna eget viverra.</dd>
			</dl>
			<div class="clear"></div>
		</div>
	</article>
    
	<div class="clear"></div>
	<%--  footer영역 include bottom.jsp --%>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"/>
</div>

</body>
</html>   

