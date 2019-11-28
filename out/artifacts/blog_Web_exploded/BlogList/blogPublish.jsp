<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布博客</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/publish.css">
</head>
<body>
<div class="whole">
        <div class="top_bg">
            <div class="content">
                <div class="title">
                    <span>写博客</span>
                </div>
                <div class="main">
                    <div class="blog_box">
                        <div class="blog_title">
                           	<form action="<%=request.getContextPath() %>/BlogInsert" method="post">
							<input type="hidden" value="" name="id" id="id"/>
								博客标题：<input type="text" value="" name="title" id="title"/><br/>
								博客作者：<input type="text" value="" name="author" id="author"/><br/>
								博客简介：<input type="text" value="" name="description" id="description"/><br/>
								<%-- 博客内容：<br><textarea rows="30" cols="200" id="content" name="content">${blogs.content}</textarea><br> --%>
								<div id="div1" >
						        	<p></p>
						    	</div>
						    	<textarea id="text1" style="display:none;" name="content"></textarea>
								<input type="submit">
							</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/wangEditor.js"></script>
<!-- 富文本编辑器 -->
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
var flag = ${flag};
if(flag!=""){
	if(flag){
		alert("发布成功！");
		window.location.href="<%=request.getContextPath()%>/BlogListServlet";
	}else{
		alert("发布失败！");
	}
}
</script>
</html>