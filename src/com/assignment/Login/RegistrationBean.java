package com.assignment.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


@ManagedBean(name="reg")
@SessionScoped
public class RegistrationBean {
	
private String fname;
private String lname;
private String email;
private String userName;
private String password;
private String address;
private String phonenumber;
private Double balance;
private Double commission;
private String manager;

	public RegistrationBean() {
	
		// TODO Auto-generated constructor stub
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validateFname(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String fname=value.toString();
	   
	    if(!(fname.matches("[a-zA-Z_]+"))) {
	    	throw new ValidatorException(new FacesMessage(
	    			"Name should contain only characters"));
	    }
	    	
	}
	public void validatePhoneNumber(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String phonenumber=value.toString();
	   
	    if(!(phonenumber.matches("[0-9]+"))) {
	    	throw new ValidatorException(new FacesMessage(
	    			"Invalid Phone Number"));
	    }
	    	
	}
	public void validateLname(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String lname=value.toString();
	   
	    if(!(lname.matches("[a-zA-Z_]+"))) {
	    	throw new ValidatorException(new FacesMessage(
	    			"Name should contain only characters"));
	    }
	    	
	}
	

	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String username=value.toString();
	    Connection con= null;
		Statement stmt= null;
	    if(!(username.matches("[a-zA-Z_]+"))) {
	    	throw new ValidatorException(new FacesMessage(
	    			"Username should contain only characters"));
	    }
	    try
		{
		
		con= DataConnect.getConnection();
		stmt = con.createStatement();
		ResultSet resultset = 
                stmt.executeQuery("select * from mystock.registration where username="+ username +"") ;
				//boolean userAlreadyExists = false;
				System.out.println("hi5");
				int id = 0;
				while(resultset.next())
					id++;
			    if (id > 0)
			    	throw new ValidatorException(new FacesMessage(
			    			"Username already exists. Please try another username"));
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
}	
	
	public String registerUser() {
		RegistrationBean register = new RegistrationBean();
		register.setFname(fname);
		register.setLname(lname);
		register.setEmail(email);
		register.setUserName(userName);
		register.setPassword(password);
		register.setPhonenumber(phonenumber);
		register.setAddress(address);
		System.out.println("FirstName:" +fname);
		String valid = UserRegistrationDAO.register(register);
		if (valid.equals("success")) 
			return "success";
		else
			return "failure";
			
	}
	public String registerManager() {
		
		RegistrationBean register = new RegistrationBean();
		register.setFname(fname);
		register.setLname(lname);
		register.setEmail(email);
		register.setUserName(userName);
		register.setPassword(password);
		register.setPhonenumber(phonenumber);
		register.setAddress(address);
		String valid = ManagerRegistrationDAO.register(register);
		if (valid.equals("success")) 
			return "success";
		else
			return "failure";
		
	}
	public String chooseManager() {
		
		String username =(String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("username");
		String mName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("approve");
		System.out.println("UserName = " +mName);
		String approve = UserRegistrationDAO.approveManager(username,mName);
		if(approve.equals("approved"))
		{
			FacesContext.getCurrentInstance().addMessage("manager:ManagerProfile",new FacesMessage(FacesMessage.SEVERITY_WARN, "Manager Updated Successfully",
					"Updated Successfully."));
			return "success";
		}
		else
			return "failure";
			
	}
	public String approveManagerRequest() {
		
		
		String uName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("approve");
		System.out.println("UserName = " +uName);
		String approve = AdminDAO.approveManager(uName);
		if(approve.equals("approved"))
			return "success";
		else
			return "failure";
			
	}
	public String rejectManagerRequest() {
		
		
		String uName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reject");
		String approve = AdminDAO.rejectManager(uName);
		if(approve.equals("rejected"))
			return "success";
		else
			return "failure";
			
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public ArrayList<RegistrationBean> viewManagerRequests(){
		ArrayList<RegistrationBean> newList = AdminDAO.getManagerRequests();
		return newList;
		
	}
	public ArrayList<RegistrationBean> viewUserActivity(){
		ArrayList<RegistrationBean> userList = AdminDAO.viewUserActivity();
		return userList;
		
	}
	public ArrayList<RegistrationBean> viewManagerActivity(){
		ArrayList<RegistrationBean> userList = AdminDAO.viewManagerActivity();
		return userList;
		
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public RegistrationBean viewUserDetails() {
		System.out.println("Update1");
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		System.out.println( "UserName:" +userName);
		RegistrationBean userDetails = UserRegistrationDAO.getUserDetails(username);
		fname = userDetails.getFname();
		lname = userDetails.getLname();
		email = userDetails.getEmail();
		address = userDetails.getAddress();
		phonenumber = userDetails.getPhonenumber();
		userName = userDetails.getUserName();
		password = userDetails.getPassword();
		System.out.println(password);
		System.out.println(username);
		return userDetails;
	}
	public RegistrationBean viewManagerDetails() {
		System.out.println("Update1");
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		System.out.println( "UserName:" +userName);
		RegistrationBean userDetails = UserRegistrationDAO.getUserDetails(username);
		fname = userDetails.getFname();
		lname = userDetails.getLname();
		email = userDetails.getEmail();
		address = userDetails.getAddress();
		phonenumber = userDetails.getPhonenumber();
		userName = userDetails.getUserName();
		password = userDetails.getPassword();
		System.out.println(password);
		System.out.println(username);
		return userDetails;
	}
	
	public String updateUser() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		System.out.println("UserName:" +username);
		RegistrationBean register = new RegistrationBean();
		register.setFname(fname);
		register.setLname(lname);
		register.setEmail(email);
		
		register.setPassword(password);
		register.setPhonenumber(phonenumber);
		register.setAddress(address);
		UserRegistrationDAO.updateUser(register, username);
		return"success";
	}
	
	public String updateManager() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		System.out.println("UserName:" +username);
		RegistrationBean register = new RegistrationBean();
		register.setFname(fname);
		register.setLname(lname);
		register.setEmail(email);
		
		register.setPassword(password);
		register.setPhonenumber(phonenumber);
		register.setAddress(address);
		UserRegistrationDAO.updateManager(register, username);
		return"success";
	}
	
	public ArrayList<RegistrationBean> names()
	{
		ArrayList<RegistrationBean> managerList = UserRegistrationDAO.getManager();
		return managerList;
		
	}
	
}
