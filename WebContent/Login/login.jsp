<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Login/css/main.css" />
	<link rel="short icon" href="<%=request.getContextPath()%>/Login/images/kodinger.jpg" />
</head>
<body >
<div class="whole">
        <div class="top">
            <div class="top_content">
                <h1>BLOG - 记录你的日常</h1>
            </div>
        </div>
        <div class="main">
            <div class="login_box">
                <div class="login_title">
                    <p>Login</p>
                </div>
                    <div class="login_body">
                        <div class="input_box">
                            <img src="<%=request.getContextPath()%>/Login/images/user.png" alt="">
                            <input type="text"  id="userName">
                        </div>
                        <div class="input_box">
                            <img src="<%=request.getContextPath()%>/Login/images/password.png" alt="">
                            <input type="password" id="password">
                        </div>
                    </div>
                    <input type="submit" value="登录" class="submit" onclick="send()">
                    <a href="<%=request.getContextPath()%>/Register/register.jsp">没有账号？去注册</a>
            </div>
        </div>
        <div class="bottom">
<!--            powerd by - Archiver-->
        </div>
    </div>
<script type="text/javascript" src="<%=request.getContextPath()%>/Login/js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $(".login_box").animate({backgroundColor:'#fff',marginLeft:'320px'},1000);
    $(document).keyup(function (event) {
        if (event.keyCode==13){
            $(".submit").trigger("click");
        }
    })
});
	function send(){
		var user = $("#userName").val();
		var pwd = $("#password").val();
		var data = {"username":user,"password":pwd};
		if(user==null||user==""){
			alert("请输入用户名！");
		}else if(pwd==null||pwd==""){
			alert("请输入密码！");
		}else{
			$.ajax({
				url:"<%=request.getContextPath() %>/LoginServlet",
				dataType:"json",
				async:true,
				data:data,
				type:"POST",
				success:function(req){
					if(req.code==200){
						alert(req.msg);
						window.location.href="<%=request.getContextPath() %>/BlogListServlet";
					}else{
						alert(req.msg);
					}
				},
				error:function(req){
					alert(req.msg);
				}
			});
		}
	}
</script>
</body>
</html>