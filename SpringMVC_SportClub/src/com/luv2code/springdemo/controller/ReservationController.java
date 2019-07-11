package com.luv2code.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.abstractions.service.ClientService;
import com.luv2code.springdemo.abstractions.service.CourtService;
import com.luv2code.springdemo.abstractions.service.ReservationService;
import com.luv2code.springdemo.entity.Client;
import com.luv2code.springdemo.entity.Court;
import com.luv2code.springdemo.entity.Reservation;
import com.luv2code.springdemo.viewModels.ReservationViewModel;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	CourtService courtService;
	@Autowired
	ClientService clientService;
	
	@GetMapping("/list")
	public String list(Model model) {
		Iterable<Reservation> reservations = reservationService.list();
		model.addAttribute("reservations", reservations);		
		return "list-reservations";
	}
	
	@GetMapping("/showFormToAdd")
	public String showFormToAdd(Model model) {
		ReservationViewModel viewModel = new ReservationViewModel();
		model.addAttribute("reservationViewModel", viewModel);
		return "reservation-form";
	}
	
	@PostMapping("/saveReservation")
	public String saveCustomer(@ModelAttribute("reservationViewModel") ReservationViewModel reservationViewModel) {
		Reservation reservation = reservationFromViewModel(reservationViewModel);
		if(reservation.getId() == 0) {
			reservationService.save(reservation);
		}
		else {
			reservationService.update(reservation);
		}
		return "redirect:/reservation/list";
	}
	
	private Reservation reservationFromViewModel(ReservationViewModel reservationViewModel) {
		Client client = clientService.findById(reservationViewModel.getClientId());
		Court court = courtService.findById(reservationViewModel.getCourtId());
		Reservation reservation = new Reservation(
				reservationViewModel.getId(),
				reservationViewModel.getStart(),
				reservationViewModel.getEnd(),
				client,
				court,
				reservationViewModel.getCost()
				);
		return reservation;
	}

	@GetMapping("/showFormToUpdate")
	public String showFormForUpdate(@RequestParam("id") int id,
									Model model) {
		Reservation reservation = reservationService.reservationById(id);
		ReservationViewModel  viewModel = ReservationViewModelFromReservation(reservation);
		model.addAttribute("reservationViewModel", viewModel);		
		return "reservation-form";
	}
	
	private ReservationViewModel ReservationViewModelFromReservation(Reservation reservation) {
		return new ReservationViewModel(
				reservation.getId(),
				reservation.getStart(),
				reservation.getEnd(),
				reservation.getClient().getId(),
				reservation.getCourt().getId(),
				reservation.getCost()
				);
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("id") int id) {
		reservationService.deleteCustomer(id);
		return "redirect:/reservation/list";
	}
}













