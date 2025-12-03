import { BrowserRouter, Route, Routes } from "react-router";
import Layout from "./pages/Layout.tsx";
import Home from "./pages/Home.tsx";
import Alojamientos from "./pages/alojamientos/Alojamientos.tsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="alojamientos" element={<Alojamientos />}>
            <Route path=":alojamientoid" />
          </Route>
          <Route path="reservas" />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
