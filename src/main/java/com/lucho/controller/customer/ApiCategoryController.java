package com.lucho.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucho.domain.web.Category;
import com.lucho.domain.web.Product;
import com.lucho.service.CategoryService;
import com.lucho.service.ProductService;

@RestController
@CrossOrigin("*")
public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/v1/categories")
	public ResponseEntity<Iterable<Category>> index() {
		Iterable<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/category/{id}/products")
	public ResponseEntity<List<Product>> products(@PathVariable("id") Integer categoryId) {
		if (categoryId == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<Product> products = productService.findByCategoryId(categoryId);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
	
}
