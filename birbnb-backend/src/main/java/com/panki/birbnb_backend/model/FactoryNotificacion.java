package com.panki.birbnb_backend.model;

public class FactoryNotificacion {

	private static Usuario obtenerUsuario(Reserva reserva) {
		return reserva.reservaRespondidaPorAnfitrion() ? reserva.getHuespedReservador()
				: reserva.getAlojamiento().getAnfitrion();
	}

	private static String crearSegunReserva(Reserva reserva) {
		String mensaje = "Huesped: " + reserva.getHuespedReservador().getNombre() +
				", Fecha: " + reserva.getRangoFechas().getFechaInicio() +
				", Cantidad de días: " + reserva.getRangoFechas().cantidadDias() +
				", Alojamiento: " + reserva.getAlojamiento().getNombre() +
				", Estado de la reserva: " + reserva.getEstadoReserva();
		return mensaje;
	}

	public static void generarNotificacion(Reserva reserva, String motivo) {
		final Usuario usuario = obtenerUsuario(reserva);
		String mensaje = crearSegunReserva(reserva);
		if (!motivo.isBlank())
			mensaje += ", Motivo: " + motivo;
		usuario.agregarNotificacion(mensaje);
	}

}
