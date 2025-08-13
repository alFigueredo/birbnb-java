package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.repository.ReservaRepository;

@Service
public class ReservaService {

	private final ReservaRepository reservaRepository;

	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	public List<Reserva> getAll() {
		return reservaRepository.findAll();
	}

	public Reserva getById(Long id) {
		return reservaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

}
