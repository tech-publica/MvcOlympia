package com.luv2code.springdemo.implementations.dao.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Client;
import com.luv2code.springdemo.entity.Court;
import com.luv2code.springdemo.entity.Reservation;
import com.luv2code.springdemo.entity.SoccerCourt;
import com.luv2code.springdemo.entity.TennisCourt;

public class SessionFactorySingleton implements AutoCloseable {
	private static SessionFactorySingleton instance = new SessionFactorySingleton();

	private SessionFactorySingleton() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Client.class)
				.addAnnotatedClass(Court.class)
				.addAnnotatedClass(TennisCourt.class)
				.addAnnotatedClass(SoccerCourt.class)
				.addAnnotatedClass(Reservation.class)
				.buildSessionFactory();
	}

	private SessionFactory factory;

	public Session openSession() {
		return factory.openSession();
	}

	public static SessionFactorySingleton getInstance() {
		return instance;
	}

	@Override
	public void close() throws Exception {
		factory.close();

	}

}
