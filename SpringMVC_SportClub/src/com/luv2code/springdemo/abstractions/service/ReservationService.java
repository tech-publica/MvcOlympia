package com.luv2code.springdemo.abstractions.service;

import java.util.List;

import com.luv2code.springdemo.entity.Reservation;

public interface ReservationService {
	Iterable<Reservation> list();

	void save(Reservation reservation);

	void update(Reservation reservation);
	
	Reservation reservationById(int id);

	void deleteCustomer(int id);
}
