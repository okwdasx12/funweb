<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- head 영역  include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp"/>
<style>
	span.fileDelete{
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
		
	<h1>공개 게시판 글작성</h1>
	<form action="/board/fileWrite" method="post" enctype="multipart/form-data" name="frm">	 
	<table id="notice">
		<tr>
			<th scope="col" width="200">아이디</th>
			<td width="500" style="text-align: left;">
				<input type="text" name="name" value="${id}" readonly>
			</td>
		</tr>
		<tr>
			<th scope="col" >글제목</th>
			<td colspan="3" style="text-align: left;">
				<input type="text" name="subject">
			</td>
		</tr>
		<tr>
			<th scope="col">파일</th>
			<td style="text-align: left;">
			<button type="button" id="btnAddFile">첨부파일 추가</button>
				<div  id="fileBox">
					<div>
						<input type="file" name="filename">
						<span class="fileDelete">X</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="col" >글내용</th>
			<td colspan="3" style="text-align: left;">
				<textarea rows="13" cols="40" name="content"></textarea>
			</td>
		</tr>
	</table>
	
	<div id="table_search">
		<button type="submit">파일글쓰기</button>
		<button type="reset">다시쓰기</button>
		<button type="button" onclick="location.href='/board/fileList?pageNum=${pageNum}'">목록보기</button>
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
	//정적 이벤트 바인딩
	var fileCount = 1;
	$('#btnAddFile').click(function(event) {
		if(fileCount>=5){
			alert('첨부파일은 최대 5개까지만 가능합니다.');
			return;
		}
		var str = '<div><input type="file" name="filename"><span class="fileDelete">X</span></div>';
		
// 		$(this).next().append(str);
		$('div#fileBox').append(str);
		fileCount++;
	})
	
	// 동적 이벤트 바인딩
	$('div#fileBox').on('click', 'span.fileDelete', function (event) {
		$(this).parent().remove(); // empty()는 안쪽요소만 비움
		fileCount--;
	});
</script>
</body>
</html>   

