package com.panki.birbnb_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.panki.birbnb_backend.model.Alojamiento;

public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long>,
		JpaSpecificationExecutor<Alojamiento> {

}
