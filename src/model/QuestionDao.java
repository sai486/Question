package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionDao {
	
	public Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/question","root","root");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return con;
	}
	public  ArrayList<Question> getQuestion() {
		ArrayList<Question> questions = new ArrayList<Question>();
		 
		
		try {

		
			Connection  con=getConnection();
			

			String query = "select * from exam_paper";
			
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			
			while (res.next()) {
				Question q = new Question(res.getString("qno"), res.getString("question"), res.getString("opt1"),
						res.getString("opt2"), res.getString("opt3"), res.getString("opt4"), res.getString("ans"));
				questions.add(q);
				
			}
//			for(Question q:questions){
//				System.out.println(q.toString());
//			}
//			
		} catch (Exception e) {
			System.out.println(e);
		}
		return questions;
	}
	
	public boolean addQuestion(Question q){
		boolean b = false;
		
		Connection  con=getConnection();
		
		try{
	int a = getCount();
		
		PreparedStatement pst = con.prepareStatement("insert into exam_paper values (?,?,?,?,?,?,?) ");
		pst.setInt(1,a+1 );
		pst.setString(2, q.getQuestion());
		pst.setString(3,q.getOpt1());
		pst.setString(4,q.getOpt2());
		pst.setString(5,q.getOpt3());
		pst.setString(6,q.getOpt4());
		pst.setString(7,q.getAns());
		int rs = pst.executeUpdate();
		if(rs>0){
			b= true;
		}
		
		}catch (Exception e) {
			e.printStackTrace();
	
		}	
		return b;
	}
	
	
	public int getCount(){
		int a = 0;
		try{
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from exam_paper ");
			while(rs.next()){
				a++;
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		
		
		return a;
	}
	
	

























}



























