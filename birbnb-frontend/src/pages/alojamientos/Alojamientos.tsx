import { useCallback, useEffect, useState } from "react";
import "../../styles/Alojamientos.css";
import AlojamientoCard from "../../components/Alojamientos/AlojamientoCard";
import SkeletonCard from "../../components/Alojamientos/SkeletonCard";
import BarraLateral from "../../components/BarraLateral";
import { getAlojamientos } from "../../services/api";
import Paginador, {
  type Pagina,
} from "../../components/Alojamientos/Paginador";
import type { Filtro } from "../../components/Filtros";
import type { Usuario } from "../../context/useUsuario";

export type Direccion = {
  calle: string;
  altura: string;
  latitud: string;
  longitud: string;
};

export type Foto = {
  path: string;
  descripcion: string;
};

export type Alojamiento = {
  [index: string]: unknown;
  id: number;
  nombre: string;
  descripcion: string;
  anfitrion: Usuario;
  precioPorNoche: number;
  cantHuespedesMax: number;
  direccion: Direccion;
  fotos: Foto[];
};

export default function Alojamientos() {
  const [alojamientos, setAlojamientos] = useState<Alojamiento[]>([]);
  const [pagina, setPagina] = useState<Pagina>({
    page: 1,
    limit: 12,
    total: 0,
    cantPaginas: 1,
  });
  const [filtros, setFiltros] = useState<Filtro>({
    caractPedidas: [],
  });
  const [loading, setLoading] = useState(true);
  const [sidebarOpen, setSidebarOpen] = useState(false); //para el dezplazamiento al abrir filtros

  function limpiarFiltros(obj: Filtro) {
    return Object.fromEntries(
      Object.entries(obj)
        .filter(
          ([key, val]) =>
            val &&
            (typeof val === "number" || val.length) &&
            key !== "cantPaginas",
        )
        .map(([key, val]) => [key, val.toString()]),
    );
  }

  const buscarAlojamientos = useCallback(async () => {
    const filtrosLimpios = limpiarFiltros({
      ...filtros,
      page: pagina.page - 1,
    });
    const res = await getAlojamientos(filtrosLimpios);
    setAlojamientos(res.data.content || []);
    const nueva = {
      page: res.data.number + 1 || 1,
      limit: res.data.size || 12,
      total: res.data.totalElements || 0,
      cantPaginas: res.data.totalPages || 0,
    };
    setPagina(nueva);
  }, [filtros, pagina.page]);

  useEffect(() => {
    (async () => {
      setLoading(true);
      try {
        await buscarAlojamientos();
      } catch (err) {
        console.error("Error al obtener alojamientos:", err);
      } finally {
        setLoading(false);
      }
    })();
  }, [filtros, pagina.page, buscarAlojamientos]);

  return (
    <main id="main-alojamientos">
      <div id="div-alojamientos">
        <div
          id="div-listado-alojamientos"
          className={`${sidebarOpen ? "sidebar-open" : "sidebar-close"} transition-all`}
        >
          <h1 id="alojamientos-title">🏡Listado de Alojamientos🏡</h1>

          <BarraLateral
            filtros={filtros}
            setFiltros={setFiltros}
            sidebarOpen={sidebarOpen}
            setSidebarOpen={setSidebarOpen}
          />

          {/* Contenedor de tarjetas */}
          <div id="aloja-cards-container">
            {loading
              ? Array.from({ length: 12 }).map((_, i) => (
                  <SkeletonCard key={i} />
                ))
              : alojamientos.map((aloj) => (
                  <AlojamientoCard key={aloj.id} aloj={aloj} />
                ))}
          </div>

          {/* Paginador */}
          {pagina.cantPaginas > 1 && (
            <Paginador pagina={pagina} setPagina={setPagina} />
          )}
        </div>
      </div>
    </main>
  );
}
