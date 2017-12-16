package com.lucho.controller.admin;

import com.lucho.domain.api.CountAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lucho.domain.web.Order;
import com.lucho.service.CategoryService;
import com.lucho.service.OrderService;
import com.lucho.service.ProductService;
import com.lucho.service.UserService;
import com.lucho.util.Const;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AdminDashboardController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/api/admin/dashboard")
	public ResponseEntity<List> index() {
		// Stats
		long totalUsers = userService.countAll();
		long totalCategories = categoryService.countAll();
		long totalProducts = productService.countAll();
		long totalOrders = orderService.countAll();
        CountAll c = new CountAll();
        c.setUserCountAll(totalUsers);
        c.setCategoryCountAll(totalCategories);
        c.setProductCountAll(totalProducts);
        c.setOrderCountAll(totalOrders);

        ArrayList<CountAll> list = new ArrayList<>();
        list.add(c);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    @GetMapping("/api/admin/newestOrders")
    public ResponseEntity<Page<Order>> newestOrders(@RequestParam("page") Integer pageNumber){
        // Fetch top 5 newest orders;
        Page<Order> newestOrders = orderService.findLatest(pageNumber, Const.TOTAL_NEWEST_ORDERS);
        return new ResponseEntity<>(newestOrders, HttpStatus.OK);
    }
}
