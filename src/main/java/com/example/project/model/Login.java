package com.example.project.model;




import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.project.dao.LoginDAO;
import com.example.project.dao.RegisterDao;



@Named
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;
	private boolean showRegisterBtn = false;

	public boolean getShowRegisterBtn() {
		return showRegisterBtn;
	}

	public void setShowRegisterBtn(boolean showRegisterBtn) {
		this.showRegisterBtn = showRegisterBtn;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		boolean valid = LoginDAO.validate(user, pwd);
		System.out.println(showRegisterBtn);
		System.out.println(user);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "test";
		} else {
			showRegisterBtn = true;
			System.out.println(showRegisterBtn);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Login Not Found: ",
							"Please register a new account"));
			return "/login.xhtml?faces-redirect=true";
		}
	}

	public String register() {
		RegisterDao.register(user, pwd);
			System.out.println(user.toString());
			validateUsernamePassword();
			showRegisterBtn = false;
			return "test";
		
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}

	public String cancel() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}
}

