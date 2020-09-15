<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<%-- head 영역  include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp"/>
<style>
	span.delete-addfile, span.delete-oldfile {
		color: red;
		font-weight: bold;
		margin-left: 10px;
	}
</style>
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
		
	<h1>파일 게시판 글수정</h1>
	
	<form action="fileModifyPro.do" method="post" enctype="multipart/form-data" name="frm">
	<input type="hidden" name="num" value="${ boardVo.num }">
	<input type="hidden" name="pageNum" value="${ pageNum }">
	<table id="notice">
		<tr>
			<th scope="col" width="200">아이디</th>
			<td style="text-align: left; width: 500px;">
				<input type="text" name="name" value="${ sessionScope.id }" readonly>
			</td>
		</tr>
		<tr>
			<th scope="col">글제목</th>
			<td style="text-align: left;">
				<input type="text" name="subject" value="${ boardVo.subject }">
			</td>
		</tr>
		<tr>
			<th scope="col">파일</th>
			<td style="text-align: left;">
				<button type="button" id="btnAddFile">첨부파일 추가</button>
				<div id="fileBox">
					<div id="oldFileBox">
					
						<c:forEach var="attach" items="${ attachList }">
							<input type="hidden" name="oldfile" value="${ attach.uuid }">
							<div>
								${ attach.filename }
								<span class="delete-oldfile">X</span>
							</div>
						</c:forEach>
					
					</div>
				
					<div id="newFileBox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="col">글내용</th>
			<td style="text-align: left;">
				<textarea rows="13" cols="40" name="content">${ boardVo.content }</textarea>
			</td>
		</tr>
	</table>

	<div id="table_search">
		<button type="submit">파일글수정</button>
		<button type="reset">다시쓰기</button>
		<button type="button" onclick="location.href='fileNotice.do?pageNum=${ pageNum }'">목록보기</button>
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


<script src="/script/jquery-3.5.1.js"></script>
<script>
	var fileCount = ${ fn:length(attachList) };

	// 정적 이벤트 바인딩
	$('button#btnAddFile').click(function (event) {
		if (fileCount >= 5) {
			alert('첨부파일은 최대 5개까지만 가능합니다.');
			return;
		}
		
		var str = '<div><input type="file" name="filename"><span class="delete-addfile">X</span></div>';
		
		//$(this).next().find('div#newFileBox').append(str);
		$('div#newFileBox').append(str);
		fileCount++;
	});
	
	// 동적 이벤트 바인딩
	$('div#newFileBox').on('click', 'span.delete-addfile', function (event) {
		$(this).parent().remove(); // empty()는 안쪽요소만 비움
		fileCount--;
	});
	
	// 정적 이벤트 바인딩
	$('span.delete-oldfile').click(function () {
		$(this).parent().prev().prop('name', 'delfile');
		$(this).parent().remove();
		fileCount--;
	});
</script>
</body>
</html>   






