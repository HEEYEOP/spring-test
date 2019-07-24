<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>
	
	<title>비밀번호 찾기</title>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		$('#submit').click(function(){
			
			var id = $('input[name=id]').val();
			var email = $('input[name=email]').val();
			
			$.ajax({
				async:true,
				type:'POST',
				data:{'id':id,'email':email},
				url:"<%=request.getContextPath()%>/sendingPwCheck",
				dataType:"json", 
				contentType:"application/json; charset=UTF-8",
				success : function(checking){
				
				}
			
			})
			
			
			
		})
	})
	
	

	</script>

</head>
<body>
	<div class="offset-4 col-4 border border-dark mt-5">
					<h1 class="text-center">T비밀번호찾기</h1>
					<br>
					<form action="" method="">
						<div class="row">
							<label class="col-4">아이디</label>
							<input name="id" type="text"class="form-control col-7" placeholder="해당 아이디를 입력하세요">
						</div>
						<br>
						<div class="row">
							<label class="col-4">e-mail</label>
							<input name="email" type="email"class="form-control col-7" placeholder="해당 이메일을 입력하세요">
						</div>
						<br>
						<div class="offset-8 col-4 clearfix p-0">
							<button class="btn btn-primary float-right" id="submit">비밀번호찾기</button>
						</div>
					</form>
	</div>



</body>
</html>

