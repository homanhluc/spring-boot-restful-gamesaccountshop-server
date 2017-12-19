package com.lucho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.lucho.domain.web.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("select p from Product p")
	List<Product> findAll();

	List<Product> findTop10ByOrderByIdDesc();

	List<Product> findByNameContaining(String keyword);
	
	@Query("select p from Product p left join fetch p.category c where c.id = ?1")
	List<Product> findByCategoryId(Integer categoryId);
	
	@Query("select p from Product p left join fetch p.category c where p.id = ?1")
	Product findOneWithCategory(Integer id);

	@Query(value = "select o from Product o",
			countQuery = "select count(o) from Product o")
	Page<Product> findLatest(Pageable pageable);
}