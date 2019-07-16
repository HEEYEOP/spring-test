<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>게시판(list)</title>
<style>
 table{
	background-color: #888;
 	
 }

</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/topbar.jsp"></jsp:include>
	
	

	  <h2>T게시판</h2>
	  <table class="table table-hover">
	    <thead>
	     	<tr>
				<th width="7%">게시번호</th>
				<th width="50%">제목</th>
				<th width="13%">작성자</th>
				<th width="15%">작성일</th>
				<th width="8%">첨부파일</th>
				<th width="5%">조회수</th>
			</tr>
	    </thead>
	    <tbody>
	      <c:forEach var="board"  items="${boardList}">
			<tr>
				<th>${board.getNum()}</th>
				<td><a href="/test/board/display?num=${board.getNum()}">${board.getTitle()}</a></td>
				<th>${board.getWriter()}</th>
				<th>${board.getRegistered()}</th>
				<th>${board.getFile()}</th>
				<th>${board.getViews()}</th>
			</tr>
		  </c:forEach>
	    </tbody>
	  </table>
	<br>
	<br>
	
	<a href="<%= request.getContextPath()%>/board/enroll"><button type="button" class="btn btn-primary btn-block">등록</button></a>
	<br>
	<a href="<%= request.getContextPath()%>/logout"><button type="button" class="btn btn-primary btn-block">로그아웃</button></a>
	
	현재 로그인 정보 : ${user}
	
	











</body>
</html>