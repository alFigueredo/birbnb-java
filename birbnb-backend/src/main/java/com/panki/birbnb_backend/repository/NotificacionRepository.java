package com.panki.birbnb_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.model.Usuario;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

}
