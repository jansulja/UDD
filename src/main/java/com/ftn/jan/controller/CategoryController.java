package com.ftn.jan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.Category;
import com.ftn.jan.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> list(){
		
		return categoryService.findAll();
		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void insert(@RequestBody Category category){
		
		categoryService.save(category);
		
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(@RequestBody Category category){
		categoryService.update(category);
	}
	
	@DeleteMapping("/{categoryId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(@PathVariable(value="categoryId") Integer categoryId) {
		categoryService.remove(categoryId);
	};
	
	
	
}
