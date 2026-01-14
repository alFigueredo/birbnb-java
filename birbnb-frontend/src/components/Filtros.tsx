import { useEffect, useState, type Dispatch, type SetStateAction } from "react";
import TextInput from "./inputs/TextInput";
import NumberInput from "./inputs/NumberInput";
import CheckboxInput from "./inputs/CheckboxInput";
import type { PartialFiltro } from "../types/Alojamiento";

interface Props {
  filtros: PartialFiltro;
  setFiltros: Dispatch<SetStateAction<PartialFiltro>>;
}

export default function Filtros({ filtros, setFiltros }: Props) {
  const [filter, setFilter] = useState(filtros);

  useEffect(() => {
    const delay = setTimeout(() => {
      setFiltros(filter);
    }, 700);
    return () => clearTimeout(delay);
  }, [filter, setFiltros]);

  return (
    <div>
      <h1 id="filtros-title">🌟Filtros🌟</h1>
      <div id="inputs-div">
        <TextInput
          id="nombre"
          label="Nombre del alojamiento"
          filtros={filter}
          setFiltros={setFilter}
        />
        <TextInput
          id="ciudad"
          label="Ciudad"
          filtros={filter}
          setFiltros={setFilter}
        />
        <TextInput
          id="pais"
          label="Pais"
          filtros={filter}
          setFiltros={setFilter}
        />
        <NumberInput
          id="precioGt"
          label="Precio mínimo"
          step="1000"
          filtros={filter}
          setFiltros={setFilter}
        />
        <NumberInput
          id="precioLt"
          label="Precio máximo"
          step="1000"
          filtros={filter}
          setFiltros={setFilter}
        />
        <NumberInput
          id="cantHuespedes"
          label="Cantidad de huéspedes"
          step="1"
          filtros={filter}
          setFiltros={setFilter}
        />
        <div id="lat-long-div">
          <TextInput
            id="lat"
            label="Latitud"
            filtros={filter}
            setFiltros={setFilter}
          />
          <TextInput
            id="long"
            label="Longitud"
            filtros={filter}
            setFiltros={setFilter}
          />
          {(filtros.lat && !filtros.long) || (!filtros.lat && filtros.long) ? (
            <p className="text-sm text-red-600">
              Completá ambos campos de coordenadas
            </p>
          ) : null}
        </div>
        <CheckboxInput
          id="caractPedidas"
          title="Características"
          filtros={filter}
          setFiltros={setFilter}
          values={[
            { id: "WIFI", label: "Wi-Fi" },
            { id: "PISCINA", label: "Piscina" },
            { id: "ESTACIONAMIENTO", label: "Estacionamiento" },
            { id: "MASCOTAS_PERMITIDAS", label: "Mascotas permitidas" },
          ]}
        />
      </div>
    </div>
  );
}
