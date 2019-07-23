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
	  <form action="<%=request.getContextPath()%>/board/list">
		  <select name="contentsNum" class="float-right">
			<option value="1">1개씩</option>
			<option value="2">2개씩</option>
			<option value="3">3개씩</option>
		  </select>
		  <button class="float-right">버튼</button>
	  </form>
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
	
	<ul class="pagination" style="justify-content: center;">
	    <c:if test="${pageMaker.prev}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">Previous</a>
	        </li>
	    </c:if>
	    
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
	    	<c:if test="${index == pageMaker.criteria.page}">
		        <li class="page-item active">
		            <a class="page-link " href="<%=request.getContextPath()%>/board/list?page=${index}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">${index}</a>
		        </li>
		    </c:if>
		    <c:if test="${index != pageMaker.criteria.page}">
		        <li class="page-item">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">${index}</a>
		        </li>
		    </c:if>
	    </c:forEach>
	    
	    <c:if test="${pageMaker.next}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}&search=${pageMaker.criteria.search}&type=${pageMaker.criteria.type}">Next</a>
	        </li>
	    </c:if>
	</ul> 
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	    <div class="container">
	        <div class="col-12">
	            <form class="form-inline float-right"  style="display:inline-block">
	                <select class="form-control" id="type" name="type">
	                    <option value="1" <c:out value="${pageMaker.criteria.type==1?'selected':'' }"/>>선택</option>
	                	<option value="2" <c:out value="${pageMaker.criteria.type==2?'selected':'' }"/>>제목</option>
					   <option value="3" <c:out value="${pageMaker.criteria.type==3?'selected':'' }"/>>저자</option>
					   <option value="4" <c:out value="${pageMaker.criteria.type==4?'selected':'' }"/>>내용</option>
	  				</select>
	  				
					<input class="form-control mr-sm-2" type="text" placeholder="검색어를 입력하세요" name="search" value="${pageMaker.criteria.search}">
					<button class="btn btn-success" type="submit">검색</button>
				</form>
			</div>
		</div>
	</nav>
	
	
	
	<a href="<%= request.getContextPath()%>/board/enroll"><button type="button" class="btn btn-primary btn-block">등록</button></a>
	<br>
	<a href="<%= request.getContextPath()%>/logout"><button type="button" class="btn btn-primary btn-block">로그아웃</button></a>
	
	현재 로그인 정보 : ${user}
	
	











</body>
</html>