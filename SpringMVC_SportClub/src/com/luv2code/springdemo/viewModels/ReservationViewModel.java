package com.luv2code.springdemo.viewModels;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationViewModel {
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime start;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime end;
	private double cost;
	private int clientId;
	private int courtId;

	public ReservationViewModel(int id, LocalDateTime start, LocalDateTime end, int clientId,
			int courtId, double cost) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.clientId = clientId;
		this.courtId = courtId;
	}

	public ReservationViewModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}
	

}
