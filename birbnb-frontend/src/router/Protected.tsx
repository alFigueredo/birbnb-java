import { Navigate, Outlet } from "react-router";
import useAuth from "../context/useAuth";

export default function Protected() {
  const { userId, initialized } = useAuth();
  if (!initialized) {
    return <div>Cargando sesión...</div>;
  }
  if (!userId) {
    return <Navigate to="/login" replace />;
  }
  return <Outlet />;
}
