package com.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.Db;
import com.blog.dao.Update;
import com.blog.entity.Blog;

/**
 * @description 用于更新博客
 * @author Archiver
 * @date 2019-8-18
 */
@WebServlet("/BlogUpdate")
public class BlogUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		
		String sql = "select * from t_blog where id='"+bid+"'";
		List<Map<String,String>> data = Db.select(sql, new String[] {"title","author","content"});
		
//		List<Blog> list = new ArrayList<>();
		
		Blog blog = null;
		
		for(Map<String,String> map : data) {
			blog = new Blog();
			blog.setId(bid);
			blog.setTitle(map.get("title"));
			blog.setContent(map.get("content"));
			blog.setAuthor(map.get("author"));
//			list.add(blog);
		}
		request.setAttribute("blogs", blog);
		request.getRequestDispatcher("BlogList/blogMessage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		String bidStr = request.getParameter("id");
		int bid = Integer.parseInt(bidStr);
		
		String sqlUpdate = "update t_blog set title='"+title+"',author='"+author+"',content='"+content+"' where id='"+bid+"'";
		
		boolean flag = Update.UpdateBlog(sqlUpdate);
		
		int code=0;
		if(flag) {
			code = 200;
			//这个方法只能用ajax使用
//			response.getWriter().write(json);
			
		}else {
			code = 500;
		}
		request.setAttribute("code", code);
		request.getRequestDispatcher("BlogList/blogMessage.jsp").forward(request, response);
	}

}
