import { useState } from "react";
import type { Reserva } from "../../pages/reservas/Reservas";
import useUsuario from "../../context/useUsuario";
import {
  cancelarReserva,
  confirmarReserva,
  rechazarReserva,
} from "../../services/api";
import FormularioEditarReserva from "./FormularioEditarReserva";
import VentanaCancelacion from "./VentanaCancelacion";
import VentanaConfirmacion from "./VentanaConfirmacion";
import VentanaRechazo from "./VentanaRechazo";

interface Props {
  reserva: Reserva;
  obtenerReservas: () => Promise<void>;
}

export default function ReservasCard({ reserva, obtenerReservas }: Props) {
  const { usuarioActual } = useUsuario();
  const [loading, setLoading] = useState({
    cancelar: false,
    confirmar: false,
    rechazar: false,
  });

  function formatDate(fecha: string) {
    return fecha.split("T")[0].split("-").reverse().join("/");
  }

  function cancelar(motivo: string) {
    setLoading({ ...loading, cancelar: true });
    cancelarReserva(reserva.id?.toString() ?? "", motivo)
      .then(() => {
        obtenerReservas();
      })
      .catch((err) => console.error("Error al cancelar la reserva", err))
      .finally(() => setLoading({ ...loading, cancelar: true }));
  }

  function confirmar() {
    setLoading({ ...loading, confirmar: true });
    confirmarReserva(reserva.id?.toString() ?? "")
      .then(() => {
        obtenerReservas();
      })
      .catch((err) => console.error("Error al confirmar la reserva", err))
      .finally(() => setLoading({ ...loading, confirmar: true }));
  }

  function rechazar(motivo: string) {
    setLoading({ ...loading, rechazar: true });
    rechazarReserva(reserva.id?.toString() ?? "", motivo)
      .then(() => {
        obtenerReservas();
      })
      .catch((err) => console.error("Error al rechazar la reserva", err))
      .finally(() => setLoading({ ...loading, rechazar: true }));
  }

  return (
    <div className="reserva-card">
      <p className="reserva-text-lg">
        Alojamiento: {reserva.alojamiento?.nombre}
      </p>
      <p className="reserva-text-lg">
        {usuarioActual?.tipo === "HUESPED"
          ? `Anfitrión: ${reserva.alojamiento?.anfitrion.nombre}`
          : `Huésped: ${reserva.huespedReservador?.nombre}`}
      </p>
      <p className="reserva-text-lg">Estado: {reserva.estadoReserva}</p>
      <p className="reserva-text-md">
        Cantidad de huéspedes: {reserva.cantHuespedes}
      </p>
      <p className="reserva-text-sm">
        Desde: {formatDate(reserva.rangoFechas?.fechaInicio ?? "")}
      </p>
      <p className="reserva-text-sm">
        Hasta: {formatDate(reserva.rangoFechas?.fechaFin ?? "")}
      </p>
      <p className="reserva-text-sm reserva-text-last">
        Alta: {new Date(reserva.fechaAlta || "").toLocaleString("es-AR")}
      </p>
      <div className="reserva-card-buttons">
        {usuarioActual?.tipo === "HUESPED" &&
          (reserva.estadoReserva === "PENDIENTE" ||
            reserva.estadoReserva === "CONFIRMADA") && (
            <FormularioEditarReserva
              reserva={reserva}
              obtenerReservas={obtenerReservas}
            />
          )}
        {usuarioActual?.tipo === "HUESPED" &&
          (reserva.estadoReserva === "PENDIENTE" ||
            reserva.estadoReserva === "CONFIRMADA") && (
            <VentanaCancelacion
              loading={loading.cancelar}
              onConfirm={cancelar}
            />
          )}
        {usuarioActual?.tipo === "ANFITRION" &&
          reserva.estadoReserva === "PENDIENTE" && (
            <VentanaConfirmacion
              loading={loading.confirmar}
              onConfirm={confirmar}
            />
          )}
        {usuarioActual?.tipo === "ANFITRION" &&
          reserva.estadoReserva === "PENDIENTE" && (
            <VentanaRechazo loading={loading.rechazar} onConfirm={rechazar} />
          )}
      </div>
    </div>
  );
}
