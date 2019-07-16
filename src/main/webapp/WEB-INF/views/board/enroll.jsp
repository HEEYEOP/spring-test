<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>T새게시물등록</title>
</head>
<body>
	<h1>T새 게시물 등록</h1>
	<form action="<%=request.getContextPath()%>/board/enroll" method=POST>
		<div class="container-fluid">
			<div class="form-group">
	  			<label >제목</label>
	  			<input type="text" class="form-control" name="title" >
			</div>
			<div class="form-group">
	  			<label >내용</label>
	  			<textarea rows="10"  class="form-control" name="contents" ></textarea>
			</div>
			<div class="form-group">
	  			<label >작성자</label>
	  			<input type="text" class="form-control" name="writer" readonly value="${user.id}">
			</div>
			<div class="form-group">
	  			<label >작성일</label>
	  			<input type="text" class="form-control" name="registered" readonly > 
			</div>
			<div class="form-group">
	  			<label >조회수</label>
	  			<input type="text" class="form-control" name="views" value="0" readonly >
	  					  															<!-- 언제 name을 지정해줘야하는지 해주지 않아야하는지, value는 어떨때 지정해주는지 잘 생각하기 -->
	  				
			</div>
			<div class="form-group">
	  			<label >파일</label>
	  			<input type="text" class="form-control" name="file" >
			</div>
		</div>
		<button class="btn btn-primary btn-block">확인</button>
	</form>
	현재 사용자 : ${user}
	<br>
	<br>
	


</body>
</html>