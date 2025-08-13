package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.repository.AlojamientoRepository;

@Service
public class AlojamientoService {

	private final AlojamientoRepository alojamientoRepository;

	public AlojamientoService(AlojamientoRepository alojamientoRepository) {
		this.alojamientoRepository = alojamientoRepository;
	}

	public List<Alojamiento> getAll() {
		return alojamientoRepository.findAll();
	}

	public Alojamiento getById(Long id) {
		return alojamientoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
	}

}
