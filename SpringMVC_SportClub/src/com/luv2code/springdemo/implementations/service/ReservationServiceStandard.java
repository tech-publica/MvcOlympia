package com.luv2code.springdemo.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.abstractions.dao.ClientDAO;
import com.luv2code.springdemo.abstractions.dao.CourtDAO;
import com.luv2code.springdemo.abstractions.dao.ReservationDAO;
import com.luv2code.springdemo.abstractions.service.ReservationService;
import com.luv2code.springdemo.entity.Reservation;

@Service
public class ReservationServiceStandard  implements ReservationService{

	@Autowired
	ReservationDAO reservationDao;
	@Autowired
	ClientDAO clientDao;
	@Autowired
	CourtDAO courtDao;
	
	@Override
	@Transactional
	public Iterable<Reservation> list() {
		return reservationDao.allReservations();
	}

	@Override
	@Transactional
	public void save(Reservation reservation) {
		reservationDao.add(reservation);
		
	}

	@Override
	@Transactional
	public Reservation reservationById(int id) {
		return reservationDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		reservationDao.delete(id);
		
	}

	@Override
	@Transactional
	public void update(Reservation reservation) {
		reservationDao.update(reservation);
		
	}

}
