package com.lucho.controller.admin;

import com.lucho.domain.web.Item;
import com.lucho.domain.web.Order;
import com.lucho.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucho.service.OrderService;

@RestController
@CrossOrigin("*")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ItemService itemService;

	@GetMapping("/api/admin/items")
	public ResponseEntity<Iterable<Item>> items() {
		return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
	}
	@GetMapping("/api/admin/order")
	public ResponseEntity<Iterable<Order>> index() {
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
	}
	@GetMapping("/api/admin/order/{id}")
	public ResponseEntity<Order> show(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(orderService.findOne(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/admin/order/{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		orderService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
