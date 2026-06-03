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
      console.log("[AuthCallback] Already processed, skipping");
      return;
    }
    hasProcessed.current = true;

    console.log("[AuthCallback] Full URL:", window.location.href);
    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");
    console.log(
      "[AuthCallback] Token from URL:",
      token ? `${token.substring(0, 20)}...` : "null",
    );

    if (token) {
      console.log("[AuthCallback] Calling login with token");
      login(token);
      console.log("[AuthCallback] Navigating to /");
      navigate("/", { replace: true });
    } else {
      console.log("[AuthCallback] No token found, redirecting to /login");
      navigate("/login", { replace: true });
    }
  }, [login, navigate]);

  return <div>Iniciando sesión...</div>;
}
