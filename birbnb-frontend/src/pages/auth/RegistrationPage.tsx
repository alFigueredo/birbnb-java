import { type FormEvent, useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router";
import useAuth from "../../context/useAuth";
import type { UserType } from "../../types/Usuario";
import { registrarUsuario } from "../../services/api";
import "../../styles/register.css";

export default function RegistrationPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const googleEmail = searchParams.get("email") || "";
  const googleName = searchParams.get("name") || "";

  const [type, setType] = useState<UserType>("HUESPED");
  const [isSubmitting, setSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!googleEmail || !googleName) {
      navigate("/login", { replace: true });
    }
  }, [googleEmail, googleName, navigate]);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setSubmitting(true);
    setError(null);

    try {
      const response = await registrarUsuario({
        nombre: googleName,
        email: googleEmail,
        tipoUsuario: type,
      });

      const { token } = response.data;
      login(token);
      navigate("/", { replace: true });
    } catch (err) {
      const message =
        err instanceof Error ? err.message : "No se pudo completar el registro";
      setError(message);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <main className="register-main">
      <h1 className="register-title">Completá tu perfil</h1>

      <div className="register-panel">
        <form className="form-grid" onSubmit={handleSubmit}>
          <div className="form-field">
            <label htmlFor="name">Nombre (desde Google)</label>
            <input
              id="name"
              value={googleName}
              disabled
              style={{ opacity: 0.7, cursor: "not-allowed" }}
            />
          </div>
          <div className="form-field">
            <label htmlFor="email">Email (desde Google)</label>
            <input
              id="email"
              value={googleEmail}
              disabled
              style={{ opacity: 0.7, cursor: "not-allowed" }}
            />
          </div>

          <fieldset
            className="form-field"
            style={{
              border: "1px solid #e5e7eb",
              padding: "0.75rem",
              borderRadius: "10px",
            }}
          >
            <legend className="muted" style={{ padding: "0 0.3rem" }}>
              Elegí tu rol
            </legend>
            <div className="button-row">
              <button
                type="button"
                className={`btn ${type === "HUESPED" ? "" : "secondary"}`}
                onClick={() => setType("HUESPED")}
              >
                Huésped
              </button>
              <button
                type="button"
                className={`btn ${type === "ANFITRION" ? "" : "secondary"}`}
                onClick={() => setType("ANFITRION")}
              >
                Anfitrión
              </button>
            </div>
          </fieldset>

          {error && <p className="error">Error: {error}</p>}

          <div className="button-row">
            <button className="btn" type="submit" disabled={isSubmitting}>
              {isSubmitting ? "Registrando…" : "Completar registro"}
            </button>
          </div>
        </form>
      </div>
    </main>
  );
}
