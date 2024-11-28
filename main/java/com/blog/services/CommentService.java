package com.blog.services;

import java.util.List;

import com.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(Integer pid,CommentDto commentDto);
	void deleteComment(Integer id);
	List<CommentDto> getAllComment();
	
}
