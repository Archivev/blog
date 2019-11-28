package com.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.Db;
import com.blog.entity.Blog;

/**
 * @description 用于处理博客列表的博客呈现和分页
 * @author Archiver
 * @date 2019-8-15
 */
@WebServlet("/BlogListServlet")
public class BlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageIndexStr = request.getParameter("pageIndex");
		//如果为空则赋值为 1 
		if(pageIndexStr==null || pageIndexStr.equals("")) {
			pageIndexStr = "1";
		}
		//强制转换成 int 类型
		int pageIndex = Integer.parseInt(pageIndexStr);
		
		String sql = "select * from t_blog limit "+(pageIndex-1)*4+",4";
		String sqlCount = "select count(*) from t_blog";
		
		int count = Db.countAll(sqlCount);
		
		List<Map<String,String>> data = Db.select(sql,new String[] {"id","title","description","content","author","date"});
		
		List<Blog> list = new ArrayList<>();
		
		for(Map<String,String> map : data) {
			Blog blog = new Blog();
			blog.setId(Integer.parseInt(map.get("id")));
			blog.setTitle(map.get("title"));
			blog.setDescription(map.get("description"));
			blog.setContent(map.get("content"));
			blog.setAuthor(map.get("author"));
			blog.setDate(map.get("date"));
			list.add(blog);
		}
		request.setAttribute("blogs", list);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("BlogList/blogList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
