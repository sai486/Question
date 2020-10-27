package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.QuestionDao;

/**
 * Servlet implementation class Addquestion
 */
@WebServlet("/Addquestion")
public class Addquestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addquestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Question q = new Question();
		QuestionDao qd = new QuestionDao();
		String question = request.getParameter("question");
		String opt1 = request.getParameter("first");
		String opt2 = request.getParameter("second");
		String opt3 = request.getParameter("third");
		String opt4 = request.getParameter("forth");
		String ans = request.getParameter("answer");
//		System.out.println(question+opt1);
		q.setQuestion(question);
		q.setOpt1(opt1);
		q.setOpt2(opt2);
		q.setOpt3(opt3);
		q.setOpt4(opt4);
		System.out.println(q.getQuestion());
		if(ans.equals("1")){
			q.setAns(opt1);
		}
		else if(ans.equals("2")){
			q.setAns(opt2);
		}
		else if(ans.equals("3")){
			q.setAns(opt3);
		}
		else {
			q.setAns(opt4);
		}
		if(qd.addQuestion(q)){
			pw.println("Question is added");
		}else{
			pw.println("not added");
		}
	
		
	}

	

}
