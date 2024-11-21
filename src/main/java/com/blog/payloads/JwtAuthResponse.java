package com.blog.payloads;

public class JwtAuthResponse {

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	String token;

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}
	
	
}
