package com.blog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponce;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;
import com.blog.payloads.UserResponce;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// get totalcount all users
	@GetMapping("/totalusers")
	public int getTotalUsers() {
		System.out.println("connected with angular");
		return this.userService.totalUsers();
	}

	// get user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.userService.getUserById(id));

	}

	// get all user
	// http://localhost:8080/api/users/
	@GetMapping("/")
	public ResponseEntity getAllUsers(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize) {

		List<UserDto> all = this.userService.getAllUsers(pageNo, pageSize);
		return ResponseEntity.ok(all); // return only list of dtos.

		// will return list of dtos and UserResponce.
		// return new ResponseEntity<UserResponce>(all,HttpStatus.OK);

	}

	// post -create user
	@PostMapping("/add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		// Check if userDto is not null here for debugging
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userid") Integer uid) {

		UserDto updateduser = userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updateduser);
	}

	
	
	
	// DELETE - delete user
	// onl admmin can delete user	
	@PreAuthorize("hasRole('ADMIN')")   // for role based authentication
	@DeleteMapping("/{uid}")
	public ResponseEntity<ApiResponce> deleteUser(@Valid @PathVariable("uid") Integer id) {
		this.userService.deleteUserById(id);

		return new ResponseEntity(new ApiResponce("User Deleted Succesfully", true), HttpStatus.OK);
	}

}









