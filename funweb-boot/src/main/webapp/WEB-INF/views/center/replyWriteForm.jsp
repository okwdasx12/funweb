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
		
	<h1>공개 게시판 답글작성</h1>
	
	<form action="/board/reply" method="post" name="frm">
	<input type="hidden" name="reRef" value="${boardVo.reRef}">
	<input type="hidden" name="reLev" value="${boardVo.reLev}">
	<input type="hidden" name="reSeq" value="${boardVo.reSeq}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<table id="notice">
		<tr>
			<th scope="col" width="200">이름</th>
			<td style="text-align: left; width: 500px;">
				<input type="text" name="name">
			</td>
		</tr>
		<tr>
			<th scope="col">비밀번호</th>
			<td style="text-align: left;">
				<input type="password" name="passwd">
			</td>
		</tr>
		<tr>
			<th scope="col">글제목</th>
			<td style="text-align: left;">
				<input type="text" name="subject" value="[RE:] ">
			</td>
		</tr>
		<tr>
			<th scope="col">글내용</th>
			<td style="text-align: left;">
				<textarea rows="13" cols="40" name="content"></textarea>
			</td>
		</tr>
	</table>

	<div id="table_search">
		<button type="submit">답글쓰기</button>
		<button type="reset">다시쓰기</button>
		<button type="button" onclick="location.href='/board/list?pageNum=${pageNum}'">목록보기</button>
	</div>
	</form>
	
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

