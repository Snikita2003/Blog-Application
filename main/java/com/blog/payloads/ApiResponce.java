package com.blog.payloads;

public class ApiResponce {

	String msg;
	boolean success;

	
	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponce(String msg2, boolean success) {
		super();
		this.msg = msg2;
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
