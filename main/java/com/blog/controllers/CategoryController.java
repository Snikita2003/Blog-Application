package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponce;
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById( @PathVariable Integer id)
	{
		return ResponseEntity.ok( this.categoryService.getCategoryById(id) );
	}
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto c)
	{
		CategoryDto ans=this.categoryService.createCategory(c);
		return new ResponseEntity<CategoryDto>(ans,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer id)
	{
		CategoryDto cat=this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto> (cat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce> deleteCategory( @PathVariable Integer id)
	{
		this.categoryService.deletByd(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Category is Deleted Succesfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> allData= this.categoryService.getAllCategories();
		return ResponseEntity.ok( allData );
	}
	
	
	
	
	
	
}





















