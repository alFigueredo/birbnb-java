import { useEffect, useRef } from "react";
import { useNavigate } from "react-router";
import useAuth from "../../context/useAuth";

export default function AuthCallbackPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const hasProcessed = useRef(false);

  useEffect(() => {
    // Prevent running multiple times (React StrictMode or re-renders)
    if (hasProcessed.current) {
      return;
    }
    hasProcessed.current = true;

    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");

    if (token) {
      login(token);
      navigate("/", { replace: true });
    } else {
      navigate("/login", { replace: true });
    }
  }, [login, navigate]);

  return <div>Iniciando sesión...</div>;
}
