import axios from "axios";
import type { PostReserva, PutReserva } from "../types/Reserva";
import type { PostUsuario } from "../types/Usuario";
import { getAuthHeader } from "./client";

const VITE_PUBLIC_SERVER_URL =
  import.meta.env.VITE_PUBLIC_SERVER_URL || "http://localhost:8080";
const API_BASE_URL = VITE_PUBLIC_SERVER_URL + "/api";
export const GOOGLE_LOGIN = `${VITE_PUBLIC_SERVER_URL}/oauth2/authorization/google`;

export const getUsuarios = async () => {
  const res = await axios.get(`${API_BASE_URL}/usuarios`, getAuthHeader());
  return res;
};

export const getAlojamiento = async (alojaId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/alojamientos/${alojaId}`,
    getAuthHeader(),
  );
  return res;
};

export const postReserva = async (reserva: PostReserva) => {
  await axios.post(`${API_BASE_URL}/reservas`, reserva, getAuthHeader());
};

export const getNotificaciones = async (userId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/usuarios/${userId}/notificaciones`,
    getAuthHeader(),
  );
  return res;
};

export const getReservas = async (usuarioId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/huespedes/${usuarioId}/reservas`,
    getAuthHeader(),
  );
  return res;
};

export const getReservasAnfitrion = async (usuarioId: string) => {
  const res = await axios.get(
    `${API_BASE_URL}/anfitriones/${usuarioId}/reservas`,
    getAuthHeader(),
  );
  return res;
};

export const getAlojamientos = async (filtros: { [index: string]: string }) => {
  const queryString = new URLSearchParams(filtros).toString();
  const req = `${API_BASE_URL}/alojamientos${queryString ? `?${queryString}` : ""}`;
  const res = await axios.get(req, getAuthHeader());
  return res;
};

export const leerNotificacion = async (notiId: string) => {
  const res = await axios.put(
    `${API_BASE_URL}/notificaciones/${notiId}/leer`,
    getAuthHeader(),
  );
  return res;
};

export const putReserva = async (reserva: PutReserva) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reserva.id}`,
    reserva,
    getAuthHeader(),
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
    getAuthHeader(),
  );
  return res;
};

export const confirmarReserva = async (reservaId: string) => {
  const res = await axios.put(
    `${API_BASE_URL}/reservas/${reservaId}/confirmar`,
    getAuthHeader(),
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
    getAuthHeader(),
  );
  return res;
};

export const registrarUsuario = async (usuario: PostUsuario) =>
  await axios.post(`${API_BASE_URL}/auth/register`, usuario, getAuthHeader());
