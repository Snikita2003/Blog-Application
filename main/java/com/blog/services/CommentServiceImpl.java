package com.blog.services;

import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	@Override
	public CommentDto createComment(Integer pid, CommentDto commentDto) {
		
		Post post=this.postRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","Id ",pid ));
		Comment c1=this.DtoToC(commentDto);
		c1.setPost(post);
		Comment ans=this.commentRepo.save(c1);
		return ctoDto(ans);
	}
	
	

	@Override
	public void deleteComment(Integer id) {
		Comment comment=this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","Id ",id));
		this.commentRepo.delete(comment);
		
	}

	public List<CommentDto> getAllComment()
	{
		List<Comment> all=this.commentRepo.findAll();
		List<CommentDto> ans=new ArrayList<>();
		
		for(Comment c:all)
		{
			ans.add( ctoDto(c));
		}
		return ans;
	}
	
	

	// post to dto
	private Comment DtoToC(CommentDto commentDto)
	{
		return this.modelMapper.map( commentDto, Comment.class);
	}
	
	// dto to post.
	private CommentDto ctoDto(Comment comment)
	{
		return this.modelMapper.map( comment, CommentDto.class);
	}

	
}
