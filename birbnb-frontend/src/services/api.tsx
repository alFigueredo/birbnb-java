import axios from "axios";
import type { PostReserva } from "../components/Formulario";
import type { PutReserva } from "../components/Reservas/FormularioEditarReserva.tsx";

const API_BASE_URL =
  (import.meta.env.VITE_PUBLIC_SERVER_URL || "http://localhost:8080") + "/api";

export const getUsuarios = async () => {
  const res = await axios.get(`${API_BASE_URL}/usuarios`);
  return res;
};

export const getAlojamiento = async (alojaId: string) => {
  const res = await axios.get(`${API_BASE_URL}/alojamientos/${alojaId}`);
  return res;
};

export const postReserva = async (reserva: PostReserva) => {
  await axios.post(`${API_BASE_URL}/reservas`, reserva);
};

export const getNotificaciones = async (userId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/usuarios/${userId}/notificaciones`,
  );
  return res;
};

export const getReservas = async (usuarioId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/huespedes/${usuarioId}/reservas`,
  );
  return res;
};

export const getReservasAnfitrion = async (usuarioId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/anfitriones/${usuarioId}/reservas`,
  );
  return res;
};

export const getAlojamientos = async (filtros: { [index: string]: string }) => {
  const queryString = new URLSearchParams(filtros).toString();
  const req = `${API_BASE_URL}/alojamientos${queryString ? `?${queryString}` : ""}`;
  const res = await axios.get(req);
  return res;
};

export const leerNotificacion = async (notiId: string) => {
  const res = await axios.put(`${API_BASE_URL}/notificaciones/${notiId}/leer`);
  return res;
};

export const putReserva = async (reserva: PutReserva) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reserva.id}`,
    reserva,
  );
  return res;
};

export const cancelarReserva = async (reservaId: string, motivo: string) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reservaId}/cancelar`,
    {
      id: reservaId,
      motivo,
    },
  );
  return res;
};

export const confirmarReserva = async (reservaId: string) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reservaId}/confirmar`,
  );
  return res;
};

export const rechazarReserva = async (reservaId: string, motivo: string) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reservaId}/rechazar`,
    {
      id: reservaId,
      motivo,
    },
  );
  return res;
};
