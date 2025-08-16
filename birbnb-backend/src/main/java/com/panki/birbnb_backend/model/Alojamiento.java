package com.panki.birbnb_backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.panki.birbnb_backend.model.enums.Caracteristica;
import com.panki.birbnb_backend.model.enums.Moneda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Alojamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anfitrion_id")
	@JsonBackReference
	private final Usuario anfitrion;
	private final String nombre;
	private final String descripcion;
	private final float precioPorNoche;
	@Enumerated(EnumType.STRING)
	private final Moneda moneda;
	private final String horarioCheckIn;
	private final String horarioCheckOut;
	@OneToOne
	@JoinColumn(name = "direccion_id")
	private final Direccion direccion;
	private final int cantHuespedesMax;
	@ElementCollection(targetClass = Caracteristica.class)
	@Enumerated(EnumType.STRING)
	private final Set<Caracteristica> caracteristicas;
	@OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<Reserva> reservas = new ArrayList<>();
	@OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<Foto> fotos = new ArrayList<>();

	protected Alojamiento() {
		this.anfitrion = null;
		this.nombre = "";
		this.descripcion = null;
		this.precioPorNoche = 0;
		this.moneda = null;
		this.horarioCheckIn = null;
		this.horarioCheckOut = null;
		this.direccion = null;
		this.cantHuespedesMax = 0;
		this.caracteristicas = null;
	}

	public Alojamiento(Usuario anfitrion, String nombre, String descripcion, float precioPorNoche, Moneda moneda,
			String horarioCheckIn, String horarioCheckOut, Direccion direccion, int cantHuespedesMax,
			Set<Caracteristica> caracteristicas) {
		this.anfitrion = anfitrion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioPorNoche = precioPorNoche;
		this.moneda = moneda;
		this.horarioCheckIn = horarioCheckIn;
		this.horarioCheckOut = horarioCheckOut;
		this.direccion = direccion;
		this.cantHuespedesMax = cantHuespedesMax;
		this.caracteristicas = caracteristicas;
	}

	public Long getId() {
		return id;
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}

	public Long getAnfitrionId() {
		return getAnfitrion().getId();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getPrecioPorNoche() {
		return precioPorNoche;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public String getHorarioCheckIn() {
		return horarioCheckIn;
	}

	public String getHorarioCheckOut() {
		return horarioCheckOut;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public int getCantHuespedesMax() {
		return cantHuespedesMax;
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void agregarFotos(String descripcion, String path) {
		final Foto foto = new Foto(descripcion, path, this);
		fotos.add(foto);
	}

	public boolean estasDisponibleEn(Reserva nuevaReserva) {
		return getReservas().stream().allMatch((reserva) -> !reserva.haySolapamiento(nuevaReserva));
	}

	public boolean tuPrecioEstaDentroDe(float valorMinimo, float valorMaximo) {
		return getPrecioPorNoche() >= valorMinimo && getPrecioPorNoche() <= valorMaximo;
	}

	public boolean tenesCaracteristica(Caracteristica caracteristica) {
		return getCaracteristicas().contains(caracteristica);
	}

	public boolean puedenAlojarse(int cantHuespedes) {
		return cantHuespedes <= getCantHuespedesMax();
	}

}
