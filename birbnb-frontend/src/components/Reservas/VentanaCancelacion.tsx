import { useState } from "react";

interface Props {
  [index: string]: unknown;
  loading: boolean;
  onConfirm: (motivo: string) => void;
}

export default function VentanaCancelacion({ loading, onConfirm }: Props) {
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
        {loading ? "Cancelando..." : "Cancelar"}
      </button>

      {showForm && (
        <div
          className="vent-div-red"
          onKeyDown={(e) => {
            if (e.key === "Escape") setShowForm(false);
          }}
        >
          <h2 className="vent-title">¿Cancelar reserva?</h2>
          <button onClick={() => setShowForm(false)} className="vent-close">
            ✕
          </button>
          <div className="vent-form">
            <label htmlFor="motivoCancelacion" className="vent-label">
              Motivo de cancelación
            </label>
            <textarea
              id="motivoCancelacion"
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
              Cancelar reserva
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
