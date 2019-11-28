package com.blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.Delete;

/**
 * @description 用于删除博客
 * @author Archiver
 * @date 2019-8-18
 */
@WebServlet("/BlogDelete")
public class BlogDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		
		String sql = "delete from t_blog where id='"+bid+"'";
		boolean flag = Delete.DeleteBlog(sql);
		request.setAttribute("flag", flag);
//		request.getRequestDispatcher("BlogList/blogList.jsp").forward(request, response);
		response.sendRedirect("BlogListServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
