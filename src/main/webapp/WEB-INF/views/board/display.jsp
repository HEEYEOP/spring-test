<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>T게시물 상세보기</title>
</head>
<body>

	
		<div class="container-fluid">
			<div class="form-group">
	  			<label >제목</label>
	  			<input type="text" class="form-control" name="title" value="${oneBoard.title}" readonly >
			</div>
			<div class="form-group">
	  			<label >내용</label>
	  			<textarea rows="10"  class="form-control" name="contents"  readonly >${oneBoard.contents}</textarea>
			</div>
			<div class="form-group">
	  			<label >작성자</label>
	  			<input type="text" class="form-control" name="writer" value="${oneBoard.writer }" readonly>
			</div>
			<div class="form-group">
	  			<label >작성일</label>
	  			<input type="text" class="form-control" name="registered" value="${oneBoard.registered }" readonly >
			</div>
			<div class="form-group">
	  			<label >조회수</label>
	  			<input type="text" class="form-control" name="views" value="${oneBoard.views }" readonly >
			</div>
			<div class="form-group">
	  			<label >파일</label>
	  			<input type="text" class="form-control" name="file" value="${oneBoard.file }" readonly >
			</div>
		</div>
		
		<c:if test="${user.id eq oneBoard.writer}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${oneBoard.num}"><button class="btn btn-primary btn-block">수정</button></a><br>
			<a href="<%=request.getContextPath()%>/board/delete?num=${oneBoard.num}"><button class="btn btn-primary btn-block">삭제</button></a><br>
			
		</c:if>
		
		<a href="<%= request.getContextPath()%>/board/list"><button type="button" class="btn btn-primary btn-block">목록</button></a>
		
		
		
		
		
	


</body>
</html>