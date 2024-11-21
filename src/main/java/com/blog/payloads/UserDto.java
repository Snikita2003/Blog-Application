package com.blog.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class UserDto {

	
	private Integer id;
	
	@NotEmpty
	@Size(min=4,message="User Name must be min of 4 characters")
	private String name;
	
	@Email(message = "Email Address is not valid. add @ ")
	private String email;
	
	@NotEmpty  // null+not empty.
	@Size(min=3,max=10,message = "password must be between 3 to 10 characters")
	private String password;
	
	
	@NotEmpty
	private String about;

	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Integer id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
