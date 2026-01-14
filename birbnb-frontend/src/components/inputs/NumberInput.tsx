import type { Dispatch, SetStateAction } from "react";
import type { PartialFiltro } from "../../types/Alojamiento";

interface Props {
  id: keyof PartialFiltro;
  label: string;
  step: string;
  filtros: PartialFiltro;
  setFiltros: Dispatch<SetStateAction<PartialFiltro>>;
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
