package com.lucho.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucho.domain.web.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
