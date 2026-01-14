import { useState, type FormEventHandler } from "react";
import useUsuario from "../context/useUsuario";
import { postReserva } from "../services/api";
import "../styles/Formulario.css";
import type { DetallesReserva, PostReserva } from "../types/Reserva";
import type { Alojamiento } from "../types/Alojamiento";

interface Props {
  aloja: Alojamiento;
}

export default function Formulario({ aloja }: Props) {
  const [showForm, setShowForm] = useState(false);
  const toggleForm = () => setShowForm(!showForm);
  const { usuarioActual } = useUsuario();
  const [detallesReserva, setDetallesReserva] =
    useState<DetallesReserva | null>(null);
  const EMPTY_DETALLES: DetallesReserva = {
    cantHuespedes: 0,
    fechaInicio: "",
    fechaFin: "",
  };
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

    const reserva: PostReserva = {
      huespedReservadorId: usuarioActual.id,
      cantHuespedes: detallesReserva?.cantHuespedes || 0,
      alojamientoId: aloja.id,
      rangoFechas: {
        fechaInicio: parseDateAsLocal(detallesReserva?.fechaInicio || ""),
        fechaFin: parseDateAsLocal(detallesReserva?.fechaFin || ""),
      },
      precioPorNoche: aloja.precioPorNoche,
    };

    try {
      await postReserva(reserva);

      setMensaje("✅ ¡Reserva creada con éxito!");
      setDetallesReserva({
        ...(detallesReserva ?? EMPTY_DETALLES),
        fechaInicio: "",
        fechaFin: "",
      });
    } catch (err) {
      console.error("Error al crear la reserva:", err);
      setMensaje("❌ " + err);
      // setMensaje("❌ " + err.response.data.message);
    } finally {
      setLoadingReserva(false);
    }
  };

  return (
    <div id="formulario">
      <button
        onClick={toggleForm}
        id="abrir-formulario-button"
        className={`${usuarioActual?.tipo === "ANFITRION" || loadingReserva ? "abrir-form-button-off" : "abrir-form-button-on transition"}`}
        disabled={usuarioActual?.tipo === "ANFITRION"}
      >
        {loadingReserva ? "Reservando..." : "Reservar"}
      </button>

      {showForm && (
        <div
          id="form-div"
          onKeyDown={(e) => {
            if (e.key === "Escape") setShowForm(false);
          }}
        >
          <h2 id="form-title">🥳Reserva tu estadía🥳</h2>
          <button onClick={() => setShowForm(false)} id="form-close">
            ✕
          </button>
          <form id="form-form" onSubmit={reservar}>
            <label htmlFor="cantHuespedes" className="form-label">
              Cantidad de huéspedes
            </label>
            <input
              type="number"
              id="cantHuespedes"
              placeholder="Cantidad de huéspedes"
              value={detallesReserva?.cantHuespedes || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...(detallesReserva ?? EMPTY_DETALLES),
                  cantHuespedes: parseInt(e.target.value),
                })
              }
              className="form-input"
              min="1"
              required
            />
            <label htmlFor="fechaInicio" className="form-label">
              Fecha de entrada
            </label>
            <input
              type="date"
              id="fechaInicio"
              value={detallesReserva?.fechaInicio || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...(detallesReserva ?? EMPTY_DETALLES),
                  fechaInicio: e.target.value,
                })
              }
              className="form-input"
              required
            />

            <label htmlFor="fechaFin" className="form-label">
              Fecha de salida
            </label>
            <input
              type="date"
              id="fechaFin"
              value={detallesReserva?.fechaFin || ""}
              onChange={(e) =>
                setDetallesReserva({
                  ...(detallesReserva ?? EMPTY_DETALLES),
                  fechaFin: e.target.value,
                })
              }
              className="form-input"
              required
            />
            <button
              type="submit"
              id="form-submit"
              className={`${loadingReserva ? "form-submit-off" : "form-submit-on transition"}`}
              disabled={loadingReserva}
            >
              {loadingReserva ? "Realizando reserva..." : "Realizar reserva"}
            </button>
            {mensaje && (
              <p id="mensaje-reserva" className="text-sm mt-2">
                {mensaje}
              </p>
            )}
            {loadingReserva && (
              <span
                id="mensaje-reserva-span"
                className="ml-2 animate-spin h-5 w-5"
              >
                ⏳
              </span>
            )}
          </form>
        </div>
      )}
    </div>
  );
}
