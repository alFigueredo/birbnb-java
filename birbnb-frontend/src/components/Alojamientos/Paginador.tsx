import type { Dispatch, SetStateAction } from "react";
import type { Pagina } from "../../types/Alojamiento";

interface Props {
  pagina: Pagina;
  setPagina: Dispatch<SetStateAction<Pagina>>;
}

export default function Paginador({ pagina, setPagina }: Props) {
  function cambiarPagina(nueva: number) {
    if (nueva >= 1 && nueva <= pagina.cantPaginas && nueva !== pagina.page)
      setPagina({ ...pagina, page: nueva });
  }

  function getPaginasVisibles() {
    let start = Math.max(pagina.page - 2, 1);
    const end = Math.min(start + 4, pagina.cantPaginas);

    if (end - start < 4) start = Math.max(end - 4, 1);

    const paginas = [];
    for (let i = start; i <= end; i++) paginas.push(i);

    return paginas;
  }

  const paginas = getPaginasVisibles();

  return (
    <div className="paginador-div">
      <button
        onClick={() => cambiarPagina(pagina.page - 1)}
        className={`paginador-button transition ${
          pagina.page === 1 ? "paginador-button-hidden" : "paginador-button-on"
        }`}
      >
        «
      </button>

      {paginas.map((page) => (
        <button
          key={page}
          onClick={() => cambiarPagina(page)}
          className={`paginador-button  transition ${
            pagina.page === page
              ? "paginador-button-off"
              : "paginador-button-on"
          }`}
        >
          {page}
        </button>
      ))}

      <button
        onClick={() => cambiarPagina(pagina.page + 1)}
        className={`paginador-button transition ${
          pagina.page === pagina.cantPaginas
            ? "paginador-button-hidden"
            : "paginador-button-on"
        }`}
      >
        »
      </button>
    </div>
  );
}
