package com.blog.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;
import com.blog.payloads.UserResponce;
import com.blog.repositories.UserRepo;


@Service
public class ImplService implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public UserDto createUser(@RequestBody UserDto userDto) {

		User user = this.dtotoUser(userDto);
		User saved = this.userRepo.save(user);
		//System.out.println("Userrepo obj - "+ userRepo.getClass().getName());
		return this.userToDto(saved);

	}

	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {

		User user1=userRepo.findById(userid).orElseThrow( ()-> new ResourceNotFoundException("User Not Found With ID - ","UserId ",userid ) );
		
		
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getName());
		user1.setAbout(userDto.getAbout());
		user1.setPassword(userDto.getPassword());
		
		User updateduser=this.userRepo.save(user1);
		UserDto userDto2=this.userToDto(updateduser);
		return userDto2;
	}
	

	@Override
	public UserDto getUserById(Integer userid) {

		User user=userRepo.findById(userid)
				.orElseThrow( ()-> new ResourceNotFoundException("User Not Found with Id- ","ID - ", userid ));
		
		UserDto UserDto=this.userToDto(user);
		
		return UserDto;
	}
	
	

	@Override
	public List<UserDto> getAllUsers(Integer pageNo,Integer pageSize) {

		
		Pageable p=PageRequest.of(pageNo, pageSize);
		
		Page<User> pageUser=userRepo.findAll(p);
		List<User> listUser=pageUser.getContent(); // all users.
		
		List<UserDto> UserDtos=listUser.stream().map(user-> this.userToDto(user)).collect( Collectors.toList());
		
		
		UserResponce pr= new UserResponce();
		
		pr.setContent( UserDtos );
		pr.setPageNo( pageUser.getNumber());
		pr.setPageSize(pageUser.getSize());
		pr.setTotalEle( pageUser.getNumberOfElements());
		pr.setTotalpages( pageUser.getTotalElements());
		pr.setLastpage( pageUser.isLast());
		
		return UserDtos;
		
	}
	

	@Override
	public void deleteUserById(Integer userid) {

		User user=userRepo.findById(userid)
				.orElseThrow( ()->new ResourceNotFoundException("User Not ","Found  With Id - ", userid ) );
	
		userRepo.delete(user);
	}

	
	
	private User dtotoUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		user.setEmail(userDto.getEmail());
//		return user;
		return user;
		
		
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		userDto.setEmail(user.getEmail());
		return userDto;
	}


	@Override
	public int totalUsers() {
		int size= (int) userRepo.count();
		return size;
	}


	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
