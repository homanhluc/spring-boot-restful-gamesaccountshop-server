package com.lucho.repository;

import java.util.List;

import com.lucho.domain.web.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lucho.domain.web.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u")
	List<User> findAll();
	
	@Query("select u from User u left join fetch u.roles where u.email = ?1")
	User findByEmail(String email);

	@Query(value = "select u from User u",
			countQuery = "select count(u) from User u")
	Page<User> findLatest(Pageable pageable);
}
