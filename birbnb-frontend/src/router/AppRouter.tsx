import { BrowserRouter, Route, Routes } from "react-router";
import Layout from "../pages/Layout";
import Home from "../pages/Home";
import Alojamientos from "../pages/alojamientos/Alojamientos";
import AuthCallbackPage from "../pages/auth/AuthCallbackPage";
import AlojamientoPage from "../pages/alojamientos/AlojamientoPage";
import Reservas from "../pages/reservas/Reservas";
import RegistrationPage from "../pages/auth/RegistrationPage";
import Protected from "./Protected";
import LoginPage from "../pages/auth/LoginPage";

export const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/auth/callback" element={<AuthCallbackPage />} />
          <Route path="/auth/register" element={<RegistrationPage />} />
          <Route element={<Protected />}>
            <Route path="alojamientos">
              <Route index element={<Alojamientos />} />
              <Route path=":alojamientoid" element={<AlojamientoPage />} />
            </Route>
            <Route path="reservas" element={<Reservas />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
