package com.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

public class PostDto {

	private Integer id;
	private String title;
	private String content;
	private User user;
	private Category category;
	private String imageName;
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	private List<Comment> comments=new ArrayList<Comment>();
	
	
	
	
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PostDto(Integer id,String title, String content, User user, Category category, String imageName) {
		super();
		this.id=id;
		this.title = title;
		this.content = content;
		this.user = user;
		this.category = category;
		this.imageName = imageName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
