package com.lucho.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucho.domain.api.Order;
import com.lucho.service.OrderService;

@RestController
@CrossOrigin("*")
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/api/v1/order/save")
	public ResponseEntity<Boolean> place(@RequestBody Order order) {
		orderService.save(order);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
}
