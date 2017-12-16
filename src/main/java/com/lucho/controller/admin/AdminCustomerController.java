package com.lucho.controller.admin;

import com.lucho.domain.web.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lucho.domain.web.User;
import com.lucho.service.OrderService;
import com.lucho.service.UserService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AdminCustomerController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/api/admin/customer")
	public ResponseEntity<List<User>> index() {
		List<User> list = userService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/api/admin/customer/{id}/orders")
	public ResponseEntity<List<Order>> showOrders(@PathVariable("id") Integer id) {
		User customer = userService.findOne(id);
		List<Order> list = orderService.findByUser(customer);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/admin/customer/{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
