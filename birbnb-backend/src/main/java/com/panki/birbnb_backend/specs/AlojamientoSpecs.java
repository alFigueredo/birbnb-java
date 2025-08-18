package com.panki.birbnb_backend.specs;

import java.util.Locale;

import org.springframework.data.jpa.domain.Specification;

import com.panki.birbnb_backend.model.Alojamiento;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class AlojamientoSpecs {

	public static Specification<Alojamiento> conNombre(String nombre) {
		return (root, query, cb) -> {
			if (nombre == null || nombre.isBlank())
				return null;
			final String[] tokens = nombre.trim().toLowerCase(Locale.ROOT).split("\\s+");
			final Expression<String> field = cb.lower(root.get("nombre"));
			Predicate p = cb.conjunction();
			for (final String token : tokens)
				p = cb.and(p, cb.like(field, "%" + token + "%"));
			return p;
		};
	}

	public static Specification<Alojamiento> conPrecioGt(Integer precioGt) {
		return (root, query, cb) -> {
			if (precioGt == null || precioGt == 0)
				return null;
			return cb.greaterThanOrEqualTo(root.get("precioPorNoche"), precioGt);
		};
	}

	public static Specification<Alojamiento> conPrecioLt(Integer precioLt) {
		return (root, query, cb) -> {
			if (precioLt == null || precioLt == 0)
				return null;
			return cb.lessThanOrEqualTo(root.get("precioPorNoche"), precioLt);
		};
	}
}
