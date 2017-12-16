package com.lucho.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucho.domain.web.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);

}
