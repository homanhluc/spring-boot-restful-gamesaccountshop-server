package com.lucho.service;

import com.lucho.domain.web.Category;

public interface CategoryService {

	Iterable<Category> findAll();
	
	Category findOne(Integer id);
	
	long countAll();

	Category save(Category category);
	
	void delete(Integer id);
	
}
