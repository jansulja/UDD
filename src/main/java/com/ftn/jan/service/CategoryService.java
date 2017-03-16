package com.ftn.jan.service;

import java.util.List;

import com.ftn.jan.model.Category;

public interface CategoryService {

	public List<Category> findAll();

	public void save(Category category);

	public void update(Category category);

	public void remove(Integer categoryId);
	
}
