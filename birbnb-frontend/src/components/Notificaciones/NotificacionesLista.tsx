import { useState } from "react";
import type { Notificacion } from "../../types/Notificacion";

interface Props {
  notificaciones: Notificacion[];
  marcarComoLeida: (idNoti: string) => void;
}

export default function NotificacionesLista({
  notificaciones,
  marcarComoLeida,
}: Props) {
  const [expandida, setExpandida] = useState<number | null>(null); //para que se expanda
  return (
    <div id="lista-notificaciones">
      <h4 id="lista-notif-titulo">Notificaciones</h4>
      {notificaciones.length === 0 ? (
        <p id="sin-notificaciones">No hay notificaciones.</p>
      ) : (
        notificaciones.map((n, i) => {
          const estaExpandida = expandida === i;
          const textoCorto =
            n.mensaje.length > 25 ? n.mensaje.slice(0, 25) + "..." : n.mensaje;

          return (
            <div
              key={i}
              onClick={() => {
                setExpandida(estaExpandida ? null : i);
                if (!n.leida) marcarComoLeida(n.id);
              }}
              className={`div-mensaje ${
                n.leida ? "mensaje-leido" : "mensaje-sin-leer"
              }`}
            >
              <p>
                {!n.leida && <span className="span-sin-leer">📩</span>}
                {estaExpandida ? n.mensaje : textoCorto}
              </p>
              {new Date(n.fechaAlta).toLocaleString("es-AR")}
            </div>
          );
        })
      )}
    </div>
  );
}
