package com.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category cat = this.DtoToCategory(categoryDto);
		Category c = this.categoryRepo.save(cat);

		return this.categoryToDto(c);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer id) {

		Category cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));
		cat.setCategoryDescription(category.getCategoryDescription());
		cat.setCategoryTitle(category.getCategoryTitle());

		Category ans = categoryRepo.save(cat);
		return this.modelmapper.map(ans, CategoryDto.class);
	}
	

	@Override
	public void deletByd(Integer id) {

		Category data = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id- ", id));

		this.categoryRepo.delete(data);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {

		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "ID - ", id));

		CategoryDto categoryDto = this.categoryToDto(category);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> list = this.categoryRepo.findAll();
		
		List<CategoryDto> ans=new ArrayList<>();
		for(Category l:list)
		{
			ans.add( categoryToDto(l));
		}
//		List<CategoryDto> ans = list.stream().map( (category) ->this.modelmapper.map(list, CategoryDto.class)).collect(Collectors.toList());
//
		return ans;
	}

	// modelmapper(category to dto).
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelmapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	// modelmapper (dto to category) ;
	private Category DtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelmapper.map(categoryDto, Category.class);
		return category;
	}

}
