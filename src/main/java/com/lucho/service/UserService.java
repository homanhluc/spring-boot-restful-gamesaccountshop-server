package com.lucho.service;

import java.util.List;

import com.lucho.domain.web.Order;
import com.lucho.domain.web.User;
import org.springframework.data.domain.Page;

public interface UserService {

	List<User> findAll();
	
	User findOne(Integer id);
	
	long countAll();
	
	void delete(Integer id);
	
	com.lucho.domain.api.User checkLogin(com.lucho.domain.api.User user);
	
	boolean register(User user);

	Page<User> findLatest(int page, int size);
	
}
