import { useState } from "react";

interface Props {
  [index: string]: unknown;
  loading: boolean;
  onConfirm: (motivo: string) => void;
}

export default function VentanaRechazo({ loading, onConfirm }: Props) {
  const [showForm, setShowForm] = useState(false);
  const toggleForm = () => setShowForm(!showForm);
  const [motivo, setMotivo] = useState("");

  function confirmarAccion() {
    setShowForm(false);
    onConfirm(motivo);
  }

  return (
    <div className="ventana">
      <button
        onClick={toggleForm}
        className={`abrir-ventana-button ${loading ? "abrir-vent-button-off" : "abrir-vent-button-on-red transition"}`}
        disabled={loading}
      >
        {loading ? "Rechazando..." : "Rechazar"}
      </button>

      {showForm && (
        <div
          className="vent-div-red"
          onKeyDown={(e) => {
            if (e.key === "Escape") setShowForm(false);
          }}
        >
          <h2 className="vent-title">¿Confirmar rechazo?</h2>
          <button onClick={() => setShowForm(false)} className="vent-close">
            ✕
          </button>
          <div className="vent-form">
            <label htmlFor="motivoRechazo" className="vent-label">
              Motivo de rechazo
            </label>
            <textarea
              id="motivoRechazo"
              className="vent-input"
              rows={3}
              value={motivo || ""}
              onChange={(e) => setMotivo(e.target.value)}
              placeholder="Especificá un motivo si lo deseás..."
            />
            <button
              className={`vent-submit vent-submit-on transition`}
              onClick={confirmarAccion}
            >
              Rechazar reserva
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
