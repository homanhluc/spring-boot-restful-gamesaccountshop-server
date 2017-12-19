package com.lucho.service;

import com.lucho.domain.web.Item;
import com.lucho.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
}
