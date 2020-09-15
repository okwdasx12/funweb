<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- head 영역  include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp"/>
</head>

<body>
<div id="wrap">
	<%-- header 영역  include top.jsp --%>
	<jsp:include page="/WEB-INF/views/include/top.jsp" />

	<div class="clear"></div>
	<div id="sub_img_center"></div>
	
	<div class="clear"></div>
	<jsp:include page="/WEB-INF/views/include/board_submenu.jsp"/>
	
	<article>
		
	<h1>공개 게시판 Content</h1>
		
	<table id="notice">
		<tr>
			<th scope="col" width="200">글번호</th>
			<td width="500" style="text-align: left;">${boardVo.num}</td>
		</tr>
		<tr>
			<th scope="col">조회수</th>
			<td style="text-align: left;">${boardVo.readcount}</td>
		</tr>
		<tr>
			<th scope="col" >작성자</th>
			<td style="text-align: left;">${boardVo.name}</td>
			</tr>
		<tr>
			<th scope="col" >작성일</th>
			<td style="text-align: left;">${boardVo.regDate}</td>
		</tr>
		<tr>
			<th scope="col" >글제목</th>
			<td style="text-align: left;">${boardVo.subject}</td>
		</tr>
		<tr>
			<th scope="col" >글내용</th>
			<td style="text-align: left;">${boardVo.content}</td>
		</tr>
	</table>

	<div id="table_search">
		<button type="button" onclick="location.href='/board/modify'">글수정</button>
		<button type="button" onclick="location.href='/board/delete?'">글삭제</button>
		<button type="button" onclick="location.href='/board/reply?reRef=${boardVo.reRef}&reLev=${boardVo.reLev}&reSeq=${boardVo.reSeq}&pageNum=${pageNum}'">답글쓰기</button>
		<button type="button" onclick="location.href='/board/list?pageNum=${pageNum}'">목록보기</button>
	</div>
	
	<div class="clear"></div>
	<div id="page_control">
	</div>
		
	</article>
    
	<div class="clear"></div>
	<%-- footer 영역  include bottom.jsp --%>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"/>
</div>

</body>
</html>   

