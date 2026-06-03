import { useCallback, useEffect, useState } from "react";
import { useUsuario } from "../../context/useAuth";
import { getReservas, getReservasAnfitrion } from "../../services/api";
import "../../styles/Reservas.css";
import SkeletonCard from "../../components/Reservas/SkeletonCard";
import ReservasCard from "../../components/Reservas/ReservasCard";
import type { Notificacion } from "../../types/Notificacion";

export default function ReservasList() {
  const usuarioActual = useUsuario();
  const [reservas, setReservas] = useState([]);
  const [loading, setLoading] = useState(true);

  function sortCriteria(a: Notificacion, b: Notificacion) {
    if (a.fechaAlta < b.fechaAlta) return 1;
    if (a.fechaAlta > b.fechaAlta) return -1;
    return 0;
  }

  const obtenerReservas = useCallback(async () => {
    try {
      setLoading(true);
      const res =
        usuarioActual.tipo === "HUESPED"
          ? await getReservas(usuarioActual.id.toString())
          : await getReservasAnfitrion(usuarioActual.id.toString());
      setReservas(res.data.sort(sortCriteria));
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }, [usuarioActual?.id, usuarioActual?.tipo]);

  useEffect(() => {
    if (!usuarioActual?.id) return;
    obtenerReservas();
  }, [usuarioActual?.id, obtenerReservas]);

  return (
    <main id="reservas-main">
      <h1 id="reservas-title">📅 Reservas 📅</h1>

      {!loading && reservas.length === 0 && (
        <p id="reservas-empty">Este ID no posee reservas❌.</p>
      )}

      {/* RESERVAS DEL USUARIO */}
      <div id="reservas-cards-container">
        {loading
          ? Array.from({ length: 12 }).map((_, i) => <SkeletonCard key={i} />)
          : reservas.map((reserva, index) => (
              <ReservasCard
                key={index}
                reserva={reserva}
                obtenerReservas={obtenerReservas}
              />
            ))}
      </div>
    </main>
  );
}
