<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>T게시물 등록</title>
</head>
<body>

	<form>
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
	  			<input type="text" class="form-control" name="views" readonly >
			</div>
			<div class="form-group">
	  			<label >파일</label>
	  			<input type="text" class="form-control" name="file" >
			</div>
		</div>
		
			
	</form>


</body>
</html>