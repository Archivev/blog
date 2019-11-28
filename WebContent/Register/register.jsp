<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Register/css/mystyle.css" />
	<link rel="short icon" href="<%=request.getContextPath()%>/Register/images/kodinger.jpg" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/Register/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/Register/js/myjavascript.js"></script>
</head>
<body class="body_bg">
<div class="whole">
		<div class="whole_title">
			Please Enter Your New Password
		</div>
		<div class="text_title">
			Your Account
		</div>
		<div class="text_input">
			<input type="text" class="input_style" id="userName"/>
		</div>
		<div class="blank_30"></div>
		<div class="text_title">
			New Password
		</div>
		<div class="text_input">
			<input type="password" class="input_style" id="password"/>
		</div>
		<div class="text_title">
			Confirm Your New Password
		</div>
		<div class="text_input">
			<input type="password" class="input_style" id="password2"/>
		</div>
		<div class="btn">
			<input type="submit" value="Finish" class="button_style" onclick="send()"/>
		</div>
		<div class="register">
			<a href="<%=request.getContextPath()%>/Login/login.jsp">已有账号？去登录</a>
		</div>
</div>
</body>
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	function send(){
		var pwd = $("#password").val();
		var pwd2 = $("#password2").val()	;
		if(pwd==pwd2){
			var data = {"username":$("#userName").val(),"password":$("#password").val()};
			$.ajax({
				url:"http://localhost:8080/blog/RegisterServlet",
				dataType:"json",
				async:true,
				data:data,
				type:"POST",
				success:function(req){
					alert(req.msg);
				},
				error:function(req){
					alert(req.msg);
				}
			});
		}else{
			alert("您两次输入的密码不一致！");
		}
	}
</script>
</html>