package com.lofty.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lofty.springboot.domain.houge.Dynasty;
import com.lofty.springboot.service.impl.DynastyService;

@RestController
@RequestMapping("/dynasty")
public class DynastyController {
	
	@Autowired
	private DynastyService dynastyService;

	@RequestMapping("/hello")
	public String hello() {
		return "success";
	}
	
	@RequestMapping("/findAll")
	public List<Dynasty> findAll() {
		List<Dynasty> dynastys = dynastyService.findAll();
		return dynastys;
	}
	
	@RequestMapping("/save")
	public String save(Dynasty dynasty) {
		dynastyService.save(dynasty);
		return "success";
	}
}
