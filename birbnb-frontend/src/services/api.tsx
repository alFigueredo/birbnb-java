import axios from "axios";

const API_BASE_URL =
  import.meta.env.VITE_PUBLIC_SERVER_URL || "http://localhost:8080";

export const getUsuarios = async () => {
  const res = await axios.get(`${API_BASE_URL}/usuario`);
  return res;
};

export const getAlojamiento = async (alojaId: string) => {
  const res = await axios.get(`${API_BASE_URL}/alojamiento/${alojaId}`);
  return res;
};

export const postReserva = async (reserva) => {
  await axios.post(`${API_BASE_URL}/reserva`, reserva);
};

export const getNotificaciones = async (userId: string) => {
  const res = await axios.get(`${API_BASE_URL}/usuario/${userId}/notificacion`);
  return res;
};

export const getReservas = async (usuarioId: string) => {
  const res = await axios.get(`${API_BASE_URL}/huesped/${usuarioId}/reserva`);
  return res;
};

export const getReservasAnfitrion = async (usuarioId: string) => {
  const res = await axios.get(`${API_BASE_URL}/anfitrion/${usuarioId}/reserva`);
  return res;
};

export const getAlojamientos = async (filtros) => {
  const queryString = new URLSearchParams(filtros).toString();
  const req = `${API_BASE_URL}/alojamiento${queryString ? `?${queryString}` : ""}`;
  const res = await axios.get(req);
  return res;
};

export const leerNotificacion = async (notiId: string) => {
  const res = await axios.put(`${API_BASE_URL}/notificacion/${notiId}/leer`);
  return res;
};

export const putReserva = async (reserva) => {
  const res = await axios.put(
    `${API_BASE_URL}/reserva/${reserva._id}`,
    reserva,
  );
  return res;
};

export const cancelarReserva = async (reservaId: string, motivo: string) => {
  const res = await axios.put(`${API_BASE_URL}/reserva/${reservaId}/cancelar`, {
    _id: reservaId,
    motivo,
  });
  return res;
};

export const confirmarReserva = async (reservaId: string) => {
  const res = await axios.put(`${API_BASE_URL}/reserva/${reservaId}/confirmar`);
  return res;
};

export const rechazarReserva = async (reservaId: string, motivo: string) => {
  const res = await axios.put(`${API_BASE_URL}/reserva/${reservaId}/rechazar`, {
    _id: reservaId,
    motivo,
  });
  return res;
};
