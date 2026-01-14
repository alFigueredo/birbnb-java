import { useCallback, useEffect, useState } from "react";
import NotificacionesLista from "./NotificacionesLista";
import { getNotificaciones, leerNotificacion } from "../../services/api";
import type { Notificacion } from "../../types/Notificacion";

interface Props {
  userId: string;
}

export default function Notificaciones({ userId }: Props) {
  const [mostrarNotis, setMostrarNotis] = useState(false);
  const [notificaciones, setNotificaciones] = useState<Notificacion[]>([]);

  function toggleCampanita() {
    setMostrarNotis((prev) => !prev);
    if (!mostrarNotis) cargarNotificaciones();
  }

  function sortCriteria(a: Notificacion, b: Notificacion) {
    if (a.fechaAlta < b.fechaAlta) return 1;
    if (a.fechaAlta > b.fechaAlta) return -1;
    return 0;
  }

  const cargarNotificaciones = useCallback(() => {
    getNotificaciones(userId)
      .then(({ data }) =>
        setNotificaciones(data.length > 0 ? data.sort(sortCriteria) : []),
      )
      .catch((err) => console.error("Error al cargar notificaciones", err));
  }, [userId]);

  useEffect(() => {
    if (!userId) return;
    cargarNotificaciones();
  }, [userId, cargarNotificaciones]);

  useEffect(() => {
    if (!userId) return;
    const intervalo = setInterval(() => cargarNotificaciones(), 7000);
    return () => clearInterval(intervalo);
  });

  function marcarComoLeida(idNoti: string) {
    leerNotificacion(idNoti)
      .then(() => {
        setNotificaciones((prev) =>
          prev.map((n) => (n.id === idNoti ? { ...n, leida: true } : n)),
        );
      })
      .catch((err) =>
        console.error("Error al marcar como leída la notificación", err),
      );
  }

  const sinLeerCount = notificaciones.filter((n) => !n.leida).length;

  return (
    <div id="notificaciones-int">
      <button onClick={toggleCampanita}>
        <img
          src="/noti.svg"
          alt="Notificaciones"
          width={24}
          height={24}
          className={`${sinLeerCount > 0 ? "animate-pulse" : ""}`}
        />
        {sinLeerCount > 0 && (
          <span className="sin-leer-count">{sinLeerCount}</span>
        )}
      </button>

      {mostrarNotis && (
        <NotificacionesLista
          notificaciones={notificaciones}
          marcarComoLeida={marcarComoLeida}
        />
      )}
    </div>
  );
}
