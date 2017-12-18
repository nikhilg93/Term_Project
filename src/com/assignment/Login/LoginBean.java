package com.assignment.Login;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "login1")
@ApplicationScoped
public class LoginBean {

	public LoginBean() {
		// TODO Auto-generated constructor stub
		
	}
	private String fName;
	private String lName;
	private String userName;
	private String password;
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
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String username=value.toString();
	    if (username == null|| username.isEmpty()) {
	    			return;
	    }
    	
	}
	public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    String pwd=value.toString();
	    if (pwd == null|| pwd.isEmpty()) {
	    			return;
	    }
    	
	}
	public String loginUser()
	{	
		//LoginBean login = new LoginBean();
		System.out.println("1");
		//login.setfName(fName);
		String valid = LoginDAO.loginUser(userName,password);
		if (valid != "Login" && !valid.equals("Login")) {
			fName = valid;
			return "success";
		}
		else
			return "Login";
		
	}
	public String loginManager()
	{	
		LoginBean login = new LoginBean();
		login.setfName(fName);
		String valid = LoginDAO.loginManager(userName,password);
		if (valid != "Login" && !valid.equals("Login")) {
			fName = valid;
			return "success";
		}
		else
			return "Login";
		
	}
	public String loginAdmin()
	{	
		LoginBean login = new LoginBean();
		login.setfName(fName);
		String valid = LoginDAO.loginAdmin(userName,password);
		if (valid != "Login" && !valid.equals("Login")) {
			
			return "success";
		}
		else
			return "Login";
		
	}
	public String redirectRegister() {
		return "registration";
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/Index.xhtml");
        return "loggedout";
    }
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
}
