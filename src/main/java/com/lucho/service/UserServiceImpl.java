package com.lucho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucho.domain.web.User;
import com.lucho.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	@Transactional(readOnly = true)
	public Page<User> findLatest(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "created");
		return userRepository.findLatest(pageRequest);
	}
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return userRepository.count();
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		userRepository.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public com.lucho.domain.api.User checkLogin(com.lucho.domain.api.User user) {
		com.lucho.domain.api.User ret = null;
		
		User dbUser = userRepository.findByEmail(user.getEmail());
		
		if (dbUser != null 
				&& passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			ret = new com.lucho.domain.api.User();
			ret.setId(dbUser.getId());
			ret.setName(dbUser.getName());
			ret.setEmail(dbUser.getEmail());
		}
		
		return ret;
	}
	
	@Override
	@Transactional
	public boolean register(User user) {
		// Check email exists
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return false; 
		} 
		
		userRepository.save(user);
		
		return true;
	}

}