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
	<div id="sub_img_member"></div>

	<div class="clear"></div>
	<nav id="sub_menu">
        <ul>
            <li> <a href="#">Join us</a></li>
            <li> <a href="#">Privacy policy</a></li>
    	</ul>
    </nav>
	
	<article>
		
	<h1>Join Us</h1>
	<form id="join" action="/member/join" method="post" name="frm">    
	<fieldset>
		<legend>Basic Info</legend>
		<label>User ID</label> <input name="id" type="text" class="id"> 
		<input type="button" value="ID중복확인" class="dup" onclick="winopen()">
		<span id="idDupMessage"></span>
		<br>
		<label>Password</label> <input name="passwd" type="password" class="pass"><br>
		<label>Retype Password</label> <input name="passwd2" type="password" class="pass"><br>
		<label>Name</label> <input name="name" type="text" class="nick"><br>
		<label>E-Mail</label> <input name="email" type="email" class="email" ><br>
		<label>Retype E-mail</label> <input name="email2" type="email" class="email"><br>
	</fieldset>
	
	<fieldset>
		<legend>Optional</legend>
		<label>Address</label> <input name="address" type="text" class="address"><br>
		<label>Phone Number</label> <input name="tel" type="tel" class="phone"><br>
		<label>Mobile Phone Number</label> <input name="mtel" type="tel" class="mobile"><br>
	</fieldset>

	<div class="clear"></div>
	<div id="buttons">
		<input type="submit" value="회원가입" class="submit"> <input type="reset" value="새로작성" class="cancel">
	</div>
	</form> 
	
	</article>
	
	<div class="clear"></div>
	<%-- footer 영역  include bottom.jsp --%>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"/>
</div>
<script src="/script/jquery-3.5.1.js"></script>
<script>
	$('input[name="id"]').keyup(function(event) {
		var id = $(this).val();
		
		$.ajax({
			url:'/member/ajaxJoinIdDupCheck', 
			data: {id:id},
//	 		: 뒤에 id가 변수 id값이 들어오는 곳
			success: function(data){
				console.log(data.isIdDup);
				console.log(data.name);
				console.log(data.age);
				
				showIdDupMessage(data);
			}

		});
	});
	
	function showIdDupMessage(data){
		if(data.isIdDup){
			$('span#idDupMessage').html('이미 사용중인 아이디 입니다.').css('color', 'red');
		} else{
			$('span#idDupMessage').html('사용 가능한 아이디 입니다.').css('color', 'green');;
		}
	}
	
	function winopen() {
		var id = document.frm.id.value;
		console.log('id = ' + id);
		
		if (id == '') {
			alert('아이디를 입력하세요');
			document.frm.id.focus();
			return;
		}
		
		// 새창 열어서 jsp요청   window.open()
		open('/member/joinIdDupCheck?id=' + id, 'dupCheck', 'width=400,height=200');
	}
</script>
</body>
</html>   








