import { BrowserRouter, Route, Routes } from "react-router";
import Layout from "./pages/Layout.tsx";
import Home from "./pages/Home.tsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="alojamientos">
            <Route path=":alojamientoid" />
          </Route>
          <Route path="reservas" />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
