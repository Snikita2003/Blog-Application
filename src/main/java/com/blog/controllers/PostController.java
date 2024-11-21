package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.AppConstantsHardCodedValues;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.payloads.ApiResponce;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/home")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	
	@PostMapping("/user/{uid}/category/{cid}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer uid,@PathVariable Integer cid)
	{
		
		PostDto newPost= this.postService.createPost(postDto,uid,cid) ;
		return new  ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
	
	
	// get by user(userId)
	@GetMapping("/user/{uid}/posts")
	public  ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer uid)
	{
		List<PostDto> allPostDtos = this.postService.getPostByUser( uid);
		return new ResponseEntity<List<PostDto>> ( allPostDtos,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{cid}/posts")
	public  ResponseEntity<List<PostDto>> getPostByCategry(@PathVariable Integer cid)
	{
		
		List<PostDto> allPostDtos=this.postService.getPostByCategory(cid);
		return new ResponseEntity<List<PostDto>> (allPostDtos,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts")
	public  ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNo",defaultValue= AppConstantsHardCodedValues.PAGENO,required=false) Integer  pageNo,
			@RequestParam(value="pageSize",defaultValue= AppConstantsHardCodedValues.PAGESIZE , required = false ) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstantsHardCodedValues.SORTBY ,required = false) String sortBy )
	{
		
		PostResponse PostDtos= this.postService.getAllPosts( pageNo,pageSize ,sortBy );
		return new ResponseEntity<PostResponse>(PostDtos,HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/post/{pid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer pid)
	{
		return ResponseEntity.ok( this.postService.getPostById(pid) ); 
	}
	
	
	@DeleteMapping("/post/{pid}")
	public ApiResponce deletePost(@PathVariable("pid") Integer id)
	{
		this.postService.deletePost(id) ;
		return new ApiResponce("Post is deleted succesffuly",true);
	}
	
	
	@PutMapping("/post/{id}")
	public ResponseEntity<PostDto> updatePost( @PathVariable Integer id, @RequestBody PostDto postDto)
	{
		
		PostDto updatePost=this.postService.updatePost(id, postDto);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	
	
	
	
}











