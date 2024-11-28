package com.blog.payloads;

public class JwtAuthRequest {

	
	String uname;
	String password;
	
	
	public JwtAuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public JwtAuthRequest(String password) {
		super();
		this.password = password;
	}
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
