package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QuestionDao;

/**
 * Servlet implementation class AdminSer
 */
@WebServlet("/AdminSer")
public class AdminSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    Connection con = null;
	public void init(ServletConfig config) throws ServletException {
		QuestionDao qd = new QuestionDao();
		con = qd.getConnection();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		System.out.println(uname+upass);
		try {

			PreparedStatement pst = con.prepareStatement("select * from admin where sname=? && spass=?");
			pst.setString(1, uname);
			pst.setString(2, upass);
			ResultSet res = pst.executeQuery();
			System.out.println("hello");
			if (res.next()) {
				System.out.println("hello");
				pw.println("<h1 align=center>LoginSuccess</h1><h2>If you want to add question </h2><a href='EnterQuestion.html'>Click here </a>");
				
			} else {
				response.sendRedirect("./LoginPage");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	

}
