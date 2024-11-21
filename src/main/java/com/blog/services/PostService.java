package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

//	Post createPost(PostDto postDto);
	PostDto updatePost(Integer post,PostDto postDto);
	void deletePost(Integer id);
	PostResponse getAllPosts( Integer pageNo,Integer pageSize ,String sortBy);
	PostDto getPostById(Integer id);
	List<PostDto> getPostByCategory(Integer id);
	List<PostDto> getPostByUser(Integer id);
	List<Post> searchPosts(String keyword);
	PostDto createPost(PostDto postDto, Integer uid, Integer cid);
	
	
	
	
}
