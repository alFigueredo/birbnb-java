import type { Dispatch, SetStateAction } from "react";
import type { Filtro } from "../Filtros";

interface Props {
  id: string;
  label: string;
  step: string;
  filtros: Filtro;
  setFiltros: Dispatch<SetStateAction<Filtro>>;
}

export default function NumberInput({
  id,
  label,
  step,
  filtros,
  setFiltros,
}: Props) {
  return (
    <div>
      <label htmlFor={id} className="text-input-label">
        {label}
      </label>
      <input
        id={id}
        type="number"
        min="0"
        step={step}
        value={filtros[id] || ""}
        onChange={(e) => setFiltros({ ...filtros, [id]: e.target.value })}
        className="text-input"
      />
    </div>
  );
}
