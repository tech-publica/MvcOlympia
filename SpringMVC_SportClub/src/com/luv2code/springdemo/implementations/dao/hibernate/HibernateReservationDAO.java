package com.luv2code.springdemo.implementations.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.abstractions.dao.ReservationDAO;
import com.luv2code.springdemo.entity.Reservation;

@Repository
public class HibernateReservationDAO implements ReservationDAO {

	public static final String HQL_SELECT_ALL = "from Reservation";

	@Autowired
	private SessionFactory factory;

	@Override
	public Iterable<Reservation> allReservations() {
		Session session = factory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Reservation> res = session.createQuery(HQL_SELECT_ALL).getResultList();
		return res;
	}

	@Override
	public Reservation add(Reservation newReservation) {
		Session session = factory.getCurrentSession();
		session.save(newReservation);
		return newReservation;
	}

	@Override
	public Reservation update(Reservation newReservation) {
		Session session = factory.getCurrentSession();
		session.update(newReservation);
		return newReservation;
	}

	@Override
	public Reservation delete(int idReservation) {
		Session session = factory.getCurrentSession();
		Reservation found = session.load(Reservation.class, idReservation);
		session.delete(found);
		return found;
	}

	@Override
	public Reservation findById(int id) {
		Session session = factory.getCurrentSession();
		Reservation found = session.get(Reservation.class, id);
		return found;
	}

}
