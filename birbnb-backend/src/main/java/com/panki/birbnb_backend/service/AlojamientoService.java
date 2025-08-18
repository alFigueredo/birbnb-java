package com.panki.birbnb_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.dto.FiltrosAlojamientoDTO;
import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.repository.AlojamientoRepository;
import com.panki.birbnb_backend.specs.AlojamientoSpecs;

@Service
public class AlojamientoService {

	private final AlojamientoRepository alojamientoRepository;

	public AlojamientoService(AlojamientoRepository alojamientoRepository) {
		this.alojamientoRepository = alojamientoRepository;
	}

	public Alojamiento getById(Long id) {
		return alojamientoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("El alojamiento " + id + " no existe"));
	}

	public Page<Alojamiento> filtrarAlojamientos(FiltrosAlojamientoDTO filtrosAlojamientoDTO, Pageable pageable) {
		Specification<Alojamiento> spec = Specification
				.allOf(AlojamientoSpecs.conNombre(filtrosAlojamientoDTO.getNombre()),
						AlojamientoSpecs.conPrecioGt(filtrosAlojamientoDTO.getPrecioGt()),
						AlojamientoSpecs.conPrecioLt(filtrosAlojamientoDTO.getPrecioLt()),
						AlojamientoSpecs.conCaracteristicas(filtrosAlojamientoDTO.getCaracteristicas()),
						AlojamientoSpecs.conCiudad(filtrosAlojamientoDTO.getCiudad()),
						AlojamientoSpecs.conPais(filtrosAlojamientoDTO.getPais()));
		return alojamientoRepository.findAll(spec, pageable);
	}

}
