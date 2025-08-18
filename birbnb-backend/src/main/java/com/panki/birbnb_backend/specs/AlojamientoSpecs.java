package com.panki.birbnb_backend.specs;

import java.util.Locale;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.model.enums.Caracteristica;

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

	public static Specification<Alojamiento> conCaracteristicas(Set<Caracteristica> caracteristicas) {
		return (root, query, cb) -> {
			if (caracteristicas == null || caracteristicas.isEmpty())
				return null;
			final Expression<Set<Caracteristica>> field = root.get("caracteristicas");
			Predicate p = cb.conjunction();
			for (final Caracteristica caracteristica : caracteristicas)
				p = cb.and(p, cb.isMember(caracteristica, field));
			return p;
		};
	}

	public static Specification<Alojamiento> conCiudad(String ciudad) {
		return (root, query, cb) -> {
			if (ciudad == null || ciudad.isBlank())
				return null;
			final String[] tokens = ciudad.trim().toLowerCase(Locale.ROOT).split("\\s+");
			final Expression<String> field = cb.lower(root.get("direccion").get("ciudad").get("nombre"));
			Predicate p = cb.conjunction();
			for (final String token : tokens)
				p = cb.and(p, cb.like(field, "%" + token + "%"));
			return p;
		};
	}

	public static Specification<Alojamiento> conPais(String pais) {
		return (root, query, cb) -> {
			if (pais == null || pais.isBlank())
				return null;
			final String[] tokens = pais.trim().toLowerCase(Locale.ROOT).split("\\s+");
			final Expression<String> field = cb.lower(root.get("direccion").get("ciudad").get("pais").get("nombre"));
			Predicate p = cb.conjunction();
			for (final String token : tokens)
				p = cb.and(p, cb.like(field, "%" + token + "%"));
			return p;
		};
	}

}
