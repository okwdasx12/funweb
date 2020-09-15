<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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
		
	<h1>파일 게시판 [전체글갯수: ${ pageDto.totalCount }]</h1>
		
	<table id="notice">
		<tr>
			<th scope="col" class="tno">no.</th>
			<th scope="col" class="ttitle">title</th>
			<th scope="col" class="twrite">writer</th>
			<th scope="col" class="tdate">date</th>
			<th scope="col" class="tread">read</th>
		</tr>
		<c:choose>
			<c:when test="${ pageDto.totalCount gt 0 }"><%-- ${ not empty boardList } --%>
			
				<c:forEach var="board" items="${ boardList }">
					<tr onclick="location.href='/board/fileContent?num=${ board.num }&pageNum=${ pageNum }'">
						<td>${ board.num }</td>
						<td class="left">
							<c:if test="${ board.reLev gt 0 }">
								<img src="/images/center/level.gif" width="${ board.reLev * 10 }" height="13">
								<img src="/images/center/re.gif">
							</c:if>
							${ board.subject }
							<c:if test="${board.replycnt gt 0 }" >
								<b>[${board.replycnt}]</b>
							</c:if>
						</td>
						<td>${ board.name }</td>
						<td><javatime:format value="${ board.regDate }" pattern="yyyy.MM.dd"/></td>
						<td>${ board.readcount }</td>
					</tr>
				</c:forEach>
				
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">게시판 글없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

	<div id="table_search">
		<form action="/board/fileList" method="get">
		<select name="category">
			<option value="content" ${ pageDto.category eq 'content' ? 'selected' : '' }>글내용</option>
			<option value="subject" ${ pageDto.category eq 'subject' ? 'selected' : '' }>글제목</option>
			<option value="name" ${ pageDto.category eq 'name' ? 'selected' : '' }>작성자</option>
		</select>
		<input type="text" name="search" value="${ pageDto.search }" class="input_box"> 
		<input type="submit" value="검색" class="btn">
		</form>
		
		<c:if test="${ not empty id }"><%-- ${ not empty sessionScope.id } --%>
			<input type="button" value="글쓰기" class="btn" onclick="location.href='/board/fileWrite?pageNum=${ pageNum }'">
		</c:if>
	</div>
	
	<div class="clear"></div>
	<div id="page_control">
		<c:if test="${ pageDto.totalCount gt 0 }">
			<%-- [이전] --%>
			<c:if test="${ pageDto.startPage gt pageDto.pageBlock }">
				<a href="/board/fileList?pageNum=${ pageDto.startPage - pageDto.pageBlock }&category=${ pageDto.category }&search=${ pageDto.search }">[이전]</a>
			</c:if>
			
			<%-- 페이지블록 내에서의 시작페이지부터 끝페이지까지 번호출력 --%>
			<c:forEach var="i" begin="${ pageDto.startPage }" end="${ pageDto.endPage }" step="1">
				<a href="/board/fileList?pageNum=${ i }&category=${ pageDto.category }&search=${ pageDto.search }">
				<c:choose>
					<c:when test="${ i eq pageNum }">
						<span style="font-weight: bold; color: red;">[${ i }]</span>
					</c:when>
					<c:otherwise>
						[${ i }]
					</c:otherwise>
				</c:choose>
				</a>
			</c:forEach>
			
			<%-- [다음] --%>
			<c:if test="${ pageDto.endPage < pageDto.pageCount }">
				<a href="/board/fileList?pageNum=${ pageDto.startPage + pageDto.pageBlock }&category=${ pageDto.category }&search=${ pageDto.search }">[다음]</a>
			</c:if>
		</c:if>
	</div>
		
	</article>
    
	<div class="clear"></div>
	<%-- footer 영역  include bottom.jsp --%>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"/>
</div>

</body>
</html>   

