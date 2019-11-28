package com.blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.Db;

/**
 * Servlet implementation class BlogInsert
 */
@WebServlet("/BlogInsert")
public class BlogInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//对用户体验的优化，返回回到上次删除的页面
//		String pageIndex = request.getParameter("pageIndex");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		String description = request.getParameter("description");
		String date = new Date().toLocaleString();
		
		String sql = "insert into t_blog (title,description,content,author) values('"+title+"','"+description+"','"+content+"','"+author+"','"+date+"')";
		boolean flag = Db.insertUser(sql);
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("BlogList/blogPublish.jsp").forward(request, response);
//		response.sendRedirect("BlogListServlet?pageIndex="+pageIndex);
//		response.sendRedirect("BlogListServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
