package com.assignment.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ManagerRegistrationDAO {

	public static String register(RegistrationBean register) {
		String check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
				con = DataConnect.getConnection();
				String sql = "INSERT INTO mystock.manager(firstname, lastname, email,username, password,address,phonenumber,timestamp) values (?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, register.getFname());
				pstmt.setString(2 ,register.getLname());
				pstmt.setString(3, register.getEmail());
				pstmt.setString(4, register.getUserName());
				pstmt.setString(5, register.getPassword());
				pstmt.setString(6, register.getAddress());
				pstmt.setString(7, register.getPhonenumber());
				pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
				pstmt.executeUpdate();
				FacesContext.getCurrentInstance().addMessage("LoginForm:login",new FacesMessage(FacesMessage.SEVERITY_WARN, "Registered Successfully. Please Login.",
					"Registered Successfully. Please wait for confirmation to access your account."));
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
