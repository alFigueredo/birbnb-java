import { BrowserRouter, Route, Routes } from "react-router";
import Layout from "./pages/Layout.tsx";
import Home from "./pages/Home.tsx";
import Alojamientos from "./pages/alojamientos/Alojamientos.tsx";
import AlojamientoPage from "./pages/alojamientos/AlojamientoPage.tsx";
import Reservas from "./pages/reservas/Reservas.tsx";

function App() {
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
}

export default App;
