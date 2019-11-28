<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>博客修改</title>
</head>
<body>
<h1>修改博客</h1>
	<form action="<%=request.getContextPath() %>/BlogUpdate" method="post">
	<input type="hidden" value="${blogs.id}" name="id" id="id"/>
		博客标题：<input type="text" value="${blogs.title}" name="title" id="title"/><br/>
		博客作者：<input type="text" value="${blogs.author}" name="author" id="author"/><br/>
<%-- 		博客内容：<br><textarea rows="30" cols="200" id="content" name="content">${blogs.content}</textarea><br> --%>
		<div id="div1" >
        	<p>${blogs.content}</p>
    	</div>
    	<textarea id="text1" style="display:none;" name="content"></textarea>
		<input type="submit">
	</form>
	<button onclick="reSet()">重置</button>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/wangEditor.js"></script>
<script type="text/javascript">
      var E = window.wangEditor;
      var editor = new E('#div1');
      var $text1 = $('#text1');
      editor.customConfig.onchange = function (html) {
          // 监控变化，同步更新到 textarea
          $text1.val(html);
      }
      editor.create()
      // 初始化 textarea 的值
      $text1.val(editor.txt.html())
  </script>
<script type="text/javascript">
var title = $("#title");
var author = $("#author");
var content = $("#content");

var reSet = function(){
	title.val(  "${blogs.title}");
	author.val("${blogs.author}");
	content.val("${blogs.content}") ;
}

var code = ${code};
if(code!=""){
	if(code==200){
		alert("修改成功！");
		window.location.href="<%=request.getContextPath()%>/BlogListServlet";
	}else{
		alert("修改失败！");
	}
}
</script>
</html>