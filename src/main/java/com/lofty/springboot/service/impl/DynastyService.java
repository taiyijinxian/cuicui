package com.lofty.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lofty.springboot.domain.houge.Dynasty;
import com.lofty.springboot.repository.houge.DynastyRepository;

@Service
public class DynastyService {

	@Autowired
	private DynastyRepository dynastyRepository;
	
	public List<Dynasty> findAll() {
		List<Dynasty> dynastys =dynastyRepository.findAll();
		return dynastys;
	}

	public void save(Dynasty dynasty) {
		dynastyRepository.save(dynasty);
	}
}
