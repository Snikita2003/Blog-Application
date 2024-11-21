package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Comment;
import com.blog.payloads.ApiResponce;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{id}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable Integer id, @RequestBody CommentDto commentDto)
	{
		CommentDto c=this.commentService.createComment(id, commentDto);
		return new ResponseEntity<CommentDto>(c,HttpStatus.CREATED);
	
	}
	
	
	@DeleteMapping("/comment/{cid}")
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable Integer cid)
	{
		this.commentService.deleteComment( cid);
		return new ResponseEntity<ApiResponce>( new ApiResponce("Deleted Comment",true),HttpStatus.OK);

	}
	
	
	@GetMapping("/")
	public  ResponseEntity<List<CommentDto>> getAllComments()
	{
		List<CommentDto> aans =this.commentService.getAllComment();
	
		return new ResponseEntity< List<CommentDto>>( aans,HttpStatus.OK);
		
	}
}









