import type { Dispatch, SetStateAction } from "react";
import Filtros, { type Filtro } from "./Filtros";
import "../styles/Filtros.css";

interface Props {
  filtros: Filtro;
  setFiltros: Dispatch<SetStateAction<Filtro>>;
  sidebarOpen: boolean;
  setSidebarOpen: Dispatch<SetStateAction<boolean>>;
}

export default function BarraLateral({
  filtros,
  setFiltros,
  sidebarOpen,
  setSidebarOpen,
}: Props) {
  return (
    <>
      <button id="boton-filtrar" onClick={() => setSidebarOpen(!sidebarOpen)}>
        Filtrar
      </button>

      <div
        id="barra-lateral-div"
        className={`transform transition-transform ${
          sidebarOpen ? "sidebar-div-open" : "sidebar-div-close"
        }`}
        onKeyDown={(e) => {
          if (e.key === "Escape") setSidebarOpen(false);
        }}
      >
        <button onClick={() => setSidebarOpen(false)} id="barra-lateral-close">
          ✕
        </button>

        <div id="filtros-div">
          <Filtros filtros={filtros} setFiltros={setFiltros} />
        </div>
      </div>
    </>
  );
}
