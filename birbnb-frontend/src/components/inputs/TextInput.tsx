import type { Dispatch, SetStateAction } from "react";
import type { PartialFiltro } from "../../types/Alojamiento";

interface Props {
  id: keyof PartialFiltro;
  label: string;
  filtros: PartialFiltro;
  setFiltros: Dispatch<SetStateAction<PartialFiltro>>;
}

export default function TextInput({ id, label, filtros, setFiltros }: Props) {
  return (
    <div>
      <label htmlFor={id} className="text-input-label">
        {label}
      </label>
      <input
        className="text-input"
        id={id}
        type="text"
        placeholder={`${label}...`}
        value={filtros[id] || ""}
        onChange={(e) => setFiltros({ ...filtros, [id]: e.target.value })}
      />
    </div>
  );
}
