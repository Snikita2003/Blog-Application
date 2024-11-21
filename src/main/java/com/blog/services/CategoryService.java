package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto category,Integer id);
	
	public void deletByd(Integer id);
	
	public CategoryDto getCategoryById(Integer id);
	
	public List<CategoryDto> getAllCategories();
	
	
}
