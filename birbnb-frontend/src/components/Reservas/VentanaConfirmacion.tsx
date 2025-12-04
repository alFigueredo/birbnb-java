import { useState } from "react";

interface Props {
  [index: string]: unknown;
  loading: boolean;
  onConfirm: () => void;
}

export default function VentanaConfirmacion({ loading, onConfirm }: Props) {
  const [showForm, setShowForm] = useState(false);
  const toggleForm = () => setShowForm(!showForm);

  function confirmarAccion() {
    setShowForm(false);
    onConfirm();
  }

  return (
    <div className="ventana">
      <button
        onClick={toggleForm}
        className={`abrir-ventana-button ${loading ? "abrir-vent-button-off" : "abrir-vent-button-on-blue transition"}`}
        disabled={loading}
      >
        {loading ? "Confirmando..." : "Confirmar"}
      </button>

      {showForm && (
        <div
          className="vent-div-blue"
          onKeyDown={(e) => {
            if (e.key === "Escape") setShowForm(false);
          }}
        >
          <h2 className="vent-title">¿Confirmar reserva?</h2>
          <button onClick={() => setShowForm(false)} className="vent-close">
            ✕
          </button>
          <div className="vent-form">
            <button
              className={`vent-submit vent-submit-on transition`}
              onClick={confirmarAccion}
            >
              Confirmar reserva
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
