package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class LoginReg
 */
@WebServlet("/LoginReg")
public class LoginReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */ Connection con = null;
	public void init(ServletConfig config) throws ServletException {
		QuestionDao qd = new QuestionDao();
		con = qd.getConnection();

	}

	

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		try {

			PreparedStatement pst = con.prepareStatement("select * from registered_students where name=? && pass=?");
			pst.setString(1, uname);
			pst.setString(2, upass);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				pw.println("<h1 align=center>LoginSuccess</h1><a href='ExamSer'>Click here to take Exam</a>");
				
			} else {
				response.sendRedirect("./LoginPage");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	

}
