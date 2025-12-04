import { useState, type FormEventHandler } from "react";
import useUsuario from "../../context/useUsuario";
import type { Reserva } from "../../pages/reservas/Reservas";
import { putReserva } from "../../services/api";
import "../../styles/FormularioEditarReserva.css";

export type RangoFechas = {
  fechaInicio: Date;
  fechaFin: Date;
};

export type DetallesReserva = {
  [index: string]: unknown;
  cantHuespedes?: number;
  fechaInicio?: string;
  fechaFin?: string;
};

export type PutReserva = {
  [index: string]: unknown;
  id?: number;
  alojamiento?: number;
  huespedReservador?: number;
  cantHuespedes?: number;
  rangoFechas: RangoFechas;
  estadoReserva?: string;
  fechaAlta?: string;
};

interface Props {
  reserva: Reserva;
  obtenerReservas: () => Promise<void>;
}

export default function FormularioEditarReserva({
  reserva,
  obtenerReservas,
}: Props) {
  const [showForm, setShowForm] = useState(false);
  const toggleForm = () => setShowForm(!showForm);
  const { usuarioActual } = useUsuario();
  const [detallesReserva, setDetallesReserva] = useState<DetallesReserva>({});
  const [mensaje, setMensaje] = useState("");
  const [loadingReserva, setLoadingReserva] = useState(false);

  function parseDateAsLocal(dateStr: string) {
    const [year, month, day] = dateStr.split("-");
    return new Date(+year, +month - 1, +day); // new Date(year, monthIndex, day)
  }

  const reservar: FormEventHandler<HTMLFormElement> = async (e) => {
    e.preventDefault();
    setMensaje("");
    setLoadingReserva(true);

    if (!usuarioActual) {
      setMensaje("Debés seleccionar un usuario.");
      return;
    }

    const reservaEditada: PutReserva = {
      id: reserva.id,
      cantHuespedes: detallesReserva.cantHuespedes || reserva.cantHuespedes,
      rangoFechas: {
        fechaInicio: detallesReserva.fechaInicio
          ? parseDateAsLocal(detallesReserva.fechaInicio)
          : parseDateAsLocal(reserva.rangoFechas.fechaInicio),
        fechaFin: detallesReserva.fechaFin
          ? parseDateAsLocal(detallesReserva.fechaFin)
          : parseDateAsLocal(reserva.rangoFechas.fechaFin),
      },
    };

    try {
      await putReserva(reservaEditada);

      setMensaje("✅ ¡Reserva editada con éxito!");
      obtenerReservas();
      setDetallesReserva({
        ...detallesReserva,
        fechaInicio: "",
        fechaFin: "",
      });
    } catch (err) {
      console.error("Error al editar la reserva:", err);
      setMensaje("❌ " + err);
      // setMensaje("❌ " + err.response.data.message);
    } finally {
      setLoadingReserva(false);
    }
  };

  return (
    <div className="formulario">
      <button
        onClick={toggleForm}
        className={`abrir-formulario-button ${loadingReserva ? "abrir-form-button-off" : "abrir-form-button-on transition"}`}
        data-cy="editar-reserva-button"
      >
        {loadingReserva ? "Editando..." : "Editar"}
      </button>

      {showForm && (
        <div
          className="form-div"
          onKeyDown={(e) => {
            if (e.key === "Escape") setShowForm(false);
          }}
        >
          <h2 className="form-title">🥳Editá tu reserva🥳</h2>
          <button onClick={() => setShowForm(false)} className="form-close">
            ✕
          </button>
          <form onSubmit={reservar} className="form-form">
            <label htmlFor="cantHuespedes" className="form-label">
              Cantidad de huéspedes
            </label>
            <input
              type="number"
              id="cantHuespedes"
              placeholder="Cantidad de huéspedes"
              value={detallesReserva.cantHuespedes || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...detallesReserva,
                  cantHuespedes: parseInt(e.target.value),
                })
              }
              className="form-input"
              min="1"
            />
            <label htmlFor="fechaInicio" className="form-label">
              Fecha de entrada
            </label>
            <input
              type="date"
              id="fechaInicio"
              value={detallesReserva.fechaInicio || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...detallesReserva,
                  fechaInicio: e.target.value,
                })
              }
              className="form-input"
            />

            <label htmlFor="fechaFin" className="form-label">
              Fecha de salida
            </label>
            <input
              type="date"
              id="fechaFin"
              value={detallesReserva.fechaFin || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...detallesReserva,
                  fechaFin: e.target.value,
                })
              }
              className="form-input"
            />
            <button
              type="submit"
              className={`form-submit ${loadingReserva ? "form-submit-off" : "form-submit-on transition"}`}
              disabled={loadingReserva}
            >
              {loadingReserva ? "Editando reserva..." : "Editar reserva"}
            </button>
            {mensaje && (
              <p className="mensaje-reserva" data-cy="mensaje-editar-reserva">
                {mensaje}
              </p>
            )}
            {loadingReserva && <span className="mensaje-reserva-span">⏳</span>}
          </form>
        </div>
      )}
    </div>
  );
}
