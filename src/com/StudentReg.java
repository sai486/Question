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
 * Servlet implementation class StudentReg
 */
@WebServlet("/StudentReg")
public class StudentReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pwd = request.getParameter("pwd");
		String rpwd = request.getParameter("rpwd");
		
		QuestionDao qd = new QuestionDao();
		if (pwd.equals(rpwd)) {
			try {
				Connection con = qd.getConnection();
				PreparedStatement ps3 = con.prepareStatement("select Count(*) from registered_students");
				ResultSet rs3 = ps3.executeQuery();
				String no;
				if (rs3.next()) {
					no = "2020OJA0" + (rs3.getInt(1) + 1);
				} else {
					no = "2020OJA01";
				}
				PreparedStatement ps1 = con.prepareStatement("insert into registered_students values(?,?,?,?)");
				ps1.setString(1, no);
				ps1.setString(2, name);
				ps1.setString(3, mail);
				ps1.setString(4, pwd);
				int x = ps1.executeUpdate();

				if (x != 0) {
					out.println("<body><h2 style=background-color:lavender;color:green><center>successfully Inserted ,<br> "
							+ " Your-Id : " + no + "</center></h2></body>");
					out.println("<a href='Login.html'><h3>Click Here to Login</h3></a>");
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			response.sendRedirect("wrong.html");
		}
		out.close();
	}

	

	

}
