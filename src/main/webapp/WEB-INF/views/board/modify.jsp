<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>T게시물수정</title>
</head>
<body>
	<h1>게시물수정</h1>
	<form action="<%=request.getContextPath()%>/board/modify?num=${origin.num}" method=POST>
		<div class="container-fluid">
			<div class="form-group">
	  			<label >제목</label>
	  			<input type="text" class="form-control" name="title" value="${origin.title}">
			</div>
			<div class="form-group">
	  			<label >내용</label>
	  			<textarea rows="10"  class="form-control" name="contents" >${origin.contents}</textarea>
			</div>
			<div class="form-group">
	  			<label >작성자</label>
	  			<input type="text" class="form-control" name="writer" readonly value="${user.id}">
			</div>
			<div class="form-group">
	  			<label >작성일</label>
	  			<input type="text" class="form-control" name="registered" readonly value="${origin.registered}" > 
			</div>
			<div class="form-group">
	  			<label >조회수</label>
	  			<input type="text" class="form-control" name="views" value="0" readonly value="${origin.views}" > 				
			</div>
			<div class="form-group">
	  			<label >파일</label>
	  			<input type="text" class="form-control" name="file" value="${origin.file}" >
			</div>
		</div>
		<button class="btn btn-primary btn-block">확인</button>
	</form>
	현재 사용자 : ${user}
	<br>
	<br>
	


</body>
</html>