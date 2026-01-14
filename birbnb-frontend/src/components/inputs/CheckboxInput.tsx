import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import Checkbox from "./Checkbox";
import type { Filtro, PartialFiltro } from "../../types/Alojamiento";

interface Props {
  id: {
    [K in keyof Filtro]: Filtro[K] extends string[] ? K : never;
  }[keyof Filtro];
  title: string;
  values: { id: string; label: string }[];
  filtros: PartialFiltro;
  setFiltros: Dispatch<SetStateAction<PartialFiltro>>;
}

export default function CheckboxInput({
  id,
  title,
  values,
  filtros,
  setFiltros,
}: Props) {
  function incluyeFiltro(value: string) {
    return filtros[id]?.includes(value) ?? false;
  }

  function setearFiltros(e: ChangeEvent<HTMLInputElement>) {
    setFiltros({
      ...filtros,
      [id]: incluyeFiltro(e.target.value)
        ? filtros[id]?.filter((v: string) => v !== e.target.value)
        : [...(filtros[id] ?? []), e.target.value],
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
