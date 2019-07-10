<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style>
	tr,th,td{
		border: 1px solid black;
	
	}

</style>
</head>
<body>

	<table>
		<tr>
			<th width="5%">게시번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="15%">작성일</th>
			<th width="10%">첨부파일</th>
			<th width="5%">조회수</th>
		</tr>
	
		
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
	</table>
	











</body>
</html>