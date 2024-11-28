package com.blog.exceptions;

import com.blog.payloads.UserDto;

public class ResourceNotFoundException extends RuntimeException {

	String resName;
	String fieldName;
	int fieldValue;
	
	
	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResourceNotFoundException(String resName, String fieldName, int fieldValue) {
		super(String.format("%s Not Found with %s  : %s ",resName, fieldName,fieldValue));
		this.resName = resName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
	
}
