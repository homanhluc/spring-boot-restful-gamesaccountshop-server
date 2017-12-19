package com.lucho.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lucho.domain.web.Product;
import com.lucho.service.CategoryService;
import com.lucho.service.ProductService;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/admin/product")
    public ResponseEntity<Iterable<Product>> index() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/admin/product/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/product/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/admin/product/{id}/upload")
    public ResponseEntity<Product> getUpload(@PathVariable("id") Integer id) {
        Product p = productService.findOne(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/api/admin/product/{id}/upload")
    public ResponseEntity<Product> postUpload(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile imageFile) {
        Product product = productService.findOne(id);
        return new ResponseEntity<>(productService.upload(product, imageFile), HttpStatus.OK);
    }

    @GetMapping("/api/admin/product/image")
    public ResponseEntity<byte[]> getImage(@PathParam("name") String name) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/upload/" + name);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);

    }
    /*@GetMapping("/admin/product/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/product_form";
	}*/

/*	@GetMapping("/admin/product/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.findOneWithCategory(id));
		model.addAttribute("categories", categoryService.findAll());
		return "admin/productƯ_form";
	}*/

	/*@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Category category = categoryService.findOne(Integer.parseInt(text));
                setValue(category);
            }
        });
    }*/
}
