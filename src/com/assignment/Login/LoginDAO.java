package com.assignment.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

public class LoginDAO {

    public static String loginUser(String username, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
		ResultSet rs =null;
		String fName =null;
        try {
            con = DataConnect.getConnection();
            String sql = "select * from mystock.registration where username =? AND password=? AND roleid= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, 3);
			rs = pstmt.executeQuery();
			LoginBean login = new LoginBean();
            if (rs.next()) {
            	fName = rs.getString("firstname");
            	System.out.println(fName);
            	login.setfName(fName);
            	System.out.println("Name:" +login.getfName());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
                //System.out.println("uid: " + rs.getString("uid"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login",login);
                DataConnect.close(con);
                
            }

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return "Login";
        } finally {

        }
        return fName;
    }
    public static String loginManager(String username, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String fName = null;
		ResultSet rs =null;
        try {
            con = DataConnect.getConnection();
            String sql = "select * from mystock.registration where username =? AND password=? AND roleid= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, 2);
			rs = pstmt.executeQuery();
			
			LoginBean login = new LoginBean();
            if (rs.next()) {
            	fName = rs.getString("firstname");
            	System.out.println(fName);
            	login.setfName(fName);
            	System.out.println("Name:" +login.getfName());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
                //System.out.println("uid: " + rs.getString("uid"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login",login);
                DataConnect.close(con);
                
            }

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return "Login";
        } finally {

        }
        return fName;
    }
    public static String loginAdmin(String username, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String fName = null;
		ResultSet rs =null;
        try {
            con = DataConnect.getConnection();
            String sql = "select * from mystock.registration where username =? AND password=? AND roleid= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, 1);
			rs = pstmt.executeQuery();
			LoginBean login = new LoginBean();
            if (rs.next()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
                //System.out.println("uid: " + rs.getString("uid"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login",login);
                DataConnect.close(con);
                
            }

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return "Login";
        } finally {

        }
        return "success";
    }

}
