package com.blog.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.blog.entities.*;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;
import com.blog.payloads.UserResponce;


public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userid);

	UserDto getUserById(Integer userid);

	List<UserDto> getAllUsers(Integer pageno,Integer pagesize);

	void deleteUserById(Integer userid);
	int totalUsers();

	UserDto registerNewUser(UserDto userDto);

}
