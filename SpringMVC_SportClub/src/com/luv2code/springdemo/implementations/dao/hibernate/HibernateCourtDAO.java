package com.luv2code.springdemo.implementations.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.abstractions.dao.CourtDAO;
import com.luv2code.springdemo.entity.Court;

@Repository
public class HibernateCourtDAO implements CourtDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public Court findById(int id) {
		Session session = factory.getCurrentSession();
		Court found = session.get(Court.class, id);
		return found;

	}

}
