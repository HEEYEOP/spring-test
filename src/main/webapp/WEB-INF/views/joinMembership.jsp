<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<html lang="en">

<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>
	
	

	<title>T회원가입 페이지</title>
	<style>
	*{
		margin: 0;
		padding : 0;
	}
	.main{
		margin-top:50px;
	}
	.row{
		margin: 5px 0px;
	}
	.fab.fa-amazon{
		font-size: 100px;
		color: red;
	}
	</style>
	
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
	
	var isCheck = -1; // -1: 중복체크를 해야하는 경우
	
	$(document).ready(function(){
		
		$('input[name=id]').change(function(){ //아이디 중복검사를 통해 회원가입이 가능한 아이디를 입력했더라도 이후에 아이디창을 통해 아이디 값이 변경되었을경우 isCheck = -1로 한다
			isCheck = -1;
		})
		
		
		$('#signup').submit(function(){
			if(!checkLength('#signup input[name=id]',8,13)){
				alert('아이디는 8~13자리입니다.');
				return false;
			}
			if(isCheck == -1){
				alert("중복체크를 해주세요")
				return false;
			}else if(isCheck == 1){
				alert("이미 가입된 아이디입니다. 다른 아이디를 입력해주세요")
				return false;
			}
			if(!checkLength('#signup input[name=pw]',8,13)){
				alert('비밀번호는 8~13자리입니다.')
				return false;
			}
			if(!checkSame('#signup input[name=pw]','#signup input[name=pwCheck]')){
				alert('비밀번호와 일치하지 않습니다.')
				return false;
			}
			if($('#signup input[type=email]').val().length == 0){
				alert('이메일을 입력해주세요')
				return false;
			}
			
			alert('회원가입에 성공했습니다.');
			return true;
		})
		
		//------------------------------------------------------
		
		$('#dup').click(function(){
			var id = $('input[name=id]').val();
			
			$.ajax({
				async:true,
				type:'POST',
				data:id,
				url:"<%=request.getContextPath()%>/dupCheck",
				dataType:"json", 
				contentType:"application/json; charset=UTF-8",
				success : function(checking){
					if(checking){
						alert("해당 아이디가 존재합니다");
						isCheck = 1; 			//1: 이미 회원이라 회원가입이 불가능한 경우
						
					}else{
						alert("아이디는 사용가능합니다");
						isCheck = 0; 			//0: 회원가입이 가능한 경우
					}
					
				}

			});
		})
		
	
		
		
	})
	
	
	
	</script>	
	



</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5">
			<h1 class="text-center">회원가입</h1>
			<form method="post" action="<%=request.getContextPath()%>/joinMembership" id="signup">
				<div class="row">
					<label class="col-4">아이디</label>
					<input name="id" type="text"class="form-control col-7" placeholder="아이디" >
				</div>
				<div>
					<button type="button" class="btn btn-outline-success offset-4 col-7" id="dup">중복 확인</button>
				</div>
				<div class="row">
					<label class="col-4">비밀번호</label>
					<input name="pw" type="password"class="form-control col-7" placeholder="비밀번호" >
				</div>
				<div class="row">
					<label class="col-4">비밀번호확인</label>
					<input name="pwCheck" type="password"class="form-control col-7" placeholder="비밀번호확인">
				</div>
				<div class="row">
					<label class="col-4">성별</label>
					<div class="col-8">
						<label class="form-check-label col-5">
							<input name="gender" type="radio" class="form-check-input" value="M" checked >남성
						</label>
						<label class="form-check-label">
							<input name="gender" type="radio" class="form-check-input" value="F" >여성
						</label>
					</div>
				</div>
				<div class="row">
					<label class="col-4">이메일</label>
					<input name="email" type="email"class="form-control col-7" placeholder="이메일">
				</div>
				<div class="row">
					<label class="col-4">이름</label>
					<input name="name" type="text"class="form-control col-7" placeholder="이름">
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right" id="submit">가입</button>
				</div>
			</form>
		</div>
	</div>

	

</body>

</html>
</html>
 