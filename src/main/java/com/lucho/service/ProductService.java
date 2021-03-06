package com.lucho.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.lucho.domain.web.Product;

public interface ProductService {

	Iterable<Product> findAll();
	
	Page<Product> findLatest(int page, int size);
	
	List<Product> search(String keyword);
	
	List<Product> findByCategoryId(Integer categoryId);

	Product findOne(Integer id);
	
	Product findOneWithCategory(Integer id);

	long countAll();

	Product save(Product product);

	void delete(Integer id);
	
	Product upload(Product product, MultipartFile imageFile);

}
