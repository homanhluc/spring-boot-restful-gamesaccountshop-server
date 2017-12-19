package com.lucho.controller.customer;

import java.util.List;

import com.lucho.domain.web.Category;
import com.lucho.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lucho.domain.web.Product;
import com.lucho.service.ProductService;

@RestController
@CrossOrigin("*")
public class ApiProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/v1/product/latest")
	public ResponseEntity<Page<Product>> findLatest(@RequestParam("page") Integer pageNumber) {
		return new ResponseEntity<>(productService.findLatest(pageNumber, 5), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/product/search") 
	public ResponseEntity<List<Product>> search(@RequestParam("keyword") String keyword) {
		return new ResponseEntity<>(productService.search(keyword), HttpStatus.OK);
	}
	@GetMapping("/api/v1/product/{id}")
	public ResponseEntity<Product> show(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productService.findOne(id), HttpStatus.OK);
	}
}
