<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>회원정보 수정 페이지</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  
	function checkSame(sel1, sel2){
		var val1 = $(sel1).val();
		var val2 = $(sel2).val();
		if(val1 == val2)
			return true;
		return false;
	}
	function checkLength(sel,min,max){
		var val = $(sel).val();	
		if(val.length >= min && val.length <= max )
			return true;
		return false;
	}
		
	$(document).ready(function(){
		
		$('#submit').click(function(){
			
			if(!checkLength('#pw',8,13)){
				alert('비밀번호는 8~13자리입니다.')
				return false;
			}
			if($('#email').val().length == 0){
				alert('이메일을 입력해주세요')
				return false;
			}
			
			alert('회원정보를 수정을 요청했습니다'); 
			return true;
		})
		if(${success ne null}){
			if(${success eq true}){
				alert('수정이 완료되었습니다');
			}else{
				alert('기존정보를 잘못입력하였습니다');
			}
		}
			
		$('body').keydown(function(e){
			if(e.which == 116 ){
				location.href= "<%=request.getContextPath()%>/memberModify";
			}
		})
		
	})
  
  </script>
</head>
<body>

	<div class="container">
	  <h2>회원정보 수정</h2>
	  <p>개인정보를 수정할 수 있습니다</p>
	  <form action="<%=request.getContextPath()%>/memberModify" method="post" name="form">
	  	<div class="form-group">
	      <label for="id">ID:</label>
	      <input type="text" class="form-control" id="id" name="id" value="${user.id}"readonly>
	    </div>
	    <div class="form-group">
	      <label for="usr">Name:</label>
	      <input type="text" class="form-control" id="name" name="name" value="${user.name}">
	    </div>
	    <div class="form-group">
	      <label for="pwd">Password:</label>
	      <input type="password" class="form-control" id="pw" name="pw" placeholder="새 비밀번호를 입력해주세요">
	    </div>
	    <div class="form-group">
		    <label for="email">E-mail:</label> 
		    <div class="input-group mb-3">
			    <input type="text" class="form-control" id="email" name="email" value="${user.email}">
			    <div class="input-group-append">
			    	<select>
			    		<option>
			    			<span class="input-group-text">@google.com</span>
			    		</option>
			    		<option>
			    			<span class="input-group-text">@naver.com</span>
			    		</option>
			    		<option>
			    			<span class="input-group-text">@daum.com</span>
			    		</option>
			    	</select>
			    </div>
			  </div>
		  </div>
		 
			<div class="form-check">
				<label class="form-check-label">
					<input type="checkbox" class="form-check-input" id="genderM" name="gender" value="M" <c:if test="${user.gender eq 'M'}">checked</c:if>>남성
				</label>
			</div>
		
			<div class="form-check">
				<label class="form-check-label">
				   <input type="checkbox" class="form-check-input" id="genderW" name="gender" value="F" <c:if test="${user.gender eq 'F'}">checked</c:if>>여성
				 </label>
			</div>
			
	    
	    <button type="submit" class="btn btn-primary float-right" id="submit">Submit</button>
	    <br>
	    <br>
	    로그인정보 : ${user}
	    ${t}
	  
	  </form>
	</div>
</body>
</html>




<div>
				<input type="hidden" value="${t}" id="t">
			</div>