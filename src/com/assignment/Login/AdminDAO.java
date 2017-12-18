package com.assignment.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AdminDAO {
	

	public static ArrayList<RegistrationBean> getManagerRequests() {
	    ArrayList<RegistrationBean> managerList = new ArrayList<>();
	   // String check = null;
		Connection con = null;
		ResultSet rs = null;
		Statement stmt= null;
		try {
			
				con = DataConnect.getConnection();
				stmt = con.createStatement();
		        rs = stmt.executeQuery("select * from  mystock.manager ORDER BY timestamp ");
		        while (rs.next()) {
		        	RegistrationBean register = new RegistrationBean();
		        	register.setFname(rs.getString("firstname"));
		        	register.setLname(rs.getString("lastname"));
		        	register.setEmail(rs.getString("email"));
		        	register.setAddress(rs.getString("address"));
		        	register.setUserName(rs.getString("username"));
		        	register.setPhonenumber(rs.getString("phonenumber"));
		        	managerList.add(register);
		        	
		        }
		}
		
		catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return managerList;
	}
	
	public static ArrayList<RegistrationBean> viewUserActivity() {
	    ArrayList<RegistrationBean> userList = new ArrayList<>();
	   // String check = null;
		Connection con = null;
		ResultSet rs = null;
		Statement stmt= null;
		try {
			
				con = DataConnect.getConnection();
				stmt = con.createStatement();
		        rs = stmt.executeQuery("select * from  mystock.registration where roleid =3 ORDER BY lastname ");
		        while (rs.next()) {
		        	RegistrationBean user = new RegistrationBean();
		        	user.setFname(rs.getString("firstname"));
		        	user.setLname(rs.getString("lastname"));
		        	user.setEmail(rs.getString("email"));
		        	user.setAddress(rs.getString("address"));
		        	user.setPhonenumber(rs.getString("phonenumber"));
		        	user.setUserName(rs.getString("username"));
		        	user.setPassword(rs.getString("password"));
		        	user.setBalance(rs.getDouble("balance"));
		        	user.setManager(rs.getString("manager"));
		        	userList.add(user);
		        	
		        }
		}
		
		catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return userList;
	}
	public static ArrayList<RegistrationBean> viewManagerActivity() {
	    ArrayList<RegistrationBean> userList = new ArrayList<>();
	   // String check = null;
		Connection con = null;
		ResultSet rs = null;
		Statement stmt= null;
		try {
			
				con = DataConnect.getConnection();
				stmt = con.createStatement();
		        rs = stmt.executeQuery("select * from  mystock.registration where roleid =2 ORDER BY lastname ");
		        while (rs.next()) {
		        	RegistrationBean user = new RegistrationBean();
		        	user.setFname(rs.getString("firstname"));
		        	user.setLname(rs.getString("lastname"));
		        	user.setEmail(rs.getString("email"));
		        	user.setAddress(rs.getString("address"));
		        	user.setUserName(rs.getString("username"));
		        	user.setPassword(rs.getString("password"));
		        	user.setBalance(rs.getDouble("balance"));
		        	user.setPhonenumber(rs.getString("phonenumber"));
		        	user.setCommission(rs.getDouble("commission"));
		        	userList.add(user);
		        	
		        }
		}
		
		catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return userList;
	}

	public static String approveManager(String uName) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement stmt= null;
		PreparedStatement pstmt = null;
		try {
				System.out.println("Hello1");
				con = DataConnect.getConnection();
				stmt = con.createStatement();
				String fName = null,lName = null, email = null, address = null,userName = null, password = null, phone =null;
				Double commission = null;
			    rs = stmt.executeQuery("select * from  mystock.manager where username='" +uName +"'");
			    while(rs.next()) {
		        	fName = rs.getString("firstname");
		        	 System.out.println("fName:" + fName);
		        	 lName = rs.getString("lastname");
		        	 email = rs.getString("email");
		        	 address = rs.getString("address");
		        	 userName = rs.getString("username");
		        	 password = rs.getString("password");
		        	 phone = rs.getString("phonenumber");
		        	 commission=rs.getDouble("commission");
			    }
		        System.out.println("Hello:" +lName);
		        String sql = "INSERT INTO mystock.registration(firstname, lastname, email,username, password,address,phonenumber,balance,commission,roleid) values (?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,fName);
				pstmt.setString(2 ,lName);
				pstmt.setString(3,email);
				pstmt.setString(4, userName);
				pstmt.setString(5, password);
				pstmt.setString(6, address);
				pstmt.setString(7, phone);
				pstmt.setDouble(8, 100000);
				pstmt.setDouble(9, commission);
				pstmt.setInt(10, 2);
				pstmt.executeUpdate();
				stmt.execute("delete  from  mystock.manager where username='" +uName +"'");
		        
		}
		
		catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return "approved";

	}

	public static String rejectManager(String uName) {
		Connection con = null;
		Statement stmt =null;
		try {
			System.out.println("Hello2");
			con = DataConnect.getConnection();
			stmt = con.createStatement();
			stmt.execute("delete  from  mystock.manager where username='" +uName +"'");
		        
		}
		catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return "rejected";
	}
		         
	}

