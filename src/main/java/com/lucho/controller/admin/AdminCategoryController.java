package com.lucho.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lucho.domain.web.Category;
import com.lucho.service.CategoryService;

@RestController
@CrossOrigin("*")
public class AdminCategoryController {
	
	@Autowired
	private CategoryService categoryService;

    @GetMapping("/api/admin/category")
    public ResponseEntity<Iterable<Category>> index() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/api/admin/category/{id}/edit")
    public ResponseEntity<Category> edit(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category c = categoryService.findOne(id);
        c.setName(category.getName());
        return new ResponseEntity<>(categoryService.save(c),HttpStatus.CREATED);
    }
    
    @PostMapping("/api/admin/category/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/api/admin/category/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

    @GetMapping("/api/admin/category/{id}/find")
    public ResponseEntity<Category> findOne(@PathVariable("id") Integer id) {
      return new ResponseEntity<>(categoryService.findOne(id), HttpStatus.OK);
    }
}
