package com.luv2code.springdemo.implementations.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.abstractions.dao.ClientDAO;
import com.luv2code.springdemo.entity.Client;

@Repository
public class HibernateClientDAO implements ClientDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public Client findById(int id) {
		Session session = factory.getCurrentSession();
		Client found = session.get(Client.class, id);
		return found;

	}

}
