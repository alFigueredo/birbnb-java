import type { Dispatch, SetStateAction } from "react";
import { type Filtro } from "../Filtros";

interface Props {
  id: string;
  label: string;
  filtros: Filtro;
  setFiltros: Dispatch<SetStateAction<Filtro>>;
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
