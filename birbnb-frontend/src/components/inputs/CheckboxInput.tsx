import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import type { Filtro } from "../Filtros";
import Checkbox from "./Checkbox";

interface Props {
  id: "caractPedidas";
  title: string;
  values: { id: string; label: string }[];
  filtros: Filtro;
  setFiltros: Dispatch<SetStateAction<Filtro>>;
}

export default function CheckboxInput({
  id,
  title,
  values,
  filtros,
  setFiltros,
}: Props) {
  function incluyeFiltro(value: string) {
    return filtros[id].includes(value);
  }

  function setearFiltros(e: ChangeEvent<HTMLInputElement>) {
    setFiltros({
      ...filtros,
      [id]: incluyeFiltro(e.target.value)
        ? filtros[id].filter((v: string) => v !== e.target.value)
        : [...filtros[id], e.target.value],
    });
  }

  return (
    <div>
      <label id={id} className="text-input-label">
        {title}
      </label>
      <div className="checkboxes-div">
        {values.map((value) => (
          <Checkbox
            key={value.id}
            id={value.id}
            label={value.label}
            incluyeFiltro={incluyeFiltro}
            setearFiltros={setearFiltros}
          />
        ))}
      </div>
    </div>
  );
}
