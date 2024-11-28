package com.blog.services;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;

import ch.qos.logback.core.model.processor.AllowAllModelFilter;
import jakarta.persistence.PostRemove;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer uid,Integer cid) {
		
		
		User user=this.userRepo.findById(uid).orElseThrow( ()-> new ResourceNotFoundException("User","Id",uid));
		Category category=this.categoryRepo.findById(cid).orElseThrow( ()-> new ResourceNotFoundException("Category","category id",cid ));
		Post post=this.DtoToPost(postDto);
	    post.setImageName("default.png");
		post.setDate( new Date(0) );
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.PosttoDto(newPost);
	}

	
	@Override
	public PostDto updatePost(Integer id, PostDto postDto) {
		
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id ",id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post PostUpdated=this.postRepo.save(post);
		return this.PosttoDto(PostUpdated);
	}
	
	

	public void deletePost(Integer id) {
		Post post1=this.postRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Post ","Post Id ",id));
		this.postRepo.delete(post1);
	}
	

	@Override
	public PostResponse getAllPosts( Integer pageNo,Integer pageSize,String sortBy) {
		
		
		Pageable p= PageRequest.of(pageNo, pageSize, Sort.by( sortBy ).ascending());

		Page<Post> pagePost = postRepo.findAll(p);
		
		List<Post> all=pagePost.getContent(); // all list of posts.
		
		List<PostDto>  allPstDtos = all .stream().map(( post)-> this.PosttoDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(allPstDtos);
		postResponse.setPageNo(pagePost.getNumber());
		postResponse.setPageSize( pagePost.getSize());
		postResponse.setTotalpages( pagePost.getTotalPages());
		postResponse.setLastpage( pagePost.isLast());
		postResponse.setTotalEle( pagePost.getNumberOfElements());

		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer id) {
		
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Id ",id));
		
		return this.PosttoDto(post);
	}
	
	

	@Override
	public List<PostDto> getPostByCategory(Integer catId) {
		
		Category category=this.categoryRepo.findById(catId).orElseThrow( ()->new ResourceNotFoundException("category","Id",catId));
		
		List<Post> allPost=this.postRepo.findByCategory(category);
		List<PostDto> ans=new ArrayList<PostDto>();
		
		for(Post p:allPost)
		{
			ans.add( PosttoDto(p));
		}
		return ans;
	}
	
	

	@Override
	public List<PostDto> getPostByUser( Integer uid) {
		
		User user1=this.userRepo.findById( uid ).orElseThrow( ()->new ResourceNotFoundException("User","Id ",uid));
		List<Post> allPost=this.postRepo.findByUser(user1);
		List<PostDto> ans=new ArrayList<PostDto>();
		
		for(Post p:allPost)
		{
			ans.add( PosttoDto(p));
		}
		return ans;
	}
	
	

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// post to dto
	private Post DtoToPost(PostDto postDto)
	{
		return this.modelMapper.map(postDto, Post.class);
	}
	
	// dto to post.
	private PostDto PosttoDto(Post post)
	{
		return this.modelMapper.map(post, PostDto.class);
	}


	

		
}
