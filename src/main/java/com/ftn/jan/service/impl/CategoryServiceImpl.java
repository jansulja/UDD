package com.ftn.jan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.jan.jpa.repository.CategoryRepository;
import com.ftn.jan.model.Category;
import com.ftn.jan.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
	}

	@Override
	public void update(Category category) {

		categoryRepository.save(category);
		
	}

	@Override
	public void remove(Integer categoryId) {
		// TODO Auto-generated method stub
		categoryRepository.delete(categoryId);
	}

}
