import { BrowserRouter, Route, Routes } from "react-router";
import Layout from "../pages/Layout";
import Home from "../pages/Home";
import Alojamientos from "../pages/alojamientos/Alojamientos";
import AlojamientoPage from "../pages/alojamientos/AlojamientoPage";
import Reservas from "../pages/reservas/Reservas";

export const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="alojamientos">
            <Route index element={<Alojamientos />} />
            <Route path=":alojamientoid" element={<AlojamientoPage />} />
          </Route>
          <Route path="reservas" element={<Reservas />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
