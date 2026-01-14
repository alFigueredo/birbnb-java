import type { Alojamiento } from "./Alojamiento";
import type { Usuario } from "./Usuario";

export type RangoFechas = {
  fechaInicio: Date;
  fechaFin: Date;
};

export type RangoFechasStr = {
  fechaInicio: string;
  fechaFin: string;
};

export type Reserva = {
  id: number;
  alojamiento: Alojamiento;
  huespedReservador: Usuario;
  cantHuespedes: number;
  rangoFechas: RangoFechasStr;
  estadoReserva: string;
  fechaAlta: string;
  fechaActualizacion: string;
  alojamientoNombre: string;
  anfitrionNombre: string;
  huespedReservadorNombre: string;
};

export type DetallesReserva = {
  cantHuespedes: number;
  fechaInicio: string;
  fechaFin: string;
};

export type PostReserva = {
  alojamientoId: number;
  huespedReservadorId: number;
  cantHuespedes: number;
  rangoFechas: RangoFechas;
  precioPorNoche: number;
};

export type PutReserva = {
  id: number;
  cantHuespedes: number;
  rangoFechas: RangoFechas;
};
