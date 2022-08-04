package com.example.project.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.project.dao.RegisterDao;


@Named
@SessionScoped
public class Register implements Serializable {
	

		private static final long serialVersionUID = 1L;
		
		private String pwd;
		private String msg;
		private String user;

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
		
		
}
