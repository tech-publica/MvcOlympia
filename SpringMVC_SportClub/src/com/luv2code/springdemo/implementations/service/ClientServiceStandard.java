package com.luv2code.springdemo.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.abstractions.dao.ClientDAO;
import com.luv2code.springdemo.abstractions.service.ClientService;
import com.luv2code.springdemo.entity.Client;

@Service
public class ClientServiceStandard implements ClientService{

	@Autowired
	private ClientDAO clientDao;
	
	@Override
	@Transactional
	public Client findById(int id) {
		return clientDao.findById(id);
	}

}
