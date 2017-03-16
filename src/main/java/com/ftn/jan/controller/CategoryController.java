package com.ftn.jan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void insert(@RequestBody Category category){
		
		categoryService.save(category);
		
	}
	
	@PostMapping("update")
	public void update(@RequestBody Category category){
		categoryService.update(category);
	}
	
	
	public void delete(@RequestParam(name="categoryId") Integer categoryId) {
		categoryService.remove(categoryId);
	};
	
	
	
}
