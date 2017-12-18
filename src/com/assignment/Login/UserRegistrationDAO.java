package com.assignment.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Registration;

public class UserRegistrationDAO {

	public static String register(RegistrationBean register) {
		String check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
				System.out.println(register.getFname());
				con = DataConnect.getConnection();
				String sql = "INSERT INTO mystock.registration(firstname, lastname, email,username, password,address,phonenumber,roleid) values (?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, register.getFname());
				pstmt.setString(2 ,register.getLname());
				pstmt.setString(3, register.getEmail());
				pstmt.setString(4, register.getUserName());
				pstmt.setString(5, register.getPassword());
				pstmt.setString(6, register.getAddress());
				pstmt.setString(7, register.getPhonenumber());
				pstmt.setInt(8, 3);
				pstmt.executeUpdate();
				FacesContext.getCurrentInstance().addMessage("LoginForm:login",new FacesMessage(FacesMessage.SEVERITY_WARN, "Registered Successfully. Please Login.",
					"Registered Successfully. Please login to access your account."));
				check = "yes";
				
		}
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {

			}
	}
		if (check.equals("yes")) 
			return "success";
		else
			return "failure";
}

	public static String updateUser(RegistrationBean register, String username) {
		String check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("hello update");
			
				
				con = DataConnect.getConnection();
				String sql = "update mystock.registration set firstname=?,lastname=?, email=?,password=?,address=?,phonenumber=?  where username =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, register.getFname());
				pstmt.setString(2, register.getLname());
				pstmt.setString(3, register.getEmail());
				pstmt.setString(4, register.getPassword());
				pstmt.setString(5, register.getAddress());
				pstmt.setString(6, register.getPhonenumber());
				pstmt.setString(7, username);
				pstmt.executeUpdate();
				FacesContext.getCurrentInstance().addMessage("LoginForm:login",new FacesMessage(FacesMessage.SEVERITY_WARN, "Registered Successfully. Please Login.",
					"Updated Successfully. Please login to access your account."));
				check = "yes";
				
		}
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {

			}
	}
		if (check.equals("yes")) 
			return "success";
		else
			return "failure";
}

	public static RegistrationBean getUserDetails(String userName) {
		Connection con = null;
		ResultSet rs = null;
		Statement stmt= null;
		RegistrationBean register = new RegistrationBean();
		try {
			
				System.out.println("Update2");
				
				con = DataConnect.getConnection();
				stmt = con.createStatement();
		        rs = stmt.executeQuery("select * from  mystock.registration where username= '" +userName +"'");
		        while (rs.next()) {
		        	System.out.println("update 3");
		        	register.setFname(rs.getString("firstname"));
		        	register.setLname(rs.getString("lastname"));
		        	register.setEmail(rs.getString("email"));
		        	register.setAddress(rs.getString("address"));
		        	register.setUserName(rs.getString("username"));
		        	register.setPassword(rs.getString("password"));
		        	register.setPhonenumber(rs.getString("phonenumber"));
		        	
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
		return register;
	}

	public static ArrayList<RegistrationBean> getManager() {
		ArrayList<RegistrationBean> managerList = new ArrayList<>();
		   // String check = null;
			Connection con = null;
			ResultSet rs = null;
			Statement stmt= null;
			try {
				
					con = DataConnect.getConnection();
					stmt = con.createStatement();
			        rs = stmt.executeQuery("select * from  mystock.registration WHERE roleid =2");
			        while (rs.next()) {
			        	System.out.println("Get Manager");
			        	RegistrationBean managers= new RegistrationBean();
			        	managers.setFname(rs.getString("firstname"));
			        	managers.setLname(rs.getString("lastname"));
			        	managers.setEmail(rs.getString("email"));
			        	managers.setPhonenumber(rs.getString("phonenumber"));
			        	managers.setCommission(rs.getDouble("commission"));
			        	managers.setUserName(rs.getString("username"));
			        	managerList.add(managers);
			        	
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

	public static String approveManager(String uName, String mName) {
		String check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("hello update");
			
				
				con = DataConnect.getConnection();
				String sql = "update mystock.registration set manager=? where username =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mName);
				pstmt.setString(2, uName);
				pstmt.executeUpdate();
				FacesContext.getCurrentInstance().addMessage("manager:ManagerProfile",new FacesMessage(FacesMessage.SEVERITY_WARN, "Registered Successfully. Please Login.",
						"Updated Successfully."));
				check = "yes";
				
		}
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {

			}
	}
		if (check.equals("yes")) 
			return "success";
		else
			return "failure";
	}

	public static String updateManager(RegistrationBean register, String username) {
		String check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("hello update");
			
				
				con = DataConnect.getConnection();
				String sql = "update mystock.registration set firstname=?,lastname=?, email=?,password=?,address=?,phonenumber=?  where username =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, register.getFname());
				pstmt.setString(2, register.getLname());
				pstmt.setString(3, register.getEmail());
				pstmt.setString(4, register.getPassword());
				pstmt.setString(5, register.getAddress());
				pstmt.setString(6, register.getPhonenumber());
				pstmt.setString(7, username);
				pstmt.executeUpdate();
				FacesContext.getCurrentInstance().addMessage("LoginForm:login",new FacesMessage(FacesMessage.SEVERITY_WARN, "Updated Successfully",
					"Updated Successfully. "));
				check = "yes";
				
		}
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {

			}
	}
		if (check.equals("yes")) 
			return "success";
		else
			return "failure";
		
	}
}