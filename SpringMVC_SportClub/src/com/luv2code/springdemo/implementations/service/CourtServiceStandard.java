package com.luv2code.springdemo.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.abstractions.dao.CourtDAO;
import com.luv2code.springdemo.abstractions.service.CourtService;
import com.luv2code.springdemo.entity.Court;

@Service
public class CourtServiceStandard implements CourtService{
  
	@Autowired
	private CourtDAO courtDao;

	@Override
	@Transactional
	public Court findById(int id) {
		return courtDao.findById(id);
		
	}
	
	
	
	
}
