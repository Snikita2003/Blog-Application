package com.blog.entities;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.payloads.UserDto;
import com.blog.services.UserService;

import jakarta.persistence.*;

@Entity
@Table(name = "User")

public class User implements UserDetails  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "user_name", nullable = true, length = 100)
	private String name;
	
	
	private String email;
	private String password;
	private String about;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns =@JoinColumn(name="user",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
	
	private Set<Role> roles=new HashSet<>();
	
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public User(Integer id, String name, String email, String password, String about) {
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


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	 List<SimpleGrantedAuthority>	list=this.roles.stream().map((r)->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
		return list;
	}


	@Override
	public String getUsername() {
		return this.email;
	}


}
