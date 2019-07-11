package com.luv2code.springdemo.abstractions.dao;

import com.luv2code.springdemo.entity.Reservation;

public interface ReservationDAO {
    Iterable<Reservation> allReservations();
    Reservation add(Reservation newReservation);
    Reservation update(Reservation newReservation);
    Reservation delete (int idReservation);
    Reservation findById(int id);
}
