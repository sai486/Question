package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.QuestionDao;

/**
 * Servlet implementation class ExamSer
 */
@WebServlet("/ExamSer")
public class ExamSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamSer() {
        super();
        // TODO Auto-generated constructor stub
    }

	 private Map<Integer, String> map = new HashMap<>();
//	 private Map<String, String> map = new HashMap<>();
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		QuestionDao Qd = new QuestionDao();
		ArrayList<Question> q = Qd.getQuestion();
		pw.println("<body>");
		pw.println("<form action='ExamSer' method='post'><h1 style = background:green;color:white;>Please Start you are exam</h1><table border = 1 align = center cellpadding=20px bgcolor=wheat>");
		int index = 1;
		for (Question quest : q) {
			
			pw.println("<tr><td colspan = 2><h2>Question no " +quest.getQno() + "<br>"+quest.getQuestion() + "</h2></td></tr>");
			pw.println("<tr><td align= center><h3><input type='radio' name='que" + index + "' value='"+quest.getOpt1()+"'>" + quest.getOpt1() + "</h3></td>");
			pw.println("<td align= center><h3><input type='radio' name='que" + index + "' value='"+quest.getOpt2()+"'>" + quest.getOpt2() + "</h3></td></tr>");
			pw.println("<tr align= center><td><h3><input type='radio' name='que" + index + "' value='"+quest.getOpt3()+"'>" + quest.getOpt3() + "</h3></td>");
			pw.println("<td align= center><h3><input type='radio' name='que" + index + "' value='"+quest.getOpt4()+"'>" + quest.getOpt4() + "</h3></td></tr>");
			index++;
			map.put(Integer.parseInt(quest.getQno()), quest.getAns());
//			map.put(quest.getQno(), quest.getAns());

		}
		for(int i = 1;i<= Qd.getCount();i++){
			System.out.println(map.get(i));
			
		}
		
		
//		for (Map.Entry<String,String> m : map.entrySet()){
//			System.out.println(m.getKey()+" "+ m.getValue());
//		}
	

		pw.println("<tr><td colspan = 2 align = center><input type='submit' value='submit' style= background:blue;color:white;> </td></tr></table></form></body>");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		QuestionDao qd = new QuestionDao();
		int score =0;
		int questionCount = qd.getCount();
//		System.out.println(questionCount);
//		System.out.println("***************************************************");
//		for (int i = 1; i <= questionCount ; i++) {
//			String s=request.getParameter("que"+i);
//			for (Map.Entry<String, String> m : map.entrySet()){
//				String ans = m.getValue();
//				
//				if(s!=null && s.equals(ans)){
//					score++;
//					System.out.println(s+" "+ans);
//				}
//				
//			}
//		
//		}
		for(int i = 1;i<= questionCount;i++){
			String s=request.getParameter("que"+i);
//			System.out.println(map.get(i));
			if(s!=null && map.get(i).equals(s)){
				score++;
			}
		}
		
		
		
		
//		System.out.println(score);
		
		if(score > questionCount/2){
		out.println("<center><h1 style=background-color:wheat;color:blue;align='center'>Your Final Score : " + score + "/"+ questionCount+"</h1></center>");
		out.println("<h2 align='center'>Congragulation you are eligible for this course </h2>");
		}
		else {
			out.println("<center><h1 style=background-color:wheat;color:blue;align='center'>Your Final Score : " + score + "/"+ questionCount+"</h1></center>");
			out.println("<h2 align='center'>Sorry you are score is below 50%. <br>So,you need to study well with the help of some links provided below</h2>");
			out.println("<h2 align = center><a href =https://www.javatpoint.com/>JavatPoint</a>  </h2>");
			out.println("<h2 align = center><a href =https://jobs4times.com/>Jobs4Times</a>  </h2>");
		}


	}

}
