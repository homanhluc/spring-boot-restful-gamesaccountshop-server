package com.lucho.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lucho.domain.web.Order;
import com.lucho.domain.web.User;

public interface OrderService {
	
	Iterable<Order> findAll();
	
	Page<Order> findLatest(int page, int size);
	
	List<Order> findByUser(User user);
	
	Order findOne(Integer id);
	
	long countAll();
	
	void save(com.lucho.domain.api.Order order);
	
	void delete(Integer orderId);
	
}
