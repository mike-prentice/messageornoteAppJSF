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

	public boolean isShowRegisterBtn() {
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
		System.out.println(user);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "test";
		} else {
			showRegisterBtn = true;
			FacesContext.getCurrentInstance().addMessage(
					"pass:msglog1",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login Not Found",
							"Please register a new account"));
			return "login";
		}
	}

	public String register() {
		if (RegisterDao.register(user, pwd)) {
			System.out.println(user.toString());
			return "test";
		} else {
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}

